package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaZles23ResultInfoDao;
import com.ebiz.mmt.domain.KonkaZles23ResultInfo;
import com.ebiz.mmt.service.KonkaZles23ResultInfoService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-03-25 15:50:31
 */
@Service
public class KonkaZles23ResultInfoServiceImpl implements KonkaZles23ResultInfoService {

	@Resource
	private KonkaZles23ResultInfoDao konkaZles23ResultInfoDao;

	@Override
	public Long createKonkaZles23ResultInfo(KonkaZles23ResultInfo t) {
		return this.konkaZles23ResultInfoDao.insertEntity(t);
	}

	@Override
	public KonkaZles23ResultInfo getKonkaZles23ResultInfo(KonkaZles23ResultInfo t) {
		return this.konkaZles23ResultInfoDao.selectEntity(t);
	}

	@Override
	public Long getKonkaZles23ResultInfoCount(KonkaZles23ResultInfo t) {
		return this.konkaZles23ResultInfoDao.selectEntityCount(t);
	}

	@Override
	public List<KonkaZles23ResultInfo> getKonkaZles23ResultInfoList(KonkaZles23ResultInfo t) {
		return this.konkaZles23ResultInfoDao.selectEntityList(t);
	}

	@Override
	public int modifyKonkaZles23ResultInfo(KonkaZles23ResultInfo t) {
		return this.konkaZles23ResultInfoDao.updateEntity(t);
	}

	@Override
	public int removeKonkaZles23ResultInfo(KonkaZles23ResultInfo t) {
		return this.konkaZles23ResultInfoDao.deleteEntity(t);
	}

	@Override
	public List<KonkaZles23ResultInfo> getKonkaZles23ResultInfoPaginatedList(KonkaZles23ResultInfo t) {
		return this.konkaZles23ResultInfoDao.selectEntityPaginatedList(t);
	}

	@Override
	public Long getKonkaZles23ResultInfoForCustomerCount(KonkaZles23ResultInfo t) {
		return this.konkaZles23ResultInfoDao.selectKonkaZles23ResultInfoForCustomerCount(t);
	}

	@Override
	public List<KonkaZles23ResultInfo> getKonkaZles23ResultInfoForCustomerPaginatedList(KonkaZles23ResultInfo t) {
		return this.konkaZles23ResultInfoDao.selectKonkaZles23ResultInfoForCustomerPaginatedList(t);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ebiz.mmt.service.KonkaZles23ResultInfoService#
	 * getKonkaZles23ResultInfoWithEbelnList()
	 */
	@Override
	public List<KonkaZles23ResultInfo> getKonkaZles23ResultInfoWithEbelnList() {
		return this.konkaZles23ResultInfoDao.selectKonkaZles23ResultInfoListWithEbeln();
	}

	
	/*
	 * (non-Javadoc)
	 * @see com.ebiz.mmt.service.KonkaZles23ResultInfoService#
	 * getKonkaZles23ResultInfoForShockInList(com.ebiz.mmt.domain.KonkaZles23ResultInfo)
	 */
	
	@Override
	public List<KonkaZles23ResultInfo> getKonkaZles23ResultInfoForShockInList(
			KonkaZles23ResultInfo resultInfo) {
		// TODO Auto-generated method stub
		return this.konkaZles23ResultInfoDao.selectKonkaZles23ResultInfoForShockInList(resultInfo);
	}

}
