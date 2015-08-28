package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaRealTimeStockDao;
import com.ebiz.mmt.domain.PePdModel;
import com.ebiz.mmt.service.KonkaRealTimeStockService;

@Service
public class KonkaRealTimeStockImpl implements KonkaRealTimeStockService {

	@Resource
	private KonkaRealTimeStockDao konkaRealTimeStockDao;

	@Override
	public List<PePdModel> getIsUseProductByKeyword(PePdModel t) {
		return this.konkaRealTimeStockDao.selectIsUseProductByKeyword(t);
	}
	@Override
	public List<PePdModel> getRealTimeStockByKeyword(PePdModel t) {
		return this.konkaRealTimeStockDao.selectRealTimeStockByKeyword(t);
	}
	@Override
	public PePdModel getRealTimeStockTotleByKeyword(PePdModel t) {
		return this.konkaRealTimeStockDao.selectRealTimeStockTotleByKeyword(t);
	}
	@Override
	public List<PePdModel> getPePdModelWithStockDetails(PePdModel t) {
		return this.konkaRealTimeStockDao.selectPePdModelWithStockDetails(t);
	}

}
