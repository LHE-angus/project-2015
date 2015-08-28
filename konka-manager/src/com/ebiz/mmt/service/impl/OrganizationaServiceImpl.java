package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.OrganizationaDao;
import com.ebiz.mmt.domain.Organizationa;
import com.ebiz.mmt.service.OrganizationaService;

/**
 * @author Xu,ZhengYang
 * @date 2010-11-14 05:05:22
 */

@Service
public class OrganizationaServiceImpl implements OrganizationaService {

	@Resource
	private OrganizationaDao organizationaDao;

	public Long createOrganizationa(Organizationa t) {
		return this.organizationaDao.insertEntity(t);
	}

	public int modifyOrganizationa(Organizationa t) {
		return this.organizationaDao.updateEntity(t);
	}

	public int removeOrganizationa(Organizationa t) {
		return this.organizationaDao.deleteEntity(t);
	}

	public Organizationa getOrganizationa(Organizationa t) {
		return this.organizationaDao.selectEntity(t);
	}

	public Long getOrganizationaCount(Organizationa t) {
		return this.organizationaDao.selectEntityCount(t);
	}

	public List<Organizationa> getOrganizationaList(Organizationa t) {
		return this.organizationaDao.selectEntityList(t);
	}

	public List<Organizationa> getOrganizationaPaginatedList(Organizationa t) {
		return this.organizationaDao.selectEntityPaginatedList(t);
	}
}
