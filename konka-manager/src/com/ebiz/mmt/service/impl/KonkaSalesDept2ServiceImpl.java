package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaSalesDept2Dao;
import com.ebiz.mmt.domain.KonkaSalesDept2;
import com.ebiz.mmt.service.KonkaSalesDept2Service;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-08-23 14:38:53
 */
@Service
public class KonkaSalesDept2ServiceImpl implements KonkaSalesDept2Service {

	@Resource
	private KonkaSalesDept2Dao konkaSalesDept2Dao;
	

	public Long createKonkaSalesDept2(KonkaSalesDept2 t) {
		return this.konkaSalesDept2Dao.insertEntity(t);
	}

	public KonkaSalesDept2 getKonkaSalesDept2(KonkaSalesDept2 t) {
		return this.konkaSalesDept2Dao.selectEntity(t);
	}

	public Long getKonkaSalesDept2Count(KonkaSalesDept2 t) {
		return this.konkaSalesDept2Dao.selectEntityCount(t);
	}

	public List<KonkaSalesDept2> getKonkaSalesDept2List(KonkaSalesDept2 t) {
		return this.konkaSalesDept2Dao.selectEntityList(t);
	}

	public int modifyKonkaSalesDept2(KonkaSalesDept2 t) {
		return this.konkaSalesDept2Dao.updateEntity(t);
	}

	public int removeKonkaSalesDept2(KonkaSalesDept2 t) {
		return this.konkaSalesDept2Dao.deleteEntity(t);
	}

	public List<KonkaSalesDept2> getKonkaSalesDept2PaginatedList(KonkaSalesDept2 t) {
		return this.konkaSalesDept2Dao.selectEntityPaginatedList(t);
	}

}
