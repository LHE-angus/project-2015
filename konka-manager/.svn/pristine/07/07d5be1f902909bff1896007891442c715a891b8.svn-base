package com.ebiz.mmt.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaOrderInfoDao;
import com.ebiz.mmt.dao.KonkaOrderInfoDetailsDao;
import com.ebiz.mmt.dao.KonkaXxSellBillDao;
import com.ebiz.mmt.dao.KonkaXxSellBillDetailsDao;
import com.ebiz.mmt.dao.KonkaXxSellBillDetailsDstDao;
import com.ebiz.mmt.dao.KonkaXxZmdSalaryDao;
import com.ebiz.mmt.domain.KonkaOrderInfo;
import com.ebiz.mmt.domain.KonkaOrderInfoDetails;
import com.ebiz.mmt.domain.KonkaXxSellBill;
import com.ebiz.mmt.domain.KonkaXxSellBillDetails;
import com.ebiz.mmt.domain.KonkaXxSellBillDetailsDst;
import com.ebiz.mmt.domain.KonkaXxZmdSalary;
import com.ebiz.mmt.service.KonkaXxSellBillService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2012-01-09 21:48:11
 */
@Service
public class KonkaXxSellBillServiceImpl implements KonkaXxSellBillService {

	private static HashMap<String, String> properties = new HashMap<String, String>();

	static {
		InputStream inputStream = InteractWebServiceImpl.class.getClassLoader().getResourceAsStream(
				"i18n/messages.properties");
		Properties p = new Properties();
		try {
			p.load(inputStream);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		for (Object key : p.keySet()) {
			properties.put((String) key, (String) p.get(key));
		}
	}

	// @Resource
	// private KonkaXxSystemMessageService msgService;

	@Resource
	private KonkaXxSellBillDao konkaXxSellBillDao;

	@Resource
	private KonkaXxSellBillDetailsDao konkaXxSellBillDetailsDao;

	// @Resource
	// private JfScortsDetailsDao jfScortsDetailsDao;

	@Resource
	private KonkaXxSellBillDetailsDstDao konkaXxSellBillDetailsDstDao;

	// @Resource
	// private JfScortsDao jfScortsDao;
	//
	// @Resource
	// private JfScortsDetailsHisDao jfScortsDetailsHisDao;

	public Long createKonkaXxSellBill(KonkaXxSellBill t) {
		return this.konkaXxSellBillDao.insertEntity(t);
	}

	public KonkaXxSellBill getKonkaXxSellBill(KonkaXxSellBill t) {
		return this.konkaXxSellBillDao.selectEntity(t);
	}

	public Long getKonkaXxSellBillCount(KonkaXxSellBill t) {
		return this.konkaXxSellBillDao.selectEntityCount(t);
	}

	public List<KonkaXxSellBill> getKonkaXxSellBillList(KonkaXxSellBill t) {
		return this.konkaXxSellBillDao.selectEntityList(t);
	}

	public int modifyKonkaXxSellBill(KonkaXxSellBill t) {
		this.konkaXxSellBillDao.updateEntity(t);

		// KonkaXxSellBill kxsb = new KonkaXxSellBill();
		// kxsb.setSell_bill_id(t.getSell_bill_id());
		// kxsb = this.konkaXxSellBillDao.selectEntity(kxsb);
		//
		// String wlqr = (String) t.getMap().get("wlqr");
		// if (!"true".equals(wlqr)) { // 确认物流费用不添加消息
		// msgService.messageToRemindTrigger(t);
		// }

		return 0;
	}

	// @Resource
	// private KonkaXxZmdDao konkaXxZmdDao;
	//
	// @Resource
	// private KonkaXxZmdAccDetailsDao konkaXxZmdAccDetailsDao;

	/**
	 * @author Ren,zhong
	 * @version 2012-04-06
	 * @desc 财务审核销售单 & 发货
	 */
	public int modifyKonkaXxSellBillForAuditOrder(KonkaXxSellBill t) {
		this.konkaXxSellBillDao.updateEntity(t);

		KonkaXxSellBillDetails details = t.getKonkaXxSellBillDetails();
		if (null != details) {
			this.konkaXxSellBillDetailsDao.updateEntity(details);
		}

		// 添加消息提醒【提醒分公司管理员确认物流费用和发货】；收入支出记录
		String[] pkss = (String[]) t.getMap().get("pkss");
		if (null != pkss && pkss.length > 0) { // 批量审核
			for (int i = 0; i < pkss.length; i++) {
				KonkaXxSellBill kxsb = new KonkaXxSellBill();
				kxsb.setSell_bill_id(Long.valueOf(pkss[i]));
				kxsb = this.konkaXxSellBillDao.selectEntity(kxsb);

				// if ("20".equals(kxsb.getSell_state().toString()) ||
				// "30".equals(kxsb.getSell_state().toString())) {
				// KonkaXxZmd xxZmd = new KonkaXxZmd();
				// xxZmd.setZmd_id(kxsb.getZmd_id());
				// xxZmd.setArc_state(20300);
				// xxZmd = this.konkaXxZmdDao.selectEntity(xxZmd);
				// if (null != xxZmd) {
				// Double total_money = kxsb.getTotal_money().doubleValue();
				// Double total_credit_line =
				// xxZmd.getTotal_credit_line().doubleValue();
				// Double temp = total_credit_line - total_money;
				//
				// // 更新专卖店余额
				// KonkaXxZmd zmd = new KonkaXxZmd();
				// zmd.setZmd_id(kxsb.getZmd_id());
				// zmd.setTotal_credit_line(BigDecimal.valueOf(temp));
				// this.konkaXxZmdDao.updateEntity(zmd);
				// // 插入收入支出记录
				// KonkaXxZmdAccDetails accDetails = new KonkaXxZmdAccDetails();
				// accDetails.setDept_id(kxsb.getDept_id());
				// accDetails.setZmd_id(kxsb.getZmd_id());
				// accDetails.setDo_man_user_id((Long)
				// t.getMap().get("audit_uesr_id"));
				// accDetails.setDo_man((String)
				// t.getMap().get("audit_user_name"));
				// accDetails.setDo_time(new Date());
				// accDetails.setLeft_money(BigDecimal.valueOf(temp));
				// accDetails.setMoney(BigDecimal.valueOf(total_money));
				// accDetails.setNote_type(-1);
				// String memo =
				// properties.get("konka.xx.zmd.audit.order.acc.details");
				// memo = StringUtils.replace(memo, "a",
				// StringUtils.leftPad(kxsb.getSell_bill_id().toString(),
				// 12, "0"));
				// accDetails.setMemo(memo);
				// this.konkaXxZmdAccDetailsDao.insertEntity(accDetails);
				// }
				// }
				//
				// msgService.messageToRemindTrigger(kxsb);
			}
		} else { // 单个审核
			// KonkaXxSellBill kxsb = new KonkaXxSellBill();
			// kxsb.setSell_bill_id(t.getSell_bill_id());
			// kxsb = this.konkaXxSellBillDao.selectEntity(kxsb);
			//
			// if ("20".equals(kxsb.getSell_state().toString()) ||
			// "30".equals(kxsb.getSell_state().toString())) { //
			// 【审核通过】或者【直接发货】
			// KonkaXxZmd xxZmd = new KonkaXxZmd();
			// xxZmd.setZmd_id(kxsb.getZmd_id());
			// xxZmd.setArc_state(20300);
			// xxZmd = this.konkaXxZmdDao.selectEntity(xxZmd);
			// if (null != xxZmd) {
			// Double total_money = kxsb.getTotal_money().doubleValue();
			// Double total_credit_line =
			// xxZmd.getTotal_credit_line().doubleValue();
			// Double temp = total_credit_line - total_money;
			//
			// // 更新专卖店余额
			// KonkaXxZmd zmd = new KonkaXxZmd();
			// zmd.setZmd_id(kxsb.getZmd_id());
			// zmd.setTotal_credit_line(BigDecimal.valueOf(temp));
			// this.konkaXxZmdDao.updateEntity(zmd);
			// // 插入收入支出记录
			// KonkaXxZmdAccDetails accDetails = new KonkaXxZmdAccDetails();
			// accDetails.setDept_id(kxsb.getDept_id());
			// accDetails.setZmd_id(kxsb.getZmd_id());
			// accDetails.setDo_man_user_id((Long)
			// t.getMap().get("audit_uesr_id"));
			// accDetails.setDo_man((String) t.getMap().get("audit_user_name"));
			// accDetails.setDo_time(new Date());
			// accDetails.setLeft_money(BigDecimal.valueOf(temp));
			// accDetails.setMoney(BigDecimal.valueOf(total_money));
			// accDetails.setNote_type(-1);
			// String memo =
			// properties.get("konka.xx.zmd.audit.order.acc.details");
			// memo = StringUtils.replace(memo, "a",
			// StringUtils.leftPad(kxsb.getSell_bill_id().toString(), 12,
			// "0"));
			// accDetails.setMemo(memo);
			// this.konkaXxZmdAccDetailsDao.insertEntity(accDetails);
			// }
			// }

			// // 积分状态更新
			// if ("20".equals(t.getSell_state().toString()) ||
			// "30".equals(t.getSell_state().toString())) {
			// JfScortsDetails jfScortsDetails = new JfScortsDetails();
			// jfScortsDetails.setOut_sys_id(t.getSell_bill_id().toString());
			// jfScortsDetails.setOut_sys_name("KONKA_XX_SELL_BILL");
			// jfScortsDetails.setUser_sn(t.getConsumer_grade_num());
			// List<JfScortsDetails> jfScortsDetailsList =
			// this.jfScortsDetailsDao.selectEntityList(jfScortsDetails);
			// if (jfScortsDetailsList.size() > 0) {
			// for (JfScortsDetails temp : jfScortsDetailsList) {
			// // 更新状态
			// JfScortsDetails jfScortsDetails2 = new JfScortsDetails();
			// jfScortsDetails2.setId(temp.getId());
			// jfScortsDetails2.setStatus(1);
			// this.jfScortsDetailsDao.updateEntity(jfScortsDetails2);
			//
			// // 插入历史表
			// JfScortsDetailsHis his = new JfScortsDetailsHis();
			// his.setDetails_id(temp.getId());
			// his.setUser_sn(temp.getUser_sn());
			// his.setDept_id(temp.getDept_id());
			// his.setPd_id(temp.getPd_id());
			// his.setJf_cate(1);
			// his.setScorts(temp.getScorts());
			// if (null != temp.getUpdate_result()) {
			// his.setUpdate_result(temp.getUpdate_result());
			// }
			// his.setAdd_user_id(temp.getAdd_user_id());
			// if (null != temp.getRemark()) {
			// his.setRemark(temp.getRemark());
			// }
			// his.setStatus(temp.getStatus());
			// his.setOut_sys_name(temp.getOut_sys_name());
			// his.setOut_sys_id(temp.getOut_sys_id());
			//
			// this.jfScortsDetailsHisDao.insertEntity(his);
			//
			// // 修改总积分
			// JfScorts jfScorts = new JfScorts();
			// jfScorts.setUser_sn(temp.getUser_sn());
			//
			// List<JfScorts> jfScortsList =
			// this.jfScortsDao.selectEntityList(jfScorts);
			//
			// if (null == jfScortsList || jfScortsList.size() == 0) {
			// jfScorts.setTotal_scorts(temp.getScorts());
			// this.jfScortsDao.insertEntity(jfScorts);
			// } else {
			// jfScorts = jfScortsList.get(0);
			// jfScorts.setTotal_scorts(jfScorts.getTotal_scorts().add(temp.getScorts()));
			// this.jfScortsDao.updateEntity(jfScorts);
			// }
			// }
			// }
			// }
			// msgService.messageToRemindTrigger(t);
		}

		return 0;
	}

	public int removeKonkaXxSellBill(KonkaXxSellBill t) {
		return this.konkaXxSellBillDao.deleteEntity(t);
	}

	public List<KonkaXxSellBill> getKonkaXxSellBillPaginatedList(KonkaXxSellBill t) {
		return this.konkaXxSellBillDao.selectEntityPaginatedList(t);
	}

	/**
	 * @author Ren, zhong
	 * @version 2012-01-10
	 * @desc 添加销售单
	 */
	public Long createKonkaXxSellBillWithDetails(KonkaXxSellBill t) {
		Long sell_bill_id = this.konkaXxSellBillDao.insertEntity(t);
		List<KonkaXxSellBillDetails> konkaXxSellBillDetailList = t.getKonkaXxSellBillDetailList();

		// List<JfScortsDetails> jfScortsDetailsList =
		// t.getJfScortsDetailsList();

		if (null != konkaXxSellBillDetailList && konkaXxSellBillDetailList.size() > 0) {
			for (KonkaXxSellBillDetails kxsbd : konkaXxSellBillDetailList) {
				kxsbd.setSell_bill_id(sell_bill_id);
				Long sell_bill_details_id = this.konkaXxSellBillDetailsDao.insertEntity(kxsbd);

				List<KonkaXxSellBillDetailsDst> konkaXxSellBillDetailsDstList = kxsbd
						.getKonkaXxSellBillDetailsDstList();
				if (null != konkaXxSellBillDetailsDstList && konkaXxSellBillDetailsDstList.size() > 0) {
					for (KonkaXxSellBillDetailsDst kxsbdd : konkaXxSellBillDetailsDstList) {
						kxsbdd.setSell_bill_id(sell_bill_id);
						kxsbdd.setSell_bill_details_id(sell_bill_details_id);
						this.konkaXxSellBillDetailsDstDao.insertEntity(kxsbdd);
					}
				}
			}
		}

		// 积分
		// if (null != jfScortsDetailsList && jfScortsDetailsList.size() > 0) {
		// // 删除积分
		// JfScortsDetails jfScortsDetails1 = new JfScortsDetails();
		// jfScortsDetails1.setUser_sn(jfScortsDetailsList.get(0).getUser_sn());
		// jfScortsDetails1.setOut_sys_id(sell_bill_id.toString());
		// jfScortsDetails1.setOut_sys_name(jfScortsDetailsList.get(0).getOut_sys_name());
		// jfScortsDetails1.setJf_cate(1);
		// jfScortsDetails1.setDept_id(t.getDept_id());
		// jfScortsDetails1.getMap().put("is_sell_score_edit", true);
		// this.jfScortsDetailsDao.deleteEntity(jfScortsDetails1);
		//
		// for (JfScortsDetails temp : jfScortsDetailsList) {
		// if (t.getSell_state() == 70 && "70".equals(t.getSell_state())) {
		//
		// temp.setOut_sys_id(sell_bill_id.toString());
		// temp.setAdd_date(new Date());
		// temp.setJf_cate(1);
		// Long id = this.jfScortsDetailsDao.insertEntity(temp);
		//
		// // 插入历史表
		// JfScortsDetailsHis his = new JfScortsDetailsHis();
		// his.setDetails_id(id);
		// his.setUser_sn(temp.getUser_sn());
		// his.setDept_id(temp.getDept_id());
		// his.setPd_id(temp.getPd_id());
		// his.setJf_cate(1);
		// his.setScorts(temp.getScorts());
		// if (null != temp.getUpdate_result()) {
		// his.setUpdate_result(temp.getUpdate_result());
		// }
		// his.setAdd_user_id(temp.getAdd_user_id());
		// if (null != temp.getRemark()) {
		// his.setRemark(temp.getRemark());
		// }
		// his.setStatus(temp.getStatus());
		// his.setOut_sys_name(temp.getOut_sys_name());
		// his.setOut_sys_id(temp.getOut_sys_id());
		//
		// this.jfScortsDetailsHisDao.insertEntity(his);
		//
		// // 修改总积分
		// if (temp.getStatus() == 1) {
		//
		// JfScorts jfScorts = new JfScorts();
		// jfScorts.setUser_sn(temp.getUser_sn());
		//
		// List<JfScorts> jfScortsList =
		// this.jfScortsDao.selectEntityList(jfScorts);
		//
		// if (null == jfScortsList || jfScortsList.size() == 0) {
		// jfScorts.setTotal_scorts(temp.getScorts());
		// this.jfScortsDao.insertEntity(jfScorts);
		// } else {
		// jfScorts = jfScortsList.get(0);
		// jfScorts.setTotal_scorts(jfScorts.getTotal_scorts().add(temp.getScorts()));
		// this.jfScortsDao.updateEntity(jfScorts);
		// }
		// }
		//
		// } else {// 新增积分
		// temp.setOut_sys_id(sell_bill_id.toString());
		// temp.setAdd_date(new Date());
		// temp.setJf_cate(1);
		// this.jfScortsDetailsDao.insertEntity(temp);
		// }
		// }
		// }

		// 添加提醒消息【提醒分公司财务审核】
		t.setSell_bill_id(sell_bill_id);
		// msgService.messageToRemindTrigger(t);

		return sell_bill_id;
	}

	
	
	
	/**
	 * @author Ren, zhong
	 * @version 2012-03-05
	 */
	public int modifyKonkaXxSellBillWithDetails(KonkaXxSellBill t,KonkaOrderInfo t2) {
		Long sell_bill_id = t.getSell_bill_id();

		KonkaXxSellBill sb = new KonkaXxSellBill();
		sb.setSell_bill_id(sell_bill_id);
		sb.setOrder_type(t.getOrder_type());
		sb = this.konkaXxSellBillDao.selectEntity(sb);
		if ("20".equals(sb.getSell_state().toString())) {
			t.setSell_state(20L);
		}
		this.konkaXxSellBillDao.updateEntity(t);

		if (null != sell_bill_id) {
			KonkaXxSellBillDetails billDetails = new KonkaXxSellBillDetails();
			billDetails.setSell_bill_id(sell_bill_id);
			this.konkaXxSellBillDetailsDao.deleteEntity(billDetails);

			KonkaXxSellBillDetailsDst sellBillDetailsDst = new KonkaXxSellBillDetailsDst();
			sellBillDetailsDst.setSell_bill_id(sell_bill_id);
			this.konkaXxSellBillDetailsDstDao.deleteEntity(sellBillDetailsDst);

			List<KonkaXxSellBillDetails> konkaXxSellBillDetailList = t.getKonkaXxSellBillDetailList();
			if (null != konkaXxSellBillDetailList && konkaXxSellBillDetailList.size() > 0) {
				for (KonkaXxSellBillDetails kxsbd : konkaXxSellBillDetailList) {
					kxsbd.setSell_bill_id(sell_bill_id);
					Long sell_bill_details_id = this.konkaXxSellBillDetailsDao.insertEntity(kxsbd);

					List<KonkaXxSellBillDetailsDst> konkaXxSellBillDetailsDstList = kxsbd
							.getKonkaXxSellBillDetailsDstList();
					if (null != konkaXxSellBillDetailsDstList && konkaXxSellBillDetailsDstList.size() > 0) {
						for (KonkaXxSellBillDetailsDst kxsbdd : konkaXxSellBillDetailsDstList) {
							kxsbdd.setSell_bill_id(sell_bill_id);
							kxsbdd.setSell_bill_details_id(sell_bill_details_id);
							this.konkaXxSellBillDetailsDstDao.insertEntity(kxsbdd);
						}
					}
				}
			}
		}

		//R3客户进货单
//		t2.setAdd_date(new Date());
//		t2.setZmd_order_flag(1);
//		t2.setZmd_order_num(sell_bill_id);
//		Long order_id = konkaOrderInfoDao.insertEntity(t2);
		
//		List<KonkaOrderInfoDetails> konkaOrderInfoDetailsList = t2.getKonkaOrderInfoDetailsList();
//		for (KonkaOrderInfoDetails konkaOrderInfoDetails : konkaOrderInfoDetailsList) {
//			konkaOrderInfoDetails.setOrder_id(order_id);
//			konkaOrderInfoDetailsDao.insertEntity(konkaOrderInfoDetails);// 添加订单详细产品
//		}
		
		return sell_bill_id.intValue();
	}

	/**
	 * @author Li,Yuan
	 * @version 2012-01-11
	 */
	public Long getKonkaXxSellBillCountForJs(KonkaXxSellBill t) {
		return this.konkaXxSellBillDao.selectKonkaXxSellBillCountForJs(t);
	}

	/**
	 * @author Li,Yuan
	 * @version 2012-01-11
	 */
	public List<KonkaXxSellBill> getKonkaXxSellBillForJSPaginatedList(KonkaXxSellBill t) {
		return this.konkaXxSellBillDao.selectKonkaXxSellBillForJSPaginatedList(t);
	}

	/**
	 * @author hu,hao
	 * @version 2012-03-03
	 */
	public Long getKonkaXxSellBillForDeptCount(KonkaXxSellBill t) {
		return this.konkaXxSellBillDao.selectKonkaXxSellBillForDeptCount(t);
	}

	/**
	 * @author hu,hao
	 * @version 2012-03-03
	 */
	public List<KonkaXxSellBill> getKonkaXxSellBillForDeptPaginatedList(KonkaXxSellBill t) {
		return this.konkaXxSellBillDao.selectKonkaXxSellBillForDeptPaginatedList(t);
	}

	/**
	 * @author Jiang,JiaYong
	 * @version 2012-03-20
	 */
	public String getKonkaXxSellBillTotalMoneySum(KonkaXxSellBill t) {
		return this.konkaXxSellBillDao.selectKonkaXxSellBillTotalMoneySum(t);
	}

	/**
	 * @author Wu,ShangLong
	 * @version 2012-03-20
	 */
	public List<KonkaXxSellBill> getKonkaXxSellBillAndDetailList(KonkaXxSellBill t) {
		return this.konkaXxSellBillDao.selectKonkaXxSellBillAndDetailList(t);
	}

	@Resource
	private KonkaXxZmdSalaryDao konkaXxZmdSalaryDao;

	public int modifyKonkaXxSellBillWithJsRebate(KonkaXxSellBill t) {
		this.konkaXxSellBillDao.updateEntity(t);

		String sell_bill_id = null;
		if (null != t.getSell_bill_id())
			sell_bill_id = t.getSell_bill_id().toString();
		String[] pks = (String[]) t.getMap().get("pks");

		KonkaXxZmdSalary salary = t.getKonkaXxZmdSalary();
		if (null != salary) {
			if (StringUtils.isNotBlank(sell_bill_id)) {
				KonkaXxSellBill bill = new KonkaXxSellBill();
				bill.setSell_bill_id(Long.valueOf(sell_bill_id));
				bill = this.konkaXxSellBillDao.selectEntity(bill);
				if (null != bill) {
					salary.setFee(bill.getJs_bill_money());
				}
			} else if (ArrayUtils.isNotEmpty(pks)) {
				Double temp = 0D;
				for (int i = 0; i < pks.length; i++) {
					KonkaXxSellBill b = new KonkaXxSellBill();
					b.setSell_bill_id(Long.valueOf(pks[i]));
					b = this.konkaXxSellBillDao.selectEntity(b);
					if (null != b) {
						temp = temp + b.getJs_bill_money().doubleValue();
					}
				}
				salary.setFee(BigDecimal.valueOf(temp));
			}
			this.konkaXxZmdSalaryDao.insertEntity(salary);
		}

		return 0;
	}

	/**
	 * @author Jiang,JiaYong
	 * @version 2012-04-06
	 */
	public Long getKonkaXxSellBillNumSum(KonkaXxSellBill t) {
		return this.konkaXxSellBillDao.selectKonkaXxSellBillNumSum(t);
	}

	/**
	 * @author Jiang,JiaYong
	 * @version 2012-04-06
	 */
	public KonkaXxSellBill getKonkaXxSellBillMoneySum(KonkaXxSellBill t) {
		return this.konkaXxSellBillDao.selectKonkaXxSellBillMoneySum(t);
	}

	/**
	 * @author Jiang,JiaYong
	 * @version 2012-04-06
	 */
	public List<KonkaXxSellBill> getKonkaXxSellBillDeptPdSellMoneySumList(KonkaXxSellBill t) {
		return this.konkaXxSellBillDao.selectKonkaXxSellBillDeptPdSellMoneySumList(t);
	}

	/**
	 * @author Jiang,JiaYong
	 * @version 2012-04-06
	 */
	public List<KonkaXxSellBill> getKonkaXxSellBillDeptPdSellNumSumList(KonkaXxSellBill t) {
		return this.konkaXxSellBillDao.selectKonkaXxSellBillDeptPdSellNumSumList(t);
	}

	/**
	 * @author Ren,zhong
	 * @version 2012-04-12
	 * @desc 物流发货
	 */
	public int modifyKonkaXxSellBillWithDetailsLock(KonkaXxSellBill t) {
		this.konkaXxSellBillDao.updateEntity(t);

		KonkaXxSellBillDetails details = t.getKonkaXxSellBillDetails();
		if (null != details) {
			this.konkaXxSellBillDetailsDao.updateEntity(details);
		}

		return 0;
	}

	/**
	 * @author Jiang,JiaYong
	 * @version 2012-05-07
	 */
	public List<KonkaXxSellBill> getKonkaXxSellBillListForCountNumSumMoney(KonkaXxSellBill t) {
		return this.konkaXxSellBillDao.selectKonkaXxSellBillListForCountNumSumMoney(t);
	}

	/**
	 * @author Hu,Hao
	 * @version 2013-03-23
	 */
	public String getKonkaXxSellBillTotalMoneySumHd(KonkaXxSellBill t) {
		return (String) this.konkaXxSellBillDao.selectKonkaXxSellBillTotalMoneySumHd(t);
	}
	
	
	@Resource
	private KonkaOrderInfoDao konkaOrderInfoDao;

	@Resource
	private KonkaOrderInfoDetailsDao konkaOrderInfoDetailsDao;
	
	/**
	 * @author Hu,Hao
	 * @version 2013-10-31
	 */
	public Long createKonkaXxSellBillWithOrderInfo(KonkaXxSellBill t1,KonkaOrderInfo t2){
		//专卖店订单
		Long sell_bill_id = this.konkaXxSellBillDao.insertEntity(t1);
		List<KonkaXxSellBillDetails> konkaXxSellBillDetailList = t1.getKonkaXxSellBillDetailList();

		if (null != konkaXxSellBillDetailList && konkaXxSellBillDetailList.size() > 0) {
			for (KonkaXxSellBillDetails kxsbd : konkaXxSellBillDetailList) {
				kxsbd.setSell_bill_id(sell_bill_id);
				Long sell_bill_details_id = this.konkaXxSellBillDetailsDao.insertEntity(kxsbd);

				List<KonkaXxSellBillDetailsDst> konkaXxSellBillDetailsDstList = kxsbd
						.getKonkaXxSellBillDetailsDstList();
				if (null != konkaXxSellBillDetailsDstList && konkaXxSellBillDetailsDstList.size() > 0) {
					for (KonkaXxSellBillDetailsDst kxsbdd : konkaXxSellBillDetailsDstList) {
						kxsbdd.setSell_bill_id(sell_bill_id);
						kxsbdd.setSell_bill_details_id(sell_bill_details_id);
						this.konkaXxSellBillDetailsDstDao.insertEntity(kxsbdd);
					}
				}
			}
		}
		t1.setSell_bill_id(sell_bill_id);

		//R3客户进货单
//		t2.setAdd_date(new Date());
//		t2.setZmd_order_flag(1);
//		t2.setZmd_order_num(sell_bill_id);
//		Long order_id = konkaOrderInfoDao.insertEntity(t2);

//		List<KonkaOrderInfoDetails> konkaOrderInfoDetailsList = t2.getKonkaOrderInfoDetailsList();
//		for (KonkaOrderInfoDetails konkaOrderInfoDetails : konkaOrderInfoDetailsList) {
//			konkaOrderInfoDetails.setOrder_id(order_id);
//			konkaOrderInfoDetailsDao.insertEntity(konkaOrderInfoDetails);// 添加订单详细产品
//		}
		
		return sell_bill_id;
	}
	
	
	
	//查详情信息
	@Override
	public	List<KonkaXxSellBill> getKonkaXxSellBillPaginatedListfordetails(KonkaXxSellBill entity){
		return this.konkaXxSellBillDao.selectKonkaXxSellBillPaginatedListfordetails(entity);
	}
	//查详情信息统计
	@Override
	public Long getKonkaXxSellBillCountfordetails(KonkaXxSellBill entity) {
		
		return this.konkaXxSellBillDao.selectKonkaXxSellBillCountfordetails(entity);
	}
}
