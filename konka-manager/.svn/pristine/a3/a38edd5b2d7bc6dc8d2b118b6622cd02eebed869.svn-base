package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.JStocksStoreSummaryDao;
import com.ebiz.mmt.domain.JStocksStoreSummary;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * 2015-04-03
 * 客户分仓库存汇总
 * @author Jacky
 */
@Service
public class JStocksStoreSummaryDaoSqlMapImpl extends EntityDaoSqlMapImpl<JStocksStoreSummary> implements JStocksStoreSummaryDao {

	@Override
	public Long insertJStocksStoreSummaryList(List<JStocksStoreSummary> list) {
		long count = 0l;
		if(list != null && list.size()>0){
			for(JStocksStoreSummary summary:list){
				this.getSqlMapClientTemplate().insert("insertJStocksStoreSummary", summary);
				count ++;
			}
		}
		return count;
	}
	/**
	 * 获取客户分仓库存汇总列表
	 * 
	 * @author ChenXiaoqi
	 */
	@SuppressWarnings("unchecked")
	public List<JStocksStoreSummary> selectJStocksStoreSummaryForR3ShopPaginatedList(JStocksStoreSummary t) {
		return this.getSqlMapClientTemplate().queryForList("selectJStocksStoreSummaryForR3ShopPaginatedList", t);
	}
	/**
	 * 导出客户分仓库存汇总列表
	 * 
	 * @author ChenXiaoqi
	 */
	@SuppressWarnings("unchecked")
	public List<JStocksStoreSummary> selectJStocksStoreSummaryForR3ShopList(JStocksStoreSummary t) {
		return this.getSqlMapClientTemplate().queryForList("selectJStocksStoreSummaryForR3ShopList", t);
	}

	/**
	 * 获取客户分仓库存汇总列表
	 * 
	 * @author ChenXiaoqi
	 */
	public Long selectJStocksStoreSummaryForR3ShopCount(JStocksStoreSummary t) {
		return (Long) this.getSqlMapClientTemplate().queryForObject("selectJStocksStoreSummaryForR3ShopCount", t);
	}
	/**
	 * 获取客户分仓库存汇总数量
	 * 
	 * @author ChenXiaoqi
	 */
	public Long selectJStocksStoreSummaryForR3ShopSumCount(JStocksStoreSummary t) {
		return (Long) this.getSqlMapClientTemplate().queryForObject("selectJStocksStoreSummaryForR3ShopSumCount", t);
	}
}
