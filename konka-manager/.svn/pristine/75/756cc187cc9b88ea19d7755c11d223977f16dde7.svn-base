package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.EcExtendDao;
import com.ebiz.mmt.domain.EcExtend;
import com.ebiz.mmt.service.EcExtendService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-03-04 15:46:59
 */
@Service
public class EcExtendServiceImpl implements EcExtendService {

	@Resource
	private EcExtendDao ecExtendDao;

	public Long createEcExtend(EcExtend t) {
		return this.ecExtendDao.insertEntity(t);
	}

	public EcExtend getEcExtend(EcExtend t) {
		return this.ecExtendDao.selectEntity(t);
	}

	public Long getEcExtendCount(EcExtend t) {
		return this.ecExtendDao.selectEntityCount(t);
	}

	public List<EcExtend> getEcExtendList(EcExtend t) {
		return this.ecExtendDao.selectEntityList(t);
	}

	public int modifyEcExtend(EcExtend t) {
		return this.ecExtendDao.updateEntity(t);
	}

	public int removeEcExtend(EcExtend t) {
		return this.ecExtendDao.deleteEntity(t);
	}

	public List<EcExtend> getEcExtendPaginatedList(EcExtend t) {
		return this.ecExtendDao.selectEntityPaginatedList(t);
	}

	public List<EcExtend> getEcExtendGroupByPropNameList(EcExtend t) {
		return this.ecExtendDao.selectEcExtendGroupByPropNameList(t);
	}

	public List<EcExtend> getEcExtendGroupByPropNameForPropValueList(EcExtend t) {
		return this.ecExtendDao.selectEcExtendGroupByPropNameForPropValueList(t);
	}

}
