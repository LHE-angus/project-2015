package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.BasePdClassDao;
import com.ebiz.mmt.domain.BasePdClass;
import com.ebiz.mmt.service.BasePdClassService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Chen,ShunHua
 * @date 2011-09-21 14:45:12
 */
@Service
public class BasePdClassServiceImpl implements BasePdClassService {

	@Resource
	private BasePdClassDao basePdClassDao;

	public Long createBasePdClass(BasePdClass t) {
		return this.basePdClassDao.insertEntity(t);
	}

	public BasePdClass getBasePdClass(BasePdClass t) {
		return this.basePdClassDao.selectEntity(t);
	}

	public Long getBasePdClassCount(BasePdClass t) {
		return this.basePdClassDao.selectEntityCount(t);
	}

	public List<BasePdClass> getBasePdClassList(BasePdClass t) {
		return this.basePdClassDao.selectEntityList(t);
	}

	public int modifyBasePdClass(BasePdClass t) {
		return this.basePdClassDao.updateEntity(t);
	}

	public int removeBasePdClass(BasePdClass t) {
		return this.basePdClassDao.deleteEntity(t);
	}

	public List<BasePdClass> getBasePdClassPaginatedList(BasePdClass t) {
		return this.basePdClassDao.selectEntityPaginatedList(t);
	}

	public List<BasePdClass> getBasePdClassListByParClassId(BasePdClass t) {
		return this.basePdClassDao.selectBasePdClassListByParClassId(t);
	}

	public List<BasePdClass> getBasePdClassCountNum(BasePdClass t) {
		return this.basePdClassDao.selectBasePdClassCountNum(t);
	}

}
