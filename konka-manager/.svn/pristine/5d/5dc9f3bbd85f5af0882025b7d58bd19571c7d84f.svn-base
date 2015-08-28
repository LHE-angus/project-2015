package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaParagonSalesDao;
import com.ebiz.mmt.domain.KonkaParagonSales;
import com.ebiz.mmt.service.KonkaParagonSalesService;


@Service
public class KonkaParagonSalesServiceImpl implements KonkaParagonSalesService {

	@Resource
	private KonkaParagonSalesDao konkaParagonSalesDao;

	public Long createKonkaParagonSales(KonkaParagonSales t) {
		return this.konkaParagonSalesDao.insertEntity(t);
	}

	public KonkaParagonSales getKonkaParagonSales(KonkaParagonSales t) {
		return this.konkaParagonSalesDao.selectEntity(t);
	}

	public Long getKonkaParagonSalesCount(KonkaParagonSales t) {
		return this.konkaParagonSalesDao.selectEntityCount(t);
	}

	public List<KonkaParagonSales> getKonkaParagonSalesList(KonkaParagonSales t) {
		return this.konkaParagonSalesDao.selectEntityList(t);
	}

	public int modifyKonkaParagonSales(KonkaParagonSales t) {
		return this.konkaParagonSalesDao.updateEntity(t);
	}

	public int removeKonkaParagonSales(KonkaParagonSales t) {
		return this.konkaParagonSalesDao.deleteEntity(t);
	}

	public List<KonkaParagonSales> getKonkaParagonSalesPaginatedList(KonkaParagonSales t) {
		return this.konkaParagonSalesDao.selectEntityPaginatedList(t);
	}

}
