package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.BaseVisitType;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-06-23 16:32:16
 */
public interface BaseVisitTypeService {

	Long createBaseVisitType(BaseVisitType t);

	int modifyBaseVisitType(BaseVisitType t);

	int removeBaseVisitType(BaseVisitType t);

	BaseVisitType getBaseVisitType(BaseVisitType t);

	List<BaseVisitType> getBaseVisitTypeList(BaseVisitType t);

	Long getBaseVisitTypeCount(BaseVisitType t);

	List<BaseVisitType> getBaseVisitTypePaginatedList(BaseVisitType t);
	
	List<BaseVisitType> getBaseVisitTypeByReportTypeList(BaseVisitType t);
}