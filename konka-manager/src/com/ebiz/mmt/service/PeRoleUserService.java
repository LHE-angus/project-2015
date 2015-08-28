package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.PeRoleUser;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2011-05-11 15:59:43
 */
public interface PeRoleUserService {

	Long createPeRoleUser(PeRoleUser t);

	int modifyPeRoleUser(PeRoleUser t);

	int removePeRoleUser(PeRoleUser t);

	PeRoleUser getPeRoleUser(PeRoleUser t);

	List<PeRoleUser> getPeRoleUserList(PeRoleUser t);

	Long getPeRoleUserCount(PeRoleUser t);

	List<PeRoleUser> getPeRoleUserPaginatedList(PeRoleUser t);

}