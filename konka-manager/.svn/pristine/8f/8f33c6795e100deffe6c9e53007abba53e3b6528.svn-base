package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.EcGroupDao;
import com.ebiz.mmt.domain.EcGroup;
import com.ebiz.mmt.service.EcGroupService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-12-08 14:09:39
 */
@Service
public class EcGroupServiceImpl implements EcGroupService {

	@Resource
	private EcGroupDao ecGroupDao;

	public Long createEcGroup(EcGroup t) {
		return this.ecGroupDao.insertEntity(t);
	}

	public EcGroup getEcGroup(EcGroup t) {
		return this.ecGroupDao.selectEntity(t);
	}

	public Long getEcGroupCount(EcGroup t) {
		return this.ecGroupDao.selectEntityCount(t);
	}

	public List<EcGroup> getEcGroupList(EcGroup t) {
		return this.ecGroupDao.selectEntityList(t);
	}

	public int modifyEcGroup(EcGroup t) {
		return this.ecGroupDao.updateEntity(t);
	}

	public int removeEcGroup(EcGroup t) {
		return this.ecGroupDao.deleteEntity(t);
	}

	public List<EcGroup> getEcGroupPaginatedList(EcGroup t) {
		return this.ecGroupDao.selectEntityPaginatedList(t);
	}

	@Override
	public List<EcGroup> getEcGroupForTreePaginatedList(EcGroup t) {
		return this.ecGroupDao.selectEcGroupForTreePaginatedList(t);
	}

	@Override
	public List<EcGroup> getEcGroupForTreeNameList(EcGroup t) {
		return this.ecGroupDao.selectEcGroupForTreeNameList(t);
	}

}
