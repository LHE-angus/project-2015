package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaMobileSailDataBillSwDao;
import com.ebiz.mmt.domain.KonkaMobileSailDataBillSw;
import com.ebiz.mmt.service.KonkaMobileSailDataBillSwService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-09-18 10:36:59
 */
@Service
public class KonkaMobileSailDataBillSwServiceImpl implements KonkaMobileSailDataBillSwService {

	@Resource
	private KonkaMobileSailDataBillSwDao konkaMobileSailDataBillSwDao;

	public Long createKonkaMobileSailDataBillSw(KonkaMobileSailDataBillSw t) {
		return this.konkaMobileSailDataBillSwDao.insertEntity(t);
	}

	public KonkaMobileSailDataBillSw getKonkaMobileSailDataBillSw(KonkaMobileSailDataBillSw t) {
		return this.konkaMobileSailDataBillSwDao.selectEntity(t);
	}

	public Long getKonkaMobileSailDataBillSwCount(KonkaMobileSailDataBillSw t) {
		return this.konkaMobileSailDataBillSwDao.selectEntityCount(t);
	}

	public List<KonkaMobileSailDataBillSw> getKonkaMobileSailDataBillSwList(KonkaMobileSailDataBillSw t) {
		return this.konkaMobileSailDataBillSwDao.selectEntityList(t);
	}

	public int modifyKonkaMobileSailDataBillSw(KonkaMobileSailDataBillSw t) {
		return this.konkaMobileSailDataBillSwDao.updateEntity(t);
	}

	public int removeKonkaMobileSailDataBillSw(KonkaMobileSailDataBillSw t) {
		return this.konkaMobileSailDataBillSwDao.deleteEntity(t);
	}

	public List<KonkaMobileSailDataBillSw> getKonkaMobileSailDataBillSwPaginatedList(KonkaMobileSailDataBillSw t) {
		return this.konkaMobileSailDataBillSwDao.selectEntityPaginatedList(t);
	}

	@Override
	public Long getKonkaMobileSailDataBillSwAndBillCount(KonkaMobileSailDataBillSw entity) {
		
		return this.konkaMobileSailDataBillSwDao.selectKonkaMobileSailDataBillSwAndBillCount(entity);
	}

	@Override
	public List<KonkaMobileSailDataBillSw> getKonkaMobileSailDataBillSwAndBillPaginatedList(
			KonkaMobileSailDataBillSw entity) {
		
		return this.konkaMobileSailDataBillSwDao.selectKonkaMobileSailDataBillSwAndBillPaginatedList(entity);
	}

	//已转订单查询分页计数
	@Override
	public Long getKonkaMobileSailDataBillSwAndBillForSwitchedCount(
			KonkaMobileSailDataBillSw entity) {
		
		return this.konkaMobileSailDataBillSwDao.selectKonkaMobileSailDataBillSwAndBillForSwitchedCount(entity);
	}
	//已转订单查询PageList
	@Override
	public List<KonkaMobileSailDataBillSw> getKonkaMobileSailDataBillSwAndBillForSwitchedPaginatedList(
			KonkaMobileSailDataBillSw entity) {
		
		return this.konkaMobileSailDataBillSwDao.selectKonkaMobileSailDataBillSwAndBillForSwitchedPaginatedList(entity);
	}

}
