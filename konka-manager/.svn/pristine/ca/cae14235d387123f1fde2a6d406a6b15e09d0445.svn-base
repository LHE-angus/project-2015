package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.EcBaseCardNoDao;
import com.ebiz.mmt.dao.SfPindexDao;
import com.ebiz.mmt.domain.EcBaseCardNo;
import com.ebiz.mmt.domain.SfPindex;
import com.ebiz.mmt.service.EcBaseCardNoService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-11-14 15:05:08
 */
@Service
public class EcBaseCardNoServiceImpl implements EcBaseCardNoService {

	@Resource
	private EcBaseCardNoDao ecBaseCardNoDao;

	@Resource
	private SfPindexDao sfPindexDao;

	public Long createEcBaseCardNo(EcBaseCardNo t) {
		return this.ecBaseCardNoDao.insertEntity(t);
	}

	public EcBaseCardNo getEcBaseCardNo(EcBaseCardNo t) {
		return this.ecBaseCardNoDao.selectEntity(t);
	}

	public Long getEcBaseCardNoCount(EcBaseCardNo t) {
		return this.ecBaseCardNoDao.selectEntityCount(t);
	}

	public List<EcBaseCardNo> getEcBaseCardNoList(EcBaseCardNo t) {
		return this.ecBaseCardNoDao.selectEntityList(t);
	}

	public int modifyEcBaseCardNo(EcBaseCardNo t) {
		return this.ecBaseCardNoDao.updateEntity(t);
	}

	public int removeEcBaseCardNo(EcBaseCardNo t) {
		return this.ecBaseCardNoDao.deleteEntity(t);
	}

	public List<EcBaseCardNo> getEcBaseCardNoPaginatedList(EcBaseCardNo t) {
		return this.ecBaseCardNoDao.selectEntityPaginatedList(t);
	}

	public String createEcBaseCardNo(List<EcBaseCardNo> list) {
		return this.ecBaseCardNoDao.insertEcBaseCardNo(list);
	}

	public int modifyEcBaseCardNoBYCardNo(EcBaseCardNo t) {
		return this.ecBaseCardNoDao.updateEcBaseCardNoBYCardNo(t);
	}

	public String createSfPindex(List<SfPindex> list) {
		return this.sfPindexDao.insertSfPindexList(list);
	}
}
