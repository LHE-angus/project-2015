package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.MvClsidJoinBrandXxhxDao;
import com.ebiz.mmt.domain.MvClsidJoinBrandXxhx;
import com.ebiz.mmt.service.MvClsidJoinBrandXxhxService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2011-05-13 11:37:04
 */
@Service
public class MvClsidJoinBrandXxhxServiceImpl implements MvClsidJoinBrandXxhxService {

	@Resource
	private MvClsidJoinBrandXxhxDao mvClsidJoinBrandXxhxDao;

	public Long createMvClsidJoinBrandXxhx(MvClsidJoinBrandXxhx t) {
		return this.mvClsidJoinBrandXxhxDao.insertEntity(t);
	}

	public MvClsidJoinBrandXxhx getMvClsidJoinBrandXxhx(MvClsidJoinBrandXxhx t) {
		return this.mvClsidJoinBrandXxhxDao.selectEntity(t);
	}

	public Long getMvClsidJoinBrandXxhxCount(MvClsidJoinBrandXxhx t) {
		return this.mvClsidJoinBrandXxhxDao.selectEntityCount(t);
	}

	public List<MvClsidJoinBrandXxhx> getMvClsidJoinBrandXxhxList(MvClsidJoinBrandXxhx t) {
		return this.mvClsidJoinBrandXxhxDao.selectEntityList(t);
	}

	public int modifyMvClsidJoinBrandXxhx(MvClsidJoinBrandXxhx t) {
		return this.mvClsidJoinBrandXxhxDao.updateEntity(t);
	}

	public int removeMvClsidJoinBrandXxhx(MvClsidJoinBrandXxhx t) {
		return this.mvClsidJoinBrandXxhxDao.deleteEntity(t);
	}

	public List<MvClsidJoinBrandXxhx> getMvClsidJoinBrandXxhxPaginatedList(MvClsidJoinBrandXxhx t) {
		return this.mvClsidJoinBrandXxhxDao.selectEntityPaginatedList(t);
	}

	/**
	 * @author Wu,ShangLong
	 * @version 2011.5.13
	 */
	public List<MvClsidJoinBrandXxhx> getBrandsByClsidsXxhxList(MvClsidJoinBrandXxhx t) {
		return this.mvClsidJoinBrandXxhxDao.selectBrandsByClsidsXxhxList(t);
	}
}
