package com.ebiz.mmt.service.impl;

import java.util.HashMap;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import com.ebiz.mmt.dao.KonkaParagonAttentionCDao;
import com.ebiz.mmt.domain.KonkaParagonAttentionC;
import com.ebiz.mmt.service.KonkaParagonAttentionCService;

@Service
public class KonkaParagonAttentionCServiceImpl implements
		KonkaParagonAttentionCService {

	@Resource
	private KonkaParagonAttentionCDao konkaParagonAttentionCDao;

	public Long createKonkaParagonAttentionC(KonkaParagonAttentionC t) {
		return this.konkaParagonAttentionCDao.insertEntity(t);
	}

	public KonkaParagonAttentionC getKonkaParagonAttentionC(
			KonkaParagonAttentionC t) {
		return this.konkaParagonAttentionCDao.selectEntity(t);
	}

	public Long getKonkaParagonAttentionCCount(KonkaParagonAttentionC t) {
		return this.konkaParagonAttentionCDao.selectEntityCount(t);
	}

	public List<KonkaParagonAttentionC> getKonkaParagonAttentionCList(
			KonkaParagonAttentionC t) {
		return this.konkaParagonAttentionCDao.selectEntityList(t);
	}

	public int modifyKonkaParagonAttentionC(KonkaParagonAttentionC t) {
		return this.konkaParagonAttentionCDao.updateEntity(t);
	}

	public int removeKonkaParagonAttentionC(KonkaParagonAttentionC t) {
		return this.konkaParagonAttentionCDao.deleteEntity(t);
	}

	public List<KonkaParagonAttentionC> getKonkaParagonAttentionCPaginatedList(
			KonkaParagonAttentionC t) {
		return this.konkaParagonAttentionCDao.selectEntityPaginatedList(t);
	}

	public List<HashMap<String, String>> selectKonkaParagonAttentionPaginatedList(
			KonkaParagonAttentionC t) throws DataAccessException {
		return this.konkaParagonAttentionCDao
				.selectKonkaParagonAttentionPaginatedList(t);
	}

}
