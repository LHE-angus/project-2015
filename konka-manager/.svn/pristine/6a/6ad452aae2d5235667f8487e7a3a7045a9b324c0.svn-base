package com.ebiz.mmt.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaJxcFhBillDao;
import com.ebiz.mmt.dao.KonkaJxcFhBillDetailsDao;
import com.ebiz.mmt.dao.KonkaJxcStoreStateDao;
import com.ebiz.mmt.dao.KonkaJxcStoreUpdateRecordDao;
import com.ebiz.mmt.domain.KonkaJxcFhBill;
import com.ebiz.mmt.domain.KonkaJxcFhBillDetails;
import com.ebiz.mmt.domain.KonkaJxcStoreState;
import com.ebiz.mmt.domain.KonkaJxcStoreUpdateRecord;
import com.ebiz.mmt.service.KonkaJxcFhBillDetailsService;
import com.ebiz.mmt.web.Constants;

/**
 * @author Wu,Yang
 * @version 2011-10-11 09:18
 */
@Service
@SuppressWarnings("unchecked")
public class KonkaJxcFhBillDetailsServiceImpl implements KonkaJxcFhBillDetailsService {

	@Resource
	private KonkaJxcFhBillDao konkaJxcFhBillDao;

	@Resource
	private KonkaJxcFhBillDetailsDao konkaJxcFhBillDetailsDao;

	@Resource
	private KonkaJxcStoreStateDao konkaJxcStoreStateDao;

	@Resource
	private KonkaJxcStoreUpdateRecordDao konkaJxcStoreUpdateRecordDao;

	public Long createKonkaJxcFhBillDetails(KonkaJxcFhBillDetails t) {
		return this.konkaJxcFhBillDetailsDao.insertEntity(t);
	}

	public KonkaJxcFhBillDetails getKonkaJxcFhBillDetails(KonkaJxcFhBillDetails t) {
		return this.konkaJxcFhBillDetailsDao.selectEntity(t);
	}

	public Long getKonkaJxcFhBillDetailsCount(KonkaJxcFhBillDetails t) {
		return this.konkaJxcFhBillDetailsDao.selectEntityCount(t);
	}

	public List<KonkaJxcFhBillDetails> getKonkaJxcFhBillDetailsList(KonkaJxcFhBillDetails t) {
		return this.konkaJxcFhBillDetailsDao.selectEntityList(t);
	}

	public int modifyKonkaJxcFhBillDetails(KonkaJxcFhBillDetails t) {
		Long fh_bill_id = Long.valueOf(t.getMap().get("fh_bill_id").toString());
		KonkaJxcFhBill konkaJxcFhBill = new KonkaJxcFhBill();
		if (null != fh_bill_id) {
			konkaJxcFhBill.setId(fh_bill_id);
		}
		konkaJxcFhBill.setUpdate_user_id(t.getUpdate_user_id());// 更新人id
		konkaJxcFhBill.setUpdate_date(t.getUpdate_date());// 更新时间
		// 更新发货数量 = 上次【总的发货数量】+（本次发货明细中的【此次发货数量】-本次发货明细中的【上次发货数量】）
		Long last_fh_sum_count = Long.valueOf(t.getMap().get("last_fh_sum_count").toString());
		Long last_fh_count = Long.valueOf(t.getMap().get("last_fh_count").toString());

		Long this_fh_sum_count = last_fh_sum_count + t.getCount() - last_fh_count;
		konkaJxcFhBill.setFh_sum_count(this_fh_sum_count);

		this.konkaJxcFhBillDao.updateEntity(konkaJxcFhBill);
		// 更新库存状态
		KonkaJxcStoreState konkaJxcStoreState = new KonkaJxcStoreState();
		konkaJxcStoreState.setDept_id(t.getAdd_dept_id());// 该部门id
		konkaJxcStoreState.setStore_id(t.getStore_id());// 仓库id
		konkaJxcStoreState.setPd_type_id(t.getPd_type_id());// 品类
		konkaJxcStoreState.setPd_id(t.getPd_id());// 型号
		konkaJxcStoreState = this.konkaJxcStoreStateDao.selectEntity(konkaJxcStoreState);
		if (null != konkaJxcStoreState) {// update
			// 此时，库存数量计算
			// 原库存数量 + (上一次发货数量 - 此次发货数量)
			Long store_pd_num = konkaJxcStoreState.getPd_num() + last_fh_count - t.getCount();
			konkaJxcStoreState.setPd_num(store_pd_num);// 库存数量
			konkaJxcStoreState.setUpdate_date(t.getUpdate_date());// --
			konkaJxcStoreState.setUpdate_user_id(t.getUpdate_user_id());// --
			this.konkaJxcStoreStateDao.updateEntity(konkaJxcStoreState);
		}

		// 更新库存时，添加库存修改记录
		KonkaJxcStoreUpdateRecord konkaJxcStoreUpdateRecord = new KonkaJxcStoreUpdateRecord();
		konkaJxcStoreUpdateRecord.setUpdate_type(7);// 发货修改
		konkaJxcStoreUpdateRecord.setStore_state_id(konkaJxcStoreState.getId());// 库存状态id
		konkaJxcStoreUpdateRecord.setDept_id(t.getAdd_dept_id());// 部门

		konkaJxcStoreUpdateRecord.setPd_type_id(konkaJxcStoreState.getPd_type_id());// 品类
		konkaJxcStoreUpdateRecord.setPd_type_name(konkaJxcStoreState.getPd_type_name());
		konkaJxcStoreUpdateRecord.setBrand_id(Constants.KONKA_BRAND_ID);
		konkaJxcStoreUpdateRecord.setBrand_name(Constants.KONKA_BRAND_NAME);
		konkaJxcStoreUpdateRecord.setPd_id(konkaJxcStoreState.getPd_id());// 型号
		konkaJxcStoreUpdateRecord.setPd_name(konkaJxcStoreState.getPd_name());

		konkaJxcStoreUpdateRecord.setUpdate_num(t.getCount());// 数量
		konkaJxcStoreUpdateRecord.setAdd_date(t.getUpdate_date());// --
		konkaJxcStoreUpdateRecord.setAdd_user_id(t.getUpdate_user_id());// --
		this.konkaJxcStoreUpdateRecordDao.insertEntity(konkaJxcStoreUpdateRecord);

		return this.konkaJxcFhBillDetailsDao.updateEntity(t);
	}

	public int removeKonkaJxcFhBillDetails(KonkaJxcFhBillDetails t) {
		return this.konkaJxcFhBillDetailsDao.deleteEntity(t);
	}

	public List<KonkaJxcFhBillDetails> getKonkaJxcFhBillDetailsPaginatedList(KonkaJxcFhBillDetails t) {
		return this.konkaJxcFhBillDetailsDao.selectEntityPaginatedList(t);
	}

	@Override
	public List<KonkaJxcFhBillDetails> getKonkaJxcFhBillDetailsWithNamesList(KonkaJxcFhBillDetails t) {
		return this.konkaJxcFhBillDetailsDao.selectKonkaJxcFhBillDetailsWithNamesList(t);
	}

	@Override
	public List<Map> getKonkaJxcFhBillDetailsSumPdCountList(KonkaJxcFhBillDetails t) {
		return this.konkaJxcFhBillDetailsDao.selectKonkaJxcFhBillDetailsSumPdCountList(t);
	}

	@Override
	public List<Map> getKonkaJxcFhBillDetailsSumPdCountListWithSrc(KonkaJxcFhBillDetails t) {
		return this.konkaJxcFhBillDetailsDao.selectKonkaJxcFhBillDetailsSumPdCountListWithSrc(t);
	}

}
