package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.EcRuleBingdingDetailDao;
import com.ebiz.mmt.domain.EcRuleBingdingDetail;
import com.ebiz.mmt.service.EcRuleBingdingDetailService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-08-08 15:20:55
 */
@Service
public class EcRuleBingdingDetailServiceImpl implements EcRuleBingdingDetailService {

	@Resource
	private EcRuleBingdingDetailDao ecRuleBingdingDetailDao;
	

	public Long createEcRuleBingdingDetail(EcRuleBingdingDetail t) {
		return this.ecRuleBingdingDetailDao.insertEntity(t);
	}

	public EcRuleBingdingDetail getEcRuleBingdingDetail(EcRuleBingdingDetail t) {
		return this.ecRuleBingdingDetailDao.selectEntity(t);
	}

	public Long getEcRuleBingdingDetailCount(EcRuleBingdingDetail t) {
		return this.ecRuleBingdingDetailDao.selectEntityCount(t);
	}

	public List<EcRuleBingdingDetail> getEcRuleBingdingDetailList(EcRuleBingdingDetail t) {
		return this.ecRuleBingdingDetailDao.selectEntityList(t);
	}

	public int modifyEcRuleBingdingDetail(EcRuleBingdingDetail t) {
		return this.ecRuleBingdingDetailDao.updateEntity(t);
	}

	public int removeEcRuleBingdingDetail(EcRuleBingdingDetail t) {
		return this.ecRuleBingdingDetailDao.deleteEntity(t);
	}

	public List<EcRuleBingdingDetail> getEcRuleBingdingDetailPaginatedList(EcRuleBingdingDetail t) {
		return this.ecRuleBingdingDetailDao.selectEntityPaginatedList(t);
	}

}
