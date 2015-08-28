package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.StdEntpUserDao;
import com.ebiz.mmt.domain.StdEntpUser;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2010-05-28 16:48:39
 */
@Service
public class StdEntpUserDaoSqlMapImpl extends EntityDaoSqlMapImpl<StdEntpUser> implements StdEntpUserDao {

	/**
	 * @author Jiang,JiaYong
	 * @version 2010-06-07
	 */
	public int updateStdEntpUserWithKeySeq(StdEntpUser t) throws DataAccessException {
		return super.getSqlMapClientTemplate().update("updateStdEntpUserWithKeySeq", t);
	}

	/**
	 * @author Du,HongGang
	 * @version 2010-07-05
	 */
	public Long selectDistinctMmtUserStdEntpUserCount(StdEntpUser t) throws DataAccessException {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectDistinctMmtUserStdEntpUserCount", t);
	}

	/**
	 * @author Du,HongGang
	 * @version 2010-07-05
	 */
	public Long selectDistinctEntpIdStdEntpUserCount(StdEntpUser t) throws DataAccessException {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectDistinctEntpIdStdEntpUserCount", t);
	}

	public int deleteStdEntpUserForMmst(StdEntpUser t) throws DataAccessException {
		return super.getSqlMapClientTemplate().delete("deleteStdEntpUserForMmst", t);
	}

	/**
	 * @author Jiang,JiaYong
	 * @version 2010-05-19
	 */
	@SuppressWarnings("unchecked")
	public List<StdEntpUser> selectStdEntpUserWithShopIdRandomOrderList(StdEntpUser t) throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForList("selectStdEntpUserWithShopIdRandomOrderList", t);
	}

	/**
	 * @author Zheng,Kun
	 * @version 2010-07-15
	 */
	@SuppressWarnings("unchecked")
	public List<StdEntpUser> selectKeyEntpShopInfoMatching(StdEntpUser t) throws DataAccessException {

		return super.getSqlMapClientTemplate().queryForList("selectKeyEntpShopInfoMatching", t);
	}

	/**
	 * @author Ren, zhong
	 * @version 2011-08-17
	 */
	@SuppressWarnings("unchecked")
	public List<StdEntpUser> selectKeyEntpUserInfoForLogoutResultList(StdEntpUser t) throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForList("selectKeyEntpUserInfoForLogoutResultList", t);
	}

	/**
	 * @author Wu,Yang
	 * @version 2011-10-13
	 * @desc 康佳进销存，用户登录时，取用户对应企业所绑定的所有密钥
	 */
	@SuppressWarnings("unchecked")
	public List<StdEntpUser> selectStdEntpUserListWithOwnStdEntpMain(StdEntpUser t) throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForList("selectStdEntpUserListWithOwnStdEntpMain", t);
	}

	/**
	 * @author Li,Ka
	 * @version 2012-3-15
	 * @desc 有密钥登录时查询登录网点信息和企业信息，需要合并买卖提用户和官网用户的所有数据，防止查询不到
	 */
	@SuppressWarnings("unchecked")
	public List<StdEntpUser> selectStdEntpUserOrUserInfoList(StdEntpUser t) throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForList("selectStdEntpUserOrUserInfoList", t);
	}

	/**
	 * @author Zhang,DaPeng 2010-09-21
	 */
	// public Long queryIdByKey(StdEntpUser seu) throws DataAccessException {
	// return
	// (Long)super.getSqlMapClientTemplate().queryForObject("selectStdEntpUserID",seu);
	// }

}
