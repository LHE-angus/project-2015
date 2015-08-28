package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaMobileCustVisitExtendDao;
import com.ebiz.mmt.domain.KonkaMobileCustVisitExtend;
import com.ebiz.mmt.service.KonkaMobileCustVisitExtendService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-06-23 16:32:16
 */
@Service
public class KonkaMobileCustVisitExtendServiceImpl implements KonkaMobileCustVisitExtendService {

	@Resource
	private KonkaMobileCustVisitExtendDao konkaMobileCustVisitExtendDao;
	

	public Long createKonkaMobileCustVisitExtend(KonkaMobileCustVisitExtend t) {
		return this.konkaMobileCustVisitExtendDao.insertEntity(t);
	}

	public KonkaMobileCustVisitExtend getKonkaMobileCustVisitExtend(KonkaMobileCustVisitExtend t) {
		return this.konkaMobileCustVisitExtendDao.selectEntity(t);
	}

	public Long getKonkaMobileCustVisitExtendCount(KonkaMobileCustVisitExtend t) {
		return this.konkaMobileCustVisitExtendDao.selectEntityCount(t);
	}

	public List<KonkaMobileCustVisitExtend> getKonkaMobileCustVisitExtendList(KonkaMobileCustVisitExtend t) {
		return this.konkaMobileCustVisitExtendDao.selectEntityList(t);
	}

	public int modifyKonkaMobileCustVisitExtend(KonkaMobileCustVisitExtend t) {
		return this.konkaMobileCustVisitExtendDao.updateEntity(t);
	}

	public int removeKonkaMobileCustVisitExtend(KonkaMobileCustVisitExtend t) {
		return this.konkaMobileCustVisitExtendDao.deleteEntity(t);
	}

	public List<KonkaMobileCustVisitExtend> getKonkaMobileCustVisitExtendPaginatedList(KonkaMobileCustVisitExtend t) {
		return this.konkaMobileCustVisitExtendDao.selectEntityPaginatedList(t);
	}

}
