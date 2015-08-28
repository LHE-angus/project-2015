package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.SysModuleRoleCanGrantDao;
import com.ebiz.mmt.domain.SysModuleRoleCanGrant;
import com.ebiz.mmt.service.SysModuleRoleCanGrantService;

/**
 * @author Xu,ZhengYang
 * @date 2011-02-09 02:25:40
 */
 
@Service
public class SysModuleRoleCanGrantServiceImpl implements SysModuleRoleCanGrantService {

	@Resource
	private SysModuleRoleCanGrantDao sysModuleRoleCanGrantDao;
	
	public Long createSysModuleRoleCanGrant(SysModuleRoleCanGrant t) {
		return this.sysModuleRoleCanGrantDao.insertEntity(t);
	}
	
	public int modifySysModuleRoleCanGrant(SysModuleRoleCanGrant t) {
		return this.sysModuleRoleCanGrantDao.updateEntity(t);
	}

	public int removeSysModuleRoleCanGrant(SysModuleRoleCanGrant t) {
		return this.sysModuleRoleCanGrantDao.deleteEntity(t);
	}

	public SysModuleRoleCanGrant getSysModuleRoleCanGrant(SysModuleRoleCanGrant t) {
		return this.sysModuleRoleCanGrantDao.selectEntity(t);
	}

	public Long getSysModuleRoleCanGrantCount(SysModuleRoleCanGrant t) {
		return this.sysModuleRoleCanGrantDao.selectEntityCount(t);
	}

	public List<SysModuleRoleCanGrant> getSysModuleRoleCanGrantList(SysModuleRoleCanGrant t) {
		return this.sysModuleRoleCanGrantDao.selectEntityList(t);
	}

	public List<SysModuleRoleCanGrant> getSysModuleRoleCanGrantPaginatedList(SysModuleRoleCanGrant t) {
		return this.sysModuleRoleCanGrantDao.selectEntityPaginatedList(t);
	}
}
