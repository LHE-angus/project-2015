package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaMobilePdBrandDao;
import com.ebiz.mmt.domain.KonkaMobilePdBrand;
import com.ebiz.mmt.service.KonkaMobilePdBrandService;

/**
 * @author Ren,zhong
 * @version 2013-05-31 14:00
 */
@Service
public class KonkaMobilePdBrandServiceImpl implements KonkaMobilePdBrandService {

	@Resource
	private KonkaMobilePdBrandDao konkaMobilePdBrandDao;
	

	public Long createKonkaMobilePdBrand(KonkaMobilePdBrand t) {
		return this.konkaMobilePdBrandDao.insertEntity(t);
	}

	public KonkaMobilePdBrand getKonkaMobilePdBrand(KonkaMobilePdBrand t) {
		return this.konkaMobilePdBrandDao.selectEntity(t);
	}

	public Long getKonkaMobilePdBrandCount(KonkaMobilePdBrand t) {
		return this.konkaMobilePdBrandDao.selectEntityCount(t);
	}

	public List<KonkaMobilePdBrand> getKonkaMobilePdBrandList(KonkaMobilePdBrand t) {
		return this.konkaMobilePdBrandDao.selectEntityList(t);
	}

	public int modifyKonkaMobilePdBrand(KonkaMobilePdBrand t) {
		return this.konkaMobilePdBrandDao.updateEntity(t);
	}

	public int removeKonkaMobilePdBrand(KonkaMobilePdBrand t) {
		return this.konkaMobilePdBrandDao.deleteEntity(t);
	}

	public List<KonkaMobilePdBrand> getKonkaMobilePdBrandPaginatedList(KonkaMobilePdBrand t) {
		return this.konkaMobilePdBrandDao.selectEntityPaginatedList(t);
	}

}
