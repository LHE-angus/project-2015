package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.FromDrpOrderDetailDao;
import com.ebiz.mmt.domain.FromDrpOrderDetail;
import com.ebiz.mmt.service.FromDrpOrderDetailService;


@Service
public class FromDrpOrderDetailServiceImpl implements FromDrpOrderDetailService {

	@Resource
	private FromDrpOrderDetailDao fromDrpOrderDetailDao;
	

	public Long createFromDrpOrderDetail(FromDrpOrderDetail t) {
		return this.fromDrpOrderDetailDao.insertEntity(t);
	}

	public FromDrpOrderDetail getFromDrpOrderDetail(FromDrpOrderDetail t) {
		return this.fromDrpOrderDetailDao.selectEntity(t);
	}

	public Long getFromDrpOrderDetailCount(FromDrpOrderDetail t) {
		return this.fromDrpOrderDetailDao.selectEntityCount(t);
	}

	public List<FromDrpOrderDetail> getFromDrpOrderDetailList(FromDrpOrderDetail t) {
		return this.fromDrpOrderDetailDao.selectEntityList(t);
	}

	public int modifyFromDrpOrderDetail(FromDrpOrderDetail t) {
		return this.fromDrpOrderDetailDao.updateEntity(t);
	}

	public int removeFromDrpOrderDetail(FromDrpOrderDetail t) {
		return this.fromDrpOrderDetailDao.deleteEntity(t);
	}

	public List<FromDrpOrderDetail> getFromDrpOrderDetailPaginatedList(FromDrpOrderDetail t) {
		return this.fromDrpOrderDetailDao.selectEntityPaginatedList(t);
	}

    @Override
    public List<FromDrpOrderDetail> getFDrpOrderDetailAndQddata(String ds_order_id) {
        return this.fromDrpOrderDetailDao.selectFDrpOrderDetailAndQddata(ds_order_id);
    }

    //

}
