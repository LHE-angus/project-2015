package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaJxcStockBillDetailsDao;
import com.ebiz.mmt.domain.KonkaJxcStockBillDetails;
import com.ebiz.mmt.service.KonkaJxcStockBillDetailsService;

/**
 * @author Wu,Yang
 * @version 2011-10-11 09:18
 */
@Service
public class KonkaJxcStockBillDetailsServiceImpl implements KonkaJxcStockBillDetailsService {

	@Resource
	private KonkaJxcStockBillDetailsDao konkaJxcStockBillDetailsDao;
	

	public Long createKonkaJxcStockBillDetails(KonkaJxcStockBillDetails t) {
		return this.konkaJxcStockBillDetailsDao.insertEntity(t);
	}

	public KonkaJxcStockBillDetails getKonkaJxcStockBillDetails(KonkaJxcStockBillDetails t) {
		return this.konkaJxcStockBillDetailsDao.selectEntity(t);
	}

	public Long getKonkaJxcStockBillDetailsCount(KonkaJxcStockBillDetails t) {
		return this.konkaJxcStockBillDetailsDao.selectEntityCount(t);
	}

	public List<KonkaJxcStockBillDetails> getKonkaJxcStockBillDetailsList(KonkaJxcStockBillDetails t) {
		return this.konkaJxcStockBillDetailsDao.selectEntityList(t);
	}

	public int modifyKonkaJxcStockBillDetails(KonkaJxcStockBillDetails t) {
		return this.konkaJxcStockBillDetailsDao.updateEntity(t);
	}

	public int removeKonkaJxcStockBillDetails(KonkaJxcStockBillDetails t) {
		return this.konkaJxcStockBillDetailsDao.deleteEntity(t);
	}

	public List<KonkaJxcStockBillDetails> getKonkaJxcStockBillDetailsPaginatedList(KonkaJxcStockBillDetails t) {
		return this.konkaJxcStockBillDetailsDao.selectEntityPaginatedList(t);
	}

	public List<KonkaJxcStockBillDetails> getKonkaJxcStockBillDetailsWithStoreInfoList(KonkaJxcStockBillDetails t) {
		return this.konkaJxcStockBillDetailsDao.selectKonkaJxcStockBillDetailsWithStoreInfoList(t);
	}

}
