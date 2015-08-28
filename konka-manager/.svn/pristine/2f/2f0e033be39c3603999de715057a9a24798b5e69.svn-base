package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.BaseVisitTypeDao;
import com.ebiz.mmt.domain.BaseVisitType;
import com.ebiz.mmt.service.BaseVisitTypeService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-06-23 16:32:16
 */
@Service
public class BaseVisitTypeServiceImpl implements BaseVisitTypeService {

	@Resource
	private BaseVisitTypeDao baseVisitTypeDao;
	

	public Long createBaseVisitType(BaseVisitType t) {
		return this.baseVisitTypeDao.insertEntity(t);
	}

	public BaseVisitType getBaseVisitType(BaseVisitType t) {
		return this.baseVisitTypeDao.selectEntity(t);
	}

	public Long getBaseVisitTypeCount(BaseVisitType t) {
		return this.baseVisitTypeDao.selectEntityCount(t);
	}

	public List<BaseVisitType> getBaseVisitTypeList(BaseVisitType t) {
		return this.baseVisitTypeDao.selectEntityList(t);
	}

	public int modifyBaseVisitType(BaseVisitType t) {
		return this.baseVisitTypeDao.updateEntity(t);
	}

	public int removeBaseVisitType(BaseVisitType t) {
		return this.baseVisitTypeDao.deleteEntity(t);
	}

	public List<BaseVisitType> getBaseVisitTypePaginatedList(BaseVisitType t) {
		return this.baseVisitTypeDao.selectEntityPaginatedList(t);
	}

	/**
	 * 通过上报类型查找对应的拜访类型
	 */
	public List<BaseVisitType> getBaseVisitTypeByReportTypeList(BaseVisitType t) {
		return this.baseVisitTypeDao.selectBaseVisitTypeByReportTypeList(t);
	}

}
