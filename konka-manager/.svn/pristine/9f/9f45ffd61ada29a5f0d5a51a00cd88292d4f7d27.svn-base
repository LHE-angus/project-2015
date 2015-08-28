package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.JStocksStoreSummaryDao;
import com.ebiz.mmt.domain.JStocksStoreSummary;
import com.ebiz.mmt.service.JStocksStoreSummaryService;

/**
 * 2015-04-03
 * 客户分仓库存汇总
 * @author Jacky
 */
@Service
public class JStocksStoreSummaryServiceImpl implements JStocksStoreSummaryService {

	@Resource
	private JStocksStoreSummaryDao jStocksStoreSummaryDao;
	

	public Long createJStocksStoreSummary(JStocksStoreSummary t) {
		return this.jStocksStoreSummaryDao.insertEntity(t);
	}

    // public JStocksStoreSummary getJStocksStoreSummary(JStocksStoreSummary t) {
    // return this.jStocksStoreSummaryDao.selectEntity(t);
    // }
    //
    // public Long getJStocksStoreSummaryCount(JStocksStoreSummary t) {
    // return this.jStocksStoreSummaryDao.selectEntityCount(t);
    // }
    //
    // public List<JStocksStoreSummary> getJStocksStoreSummaryList(JStocksStoreSummary t) {
    // return this.jStocksStoreSummaryDao.selectEntityList(t);
    // }

    // public List<JStocksStoreSummary> getJStocksStoreSummaryPaginatedList(JStocksStoreSummary t) {
    // return this.jStocksStoreSummaryDao.selectEntityPaginatedList(t);
    // }


	public int modifyJStocksStoreSummary(JStocksStoreSummary t) {
		return this.jStocksStoreSummaryDao.updateEntity(t);
	}

	public int removeJStocksStoreSummary(JStocksStoreSummary t) {
		return this.jStocksStoreSummaryDao.deleteEntity(t);
	}

	

	/**
	 * 插入List
	 * @param list
	 * @return
	 */
	public Long createJStocksStoreSummaryList(List<JStocksStoreSummary> list){
		return this.jStocksStoreSummaryDao.insertJStocksStoreSummaryList(list);
	}
	/**
	 * 获取客户分仓库存汇总列表
	 * 
	 * @author ChenXiaoqi
	 */
	public Long getJStocksStoreSummaryForR3ShopCount(JStocksStoreSummary t) {
		return this.jStocksStoreSummaryDao.selectJStocksStoreSummaryForR3ShopCount(t);
	}
	/**
	 * 获取客户分仓库存汇总数量
	 * 
	 * @author ChenXiaoqi
	 */
	public Long getJStocksStoreSummaryForR3ShopSumCount(JStocksStoreSummary t) {
		return this.jStocksStoreSummaryDao.selectJStocksStoreSummaryForR3ShopSumCount(t);
	}

	/**
	 * 获取客户分仓库存汇总列表
	 * 
	 * @author ChenXiaoqi
	 */
	public List<JStocksStoreSummary> getJStocksStoreSummaryForR3ShopPaginatedList(JStocksStoreSummary t) {
		return this.jStocksStoreSummaryDao.selectJStocksStoreSummaryForR3ShopPaginatedList(t);
	}
	
	/**
	 * 导出客户分仓库存汇总列表
	 * 
	 * @author ChenXiaoqi
	 */
	public List<JStocksStoreSummary> getJStocksStoreSummaryForR3ShopList(JStocksStoreSummary t){
		return this.jStocksStoreSummaryDao.selectJStocksStoreSummaryForR3ShopList(t);
	}

}
