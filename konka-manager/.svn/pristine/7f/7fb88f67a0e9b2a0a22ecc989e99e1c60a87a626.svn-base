package com.ebiz.mmt.web.struts.manager.paragon;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.alibaba.fastjson.JSON;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 *
 * 
 * 
 */
public class SelectSalesManAction extends BaseAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		pager.setPageSize(15);
		String sales_name_like = (String) dynaBean.get("sales_name_like");
		String selects = (String) dynaBean.get("selects");
		// 业务员列表
		PeProdUser  peUser = new PeProdUser();
		if (StringUtils.isNotBlank(selects)) {
			peUser.getMap().put("selects", selects);
			request.setAttribute("selectList", super.getFacade().getPeProdUserService().getPeProdUserListForShowInfo(peUser));
			peUser.getMap().put("selects", null);
			peUser.getMap().put("not_selects", selects);
		}
		peUser.getRow().setCount(500);
		
		super.copyProperties(peUser, form);
		peUser.getMap().put("sales_name_like", sales_name_like);
		if (null == peUser.getIs_del()) {
			peUser.setIs_del(0);
		}
		peUser.getMap().put("getOthers", 1);
		List<PeProdUser> entityList =super.getFacade().getPeProdUserService().getPeProdUserListForShowInfo(peUser);
		request.setAttribute("entityList", entityList);

		return mapping.findForward("list");

	}
	
	public ActionForward getQueryNames(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String sales_name_like = (String) dynaBean.get("sales_name_like");
		String pageCount = (String) dynaBean.get("pageCount");
		StringBuffer sb = new StringBuffer("[");
		int count = 500; // 每次最多取出数量

		if (StringUtils.isNotBlank(pageCount)) {
			count = Integer.valueOf(pageCount);
		}

		PeProdUser  entity = new PeProdUser();
		
		entity.getMap().put("sales_name_like", sales_name_like);
		entity.getRow().setCount(500);
		entity.getRow().setCount(count);
		List<PeProdUser> entityList = getFacade().getPeProdUserService().getPeProdUserListForShowInfo(entity);
		for (PeProdUser t : entityList) {
			sb.append("{'id':'" + String.valueOf(t.getId()) + "',");
			sb.append("'name':'" + StringEscapeUtils.escapeJavaScript(t.getUser_name()) + "'},");

		}
		sb.append("{'end':''}]");
		super.renderJson(response, sb.toString());

		return null;
	}

	public ActionForward ajaxMerchant(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		List<PeProdUser> sList = new ArrayList<PeProdUser>();
		PeProdUser model = new PeProdUser();
		if (StringUtils.isNotEmpty(id))
			model.setId(new Long(id));
		sList = super.getFacade().getPeProdUserService().getPeProdUserListForShowInfo(model);
		String jsStr = JSON.toJSONString(sList);
		jsStr = jsStr.substring(0, jsStr.length() - 1);
		jsStr = jsStr.substring(1, jsStr.length());
		super.renderJson(response, jsStr);
		return null;
	}
}
