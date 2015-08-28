package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaPdModelPricesDao;
import com.ebiz.mmt.domain.KonkaPdModelPrices;
import com.ebiz.mmt.service.KonkaPdModelPricesService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-07-23 15:17:47
 */
@Service
public class KonkaPdModelPricesServiceImpl implements KonkaPdModelPricesService {

	@Resource
	private KonkaPdModelPricesDao konkaPdModelPricesDao;
	

	public Long createKonkaPdModelPrices(KonkaPdModelPrices t) {
		return this.konkaPdModelPricesDao.insertEntity(t);
	}

	public KonkaPdModelPrices getKonkaPdModelPrices(KonkaPdModelPrices t) {
		return this.konkaPdModelPricesDao.selectEntity(t);
	}

	public Long getKonkaPdModelPricesCount(KonkaPdModelPrices t) {
		return this.konkaPdModelPricesDao.selectEntityCount(t);
	}

	public List<KonkaPdModelPrices> getKonkaPdModelPricesList(KonkaPdModelPrices t) {
		return this.konkaPdModelPricesDao.selectEntityList(t);
	}

	public int modifyKonkaPdModelPrices(KonkaPdModelPrices t) {
		return this.konkaPdModelPricesDao.updateEntity(t);
	}

	public int removeKonkaPdModelPrices(KonkaPdModelPrices t) {
		return this.konkaPdModelPricesDao.deleteEntity(t);
	}

	public List<KonkaPdModelPrices> getKonkaPdModelPricesPaginatedList(KonkaPdModelPrices t) {
		return this.konkaPdModelPricesDao.selectEntityPaginatedList(t);
	}

}
