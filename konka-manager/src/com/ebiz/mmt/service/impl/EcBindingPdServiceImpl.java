package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.EcBindingPdDao;
import com.ebiz.mmt.domain.EcBindingPd;
import com.ebiz.mmt.service.EcBindingPdService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-09-10 11:43:03
 */
@Service
public class EcBindingPdServiceImpl implements EcBindingPdService {

	@Resource
	private EcBindingPdDao ecBindingPdDao;
	

	public Long createEcBindingPd(EcBindingPd t) {
		return this.ecBindingPdDao.insertEntity(t);
	}

	public EcBindingPd getEcBindingPd(EcBindingPd t) {
		return this.ecBindingPdDao.selectEntity(t);
	}

	public Long getEcBindingPdCount(EcBindingPd t) {
		return this.ecBindingPdDao.selectEntityCount(t);
	}

	public List<EcBindingPd> getEcBindingPdList(EcBindingPd t) {
		return this.ecBindingPdDao.selectEntityList(t);
	}

	public int modifyEcBindingPd(EcBindingPd t) {
		return this.ecBindingPdDao.updateEntity(t);
	}

	public int removeEcBindingPd(EcBindingPd t) {
		return this.ecBindingPdDao.deleteEntity(t);
	}

	public List<EcBindingPd> getEcBindingPdPaginatedList(EcBindingPd t) {
		return this.ecBindingPdDao.selectEntityPaginatedList(t);
	}

	/**
	 * @author Jiang,JiaYong
	 * @version 2013-09-13
	 */
	public List<EcBindingPd> getEcBindingPdWithGoodsIdAndTypeList(EcBindingPd t) {
		return this.ecBindingPdDao.selectEcBindingPdWithGoodsIdAndTypeList(t);
	}

}
