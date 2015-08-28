package com.ebiz.mmt.web.struts.mobile.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.SysSetting;
import com.ebiz.mmt.web.struts.BaseAction;

/**
 * @author Hui,Gang
 * @version 2012-5-14 下午04:02:11
 */
public class VersionCheckAction extends BaseAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String v = (String) dynaBean.get("v");

		SysSetting sysSetting = new SysSetting();
		sysSetting.setTitle("version");
		if (v.equals(getFacade().getSysSettingService().getSysSetting(sysSetting).getContent())) {// 最新版本
			super.renderText(response, "true");
			return null;
		} else {// 不是最新版本
			super.renderText(response, "false");
			return null;
		}
	}
	
	public ActionForward u(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return null;
	}

}
