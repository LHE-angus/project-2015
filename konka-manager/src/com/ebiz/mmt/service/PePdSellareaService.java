package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.PePdSellarea;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2011-03-22 14:44:58
 */
public interface PePdSellareaService {

	Long createPePdSellarea(PePdSellarea t);

	int modifyPePdSellarea(PePdSellarea t);

	int removePePdSellarea(PePdSellarea t);

	PePdSellarea getPePdSellarea(PePdSellarea t);

	List<PePdSellarea> getPePdSellareaList(PePdSellarea t);

	Long getPePdSellareaCount(PePdSellarea t);

	List<PePdSellarea> getPePdSellareaPaginatedList(PePdSellarea t);

	/**
	 * @author Wu,ShangLong
	 * @version 2011-03-21
	 */
	void createPePdSellareaList(PePdSellarea t, String[] p_index_list);

}