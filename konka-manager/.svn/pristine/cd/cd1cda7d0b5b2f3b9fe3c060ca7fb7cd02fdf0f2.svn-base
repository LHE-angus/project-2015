package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.EcBindingPdOrdeDetailsDao;
import com.ebiz.mmt.domain.EcBindingPdOrdeDetails;
import com.ebiz.mmt.service.EcBindingPdOrdeDetailsService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-09-10 11:43:03
 */
@Service
public class EcBindingPdOrdeDetailsServiceImpl implements EcBindingPdOrdeDetailsService {

	@Resource
	private EcBindingPdOrdeDetailsDao ecBindingPdOrdeDetailsDao;
	

	public Long createEcBindingPdOrdeDetails(EcBindingPdOrdeDetails t) {
		return this.ecBindingPdOrdeDetailsDao.insertEntity(t);
	}

	public EcBindingPdOrdeDetails getEcBindingPdOrdeDetails(EcBindingPdOrdeDetails t) {
		return this.ecBindingPdOrdeDetailsDao.selectEntity(t);
	}

	public Long getEcBindingPdOrdeDetailsCount(EcBindingPdOrdeDetails t) {
		return this.ecBindingPdOrdeDetailsDao.selectEntityCount(t);
	}

	public List<EcBindingPdOrdeDetails> getEcBindingPdOrdeDetailsList(EcBindingPdOrdeDetails t) {
		return this.ecBindingPdOrdeDetailsDao.selectEntityList(t);
	}

	public int modifyEcBindingPdOrdeDetails(EcBindingPdOrdeDetails t) {
		return this.ecBindingPdOrdeDetailsDao.updateEntity(t);
	}

	public int removeEcBindingPdOrdeDetails(EcBindingPdOrdeDetails t) {
		return this.ecBindingPdOrdeDetailsDao.deleteEntity(t);
	}

	public List<EcBindingPdOrdeDetails> getEcBindingPdOrdeDetailsPaginatedList(EcBindingPdOrdeDetails t) {
		return this.ecBindingPdOrdeDetailsDao.selectEntityPaginatedList(t);
	}

}
