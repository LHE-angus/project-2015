package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.EcSelfAreaDao;
import com.ebiz.mmt.domain.EcSelfArea;
import com.ebiz.mmt.service.EcSelfAreaService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-05-15 15:20:16
 */
@Service
public class EcSelfAreaServiceImpl implements EcSelfAreaService {

	@Resource
	private EcSelfAreaDao ecSelfAreaDao;
	

	public Long createEcSelfArea(EcSelfArea t) {
		return this.ecSelfAreaDao.insertEntity(t);
	}

	public EcSelfArea getEcSelfArea(EcSelfArea t) {
		return this.ecSelfAreaDao.selectEntity(t);
	}

	public Long getEcSelfAreaCount(EcSelfArea t) {
		return this.ecSelfAreaDao.selectEntityCount(t);
	}

	public List<EcSelfArea> getEcSelfAreaList(EcSelfArea t) {
		return this.ecSelfAreaDao.selectEntityList(t);
	}

	public int modifyEcSelfArea(EcSelfArea t) {
		return this.ecSelfAreaDao.updateEntity(t);
	}

	public int removeEcSelfArea(EcSelfArea t) {
		return this.ecSelfAreaDao.deleteEntity(t);
	}

	public List<EcSelfArea> getEcSelfAreaPaginatedList(EcSelfArea t) {
		return this.ecSelfAreaDao.selectEntityPaginatedList(t);
	}

}
