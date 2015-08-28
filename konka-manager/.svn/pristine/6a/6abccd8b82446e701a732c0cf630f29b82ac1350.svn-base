package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.ebiz.mmt.dao.KonkaShopDevelopDao;
import com.ebiz.mmt.domain.KonkaShopDevelop;
import com.ebiz.mmt.service.KonkaShopDevelopService;

/**
 * @author Cheng,Bing
 */
@Service
public class KonkaShopDevelopServiceImpl implements KonkaShopDevelopService {

	@Resource
	private KonkaShopDevelopDao konkaShopDevelopDao;

	public Long createKonkaShopDevelop(KonkaShopDevelop t) {
		return this.konkaShopDevelopDao.insertEntity(t);
	}

	public int removeKonkaShopDevelop(KonkaShopDevelop t) {
		return this.konkaShopDevelopDao.deleteEntity(t);
	}
	
	public int modifyKonkaShopDevelop(KonkaShopDevelop t) {
		return this.konkaShopDevelopDao.updateEntity(t);
	}
	
	public KonkaShopDevelop getKonkaShopDevelop(KonkaShopDevelop t) {
		return this.konkaShopDevelopDao.selectEntity(t);
	}

	public Long getKonkaShopDevelopCount(KonkaShopDevelop t) {
		return this.konkaShopDevelopDao.selectEntityCount(t);
	}

	public List<KonkaShopDevelop> getKonkaShopDevelopList(KonkaShopDevelop t) {
		return this.konkaShopDevelopDao.selectEntityList(t);
	}
	
	public List<KonkaShopDevelop> getKonkaShopDevelopPaginatedList(KonkaShopDevelop t) {
		return this.konkaShopDevelopDao.selectEntityPaginatedList(t);
	}
}
