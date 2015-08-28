package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaDeptJbTaskDao;
import com.ebiz.mmt.domain.KonkaDeptJbTask;
import com.ebiz.mmt.service.KonkaDeptJbTaskService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-12-17 11:44:15
 */
@Service
public class KonkaDeptJbTaskServiceImpl implements KonkaDeptJbTaskService {

	@Resource
	private KonkaDeptJbTaskDao konkaDeptJbTaskDao;

	@Override
	public Long createKonkaDeptJbTask(KonkaDeptJbTask t) {
		return this.konkaDeptJbTaskDao.insertEntity(t);
	}

	@Override
	public KonkaDeptJbTask getKonkaDeptJbTask(KonkaDeptJbTask t) {
		return this.konkaDeptJbTaskDao.selectEntity(t);
	}

	@Override
	public Long getKonkaDeptJbTaskCount(KonkaDeptJbTask t) {
		return this.konkaDeptJbTaskDao.selectEntityCount(t);
	}

	@Override
	public List<KonkaDeptJbTask> getKonkaDeptJbTaskList(KonkaDeptJbTask t) {
		return this.konkaDeptJbTaskDao.selectEntityList(t);
	}

	@Override
	public int modifyKonkaDeptJbTask(KonkaDeptJbTask t) {
		return this.konkaDeptJbTaskDao.updateEntity(t);
	}

	@Override
	public int removeKonkaDeptJbTask(KonkaDeptJbTask t) {
		return this.konkaDeptJbTaskDao.deleteEntity(t);
	}

	@Override
	public List<KonkaDeptJbTask> getKonkaDeptJbTaskPaginatedList(KonkaDeptJbTask t) {
		return this.konkaDeptJbTaskDao.selectEntityPaginatedList(t);
	}

	@Override
	public String createKonkaDeptJbTaskByExcel(List<KonkaDeptJbTask> list) {
		return this.konkaDeptJbTaskDao.insertKonkaDeptJbTask(list);
	}

	@Override
	public List<KonkaDeptJbTask> getKonkaDeptJbTaskForBackMoneyList(KonkaDeptJbTask t) {
		return this.konkaDeptJbTaskDao.selectKonkaDeptJbTaskForBackMoneyList(t);
	}

}
