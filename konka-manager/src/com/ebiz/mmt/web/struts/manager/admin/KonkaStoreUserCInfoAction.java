package com.ebiz.mmt.web.struts.manager.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaStoreUserCInfo;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.util.EncryptUtils;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Hu,Hao
 * @version 2013-11-27
 */
public class KonkaStoreUserCInfoAction extends BaseAction {
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		//setNaviStringToRequestScope(form, request);
		//super.getModPopeDom(form, request);

		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);

		String ss_name_like = (String) dynaBean.get("ss_name_like");
		String c_type = (String) dynaBean.get("c_type");
		String add_user_like = (String) dynaBean.get("add_user_like");

		Pager pager = (Pager) dynaBean.get("pager");

		KonkaStoreUserCInfo entity = new KonkaStoreUserCInfo();

		if (StringUtils.isNotBlank(ss_name_like))
			entity.getMap().put("ss_name_like", ss_name_like);

		if (StringUtils.isNotBlank(c_type))
			entity.setC_type(Integer.valueOf(c_type));

		if (StringUtils.isNotBlank(add_user_like))
			entity.getMap().put("add_user_like", add_user_like);

		Long recordCount = getFacade().getKonkaStoreUserCInfoService().getKonkaStoreUserCInfoCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		//entity.getRow().setCount(pager.getRowCount());
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename=" + EncryptUtils.encodingFileName("记录变更表")
		        + ".xls");

		entity.getRow().setCount(recordCount.intValue());
		List<KonkaStoreUserCInfo> entityList = getFacade().getKonkaStoreUserCInfoService()
				.getKonkaStoreUserCInfoPaginatedList(entity);

		request.setAttribute("entityList", entityList);
		return mapping.findForward("view");
		//return mapping.findForward("list");
	}
}
