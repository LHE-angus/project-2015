package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.MemberInfo;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-07-26 11:10:27
 */
public interface MemberInfoService {

	Long createMemberInfo(MemberInfo t);

	int modifyMemberInfo(MemberInfo t);

	int removeMemberInfo(MemberInfo t);

	MemberInfo getMemberInfo(MemberInfo t);

	List<MemberInfo> getMemberInfoList(MemberInfo t);

	Long getMemberInfoCount(MemberInfo t);

	List<MemberInfo> getMemberInfoPaginatedList(MemberInfo t);

	List<MemberInfo> getMemberInfoForTotalScortsPaginatedList(MemberInfo t);

	// 更换会员卡
	void createMemberCardChang(String id, String now_user_sn);

}