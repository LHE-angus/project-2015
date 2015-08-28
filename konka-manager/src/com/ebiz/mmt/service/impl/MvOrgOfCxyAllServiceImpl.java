package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.MvOrgOfCxyAllDao;
import com.ebiz.mmt.domain.MvOrgOfCxyAll;
import com.ebiz.mmt.service.MvOrgOfCxyAllService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2015-05-25 14:26:30
 */
@Service
public class MvOrgOfCxyAllServiceImpl implements MvOrgOfCxyAllService {

	@Resource
	private MvOrgOfCxyAllDao mvOrgOfCxyAllDao;
	

	public Long createMvOrgOfCxyAll(MvOrgOfCxyAll t) {
		return this.mvOrgOfCxyAllDao.insertEntity(t);
	}

	public MvOrgOfCxyAll getMvOrgOfCxyAll(MvOrgOfCxyAll t) {
		return this.mvOrgOfCxyAllDao.selectEntity(t);
	}

	public Long getMvOrgOfCxyAllCount(MvOrgOfCxyAll t) {
		return this.mvOrgOfCxyAllDao.selectEntityCount(t);
	}

	public List<MvOrgOfCxyAll> getMvOrgOfCxyAllList(MvOrgOfCxyAll t) {
		return this.mvOrgOfCxyAllDao.selectEntityList(t);
	}

	public int modifyMvOrgOfCxyAll(MvOrgOfCxyAll t) {
		return this.mvOrgOfCxyAllDao.updateEntity(t);
	}

	public int removeMvOrgOfCxyAll(MvOrgOfCxyAll t) {
		return this.mvOrgOfCxyAllDao.deleteEntity(t);
	}

	public List<MvOrgOfCxyAll> getMvOrgOfCxyAllPaginatedList(MvOrgOfCxyAll t) {
		return this.mvOrgOfCxyAllDao.selectEntityPaginatedList(t);
	}

}
