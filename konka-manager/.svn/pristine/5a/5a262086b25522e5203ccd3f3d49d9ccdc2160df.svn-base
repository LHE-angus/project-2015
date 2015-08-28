package com.ebiz.mmt.service.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.SysModuleDao;
import com.ebiz.mmt.domain.SysModule;
import com.ebiz.mmt.service.SysModuleService;

/**
 * @author Xu,ZhengYang
 * @date 2010-11-11 02:47:12
 */

@Service
public class SysModuleServiceImpl implements SysModuleService {

	@Resource
	private SysModuleDao sysModuleDao;

	public Long createSysModule(SysModule t) {
		return this.sysModuleDao.insertEntity(t);
	}

	public int modifySysModule(SysModule t) {
		return this.sysModuleDao.updateEntity(t);
	}

	public int removeSysModule(SysModule t) {
		return this.sysModuleDao.deleteEntity(t);
	}

	public SysModule getSysModule(SysModule t) {
		return this.sysModuleDao.selectEntity(t);
	}

	public Long getSysModuleCount(SysModule t) {
		return this.sysModuleDao.selectEntityCount(t);
	}

	public List<SysModule> getSysModuleList(SysModule t) {
		return this.sysModuleDao.selectEntityList(t);
	}

	public List<SysModule> getSysModulePaginatedList(SysModule t) {
		return this.sysModuleDao.selectEntityPaginatedList(t);
	}

	public List<SysModule> getSysModuleListForModPopedom(SysModule t) {
		return this.sysModuleDao.selectSysModuleListForModPopedom(t);
	}

	public List<SysModule> getSysModuleListForLeftTree(SysModule t) {
		return this.sysModuleDao.selectSysModuleListForLeftTree(t);
	}

	public List<SysModule> getSysModuleParentList(SysModule t) {
		return this.sysModuleDao.selectSysModuleParentList(t);
	}

	public List<SysModule> getSysModuleSonList(SysModule t) {
		return this.sysModuleDao.selectSysModuleSonList(t);
	}

	public List<SysModule> getSysModuleListForChildModPopedom(SysModule t) {
		return this.sysModuleDao.selectSysModuleListForChildModPopedom(t);
	}

	public List<SysModule> getSysModuleListForMobileRole(SysModule t) {
		return this.sysModuleDao.selectSysModuleListForMobileRole(t);
	}

	/**
	 * @author Hu,Hao
	 * @version 2013-08-26
	 */
	public void modifySysModuleForFgs(Set<String> t) {
		SysModule sm = new SysModule();
		sm.getMap().put("is_fgs_1", true);
		sm.setIs_view(0);
		this.sysModuleDao.updateEntity(sm);

		Iterator<String> iterator=t.iterator();
		while(iterator.hasNext()){
			SysModule entity = new SysModule();
			entity.setId(Integer.valueOf(iterator.next()));
			entity.setIs_view(1);
			this.sysModuleDao.updateEntity(entity);
		}
	}
}
