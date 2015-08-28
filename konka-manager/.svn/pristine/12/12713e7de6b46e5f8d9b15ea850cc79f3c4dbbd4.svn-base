package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.EcGoodsPriceAreaDao;
import com.ebiz.mmt.domain.EcGoodsPriceArea;
import com.ebiz.mmt.service.EcGoodsPriceAreaService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-09-09 10:06:25
 */
@Service
public class EcGoodsPriceAreaServiceImpl implements EcGoodsPriceAreaService {

	@Resource
	private EcGoodsPriceAreaDao ecGoodsPriceAreaDao;
	

	public Long createEcGoodsPriceArea(EcGoodsPriceArea t) {
		return this.ecGoodsPriceAreaDao.insertEntity(t);
	}

	public EcGoodsPriceArea getEcGoodsPriceArea(EcGoodsPriceArea t) {
		return this.ecGoodsPriceAreaDao.selectEntity(t);
	}

	public Long getEcGoodsPriceAreaCount(EcGoodsPriceArea t) {
		return this.ecGoodsPriceAreaDao.selectEntityCount(t);
	}

	public List<EcGoodsPriceArea> getEcGoodsPriceAreaList(EcGoodsPriceArea t) {
		return this.ecGoodsPriceAreaDao.selectEntityList(t);
	}

	public int modifyEcGoodsPriceArea(EcGoodsPriceArea t) {
		return this.ecGoodsPriceAreaDao.updateEntity(t);
	}

	public int removeEcGoodsPriceArea(EcGoodsPriceArea t) {
		return this.ecGoodsPriceAreaDao.deleteEntity(t);
	}

	public List<EcGoodsPriceArea> getEcGoodsPriceAreaPaginatedList(EcGoodsPriceArea t) {
		return this.ecGoodsPriceAreaDao.selectEntityPaginatedList(t);
	}

}
