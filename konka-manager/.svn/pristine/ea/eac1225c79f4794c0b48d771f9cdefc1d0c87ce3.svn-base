package com.ebiz.mmt.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaPdModelTaskDao;
import com.ebiz.mmt.domain.KonkaPdModelTask;
import com.ebiz.mmt.service.KonkaPdModelTaskService;


@Service
public class KonkaPdModelTaskServiceImpl implements KonkaPdModelTaskService {

	@Resource
	private KonkaPdModelTaskDao konkaPdModelTaskDao;

	@Override
	public Long createKonkaPdModelTask(KonkaPdModelTask t) {
		return this.konkaPdModelTaskDao.insertEntity(t);
	}

	@Override
	public KonkaPdModelTask getKonkaPdModelTask(KonkaPdModelTask t) {
		return this.konkaPdModelTaskDao.selectEntity(t);
	}

	@Override
	public Long getKonkaPdModelTaskCount(KonkaPdModelTask t) {
		return this.konkaPdModelTaskDao.selectEntityCount(t);
	}

	@Override
	public List<KonkaPdModelTask> getKonkaPdModelTaskList(KonkaPdModelTask t) {
		return this.konkaPdModelTaskDao.selectEntityList(t);
	}

	@Override
	public int modifyKonkaPdModelTask(KonkaPdModelTask t) {
		return this.konkaPdModelTaskDao.updateEntity(t);
	}

	@Override
	public int removeKonkaPdModelTask(KonkaPdModelTask t) {
		return this.konkaPdModelTaskDao.deleteEntity(t);
	}

	@Override
	public List<KonkaPdModelTask> getKonkaPdModelTaskPaginatedList(KonkaPdModelTask t) {
		return this.konkaPdModelTaskDao.selectEntityPaginatedList(t);
	}

	@Override
	public String createKonkaPdModelTask(List<KonkaPdModelTask> list) {
		return this.konkaPdModelTaskDao.insertKonkaPdModelTask(list);
	}


	@Override
	public List<HashMap<String, String>> getKonkaPdModelXCLY(KonkaPdModelTask t) {
		return this.konkaPdModelTaskDao.selectKonkaPdModelXCLY(t);
	}

	@Override
	public List<HashMap<String, Object>> getKonkaPdModelXCLYHZ(KonkaPdModelTask t) {
		return this.konkaPdModelTaskDao.selectKonkaPdModelXCLYHZ(t);
	}
}
