package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.EcGoodsPriceModifyForToucDao;
import com.ebiz.mmt.domain.EcGoodsPriceModifyForTouc;
import com.ebiz.mmt.service.EcGoodsPriceModifyForToucService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-09-27 16:35:56
 */
@Service
public class EcGoodsPriceModifyForToucServiceImpl implements EcGoodsPriceModifyForToucService {

	@Resource
	private EcGoodsPriceModifyForToucDao ecGoodsPriceModifyForToucDao;
	

	public Long createEcGoodsPriceModifyForTouc(EcGoodsPriceModifyForTouc t) {
		return this.ecGoodsPriceModifyForToucDao.insertEntity(t);
	}

	public EcGoodsPriceModifyForTouc getEcGoodsPriceModifyForTouc(EcGoodsPriceModifyForTouc t) {
		return this.ecGoodsPriceModifyForToucDao.selectEntity(t);
	}

	public Long getEcGoodsPriceModifyForToucCount(EcGoodsPriceModifyForTouc t) {
		return this.ecGoodsPriceModifyForToucDao.selectEntityCount(t);
	}

	public List<EcGoodsPriceModifyForTouc> getEcGoodsPriceModifyForToucList(EcGoodsPriceModifyForTouc t) {
		return this.ecGoodsPriceModifyForToucDao.selectEntityList(t);
	}

	public int modifyEcGoodsPriceModifyForTouc(EcGoodsPriceModifyForTouc t) {
		return this.ecGoodsPriceModifyForToucDao.updateEntity(t);
	}

	public int removeEcGoodsPriceModifyForTouc(EcGoodsPriceModifyForTouc t) {
		return this.ecGoodsPriceModifyForToucDao.deleteEntity(t);
	}

	public List<EcGoodsPriceModifyForTouc> getEcGoodsPriceModifyForToucPaginatedList(EcGoodsPriceModifyForTouc t) {
		return this.ecGoodsPriceModifyForToucDao.selectEntityPaginatedList(t);
	}

}
