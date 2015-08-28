package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaSalesDeptDao;
import com.ebiz.mmt.domain.KonkaSalesDept;
import com.ebiz.mmt.service.KonkaSalesDeptService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-08-23 14:38:53
 */
@Service
public class KonkaSalesDeptServiceImpl implements KonkaSalesDeptService {

	@Resource
	private KonkaSalesDeptDao konkaSalesDeptDao;
	

	public Long createKonkaSalesDept(KonkaSalesDept t) {
		return this.konkaSalesDeptDao.insertEntity(t);
	}

	public KonkaSalesDept getKonkaSalesDept(KonkaSalesDept t) {
		return this.konkaSalesDeptDao.selectEntity(t);
	}

	public Long getKonkaSalesDeptCount(KonkaSalesDept t) {
		return this.konkaSalesDeptDao.selectEntityCount(t);
	}

	public List<KonkaSalesDept> getKonkaSalesDeptList(KonkaSalesDept t) {
		return this.konkaSalesDeptDao.selectEntityList(t);
	}

	public int modifyKonkaSalesDept(KonkaSalesDept t) {
		return this.konkaSalesDeptDao.updateEntity(t);
	}

	public int removeKonkaSalesDept(KonkaSalesDept t) {
		return this.konkaSalesDeptDao.deleteEntity(t);
	}

	public List<KonkaSalesDept> getKonkaSalesDeptPaginatedList(KonkaSalesDept t) {
		return this.konkaSalesDeptDao.selectEntityPaginatedList(t);
	}

}
