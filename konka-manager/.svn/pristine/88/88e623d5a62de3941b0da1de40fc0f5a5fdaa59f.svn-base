package com.ebiz.mmt.web.struts.mobile.admin;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaoaFiles;
import com.ebiz.mmt.domain.KonkaoaFilesAuditNode;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;

/**
 * @author WANG,YANG
 * @version Build 2012-04-27
 */
public class FilesManagerAction extends MobileBaseAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;

		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_MOBILE);

		String map_file_status = (String) dynaBean.get("map_file_status");
		String file_no =(String) dynaBean.get("file_no");

		KonkaoaFiles entity = new KonkaoaFiles();
		super.copyProperties(entity, form);
		entity.getMap().put("file_title_like", (String) dynaBean.get("file_title_like"));
		entity.getMap().put("st_submit_datetime", (String) dynaBean.get("st_submit_datetime"));
		entity.getMap().put("en_submit_datetime", (String) dynaBean.get("en_submit_datetime"));
		entity.getMap().put("map_file_status", map_file_status);
		
		entity.setIs_del(0);
		entity.setSubmit_user_id(ui.getId());
		entity.setFile_type(0);// 文件类型设为0，表示该文件是文件提交
		
		request.setAttribute("file_title_like", (String) dynaBean.get("file_title_like"));
		request.setAttribute("file_no", file_no);
		request.setAttribute("map_file_status", map_file_status);
		request.setAttribute("st_submit_datetime", (String) dynaBean.get("st_submit_datetime"));
		request.setAttribute("en_submit_datetime", (String) dynaBean.get("en_submit_datetime"));
		

		String page = (String) dynaBean.get("page");
		String forward = (String) dynaBean.get("forward");
		int currentPage = 1;
		int pageSize = 3;
		if (StringUtils.isEmpty(page))
			request.setAttribute("page", currentPage);
		else {
			currentPage = (Integer.parseInt(page))
					+ (Integer.parseInt(forward));
			request.setAttribute("page", currentPage);
		}
		entity.getRow().setFirst((currentPage - 1) * pageSize);
		entity.getRow().setCount(pageSize);

		Long count = super.getFacade().getKonkaoaFilesService().getKonkaoaFilesCountForPaginatedList(entity);
		if (count % pageSize > 0)
			request.setAttribute("pagelimit", (count / pageSize) + 1);
		else
			request.setAttribute("pagelimit", (count / pageSize));
		if (count > 0) {
			List<KonkaoaFiles> entityList = super.getFacade().getKonkaoaFilesService().getKonkaoaFilesPaginatedList(entity);
			request.setAttribute("entityList", entityList);
		}
		//写日志
		createMobileSysOperLog(request, "KONKAOA_FILES", 720300l, "720300", "查询",
				"手机端-文件管理-查询");
		return mapping.findForward("list");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		saveToken(request);

		DynaBean dynaBean = (DynaBean) form;

		String id = (String) dynaBean.get("id");
		if(StringUtils.isBlank(id)){
			super.renderText(response, "错误！");
			return null;
		}
		request.setAttribute("id", id);

		KonkaoaFiles files = new KonkaoaFiles();
		files.setId(new Long(id));
		files = super.getFacade().getKonkaoaFilesService().getKonkaoaFiles(files);
		if (null == files) {
			super.renderText(response, "数据错误，或已被删除！");
			return null;
		}
		
		super.copyProperties(form, files);
		request.setAttribute("submit_datetime", files.getSubmit_datetime());

		super.setCategoryListToRequestScope(request);

		KonkaoaFilesAuditNode fan = new KonkaoaFilesAuditNode();
		fan.setLink_id(files.getId());
		fan.setAudit_type(0);
		List<KonkaoaFilesAuditNode> auditList = super.getFacade().getKonkaoaFilesAuditNodeService()
				.getKonkaoaFilesAuditNodeList(fan);

		if(auditList.size()!=0){
			dynaBean.set("audit_user_name", auditList.get(0).getAudit_user());
		}
		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_MOBILE);
		
		DynaBean dynaBean = (DynaBean) form;
		String audit_user_name = (String) dynaBean.get("audit_user_name");
		String content = (String)dynaBean.get("content");
		String file_title = (String)dynaBean.get("file_title");
		String id = (String)dynaBean.get("id");
		
		if(StringUtils.isBlank(id)){
			super.renderText(response, "错误！");
			return null;
		}
		
		
		KonkaoaFiles entity = new KonkaoaFiles();
		entity.setId(new Long(id));
		entity=getFacade().getKonkaoaFilesService().getKonkaoaFiles(entity);
		if(entity.getFile_status()!=0 &&entity.getFile_status()!=-3){
			super.renderText(response, "此文件正在被人审批，无法修改！");
			return null;
		}
		
		
		Long audit_user_id = new Long(0);
		if(StringUtils.isBlank(file_title)||("标题").equals(file_title)){
			super.renderText(response, "请填写标题！");
			return null;
		}
		if(StringUtils.isBlank(content)){
			super.renderText(response, "请填写内容！");
			return null;
		}
		if(StringUtils.isBlank(audit_user_name)||("审批人").equals(audit_user_name)){
			super.renderText(response, "请填写审批人！");
			return null;
		}else if(StringUtils.isNotBlank(audit_user_name)){
			PeProdUser user = new PeProdUser();
			user.setUser_name(audit_user_name);
			user = getFacade().getPeProdUserService().getPeProdUserResult(user);
			if(user==null){
				super.renderText(response, "无法查找到\""+audit_user_name+"\"！请检查是否输入有误");
				return null;
			}else if(user !=null){
				Long audit_dept_id = super.getSuperiorForDeptType(user.getDept_id(), 3).getDept_id();
				Long submit_dept_id = super.getSuperiorForDeptType(ui.getDept_id(), 3).getDept_id();
				if(!audit_dept_id.equals(submit_dept_id)){
					super.renderText(response, audit_user_name+"此人与您不在同一分公司，请重新输入审批人！");
					return null;
				}else{
					audit_user_id = user.getId();
				}
			}
		}


        super.copyProperties(entity, form);
//		String mod_id = (String) dynaBean.get("mod_id");

		// 审批结点
		List<KonkaoaFilesAuditNode> filesAuditNodeList = new ArrayList<KonkaoaFilesAuditNode>();

		// 审批人
		KonkaoaFilesAuditNode _fan = new KonkaoaFilesAuditNode();
		_fan.setAudit_user_id(audit_user_id);
		_fan.setAudit_user(audit_user_name);
		_fan.setAudit_type(0);

		filesAuditNodeList.add(_fan);

		entity.setFilesAuditNodeList(filesAuditNodeList);

		super.getFacade().getKonkaoaFilesService().modifyKonkaoaFiles(entity);
		saveMessage(request, "entity.inserted");

		// super.createSysOperLog(request, "FILES", id, mod_id, "添加", "id=" + id + ",file_title=" +
		// entity.getFile_title());

		dynaBean.set("category12", "");
		dynaBean.set("category13", "");
		dynaBean.set("category14", "");
		dynaBean.set("is_stamp", "");
		dynaBean.set("is_forward", "");

		dynaBean.set("file_title", "");
		dynaBean.set("content", "");
		dynaBean.set("apply_people", "");
		
		createMobileSysOperLog(request, "KONKAOA_FILES", 720300l, "720300", "修改",
				"手机端-文件管理-修改");
		super.renderText(response, "success");
		return null;
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// if (null == super.checkUserModPopeDom(form, request, "4")) {
		// return super.checkPopedomInvalid(request, response);
		// }

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
//		String mod_id = (String) dynaBean.get("mod_id");
		
		if(StringUtils.isBlank(id)){
			super.renderText(response, "错误！");
			return null;
		}
		
		KonkaoaFiles entity = new KonkaoaFiles();
		entity.setId(new Long(id));
		entity=getFacade().getKonkaoaFilesService().getKonkaoaFiles(entity);
		if(entity.getFile_status()==null){
			super.renderText(response, "数据错误！");
			return null;
		}
		if(entity.getFile_status()!=0 &&entity.getFile_status()!=-3){
			super.renderText(response, "此文件正在被人审批，无法删除！");
			return null;
		}

		if (!StringUtils.isBlank(id) && GenericValidator.isLong(id)) {
			super.getFacade().getKonkaoaFilesService().removeKonkaoaFiles(entity);
			saveMessage(request, "entity.deleted");

			// super.createSysOperLog(request, "FILES", entity.getId(), mod_id, "删除", entity.toString());
		}

			// super.createSysOperLog(request, "FILES", new Long(0), mod_id, "删除", "批量删除,".concat(entity.toString()));
		createMobileSysOperLog(request, "KONKAOA_FILES", new Long(id), "720300", "删除",
				"手机端-文件管理-删除");
		KonkaoaFiles files = new KonkaoaFiles();
		super.copyProperties(form, files);
		return this.list(mapping, form, request, response);
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;

		String id = (String) dynaBean.get("id");
		if (!GenericValidator.isLong(id)) {
			saveError(request, "errors.long", id);
			return mapping.findForward("list");
		}

		KonkaoaFiles files = new KonkaoaFiles();
		files.setId(new Long(id));
		files = super.getFacade().getKonkaoaFilesService().getKonkaoaFiles(files);
		if (null == files) {
			saveMessage(request, "entity.missing");
			return mapping.findForward("list");
		}

		super.copyProperties(form, files);

		super.setCategoryListToRequestScope(request);

		// 会签记录显示
		KonkaoaFilesAuditNode _fan = new KonkaoaFilesAuditNode();
		_fan.setLink_id(files.getId());
		_fan.setAudit_type(0);
		List<KonkaoaFilesAuditNode> auditList = super.getFacade().getKonkaoaFilesAuditNodeService()
				.getKonkaoaFilesAuditNodeList(_fan);

		if(auditList.size()!=0){
			dynaBean.set("audit_user_name", auditList.get(0).getAudit_user());
		}
		createMobileSysOperLog(request, "KONKAOA_FILES", new Long(id), "720300", "查看",
				"手机端-文件管理-查看");
		return mapping.findForward("view");
	}
}