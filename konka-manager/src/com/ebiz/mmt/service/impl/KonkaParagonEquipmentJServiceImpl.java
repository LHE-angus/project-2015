package com.ebiz.mmt.service.impl;

import java.util.HashMap;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import com.ebiz.mmt.dao.KonkaParagonEquipmentJDao;
import com.ebiz.mmt.domain.KonkaParagonEquipmentJ;
import com.ebiz.mmt.service.KonkaParagonEquipmentJService;

@Service
public class KonkaParagonEquipmentJServiceImpl implements
		KonkaParagonEquipmentJService {

	@Resource
	private KonkaParagonEquipmentJDao konkaParagonEquipmentJDao;

	public Long createKonkaParagonEquipmentJ(KonkaParagonEquipmentJ t) {
		return this.konkaParagonEquipmentJDao.insertEntity(t);
	}

	public Long selectEquipmentNum(KonkaParagonEquipmentJ t) {
		return this.konkaParagonEquipmentJDao.selectEquipmentNum(t);
	}

	public KonkaParagonEquipmentJ getKonkaParagonEquipmentJ(
			KonkaParagonEquipmentJ t) {
		return this.konkaParagonEquipmentJDao.selectEntity(t);
	}

	public Long getKonkaParagonEquipmentJCount(KonkaParagonEquipmentJ t) {
		return this.konkaParagonEquipmentJDao.selectEntityCount(t);
	}

	public List<KonkaParagonEquipmentJ> getKonkaParagonEquipmentJList(
			KonkaParagonEquipmentJ t) {
		return this.konkaParagonEquipmentJDao.selectEntityList(t);
	}

	public int modifyKonkaParagonEquipmentJ(KonkaParagonEquipmentJ t) {
		return this.konkaParagonEquipmentJDao.updateEntity(t);
	}

	public int removeKonkaParagonEquipmentJ(KonkaParagonEquipmentJ t) {
		return this.konkaParagonEquipmentJDao.deleteEntity(t);
	}

	public List<KonkaParagonEquipmentJ> getKonkaParagonEquipmentJPaginatedList(
			KonkaParagonEquipmentJ t) {
		return this.konkaParagonEquipmentJDao.selectEntityPaginatedList(t);
	}

	public List<HashMap<String, String>> selectKonkaParagonEquipmentPaginatedList(
			KonkaParagonEquipmentJ t) throws DataAccessException {
		return this.konkaParagonEquipmentJDao
				.selectKonkaParagonEquipmentPaginatedList(t);
	}
}
