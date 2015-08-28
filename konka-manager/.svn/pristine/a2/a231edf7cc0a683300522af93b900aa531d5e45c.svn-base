package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.EcSpecFbUgdetailDao;
import com.ebiz.mmt.domain.EcSpecFbUgdetail;
import com.ebiz.mmt.service.EcSpecFbUgdetailService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-06-08 10:31:47
 */
@Service
public class EcSpecFbUgdetailServiceImpl implements EcSpecFbUgdetailService {

	@Resource
	private EcSpecFbUgdetailDao ecSpecFbUgdetailDao;

	public Long createEcSpecFbUgdetail(EcSpecFbUgdetail t) {
		return this.ecSpecFbUgdetailDao.insertEntity(t);
	}

	public EcSpecFbUgdetail getEcSpecFbUgdetail(EcSpecFbUgdetail t) {
		return this.ecSpecFbUgdetailDao.selectEntity(t);
	}

	public Long getEcSpecFbUgdetailCount(EcSpecFbUgdetail t) {
		return this.ecSpecFbUgdetailDao.selectEntityCount(t);
	}

	public List<EcSpecFbUgdetail> getEcSpecFbUgdetailList(EcSpecFbUgdetail t) {
		return this.ecSpecFbUgdetailDao.selectEntityList(t);
	}

	public int modifyEcSpecFbUgdetail(EcSpecFbUgdetail t) {
		return this.ecSpecFbUgdetailDao.updateEntity(t);
	}

	public int removeEcSpecFbUgdetail(EcSpecFbUgdetail t) {
		return this.ecSpecFbUgdetailDao.deleteEntity(t);
	}

	public List<EcSpecFbUgdetail> getEcSpecFbUgdetailPaginatedList(EcSpecFbUgdetail t) {
		return this.ecSpecFbUgdetailDao.selectEntityPaginatedList(t);
	}

	public List<EcSpecFbUgdetail> getEcSpecFbUgdetailForTjList(EcSpecFbUgdetail t) {
		return this.ecSpecFbUgdetailDao.selectEcSpecFbUgdetailForTjList(t);
	}

	public List<EcSpecFbUgdetail> getEcSpecFbUgdetailForJlList(EcSpecFbUgdetail t) {
		return this.ecSpecFbUgdetailDao.selectEcSpecFbUgdetailForJlList(t);
	}

	/**
	 * 连表查询list
	 */
	@Override
	public List<EcSpecFbUgdetail> getEcSpecFbUgdetailForExecList(EcSpecFbUgdetail t) {
		return this.ecSpecFbUgdetailDao.selectEcSpecFbUgdetailForExecList(t);
	}

	public Long getEcSpecFbUgdetailForTjCount(EcSpecFbUgdetail t) {
		return this.ecSpecFbUgdetailDao.selectEcSpecFbUgdetailForTjCount(t);
	}

}
