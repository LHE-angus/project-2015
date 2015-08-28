package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaR3CustomerTypeDao;
import com.ebiz.mmt.domain.KonkaR3CustomerType;
import com.ebiz.mmt.service.KonkaR3CustomerTypeService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-06-25 19:52:54
 */
@Service
public class KonkaR3CustomerTypeServiceImpl implements KonkaR3CustomerTypeService {

	@Resource
	private KonkaR3CustomerTypeDao konkaR3CustomerTypeDao;
	

	public Long createKonkaR3CustomerType(KonkaR3CustomerType t) {
		return this.konkaR3CustomerTypeDao.insertEntity(t);
	}

	public KonkaR3CustomerType getKonkaR3CustomerType(KonkaR3CustomerType t) {
		return this.konkaR3CustomerTypeDao.selectEntity(t);
	}

	public Long getKonkaR3CustomerTypeCount(KonkaR3CustomerType t) {
		return this.konkaR3CustomerTypeDao.selectEntityCount(t);
	}

	public List<KonkaR3CustomerType> getKonkaR3CustomerTypeList(KonkaR3CustomerType t) {
		return this.konkaR3CustomerTypeDao.selectEntityList(t);
	}

	public int modifyKonkaR3CustomerType(KonkaR3CustomerType t) {
		return this.konkaR3CustomerTypeDao.updateEntity(t);
	}

	public int removeKonkaR3CustomerType(KonkaR3CustomerType t) {
		return this.konkaR3CustomerTypeDao.deleteEntity(t);
	}

	public List<KonkaR3CustomerType> getKonkaR3CustomerTypePaginatedList(KonkaR3CustomerType t) {
		return this.konkaR3CustomerTypeDao.selectEntityPaginatedList(t);
	}

}
