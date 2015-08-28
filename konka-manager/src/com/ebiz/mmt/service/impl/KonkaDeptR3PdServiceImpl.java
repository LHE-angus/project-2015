package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaDeptR3PdDao;
import com.ebiz.mmt.domain.KonkaDeptR3Pd;
import com.ebiz.mmt.service.KonkaDeptR3PdService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-01-18 15:28:01
 */
@Service
public class KonkaDeptR3PdServiceImpl implements KonkaDeptR3PdService {

	@Resource
	private KonkaDeptR3PdDao konkaDeptR3PdDao;

	public Long createKonkaDeptR3Pd(KonkaDeptR3Pd t) {
		return this.konkaDeptR3PdDao.insertEntity(t);
	}

	public KonkaDeptR3Pd getKonkaDeptR3Pd(KonkaDeptR3Pd t) {
		return this.konkaDeptR3PdDao.selectEntity(t);
	}

	public Long getKonkaDeptR3PdCount(KonkaDeptR3Pd t) {
		return this.konkaDeptR3PdDao.selectEntityCount(t);
	}

	public List<KonkaDeptR3Pd> getKonkaDeptR3PdList(KonkaDeptR3Pd t) {
		return this.konkaDeptR3PdDao.selectEntityList(t);
	}

	public int modifyKonkaDeptR3Pd(KonkaDeptR3Pd t) {
		return this.konkaDeptR3PdDao.updateEntity(t);
	}

	public int removeKonkaDeptR3Pd(KonkaDeptR3Pd t) {
		return this.konkaDeptR3PdDao.deleteEntity(t);
	}

	public List<KonkaDeptR3Pd> getKonkaDeptR3PdPaginatedList(KonkaDeptR3Pd t) {
		return this.konkaDeptR3PdDao.selectEntityPaginatedList(t);
	}

	public String createKonkaDeptR3Pd(List<KonkaDeptR3Pd> list) {
		return this.konkaDeptR3PdDao.insertKonkaDeptR3Pd(list);
	}

}
