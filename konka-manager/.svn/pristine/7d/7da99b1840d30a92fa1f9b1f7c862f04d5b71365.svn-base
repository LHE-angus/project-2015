package com.ebiz.mmt.service.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaJxcFhBillDao;
import com.ebiz.mmt.dao.KonkaJxcStockBillDao;
import com.ebiz.mmt.dao.KonkaJxcStockBillDetailsDao;
import com.ebiz.mmt.dao.KonkaJxcStoreStateDao;
import com.ebiz.mmt.dao.KonkaJxcStoreUpdateRecordDao;
import com.ebiz.mmt.domain.KonkaJxcStockBill;
import com.ebiz.mmt.domain.KonkaJxcStockBillDetails;
import com.ebiz.mmt.domain.KonkaJxcStoreState;
import com.ebiz.mmt.domain.KonkaJxcStoreUpdateRecord;
import com.ebiz.mmt.service.KonkaJxcStockBillService;
import com.ebiz.mmt.web.Constants;

/**
 * @author Li,Ka
 * @version 2011-10-19 09:18
 */
@Service
public class KonkaJxcStockBillServiceImpl implements KonkaJxcStockBillService {

	@Resource
	private KonkaJxcStockBillDao konkaJxcStockBillDao;

	@Resource
	private KonkaJxcStockBillDetailsDao konkaJxcStockBillDetailsDao;

	@Resource
	private KonkaJxcStoreStateDao konkaJxcStoreStateDao;

	@Resource
	private KonkaJxcStoreUpdateRecordDao konkaJxcStoreUpdateRecordDao;

	@Resource
	private KonkaJxcFhBillDao konkaJxcFhBillDao;

	public Long createKonkaJxcStockBill(KonkaJxcStockBill t) {
		Long id = this.konkaJxcStockBillDao.insertEntity(t);// 插入进货单

		if (null != t.getKonkaJxcFhBill()) {
			konkaJxcFhBillDao.updateEntity(t.getKonkaJxcFhBill());// 更新发货单为已确认（只有在收货确认是才更改）
		}

		List<KonkaJxcStockBillDetails> konkaJxcStockBillDetailsList = t.getKonkaJxcStockBillDetailsList();
		if (null != konkaJxcStockBillDetailsList && konkaJxcStockBillDetailsList.size() > 0) {
			DecimalFormat df = new DecimalFormat("0.000000");
			for (KonkaJxcStockBillDetails kjsbd : konkaJxcStockBillDetailsList) {
				kjsbd.setStock_bill_id(id);
				kjsbd.setIs_pc(0);

				KonkaJxcStoreState konkaJxcStoreState = new KonkaJxcStoreState();// 库存
				KonkaJxcStoreUpdateRecord konkaJxcStoreUpdateRecord = new KonkaJxcStoreUpdateRecord();
				konkaJxcStoreState.setStore_id(kjsbd.getStore_id());
				konkaJxcStoreState.setDept_id(t.getAdd_dept_id());
				konkaJxcStoreState.setPd_id(kjsbd.getPd_id());
				konkaJxcStoreState = konkaJxcStoreStateDao.selectEntity(konkaJxcStoreState);
				if (null != konkaJxcStoreState) {// 更新库存和插入库存
					// 更新库存
					Float f1 = Float.valueOf("0");
					if (null == konkaJxcStoreState.getCur_cost_price()) {
						f1 = Float.valueOf("0");
					} else {
						f1 = konkaJxcStoreState.getPd_num() * konkaJxcStoreState.getCur_cost_price().floatValue();// 更新前库存总价
					}
					Float f2 = kjsbd.getCount() * kjsbd.getPrice().floatValue();// 进货总价
					konkaJxcStoreState.setPd_num(konkaJxcStoreState.getPd_num() + kjsbd.getCount());// 更新实时库存

					if ((konkaJxcStoreState.getPd_num().intValue() + kjsbd.getCount().intValue()) == 0) {
						// 退货：刚好库存为0，实时成本价即为0 ，也防止0作除数
						konkaJxcStoreState.setCur_cost_price(new BigDecimal("0"));
					} else {
						// 库存不为0，计算实时成本价
						konkaJxcStoreState.setCur_cost_price(new BigDecimal(df.format((f1 + f2)
								/ konkaJxcStoreState.getPd_num())));// 更新计算后实时成本价
					}
					konkaJxcStoreState.setPrice_ref(kjsbd.getPrice());// 更新库存状态的进货参考价
					konkaJxcStoreState.setUpdate_date(new Date());
					konkaJxcStoreState.setUpdate_user_id(t.getAdd_user_id());
					konkaJxcStoreStateDao.updateEntity(konkaJxcStoreState);
					konkaJxcStoreUpdateRecord.setStore_state_id(konkaJxcStoreState.getId());// 库存记录ID
				} else {// 插入库存
					KonkaJxcStoreState entity = new KonkaJxcStoreState();
					entity.setStore_id(kjsbd.getStore_id());
					entity.setStore_name((String) kjsbd.getMap().get("storeName"));
					entity.setDept_id(t.getAdd_dept_id());
					entity.setAdd_user_id(t.getAdd_user_id());
					entity.setPd_type_id(kjsbd.getPd_type_id());
					entity.setPd_type_name(kjsbd.getPd_type_name());
					entity.setBrand_id(Constants.KONKA_BRAND_ID);// 康佳品牌ID
					entity.setBrand_name(kjsbd.getBrand_name());
					entity.setPd_id(kjsbd.getPd_id());
					entity.setPd_name(kjsbd.getPd_name());
					entity.setPd_num(kjsbd.getCount());// 产品实时库存数量
					entity.setPrice(kjsbd.getPrice());// 产品单价，第一次插入时存用于计算期初价格
					entity.setCur_cost_price(kjsbd.getPrice());// 产品实时成本价
					entity.setPrice_ref(kjsbd.getPrice());// 库存状态的进货参考价
					Long storeStateId = konkaJxcStoreStateDao.insertEntity(entity);
					konkaJxcStoreUpdateRecord.setStore_state_id(storeStateId);// 库存记录ID
				}

				// 插入库存修改记录
				konkaJxcStoreUpdateRecord.setUpdate_type(0);// 插入进货单
				konkaJxcStoreUpdateRecord.setAdd_user_id(t.getAdd_user_id());
				konkaJxcStoreUpdateRecord.setPd_type_id(kjsbd.getPd_type_id());// 品类
				konkaJxcStoreUpdateRecord.setPd_type_name(kjsbd.getPd_type_name());
				konkaJxcStoreUpdateRecord.setBrand_id(Constants.KONKA_BRAND_ID);
				konkaJxcStoreUpdateRecord.setBrand_name(Constants.KONKA_BRAND_NAME);
				konkaJxcStoreUpdateRecord.setPd_id(kjsbd.getPd_id());// 型号
				konkaJxcStoreUpdateRecord.setPd_name(kjsbd.getPd_name());
				konkaJxcStoreUpdateRecord.setDept_id(t.getAdd_dept_id());
				konkaJxcStoreUpdateRecord.setStore_id(kjsbd.getStore_id());// 进货仓库ID
				konkaJxcStoreUpdateRecord.setStore_name((String) kjsbd.getMap().get("storeName"));
				konkaJxcStoreUpdateRecord.setUpdate_num(kjsbd.getCount());// 进货数量
				konkaJxcStoreUpdateRecord.setPrice(kjsbd.getPrice());// 进货单价
				konkaJxcStoreUpdateRecord.setCur_cost_price(new BigDecimal("0"));// 实时成本价，进货不存直接记为0
				konkaJxcStoreUpdateRecord.setPrice_sum(new BigDecimal(df.format(kjsbd.getCount()
						* kjsbd.getPrice().floatValue())));
				konkaJxcStoreUpdateRecordDao.insertEntity(konkaJxcStoreUpdateRecord);

				konkaJxcStockBillDetailsDao.insertEntity(kjsbd);// 插入进货单明细
			}
		}
		return id;
	}

	public KonkaJxcStockBill getKonkaJxcStockBill(KonkaJxcStockBill t) {
		KonkaJxcStockBill konkaJxcStockBill = this.konkaJxcStockBillDao.selectKonkaJxcStockBillWithParDept(t);
		return konkaJxcStockBill;
	}

	public Long getKonkaJxcStockBillCount(KonkaJxcStockBill t) {
		return this.konkaJxcStockBillDao.selectEntityCount(t);
	}

	public List<KonkaJxcStockBill> getKonkaJxcStockBillList(KonkaJxcStockBill t) {
		return this.konkaJxcStockBillDao.selectEntityList(t);
	}

	public int modifyKonkaJxcStockBill(KonkaJxcStockBill t) {
		return this.konkaJxcStockBillDao.updateEntity(t);
	}

	/** 修改进货单、库存、库存修改记录 */
	public int modifyKonkaJxcStockBillWithDetails(KonkaJxcStockBill t) {
		KonkaJxcStockBillDetails konkaJxcStockBillDetails = t.getKonkaJxcStockBillDetailsList().get(0);
		KonkaJxcStoreState konkaJxcStoreState = konkaJxcStockBillDetails.getKonkaJxcStoreStateList().get(0);
		KonkaJxcStoreUpdateRecord konkaJxcStoreUpdateRecord = konkaJxcStoreState.getKonkaJxcStoreUpdateRecordList()
				.get(0);
		int id = konkaJxcStockBillDao.updateEntity(t);
		konkaJxcStockBillDetailsDao.updateEntity(konkaJxcStockBillDetails);
		konkaJxcStoreStateDao.updateEntity(konkaJxcStoreState);
		konkaJxcStoreUpdateRecordDao.insertEntity(konkaJxcStoreUpdateRecord);

		return id;
	}

	public int removeKonkaJxcStockBill(KonkaJxcStockBill t) {
		return this.konkaJxcStockBillDao.deleteEntity(t);
	}

	public List<KonkaJxcStockBill> getKonkaJxcStockBillPaginatedList(KonkaJxcStockBill t) {
		return this.konkaJxcStockBillDao.selectEntityPaginatedList(t);
	}

	/** 查询进货单包含大类、品牌、型号、仓库、部门信息 */
	public List<KonkaJxcStockBill> getKonkaJxcStockBillWithDetailsPaginatedList(KonkaJxcStockBill t) {
		return this.konkaJxcStockBillDao.selectKonkaJxcStockBillWithDetailsPaginatedList(t);
	}

}
