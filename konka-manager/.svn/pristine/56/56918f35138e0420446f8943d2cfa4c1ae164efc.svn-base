package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.EcGoodsStockModifyForToucDao;
import com.ebiz.mmt.domain.EcGoodsStockModifyForTouc;
import com.ebiz.mmt.service.EcGoodsStockModifyForToucService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-09-27 16:41:35
 */
@Service
public class EcGoodsStockModifyForToucServiceImpl implements EcGoodsStockModifyForToucService {

	@Resource
	private EcGoodsStockModifyForToucDao ecGoodsStockModifyForToucDao;
	

	public Long createEcGoodsStockModifyForTouc(EcGoodsStockModifyForTouc t) {
		return this.ecGoodsStockModifyForToucDao.insertEntity(t);
	}

	public EcGoodsStockModifyForTouc getEcGoodsStockModifyForTouc(EcGoodsStockModifyForTouc t) {
		return this.ecGoodsStockModifyForToucDao.selectEntity(t);
	}

	public Long getEcGoodsStockModifyForToucCount(EcGoodsStockModifyForTouc t) {
		return this.ecGoodsStockModifyForToucDao.selectEntityCount(t);
	}

	public List<EcGoodsStockModifyForTouc> getEcGoodsStockModifyForToucList(EcGoodsStockModifyForTouc t) {
		return this.ecGoodsStockModifyForToucDao.selectEntityList(t);
	}

	public int modifyEcGoodsStockModifyForTouc(EcGoodsStockModifyForTouc t) {
		return this.ecGoodsStockModifyForToucDao.updateEntity(t);
	}

	public int removeEcGoodsStockModifyForTouc(EcGoodsStockModifyForTouc t) {
		return this.ecGoodsStockModifyForToucDao.deleteEntity(t);
	}

	public List<EcGoodsStockModifyForTouc> getEcGoodsStockModifyForToucPaginatedList(EcGoodsStockModifyForTouc t) {
		return this.ecGoodsStockModifyForToucDao.selectEntityPaginatedList(t);
	}

}
