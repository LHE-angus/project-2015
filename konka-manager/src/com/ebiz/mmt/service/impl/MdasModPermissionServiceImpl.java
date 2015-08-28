package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.MdasModPermissionDao;
import com.ebiz.mmt.domain.MdasModPermission;
import com.ebiz.mmt.service.MdasModPermissionService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2010-08-25 10:35:27
 */
@Service
public class MdasModPermissionServiceImpl implements MdasModPermissionService {

	@Resource
	private MdasModPermissionDao mdasModPermissionDao;

	public Long createMdasModPermission(MdasModPermission t) {
		return this.mdasModPermissionDao.insertEntity(t);
	}

	public MdasModPermission getMdasModPermission(MdasModPermission t) {
		return this.mdasModPermissionDao.selectEntity(t);
	}

	public Long getMdasModPermissionCount(MdasModPermission t) {
		return this.mdasModPermissionDao.selectEntityCount(t);
	}

	public List<MdasModPermission> getMdasModPermissionList(MdasModPermission t) {
		return this.mdasModPermissionDao.selectEntityList(t);
	}

	public int modifyMdasModPermission(MdasModPermission t) {
		return this.mdasModPermissionDao.updateEntity(t);
	}

	public int removeMdasModPermission(MdasModPermission t) {
		return this.mdasModPermissionDao.deleteEntity(t);
	}

	public List<MdasModPermission> getMdasModPermissionPaginatedList(MdasModPermission t) {
		return this.mdasModPermissionDao.selectEntityPaginatedList(t);
	}

	public Long getAreaLimitCount(MdasModPermission t) {
		return this.mdasModPermissionDao.selectAreaLimitCount(t);
	}

	public List<MdasModPermission> getAreaLimitPaginatedList(MdasModPermission t) {
		return this.mdasModPermissionDao.selectAreaLimitPaginatedList(t);
	}

	/**
	 * @author Du,HongGang
	 * @version 2010-08-26
	 */

	public int removeMdasModPermissionNotInModIds(MdasModPermission t) {
		return this.mdasModPermissionDao.deleteMdasModPermissionNotInModIds(t);
	}

	public Long getMdasModPermissionByUserNameCount(MdasModPermission t) {
		return this.mdasModPermissionDao.selectEntityByUserCount(t);
	}

	public List<MdasModPermission> getMdasModPermissionByUserNamePaginatedList(MdasModPermission t) {
		return this.mdasModPermissionDao.selectEntityByUserNamePaginatedList(t);
	}

	public MdasModPermission getMdasModPermissionAndName(MdasModPermission t) {
		return this.mdasModPermissionDao.selectEntityAndName(t);
	}

	public int removeMdasModPermissionByModId(MdasModPermission t) {
		return this.mdasModPermissionDao.deleteMdasModPermissionInModIds(t);
	}

	public List<MdasModPermission> getMdasModPermissionWithPNameList(MdasModPermission t) {
		return this.mdasModPermissionDao.selectMdasModPermissionWithPNameList(t);
	}

	public List<MdasModPermission> getMdasModPermissionWithBrandNameList(MdasModPermission t) {
		return this.mdasModPermissionDao.selectMdasModPermissionWithBrandNameList(t);
	}

	public List<MdasModPermission> getMdasModPermissionWithBrandNamePaginatedList(MdasModPermission t) {
		return this.mdasModPermissionDao.selectMdasModPermissionWithBrandNamePaginatedList(t);
	}

	public Long getMdasModPermissionWithBrandNameCount(MdasModPermission t) {
		return this.mdasModPermissionDao.selectMdasModPermissionWithBrandNameCount(t);
	}
}
