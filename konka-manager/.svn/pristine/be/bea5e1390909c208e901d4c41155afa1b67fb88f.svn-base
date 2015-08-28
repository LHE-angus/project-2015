package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.SfhkRelEppOrderDao;
import com.ebiz.mmt.domain.SfhkRelEppOrder;
import com.ebiz.mmt.service.SfhkRelEppOrderService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-10-23 14:51:56
 */
@Service
public class SfhkRelEppOrderServiceImpl implements SfhkRelEppOrderService {

	@Resource
	private SfhkRelEppOrderDao sfhkRelEppOrderDao;

	public Long createSfhkRelEppOrder(SfhkRelEppOrder t) {
		return this.sfhkRelEppOrderDao.insertEntity(t);
	}

	public SfhkRelEppOrder getSfhkRelEppOrder(SfhkRelEppOrder t) {
		return this.sfhkRelEppOrderDao.selectEntity(t);
	}

	public Long getSfhkRelEppOrderCount(SfhkRelEppOrder t) {
		return this.sfhkRelEppOrderDao.selectEntityCount(t);
	}

	public List<SfhkRelEppOrder> getSfhkRelEppOrderList(SfhkRelEppOrder t) {
		return this.sfhkRelEppOrderDao.selectEntityList(t);
	}

	public int modifySfhkRelEppOrder(SfhkRelEppOrder t) {
		return this.sfhkRelEppOrderDao.updateEntity(t);
	}

	public int removeSfhkRelEppOrder(SfhkRelEppOrder t) {
		return this.sfhkRelEppOrderDao.deleteEntity(t);
	}

	public List<SfhkRelEppOrder> getSfhkRelEppOrderPaginatedList(SfhkRelEppOrder t) {
		return this.sfhkRelEppOrderDao.selectEntityPaginatedList(t);
	}

	@Override
	public List<SfhkRelEppOrder> getSfhkRelEppOrderAndOrderIdList(SfhkRelEppOrder t) {
		return this.sfhkRelEppOrderDao.selectSfhkRelEppOrderAndOrderIdList(t);
	}

}
