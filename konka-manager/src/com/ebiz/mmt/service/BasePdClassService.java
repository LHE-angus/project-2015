package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.BasePdClass;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Chen,ShunHua
 * @date 2011-09-21 09:45:12
 */
public interface BasePdClassService {

	Long createBasePdClass(BasePdClass t);

	int modifyBasePdClass(BasePdClass t);

	int removeBasePdClass(BasePdClass t);

	BasePdClass getBasePdClass(BasePdClass t);

	List<BasePdClass> getBasePdClassList(BasePdClass t);

	Long getBasePdClassCount(BasePdClass t);

	List<BasePdClass> getBasePdClassPaginatedList(BasePdClass t);

	List<BasePdClass> getBasePdClassListByParClassId(BasePdClass t);

	List<BasePdClass> getBasePdClassCountNum(BasePdClass t);

}