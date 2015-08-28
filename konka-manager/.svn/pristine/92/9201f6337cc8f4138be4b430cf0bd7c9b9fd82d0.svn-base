package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaDeptJsMoneyDao;
import com.ebiz.mmt.domain.KonkaDeptJsMoney;
import com.ebiz.mmt.service.KonkaDeptJsMoneyService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-01-17 14:56:37
 */
@Service
public class KonkaDeptJsMoneyServiceImpl implements KonkaDeptJsMoneyService {

	@Resource
	private KonkaDeptJsMoneyDao konkaDeptJsMoneyDao;
	

	public Long createKonkaDeptJsMoney(KonkaDeptJsMoney t) {
		return this.konkaDeptJsMoneyDao.insertEntity(t);
	}

	public KonkaDeptJsMoney getKonkaDeptJsMoney(KonkaDeptJsMoney t) {
		return this.konkaDeptJsMoneyDao.selectEntity(t);
	}

	public Long getKonkaDeptJsMoneyCount(KonkaDeptJsMoney t) {
		return this.konkaDeptJsMoneyDao.selectEntityCount(t);
	}

	public List<KonkaDeptJsMoney> getKonkaDeptJsMoneyList(KonkaDeptJsMoney t) {
		return this.konkaDeptJsMoneyDao.selectEntityList(t);
	}

	public int modifyKonkaDeptJsMoney(KonkaDeptJsMoney t) {
		return this.konkaDeptJsMoneyDao.updateEntity(t);
	}

	public int removeKonkaDeptJsMoney(KonkaDeptJsMoney t) {
		return this.konkaDeptJsMoneyDao.deleteEntity(t);
	}

	public List<KonkaDeptJsMoney> getKonkaDeptJsMoneyPaginatedList(KonkaDeptJsMoney t) {
		return this.konkaDeptJsMoneyDao.selectEntityPaginatedList(t);
	}

	/**
	 * @author Hu,Hao
	 * @version 2013-01-18
	 */
	public List<KonkaDeptJsMoney> getKonkaDeptJsMoneyToR3List(KonkaDeptJsMoney t){
		return this.konkaDeptJsMoneyDao.selectKonkaDeptJsMoneyToR3List(t);
	}
}
