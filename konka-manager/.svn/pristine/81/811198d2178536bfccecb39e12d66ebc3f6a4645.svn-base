package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.EcSallareaDao;
import com.ebiz.mmt.domain.EcSallarea;
import com.ebiz.mmt.service.EcSallareaService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-12-12 10:43:11
 */
@Service
public class EcSallareaServiceImpl implements EcSallareaService {

	@Resource
	private EcSallareaDao ecSallareaDao;
	

	public Long createEcSallarea(EcSallarea t) {
		return this.ecSallareaDao.insertEntity(t);
	}

	public EcSallarea getEcSallarea(EcSallarea t) {
		return this.ecSallareaDao.selectEntity(t);
	}

	public Long getEcSallareaCount(EcSallarea t) {
		return this.ecSallareaDao.selectEntityCount(t);
	}

	public List<EcSallarea> getEcSallareaList(EcSallarea t) {
		return this.ecSallareaDao.selectEntityList(t);
	}

	public int modifyEcSallarea(EcSallarea t) {
		return this.ecSallareaDao.updateEntity(t);
	}

	public int removeEcSallarea(EcSallarea t) {
		return this.ecSallareaDao.deleteEntity(t);
	}

	public List<EcSallarea> getEcSallareaPaginatedList(EcSallarea t) {
		return this.ecSallareaDao.selectEntityPaginatedList(t);
	}

}
