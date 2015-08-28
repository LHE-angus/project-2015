package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaMobileTerminalFbBackDao;
import com.ebiz.mmt.domain.KonkaMobileTerminalFbBack;
import com.ebiz.mmt.service.KonkaMobileTerminalFbBackService;


@Service
public class KonkaMobileTerminalFbBackServiceImpl implements KonkaMobileTerminalFbBackService {

	@Resource
	private KonkaMobileTerminalFbBackDao konkaMobileTerminalFbBackDao;

	public Long createKonkaMobileTerminalFbBack(KonkaMobileTerminalFbBack t) {
		return this.konkaMobileTerminalFbBackDao.insertEntity(t);
	}

	public KonkaMobileTerminalFbBack getKonkaMobileTerminalFbBack(KonkaMobileTerminalFbBack t) {
		return this.konkaMobileTerminalFbBackDao.selectEntity(t);
	}

	public Long getKonkaMobileTerminalFbBackCount(KonkaMobileTerminalFbBack t) {
		return this.konkaMobileTerminalFbBackDao.selectEntityCount(t);
	}

	public List<KonkaMobileTerminalFbBack> getKonkaMobileTerminalFbBackList(KonkaMobileTerminalFbBack t) {
		return this.konkaMobileTerminalFbBackDao.selectEntityList(t);
	}

	public int modifyKonkaMobileTerminalFbBack(KonkaMobileTerminalFbBack t) {
		return this.konkaMobileTerminalFbBackDao.updateEntity(t);
	}

	public int removeKonkaMobileTerminalFbBack(KonkaMobileTerminalFbBack t) {
		return this.konkaMobileTerminalFbBackDao.deleteEntity(t);
	}

	public List<KonkaMobileTerminalFbBack> getKonkaMobileTerminalFbBackPaginatedList(KonkaMobileTerminalFbBack t) {
		return this.konkaMobileTerminalFbBackDao.selectEntityPaginatedList(t);
	}

}
