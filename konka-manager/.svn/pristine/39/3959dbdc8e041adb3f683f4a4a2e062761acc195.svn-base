package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaMobileCustVisitLinkDao;
import com.ebiz.mmt.domain.KonkaMobileCustVisitLink;
import com.ebiz.mmt.service.KonkaMobileCustVisitLinkService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-09-04 14:07:23
 */
@Service
public class KonkaMobileCustVisitLinkServiceImpl implements KonkaMobileCustVisitLinkService {

	@Resource
	private KonkaMobileCustVisitLinkDao konkaMobileCustVisitLinkDao;
	

	public Long createKonkaMobileCustVisitLink(KonkaMobileCustVisitLink t) {
		return this.konkaMobileCustVisitLinkDao.insertEntity(t);
	}

	public KonkaMobileCustVisitLink getKonkaMobileCustVisitLink(KonkaMobileCustVisitLink t) {
		return this.konkaMobileCustVisitLinkDao.selectEntity(t);
	}

	public Long getKonkaMobileCustVisitLinkCount(KonkaMobileCustVisitLink t) {
		return this.konkaMobileCustVisitLinkDao.selectEntityCount(t);
	}

	public List<KonkaMobileCustVisitLink> getKonkaMobileCustVisitLinkList(KonkaMobileCustVisitLink t) {
		return this.konkaMobileCustVisitLinkDao.selectEntityList(t);
	}

	public int modifyKonkaMobileCustVisitLink(KonkaMobileCustVisitLink t) {
		return this.konkaMobileCustVisitLinkDao.updateEntity(t);
	}

	public int removeKonkaMobileCustVisitLink(KonkaMobileCustVisitLink t) {
		return this.konkaMobileCustVisitLinkDao.deleteEntity(t);
	}

	public List<KonkaMobileCustVisitLink> getKonkaMobileCustVisitLinkPaginatedList(KonkaMobileCustVisitLink t) {
		return this.konkaMobileCustVisitLinkDao.selectEntityPaginatedList(t);
	}

}
