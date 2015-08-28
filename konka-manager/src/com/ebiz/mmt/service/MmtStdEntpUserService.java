package com.ebiz.mmt.service;

import java.util.List;
import com.ebiz.mmt.domain.MmtStdEntpUser;

public interface MmtStdEntpUserService {

	Long createMmtStdEntpUser(MmtStdEntpUser t);

	int modifyMmtStdEntpUser(MmtStdEntpUser t);

	int removeMmtStdEntpUser(MmtStdEntpUser t);

	MmtStdEntpUser getMmtStdEntpUser(MmtStdEntpUser t);

	List<MmtStdEntpUser> getMmtStdEntpUserList(MmtStdEntpUser t);

	Long getMmtStdEntpUserCount(MmtStdEntpUser t);

	List<MmtStdEntpUser> getMmtStdEntpUserPaginatedList(MmtStdEntpUser t);

}
