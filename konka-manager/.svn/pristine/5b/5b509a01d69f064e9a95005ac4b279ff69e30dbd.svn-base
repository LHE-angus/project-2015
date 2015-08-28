package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaR3ShopNewAttDao;
import com.ebiz.mmt.domain.KonkaR3ShopNewAtt;
import com.ebiz.mmt.service.KonkaR3ShopNewAttService;


@Service
public class KonkaR3ShopNewAttServiceImpl implements KonkaR3ShopNewAttService {

	@Resource
	private KonkaR3ShopNewAttDao konkaR3ShopNewAttDao;
	

	public Long createKonkaR3ShopNewAtt(KonkaR3ShopNewAtt t) {
		return this.konkaR3ShopNewAttDao.insertEntity(t);
	}

	public KonkaR3ShopNewAtt getKonkaR3ShopNewAtt(KonkaR3ShopNewAtt t) {
		return this.konkaR3ShopNewAttDao.selectEntity(t);
	}

	public Long getKonkaR3ShopNewAttCount(KonkaR3ShopNewAtt t) {
		return this.konkaR3ShopNewAttDao.selectEntityCount(t);
	}

	public List<KonkaR3ShopNewAtt> getKonkaR3ShopNewAttList(KonkaR3ShopNewAtt t) {
		return this.konkaR3ShopNewAttDao.selectEntityList(t);
	}

	public int modifyKonkaR3ShopNewAtt(KonkaR3ShopNewAtt t) {
		return this.konkaR3ShopNewAttDao.updateEntity(t);
	}

	public int removeKonkaR3ShopNewAtt(KonkaR3ShopNewAtt t) {
		return this.konkaR3ShopNewAttDao.deleteEntity(t);
	}

	public List<KonkaR3ShopNewAtt> getKonkaR3ShopNewAttPaginatedList(KonkaR3ShopNewAtt t) {
		return this.konkaR3ShopNewAttDao.selectEntityPaginatedList(t);
	}

}
