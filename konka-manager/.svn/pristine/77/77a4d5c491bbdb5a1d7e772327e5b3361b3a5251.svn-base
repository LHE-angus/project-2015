package com.ebiz.mmt.web.struts.manager.paragon;

import java.util.HashMap;
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
import com.ebiz.mmt.domain.KonkaParagonEquipmentJ;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

public class KonkaParagonEquipmentJAction extends BaseAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "1")) {
			return super.checkPopedomInvalid(request, response);
		}
		saveToken(request);
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		dynaBean.set("queryString", super.serialize(request, "id", "method"));
		return mapping.findForward("input");
	}

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}
		
		setNaviStringToRequestScope(form, request);
		super.getModPopeDom(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String etype = (String) dynaBean.get("etype");
		String shop_code_like = (String) dynaBean.get("shop_code_like");
		String shop_name_like = (String) dynaBean.get("shop_name_like");
		String channel_name_like = (String) dynaBean.get("channel_name_like");
		String part_company_id = (String) dynaBean.get("part_company_id");
		String area_id = (String) dynaBean.get("area_id");
		String fixdate = (String) dynaBean.get("fixdate");
		
		//登录人信息
		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
		
		PeProdUser userinfo = new PeProdUser();
		userinfo.setId(ui.getId());
		userinfo = super.getFacade().getPeProdUserService().getPeProdUser(userinfo);
		
		super.encodeCharacterForGetMethod(dynaBean, request);
		Pager pager = (Pager) dynaBean.get("pager");
		pager.setPageSize(15);
		
		KonkaParagonEquipmentJ entity = new KonkaParagonEquipmentJ();
		entity.getMap().put("shop_name_like", shop_name_like);
		entity.getMap().put("shop_code_like", shop_code_like);
		entity.getMap().put("etype", Integer.parseInt(etype));
		entity.getMap().put("channel_name_like", channel_name_like);
		entity.getMap().put("part_company_id", part_company_id);
		entity.getMap().put("area_id", area_id);
		if(StringUtils.isNotBlank(fixdate)){
			entity.setFixdate(fixdate);
		}
		
		// 分公司列表
		KonkaDept konka_dept = new KonkaDept();
		konka_dept.setDept_type(3);
		konka_dept.setPar_id(0l);
		List<KonkaDept> deptInfoList = super.getFacade().getKonkaDeptService().getKonkaDeptList(konka_dept);
		request.setAttribute("deptInfoList", deptInfoList);
		
		if(userinfo!=null){
			if(userinfo.getMap().get("dept_type").toString().equals("3")){
				entity.getMap().put("part_company_id_equip", new Long(userinfo.getMap().get("par_dept_ids").toString()));
			}else if(userinfo.getMap().get("dept_type").toString().equals("4")){
				entity.getMap().put("part_company_id_equip", new Long(userinfo.getMap().get("par_id").toString()));
			}else if(userinfo.getMap().get("dept_type").toString().equals("5")){
				KonkaDept konka_dept_5 = new KonkaDept();
				konka_dept_5.setDept_id(new Long(userinfo.getMap().get("par_id").toString()));
				konka_dept_5=super.getFacade().getKonkaDeptService().getKonkaDept(konka_dept_5);
				entity.getMap().put("part_company_id_equip", konka_dept_5.getPar_id());
			}
		}
		Long recordCount = getFacade().getKonkaParagonEquipmentJService()
				.getKonkaParagonEquipmentJCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<HashMap<String, String>> entityList = getFacade()
				.getKonkaParagonEquipmentJService()
				.selectKonkaParagonEquipmentPaginatedList(entity);
		request.setAttribute("entityList", entityList);

		return mapping.findForward("list");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
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

		KonkaParagonEquipmentJ entity = new KonkaParagonEquipmentJ();
		super.copyProperties(entity, form);

		if (null == entity.getEquipment_j_id()) {
			getFacade().getKonkaParagonEquipmentJService()
					.createKonkaParagonEquipmentJ(entity);
			saveMessage(request, "entity.inserted");
		} else {
			getFacade().getKonkaParagonEquipmentJService()
					.modifyKonkaParagonEquipmentJ(entity);
			saveMessage(request, "entity.updated");
		}

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(entity
				.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "2")) {
			return super.checkPopedomInvalid(request, response);
		}
		saveToken(request);
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");

		if (GenericValidator.isLong(id)) {
			KonkaParagonEquipmentJ entity = new KonkaParagonEquipmentJ();
			entity.setEquipment_j_id(new Long(id));
			entity = getFacade().getKonkaParagonEquipmentJService()
					.getKonkaParagonEquipmentJ(entity);
			if (null == entity) {
				saveMessage(request, "entity.missing");
				return mapping.findForward("list");
			}
			entity.setQueryString(super.serialize(request, "id", "method"));
			super.copyProperties(form, entity);

			return mapping.findForward("input");
		} else {
			saveError(request, "errors.long", id);
			return mapping.findForward("list");
		}
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "4")) {
			return super.checkPopedomInvalid(request, response);
		}

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String[] pks = (String[]) dynaBean.get("pks");

		if (!StringUtils.isBlank(id) && GenericValidator.isLong(id)) {
			KonkaParagonEquipmentJ entity = new KonkaParagonEquipmentJ();
			entity.setEquipment_j_id(new Long(id));
			getFacade().getKonkaParagonEquipmentJService()
					.removeKonkaParagonEquipmentJ(entity);
			saveMessage(request, "entity.deleted");
		} else if (!ArrayUtils.isEmpty(pks)) {
			KonkaParagonEquipmentJ entity = new KonkaParagonEquipmentJ();
			entity.getMap().put("pks", pks);
			getFacade().getKonkaParagonEquipmentJService()
					.removeKonkaParagonEquipmentJ(entity);
			saveMessage(request, "entity.deleted");
		}
		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(
				request, "id", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");

		if (GenericValidator.isLong(id)) {
			KonkaParagonEquipmentJ entity = new KonkaParagonEquipmentJ();
			entity.setEquipment_j_id(new Long(id));
			entity = getFacade().getKonkaParagonEquipmentJService()
					.getKonkaParagonEquipmentJ(entity);

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