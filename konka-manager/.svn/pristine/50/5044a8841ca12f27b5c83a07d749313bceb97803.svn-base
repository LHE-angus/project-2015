package com.ebiz.mmt.web.struts.manager.zmd;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaXxPd;

/**
 * @author Li,Yuan
 * @version 2012-01-12
 */
public class KonkaXxZbKcSearchAction extends BaseZmdAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);
		// 商品表取商品型号
		KonkaXxPd konkaXxPd = new KonkaXxPd();
		KonkaDept kDept = getKonkaDeptForFgs(super.getSessionUserInfo(request).getDept_id());
		if (kDept != null)
			konkaXxPd.setDept_id(kDept.getDept_id());
		List<KonkaXxPd> konkaXxPdList = super.getFacade().getKonkaXxPdService().getKonkaXxPdList(konkaXxPd);
		request.setAttribute("konkaXxPdList", konkaXxPdList);

		String md_name = (String) dynaBean.get("md_name");
		String factory_id = (String) dynaBean.get("factory_id");
		String store_id = (String) dynaBean.get("store_id");
		if (StringUtils.isNotBlank(md_name)) {
			dynaBean.set("md_name", md_name);
			Long ret = super.getStocks(md_name, factory_id, store_id);
			request.setAttribute("ret", ret);

		}
		return mapping.findForward("list");
	}
}
