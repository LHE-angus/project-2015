package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.EcRuleGoodsDao;
import com.ebiz.mmt.domain.EcRuleGoods;
import com.ebiz.mmt.service.EcRuleGoodsService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-05-09 17:52:25
 */
@Service
public class EcRuleGoodsServiceImpl implements EcRuleGoodsService {

	@Resource
	private EcRuleGoodsDao ecRuleGoodsDao;
	

	public Long createEcRuleGoods(EcRuleGoods t) {
		return this.ecRuleGoodsDao.insertEntity(t);
	}

	public EcRuleGoods getEcRuleGoods(EcRuleGoods t) {
		return this.ecRuleGoodsDao.selectEntity(t);
	}

	public Long getEcRuleGoodsCount(EcRuleGoods t) {
		return this.ecRuleGoodsDao.selectEntityCount(t);
	}

	public List<EcRuleGoods> getEcRuleGoodsList(EcRuleGoods t) {
		return this.ecRuleGoodsDao.selectEntityList(t);
	}

	public int modifyEcRuleGoods(EcRuleGoods t) {
		return this.ecRuleGoodsDao.updateEntity(t);
	}

	public int removeEcRuleGoods(EcRuleGoods t) {
		return this.ecRuleGoodsDao.deleteEntity(t);
	}

	public List<EcRuleGoods> getEcRuleGoodsPaginatedList(EcRuleGoods t) {
		return this.ecRuleGoodsDao.selectEntityPaginatedList(t);
	}

}
