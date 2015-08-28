package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.EcGroup;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-12-08 14:09:39
 */
public interface EcGroupService {

	Long createEcGroup(EcGroup t);

	int modifyEcGroup(EcGroup t);

	int removeEcGroup(EcGroup t);

	EcGroup getEcGroup(EcGroup t);

	List<EcGroup> getEcGroupList(EcGroup t);

	Long getEcGroupCount(EcGroup t);

	List<EcGroup> getEcGroupPaginatedList(EcGroup t);

	List<EcGroup> getEcGroupForTreePaginatedList(EcGroup t);

	List<EcGroup> getEcGroupForTreeNameList(EcGroup t);

}