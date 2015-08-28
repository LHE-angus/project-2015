package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.EcBaseExpressDao;
import com.ebiz.mmt.domain.EcBaseExpress;
import com.ebiz.mmt.service.EcBaseExpressService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-09-23 19:07:22
 */
@Service
public class EcBaseExpressServiceImpl implements EcBaseExpressService {

	@Resource
	private EcBaseExpressDao ecBaseExpressDao;
	

	public Long createEcBaseExpress(EcBaseExpress t) {
		return this.ecBaseExpressDao.insertEntity(t);
	}

	public EcBaseExpress getEcBaseExpress(EcBaseExpress t) {
		return this.ecBaseExpressDao.selectEntity(t);
	}

	public Long getEcBaseExpressCount(EcBaseExpress t) {
		return this.ecBaseExpressDao.selectEntityCount(t);
	}

	public List<EcBaseExpress> getEcBaseExpressList(EcBaseExpress t) {
		return this.ecBaseExpressDao.selectEntityList(t);
	}

	public int modifyEcBaseExpress(EcBaseExpress t) {
		return this.ecBaseExpressDao.updateEntity(t);
	}

	public int removeEcBaseExpress(EcBaseExpress t) {
		return this.ecBaseExpressDao.deleteEntity(t);
	}

	public List<EcBaseExpress> getEcBaseExpressPaginatedList(EcBaseExpress t) {
		return this.ecBaseExpressDao.selectEntityPaginatedList(t);
	}

}
