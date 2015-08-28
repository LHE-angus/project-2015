package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaMobileUserGpsDao;
import com.ebiz.mmt.domain.KonkaMobileUserGps;
import com.ebiz.mmt.service.KonkaMobileUserGpsService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-07-05 15:52:02
 */
@Service
public class KonkaMobileUserGpsServiceImpl implements KonkaMobileUserGpsService {

	@Resource
	private KonkaMobileUserGpsDao konkaMobileUserGpsDao;

	public Long createKonkaMobileUserGps(KonkaMobileUserGps t) {
		return this.konkaMobileUserGpsDao.insertEntity(t);
	}

	public KonkaMobileUserGps getKonkaMobileUserGps(KonkaMobileUserGps t) {
		return this.konkaMobileUserGpsDao.selectEntity(t);
	}

	public Long getKonkaMobileUserGpsCount(KonkaMobileUserGps t) {
		return this.konkaMobileUserGpsDao.selectEntityCount(t);
	}

	public List<KonkaMobileUserGps> getKonkaMobileUserGpsList(KonkaMobileUserGps t) {
		return this.konkaMobileUserGpsDao.selectEntityList(t);
	}

	public int modifyKonkaMobileUserGps(KonkaMobileUserGps t) {
		return this.konkaMobileUserGpsDao.updateEntity(t);
	}

	public int removeKonkaMobileUserGps(KonkaMobileUserGps t) {
		return this.konkaMobileUserGpsDao.deleteEntity(t);
	}

	public List<KonkaMobileUserGps> getKonkaMobileUserGpsPaginatedList(KonkaMobileUserGps t) {
		return this.konkaMobileUserGpsDao.selectEntityPaginatedList(t);
	}

	/**
	 * @author Wu,ShangLong
	 * @version 2013-07-08
	 */
	public Long getKonkaMobileUserGpsAndYwyCount(KonkaMobileUserGps t) {
		return this.konkaMobileUserGpsDao.selectKonkaMobileUserGpsAndYwyCount(t);
	}

	public List<KonkaMobileUserGps> getKonkaMobileUserGpsAndYwyPaginatedList(KonkaMobileUserGps t) {
		return this.konkaMobileUserGpsDao.selectKonkaMobileUserGpsAndYwyPaginatedList(t);
	}

}
