package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.SysModuleRoleCanGrant;

/**
 * @author Xu,ZhengYang
 * @date 2011-02-09 02:25:40
 */
public interface SysModuleRoleCanGrantService {

	Long createSysModuleRoleCanGrant(SysModuleRoleCanGrant t);

	int modifySysModuleRoleCanGrant(SysModuleRoleCanGrant t);

	int removeSysModuleRoleCanGrant(SysModuleRoleCanGrant t);

	SysModuleRoleCanGrant getSysModuleRoleCanGrant(SysModuleRoleCanGrant t);

	List<SysModuleRoleCanGrant> getSysModuleRoleCanGrantList(SysModuleRoleCanGrant t);

	Long getSysModuleRoleCanGrantCount(SysModuleRoleCanGrant t);

	List<SysModuleRoleCanGrant> getSysModuleRoleCanGrantPaginatedList(SysModuleRoleCanGrant t);
}