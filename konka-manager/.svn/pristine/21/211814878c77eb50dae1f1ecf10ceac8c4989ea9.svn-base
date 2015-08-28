package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaJxcStoreStateDao;
import com.ebiz.mmt.domain.KonkaJxcStoreState;
import com.ebiz.mmt.service.KonkaJxcStoreStateService;

/**
 * @author Wu,Yang
 * @version 2011-10-11 09:18
 */
@Service
public class KonkaJxcStoreStateServiceImpl implements KonkaJxcStoreStateService {

	@Resource
	private KonkaJxcStoreStateDao konkaJxcStoreStateDao;
	

	public Long createKonkaJxcStoreState(KonkaJxcStoreState t) {
		return this.konkaJxcStoreStateDao.insertEntity(t);
	}

	public KonkaJxcStoreState getKonkaJxcStoreState(KonkaJxcStoreState t) {
		return this.konkaJxcStoreStateDao.selectEntity(t);
	}

	public Long getKonkaJxcStoreStateCount(KonkaJxcStoreState t) {
		return this.konkaJxcStoreStateDao.selectEntityCount(t);
	}

	public List<KonkaJxcStoreState> getKonkaJxcStoreStateList(KonkaJxcStoreState t) {
		return this.konkaJxcStoreStateDao.selectEntityList(t);
	}

	public int modifyKonkaJxcStoreState(KonkaJxcStoreState t) {
		return this.konkaJxcStoreStateDao.updateEntity(t);
	}

	public int removeKonkaJxcStoreState(KonkaJxcStoreState t) {
		return this.konkaJxcStoreStateDao.deleteEntity(t);
	}

	public List<KonkaJxcStoreState> getKonkaJxcStoreStatePaginatedList(KonkaJxcStoreState t) {
		return this.konkaJxcStoreStateDao.selectEntityPaginatedList(t);
	}

	@Override
	public KonkaJxcStoreState selectQcjcForStockState(KonkaJxcStoreState t) {
		return this.konkaJxcStoreStateDao.selectQcjcForStockState(t);
	}

	@Override
	public List<KonkaJxcStoreState> selectStockRcjhAndRcfhList(KonkaJxcStoreState t) {
		return this.konkaJxcStoreStateDao.selectStockRcjhAndRcfhList(t);
	}

	@Override
	public List<KonkaJxcStoreState> selectStockRcjhAndRcfhListForStore(KonkaJxcStoreState t) {
		return this.konkaJxcStoreStateDao.selectStockRcjhAndRcfhListForStore(t);
	}

	@Override
	public List<KonkaJxcStoreState> selectKonkaJXCStockDetailsList(KonkaJxcStoreState t) {
		return this.konkaJxcStoreStateDao.selectKonkaJXCStockDetailsList(t);
	}

}
