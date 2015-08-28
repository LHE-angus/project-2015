package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.EcGoodsSallarea;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-12-12 10:43:11
 */
public interface EcGoodsSallareaService {

	Long createEcGoodsSallarea(EcGoodsSallarea t);

	int modifyEcGoodsSallarea(EcGoodsSallarea t);

	int removeEcGoodsSallarea(EcGoodsSallarea t);

	EcGoodsSallarea getEcGoodsSallarea(EcGoodsSallarea t);

	List<EcGoodsSallarea> getEcGoodsSallareaList(EcGoodsSallarea t);

	Long getEcGoodsSallareaCount(EcGoodsSallarea t);

	List<EcGoodsSallarea> getEcGoodsSallareaPaginatedList(EcGoodsSallarea t);

}