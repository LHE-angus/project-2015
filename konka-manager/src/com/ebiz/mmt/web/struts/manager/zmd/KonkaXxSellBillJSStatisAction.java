package com.ebiz.mmt.web.struts.manager.zmd;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaXxSellBill;
import com.ebiz.mmt.domain.KonkaXxZmd;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Jiang,JiaYong
 * @version 2012-05-02
 */
public class KonkaXxSellBillJSStatisAction extends BaseZmdAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.view(mapping, form, request, response);
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String date_start = (String) dynaBean.get("date_start");
		String date_end = (String) dynaBean.get("date_end");

		// 取当前登录用户
		PeProdUser ui = super.getSessionUserInfo(request);
		// 取用户角色并判断是不是分公司级别的角色
		// PeRoleUser peRoleUser = super.getRoleInfoByThisLogin(request);
		// request.setAttribute("peRoleUser", peRoleUser);

		List<PeRoleUser> peRoleUserList = getPeRoleList(ui.getId());

		Boolean role_id_gt_400 = false;
		if (peRoleUserList.size() > 0) {
			for (PeRoleUser temp : peRoleUserList) {
				if (temp.getRole_id() <= 400) {
					role_id_gt_400 = true;
				}
			}
		}

		if (!role_id_gt_400) {
			String msg = super.getMessage(request, "konka.xx.zmd.role.error");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		// 选择了查询条件
		if (StringUtils.isNotBlank(date_start) && StringUtils.isNotBlank(date_end)) {
			KonkaDept konkaDept = new KonkaDept();
			konkaDept.getRow().setCount(2);

			KonkaDept kDept = getKonkaDeptForFgs(ui.getDept_id());
			if (kDept != null)
				konkaDept.setDept_id(kDept.getDept_id());
			List<KonkaDept> konkaDeptList = super.getFacade().getKonkaDeptService().getKonkaDeptList(konkaDept);
			if (1 != konkaDeptList.size()) {
				String m = getMessage(request, "konka.zmd.deptinfo.missing");
				super.renderJavaScript(response, "window.onload=function(){alert('" + m + "');history.back();}");
				return null;
			}

			konkaDept = konkaDeptList.get(0);

			/*
			 * 开始查询数据 PAY_WAY 销售单付款方式：0-未付款，1-现金支付，2-POS刷卡支付，3-货到付款；
			 * CHECKOUT_DIST_STATE 财务物流结算状态：0-未结算，1-已结算 CHECKOUT_ZMD_STATE
			 * 财务专卖店结算状态：0-未结算，1-已结算
			 */
			KonkaXxSellBill ksb = new KonkaXxSellBill();
			ksb.setDept_id(konkaDept.getDept_id());
			ksb.getMap().put("add_date_ge", date_start);
			ksb.getMap().put("add_date_le", date_end);

			Long all_n = 0L; // 合计订单数
			BigDecimal all_m = new BigDecimal(0); // 合计订单金额

			// 已结算物流代收，已结算专卖店代收
			ksb.setPay_way(3L);
			ksb.setCheckout_state(1);
			Long p_3_w_1_z_1_n = super.getFacade().getKonkaXxSellBillService().getKonkaXxSellBillCount(ksb);
			if (null == p_3_w_1_z_1_n)
				p_3_w_1_z_1_n = 0L;
			all_n += p_3_w_1_z_1_n;
			String p_3_w_1_z_1_m = super.getFacade().getKonkaXxSellBillService().getKonkaXxSellBillTotalMoneySum(ksb);
			if (null == p_3_w_1_z_1_m)
				p_3_w_1_z_1_m = "0";
			all_m = all_m.add(new BigDecimal(p_3_w_1_z_1_m)).setScale(2, BigDecimal.ROUND_UP);
			konkaDept.getMap().put("p_3_w_1_z_1_n", p_3_w_1_z_1_n);
			konkaDept.getMap().put("p_3_w_1_z_1_m", p_3_w_1_z_1_m);

			// 已结算POS机到款
			ksb.setPay_way(2L);
			ksb.setCheckout_state(1);
			Long p_2_w_1_z_1_n = super.getFacade().getKonkaXxSellBillService().getKonkaXxSellBillCount(ksb);
			if (null == p_2_w_1_z_1_n)
				p_2_w_1_z_1_n = 0L;
			all_n += p_2_w_1_z_1_n;
			String p_2_w_1_z_1_m = super.getFacade().getKonkaXxSellBillService().getKonkaXxSellBillTotalMoneySum(ksb);
			if (null == p_2_w_1_z_1_m)
				p_2_w_1_z_1_m = "0";
			all_m = all_m.add(new BigDecimal(p_2_w_1_z_1_m)).setScale(2, BigDecimal.ROUND_UP);
			konkaDept.getMap().put("p_2_w_1_z_1_n", p_2_w_1_z_1_n);
			konkaDept.getMap().put("p_2_w_1_z_1_m", p_2_w_1_z_1_m);

			// 已结算专卖店代收款
			ksb.setPay_way(1L);
			ksb.setCheckout_state(1);
			Long p_1_w_1_z_1_n = super.getFacade().getKonkaXxSellBillService().getKonkaXxSellBillCount(ksb);
			if (null == p_1_w_1_z_1_n)
				p_1_w_1_z_1_n = 0L;
			all_n += p_1_w_1_z_1_n;
			String p_1_w_1_z_1_m = super.getFacade().getKonkaXxSellBillService().getKonkaXxSellBillTotalMoneySum(ksb);
			if (null == p_1_w_1_z_1_m)
				p_1_w_1_z_1_m = "0";
			all_m = all_m.add(new BigDecimal(p_1_w_1_z_1_m)).setScale(2, BigDecimal.ROUND_UP);
			konkaDept.getMap().put("p_1_w_1_z_1_n", p_1_w_1_z_1_n);
			konkaDept.getMap().put("p_1_w_1_z_1_m", p_1_w_1_z_1_m);

			// 已结算物流代收，未结算专卖店代收
			ksb.setPay_way(3L);
			ksb.setCheckout_state(null);
			ksb.setCheckout_dist_state(1);
			ksb.setCheckout_zmd_state(0);
			Long p_3_w_1_z_0_n = super.getFacade().getKonkaXxSellBillService().getKonkaXxSellBillCount(ksb);
			if (null == p_3_w_1_z_0_n)
				p_3_w_1_z_0_n = 0L;
			all_n += p_3_w_1_z_0_n;
			String p_3_w_1_z_0_m = super.getFacade().getKonkaXxSellBillService().getKonkaXxSellBillTotalMoneySum(ksb);
			if (null == p_3_w_1_z_0_m)
				p_3_w_1_z_0_m = "0";
			all_m = all_m.add(new BigDecimal(p_3_w_1_z_0_m)).setScale(2, BigDecimal.ROUND_UP);
			konkaDept.getMap().put("p_3_w_1_z_0_n", p_3_w_1_z_0_n);
			konkaDept.getMap().put("p_3_w_1_z_0_m", p_3_w_1_z_0_m);

			// 未结算物流代收，已结算专卖店代收
			ksb.setPay_way(3L);
			ksb.setCheckout_state(null);
			ksb.setCheckout_dist_state(0);
			ksb.setCheckout_zmd_state(1);
			Long p_3_w_0_z_1_n = super.getFacade().getKonkaXxSellBillService().getKonkaXxSellBillCount(ksb);
			if (null == p_3_w_0_z_1_n)
				p_3_w_0_z_1_n = 0L;
			all_n += p_3_w_0_z_1_n;
			String p_3_w_0_z_1_m = super.getFacade().getKonkaXxSellBillService().getKonkaXxSellBillTotalMoneySum(ksb);
			if (null == p_3_w_0_z_1_m)
				p_3_w_0_z_1_m = "0";
			all_m = all_m.add(new BigDecimal(p_3_w_0_z_1_m)).setScale(2, BigDecimal.ROUND_UP);
			konkaDept.getMap().put("p_3_w_0_z_1_n", p_3_w_0_z_1_n);
			konkaDept.getMap().put("p_3_w_0_z_1_m", p_3_w_0_z_1_m);

			// 未结算物流代收，未结算专卖店代收
			ksb.setPay_way(3L);
			ksb.setCheckout_state(null);
			ksb.setCheckout_dist_state(0);
			ksb.setCheckout_zmd_state(0);
			Long p_3_w_0_z_0_n = super.getFacade().getKonkaXxSellBillService().getKonkaXxSellBillCount(ksb);
			if (null == p_3_w_0_z_0_n)
				p_3_w_0_z_0_n = 0L;
			all_n += p_3_w_0_z_0_n;
			String p_3_w_0_z_0_m = super.getFacade().getKonkaXxSellBillService().getKonkaXxSellBillTotalMoneySum(ksb);
			if (null == p_3_w_0_z_0_m)
				p_3_w_0_z_0_m = "0";
			all_m = all_m.add(new BigDecimal(p_3_w_0_z_0_m)).setScale(2, BigDecimal.ROUND_UP);
			konkaDept.getMap().put("p_3_w_0_z_0_n", p_3_w_0_z_0_n);
			konkaDept.getMap().put("p_3_w_0_z_0_m", p_3_w_0_z_0_m);

			// 未结算POS机到款
			ksb.setPay_way(2L);
			ksb.setCheckout_state(0);
			ksb.setCheckout_dist_state(null);
			ksb.setCheckout_zmd_state(null);
			Long p_2_w_0_z_0_n = super.getFacade().getKonkaXxSellBillService().getKonkaXxSellBillCount(ksb);
			if (null == p_2_w_0_z_0_n)
				p_2_w_0_z_0_n = 0L;
			all_n += p_2_w_0_z_0_n;
			String p_2_w_0_z_0_m = super.getFacade().getKonkaXxSellBillService().getKonkaXxSellBillTotalMoneySum(ksb);
			if (null == p_2_w_0_z_0_m)
				p_2_w_0_z_0_m = "0";
			all_m = all_m.add(new BigDecimal(p_2_w_0_z_0_m)).setScale(2, BigDecimal.ROUND_UP);
			konkaDept.getMap().put("p_2_w_0_z_0_n", p_2_w_0_z_0_n);
			konkaDept.getMap().put("p_2_w_0_z_0_m", p_2_w_0_z_0_m);

			// 未结算专卖店代收款
			ksb.setPay_way(1L);
			ksb.setCheckout_state(0);
			ksb.setCheckout_dist_state(null);
			ksb.setCheckout_zmd_state(null);
			Long p_1_w_0_z_0_n = super.getFacade().getKonkaXxSellBillService().getKonkaXxSellBillCount(ksb);
			if (null == p_1_w_0_z_0_n)
				p_1_w_0_z_0_n = 0L;
			all_n += p_1_w_0_z_0_n;
			String p_1_w_0_z_0_m = super.getFacade().getKonkaXxSellBillService().getKonkaXxSellBillTotalMoneySum(ksb);
			if (null == p_1_w_0_z_0_m)
				p_1_w_0_z_0_m = "0";
			all_m = all_m.add(new BigDecimal(p_1_w_0_z_0_m)).setScale(2, BigDecimal.ROUND_UP);
			konkaDept.getMap().put("p_1_w_0_z_0_n", p_1_w_0_z_0_n);
			konkaDept.getMap().put("p_1_w_0_z_0_m", p_1_w_0_z_0_m);

			// 开始查询数据结束

			konkaDept.getMap().put("all_n", all_n);
			konkaDept.getMap().put("all_m", all_m);
			request.setAttribute("konkaDept", konkaDept);
		}

		return mapping.findForward("view");
	}

	public ActionForward zmd(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String date_start = (String) dynaBean.get("date_start");
		String date_end = (String) dynaBean.get("date_end");
		String pay_way = (String) dynaBean.get("pay_way");

		// 取当前登录用户
		PeProdUser ui = super.getSessionUserInfo(request);
		// 取用户角色并判断是不是分公司级别的角色
		// PeRoleUser peRoleUser = super.getRoleInfoByThisLogin(request);
		// request.setAttribute("peRoleUser", peRoleUser);
		List<PeRoleUser> peRoleUserList = getPeRoleList(ui.getId());

		Boolean role_id_gt_400 = false;
		if (peRoleUserList.size() > 0) {
			for (PeRoleUser temp : peRoleUserList) {
				if (temp.getRole_id() <= 400) {
					role_id_gt_400 = true;
				}
			}
		}

		if (!role_id_gt_400) {
			String msg = super.getMessage(request, "konka.xx.zmd.role.error");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		if (!GenericValidator.isLong(pay_way) || StringUtils.isBlank(date_start) || StringUtils.isBlank(date_end)) {
			String msg = super.getMessage(request, "errors.parm");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		// 查询分公司信息
		KonkaDept konkaDept = new KonkaDept();
		konkaDept.getRow().setCount(2);
		KonkaDept kDept = getKonkaDeptForFgs(ui.getDept_id());
		if (null != kDept)
			konkaDept.setDept_id(kDept.getDept_id());
		List<KonkaDept> konkaDeptList = super.getFacade().getKonkaDeptService().getKonkaDeptList(konkaDept);
		if (1 != konkaDeptList.size()) {
			String m = getMessage(request, "konka.zmd.deptinfo.missing");
			super.renderJavaScript(response, "window.onload=function(){alert('" + m + "');history.back();}");
			return null;
		}

		konkaDept = konkaDeptList.get(0);

		// 初始化参数
		KonkaXxSellBill ksb = new KonkaXxSellBill();
		ksb.setDept_id(konkaDept.getDept_id());
		ksb.getMap().put("add_date_ge", date_start);
		ksb.getMap().put("add_date_le", date_end);
		ksb.setPay_way(Long.valueOf(pay_way));

		// 查询已结算的订单
		ksb.setCheckout_state(1);
		List<KonkaXxSellBill> konkaXxSellBillList_1 = super.getFacade().getKonkaXxSellBillService()
				.getKonkaXxSellBillListForCountNumSumMoney(ksb);

		// 查询未结算的订单
		ksb.setCheckout_state(0);
		List<KonkaXxSellBill> konkaXxSellBillList_0 = super.getFacade().getKonkaXxSellBillService()
				.getKonkaXxSellBillListForCountNumSumMoney(ksb);

		List<Long> zmd_id_list = new ArrayList<Long>();
		List<KonkaXxSellBill> konkaXxSellBillList = new ArrayList<KonkaXxSellBill>();
		// 两边数据大小不一致处理
		for (KonkaXxSellBill t_0 : konkaXxSellBillList_0) {
			for (KonkaXxSellBill t_1 : konkaXxSellBillList_1) {
				if (t_0.getZmd_id().toString().equals(t_1.getZmd_id().toString())) {
					KonkaXxSellBill t = new KonkaXxSellBill();
					t.getMap().put("count_num_0", t_0.getMap().get("count_num"));
					t.getMap().put("count_num_1", t_1.getMap().get("count_num"));
					t.getMap().put("sum_money_0", t_0.getMap().get("sum_money"));
					t.getMap().put("sum_money_1", t_1.getMap().get("sum_money"));
					t.setZmd_id(t_0.getZmd_id());
					t.setZmd_sn(t_0.getZmd_sn());

					zmd_id_list.add(t_0.getZmd_id());
					konkaXxSellBillList.add(t);
				}
			}
		}
		for (KonkaXxSellBill t_0 : konkaXxSellBillList_0) {
			boolean flag = true;
			for (Long zmd_id : zmd_id_list) {
				if (t_0.getZmd_id().toString().equals(zmd_id.toString())) {
					flag = false;
					break;
				}
			}

			if (flag) {
				KonkaXxSellBill t = new KonkaXxSellBill();
				t.getMap().put("count_num_0", t_0.getMap().get("count_num"));
				t.getMap().put("count_num_1", "0");
				t.getMap().put("sum_money_0", t_0.getMap().get("sum_money"));
				t.getMap().put("sum_money_1", "0");
				t.setZmd_id(t_0.getZmd_id());
				t.setZmd_sn(t_0.getZmd_sn());

				konkaXxSellBillList.add(t);
			}
		}
		for (KonkaXxSellBill t_1 : konkaXxSellBillList_1) {
			boolean flag = true;
			for (Long zmd_id : zmd_id_list) {
				if (t_1.getZmd_id().toString().equals(zmd_id.toString())) {
					flag = false;
					break;
				}
			}

			if (flag) {
				KonkaXxSellBill t = new KonkaXxSellBill();
				t.getMap().put("count_num_0", "0");
				t.getMap().put("count_num_1", t_1.getMap().get("count_num"));
				t.getMap().put("sum_money_0", "0");
				t.getMap().put("sum_money_1", t_1.getMap().get("sum_money"));
				t.setZmd_id(t_1.getZmd_id());
				t.setZmd_sn(t_1.getZmd_sn());

				konkaXxSellBillList.add(t);
			}
		}

		request.setAttribute("konkaXxSellBillList", konkaXxSellBillList);
		request.setAttribute("konkaDept", konkaDept);
		return new ActionForward("/../manager/zmd/KonkaXxSellBillJSStatis/zmd.jsp");
	}

	public ActionForward zmdView(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String date_start = (String) dynaBean.get("date_start");
		String date_end = (String) dynaBean.get("date_end");
		String zmd_id = (String) dynaBean.get("zmd_id");

		// 取当前登录用户
		PeProdUser ui = super.getSessionUserInfo(request);
		// 取用户角色并判断是不是分公司级别的角色
		// PeRoleUser peRoleUser = super.getRoleInfoByThisLogin(request);
		// request.setAttribute("peRoleUser", peRoleUser);
		List<PeRoleUser> peRoleUserList = getPeRoleList(ui.getId());

		Boolean role_id_gt_400 = false;
		if (peRoleUserList.size() > 0) {
			for (PeRoleUser temp : peRoleUserList) {
				if (temp.getRole_id() <= 400) {
					role_id_gt_400 = true;
				}
			}
		}
		if (!role_id_gt_400) {
			String msg = super.getMessage(request, "konka.xx.zmd.role.error");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		if (StringUtils.isBlank(date_start) || StringUtils.isBlank(date_end) || !GenericValidator.isLong(zmd_id)) {
			String msg = super.getMessage(request, "errors.parm");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		KonkaXxZmd entity = new KonkaXxZmd();

		KonkaDept kDept = getKonkaDeptForFgs(ui.getDept_id());
		if (null != kDept)
			entity.setDept_id(kDept.getDept_id());
		entity.setZmd_id(Long.valueOf(zmd_id));
		entity = super.getFacade().getKonkaXxZmdService().getKonkaXxZmd(entity);
		if (null == entity) {
			String msg = super.getMessage(request, "errors.parm");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		/*
		 * 开始查询数据 PAY_WAY 销售单付款方式：0-未付款，1-现金支付，2-POS刷卡支付，3-货到付款；
		 * CHECKOUT_DIST_STATE 财务物流结算状态：0-未结算，1-已结算 CHECKOUT_ZMD_STATE
		 * 财务专卖店结算状态：0-未结算，1-已结算
		 */
		KonkaXxSellBill ksb = new KonkaXxSellBill();
		ksb.setDept_id(entity.getDept_id());
		ksb.setZmd_id(entity.getZmd_id());
		ksb.setZmd_sn(entity.getZmd_sn());
		ksb.getMap().put("add_date_ge", date_start);
		ksb.getMap().put("add_date_le", date_end);

		Long all_n = 0L; // 合计订单数
		BigDecimal all_m = new BigDecimal(0); // 合计订单金额

		// 已结算物流代收，已结算专卖店代收
		ksb.setPay_way(3L);
		ksb.setCheckout_state(1);
		Long p_3_w_1_z_1_n = super.getFacade().getKonkaXxSellBillService().getKonkaXxSellBillCount(ksb);
		if (null == p_3_w_1_z_1_n)
			p_3_w_1_z_1_n = 0L;
		all_n += p_3_w_1_z_1_n;
		String p_3_w_1_z_1_m = super.getFacade().getKonkaXxSellBillService().getKonkaXxSellBillTotalMoneySum(ksb);
		if (null == p_3_w_1_z_1_m)
			p_3_w_1_z_1_m = "0";
		all_m = all_m.add(new BigDecimal(p_3_w_1_z_1_m)).setScale(2, BigDecimal.ROUND_UP);
		entity.getMap().put("p_3_w_1_z_1_n", p_3_w_1_z_1_n);
		entity.getMap().put("p_3_w_1_z_1_m", p_3_w_1_z_1_m);

		// 已结算POS机到款
		ksb.setPay_way(2L);
		ksb.setCheckout_state(1);
		Long p_2_w_1_z_1_n = super.getFacade().getKonkaXxSellBillService().getKonkaXxSellBillCount(ksb);
		if (null == p_2_w_1_z_1_n)
			p_2_w_1_z_1_n = 0L;
		all_n += p_2_w_1_z_1_n;
		String p_2_w_1_z_1_m = super.getFacade().getKonkaXxSellBillService().getKonkaXxSellBillTotalMoneySum(ksb);
		if (null == p_2_w_1_z_1_m)
			p_2_w_1_z_1_m = "0";
		all_m = all_m.add(new BigDecimal(p_2_w_1_z_1_m)).setScale(2, BigDecimal.ROUND_UP);
		entity.getMap().put("p_2_w_1_z_1_n", p_2_w_1_z_1_n);
		entity.getMap().put("p_2_w_1_z_1_m", p_2_w_1_z_1_m);

		// 已结算专卖店代收款
		ksb.setPay_way(1L);
		ksb.setCheckout_state(1);
		Long p_1_w_1_z_1_n = super.getFacade().getKonkaXxSellBillService().getKonkaXxSellBillCount(ksb);
		if (null == p_1_w_1_z_1_n)
			p_1_w_1_z_1_n = 0L;
		all_n += p_1_w_1_z_1_n;
		String p_1_w_1_z_1_m = super.getFacade().getKonkaXxSellBillService().getKonkaXxSellBillTotalMoneySum(ksb);
		if (null == p_1_w_1_z_1_m)
			p_1_w_1_z_1_m = "0";
		all_m = all_m.add(new BigDecimal(p_1_w_1_z_1_m)).setScale(2, BigDecimal.ROUND_UP);
		entity.getMap().put("p_1_w_1_z_1_n", p_1_w_1_z_1_n);
		entity.getMap().put("p_1_w_1_z_1_m", p_1_w_1_z_1_m);

		// 已结算物流代收，未结算专卖店代收
		ksb.setPay_way(3L);
		ksb.setCheckout_state(null);
		ksb.setCheckout_dist_state(1);
		ksb.setCheckout_zmd_state(0);
		Long p_3_w_1_z_0_n = super.getFacade().getKonkaXxSellBillService().getKonkaXxSellBillCount(ksb);
		if (null == p_3_w_1_z_0_n)
			p_3_w_1_z_0_n = 0L;
		all_n += p_3_w_1_z_0_n;
		String p_3_w_1_z_0_m = super.getFacade().getKonkaXxSellBillService().getKonkaXxSellBillTotalMoneySum(ksb);
		if (null == p_3_w_1_z_0_m)
			p_3_w_1_z_0_m = "0";
		all_m = all_m.add(new BigDecimal(p_3_w_1_z_0_m)).setScale(2, BigDecimal.ROUND_UP);
		entity.getMap().put("p_3_w_1_z_0_n", p_3_w_1_z_0_n);
		entity.getMap().put("p_3_w_1_z_0_m", p_3_w_1_z_0_m);

		// 未结算物流代收，已结算专卖店代收
		ksb.setPay_way(3L);
		ksb.setCheckout_state(null);
		ksb.setCheckout_dist_state(0);
		ksb.setCheckout_zmd_state(1);
		Long p_3_w_0_z_1_n = super.getFacade().getKonkaXxSellBillService().getKonkaXxSellBillCount(ksb);
		if (null == p_3_w_0_z_1_n)
			p_3_w_0_z_1_n = 0L;
		all_n += p_3_w_0_z_1_n;
		String p_3_w_0_z_1_m = super.getFacade().getKonkaXxSellBillService().getKonkaXxSellBillTotalMoneySum(ksb);
		if (null == p_3_w_0_z_1_m)
			p_3_w_0_z_1_m = "0";
		all_m = all_m.add(new BigDecimal(p_3_w_0_z_1_m)).setScale(2, BigDecimal.ROUND_UP);
		entity.getMap().put("p_3_w_0_z_1_n", p_3_w_0_z_1_n);
		entity.getMap().put("p_3_w_0_z_1_m", p_3_w_0_z_1_m);

		// 未结算物流代收，未结算专卖店代收
		ksb.setPay_way(3L);
		ksb.setCheckout_state(null);
		ksb.setCheckout_dist_state(0);
		ksb.setCheckout_zmd_state(0);
		Long p_3_w_0_z_0_n = super.getFacade().getKonkaXxSellBillService().getKonkaXxSellBillCount(ksb);
		if (null == p_3_w_0_z_0_n)
			p_3_w_0_z_0_n = 0L;
		all_n += p_3_w_0_z_0_n;
		String p_3_w_0_z_0_m = super.getFacade().getKonkaXxSellBillService().getKonkaXxSellBillTotalMoneySum(ksb);
		if (null == p_3_w_0_z_0_m)
			p_3_w_0_z_0_m = "0";
		all_m = all_m.add(new BigDecimal(p_3_w_0_z_0_m)).setScale(2, BigDecimal.ROUND_UP);
		entity.getMap().put("p_3_w_0_z_0_n", p_3_w_0_z_0_n);
		entity.getMap().put("p_3_w_0_z_0_m", p_3_w_0_z_0_m);

		// 未结算POS机到款
		ksb.setPay_way(2L);
		ksb.setCheckout_state(0);
		ksb.setCheckout_dist_state(null);
		ksb.setCheckout_zmd_state(null);
		Long p_2_w_0_z_0_n = super.getFacade().getKonkaXxSellBillService().getKonkaXxSellBillCount(ksb);
		if (null == p_2_w_0_z_0_n)
			p_2_w_0_z_0_n = 0L;
		all_n += p_2_w_0_z_0_n;
		String p_2_w_0_z_0_m = super.getFacade().getKonkaXxSellBillService().getKonkaXxSellBillTotalMoneySum(ksb);
		if (null == p_2_w_0_z_0_m)
			p_2_w_0_z_0_m = "0";
		all_m = all_m.add(new BigDecimal(p_2_w_0_z_0_m)).setScale(2, BigDecimal.ROUND_UP);
		entity.getMap().put("p_2_w_0_z_0_n", p_2_w_0_z_0_n);
		entity.getMap().put("p_2_w_0_z_0_m", p_2_w_0_z_0_m);

		// 未结算专卖店代收款
		ksb.setPay_way(1L);
		ksb.setCheckout_state(0);
		ksb.setCheckout_dist_state(null);
		ksb.setCheckout_zmd_state(null);
		Long p_1_w_0_z_0_n = super.getFacade().getKonkaXxSellBillService().getKonkaXxSellBillCount(ksb);
		if (null == p_1_w_0_z_0_n)
			p_1_w_0_z_0_n = 0L;
		all_n += p_1_w_0_z_0_n;
		String p_1_w_0_z_0_m = super.getFacade().getKonkaXxSellBillService().getKonkaXxSellBillTotalMoneySum(ksb);
		if (null == p_1_w_0_z_0_m)
			p_1_w_0_z_0_m = "0";
		all_m = all_m.add(new BigDecimal(p_1_w_0_z_0_m)).setScale(2, BigDecimal.ROUND_UP);
		entity.getMap().put("p_1_w_0_z_0_n", p_1_w_0_z_0_n);
		entity.getMap().put("p_1_w_0_z_0_m", p_1_w_0_z_0_m);

		// 开始查询数据结束

		entity.getMap().put("all_n", all_n);
		entity.getMap().put("all_m", all_m);
		request.setAttribute("konkaXxZmd", entity);

		return new ActionForward("/../manager/zmd/KonkaXxSellBillJSStatis/zmd_view.jsp");
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String date_start = (String) dynaBean.get("date_start");
		String date_end = (String) dynaBean.get("date_end");
		String checkout_dist_state = (String) dynaBean.get("checkout_dist_state");
		String checkout_zmd_state = (String) dynaBean.get("checkout_zmd_state");
		String pay_way = (String) dynaBean.get("pay_way");
		String zmd_id = (String) dynaBean.get("zmd_id");

		if (!GenericValidator.isLong(pay_way) || !GenericValidator.isLong(zmd_id) || StringUtils.isEmpty(date_start)
				|| StringUtils.isEmpty(date_end)) {
			String msg = super.getMessage(request, "errors.param");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		// 取当前登录用户
		PeProdUser ui = super.getSessionUserInfo(request);
		// 取用户角色并判断是不是分公司级别的角色
		// PeRoleUser peRoleUser = super.getRoleInfoByThisLogin(request);
		// request.setAttribute("peRoleUser", peRoleUser);
		List<PeRoleUser> peRoleUserList = getPeRoleList(ui.getId());

		Boolean role_id_gt_400 = false;
		if (peRoleUserList.size() > 0) {
			for (PeRoleUser temp : peRoleUserList) {
				if (temp.getRole_id() <= 400) {
					role_id_gt_400 = true;
				}
			}
		}

		if (!role_id_gt_400) {
			String msg = super.getMessage(request, "konka.xx.zmd.role.error");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		KonkaXxZmd konkaXxZmd = new KonkaXxZmd();
		KonkaDept kDept = getKonkaDeptForFgs(ui.getDept_id());
		if (null != kDept)
			konkaXxZmd.setDept_id(kDept.getDept_id());
		konkaXxZmd.setZmd_id(Long.valueOf(zmd_id));
		konkaXxZmd = super.getFacade().getKonkaXxZmdService().getKonkaXxZmd(konkaXxZmd);
		if (null == konkaXxZmd) {
			String msg = super.getMessage(request, "errors.parm");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		KonkaXxSellBill entity = new KonkaXxSellBill();
		entity.setDept_id(konkaXxZmd.getDept_id());
		entity.setZmd_id(konkaXxZmd.getZmd_id());
		entity.setZmd_sn(konkaXxZmd.getZmd_sn());
		entity.getMap().put("add_date_ge", date_start);
		entity.getMap().put("add_date_le", date_end);
		entity.setPay_way(Long.valueOf(pay_way));
		if ("3".equals(pay_way)) {
			if (GenericValidator.isLong(checkout_dist_state))
				entity.setCheckout_dist_state(Integer.valueOf(checkout_dist_state));
			if (GenericValidator.isLong(checkout_zmd_state))
				entity.setCheckout_zmd_state(Integer.valueOf(checkout_zmd_state));
		}
		if (!"3".equals(pay_way) && "0".equals(checkout_zmd_state)) {
			entity.setCheckout_state(0);
		}
		if (!"3".equals(pay_way) && "1".equals(checkout_zmd_state)) {
			entity.setCheckout_state(1);
		}

		Long recordCount = super.getFacade().getKonkaXxSellBillService().getKonkaXxSellBillCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setCount(pager.getRowCount());
		entity.getRow().setFirst(pager.getFirstRow());
		List<KonkaXxSellBill> entityList = super.getFacade().getKonkaXxSellBillService()
				.getKonkaXxSellBillPaginatedList(entity);
		request.setAttribute("entityList", entityList);

		return mapping.findForward("list");

	}
}
