package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.EcGoodsIntegralDao;
import com.ebiz.mmt.domain.EcGoodsIntegral;
import com.ebiz.mmt.service.EcGoodsIntegralService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-12-11 10:26:51
 */
@Service
public class EcGoodsIntegralServiceImpl implements EcGoodsIntegralService {

	@Resource
	private EcGoodsIntegralDao ecGoodsIntegralDao;

	public Long createEcGoodsIntegral(EcGoodsIntegral t) {
		return this.ecGoodsIntegralDao.insertEntity(t);
	}

	public EcGoodsIntegral getEcGoodsIntegral(EcGoodsIntegral t) {
		return this.ecGoodsIntegralDao.selectEntity(t);
	}

	public Long getEcGoodsIntegralCount(EcGoodsIntegral t) {
		return this.ecGoodsIntegralDao.selectEntityCount(t);
	}

	public List<EcGoodsIntegral> getEcGoodsIntegralList(EcGoodsIntegral t) {
		return this.ecGoodsIntegralDao.selectEntityList(t);
	}

	public int modifyEcGoodsIntegral(EcGoodsIntegral t) {
		return this.ecGoodsIntegralDao.updateEntity(t);
	}

	public int removeEcGoodsIntegral(EcGoodsIntegral t) {
		return this.ecGoodsIntegralDao.deleteEntity(t);
	}

	public List<EcGoodsIntegral> getEcGoodsIntegralPaginatedList(EcGoodsIntegral t) {
		return this.ecGoodsIntegralDao.selectEntityPaginatedList(t);
	}

	@Override
	public Long getEcGoodsIntegralNewCount(EcGoodsIntegral t) {
		return this.ecGoodsIntegralDao.selectEcGoodsIntegralNewCount(t);
	}

	@Override
	public List<EcGoodsIntegral> getEcGoodsIntegralNewPaginatedList(EcGoodsIntegral t) {
		return this.ecGoodsIntegralDao.selectEcGoodsIntegralNewPaginatedList(t);
	}

}
