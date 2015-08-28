package com.ebiz.mmt.service;

import java.util.List;
import com.ebiz.mmt.domain.MmtPeProdUser;

public interface MmtPeProdUserService {

	Long createMmtPeProdUser(MmtPeProdUser t);

	int modifyMmtPeProdUser(MmtPeProdUser t);

	int removeMmtPeProdUser(MmtPeProdUser t);

	MmtPeProdUser getMmtPeProdUser(MmtPeProdUser t);

	List<MmtPeProdUser> getMmtPeProdUserList(MmtPeProdUser t);

	Long getMmtPeProdUserCount(MmtPeProdUser t);

	List<MmtPeProdUser> getMmtPeProdUserPaginatedList(MmtPeProdUser t);

}
