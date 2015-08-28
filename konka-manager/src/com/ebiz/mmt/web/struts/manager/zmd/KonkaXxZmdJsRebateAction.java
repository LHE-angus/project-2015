package com.ebiz.mmt.web.struts.manager.zmd;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaXxSellBill;
import com.ebiz.mmt.domain.KonkaXxZmd;
import com.ebiz.mmt.domain.KonkaXxZmdSalary;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Ren,zhong
 * @version 2012-04-06
 */
public class KonkaXxZmdJsRebateAction extends BaseZmdAction {

	protected ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		// DynaBean dynaBean = (DynaBean) form;

		PeProdUser ui = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

		KonkaDept kDept = getKonkaDeptForFgs(ui.getDept_id());
		KonkaXxZmd konkaXxZmd = new KonkaXxZmd();
		konkaXxZmd.setArc_state(20300);
		if (kDept != null)
			konkaXxZmd.setDept_id(kDept.getDept_id());
		// konkaXxZmd.getMap().put("zmdNotIn", ui.getDept_id());
		List<KonkaXxZmd> zmdList = getFacade().getKonkaXxZmdService().getKonkaXxZmdList(konkaXxZmd);
		request.setAttribute("zmdList", zmdList);

		return mapping.findForward("list");
	}

	public ActionForward stepOne(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String msg = request.getParameter("msg");

		String zmd_id = (String) dynaBean.get("zmd_id");
		if (StringUtils.isBlank(zmd_id)) {
			renderJavaScript(response, "alert('参数丢失！');history.back()");
			return null;
		}

		KonkaXxZmd zmd = new KonkaXxZmd();
		zmd.setZmd_id(Long.valueOf(zmd_id));
		zmd = getFacade().getKonkaXxZmdService().getKonkaXxZmd(zmd);

		KonkaXxSellBill entity = new KonkaXxSellBill();
		entity.setZmd_id(Long.valueOf(zmd_id));
		entity.setJs_bill_state(1);
		entity.setOrder_type(1);
		entity.setStock_from(1);

		Long recordCount = getFacade().getKonkaXxSellBillService().getKonkaXxSellBillCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setCount(pager.getRowCount());
		entity.getRow().setFirst(pager.getFirstRow());
		List<KonkaXxSellBill> list = getFacade().getKonkaXxSellBillService().getKonkaXxSellBillPaginatedList(entity);
		if (null != list && list.size() > 0) {
			for (KonkaXxSellBill bill : list) {
				if (null != zmd) {
					bill.getMap().put("zmd_sn", zmd.getZmd_sn());
				}
			}
		}
		request.setAttribute("entityList", list);

		dynaBean.set("zmd_id", zmd_id);
		if (null != zmd && zmd.getDept_id() != null) {
			dynaBean.set("dept_id", zmd.getDept_id().toString());
		}

		if ("1".equals(msg)) {
			msg = super.getMessage(request, "konka.xx.zmd.js.rabate.success");
			dynaBean.set("msg", msg);
		}

		return mapping.findForward("input");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");

		String zmd_id = (String) dynaBean.get("zmd_id");

		if (StringUtils.isBlank(zmd_id)) {
			renderJavaScript(response, "alert('参数丢失！');history.back()");
			return null;
		}

		KonkaXxZmd zmd = new KonkaXxZmd();
		zmd.setZmd_id(Long.valueOf(zmd_id));
		zmd = getFacade().getKonkaXxZmdService().getKonkaXxZmd(zmd);

		KonkaXxZmdSalary entity = new KonkaXxZmdSalary();
		entity.setZmd_id(Long.valueOf(zmd_id));
		Long recordCount = getFacade().getKonkaXxZmdSalaryService().getKonkaXxZmdSalaryCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setCount(pager.getRowCount());
		entity.getRow().setFirst(pager.getFirstRow());
		List<KonkaXxZmdSalary> list = getFacade().getKonkaXxZmdSalaryService().getKonkaXxZmdSalaryPaginatedList(entity);
		if (null != list && list.size() > 0) {
			for (KonkaXxZmdSalary kzs : list) {
				if (null != zmd) {
					kzs.getMap().put("zmd_sn", zmd.getZmd_sn());
				}
			}
		}
		request.setAttribute("entityList", list);

		dynaBean.set("zmd_id", zmd_id);
		if (null != zmd) {
			dynaBean.set("dept_id", zmd.getDept_id()+"");
		}

		return mapping.findForward("view");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String zmd_id = (String) dynaBean.get("zmd_id");
		String dept_id = (String) dynaBean.get("dept_id");
		String sell_bill_id = (String) dynaBean.get("sell_bill_id");
		String[] pks = (String[]) dynaBean.get("pks");

		if (StringUtils.isBlank(zmd_id) || StringUtils.isBlank(dept_id)) {
			renderJavaScript(response, "alert('参数丢失！');history.back();");
			return null;
		}

		// PeProdUser ui = (PeProdUser) super.getSessionAttribute(request,
		// Constants.USER_INFO);

		KonkaXxZmdSalary salary = new KonkaXxZmdSalary();
		salary.setZmd_id(Long.valueOf(zmd_id));
		salary.setDept_id(Long.valueOf(dept_id));
		salary.setAdd_date(new Date());

		KonkaXxSellBill entity = new KonkaXxSellBill();
		entity.setJs_bill_state(2);
		entity.setJs_bill_date(new Date());
		entity.setKonkaXxZmdSalary(salary);

		if (StringUtils.isNotBlank(sell_bill_id)) {
			entity.setSell_bill_id(Long.valueOf(sell_bill_id));
		} else if (ArrayUtils.isNotEmpty(pks)) {
			entity.getMap().put("pks", pks);
		}

		super.getFacade().getKonkaXxSellBillService().modifyKonkaXxSellBillWithJsRebate(entity);

		super.renderJavaScript(response,
				"window.onload=function(){location.href='KonkaXxZmdJsRebate.do?method=stepOne&msg=1&zmd_id=" + zmd_id
						+ "'}");
		return null;
	}

}
