package com.ebiz.mmt.web.struts.mobile.admin;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaMobileRetailRest;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;

/**
 * @author Wang Hao
 */
public class RestReportAction extends BaseAction {

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

		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(
				Constants.USER_MOBILE);
		KonkaMobileRetailRest kmrr = new KonkaMobileRetailRest();
		kmrr.setAdd_id(ui.getId());
		List<KonkaMobileRetailRest> restList = super.getFacade()
				.getKonkaMobileRetailRestService()
				.getKonkaMobileRetailRestList(kmrr);

		request.setAttribute("restlist", restList);

		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DynaBean lazyForm = (DynaBean) form;

		String date = (String) lazyForm.get("date");
		String retail_desc = (String) lazyForm.get("retail_desc");
		// String mod_id = (String) lazyForm.get("mod_id");

		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(
				Constants.USER_MOBILE);

		KonkaMobileRetailRest retailRest = new KonkaMobileRetailRest();
		retailRest.setAdd_id(ui.getId());
		retailRest.setAdd_name(ui.getReal_name());
		retailRest.setAdd_date(new Date());
		retailRest.setRetail_id(ui.getId());
		retailRest.setRetail_name(ui.getReal_name());

		// 办事处
		retailRest.setOffice_id(ui.getDept_id());
		retailRest.setOffice_name(ui.getDepartment());

		// 分公司
		if (ui.getPar_dept_id() != null) {
			KonkaDept konkaDept = new KonkaDept();
			konkaDept.setDept_id(ui.getPar_dept_id());
			konkaDept = super.getFacade().getKonkaDeptService().getKonkaDept(
					konkaDept);
			if (konkaDept != null) {
				retailRest.setSubcomp_id(konkaDept.getDept_id());
				retailRest.setSubcomp_name(konkaDept.getDept_name());
			}
		}

		if (StringUtils.isNotEmpty(retail_desc)) {
			retailRest.setRetail_desc(retail_desc);
		} else {
			super.renderText(response, "请休假原因！");
			return null;
		}
		if (StringUtils.isNotEmpty(date)) {
			SimpleDateFormat DataFormat = new SimpleDateFormat("MM/dd/yyyy");
			retailRest.setRetail_date(DataFormat.parse(date));
		} else {
			super.renderText(response, "请填写休假日期！");
			return null;
		}
		getFacade().getKonkaMobileRetailRestService()
				.createKonkaMobileRetailRest(retailRest);

		// super.createMobileSysOperLog(request, "KONKA_MOBILE_SAIL_DATA",
		// retailRest.getId(), mod_id, "" + "新增："
		// +ui.getUser_name()+"请求于"+retailRest.getRetail_date()+"休息",
		// BeanUtils.describe(retailRest).toString());

		super.renderText(response, "success");
		return null;
	}
}
