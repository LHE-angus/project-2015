package com.ebiz.mmt.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.ebiz.mmt.dao.KonkaShopVisitDao;
import com.ebiz.mmt.domain.KonkaShopVisit;
import com.ebiz.mmt.service.KonkaShopVisitService;

/**
 * @author Cheng,Bing
 */
@Service
public class KonkaShopVisitServiceImpl implements KonkaShopVisitService {

	@Resource
	private KonkaShopVisitDao konkaShopVisitDao;

	public Long createKonkaShopVisit(KonkaShopVisit t) {
		return this.konkaShopVisitDao.insertEntity(t);
	}

	public int removeKonkaShopVisit(KonkaShopVisit t) {
		return this.konkaShopVisitDao.deleteEntity(t);
	}
	
	public int modifyKonkaShopVisit(KonkaShopVisit t) {
		return this.konkaShopVisitDao.updateEntity(t);
	}
	
	public KonkaShopVisit getKonkaShopVisit(KonkaShopVisit t) {
		return this.konkaShopVisitDao.selectEntity(t);
	}

	public Long getKonkaShopVisitCount(KonkaShopVisit t) {
		return this.konkaShopVisitDao.selectEntityCount(t);
	}

	public List<KonkaShopVisit> getKonkaShopVisitList(KonkaShopVisit t) {
		return this.konkaShopVisitDao.selectEntityList(t);
	}
	
	public List<KonkaShopVisit> getKonkaShopVisitPaginatedList(KonkaShopVisit t) {
		return this.konkaShopVisitDao.selectEntityPaginatedList(t);
	}
}
