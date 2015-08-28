package com.ebiz.mmt.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.JStocksSummaryDao;
import com.ebiz.mmt.domain.JStocksSummary;
import com.ebiz.mmt.service.JStocksSummaryService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-08-15 15:54:21
 */
@Service
public class JStocksSummaryServiceImpl implements JStocksSummaryService {

	@Resource
	private JStocksSummaryDao jStocksSummaryDao;

	public Long createJStocksSummary(JStocksSummary t) {
		return this.jStocksSummaryDao.insertEntity(t);
	}

	public JStocksSummary getJStocksSummary(JStocksSummary t) {
		return this.jStocksSummaryDao.selectEntity(t);
	}

	public Long getJStocksSummaryCount(JStocksSummary t) {
		return this.jStocksSummaryDao.selectEntityCount(t);
	}

	public List<JStocksSummary> getJStocksSummaryList(JStocksSummary t) {
		return this.jStocksSummaryDao.selectEntityList(t);
	}

	public int modifyJStocksSummary(JStocksSummary t) {
		return this.jStocksSummaryDao.updateEntity(t);
	}

	public int removeJStocksSummary(JStocksSummary t) {
		return this.jStocksSummaryDao.deleteEntity(t);
	}

	public List<JStocksSummary> getJStocksSummaryPaginatedList(JStocksSummary t) {
		return this.jStocksSummaryDao.selectEntityPaginatedList(t);
	}

	/**
	 * 获取客户库存汇总列表
	 * 
	 * @author Coder AutoGenerator by Xiao,GuoJian
	 * @date 2014-08-15 15:54:21
	 */
	public List<JStocksSummary> getJStocksSummaryForR3ShopList(JStocksSummary t) {
		return this.jStocksSummaryDao.selectJStocksSummaryForR3ShopList(t);
	}

	/**
	 * 获取客户库存汇总列表
	 * 
	 * @author Coder AutoGenerator by Xiao,GuoJian
	 * @date 2014-08-15 15:54:21
	 */
	public Long getJStocksSummaryForR3ShopCount(JStocksSummary t) {
		return this.jStocksSummaryDao.selectJStocksSummaryForR3ShopCount(t);
	}

	/**
	 * 获取客户库存汇总列表
	 * 
	 * @author Coder AutoGenerator by Xiao,GuoJian
	 * @date 2014-08-15 15:54:21
	 */
	public List<JStocksSummary> getJStocksSummaryForR3ShopPaginatedList(JStocksSummary t) {
		return this.jStocksSummaryDao.selectJStocksSummaryForR3ShopPaginatedList(t);
	}

    /**
     * 获取客户库存汇总列表 剩余库存总数
     */
	@Override
	public Long getJStocksSummaryForR3ShopSumCount(JStocksSummary t) {
		return this.jStocksSummaryDao.selectJStocksSummaryForR3ShopSumCount(t);
	}
	/**
	 * 插入JStocksSummarylist
	 * 
	 * @author Coder AutoGenerator by Xiao,GuoJian
	 * @date 2014-09-28 15:54:21
	 */
	@Override
	public Long createJStocksSummaryList(List<JStocksSummary> list) {
		return this.jStocksSummaryDao.insertJStocksSummaryList(list);
	}
	/**
	 * 客户 库存周转率
	 * @param t
	 * @return
	 */
	@Override
	public List<Map<String, Object>> getJcfxCustList(JStocksSummary t) {
		return this.jStocksSummaryDao.selectJcfxCustList(t);
	}
	/**
	 * 客户小类 库存周转率
	 * @param t
	 * @return
	 */
	@Override
	public List<Map<String, Object>> getJcfxKhList(JStocksSummary t) {
		return this.jStocksSummaryDao.selectJcfxKhList(t);
	}
	/**
	 * 客户 大类库存周转率
	 * @param t
	 * @return
	 */
	@Override
	public List<Map<String, Object>> getJcfxParkhList(JStocksSummary t) {
		return this.jStocksSummaryDao.selectJcfxParkhList(t);
	}

	@Override
	public Long getJStocksSummaryLastStocks(
			JStocksSummary jStocksSummary) {
		
		return this.jStocksSummaryDao.selectJStocksSummaryLastStocks(jStocksSummary);
	}

	@Override
	public List<Map<String, Object>> getCustRepertoryReportList(JStocksSummary t) {
		return this.jStocksSummaryDao.selectCustRepertoryReportList(t);
	}

	@Override
	public Long getCustRepertoryReportListCount(JStocksSummary t) {
		return this.jStocksSummaryDao.selectCustRepertoryReportListCount(t);
	}
	/**
    * 客户型号统计报表 count
    * @param t
    * @return
    */
	@Override
	public Long getCustGoodsNameReportCount(JStocksSummary t) {
		return this.jStocksSummaryDao.selectCustGoodsNameReportCount(t);
	}
	
	/**
    * 客户型号统计报表list
    * @param t
    * @return
    */
	@Override
	public List<Map<String, Object>> getCustGoodsNameReportList(JStocksSummary t) {
		return this.jStocksSummaryDao.selectCustGoodsNameReportList(t);
	}

	@Override
	public List<JStocksSummary> getjcfxzdkhListList(JStocksSummary t) {
		return this.jStocksSummaryDao.selectjcfxzdkhListList(t);
	}


}
