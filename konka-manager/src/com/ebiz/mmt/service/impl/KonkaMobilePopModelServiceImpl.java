package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaMobilePopModelDao;
import com.ebiz.mmt.domain.KonkaMobilePopModel;
import com.ebiz.mmt.service.KonkaMobilePopModelService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-05-04 00:44:48
 */
@Service
public class KonkaMobilePopModelServiceImpl implements KonkaMobilePopModelService {

	@Resource
	private KonkaMobilePopModelDao konkaMobilePopModelDao;
	

	public Long createKonkaMobilePopModel(KonkaMobilePopModel t) {
		return this.konkaMobilePopModelDao.insertEntity(t);
	}

	public KonkaMobilePopModel getKonkaMobilePopModel(KonkaMobilePopModel t) {
		return this.konkaMobilePopModelDao.selectEntity(t);
	}

	public Long getKonkaMobilePopModelCount(KonkaMobilePopModel t) {
		return this.konkaMobilePopModelDao.selectEntityCount(t);
	}

	public List<KonkaMobilePopModel> getKonkaMobilePopModelList(KonkaMobilePopModel t) {
		return this.konkaMobilePopModelDao.selectEntityList(t);
	}

	public int modifyKonkaMobilePopModel(KonkaMobilePopModel t) {
		return this.konkaMobilePopModelDao.updateEntity(t);
	}

	public int removeKonkaMobilePopModel(KonkaMobilePopModel t) {
		return this.konkaMobilePopModelDao.deleteEntity(t);
	}

	public List<KonkaMobilePopModel> getKonkaMobilePopModelPaginatedList(KonkaMobilePopModel t) {
		return this.konkaMobilePopModelDao.selectEntityPaginatedList(t);
	}

}
