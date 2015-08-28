package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaParagonEquipmentJDao;
import com.ebiz.mmt.dao.KonkaParagonShowshopDetailDao;
import com.ebiz.mmt.domain.KonkaParagonEquipmentJ;
import com.ebiz.mmt.domain.KonkaParagonShowshopDetail;
import com.ebiz.mmt.service.KonkaParagonShowshopDetailService;

@Service
public class KonkaParagonShowshopDetailServiceImpl implements
		KonkaParagonShowshopDetailService {

	@Resource
	private KonkaParagonShowshopDetailDao konkaParagonShowshopDetailDao;

	@Resource
	private KonkaParagonEquipmentJDao konkaParagonEquipmentJDao;

	public Long createKonkaParagonShowshopDetail(KonkaParagonShowshopDetail t) {
		return this.konkaParagonShowshopDetailDao.insertEntity(t);
	}

	public KonkaParagonShowshopDetail getKonkaParagonShowshopDetail(
			KonkaParagonShowshopDetail t) {
		return this.konkaParagonShowshopDetailDao.selectEntity(t);
	}

	public Long getKonkaParagonShowshopDetailCount(KonkaParagonShowshopDetail t) {
		return this.konkaParagonShowshopDetailDao.selectEntityCount(t);
	}

	public List<KonkaParagonShowshopDetail> getKonkaParagonShowshopDetailList(
			KonkaParagonShowshopDetail t) {
		List<KonkaParagonShowshopDetail> konkaParagonShowshopDetailList = this.konkaParagonShowshopDetailDao
				.selectEntityList(t);
		for (KonkaParagonShowshopDetail konkaParagonShowshopDetail : konkaParagonShowshopDetailList) {
			KonkaParagonEquipmentJ konkaParagonEquipmentJ = new KonkaParagonEquipmentJ();
			konkaParagonEquipmentJ.setShow_shop_code(konkaParagonShowshopDetail
					.getShow_shop_code());
			konkaParagonEquipmentJ.getMap().put("etype", 2);
			konkaParagonEquipmentJ.getMap().put("proto_size", 46);
			konkaParagonShowshopDetail.getMap().put(
					"proto_sum",
					konkaParagonEquipmentJDao
							.selectEquipmentNum(konkaParagonEquipmentJ));
		}
		return konkaParagonShowshopDetailList;
	}

	public int modifyKonkaParagonShowshopDetail(KonkaParagonShowshopDetail t) {
		return this.konkaParagonShowshopDetailDao.updateEntity(t);
	}

	public int removeKonkaParagonShowshopDetail(KonkaParagonShowshopDetail t) {
		return this.konkaParagonShowshopDetailDao.deleteEntity(t);
	}

	public List<KonkaParagonShowshopDetail> getKonkaParagonShowshopDetailPaginatedList(
			KonkaParagonShowshopDetail t) {
		List<KonkaParagonShowshopDetail> konkaParagonShowshopDetailList = this.konkaParagonShowshopDetailDao
				.selectEntityPaginatedList(t);
		return konkaParagonShowshopDetailList;
	}

}
