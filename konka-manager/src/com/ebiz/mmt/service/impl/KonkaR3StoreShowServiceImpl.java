package com.ebiz.mmt.service.impl;

import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaR3StoreShowDao;
import com.ebiz.mmt.domain.KonkaR3StoreShow;
import com.ebiz.mmt.service.KonkaR3StoreShowService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-05-05 14:37:12
 */
@Service
public class KonkaR3StoreShowServiceImpl implements KonkaR3StoreShowService {

	@Resource
	private KonkaR3StoreShowDao konkaR3StoreShowDao;

	public Long createKonkaR3StoreShow(KonkaR3StoreShow t) {
		return this.konkaR3StoreShowDao.insertEntity(t);
	}

	public KonkaR3StoreShow getKonkaR3StoreShow(KonkaR3StoreShow t) {
		return this.konkaR3StoreShowDao.selectEntity(t);
	}

	public Long getKonkaR3StoreShowCount(KonkaR3StoreShow t) {
		return this.konkaR3StoreShowDao.selectEntityCount(t);
	}

	public List<KonkaR3StoreShow> getKonkaR3StoreShowList(KonkaR3StoreShow t) {
		return this.konkaR3StoreShowDao.selectEntityList(t);
	}

	public int modifyKonkaR3StoreShow(KonkaR3StoreShow t) {
		return this.konkaR3StoreShowDao.updateEntity(t);
	}

	public int removeKonkaR3StoreShow(KonkaR3StoreShow t) {
		return this.konkaR3StoreShowDao.deleteEntity(t);
	}

	public List<KonkaR3StoreShow> getKonkaR3StoreShowPaginatedList(KonkaR3StoreShow t) {
		return this.konkaR3StoreShowDao.selectEntityPaginatedList(t);
	}

	public String createKonkaR3StoreShow(List<KonkaR3StoreShow> list) {
		return this.konkaR3StoreShowDao.insertKonkaR3StoreShowList(list);
	}

	@Override
	public String createKonkaR3StoreShow(HashSet<KonkaR3StoreShow> list) {
		return this.konkaR3StoreShowDao.insertKonkaR3StoreShowList(list);
	}
	//连表查询客户信息
	@Override
	public Long getKonkaR3StoreShowLBCount(KonkaR3StoreShow entity) {
		return this.konkaR3StoreShowDao.selectKonkaR3StoreShowLBCount(entity);
	}
    //连表查询客户信息
	@Override
	public List<KonkaR3StoreShow> getKonkaR3StoreShowLBPaginatedList(
			KonkaR3StoreShow entity) {
		return this.konkaR3StoreShowDao.selectKonkaR3StoreShowLBPaginatedList(entity);
	}

}
