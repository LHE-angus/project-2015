package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.EcUserScoreRecDao;
import com.ebiz.mmt.domain.EcUserScoreRec;
import com.ebiz.mmt.service.EcUserScoreRecService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-09-09 10:06:25
 */
@Service
public class EcUserScoreRecServiceImpl implements EcUserScoreRecService {

	@Resource
	private EcUserScoreRecDao ecUserScoreRecDao;

	public Long createEcUserScoreRec(EcUserScoreRec t) {
		return this.ecUserScoreRecDao.insertEntity(t);
	}

	public EcUserScoreRec getEcUserScoreRec(EcUserScoreRec t) {
		return this.ecUserScoreRecDao.selectEntity(t);
	}

	public Long getEcUserScoreRecCount(EcUserScoreRec t) {
		return this.ecUserScoreRecDao.selectEntityCount(t);
	}

	public List<EcUserScoreRec> getEcUserScoreRecList(EcUserScoreRec t) {
		return this.ecUserScoreRecDao.selectEntityList(t);
	}

	public int modifyEcUserScoreRec(EcUserScoreRec t) {
		return this.ecUserScoreRecDao.updateEntity(t);
	}

	public int removeEcUserScoreRec(EcUserScoreRec t) {
		return this.ecUserScoreRecDao.deleteEntity(t);
	}

	public List<EcUserScoreRec> getEcUserScoreRecPaginatedList(EcUserScoreRec t) {
		return this.ecUserScoreRecDao.selectEntityPaginatedList(t);
	}

	public String getEcUserScoreRecForUserTotalScore(Long user_id) {
		String v = this.ecUserScoreRecDao.selectEcUserScoreRecForUserTotalScore(user_id);
		if (v == null)
			v = "0";
		return v;
	}

	public String getEcUserScoreRecForPayJf(Long userId) {
		String v = this.ecUserScoreRecDao.selectEcUserScoreRecForPayJf(userId);
		if (v == null)
			v = "0";
		return v;
	}
	
	public String getEcUserScoreRecForUserXFScore(Long userId) {
		String v = this.ecUserScoreRecDao.selectEcUserScoreRecForUserXFScore(userId);
		if (v == null)
			v = "0";
		return v;
	}
	
	public String getEcUserScoreRecForUserJLScore(Long userId) {
		String v = this.ecUserScoreRecDao.selectEcUserScoreRecForUserJLScore(userId);
		if (v == null)
			v = "0";
		return v;
	}

}
