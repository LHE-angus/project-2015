package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaRoleDataLevelDao;
import com.ebiz.mmt.domain.KonkaRoleDataLevel;
import com.ebiz.mmt.service.KonkaRoleDataLevelService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-09-02 16:28:48
 */
@Service
public class KonkaRoleDataLevelServiceImpl implements KonkaRoleDataLevelService {

	@Resource
	private KonkaRoleDataLevelDao konkaRoleDataLevelDao;
	

	public Long createKonkaRoleDataLevel(KonkaRoleDataLevel t) {
		return this.konkaRoleDataLevelDao.insertEntity(t);
	}

	public KonkaRoleDataLevel getKonkaRoleDataLevel(KonkaRoleDataLevel t) {
		return this.konkaRoleDataLevelDao.selectEntity(t);
	}

	public Long getKonkaRoleDataLevelCount(KonkaRoleDataLevel t) {
		return this.konkaRoleDataLevelDao.selectEntityCount(t);
	}

	public List<KonkaRoleDataLevel> getKonkaRoleDataLevelList(KonkaRoleDataLevel t) {
		return this.konkaRoleDataLevelDao.selectEntityList(t);
	}

	public int modifyKonkaRoleDataLevel(KonkaRoleDataLevel t) {
		return this.konkaRoleDataLevelDao.updateEntity(t);
	}

	public int removeKonkaRoleDataLevel(KonkaRoleDataLevel t) {
		return this.konkaRoleDataLevelDao.deleteEntity(t);
	}

	public List<KonkaRoleDataLevel> getKonkaRoleDataLevelPaginatedList(KonkaRoleDataLevel t) {
		return this.konkaRoleDataLevelDao.selectEntityPaginatedList(t);
	}

}
