package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaJxcStoreStateDao;
import com.ebiz.mmt.domain.KonkaJxcStoreState;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * @author Wu,Yang
 * @version 2011-10-11 09:18
 */
@Service
public class KonkaJxcStoreStateDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaJxcStoreState> implements KonkaJxcStoreStateDao {

	@Override
	public KonkaJxcStoreState selectQcjcForStockState(KonkaJxcStoreState t) {
		return (KonkaJxcStoreState) super.getSqlMapClientTemplate().queryForObject("selectQcjcForStockState", t);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<KonkaJxcStoreState> selectStockRcjhAndRcfhList(KonkaJxcStoreState t) {
		return super.getSqlMapClientTemplate().queryForList("selectStockRcjhAndRcfhList",t);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<KonkaJxcStoreState> selectStockRcjhAndRcfhListForStore(KonkaJxcStoreState t) {
		return super.getSqlMapClientTemplate().queryForList("selectStockRcjhAndRcfhListForStore",t);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<KonkaJxcStoreState> selectKonkaJXCStockDetailsList(KonkaJxcStoreState t) {
		return super.getSqlMapClientTemplate().queryForList("selectKonkaJXCStockDetailsList",t);
	}

}
