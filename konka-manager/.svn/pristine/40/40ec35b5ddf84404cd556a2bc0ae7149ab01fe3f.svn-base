package com.ebiz.mmt.web.struts.manager.zmd;

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
import com.ebiz.mmt.domain.KonkaXxSellBillDetails;
import com.ebiz.mmt.domain.KonkaXxStdPd;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Wu,ShangLong
 * @version 2012-4-1
 */
public class KonkaXxZmdStorePdInRoadAction extends BaseZmdAction {

	protected ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String pd_cls = (String) dynaBean.get("pd_cls");
		String md_name = (String) dynaBean.get("md_name");

		PeProdUser user_info = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

		// 取产品类别
		request.setAttribute("basePdClazzList", getBasePdClazzList());

		// 获取产品型号
		KonkaXxStdPd konkaXxStdPd = new KonkaXxStdPd();
		konkaXxStdPd.setMd_type(0);
		List<KonkaXxStdPd> konkaXxStdPdList = super.getFacade().getKonkaXxStdPdService().getKonkaXxStdPdList(
				konkaXxStdPd);
		request.setAttribute("konkaXxStdPdList", konkaXxStdPdList);

		KonkaDept dept = getKonkaDeptForFgs(user_info.getDept_id());

		KonkaXxSellBillDetails entity = new KonkaXxSellBillDetails();
		if (dept != null)
			entity.getMap().put("dept_id", dept.getDept_id());
		entity.getMap().put("pd_in_load", true);

		if (GenericValidator.isLong(pd_cls)) {
			entity.setPd_cls(Long.valueOf(pd_cls));
		}

		if (StringUtils.isNotBlank(md_name)) {
			entity.setMd_name(md_name);
		}

		Long recordCount = super.getFacade().getKonkaXxSellBillDetailsService().getKonkaXxSellBillDetailsInRoadCount(
				entity);

		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setCount(pager.getRowCount());
		entity.getRow().setFirst(pager.getFirstRow());

		List<KonkaXxSellBillDetails> entityList = super.getFacade().getKonkaXxSellBillDetailsService()
				.getKonkaXxSellBillDetailsInRoadPaginatedList(entity);

		if (entityList.size() > 0) {
			for (KonkaXxSellBillDetails temp : entityList) {
				if (temp.getPd_cls() != null && temp.getMd_name() != null) {
					KonkaXxSellBillDetails konkaXxSellBillDetails = new KonkaXxSellBillDetails();
					konkaXxSellBillDetails.setMd_name(temp.getMd_name());
					konkaXxSellBillDetails.setPd_cls(temp.getPd_cls());
					konkaXxSellBillDetails.getMap().put("pd_in_load", true);
					List<KonkaXxSellBillDetails> detileList = super.getFacade().getKonkaXxSellBillDetailsService()
							.getKonkaXxSellBillDetailsForInRoadList(konkaXxSellBillDetails);
					temp.getMap().put("detileList", detileList);
				}
			}
		}

		request.setAttribute("entityList", entityList);

		return mapping.findForward("list");

	}
}
