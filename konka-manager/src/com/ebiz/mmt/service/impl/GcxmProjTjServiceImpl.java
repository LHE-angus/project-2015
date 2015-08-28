package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.GcxmProjTjDao;
import com.ebiz.mmt.domain.GcxmProjTj;
import com.ebiz.mmt.service.GcxmProjTjService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2015-03-23 13:53:09
 */
@Service
public class GcxmProjTjServiceImpl implements GcxmProjTjService {

	@Resource
	private GcxmProjTjDao gcxmProjTjDao;
	

	public Long createGcxmProjTj(GcxmProjTj t) {
		return this.gcxmProjTjDao.insertEntity(t);
	}

	public GcxmProjTj getGcxmProjTj(GcxmProjTj t) {
		return this.gcxmProjTjDao.selectEntity(t);
	}

	public Long getGcxmProjTjCount(GcxmProjTj t) {
		return this.gcxmProjTjDao.selectEntityCount(t);
	}

	public List<GcxmProjTj> getGcxmProjTjList(GcxmProjTj t) {
		return this.gcxmProjTjDao.selectEntityList(t);
	}

	public int modifyGcxmProjTj(GcxmProjTj t) {
		return this.gcxmProjTjDao.updateEntity(t);
	}

	public int removeGcxmProjTj(GcxmProjTj t) {
		return this.gcxmProjTjDao.deleteEntity(t);
	}

	public List<GcxmProjTj> getGcxmProjTjPaginatedList(GcxmProjTj t) {
		return this.gcxmProjTjDao.selectEntityPaginatedList(t);
	}

}
