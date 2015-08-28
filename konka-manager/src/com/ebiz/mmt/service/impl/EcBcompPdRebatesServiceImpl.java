package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.EcBcompPdRebatesDao;
import com.ebiz.mmt.domain.EcBcompPdRebates;
import com.ebiz.mmt.service.EcBcompPdRebatesService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-09-09 21:36:35
 */
@Service
public class EcBcompPdRebatesServiceImpl implements EcBcompPdRebatesService {

	@Resource
	private EcBcompPdRebatesDao ecBcompPdRebatesDao;
	

	public Long createEcBcompPdRebates(EcBcompPdRebates t) {
		return this.ecBcompPdRebatesDao.insertEntity(t);
	}

	public EcBcompPdRebates getEcBcompPdRebates(EcBcompPdRebates t) {
		return this.ecBcompPdRebatesDao.selectEntity(t);
	}

	public Long getEcBcompPdRebatesCount(EcBcompPdRebates t) {
		return this.ecBcompPdRebatesDao.selectEntityCount(t);
	}

	public List<EcBcompPdRebates> getEcBcompPdRebatesList(EcBcompPdRebates t) {
		return this.ecBcompPdRebatesDao.selectEntityList(t);
	}

	public int modifyEcBcompPdRebates(EcBcompPdRebates t) {
		return this.ecBcompPdRebatesDao.updateEntity(t);
	}

	public int removeEcBcompPdRebates(EcBcompPdRebates t) {
		return this.ecBcompPdRebatesDao.deleteEntity(t);
	}

	public List<EcBcompPdRebates> getEcBcompPdRebatesPaginatedList(EcBcompPdRebates t) {
		return this.ecBcompPdRebatesDao.selectEntityPaginatedList(t);
	}

}
