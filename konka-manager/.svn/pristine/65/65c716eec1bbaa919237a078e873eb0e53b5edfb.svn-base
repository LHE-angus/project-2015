package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaMobileTestDataDao;
import com.ebiz.mmt.domain.KonkaMobileTestData;
import com.ebiz.mmt.service.KonkaMobileTestDataService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-04-22 15:41:58
 */
@Service
public class KonkaMobileTestDataServiceImpl implements KonkaMobileTestDataService {

	@Resource
	private KonkaMobileTestDataDao konkaMobileTestDataDao;

	public Long createKonkaMobileTestData(KonkaMobileTestData t) {
		return this.konkaMobileTestDataDao.insertEntity(t);
	}

	public KonkaMobileTestData getKonkaMobileTestData(KonkaMobileTestData t) {
		return this.konkaMobileTestDataDao.selectEntity(t);
	}

	public Long getKonkaMobileTestDataCount(KonkaMobileTestData t) {
		return this.konkaMobileTestDataDao.selectEntityCount(t);
	}

	public List<KonkaMobileTestData> getKonkaMobileTestDataList(KonkaMobileTestData t) {
		return this.konkaMobileTestDataDao.selectEntityList(t);
	}

	public int modifyKonkaMobileTestData(KonkaMobileTestData t) {
		return this.konkaMobileTestDataDao.updateEntity(t);
	}

	public int removeKonkaMobileTestData(KonkaMobileTestData t) {
		return this.konkaMobileTestDataDao.deleteEntity(t);
	}

	public List<KonkaMobileTestData> getKonkaMobileTestDataPaginatedList(KonkaMobileTestData t) {
		return this.konkaMobileTestDataDao.selectEntityPaginatedList(t);
	}

	public List<KonkaMobileTestData> getKonkaMobileTestDataAndSailDatasPaginatedList(KonkaMobileTestData t) {
		return this.konkaMobileTestDataDao.selectKonkaMobileTestDataAndSailDatasPaginatedList(t);
	}

	public List<KonkaMobileTestData> getKonkaMobileTestDataAndCodeList(KonkaMobileTestData t) {
		return this.konkaMobileTestDataDao.selectKonkaMobileTestDataAndCodeList(t);
	}
	
	public List<KonkaMobileTestData> getselectKonkaMobileTestDataAndCode(KonkaMobileTestData t) {
	return this.konkaMobileTestDataDao.selectKonkaMobileTestDataAndCode(t);
}

}
