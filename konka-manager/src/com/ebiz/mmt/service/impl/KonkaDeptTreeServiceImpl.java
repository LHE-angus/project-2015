package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaDeptTreeDao;
import com.ebiz.mmt.domain.KonkaDeptTree;
import com.ebiz.mmt.service.KonkaDeptTreeService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-08-28 18:17:19
 */
@Service
public class KonkaDeptTreeServiceImpl implements KonkaDeptTreeService {

	@Resource
	private KonkaDeptTreeDao konkaDeptTreeDao;
	

	public Long createKonkaDeptTree(KonkaDeptTree t) {
		return this.konkaDeptTreeDao.insertEntity(t);
	}

	public KonkaDeptTree getKonkaDeptTree(KonkaDeptTree t) {
		return this.konkaDeptTreeDao.selectEntity(t);
	}

	public Long getKonkaDeptTreeCount(KonkaDeptTree t) {
		return this.konkaDeptTreeDao.selectEntityCount(t);
	}

	public List<KonkaDeptTree> getKonkaDeptTreeList(KonkaDeptTree t) {
		return this.konkaDeptTreeDao.selectEntityList(t);
	}

	public int modifyKonkaDeptTree(KonkaDeptTree t) {
		return this.konkaDeptTreeDao.updateEntity(t);
	}

	public int removeKonkaDeptTree(KonkaDeptTree t) {
		return this.konkaDeptTreeDao.deleteEntity(t);
	}

	public List<KonkaDeptTree> getKonkaDeptTreePaginatedList(KonkaDeptTree t) {
		return this.konkaDeptTreeDao.selectEntityPaginatedList(t);
	}

}
