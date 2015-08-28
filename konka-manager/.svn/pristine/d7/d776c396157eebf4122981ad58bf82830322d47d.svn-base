package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.MmtEntpUserRegDao;
import com.ebiz.mmt.domain.MmtEntpUserReg;
import com.ebiz.mmt.service.MmtEntpUserRegService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2011-04-29 14:55:31
 */
@Service
public class MmtEntpUserRegServiceImpl implements MmtEntpUserRegService {

	@Resource
	private MmtEntpUserRegDao mmtEntpUserRegDao;

	/**
	 * @author Wu,ShangLong
	 * @date 2011-05-03
	 */
	public Long createEntpUserReg(MmtEntpUserReg t) {
		return mmtEntpUserRegDao.insertEntity(t);
	}

	public MmtEntpUserReg getEntpUserReg(MmtEntpUserReg t) {
		return this.mmtEntpUserRegDao.selectEntity(t);
	}

	public Long getEntpUserRegCount(MmtEntpUserReg t) {
		return this.mmtEntpUserRegDao.selectEntityCount(t);
	}

	public List<MmtEntpUserReg> getEntpUserRegList(MmtEntpUserReg t) {
		return this.mmtEntpUserRegDao.selectEntityList(t);
	}

	public int modifyEntpUserReg(MmtEntpUserReg t) {
		return this.mmtEntpUserRegDao.updateEntity(t);
	}

	public int removeEntpUserReg(MmtEntpUserReg t) {
		return this.mmtEntpUserRegDao.deleteEntity(t);
	}

	public List<MmtEntpUserReg> getEntpUserRegPaginatedList(MmtEntpUserReg t) {
		return this.mmtEntpUserRegDao.selectEntityPaginatedList(t);
	}

	/**
	 * @author Wu,ShangLong
	 * @version 2011-05-25
	 */
	public Long getEntpUserRegWithLinkerCount(MmtEntpUserReg t) {
		return this.mmtEntpUserRegDao.selectEntpUserRegWithLinkerCount(t);
	}

	/**
	 * @author Wu,ShangLong
	 * @version 2011-05-25
	 */
	public List<MmtEntpUserReg> getEntpUserRegWithLinkerPaginatedList(MmtEntpUserReg t) {
		return this.mmtEntpUserRegDao.selectEntpUserRegWithLinkerPaginatedList(t);
	}

}
