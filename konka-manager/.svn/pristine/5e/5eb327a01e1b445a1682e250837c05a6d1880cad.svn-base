package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaJobsDao;
import com.ebiz.mmt.domain.KonkaJobs;
import com.ebiz.mmt.service.KonkaJobsService;


@Service
public class KonkaJobsServiceImpl implements KonkaJobsService {

	@Resource
	private KonkaJobsDao konkaJobsDao;
	

	public Long createKonkaJobs(KonkaJobs t) {
		return this.konkaJobsDao.insertEntity(t);
	}

	public KonkaJobs getKonkaJobs(KonkaJobs t) {
		return this.konkaJobsDao.selectEntity(t);
	}

	public Long getKonkaJobsCount(KonkaJobs t) {
		return this.konkaJobsDao.selectEntityCount(t);
	}

	public List<KonkaJobs> getKonkaJobsList(KonkaJobs t) {
		return this.konkaJobsDao.selectEntityList(t);
	}

	public int modifyKonkaJobs(KonkaJobs t) {
		return this.konkaJobsDao.updateEntity(t);
	}

	public int removeKonkaJobs(KonkaJobs t) {
		return this.konkaJobsDao.deleteEntity(t);
	}

	public List<KonkaJobs> getKonkaJobsPaginatedList(KonkaJobs t) {
		return this.konkaJobsDao.selectEntityPaginatedList(t);
	}

}
