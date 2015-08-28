package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.ebiz.mmt.dao.BaseBrandInfoDao;
import com.ebiz.mmt.domain.BaseBrandInfo;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * @author Jin,Q ingHua
 */
@Repository
public class BaseBrandInfoDaoSqlMapImpl extends EntityDaoSqlMapImpl<BaseBrandInfo> implements BaseBrandInfoDao {

	/**
	 * @author Jiang,JiaYong
	 */
	@SuppressWarnings("unchecked")
	public List<BaseBrandInfo> selectBaseBrandInfoForEntpShopList(BaseBrandInfo t) throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForList("selectBaseBrandInfoForEntpShopList", t);
	}

	/**
	 * @author Li,Yuan
	 */
	@SuppressWarnings("unchecked")
	public List<BaseBrandInfo> selectBaseBrandInfoByPdType(BaseBrandInfo t) {
		return super.getSqlMapClientTemplate().queryForList("selectBaseBrandInfoByPdType", t);
	}

	/**
	 * @author Du,HongGang
	 * @version 2010-10-25
	 */
	@SuppressWarnings("unchecked")
	public List<BaseBrandInfo> selectBaseBrandInfoForjoinEntpProdList(BaseBrandInfo t) {
		return super.getSqlMapClientTemplate().queryForList("selectBaseBrandInfoForjoinEntpProdList", t);
	}

	/**
	 * @author Du,HongGang
	 * @version 2010-10-26
	 */
	public BaseBrandInfo selectBaseBrandInfoForjoinEntpProd(BaseBrandInfo t) {
		return (BaseBrandInfo) super.getSqlMapClientTemplate().queryForObject("selectBaseBrandInfoForjoinEntpProd", t);
	}

	/**
	 * @author Du,HongGang
	 * @version 2010-11-16
	 */
	@SuppressWarnings("unchecked")
	public List<BaseBrandInfo> selectBaseBrandInfoForPopList(BaseBrandInfo t) {
		return super.getSqlMapClientTemplate().queryForList("selectBaseBrandInfoForPopList", t);
	}

	/**
	 * @author Chen,LiLin
	 * @version 2011-1-24
	 */
	public Long selectBaseBrandInfoForjoinEntpProdCount(BaseBrandInfo t) {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectBaseBrandInfoForjoinEntpProdCount", t);
	}
}
