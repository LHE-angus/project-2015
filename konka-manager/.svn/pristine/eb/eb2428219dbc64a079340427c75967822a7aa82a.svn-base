package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.EcGiftOrdeDao;
import com.ebiz.mmt.dao.EcUserScoreRecDao;
import com.ebiz.mmt.domain.EcGiftOrde;
import com.ebiz.mmt.service.EcGiftOrdeService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-09-09 10:06:25
 */
@Service
public class EcGiftOrdeServiceImpl implements EcGiftOrdeService {

	@Resource
	private EcGiftOrdeDao ecGiftOrdeDao;

	@Resource
	private EcUserScoreRecDao ecUserScoreRecDao;

	public Long createEcGiftOrde(EcGiftOrde t) {
		return this.ecGiftOrdeDao.insertEntity(t);
	}

	public EcGiftOrde getEcGiftOrde(EcGiftOrde t) {
		return this.ecGiftOrdeDao.selectEntity(t);
	}

	public Long getEcGiftOrdeCount(EcGiftOrde t) {
		return this.ecGiftOrdeDao.selectEntityCount(t);
	}

	public List<EcGiftOrde> getEcGiftOrdeList(EcGiftOrde t) {
		return this.ecGiftOrdeDao.selectEntityList(t);
	}

	public int modifyEcGiftOrde(EcGiftOrde t) {
		return this.ecGiftOrdeDao.updateEntity(t);
	}

	public int removeEcGiftOrde(EcGiftOrde t) {
		return this.ecGiftOrdeDao.deleteEntity(t);
	}

	public List<EcGiftOrde> getEcGiftOrdePaginatedList(EcGiftOrde t) {
		return this.ecGiftOrdeDao.selectEntityPaginatedList(t);
	}

	public int modifyEcGiftOrdeIncludeStore(EcGiftOrde t) {
		if (null != t.getEcUserScoreRec()) {
			this.ecUserScoreRecDao.insertEntity(t.getEcUserScoreRec());
		}

		return this.ecGiftOrdeDao.updateEntity(t);
	}

	public Long createEcGiftOrdeIncludeStore(EcGiftOrde t) {
		if (null != t.getEcUserScoreRec()) {
			this.ecUserScoreRecDao.insertEntity(t.getEcUserScoreRec());
		}
		return this.ecGiftOrdeDao.insertEntity(t);
	}

	/**
	 * @author Liu,ZhiXiang
	 * @version 2013-09-17
	 */
	public Long getEcGiftOrdeForDeptNameAndFullNameListCount(EcGiftOrde t) {
		return this.ecGiftOrdeDao.selectEcGiftOrdeForDeptNameAndFullNameListCount(t);
	}

	/**
	 * @author Liu,ZhiXiang
	 * @version 2013-09-17
	 */
	public List<EcGiftOrde> getEcGiftOrdeForDeptNameAndFullNamePaginatedList(EcGiftOrde t) {
		return this.ecGiftOrdeDao.selectEcGiftOrdeForDeptNameAndFullNamePaginatedList(t);
	}

}
