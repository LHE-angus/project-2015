package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaCategoryTempDao;
import com.ebiz.mmt.domain.KonkaCategoryTemp;
import com.ebiz.mmt.service.KonkaCategoryTempService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-07-12 16:57:52
 */
@Service
public class KonkaCategoryTempServiceImpl implements KonkaCategoryTempService {

	@Resource
	private KonkaCategoryTempDao konkaCategoryTempDao;
	

	public Long createKonkaCategoryTemp(KonkaCategoryTemp t) {
		return this.konkaCategoryTempDao.insertEntity(t);
	}

	public KonkaCategoryTemp getKonkaCategoryTemp(KonkaCategoryTemp t) {
		return this.konkaCategoryTempDao.selectEntity(t);
	}

	public Long getKonkaCategoryTempCount(KonkaCategoryTemp t) {
		return this.konkaCategoryTempDao.selectEntityCount(t);
	}

	public List<KonkaCategoryTemp> getKonkaCategoryTempList(KonkaCategoryTemp t) {
		return this.konkaCategoryTempDao.selectEntityList(t);
	}

	public int modifyKonkaCategoryTemp(KonkaCategoryTemp t) {
		return this.konkaCategoryTempDao.updateEntity(t);
	}

	public int removeKonkaCategoryTemp(KonkaCategoryTemp t) {
		return this.konkaCategoryTempDao.deleteEntity(t);
	}

	public List<KonkaCategoryTemp> getKonkaCategoryTempPaginatedList(KonkaCategoryTemp t) {
		return this.konkaCategoryTempDao.selectEntityPaginatedList(t);
	}

}
