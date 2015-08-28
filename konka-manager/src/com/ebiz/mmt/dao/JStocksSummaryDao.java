package com.ebiz.mmt.dao;

import java.util.List;
import java.util.Map;

import com.ebiz.mmt.domain.JStocksSummary;
import com.ebiz.ssi.dao.EntityDao;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xiao,GuoJian
 * @date 2014-08-15 15:54:21
 */
public interface JStocksSummaryDao extends EntityDao<JStocksSummary> {
	/**
	 * 获取客户库存汇总列表
	 * 
	 * @author Coder AutoGenerator by Xiao,GuoJian
	 * @date 2014-08-15 15:54:21
	 */
	public List<JStocksSummary> selectJStocksSummaryForR3ShopList(JStocksSummary t);

	/**
	 * 获取客户库存汇总列表
	 * 
	 * @author Coder AutoGenerator by Xiao,GuoJian
	 * @date 2014-08-15 15:54:21
	 */
	public Long selectJStocksSummaryForR3ShopCount(JStocksSummary t);

	/**
	 * 获取客户库存汇总列表
	 * 
	 * @author Coder AutoGenerator by Xiao,GuoJian
	 * @date 2014-08-15 15:54:21
	 */
	public List<JStocksSummary> selectJStocksSummaryForR3ShopPaginatedList(JStocksSummary t);
	
	/**
	 * 获取客户库存汇总列表库存总数
	 * 
	 * @author Coder AutoGenerator by Xiao,GuoJian
	 * @date 2014-08-15 15:54:21
	 */
	public Long selectJStocksSummaryForR3ShopSumCount(JStocksSummary t);
	/**
	 * 插入JStocksSummarylist
	 * 
	 * @author Coder AutoGenerator by Xiao,GuoJian
	 * @date 2014-09-28 15:54:21
	 */
	public Long insertJStocksSummaryList(List<JStocksSummary> list);
	
	/**
	 * 客户 大类库存周转率
	 * @param t
	 * @return
	 */
	public List<Map<String, Object>> selectJcfxParkhList(JStocksSummary t);
	
	/**
	 * 客户小类 库存周转率
	 * @param t
	 * @return
	 */
	public List<Map<String, Object>> selectJcfxKhList(JStocksSummary t);
	
	/**
	 * 客户 库存周转率
	 * @param t
	 * @return
	 */
	public List<Map<String, Object>> selectJcfxCustList(JStocksSummary t);

	public Long selectJStocksSummaryLastStocks(
			JStocksSummary jStocksSummary);
   /**
    * 客户库存预警
    * @param t
    * @return
    */
	public Long selectCustRepertoryReportListCount(JStocksSummary t);
	/**
	    * 客户库存预警
	    * @param t
	    * @return
	    */
	public List<Map<String, Object>> selectCustRepertoryReportList(JStocksSummary t);
	
	/**
    * 客户型号统计报表 count
    * @param t
    * @return
    */
	public Long selectCustGoodsNameReportCount(JStocksSummary t);
	/**
	    * 客户型号统计报表list
	    * @param t
	    * @return
	    */
	public List<Map<String, Object>> selectCustGoodsNameReportList(JStocksSummary t);
	
	/**
	 * 金额 由数量*现款价计算得出
	 * @param t
	 * @return
	 */
	public List<JStocksSummary> selectjcfxzdkhListList(JStocksSummary t);
	
}
