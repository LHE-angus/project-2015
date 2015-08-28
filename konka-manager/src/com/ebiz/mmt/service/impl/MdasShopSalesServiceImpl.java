package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.MdasShopSalesDao;
import com.ebiz.mmt.domain.MdasShopSales;
import com.ebiz.mmt.service.MdasShopSalesService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2010-10-12 17:31:02
 */
@Service
public class MdasShopSalesServiceImpl implements MdasShopSalesService {

	@Resource
	private MdasShopSalesDao mdasShopSalesDao;

	public Long createMdasShopSales(MdasShopSales t) {
		return this.mdasShopSalesDao.insertEntity(t);
	}

	public MdasShopSales getMdasShopSales(MdasShopSales t) {
		return this.mdasShopSalesDao.selectEntity(t);
	}

	public Long getMdasShopSalesCount(MdasShopSales t) {
		return this.mdasShopSalesDao.selectEntityCount(t);
	}

	public List<MdasShopSales> getMdasShopSalesList(MdasShopSales t) {
		return this.mdasShopSalesDao.selectEntityList(t);
	}

	public int modifyMdasShopSales(MdasShopSales t) {
		return this.mdasShopSalesDao.updateEntity(t);
	}

	public int removeMdasShopSales(MdasShopSales t) {
		return this.mdasShopSalesDao.deleteEntity(t);
	}

	public List<MdasShopSales> getMdasShopSalesPaginatedList(MdasShopSales t) {
		return this.mdasShopSalesDao.selectEntityPaginatedList(t);
	}

}
