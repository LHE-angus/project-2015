package com.ebiz.mmt.web.struts.mobile.html;

import java.text.SimpleDateFormat;
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

import com.ebiz.mmt.domain.KonkaDept;
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
		

		String page = (String) dynaBean.get("currentPage");
		String pagelimit = (String) dynaBean.get("pagelimit");
		int currentPage = 1;
		int pageSize = 15;
		if (StringUtils.isNotEmpty(page)) {
			currentPage = Integer.parseInt(page);
		}
		if (StringUtils.isNotEmpty(pagelimit)) {
			pageSize = Integer.parseInt(pagelimit);
		}
		entity.getRow().setFirst((currentPage - 1) * pageSize);
		entity.getRow().setCount(pageSize);

		Long count = super.getFacade().getKonkaoaFilesService().getKonkaoaFilesCountForPaginatedList(entity);
		if (count % pageSize > 0)
			request.setAttribute("pagelimit", (count / pageSize) + 1);
		else
			request.setAttribute("pagelimit", (count / pageSize));
		String outStr = "{htmlData:'";
		if (count > 0) {
			List<KonkaoaFiles> entityList = super.getFacade().getKonkaoaFilesService().getKonkaoaFilesPaginatedList(entity);
			outStr += "<ol data-role=\"listview\">";
			for (KonkaoaFiles _k : entityList) {
				outStr += "<li>";
					outStr += "<a href=\"javascript:void(0)\" onclick=location.href=\"FilesManager_view.html?id="+_k.getId()+"\">";
					outStr += _k.getFile_no()+ " 《"+_k.getFile_title()+"》 ";
						if(_k.getFile_status()==0 || _k.getFile_status()==-3){
							outStr += "未审批";
						}else if(_k.getFile_status()==1 || _k.getFile_status()==-1|| _k.getFile_status()==-2){
							outStr += "审批中";
						}else if(_k.getFile_status()==2){
							outStr += "被驳回";
						}else if(_k.getFile_status()==3){
							outStr += "已批准";
						}
//					}
//					else if(_k.getFile_from()==1){
//						if(_k.getFile_status()==0 || _k.getFile_status()==-3){
//							outStr += "<p>文件状态：<strong>未审批</strong>" +
//									" <input id=\"delButton\" name=\"delButton\" type=\"button\" data-role=\"button\"  data-theme=\"a\" data-inline=\"true\" value=\"删除\" onclick=\"del("+_k.getId() +" );\"/>" +
//									" <input id=\"editButton\" name=\"editButton\" type=\"button\" data-role=\"button\"  data-theme=\"a\" data-inline=\"true\" value=\"修改\" onclick=\"edit("+_k.getId()+" );\"/>" +
//											"</p>";
//						}else if(_k.getFile_status()==1 || _k.getFile_status()==-1|| _k.getFile_status()==-2){
//							outStr += "<p>文件状态：<strong>审批中</strong></p>";
//						}else if(_k.getFile_status()==2){
//							outStr += "<p>文件状态：<strong>被驳回</strong></p>";
//						}else if(_k.getFile_status()==3){
//							outStr += "<p>文件状态：<strong>已批准</strong></p>";
//						}
//					}
					outStr += "</a>";
					outStr += "</li>";
			}
			outStr += "</ol>',";
			outStr += "currentPage:" + currentPage + ",recordCount:" + count
					+ "}";
		} else {
			outStr += "',";
			outStr += "currentPage:" + currentPage + ",recordCount:" + count
					+ "}";
		}
		log.info(outStr);
		//写日志
		createMobileSysOperLog(request, "KONKAOA_FILES", 720300l, "720300", "查询",
				"手机端-文件管理-查询");
		super.renderText(response, outStr);
		return null;
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
		StringBuffer dataSb = new StringBuffer("[");
		dataSb.append("{");
		dataSb.append("\"apply_user_name\":\"").append(files.getApply_user_name()).append("\",");
		dataSb.append("\"apply_user_tel\":\"").append(files.getApply_user_tel()).append("\",");
		dataSb.append("\"file_title\":\"").append(files.getFile_title()).append("\",");
		dataSb.append("\"content\":\"").append(files.getContent()).append("\",");
		
		KonkaoaFilesAuditNode fan = new KonkaoaFilesAuditNode();
		fan.setLink_id(files.getId());
		fan.setAudit_type(0);
		List<KonkaoaFilesAuditNode> auditList = super.getFacade().getKonkaoaFilesAuditNodeService()
				.getKonkaoaFilesAuditNodeList(fan);

		if(auditList.size()!=0){
			dataSb.append("\"audit_user_name\":\"").append(auditList.get(0).getAudit_user()).append("\"");
		}
		dataSb.append("},");
		dataSb.append("{}]");
		super.renderJson(response, dataSb.toString());
		return null;
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
//		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_MOBILE);
		PeProdUser _peProdUser = (PeProdUser) request.getSession().getAttribute(Constants.USER_MOBILE);
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

			KonkaDept kd = super.getSuperiorForDeptType(_peProdUser.getDept_id(), 3);
			PeProdUser peProdUser = new PeProdUser();
			peProdUser.setIs_del(0);
			if (null != kd) {
				peProdUser.getMap().put("dept_id_in", kd.getDept_id());
			}
			peProdUser.getRow().setCount(2);
			peProdUser.getMap().put("user_id", _peProdUser.getId());
			peProdUser.getMap().put("user_name", audit_user_name);
			List<PeProdUser> peProdUserList = super.getFacade().getPeProdUserService()
					.getPeProdUserListForSelectUser(peProdUser);
			if(peProdUserList.size()==0){
				super.renderText(response, "审批人不正确！请检查是否输入有误");
				return null;
			}
		}

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
		super.renderText(response, "success");
		return null;
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm");

		String id = (String) dynaBean.get("id");
		if(StringUtils.isBlank(id)){
			super.renderText(response, "错误！");
			return null;
		}

		KonkaoaFiles files = new KonkaoaFiles();
		files.setId(new Long(id));
		files = super.getFacade().getKonkaoaFilesService().getKonkaoaFiles(files);
		if (null == files) {
			super.renderText(response, "数据错误，或已被删除！");
			return null;
		}
		String outStr = "{htmlData:'";
		outStr += "<ul data-role=\"listview\" data-inset=\"true\" data-theme=\"b\" data-divider-theme=\"a\">";
		outStr +="<li data-role=\"list-divider\">标   题："+files.getFile_title()+"</li>";
		outStr +="<li>";
		outStr +="<p>编&nbsp;&nbsp;&nbsp;&nbsp;号："+files.getFile_no()+"</p>";
		outStr +="<p>负责人："+files.getApply_user_name()+"</p>";
		if(files.getApply_user_tel()==null){
			outStr +="<p>电&nbsp;&nbsp;&nbsp;&nbsp;话：</p>";
		}else {
			outStr +="<p>电&nbsp;&nbsp;&nbsp;&nbsp;话："+files.getApply_user_tel()+"</p>";
		}
		
		KonkaoaFilesAuditNode fan = new KonkaoaFilesAuditNode();
		fan.setLink_id(files.getId());
		fan.setAudit_type(0);
		List<KonkaoaFilesAuditNode> auditList = super.getFacade().getKonkaoaFilesAuditNodeService()
				.getKonkaoaFilesAuditNodeList(fan);

		if(auditList.size()!=0){
			String[] audit_user = new String[auditList.size()];
			for(int i=0;i<auditList.size();i++){
				audit_user[i]=auditList.get(i).getAudit_user();
			}
			outStr +="<p>审批人："+StringUtils.join(audit_user,",")+"</p>";
		}
		outStr +="<p>内&nbsp;&nbsp;&nbsp;&nbsp;容："+files.getContent()+"</p>";
		outStr += "</li>";
		outStr += "</ul>";
		// 审批记录显示
		KonkaoaFilesAuditNode _fan = new KonkaoaFilesAuditNode();
		_fan.setLink_id(files.getId());
		_fan.setAudit_type(0);
		_fan.getMap().put("is_audit", "is_audit");// 只查出经过审批后的审批记录
		List<KonkaoaFilesAuditNode> auditNodeList=getFacade().getKonkaoaFilesAuditNodeService()
				.getKonkaoaFilesAuditNodeList(_fan);
		if(auditNodeList.size()!=0){
			outStr +="<br/>";
			outStr +="<br/>";
			outStr +="<div data-role=\"controlgroup\"  data-type=\"horizontal\">";
			outStr += "<ol data-role=\"listview\">";
			for(KonkaoaFilesAuditNode t:auditNodeList){
				outStr +="<li>";
				if(t.getAudit_result()==2){
					outStr +="<span style=\"color:green\">同意</span>";
				}else if(t.getAudit_result()==1){
					outStr +="<span style=\"color:red\">驳回</span>";
				}else if(t.getAudit_result()==-1){
					outStr +="<span style=\"color:red\">转发</span>";
				}
				outStr += "&nbsp;&nbsp;&nbsp;&nbsp;审批人："+t.getAudit_user();
				outStr += "&nbsp;&nbsp;&nbsp;&nbsp;"+s.format(t.getAudit_datetime());
				outStr +="</li>";
			}
			outStr +="</ol>";
			outStr +="</div>";
		}
		if(files.getFile_from()==1){
			outStr +="',file_from:1";
		}else{
			outStr +="',file_from:0";
		}
		if(files.getFile_status()==0||files.getFile_status()==-3){
			outStr +=",key:0";
		}else {
			outStr +=",key:1";
		}
		outStr +="}";
		super.renderText(response, outStr);
		return null;
	}
}