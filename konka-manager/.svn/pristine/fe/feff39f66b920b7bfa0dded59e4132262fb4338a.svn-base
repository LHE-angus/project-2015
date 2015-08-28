package com.ebiz.mmt.service;

import java.util.List;
import java.util.Map;

import com.ebiz.mmt.domain.JStocksSummary;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xiao,GuoJian
 * @date 2014-08-15 15:54:21
 */
public interface JStocksSummaryService {

	Long createJStocksSummary(JStocksSummary t);

	int modifyJStocksSummary(JStocksSummary t);

	int removeJStocksSummary(JStocksSummary t);

	JStocksSummary getJStocksSummary(JStocksSummary t);

	List<JStocksSummary> getJStocksSummaryList(JStocksSummary t);

	Long getJStocksSummaryCount(JStocksSummary t);

	List<JStocksSummary> getJStocksSummaryPaginatedList(JStocksSummary t);

	/**
	 * 获取客户库存汇总列表
	 * 
	 * @author Coder AutoGenerator by Xiao,GuoJian
	 * @date 2014-08-15 15:54:21
	 */
	List<JStocksSummary> getJStocksSummaryForR3ShopList(JStocksSummary t);

	/**
	 * 获取客户库存汇总列表数量
	 * 
	 * @author Coder AutoGenerator by Xiao,GuoJian
	 * @date 2014-08-15 15:54:21
	 */
	Long getJStocksSummaryForR3ShopCount(JStocksSummary t);

	/**
	 * 获取客户库存汇总列表
	 * 
	 * @author Coder AutoGenerator by Xiao,GuoJian
	 * @date 2014-08-15 15:54:21
	 */
	List<JStocksSummary> getJStocksSummaryForR3ShopPaginatedList(JStocksSummary t);
	
	
	/**
	 * 获取客户库存汇总列表 剩余库存总数
	 * 
	 * @author Coder AutoGenerator by Xiao,GuoJian
	 * @date 2014-08-15 15:54:21
	 */
	Long getJStocksSummaryForR3ShopSumCount(JStocksSummary t);
	/**
	 * 插入JStocksSummarylist
	 * 
	 * @author Coder AutoGenerator by Xiao,GuoJian
	 * @date 2014-09-28 15:54:21
	 */
	Long createJStocksSummaryList(List<JStocksSummary> list);
	
	/**
	 * 客户 大类库存周转率
	 * @param t
	 * @return
	 */
	public List<Map<String, Object>> getJcfxParkhList(JStocksSummary t);
	
	/**
	 * 客户小类 库存周转率
	 * @param t
	 * @return
	 */
	public List<Map<String, Object>> getJcfxKhList(JStocksSummary t);
	
	/**
	 * 客户 库存周转率
	 * @param t
	 * @return
	 */
	public List<Map<String, Object>> getJcfxCustList(JStocksSummary t);

	Long getJStocksSummaryLastStocks(JStocksSummary jStocksSummary);
	
	/**
	 * 客户库存预警
	 * 
	 * @param t
	 * @return
	 */
	public Long getCustRepertoryReportListCount(JStocksSummary t);

	/**
	 * 客户库存预警
	 * 
	 * @param t
	 * @return
	 */
	public List<Map<String, Object>> getCustRepertoryReportList(
			JStocksSummary t);
	
	/**
    * 客户型号统计报表 count
    * @param t
    * @return
    */
	public Long getCustGoodsNameReportCount(JStocksSummary t);
	/**
    * 客户型号统计报表list
    * @param t
    * @return
    */
	public List<Map<String, Object>> getCustGoodsNameReportList(JStocksSummary t);
	
	/**
	 * 金额 由数量*现款价计算得出
	 * @param t
	 * @return
	 */
	public List<JStocksSummary> getjcfxzdkhListList(JStocksSummary t);

}