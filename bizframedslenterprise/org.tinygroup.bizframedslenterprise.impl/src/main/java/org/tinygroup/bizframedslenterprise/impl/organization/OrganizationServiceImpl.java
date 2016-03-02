package org.tinygroup.bizframedslenterprise.impl.organization;

import java.util.List;

import org.tinygroup.bizframedslenterprise.impl.BaseService;
import org.tinygroup.bizframedslenterprise.impl.dao.TOrganizationDao;
import org.tinygroup.bizframedslenterprise.inter.organization.OrganizationService;
import org.tinygroup.bizframedslenterprise.inter.pojo.TOrganization;
import org.tinygroup.bizframedslenterprise.inter.util.Pager;

public class OrganizationServiceImpl extends BaseService implements
		OrganizationService {
	private TOrganizationDao tOrganizationDao;
	
	public TOrganizationDao gettOrganizationDao() {
		return tOrganizationDao;
	}

	public void settOrganizationDao(TOrganizationDao tOrganizationDao) {
		this.tOrganizationDao = tOrganizationDao;
	}

	public TOrganization addOrganization(TOrganization tOrganization) {
		return tOrganizationDao.insertTOrganization(tOrganization);
	}

	public void updateOrganization(TOrganization tOrganization) {
		tOrganizationDao.updateTOrganization(tOrganization);
	}

	public void deleteOrganization(int organizationId) {
		tOrganizationDao.deleteTOrganization(organizationId);
	}

	public void deleteOrganizations(String organizationIds) {
		tOrganizationDao.deleteTOrganizationByIds(toArray(organizationIds));
	}

	public TOrganization getOrganization(int organizationId) {
		return tOrganizationDao.getTOrganizationById(organizationId);
	}

	public List<TOrganization> getSubOrganizations(int organizationId) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<TOrganization> getOrganizations(TOrganization tOrganization) {
		return tOrganizationDao.queryTOrganizations(tOrganization);
	}

	public Pager getOrganizations(TOrganization tOrganization,
			int start, int limit) {
		// TODO Auto-generated method stub
		return tOrganizationDao.queryTOrganizations(tOrganization, start, limit);
	}

	public Pager getOrganizationPager(
			TOrganization tOrganization, int start, int limit) {
		return tOrganizationDao.queryTOrganizations(tOrganization,start,limit);
	}

	public List<TOrganization> getRootOrganizations() {
		return tOrganizationDao.queryTOrganizations(null);
	}

	public Pager getRootOrganizationsPager(
			int start, int limit) {
		return tOrganizationDao.queryRootTOrganizations(null,start,limit);
	}

	

}
