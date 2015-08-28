package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaMobilePaymentDao;
import com.ebiz.mmt.domain.KonkaMobilePayment;
import com.ebiz.mmt.service.KonkaMobilePaymentService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-05-06 17:18:42
 */
@Service
public class KonkaMobilePaymentServiceImpl implements KonkaMobilePaymentService {

	@Resource
	private KonkaMobilePaymentDao konkaMobilePaymentDao;
	

	public Long createKonkaMobilePayment(KonkaMobilePayment t) {
		return this.konkaMobilePaymentDao.insertEntity(t);
	}

	public KonkaMobilePayment getKonkaMobilePayment(KonkaMobilePayment t) {
		return this.konkaMobilePaymentDao.selectEntity(t);
	}

	public Long getKonkaMobilePaymentCount(KonkaMobilePayment t) {
		return this.konkaMobilePaymentDao.selectEntityCount(t);
	}

	public List<KonkaMobilePayment> getKonkaMobilePaymentList(KonkaMobilePayment t) {
		return this.konkaMobilePaymentDao.selectEntityList(t);
	}

	public int modifyKonkaMobilePayment(KonkaMobilePayment t) {
		return this.konkaMobilePaymentDao.updateEntity(t);
	}

	public int removeKonkaMobilePayment(KonkaMobilePayment t) {
		return this.konkaMobilePaymentDao.deleteEntity(t);
	}

	public List<KonkaMobilePayment> getKonkaMobilePaymentPaginatedList(KonkaMobilePayment t) {
		return this.konkaMobilePaymentDao.selectEntityPaginatedList(t);
	}

}
