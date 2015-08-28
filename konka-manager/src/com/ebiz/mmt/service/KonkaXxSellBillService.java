package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.KonkaOrderInfo;
import com.ebiz.mmt.domain.KonkaXxSellBill;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2012-01-09 21:48:11
 */
public interface KonkaXxSellBillService {

	Long createKonkaXxSellBill(KonkaXxSellBill t);

	int modifyKonkaXxSellBill(KonkaXxSellBill t);

	int removeKonkaXxSellBill(KonkaXxSellBill t);

	KonkaXxSellBill getKonkaXxSellBill(KonkaXxSellBill t);

	List<KonkaXxSellBill> getKonkaXxSellBillList(KonkaXxSellBill t);

	Long getKonkaXxSellBillCount(KonkaXxSellBill t);

	List<KonkaXxSellBill> getKonkaXxSellBillPaginatedList(KonkaXxSellBill t);

	/**
	 * @author Ren, zhong
	 * @version 2012-01-10
	 */
	Long createKonkaXxSellBillWithDetails(KonkaXxSellBill t);

	/**
	 * @author Ren, zhong
	 * @version 2012-03-05
	 */
	int modifyKonkaXxSellBillWithDetails(KonkaXxSellBill t,KonkaOrderInfo konkaOrderInfo);

	/**
	 * @author Li,Yuan
	 * @version 2012-01-11
	 */
	Long getKonkaXxSellBillCountForJs(KonkaXxSellBill t);

	/**
	 * @author Li,Yuan
	 * @version 2012-01-11
	 */
	List<KonkaXxSellBill> getKonkaXxSellBillForJSPaginatedList(KonkaXxSellBill t);

	/**
	 * @author hu,hao
	 * @version 2012-03-03
	 */
	Long getKonkaXxSellBillForDeptCount(KonkaXxSellBill t);

	/**
	 * @author hu,hao
	 * @version 2012-03-03
	 */
	List<KonkaXxSellBill> getKonkaXxSellBillForDeptPaginatedList(KonkaXxSellBill t);

	/**
	 * @author Jiang,JiaYong
	 * @version 2012-03-20
	 */
	String getKonkaXxSellBillTotalMoneySum(KonkaXxSellBill t);

	/**
	 * @author Wu,ShangLong
	 * @version 2012-03-20
	 */
	List<KonkaXxSellBill> getKonkaXxSellBillAndDetailList(KonkaXxSellBill t);
	
	/**
	 * @author Ren,zhong
	 * @version 2012-04-06
	 */
	int modifyKonkaXxSellBillForAuditOrder(KonkaXxSellBill t);
	
	/**
	 * @author Jiang,JiaYong
	 * @version 2012-04-06
	 */
	Long getKonkaXxSellBillNumSum(KonkaXxSellBill t);
	
	/**
	 * @author Jiang,JiaYong
	 * @version 2012-04-06
	 */
	KonkaXxSellBill getKonkaXxSellBillMoneySum(KonkaXxSellBill t);
	
	/**
	 * @author Jiang,JiaYong
	 * @version 2012-04-06
	 */
	List<KonkaXxSellBill> getKonkaXxSellBillDeptPdSellMoneySumList(KonkaXxSellBill t);
	
	/**
	 * @author Jiang,JiaYong
	 * @version 2012-04-06
	 */
	List<KonkaXxSellBill> getKonkaXxSellBillDeptPdSellNumSumList(KonkaXxSellBill t);
	
	/**
	 * @author Jiang,JiaYong
	 * @version 2012-05-07
	 */
	List<KonkaXxSellBill> getKonkaXxSellBillListForCountNumSumMoney(KonkaXxSellBill t);
	
	
	/**
	 * @author Ren,zhong
	 * @version 2012-04-06
	 */
	int modifyKonkaXxSellBillWithJsRebate(KonkaXxSellBill t);
	
	/**
	 * @author Ren,zhong
	 * @version 2012-04-12
	 * @desc 物流发货
	 */
	int modifyKonkaXxSellBillWithDetailsLock(KonkaXxSellBill t);
	
	/**
	 * @author Hu,Hao
	 * @version 2013-03-23
	 */
	String getKonkaXxSellBillTotalMoneySumHd(KonkaXxSellBill t);
	
	/**
	 * @author Ren, zhong
	 * @version 2012-01-10
	 */
	Long createKonkaXxSellBillWithOrderInfo(KonkaXxSellBill t1,KonkaOrderInfo t2);

	
	
	/**
	 * @author Wang,KunLin
	 * @version 2014-06-11
	 */
	List<KonkaXxSellBill> getKonkaXxSellBillPaginatedListfordetails(
			KonkaXxSellBill entity);

	Long getKonkaXxSellBillCountfordetails(KonkaXxSellBill entity);
	
}