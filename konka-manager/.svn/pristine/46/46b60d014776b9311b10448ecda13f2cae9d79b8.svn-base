package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaXxSellBillCstSatformDao;
import com.ebiz.mmt.dao.KonkaXxSellBillDao;
import com.ebiz.mmt.domain.KonkaXxSellBill;
import com.ebiz.mmt.domain.KonkaXxSellBillCstSatform;
import com.ebiz.mmt.service.KonkaXxSellBillCstSatformService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2012-04-10 12:30:55
 */
@Service
public class KonkaXxSellBillCstSatformServiceImpl implements KonkaXxSellBillCstSatformService {

	@Resource
	private KonkaXxSellBillCstSatformDao konkaXxSellBillCstSatformDao;
	
	@Resource
	private KonkaXxSellBillDao konkaXxSellBillDao;
	

	public Long createKonkaXxSellBillCstSatform(KonkaXxSellBillCstSatform t) {
		return this.konkaXxSellBillCstSatformDao.insertEntity(t);
	}

	public KonkaXxSellBillCstSatform getKonkaXxSellBillCstSatform(KonkaXxSellBillCstSatform t) {
		return this.konkaXxSellBillCstSatformDao.selectEntity(t);
	}

	public Long getKonkaXxSellBillCstSatformCount(KonkaXxSellBillCstSatform t) {
		return this.konkaXxSellBillCstSatformDao.selectEntityCount(t);
	}

	public List<KonkaXxSellBillCstSatform> getKonkaXxSellBillCstSatformList(KonkaXxSellBillCstSatform t) {
		return this.konkaXxSellBillCstSatformDao.selectEntityList(t);
	}

	public int modifyKonkaXxSellBillCstSatform(KonkaXxSellBillCstSatform t) {
		return this.konkaXxSellBillCstSatformDao.updateEntity(t);
	}

	public int removeKonkaXxSellBillCstSatform(KonkaXxSellBillCstSatform t) {
		return this.konkaXxSellBillCstSatformDao.deleteEntity(t);
	}

	public List<KonkaXxSellBillCstSatform> getKonkaXxSellBillCstSatformPaginatedList(KonkaXxSellBillCstSatform t) {
		return this.konkaXxSellBillCstSatformDao.selectEntityPaginatedList(t);
	}

	/**
	 * @author Jiang,JiaYong
	 * @version 2012-04-13
	 */
	public Long createKonkaXxSellBillCstSatformListAndSellBill(List<KonkaXxSellBillCstSatform> konkaXxSellBillCstSatformList, KonkaXxSellBill konkaXxSellBill) {
		for (KonkaXxSellBillCstSatform konkaXxSellBillCstSatform : konkaXxSellBillCstSatformList) {
			KonkaXxSellBillCstSatform t = new KonkaXxSellBillCstSatform();
			t.setSell_bill_id(konkaXxSellBillCstSatform.getSell_bill_id());
			t.setMd_name(konkaXxSellBillCstSatform.getMd_name());
			t = this.konkaXxSellBillCstSatformDao.selectKonkaXxSellBillCstSatformBySellBillIdAndMdName(t);
			if (null == t){
				this.konkaXxSellBillCstSatformDao.insertEntity(konkaXxSellBillCstSatform);
			} else {
				konkaXxSellBillCstSatform.setId(t.getId());
				this.konkaXxSellBillCstSatformDao.updateEntity(konkaXxSellBillCstSatform);
			}
		}
		return (long) this.konkaXxSellBillDao.updateEntity(konkaXxSellBill);
	}

}
