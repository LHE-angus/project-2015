package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.ToDrpOrderDetailDao;
import com.ebiz.mmt.domain.ToDrpOrderDetail;
import com.ebiz.mmt.service.ToDrpOrderDetailService;


@Service
public class ToDrpOrderDetailServiceImpl implements ToDrpOrderDetailService {

	@Resource
	private ToDrpOrderDetailDao toDrpOrderDetailDao;
	

	public Long createToDrpOrderDetail(ToDrpOrderDetail t) {
		return this.toDrpOrderDetailDao.insertEntity(t);
	}

	public ToDrpOrderDetail getToDrpOrderDetail(ToDrpOrderDetail t) {
		return this.toDrpOrderDetailDao.selectEntity(t);
	}

	public Long getToDrpOrderDetailCount(ToDrpOrderDetail t) {
		return this.toDrpOrderDetailDao.selectEntityCount(t);
	}

	public List<ToDrpOrderDetail> getToDrpOrderDetailList(ToDrpOrderDetail t) {
		return this.toDrpOrderDetailDao.selectEntityList(t);
	}

	public int modifyToDrpOrderDetail(ToDrpOrderDetail t) {
		return this.toDrpOrderDetailDao.updateEntity(t);
	}

	public int removeToDrpOrderDetail(ToDrpOrderDetail t) {
		return this.toDrpOrderDetailDao.deleteEntity(t);
	}

	public List<ToDrpOrderDetail> getToDrpOrderDetailPaginatedList(ToDrpOrderDetail t) {
		return this.toDrpOrderDetailDao.selectEntityPaginatedList(t);
	}


}
