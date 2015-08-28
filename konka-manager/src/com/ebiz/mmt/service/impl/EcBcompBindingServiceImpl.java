package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.EcBcompBindingDao;
import com.ebiz.mmt.domain.EcBcompBinding;
import com.ebiz.mmt.service.EcBcompBindingService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-09-10 11:43:03
 */
@Service
public class EcBcompBindingServiceImpl implements EcBcompBindingService {

	@Resource
	private EcBcompBindingDao ecBcompBindingDao;
	

	public Long createEcBcompBinding(EcBcompBinding t) {
		return this.ecBcompBindingDao.insertEntity(t);
	}

	public EcBcompBinding getEcBcompBinding(EcBcompBinding t) {
		return this.ecBcompBindingDao.selectEntity(t);
	}

	public Long getEcBcompBindingCount(EcBcompBinding t) {
		return this.ecBcompBindingDao.selectEntityCount(t);
	}

	public List<EcBcompBinding> getEcBcompBindingList(EcBcompBinding t) {
		return this.ecBcompBindingDao.selectEntityList(t);
	}

	public int modifyEcBcompBinding(EcBcompBinding t) {
		return this.ecBcompBindingDao.updateEntity(t);
	}

	public int removeEcBcompBinding(EcBcompBinding t) {
		return this.ecBcompBindingDao.deleteEntity(t);
	}

	public List<EcBcompBinding> getEcBcompBindingPaginatedList(EcBcompBinding t) {
		return this.ecBcompBindingDao.selectEntityPaginatedList(t);
	}

}
