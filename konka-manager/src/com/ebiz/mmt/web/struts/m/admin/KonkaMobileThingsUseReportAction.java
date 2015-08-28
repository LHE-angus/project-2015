package com.ebiz.mmt.web.struts.m.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaMobileCategory;
import com.ebiz.mmt.domain.KonkaMobileThingsUseReport;
import com.ebiz.mmt.domain.MobileSelectItem;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;

public class KonkaMobileThingsUseReportAction extends MobileBaseAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);

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

		return mapping.findForward("list");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DynaBean lazyForm = (DynaBean) form;

		String thing_id = (String) lazyForm.get("thing_id");
		String thing_num = (String) lazyForm.get("sales_count");

		PeProdUser ui = super.getSessionUserInfo(request);

		KonkaMobileThingsUseReport report = new KonkaMobileThingsUseReport();

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

		super.renderText(response, "success");
		return null;
	}
}