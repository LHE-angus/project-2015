package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaR3StoreTaskDao;
import com.ebiz.mmt.domain.KonkaR3StoreTask;
import com.ebiz.mmt.service.KonkaR3StoreTaskService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-05-06 14:47:57
 */
@Service
public class KonkaR3StoreTaskServiceImpl implements KonkaR3StoreTaskService {

	@Resource
	private KonkaR3StoreTaskDao konkaR3StoreTaskDao;

	public Long createKonkaR3StoreTask(KonkaR3StoreTask t) {
		return this.konkaR3StoreTaskDao.insertEntity(t);
	}

	public KonkaR3StoreTask getKonkaR3StoreTask(KonkaR3StoreTask t) {
		return this.konkaR3StoreTaskDao.selectEntity(t);
	}

	public Long getKonkaR3StoreTaskCount(KonkaR3StoreTask t) {
		return this.konkaR3StoreTaskDao.selectEntityCount(t);
	}

	public List<KonkaR3StoreTask> getKonkaR3StoreTaskList(KonkaR3StoreTask t) {
		return this.konkaR3StoreTaskDao.selectEntityList(t);
	}

	public int modifyKonkaR3StoreTask(KonkaR3StoreTask t) {
		return this.konkaR3StoreTaskDao.updateEntity(t);
	}

	public int removeKonkaR3StoreTask(KonkaR3StoreTask t) {
		return this.konkaR3StoreTaskDao.deleteEntity(t);
	}

	public List<KonkaR3StoreTask> getKonkaR3StoreTaskPaginatedList(KonkaR3StoreTask t) {
		return this.konkaR3StoreTaskDao.selectEntityPaginatedList(t);
	}

	public String createKonkaR3StoreTask(List<KonkaR3StoreTask> list) {
		return this.konkaR3StoreTaskDao.insertKonkaR3StoreTaskList(list);
	}

}
