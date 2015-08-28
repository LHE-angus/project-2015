package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.MvOrgOfCustomerAllDao;
import com.ebiz.mmt.domain.MvOrgOfCustomerAll;
import com.ebiz.mmt.service.MvOrgOfCustomerAllService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2015-05-25 14:26:30
 */
@Service
public class MvOrgOfCustomerAllServiceImpl implements MvOrgOfCustomerAllService {

	@Resource
	private MvOrgOfCustomerAllDao mvOrgOfCustomerAllDao;
	

	public Long createMvOrgOfCustomerAll(MvOrgOfCustomerAll t) {
		return this.mvOrgOfCustomerAllDao.insertEntity(t);
	}

	public MvOrgOfCustomerAll getMvOrgOfCustomerAll(MvOrgOfCustomerAll t) {
		return this.mvOrgOfCustomerAllDao.selectEntity(t);
	}

	public Long getMvOrgOfCustomerAllCount(MvOrgOfCustomerAll t) {
		return this.mvOrgOfCustomerAllDao.selectEntityCount(t);
	}

	public List<MvOrgOfCustomerAll> getMvOrgOfCustomerAllList(MvOrgOfCustomerAll t) {
		return this.mvOrgOfCustomerAllDao.selectEntityList(t);
	}

	public int modifyMvOrgOfCustomerAll(MvOrgOfCustomerAll t) {
		return this.mvOrgOfCustomerAllDao.updateEntity(t);
	}

	public int removeMvOrgOfCustomerAll(MvOrgOfCustomerAll t) {
		return this.mvOrgOfCustomerAllDao.deleteEntity(t);
	}

	public List<MvOrgOfCustomerAll> getMvOrgOfCustomerAllPaginatedList(MvOrgOfCustomerAll t) {
		return this.mvOrgOfCustomerAllDao.selectEntityPaginatedList(t);
	}

	@Override
	public int updateCustomerInfoAll(MvOrgOfCustomerAll t) {
		return this.mvOrgOfCustomerAllDao.updateMvOrgOfCustomerAllByUserId(t);
	}

}
