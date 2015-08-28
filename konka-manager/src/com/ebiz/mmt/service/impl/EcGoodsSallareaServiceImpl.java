package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.EcGoodsSallareaDao;
import com.ebiz.mmt.domain.EcGoodsSallarea;
import com.ebiz.mmt.service.EcGoodsSallareaService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-12-12 10:43:11
 */
@Service
public class EcGoodsSallareaServiceImpl implements EcGoodsSallareaService {

	@Resource
	private EcGoodsSallareaDao ecGoodsSallareaDao;
	

	public Long createEcGoodsSallarea(EcGoodsSallarea t) {
		return this.ecGoodsSallareaDao.insertEntity(t);
	}

	public EcGoodsSallarea getEcGoodsSallarea(EcGoodsSallarea t) {
		return this.ecGoodsSallareaDao.selectEntity(t);
	}

	public Long getEcGoodsSallareaCount(EcGoodsSallarea t) {
		return this.ecGoodsSallareaDao.selectEntityCount(t);
	}

	public List<EcGoodsSallarea> getEcGoodsSallareaList(EcGoodsSallarea t) {
		return this.ecGoodsSallareaDao.selectEntityList(t);
	}

	public int modifyEcGoodsSallarea(EcGoodsSallarea t) {
		return this.ecGoodsSallareaDao.updateEntity(t);
	}

	public int removeEcGoodsSallarea(EcGoodsSallarea t) {
		return this.ecGoodsSallareaDao.deleteEntity(t);
	}

	public List<EcGoodsSallarea> getEcGoodsSallareaPaginatedList(EcGoodsSallarea t) {
		return this.ecGoodsSallareaDao.selectEntityPaginatedList(t);
	}

}
