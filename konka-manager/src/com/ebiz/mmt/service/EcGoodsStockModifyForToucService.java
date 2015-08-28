package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.EcGoodsStockModifyForTouc;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-09-27 16:41:35
 */
public interface EcGoodsStockModifyForToucService {

	Long createEcGoodsStockModifyForTouc(EcGoodsStockModifyForTouc t);

	int modifyEcGoodsStockModifyForTouc(EcGoodsStockModifyForTouc t);

	int removeEcGoodsStockModifyForTouc(EcGoodsStockModifyForTouc t);

	EcGoodsStockModifyForTouc getEcGoodsStockModifyForTouc(EcGoodsStockModifyForTouc t);

	List<EcGoodsStockModifyForTouc> getEcGoodsStockModifyForToucList(EcGoodsStockModifyForTouc t);

	Long getEcGoodsStockModifyForToucCount(EcGoodsStockModifyForTouc t);

	List<EcGoodsStockModifyForTouc> getEcGoodsStockModifyForToucPaginatedList(EcGoodsStockModifyForTouc t);

}