package com.ebiz.mmt.web.struts.manager.admin;

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

import com.ebiz.mmt.domain.KonkaSapInterfaceSetting;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

public class KonkaSapInterfaceSettingAction extends BaseAction {

	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 权限判断
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");

		String serviceDesc = (String) dynaBean.get("serviceDesc");

		PeProdUser userInfo = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
		if (null == userInfo) {
			String msg = super.getMessage(request, "user.session.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		KonkaSapInterfaceSetting h = new KonkaSapInterfaceSetting();
		h.setServiceDesc(serviceDesc);

		Long recordCount = super.getFacade().getKonkaSapInterfaceSettingService().getKonkaSapInterfaceSettingCount(h);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		h.getRow().setFirst(pager.getFirstRow());
		h.getRow().setCount(pager.getRowCount());

		List<KonkaSapInterfaceSetting> entityList = super.getFacade().getKonkaSapInterfaceSettingService()
				.getKonkaSapInterfaceSettingPaginatedList(h);

		request.setAttribute("entityList", entityList);

		return mapping.findForward("list");
	}

	// 新增
	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		saveToken(request);
		setNaviStringToRequestScope(form, request);

		return mapping.findForward("input");
	}

	// 修改
	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		saveToken(request);
		setNaviStringToRequestScope(form, request);
		PeProdUser userInfo = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
		if (null == userInfo) {
			String msg = super.getMessage(request, "user.session.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");// 主键ID not null
		KonkaSapInterfaceSetting h = new KonkaSapInterfaceSetting();
		if (StringUtils.isNotEmpty(id)) {
			h.setId(Long.valueOf(id));
			h = super.getFacade().getKonkaSapInterfaceSettingService().getKonkaSapInterfaceSetting(h);
		}
		super.copyProperties(form, h);
		return mapping.findForward("input");
	}

	// 保存操作,区分新增与修改
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PeProdUser userInfo = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
		if (null == userInfo) {
			String msg = super.getMessage(request, "user.session.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		DynaBean dynaBean = (DynaBean) form;
		String service = (String) dynaBean.get("service");
		String serviceDesc = (String) dynaBean.get("serviceDesc");
		String status = (String) dynaBean.get("status");// 靠动作区别值
		String mod_id = (String) dynaBean.get("mod_id");
		String id = (String) dynaBean.get("id");

		KonkaSapInterfaceSetting h = new KonkaSapInterfaceSetting();

		if (StringUtils.isNotEmpty(id)) {
			h.setId(Long.valueOf(id));
			h.setService(service);
			h.setServiceDesc(serviceDesc);
			h.setStatus(status);
			h.setUpdate_time(sdf.format(new Date()));
			h.setUpdated_by(String.valueOf(userInfo.getId()));
			super.getFacade().getKonkaSapInterfaceSettingService().modifyKonkaSapInterfaceSetting(h);
			saveMessage(request, "entity.updated");
		} else {
			h.setService(service);
			h.setServiceDesc(serviceDesc);
			h.setStatus(status);
			h.setUpdate_time(sdf.format(new Date()));
			h.setUpdated_by(String.valueOf(userInfo.getId()));
			super.getFacade().getKonkaSapInterfaceSettingService().createKonkaSapInterfaceSetting(h);
			saveMessage(request, "entity.inserted");
		}

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		return forward;
	}


}