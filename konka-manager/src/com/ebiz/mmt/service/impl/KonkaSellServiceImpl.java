package com.ebiz.mmt.service.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaSellDao;
import com.ebiz.mmt.dao.KonkaSellDetailsDao;
import com.ebiz.mmt.domain.KonkaSell;
import com.ebiz.mmt.domain.KonkaSellDetails;
import com.ebiz.mmt.service.KonkaSellService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2011-09-01 12:46:44
 */
@Service
public class KonkaSellServiceImpl implements KonkaSellService {

	@Resource
	private KonkaSellDao konkaSellDao;

	@Resource
	private KonkaSellDetailsDao konkaSellDetailsDao;

	public Long createKonkaSell(KonkaSell t) {
		return this.konkaSellDao.insertEntity(t);
	}

	public KonkaSell getKonkaSell(KonkaSell t) {
		return this.konkaSellDao.selectEntity(t);
	}

	public Long getKonkaSellCount(KonkaSell t) {
		return this.konkaSellDao.selectEntityCount(t);
	}

	public List<KonkaSell> getKonkaSellList(KonkaSell t) {
		return this.konkaSellDao.selectEntityList(t);
	}

	public int modifyKonkaSell(KonkaSell t) {
		return this.konkaSellDao.updateEntity(t);
	}

	public int removeKonkaSell(KonkaSell t) {
		return this.konkaSellDao.deleteEntity(t);
	}

	public List<KonkaSell> getKonkaSellPaginatedList(KonkaSell t) {
		return this.konkaSellDao.selectEntityPaginatedList(t);
	}

	/**
	 * @author Jiang,JiaYong
	 * @version 2011-08-25
	 */
	public Long createKonkaSellIncludeDetails(KonkaSell t) {
		Long s_id = this.konkaSellDao.insertEntity(t);

		// 增加前先删除
		KonkaSellDetails konkaSellDetails = new KonkaSellDetails();
		konkaSellDetails.setS_id(s_id);
		this.konkaSellDetailsDao.deleteKonkaSellDetailsWithSID(konkaSellDetails);

		for (KonkaSellDetails kd : t.getKonkaSellDetailsList()) {
			kd.setS_id(s_id);
			this.konkaSellDetailsDao.insertEntity(kd);
		}

		return s_id;
	}

	/**
	 * @author Jiang,JiaYong
	 * @version 2011-08-25
	 */
	public int modifyKonkaSellIncludeDetails(KonkaSell t) {
		// 增加前先删除
		KonkaSellDetails konkaSellDetails = new KonkaSellDetails();
		konkaSellDetails.setS_id(t.getS_id());
		this.konkaSellDetailsDao.deleteKonkaSellDetailsWithSID(konkaSellDetails);

		for (KonkaSellDetails kd : t.getKonkaSellDetailsList()) {
			kd.setS_id(t.getS_id());
			this.konkaSellDetailsDao.insertEntity(kd);
		}

		return this.konkaSellDao.updateEntity(t);
	}
	
	/**
	 * @author Wang,Yang
	 * @version 2011-11-10
	 */
	public Long getKonkaSellCountByPd(KonkaSell t) {
		return this.konkaSellDao.selectKonkaSellCountByPd(t);
	}

	public BigDecimal getKonkaSellCountCostByPd(KonkaSell t) {
		return this.konkaSellDao.selectKonkaSellCountCostByPd(t);
	}

}
