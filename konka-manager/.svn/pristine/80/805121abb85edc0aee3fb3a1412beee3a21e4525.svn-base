package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaMobileHandphonesDao;
import com.ebiz.mmt.domain.KonkaMobileHandphones;
import com.ebiz.mmt.service.KonkaMobileHandphonesService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-05-09 21:22:44
 */
@Service
public class KonkaMobileHandphonesServiceImpl implements KonkaMobileHandphonesService {

	@Resource
	private KonkaMobileHandphonesDao konkaMobileHandphonesDao;
	

	public Long createKonkaMobileHandphones(KonkaMobileHandphones t) {
		return this.konkaMobileHandphonesDao.insertEntity(t);
	}

	public KonkaMobileHandphones getKonkaMobileHandphones(KonkaMobileHandphones t) {
		return this.konkaMobileHandphonesDao.selectEntity(t);
	}

	public Long getKonkaMobileHandphonesCount(KonkaMobileHandphones t) {
		return this.konkaMobileHandphonesDao.selectEntityCount(t);
	}

	public List<KonkaMobileHandphones> getKonkaMobileHandphonesList(KonkaMobileHandphones t) {
		return this.konkaMobileHandphonesDao.selectEntityList(t);
	}

	public int modifyKonkaMobileHandphones(KonkaMobileHandphones t) {
		return this.konkaMobileHandphonesDao.updateEntity(t);
	}

	public int removeKonkaMobileHandphones(KonkaMobileHandphones t) {
		return this.konkaMobileHandphonesDao.deleteEntity(t);
	}

	public List<KonkaMobileHandphones> getKonkaMobileHandphonesPaginatedList(KonkaMobileHandphones t) {
		return this.konkaMobileHandphonesDao.selectEntityPaginatedList(t);
	}

}
