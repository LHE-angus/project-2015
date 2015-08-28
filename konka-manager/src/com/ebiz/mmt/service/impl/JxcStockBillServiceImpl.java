package com.ebiz.mmt.service.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.JxcPdDao;
import com.ebiz.mmt.dao.JxcSellBillDao;
import com.ebiz.mmt.dao.JxcSellBillDetailsDao;
import com.ebiz.mmt.dao.JxcStockBillDao;
import com.ebiz.mmt.dao.JxcStockBillDetailsDao;
import com.ebiz.mmt.dao.JxcSupplierDao;
import com.ebiz.mmt.dao.KonkaJxcFhBillDao;
import com.ebiz.mmt.dao.KonkaJxcFhBillDetailsDao;
import com.ebiz.mmt.dao.KonkaPdTypeJoinPdClassDao;
import com.ebiz.mmt.domain.JxcPd;
import com.ebiz.mmt.domain.JxcSellBill;
import com.ebiz.mmt.domain.JxcSellBillDetails;
import com.ebiz.mmt.domain.JxcStockBill;
import com.ebiz.mmt.domain.JxcStockBillDetails;
import com.ebiz.mmt.domain.JxcSupplier;
import com.ebiz.mmt.domain.KonkaJxcFhBill;
import com.ebiz.mmt.domain.KonkaJxcFhBillDetails;
import com.ebiz.mmt.domain.KonkaPdTypeJoinPdClass;
import com.ebiz.mmt.service.JxcStockBillService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2011-03-03 09:42:37
 */
@Service
public class JxcStockBillServiceImpl implements JxcStockBillService {

	@Resource
	private JxcStockBillDao jxcStockBillDao;

	@Resource
	private JxcStockBillDetailsDao jxcStockBillDetailsDao;

	@Resource
	private JxcPdDao jxcPdDao;

	@Resource
	private JxcSupplierDao jxcSupplierDao;

	@Resource
	private KonkaPdTypeJoinPdClassDao konkaPdTypeJoinPdClassDao;

	@Resource
	private KonkaJxcFhBillDao konkaJxcFhBillDao;

	@Resource
	private KonkaJxcFhBillDetailsDao konkaJxcFhBillDetailsDao;

	@Resource
	private JxcSellBillDao jxcSellBillDao;

	@Resource
	private JxcSellBillDetailsDao jxcSellBillDetailsDao;
	

	/** LiKa:保存或更新进货单相关信息 */
	public Long createJxcStockBill(JxcStockBill t) {
		Long stockBillId = jxcStockBillDao.insertEntity(t);// 保存进货单
		if (null != t.getJxcSupplier()) {
			jxcSupplierDao.updateEntity(t.getJxcSupplier());
		}

		List<JxcStockBillDetails> jxcStockBillDetailsList = t.getJxcStockBillDetailsList();
		if (null != jxcStockBillDetailsList && jxcStockBillDetailsList.size() > 0) {
			for (JxcStockBillDetails jsbd : jxcStockBillDetailsList) {
				JxcPd entityJxcPd = jsbd.getJxcPd();
				DecimalFormat df = new DecimalFormat("0.000000");

				// 查询该产品是否存在产品
				if (null != entityJxcPd.getMap().get("insertJxcPd")) {// 添加
					Long id = jxcPdDao.insertEntity(entityJxcPd);
					entityJxcPd.setId(id);
					// if (null == entityJxcPd.getOut_sys_id() && entityJxcPd.getOwn_sys().intValue() == 0) {
					// JxcPd jxcPd = new JxcPd();
					// jxcPd.setId(id);
					// jxcPd.setOut_sys_id(id * -1);
					// this.jxcPdDao.updateEntity(jxcPd);
					// }
				}
				if (null != entityJxcPd.getMap().get("updateJxcPd")) {// 修改
					JxcPd jxcPd = new JxcPd();
					jxcPd.setId(entityJxcPd.getId());
					entityJxcPd = jxcPdDao.selectEntity(jxcPd);// 重新取一下，防止页面选择了相同的型号，而导致库存不一致的问题
					Float f1 = entityJxcPd.getCount() * entityJxcPd.getCur_cost_price().floatValue();// 更新前库存总价
					Float f2 = jsbd.getCount() * jsbd.getPrice().floatValue();// 进货总价
					entityJxcPd.setCount(entityJxcPd.getCount() + jsbd.getCount());// 实时库存=当前库存+进货数量
					entityJxcPd.setCur_cost_price(new BigDecimal(df.format((f1 + f2) / entityJxcPd.getCount())));// 实时成本价=（进货前的库存*实时成本价+进货数量*进货价格）/总库存
					jxcPdDao.updateEntity(entityJxcPd);
				}

				jsbd.setStock_bill_id(stockBillId);// set进货单ID
				jsbd.setPd_id(entityJxcPd.getId());// 型号ID
				jxcStockBillDetailsDao.insertEntity(jsbd);
			}

		}

		return stockBillId;

	}

	public JxcStockBill getJxcStockBill(JxcStockBill t) {
		return this.jxcStockBillDao.selectEntity(t);
	}

	public JxcStockBill getJxcStockBillWithMoney(JxcStockBill t) {
		return this.jxcStockBillDao.selectJxcStockBillWithMoney(t);
	};

	public Long getJxcStockBillCount(JxcStockBill t) {
		return this.jxcStockBillDao.selectEntityCount(t);
	}

	public List<JxcStockBill> getJxcStockBillList(JxcStockBill t) {
		return this.jxcStockBillDao.selectEntityList(t);
	}

	public List<JxcStockBill> getJxcStockBillListForSZMX(JxcStockBill t) {
		return this.jxcStockBillDao.selectJxcStockBillListForSZMX(t);
	}

	public int modifyJxcStockBill(JxcStockBill t) {
		return this.jxcStockBillDao.updateEntity(t);
	}

	public int removeJxcStockBill(JxcStockBill t) {
		return this.jxcStockBillDao.deleteEntity(t);
	}

	public List<JxcStockBill> getJxcStockBillPaginatedList(JxcStockBill t) {
		return this.jxcStockBillDao.selectEntityPaginatedList(t);
	}

	public List<JxcStockBill> getJxcStockBillPaginatedListWithSname(JxcStockBill t) {
		return this.jxcStockBillDao.selectJxcStockBillPaginatedListWithSname(t);
	}

	/** LiKa:修改进货单明细数量、单件，同时修改供应商当前应付款、和进货单本次付款、应付款 */
	// 这个方法已弃用
	public int modifyJxcStockBillWithDetailsAndSupplier(JxcStockBill t) {
		return 0;
	}

	/** Li,Ka: 收货确认插入，进货单 */
	public Long createJxcStockBillByConfirm(JxcStockBill t) {
		Long fhBillId = (Long) t.getMap().get("fh_bill_id");// 发货地id
		//Integer ownSys = (Integer) t.getMap().get("own_sys");// 收货所属系统
		String[] fh_bill_detail_ids = (String[]) t.getMap().get("fh_bill_detail_ids");// 所有发货明细ID
		String[] prices = (String[]) t.getMap().get("prices");// 所有明细对应的进货单价
		String[] counts = (String[]) t.getMap().get("counts");// 所有明细对应的进货数量
		DecimalFormat df = new DecimalFormat("0.000000");

		KonkaJxcFhBill konkaJxcFhBill = new KonkaJxcFhBill();
		konkaJxcFhBill.setId(fhBillId);
		konkaJxcFhBill.setIs_confirm(1);// 已确认
		konkaJxcFhBillDao.updateEntity(konkaJxcFhBill);// 更新发货单状态为已确认

		// 插入进货单
		t.setIs_del(0);
		t.setSn("JH" + DateFormatUtils.format(new Date(), "yyyyMMddHHmmssSSS"));// 生成固定格式的进货单编号
		t.setIn_date(new Date());// 进货日期
		t.setPay_money(new BigDecimal("0.00"));// 初始化应付款
		t.setPaid_money(new BigDecimal("0.00"));// 初始化实付款
		for (int i = 0; i < counts.length; i++) {// 计算进货单实付和应付款(两者相等)
			t.setPay_money(t.getPay_money().add((new BigDecimal(counts[i])).multiply(new BigDecimal(prices[i])))
					.setScale(2));
		}
		t.setPaid_money(t.getPay_money());

		// 检查发货部门供应商:根据shop_id，供应商名称
		JxcSupplier jxcSupplier = new JxcSupplier();
		jxcSupplier.setShop_id(t.getShop_id());
		jxcSupplier.setName((String) t.getMap().get("supplier_name"));
		jxcSupplier = jxcSupplierDao.selectEntity(jxcSupplier);
		if (null == jxcSupplier) {
			JxcSupplier newJxcSupplier = new JxcSupplier();
			newJxcSupplier.setShop_id(t.getShop_id());
			newJxcSupplier.setShop_p_index(t.getShop_p_index());
			newJxcSupplier.setAdd_user_id(t.getAdd_user_id());
			newJxcSupplier.setName((String) t.getMap().get("supplier_name"));
			Long supplierId = jxcSupplierDao.insertEntity(newJxcSupplier);
			t.setSupplier_id(supplierId);// 进货单供应商ID
		} else {
			t.setSupplier_id(jxcSupplier.getId());// 进货单供应商ID
		}

		Long jxcStockBillId = jxcStockBillDao.insertEntity(t);// 插入进货单

		for (int i = 0; i < fh_bill_detail_ids.length; i++) {
			KonkaJxcFhBillDetails konkaJxcFhBillDetails = new KonkaJxcFhBillDetails();
			konkaJxcFhBillDetails.setId(Long.valueOf(fh_bill_detail_ids[i]));
			konkaJxcFhBillDetails = konkaJxcFhBillDetailsDao.selectEntity(konkaJxcFhBillDetails);
			if (null != konkaJxcFhBillDetails) {
				Long pdId = konkaJxcFhBillDetails.getPd_id();// 型号ID
				int ownSys = 0;// 所属系统：1 下乡        0 非下乡 

				JxcPd jxcPdCopy = new JxcPd();// 拷贝被更新或者插入的产品信息
				JxcPd jxcPd = new JxcPd();
				jxcPd.setShop_id(t.getShop_id());
				
				if (pdId.longValue() > 1999999999l && pdId.longValue() < 3000000000l) {// 根据康佳品牌的型号id关联买卖提pe_pd_model表，查询该型号下乡还是非下乡：如果在pe_pd_model有就是下乡，其余都是外部添加的（非下乡）
					ownSys = 1;
				}
				
				jxcPd.setOwn_sys(ownSys);
				jxcPd.setOut_sys_id(pdId);
				jxcPd = jxcPdDao.selectEntity(jxcPd);
				if (null == jxcPd) {// 没有该型号，插入新型号:计算实时成本价、起初成本价、进货参考价
					JxcPd entity = new JxcPd();
					entity.setShop_id(t.getShop_id());
					entity.setShop_p_index(t.getShop_p_index());
					entity.setName(konkaJxcFhBillDetails.getPd_name());
					entity.setBrand_id(konkaJxcFhBillDetails.getBrand_id());
					entity.setBrand_name(konkaJxcFhBillDetails.getBrand_name());
					entity.setAdd_user_id(t.getAdd_user_id());
					entity.setOut_sys_id(pdId);
					entity.setOwn_sys(ownSys);// 所属系统
					entity.setCount(Long.valueOf(counts[i]));// 进货数量
					entity.setRef_price(new BigDecimal(prices[i]).setScale(2));// 进货单价
					entity.setInit_cost_price(new BigDecimal(prices[i]).setScale(2));// 起初成本价
					entity.setCur_cost_price(new BigDecimal(prices[i]).setScale(2));// 实时成本价

					// 大类ID和名称需要转换：通过进货单明细大类pd_type_id到konka_pd_type_join_pd_class找pd_type和pd_name
					KonkaPdTypeJoinPdClass konkaPdTypeJoinPdClass = new KonkaPdTypeJoinPdClass();
					konkaPdTypeJoinPdClass.setCls_id(konkaJxcFhBillDetails.getPd_type_id());
					konkaPdTypeJoinPdClass = konkaPdTypeJoinPdClassDao.selectEntity(konkaPdTypeJoinPdClass);
					if (null != konkaPdTypeJoinPdClass) {
						entity.setPd_type(konkaPdTypeJoinPdClass.getPd_type());
						entity.setPd_type_name(konkaPdTypeJoinPdClass.getPd_name());
					}
					Long jxcPdId = jxcPdDao.insertEntity(entity);
					entity.setId(jxcPdId);
					DomainUtils.copyProperties(jxcPdCopy, entity);
				} else {// 有该型号，更新产品数量:计算实时成本价、起初成本价、进货参考价

					Float f1 = jxcPd.getCount() * jxcPd.getCur_cost_price().floatValue();// 更新前库存总价
					Float f2 = konkaJxcFhBillDetails.getCount() * new BigDecimal(prices[i]).floatValue();// 收货总价
					jxcPd.setCount(jxcPd.getCount() + konkaJxcFhBillDetails.getCount());// 产品数量=原产品数量+收货数量
					jxcPd.setCur_cost_price(new BigDecimal(df.format((f1 + f2) / jxcPd.getCount())));// 实时成本价=（进货前的库存*实时成本价+进货数量*进货价格）/总库存
					jxcPdDao.updateEntity(jxcPd);

					DomainUtils.copyProperties(jxcPdCopy, jxcPd);
				}

				JxcStockBillDetails jxcStockBillDetails = new JxcStockBillDetails();
				jxcStockBillDetails.setStock_bill_id(jxcStockBillId);
				jxcStockBillDetails.setIs_pc(0);
				jxcStockBillDetails.setShop_id(t.getShop_id());
				jxcStockBillDetails.setPd_id(jxcPdCopy.getId());// 产品型号表ID
				jxcStockBillDetails.setPd_name(jxcPdCopy.getName());
				jxcStockBillDetails.setBrand_id(jxcPdCopy.getBrand_id());
				jxcStockBillDetails.setBrand_name(jxcPdCopy.getBrand_name());
				jxcStockBillDetails.setPd_type(jxcPdCopy.getPd_type());
				jxcStockBillDetails.setPd_type_name(jxcPdCopy.getPd_type_name());
				jxcStockBillDetails.setCount(konkaJxcFhBillDetails.getCount());// 进货数量
				jxcStockBillDetails.setPrice(new BigDecimal(prices[i]).setScale(2));// 进货单价

				jxcStockBillDetailsDao.insertEntity(jxcStockBillDetails);
			}

		}

		return jxcStockBillId;
		// return null;
	}

	/** Zhang,Xufeng: 分销确认确认,插入进货单 */
	public Long createJxcStockBillByFxConfirm(JxcStockBill t) {
		Long sell_bill_id = (Long) t.getMap().get("sell_bill_id");// 销售单id
		Long supplier_id = new Long((String) t.getMap().get("supplier_id"));// 供应商id
		String supplier_name = (String) t.getMap().get("supplier_name");// 供应商名称
		// Long supplier_p_index = new Long((String) t.getMap().get("supplier_p_index"));// 供应商地区
		Integer ownSys = (Integer) t.getMap().get("own_sys");// 收货所属系统
		String[] sell_bill_detail_ids = (String[]) t.getMap().get("sell_bill_detail_ids");// 所有销售明细ID
		String[] prices = (String[]) t.getMap().get("prices");// 所有明细对应的 销售单价
		String[] counts = (String[]) t.getMap().get("counts");// 所有明细对应的 销售数量
		DecimalFormat df = new DecimalFormat("0.000000");

		JxcSellBill jxcSellBill = new JxcSellBill();
		jxcSellBill.setId(sell_bill_id);
		jxcSellBill.setIs_confirm(1);// 已确认
		jxcSellBillDao.updateEntity(jxcSellBill);// 更新 销售单状态为已确认

		// 插入进货单
		t.setIs_del(0);
		t.setSn("JH" + DateFormatUtils.format(new Date(), "yyyyMMddHHmmssSSS"));// 生成固定格式的进货单编号
		t.setIn_date(new Date());// 进货日期
		t.setPay_money(new BigDecimal("0.00"));// 初始化应付款
		t.setPaid_money(new BigDecimal("0.00"));// 初始化实付款
		for (int i = 0; i < counts.length; i++) {// 计算进货单实付和应付款(两者相等)
			t.setPay_money(t.getPay_money().add((new BigDecimal(counts[i])).multiply(new BigDecimal(prices[i])))
					.setScale(2));
		}
		t.setPaid_money(t.getPay_money());

		// 检查发货部门供应商:根据shop_id，供应商名称
		JxcSupplier jxcSupplier = new JxcSupplier();
		jxcSupplier.setShop_id(supplier_id);
		jxcSupplier.setName(supplier_name);
		jxcSupplier = jxcSupplierDao.selectEntity(jxcSupplier);
		if (null == jxcSupplier) {
			JxcSupplier newJxcSupplier = new JxcSupplier();
			newJxcSupplier.setShop_id(supplier_id);
			// newJxcSupplier.setShop_p_index(supplier_p_index);
			newJxcSupplier.setAdd_user_id(t.getAdd_user_id());
			newJxcSupplier.setName((String) t.getMap().get("supplier_name"));
			Long supplierId = jxcSupplierDao.insertEntity(newJxcSupplier);
			t.setSupplier_id(supplierId);// 进货单供应商ID
		} else {
			t.setSupplier_id(jxcSupplier.getId());// 进货单供应商ID
		}

		Long jxcStockBillId = jxcStockBillDao.insertEntity(t);// 插入进货单

		for (int i = 0; i < sell_bill_detail_ids.length; i++) {
			JxcSellBillDetails jxcSellBillDetails = new JxcSellBillDetails();
			jxcSellBillDetails.setId(Long.valueOf(sell_bill_detail_ids[i]));
			jxcSellBillDetails = jxcSellBillDetailsDao.selectEntity(jxcSellBillDetails);
			if (null != jxcSellBillDetails) {
				Long pdId = jxcSellBillDetails.getPd_id();// 型号ID

				JxcPd jxcPdCopy = new JxcPd();// 拷贝被更新或者插入的产品信息
				JxcPd jxcPd = new JxcPd();
				jxcPd.setShop_id(t.getShop_id());
				jxcPd.setOwn_sys(ownSys);
				jxcPd.setOut_sys_id(pdId);
				jxcPd = jxcPdDao.selectEntity(jxcPd);
				if (null == jxcPd) {// 没有该型号，插入新型号:计算实时成本价、起初成本价、进货参考价
					JxcPd entity = new JxcPd();
					entity.setShop_id(t.getShop_id());
					entity.setShop_p_index(t.getShop_p_index());
					entity.setName(jxcSellBillDetails.getPd_name());
					entity.setBrand_id(jxcSellBillDetails.getBrand_id());
					entity.setBrand_name(jxcSellBillDetails.getBrand_name());
					entity.setAdd_user_id(t.getAdd_user_id());
					entity.setOut_sys_id(pdId);
					entity.setOwn_sys(ownSys);// 所属系统
					entity.setCount(Long.valueOf(counts[i]));// 进货数量
					entity.setRef_price(new BigDecimal(prices[i]).setScale(2));// 进货单价
					entity.setInit_cost_price(new BigDecimal(prices[i]).setScale(2));// 起初成本价
					entity.setCur_cost_price(new BigDecimal(prices[i]).setScale(2));// 实时成本价

					// 大类ID和名称需要转换：通过进货单明细大类pd_type_id到konka_pd_type_join_pd_class找pd_type和pd_name
					KonkaPdTypeJoinPdClass konkaPdTypeJoinPdClass = new KonkaPdTypeJoinPdClass();
					konkaPdTypeJoinPdClass.setCls_id(jxcSellBillDetails.getPd_type());
					konkaPdTypeJoinPdClass = konkaPdTypeJoinPdClassDao.selectEntity(konkaPdTypeJoinPdClass);
					if (null != konkaPdTypeJoinPdClass) {
						entity.setPd_type(konkaPdTypeJoinPdClass.getPd_type());
						entity.setPd_type_name(konkaPdTypeJoinPdClass.getPd_name());
					}
					Long jxcPdId = jxcPdDao.insertEntity(entity);
					entity.setId(jxcPdId);
					DomainUtils.copyProperties(jxcPdCopy, entity);
				} else {// 有该型号，更新产品数量:计算实时成本价、起初成本价、进货参考价

					Float f1 = jxcPd.getCount() * jxcPd.getCur_cost_price().floatValue();// 更新前库存总价
					Float f2 = jxcSellBillDetails.getCount() * new BigDecimal(prices[i]).floatValue();// 收货总价
					jxcPd.setCount(jxcPd.getCount() + jxcSellBillDetails.getCount());// 产品数量=原产品数量+收货数量
					jxcPd.setCur_cost_price(new BigDecimal(df.format((f1 + f2) / jxcPd.getCount())));// 实时成本价=（进货前的库存*实时成本价+进货数量*进货价格）/总库存
					jxcPdDao.updateEntity(jxcPd);

					DomainUtils.copyProperties(jxcPdCopy, jxcPd);
				}

				JxcStockBillDetails jxcStockBillDetails = new JxcStockBillDetails();
				jxcStockBillDetails.setStock_bill_id(jxcStockBillId);
				jxcStockBillDetails.setIs_pc(0);
				jxcStockBillDetails.setShop_id(t.getShop_id());
				jxcStockBillDetails.setPd_id(jxcPdCopy.getId());// 产品型号表ID
				jxcStockBillDetails.setPd_name(jxcPdCopy.getName());
				jxcStockBillDetails.setBrand_id(jxcPdCopy.getBrand_id());
				jxcStockBillDetails.setBrand_name(jxcPdCopy.getBrand_name());
				jxcStockBillDetails.setPd_type(jxcPdCopy.getPd_type());
				jxcStockBillDetails.setPd_type_name(jxcPdCopy.getPd_type_name());
				jxcStockBillDetails.setCount(jxcSellBillDetails.getCount());// 进货数量
				jxcStockBillDetails.setPrice(new BigDecimal(prices[i]).setScale(2));// 进货单价

				jxcStockBillDetailsDao.insertEntity(jxcStockBillDetails);
			}

		}

		return jxcStockBillId;
	}

}
