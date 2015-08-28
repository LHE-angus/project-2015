package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.UserChangeInfoDao;
import com.ebiz.mmt.domain.UserChangeInfo;
import com.ebiz.mmt.service.UserChangeInfoService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-11-02 15:54:44
 */
@Service
public class UserChangeInfoServiceImpl implements UserChangeInfoService {

	@Resource
	private UserChangeInfoDao userChangeInfoDao;

	public Long createUserChangeInfo(UserChangeInfo t) {
		return this.userChangeInfoDao.insertEntity(t);
	}

	public UserChangeInfo getUserChangeInfo(UserChangeInfo t) {
		return this.userChangeInfoDao.selectEntity(t);
	}

	public Long getUserChangeInfoCount(UserChangeInfo t) {
		return this.userChangeInfoDao.selectEntityCount(t);
	}

	public List<UserChangeInfo> getUserChangeInfoList(UserChangeInfo t) {
		return this.userChangeInfoDao.selectEntityList(t);
	}

	public int modifyUserChangeInfo(UserChangeInfo t) {
		return this.userChangeInfoDao.updateEntity(t);
	}

	public int removeUserChangeInfo(UserChangeInfo t) {
		return this.userChangeInfoDao.deleteEntity(t);
	}

	public List<UserChangeInfo> getUserChangeInfoPaginatedList(UserChangeInfo t) {
		return this.userChangeInfoDao.selectEntityPaginatedList(t);
	}

	public List<UserChangeInfo> getUserChangeInfoByTimeList(UserChangeInfo t) {
		return this.userChangeInfoDao.selectUserChangeInfoByTimeList(t);
	}

}
