package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.StdEntpProdDao;
import com.ebiz.mmt.domain.StdEntpProd;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2010-10-11 11:51:17
 */
@Service
public class StdEntpProdDaoSqlMapImpl extends EntityDaoSqlMapImpl<StdEntpProd> implements StdEntpProdDao {
	/**
	 * @author Du,HongGang
	 * @version 2010-10-25
	 */
	@SuppressWarnings("unchecked")
	public List<StdEntpProd> selectEntpProdForjoinBrandInfoList(StdEntpProd t) {
		return super.getSqlMapClientTemplate().queryForList("selectEntpProdForjoinBrandInfoList", t);
	}

	/**
	 * @author Du,HongGang
	 * @version 2010-10-26
	 */
	public StdEntpProd selectEntpProdForjoinBrandInfo(StdEntpProd t) {
		return (StdEntpProd) super.getSqlMapClientTemplate().queryForObject("selectEntpProdForjoinBrandInfo", t);
	}

	/**
	 * @author Li,Yuan
	 * @version 2010-11-18
	 */
	@SuppressWarnings("unchecked")
	public List<StdEntpProd> selectStdEntpProdForLogoList(StdEntpProd t) {
		return super.getSqlMapClientTemplate().queryForList("selectStdEntpProdForLogoList", t);
	}

	/**
	 * @author Wu,ShangLong
	 * @version 2011-03-10
	 */
	public Long selectStdEntpProdForjoinBrandAndEntpProdCount(StdEntpProd t) {
		return (Long) super.getSqlMapClientTemplate()
				.queryForObject("selectStdEntpProdForjoinBrandAndEntpProdCount", t);
	};

	/**
	 * @author Wu,ShangLong
	 * @version 2011-03-10
	 */
	@SuppressWarnings("unchecked")
	public List<StdEntpProd> selectStdEntpProdForjoinBrandAndEntpProdPaginatedList(StdEntpProd t) {
		return super.getSqlMapClientTemplate().queryForList("selectStdEntpProdForjoinBrandAndEntpProdPaginatedList", t);
	}

	/**
	 * @author Wu,ShangLong
	 * @version 2011-03-10
	 */
	public Long selectStdEntpProdNotBrandIdCount(StdEntpProd t) {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectStdEntpProdNotBrandIdCount", t);
	}

	/**
	 * @author Wu,ShangLong
	 * @version 2011-03-10
	 */
	@SuppressWarnings("unchecked")
	public List<StdEntpProd> selectStdEntpProdNotBrandIdList(StdEntpProd t) {
		return super.getSqlMapClientTemplate().queryForList("selectStdEntpProdNotBrandIdList", t);
	}
}
