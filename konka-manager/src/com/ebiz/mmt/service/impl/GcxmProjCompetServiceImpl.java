package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.GcxmProjCompetDao;
import com.ebiz.mmt.domain.GcxmProjCompet;
import com.ebiz.mmt.service.GcxmProjCompetService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2015-03-23 13:53:09
 */
@Service
public class GcxmProjCompetServiceImpl implements GcxmProjCompetService {

	@Resource
	private GcxmProjCompetDao gcxmProjCompetDao;

	public Long createGcxmProjCompet(GcxmProjCompet t) {
		return this.gcxmProjCompetDao.insertEntity(t);
	}

	public GcxmProjCompet getGcxmProjCompet(GcxmProjCompet t) {
		return this.gcxmProjCompetDao.selectEntity(t);
	}

	public Long getGcxmProjCompetCount(GcxmProjCompet t) {
		return this.gcxmProjCompetDao.selectEntityCount(t);
	}

	public List<GcxmProjCompet> getGcxmProjCompetList(GcxmProjCompet t) {
		return this.gcxmProjCompetDao.selectEntityList(t);
	}

	public int modifyGcxmProjCompet(GcxmProjCompet t) {
		return this.gcxmProjCompetDao.updateEntity(t);
	}

	public int removeGcxmProjCompet(GcxmProjCompet t) {
		return this.gcxmProjCompetDao.deleteEntity(t);
	}

	public List<GcxmProjCompet> getGcxmProjCompetPaginatedList(GcxmProjCompet t) {
		return this.gcxmProjCompetDao.selectEntityPaginatedList(t);
	}

	@Override
	public Long getGcxmProjCompetForProjCount(GcxmProjCompet t) {
		return this.gcxmProjCompetDao.selectGcxmProjCompetForProjCount(t);
	}

	@Override
	public List<GcxmProjCompet> getGcxmProjCompetForProjPaginatedList(GcxmProjCompet t) {
		return this.gcxmProjCompetDao.selectGcxmProjCompetForProjPaginatedList(t);
	}

}
