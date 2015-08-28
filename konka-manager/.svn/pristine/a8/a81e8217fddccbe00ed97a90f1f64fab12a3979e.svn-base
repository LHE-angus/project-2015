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

import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaParagonShowmanre;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

public class KonkaParagonShowmanreAction extends BaseAction {
	
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
		dynaBean.set("queryString", super.serialize(request, "re_id", "method"));
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
		
		String user_name_like = (String)dynaBean.get("user_name_like");
		String st_pub_date = (String) dynaBean.get("st_pub_date");
		String en_pub_date = (String) dynaBean.get("en_pub_date");
		
		KonkaParagonShowmanre entity = new KonkaParagonShowmanre();
		
		// 分公司列表
		KonkaDept konka_dept = new KonkaDept();
		konka_dept.setDept_type(3);
		konka_dept.setPar_id(0L);
		List<KonkaDept> deptInfoList = super.getFacade().getKonkaDeptService().getKonkaDeptList(konka_dept);
		request.setAttribute("deptInfoList", deptInfoList);
		if(user_name_like!=null){
			entity.getMap().put("user_name_like", user_name_like);
		}
		
		entity.getMap().put("st_pub_date", st_pub_date);
		entity.getMap().put("en_pub_date", en_pub_date);
		
		Long recordCount = getFacade().getKonkaParagonShowmanreService().getKonkaParagonShowmanreCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<KonkaParagonShowmanre> entityList = getFacade().getKonkaParagonShowmanreService().getKonkaParagonShowmanrePaginatedList(entity);
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

		// 获取登录用户 企业信息
		PeProdUser userInfo = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String show_shop_codes = request.getParameter("show_shop_codes"); // 门店代码
		
		KonkaParagonShowmanre entity = new KonkaParagonShowmanre();
		super.copyProperties(entity, form);
		entity.setAddman(userInfo.getId());
		entity.setAddtime(new Date());
		if (null == entity.getRe_id()) {
			String[] show_shop_code_array = show_shop_codes.split(",");
			for (int i = 0; i < show_shop_code_array.length; i++) { 
				entity.setShow_shop_code(show_shop_code_array[i]);
				getFacade().getKonkaParagonShowmanreService().createKonkaParagonShowmanre(entity);
			}
			saveMessage(request, "entity.inserted");
		} else {
			getFacade().getKonkaParagonShowmanreService().modifyKonkaParagonShowmanre(entity);
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
	public ActionForward saveForEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (isCancelled(request)) {
			return list(mapping, form, request, response);
		}
		if (!isTokenValid(request)) {
			saveError(request, "errors.token");
			return list(mapping, form, request, response);
		}
		resetToken(request);

		// 获取登录用户 企业信息
		PeProdUser userInfo = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String user_id = request.getParameter("user_id"); // 用户ID
		String show_shop_codes = request.getParameter("show_shop_codes"); // 门店代码
		
		KonkaParagonShowmanre entity = new KonkaParagonShowmanre();
		super.copyProperties(entity, form);
		entity.setAddman(userInfo.getId());
		entity.setAddtime(new Date());
		if (null != user_id) {
			
			KonkaParagonShowmanre entity1 = new KonkaParagonShowmanre();
			entity1.setUser_id(new Long(user_id));
			List<KonkaParagonShowmanre> manreList = super.getFacade().getKonkaParagonShowmanreService().getKonkaParagonShowmanreList(entity1);
			for(KonkaParagonShowmanre t : manreList){
				getFacade().getKonkaParagonShowmanreService().removeKonkaParagonShowmanre(t);
			}
			String[] show_shop_code_array = show_shop_codes.split(",");
			for (int i = 0; i < show_shop_code_array.length; i++) { 
				entity.setShow_shop_code(show_shop_code_array[i]);
				getFacade().getKonkaParagonShowmanreService().createKonkaParagonShowmanre(entity);
			}
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
		String user_id = (String) dynaBean.get("user_id");
		Pager pager = (Pager) dynaBean.get("pager");
		pager.setPageSize(10);
		KonkaParagonShowmanre entity = new KonkaParagonShowmanre();
		if(StringUtils.isNotBlank(user_id)){
			entity.setUser_id(new Long(user_id));
		}
		entity  = getFacade().getKonkaParagonShowmanreService().getKonkaParagonShowmanreForEdit(entity);
		
		// 业务员列表
		KonkaParagonShowmanre entity1 = new KonkaParagonShowmanre();
		super.copyProperties(entity1, form);
		if(StringUtils.isNotBlank(user_id)){
			entity1.setUser_id(new Long(user_id));
		}
		
		Long recordCount = getFacade().getKonkaParagonShowmanreService().getKonkaParagonShowmanreCountForShowOut(entity1);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity1.getRow().setFirst(pager.getFirstRow());
		entity1.getRow().setCount(pager.getRowCount());
		List<KonkaParagonShowmanre> entityList = super.getFacade().getKonkaParagonShowmanreService().getKonkaParagonShowmanrePaginatedListForShowOut(entity1);
		request.setAttribute("entityList", entityList);
		
		if (null == entity) {
			saveMessage(request, "entity.missing");
			return mapping.findForward("list");
		}
		entity.setQueryString(super.serialize(request, "user_id", "method"));
		super.copyProperties(form, entity);
		
		return new ActionForward("/paragon/KonkaParagonShowmanre/edit.jsp");
	}
	

	public ActionForward deleteForShowOut(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "4")) {
			return super.checkPopedomInvalid(request, response);
		}
		
		DynaBean dynaBean = (DynaBean) form;
		String re_id = (String) dynaBean.get("re_id");
		String user_id = (String) dynaBean.get("user_id");
		String[] pks = (String[]) dynaBean.get("pks");
		String mod_id = (String) dynaBean.get("mod_id");

		if (!StringUtils.isBlank(re_id) && GenericValidator.isLong(re_id)) {
			KonkaParagonShowmanre entity = new KonkaParagonShowmanre();
			entity.setRe_id(new Long(re_id));
			getFacade().getKonkaParagonShowmanreService().removeKonkaParagonShowmanre(entity);
			saveMessage(request, "entity.deleted");
		} else if (!ArrayUtils.isEmpty(pks)) {
			KonkaParagonShowmanre entity = new KonkaParagonShowmanre();
			entity.getMap().put("pks", pks);
			getFacade().getKonkaParagonShowmanreService().removeKonkaParagonShowmanre(entity);
			saveMessage(request, "entity.deleted");
		}
		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append("/paragon/KonkaParagonShowmanre.do?method=edit");
		pathBuffer.append("&mod_id="+mod_id);
		pathBuffer.append("&user_id="+user_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "re_id", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}
	
	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "4")) {
			return super.checkPopedomInvalid(request, response);
		}
		
		DynaBean dynaBean = (DynaBean) form;
		String user_id = (String) dynaBean.get("user_id");
		String[] pks = (String[]) dynaBean.get("pks");
		
		if (!StringUtils.isBlank(user_id) && GenericValidator.isLong(user_id)) {
			KonkaParagonShowmanre entity = new KonkaParagonShowmanre();
			entity.setUser_id(new Long(user_id));
			List<KonkaParagonShowmanre> entityList = super.getFacade().getKonkaParagonShowmanreService().getKonkaParagonShowmanreList(entity);
			if(entityList!=null){
				for(KonkaParagonShowmanre t : entityList){
					KonkaParagonShowmanre showmanre = new KonkaParagonShowmanre();
					showmanre.setRe_id(t.getRe_id());
					getFacade().getKonkaParagonShowmanreService().removeKonkaParagonShowmanre(showmanre);
				}
			}
			saveMessage(request, "entity.deleted");
		} else if (!ArrayUtils.isEmpty(pks)) {
			KonkaParagonShowmanre entity = new KonkaParagonShowmanre();
			entity.getMap().put("pks", pks);
			
			getFacade().getKonkaParagonShowmanreService().removeKonkaParagonShowmanre(entity);
			saveMessage(request, "entity.deleted");
		}
		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "user_id", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("re_id");

		if (GenericValidator.isLong(id)) {
			KonkaParagonShowmanre entity = new KonkaParagonShowmanre();
			entity.setRe_id(new Long(id));
			entity = getFacade().getKonkaParagonShowmanreService().getKonkaParagonShowmanre(entity);

			if (null == entity) {
				saveMessage(request, "entity.missing");
				return mapping.findForward("list");
			}

			super.copyProperties(form, entity);
			return mapping.findForward("view");
		} else {
			saveError(request, "errors.long", id);
			return mapping.findForward("list");
		}
	}
}