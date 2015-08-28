package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaMobileImpHisDao;
import com.ebiz.mmt.dao.KonkaMobileImpStoreDao;
import com.ebiz.mmt.domain.KonkaMobileImpHis;
import com.ebiz.mmt.domain.KonkaMobileImpStore;
import com.ebiz.mmt.service.KonkaMobileImpStoreService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-05-23 15:45:29
 */
@Service
public class KonkaMobileImpStoreServiceImpl implements KonkaMobileImpStoreService {

	@Resource
	private KonkaMobileImpStoreDao konkaMobileImpStoreDao;
	
	@Resource
	private KonkaMobileImpHisDao konkaMobileImpHisDao;
	

	public Long createKonkaMobileImpStore(KonkaMobileImpStore t) {
		return this.konkaMobileImpStoreDao.insertEntity(t);
	}
	
	public Long createKonkaMobileImpStoreListAndHis(List<KonkaMobileImpStore> konkaMobileImpStoreList, KonkaMobileImpHis konkaMobileImpHis) {
		Long his_id = this.konkaMobileImpHisDao.insertEntity(konkaMobileImpHis);
		for (KonkaMobileImpStore konkaMobileImpStore : konkaMobileImpStoreList) {
			konkaMobileImpStore.setOpr_his_id(his_id);
			this.konkaMobileImpStoreDao.insertEntity(konkaMobileImpStore);
		}
		return his_id;
	}

	public KonkaMobileImpStore getKonkaMobileImpStore(KonkaMobileImpStore t) {
		return this.konkaMobileImpStoreDao.selectEntity(t);
	}

	public Long getKonkaMobileImpStoreCount(KonkaMobileImpStore t) {
		return this.konkaMobileImpStoreDao.selectEntityCount(t);
	}

	public List<KonkaMobileImpStore> getKonkaMobileImpStoreList(KonkaMobileImpStore t) {
		return this.konkaMobileImpStoreDao.selectEntityList(t);
	}

	public int modifyKonkaMobileImpStore(KonkaMobileImpStore t) {
		return this.konkaMobileImpStoreDao.updateEntity(t);
	}

	public int removeKonkaMobileImpStore(KonkaMobileImpStore t) {
		return this.konkaMobileImpStoreDao.deleteEntity(t);
	}

	public List<KonkaMobileImpStore> getKonkaMobileImpStorePaginatedList(KonkaMobileImpStore t) {
		return this.konkaMobileImpStoreDao.selectEntityPaginatedList(t);
	}

	public List<KonkaMobileImpStore> getKonkaMobileImpStoreListForDistinctSdf(KonkaMobileImpStore t) {
		return this.konkaMobileImpStoreDao.selectKonkaMobileImpStoreListForDistinctSdf(t);
	}

}
