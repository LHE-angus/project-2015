package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.EcCustDao;
import com.ebiz.mmt.domain.EcCust;
import com.ebiz.mmt.service.EcCustService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-12-08 14:09:39
 */
@Service
public class EcCustServiceImpl implements EcCustService {

	@Resource
	private EcCustDao ecCustDao;

	public Long createEcCust(EcCust t) {
		return this.ecCustDao.insertEntity(t);
	}

	public EcCust getEcCust(EcCust t) {
		return this.ecCustDao.selectEntity(t);
	}

	public Long getEcCustCount(EcCust t) {
		return this.ecCustDao.selectEntityCount(t);
	}

	public List<EcCust> getEcCustList(EcCust t) {
		return this.ecCustDao.selectEntityList(t);
	}

	public int modifyEcCust(EcCust t) {
		return this.ecCustDao.updateEntity(t);
	}

	public int removeEcCust(EcCust t) {
		return this.ecCustDao.deleteEntity(t);
	}

	public List<EcCust> getEcCustPaginatedList(EcCust t) {
		return this.ecCustDao.selectEntityPaginatedList(t);
	}

	@Override
	public Long getEcCustForDetailsCount(EcCust t) {
		return this.ecCustDao.selectEcCustForDetailsCount(t);
	}

	@Override
	public List<EcCust> getEcCustForDetailsPaginatedList(EcCust t) {
		return this.ecCustDao.selectEcCustForDetailsPaginatedList(t);
	}

}
