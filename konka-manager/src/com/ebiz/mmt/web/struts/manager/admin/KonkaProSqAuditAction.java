package com.ebiz.mmt.web.struts.manager.admin;
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
import com.ebiz.mmt.domain.FighterInfo;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaProSq;
import com.ebiz.mmt.domain.KonkaXxAuditNote;
import com.ebiz.mmt.domain.KonkaXxBaseType;
import com.ebiz.mmt.domain.KonkaXxZmdAuditUserHis;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

public class KonkaProSqAuditAction extends BaseAction {
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
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String file_no_like = (String) dynaBean.get("file_no_like");
		String pro_name_like = (String) dynaBean.get("pro_name_like");
		String add_time_start = (String) dynaBean.get("add_time_start");
		String add_time_end = (String) dynaBean.get("add_time_end");
		String excel_all = (String) dynaBean.get("excel_all");
		String pro_state = (String) dynaBean.get("pro_state");
		String area_id = (String) dynaBean.get("area_id");
		String dept_id = (String) dynaBean.get("dept_id");
		//得到登陆人分公司id
		PeProdUser user_id = super.getSessionUserInfo(request);
		KonkaDept kDept = super.getKonkaDeptForFgs(user_id.getDept_id());

		KonkaProSq entity = new KonkaProSq();
		if (null!=kDept) {
			dept_id=kDept.getDept_id().toString();
		}
		if (StringUtils.isNotBlank(file_no_like)) {
			entity.getMap().put("file_no_like", file_no_like);
		}
		if (StringUtils.isNotBlank(pro_name_like)) {
			entity.getMap().put("pro_name_like", pro_name_like);
		}
		if (StringUtils.isNotBlank(add_time_start)) {
			entity.getMap().put("add_time_start", add_time_start + " 00:00:00");
		}
		if (StringUtils.isNotBlank(add_time_end)) {
			entity.getMap().put("add_time_end", add_time_end + " 23:59:59");
		}
		if (StringUtils.isNotBlank(pro_state)) {
			entity.setPro_state(Integer.valueOf(pro_state));
		}
		if (StringUtils.isNotBlank(area_id)) {
			entity.setArea_id(Integer.valueOf(area_id));
		}
		if (StringUtils.isNotBlank(dept_id)) {
			entity.setDept_id(Long.valueOf(dept_id));
		}
		//只显示待审核项目
		entity.getMap().put("file_state_begin", "1");
		entity.getMap().put("file_state_end", "2");
		
		entity.setIs_del(0);
		Long recordCount = getFacade().getKonkaProSqService().getKonkaProSqCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<KonkaProSq> entityList = getFacade().getKonkaProSqService().getKonkaProSqPaginatedList(entity);
		request.setAttribute("entityList", entityList);
		request.setAttribute("sybDeptInfoList", super.getDeptInfoListWithOutLimit(mapping, form, request, response));
		if (StringUtils.isNotBlank(excel_all) && "1".equals(excel_all)) {
			if (recordCount.intValue() > 5000) {
				renderJavaScript(response, "alert('" + super.getMessage(request, "export.too.many")
				        + "！');history.back();");
				return null;
			}
			entity.getRow().setCount(recordCount.intValue());
			List<KonkaProSq> entityList1 = getFacade().getKonkaProSqService().getKonkaProSqPaginatedList(entity);
			dynaBean.set("excel_all", excel_all);
			request.setAttribute("allList", entityList1);
		}
		return mapping.findForward("list");
	}

	
	// 审核模块：主要用于判断是否有审核权限
	
	public ActionForward audit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		saveToken(request);
		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);

		String id = (String) dynaBean.get("id");
		
		//得到登陆人角色
		PeProdUser user_id = super.getSessionUserInfo(request);
		PeRoleUser peRoleUser = new PeRoleUser();
		peRoleUser.setUser_id(user_id.getId());
		List<PeRoleUser> entityListRole = super.getFacade().getPeRoleUserService().getPeRoleUserList(peRoleUser);
		
		//通过id得到当前节点node_id(role_id)
		KonkaXxZmdAuditUserHis kAuditUserHis2 = new KonkaXxZmdAuditUserHis();
		kAuditUserHis2.setZmd_user_id(Long.valueOf(id));
		kAuditUserHis2=super.getFacade().getKonkaXxZmdAuditUserHisService().getKonkaXxZmdAuditUserHisMAX(kAuditUserHis2);
		Long role_id=kAuditUserHis2.getAudit_next_node_id();
		int is_end=kAuditUserHis2.getIs_end();
		//role_id登陆人和当前节点匹配
		
		//审核结束
		if(is_end==1){
			super.renderJavaScript(response, "window.onload=function(){alert('已审核结束!');history.back();}");
			return null;
		}
		if (entityListRole.size() > 0) {
			int flag=0;
			for (PeRoleUser temp : entityListRole) {
				if(temp.getRole_id().equals(role_id)){
					flag++;
				}
			}
			//没有审核权限
			if(flag==0){
				super.renderJavaScript(response, "window.onload=function(){alert('您无审核权限!');history.back();}");
				return null;
			}
		}
		
		
		
		//当前审核角色传入form页面
		request.setAttribute("audit_role_id", role_id);
		KonkaProSq entity = new KonkaProSq();
		entity.setId(new Long(id));
		entity = getFacade().getKonkaProSqService().getKonkaProSq(entity);
		KonkaDept konkaDept = new KonkaDept();
		konkaDept.setDept_id(entity.getDept_id());
		konkaDept = super.getFacade().getKonkaDeptService().getKonkaDept(konkaDept);
		request.setAttribute("dept_name", konkaDept.getDept_name());
		if (null == entity) {
			saveMessage(request, "entity.missing");
			return mapping.findForward("list");
		}
		super.copyProperties(form, entity);

		// 驳回时显示驳回的具体角色
		KonkaXxAuditNote auditNote = new KonkaXxAuditNote();
		auditNote.getMap().put("role_id_value", role_id);
		auditNote.setAudit_type(30L);
		List<KonkaXxAuditNote> konkaXxAuditNoteList = super.getFacade().getKonkaXxAuditNoteService()
				.getKonkaXxAuditNoteList(auditNote);

		request.setAttribute("konkaXxAuditNoteList", konkaXxAuditNoteList);
		dynaBean.set("role_id_value", role_id);
		return mapping.findForward("input");
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
		String id = (String) dynaBean.get("id");
		String file_status = (String) dynaBean.get("file_status");
		String is_support = (String) dynaBean.get("is_support");
		String support_content = (String) dynaBean.get("support_content");
		//驳回角色
		String audit_user_role = (String) dynaBean.get("audit_user_role");
        //审核意见
		String audit_comment = (String) dynaBean.get("audit_comment");
		if (!GenericValidator.isLong(id)) {
			String msg = super.getMessage(request, "errors.parm");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}
		PeProdUser user_id = super.getSessionUserInfo(request);
		KonkaProSq entity = new KonkaProSq();
		super.copyProperties(entity, form);
		entity.setId(Long.valueOf(id));
			
			//*******审核模块开始
			KonkaXxZmdAuditUserHis kAuditUserHis = new KonkaXxZmdAuditUserHis();
			// 审核通过
			if ("3".equals(file_status)) {
				//获得node_id
				KonkaXxZmdAuditUserHis kAuditUserHis2 = new KonkaXxZmdAuditUserHis();
				kAuditUserHis2.setZmd_user_id(Long.valueOf(id));
				kAuditUserHis2=super.getFacade().getKonkaXxZmdAuditUserHisService().getKonkaXxZmdAuditUserHisMAX(kAuditUserHis2);
				Long audit_role_id=kAuditUserHis2.getAudit_next_node_id();
				KonkaXxAuditNote auditNote = new KonkaXxAuditNote();
				auditNote.setAudit_type(30L);
				auditNote.setAudit_node_id(Long.valueOf(audit_role_id));
				auditNote = super.getFacade().getKonkaXxAuditNoteService().getKonkaXxAuditNote(auditNote);
				if (null == auditNote) {
					String msg = super.getMessage(request, "entity.missing");
					super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
					return null;
				}
				// 历史记录表
				kAuditUserHis.setAudit_status_desc("审核通过");
				kAuditUserHis.setAudit_desc("备案申请");
				kAuditUserHis.setAudit_type(24000L);
				kAuditUserHis.setAudit_user_id(user_id.getId());
				kAuditUserHis.setZmd_user_id(Long.valueOf(id));
				kAuditUserHis.setAudit_user_name(user_id.getUser_name());
				kAuditUserHis.setAudit_date(new Date());
				if (StringUtils.isNotBlank(audit_comment)) {
					kAuditUserHis.setAudit_option(audit_comment);
				}
				
				//审核结束
				if (auditNote.getIs_audit_end() == 1) {
					entity.setPro_state(1);
					entity.setFile_state(3);
				    // 历史记录表
					kAuditUserHis.setIs_end(1);
					kAuditUserHis.setAudit_status(24300L);
					kAuditUserHis.setAudit_node_id(auditNote.getAudit_node_id());
					kAuditUserHis.setAudit_node_name(auditNote.getAudit_node_name());
				}else if(auditNote.getIs_audit_end() == 0 && audit_role_id==8001L){
					//判断是否需要总部支持
						if("1".equals(is_support)){//否,流程结束
							entity.setPro_state(1);
							entity.setFile_state(3);
							entity.setIs_support(1);
							//历史记录表
							kAuditUserHis.setIs_end(1);
							kAuditUserHis.setAudit_status(24300L);
							kAuditUserHis.setAudit_node_id(auditNote.getAudit_node_id());
							kAuditUserHis.setAudit_node_name(auditNote.getAudit_node_name());
					    }else{//是，继续审核
						entity.setPro_state(0);
						entity.setFile_state(2);
						entity.setIs_support(0);
						entity.setSupport_content(support_content);
						// 历史记录表
						kAuditUserHis.setIs_end(0);
						kAuditUserHis.setAudit_next_node_id(auditNote.getAudit_next_node_id());
						kAuditUserHis.setAudit_next_node_name(auditNote.getAudit_next_node_name());
						kAuditUserHis.setAudit_status(24110L);
						kAuditUserHis.setAudit_node_id(auditNote.getAudit_node_id());
						kAuditUserHis.setAudit_node_name(auditNote.getAudit_node_name());
					}
				}
			}
			// 驳回
			if ("4".equals(file_status)) {
				/*if (!GenericValidator.isLong(audit_user_role)) {
					String msg = super.getMessage(request, "errors.parm");
					super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
					return null;
				}*/
				KonkaXxAuditNote auditNote = new KonkaXxAuditNote();
				auditNote.setAudit_type(30L);
				auditNote.setAudit_node_id(Long.valueOf(audit_user_role));
				auditNote = super.getFacade().getKonkaXxAuditNoteService().getKonkaXxAuditNote(auditNote);
				if (null == auditNote) {
					String msg = super.getMessage(request, "entity.missing");
					super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
					return null;
				}
				// 历史记录表
				kAuditUserHis.setAudit_desc("备案申请");
				kAuditUserHis.setAudit_type(24000L);
				kAuditUserHis.setZmd_user_id(Long.valueOf(id));
				kAuditUserHis.setAudit_user_id(user_id.getId());
				kAuditUserHis.setAudit_user_name(user_id.getUser_name());
				kAuditUserHis.setAudit_date(new Date());
				kAuditUserHis.setIs_end(0);
				if (StringUtils.isNotBlank(audit_comment)) {
					kAuditUserHis.setAudit_option(audit_comment);
				}
				if (auditNote.getAudit_seq() == 0) {//驳回到申请人
					entity.setPro_state(0);
					entity.setFile_state(5);
					entity.setIs_support(1);
					entity.setSupport_content("");
					kAuditUserHis.setAudit_status_desc("驳回到申请人");
					kAuditUserHis.setAudit_status(24100L);
					kAuditUserHis.setAudit_next_node_id(0L);
					kAuditUserHis.setAudit_next_node_name("申请人");
					kAuditUserHis.setAudit_node_id(8001L);
					kAuditUserHis.setAudit_node_name("工程部经理");
				} else if(auditNote.getAudit_seq() == 1){//驳回到工程部经理
					entity.setPro_state(0);
					entity.setFile_state(4);
					kAuditUserHis.setAudit_status_desc("驳回");
					kAuditUserHis.setAudit_status(24110L);
					kAuditUserHis.setAudit_next_node_id(8001L);
					kAuditUserHis.setAudit_next_node_name("工程部经理");
					kAuditUserHis.setAudit_node_id(8002L);
					kAuditUserHis.setAudit_node_name("渠道管理部项目主管");
					entity.setIs_support(1);
					entity.setSupport_content("");
				}else{
					//当前节点无法驳回
				}
			}
			entity.setKonkaXxZmdAuditUserHis(kAuditUserHis);
			super.getFacade().getKonkaProSqService().modifyKonkaProSqAndHistory(entity);
			//*******审核模块结束
			
			saveMessage(request, "entity.inserted_success");
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
		super.getModPopeDom(form, request);
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;

		String id = (String) dynaBean.get("id");

		if (!GenericValidator.isLong(id)) {
			saveError(request, "errors.long", id);
			return mapping.findForward("list");
		}

		KonkaProSq entity = new KonkaProSq();
		entity.setId(new Long(id));
		entity = getFacade().getKonkaProSqService().getKonkaProSq(entity);
		if (null != entity.getArea_id()) {
			if (entity.getArea_id().intValue() == 10) {
				dynaBean.set("area_name", "华东");
			} else if (entity.getArea_id().intValue() == 20) {
				dynaBean.set("area_name", "山东");
			} else if (entity.getArea_id().intValue() == 30) {
				dynaBean.set("area_name", "东北");
			} else if (entity.getArea_id().intValue() == 40) {
				dynaBean.set("area_name", "华北");
			}else if (entity.getArea_id().intValue() == 50) {
				dynaBean.set("area_name", "华南");
			} else if (entity.getArea_id().intValue() == 60) {
				dynaBean.set("area_name", "西南");
			} else if (entity.getArea_id().intValue() == 70) {
				dynaBean.set("area_name", "华中");
			} else if (entity.getArea_id().intValue() == 80) {
				dynaBean.set("area_name", "西北");
			}
		}
		if (null == entity) {
			saveMessage(request, "entity.missing");
			return mapping.findForward("list");
		}
		entity.setQueryString(super.serialize(request, "id", "method"));
		super.copyProperties(form, entity);

		FighterInfo ft = new FighterInfo();
		ft.setPro_id(entity.getId());
		List<FighterInfo> fighterInfoList = super.getFacade().getFighterInfoService().getFighterInfoList(ft);

		request.setAttribute("fighterInfoList", fighterInfoList);

		request.setAttribute("sybDeptInfoList", super.getDeptInfoListWithOutLimit(mapping, form, request, response));

		KonkaXxBaseType kbt = new KonkaXxBaseType();
		kbt.setPar_id(140000L);
		List<KonkaXxBaseType> konkaXxBaseTypeList = super.getFacade().getKonkaXxBaseTypeService()
		        .getKonkaXxBaseTypeList(kbt);
		request.setAttribute("konkaXxBaseTypeList", konkaXxBaseTypeList);

		return mapping.findForward("input");
	}
	
	
	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		if (!GenericValidator.isLong(id)) {
			saveError(request, "errors.long", id);
			return mapping.findForward("list");
		}

		KonkaProSq entity = new KonkaProSq();
		entity.setId(new Long(id));
		entity = getFacade().getKonkaProSqService().getKonkaProSq(entity);

		KonkaDept konkaDept = new KonkaDept();
		konkaDept.setDept_id(entity.getDept_id());
		konkaDept = super.getFacade().getKonkaDeptService().getKonkaDept(konkaDept);
		request.setAttribute("dept_name", konkaDept.getDept_name());

		if (null == entity) {
			saveMessage(request, "entity.missing");
			return mapping.findForward("list");
		}
		super.copyProperties(form, entity);

		FighterInfo ft = new FighterInfo();
		ft.setPro_id(entity.getId());
		List<FighterInfo> fighterInfoList = super.getFacade().getFighterInfoService().getFighterInfoList(ft);

		request.setAttribute("fighterInfoList", fighterInfoList);
		
		// 审核意见
		KonkaXxZmdAuditUserHis konkaXxZmdAuditUserHis = new KonkaXxZmdAuditUserHis();
		konkaXxZmdAuditUserHis.setZmd_user_id(Long.valueOf(id));
		konkaXxZmdAuditUserHis.setAudit_type(24000L);
		konkaXxZmdAuditUserHis.getMap().put("order_by_id", true);
		List<KonkaXxZmdAuditUserHis> kHis = super.getFacade().getKonkaXxZmdAuditUserHisService()
				.getKonkaXxZmdAuditUserHisList(konkaXxZmdAuditUserHis);
		request.setAttribute("kHis", kHis);

		return mapping.findForward("view");

	}

	/**
	 * @param request
	 *            自动生成登录用户所在部门的提交文件编号
	 * @author Cheng,Bing
	 */
	public String getFilesMaxNo(String file_no_lm) {

		Long max_fileno = null;

		KonkaProSq kd = new KonkaProSq();
		kd.getMap().put("file_no_lm", file_no_lm);
		Long max_fileno_1 = super.getFacade().getKonkaProSqService().getKonkaProSqNoMax(kd);
		max_fileno_1 = max_fileno_1 == null ? 0 : max_fileno_1;

		KonkaProSq kf = new KonkaProSq();
		kf.getMap().put("file_no_lm", file_no_lm);
		Long max_fileno_2 = super.getFacade().getKonkaProSqService().getKonkaProSqNoMax(kf);
		max_fileno_2 = max_fileno_2 == null ? 0 : max_fileno_2;

		max_fileno = max_fileno_1 >= max_fileno_2 ? max_fileno_1 : max_fileno_2;
		max_fileno = max_fileno + 1;
		String file_no_r = "";
		if (max_fileno < 1000) {
			file_no_r = "0000".substring(0, 4 - ("" + max_fileno).length()) + max_fileno;
		} else {
			file_no_r = "" + max_fileno;
		}

		return file_no_lm + file_no_r;
	}

	public ActionForward chooseArea(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String dept_id = (String) dynaBean.get("dept_id");
		String area_id = "";
		if (StringUtils.isNotBlank(dept_id)) {
			KonkaDept entity = new KonkaDept();
			entity.setDept_id(Long.valueOf(dept_id));
			entity = super.getFacade().getKonkaDeptService().getKonkaDept(entity);
			if (null != entity.getP_area()) {
				area_id = entity.getP_area().toString();// 表示该分公司有大区
			} else {
				area_id = "";// 表示该分公司没有大区，可以选择大区
			}
		}

		super.renderJson(response, area_id);
		return null;
	}

}
