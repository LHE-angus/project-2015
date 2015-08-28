package com.ebiz.mmt.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ebiz.mmt.domain.UserInfo;
import com.ebiz.ssi.dao.EntityDao;

/**
 * @author Hui,Gang
 */
public interface UserInfoDao extends EntityDao<UserInfo> {

	List<UserInfo> selectUserInfoListForEntpTypeId(UserInfo t) throws DataAccessException;

	/**
	 * @author Jiang,JiaYong
	 * @version 2010-07-23
	 */
	List<UserInfo> selectUserInfoListForBindWithEntpNameAndPindex(UserInfo t) throws DataAccessException;

}
