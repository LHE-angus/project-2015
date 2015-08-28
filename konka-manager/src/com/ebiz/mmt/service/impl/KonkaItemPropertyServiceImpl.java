package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaItemPropertyDao;
import com.ebiz.mmt.domain.KonkaItemProperty;
import com.ebiz.mmt.service.KonkaItemPropertyService;

/**
 * @author Ren,Peng
 * @version 2011-10-20 16:41
 */
@Service
public class KonkaItemPropertyServiceImpl implements KonkaItemPropertyService {

	@Resource
	private KonkaItemPropertyDao konkaItemPropertyDao;
	

	public Long createKonkaItemProperty(KonkaItemProperty t) {
		return this.konkaItemPropertyDao.insertEntity(t);
	}

	public KonkaItemProperty getKonkaItemProperty(KonkaItemProperty t) {
		return this.konkaItemPropertyDao.selectEntity(t);
	}

	public Long getKonkaItemPropertyCount(KonkaItemProperty t) {
		return this.konkaItemPropertyDao.selectEntityCount(t);
	}

	public List<KonkaItemProperty> getKonkaItemPropertyList(KonkaItemProperty t) {
		return this.konkaItemPropertyDao.selectEntityList(t);
	}

	public int modifyKonkaItemProperty(KonkaItemProperty t) {
		return this.konkaItemPropertyDao.updateEntity(t);
	}

	public int removeKonkaItemProperty(KonkaItemProperty t) {
		return this.konkaItemPropertyDao.deleteEntity(t);
	}

	public List<KonkaItemProperty> getKonkaItemPropertyPaginatedList(KonkaItemProperty t) {
		return this.konkaItemPropertyDao.selectEntityPaginatedList(t);
	}

}
