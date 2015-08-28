package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.MemberChangeCardInfoDao;
import com.ebiz.mmt.domain.MemberChangeCardInfo;
import com.ebiz.mmt.service.MemberChangeCardInfoService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-07-26 10:16:20
 */
@Service
public class MemberChangeCardInfoServiceImpl implements MemberChangeCardInfoService {

	@Resource
	private MemberChangeCardInfoDao memberChangeCardInfoDao;

	public Long createMemberChangeCardInfo(MemberChangeCardInfo t) {
		return this.memberChangeCardInfoDao.insertEntity(t);
	}

	public MemberChangeCardInfo getMemberChangeCardInfo(MemberChangeCardInfo t) {
		return this.memberChangeCardInfoDao.selectEntity(t);
	}

	public Long getMemberChangeCardInfoCount(MemberChangeCardInfo t) {
		return this.memberChangeCardInfoDao.selectEntityCount(t);
	}

	public List<MemberChangeCardInfo> getMemberChangeCardInfoList(MemberChangeCardInfo t) {
		return this.memberChangeCardInfoDao.selectEntityList(t);
	}

	public int modifyMemberChangeCardInfo(MemberChangeCardInfo t) {
		return this.memberChangeCardInfoDao.updateEntity(t);
	}

	public int removeMemberChangeCardInfo(MemberChangeCardInfo t) {
		return this.memberChangeCardInfoDao.deleteEntity(t);
	}

	public List<MemberChangeCardInfo> getMemberChangeCardInfoPaginatedList(MemberChangeCardInfo t) {
		return this.memberChangeCardInfoDao.selectEntityPaginatedList(t);
	}

	public List<MemberChangeCardInfo> getMemberChangeCardInfoHistoryList(MemberChangeCardInfo t) {
		return this.memberChangeCardInfoDao.selectMemberChangeCardInfoHistoryList(t);
	}

}
