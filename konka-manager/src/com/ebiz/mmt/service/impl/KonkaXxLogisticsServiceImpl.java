package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaXxLogisticsDao;
import com.ebiz.mmt.domain.KonkaXxLogistics;
import com.ebiz.mmt.service.KonkaXxLogisticsService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2012-03-31 14:24:51
 */
@Service
public class KonkaXxLogisticsServiceImpl implements KonkaXxLogisticsService {

	@Resource
	private KonkaXxLogisticsDao konkaXxLogisticsDao;

	public Long createKonkaXxLogistics(KonkaXxLogistics t) {
		return this.konkaXxLogisticsDao.insertEntity(t);
	}

	public KonkaXxLogistics getKonkaXxLogistics(KonkaXxLogistics t) {
		return this.konkaXxLogisticsDao.selectEntity(t);
	}

	public Long getKonkaXxLogisticsCount(KonkaXxLogistics t) {
		return this.konkaXxLogisticsDao.selectEntityCount(t);
	}

	public List<KonkaXxLogistics> getKonkaXxLogisticsList(KonkaXxLogistics t) {
		return this.konkaXxLogisticsDao.selectEntityList(t);
	}

	public int modifyKonkaXxLogistics(KonkaXxLogistics t) {
		return this.konkaXxLogisticsDao.updateEntity(t);
	}

	public int removeKonkaXxLogistics(KonkaXxLogistics t) {
		return this.konkaXxLogisticsDao.deleteEntity(t);
	}

	public List<KonkaXxLogistics> getKonkaXxLogisticsPaginatedList(KonkaXxLogistics t) {
		return this.konkaXxLogisticsDao.selectEntityPaginatedList(t);
	}

	/**
	 * @author Wu,ShangLong
	 * @version 2012-04-06
	 */
	public Long getKonkaXxLogisticsWithPNameCount(KonkaXxLogistics t) {
		return this.konkaXxLogisticsDao.selectKonkaXxLogisticsWithPNameCount(t);
	}

	/**
	 * @author Wu,ShangLong
	 * @version 2012-04-06
	 */
	public List<KonkaXxLogistics> getKonkaXxLogisticsWithPNamePaginatedList(KonkaXxLogistics t) {
		return this.konkaXxLogisticsDao.selectKonkaXxLogisticsWithPNamePaginatedList(t);
	}

}
