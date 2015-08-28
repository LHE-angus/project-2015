package com.ebiz.mmt.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaR3ShopBrandDao;
import com.ebiz.mmt.domain.KonkaR3ShopBrand;
import com.ebiz.mmt.service.KonkaR3ShopBrandService;


@Service
public class KonkaR3ShopBrandServiceImpl implements KonkaR3ShopBrandService {

	@Resource
	private KonkaR3ShopBrandDao konkaR3ShopBrandDao;
	

	public Long createKonkaR3ShopBrand(KonkaR3ShopBrand t) {
		return this.konkaR3ShopBrandDao.insertEntity(t);
	}

	public KonkaR3ShopBrand getKonkaR3ShopBrand(KonkaR3ShopBrand t) {
		return this.konkaR3ShopBrandDao.selectEntity(t);
	}

	public Long getKonkaR3ShopBrandCount(KonkaR3ShopBrand t) {
		return this.konkaR3ShopBrandDao.selectEntityCount(t);
	}

	public List<KonkaR3ShopBrand> getKonkaR3ShopBrandList(KonkaR3ShopBrand t) {
		return this.konkaR3ShopBrandDao.selectEntityList(t);
	}

	public int modifyKonkaR3ShopBrand(KonkaR3ShopBrand t) {
		return this.konkaR3ShopBrandDao.updateEntity(t);
	}

	public int removeKonkaR3ShopBrand(KonkaR3ShopBrand t) {
		return this.konkaR3ShopBrandDao.deleteEntity(t);
	}

	public List<KonkaR3ShopBrand> getKonkaR3ShopBrandPaginatedList(KonkaR3ShopBrand t) {
		return this.konkaR3ShopBrandDao.selectEntityPaginatedList(t);
	}

	@Override
	public String synData() {
		return this.konkaR3ShopBrandDao.computerData();
	}

}
