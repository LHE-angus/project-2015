package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaOrderInfoDetailsDao;
import com.ebiz.mmt.domain.KonkaOrderInfoDetails;
import com.ebiz.mmt.service.KonkaOrderInfoDetailsService;

/**
 * @author Wu,Yang
 * @version 2011-11-25 09:08
 */
@Service
public class KonkaOrderInfoDetailsServiceImpl implements KonkaOrderInfoDetailsService {

	@Resource
	private KonkaOrderInfoDetailsDao konkaOrderInfoDetailsDao;
	

	public Long createKonkaOrderInfoDetails(KonkaOrderInfoDetails t) {
		return this.konkaOrderInfoDetailsDao.insertEntity(t);
	}

	public KonkaOrderInfoDetails getKonkaOrderInfoDetails(KonkaOrderInfoDetails t) {
		return this.konkaOrderInfoDetailsDao.selectEntity(t);
	}

	public Long getKonkaOrderInfoDetailsCount(KonkaOrderInfoDetails t) {
		return this.konkaOrderInfoDetailsDao.selectEntityCount(t);
	}

	public List<KonkaOrderInfoDetails> getKonkaOrderInfoDetailsList(KonkaOrderInfoDetails t) {
		return this.konkaOrderInfoDetailsDao.selectEntityList(t);
	}

	public int modifyKonkaOrderInfoDetails(KonkaOrderInfoDetails t) {
		return this.konkaOrderInfoDetailsDao.updateEntity(t);
	}

	public int removeKonkaOrderInfoDetails(KonkaOrderInfoDetails t) {
		return this.konkaOrderInfoDetailsDao.deleteEntity(t);
	}

	public List<KonkaOrderInfoDetails> getKonkaOrderInfoDetailsPaginatedList(KonkaOrderInfoDetails t) {
		return this.konkaOrderInfoDetailsDao.selectEntityPaginatedList(t);
	}

	@Override
	public List<KonkaOrderInfoDetails> getKonkaOrderInfoDetailsForTrans(
			KonkaOrderInfoDetails t) {
		return this.konkaOrderInfoDetailsDao.selectKonkaOrderInfoDetailsForTrans(t);
	}

}
