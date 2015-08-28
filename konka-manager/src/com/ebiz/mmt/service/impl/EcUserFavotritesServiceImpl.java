package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.EcUserFavotritesDao;
import com.ebiz.mmt.domain.EcUserFavotrites;
import com.ebiz.mmt.service.EcUserFavotritesService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-09-09 10:06:25
 */
@Service
public class EcUserFavotritesServiceImpl implements EcUserFavotritesService {

	@Resource
	private EcUserFavotritesDao ecUserFavotritesDao;
	

	public Long createEcUserFavotrites(EcUserFavotrites t) {
		return this.ecUserFavotritesDao.insertEntity(t);
	}

	public EcUserFavotrites getEcUserFavotrites(EcUserFavotrites t) {
		return this.ecUserFavotritesDao.selectEntity(t);
	}

	public Long getEcUserFavotritesCount(EcUserFavotrites t) {
		return this.ecUserFavotritesDao.selectEntityCount(t);
	}

	public List<EcUserFavotrites> getEcUserFavotritesList(EcUserFavotrites t) {
		return this.ecUserFavotritesDao.selectEntityList(t);
	}

	public int modifyEcUserFavotrites(EcUserFavotrites t) {
		return this.ecUserFavotritesDao.updateEntity(t);
	}

	public int removeEcUserFavotrites(EcUserFavotrites t) {
		return this.ecUserFavotritesDao.deleteEntity(t);
	}

	public List<EcUserFavotrites> getEcUserFavotritesPaginatedList(EcUserFavotrites t) {
		return this.ecUserFavotritesDao.selectEntityPaginatedList(t);
	}

}
