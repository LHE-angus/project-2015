package com.ebiz.mmt.web.struts.jxcnokey;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.PePdModel;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;

public class SelectPePdModelAction extends BaseAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	// 全部康佳产品
	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);
		String key_word = (String) dynaBean.get("key_word");
		String cls_id = (String) dynaBean.get("cls_id");

		String selects = (String) dynaBean.get("selects");
		PePdModel entity = new PePdModel();
		entity.setIs_del(0);
		if (StringUtils.isNotBlank(selects)) {
			entity.getMap().put("selects", selects);
			entity.getMap().put("OrderByMd", 1);
			List<PePdModel> selectList = super.getFacade().getPePdModelService().getPePdModelList(entity);
			request.setAttribute("selectList", selectList);
		}

		request.getSession().setAttribute("selects", selects);
		request.setAttribute("clsList", super.getBasePdClazzList());

		// =========
		PePdModel pePdModel = new PePdModel();
		// pePdModel.setBrand_id(Constants.KONKA_BRAND_ID);
		pePdModel.setIs_del(0);

		if (StringUtils.isNotBlank(selects)) {// 筛选掉已经选择的
			pePdModel.getMap().put("not_selects", selects);
		}
		if (StringUtils.isNotBlank(key_word)) {
			pePdModel.getMap().put("md_name_like", key_word);
		}
		if (StringUtils.isNotBlank(cls_id)) {
			pePdModel.setCls_id(Long.valueOf(cls_id));
		}
		logger.info("=========selects{}:" + selects);

		List<PePdModel> pePdModelList = getFacade().getPePdModelService().getPePdModelList(pePdModel);
		request.setAttribute("entityList", pePdModelList);

		return mapping.findForward("list");
	}

	public ActionForward getQueryNames(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String key_word = (String) dynaBean.get("key_word");
		String cls_id = (String) dynaBean.get("cls_id");
		String selects = (String) request.getSession().getAttribute("selects");

		PePdModel pePdModel = new PePdModel();
//		pePdModel.setBrand_id(Constants.KONKA_BRAND_ID);
		pePdModel.setIs_del(0);
		if (StringUtils.isNotBlank(selects)) {
			pePdModel.getMap().put("not_selects", selects);
		}
		if (StringUtils.isNotBlank(key_word)) {
			pePdModel.getMap().put("md_name_like", key_word);
		}
		if (StringUtils.isNotBlank(cls_id)) {
			pePdModel.getMap().put("cls_id_par", cls_id);
		}

		List<PePdModel> entityList = getFacade().getPePdModelService().getPePdModelList(pePdModel);
		StringBuffer sb = new StringBuffer("[");
		for (PePdModel t : entityList) {
			sb.append("{\"id\":\"" + String.valueOf(t.getPd_id()) + "\",");
			sb.append("\"name\":\"" + StringEscapeUtils.escapeJavaScript(t.getMd_name()) + "\"},");
		}
		sb.append("{\"end\":\"\"}]");
		super.renderJson(response, sb.toString());

		return null;
	}
}
