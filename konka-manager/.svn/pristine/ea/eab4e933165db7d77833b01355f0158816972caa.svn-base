package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.JdxxEntpSellDao;
import com.ebiz.mmt.domain.JdxxEntpSell;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2010-05-28 16:48:39
 */
@Service
public class JdxxEntpSellDaoSqlMapImpl extends EntityDaoSqlMapImpl<JdxxEntpSell> implements JdxxEntpSellDao {

	/**
	 * @author Jiang,JiaYong
	 */
	@SuppressWarnings("unchecked")
	public List<JdxxEntpSell> selectJdxxEntpSellIncludeMdNameAndBrandNameList(JdxxEntpSell t)
			throws DataAccessException {
		return this.getSqlMapClientTemplate().queryForList("selectJdxxEntpSellIncludeMdNameAndBrandNameList", t);
	}

	/**
	 * @author Jiang,JiaYong
	 * @version 2010-12-02
	 */
	@SuppressWarnings("unchecked")
	public List<JdxxEntpSell> selectJdxxEntpSellIncludeMdNameAndBrandNameForMmmtList(JdxxEntpSell t)
			throws DataAccessException {
		return this.getSqlMapClientTemplate().queryForList("selectJdxxEntpSellIncludeMdNameAndBrandNameForMmmtList", t);
	}

	/**
	 * @author Jiang,JiaYong
	 * @version 2010-07-07
	 */
	public Long selectJdxxEntpSellCountForStatistics(JdxxEntpSell t) throws DataAccessException {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectJdxxEntpSellCountForStatistics", t);
	}

	/**
	 * @author Xing,XiuDong
	 * @version 2010-11-17
	 */
	@SuppressWarnings("unchecked")
	public List<JdxxEntpSell> selectJdxxEntpSellCountGroupByPdType(JdxxEntpSell t) throws DataAccessException {
		return (List<JdxxEntpSell>) super.getSqlMapClientTemplate().queryForList(
				"selectJdxxEntpSellCountGroupByPdType", t);
	}

	/**
	 * @author Xing,XiuDong
	 * @version 2010-11-17
	 */
	@SuppressWarnings("unchecked")
	public List<JdxxEntpSell> selectJdxxEntpSellCountGroupByPdTypeAndWeek(JdxxEntpSell t) throws DataAccessException {
		return (List<JdxxEntpSell>) super.getSqlMapClientTemplate().queryForList(
				"selectJdxxEntpSellCountGroupByPdTypeAndWeek", t);
	}

	/**
	 * @author Xing,XiuDong
	 * @version 2011-6-22
	 */
	@SuppressWarnings("unchecked")
	public List<JdxxEntpSell> selectTopPdModelList(JdxxEntpSell t) throws DataAccessException {
		return (List<JdxxEntpSell>) super.getSqlMapClientTemplate().queryForList("selectTopPdModelList", t);
	}

	@SuppressWarnings("unchecked")
	public List<Long> selectJdxxEntpSellIdStrings(JdxxEntpSell t) {

		return this.getSqlMapClientTemplate().queryForList("selectJdxxEntpSellId", t);
	}

	/**
	 * @author Du,HongGang
	 * @version 2010-12-07
	 */
	@SuppressWarnings("unchecked")
	public List<JdxxEntpSell> selectJdxxEntpSellForBrandXsSalesList(JdxxEntpSell t) throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForList("selectJdxxEntpSellForBrandXsSalesList", t);
	}

	/**
	 * @author Chen,LiLin
	 * @version 2011-3-30
	 */
	@SuppressWarnings("unchecked")
	public List<JdxxEntpSell> selectJdxxEntpSellWithMdNamePaginatedList(JdxxEntpSell t) throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForList("selectJdxxEntpSellWithMdNamePaginatedList", t);
	}

	/**
	 * @author Wu,ShangLong
	 * @version 2011-3-30
	 */
	@SuppressWarnings("unchecked")
	public List<JdxxEntpSell> selectJdxxEntpSellWithMdNameList(JdxxEntpSell t) throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForList("selectJdxxEntpSellWithMdNameList", t);
	}

	/**
	 * @author Chen,LiLin
	 * @version 2011-3-31
	 */
	public int updateJdxxEntpSellForSellHelper(JdxxEntpSell t) throws DataAccessException {
		return super.getSqlMapClientTemplate().update("updateJdxxEntpSellForSellHelper", t);
	}
}
