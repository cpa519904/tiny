package org.tinygroup.bizframedslenterprise.impl.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tinygroup.bizframedslenterprise.inter.action.UserAction;
import org.tinygroup.bizframedslenterprise.inter.log.LogAdapter;
import org.tinygroup.bizframedslenterprise.inter.pojo.TUser;
import org.tinygroup.bizframedslenterprise.inter.user.UserService;
import org.tinygroup.weblayer.WebContext;
import org.tinygroup.weblayer.mvc.WebContextAware;

import java.io.IOException;


@Controller
public class UserActionImpl extends BaseAction implements UserAction, WebContextAware {
    private WebContext webContext;

    @Autowired
    private UserService userService;

    @Autowired
    private LogAdapter logAdapter;

    public void setContext(WebContext webContext) {
        this.webContext = webContext;
    }

    @RequestMapping(value = {"/addUser.do"})
    public @ResponseBody TUser addUserMethod(TUser tUser) {
        writeOperatorLog(logAdapter, webContext, 1, "开始增加用户");
        TUser user = userService.addUser(tUser);
        writeOperatorLog(logAdapter, webContext, 1, "完成增加用户");
        return user;
    }

    @RequestMapping(value = {"/updateUser.do"})
    public @ResponseBody void updateUserMethod(TUser tUser) {
        writeOperatorLog(logAdapter, webContext, 1, "开始更新用户");
        userService.updateUser(tUser);
        writeOperatorLog(logAdapter, webContext, 1, "完成更新用户");
    }

    @RequestMapping(value = {"/deleteUser.do"})
    public @ResponseBody void delteteUserMethod(String userIds) {
        writeOperatorLog(logAdapter, webContext, 1, "开始增加用户");
        userService.deleteUsers(userIds);
        writeOperatorLog(logAdapter, webContext, 1, "完成增加用户");
    }

    @RequestMapping(value = {"/updateAuthorizeRolesUsers.do"})
    public @ResponseBody void updateAuthorizeRolesUsersMethod(int roleIds, String authorizeUserIds, String removeUserIds) {
        writeOperatorLog(logAdapter, webContext, 1, "开始给角色分配用户");
        userService.updateAuthorizeRolesUsers(roleIds, authorizeUserIds, removeUserIds);
        writeOperatorLog(logAdapter, webContext, 1, "完成给角色分配用户");
    }

    @RequestMapping(value = {"/authRoleInsteadByString.do"})
    public  @ResponseBody void authRoleInsteadByStringMethod(int userId, String roleIds, String removeRoleIds) {
        writeOperatorLog(logAdapter, webContext, 1, "开始给用户分配角色");
        userService.authorizeRoleInsteadByString(userId, roleIds, removeRoleIds);
        writeOperatorLog(logAdapter, webContext, 1, "完成给用户分配角色");
    }
    
    @RequestMapping(value = {"/addUserRoles.do"})
    public  @ResponseBody void addUserRoles(int userId, Integer[] roleId) {
        writeOperatorLog(logAdapter, webContext, 1, "开始给用户分配角色");
        webContext.put("roleId", roleId);
        callService("addUserRoles",webContext);
        writeOperatorLog(logAdapter, webContext, 1, "完成给用户分配角色");
    }
    
    @RequestMapping(value = {"/removeUserRoles.do"})
    public  @ResponseBody void removeUserRoles(int userId, Integer[] roleId) {
        writeOperatorLog(logAdapter, webContext, 1, "开始取消用户角色");
        webContext.put("roleId", roleId);
        callService("removeUserRoles",webContext);
        writeOperatorLog(logAdapter, webContext, 1, "完成取消用户角色");
    }

    public  @ResponseBody boolean isLoginNameExit(String loginName) {
        TUser tUser = new TUser();
        tUser.setLoginName(loginName);
        return userService.isUserExist(tUser);
    }

    @RequestMapping(value = {"/sysLogin.do"})
    public  @ResponseBody void loginMethod(String loginname, String password) {
        int result = 1;
        if (webContext.get("loginname") != null && webContext.get("password") != null) {
            TUser tUser = userService.loginValidate((String) webContext.get("loginname"), (String) webContext.get("password"));
            if (tUser != null) {
                result = 0;
                webContext.getRequest().getSession().setAttribute("currentUserId", tUser.getUserId());
            }
        }
        try {
            webContext.getResponse().getWriter().print(result);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}