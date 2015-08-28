package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.KonkaXxSellBillDetailsDst;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2012-01-11 10:56:03
 */
public interface KonkaXxSellBillDetailsDstService {

	Long createKonkaXxSellBillDetailsDst(KonkaXxSellBillDetailsDst t);

	int modifyKonkaXxSellBillDetailsDst(KonkaXxSellBillDetailsDst t);

	int removeKonkaXxSellBillDetailsDst(KonkaXxSellBillDetailsDst t);

	KonkaXxSellBillDetailsDst getKonkaXxSellBillDetailsDst(KonkaXxSellBillDetailsDst t);

	List<KonkaXxSellBillDetailsDst> getKonkaXxSellBillDetailsDstList(KonkaXxSellBillDetailsDst t);

	Long getKonkaXxSellBillDetailsDstCount(KonkaXxSellBillDetailsDst t);

	List<KonkaXxSellBillDetailsDst> getKonkaXxSellBillDetailsDstPaginatedList(KonkaXxSellBillDetailsDst t);
	
	/**
	 * @author Ren,zhong
	 * @version 2011-01-12
	 */
	Long createKonkaXxSellBillDetailsDstForPdStock(KonkaXxSellBillDetailsDst t);
	
	/**
	 * @author Ren,zhong
	 * @version 2011-04-09
	 */
	List<KonkaXxSellBillDetailsDst> getKonkaXxSellBillDetailsDstForPrintOutOrders(KonkaXxSellBillDetailsDst t);
	
}