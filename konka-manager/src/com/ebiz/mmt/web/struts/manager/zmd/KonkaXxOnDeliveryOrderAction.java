package com.ebiz.mmt.web.struts.manager.zmd;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaXxZmdDailyDist;
import com.ebiz.mmt.domain.KonkaXxZmdDailyDistDetail;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Ren,zhong
 * @version 2012-03-22
 */
public class KonkaXxOnDeliveryOrderAction extends BaseZmdAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		
		PeProdUser user_info = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
//		PeRoleUser role_info = super.getRoleInfoByThisLogin(request);
		
		KonkaXxZmdDailyDist entity = new KonkaXxZmdDailyDist();
		entity.setDept_id(getKonkaDeptForFgs(user_info.getDept_id()).getDept_id());
		entity.setDist_type(1);
		
		Long recordCount = super.getFacade().getKonkaXxZmdDailyDistService().getKonkaXxZmdDailyDistCount(entity); 
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<KonkaXxZmdDailyDist> list = getFacade().getKonkaXxZmdDailyDistService().getKonkaXxZmdDailyDistPaginatedList(entity);
		
		request.setAttribute("list", list);
		
		return mapping.findForward("list");
	}
	
	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String dist_id = (String) dynaBean.get("dist_id");
		
		if (StringUtils.isBlank(dist_id)) {
			super.renderJavaScript(response, "参数丢失");
			return null;
		}
		
		KonkaXxZmdDailyDistDetail dailyDistDetail = new KonkaXxZmdDailyDistDetail();
		dailyDistDetail.setDist_id(Long.valueOf(dist_id));
		List<KonkaXxZmdDailyDistDetail> list = getFacade().getKonkaXxZmdDailyDistDetailService().getKonkaXxZmdDailyDistDetailList(dailyDistDetail);
		request.setAttribute("dailyDistDetailList", list);
		
		KonkaXxZmdDailyDist dailyDist = new KonkaXxZmdDailyDist();
		dailyDist.setId(Long.valueOf(dist_id));
		dailyDist = getFacade().getKonkaXxZmdDailyDistService().getKonkaXxZmdDailyDist(dailyDist);
		request.setAttribute("dailyDist", dailyDist);
		
		return mapping.findForward("view");
	}
	
	public ActionForward load(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		try {
			super.toWord(mapping, form, request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
