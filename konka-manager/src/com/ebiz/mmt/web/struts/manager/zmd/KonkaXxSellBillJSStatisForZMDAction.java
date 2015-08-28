package com.ebiz.mmt.web.struts.manager.zmd;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaXxSellBill;
import com.ebiz.mmt.domain.KonkaXxZmd;
import com.ebiz.mmt.domain.KonkaXxZmdUsers;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Jiang,JiaYong
 * @version 2012-05-02
 */
public class KonkaXxSellBillJSStatisForZMDAction extends BaseZmdAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return this.view(mapping, form, request, response);
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String date_start = (String) dynaBean.get("date_start");
		String date_end = (String) dynaBean.get("date_end");

		// 取当前登录用户
		PeProdUser ui = super.getSessionUserInfo(request);
		// 取用户角色并判断是不是专卖店级别的角色
		//PeRoleUser peRoleUser = super.getRoleInfoByThisLogin(request);
		//request.setAttribute("peRoleUser", peRoleUser);
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
			KonkaXxZmdUsers konkaXxZmdUsers = new KonkaXxZmdUsers();
			konkaXxZmdUsers.setUser_id(ui.getId());
			konkaXxZmdUsers.getRow().setCount(2);
			List<KonkaXxZmdUsers> konkaXxZmdUsersList = getFacade().getKonkaXxZmdUsersService().getKonkaXxZmdUsersList(konkaXxZmdUsers);
			if (null == konkaXxZmdUsersList || konkaXxZmdUsersList.size() == 0) {
				String m = getMessage(request, "konka.zmd.userinfo.missing");
				super.renderJavaScript(response, "window.onload=function(){alert('" + m + "');history.back();}");
				return null;
			}
			if (konkaXxZmdUsersList.size() == 2) {
				String m = getMessage(request, "konka.zmd.userinfo.too.many");
				super.renderJavaScript(response, "window.onload=function(){alert('" + m + "');history.back();}");
				return null;
			}

			// 查询专卖店信息
			KonkaXxZmd konkaXxZmd = new KonkaXxZmd();
			konkaXxZmd.getRow().setCount(2);
			konkaXxZmd.setZmd_id(konkaXxZmdUsersList.get(0).getZmd_id());
			List<KonkaXxZmd> konkaXxZmdList = super.getFacade().getKonkaXxZmdService().getKonkaXxZmdList(konkaXxZmd);
			if (1 != konkaXxZmdList.size()) {
				String msg = super.getMessage(request, "konka.zmd.missing");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			}
			konkaXxZmd = konkaXxZmdList.get(0);

			/*
			 * 开始查询数据 PAY_WAY 销售单付款方式：0-未付款，1-现金支付，2-POS刷卡支付，3-货到付款； 
			 */
			KonkaXxSellBill ksb = new KonkaXxSellBill();
			ksb.setDept_id(konkaXxZmd.getDept_id());
			ksb.setZmd_id(konkaXxZmd.getZmd_id());
			ksb.setZmd_sn(konkaXxZmd.getZmd_sn());
			ksb.getMap().put("add_date_ge", date_start);
			ksb.getMap().put("add_date_le", date_end);

			Long all_n = 0L; // 合计订单数
			BigDecimal all_m = new BigDecimal(0); // 合计订单金额
			
			// 已结算物流代收，已结算专卖店代收
			ksb.setPay_way(3L);
			ksb.setCheckout_state(1);
			Long p_3_w_1_z_1_n = super.getFacade().getKonkaXxSellBillService().getKonkaXxSellBillCount(ksb); 
			if(null == p_3_w_1_z_1_n)
				p_3_w_1_z_1_n = 0L;
			all_n += p_3_w_1_z_1_n;
			String p_3_w_1_z_1_m = super.getFacade().getKonkaXxSellBillService().getKonkaXxSellBillTotalMoneySum(ksb);
			if(null == p_3_w_1_z_1_m)
				p_3_w_1_z_1_m = "0";
			all_m = all_m.add(new BigDecimal(p_3_w_1_z_1_m)).setScale(2, BigDecimal.ROUND_UP);
			konkaXxZmd.getMap().put("p_3_w_1_z_1_n", p_3_w_1_z_1_n);
			konkaXxZmd.getMap().put("p_3_w_1_z_1_m", p_3_w_1_z_1_m);
			
			// 已结算POS机到款
			ksb.setPay_way(2L);
			ksb.setCheckout_state(1);
			Long p_2_w_1_z_1_n = super.getFacade().getKonkaXxSellBillService().getKonkaXxSellBillCount(ksb); 
			if(null == p_2_w_1_z_1_n)
				p_2_w_1_z_1_n = 0L;
			all_n += p_2_w_1_z_1_n;
			String p_2_w_1_z_1_m = super.getFacade().getKonkaXxSellBillService().getKonkaXxSellBillTotalMoneySum(ksb);
			if(null == p_2_w_1_z_1_m)
				p_2_w_1_z_1_m = "0";
			all_m = all_m.add(new BigDecimal(p_2_w_1_z_1_m)).setScale(2, BigDecimal.ROUND_UP);
			konkaXxZmd.getMap().put("p_2_w_1_z_1_n", p_2_w_1_z_1_n);
			konkaXxZmd.getMap().put("p_2_w_1_z_1_m", p_2_w_1_z_1_m);
			
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
			konkaXxZmd.getMap().put("p_1_w_1_z_1_n", p_1_w_1_z_1_n);
			konkaXxZmd.getMap().put("p_1_w_1_z_1_m", p_1_w_1_z_1_m);
			
			//已结算物流代收，未结算专卖店代收
			ksb.setPay_way(3L);
			ksb.setCheckout_state(null);
			ksb.setCheckout_dist_state(1);
			ksb.setCheckout_zmd_state(0);
			Long p_3_w_1_z_0_n = super.getFacade().getKonkaXxSellBillService().getKonkaXxSellBillCount(ksb); 
			if(null == p_3_w_1_z_0_n)
				p_3_w_1_z_0_n = 0L;
			all_n += p_3_w_1_z_0_n;
			String p_3_w_1_z_0_m = super.getFacade().getKonkaXxSellBillService().getKonkaXxSellBillTotalMoneySum(ksb);
			if(null == p_3_w_1_z_0_m)
				p_3_w_1_z_0_m = "0";
			all_m = all_m.add(new BigDecimal(p_3_w_1_z_0_m)).setScale(2, BigDecimal.ROUND_UP);
			konkaXxZmd.getMap().put("p_3_w_1_z_0_n", p_3_w_1_z_0_n);
			konkaXxZmd.getMap().put("p_3_w_1_z_0_m", p_3_w_1_z_0_m);
			
			// 未结算物流代收，已结算专卖店代收
			ksb.setPay_way(3L);
			ksb.setCheckout_state(null);
			ksb.setCheckout_dist_state(0);
			ksb.setCheckout_zmd_state(1);
			Long p_3_w_0_z_1_n = super.getFacade().getKonkaXxSellBillService().getKonkaXxSellBillCount(ksb); 
			if(null == p_3_w_0_z_1_n)
				p_3_w_0_z_1_n = 0L;
			all_n += p_3_w_0_z_1_n;
			String p_3_w_0_z_1_m = super.getFacade().getKonkaXxSellBillService().getKonkaXxSellBillTotalMoneySum(ksb);
			if(null == p_3_w_0_z_1_m)
				p_3_w_0_z_1_m = "0";
			all_m = all_m.add(new BigDecimal(p_3_w_0_z_1_m)).setScale(2, BigDecimal.ROUND_UP);
			konkaXxZmd.getMap().put("p_3_w_0_z_1_n", p_3_w_0_z_1_n);
			konkaXxZmd.getMap().put("p_3_w_0_z_1_m", p_3_w_0_z_1_m);
			
			// 未结算物流代收，未结算专卖店代收
			ksb.setPay_way(3L);
			ksb.setCheckout_state(null);
			ksb.setCheckout_dist_state(0);
			ksb.setCheckout_zmd_state(0);
			Long p_3_w_0_z_0_n = super.getFacade().getKonkaXxSellBillService().getKonkaXxSellBillCount(ksb); 
			if(null == p_3_w_0_z_0_n)
				p_3_w_0_z_0_n = 0L;
			all_n += p_3_w_0_z_0_n;
			String p_3_w_0_z_0_m = super.getFacade().getKonkaXxSellBillService().getKonkaXxSellBillTotalMoneySum(ksb);
			if(null == p_3_w_0_z_0_m)
				p_3_w_0_z_0_m = "0";
			all_m = all_m.add(new BigDecimal(p_3_w_0_z_0_m)).setScale(2, BigDecimal.ROUND_UP);
			konkaXxZmd.getMap().put("p_3_w_0_z_0_n", p_3_w_0_z_0_n);
			konkaXxZmd.getMap().put("p_3_w_0_z_0_m", p_3_w_0_z_0_m);
			
			// 未结算POS机到款
			ksb.setPay_way(2L);
			ksb.setCheckout_state(0);
			ksb.setCheckout_dist_state(null);
			ksb.setCheckout_zmd_state(null);
			Long p_2_w_0_z_0_n = super.getFacade().getKonkaXxSellBillService().getKonkaXxSellBillCount(ksb); 
			if(null == p_2_w_0_z_0_n)
				p_2_w_0_z_0_n = 0L;
			all_n += p_2_w_0_z_0_n;
			String p_2_w_0_z_0_m = super.getFacade().getKonkaXxSellBillService().getKonkaXxSellBillTotalMoneySum(ksb);
			if(null == p_2_w_0_z_0_m)
				p_2_w_0_z_0_m = "0";
			all_m = all_m.add(new BigDecimal(p_2_w_0_z_0_m)).setScale(2, BigDecimal.ROUND_UP);
			konkaXxZmd.getMap().put("p_2_w_0_z_0_n", p_2_w_0_z_0_n);
			konkaXxZmd.getMap().put("p_2_w_0_z_0_m", p_2_w_0_z_0_m);
			
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
			konkaXxZmd.getMap().put("p_1_w_0_z_0_n", p_1_w_0_z_0_n);
			konkaXxZmd.getMap().put("p_1_w_0_z_0_m", p_1_w_0_z_0_m);
			
			// 开始查询数据结束

			konkaXxZmd.getMap().put("all_n", all_n);
			konkaXxZmd.getMap().put("all_m", all_m);
			request.setAttribute("konkaXxZmd", konkaXxZmd);
		}

		return mapping.findForward("view");
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

		if(!GenericValidator.isLong(pay_way) || StringUtils.isEmpty(date_start) || StringUtils.isEmpty(date_end)){
			String msg = super.getMessage(request, "errors.param");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}
		
		// 取当前登录用户
		PeProdUser ui = super.getSessionUserInfo(request);
		// 取用户角色并判断是不是专卖店级别的角色
		//PeRoleUser peRoleUser = super.getRoleInfoByThisLogin(request);
		//request.setAttribute("peRoleUser", peRoleUser);
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
				
		// 查询专卖店信息
		KonkaXxZmdUsers konkaXxZmdUsers = new KonkaXxZmdUsers();
		konkaXxZmdUsers.setUser_id(ui.getId());
		konkaXxZmdUsers.getRow().setCount(2);
		List<KonkaXxZmdUsers> konkaXxZmdUsersList = getFacade().getKonkaXxZmdUsersService().getKonkaXxZmdUsersList(konkaXxZmdUsers);
		if (null == konkaXxZmdUsersList || konkaXxZmdUsersList.size() == 0) {
			String m = getMessage(request, "konka.zmd.userinfo.missing");
			super.renderJavaScript(response, "window.onload=function(){alert('" + m + "');history.back();}");
			return null;
		}
		if (konkaXxZmdUsersList.size() == 2) {
			String m = getMessage(request, "konka.zmd.userinfo.too.many");
			super.renderJavaScript(response, "window.onload=function(){alert('" + m + "');history.back();}");
			return null;
		}

		// 查询专卖店信息
		KonkaXxZmd konkaXxZmd = new KonkaXxZmd();
		konkaXxZmd.getRow().setCount(2);
		konkaXxZmd.setZmd_id(konkaXxZmdUsersList.get(0).getZmd_id());
		List<KonkaXxZmd> konkaXxZmdList = super.getFacade().getKonkaXxZmdService().getKonkaXxZmdList(konkaXxZmd);
		if (1 != konkaXxZmdList.size()) {
			String msg = super.getMessage(request, "konka.zmd.missing");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}
		konkaXxZmd = konkaXxZmdList.get(0);

		KonkaXxSellBill entity = new KonkaXxSellBill();
		entity.setDept_id(konkaXxZmd.getDept_id());
		entity.setZmd_id(konkaXxZmd.getZmd_id());
		entity.setZmd_sn(konkaXxZmd.getZmd_sn());
		entity.getMap().put("add_date_ge", date_start);
		entity.getMap().put("add_date_le", date_end);
		entity.setPay_way(Long.valueOf(pay_way));
		if("3".equals(pay_way)){
			if(GenericValidator.isLong(checkout_dist_state))
				entity.setCheckout_dist_state(Integer.valueOf(checkout_dist_state));
			if(GenericValidator.isLong(checkout_zmd_state))
				entity.setCheckout_zmd_state(Integer.valueOf(checkout_zmd_state));
		}
		if(!"3".equals(pay_way) && "0".equals(checkout_zmd_state)){
			entity.setCheckout_state(0);
		}
		if(!"3".equals(pay_way) && "1".equals(checkout_zmd_state)){
			entity.setCheckout_state(1);
		}

		Long recordCount = super.getFacade().getKonkaXxSellBillService().getKonkaXxSellBillCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setCount(pager.getRowCount());
		entity.getRow().setFirst(pager.getFirstRow());
		List<KonkaXxSellBill> entityList = super.getFacade().getKonkaXxSellBillService().getKonkaXxSellBillPaginatedList(entity);
		request.setAttribute("entityList", entityList);

		return mapping.findForward("list");

	}
}
