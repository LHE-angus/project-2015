package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.JxcPdDao;
import com.ebiz.mmt.domain.JxcPd;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2011-03-03 09:42:37
 */
@Service
public class JxcPdDaoSqlMapImpl extends EntityDaoSqlMapImpl<JxcPd> implements JxcPdDao {

	/**
	 * @author Du,HongGang
	 * @version 2011-03-03
	 */
	@SuppressWarnings("unchecked")
	public List<JxcPd> selectJxcPdListForJX(JxcPd t) {
		return super.getSqlMapClientTemplate().queryForList("selectJxcPdListForJX", t);
	}

	/**
	 * @author Chen,LiLin
	 * @version 2011-03-07
	 */
	public int updateJxcPdCount(JxcPd t) {
		return super.getSqlMapClientTemplate().update("updateJxcPdCount", t);
	}

	/**
	 * @author Ren zhong
	 * @version 2011-03-07
	 */
	public JxcPd selectQcjcForStock(JxcPd t) {
		return (JxcPd) super.getSqlMapClientTemplate().queryForObject("selectQcjcForStock", t);
	}

	public JxcPd selectRcjhForStock(JxcPd t) {
		return (JxcPd) super.getSqlMapClientTemplate().queryForObject("selectRcjhForStock", t);
	}

	public JxcPd selectRcchForStock(JxcPd t) {
		return (JxcPd) super.getSqlMapClientTemplate().queryForObject("selectRcchForStock", t);
	}

	@SuppressWarnings("unchecked")
	public List<JxcPd> selectSskcDetailsForStork(JxcPd t) {
		return super.getSqlMapClientTemplate().queryForList("selectSskcDetailsForStork", t);
	}

	@SuppressWarnings("unchecked")
	public List<JxcPd> selectSsxsDetailsForStork(JxcPd t) {
		return super.getSqlMapClientTemplate().queryForList("selectSsxsDetailsForStork", t);
	}

	@SuppressWarnings("unchecked")
	public List<JxcPd> selectStockDetailsList(JxcPd t) {
		return super.getSqlMapClientTemplate().queryForList("selectStockDetailsList", t);
	}

	@SuppressWarnings("unchecked")
	public List<JxcPd> selectStockRcjhAndRcchList(JxcPd t) {
		return super.getSqlMapClientTemplate().queryForList("selectStockRcjhAndRcchList", t);
	}

	public Long selectStockRcjhAndRcchCount(JxcPd t) {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectStockRcjhAndRcchCount", t);
	}

	@SuppressWarnings("unchecked")
	public List<JxcPd> selectJxcShopUsedStatisticsResultForList(JxcPd t) {
		return super.getSqlMapClientTemplate().queryForList("selectJxcShopUsedStatisticsResultForList", t);
	}

	/**
	 * @author Ren zhong
	 * @version 2011-08-12
	 */
	public Long selectJxcPdDetailsByProvinceCount(JxcPd t) {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectJxcPdDetailsByProvinceCount", t);
	}

	@SuppressWarnings("unchecked")
	public List<JxcPd> selectJxcPdDetailsByProvincePaginatedList(JxcPd t) {
		return super.getSqlMapClientTemplate().queryForList("selectJxcPdDetailsByProvincePaginatedList", t);
	}

	@SuppressWarnings("unchecked")
	public List<JxcPd> selectJxcShopSalesPdTypeStatisticsResultForList(JxcPd t) {
		return super.getSqlMapClientTemplate().queryForList("selectJxcShopSalesPdTypeStatisticsResultForList", t);
	}

	@SuppressWarnings("unchecked")
	public List<JxcPd> selectJxcShopSalesNotJDXXDetailsResultForList(JxcPd t) {
		return super.getSqlMapClientTemplate().queryForList("selectJxcShopSalesNotJDXXDetailsResultForList", t);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<JxcPd> selectStockDetailsListForPc(JxcPd t) {
		return super.getSqlMapClientTemplate().queryForList("selectStockDetailsListForPc", t);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<JxcPd> selectShopSellStatisticsList(JxcPd t) {
		return super.getSqlMapClientTemplate().queryForList("selectShopSellStatisticsList", t);
	}

	@Override
	public JxcPd selectQcjcForShopSellStatistics(JxcPd t) {
		return (JxcPd) super.getSqlMapClientTemplate().queryForObject("selectQcjcForShopSellStatistics", t);
	}
}
