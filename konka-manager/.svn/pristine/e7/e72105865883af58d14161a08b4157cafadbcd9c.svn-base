package com.ebiz.mmt.web.struts.manager.zmd;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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
import com.ebiz.mmt.domain.KonkaXxBaseType;
import com.ebiz.mmt.domain.KonkaXxSellBill;
import com.ebiz.mmt.domain.KonkaXxSellBillDetailsDst;
import com.ebiz.mmt.domain.KonkaXxZmd;
import com.ebiz.mmt.domain.KonkaXxZmdUsers;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Wu,ShangLong
 * @version 2012-1-11
 */
public class KonkaXxZmdOutStoreOrderAction extends BaseZmdAction {

	protected ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String dept_id = (String) dynaBean.get("dept_id");
		String zmd_id = (String) dynaBean.get("zmd_id");
		String sell_bill_id = (String) dynaBean.get("sell_bill_id");
		String add_date_ge = (String) dynaBean.get("add_date_ge");
		String add_date_le = (String) dynaBean.get("add_date_le");
		String sell_state = (String) dynaBean.get("sell_state");
		String add_user_realname_like = (String) dynaBean.get("add_user_realname_like");
		String buyer_name_like = (String) dynaBean.get("buyer_name_like");
		String pay_way = (String) dynaBean.get("pay_way");

		KonkaXxBaseType konkaXxBaseType = new KonkaXxBaseType();
		konkaXxBaseType.setPar_id(50000L); // 付款方式
		List<KonkaXxBaseType> pay_way_arr = super.getFacade().getKonkaXxBaseTypeService().getKonkaXxBaseTypeList(
				konkaXxBaseType);
		request.setAttribute("pay_way_arr", pay_way_arr);

		KonkaXxSellBill entity = new KonkaXxSellBill();

		PeProdUser user_info = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		// PeRoleUser role_info = super.getRoleInfoByThisLogin(request);

		List<PeRoleUser> peRoleUserList = getPeRoleList(user_info.getId());

		Boolean role_id_eq_400 = false;
		Boolean role_id_eq_390 = false;
		Boolean role_id_btw_300_400 = false;
		Boolean role_id_btw_200_300 = false;
		if (peRoleUserList.size() > 0) {
			for (PeRoleUser temp : peRoleUserList) {
				if (temp.getRole_id() == 400)
					role_id_eq_400 = true;

				if (temp.getRole_id() == 390)
					role_id_eq_390 = true;

				if ((temp.getRole_id() >= 300 && temp.getRole_id() < 400)
						|| (temp.getRole_id() >= 30 && temp.getRole_id() < 40))
					role_id_btw_300_400 = true;

				if ((temp.getRole_id() >= 200 && temp.getRole_id() < 300) || temp.getRole_id() < 30)
					role_id_btw_200_300 = true;
			}
		}

		// 页面角色控制 start
		// long role_id = role_info.getRole_id();
		if (role_id_eq_400) { // 专卖店人员
			KonkaXxZmdUsers konkaXxZmdUsers = new KonkaXxZmdUsers();
			konkaXxZmdUsers.setUser_id(user_info.getId());

			List<KonkaXxZmdUsers> konkaXxZmdUsersList = super.getFacade().getKonkaXxZmdUsersService()
					.getKonkaXxZmdUsersList(konkaXxZmdUsers);
			if (null != konkaXxZmdUsersList && konkaXxZmdUsersList.size() > 0) {
				entity.setZmd_id(konkaXxZmdUsersList.get(0).getZmd_id());
			} else {
				String msg = getMessage(request, "konka.xx.zmd.user.not.bind.zmd");
				saveDirectlyError(request, msg);
				return mapping.findForward("list");
			}

			KonkaXxZmd zmd = new KonkaXxZmd();
			zmd.setZmd_id(konkaXxZmdUsersList.get(0).getZmd_id());
			zmd = super.getFacade().getKonkaXxZmdService().getKonkaXxZmd(zmd);

			KonkaDept kd = new KonkaDept();
			kd.setDept_id(zmd.getDept_id());
			kd = super.getFacade().getKonkaDeptService().getKonkaDept(kd);

			request.setAttribute("dept_name", kd.getDept_name());

			request.setAttribute("zmd_sn", zmd.getZmd_sn());

		} else if (role_id_btw_300_400) { // 分公司人员
			
			Long dept_id_value = null;
			KonkaDept kDept = getKonkaDeptForFgs(user_info.getDept_id());
			if (kDept != null)
				dept_id_value = kDept.getDept_id();
			
			if (role_id_eq_390) { // 分公司业务员
				KonkaXxZmdUsers konkaXxZmdUsers = new KonkaXxZmdUsers();
				konkaXxZmdUsers.setUser_id(user_info.getId());

				List<KonkaXxZmdUsers> konkaXxZmdUsersList = super.getFacade().getKonkaXxZmdUsersService()
						.getKonkaXxZmdUsersList(konkaXxZmdUsers);
				if (null != konkaXxZmdUsersList && konkaXxZmdUsersList.size() > 0) {
					List<Long> zmds = new ArrayList<Long>();
					for (KonkaXxZmdUsers temp : konkaXxZmdUsersList) {
						zmds.add(temp.getZmd_id());
					}

					entity.getMap().put("zmds", zmds.toArray());

					// 初始化业务员下面专卖店的下拉框
					KonkaXxZmd zmd = new KonkaXxZmd();
					zmd.getMap().put("zmds", zmds.toArray());
					zmd.setIs_del(0);
					List<KonkaXxZmd> zmdList = super.getFacade().getKonkaXxZmdService().getKonkaXxZmdList(zmd);

					request.setAttribute("zmdList", zmdList);
				} else {
					String msg = getMessage(request, "konka.xx.zmd.user.not.bind.zmd");
					saveDirectlyError(request, msg);
					return mapping.findForward("list");
				}
			} else {
				entity.setDept_id(dept_id_value);

				// 初始化非业务员下面专卖店的下拉框
				KonkaXxZmd zmd = new KonkaXxZmd();
				zmd.setDept_id(dept_id_value);
				zmd.setArc_state(20300);
				zmd.setIs_del(0);
				List<KonkaXxZmd> zmdList = super.getFacade().getKonkaXxZmdService().getKonkaXxZmdList(zmd);

				request.setAttribute("zmdList", zmdList);
			}

			KonkaDept kd = new KonkaDept();
			kd.setDept_id(dept_id_value);
			kd = super.getFacade().getKonkaDeptService().getKonkaDept(kd);

			request.setAttribute("dept_name", kd.getDept_name());

		} else if (role_id_btw_200_300) { // 总部人员
				// 初始化业务员下面专卖店的下拉框
				KonkaXxZmd zmd = new KonkaXxZmd();
				zmd.setArc_state(20300);
				zmd.setIs_del(0);
				List<KonkaXxZmd> zmdList = super.getFacade().getKonkaXxZmdService().getKonkaXxZmdList(zmd);

				request.setAttribute("zmdList", zmdList);
		}
		// 页面角色控制 end

		entity.setSell_state(30L); // 30：已审核已发货，打印出库单必须是“已审核已发货”状态

		// 选择的条件字段
		if (GenericValidator.isLong(dept_id)) {
			entity.setDept_id(Long.valueOf(dept_id));
		}
		if (GenericValidator.isLong(zmd_id)) {
			entity.setZmd_id(Long.valueOf(zmd_id));
		}
		if (GenericValidator.isLong(sell_bill_id)) {
			entity.setSell_bill_id(Long.valueOf(sell_bill_id));
		}
		if (StringUtils.isNotBlank(add_date_ge)) {
			entity.getMap().put("add_date_ge", add_date_ge);
		}
		if (StringUtils.isNotBlank(add_date_le)) {
			entity.getMap().put("add_date_le", add_date_le);
		}
		if (GenericValidator.isLong(sell_state)) {
			entity.setSell_state(Long.valueOf(sell_state));
		}
		if (StringUtils.isNotBlank(add_user_realname_like)) {
			entity.getMap().put("add_user_realname_like", add_user_realname_like);
		}
		if (StringUtils.isNotBlank(buyer_name_like)) {
			entity.getMap().put("buyer_name_like", buyer_name_like);
		}
		if (GenericValidator.isLong(pay_way)) {
			entity.setPay_way(Long.valueOf(pay_way));
		}

		Long recordCount = super.getFacade().getKonkaXxSellBillService().getKonkaXxSellBillCountForJs(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setCount(pager.getRowCount());
		entity.getRow().setFirst(pager.getFirstRow());

		List<KonkaXxSellBill> entityList = super.getFacade().getKonkaXxSellBillService()
				.getKonkaXxSellBillForJSPaginatedList(entity);

		request.setAttribute("entityList", entityList);
		request.setAttribute("konkaDepts", super.getKonkaDepts());

		return mapping.findForward("list");

	}

	public ActionForward showOutStoreOrder(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		// setNaviStringToRequestScope(form, request);
		// DynaBean dynaBean = (DynaBean) form;
		// String sell_bill_id = (String) dynaBean.get("sell_bill_id");
		//
		// if (!GenericValidator.isLong(sell_bill_id)) {
		// String msg = getMessage(request, "konka.xx.zmd.sell.id.not.num");
		// saveError(request, msg);
		// return mapping.findForward("list");
		// }
		//
		// KonkaXxSellBill konkaXxSellBill = new KonkaXxSellBill();
		// konkaXxSellBill.setSell_bill_id(Long.valueOf(sell_bill_id));
		// konkaXxSellBill =
		// super.getFacade().getKonkaXxSellBillService().getKonkaXxSellBill(konkaXxSellBill);
		//
		// if (null == konkaXxSellBill) {
		// String msg = getMessage(request, "konka.xx.zmd.sell.id.not.exist");
		// saveError(request, msg);
		// return mapping.findForward("list");
		// }
		//
		// String p_index_name = "";
		// if (null != konkaXxSellBill.getSell_post_p_index()) {
		// p_index_name =
		// super.getPIndexName(konkaXxSellBill.getSell_post_p_index().toString(),
		// "");
		// }
		// String buyer_tel = null == konkaXxSellBill.getBuyer_tel() ?
		// konkaXxSellBill.getBuyer_phone() : konkaXxSellBill
		// .getBuyer_tel();
		//
		// request.setAttribute("zmd_sn", konkaXxSellBill.getZmd_sn());
		// request.setAttribute("sell_date", konkaXxSellBill.getSell_date());
		// request.setAttribute("buyer_name", konkaXxSellBill.getBuyer_name());
		// request.setAttribute("sell_bill_id",
		// konkaXxSellBill.getSell_bill_id().toString());
		// request.setAttribute("p_index_name", p_index_name);
		// request.setAttribute("buyer_tel", buyer_tel);
		// request.setAttribute("addr", konkaXxSellBill.getSell_post_addr());
		//
		// // if (null != konkaXxSellBill.getPay_way()) {
		// // KonkaXxBaseType konkaXxBaseType = new KonkaXxBaseType();
		// // konkaXxBaseType.setType_id(konkaXxSellBill.getPay_way());
		// // konkaXxBaseType =
		// super.getFacade().getKonkaXxBaseTypeService().getKonkaXxBaseType(konkaXxBaseType);
		// // request.setAttribute("pay_way", konkaXxBaseType.getType_name());
		// // }
		//
		// KonkaXxSellBillDetails entity = new KonkaXxSellBillDetails();
		// entity.setSell_bill_id(Long.valueOf(sell_bill_id));
		//
		// List<KonkaXxSellBillDetails> entityList =
		// super.getFacade().getKonkaXxSellBillDetailsService()
		// .getKonkaXxSellBillDetailsWithSpecList(entity);
		//
		// BigDecimal total_pay = new BigDecimal("0"); // 销售总价
		// if (null != entityList && entityList.size() > 0) {
		// for (KonkaXxSellBillDetails temp : entityList) {
		// total_pay = total_pay.add(temp.getPrice().multiply(new
		// BigDecimal(temp.getCounts().toString())));
		// }
		// request.setAttribute("total_pay", total_pay.toString());
		// }
		//
		// request.setAttribute("entityList", entityList);

		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String sell_bill_id = (String) dynaBean.get("sell_bill_id");

		if (!GenericValidator.isLong(sell_bill_id)) {
			String msg = getMessage(request, "konka.xx.zmd.sell.id.not.num");
			saveError(request, msg);
			return mapping.findForward("list");
		}

		KonkaXxSellBill konkaXxSellBill = new KonkaXxSellBill();
		konkaXxSellBill.setSell_bill_id(Long.valueOf(sell_bill_id));
		konkaXxSellBill = super.getFacade().getKonkaXxSellBillService().getKonkaXxSellBill(konkaXxSellBill);

		if (null == konkaXxSellBill) {
			String msg = getMessage(request, "konka.xx.zmd.sell.id.not.exist");
			saveError(request, msg);
			return mapping.findForward("list");
		}

		String p_index_name = "";
		if (null != konkaXxSellBill.getSell_post_p_index()) {
			p_index_name = super.getPIndexName(konkaXxSellBill.getSell_post_p_index().toString(), "");
		}
		String buyer_tel = null == konkaXxSellBill.getBuyer_tel() ? konkaXxSellBill.getBuyer_phone() : konkaXxSellBill
				.getBuyer_tel();

		request.setAttribute("zmd_sn", konkaXxSellBill.getZmd_sn());
		request.setAttribute("sell_date", konkaXxSellBill.getSell_date());
		request.setAttribute("buyer_name", konkaXxSellBill.getBuyer_name());
		request.setAttribute("sell_bill_id", konkaXxSellBill.getSell_bill_id().toString());
		request.setAttribute("p_index_name", p_index_name);
		request.setAttribute("buyer_tel", buyer_tel);
		request.setAttribute("addr", konkaXxSellBill.getSell_post_addr());
		request.setAttribute("pay_way_id", konkaXxSellBill.getPay_way());
		request.setAttribute("money_of_deposit", konkaXxSellBill.getMoney_of_deposit());
		request.setAttribute("today", new Date());

		// KonkaXxSellBillDetails entity = new KonkaXxSellBillDetails();
		// entity.setSell_bill_id(Long.valueOf(sell_bill_id));
		//
		// List<KonkaXxSellBillDetails> entityList =
		// super.getFacade().getKonkaXxSellBillDetailsService()
		// .getKonkaXxSellBillDetailsWithSpecList(entity);
		//
		// BigDecimal total_pay = new BigDecimal("0"); // 销售总价
		// if (null != entityList && entityList.size() > 0) {
		// for (KonkaXxSellBillDetails temp : entityList) {
		// total_pay = total_pay.add(temp.getPrice().multiply(new
		// BigDecimal(temp.getCounts().toString())));
		// }
		// request.setAttribute("total_pay", total_pay.toString());
		// }

		KonkaXxSellBillDetailsDst entity = new KonkaXxSellBillDetailsDst();
		entity.setSell_bill_id(Long.valueOf(sell_bill_id));
		List<KonkaXxSellBillDetailsDst> entityList = getFacade().getKonkaXxSellBillDetailsDstService()
				.getKonkaXxSellBillDetailsDstForPrintOutOrders(entity);

		request.setAttribute("entityList", entityList);
		if (null != entityList && entityList.size() > 0) {
			Double total_pay = 0D;
			for (KonkaXxSellBillDetailsDst kd : entityList) {
				BigDecimal price = (BigDecimal) kd.getMap().get("price");
				Long count = kd.getCounts();
				total_pay = total_pay + price.doubleValue() * count.doubleValue();
			}
			request.setAttribute("total_pay", total_pay.toString());
		}

		return mapping.findForward("view");
	}

	public ActionForward toExcel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		try {
			super.toExcel(mapping, form, request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
