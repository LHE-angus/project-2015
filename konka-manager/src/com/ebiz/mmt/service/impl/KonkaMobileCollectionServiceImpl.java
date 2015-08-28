package com.ebiz.mmt.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaMobileCollectionDao;
import com.ebiz.mmt.dao.KonkaMobileStocksHisDao;
import com.ebiz.mmt.domain.KonkaMobileCollection;
import com.ebiz.mmt.domain.KonkaMobileStocksHis;
import com.ebiz.mmt.service.KonkaMobileCollectionService;

@Service
public class KonkaMobileCollectionServiceImpl implements
		KonkaMobileCollectionService {

	@Resource
	private KonkaMobileCollectionDao konkaMobileCollectionDao;

	@Resource
	private KonkaMobileStocksHisDao konkaMobileStocksHisDao;

	public Long createKonkaMobileCollection(KonkaMobileCollection t) {
		return this.konkaMobileCollectionDao.insertEntity(t);
	}

	public KonkaMobileCollection getKonkaMobileCollection(
			KonkaMobileCollection t) {
		return this.konkaMobileCollectionDao.selectEntity(t);
	}

	public Long getKonkaMobileCollectionCount(KonkaMobileCollection t) {
		return this.konkaMobileCollectionDao.selectEntityCount(t);
	}

	public List<KonkaMobileCollection> getKonkaMobileCollectionList(
			KonkaMobileCollection t) {
		return this.konkaMobileCollectionDao.selectEntityList(t);
	}

	public int modifyKonkaMobileCollection(KonkaMobileCollection t) {
		return this.konkaMobileCollectionDao.updateEntity(t);
	}

	public int removeKonkaMobileCollection(KonkaMobileCollection t) {
		return this.konkaMobileCollectionDao.deleteEntity(t);
	}

	public List<KonkaMobileCollection> getKonkaMobileCollectionPaginatedList(
			KonkaMobileCollection t) {
		return this.konkaMobileCollectionDao.selectEntityPaginatedList(t);
	}


	public int doLock(KonkaMobileCollection t) {
		int result = this.konkaMobileCollectionDao.updateEntity(t);

		// 记录历史数据
		KonkaMobileCollection collection = new KonkaMobileCollection();
		collection.setColl_id(t.getColl_id());
		collection = this.konkaMobileCollectionDao.selectEntity(collection);

		KonkaMobileStocksHis konkaMobileStocksHis = new KonkaMobileStocksHis();
		konkaMobileStocksHis.setColl_id(collection.getColl_id());
		konkaMobileStocksHis.setStorage_id(collection.getStorage_id());
		konkaMobileStocksHis.setOp_num(collection.getBusi_num());
		konkaMobileStocksHis.setOp_type((Long) t.getMap().get("opty"));
		konkaMobileStocksHis.setOp_man((Long) t.getMap().get("op_man"));
		konkaMobileStocksHis.setOp_time(new Date());

		konkaMobileStocksHisDao.insertEntity(konkaMobileStocksHis);
		return result;
	}

}
