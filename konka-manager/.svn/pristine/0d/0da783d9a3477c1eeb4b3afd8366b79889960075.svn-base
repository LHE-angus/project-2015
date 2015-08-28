package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaSellDetailsDao;
import com.ebiz.mmt.domain.KonkaSellDetails;
import com.ebiz.mmt.service.KonkaSellDetailsService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2011-09-01 12:46:44
 */
@Service
public class KonkaSellDetailsServiceImpl implements KonkaSellDetailsService {

	@Resource
	private KonkaSellDetailsDao konkaSellDetailsDao;

	public Long createKonkaSellDetails(KonkaSellDetails t) {
		return this.konkaSellDetailsDao.insertEntity(t);
	}

	public KonkaSellDetails getKonkaSellDetails(KonkaSellDetails t) {
		return this.konkaSellDetailsDao.selectEntity(t);
	}

	public Long getKonkaSellDetailsCount(KonkaSellDetails t) {
		return this.konkaSellDetailsDao.selectEntityCount(t);
	}

	public List<KonkaSellDetails> getKonkaSellDetailsList(KonkaSellDetails t) {
		return this.konkaSellDetailsDao.selectEntityList(t);
	}

	public int modifyKonkaSellDetails(KonkaSellDetails t) {
		return this.konkaSellDetailsDao.updateEntity(t);
	}

	public int removeKonkaSellDetails(KonkaSellDetails t) {
		return this.konkaSellDetailsDao.deleteEntity(t);
	}

	public List<KonkaSellDetails> getKonkaSellDetailsPaginatedList(KonkaSellDetails t) {
		return this.konkaSellDetailsDao.selectEntityPaginatedList(t);
	}

	/**
	 * @author Jiang,JiaYong
	 * @version 2011-08-25
	 */
	public int removeKonkaSellDetailsWithSID(KonkaSellDetails t) {
		return this.konkaSellDetailsDao.deleteKonkaSellDetailsWithSID(t);
	}

	/**
	 * @author Jiang,JiaYong
	 * @version 2011-08-25
	 */
	public Long getKonkaSellDetailsForStatisticsCount(KonkaSellDetails t) {
		return this.konkaSellDetailsDao.selectKonkaSellDetailsForStatisticsCount(t);
	}

	/**
	 * @author Jiang,JiaYong
	 * @version 2011-08-25
	 */
	public List<KonkaSellDetails> getKonkaSellDetailsForStatisticsPaginatedList(KonkaSellDetails t) {
		return this.konkaSellDetailsDao.selectKonkaSellDetailsForStatisticsPaginatedList(t);
	}

}
