package org.tinygroup.bizframedslenterprise.inter.organization;

import java.util.List;

import org.tinygroup.bizframedslenterprise.inter.pojo.TOrganization;
import org.tinygroup.bizframedslenterprise.inter.util.Pager;


public interface OrganizationService {
	
	TOrganization addOrganization(TOrganization tOrganization);

	void updateOrganization(TOrganization tOrganization);

	void deleteOrganization(int organizationId);
	
	void deleteOrganizations(String organizationIds);

	TOrganization getOrganization(int organizationId);
	
	List<TOrganization> getSubOrganizations(int organizationId);

	List<TOrganization> getOrganizations(TOrganization tOrganization);

	Pager getOrganizations(TOrganization tOrganization, int start,
			int limit);
	Pager getOrganizationPager(TOrganization tOrganization, int start,
			int limit);
			
	List<TOrganization> getRootOrganizations();
	
	Pager getRootOrganizationsPager(int start,int limit);
}
