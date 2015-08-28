package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaMobilePaymentChisDao;
import com.ebiz.mmt.domain.KonkaMobilePaymentChis;
import com.ebiz.mmt.service.KonkaMobilePaymentChisService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-05-06 17:18:42
 */
@Service
public class KonkaMobilePaymentChisServiceImpl implements KonkaMobilePaymentChisService {

	@Resource
	private KonkaMobilePaymentChisDao konkaMobilePaymentChisDao;
	

	public Long createKonkaMobilePaymentChis(KonkaMobilePaymentChis t) {
		return this.konkaMobilePaymentChisDao.insertEntity(t);
	}

	public KonkaMobilePaymentChis getKonkaMobilePaymentChis(KonkaMobilePaymentChis t) {
		return this.konkaMobilePaymentChisDao.selectEntity(t);
	}

	public Long getKonkaMobilePaymentChisCount(KonkaMobilePaymentChis t) {
		return this.konkaMobilePaymentChisDao.selectEntityCount(t);
	}

	public List<KonkaMobilePaymentChis> getKonkaMobilePaymentChisList(KonkaMobilePaymentChis t) {
		return this.konkaMobilePaymentChisDao.selectEntityList(t);
	}

	public int modifyKonkaMobilePaymentChis(KonkaMobilePaymentChis t) {
		return this.konkaMobilePaymentChisDao.updateEntity(t);
	}

	public int removeKonkaMobilePaymentChis(KonkaMobilePaymentChis t) {
		return this.konkaMobilePaymentChisDao.deleteEntity(t);
	}

	public List<KonkaMobilePaymentChis> getKonkaMobilePaymentChisPaginatedList(KonkaMobilePaymentChis t) {
		return this.konkaMobilePaymentChisDao.selectEntityPaginatedList(t);
	}

}
