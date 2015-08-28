package com.ebiz.mmt.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaSpTaskDao;
import com.ebiz.mmt.domain.KonkaSpTask;
import com.ebiz.mmt.service.KonkaSpTaskService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-01-23 11:36:10
 */
@Service
public class KonkaSpTaskServiceImpl implements KonkaSpTaskService {

	@Resource
	private KonkaSpTaskDao konkaSpTaskDao;

	public Long createKonkaSpTask(KonkaSpTask t) {
		return this.konkaSpTaskDao.insertEntity(t);
	}

	public KonkaSpTask getKonkaSpTask(KonkaSpTask t) {
		return this.konkaSpTaskDao.selectEntity(t);
	}

	public Long getKonkaSpTaskCount(KonkaSpTask t) {
		return this.konkaSpTaskDao.selectEntityCount(t);
	}

	public List<KonkaSpTask> getKonkaSpTaskList(KonkaSpTask t) {
		return this.konkaSpTaskDao.selectEntityList(t);
	}

	public int modifyKonkaSpTask(KonkaSpTask t) {
		return this.konkaSpTaskDao.updateEntity(t);
	}

	public int removeKonkaSpTask(KonkaSpTask t) {
		return this.konkaSpTaskDao.deleteEntity(t);
	}

	public List<KonkaSpTask> getKonkaSpTaskPaginatedList(KonkaSpTask t) {
		return this.konkaSpTaskDao.selectEntityPaginatedList(t);
	}

	public Long createKonkaSpTaskForExcel(List<KonkaSpTask> entityList) {
		if (null != entityList && entityList.size() > 0) {
			for (KonkaSpTask kpt : entityList) {
				KonkaSpTask t = new KonkaSpTask();
				t.setYear(kpt.getYear());
				t.setMonth(kpt.getMonth());
				t.setDept_name(kpt.getDept_name());
				t.setDept_sn(kpt.getDept_sn());
				t.setDept_id(kpt.getDept_id());
				t.setTask_money(kpt.getTask_money());
				t.setAdd_user(kpt.getAdd_user());
				t.setAdd_user_job_id(kpt.getAdd_user_job_id());
				t.setAdd_date(new Date());
				t.setUpdate_date(new Date());
				this.konkaSpTaskDao.insertEntity(kpt);
				//System.out.println("add_date:"+t.getAdd_date()+"    update_date:"+t.getUpdate_date());
			}
		}
		return null;
	}

}
