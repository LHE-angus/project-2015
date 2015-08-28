package com.ebiz.mmt.web.struts.mobile.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaMobileCategory;
import com.ebiz.mmt.domain.KonkaMobileThingsUseReport;
import com.ebiz.mmt.domain.MobileSelectItem;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;

public class ThingsUseReportAction extends MobileBaseAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return this.add(mapping, form, request, response);
	}

	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);

		String mod_id = (String) dynaBean.get("mod_id");
		dynaBean.set("mod_id", mod_id);

		List<KonkaMobileCategory> wuliaoList = getFacade()
				.getKonkaMobileCategoryService().getWuliaoCategoryList();
		List<MobileSelectItem> list = new ArrayList<MobileSelectItem>();
		for (KonkaMobileCategory _t : wuliaoList) {
			MobileSelectItem t = new MobileSelectItem();
			t.setId(_t.getC_index().toString());
			t.setName(_t.getC_name());
			list.add(t);
		}
		request.setAttribute("wuliaoList", list);

		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DynaBean lazyForm = (DynaBean) form;

		String thing_id = (String) lazyForm.get("thing_id");
		String thing_num = (String) lazyForm.get("sales_count");

		String mod_id = (String) lazyForm.get("mod_id");

		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(
				Constants.USER_MOBILE);

		KonkaMobileThingsUseReport report = new KonkaMobileThingsUseReport();

		// 办事处
		report.setOffice_id(ui.getDept_id());

		// 分公司
		if (ui.getPar_dept_id() != null) {
			KonkaDept konkaDept = new KonkaDept();
			konkaDept.setDept_id(ui.getPar_dept_id());
			konkaDept = super.getFacade().getKonkaDeptService().getKonkaDept(
					konkaDept);
			if (konkaDept != null) {
				report.setSubcomp_id(konkaDept.getDept_id());
			}
		} else {
			super.renderText(response, "请重新登录！");
			return null;
		}

		if (StringUtils.isNotBlank(thing_id)) {
			report.setThing_id(Long.valueOf(thing_id));
		} else {
			super.renderText(response, "请选择物料！");
			return null;
		}
		if (StringUtils.isNotBlank(thing_num)) {
			if (NumberUtils.isNumber(thing_num))
				report.setThing_num(Long.valueOf(thing_num));
			else {
				super.renderText(response, "使用量必须为数字！");
				return null;
			}
		} else {
			super.renderText(response, "请填写使用量！");
			return null;
		}
		report.setReprot_man(Long.valueOf(ui.getId()));
		report.setReprot_time(new Date());

		getFacade().getKonkaMobileThingsUseReportService()
				.createKonkaMobileThingsUseReportForHis(report);

		KonkaMobileCategory category = new KonkaMobileCategory();
		category.setC_index(report.getThing_id());
		category = super.getFacade().getKonkaMobileCategoryService()
				.getKonkaMobileCategory(category);

		super.createMobileSysOperLog(request, "KONKA_MOBILE_SAIL_DATA", report
				.getId(), mod_id, "" + "新增：新增了一条物料名称：" + category.getC_name()
				+ " 使用量：" + report.getThing_num() + "的数据", BeanUtils.describe(
				report).toString());

		super.renderText(response, "success");
		return null;
	}
}