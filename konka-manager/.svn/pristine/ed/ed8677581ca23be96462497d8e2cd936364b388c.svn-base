package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaPdModelDao;
import com.ebiz.mmt.domain.KonkaPdModel;
import com.ebiz.mmt.service.KonkaPdModelService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2011-08-25 08:32:14
 */
@Service
public class KonkaPdModelServiceImpl implements KonkaPdModelService {

	@Resource
	private KonkaPdModelDao konkaPdModelDao;
	

	public Long createKonkaPdModel(KonkaPdModel t) {
		return this.konkaPdModelDao.insertEntity(t);
	}

	public KonkaPdModel getKonkaPdModel(KonkaPdModel t) {
		return this.konkaPdModelDao.selectEntity(t);
	}

	public Long getKonkaPdModelCount(KonkaPdModel t) {
		return this.konkaPdModelDao.selectEntityCount(t);
	}

	public List<KonkaPdModel> getKonkaPdModelList(KonkaPdModel t) {
		return this.konkaPdModelDao.selectEntityList(t);
	}

	public int modifyKonkaPdModel(KonkaPdModel t) {
		return this.konkaPdModelDao.updateEntity(t);
	}

	public int removeKonkaPdModel(KonkaPdModel t) {
		return this.konkaPdModelDao.deleteEntity(t);
	}

	public List<KonkaPdModel> getKonkaPdModelPaginatedList(KonkaPdModel t) {
		return this.konkaPdModelDao.selectEntityPaginatedList(t);
	}

}
