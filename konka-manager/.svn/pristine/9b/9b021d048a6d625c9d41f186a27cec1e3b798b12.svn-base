package com.ebiz.mmt.web.struts.manager.oa;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaoaCategory;
import com.ebiz.mmt.web.struts.BaseAction;

public class SelectKonkaOaCategoryAction extends BaseAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);
		String key_word = (String) dynaBean.get("key_word");
		String selects = (String) dynaBean.get("selects");
		String select_indexes = (String) dynaBean.get("select_indexes");
		String module_id = (String) dynaBean.get("module_id");

		KonkaoaCategory entity = new KonkaoaCategory();

		log.info("===============" + selects);

		if (StringUtils.isNotBlank(selects)) {
			entity.getMap().put("selects", selects);
			request.setAttribute("selectList", super.getFacade().getKonkaoaCategoryService().getKonkaoaCategoryList(
					entity));
			entity.getMap().put("selects", null);
			entity.getMap().put("not_selects", selects);

		}
		if(StringUtils.isNotBlank(module_id)){
			entity.setModule_id(Long.valueOf(module_id));
		}else{
			entity.getMap().put("module_id_is_null", true);
		}
		
		if (StringUtils.isNotBlank(select_indexes)) {
			entity.getMap().put("select_indexes", select_indexes.substring(0, select_indexes.length() - 1));
			dynaBean.set("select_indexes", select_indexes.substring(0, select_indexes.length() - 1));
		}

		entity.getMap().put("key_word", key_word);
		entity.setIs_del(0);
		List<KonkaoaCategory> entityList = getFacade().getKonkaoaCategoryService().getKonkaoaCategoryList(entity);
		request.setAttribute("entityList", entityList);

		return mapping.findForward("list");
	}

	public ActionForward getQueryNames(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String key_word = (String) dynaBean.get("key_word");
		String select_indexes = (String) dynaBean.get("select_indexes");

		StringBuffer sb = new StringBuffer("[");

		KonkaoaCategory entity = new KonkaoaCategory();
		entity.getMap().put("key_word", key_word);
		if (StringUtils.isNotBlank(select_indexes)) {
			entity.getMap().put("select_indexes", select_indexes);
		}
		entity.setIs_del(0);
		List<KonkaoaCategory> entityList = getFacade().getKonkaoaCategoryService().getKonkaoaCategoryList(entity);

		for (KonkaoaCategory t : entityList) {
			sb.append("{\"c_index\":\"" + String.valueOf(t.getC_index()) + "\",");
			sb.append("\"c_name\":\"" + StringEscapeUtils.escapeJavaScript(t.getC_name()) + "\"},");
		}
		sb.append("{\"end\":\"\"}]");
		log.info("sb:" + sb.toString());
		super.renderJson(response, sb.toString());

		return null;
	}
}
