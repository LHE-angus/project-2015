package com.ebiz.mmt.web.struts.manager.zmd;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaXxDistAccountRec;
import com.ebiz.mmt.domain.KonkaXxSellBill;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Hu Hao
 * @version 2012-04-01
 */
public class KonkaXxDistAccountRecAction extends BaseZmdAction {

	protected ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);

		PeProdUser user_info = super.getSessionUserInfo(request);
		// PeRoleUser peRoleUser = super.getRoleInfoByThisLogin(request);
		Boolean role_id_200_btw_400 = getRoleIdFlag(user_info.getId(), 199L, 401L);
		if (!role_id_200_btw_400) {
			String msg = super.getMessage(request, "konka.xx.zmd.role.error");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		Pager pager = (Pager) dynaBean.get("pager");
		String plan_date_ge = (String) dynaBean.get("plan_date_ge");
		String plan_date_le = (String) dynaBean.get("plan_date_le");
		KonkaXxDistAccountRec entity = new KonkaXxDistAccountRec();

		if (StringUtils.isNotBlank(plan_date_ge)) {
			entity.getMap().put("plan_date_ge", plan_date_ge);
		}
		if (StringUtils.isNotBlank(plan_date_le)) {
			entity.getMap().put("plan_date_le", plan_date_le);
		}

		entity.setDept_id(getKonkaDeptForFgs(user_info.getDept_id()).getDept_id());
		entity.setState(0);
		Long recordCount = super.getFacade().getKonkaXxDistAccountRecService().getKonkaXxDistAccountRecCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<KonkaXxDistAccountRec> entityList = super.getFacade().getKonkaXxDistAccountRecService()
				.getKonkaXxDistAccountRecPaginatedList(entity);
		request.setAttribute("entityList", entityList);
		return mapping.findForward("list");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String dist_id = (String) dynaBean.get("dist_id");

		KonkaXxSellBill konkaXxSellBill = new KonkaXxSellBill();
		konkaXxSellBill.setDist_id(Long.valueOf(dist_id));
		List<KonkaXxSellBill> konkaXxSellBillList = super.getFacade().getKonkaXxSellBillService()
				.getKonkaXxSellBillList(konkaXxSellBill);

		request.setAttribute("konkaXxSellBillList", konkaXxSellBillList);

		KonkaXxDistAccountRec entity = new KonkaXxDistAccountRec();
		entity.setDist_id(Long.valueOf(dist_id));

		entity = getFacade().getKonkaXxDistAccountRecService().getKonkaXxDistAccountRec(entity);
		if (null == entity) {
			saveMessage(request, "entity.missing");
			return mapping.findForward("list");
		} else {
			entity.setQueryString(super.serialize(request, "id", "method"));
			super.copyProperties(form, entity);
			return mapping.findForward("input");
		}
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");

		String remit_sn = (String) dynaBean.get("remit_sn");
		String man = (String) dynaBean.get("man");
		String dist_id = (String) dynaBean.get("dist_id");

		KonkaXxDistAccountRec entity = new KonkaXxDistAccountRec();

		if (StringUtils.isNotBlank(remit_sn)) {
			entity.setRemit_sn(remit_sn);
		}
		if (StringUtils.isNotBlank(man)) {
			entity.setMan(man);
		}
		entity.setState(1);

		entity.setDist_id(Long.valueOf(dist_id));
		super.getFacade().getKonkaXxDistAccountRecService().modifyKonkaXxDistAccountRecWithSellBill(entity);
		saveMessage(request, "entity.updated");

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		return forward;
	}
}
