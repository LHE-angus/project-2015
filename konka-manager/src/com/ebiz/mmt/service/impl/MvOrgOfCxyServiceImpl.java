package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.MvOrgOfCxyDao;
import com.ebiz.mmt.domain.MvOrgOfCxy;
import com.ebiz.mmt.service.MvOrgOfCxyService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2015-05-25 14:26:30
 */
@Service
public class MvOrgOfCxyServiceImpl implements MvOrgOfCxyService {

	@Resource
	private MvOrgOfCxyDao mvOrgOfCxyDao;
	

	public Long createMvOrgOfCxy(MvOrgOfCxy t) {
		return this.mvOrgOfCxyDao.insertEntity(t);
	}

	public MvOrgOfCxy getMvOrgOfCxy(MvOrgOfCxy t) {
		return this.mvOrgOfCxyDao.selectEntity(t);
	}

	public Long getMvOrgOfCxyCount(MvOrgOfCxy t) {
		return this.mvOrgOfCxyDao.selectEntityCount(t);
	}

	public List<MvOrgOfCxy> getMvOrgOfCxyList(MvOrgOfCxy t) {
		return this.mvOrgOfCxyDao.selectEntityList(t);
	}

	public int modifyMvOrgOfCxy(MvOrgOfCxy t) {
		return this.mvOrgOfCxyDao.updateEntity(t);
	}

	public int removeMvOrgOfCxy(MvOrgOfCxy t) {
		return this.mvOrgOfCxyDao.deleteEntity(t);
	}

	public List<MvOrgOfCxy> getMvOrgOfCxyPaginatedList(MvOrgOfCxy t) {
		return this.mvOrgOfCxyDao.selectEntityPaginatedList(t);
	}

}
