package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.MmtUserInfoDao;
import com.ebiz.mmt.domain.MmtUserInfo;
import com.ebiz.mmt.service.MmtUserInfoService;


@Service
public class MmtUserInfoServiceImpl implements MmtUserInfoService {

	@Resource
	private MmtUserInfoDao mmtUserInfoDao;

	public Long createMmtUserInfo(MmtUserInfo t) {
		return this.mmtUserInfoDao.insertEntity(t);
	}

	public MmtUserInfo getMmtUserInfo(MmtUserInfo t) {
		return this.mmtUserInfoDao.selectEntity(t);
	}

	public Long getMmtUserInfoCount(MmtUserInfo t) {
		return this.mmtUserInfoDao.selectEntityCount(t);
	}

	public List<MmtUserInfo> getMmtUserInfoList(MmtUserInfo t) {
		return this.mmtUserInfoDao.selectEntityList(t);
	}

	public int modifyMmtUserInfo(MmtUserInfo t) {
		return this.mmtUserInfoDao.updateEntity(t);
	}

	public int removeMmtUserInfo(MmtUserInfo t) {
		return this.mmtUserInfoDao.deleteEntity(t);
	}

	public List<MmtUserInfo> getMmtUserInfoPaginatedList(MmtUserInfo t) {
		return this.mmtUserInfoDao.selectEntityPaginatedList(t);
	}

}
