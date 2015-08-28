package com.ebiz.mmt.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaOrderInfoTransDao;
import com.ebiz.mmt.dao.KonkaOrderInfoTransDetailsDao;
import com.ebiz.mmt.domain.KonkaOrderInfoTrans;
import com.ebiz.mmt.domain.KonkaOrderInfoTransDetails;
import com.ebiz.mmt.service.KonkaOrderInfoTransService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-05-22 15:41:39
 */
@Service
public class KonkaOrderInfoTransServiceImpl implements KonkaOrderInfoTransService {

	@Resource
	private KonkaOrderInfoTransDao konkaOrderInfoTransDao;
	@Resource
	private KonkaOrderInfoTransDetailsDao konkaOrderInfoTransDetailsDao;

	public Long createKonkaOrderInfoTrans(KonkaOrderInfoTrans t) {
		return this.konkaOrderInfoTransDao.insertEntity(t);
	}

	public KonkaOrderInfoTrans getKonkaOrderInfoTrans(KonkaOrderInfoTrans t) {
		return this.konkaOrderInfoTransDao.selectEntity(t);
	}

	public Long getKonkaOrderInfoTransCount(KonkaOrderInfoTrans t) {
		return this.konkaOrderInfoTransDao.selectEntityCount(t);
	}

	public List<KonkaOrderInfoTrans> getKonkaOrderInfoTransList(KonkaOrderInfoTrans t) {
		return this.konkaOrderInfoTransDao.selectEntityList(t);
	}

	public int modifyKonkaOrderInfoTrans(KonkaOrderInfoTrans t) {
		return this.konkaOrderInfoTransDao.updateEntity(t);
	}

	public int removeKonkaOrderInfoTrans(KonkaOrderInfoTrans t) {
		return this.konkaOrderInfoTransDao.deleteEntity(t);
	}

	public List<KonkaOrderInfoTrans> getKonkaOrderInfoTransPaginatedList(KonkaOrderInfoTrans t) {
		return this.konkaOrderInfoTransDao.selectEntityPaginatedList(t);
	}

	public Long getKonkaOrderInfoTransForFHDCount(KonkaOrderInfoTrans t) {
		return this.konkaOrderInfoTransDao.selectKonkaOrderInfoTransForFHDCount(t);
	}

	public List<KonkaOrderInfoTrans> getKonkaOrderInfoTransForFHDPaginatedList(KonkaOrderInfoTrans t) {
		return this.konkaOrderInfoTransDao.selectKonkaOrderInfoTransForFHDPaginatedList(t);
	}

	/**
	 * 插入订单并且插入详情 Wang,KunLin
	 */
	@Override
	public Long createKonkaOrderInfoTransAndDetails(KonkaOrderInfoTrans t) {
		// 插入主表，并拿回id
		Long trans_id = this.konkaOrderInfoTransDao.insertEntity(t);
		// 如果有详情
		List<KonkaOrderInfoTransDetails> KonkaOrderInfoTransDetailsList = t.getKonkaOrderInfoTransDetailsList();
		if (null != KonkaOrderInfoTransDetailsList && KonkaOrderInfoTransDetailsList.size() > 0) {
			for (KonkaOrderInfoTransDetails ks : KonkaOrderInfoTransDetailsList) {
				ks.setTrans_id(trans_id);
				ks.setAdd_date(new Date());
				this.konkaOrderInfoTransDetailsDao.insertEntity(ks);
			}
		}
		return trans_id;
	}

	/**
	 * 修改订单并且修改详情 Wang,KunLin
	 */
	@Override
	public int modifyKonkaOrderInfoTransAndDetails(KonkaOrderInfoTrans t) {
		
		int count = modifyKonkaOrderInfoTrans(t);
		List<KonkaOrderInfoTransDetails> KonkaOrderInfoTransDetailsList = t.getKonkaOrderInfoTransDetailsList();
		if (null != KonkaOrderInfoTransDetailsList && KonkaOrderInfoTransDetailsList.size() > 0) {
			// 先删除
			for (KonkaOrderInfoTransDetails ks : KonkaOrderInfoTransDetailsList) {
				KonkaOrderInfoTransDetails koid = new KonkaOrderInfoTransDetails();
				koid.setEnsu_id(ks.getEnsu_id());
				this.konkaOrderInfoTransDetailsDao.deleteEntity(koid);
			}
			// 再重新添加
			for (KonkaOrderInfoTransDetails ks : KonkaOrderInfoTransDetailsList) {
				ks.setTrans_id(t.getTrans_id());
				ks.setAdd_date(new Date());
				this.konkaOrderInfoTransDetailsDao.insertEntity(ks);
			}
		}
		return count;
	}

	@Override
	public KonkaOrderInfoTrans getKonkaOrderInfoTransForPrint(KonkaOrderInfoTrans t) {
		return this.konkaOrderInfoTransDao.selectKonkaOrderInfoTransForPrint(t);
	}

}
