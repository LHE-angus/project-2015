package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.EcCashPriceDao;
import com.ebiz.mmt.domain.EcCashPrice;
import com.ebiz.mmt.service.EcCashPriceService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-12-18 10:23:43
 */
@Service
public class EcCashPriceServiceImpl implements EcCashPriceService {

	@Resource
	private EcCashPriceDao ecCashPriceDao;
	

	public Long createEcCashPrice(EcCashPrice t) {
		return this.ecCashPriceDao.insertEntity(t);
	}

	public EcCashPrice getEcCashPrice(EcCashPrice t) {
		return this.ecCashPriceDao.selectEntity(t);
	}

	public Long getEcCashPriceCount(EcCashPrice t) {
		return this.ecCashPriceDao.selectEntityCount(t);
	}

	public List<EcCashPrice> getEcCashPriceList(EcCashPrice t) {
		return this.ecCashPriceDao.selectEntityList(t);
	}

	public int modifyEcCashPrice(EcCashPrice t) {
		return this.ecCashPriceDao.updateEntity(t);
	}

	public int removeEcCashPrice(EcCashPrice t) {
		return this.ecCashPriceDao.deleteEntity(t);
	}

	public List<EcCashPrice> getEcCashPricePaginatedList(EcCashPrice t) {
		return this.ecCashPriceDao.selectEntityPaginatedList(t);
	}
	
	public String  createEcCashPrice(List<EcCashPrice> list){
		return this.ecCashPriceDao.insertEcCashPrice(list);
	}
}
