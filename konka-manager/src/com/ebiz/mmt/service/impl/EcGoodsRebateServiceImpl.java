package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.EcGoodsRebateDao;
import com.ebiz.mmt.domain.EcGoodsRebate;
import com.ebiz.mmt.service.EcGoodsRebateService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-12-26 16:03:16
 */
@Service
public class EcGoodsRebateServiceImpl implements EcGoodsRebateService {

	@Resource
	private EcGoodsRebateDao ecGoodsRebateDao;

	public Long createEcGoodsRebate(EcGoodsRebate t) {
		return this.ecGoodsRebateDao.insertEntity(t);
	}

	public EcGoodsRebate getEcGoodsRebate(EcGoodsRebate t) {
		return this.ecGoodsRebateDao.selectEntity(t);
	}

	public Long getEcGoodsRebateCount(EcGoodsRebate t) {
		return this.ecGoodsRebateDao.selectEntityCount(t);
	}

	public List<EcGoodsRebate> getEcGoodsRebateList(EcGoodsRebate t) {
		return this.ecGoodsRebateDao.selectEntityList(t);
	}

	public int modifyEcGoodsRebate(EcGoodsRebate t) {
		return this.ecGoodsRebateDao.updateEntity(t);
	}

	public int removeEcGoodsRebate(EcGoodsRebate t) {
		return this.ecGoodsRebateDao.deleteEntity(t);
	}

	public List<EcGoodsRebate> getEcGoodsRebatePaginatedList(EcGoodsRebate t) {
		return this.ecGoodsRebateDao.selectEntityPaginatedList(t);
	}

	@Override
	public Long getEcGoodsRebateNewCount(EcGoodsRebate t) {
		return this.ecGoodsRebateDao.selectEcGoodsRebateNewCount(t);
	}

	@Override
	public List<EcGoodsRebate> getEcGoodsRebateNewPaginatedList(EcGoodsRebate t) {
		return this.ecGoodsRebateDao.selectEcGoodsRebateNewPaginatedList(t);
	}

}
