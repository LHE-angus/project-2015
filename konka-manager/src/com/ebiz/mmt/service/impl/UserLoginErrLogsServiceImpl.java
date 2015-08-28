package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.UserLoginErrLogsDao;
import com.ebiz.mmt.domain.UserLoginErrLogs;
import com.ebiz.mmt.service.UserLoginErrLogsService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-12-11 14:56:02
 */
@Service
public class UserLoginErrLogsServiceImpl implements UserLoginErrLogsService {

	@Resource
	private UserLoginErrLogsDao userLoginErrLogsDao;
	

	public Long createUserLoginErrLogs(UserLoginErrLogs t) {
		return this.userLoginErrLogsDao.insertEntity(t);
	}

	public UserLoginErrLogs getUserLoginErrLogs(UserLoginErrLogs t) {
		return this.userLoginErrLogsDao.selectEntity(t);
	}

	public Long getUserLoginErrLogsCount(UserLoginErrLogs t) {
		return this.userLoginErrLogsDao.selectEntityCount(t);
	}

	public List<UserLoginErrLogs> getUserLoginErrLogsList(UserLoginErrLogs t) {
		return this.userLoginErrLogsDao.selectEntityList(t);
	}

	public int modifyUserLoginErrLogs(UserLoginErrLogs t) {
		return this.userLoginErrLogsDao.updateEntity(t);
	}

	public int removeUserLoginErrLogs(UserLoginErrLogs t) {
		return this.userLoginErrLogsDao.deleteEntity(t);
	}

	public List<UserLoginErrLogs> getUserLoginErrLogsPaginatedList(UserLoginErrLogs t) {
		return this.userLoginErrLogsDao.selectEntityPaginatedList(t);
	}

}
