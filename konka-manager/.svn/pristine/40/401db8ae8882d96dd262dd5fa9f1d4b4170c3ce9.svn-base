package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaJxcHhRecordDao;
import com.ebiz.mmt.domain.KonkaJxcHhRecord;
import com.ebiz.mmt.service.KonkaJxcHhRecordService;

/**
 * @author Wu,Yang
 * @version 2011-10-11 09:18
 */
@Service
public class KonkaJxcHhRecordServiceImpl implements KonkaJxcHhRecordService {

	@Resource
	private KonkaJxcHhRecordDao konkaJxcHhRecordDao;
	

	public Long createKonkaJxcHhRecord(KonkaJxcHhRecord t) {
		return this.konkaJxcHhRecordDao.insertEntity(t);
	}

	public KonkaJxcHhRecord getKonkaJxcHhRecord(KonkaJxcHhRecord t) {
		return this.konkaJxcHhRecordDao.selectEntity(t);
	}

	public Long getKonkaJxcHhRecordCount(KonkaJxcHhRecord t) {
		return this.konkaJxcHhRecordDao.selectEntityCount(t);
	}

	public List<KonkaJxcHhRecord> getKonkaJxcHhRecordList(KonkaJxcHhRecord t) {
		return this.konkaJxcHhRecordDao.selectEntityList(t);
	}

	public int modifyKonkaJxcHhRecord(KonkaJxcHhRecord t) {
		return this.konkaJxcHhRecordDao.updateEntity(t);
	}

	public int removeKonkaJxcHhRecord(KonkaJxcHhRecord t) {
		return this.konkaJxcHhRecordDao.deleteEntity(t);
	}

	public List<KonkaJxcHhRecord> getKonkaJxcHhRecordPaginatedList(KonkaJxcHhRecord t) {
		return this.konkaJxcHhRecordDao.selectEntityPaginatedList(t);
	}

}
