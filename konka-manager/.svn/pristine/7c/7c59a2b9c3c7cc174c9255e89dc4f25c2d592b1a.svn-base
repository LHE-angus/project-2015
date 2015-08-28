package com.ebiz.mmt.web.struts.manager.spgl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.EcGiftJfBuy;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author TUDP
 * @version 2014-11-25
 */
public class EcGiftJfBuyAction extends BaseAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String user_name = (String) dynaBean.get("user_name");
		String state = (String) dynaBean.get("state");
		String real_name_like = (String) dynaBean.get("real_name_like");
		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		} 
		EcGiftJfBuy entity = new EcGiftJfBuy();
		if (StringUtils.isNotBlank(real_name_like)) {
			entity.getMap().put("real_name_like", real_name_like);
		}
		if (StringUtils.isNotBlank(user_name)) {
			entity.setUser_name(user_name);
		}
		if (StringUtils.isNotBlank(state)) {
			entity.setState(new Integer(state));
		}
		Long recordCount = super.getFacade().getEcGiftJfBuyService().getEcGiftJfBuyCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<EcGiftJfBuy> entityList = super.getFacade().getEcGiftJfBuyService().getEcGiftJfBuyPaginatedList(entity);
		request.setAttribute("entityList", entityList);

		return mapping.findForward("list");
	}
 
	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		HttpSession session = request.getSession();

		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		EcGiftJfBuy entity = new EcGiftJfBuy();
		entity.setId(Long.valueOf(id));
		entity = super.getFacade().getEcGiftJfBuyService().getEcGiftJfBuy(entity);

		EcGiftJfBuy ecInfo = new EcGiftJfBuy();
		ecInfo.setId(Long.valueOf(id)); 
		super.getFacade().getEcGiftJfBuyService().modifyEcGiftJfBuy(ecInfo); 
		super.copyProperties(form, entity); 
		return mapping.findForward("view");
	}
 
}
