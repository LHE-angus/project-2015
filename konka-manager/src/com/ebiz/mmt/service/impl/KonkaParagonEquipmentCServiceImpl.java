package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaParagonEquipmentCDao;
import com.ebiz.mmt.domain.KonkaParagonEquipmentC;
import com.ebiz.mmt.service.KonkaParagonEquipmentCService;

@Service
public class KonkaParagonEquipmentCServiceImpl implements
		KonkaParagonEquipmentCService {

	@Resource
	private KonkaParagonEquipmentCDao konkaParagonEquipmentCDao;

	public Long createKonkaParagonEquipmentC(KonkaParagonEquipmentC t) {
		return this.konkaParagonEquipmentCDao.insertEntity(t);
	}

	public KonkaParagonEquipmentC getKonkaParagonEquipmentC(
			KonkaParagonEquipmentC t) {
		return this.konkaParagonEquipmentCDao.selectEntity(t);
	}

	public Long getKonkaParagonEquipmentCCount(KonkaParagonEquipmentC t) {
		return this.konkaParagonEquipmentCDao.selectEntityCount(t);
	}

	public List<KonkaParagonEquipmentC> getKonkaParagonEquipmentCList(
			KonkaParagonEquipmentC t) {
		return this.konkaParagonEquipmentCDao.selectEntityList(t);
	}

	public int modifyKonkaParagonEquipmentC(KonkaParagonEquipmentC t) {
		return this.konkaParagonEquipmentCDao.updateEntity(t);
	}

	public int removeKonkaParagonEquipmentC(KonkaParagonEquipmentC t) {
		return this.konkaParagonEquipmentCDao.deleteEntity(t);
	}

	public List<KonkaParagonEquipmentC> getKonkaParagonEquipmentCPaginatedList(
			KonkaParagonEquipmentC t) {
		return this.konkaParagonEquipmentCDao.selectEntityPaginatedList(t);
	}

	public List<KonkaParagonEquipmentC> getKonkaParagonEquipmentList(
			KonkaParagonEquipmentC t) {
		return this.konkaParagonEquipmentCDao.selectKonkaParagonEquipment(t);
	}

}
