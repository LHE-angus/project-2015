package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaMobileLoginFlagDao;
import com.ebiz.mmt.domain.KonkaMobileLoginFlag;
import com.ebiz.mmt.service.KonkaMobileLoginFlagService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2015-04-13 20:28:52
 */
@Service
public class KonkaMobileLoginFlagServiceImpl implements KonkaMobileLoginFlagService {

	@Resource
	private KonkaMobileLoginFlagDao konkaMobileLoginFlagDao;
	

	public Long createKonkaMobileLoginFlag(KonkaMobileLoginFlag t) {
		return this.konkaMobileLoginFlagDao.insertEntity(t);
	}

	public KonkaMobileLoginFlag getKonkaMobileLoginFlag(KonkaMobileLoginFlag t) {
		return this.konkaMobileLoginFlagDao.selectEntity(t);
	}

	public Long getKonkaMobileLoginFlagCount(KonkaMobileLoginFlag t) {
		return this.konkaMobileLoginFlagDao.selectEntityCount(t);
	}

	public List<KonkaMobileLoginFlag> getKonkaMobileLoginFlagList(KonkaMobileLoginFlag t) {
		return this.konkaMobileLoginFlagDao.selectEntityList(t);
	}

	public int modifyKonkaMobileLoginFlag(KonkaMobileLoginFlag t) {
		return this.konkaMobileLoginFlagDao.updateEntity(t);
	}

	public int removeKonkaMobileLoginFlag(KonkaMobileLoginFlag t) {
		return this.konkaMobileLoginFlagDao.deleteEntity(t);
	}

	public List<KonkaMobileLoginFlag> getKonkaMobileLoginFlagPaginatedList(KonkaMobileLoginFlag t) {
		return this.konkaMobileLoginFlagDao.selectEntityPaginatedList(t);
	}

}
