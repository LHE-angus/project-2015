package com.ebiz.mmt.web.struts.manager.admin;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.r3.ZJ98;
import com.ebiz.mmt.r3.helper.DAOFactory;
import com.ebiz.mmt.web.struts.BaseAction;

/**
 * @author Hu,hao
 * @version 2013-12-11
 */
public class KonkaPdJxcAnalyseAction extends BaseAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		setNaviStringToRequestScope(form, request);
		
		DynaBean dnBean = (DynaBean) form;
		// 型号
		String v_matnr_begin = (String) dnBean.get("v_matnr_begin");
		// 型号
		String v_matnr_end = (String) dnBean.get("v_matnr_end");
		// 分销渠道
		String v_vtweg = (String) dnBean.get("v_vtweg");
		// 产品组
		String v_spart = (String) dnBean.get("v_spart");
		// 开始日期
		String v_bstdk_begin = (String) dnBean.get("v_bstdk_begin");
		// 结束日期
		String v_bstdk_end = (String) dnBean.get("v_bstdk_end");
		// 存销比开始
		String v_cxb_begin = (String) dnBean.get("v_cxb_begin");
		// 存销比结束
		String v_cxb_end = (String) dnBean.get("v_cxb_end");

		List<ZJ98> list = new ArrayList<ZJ98>();

		if (v_bstdk_end != null && v_bstdk_end.length() >= 0) {
			v_bstdk_end = StringUtils.replace(v_bstdk_end, "-", "");
		}
		if (v_bstdk_begin == null || "".equals(v_bstdk_begin)) {
			return mapping.findForward("list");
		}
		if (v_bstdk_begin != null && v_bstdk_begin.length() >= 0) {
			v_bstdk_begin = StringUtils.replace(v_bstdk_begin, "-", "");
		}
		if (v_bstdk_begin == null || ("").equals(v_bstdk_begin)) {
			return mapping.findForward("list");
		}
		if (v_vtweg == null || "".equals(v_vtweg)) {
			return mapping.findForward("list");
		}
		if (v_spart == null || "".equals(v_spart)) {
			return mapping.findForward("list");
		}

		list = DAOFactory.getInstance().getZlesZJ98(v_matnr_begin, v_matnr_end, v_vtweg, v_spart, v_bstdk_begin,
				v_bstdk_end, v_cxb_begin, v_cxb_end);

		request.setAttribute("entityList", list);

		return mapping.findForward("list");
	}
}
