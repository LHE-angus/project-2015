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
import com.ebiz.mmt.domain.KonkaParagonEquipmentC;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

public class KonkaParagonEquipmentCAction extends BaseAction {
	
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
		dynaBean.set("queryString", super.serialize(request, "equipment_id", "method"));
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
		String etype = (String) dynaBean.get("etype");
		String equipment_name_like = (String) dynaBean.get("equipment_name_like");
		String st_pub_date = (String) dynaBean.get("st_pub_date");
		String en_pub_date = (String) dynaBean.get("en_pub_date");
		super.encodeCharacterForGetMethod(dynaBean, request);
		Pager pager = (Pager) dynaBean.get("pager");
		pager.setPageSize(15);

		KonkaParagonEquipmentC entity = new KonkaParagonEquipmentC();
		entity.getMap().put("equipment_name_like", equipment_name_like);
		entity.getMap().put("st_pub_date", st_pub_date);
		entity.getMap().put("en_pub_date", en_pub_date);
		
		entity.setEtype(Integer.parseInt(etype));
		entity.setIs_del(0); //未删除记录
		
		Long recordCount = getFacade().getKonkaParagonEquipmentCService().getKonkaParagonEquipmentCCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<KonkaParagonEquipmentC> entityList = getFacade().getKonkaParagonEquipmentCService().getKonkaParagonEquipmentCPaginatedList(entity);
		request.setAttribute("entityList", entityList);
		
		return mapping.findForward("list");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String etype = (String) dynaBean.get("etype");

		KonkaParagonEquipmentC entity = new KonkaParagonEquipmentC();
		super.copyProperties(entity, form);

		if (null == entity.getEquipment_id()) {
			PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
			entity.setAddman(peProdUser.getId());
			entity.setAddtime(new Date());
			entity.setIs_del(0);
			getFacade().getKonkaParagonEquipmentCService().createKonkaParagonEquipmentC(entity);
			saveMessage(request, "entity.inserted");
		} else {
			getFacade().getKonkaParagonEquipmentCService().modifyKonkaParagonEquipmentC(entity);
			saveMessage(request, "entity.updated");
		}

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&etype=" + etype);
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
		String equipment_id = (String) dynaBean.get("equipment_id");

		if (GenericValidator.isLong(equipment_id)) {
			KonkaParagonEquipmentC entity = new KonkaParagonEquipmentC();
			entity.setEquipment_id(new Long(equipment_id));
			entity = getFacade().getKonkaParagonEquipmentCService().getKonkaParagonEquipmentC(entity);
			if (null == entity) {
				saveMessage(request, "entity.missing");
				return mapping.findForward("list");
			}
			entity.setQueryString(super.serialize(request, "equipment_id", "method"));
			super.copyProperties(form, entity);
			
			return mapping.findForward("input");
		} else {
			saveError(request, "errors.long", equipment_id);
			return mapping.findForward("list");
		}
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "4")) {
			return super.checkPopedomInvalid(request, response);
		}
		
		DynaBean dynaBean = (DynaBean) form;
		String equipment_id = (String) dynaBean.get("equipment_id");
		String etype = (String) dynaBean.get("etype");
		String[] pks = (String[]) dynaBean.get("pks");

		if (!StringUtils.isBlank(equipment_id) && GenericValidator.isLong(equipment_id)) {
			KonkaParagonEquipmentC entity = new KonkaParagonEquipmentC();
			entity.setEquipment_id(new Long(equipment_id));
			entity.setIs_del(1);
			getFacade().getKonkaParagonEquipmentCService().modifyKonkaParagonEquipmentC(entity);
			saveMessage(request, "entity.deleted");
		} else if (!ArrayUtils.isEmpty(pks)) {
			KonkaParagonEquipmentC entity = new KonkaParagonEquipmentC();
			entity.getMap().put("pks", pks);
			entity.setIs_del(1);
			getFacade().getKonkaParagonEquipmentCService().modifyKonkaParagonEquipmentC(entity);
			saveMessage(request, "entity.deleted");
		}
		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&etype=" + etype);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "equipment_id", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String equipment_id = (String) dynaBean.get("equipment_id");

		if (GenericValidator.isLong(equipment_id)) {
			KonkaParagonEquipmentC entity = new KonkaParagonEquipmentC();
			entity.setEquipment_id(new Long(equipment_id));
			entity = getFacade().getKonkaParagonEquipmentCService().getKonkaParagonEquipmentC(entity);

			if (null == entity) {
				saveMessage(request, "entity.missing");
				return mapping.findForward("list");
			}

			super.copyProperties(form, entity);
			return mapping.findForward("view");
		} else {
			saveError(request, "errors.long", equipment_id);
			return mapping.findForward("list");
		}
	}
}