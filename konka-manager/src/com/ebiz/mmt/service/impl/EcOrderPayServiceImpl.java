package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.EcOrderPayDao;
import com.ebiz.mmt.domain.EcOrderPay;
import com.ebiz.mmt.service.EcOrderPayService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-07-11 16:03:36
 */
@Service
public class EcOrderPayServiceImpl implements EcOrderPayService {

	@Resource
	private EcOrderPayDao ecOrderPayDao;
	

	public Long createEcOrderPay(EcOrderPay t) {
		return this.ecOrderPayDao.insertEntity(t);
	}

	public EcOrderPay getEcOrderPay(EcOrderPay t) {
		return this.ecOrderPayDao.selectEntity(t);
	}

	public Long getEcOrderPayCount(EcOrderPay t) {
		return this.ecOrderPayDao.selectEntityCount(t);
	}

	public List<EcOrderPay> getEcOrderPayList(EcOrderPay t) {
		return this.ecOrderPayDao.selectEntityList(t);
	}

	public int modifyEcOrderPay(EcOrderPay t) {
		return this.ecOrderPayDao.updateEntity(t);
	}

	public int removeEcOrderPay(EcOrderPay t) {
		return this.ecOrderPayDao.deleteEntity(t);
	}

	public List<EcOrderPay> getEcOrderPayPaginatedList(EcOrderPay t) {
		return this.ecOrderPayDao.selectEntityPaginatedList(t);
	}

}
