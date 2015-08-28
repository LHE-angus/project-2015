package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaMobileCustVisitDetailDao;
import com.ebiz.mmt.domain.KonkaMobileCustVisitDetail;
import com.ebiz.mmt.service.KonkaMobileCustVisitDetailService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-06-23 16:32:16
 */
@Service
public class KonkaMobileCustVisitDetailServiceImpl implements KonkaMobileCustVisitDetailService {

	@Resource
	private KonkaMobileCustVisitDetailDao konkaMobileCustVisitDetailDao;
	

	public Long createKonkaMobileCustVisitDetail(KonkaMobileCustVisitDetail t) {
		return this.konkaMobileCustVisitDetailDao.insertEntity(t);
	}

	public KonkaMobileCustVisitDetail getKonkaMobileCustVisitDetail(KonkaMobileCustVisitDetail t) {
		return this.konkaMobileCustVisitDetailDao.selectEntity(t);
	}

	public Long getKonkaMobileCustVisitDetailCount(KonkaMobileCustVisitDetail t) {
		return this.konkaMobileCustVisitDetailDao.selectEntityCount(t);
	}

	public List<KonkaMobileCustVisitDetail> getKonkaMobileCustVisitDetailList(KonkaMobileCustVisitDetail t) {
		return this.konkaMobileCustVisitDetailDao.selectEntityList(t);
	}

	public int modifyKonkaMobileCustVisitDetail(KonkaMobileCustVisitDetail t) {
		return this.konkaMobileCustVisitDetailDao.updateEntity(t);
	}

	public int removeKonkaMobileCustVisitDetail(KonkaMobileCustVisitDetail t) {
		return this.konkaMobileCustVisitDetailDao.deleteEntity(t);
	}

	public List<KonkaMobileCustVisitDetail> getKonkaMobileCustVisitDetailPaginatedList(KonkaMobileCustVisitDetail t) {
		return this.konkaMobileCustVisitDetailDao.selectEntityPaginatedList(t);
	}

}
