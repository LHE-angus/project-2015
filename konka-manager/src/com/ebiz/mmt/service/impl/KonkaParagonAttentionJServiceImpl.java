package com.ebiz.mmt.service.impl;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.ebiz.mmt.dao.KonkaParagonAttentionJDao;
import com.ebiz.mmt.domain.KonkaParagonAttentionJ;
import com.ebiz.mmt.service.KonkaParagonAttentionJService;


@Service
public class KonkaParagonAttentionJServiceImpl implements KonkaParagonAttentionJService {

	@Resource
	private KonkaParagonAttentionJDao konkaParagonAttentionJDao;

	public Long createKonkaParagonAttentionJ(KonkaParagonAttentionJ t) {
		return this.konkaParagonAttentionJDao.insertEntity(t);
	}

	public KonkaParagonAttentionJ getKonkaParagonAttentionJ(KonkaParagonAttentionJ t) {
		return this.konkaParagonAttentionJDao.selectEntity(t);
	}

	public Long getKonkaParagonAttentionJCount(KonkaParagonAttentionJ t) {
		return this.konkaParagonAttentionJDao.selectEntityCount(t);
	}

	public List<KonkaParagonAttentionJ> getKonkaParagonAttentionJList(KonkaParagonAttentionJ t) {
		return this.konkaParagonAttentionJDao.selectEntityList(t);
	}

	public int modifyKonkaParagonAttentionJ(KonkaParagonAttentionJ t) {
		return this.konkaParagonAttentionJDao.updateEntity(t);
	}

	public int removeKonkaParagonAttentionJ(KonkaParagonAttentionJ t) {
		return this.konkaParagonAttentionJDao.deleteEntity(t);
	}

	public List<KonkaParagonAttentionJ> getKonkaParagonAttentionJPaginatedList(KonkaParagonAttentionJ t) {
		return this.konkaParagonAttentionJDao.selectEntityPaginatedList(t);
	}
	
	public Long createParagonAttentionJ(Integer attention_id , Long user_id) {
		KonkaParagonAttentionJ entity = new KonkaParagonAttentionJ();
		entity.setAttention_id(attention_id);
		entity.setAddman(user_id);
		entity.setAddtime(new Date());
		Long attentionJ = konkaParagonAttentionJDao.insertEntity(entity);
		return attentionJ;
	}
}
