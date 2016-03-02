package org.tinygroup.bizframedslenterprise.inter.action;

import org.tinygroup.bizframedslenterprise.inter.pojo.TOrganization;


public interface OrganizationAction {
	TOrganization addOrganizationMethod(TOrganization tOrganization);

	void updateOrganizationMethod(TOrganization tOrganization);

	void deleteOrganizationMethod(String organizationIds);
}
