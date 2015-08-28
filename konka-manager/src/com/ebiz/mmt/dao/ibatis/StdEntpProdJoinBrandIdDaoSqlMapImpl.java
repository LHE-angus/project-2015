package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ebiz.mmt.dao.StdEntpProdJoinBrandIdDao;
import com.ebiz.mmt.domain.StdEntpProdJoinBrandId;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * @author Wu,Yang
 * @version 2010-10-25 11:39:12
 */
@Repository
public class StdEntpProdJoinBrandIdDaoSqlMapImpl extends EntityDaoSqlMapImpl<StdEntpProdJoinBrandId> implements
		StdEntpProdJoinBrandIdDao {
	/**
	 * @author Du,HongGang
	 * @version 2010-10-26
	 */
	public int deleteStdEntpProdJoinBrandIdByEntpIdOrBrandId(StdEntpProdJoinBrandId t) {
		return super.getSqlMapClientTemplate().delete("deleteStdEntpProdJoinBrandIdByEntpIdOrBrandId", t);
	}

	/**
	 * @author Wu,ShangLong
	 * @version 2011-03-10
	 */
	@SuppressWarnings("unchecked")
	public List<StdEntpProdJoinBrandId> selectStdEntpProdJoinBrandIdWithEntpName(StdEntpProdJoinBrandId t) {
		return super.getSqlMapClientTemplate().queryForList("selectStdEntpProdJoinBrandIdWithEntpName", t);
	}
}
