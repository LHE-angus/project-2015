package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.UserInfo;

/**
 * @author Liu,AiZhou
 */
public interface UserInfoService {

	Long createUserInfo(UserInfo t);

	int modifyUserInfo(UserInfo t);

	int removeUserInfo(UserInfo t);

	UserInfo getUserInfo(UserInfo t);

	List<UserInfo> getUserInfoList(UserInfo t);

	Long getUserInfoCount(UserInfo t);

	List<UserInfo> getUserInfoPaginatedList(UserInfo t);

	List<UserInfo> getUserInfoListForEntpTypeId(UserInfo t);

	/**
	 * @author Jiang,JiaYong
	 * @version 2010-07-23
	 */
	List<UserInfo> getUserInfoListForBindWithEntpNameAndPindex(UserInfo t);
}
