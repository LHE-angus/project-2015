package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.SysObjDataDao;
import com.ebiz.mmt.domain.SysObjData;
import com.ebiz.mmt.service.SysObjDataService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2015-01-16 11:06:13
 */
@Service
public class SysObjDataServiceImpl implements SysObjDataService {

	@Resource
	private SysObjDataDao sysObjDataDao;
	

	public Long createSysObjData(SysObjData t) {
		return this.sysObjDataDao.insertEntity(t);
	}

	public SysObjData getSysObjData(SysObjData t) {
		return this.sysObjDataDao.selectEntity(t);
	}

	public Long getSysObjDataCount(SysObjData t) {
		return this.sysObjDataDao.selectEntityCount(t);
	}

	public List<SysObjData> getSysObjDataList(SysObjData t) {
		return this.sysObjDataDao.selectEntityList(t);
	}

	public int modifySysObjData(SysObjData t) {
		return this.sysObjDataDao.updateEntity(t);
	}

	public int removeSysObjData(SysObjData t) {
		return this.sysObjDataDao.deleteEntity(t);
	}

	public List<SysObjData> getSysObjDataPaginatedList(SysObjData t) {
		return this.sysObjDataDao.selectEntityPaginatedList(t);
	}

}
