package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ebiz.mmt.dao.KonkaRealTimeStockDao;
import com.ebiz.mmt.domain.KonkaRealTimeStock;
import com.ebiz.mmt.domain.PePdModel;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

@Repository
public class KonkaRealTimeStockDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaRealTimeStock> implements
		KonkaRealTimeStockDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<PePdModel> selectIsUseProductByKeyword(PePdModel t) {
		return super.getSqlMapClientTemplate().queryForList("selectIsUseProductByKeyword", t);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<PePdModel> selectRealTimeStockByKeyword(PePdModel t) {
		return super.getSqlMapClientTemplate().queryForList("selectRealTimeStockByKeyword", t);
	}
	@Override
	public PePdModel selectRealTimeStockTotleByKeyword(PePdModel t) {
		return (PePdModel)super.getSqlMapClientTemplate().queryForObject("selectRealTimeStockTotleByKeyword", t);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PePdModel> selectPePdModelWithStockDetails(PePdModel t) {
		return super.getSqlMapClientTemplate().queryForList("selectPePdModelWithStockDetails", t);
	}

}