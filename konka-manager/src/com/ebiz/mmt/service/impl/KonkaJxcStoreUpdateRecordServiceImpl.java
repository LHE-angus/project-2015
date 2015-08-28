package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaJxcStoreUpdateRecordDao;
import com.ebiz.mmt.domain.KonkaJxcStoreUpdateRecord;
import com.ebiz.mmt.service.KonkaJxcStoreUpdateRecordService;

/**
 * @author Wu,Yang
 * @version 2011-10-11 09:18
 */
@Service
public class KonkaJxcStoreUpdateRecordServiceImpl implements KonkaJxcStoreUpdateRecordService {

	@Resource
	private KonkaJxcStoreUpdateRecordDao konkaJxcStoreUpdateRecordDao;
	

	public Long createKonkaJxcStoreUpdateRecord(KonkaJxcStoreUpdateRecord t) {
		return this.konkaJxcStoreUpdateRecordDao.insertEntity(t);
	}

	public KonkaJxcStoreUpdateRecord getKonkaJxcStoreUpdateRecord(KonkaJxcStoreUpdateRecord t) {
		return this.konkaJxcStoreUpdateRecordDao.selectEntity(t);
	}

	public Long getKonkaJxcStoreUpdateRecordCount(KonkaJxcStoreUpdateRecord t) {
		return this.konkaJxcStoreUpdateRecordDao.selectEntityCount(t);
	}

	public List<KonkaJxcStoreUpdateRecord> getKonkaJxcStoreUpdateRecordList(KonkaJxcStoreUpdateRecord t) {
		return this.konkaJxcStoreUpdateRecordDao.selectEntityList(t);
	}

	public int modifyKonkaJxcStoreUpdateRecord(KonkaJxcStoreUpdateRecord t) {
		return this.konkaJxcStoreUpdateRecordDao.updateEntity(t);
	}

	public int removeKonkaJxcStoreUpdateRecord(KonkaJxcStoreUpdateRecord t) {
		return this.konkaJxcStoreUpdateRecordDao.deleteEntity(t);
	}

	public List<KonkaJxcStoreUpdateRecord> getKonkaJxcStoreUpdateRecordPaginatedList(KonkaJxcStoreUpdateRecord t) {
		return this.konkaJxcStoreUpdateRecordDao.selectEntityPaginatedList(t);
	}

}
