package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaR3ZsofDao;
import com.ebiz.mmt.domain.KonkaR3Zsof;
import com.ebiz.mmt.service.KonkaR3ZsofService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-04-14 16:44:47
 */
@Service
public class KonkaR3ZsofServiceImpl implements KonkaR3ZsofService {

	@Resource
	private KonkaR3ZsofDao konkaR3ZsofDao;
	

	public Long createKonkaR3Zsof(KonkaR3Zsof t) {
		return this.konkaR3ZsofDao.insertEntity(t);
	}

	public KonkaR3Zsof getKonkaR3Zsof(KonkaR3Zsof t) {
		return this.konkaR3ZsofDao.selectEntity(t);
	}

	public Long getKonkaR3ZsofCount(KonkaR3Zsof t) {
		return this.konkaR3ZsofDao.selectEntityCount(t);
	}

	public List<KonkaR3Zsof> getKonkaR3ZsofList(KonkaR3Zsof t) {
		return this.konkaR3ZsofDao.selectEntityList(t);
	}

	public int modifyKonkaR3Zsof(KonkaR3Zsof t) {
		return this.konkaR3ZsofDao.updateEntity(t);
	}

	public int removeKonkaR3Zsof(KonkaR3Zsof t) {
		return this.konkaR3ZsofDao.deleteEntity(t);
	}

	public List<KonkaR3Zsof> getKonkaR3ZsofPaginatedList(KonkaR3Zsof t) {
		return this.konkaR3ZsofDao.selectEntityPaginatedList(t);
	}

}
