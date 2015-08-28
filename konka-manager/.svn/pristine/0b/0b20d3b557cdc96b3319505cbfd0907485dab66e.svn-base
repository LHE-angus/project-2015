package com.ebiz.mmt.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaR3SellImpInvalidDataDao;
import com.ebiz.mmt.domain.KonkaR3SellImpInvalidData;
import com.ebiz.mmt.service.KonkaR3SellImpInvalidDataService;

/**
 * @author Wu,Yang
 * @version 2011-11-16 17:47
 */
@Service
@SuppressWarnings("unchecked")
public class KonkaR3SellImpInvalidDataServiceImpl implements KonkaR3SellImpInvalidDataService {

	@Resource
	private KonkaR3SellImpInvalidDataDao konkaR3SellImpInvalidDataDao;
	

	public Long createKonkaR3SellImpInvalidData(KonkaR3SellImpInvalidData t) {
		return this.konkaR3SellImpInvalidDataDao.insertEntity(t);
	}

	public KonkaR3SellImpInvalidData getKonkaR3SellImpInvalidData(KonkaR3SellImpInvalidData t) {
		return this.konkaR3SellImpInvalidDataDao.selectEntity(t);
	}

	public Long getKonkaR3SellImpInvalidDataCount(KonkaR3SellImpInvalidData t) {
		return this.konkaR3SellImpInvalidDataDao.selectEntityCount(t);
	}

	public List<KonkaR3SellImpInvalidData> getKonkaR3SellImpInvalidDataList(KonkaR3SellImpInvalidData t) {
		return this.konkaR3SellImpInvalidDataDao.selectEntityList(t);
	}

	public int modifyKonkaR3SellImpInvalidData(KonkaR3SellImpInvalidData t) {
		return this.konkaR3SellImpInvalidDataDao.updateEntity(t);
	}

	public int removeKonkaR3SellImpInvalidData(KonkaR3SellImpInvalidData t) {
		return this.konkaR3SellImpInvalidDataDao.deleteEntity(t);
	}

	public List<KonkaR3SellImpInvalidData> getKonkaR3SellImpInvalidDataPaginatedList(KonkaR3SellImpInvalidData t) {
		return this.konkaR3SellImpInvalidDataDao.selectEntityPaginatedList(t);
	}

	/**
	 * @author Li,Ka
	 * @version 2011-11-17 根据R3_sell_date分组订单号
	 */
	public List<Map> getOrderSnGroupList(KonkaR3SellImpInvalidData t) {
		return this.konkaR3SellImpInvalidDataDao.selectOrderSnGroupList(t);
	}
	
}
