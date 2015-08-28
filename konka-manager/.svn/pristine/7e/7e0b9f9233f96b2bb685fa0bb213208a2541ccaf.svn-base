package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.EcPdEavlZanDao;
import com.ebiz.mmt.domain.EcPdEavlZan;
import com.ebiz.mmt.service.EcPdEavlZanService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2015-07-15 14:32:12
 */
@Service
public class EcPdEavlZanServiceImpl implements EcPdEavlZanService {

	@Resource
	private EcPdEavlZanDao ecPdEavlZanDao;
	

	public Long createEcPdEavlZan(EcPdEavlZan t) {
		return this.ecPdEavlZanDao.insertEntity(t);
	}

	public EcPdEavlZan getEcPdEavlZan(EcPdEavlZan t) {
		return this.ecPdEavlZanDao.selectEntity(t);
	}

	public Long getEcPdEavlZanCount(EcPdEavlZan t) {
		return this.ecPdEavlZanDao.selectEntityCount(t);
	}

	public List<EcPdEavlZan> getEcPdEavlZanList(EcPdEavlZan t) {
		return this.ecPdEavlZanDao.selectEntityList(t);
	}

	public int modifyEcPdEavlZan(EcPdEavlZan t) {
		return this.ecPdEavlZanDao.updateEntity(t);
	}

	public int removeEcPdEavlZan(EcPdEavlZan t) {
		return this.ecPdEavlZanDao.deleteEntity(t);
	}

	public List<EcPdEavlZan> getEcPdEavlZanPaginatedList(EcPdEavlZan t) {
		return this.ecPdEavlZanDao.selectEntityPaginatedList(t);
	}

}
