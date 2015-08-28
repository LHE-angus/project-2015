package com.ebiz.mmt.dao;

import java.util.List;

import com.ebiz.mmt.domain.KonkaRealTimeStock;
import com.ebiz.mmt.domain.PePdModel;
import com.ebiz.ssi.dao.EntityDao;

public interface KonkaRealTimeStockDao extends EntityDao<KonkaRealTimeStock> {

	List<PePdModel> selectIsUseProductByKeyword(PePdModel t);
	
	List<PePdModel> selectRealTimeStockByKeyword(PePdModel t);
	
	PePdModel selectRealTimeStockTotleByKeyword(PePdModel t);
	
	List<PePdModel> selectPePdModelWithStockDetails(PePdModel t);

}