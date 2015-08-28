package com.ebiz.mmt.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaParagonAttentionCDao;
import com.ebiz.mmt.dao.KonkaParagonEquipmentJDao;
import com.ebiz.mmt.dao.KonkaParagonEtcashDao;
import com.ebiz.mmt.dao.KonkaParagonManinfoDao;
import com.ebiz.mmt.dao.KonkaParagonSalesDao;
import com.ebiz.mmt.dao.KonkaParagonShowinfoDao;
import com.ebiz.mmt.dao.KonkaParagonShowtDao;
import com.ebiz.mmt.domain.KonkaParagonAttentionC;
import com.ebiz.mmt.domain.KonkaParagonEquipmentJ;
import com.ebiz.mmt.domain.KonkaParagonEtcash;
import com.ebiz.mmt.domain.KonkaParagonManinfo;
import com.ebiz.mmt.domain.KonkaParagonSales;
import com.ebiz.mmt.domain.KonkaParagonShowinfo;
import com.ebiz.mmt.domain.KonkaParagonShowt;
import com.ebiz.mmt.service.KonkaParagonShowinfoService;

@Service
public class KonkaParagonShowinfoServiceImpl implements
		KonkaParagonShowinfoService {

	@Resource
	private KonkaParagonShowinfoDao konkaParagonShowinfoDao;
	@Resource
	private KonkaParagonManinfoDao konkaParagonManinfoDao;
	@Resource
	private KonkaParagonEquipmentJDao konkaParagonEquipmentJDao;
	@Resource
	private KonkaParagonSalesDao konkaParagonSalesDao;
	@Resource
	private KonkaParagonEtcashDao konkaParagonEtcashDao;
	@Resource
	private KonkaParagonShowtDao konkaParagonShowtDao;
	@Resource
	private KonkaParagonAttentionCDao konkaParagonAttentionCDao;

	public Long createKonkaParagonShowinfo(KonkaParagonShowinfo t) {
		return this.konkaParagonShowinfoDao.insertEntity(t);
	}

	public KonkaParagonShowinfo getKonkaParagonShowinfo(KonkaParagonShowinfo t) {
		return this.konkaParagonShowinfoDao.selectEntity(t);
	}

	public Long getKonkaParagonShowinfoCount(KonkaParagonShowinfo t) {
		return this.konkaParagonShowinfoDao.selectEntityCount(t);
	}

	public List<KonkaParagonShowinfo> getKonkaParagonShowinfoList(
			KonkaParagonShowinfo t) {
		return this.konkaParagonShowinfoDao.selectEntityList(t);
	}

	public int modifyKonkaParagonShowinfo(KonkaParagonShowinfo t) {
		return this.konkaParagonShowinfoDao.updateEntity(t);
	}

	public int removeKonkaParagonShowinfo(KonkaParagonShowinfo t) {
		return this.konkaParagonShowinfoDao.deleteEntity(t);
	}

	public List<KonkaParagonShowinfo> getKonkaParagonShowinfoPaginatedList(
			KonkaParagonShowinfo t) {
		return this.konkaParagonShowinfoDao.selectEntityPaginatedList(t);
	}

	public List<KonkaParagonShowinfo> selectDistinctShopCode(
			KonkaParagonShowinfo t) {
		return this.konkaParagonShowinfoDao.selectDistinctShopCode(t);
	}

	public KonkaParagonShowinfo getKonkaParagonShowinfoForView(
			KonkaParagonShowinfo t) {
		return this.konkaParagonShowinfoDao.selectEntityForView(t);
	}

	public String generateShopCode(KonkaParagonShowinfo t) {
		String str = "";
		str = this.konkaParagonShowinfoDao.generateShopCode(t);
		return str;
	}
	
	
	public Long getKonkaParagonShowinfoListForSelctCount(KonkaParagonShowinfo t) {
		return this.konkaParagonShowinfoDao.selectEntityListForSelctCount(t);
	}
	
	public List<KonkaParagonShowinfo> getKonkaParagonShowinfoListForSelct(
			KonkaParagonShowinfo t) {
		return this.konkaParagonShowinfoDao.selectEntityListForSelct(t);
	}

	public List<KonkaParagonShowinfo> selectKonkaParagonShowinfoPaginatedListForSub(
			KonkaParagonShowinfo t) throws DataAccessException {
		return this.konkaParagonShowinfoDao
				.selectKonkaParagonShowinfoPaginatedListForSub(t);
	}

	public long selectKonkaParagonShowinfoCountForsub(KonkaParagonShowinfo t)
			throws DataAccessException {
		return this.konkaParagonShowinfoDao
				.selectKonkaParagonShowinfoCountForsub(t);
	}

	public void saveKonkaParagonShowinfo(KonkaParagonShowinfo t) {
		// 处理业务员
		KonkaParagonManinfo _tman = new KonkaParagonManinfo();
		_tman.setShow_shop_code(t.getShow_shop_code());
		_tman.setFixdate(t.getMap().get("fixdate").toString());
		this.konkaParagonManinfoDao.deleteEntity(_tman);
		for (KonkaParagonManinfo _t : t.getManList()) {
			this.konkaParagonManinfoDao.insertEntity(_t);
		}
		// 处理演示设备
		KonkaParagonEquipmentJ _tset = new KonkaParagonEquipmentJ();
		_tset.setShow_shop_code(t.getShow_shop_code());
		_tset.setFixdate(t.getMap().get("fixdate").toString());
		this.konkaParagonEquipmentJDao.deleteEntity(_tset);
		for (KonkaParagonEquipmentJ _t : t.getSet1List()) {
			this.konkaParagonEquipmentJDao.insertEntity(_t);
		}
		// 处理样机
		for (KonkaParagonEquipmentJ _t : t.getSet2List()) {
			this.konkaParagonEquipmentJDao.insertEntity(_t);
		}
		if (t.getSales() != null) {
			// 处理销售额
			KonkaParagonSales _t = new KonkaParagonSales();
			_t.setShow_shop_code(t.getShow_shop_code());
			_t.setSale_year(t.getMap().get("fixdate").toString());
			this.konkaParagonSalesDao.deleteEntity(_t);
			this.konkaParagonSalesDao.insertEntity(t.getSales());
		}
		if (t.getEtcash() != null) {
			// 处理进场费
			KonkaParagonEtcash _t = new KonkaParagonEtcash();
			_t.setShow_shop_code(t.getShow_shop_code());
			_t.setEt_year(t.getMap().get("fixdate").toString());
			this.konkaParagonEtcashDao.deleteEntity(_t);
			this.konkaParagonEtcashDao.insertEntity(t.getEtcash());
		}
		// 处理展台展柜
		KonkaParagonShowt _tshow = new KonkaParagonShowt();
		_tshow.setShow_shop_code(t.getShow_shop_code());
		_tshow.setFixdate(t.getMap().get("fixdate").toString());
		this.konkaParagonShowtDao.deleteEntity(_tshow);
		for (KonkaParagonShowt _item : t.getShowtList()) {
			this.konkaParagonShowtDao.insertEntity(_item);
		}

		// 生成提示
		KonkaParagonAttentionC _atc = new KonkaParagonAttentionC();
		_atc.setShow_shop_code(t.getShow_shop_code());
		_atc.setAddtime(new Date());
		_atc.setFixdate(t.getMap().get("fixdate").toString());
		this.konkaParagonAttentionCDao.insertEntity(_atc);

		// 保存主项
		// if (t.getShow_shop_id() != null)
		// this.konkaParagonShowinfoDao.updateEntity(t);
		// else
		// this.konkaParagonShowinfoDao.insertEntity(t);
	}
}
