package com.ebiz.mmt.web.struts.manager.paragon;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaParagonShowversion;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 *
 * 
 * 
 */
public class SelectVersionAction extends BaseAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		pager.setPageSize(15);
		String version_name_like = (String) dynaBean.get("version_name_like");
		String selects = (String) dynaBean.get("selects");
		
		KonkaParagonShowversion entity = new KonkaParagonShowversion();
		
		if (StringUtils.isNotBlank(selects)) {
			entity.getMap().put("selects", selects);
			request.setAttribute("selectList", super.getFacade().getKonkaParagonShowversionService().getKonkaParagonShowversionList(entity));
			entity.getMap().put("selects", null);
			entity.getMap().put("not_selects", selects);
		}
		entity.getRow().setCount(500);
		
		super.copyProperties(entity, form);
		entity.getMap().put("version_name_like", version_name_like);
		List<KonkaParagonShowversion> entityList =super.getFacade().getKonkaParagonShowversionService().getKonkaParagonShowversionList(entity);
		request.setAttribute("entityList", entityList);

		return mapping.findForward("list");

	}
	
	public ActionForward getQueryNames(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String version_name_like = (String) dynaBean.get("version_name_like");
		String pageCount = (String) dynaBean.get("pageCount");
		StringBuffer sb = new StringBuffer("[");
		int count = 500; // 每次最多取出数量

		if (StringUtils.isNotBlank(pageCount)) {
			count = Integer.valueOf(pageCount);
		}

		KonkaParagonShowversion entity = new KonkaParagonShowversion();
		
		entity.getMap().put("version_name_like", version_name_like);
		entity.getRow().setCount(500);
		entity.getRow().setCount(count);
		List<KonkaParagonShowversion> entityList = getFacade().getKonkaParagonShowversionService().getKonkaParagonShowversionList(entity);
		for (KonkaParagonShowversion t : entityList) {
			sb.append("{'id':'" + String.valueOf(t.getVersion_id()) + "',");
			sb.append("'name':'" + StringEscapeUtils.escapeJavaScript(t.getVersion_name()) + "'},");

		}
		sb.append("{'end':''}]");
		super.renderJson(response, sb.toString());

		return null;
	}
}
