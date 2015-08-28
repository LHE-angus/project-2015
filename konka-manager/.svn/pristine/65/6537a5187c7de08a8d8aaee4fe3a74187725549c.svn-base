package com.ebiz.mmt.web.struts.manager.admin;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.BasePdClazz;
import com.ebiz.mmt.web.struts.BaseAction;

/**
 * @author Cheng,Bing
 * @version build 2011.09.22
 */
public class BasePdClazzListAction extends BaseAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}
		
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);

		String cls_name = (String) dynaBean.get("cls_name");
		String par_name = (String) dynaBean.get("par_name");
		String root_name = (String) dynaBean.get("root_name");
		String tree_name = (String) dynaBean.get("tree_name");
		String cls_level = (String) dynaBean.get("cls_level");

		// 品类
		BasePdClazz basePdClazz = new BasePdClazz();
		basePdClazz.setIs_del(0);

		if (StringUtils.isNotBlank(root_name)) {
			basePdClazz.setRoot_name(root_name);
		}

		if (StringUtils.isNotBlank(par_name)) {
			basePdClazz.setPar_name(par_name);
		}

		if (StringUtils.isNotBlank(cls_name)) {
			basePdClazz.setCls_name(cls_name);
		}

		if (StringUtils.isNotBlank(tree_name)) {
			basePdClazz.setTree_name(tree_name);
		}

		if (StringUtils.isNotBlank(cls_level)) {
			basePdClazz.setCls_level(BigDecimal.valueOf(Integer.parseInt(cls_level)));
		}
		basePdClazz.getRow().setCount(5000000);

		request.setAttribute("basePdClazzList", getBasePdClazzList());
		return mapping.findForward("list");
	}
}