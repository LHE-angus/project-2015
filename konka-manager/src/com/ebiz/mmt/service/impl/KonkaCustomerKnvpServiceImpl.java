package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaCustomerKnvpDao;
import com.ebiz.mmt.domain.KonkaCustomerKnvp;
import com.ebiz.mmt.service.KonkaCustomerKnvpService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-11-18 14:10:55
 */
@Service
public class KonkaCustomerKnvpServiceImpl implements KonkaCustomerKnvpService {

	@Resource
	private KonkaCustomerKnvpDao konkaCustomerKnvpDao;
	

	public Long createKonkaCustomerKnvp(KonkaCustomerKnvp t) {
		return this.konkaCustomerKnvpDao.insertEntity(t);
	}

	public KonkaCustomerKnvp getKonkaCustomerKnvp(KonkaCustomerKnvp t) {
		return this.konkaCustomerKnvpDao.selectEntity(t);
	}

	public Long getKonkaCustomerKnvpCount(KonkaCustomerKnvp t) {
		return this.konkaCustomerKnvpDao.selectEntityCount(t);
	}

	public List<KonkaCustomerKnvp> getKonkaCustomerKnvpList(KonkaCustomerKnvp t) {
		return this.konkaCustomerKnvpDao.selectEntityList(t);
	}

	public int modifyKonkaCustomerKnvp(KonkaCustomerKnvp t) {
		return this.konkaCustomerKnvpDao.updateEntity(t);
	}

	public int removeKonkaCustomerKnvp(KonkaCustomerKnvp t) {
		return this.konkaCustomerKnvpDao.deleteEntity(t);
	}

	public List<KonkaCustomerKnvp> getKonkaCustomerKnvpPaginatedList(KonkaCustomerKnvp t) {
		return this.konkaCustomerKnvpDao.selectEntityPaginatedList(t);
	}

}
