package com.ebiz.mmt.dao;

import java.util.List;

import com.ebiz.mmt.domain.KonkaJxcStoreState;
import com.ebiz.ssi.dao.EntityDao;

/**
 * @author Wu,Yang
 * @version 2011-10-11 09:18
 */
public interface KonkaJxcStoreStateDao extends EntityDao<KonkaJxcStoreState> {
	/**
	 * @author Qin,Yue
	 * @version 2011-11-18
	 */
	public KonkaJxcStoreState selectQcjcForStockState(KonkaJxcStoreState t);
	
	public List<KonkaJxcStoreState> selectStockRcjhAndRcfhList(KonkaJxcStoreState t);
	
	public List<KonkaJxcStoreState> selectStockRcjhAndRcfhListForStore(KonkaJxcStoreState t);
	
	public List<KonkaJxcStoreState> selectKonkaJXCStockDetailsList(KonkaJxcStoreState t);
}
