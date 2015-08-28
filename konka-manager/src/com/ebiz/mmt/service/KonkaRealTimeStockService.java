package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.PePdModel;

public interface KonkaRealTimeStockService {

	List<PePdModel> getIsUseProductByKeyword(PePdModel t);
	List<PePdModel> getRealTimeStockByKeyword(PePdModel t);
	PePdModel getRealTimeStockTotleByKeyword(PePdModel t);
	List<PePdModel> getPePdModelWithStockDetails(PePdModel t);
}
