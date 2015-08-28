package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaXxZmdRewardSetDao;
import com.ebiz.mmt.domain.KonkaXxZmdRewardSet;
import com.ebiz.mmt.service.KonkaXxZmdRewardSetService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2012-01-09 09:19:48
 */
@Service
public class KonkaXxZmdRewardSetServiceImpl implements KonkaXxZmdRewardSetService {

	@Resource
	private KonkaXxZmdRewardSetDao konkaXxZmdRewardSetDao;

	public Long createKonkaXxZmdRewardSet(KonkaXxZmdRewardSet t) {
		return this.konkaXxZmdRewardSetDao.insertEntity(t);
	}

	public KonkaXxZmdRewardSet getKonkaXxZmdRewardSet(KonkaXxZmdRewardSet t) {
		return this.konkaXxZmdRewardSetDao.selectEntity(t);
	}

	public Long getKonkaXxZmdRewardSetCount(KonkaXxZmdRewardSet t) {
		return this.konkaXxZmdRewardSetDao.selectEntityCount(t);
	}

	public List<KonkaXxZmdRewardSet> getKonkaXxZmdRewardSetList(KonkaXxZmdRewardSet t) {
		return this.konkaXxZmdRewardSetDao.selectEntityList(t);
	}

	public int modifyKonkaXxZmdRewardSet(KonkaXxZmdRewardSet t) {
		return this.konkaXxZmdRewardSetDao.updateEntity(t);
	}

	public int removeKonkaXxZmdRewardSet(KonkaXxZmdRewardSet t) {
		return this.konkaXxZmdRewardSetDao.deleteEntity(t);
	}

	public List<KonkaXxZmdRewardSet> getKonkaXxZmdRewardSetPaginatedList(KonkaXxZmdRewardSet t) {
		return this.konkaXxZmdRewardSetDao.selectEntityPaginatedList(t);
	}

	/**
	 * @author hu,hao
	 * @version 2012-03-09
	 */
	public List<KonkaXxZmdRewardSet> getKonkaXxZmdRewardSetForTypeNameList(KonkaXxZmdRewardSet t) {
		return this.konkaXxZmdRewardSetDao.selectKonkaXxZmdRewardSetForTypeNameList(t);
	}

}
