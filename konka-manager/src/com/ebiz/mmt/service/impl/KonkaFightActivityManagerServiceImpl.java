package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaFightActivityManagerDao;
import com.ebiz.mmt.domain.KonkaFightActivityManager;
import com.ebiz.mmt.service.KonkaFightActivityManagerService;
import com.ebiz.mmt.web.struts.manager.admin.KonkaFightActivityManagerAction;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2015-03-16 11:42:03
 */
@Service
public class KonkaFightActivityManagerServiceImpl implements KonkaFightActivityManagerService {

	@Resource
	private KonkaFightActivityManagerDao konkaFightActivityManagerDao;
	

	public Long createKonkaFightActivityManager(KonkaFightActivityManager t) {
		return this.konkaFightActivityManagerDao.insertEntity(t);
	}

	public KonkaFightActivityManager getKonkaFightActivityManager(KonkaFightActivityManager t) {
		return this.konkaFightActivityManagerDao.selectEntity(t);
	}

	public Long getKonkaFightActivityManagerCount(KonkaFightActivityManager t) {
		return this.konkaFightActivityManagerDao.selectEntityCount(t);
	}

	public List<KonkaFightActivityManager> getKonkaFightActivityManagerList(KonkaFightActivityManager t) {
		return this.konkaFightActivityManagerDao.selectEntityList(t);
	}

	public int modifyKonkaFightActivityManager(KonkaFightActivityManager t) {
		return this.konkaFightActivityManagerDao.updateEntity(t);
	}

	public int removeKonkaFightActivityManager(KonkaFightActivityManager t) {
		return this.konkaFightActivityManagerDao.deleteEntity(t);
	}

	public List<KonkaFightActivityManager> getKonkaFightActivityManagerPaginatedList(KonkaFightActivityManager t) {
		return this.konkaFightActivityManagerDao.selectEntityPaginatedList(t);
	}

	@Override
	public Long getKonkaFightActivityManagerMainCount(
			KonkaFightActivityManager entity) {
		
		return this.konkaFightActivityManagerDao.selectKonkaFightActivityManagerMainCount(entity);
	}

	@Override
	public List<KonkaFightActivityManager> getKonkaFightActivityManagerMainPaginatedList(
			KonkaFightActivityManager entity) {
		
		return this.konkaFightActivityManagerDao.selectKonkaFightActivityManagerMainPaginatedList(entity);
	}


	

}
