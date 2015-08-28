package com.ebiz.mmt.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.VADetailsOfSalesDataDao;
import com.ebiz.mmt.domain.VADetailsOfSalesData;
import com.ebiz.mmt.service.VADetailsOfSalesDataService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-09-08 12:53:15
 */
@Service
public class VADetailsOfSalesDataServiceImpl implements VADetailsOfSalesDataService {

	@Resource
	private VADetailsOfSalesDataDao vADetailsOfSalesDataDao;

	public Long createVADetailsOfSalesData(VADetailsOfSalesData t) {
		return this.vADetailsOfSalesDataDao.insertEntity(t);
	}

	public VADetailsOfSalesData getVADetailsOfSalesData(VADetailsOfSalesData t) {
		return this.vADetailsOfSalesDataDao.selectEntity(t);
	}

	public Long getVADetailsOfSalesDataCount(VADetailsOfSalesData t) {
		return this.vADetailsOfSalesDataDao.selectEntityCount(t);
	}

	public List<VADetailsOfSalesData> getVADetailsOfSalesDataList(VADetailsOfSalesData t) {
		return this.vADetailsOfSalesDataDao.selectEntityList(t);
	}

	public int modifyVADetailsOfSalesData(VADetailsOfSalesData t) {
		return this.vADetailsOfSalesDataDao.updateEntity(t);
	}

	public int removeVADetailsOfSalesData(VADetailsOfSalesData t) {
		return this.vADetailsOfSalesDataDao.deleteEntity(t);
	}

	public List<VADetailsOfSalesData> getVADetailsOfSalesDataPaginatedList(VADetailsOfSalesData t) {
		return this.vADetailsOfSalesDataDao.selectEntityPaginatedList(t);
	}

	public List<VADetailsOfSalesData> getVADetailsOfSalesDataListForFX(VADetailsOfSalesData t) {
		return this.vADetailsOfSalesDataDao.selectVADetailsOfSalesDataListForFX(t);
	}
	
	public List<HashMap> getVADetailsOfSalesDataListForFXNew(VADetailsOfSalesData t) {
		return this.vADetailsOfSalesDataDao.selectVADetailsOfSalesDataListForFXNew(t);
	}

	@Override
	public List<HashMap> getKonkaMobileSailDataForSum(VADetailsOfSalesData t) {
		return this.vADetailsOfSalesDataDao.selectKonkaMobileSailDataForSum(t);
	}

	@Override
	public List<HashMap> getVADetailsOfSalesDataListForMap(
			VADetailsOfSalesData t) {
		return this.vADetailsOfSalesDataDao.selectVADetailsOfSalesDataListForMap(t);
	}
	
	@Override
	public List<HashMap> getCustomModelList(VADetailsOfSalesData t) {
		return this.vADetailsOfSalesDataDao.selectCustomModelList(t);
	}

	@Override
	public List<HashMap> getModelInDetailsList(VADetailsOfSalesData t) {
		return this.vADetailsOfSalesDataDao.selectModelInDetailsList(t);
	}
	
	@Override
	public List<HashMap> getModelOutDetailsList(VADetailsOfSalesData t) {
		return this.vADetailsOfSalesDataDao.selectModelOutDetailsList(t);
	}
	
	
	@Override
	public List<HashMap> getSalesDataOfDeptListForMap(VADetailsOfSalesData t) {
		return this.vADetailsOfSalesDataDao.selectSalesDataOfDeptListForMap(t);
	}

	@Override
	public List<HashMap> getSalesDataOfChannelListForMap(VADetailsOfSalesData t) {
		return this.vADetailsOfSalesDataDao.selectSalesDataOfChannelListForMap(t);
	}

	@Override
	public List<HashMap> getChannelDetailsList(VADetailsOfSalesData t) {
		return this.vADetailsOfSalesDataDao.selectChannelDetailsList(t);
	}

}
