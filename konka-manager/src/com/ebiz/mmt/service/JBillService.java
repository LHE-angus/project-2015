package com.ebiz.mmt.service;

import java.util.HashMap;
import java.util.List;

import com.ebiz.mmt.domain.JBill;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-06-08 17:03:35
 */
public interface JBillService {

	Long createJBill(JBill t);

	int modifyJBill(JBill t);

	int removeJBill(JBill t);

	JBill getJBill(JBill t);

	List<JBill> getJBillList(JBill t);

	Long getJBillCount(JBill t);
	
	/**
	 * 
	 * 
	 * @author Wang,KunLin
	 * @date 2014-03-31
	 */
	Long getJBillCountNameLike(JBill t);

	Long getJBillCountForFourWeek(JBill t);

	List<JBill> getJBillPaginatedList(JBill t);

	/**
	 * @author Wu,ShangLong
	 * @version 2013-6-9
	 */
	Long getSeqJBillId();

	/**
	 * @author Wu,ShangLong
	 * @version 2013-6-9
	 */
	Long createJBillAndDeatails(JBill t);

	/**
	 * @author Wu,ShangLong
	 * @version 2013-6-9
	 */
	int modifyJBillAndDeatails(JBill t);

	/**
	 * @author Wu,ShangLong
	 * @version 2013-6-13
	 */
	int removeJBillAndDetails(JBill t);

	/**
	 * @author Wu,ShangLong
	 * @version 2013-6-17
	 */
	Long createJBillSubSell(JBill t);

	/**
	 * 客户端-分销查询统计
	 * @author Liang Houen
	 * @param t
	 * @return
	 */
	Long getFXJbillCount(JBill t);
	
	/**
	 * 客户端-分销查询列表
	 * @author Liang Houen
	 * @param t
	 * @return
	 */
	List<JBill> getFXJbillList(JBill t);
	
	Long getJBillForTHCount(JBill t);
	
	/**
	 * 获取可做分销退货的订单
	 * @param t
	 * @return
	 */
	List<HashMap> getJBillForTH(JBill t);
	
	/**
	 * 客户端-零售查询统计数量
	 * @author Liang Houen
	 * @since 2015-06-23
	 * @param t
	 * @return
	 */
	Long getSaleDataForkhCount(JBill t);

	/**
	 * 客户端-零售查询列表
	 * @author Liang Houen
	 * @since 2015-06-24
	 * @param t
	 * @return
	 */
	List<HashMap> getSaleDataForkhList(JBill t);

	/**
	 * 查询客户端-销售管理首页数据
	 * @author Liang Houen
	 * @since 2015-7-17
	 * @param t
	 * @return
	 */
	HashMap getInfoForSales(JBill t);

	/**
	 * 客户端-销售管理首页中的月度日销售数据
	 * @author Liang Houen
	 * @since 2015-7-17
	 * @param m
	 * @return
	 */
	List<HashMap> getSaleInfoByDate(HashMap m);

	/**
	 * 客户端-销售管理首页列表
	 * @author Liang Houen
	 * @since 2015-7-21
	 * @param m
	 * @return
	 */
	List<HashMap> getSaleForMonthList(HashMap m);

	/**
	 * 客户端-进货管理进货数据
	 * @author Liang Houen
	 * @since 2015-7-22
	 * @param m
	 * @return
	 */
	HashMap getBuyInfo(HashMap m);

	String getMonthMoney(HashMap m);

	/**
	 * 月进货统计
	 * @author Liang Houen
	 * @since 2015-7-22
	 * @param m
	 * @return
	 */
	List<HashMap> getMonthInDataList(HashMap m);

	/**
	 * 其他产品进货订单查询统计
	 * @author Liang Houen
	 * @since 2015-07-29
	 * @param t
	 * @return
	 */
	Long getOtherSaleDataCount(JBill t);

	List<HashMap> getOtherSaleDataList(JBill m);
}
