package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.UserLoginErrLogs;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-12-11 14:56:02
 */
public interface UserLoginErrLogsService {

	Long createUserLoginErrLogs(UserLoginErrLogs t);

	int modifyUserLoginErrLogs(UserLoginErrLogs t);

	int removeUserLoginErrLogs(UserLoginErrLogs t);

	UserLoginErrLogs getUserLoginErrLogs(UserLoginErrLogs t);

	List<UserLoginErrLogs> getUserLoginErrLogsList(UserLoginErrLogs t);

	Long getUserLoginErrLogsCount(UserLoginErrLogs t);

	List<UserLoginErrLogs> getUserLoginErrLogsPaginatedList(UserLoginErrLogs t);

}