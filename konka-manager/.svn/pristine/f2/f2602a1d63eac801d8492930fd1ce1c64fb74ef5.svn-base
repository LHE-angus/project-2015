package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.EcVouchersTypeDao;
import com.ebiz.mmt.domain.EcVouchersType;
import com.ebiz.mmt.service.EcVouchersTypeService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-12-25 15:36:42
 */
@Service
public class EcVouchersTypeServiceImpl implements EcVouchersTypeService {

	@Resource
	private EcVouchersTypeDao ecVouchersTypeDao;
	

	public Long createEcVouchersType(EcVouchersType t) {
		return this.ecVouchersTypeDao.insertEntity(t);
	}

	public EcVouchersType getEcVouchersType(EcVouchersType t) {
		return this.ecVouchersTypeDao.selectEntity(t);
	}

	public Long getEcVouchersTypeCount(EcVouchersType t) {
		return this.ecVouchersTypeDao.selectEntityCount(t);
	}

	public List<EcVouchersType> getEcVouchersTypeList(EcVouchersType t) {
		return this.ecVouchersTypeDao.selectEntityList(t);
	}

	public int modifyEcVouchersType(EcVouchersType t) {
		return this.ecVouchersTypeDao.updateEntity(t);
	}

	public int removeEcVouchersType(EcVouchersType t) {
		return this.ecVouchersTypeDao.deleteEntity(t);
	}

	public List<EcVouchersType> getEcVouchersTypePaginatedList(EcVouchersType t) {
		return this.ecVouchersTypeDao.selectEntityPaginatedList(t);
	}

}
