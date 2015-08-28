package com.ebiz.mmt.dao;

import java.util.HashMap;
import java.util.List;

import com.ebiz.mmt.domain.JBill;
import com.ebiz.ssi.dao.EntityDao;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-06-08 17:03:35
 */
public interface JBillDao extends EntityDao<JBill> {

	/**
	 * @author Wu,ShangLong
	 * @version 2013-6-9
	 */
	Long selectSeqJBillId();

	Long selectCountForFourWeek(JBill t);
	
	
	/**
	 * @author Wang,KunLin
	 * @version 2014-03-31
	 */
	Long getJBillCountNameLike(JBill t);
	
	Long selectFXJbillCount(JBill t);
	
	Long selectJBillForTHCount(JBill t);
	
	List<JBill> selectFXJbillList(JBill t);
	
	List<HashMap> selectJBillForTH(JBill t);
	
	/**
	 * 客户端-零售查询
	 * @author Liang Houen
	 * @since 2015-06-23
	 * @param t
	 * @return
	 */
	Long getSaleDataForkhCount(JBill t);
	
	/**
	 * 客户端-零售查询列表
	 * @author Liang Houen
	 * @since 2015-6-24
	 * @param t
	 * @return
	 */
	List<HashMap> getSaleDataForkhList(JBill t);
	
	/**
	 * 客户端-销售管理首页
	 * @author Liang Houen
	 * @since 2015-7-17
	 * @param t
	 * @return
	 */
	HashMap selectInfoForSales(JBill t);
	
	/**
	 * 客户端-销售管理首页月度日销售数据
	 * @author Liang Houen
	 * @since 2015-7-17
	 * @param m
	 * @return
	 */
	List<HashMap> selectSaleInfoByDate(HashMap m);
	
	List<HashMap> selectSaleForMonthList(HashMap m);
	
	HashMap selectBuyInfo(HashMap m);
	
	String selectMonthMoney(HashMap m);
	
	List<HashMap> selectMonthInDataList(HashMap m);

	Long selectOtherSaleDataCount(JBill t);

	List<HashMap> selectOtherSaleDataList(JBill m);
}
