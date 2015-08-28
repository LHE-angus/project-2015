package com.ebiz.mmt.web.struts.manager.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaMobileImpHis;
import com.ebiz.mmt.web.struts.BaseAction;

/**
 * @author Hu,Hao
 * @version 2013-05-23
 */
public class KonkaDeptImpAction extends BaseAction {

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

		KonkaDept entity = new KonkaDept();
		entity.setDept_type(3);
		entity.getMap().put("is_fgs", true);

		List<KonkaDept> entityList = super.getFacade().getKonkaDeptService().getKonkaDeptList(entity);

		request.setAttribute("entityList", entityList);
		return mapping.findForward("list");
	}

	public ActionForward next(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String dept_id = (String) dynaBean.get("dept_id");
		String mod_id = (String) dynaBean.get("mod_id");

		if (!GenericValidator.isLong(dept_id)) {
			String msg = super.getMessage(request, "errors.parm");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		KonkaDept konkaDept = new KonkaDept();
		konkaDept.setDept_id(Long.valueOf(dept_id));
		List<KonkaDept> konkaDeptList = super.getFacade().getKonkaDeptService().getKonkaDeptList(konkaDept);
		if (konkaDeptList.size() > 0) {
			dynaBean.set("dept_name", konkaDeptList.get(0).getDept_name());
		}

		dynaBean.set("dept_id", dept_id);
		dynaBean.set("mod_id", mod_id);
		return new ActionForward("/admin/KonkaDeptImp/next.jsp");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String dept_id = (String) dynaBean.get("dept_id");
		String mod_id = (String) dynaBean.get("mod_id");
		String type_id = (String) dynaBean.get("type_id");

		if (!GenericValidator.isLong(dept_id) || StringUtils.isBlank(type_id)) {
			String msg = super.getMessage(request, "errors.parm");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		KonkaMobileImpHis entity = new KonkaMobileImpHis();
		if ("1".equals(type_id)) {
			entity.setOpr_obj(2);
		} else if ("2".equals(type_id) || "4".equals(type_id)) {
			entity.setOpr_obj(3);
		} else if ("3".equals(type_id) || "5".equals(type_id)) {
			entity.setOpr_obj(1);
		}
		entity.setDept_id(Long.valueOf(dept_id));
		List<KonkaMobileImpHis> entityList = super.getFacade().getKonkaMobileImpHisService().getKonkaMobileImpHisList(
				entity);

		request.setAttribute("entityList", entityList);

		KonkaDept konkaDept = new KonkaDept();
		konkaDept.setDept_id(Long.valueOf(dept_id));
		konkaDept = super.getFacade().getKonkaDeptService().getKonkaDept(konkaDept);

		if (null != konkaDept) {
			dynaBean.set("dept_name", konkaDept.getDept_name());
			dynaBean.set("dept_sn", konkaDept.getDept_sn());
		}

		dynaBean.set("type_id", type_id);
		dynaBean.set("mod_id", mod_id);

		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String dept_sn = (String) dynaBean.get("dept_sn");
		String his_id = (String) dynaBean.get("his_id");
		String type_id = (String) dynaBean.get("type_id");

		if (!GenericValidator.isLong(his_id) || StringUtils.isBlank(dept_sn)) {
			String msg = super.getMessage(request, "errors.parm");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}
		
		KonkaMobileImpHis entity = new KonkaMobileImpHis();
		entity.setOpr_his_id(Long.valueOf(his_id));
		entity.getMap().put("dept_sn", dept_sn);

		if("1".equals(type_id)){
			entity.getMap().put("is_type_1", true);
		}else if("2".equals(type_id)){
			entity.getMap().put("is_type_2", true);
		}else if("3".equals(type_id)){
			entity.getMap().put("is_type_3", true);
		}else if("4".equals(type_id)){
			entity.getMap().put("is_type_4", true);
		}else if("5".equals(type_id)){
			entity.getMap().put("is_type_5", true);
		}	
		
		super.getFacade().getKonkaMobileImpHisService().modifyKonkaMobileImpDAtaPro(entity);
		
		saveMessage(request,"konka.nobile.data.init");
		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append("KonkaDataInit.do?");
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		return forward;
		// end
	}
	
	public ActionForward hislist(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		//String mod_id = (String) dynaBean.get("mod_id");
		String type_id = (String) dynaBean.get("type_id");
		String dept_id = (String) dynaBean.get("dept_id");
		
		if (!GenericValidator.isLong(type_id) || !GenericValidator.isLong(dept_id)) {
			String msg = super.getMessage(request, "errors.parm");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}
		
		KonkaMobileImpHis entity = new KonkaMobileImpHis();
		entity.setDept_id(Long.valueOf(dept_id));

		if("1".equals(type_id)){
			entity.setOpr_obj(11);
		}else if("2".equals(type_id)){
			entity.setOpr_obj(12);
		}else if("3".equals(type_id)){
			entity.setOpr_obj(13);
		}else if("4".equals(type_id)){
			entity.setOpr_obj(14);
		}else if("5".equals(type_id)){
			entity.setOpr_obj(15);
		}

		List<KonkaMobileImpHis> entityList = super.getFacade().getKonkaMobileImpHisService().getKonkaMobileImpHisWithUserNameList(entity);
		
		request.setAttribute("entityList",entityList);
		
		return new ActionForward("/admin/KonkaDeptImp/his_list.jsp");
	}
}
