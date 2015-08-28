package com.ebiz.mmt.web.struts.manager.zmd;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.BaseProvinceListFour;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaXxDistEmployee;
import com.ebiz.mmt.domain.KonkaXxSellBill;
import com.ebiz.mmt.domain.KonkaXxSellBillDetails;
import com.ebiz.mmt.domain.KonkaXxSellBillDetailsDst;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author hu hao
 *@version 2012-3-3
 */
public class KonkaXxZmdReturnSalesOrderAction extends BaseZmdAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}
		super.getModPopeDom(form, request);
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);

		Pager pager = (Pager) dynaBean.get("pager");
		String sell_bill_id_like = (String) dynaBean.get("sell_bill_id_like");
		String dept_id = (String) dynaBean.get("dept_id");

		KonkaXxSellBill entity = new KonkaXxSellBill();

		if (StringUtils.isNotBlank(sell_bill_id_like)) {
			entity.getMap().put("sell_bill_id_like", sell_bill_id_like);
		}
		if (StringUtils.isNotBlank(dept_id)) {
			entity.setDept_id(Long.valueOf(dept_id));
		}
		entity.setReturn_state(1);
		PeProdUser user_id = super.getSessionUserInfo(request);
		// 取用户角色
		// PeRoleUser peRoleUser = super.getRoleInfoByThisLogin(request);
		List<PeRoleUser> peRoleUserList = getPeRoleList(user_id.getId());

		Boolean role_id_gt_400 = false;
		Boolean role_id_gt_300_350 = false;
		Boolean role_id_gt_200_300 = false;
		Boolean role_id_eq_350 = false;
		if (peRoleUserList.size() > 0) {
			for (PeRoleUser temp : peRoleUserList) {
				if (temp.getRole_id() <= 400) {
					role_id_gt_400 = true;
				}
				if (temp.getRole_id() == 350) {
					role_id_eq_350 = true;
				}
				if (temp.getRole_id() >= 200 && temp.getRole_id() <= 300) {
					role_id_gt_200_300 = true;
				}
				if (temp.getRole_id() >= 300 && temp.getRole_id() < 350) {
					role_id_gt_300_350 = true;
				}
			}
		}

		Long dept_id_value = null;
		KonkaDept kDept = getKonkaDeptForFgs(user_id.getDept_id());
		if (kDept != null)
			dept_id_value = kDept.getDept_id();

		if (!role_id_gt_400) {
			String msg = super.getMessage(request, "konka.xx.zmd.role.error");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}
		if (role_id_gt_300_350) {
			entity.setDept_id(dept_id_value);
		} else if (role_id_eq_350) {// 分公司财务
			request.setAttribute("is_fgscw", true);
		} else if (role_id_gt_200_300) {
			request.setAttribute("dept_id", dept_id_value);
		}

		Long recordCount = super.getFacade().getKonkaXxSellBillService().getKonkaXxSellBillForDeptCount(entity);

		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		request.setAttribute("konkaDeptList", super.getKonkaDepts());

		List<KonkaXxSellBill> entityList = super.getFacade().getKonkaXxSellBillService()
				.getKonkaXxSellBillForDeptPaginatedList(entity);
		for (KonkaXxSellBill temp : entityList) {
			KonkaXxSellBillDetails konkaXxSellBillDetails = new KonkaXxSellBillDetails();
			konkaXxSellBillDetails.setSell_bill_id(temp.getSell_bill_id());
			List<KonkaXxSellBillDetails> konkaXxSellBillDetailsList = super.getFacade()
					.getKonkaXxSellBillDetailsService().getKonkaXxSellBillDetailsList(konkaXxSellBillDetails);
			if (null != konkaXxSellBillDetailsList && konkaXxSellBillDetailsList.size() > 0) {
				temp.getMap().put("konkaXxSellBillDetailsList", konkaXxSellBillDetailsList);
			}
		}
		request.setAttribute("entityList", entityList);
		return mapping.findForward("list");
	}

	public ActionForward returnBill(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}
		super.getModPopeDom(form, request);
		setNaviStringToRequestScope(form, request);

		return mapping.findForward("input");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		// String mod_id = (String) dynaBean.get("mod_id");

		String sell_bill_id = (String) dynaBean.get("sell_bill_id");

		KonkaXxSellBill entity = new KonkaXxSellBill();

		if (StringUtils.isNotBlank(sell_bill_id)) {
			entity.setSell_bill_id(Long.valueOf(sell_bill_id));
		}

		entity = super.getFacade().getKonkaXxSellBillService().getKonkaXxSellBill(entity);
		if (null == entity || entity.getReturn_state() == 1) {
			String msg = super.getMessage(request, "konka.xx.zmd.bill.return.error");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		setOrderNavigationRequest(Long.valueOf(sell_bill_id), request);

		KonkaXxSellBill konkaXxSellBill = new KonkaXxSellBill();
		konkaXxSellBill.setSell_bill_id(Long.valueOf(sell_bill_id));
		konkaXxSellBill = getFacade().getKonkaXxSellBillService().getKonkaXxSellBill(konkaXxSellBill);
		if (null == konkaXxSellBill) {
			return null;
		}
		super.copyProperties(form, konkaXxSellBill);

		if (null != konkaXxSellBill.getSell_post_p_index()) {
			BaseProvinceListFour baseProvinceListFour = new BaseProvinceListFour();
			baseProvinceListFour.setP_index(konkaXxSellBill.getSell_post_p_index());
			baseProvinceListFour = getFacade().getBaseProvinceListFourService().getBaseProvinceListFour(
					baseProvinceListFour);
			if (null != baseProvinceListFour) {
				dynaBean.set("sell_post_area", baseProvinceListFour.getFull_name());
			}
		}

		if (null != konkaXxSellBill.getBuyer_p_index()) {

			BaseProvinceListFour baseProvinceListFour = new BaseProvinceListFour();
			baseProvinceListFour.setP_index(konkaXxSellBill.getBuyer_p_index());
			baseProvinceListFour = getFacade().getBaseProvinceListFourService().getBaseProvinceListFour(
					baseProvinceListFour);
			if (null != baseProvinceListFour) {

				dynaBean.set("buyer_area", baseProvinceListFour.getFull_name());
			}
		}

		KonkaDept konkaDept = new KonkaDept();
		konkaDept.setDept_id(Long.valueOf(konkaXxSellBill.getDept_id()));
		konkaDept = getFacade().getKonkaDeptService().getKonkaDept(konkaDept);
		if (null == konkaDept) {
			return null;
		}
		dynaBean.set("dept_name", konkaDept.getDept_name());

		Integer total_counts = 0;

		KonkaXxSellBillDetails konkaXxSellBillDetails = new KonkaXxSellBillDetails();
		konkaXxSellBillDetails.setSell_bill_id(konkaXxSellBill.getSell_bill_id());
		// konkaXxSellBillDetails.setLock_stock_state(1);
		List<KonkaXxSellBillDetails> konkaXxSellBillDetailsList = getFacade().getKonkaXxSellBillDetailsService()
				.getKonkaXxSellBillDetailsList(konkaXxSellBillDetails);
		if (null != konkaXxSellBillDetailsList && konkaXxSellBillDetailsList.size() > 0) {
			for (KonkaXxSellBillDetails kxsbd : konkaXxSellBillDetailsList) {
				KonkaXxSellBillDetailsDst kxsbdd = new KonkaXxSellBillDetailsDst();
				kxsbdd.setSell_bill_details_id(kxsbd.getSell_bill_details_id());
				List<KonkaXxSellBillDetailsDst> list = getFacade().getKonkaXxSellBillDetailsDstService()
						.getKonkaXxSellBillDetailsDstList(kxsbdd);
				kxsbd.getMap().put("dstList", list);

				kxsbd.getMap().put("all_money", kxsbd.getPrice().multiply(new BigDecimal(kxsbd.getCounts())));

				total_counts += kxsbd.getCounts();
			}

		}
		request.setAttribute("konkaXxSellBillDetailsList", konkaXxSellBillDetailsList);
		request.setAttribute("total_counts", total_counts);

		if (konkaXxSellBill.getDist_employee_id() != null) {
			KonkaXxDistEmployee user = new KonkaXxDistEmployee();
			user.setDist_employee_id(konkaXxSellBill.getDist_employee_id());
			user.setIs_del(0);
			user = getFacade().getKonkaXxDistEmployeeService().getKonkaXxDistEmployee(user);
			if (null != user) {
				dynaBean.set("dist_employee_name", user.getReal_name());
			}
		}

		super.setBaseTypeListByParIdToRequest(50000L, request);
		super.setBaseTypeListByParIdToRequest(70000L, request);
		super.setBaseTypeListByParIdToRequest(90000L, request);

		return mapping.findForward("view");

	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");

		String sell_bill_id = (String) dynaBean.get("sell_bill_id");

		KonkaXxSellBill entity = new KonkaXxSellBill();

		if (StringUtils.isNotBlank(sell_bill_id)) {
			entity.setSell_bill_id(Long.valueOf(sell_bill_id));
		}
		entity = super.getFacade().getKonkaXxSellBillService().getKonkaXxSellBill(entity);
		if (null == entity || entity.getReturn_state() == 1) {
			String msg = super.getMessage(request, "konka.xx.zmd.bill.return.error");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		} else {
			entity.setReturn_state(1);
			entity.setReturn_date(new Date());
		}

		super.getFacade().getKonkaXxSellBillService().modifyKonkaXxSellBill(entity);
		saveMessage(request, "entity.inserted");

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		return forward;
	}
}
