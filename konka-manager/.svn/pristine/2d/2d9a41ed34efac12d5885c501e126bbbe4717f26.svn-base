package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.EcVouchCodeDao;
import com.ebiz.mmt.domain.EcVouchCode;
import com.ebiz.mmt.service.EcVouchCodeService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-11-24 17:45:53
 */
@Service
public class EcVouchCodeServiceImpl implements EcVouchCodeService {

	@Resource
	private EcVouchCodeDao ecVouchCodeDao;

	public Long createEcVouchCode(EcVouchCode t) {
		return this.ecVouchCodeDao.insertEntity(t);
	}

	public EcVouchCode getEcVouchCode(EcVouchCode t) {
		return this.ecVouchCodeDao.selectEntity(t);
	}

	public Long getEcVouchCodeCount(EcVouchCode t) {
		return this.ecVouchCodeDao.selectEntityCount(t);
	}

	public List<EcVouchCode> getEcVouchCodeList(EcVouchCode t) {
		return this.ecVouchCodeDao.selectEntityList(t);
	}

	public int modifyEcVouchCode(EcVouchCode t) {
		return this.ecVouchCodeDao.updateEntity(t);
	}

	public int removeEcVouchCode(EcVouchCode t) {
		return this.ecVouchCodeDao.deleteEntity(t);
	}

	public List<EcVouchCode> getEcVouchCodePaginatedList(EcVouchCode t) {
		return this.ecVouchCodeDao.selectEntityPaginatedList(t);
	}

	@Override
	public List<EcVouchCode> getEcVouchCodeAndCodeList(EcVouchCode t) {
		return this.ecVouchCodeDao.selectEcVouchCodeAndCodeList(t);
	}

}
