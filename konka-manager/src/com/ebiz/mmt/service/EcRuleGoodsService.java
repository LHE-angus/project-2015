package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.EcRuleGoods;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-05-09 17:52:25
 */
public interface EcRuleGoodsService {

	Long createEcRuleGoods(EcRuleGoods t);

	int modifyEcRuleGoods(EcRuleGoods t);

	int removeEcRuleGoods(EcRuleGoods t);

	EcRuleGoods getEcRuleGoods(EcRuleGoods t);

	List<EcRuleGoods> getEcRuleGoodsList(EcRuleGoods t);

	Long getEcRuleGoodsCount(EcRuleGoods t);

	List<EcRuleGoods> getEcRuleGoodsPaginatedList(EcRuleGoods t);

}