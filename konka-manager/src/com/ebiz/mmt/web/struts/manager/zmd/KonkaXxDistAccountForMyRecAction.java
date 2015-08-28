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

import com.ebiz.mmt.domain.KonkaXxDistAccountRec;
import com.ebiz.mmt.domain.KonkaXxSellBill;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Hu Hao
 * @version 2012-03-22
 */
public class KonkaXxDistAccountForMyRecAction extends BaseZmdAction{

	protected ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}
		super.getModPopeDom(form, request);
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
		String state = (String) dynaBean.get("state");
		KonkaXxDistAccountRec entity = new KonkaXxDistAccountRec();

		if (StringUtils.isNotBlank(plan_date_ge)) {
			entity.getMap().put("plan_date_ge", plan_date_ge);
		}
		if (StringUtils.isNotBlank(plan_date_le)) {
			entity.getMap().put("plan_date_le", plan_date_le);
		}
		if(StringUtils.isNotBlank(state)){
			entity.setState(Integer.valueOf(state));
		}
		
		entity.setDept_id(getKonkaDeptForFgs(user_info.getDept_id()).getDept_id());
		
		Long recordCount = super.getFacade().getKonkaXxDistAccountRecService().getKonkaXxDistAccountRecCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		
		List<KonkaXxDistAccountRec> entityList = super.getFacade().getKonkaXxDistAccountRecService().getKonkaXxDistAccountRecPaginatedList(entity);
		request.setAttribute("entityList", entityList);
		return mapping.findForward("list");
	}
	
	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String dist_id = (String) dynaBean.get("dist_id");

		if (!GenericValidator.isLong(dist_id)) {
			this.saveError(request, "errors.long", new String[] { dist_id });
			return mapping.findForward("list");
		}

		KonkaXxSellBill  konkaXxSellBill = new KonkaXxSellBill();
		konkaXxSellBill.setDist_id(Long.valueOf(dist_id));
		List<KonkaXxSellBill> konkaXxSellBillList = super.getFacade().getKonkaXxSellBillService().getKonkaXxSellBillList(konkaXxSellBill);
		request.setAttribute("konkaXxSellBillList", konkaXxSellBillList);
		
		KonkaXxDistAccountRec entity = new KonkaXxDistAccountRec(); 
		entity.setDist_id(Long.valueOf(dist_id));
		entity = super.getFacade().getKonkaXxDistAccountRecService().getKonkaXxDistAccountRec(entity);
		if (null == entity) {
			saveMessage(request, "entity.missing");
			return mapping.findForward("list");
		}
		super.copyProperties(form, entity);
		return mapping.findForward("view");
	}
}
