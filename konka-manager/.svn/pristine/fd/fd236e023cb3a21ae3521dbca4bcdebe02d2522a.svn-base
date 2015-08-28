package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.SfhkCompanyDao;
import com.ebiz.mmt.domain.SfhkCompany;
import com.ebiz.mmt.service.SfhkCompanyService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-10-28 10:39:24
 */
@Service
public class SfhkCompanyServiceImpl implements SfhkCompanyService {

	@Resource
	private SfhkCompanyDao sfhkCompanyDao;
	

	public Long createSfhkCompany(SfhkCompany t) {
		return this.sfhkCompanyDao.insertEntity(t);
	}

	public SfhkCompany getSfhkCompany(SfhkCompany t) {
		return this.sfhkCompanyDao.selectEntity(t);
	}

	public Long getSfhkCompanyCount(SfhkCompany t) {
		return this.sfhkCompanyDao.selectEntityCount(t);
	}

	public List<SfhkCompany> getSfhkCompanyList(SfhkCompany t) {
		return this.sfhkCompanyDao.selectEntityList(t);
	}

	public int modifySfhkCompany(SfhkCompany t) {
		return this.sfhkCompanyDao.updateEntity(t);
	}

	public int removeSfhkCompany(SfhkCompany t) {
		return this.sfhkCompanyDao.deleteEntity(t);
	}

	public List<SfhkCompany> getSfhkCompanyPaginatedList(SfhkCompany t) {
		return this.sfhkCompanyDao.selectEntityPaginatedList(t);
	}

}
