package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.CrmPriceLinesDao;
import com.ebiz.mmt.domain.CrmPriceLines;
import com.ebiz.mmt.service.CrmPriceLinesService;


@Service
public class CrmPriceLinesServiceImpl implements CrmPriceLinesService {

	@Resource
	private CrmPriceLinesDao crmPriceLinesDao;
	

	public Long createCrmPriceLines(CrmPriceLines t) {
		return this.crmPriceLinesDao.insertEntity(t);
	}

	public CrmPriceLines getCrmPriceLines(CrmPriceLines t) {
		return this.crmPriceLinesDao.selectEntity(t);
	}

	public Long getCrmPriceLinesCount(CrmPriceLines t) {
		return this.crmPriceLinesDao.selectEntityCount(t);
	}

	public List<CrmPriceLines> getCrmPriceLinesList(CrmPriceLines t) {
		return this.crmPriceLinesDao.selectEntityList(t);
	}

	public int modifyCrmPriceLines(CrmPriceLines t) {
		return this.crmPriceLinesDao.updateEntity(t);
	}

	public int removeCrmPriceLines(CrmPriceLines t) {
		return this.crmPriceLinesDao.deleteEntity(t);
	}

	public List<CrmPriceLines> getCrmPriceLinesPaginatedList(CrmPriceLines t) {
		return this.crmPriceLinesDao.selectEntityPaginatedList(t);
	}

}
