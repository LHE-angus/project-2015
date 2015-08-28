package com.ebiz.mmt.dao.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.JStocksSummaryDao;
import com.ebiz.mmt.domain.JStocksSummary;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-08-15 15:54:21
 */
@Service
public class JStocksSummaryDaoSqlMapImpl extends EntityDaoSqlMapImpl<JStocksSummary> implements JStocksSummaryDao {

	

	/**
	 * 获取客户库存汇总列表
	 * 
	 * @author Coder AutoGenerator by Xiao,GuoJian
	 * @date 2014-08-15 15:54:21
	 */
	public List<JStocksSummary> selectJStocksSummaryForR3ShopList(JStocksSummary t) {
		return this.getSqlMapClientTemplate().queryForList("selectJStocksSummaryForR3ShopList", t);
	}

	/**
	 * 获取客户库存汇总列表
	 * 
	 * @author Coder AutoGenerator by Xiao,GuoJian
	 * @date 2014-08-15 15:54:21
	 */
	public Long selectJStocksSummaryForR3ShopCount(JStocksSummary t) {
		return (Long) this.getSqlMapClientTemplate().queryForObject("selectJStocksSummaryForR3ShopCount", t);
	}

	/**
	 * 获取客户库存汇总列表
	 * 
	 * @author Coder AutoGenerator by Xiao,GuoJian
	 * @date 2014-08-15 15:54:21
	 */
	public List<JStocksSummary> selectJStocksSummaryForR3ShopPaginatedList(JStocksSummary t) {
		return this.getSqlMapClientTemplate().queryForList("selectJStocksSummaryForR3ShopPaginatedList", t);
	}
	/**
	 * 获取客户库存汇总列表 剩余库存总数
	 */
	@Override
	public Long selectJStocksSummaryForR3ShopSumCount(JStocksSummary t) {
		return (Long) this.getSqlMapClientTemplate().queryForObject("selectJStocksSummaryForR3ShopSumCount", t);
	}
	
	/**
	 * 插入JStocksSummarylist
	 * 
	 * @author Coder AutoGenerator by Xiao,GuoJian
	 * @date 2014-09-28 15:54:21
	 */
	@Override
	public Long insertJStocksSummaryList(List<JStocksSummary> list) {
		long count = 0l;
		
		if(list != null && list.size()>0){
			for(JStocksSummary summary:list){
				this.getSqlMapClientTemplate().insert("insertJStocksSummary", summary);
				count ++;
			}
		}
		return count;
	}
	/**
	 * 客户 库存周转率
	 * @param t
	 * @return
	 */
	@Override
	public List<Map<String, Object>> selectJcfxCustList(JStocksSummary t) {
		return super.getSqlMapClientTemplate().queryForList("selectJcfxCustList", t);
	}
	/**
	 * 客户小类 库存周转率
	 * @param t
	 * @return
	 */
	@Override
	public List<Map<String, Object>> selectJcfxKhList(JStocksSummary t) {
		return super.getSqlMapClientTemplate().queryForList("selectJcfxKhList", t);
	}
	
	/**
	 * 客户 大类库存周转率
	 * @param t
	 * @return
	 */
	@Override
	public List<Map<String, Object>> selectJcfxParkhList(JStocksSummary t) {
		return super.getSqlMapClientTemplate().queryForList("selectJcfxParkhList", t);
	}

	/**
	 * 拿到上个月的库存
	 * @param t
	 * @return
	 */
	@Override
	public Long selectJStocksSummaryLastStocks(
			JStocksSummary jStocksSummary) {
		
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectJStocksSummaryLastStocks", jStocksSummary);
	}

	@Override
	public List<Map<String, Object>> selectCustRepertoryReportList(
			JStocksSummary t) {
		return super.getSqlMapClientTemplate().queryForList("selectCustRepertoryReportList", t);
	}

	@Override
	public Long selectCustRepertoryReportListCount(JStocksSummary t) {
		return (Long) this.getSqlMapClientTemplate().queryForObject("selectCustRepertoryReportCount", t);
	}
	/**
	 * 客户型号统计报表 count
	 */
	@Override
	public Long selectCustGoodsNameReportCount(JStocksSummary t) {
		return (Long) this.getSqlMapClientTemplate().queryForObject("selectCustGoodsNameReportCount", t);
	}
	/**
	 * 客户型号统计报表 list
	 */
	@Override
	public List<Map<String, Object>> selectCustGoodsNameReportList(
			JStocksSummary t) {
		return super.getSqlMapClientTemplate().queryForList("selectCustGoodsNameReportList", t);
	}

	@Override
	public List<JStocksSummary> selectjcfxzdkhListList(JStocksSummary t) {
		return this.getSqlMapClientTemplate().queryForList("selectjcfxzdkhListList", t);
	}

}
