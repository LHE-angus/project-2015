package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.VOrgOfDept;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-07-04 18:27:36
 */
public interface VOrgOfDeptService {

	Long createVOrgOfDept(VOrgOfDept t);

	int modifyVOrgOfDept(VOrgOfDept t);

	int removeVOrgOfDept(VOrgOfDept t);

	VOrgOfDept getVOrgOfDept(VOrgOfDept t);

	List<VOrgOfDept> getVOrgOfDeptList(VOrgOfDept t);

	Long getVOrgOfDeptCount(VOrgOfDept t);

	List<VOrgOfDept> getVOrgOfDeptPaginatedList(VOrgOfDept t);

}