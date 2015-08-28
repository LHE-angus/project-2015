package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.SyncRecodeSfhkDao;
import com.ebiz.mmt.domain.SyncRecodeSfhk;
import com.ebiz.mmt.service.SyncRecodeSfhkService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-10-23 14:51:56
 */
@Service
public class SyncRecodeSfhkServiceImpl implements SyncRecodeSfhkService {

	@Resource
	private SyncRecodeSfhkDao syncRecodeSfhkDao;
	

	public Long createSyncRecodeSfhk(SyncRecodeSfhk t) {
		return this.syncRecodeSfhkDao.insertEntity(t);
	}

	public SyncRecodeSfhk getSyncRecodeSfhk(SyncRecodeSfhk t) {
		return this.syncRecodeSfhkDao.selectEntity(t);
	}

	public Long getSyncRecodeSfhkCount(SyncRecodeSfhk t) {
		return this.syncRecodeSfhkDao.selectEntityCount(t);
	}

	public List<SyncRecodeSfhk> getSyncRecodeSfhkList(SyncRecodeSfhk t) {
		return this.syncRecodeSfhkDao.selectEntityList(t);
	}

	public int modifySyncRecodeSfhk(SyncRecodeSfhk t) {
		return this.syncRecodeSfhkDao.updateEntity(t);
	}

	public int removeSyncRecodeSfhk(SyncRecodeSfhk t) {
		return this.syncRecodeSfhkDao.deleteEntity(t);
	}

	public List<SyncRecodeSfhk> getSyncRecodeSfhkPaginatedList(SyncRecodeSfhk t) {
		return this.syncRecodeSfhkDao.selectEntityPaginatedList(t);
	}

}
