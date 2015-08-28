package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaPePdModelLowestPriceDao;
import com.ebiz.mmt.domain.KonkaPePdModelLowestPrice;
import com.ebiz.mmt.service.KonkaPePdModelLowestPriceService;

/**
 * @author Wu,Yang
 * @version 2011-11-30 11:11
 */
@Service
public class KonkaPePdModelLowestPriceServiceImpl implements KonkaPePdModelLowestPriceService {

	@Resource
	private KonkaPePdModelLowestPriceDao konkaPePdModelLowestPriceDao;
	

	public Long createKonkaPePdModelLowestPrice(KonkaPePdModelLowestPrice t) {
		return this.konkaPePdModelLowestPriceDao.insertEntity(t);
	}

	public KonkaPePdModelLowestPrice getKonkaPePdModelLowestPrice(KonkaPePdModelLowestPrice t) {
		return this.konkaPePdModelLowestPriceDao.selectEntity(t);
	}

	public Long getKonkaPePdModelLowestPriceCount(KonkaPePdModelLowestPrice t) {
		return this.konkaPePdModelLowestPriceDao.selectEntityCount(t);
	}

	public List<KonkaPePdModelLowestPrice> getKonkaPePdModelLowestPriceList(KonkaPePdModelLowestPrice t) {
		return this.konkaPePdModelLowestPriceDao.selectEntityList(t);
	}

	public int modifyKonkaPePdModelLowestPrice(KonkaPePdModelLowestPrice t) {
		return this.konkaPePdModelLowestPriceDao.updateEntity(t);
	}

	public int removeKonkaPePdModelLowestPrice(KonkaPePdModelLowestPrice t) {
		return this.konkaPePdModelLowestPriceDao.deleteEntity(t);
	}

	public List<KonkaPePdModelLowestPrice> getKonkaPePdModelLowestPricePaginatedList(KonkaPePdModelLowestPrice t) {
		return this.konkaPePdModelLowestPriceDao.selectEntityPaginatedList(t);
	}

}
