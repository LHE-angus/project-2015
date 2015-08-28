package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaMobileMdPercentDao;
import com.ebiz.mmt.domain.KonkaMobileMdPercent;
import com.ebiz.mmt.service.KonkaMobileMdPercentService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-05-06 17:18:42
 */
@Service
public class KonkaMobileMdPercentServiceImpl implements KonkaMobileMdPercentService {

	@Resource
	private KonkaMobileMdPercentDao konkaMobileMdPercentDao;
	

	public Long createKonkaMobileMdPercent(KonkaMobileMdPercent t) {
		return this.konkaMobileMdPercentDao.insertEntity(t);
	}

	public KonkaMobileMdPercent getKonkaMobileMdPercent(KonkaMobileMdPercent t) {
		return this.konkaMobileMdPercentDao.selectEntity(t);
	}

	public Long getKonkaMobileMdPercentCount(KonkaMobileMdPercent t) {
		return this.konkaMobileMdPercentDao.selectEntityCount(t);
	}

	public List<KonkaMobileMdPercent> getKonkaMobileMdPercentList(KonkaMobileMdPercent t) {
		return this.konkaMobileMdPercentDao.selectEntityList(t);
	}

	public int modifyKonkaMobileMdPercent(KonkaMobileMdPercent t) {
		return this.konkaMobileMdPercentDao.updateEntity(t);
	}

	public int removeKonkaMobileMdPercent(KonkaMobileMdPercent t) {
		return this.konkaMobileMdPercentDao.deleteEntity(t);
	}

	public List<KonkaMobileMdPercent> getKonkaMobileMdPercentPaginatedList(KonkaMobileMdPercent t) {
		return this.konkaMobileMdPercentDao.selectEntityPaginatedList(t);
	}

}
