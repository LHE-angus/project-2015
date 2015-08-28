package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.SysSettingDao;
import com.ebiz.mmt.domain.SysSetting;
import com.ebiz.mmt.service.SysSettingService;

/**
 * @author Jin,QingHua
 * @version 2010-04-30 16:57:15
 */
@Service
public class SysSettingServiceImpl implements SysSettingService {

	@Resource
	private SysSettingDao sysSettingDao;

	public Long createSysSetting(SysSetting t) {
		return this.sysSettingDao.insertEntity(t);
	}

	public int modifySysSetting(SysSetting t) {
		return this.sysSettingDao.updateEntity(t);
	}

	public int removeSysSetting(SysSetting t) {
		return this.sysSettingDao.deleteEntity(t);
	}

	public SysSetting getSysSetting(SysSetting t) {
		return this.sysSettingDao.selectEntity(t);
	}

	public Long getSysSettingCount(SysSetting t) {
		return this.sysSettingDao.selectEntityCount(t);
	}

	public List<SysSetting> getSysSettingList(SysSetting t) {
		return this.sysSettingDao.selectEntityList(t);
	}

	public List<SysSetting> getSysSettingPaginatedList(SysSetting t) {
		return this.sysSettingDao.selectEntityPaginatedList(t);
	}

}
