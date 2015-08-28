package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.EcBaseCardTypeDao;
import com.ebiz.mmt.domain.EcBaseCardType;
import com.ebiz.mmt.service.EcBaseCardTypeService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-11-14 15:05:08
 */
@Service
public class EcBaseCardTypeServiceImpl implements EcBaseCardTypeService {

	@Resource
	private EcBaseCardTypeDao ecBaseCardTypeDao;
	

	public Long createEcBaseCardType(EcBaseCardType t) {
		return this.ecBaseCardTypeDao.insertEntity(t);
	}

	public EcBaseCardType getEcBaseCardType(EcBaseCardType t) {
		return this.ecBaseCardTypeDao.selectEntity(t);
	}

	public Long getEcBaseCardTypeCount(EcBaseCardType t) {
		return this.ecBaseCardTypeDao.selectEntityCount(t);
	}

	public List<EcBaseCardType> getEcBaseCardTypeList(EcBaseCardType t) {
		return this.ecBaseCardTypeDao.selectEntityList(t);
	}

	public int modifyEcBaseCardType(EcBaseCardType t) {
		return this.ecBaseCardTypeDao.updateEntity(t);
	}

	public int removeEcBaseCardType(EcBaseCardType t) {
		return this.ecBaseCardTypeDao.deleteEntity(t);
	}

	public List<EcBaseCardType> getEcBaseCardTypePaginatedList(EcBaseCardType t) {
		return this.ecBaseCardTypeDao.selectEntityPaginatedList(t);
	}

}
