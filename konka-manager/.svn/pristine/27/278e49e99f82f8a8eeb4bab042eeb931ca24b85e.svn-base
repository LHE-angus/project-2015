package com.ebiz.mmt.service.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaJxcFhBillDao;
import com.ebiz.mmt.dao.KonkaJxcFhBillDetailsDao;
import com.ebiz.mmt.dao.KonkaJxcStoreStateDao;
import com.ebiz.mmt.dao.KonkaJxcStoreUpdateRecordDao;
import com.ebiz.mmt.dao.KonkaR3SellImpInvalidDataDao;
import com.ebiz.mmt.domain.KonkaJxcFhBill;
import com.ebiz.mmt.domain.KonkaJxcFhBillDetails;
import com.ebiz.mmt.domain.KonkaJxcStoreState;
import com.ebiz.mmt.domain.KonkaJxcStoreUpdateRecord;
import com.ebiz.mmt.domain.KonkaR3SellImpInvalidData;
import com.ebiz.mmt.service.KonkaJxcFhBillService;
import com.ebiz.mmt.web.Constants;

/**
 * @author Wu,Yang
 * @version 2011-10-11 09:18
 */
@Service
public class KonkaJxcFhBillServiceImpl implements KonkaJxcFhBillService {

	@Resource
	private KonkaJxcFhBillDao konkaJxcFhBillDao;

	@Resource
	private KonkaJxcFhBillDetailsDao konkaJxcFhBillDetailsDao;

	@Resource
	private KonkaJxcStoreStateDao konkaJxcStoreStateDao;

	@Resource
	private KonkaJxcStoreUpdateRecordDao konkaJxcStoreUpdateRecordDao;

	@Resource
	private KonkaR3SellImpInvalidDataDao konkaR3SellImpInvalidDataDao;

	public Long createKonkaJxcFhBill(KonkaJxcFhBill t) {

		Long id = this.konkaJxcFhBillDao.insertEntity(t);
		// 取发货登记明细list
		List<KonkaJxcFhBillDetails> konkaJxcFhBillDetailsList = t.getKonkaJxcFhBillDetails();

		for (KonkaJxcFhBillDetails konkaJxcFhBillDetails : konkaJxcFhBillDetailsList) {
			konkaJxcFhBillDetails.setFh_bill_id(id);
			this.konkaJxcFhBillDetailsDao.insertEntity(konkaJxcFhBillDetails);// 生成 发货登记明细表

			// 更新库存状态表
			// 部门、分公司、仓库、品类、型号
			KonkaJxcStoreState konkaJxcStoreState = konkaJxcFhBillDetails.getKonkaJxcStoreState();
			if (null != konkaJxcStoreState) {// update
				this.konkaJxcStoreStateDao.updateEntity(konkaJxcStoreState);

				// 更新库存时，添加库存修改记录
				KonkaJxcStoreUpdateRecord konkaJxcStoreUpdateRecord = new KonkaJxcStoreUpdateRecord();
				konkaJxcStoreUpdateRecord.setUpdate_type(1);// 发货
				konkaJxcStoreUpdateRecord.setStore_state_id(konkaJxcStoreState.getId());// 库存状态id
				konkaJxcStoreUpdateRecord.setDept_id(t.getAdd_dept_id());// 部门

				konkaJxcStoreUpdateRecord.setPd_type_id(konkaJxcStoreState.getPd_type_id());// 品类
				konkaJxcStoreUpdateRecord.setPd_type_name(konkaJxcStoreState.getPd_type_name());
				konkaJxcStoreUpdateRecord.setBrand_id(Constants.KONKA_BRAND_ID);// 品牌
				konkaJxcStoreUpdateRecord.setBrand_name(Constants.KONKA_BRAND_NAME);
				konkaJxcStoreUpdateRecord.setPd_id(konkaJxcStoreState.getPd_id());// 型号
				konkaJxcStoreUpdateRecord.setPd_name(konkaJxcStoreState.getPd_name());
				konkaJxcStoreUpdateRecord.setStore_id(konkaJxcStoreState.getStore_id());// 仓库id
				konkaJxcStoreUpdateRecord.setStore_name(konkaJxcStoreState.getStore_name());// 仓库名称
				konkaJxcStoreUpdateRecord.setUpdate_num(konkaJxcFhBillDetails.getCount());// 数量
				konkaJxcStoreUpdateRecord.setPrice(konkaJxcFhBillDetails.getPrice());// 单价
				konkaJxcStoreUpdateRecord.setPrice_sum(konkaJxcStoreState.getCur_cost_price().multiply(
						new BigDecimal(konkaJxcFhBillDetails.getCount().longValue())));// 发货金额
				konkaJxcStoreUpdateRecord.setCur_cost_price(konkaJxcStoreState.getCur_cost_price());// 实时成本价
				konkaJxcStoreUpdateRecord.setAdd_date(t.getAdd_date());
				konkaJxcStoreUpdateRecord.setAdd_user_id(t.getAdd_user_id());

				this.konkaJxcStoreUpdateRecordDao.insertEntity(konkaJxcStoreUpdateRecord);
			}
		}
		return id;
	}

	public KonkaJxcFhBill getKonkaJxcFhBill(KonkaJxcFhBill t) {
		return this.konkaJxcFhBillDao.selectEntity(t);
	}

	public Long getKonkaJxcFhBillCount(KonkaJxcFhBill t) {
		return this.konkaJxcFhBillDao.selectEntityCount(t);
	}

	public List<KonkaJxcFhBill> getKonkaJxcFhBillList(KonkaJxcFhBill t) {
		return this.konkaJxcFhBillDao.selectEntityList(t);
	}

	public int modifyKonkaJxcFhBill(KonkaJxcFhBill t) {
		return this.konkaJxcFhBillDao.updateEntity(t);
	}

	public int removeKonkaJxcFhBill(KonkaJxcFhBill t) {
		return this.konkaJxcFhBillDao.deleteEntity(t);
	}

	public List<KonkaJxcFhBill> getKonkaJxcFhBillPaginatedList(KonkaJxcFhBill t) {
		return this.konkaJxcFhBillDao.selectEntityPaginatedList(t);
	}

	/** 导入R3销售表生成的发货单列表 */
	public Long[] importR3SellKonkaJxcFhBill(List<KonkaJxcFhBill> t, List<KonkaR3SellImpInvalidData> invalidDataList,
			KonkaR3SellImpInvalidData konkaR3SellImpInvalidData) {
		// 先删除临时保存 导入的R3销售数据
		this.konkaR3SellImpInvalidDataDao.deleteEntity(konkaR3SellImpInvalidData);

		Long[] results = new Long[t.size()];
		if (t.size() > 0) {
			int i = 0;
			for (KonkaJxcFhBill konkaJxcFhBill : t) {
				Long fhBillId = this.konkaJxcFhBillDao.insertEntity(konkaJxcFhBill);
				results[i] = fhBillId;
				i++;
				// 取发货登记明细list
				List<KonkaJxcFhBillDetails> konkaJxcFhBillDetailsList = konkaJxcFhBill.getKonkaJxcFhBillDetailsList();
				if (konkaJxcFhBillDetailsList.size() > 0) {
					for (KonkaJxcFhBillDetails konkaJxcFhBillDetails : konkaJxcFhBillDetailsList) {
						Long stoteStateId = 0L;

						if (null != konkaJxcFhBillDetails.getInsertKonkaJxcStoreState()) {// 没有库存状态的型号插入库存状态
							// 重新验证下 是否该销售单中是否之前已经新增
							KonkaJxcStoreState temp = new KonkaJxcStoreState();
							temp.setDept_id(konkaJxcFhBillDetails.getInsertKonkaJxcStoreState().getDept_id());
							temp.setStore_id(konkaJxcFhBillDetails.getInsertKonkaJxcStoreState().getStore_id());
							temp.setPd_id(konkaJxcFhBillDetails.getInsertKonkaJxcStoreState().getPd_id());
							temp = this.konkaJxcStoreStateDao.selectEntity(temp);
							if (null == temp) {
								stoteStateId = this.konkaJxcStoreStateDao.insertEntity(konkaJxcFhBillDetails
										.getInsertKonkaJxcStoreState());
							} else {
								temp.setPd_num(temp.getPd_num()
										- konkaJxcFhBillDetails.getInsertKonkaJxcStoreState().getPd_num());
								stoteStateId = temp.getId();
								this.konkaJxcStoreStateDao.updateEntity(temp);
							}
						}
						/*
						 * if ( null != konkaJxcFhBillDetails.getKonkaJxcStoreState()) {//更新库存状态 stoteStateId =
						 * konkaJxcFhBillDetails.getKonkaJxcStoreState().getId();
						 * this.konkaJxcStoreStateDao.updateEntity(konkaJxcFhBillDetails.getKonkaJxcStoreState()); }
						 */

						konkaJxcFhBillDetails.setFh_bill_id(fhBillId);
						this.konkaJxcFhBillDetailsDao.insertEntity(konkaJxcFhBillDetails);// 生成 发货登记明细表

						// 更新库存时，添加库存修改记录
						/*
						 * KonkaJxcStoreUpdateRecord konkaJxcStoreUpdateRecord = new KonkaJxcStoreUpdateRecord();
						 * konkaJxcStoreUpdateRecord.setUpdate_type(1);// 发货
						 * konkaJxcStoreUpdateRecord.setStore_state_id(stoteStateId);// 库存状态id
						 * konkaJxcStoreUpdateRecord.setDept_id(konkaJxcFhBillDetails.getAdd_dept_id());// 部门
						 * konkaJxcStoreUpdateRecord.setPd_type_id(konkaJxcFhBillDetails.getPd_type_id());// 品类
						 * konkaJxcStoreUpdateRecord.setPd_type_name(konkaJxcFhBillDetails.getPd_type_name());
						 * konkaJxcStoreUpdateRecord.setBrand_id(Constants.KONKA_BRAND_ID);// 品牌
						 * konkaJxcStoreUpdateRecord.setBrand_name(Constants.KONKA_BRAND_NAME);
						 * konkaJxcStoreUpdateRecord.setPd_id(konkaJxcFhBillDetails.getPd_id());// 型号
						 * konkaJxcStoreUpdateRecord.setPd_name(konkaJxcFhBillDetails.getPd_name());
						 * konkaJxcStoreUpdateRecord.setStore_id(konkaJxcFhBillDetails.getStore_id());// 仓库id
						 * konkaJxcStoreUpdateRecord.setStore_name(konkaJxcFhBillDetails.getFh_store());// 仓库名称
						 * konkaJxcStoreUpdateRecord.setUpdate_num(konkaJxcFhBillDetails.getCount());// 数量
						 * konkaJxcStoreUpdateRecord.setPrice(konkaJxcFhBillDetails.getPrice());// 单价
						 * konkaJxcStoreUpdateRecord.setPrice_sum(konkaJxcFhBillDetails.getSum_money());//发货金额
						 * //konkaJxcStoreUpdateRecord.setCur_cost_price(konkaJxcStoreState.getCur_cost_price());//
						 * 实时成本价 konkaJxcStoreUpdateRecord.setAdd_date(konkaJxcFhBillDetails.getAdd_date());
						 * konkaJxcStoreUpdateRecord.setAdd_user_id(konkaJxcFhBillDetails.getAdd_user_id());
						 * this.konkaJxcStoreUpdateRecordDao.insertEntity(konkaJxcStoreUpdateRecord);
						 */
					}
				}

			}
		}

		if (invalidDataList.size() > 0) {
			for (KonkaR3SellImpInvalidData ivlData : invalidDataList) {
				this.konkaR3SellImpInvalidDataDao.insertEntity(ivlData);
			}
		}

		return results;
	}

}
