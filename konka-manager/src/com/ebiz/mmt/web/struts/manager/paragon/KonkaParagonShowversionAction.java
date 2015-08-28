package com.ebiz.mmt.web.struts.manager.paragon;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaParagonShowversion;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

public class KonkaParagonShowversionAction extends BaseAction {
	
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "1")) {
			return super.checkPopedomInvalid(request, response);
		}
		saveToken(request);
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		dynaBean.set("queryString", super.serialize(request, "version_id", "method"));
		return mapping.findForward("input");
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}
		
		setNaviStringToRequestScope(form, request);
		super.getModPopeDom(form, request);

		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);
		Pager pager = (Pager) dynaBean.get("pager");
		pager.setPageSize(15);

		String version_code = (String) dynaBean.get("version_code");
		String version_name_like = (String) dynaBean.get("version_name_like");
		String st_pub_date = (String) dynaBean.get("st_pub_date");
		String en_pub_date = (String) dynaBean.get("en_pub_date");
		KonkaParagonShowversion entity = new KonkaParagonShowversion();
		
		if(version_code!=null){
			entity.setVersion_code(version_code);
		}
		if(version_code!=null){
			entity.getMap().put("version_name_like", version_name_like);
		}
		entity.getMap().put("st_pub_date", st_pub_date);
		entity.getMap().put("en_pub_date", en_pub_date);
		Long recordCount = getFacade().getKonkaParagonShowversionService().getKonkaParagonShowversionCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<KonkaParagonShowversion> entityList = getFacade().getKonkaParagonShowversionService().getKonkaParagonShowversionPaginatedList(entity);
		request.setAttribute("entityList", entityList);
		
		return mapping.findForward("list");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (isCancelled(request)) {
			return list(mapping, form, request, response);
		}
		if (!isTokenValid(request)) {
			saveError(request, "errors.token");
			return list(mapping, form, request, response);
		}
		resetToken(request);

		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");

		KonkaParagonShowversion entity = new KonkaParagonShowversion();
		super.copyProperties(entity, form);
		// 获取登录用户 企业信息
		PeProdUser userInfo = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		
		if (null == entity.getVersion_id()) {
			entity.setAddman(userInfo.getId());
			entity.setAddtime(new Date());
			getFacade().getKonkaParagonShowversionService().createKonkaParagonShowversion(entity);
			saveMessage(request, "entity.inserted");
		} else {
			getFacade().getKonkaParagonShowversionService().modifyKonkaParagonShowversion(entity);
			saveMessage(request, "entity.updated");
		}

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "2")) {
			return super.checkPopedomInvalid(request, response);
		}
		saveToken(request);
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String version_id = (String) dynaBean.get("version_id");

		if (GenericValidator.isLong(version_id)) {
			KonkaParagonShowversion entity = new KonkaParagonShowversion();
			entity.setVersion_id(new Long(version_id));
			entity = getFacade().getKonkaParagonShowversionService().getKonkaParagonShowversion(entity);
			if (null == entity) {
				saveMessage(request, "entity.missing");
				return mapping.findForward("list");
			}
			entity.setQueryString(super.serialize(request, "version_id", "method"));
			super.copyProperties(form, entity);
			
			return mapping.findForward("input");
		} else {
			saveError(request, "errors.long", version_id);
			return mapping.findForward("list");
		}
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "4")) {
			return super.checkPopedomInvalid(request, response);
		}
		
		DynaBean dynaBean = (DynaBean) form;
		String version_id = (String) dynaBean.get("version_id");
		String[] pks = (String[]) dynaBean.get("pks");

		if (!StringUtils.isBlank(version_id) && GenericValidator.isLong(version_id)) {
			KonkaParagonShowversion entity = new KonkaParagonShowversion();
			entity.setVersion_id(new Long(version_id));
			getFacade().getKonkaParagonShowversionService().removeKonkaParagonShowversion(entity);
			saveMessage(request, "entity.deleted");
		} else if (!ArrayUtils.isEmpty(pks)) {
			KonkaParagonShowversion entity = new KonkaParagonShowversion();
			entity.getMap().put("pks", pks);
			getFacade().getKonkaParagonShowversionService().removeKonkaParagonShowversion(entity);
			saveMessage(request, "entity.deleted");
		}
		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "version_id", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String version_id = (String) dynaBean.get("version_id");

		if (GenericValidator.isLong(version_id)) {
			KonkaParagonShowversion entity = new KonkaParagonShowversion();
			entity.setVersion_id(new Long(version_id));
			entity = getFacade().getKonkaParagonShowversionService().getKonkaParagonShowversion(entity);

			if (null == entity) {
				saveMessage(request, "entity.missing");
				return mapping.findForward("list");
			}

			super.copyProperties(form, entity);
			return mapping.findForward("view");
		} else {
			saveError(request, "errors.long", version_id);
			return mapping.findForward("list");
		}
	}
}