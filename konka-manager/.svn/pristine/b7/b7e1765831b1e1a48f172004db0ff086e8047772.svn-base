package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ebiz.mmt.dao.UserInfoDao;
import com.ebiz.mmt.domain.UserInfo;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * @author Hui,Gang
 */
@Repository
@SuppressWarnings("unchecked")
public class UserInfoDaoSqlMapImpl extends EntityDaoSqlMapImpl<UserInfo> implements UserInfoDao {

	public List<UserInfo> selectUserInfoListForEntpTypeId(UserInfo t) {
		return super.getSqlMapClientTemplate().queryForList("selectUserInfoListForEntpTypeId", t);
	}

	/**
	 * @author Jiang,JiaYong
	 * @version 2010-07-23
	 */
	public List<UserInfo> selectUserInfoListForBindWithEntpNameAndPindex(UserInfo t) {
		return super.getSqlMapClientTemplate().queryForList("selectUserInfoListForBindWithEntpNameAndPindex", t);
	}
}
