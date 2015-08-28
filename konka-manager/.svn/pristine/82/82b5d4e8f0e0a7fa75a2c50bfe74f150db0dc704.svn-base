package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.VOrgOfDeptDao;
import com.ebiz.mmt.domain.VOrgOfDept;
import com.ebiz.mmt.service.VOrgOfDeptService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-07-04 18:27:36
 */
@Service
public class VOrgOfDeptServiceImpl implements VOrgOfDeptService {

	@Resource
	private VOrgOfDeptDao vOrgOfDeptDao;
	

	public Long createVOrgOfDept(VOrgOfDept t) {
		return this.vOrgOfDeptDao.insertEntity(t);
	}

	public VOrgOfDept getVOrgOfDept(VOrgOfDept t) {
		return this.vOrgOfDeptDao.selectEntity(t);
	}

	public Long getVOrgOfDeptCount(VOrgOfDept t) {
		return this.vOrgOfDeptDao.selectEntityCount(t);
	}

	public List<VOrgOfDept> getVOrgOfDeptList(VOrgOfDept t) {
		return this.vOrgOfDeptDao.selectEntityList(t);
	}

	public int modifyVOrgOfDept(VOrgOfDept t) {
		return this.vOrgOfDeptDao.updateEntity(t);
	}

	public int removeVOrgOfDept(VOrgOfDept t) {
		return this.vOrgOfDeptDao.deleteEntity(t);
	}

	public List<VOrgOfDept> getVOrgOfDeptPaginatedList(VOrgOfDept t) {
		return this.vOrgOfDeptDao.selectEntityPaginatedList(t);
	}

}
