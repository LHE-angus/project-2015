package com.ebiz.mmt.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.JnhmSelledPdCodeDao;
import com.ebiz.mmt.dao.JxcCustomerDao;
import com.ebiz.mmt.dao.JxcJnhmSellBillAuditDao;
import com.ebiz.mmt.dao.JxcPdDao;
import com.ebiz.mmt.dao.JxcSellBillDao;
import com.ebiz.mmt.dao.JxcSellBillDetailsDao;
import com.ebiz.mmt.dao.JxcUseNojdxxShopDao;
import com.ebiz.mmt.domain.JnhmSelledPdCode;
import com.ebiz.mmt.domain.JxcJnhmSellBillAudit;
import com.ebiz.mmt.domain.JxcPd;
import com.ebiz.mmt.domain.JxcSellBill;
import com.ebiz.mmt.domain.JxcSellBillDetails;
import com.ebiz.mmt.domain.JxcUseNojdxxShop;
import com.ebiz.mmt.service.JxcSellBillService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2011-03-03 09:42:37
 */
@Service
public class JxcSellBillServiceImpl implements JxcSellBillService {

	@Resource
	private JxcSellBillDao jxcSellBillDao;

	@Resource
	private JxcSellBillDetailsDao jxcSellBillDetailsDao;

	@Resource
	private JxcCustomerDao jxcCustomerDao;

	@Resource
	private JxcPdDao jxcPdDao;

	@Resource
	private JxcUseNojdxxShopDao jxcUseNojdxxShopDao;

	@Resource
	private JnhmSelledPdCodeDao jnhmSelledPdCodeDao;

	@Resource
	private JxcJnhmSellBillAuditDao jxcJnhmSellBillAuditDao;

	public Long createJxcSellBill(JxcSellBill t) {
		JxcUseNojdxxShop jxcUseNojdxxShop = new JxcUseNojdxxShop();
		jxcUseNojdxxShop.setShop_id(t.getShop_id());
		Long num = this.jxcUseNojdxxShopDao.selectEntityCount(jxcUseNojdxxShop);
		if (num.intValue() <= 0) {
			jxcUseNojdxxShop.setShop_p_index(t.getShop_p_index());
			jxcUseNojdxxShop.setAdd_date(t.getAdd_date());
			this.jxcUseNojdxxShopDao.insertEntity(jxcUseNojdxxShop);
		}
		if (null != t.getJxcCustomer()) {// 处理客户信息
			t.getJxcCustomer().setCur_pay(t.getJxcCustomer().getCur_pay().add(t.getMoney()).subtract(t.getPay_money()));
			if (null == t.getJxcCustomer().getId()) {// 添加新客户
				Long customer_id = this.jxcCustomerDao.insertEntity(t.getJxcCustomer());
				t.setCustomer_id(customer_id);
			} else {// 更新客户信息
				this.jxcCustomerDao.updateEntity(t.getJxcCustomer());
			}

		}
		Long sell_id = this.jxcSellBillDao.insertEntity(t);
		// if (!t.getMoney().equals(t.getPay_money())) {
		// JxcCustomer customer = new JxcCustomer();
		// customer.setId(t.getCustomer_id());
		// customer.setIs_del(0);
		// customer = this.jxcCustomerDao.selectEntity(customer);
		// if (null != customer) {
		// customer.setCur_pay(customer.getCur_pay().add(t.getMoney()).subtract(t.getPay_money()));
		// this.jxcCustomerDao.updateEntity(customer);
		// }
		// }

		List<JxcSellBillDetails> sellBillDetailList = (List<JxcSellBillDetails>) t.getSellBillDetailList();
		for (JxcSellBillDetails jxcSellBillDetails : sellBillDetailList) {
			jxcSellBillDetails.setSell_bill_id(sell_id);
			// jxcSellBillDetails.setSell_src(0);// 销售来源：默认进销存
			Long sell_detail_id = this.jxcSellBillDetailsDao.insertEntity(jxcSellBillDetails);

			List<JnhmSelledPdCode> pdCodeList = jxcSellBillDetails.getJnhmSelledPdCodeList();
			for (JnhmSelledPdCode jspc : pdCodeList) {
				jspc.setSell_bill_id(sell_id);
				jspc.setSell_bill_details_id(sell_detail_id);
				this.jnhmSelledPdCodeDao.insertEntity(jspc);
			}

			JxcPd pd = new JxcPd();
			pd.setId(jxcSellBillDetails.getPd_id());
			pd.setIs_del(0);
			pd = this.jxcPdDao.selectEntity(pd);
			if (null != pd) {
				pd.setCount(pd.getCount() - jxcSellBillDetails.getCount());
				this.jxcPdDao.updateEntity(pd);
			}
		}

		return sell_id;
	}

	public JxcSellBill getJxcSellBill(JxcSellBill t) {
		return this.jxcSellBillDao.selectEntity(t);
	}

	public Long getJxcSellBillCount(JxcSellBill t) {
		return this.jxcSellBillDao.selectEntityCount(t);
	}

	public List<JxcSellBill> getJxcSellBillList(JxcSellBill t) {
		return this.jxcSellBillDao.selectEntityList(t);
	}

	public int modifyJxcSellBill(JxcSellBill t) {
		return this.jxcSellBillDao.updateEntity(t);
	}

	public int removeJxcSellBill(JxcSellBill t) {
		return this.jxcSellBillDao.deleteEntity(t);
	}

	public List<JxcSellBill> getJxcSellBillPaginatedList(JxcSellBill t) {
		return this.jxcSellBillDao.selectEntityPaginatedList(t);
	}

	/**
	 * @author Du,HongGang
	 * @version 2011-03-04
	 */
	public Long getJxcSellBillCountForXS(JxcSellBill t) {
		return this.jxcSellBillDao.selectJxcSellBillCountForXS(t);
	}

	/**
	 * @author Du,HongGang
	 * @version 2011-03-04
	 */
	public List<JxcSellBill> getJxcSellBillPaginatedListForXS(JxcSellBill t) {
		return this.jxcSellBillDao.selectJxcSellBillPaginatedListForXS(t);
	}

	/**
	 * @author Du,HongGang
	 * @version 2011-03-04
	 */
	public JxcSellBill getJxcSellBillForSZ(JxcSellBill t) {
		return this.jxcSellBillDao.selectJxcSellBillForSZ(t);
	}

	/**
	 * @author Du,HongGang
	 * @version 2011-03-07
	 */
	public List<JxcSellBill> getJxcSellBillForSZMX(JxcSellBill t) {
		return this.jxcSellBillDao.selectJxcSellBillForSZMX(t);
	}

	/**
	 * @author Du,HongGang
	 * @version 2011-03-09
	 */
	public List<JxcSellBill> getJxcSellBillListForXS(JxcSellBill t) {
		return this.jxcSellBillDao.selectJxcSellBillListForXS(t);
	}

	/**
	 * 作战地图.区域网点销售统计
	 * 
	 * @author Cheng,Bing
	 * @version 2011-11-15
	 */
	public List<JxcSellBill> getJxcSellBillForArea(JxcSellBill t) {
		return this.jxcSellBillDao.selectJxcSellBillForArea(t);
	}

	/**
	 * 作战地图.网点销售统计
	 * 
	 * @author Cheng,Bing
	 * @version 2011-11-15
	 */
	public List<JxcSellBill> getJxcSellBillForShop(JxcSellBill t) {
		return this.jxcSellBillDao.selectJxcSellBillForShop(t);
	}

	/**
	 * 作战地图.销售总数量统计
	 * 
	 * @author wang,yang
	 * @version 2012-05-22
	 */
	public Long getJxcSellBillDetailsCountSum(JxcSellBill t) {
		return this.jxcSellBillDao.selectJxcSellBillDetailsCountSum(t);
	}

	/**
	 * 作战地图.销售总金额统计
	 * 
	 * @author wang,yang
	 * @version 2012-05-22
	 */
	public Long getJxcSellBillMoneySum(JxcSellBill t) {
		return this.jxcSellBillDao.selectJxcSellBillMoneySum(t);
	}

	/**
	 * 作战地图.R3销售数量统计
	 * 
	 * @author wang,yang
	 * @version 2012-05-22
	 */
	public Long getJxcSellBillDetailsCountSumForR3(JxcSellBill t) {
		return this.jxcSellBillDao.selectJxcSellBillDetailsCountSumForR3(t);
	}

	public List<JxcSellBill> getJxcSellBillListForJnhm(JxcSellBill t) {
		return this.jxcSellBillDao.selectJxcSellBillListForJnhm(t);
	}

	/**
	 * @author Hu,Hao
	 * @version 2012-08-16
	 */
	public Long getJxcSellBillForJnhmAuditCount(JxcSellBill t) {
		return this.jxcSellBillDao.selectJxcSellBillForJnhmAuditCount(t);
	}

	/**
	 * @author Hu,Hao
	 * @version 2012-08-16
	 */
	public List<JxcSellBill> getJxcSellBillForJnhmAuditPaginatedList(JxcSellBill t) {
		return this.jxcSellBillDao.selectJxcSellBillForJnhmAuditPaginatedList(t);
	}

	/**
	 * @author Hu,Hao
	 * @version 2012-08-22
	 */
	public List<JxcSellBill> getJxcSellBillForJnhmAuditList(JxcSellBill t) {
		return this.jxcSellBillDao.selectJxcSellBillForJnhmAuditList(t);
	}

	/**
	 * @author Hu,Hao
	 * @version 2012-08-22
	 */
	// 节能惠民一键审核
	public void jxcSellBillAudit(JxcSellBill t) {
		JxcSellBill jxcSellBill = new JxcSellBill();
		jxcSellBill.setShop_id(t.getShop_id());
		List<JxcSellBill> jxcSellBillList = this.getJxcSellBillForJnhmAuditList(t);
		for (JxcSellBill temp : jxcSellBillList) {
			JxcJnhmSellBillAudit jxcJnhmSellBillAudit = new JxcJnhmSellBillAudit();// 判断审核的订单是否存在待审核的订单
			jxcJnhmSellBillAudit.setSell_bill_id(temp.getId());
			jxcJnhmSellBillAudit = this.jxcJnhmSellBillAuditDao.selectEntity(jxcJnhmSellBillAudit);

			JxcJnhmSellBillAudit entity = new JxcJnhmSellBillAudit();
			entity.setSell_bill_id(temp.getId());
			entity.setAudit_date(new Date());
			entity.setStates(1);
			if (jxcJnhmSellBillAudit == null) { // 审核通过
				this.jxcJnhmSellBillAuditDao.insertEntity(entity);
			} else if (jxcJnhmSellBillAudit != null && jxcJnhmSellBillAudit.getStates() == 0) {// 待审核订单
				entity.setId(jxcJnhmSellBillAudit.getId());
				entity.setAudit_remarks("");
				this.jxcJnhmSellBillAuditDao.updateEntity(entity);
			}
		}
	}

	/**
	 * @author Hu,Hao
	 * @version 2012-08-22
	 */
	// 节能惠民批量审核
	public void jxcSellBillSelectAudit(JxcSellBill t) {
		String[] ids = (String[]) t.getMap().get("pkss");
		String audit_memo = (String) t.getMap().get("audit_memo");
		String audit_state = (String) t.getMap().get("audit_state");
		String audit_user_name = (String) t.getMap().get("audit_user_name");

		for (int i = 0; i < ids.length; i++) {
			JxcJnhmSellBillAudit jxcJnhmSellBillAudit = new JxcJnhmSellBillAudit();
			jxcJnhmSellBillAudit.setSell_bill_id(Long.valueOf(ids[i]));
			jxcJnhmSellBillAudit = this.jxcJnhmSellBillAuditDao.selectEntity(jxcJnhmSellBillAudit);// 判断审核的订单是否存在

			JxcJnhmSellBillAudit entity = new JxcJnhmSellBillAudit();
			entity.setSell_bill_id(Long.valueOf(ids[i]));
			if (audit_user_name != null && !("").equals(audit_user_name)) {
				entity.setAudit_user_name(audit_user_name);
			}
			entity.setAudit_date(new Date());
			if (jxcJnhmSellBillAudit == null) {// 此订单未审核过
				entity.setAudit_remarks("");
				entity.setStates(Integer.valueOf(audit_state));
				this.jxcJnhmSellBillAuditDao.insertEntity(entity);
			} else if (jxcJnhmSellBillAudit != null && jxcJnhmSellBillAudit.getStates() == 0 && audit_state.equals("0")) {// 已审核，待审核订单
				entity.setId(jxcJnhmSellBillAudit.getId());
				entity.setAudit_remarks(audit_memo);
				this.jxcJnhmSellBillAuditDao.updateEntity(entity);
			} else if (jxcJnhmSellBillAudit != null && jxcJnhmSellBillAudit.getStates() == 0 && audit_state.equals("1")) {// 已审核，审核通过
				entity.setId(jxcJnhmSellBillAudit.getId());
				entity.setAudit_remarks("");
				entity.setStates(1);
				this.jxcJnhmSellBillAuditDao.updateEntity(entity);
			}
		}
	}
}
