package com.ebiz.mmt.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ebiz.mmt.domain.StdEntpUser;
import com.ebiz.ssi.dao.EntityDao;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2010-05-28 16:48:39
 */
public interface StdEntpUserDao extends EntityDao<StdEntpUser> {
	/**
	 * @author Jiang,JiaYong
	 * @version 2010-06-07
	 */
	public int updateStdEntpUserWithKeySeq(StdEntpUser t) throws DataAccessException;

	/**
	 * @author Du,HongGang
	 * @version 2010-07-05
	 */
	public Long selectDistinctMmtUserStdEntpUserCount(StdEntpUser t) throws DataAccessException;

	/**
	 * @author Du,HongGang
	 * @version 2010-07-05
	 */
	public Long selectDistinctEntpIdStdEntpUserCount(StdEntpUser t) throws DataAccessException;

	/**
	 * @author Chen,LiLin
	 * @version 2010-08-17
	 */
	public int deleteStdEntpUserForMmst(StdEntpUser t) throws DataAccessException;

	/**
	 * @author Zhang,Dapeng
	 * @version 2010-09-21
	 */
	// public Long queryIdByKey(StdEntpUser seu) throws DataAccessException;

	/**
	 * @author Jiang,JiaYong
	 * @version 2011-05-19
	 */
	public abstract List<StdEntpUser> selectStdEntpUserWithShopIdRandomOrderList(StdEntpUser t)
			throws DataAccessException;

	/**
	 * @author Zheng,Kun
	 * @version 2011-07-15
	 */
	public List<StdEntpUser> selectKeyEntpShopInfoMatching(StdEntpUser t) throws DataAccessException;

	/**
	 * @author Ren, zhong
	 * @version 2011-08-17
	 */
	public List<StdEntpUser> selectKeyEntpUserInfoForLogoutResultList(StdEntpUser t) throws DataAccessException;

	/**
	 * @author Wu,Yang
	 * @version 2011-10-13
	 * @desc 康佳进销存，用户登录时，取用户对应企业所绑定的所有密钥
	 */
	public List<StdEntpUser> selectStdEntpUserListWithOwnStdEntpMain(StdEntpUser t) throws DataAccessException;
	
	/**
	 * @author Li,Ka
	 * @version 2012-3-15
	 * @desc 有密钥登录时查询登录网点信息和企业信息，需要合并买卖提用户和官网用户的所有数据，防止查询不到
	 */
	public List<StdEntpUser> selectStdEntpUserOrUserInfoList(StdEntpUser t) throws DataAccessException;
}
