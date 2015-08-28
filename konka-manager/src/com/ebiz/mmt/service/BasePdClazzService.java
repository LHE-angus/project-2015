package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.BasePdClazz;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Chen,ShunHua
 * @date 2011-09-21 09:45:12
 */
public interface BasePdClazzService {

	Long createBasePdClazz(BasePdClazz t);

	int modifyBasePdClazz(BasePdClazz t);

	int removeBasePdClazz(BasePdClazz t);

	BasePdClazz getBasePdClazz(BasePdClazz t);

	List<BasePdClazz> getBasePdClazzList(BasePdClazz t);

	Long getBasePdClazzCount(BasePdClazz t);

	List<BasePdClazz> getBasePdClazzPaginatedList(BasePdClazz t);

	/**
	 * @author Chen,ShunHua
	 * @version 2011.09.21
	 */
	List<BasePdClazz> getBasePdClazzWithPropnamesList(BasePdClazz t);

}