package com.ebiz.mmt.web.struts.manager.zmd;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaXxZmd;
import com.ebiz.mmt.domain.KonkaXxZmdCreditApply;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.ssi.web.struts.bean.Pager;

public class KonkaXxZmdInquiryCreditLineAction extends BaseZmdAction {

	protected ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");

		PeProdUser ui = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

		Long dept_id = null;
		KonkaDept kDept = getKonkaDeptForFgs(ui.getDept_id());
		if (kDept != null)
			dept_id = kDept.getDept_id();

		KonkaXxZmdCreditApply entity = new KonkaXxZmdCreditApply();
		super.copyProperties(entity, form);

		entity.setDept_id(dept_id);

		Long recordCount = getFacade().getKonkaXxZmdCreditApplyService().getKonkaXxZmdCreditApplyCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setCount(pager.getRowCount());
		entity.getRow().setFirst(pager.getFirstRow());
		List<KonkaXxZmdCreditApply> list = getFacade().getKonkaXxZmdCreditApplyService()
				.getKonkaXxZmdCreditApplyPaginatedList(entity);
		if (null != list && list.size() > 0) {
			for (KonkaXxZmdCreditApply kxzca : list) {
				KonkaXxZmd zmd = new KonkaXxZmd();
				zmd.setZmd_id(kxzca.getZmd_id());
				zmd = getFacade().getKonkaXxZmdService().getKonkaXxZmd(zmd);
				if (null != zmd) {
					kxzca.getMap().put("zmd_sn", zmd.getZmd_sn());
				}
			}
		}
		request.setAttribute("entityList", list);

		KonkaXxZmd konkaXxZmd = new KonkaXxZmd();
		konkaXxZmd.setArc_state(20300);
		konkaXxZmd.setDept_id(dept_id);
		// konkaXxZmd.getMap().put("zmdNotIn", ui.getDept_id());
		List<KonkaXxZmd> zmdList = getFacade().getKonkaXxZmdService().getKonkaXxZmdList(konkaXxZmd);
		request.setAttribute("zmdList", zmdList);

		return mapping.findForward("list");
	}
}
