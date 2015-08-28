package com.ebiz.mmt.web.struts.manager.oa;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.FileReportType;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Hu,Hao
 * @version 2014-02-13
 * @desc OA文件类型
 */
public class FileReportTypeAction extends BaseAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}

		super.getModPopeDom(form, request);
		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
		setNaviStringToRequestScope(form, request);

		Long dept_id = 0L;

		KonkaDept kDept = super.getKonkaDeptForFgs(ui.getDept_id());
		if (null != kDept) {
			dept_id = kDept.getDept_id();
		}

		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String f_type_like = (String) dynaBean.get("f_type_like");

		FileReportType entity = new FileReportType();

		if (StringUtils.isNotBlank(f_type_like))
			entity.getMap().put("f_type_like", f_type_like);

		entity.setDept_id(dept_id);
		Long recordCount = getFacade().getFileReportTypeService().getFileReportTypeCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<FileReportType> entityList = super.getFacade().getFileReportTypeService().getFileReportTypeList(entity);

		request.setAttribute("entityList", entityList);
		dynaBean.set("dept_id", dept_id);
		return mapping.findForward("list");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
		Long dept_id = 0L;

		KonkaDept kDept = super.getKonkaDeptForFgs(ui.getDept_id());
		if (null != kDept) {
			dept_id = kDept.getDept_id();
		}

		dynaBean.set("dept_id", dept_id);
		return mapping.findForward("input");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");

		if (!GenericValidator.isLong(id)) {
			String m = getMessage(request, "entity.missing");
			super.renderJavaScript(response, "window.onload=function(){alert('" + m + "');history.back();}");
			return null;
		}

		FileReportType entity = new FileReportType();
		entity.setId(Long.valueOf(id));

		entity = super.getFacade().getFileReportTypeService().getFileReportType(entity);

		super.copyProperties(form, entity);

		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String f_type = (String) dynaBean.get("f_type");
		String mod_id = (String) dynaBean.get("mod_id");

		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);

		FileReportType entity = new FileReportType();

		super.copyProperties(entity, form);

		FileReportType ft = new FileReportType();
		ft.setF_type(f_type);

		if (GenericValidator.isLong(id)) {
			ft.getMap().put("id_is_exit", id);
			Long count = super.getFacade().getFileReportTypeService().getFileReportTypeCount(ft);
			if (count > 0) {
				String m = getMessage(request, "pe.file.type.error");
				super.renderJavaScript(response, "window.onload=function(){alert('" + m + "');history.back();}");
				return null;
			}

			entity.setId(Long.valueOf(id));
			super.getFacade().getFileReportTypeService().modifyFileReportType(entity);

			saveMessage(request, "entity.updated");

		} else {
			Long count = super.getFacade().getFileReportTypeService().getFileReportTypeCount(ft);
			if (count > 0) {
				String m = getMessage(request, "pe.file.type.error");
				super.renderJavaScript(response, "window.onload=function(){alert('" + m + "');history.back();}");
				return null;
			}

			entity.setAdd_user_id(ui.getId());
			entity.setAdd_user_name(ui.getReal_name());
			entity.setAdd_date(new Date());
			entity.setIs_del(0);
			super.getFacade().getFileReportTypeService().createFileReportType(entity);

			saveMessage(request, "entity.inserted");
		}

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}
	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;

		String id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id");

		if (StringUtils.isNotBlank(id) && GenericValidator.isLong(id)) {
			FileReportType entity = new FileReportType();
			entity.setId(new Long(id));
			getFacade().getFileReportTypeService().removeFileReportType(entity);
			saveMessage(request, "entity.deleted");
		}
		//super.renderText(response, "success");
		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}
}
