package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaR3ShopTaskDao;
import com.ebiz.mmt.domain.KonkaR3ShopTask;
import com.ebiz.mmt.service.KonkaR3ShopTaskService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-12-05 09:51:13
 */
@Service
public class KonkaR3ShopTaskServiceImpl implements KonkaR3ShopTaskService {

	@Resource
	private KonkaR3ShopTaskDao konkaR3ShopTaskDao;

	public Long createKonkaR3ShopTask(KonkaR3ShopTask t) {
		return this.konkaR3ShopTaskDao.insertEntity(t);
	}

	public KonkaR3ShopTask getKonkaR3ShopTask(KonkaR3ShopTask t) {
		return this.konkaR3ShopTaskDao.selectEntity(t);
	}

	public Long getKonkaR3ShopTaskCount(KonkaR3ShopTask t) {
		return this.konkaR3ShopTaskDao.selectEntityCount(t);
	}

	public List<KonkaR3ShopTask> getKonkaR3ShopTaskList(KonkaR3ShopTask t) {
		return this.konkaR3ShopTaskDao.selectEntityList(t);
	}

	public int modifyKonkaR3ShopTask(KonkaR3ShopTask t) {
		return this.konkaR3ShopTaskDao.updateEntity(t);
	}

	public int removeKonkaR3ShopTask(KonkaR3ShopTask t) {
		return this.konkaR3ShopTaskDao.deleteEntity(t);
	}

	public List<KonkaR3ShopTask> getKonkaR3ShopTaskPaginatedList(KonkaR3ShopTask t) {
		return this.konkaR3ShopTaskDao.selectEntityPaginatedList(t);
	}

	/**
	 * @author Hu,Hap
	 * @version 2013-12-06
	 */
	public List<KonkaR3ShopTask> getKonkaR3ShopTaskToDeptUserPaginatedList(KonkaR3ShopTask t) {
		return this.konkaR3ShopTaskDao.selectKonkaR3ShopTaskToDeptUserPaginatedList(t);
	}

	/**
	 * @author Hu,Hap
	 * @version 2013-12-06
	 */
	public Long getKonkaR3ShopTaskToDeptUserCount(KonkaR3ShopTask t) {
		return this.konkaR3ShopTaskDao.selectKonkaR3ShopTaskToDeptUserCount(t);
	}
}
