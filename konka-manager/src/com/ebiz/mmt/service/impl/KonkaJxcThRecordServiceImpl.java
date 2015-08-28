package com.ebiz.mmt.service.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.BasePdClassDao;
import com.ebiz.mmt.dao.JxcPdDao;
import com.ebiz.mmt.dao.JxcStockBillDao;
import com.ebiz.mmt.dao.JxcStockBillDetailsDao;
import com.ebiz.mmt.dao.KonkaJxcStockBillDao;
import com.ebiz.mmt.dao.KonkaJxcStockBillDetailsDao;
import com.ebiz.mmt.dao.KonkaJxcStoreStateDao;
import com.ebiz.mmt.dao.KonkaJxcStoreUpdateRecordDao;
import com.ebiz.mmt.dao.KonkaJxcThRecordDao;
import com.ebiz.mmt.domain.BasePdClass;
import com.ebiz.mmt.domain.JxcPd;
import com.ebiz.mmt.domain.JxcStockBillDetails;
import com.ebiz.mmt.domain.KonkaJxcStockBill;
import com.ebiz.mmt.domain.KonkaJxcStockBillDetails;
import com.ebiz.mmt.domain.KonkaJxcStoreState;
import com.ebiz.mmt.domain.KonkaJxcStoreUpdateRecord;
import com.ebiz.mmt.domain.KonkaJxcThRecord;
import com.ebiz.mmt.service.KonkaJxcThRecordService;
import com.ebiz.mmt.web.Constants;

/**
 * @author Wu,Yang
 * @version 2011-10-11 09:18
 */
@Service
public class KonkaJxcThRecordServiceImpl implements KonkaJxcThRecordService {

	@Resource
	private KonkaJxcThRecordDao konkaJxcThRecordDao;

	@Resource
	private JxcStockBillDao jxcStockBillDao;

	@Resource
	private JxcStockBillDetailsDao jxcStockBillDetailsDao;

	@Resource
	private KonkaJxcStockBillDao konkajxcStockBillDao;

	@Resource
	private KonkaJxcStockBillDetailsDao konkajxcStockBillDetailsDao;

	@Resource
	private JxcPdDao jxcPdDao;

	@Resource
	private KonkaJxcStoreStateDao konkaJxcStoreStateDao;

	@Resource
	private KonkaJxcStoreUpdateRecordDao konkaJxcStoreUpdateRecordDao;

	@Resource
	private BasePdClassDao basePdClassDao;

	public Long createKonkaJxcThRecord(KonkaJxcThRecord t) {
		return this.konkaJxcThRecordDao.insertEntity(t);
	}

	public KonkaJxcThRecord getKonkaJxcThRecord(KonkaJxcThRecord t) {
		return this.konkaJxcThRecordDao.selectEntity(t);
	}

	public KonkaJxcThRecord getKonkaJxcThRecordWith2Names(KonkaJxcThRecord t) {
		return this.konkaJxcThRecordDao.selectKonkaJxcThRecordWith2Names(t);
	}

	public Long getKonkaJxcThRecordCount(KonkaJxcThRecord t) {
		return this.konkaJxcThRecordDao.selectEntityCount(t);
	}

	public List<KonkaJxcThRecord> getKonkaJxcThRecordList(KonkaJxcThRecord t) {
		return this.konkaJxcThRecordDao.selectEntityList(t);
	}

	public int modifyKonkaJxcThRecord(KonkaJxcThRecord t) {
		DecimalFormat df = new DecimalFormat("0.000000");
		int rows = 0;
		rows = this.konkaJxcThRecordDao.updateEntity(t);
		if (null != t.getMap().get("shopConfirm")) {// 进销存网点退货确认
			if (null != t.getJxcStockBill()) {// 更新库存，插入一条进货记录，防止库存统计时，库存不平
				JxcPd entityJxcPd = t.getJxcStockBill().getJxcPdList().get(0);// 进货单的产品
				JxcStockBillDetails entityJxcStockBillDetails = t.getJxcStockBill().getJxcStockBillDetailsList().get(0);// 进货单明细

				entityJxcPd.setCount(entityJxcPd.getCount() + entityJxcStockBillDetails.getCount());// 实时库存=当前库存+进货数量
				jxcPdDao.updateEntity(entityJxcPd);

				Long stockBillId = jxcStockBillDao.insertEntity(t.getJxcStockBill());// 保存进货单

				entityJxcStockBillDetails.setStock_bill_id(stockBillId);// set进货单ID
				jxcStockBillDetailsDao.insertEntity(entityJxcStockBillDetails);

			}
			return rows;
		}
		if (null != t.getMap().get("managerConfirm")) {// 管理端确认: 对未确认的退货 确认并更新库存、插入库存记录
			if (null != t.getKonkaJxcStoreState()) {
				KonkaJxcStoreUpdateRecord konkaJxcStoreUpdateRecord = new KonkaJxcStoreUpdateRecord();
				// 更新库存，插入一条进货记录，防止库存统计时，库存不平
				KonkaJxcStockBill konkaJxcStockBill = new KonkaJxcStockBill();// 进货记录表 插入记录 主表操作
				konkaJxcStockBill.setSn("JH" + DateFormatUtils.format(new Date(), "yyyyMMddHHmmssSSS"));// 生成进货单号
				konkaJxcStockBill.setJh_sum_count(t.getTh_count() * -1);
				Float money_must = t.getTh_count() * t.getPrice().floatValue() * -1;// 退货总价
				konkaJxcStockBill.setMoney_must(new BigDecimal(money_must));
				konkaJxcStockBill.setMoney_result(new BigDecimal(money_must));// 默认为应付款
				if (StringUtils.isNotBlank(t.getMap().get("par_dept_id").toString())) {// 上级部门
					konkaJxcStockBill.setPart_dept_id(new Long(t.getMap().get("par_dept_id").toString()));
				}

				konkaJxcStockBill.setAdd_dept_name(t.getAdd_dept_name());
				konkaJxcStockBill.setAdd_user_id(t.getUpdate_user_id());
				konkaJxcStockBill.setAdd_user_name(t.getAdd_user_name());
				konkaJxcStockBill.setAdd_user_type(t.getAdd_user_type());
				KonkaJxcStockBillDetails konkaJxcStockBillDetails = new KonkaJxcStockBillDetails();// 进货记录表 插入记录 字表表操作
				konkaJxcStockBill.setAdd_dept_id(t.getAdd_dept_id());// 确认时取添加退货的部门
				konkaJxcStockBillDetails.setAdd_dept_id(t.getAdd_dept_id());// 确认时取添加退货的部门

				Long pd_type_id = t.getPd_type_id() == null ? 0l : t.getPd_type_id();
				konkaJxcStockBillDetails.setPd_type_id(pd_type_id);
				konkaJxcStockBillDetails.setPd_type_name(t.getPd_type_name());
				konkaJxcStockBillDetails.setBrand_id(t.getBrand_id());
				konkaJxcStockBillDetails.setBrand_name(t.getBrand_name());
				konkaJxcStockBillDetails.setPd_id(t.getPd_id());
				konkaJxcStockBillDetails.setPd_name(t.getPd_name());
				konkaJxcStockBillDetails.setCount(t.getTh_count() * -1);

				konkaJxcStockBillDetails.setStore_id(t.getTh_store_id_son());
				konkaJxcStockBillDetails.setAdd_user_id(t.getAdd_user_id());
				konkaJxcStockBillDetails.setPrice(t.getPrice());

				if (t.getTh_type() == 1) {// 退货为正品 更新产品数量及实时成本价（update by du，zhiming 20111107）

					konkaJxcStockBill.setRemark("退货正品" + "[" + t.getPd_id() + "]");

					konkaJxcStoreUpdateRecord.setUpdate_type(2);// 退货正品

				}
				if (t.getTh_type() == 0) {// 退货为残次品 只更新残次产品数量
					konkaJxcStockBill.setRemark("退货残次品" + "[" + t.getPd_id() + "]");

					konkaJxcStoreUpdateRecord.setUpdate_type(4);// 退货残次品
				}
				Long stockBillId = konkajxcStockBillDao.insertEntity(konkaJxcStockBill);// 插入主表
				konkaJxcStockBillDetails.setStock_bill_id(stockBillId);
				konkajxcStockBillDetailsDao.insertEntity(konkaJxcStockBillDetails);// 插入字表

				konkaJxcStoreStateDao.updateEntity(t.getKonkaJxcStoreState());// 更新库存
				// 插入库存更新记录

				konkaJxcStoreUpdateRecord.setAdd_user_id(t.getAdd_user_id());
				konkaJxcStoreUpdateRecord.setDept_id(t.getAdd_dept_id());
				konkaJxcStoreUpdateRecord.setPd_type_id(t.getPd_type_id());// 品类
				konkaJxcStoreUpdateRecord.setPd_type_name(t.getPd_type_name());
				konkaJxcStoreUpdateRecord.setBrand_id(Constants.KONKA_BRAND_ID);
				konkaJxcStoreUpdateRecord.setBrand_name(Constants.KONKA_BRAND_NAME);
				konkaJxcStoreUpdateRecord.setPd_id(t.getPd_id());// 型号
				konkaJxcStoreUpdateRecord.setPd_name(t.getPd_name());

				konkaJxcStoreUpdateRecord.setStore_state_id(t.getKonkaJxcStoreState().getId());
				konkaJxcStoreUpdateRecord.setUpdate_num(t.getTh_count() * -1);
				konkaJxcStoreUpdateRecord.setPrice(t.getPrice());// 单价
				Float price_sum = t.getTh_count() * t.getPrice().floatValue() * -1;// 退货总价
				konkaJxcStoreUpdateRecord.setPrice_sum(new BigDecimal(df.format(price_sum)));
				konkaJxcStoreUpdateRecord.setStore_id(t.getTh_store_id_son());// 审核仓库id
				konkaJxcStoreUpdateRecord.setStore_name(t.getMap().get("storeName").toString());// 审核仓库 storeName

				konkaJxcStoreUpdateRecordDao.insertEntity(konkaJxcStoreUpdateRecord);

			}

		}

		if (null != t.getMap().get("audit")) {// 管理端审批
			KonkaJxcStoreState konkaJxcStoreStateCopy = new KonkaJxcStoreState();
			if (t.getIs_audit() == 1) {// 同意时更新库存、插入库存记录

				// 更新库存，插入一条进货记录，防止库存统计时，库存不平
				KonkaJxcStockBill konkaJxcStockBill = new KonkaJxcStockBill();// 进货记录表 插入记录 主表操作
				KonkaJxcStockBillDetails konkaJxcStockBillDetails = new KonkaJxcStockBillDetails();// 进货记录表 插入记录 字表表操作

				konkaJxcStockBill.setSn("JH" + DateFormatUtils.format(new Date(), "yyyyMMddHHmmssSSS"));// 生成进货单号
				konkaJxcStockBill.setJh_sum_count(t.getTh_count());
				Float money_must = t.getTh_count() * t.getPrice().floatValue();// 退货总价
				konkaJxcStockBill.setMoney_must(new BigDecimal(money_must));
				konkaJxcStockBill.setMoney_result(new BigDecimal(money_must));// 默认为应付款
				if (StringUtils.isNotBlank(t.getMap().get("par_dept_id").toString())) {// 上级部门
					konkaJxcStockBill.setPart_dept_id(new Long(t.getMap().get("par_dept_id").toString()));
				}

				/***************** 以下字段取审批人信息 start *****************************/
				konkaJxcStockBill.setAdd_dept_id(t.getAudit_dept_id());
				konkaJxcStockBill.setAdd_dept_name(t.getAdd_dept_name());
				konkaJxcStockBill.setAdd_user_id(t.getAudit_user_id());
				konkaJxcStockBill.setAdd_user_type(t.getAudit_user_type());

				konkaJxcStockBillDetails.setAdd_dept_id(t.getAudit_dept_id());
				konkaJxcStockBillDetails.setAdd_user_id(t.getAudit_user_id());
				if (StringUtils.isNotBlank(t.getMap().get("own_user_name").toString())) {
					konkaJxcStockBill.setAdd_user_name(t.getMap().get("own_user_name").toString());
				}
				if (StringUtils.isNotBlank(t.getMap().get("own_dept_name").toString())) {
					konkaJxcStockBill.setAdd_dept_name(t.getMap().get("own_dept_name").toString());
				}

				/***************** 以下字段取审批人信息 end *****************************/

				konkaJxcStockBillDetails.setPd_type_id(t.getPd_type_id());
				konkaJxcStockBillDetails.setPd_type_name(t.getPd_type_name());
				konkaJxcStockBillDetails.setBrand_id(t.getBrand_id());
				konkaJxcStockBillDetails.setBrand_name(t.getBrand_name());
				konkaJxcStockBillDetails.setPd_id(t.getPd_id());
				konkaJxcStockBillDetails.setPd_name(t.getPd_name());
				konkaJxcStockBillDetails.setCount(t.getTh_count());

				konkaJxcStockBillDetails.setStore_id(t.getTh_store_id_par());

				konkaJxcStockBillDetails.setPrice(t.getPrice());

				KonkaJxcStoreState konkaJxcStoreState = new KonkaJxcStoreState();
				KonkaJxcStoreUpdateRecord konkaJxcStoreUpdateRecord = new KonkaJxcStoreUpdateRecord();// 库存记录

				konkaJxcStoreState.setStore_id(t.getTh_store_id_par());// 入库仓库即退货审批的接受仓库
				konkaJxcStoreState.setDept_id(t.getAudit_dept_id());// 部门
				konkaJxcStoreState.setPd_id(t.getPd_id());// 产品型号Id
				if (null != t.getShop_id()) {// //
					// 网点端退货端退货:网点端退货记录存的是jxc_pd表ID，要找到表的out_sys_id才能对应到库存状态中存的pe_pd_model表ID
					konkaJxcStoreState.setPd_id(t.getPePdModel().getPd_id());
					konkaJxcStoreState.setShop_id(t.getShop_id());
					konkaJxcStoreUpdateRecord.setShop_id(t.getShop_id());
				}
				konkaJxcStoreState = konkaJxcStoreStateDao.selectEntity(konkaJxcStoreState);

				if (null != konkaJxcStoreState) {// 选择的接收仓库有库存记录
					konkaJxcStoreState.setStore_name(t.getMap().get("storeName").toString());
					DomainUtils.copyProperties(konkaJxcStoreStateCopy, konkaJxcStoreState);
					if (t.getTh_type() == 1) {// 退货为正品 更新产品数量及实时成本价（update by du，zhiming 20111107）
						konkaJxcStoreState.setPd_num(konkaJxcStoreState.getPd_num() + t.getTh_count());// 实时正品库存=当前正品库存+进货数量
						// 更新实时成本价 实时成本价 =（当前库存 * 实时成本价 + 退货数量 * 退货价格）/ （当前库存 + 进货数量）
						Float f1 = konkaJxcStoreState.getPd_num() * konkaJxcStoreState.getCur_cost_price().floatValue();// 更新前库存总价
						Float f2 = t.getTh_count() * t.getPrice().floatValue();// 退货总价
						BigDecimal cur_cost_price = new BigDecimal(df.format(0));
						if (konkaJxcStoreState.getPd_num().intValue() != 0) {//页面已处理除数为零的情况 此处判断以防页面处理失效 add by du,zhiming 20111 1节
							cur_cost_price = new BigDecimal(df.format((f1 + f2) / konkaJxcStoreState.getPd_num()));
						}
						konkaJxcStoreState.setCur_cost_price(cur_cost_price);
						konkaJxcStockBill.setRemark("退货正品" + "[" + t.getPd_id() + "]");

						konkaJxcStoreUpdateRecord.setUpdate_type(2);// 退货正品

					}
					if (t.getTh_type() == 0) {// 退货为残次品 只更新残次产品数量
						konkaJxcStoreState.setPd_bad_num(konkaJxcStoreState.getPd_bad_num() + t.getTh_count());// 实时残次品库存=当前残次品库存+进货数量
						konkaJxcStockBill.setRemark("退货残次品" + "[" + t.getPd_id() + "]");

						konkaJxcStoreUpdateRecord.setUpdate_type(4);// 退货残次品
					}

					konkaJxcStoreState.setUpdate_date(t.getUpdate_date());// 更新时间
					konkaJxcStoreState.setUpdate_user_id(t.getUpdate_user_id());// 更新人
					konkaJxcStoreStateDao.updateEntity(konkaJxcStoreState);// 修改库存状态
					konkaJxcStoreUpdateRecord.setStore_state_id(konkaJxcStoreState.getId());// 库存状态ID
				} else {// 选择的接收仓库无库存记录
					KonkaJxcStoreState entity = new KonkaJxcStoreState();
					entity.setStore_name(t.getMap().get("storeName").toString());
					entity.setStore_id(t.getTh_store_id_par());
					entity.setDept_id(t.getAudit_dept_id());// 部门
					entity.setBrand_id(t.getBrand_id());// 康佳品牌ID
					entity.setBrand_name(t.getBrand_name());

					if (null != t.getShop_id()) {// 网点端退货:重新查询网点端信号对应的
						BasePdClass basePdClass = new BasePdClass();
						basePdClass.setCls_id(t.getPePdModel().getCls_id());
						basePdClass.setIs_del(0);
						basePdClass = this.basePdClassDao.selectEntity(basePdClass);
						if (null != basePdClass) {
							entity.setPd_type_name(basePdClass.getCls_name());
						}
						entity.setShop_id(t.getShop_id());
						entity.setPd_type_id(t.getPePdModel().getCls_id());
						entity.setPd_id(t.getPePdModel().getPd_id());
						entity.setPd_name(t.getPePdModel().getMd_name());

					} else {// 管理端退货：直接从退货记录里取
						entity.setPd_type_id(t.getPd_type_id());
						entity.setPd_type_name(t.getPd_type_name());
						entity.setPd_id(t.getPd_id());
						entity.setPd_name(t.getPd_name());
					}
					if (t.getTh_type() == 1) {// 退货为正品
						entity.setPd_num(t.getTh_count());// 实时正品库存=进货数量
						entity.setCur_cost_price(t.getPrice());// 仓库无库存记录时 实时成本价即为退货价 add by dzm 20111109
						konkaJxcStockBill.setRemark("退货正品" + "[" + t.getPd_id() + "]");
					}
					if (t.getTh_type() == 0) {// 退货为残次品
						entity.setPd_bad_num(t.getTh_count());// 实时残次品库存=进货数量
						konkaJxcStockBill.setRemark("退货残次品" + "[" + t.getPd_id() + "]");
					}
					entity.setAdd_user_id(t.getUpdate_user_id());// 添加人
					Long storeStateId = konkaJxcStoreStateDao.insertEntity(entity);// 插入库存状态
					konkaJxcStoreUpdateRecord.setStore_state_id(storeStateId);// 库存状态ID
					DomainUtils.copyProperties(konkaJxcStoreStateCopy, entity);
				}

				Long stockBillId = konkajxcStockBillDao.insertEntity(konkaJxcStockBill);// 插入主表
				konkaJxcStockBillDetails.setStock_bill_id(stockBillId);
				konkajxcStockBillDetailsDao.insertEntity(konkaJxcStockBillDetails);// 插入字表

				konkaJxcStoreUpdateRecord.setAdd_user_id(t.getUpdate_user_id());
				konkaJxcStoreUpdateRecord.setPd_type_id(konkaJxcStoreStateCopy.getPd_type_id());// 品类
				konkaJxcStoreUpdateRecord.setPd_type_name(konkaJxcStoreStateCopy.getPd_type_name());
				konkaJxcStoreUpdateRecord.setBrand_id(Constants.KONKA_BRAND_ID);
				konkaJxcStoreUpdateRecord.setBrand_name(Constants.KONKA_BRAND_NAME);
				konkaJxcStoreUpdateRecord.setPd_id(konkaJxcStoreStateCopy.getPd_id());// 型号
				konkaJxcStoreUpdateRecord.setPd_name(konkaJxcStoreStateCopy.getPd_name());
				konkaJxcStoreUpdateRecord.setDept_id(t.getAudit_dept_id());// 部门
				konkaJxcStoreUpdateRecord.setUpdate_num(t.getTh_count());
				// konkaJxcStoreUpdateRecord.setUpdate_type(0);// 进货
				konkaJxcStoreUpdateRecord.setRemark("退货审批通过增加进货库存修改记录");
				konkaJxcStoreUpdateRecord.setPrice(t.getPrice());// 单价
				Float price_sum = t.getTh_count() * t.getPrice().floatValue();// 退货总价
				konkaJxcStoreUpdateRecord.setPrice_sum(new BigDecimal(df.format(price_sum)));
				konkaJxcStoreUpdateRecord.setStore_id(t.getTh_store_id_par());// 审核仓库id
				konkaJxcStoreUpdateRecord.setStore_name(t.getMap().get("storeName").toString());// 审核仓库 storeName

				rows = this.konkaJxcThRecordDao.updateEntity(t);// 更新退货记录
				konkaJxcStoreUpdateRecordDao.insertEntity(konkaJxcStoreUpdateRecord);// 插入库存更新记录

				return rows;
			}
		}
		return rows;
	}

	public int removeKonkaJxcThRecord(KonkaJxcThRecord t) {
		return this.konkaJxcThRecordDao.deleteEntity(t);
	}

	public List<KonkaJxcThRecord> getKonkaJxcThRecordPaginatedList(KonkaJxcThRecord t) {
		return this.konkaJxcThRecordDao.selectEntityPaginatedList(t);
	}

}
