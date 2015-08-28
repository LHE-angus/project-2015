package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.MmtStdEntpUserDao;
import com.ebiz.mmt.domain.MmtStdEntpUser;
import com.ebiz.mmt.service.MmtStdEntpUserService;


@Service
public class MmtStdEntpUserServiceImpl implements MmtStdEntpUserService {

	@Resource
	private MmtStdEntpUserDao mmtStdEntpUserDao;

	public Long createMmtStdEntpUser(MmtStdEntpUser t) {
		return this.mmtStdEntpUserDao.insertEntity(t);
	}

	public MmtStdEntpUser getMmtStdEntpUser(MmtStdEntpUser t) {
		return this.mmtStdEntpUserDao.selectEntity(t);
	}

	public Long getMmtStdEntpUserCount(MmtStdEntpUser t) {
		return this.mmtStdEntpUserDao.selectEntityCount(t);
	}

	public List<MmtStdEntpUser> getMmtStdEntpUserList(MmtStdEntpUser t) {
		return this.mmtStdEntpUserDao.selectEntityList(t);
	}

	public int modifyMmtStdEntpUser(MmtStdEntpUser t) {
		return this.mmtStdEntpUserDao.updateEntity(t);
	}

	public int removeMmtStdEntpUser(MmtStdEntpUser t) {
		return this.mmtStdEntpUserDao.deleteEntity(t);
	}

	public List<MmtStdEntpUser> getMmtStdEntpUserPaginatedList(MmtStdEntpUser t) {
		return this.mmtStdEntpUserDao.selectEntityPaginatedList(t);
	}

}
