package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.EcSpecFbTeamListDao;
import com.ebiz.mmt.domain.EcSpecFbTeamList;
import com.ebiz.mmt.service.EcSpecFbTeamListService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-06-08 10:31:47
 */
@Service
public class EcSpecFbTeamListServiceImpl implements EcSpecFbTeamListService {

	@Resource
	private EcSpecFbTeamListDao ecSpecFbTeamListDao;
	

	public Long createEcSpecFbTeamList(EcSpecFbTeamList t) {
		return this.ecSpecFbTeamListDao.insertEntity(t);
	}

	public EcSpecFbTeamList getEcSpecFbTeamList(EcSpecFbTeamList t) {
		return this.ecSpecFbTeamListDao.selectEntity(t);
	}

	public Long getEcSpecFbTeamListCount(EcSpecFbTeamList t) {
		return this.ecSpecFbTeamListDao.selectEntityCount(t);
	}

	public List<EcSpecFbTeamList> getEcSpecFbTeamListList(EcSpecFbTeamList t) {
		return this.ecSpecFbTeamListDao.selectEntityList(t);
	}

	public int modifyEcSpecFbTeamList(EcSpecFbTeamList t) {
		return this.ecSpecFbTeamListDao.updateEntity(t);
	}

	public int removeEcSpecFbTeamList(EcSpecFbTeamList t) {
		return this.ecSpecFbTeamListDao.deleteEntity(t);
	}

	public List<EcSpecFbTeamList> getEcSpecFbTeamListPaginatedList(EcSpecFbTeamList t) {
		return this.ecSpecFbTeamListDao.selectEntityPaginatedList(t);
	}

}
