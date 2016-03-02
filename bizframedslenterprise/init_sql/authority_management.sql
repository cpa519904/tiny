/*
Navicat MySQL Data Transfer

Source Server         : local_mysql
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : authority_management

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2015-06-18 14:38:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_dict
-- ----------------------------
DROP TABLE IF EXISTS `t_dict`;
CREATE TABLE `t_dict` (
  `dict_id` int(11) NOT NULL AUTO_INCREMENT,
  `dict_name` varchar(200) DEFAULT NULL,
  `dict_value` varchar(200) DEFAULT NULL,
  `dict_type` int(11) DEFAULT NULL,
  `sort_num` int(11) DEFAULT NULL,
  PRIMARY KEY (`dict_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_dictitem
-- ----------------------------
DROP TABLE IF EXISTS `t_dictitem`;
CREATE TABLE `t_dictitem` (
  `dictitem_id` int(11) NOT NULL AUTO_INCREMENT,
  `dict_id` int(11) DEFAULT NULL,
  `dictitem_name` varchar(200) DEFAULT NULL,
  `dictitem_value` varchar(200) DEFAULT NULL,
  `sort_num` int(11) DEFAULT NULL,
  PRIMARY KEY (`dictitem_id`),
  KEY `FK_T_DICTIT_REFERENCE_T_DICT` (`dict_id`),
  CONSTRAINT `FK_T_DICTIT_REFERENCE_T_DICT` FOREIGN KEY (`dict_id`) REFERENCES `t_dict` (`dict_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1215 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_function
-- ----------------------------
DROP TABLE IF EXISTS `t_function`;
CREATE TABLE `t_function` (
  `function_id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) DEFAULT NULL,
  `function_name` varchar(32) DEFAULT NULL,
  `function_type` int(11) DEFAULT NULL,
  `function_url` varchar(256) DEFAULT NULL,
  `sort_num` int(11) DEFAULT NULL,
  `function_code` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`function_id`),
  KEY `FK_T_FUNCTI_REFERENCE_T_FUNCTI` (`parent_id`),
  CONSTRAINT `FK_T_FUNCTI_REFERENCE_T_FUNCTI` FOREIGN KEY (`parent_id`) REFERENCES `t_function` (`function_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3309 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_operation_log
-- ----------------------------
DROP TABLE IF EXISTS `t_operation_log`;
CREATE TABLE `t_operation_log` (
  `operator_log_id` int(11) NOT NULL AUTO_INCREMENT,
  `operation_content` varchar(1000) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `operation_ip` varchar(100) DEFAULT NULL,
  `operation_type` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`operator_log_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7103 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_organization
-- ----------------------------
DROP TABLE IF EXISTS `t_organization`;
CREATE TABLE `t_organization` (
  `organization_name` varchar(32) DEFAULT NULL,
  `organization_id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) DEFAULT NULL,
  `company_num` varchar(32) DEFAULT NULL,
  `sort_num` int(11) DEFAULT NULL,
  PRIMARY KEY (`organization_id`),
  KEY `FK_Reference_5` (`parent_id`),
  CONSTRAINT `FK_Reference_5` FOREIGN KEY (`parent_id`) REFERENCES `t_organization` (`organization_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3203 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(32) DEFAULT NULL,
  `role_type` varchar(200) DEFAULT NULL,
  `sort_num` int(11) DEFAULT NULL,
  `role_code` varchar(200) DEFAULT NULL,
  `is_default` int(11) DEFAULT '0' COMMENT '是否是默认角色,默认角色不可删除,非默认角色可删除；默认值为0,即非默认角色(可删除的角色)',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1211 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_role_t_function
-- ----------------------------
DROP TABLE IF EXISTS `t_role_t_function`;
CREATE TABLE `t_role_t_function` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `function_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_T_ROLE_T_REFERENCE_T_FUNCTI` (`function_id`),
  KEY `FK_T_ROLE_T_REFERENCE_T_ROLE` (`role_id`),
  CONSTRAINT `FK_T_ROLE_T_REFERENCE_T_FUNCTI` FOREIGN KEY (`function_id`) REFERENCES `t_function` (`function_id`),
  CONSTRAINT `FK_T_ROLE_T_REFERENCE_T_ROLE` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3415 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_sysparam
-- ----------------------------
DROP TABLE IF EXISTS `t_sysparam`;
CREATE TABLE `t_sysparam` (
  `sysparam_id` int(11) NOT NULL AUTO_INCREMENT,
  `sysparam_name` varchar(32) DEFAULT NULL,
  `sysparam_value` varchar(32) DEFAULT NULL,
  `sysparam_type` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`sysparam_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `organization_id` int(11) DEFAULT NULL,
  `user_name` varchar(32) DEFAULT NULL,
  `user_password` varchar(50) DEFAULT NULL,
  `user_type` varchar(11) DEFAULT NULL COMMENT '0系统用户\r\n            1普通用户',
  `user_status` varchar(11) DEFAULT NULL COMMENT '0正常\r\n            1冻结',
  `sort_num` int(11) DEFAULT NULL,
  `login_name` varchar(200) DEFAULT NULL,
  `is_default` int(11) DEFAULT '0' COMMENT '是否是默认用户,默认用户不可删除,非默认用户可删除；默认值为0,即非默认用户(可删除的用户)',
  PRIMARY KEY (`user_id`),
  KEY `FK_Reference_1` (`organization_id`),
  CONSTRAINT `FK_Reference_1` FOREIGN KEY (`organization_id`) REFERENCES `t_organization` (`organization_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2103 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_user_t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_t_role`;
CREATE TABLE `t_user_t_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_T_USER_T_REFERENCE_T_ROLE` (`role_id`),
  KEY `FK_T_USER_T_REFERENCE_T_USER` (`user_id`),
  CONSTRAINT `FK_T_USER_T_REFERENCE_T_ROLE` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`role_id`),
  CONSTRAINT `FK_T_USER_T_REFERENCE_T_USER` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4613 DEFAULT CHARSET=utf8;
