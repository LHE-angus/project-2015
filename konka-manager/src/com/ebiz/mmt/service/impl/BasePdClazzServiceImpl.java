package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.BasePdClazzDao;
import com.ebiz.mmt.domain.BasePdClazz;
import com.ebiz.mmt.service.BasePdClazzService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Chen,ShunHua
 * @date 2011-09-21 14:45:12
 */
@Service
public class BasePdClazzServiceImpl implements BasePdClazzService {

	@Resource
	private BasePdClazzDao basePdClazzDao;

	public Long createBasePdClazz(BasePdClazz t) {
		return this.basePdClazzDao.insertEntity(t);
	}

	public BasePdClazz getBasePdClazz(BasePdClazz t) {
		return this.basePdClazzDao.selectEntity(t);
	}

	public Long getBasePdClazzCount(BasePdClazz t) {
		return this.basePdClazzDao.selectEntityCount(t);
	}

	public List<BasePdClazz> getBasePdClazzList(BasePdClazz t) {
		return this.basePdClazzDao.selectEntityList(t);
	}

	public int modifyBasePdClazz(BasePdClazz t) {
		return this.basePdClazzDao.updateEntity(t);
	}

	public int removeBasePdClazz(BasePdClazz t) {
		return this.basePdClazzDao.deleteEntity(t);
	}

	public List<BasePdClazz> getBasePdClazzPaginatedList(BasePdClazz t) {
		return this.basePdClazzDao.selectEntityPaginatedList(t);
	}

	/**
	 * @author Chen,ShunHua
	 * @version 2011.09.21
	 */
	public List<BasePdClazz> getBasePdClazzWithPropnamesList(BasePdClazz t) {
		return this.basePdClazzDao.selectBasePdClazzWithPropnamesList(t);
	}

}
