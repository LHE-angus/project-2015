package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.SfhkOutStorageDao;
import com.ebiz.mmt.domain.SfhkOutStorage;
import com.ebiz.mmt.service.SfhkOutStorageService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-10-24 10:26:43
 */
@Service
public class SfhkOutStorageServiceImpl implements SfhkOutStorageService {

	@Resource
	private SfhkOutStorageDao sfhkOutStorageDao;
	

	public Long createSfhkOutStorage(SfhkOutStorage t) {
		return this.sfhkOutStorageDao.insertEntity(t);
	}

	public SfhkOutStorage getSfhkOutStorage(SfhkOutStorage t) {
		return this.sfhkOutStorageDao.selectEntity(t);
	}

	public Long getSfhkOutStorageCount(SfhkOutStorage t) {
		return this.sfhkOutStorageDao.selectEntityCount(t);
	}

	public List<SfhkOutStorage> getSfhkOutStorageList(SfhkOutStorage t) {
		return this.sfhkOutStorageDao.selectEntityList(t);
	}

	public int modifySfhkOutStorage(SfhkOutStorage t) {
		return this.sfhkOutStorageDao.updateEntity(t);
	}

	public int removeSfhkOutStorage(SfhkOutStorage t) {
		return this.sfhkOutStorageDao.deleteEntity(t);
	}

	public List<SfhkOutStorage> getSfhkOutStoragePaginatedList(SfhkOutStorage t) {
		return this.sfhkOutStorageDao.selectEntityPaginatedList(t);
	}

}
