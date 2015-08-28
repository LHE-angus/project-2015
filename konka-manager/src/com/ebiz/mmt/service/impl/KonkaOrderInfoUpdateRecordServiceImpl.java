package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaOrderInfoUpdateRecordDao;
import com.ebiz.mmt.domain.KonkaOrderInfoUpdateRecord;
import com.ebiz.mmt.service.KonkaOrderInfoUpdateRecordService;

/**
 * @author Wu,Yang
 * @version 2011-12-13 09:56
 */
@Service
public class KonkaOrderInfoUpdateRecordServiceImpl implements KonkaOrderInfoUpdateRecordService {

	@Resource
	private KonkaOrderInfoUpdateRecordDao konkaOrderInfoUpdateRecordDao;

	public Long createKonkaOrderInfoUpdateRecord(KonkaOrderInfoUpdateRecord t) {
		return this.konkaOrderInfoUpdateRecordDao.insertEntity(t);
	}

	public KonkaOrderInfoUpdateRecord getKonkaOrderInfoUpdateRecord(KonkaOrderInfoUpdateRecord t) {
		return this.konkaOrderInfoUpdateRecordDao.selectEntity(t);
	}

	public Long getKonkaOrderInfoUpdateRecordCount(KonkaOrderInfoUpdateRecord t) {
		return this.konkaOrderInfoUpdateRecordDao.selectEntityCount(t);
	}

	public List<KonkaOrderInfoUpdateRecord> getKonkaOrderInfoUpdateRecordList(KonkaOrderInfoUpdateRecord t) {
		return this.konkaOrderInfoUpdateRecordDao.selectEntityList(t);
	}

	public int modifyKonkaOrderInfoUpdateRecord(KonkaOrderInfoUpdateRecord t) {
		return this.konkaOrderInfoUpdateRecordDao.updateEntity(t);
	}

	public int removeKonkaOrderInfoUpdateRecord(KonkaOrderInfoUpdateRecord t) {
		return this.konkaOrderInfoUpdateRecordDao.deleteEntity(t);
	}

	public List<KonkaOrderInfoUpdateRecord> getKonkaOrderInfoUpdateRecordPaginatedList(KonkaOrderInfoUpdateRecord t) {
		return this.konkaOrderInfoUpdateRecordDao.selectEntityPaginatedList(t);
	}

	/**
	 * @author Wu,ShangLong
	 * @version 2013-08-09
	 */
	public List<KonkaOrderInfoUpdateRecord> getKonkaOrderInfoUpdateRecordGroupList(KonkaOrderInfoUpdateRecord t) {
		return this.konkaOrderInfoUpdateRecordDao.selectKonkaOrderInfoUpdateRecordGroupList(t);
	}

}
