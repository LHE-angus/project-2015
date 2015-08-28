package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.EcBaseExpressReachDayDao;
import com.ebiz.mmt.domain.EcBaseExpressReachDay;
import com.ebiz.mmt.service.EcBaseExpressReachDayService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-09-23 19:18:37
 */
@Service
public class EcBaseExpressReachDayServiceImpl implements EcBaseExpressReachDayService {

	@Resource
	private EcBaseExpressReachDayDao ecBaseExpressReachDayDao;

	public Long createEcBaseExpressReachDay(EcBaseExpressReachDay t) {
		return this.ecBaseExpressReachDayDao.insertEntity(t);
	}

	public EcBaseExpressReachDay getEcBaseExpressReachDay(EcBaseExpressReachDay t) {
		return this.ecBaseExpressReachDayDao.selectEntity(t);
	}

	public Long getEcBaseExpressReachDayCount(EcBaseExpressReachDay t) {
		return this.ecBaseExpressReachDayDao.selectEntityCount(t);
	}

	public List<EcBaseExpressReachDay> getEcBaseExpressReachDayList(EcBaseExpressReachDay t) {
		return this.ecBaseExpressReachDayDao.selectEntityList(t);
	}

	public int modifyEcBaseExpressReachDay(EcBaseExpressReachDay t) {
		return this.ecBaseExpressReachDayDao.updateEntity(t);
	}

	public int removeEcBaseExpressReachDay(EcBaseExpressReachDay t) {
		return this.ecBaseExpressReachDayDao.deleteEntity(t);
	}

	public List<EcBaseExpressReachDay> getEcBaseExpressReachDayPaginatedList(EcBaseExpressReachDay t) {
		return this.ecBaseExpressReachDayDao.selectEntityPaginatedList(t);
	}

	public Long getEcBaseExpressReachDayForStoreNameAndFullNameCount(EcBaseExpressReachDay t) {
		return this.ecBaseExpressReachDayDao.selectEcBaseExpressReachDayForStoreNameAndFullNameCount(t);
	}

	public List<EcBaseExpressReachDay> getEcBaseExpressReachDayForStoreNameAndFullNamePaginatedList(
	        EcBaseExpressReachDay t) {
		return this.ecBaseExpressReachDayDao.selectEcBaseExpressReachDayForStoreNameAndFullNamePaginatedList(t);
	}

}
