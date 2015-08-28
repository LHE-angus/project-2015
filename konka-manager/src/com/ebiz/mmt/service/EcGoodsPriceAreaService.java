package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.EcGoodsPriceArea;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-09-09 10:06:25
 */
public interface EcGoodsPriceAreaService {

	Long createEcGoodsPriceArea(EcGoodsPriceArea t);

	int modifyEcGoodsPriceArea(EcGoodsPriceArea t);

	int removeEcGoodsPriceArea(EcGoodsPriceArea t);

	EcGoodsPriceArea getEcGoodsPriceArea(EcGoodsPriceArea t);

	List<EcGoodsPriceArea> getEcGoodsPriceAreaList(EcGoodsPriceArea t);

	Long getEcGoodsPriceAreaCount(EcGoodsPriceArea t);

	List<EcGoodsPriceArea> getEcGoodsPriceAreaPaginatedList(EcGoodsPriceArea t);

}