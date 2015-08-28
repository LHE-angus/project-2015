package com.ebiz.mmt.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ebiz.mmt.domain.StdEntpUser;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2010-05-28 16:48:39
 */
public interface StdEntpUserService {

	Long createStdEntpUser(StdEntpUser t);

	int modifyStdEntpUser(StdEntpUser t);

	int modifyStdEntpUserAtLogin(StdEntpUser t);

	int modifyStdEntpUserWithKeySeq(StdEntpUser t);

	int removeStdEntpUser(StdEntpUser t);

	StdEntpUser getStdEntpUser(StdEntpUser t);

	List<StdEntpUser> getStdEntpUserList(StdEntpUser t);

	Long getStdEntpUserCount(StdEntpUser t);

	List<StdEntpUser> getStdEntpUserPaginatedList(StdEntpUser t);

	/**
	 * @author Du,HongGang
	 * @version 2010-07-05
	 */
	Long getDistinctMmtUserStdEntpUserCount(StdEntpUser t);

	/**
	 * @author Du,HongGang
	 * @version 2010-07-05
	 */
	Long getDistinctEntpIdStdEntpUserCount(StdEntpUser t);

	/**
	 * @author Zhang,DaPeng
	 * @version 2010-09-21
	 */
	// public Long queryIdByKey(StdEntpUser seu);

	/**
	 * @author Jiang,JiaYong
	 * @version 2011-05-19
	 */
	List<StdEntpUser> getStdEntpUserWithShopIdRandomOrderList(StdEntpUser t);

	/**
	 * @author Jiang,JiaYong
	 * @version 2011-06-28
	 */
	int createOrModifyStdEntpUserInStdEntpMain(StdEntpUser t);

	/**
	 * @author Zheng,Kun
	 * @version 2011-07-15
	 */
	public List<StdEntpUser> getKeyEntpShopInfoMatching(StdEntpUser t) throws DataAccessException;

	/**
	 * @author Ren, zhong
	 * @version 2011-08-17
	 */
	public List<StdEntpUser> getKeyEntpUserInfoForLogoutResultList(StdEntpUser t) throws DataAccessException;

	/**
	 * @author Wu,Yang
	 * @version 2011-10-13
	 * @desc 康佳进销存，用户登录时，取用户对应企业所绑定的所有密钥
	 */
	public List<StdEntpUser> getStdEntpUserListWithOwnStdEntpMain(StdEntpUser t);
	
	/**
	 * @author Li,Ka
	 * @version 2012-3-15
	 * @desc 有密钥登录时查询登录网点信息和企业信息，需要合并买卖提用户和官网用户的所有数据，防止查询不到
	 */
	public List<StdEntpUser> getStdEntpUserOrUserInfoList(StdEntpUser t);
}