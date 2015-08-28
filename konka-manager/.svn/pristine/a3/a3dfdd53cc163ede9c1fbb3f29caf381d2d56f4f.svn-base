package com.ebiz.mmt.web.struts.manager.admin;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.Attachment;
import com.ebiz.mmt.domain.GcxmAuditProcess;
import com.ebiz.mmt.domain.GcxmAuditProcessNode;
import com.ebiz.mmt.domain.GcxmProj;
import com.ebiz.mmt.domain.GcxmProjAudit;
import com.ebiz.mmt.domain.GcxmProjAuditNode;
import com.ebiz.mmt.domain.GcxmProjCompet;
import com.ebiz.mmt.domain.GcxmProjOffer;
import com.ebiz.mmt.domain.GcxmProjSupply;
import com.ebiz.mmt.domain.GcxmProjTj;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.PePdModel;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.util.EncryptUtils;
import com.ebiz.ssi.web.struts.bean.Pager;
import com.ebiz.ssi.web.struts.bean.UploadFile;

public class GcxmProjAction extends BaseAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// if (null == super.checkUserModPopeDom(form, request, "0")) {
		// return super.checkPopedomInvalid(request, response);
		// }

		super.getModPopeDom(form, request);
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String mod_id = (String) dynaBean.get("mod_id");
		String proj_type = (String) dynaBean.get("proj_type");
		String proj_code_like = (String) dynaBean.get("proj_code_like");
		String proj_name_like = (String) dynaBean.get("proj_name_like");
		String buyer_like = (String) dynaBean.get("buyer_like");
		String fgs_id = (String) dynaBean.get("fgs_id");
		String create_date_start = (String) dynaBean.get("create_date_start");
		String create_date_end = (String) dynaBean.get("create_date_end");
		String create_name_like = (String) dynaBean.get("create_name_like");
		String info_state = (String) dynaBean.get("info_state");
		String is_validate = (String) dynaBean.get("is_validate");
		String offer_date_start = (String) dynaBean.get("offer_date_start");
		String offer_date_end = (String) dynaBean.get("offer_date_end");
		String size_like = (String) dynaBean.get("size_like");
		//获取当前登录者信息
		PeProdUser userInfo = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
		if (null == userInfo) {
			String msg = super.getMessage(request, "user.session.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		GcxmProj entity = new GcxmProj();

		KonkaDept konkaDept = new KonkaDept();
		konkaDept.setDept_id(userInfo.getDept_id());
		konkaDept = super.getFacade().getKonkaDeptService().getKonkaDept(konkaDept);

		// 分公司取得
		KonkaDept fgs_dept = super.getSuperiorForDeptType(userInfo.getDept_id(), 3);
		if (fgs_dept != null) {
			dynaBean.set("fgs_dept_name", fgs_dept.getDept_name());
		}

		boolean zb = false;
		boolean fgs = false;
		boolean qt = false;
		PeRoleUser rUser = new PeRoleUser();
		rUser.setUser_id(userInfo.getId());
		List<PeRoleUser> roleUserList = getFacade().getPeRoleUserService().getPeRoleUserList(rUser);
		if (null != roleUserList && roleUserList.size() > 0) {
			for (PeRoleUser pu : roleUserList) {
				if (pu.getRole_id() < 30L || pu.getRole_id().intValue() == 223 || pu.getRole_id().intValue() == 224
						|| pu.getRole_id().intValue() == 225) {
					zb = true;// 总部
					break;
				} else if (pu.getRole_id().intValue() == 30 || pu.getRole_id().intValue() == 34) {
					fgs = true;// 分公司
					break;
				} else {
					qt = true;
				}
			}
		}

		if (zb) {

		} else if (fgs) {
			entity.setFgs_id(fgs_dept.getDept_id());
		} else if (qt) {
			entity.setCreate_user_id(userInfo.getId());
		}

		entity.getMap().put("create_user_id", userInfo.getId());// union 过滤未提交的数据

		if (StringUtils.isNotBlank(proj_type)) {
			entity.setProj_type(Long.valueOf(proj_type));
		}
		if (StringUtils.isNotBlank(buyer_like)) {
			entity.getMap().put("buyer_like", buyer_like);
		}
		if (StringUtils.isNotBlank(proj_code_like)) {
			entity.getMap().put("proj_code_like", proj_code_like);
		}
		if (StringUtils.isNotBlank(proj_name_like)) {
			entity.getMap().put("proj_name_like", proj_name_like);
		}
		if (StringUtils.isNotBlank(create_date_start)) {
			entity.getMap().put("create_date_start", create_date_start + " 00:00:00");
		}
		if (StringUtils.isNotBlank(create_date_end)) {
			entity.getMap().put("create_date_end", create_date_end + " 23:59:59");
		}
		if (StringUtils.isNotBlank(create_name_like)) {
			entity.getMap().put("create_name_like", create_name_like);
		}
		if (StringUtils.isNotBlank(info_state)) {
			entity.setInfo_state(Integer.valueOf(info_state));
		}
		if (StringUtils.isNotBlank(is_validate)) {
			if(is_validate.equals("0")){
				entity.getMap().put("is_validate_true", true);
			}else if(is_validate.equals("1")){
				entity.getMap().put("is_validate_false", true);
			}
		}else{
			entity.getMap().put("is_validate_true", true);
		}
		if (StringUtils.isNotBlank(fgs_id)) {
			entity.setFgs_id(Long.valueOf(fgs_id));
		}
		if (StringUtils.isNotBlank(offer_date_start)) {
			entity.getMap().put("offer_date_start", offer_date_start + " 00:00:00");
		}
		if (StringUtils.isNotBlank(offer_date_end)) {
			entity.getMap().put("offer_date_end", offer_date_end + " 23:59:59");
		}
		if (StringUtils.isNotBlank(size_like)) {
			entity.getMap().put("size_like", size_like);
		}
		// entity.setCreate_user_id(userInfo.getId());// 只能看到本人添加的
		// entity.setDel_mark(0);
		entity.setDel_mark(0);
		
		Long recordCount = getFacade().getGcxmProjService().getGcxmProjForUnionCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<GcxmProj> entityList = getFacade().getGcxmProjService().getGcxmProjForUnionPaginatedList(entity);
		if (entityList != null && entityList.size() > 0) {
			for (GcxmProj gcxmProj : entityList) {
				GcxmProjTj pt = new GcxmProjTj();
				pt.setProj_id(gcxmProj.getId().toString());
				List<GcxmProjTj> ptList = super.getFacade().getGcxmProjTjService().getGcxmProjTjList(pt);
				String model_1 = "";
				String model_2 = "";
				String model_3 = "";
				if (ptList != null && ptList.size() > 0) {
					for (GcxmProjTj gcxmProjTj : ptList) {
						if (gcxmProjTj.getMemo() != null && gcxmProjTj.getMemo().equals("1")) {
							model_1 = gcxmProjTj.getModel();
						} else if (gcxmProjTj.getMemo() != null && gcxmProjTj.getMemo().equals("2")) {
							model_2 = gcxmProjTj.getModel();
						} else if (gcxmProjTj.getMemo() != null && gcxmProjTj.getMemo().equals("3")) {
							model_3 = gcxmProjTj.getModel();
						}
					}
				}
				gcxmProj.getMap().put("model_1", model_1);
				gcxmProj.getMap().put("model_2", model_2);
				gcxmProj.getMap().put("model_3", model_3);

				KonkaDept kd = new KonkaDept();
				kd.setDept_id(gcxmProj.getFgs_id());
				kd = super.getFacade().getKonkaDeptService().getKonkaDept(kd);
				if (kd != null) {
					gcxmProj.getMap().put("dept_name", kd.getDept_name());
				}

				GcxmProjAuditNode pn = new GcxmProjAuditNode();
				pn.setProj_id(gcxmProj.getId());
				pn = super.getFacade().getGcxmProjAuditNodeService().getGcxmProjAuditNode(pn);

				boolean is_audit = false;
				if (null != roleUserList && roleUserList.size() > 0) {
					for (PeRoleUser pu : roleUserList) {
						if (pn != null) {
							if (pu.getRole_id().intValue() == pn.getAudit_role_id().intValue()) {
								is_audit = true;
								break;
							}
						}
					}
				}
				gcxmProj.getMap().put("is_audit", is_audit);
			}
		}
		
		dynaBean.set("mod_id", mod_id);
		dynaBean.set("user_id", userInfo.getId());
		request.setAttribute("entityList", entityList);

		request.setAttribute("sybDeptInfoList", super.getDeptInfoListWithOutLimit(mapping, form, request, response));

		return mapping.findForward("list");
	}

	public ActionForward sheet(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// if (null == super.checkUserModPopeDom(form, request, "0")) {
		// return super.checkPopedomInvalid(request, response);
		// }

		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String mod_id = (String) dynaBean.get("mod_id");
		String proj_type = (String) dynaBean.get("proj_type");
		String fgs_id = (String) dynaBean.get("fgs_id");
		String proj_code_like = (String) dynaBean.get("proj_code_like");
		String proj_name_like = (String) dynaBean.get("proj_name_like");
		String buyer_like = (String) dynaBean.get("buyer_like");
		String create_date_start = (String) dynaBean.get("create_date_start");
		String create_date_end = (String) dynaBean.get("create_date_end");
		String create_name_like = (String) dynaBean.get("create_name_like");
		String info_state = (String) dynaBean.get("info_state");
		String is_validate = (String) dynaBean.get("is_validate");
		String offer_date_start = (String) dynaBean.get("offer_date_start");
		String offer_date_end = (String) dynaBean.get("offer_date_end");
		String size_like = (String) dynaBean.get("size_like");
		//
		PeProdUser userInfo = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
		if (null == userInfo) {
			String msg = super.getMessage(request, "user.session.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		GcxmProj entity = new GcxmProj();

		KonkaDept konkaDept = new KonkaDept();
		konkaDept.setDept_id(userInfo.getDept_id());
		konkaDept = super.getFacade().getKonkaDeptService().getKonkaDept(konkaDept);

		// 分公司取得
		KonkaDept fgs_dept = super.getSuperiorForDeptType(userInfo.getDept_id(), 3);
		if (fgs_dept != null) {
			dynaBean.set("fgs_dept_name", fgs_dept.getDept_name());
		}

		boolean zb = false;
		boolean fgs = false;
		boolean qt = false;
		PeRoleUser rUser = new PeRoleUser();
		rUser.setUser_id(userInfo.getId());
		List<PeRoleUser> roleUserList = getFacade().getPeRoleUserService().getPeRoleUserList(rUser);
		if (null != roleUserList && roleUserList.size() > 0) {
			for (PeRoleUser pu : roleUserList) {
				if (pu.getRole_id() < 30L || pu.getRole_id().intValue() == 223 || pu.getRole_id().intValue() == 224
						|| pu.getRole_id().intValue() == 225) {
					zb = true;// 总部
					break;
				} else if (pu.getRole_id().intValue() == 30 || pu.getRole_id().intValue() == 34) {
					fgs = true;// 分公司
					break;
				} else {
					qt = true;
				}
			}
		}

		if (zb) {

		} else if (fgs) {
			entity.setFgs_id(fgs_dept.getDept_id());
		} else if (qt) {
			entity.setCreate_user_id(userInfo.getId());
		}

		entity.getMap().put("create_user_id", userInfo.getId());// union 过滤未提交的数据

		if (StringUtils.isNotBlank(proj_type)) {
			entity.setProj_type(Long.valueOf(proj_type));
		}
		if (StringUtils.isNotBlank(buyer_like)) {
			entity.getMap().put("buyer_like", buyer_like);
		}
		if (StringUtils.isNotBlank(proj_code_like)) {
			entity.getMap().put("proj_code_like", proj_code_like);
		}
		if (StringUtils.isNotBlank(proj_name_like)) {
			entity.getMap().put("proj_name_like", proj_name_like);
		}
		if (StringUtils.isNotBlank(create_date_start)) {
			entity.getMap().put("create_date_start", create_date_start + " 00:00:00");
		}
		if (StringUtils.isNotBlank(create_date_end)) {
			entity.getMap().put("create_date_end", create_date_end + " 23:59:59");
		}
		if (StringUtils.isNotBlank(create_name_like)) {
			entity.getMap().put("create_name_like", create_name_like);
		}
		if (StringUtils.isNotBlank(info_state)) {
			entity.setInfo_state(Integer.valueOf(info_state));
		}
		if (StringUtils.isNotBlank(is_validate)) {
			if(is_validate.equals("0")){
				entity.getMap().put("is_validate_true", true);
			}else if(is_validate.equals("1")){
				entity.getMap().put("is_validate_false", true);
			}
		}else{
			entity.setIs_validate(0);
		}
		if (StringUtils.isNotBlank(fgs_id)) {
			entity.setFgs_id(Long.valueOf(fgs_id));
		}
		if (StringUtils.isNotBlank(offer_date_start)) {
			entity.getMap().put("offer_date_start", offer_date_start + " 00:00:00");
		}
		if (StringUtils.isNotBlank(offer_date_end)) {
			entity.getMap().put("offer_date_end", offer_date_end + " 23:59:59");
		}
		if (StringUtils.isNotBlank(size_like)) {
			entity.getMap().put("size_like", size_like);
		}
		// entity.setCreate_user_id(userInfo.getId());// 只能看到本人添加的
		// entity.setDel_mark(0);
		// 获取分公司
		entity.setDel_mark(0);
		
		Long recordCount = getFacade().getGcxmProjService().getGcxmProjForUnionCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<GcxmProj> entityList = getFacade().getGcxmProjService().getGcxmProjForUnionPaginatedList(entity);
		if (entityList != null && entityList.size() > 0) {
			for (GcxmProj gcxmProj : entityList) {
				GcxmProjTj pt = new GcxmProjTj();
				pt.setProj_id(gcxmProj.getId().toString());
				List<GcxmProjTj> ptList = super.getFacade().getGcxmProjTjService().getGcxmProjTjList(pt);
				String model_1 = "";
				String model_2 = "";
				String model_3 = "";
				if (ptList != null && ptList.size() > 0) {
					for (GcxmProjTj gcxmProjTj : ptList) {
						if (gcxmProjTj.getMemo() != null && gcxmProjTj.getMemo().equals("1")) {
							model_1 = gcxmProjTj.getModel();
						} else if (gcxmProjTj.getMemo() != null && gcxmProjTj.getMemo().equals("2")) {
							model_2 = gcxmProjTj.getModel();
						} else if (gcxmProjTj.getMemo() != null && gcxmProjTj.getMemo().equals("3")) {
							model_3 = gcxmProjTj.getModel();
						}
					}
				}
				gcxmProj.getMap().put("model_1", model_1);
				gcxmProj.getMap().put("model_2", model_2);
				gcxmProj.getMap().put("model_3", model_3);

				KonkaDept kd = new KonkaDept();
				kd.setDept_id(gcxmProj.getFgs_id());
				kd = super.getFacade().getKonkaDeptService().getKonkaDept(kd);
				if (kd != null) {
					gcxmProj.getMap().put("dept_name", kd.getDept_name());
				}

				GcxmProjAuditNode pn = new GcxmProjAuditNode();
				pn.setProj_id(gcxmProj.getId());
				pn = super.getFacade().getGcxmProjAuditNodeService().getGcxmProjAuditNode(pn);

				boolean is_audit = false;
				if (null != roleUserList && roleUserList.size() > 0) {
					for (PeRoleUser pu : roleUserList) {
						if (pn != null) {
							if (pu.getRole_id().intValue() == pn.getAudit_role_id().intValue()) {
								is_audit = true;
								break;
							}
						}
					}
				}
				gcxmProj.getMap().put("is_audit", is_audit);
			}
		}
		
		dynaBean.set("mod_id", mod_id);
		dynaBean.set("user_id", userInfo.getId());
		request.setAttribute("entityList", entityList);

		// request.setAttribute("sybDeptInfoList", super.getDeptInfoListWithOutLimit(mapping, form, request, response));

		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename=" + EncryptUtils.encodingFileName("项目工程上报")
				+ ".xls");
		return new ActionForward("/../manager/admin/GcxmProj/sheet.jsp");

	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// if (null == super.checkUserModPopeDom(form, request, "1")) {
		// return super.checkPopedomInvalid(request, response);
		// }

		saveToken(request);
		super.getModPopeDom(form, request);
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");

		Date now = new Date();

		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);

		KonkaDept konkaDept = new KonkaDept();
		konkaDept.setDept_id(ui.getDept_id());
		konkaDept = super.getFacade().getKonkaDeptService().getKonkaDept(konkaDept);
		
		boolean zb = false;
		boolean fgs = false;
		boolean qt = false;
		PeRoleUser rUser = new PeRoleUser();
		rUser.setUser_id(ui.getId());
		List<PeRoleUser> roleUserList = getFacade().getPeRoleUserService().getPeRoleUserList(rUser);
		if (null != roleUserList && roleUserList.size() > 0) {
			for (PeRoleUser pu : roleUserList) {
				if (pu.getRole_id() < 30L || pu.getRole_id().intValue() == 223 || pu.getRole_id().intValue() == 224
						|| pu.getRole_id().intValue() == 225) {
					zb = true;// 总部
					break;
				} else if (pu.getRole_id().intValue() >= 30 || pu.getRole_id().intValue() <= 188) {
					fgs = true;// 分公司
					break;
				} else {
					qt = true;
				}
			}
		}


		// 分公司取得
		if (null != konkaDept.getDept_type() && konkaDept.getDept_type() > 2) {
			KonkaDept fgs_dept = super.getSuperiorForDeptType(ui.getDept_id(), 3);
			dynaBean.set("fgs_dept_name", fgs_dept.getDept_name());
			
			String gcxm_sn = "";
			Long now_time = now.getTime();
			String now_time_s = now_time.toString();
			now_time_s = StringUtils.substring(now_time_s, now_time_s.length() - 8, now_time_s.length());

			String random_s = GetRandomNumber();

			gcxm_sn = "GCXM" + fgs_dept.getDept_sn() + now_time_s + random_s;
			dynaBean.set("gcxm_sn", gcxm_sn);

		} else {
			dynaBean.set("fgs_dept_name", "总部");
			String gcxm_sn = "";
			Long now_time = now.getTime();
			String now_time_s = now_time.toString();
			now_time_s = StringUtils.substring(now_time_s, now_time_s.length() - 8, now_time_s.length());

			String random_s = GetRandomNumber();

			gcxm_sn = "GCXM" + "KONKA" + now_time_s + random_s;
			dynaBean.set("gcxm_sn", gcxm_sn);
		}

		dynaBean.set("user_name", ui.getUser_name());
		//dynaBean.set("offer_date", now);
		//dynaBean.set("delivery_date", now);
		dynaBean.set("create_date", now);
		dynaBean.set("mod_id", mod_id);

		GcxmAuditProcess gp = new GcxmAuditProcess();
		gp.setIs_del(0);
		gp.setAudit_type(1001L);
		
		// 根据当前登录人获取创建人分公司相同的流程
		if(zb){
			
		} else if (fgs) {
			konkaDept = new KonkaDept();
			konkaDept = getKonkaDeptForFgs(ui.getDept_id());
			
			List<Long> deptInList=new ArrayList<Long>();
			deptInList.add(0L);
			deptInList.add(konkaDept.getDept_id());
			gp.getMap().put("dept_id_0", deptInList);
		} else {
				String msg = super.getMessage(request, "您没有查看权限");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
		}
		
		List<GcxmAuditProcess> gpList = super.getFacade().getGcxmAuditProcessService().getGcxmAuditProcessList(gp);
		request.setAttribute("gpList", gpList);

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
		String model_1 = (String) dynaBean.get("model_1");
		String model_2 = (String) dynaBean.get("model_2");
		String model_3 = (String) dynaBean.get("model_3");
		String process_id = (String) dynaBean.get("process_id");

		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		if (null == peProdUser) {
			return null;
		}

		GcxmProj entity = new GcxmProj();
		super.copyProperties(entity, form);

		KonkaDept konkaDept = new KonkaDept();
		konkaDept.setDept_id(peProdUser.getDept_id());
		konkaDept = super.getFacade().getKonkaDeptService().getKonkaDept(konkaDept);

		// 分公司取得
		KonkaDept fgs_dept = null;
		if (null != konkaDept.getDept_type() && konkaDept.getDept_type() > 2) {
			fgs_dept = super.getSuperiorForDeptType(peProdUser.getDept_id(), 3);

		}

		List<UploadFile> uploadFileList = super.uploadFile(form, true, 0, new int[] { 240 });
		List<Attachment> filesAttachmentList = new ArrayList<Attachment>();

		List<Attachment> filesAttachment1List = new ArrayList<Attachment>();
		List<Attachment> filesAttachment2List = new ArrayList<Attachment>();
		List<Attachment> filesAttachment3List = new ArrayList<Attachment>();

		if (null != uploadFileList && uploadFileList.size() > 0) {
			for (UploadFile uploadFile : uploadFileList) {
				if ("file_1".endsWith(uploadFile.getFormName())) {
					Attachment filesAttachment = new Attachment();
					filesAttachment.setFile_name(uploadFile.getFileName());
					filesAttachment.setFile_type(uploadFile.getContentType());
					filesAttachment.setFile_size(new Integer(uploadFile.getFileSize()));
					filesAttachment.setSave_path(uploadFile.getFileSavePath());
					filesAttachment.setSave_name(uploadFile.getFileSaveName());
					filesAttachment.setDel_mark(new Short("0"));
					filesAttachment1List.add(filesAttachment);
				} else if ("file_2".endsWith(uploadFile.getFormName())) {
					Attachment filesAttachment = new Attachment();
					filesAttachment.setFile_name(uploadFile.getFileName());
					filesAttachment.setFile_type(uploadFile.getContentType());
					filesAttachment.setFile_size(new Integer(uploadFile.getFileSize()));
					filesAttachment.setSave_path(uploadFile.getFileSavePath());
					filesAttachment.setSave_name(uploadFile.getFileSaveName());
					filesAttachment.setDel_mark(new Short("0"));
					filesAttachment2List.add(filesAttachment);
				} else if ("file_3".endsWith(uploadFile.getFormName())) {
					Attachment filesAttachment = new Attachment();
					filesAttachment.setFile_name(uploadFile.getFileName());
					filesAttachment.setFile_type(uploadFile.getContentType());
					filesAttachment.setFile_size(new Integer(uploadFile.getFileSize()));
					filesAttachment.setSave_path(uploadFile.getFileSavePath());
					filesAttachment.setSave_name(uploadFile.getFileSaveName());
					filesAttachment.setDel_mark(new Short("0"));
					filesAttachment3List.add(filesAttachment);

				} else {
					Attachment filesAttachment = new Attachment();
					filesAttachment.setFile_name(uploadFile.getFileName());
					filesAttachment.setFile_type(uploadFile.getContentType());
					filesAttachment.setFile_size(new Integer(uploadFile.getFileSize()));
					filesAttachment.setSave_path(uploadFile.getFileSavePath());
					filesAttachment.setSave_name(uploadFile.getFileSaveName());
					filesAttachment.setDel_mark(new Short("0"));
					filesAttachmentList.add(filesAttachment);
				}

			}
			entity.setAttachmentList(filesAttachmentList);
			entity.setAttachment1List(filesAttachment1List);
			entity.setAttachment2List(filesAttachment2List);
			entity.setAttachment3List(filesAttachment3List);
		}

		entity.getMap().put("model_1", model_1);
		entity.getMap().put("model_2", model_2);
		entity.getMap().put("model_3", model_3);

		if (StringUtils.isBlank(id)) {

			entity.getMap().put("process_id", process_id);
			entity.setCreate_date(new Date());
			entity.setCreate_name(peProdUser.getUser_name());
			entity.setCreate_user_id(peProdUser.getId());
			entity.setDel_mark(0);// 0未删除 1已删除
			entity.setFgs_id(fgs_dept == null ? 0L : fgs_dept.getDept_id());

			super.getFacade().getGcxmProjService().createGcxmProj(entity);
			saveMessage(request, "entity.inserted");
		} else {

			entity.setId(Long.valueOf(id));
			super.getFacade().getGcxmProjService().modifyGcxmProjForFj(entity);
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

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// if (null == super.checkUserModPopeDom(form, request, "4")) {
		// return super.checkPopedomInvalid(request, response);
		// }

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String[] pks = (String[]) dynaBean.get("pks");
		String mod_id = (String) dynaBean.get("mod_id");

		if (!StringUtils.isBlank(id) && GenericValidator.isLong(id)) {
			GcxmProj entity = new GcxmProj();
			entity.setId(new Long(id));
			entity.setDel_mark(1);
			getFacade().getGcxmProjService().modifyGcxmProj(entity);
			saveMessage(request, "entity.deleted");
		} else if (!ArrayUtils.isEmpty(pks)) {
			for (String xx : pks) {
				GcxmProj entity = new GcxmProj();
				entity.setId(new Long(xx));
				entity.setDel_mark(1);
				getFacade().getGcxmProjService().modifyGcxmProj(entity);
			}
			saveMessage(request, "entity.deleted");
		}
		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&").append("mod_id=").append(mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "id", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward validate(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// if (null == super.checkUserModPopeDom(form, request, "4")) {
		// return super.checkPopedomInvalid(request, response);
		// }

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id");

		if (!StringUtils.isBlank(id) && GenericValidator.isLong(id)) {
			//上报的单据状态改为无效
			GcxmProj entity = new GcxmProj();
			entity.setId(new Long(id));
			entity.setIs_validate(1);
			getFacade().getGcxmProjService().modifyGcxmProj(entity);
			saveMessage(request, "entity.deleted");
			//报价的单据状态改为无效
			GcxmProjOffer offer=new GcxmProjOffer();
			offer.setProj_id(id);
			offer=super.getFacade().getGcxmProjOfferService().getGcxmProjOffer(offer);
			offer.setIs_validate(1);
			getFacade().getGcxmProjOfferService().modifyGcxmProjOffer(offer);
			//结果的单据状态改为无效
			GcxmProjSupply supply = new GcxmProjSupply();
			supply.setProj_id(id);
			supply = super.getFacade().getGcxmProjSupplyService().getGcxmProjSupply(supply);
			supply.setIs_validate(1);
			getFacade().getGcxmProjSupplyService().modifyGcxmProjSupply(supply);
			
		}
		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&").append("mod_id=").append(mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "id", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward back(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// if (null == super.checkUserModPopeDom(form, request, "4")) {
		// return super.checkPopedomInvalid(request, response);
		// }

		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		if (null == peProdUser) {
			return null;
		}

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id");

		if (!StringUtils.isBlank(id) && GenericValidator.isLong(id)) {
			GcxmProj entity = new GcxmProj();
			entity.getMap().put("peProdUser", peProdUser);
			entity.setId(new Long(id));
			entity.setInfo_state(-1);
			getFacade().getGcxmProjService().modifyGcxmProjForCh(entity);
			saveMessage(request, "entity.deleted");
		}
		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&").append("mod_id=").append(mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "id", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// if (null == super.checkUserModPopeDom(form, request, "2")) {
		// return super.checkPopedomInvalid(request, response);
		// }
		saveToken(request);
		// super.getModPopeDom(form, request);
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;

		String id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id");

		if (!GenericValidator.isLong(id)) {
			saveError(request, "errors.long", id);
			return mapping.findForward("list");
		}

		GcxmProj entity = new GcxmProj();
		entity.setId(Long.valueOf(id));
		entity = super.getFacade().getGcxmProjService().getGcxmProj(entity);

		super.copyProperties(form, entity);

		GcxmProjAuditNode gn = new GcxmProjAuditNode();
		gn.setProj_id(Long.valueOf(id));
		gn = super.getFacade().getGcxmProjAuditNodeService().getGcxmProjAuditNode(gn);
		if (gn != null) {
			dynaBean.set("process_id", gn.getProcess_id());
		}

		KonkaDept kd = new KonkaDept();
		kd.setDept_id(entity.getFgs_id());
		kd = super.getFacade().getKonkaDeptService().getKonkaDept(kd);

		dynaBean.set("fgs_dept_name", kd.getDept_name());
		dynaBean.set("gcxm_sn", entity.getProj_code());
		dynaBean.set("user_name", entity.getCreate_name());

		// 附件
		Attachment attachment = new Attachment();
		attachment.setLink_id(new Long(id));
		attachment.setLink_tab("GCXM_PROJ");
		attachment.setDel_mark(new Short("0"));
		request.setAttribute("attachmentList", getFacade().getAttachmentService().getAttachmentList(attachment));

		GcxmProjTj pj = new GcxmProjTj();
		pj.setProj_id(id);
		List<GcxmProjTj> pjList = super.getFacade().getGcxmProjTjService().getGcxmProjTjList(pj);
		if (pjList != null && pjList.size() > 0) {
			for (GcxmProjTj gt : pjList) {
				if (gt.getMemo().equals("1")) {
					dynaBean.set("model_1", gt.getModel());
					dynaBean.set("fj_1", gt.getFj_url());
					dynaBean.set("fj_name_1", gt.getFj_name());

				} else if (gt.getMemo().equals("2")) {
					dynaBean.set("model_2", gt.getModel());
					dynaBean.set("fj_2", gt.getFj_url());
					dynaBean.set("fj_name_2", gt.getFj_name());

				} else if (gt.getMemo().equals("3")) {
					dynaBean.set("model_3", gt.getModel());
					dynaBean.set("fj_3", gt.getFj_url());
					dynaBean.set("fj_name_3", gt.getFj_name());
				}
			}
		}

		GcxmAuditProcess gp = new GcxmAuditProcess();
		gp.setIs_del(0);
		gp.setAudit_type(1001L);
		List<GcxmAuditProcess> gpList = super.getFacade().getGcxmAuditProcessService().getGcxmAuditProcessList(gp);
		request.setAttribute("gpList", gpList);

		GcxmProjAudit pt = new GcxmProjAudit();
		pt.setProj_id(Long.valueOf(id));
		List<GcxmProjAudit> auditList = super.getFacade().getGcxmProjAuditService().getGcxmProjAuditList(pt);
		for (GcxmProjAudit gcxmProjAudit : auditList) {
			PeProdUser pp = new PeProdUser();
			pp.setId(gcxmProjAudit.getAudit_user_id().longValue());
			pp = super.getFacade().getPeProdUserService().getPeProdUser(pp);
			if (pp != null) {
				gcxmProjAudit.getMap().put("audit_user_name", pp.getUser_name());
			}
		}
		request.setAttribute("auditList", auditList);
		dynaBean.set("mod_id", mod_id);

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

		GcxmProj entity = new GcxmProj();
		entity.setId(Long.valueOf(id));
		entity = super.getFacade().getGcxmProjService().getGcxmProj(entity);

		super.copyProperties(form, entity);

		// 附件
		Attachment attachment = new Attachment();
		attachment.setLink_id(new Long(id));
		attachment.setLink_tab("GCXM_PROJ");
		attachment.setDel_mark(new Short("0"));
		request.setAttribute("attachmentList", getFacade().getAttachmentService().getAttachmentList(attachment));

		// 附件2
		GcxmProjOffer gf = new GcxmProjOffer();
		gf.setProj_id(id);
		gf = super.getFacade().getGcxmProjOfferService().getGcxmProjOffer(gf);

		if (gf != null) {
			attachment = new Attachment();
			attachment.setLink_id(gf.getId());
			attachment.setLink_tab("GCXM_PROJ_OFFER");
			attachment.setDel_mark(new Short("0"));
			request.setAttribute("attachmentList2", getFacade().getAttachmentService().getAttachmentList(attachment));
		}

		GcxmProjAudit pt = new GcxmProjAudit();
		pt.setProj_id(Long.valueOf(id));
		List<GcxmProjAudit> auditList = super.getFacade().getGcxmProjAuditService().getGcxmProjAuditList(pt);
		for (GcxmProjAudit gcxmProjAudit : auditList) {
			PeProdUser pp = new PeProdUser();
			pp.setId(gcxmProjAudit.getAudit_user_id().longValue());
			pp = super.getFacade().getPeProdUserService().getPeProdUser(pp);
			if (pp != null) {
				gcxmProjAudit.getMap().put("audit_user_name", pp.getUser_name());
			}
		}
		request.setAttribute("auditList", auditList);

		if (gf != null) {
			GcxmProjAudit pt2 = new GcxmProjAudit();
			pt2.setProj_id(gf.getId());
			List<GcxmProjAudit> auditList2 = super.getFacade().getGcxmProjAuditService().getGcxmProjAuditList(pt2);
			for (GcxmProjAudit gcxmProjAudit : auditList2) {
				PeProdUser pp = new PeProdUser();
				pp.setId(gcxmProjAudit.getAudit_user_id().longValue());
				pp = super.getFacade().getPeProdUserService().getPeProdUser(pp);
				if (pp != null) {
					gcxmProjAudit.getMap().put("audit_user_name", pp.getUser_name());
				}
			}
			request.setAttribute("auditList2", auditList2);

		}

		GcxmProjCompet gc = new GcxmProjCompet();
		gc.setProj_id(Long.valueOf(id));
		List<GcxmProjCompet> gcList = super.getFacade().getGcxmProjCompetService().getGcxmProjCompetList(gc);
		request.setAttribute("gcList", gcList);

		GcxmProjTj pj = new GcxmProjTj();
		pj.setProj_id(id);
		List<GcxmProjTj> pjList = super.getFacade().getGcxmProjTjService().getGcxmProjTjList(pj);
		if (pjList != null && pjList.size() > 0) {
			for (GcxmProjTj gt : pjList) {
				if (gt.getMemo().equals("1")) {
					dynaBean.set("model_1", gt.getModel());
					dynaBean.set("fj_1", gt.getFj_url());
					dynaBean.set("fj_name_1", gt.getFj_name());

				} else if (gt.getMemo().equals("2")) {
					dynaBean.set("model_2", gt.getModel());
					dynaBean.set("fj_2", gt.getFj_url());
					dynaBean.set("fj_name_2", gt.getFj_name());

				} else if (gt.getMemo().equals("3")) {
					dynaBean.set("model_3", gt.getModel());
					dynaBean.set("fj_3", gt.getFj_url());
					dynaBean.set("fj_name_3", gt.getFj_name());
				}
			}
		}

		KonkaDept kd = new KonkaDept();
		kd.setDept_id(entity.getFgs_id());
		kd = super.getFacade().getKonkaDeptService().getKonkaDept(kd);

		dynaBean.set("fgs_dept_name", kd.getDept_name());
		dynaBean.set("gcxm_sn", entity.getProj_code());
		dynaBean.set("user_name", entity.getCreate_name());

		return mapping.findForward("view");

	}

	public ActionForward audit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id");
		if (!GenericValidator.isLong(id)) {
			saveError(request, "errors.long", id);
			return mapping.findForward("list");
		}

		GcxmProj entity = new GcxmProj();
		entity.setId(Long.valueOf(id));
		entity = super.getFacade().getGcxmProjService().getGcxmProj(entity);

		super.copyProperties(form, entity);

		// 附件
		Attachment attachment = new Attachment();
		attachment.setLink_id(new Long(id));
		attachment.setLink_tab("GCXM_PROJ");
		attachment.setDel_mark(new Short("0"));
		request.setAttribute("attachmentList", getFacade().getAttachmentService().getAttachmentList(attachment));

		GcxmProjAudit pt = new GcxmProjAudit();
		pt.setProj_id(Long.valueOf(id));
		List<GcxmProjAudit> auditList = super.getFacade().getGcxmProjAuditService().getGcxmProjAuditList(pt);
		for (GcxmProjAudit gcxmProjAudit : auditList) {
			PeProdUser pp = new PeProdUser();
			pp.setId(gcxmProjAudit.getAudit_user_id().longValue());
			pp = super.getFacade().getPeProdUserService().getPeProdUser(pp);
			if (pp != null) {
				gcxmProjAudit.getMap().put("audit_user_name", pp.getUser_name());
			}
		}
		request.setAttribute("auditList", auditList);

		GcxmProjTj pj = new GcxmProjTj();
		pj.setProj_id(id);
		List<GcxmProjTj> pjList = super.getFacade().getGcxmProjTjService().getGcxmProjTjList(pj);
		if (pjList != null && pjList.size() > 0) {
			for (GcxmProjTj gt : pjList) {
				if (gt.getMemo().equals("1")) {
					dynaBean.set("model_1", gt.getModel());
					dynaBean.set("fj_1", gt.getFj_url());
					dynaBean.set("fj_name_1", gt.getFj_name());

				} else if (gt.getMemo().equals("2")) {
					dynaBean.set("model_2", gt.getModel());
					dynaBean.set("fj_2", gt.getFj_url());
					dynaBean.set("fj_name_2", gt.getFj_name());

				} else if (gt.getMemo().equals("3")) {
					dynaBean.set("model_3", gt.getModel());
					dynaBean.set("fj_3", gt.getFj_url());
					dynaBean.set("fj_name_3", gt.getFj_name());
				}
			}
		} else {
			dynaBean.set("tj_model_is_empty", true);
		}

		GcxmProjAuditNode pn = new GcxmProjAuditNode();
		pn.setProj_id(Long.valueOf(id));
		pn = super.getFacade().getGcxmProjAuditNodeService().getGcxmProjAuditNode(pn);

		GcxmAuditProcessNode gn = new GcxmAuditProcessNode();
		gn.setProcess_id(pn.getProcess_id());
		gn.setAudit_role_id(pn.getAudit_role_id());
		gn = super.getFacade().getGcxmAuditProcessNodeService().getGcxmAuditProcessNode(gn);

		GcxmAuditProcessNode node = new GcxmAuditProcessNode();
		node.setProcess_id(gn.getProcess_id());
		node.getMap().put("node_id_lt", gn.getId());
		List<GcxmAuditProcessNode> nodeList = super.getFacade().getGcxmAuditProcessNodeService()
				.getGcxmAuditProcessNodeList(node);
		request.setAttribute("nodeList", nodeList);

		KonkaDept kd = new KonkaDept();
		kd.setDept_id(entity.getFgs_id());
		kd = super.getFacade().getKonkaDeptService().getKonkaDept(kd);

		dynaBean.set("fgs_dept_name", kd.getDept_name());
		dynaBean.set("gcxm_sn", entity.getProj_code());
		dynaBean.set("user_name", entity.getCreate_name());
		dynaBean.set("mod_id", mod_id);

		return new ActionForward("/../manager/admin/GcxmProj/audit.jsp");

	}

	public ActionForward auditSave(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String id = (String) dynaBean.get("id");
		String node_id = (String) dynaBean.get("node_id");
		String model_1 = (String) dynaBean.get("model_1");
		String model_2 = (String) dynaBean.get("model_2");
		String model_3 = (String) dynaBean.get("model_3");
		String is_tj = (String) dynaBean.get("is_tj");

		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		if (null == peProdUser) {
			return null;
		}

		GcxmProjAuditNode pn = new GcxmProjAuditNode();
		pn.setProj_id(Long.valueOf(id));
		pn = super.getFacade().getGcxmProjAuditNodeService().getGcxmProjAuditNode(pn);

		PeRoleUser rUser = new PeRoleUser();
		rUser.setUser_id(peProdUser.getId());
		List<PeRoleUser> roleUserList = getFacade().getPeRoleUserService().getPeRoleUserList(rUser);

		boolean is_audit = false;
		if (null != roleUserList && roleUserList.size() > 0) {
			for (PeRoleUser pu : roleUserList) {
				if (pu.getRole_id().intValue() == pn.getAudit_role_id().intValue()) {
					is_audit = true;
					break;
				}
			}
		}

		if (!is_audit) {
			return null;
		}

		GcxmProjAudit entity = new GcxmProjAudit();
		super.copyProperties(entity, form);

		List<UploadFile> uploadFileList = super.uploadFile(form, true, 0, new int[] { 240 });

		List<Attachment> filesAttachment1List = new ArrayList<Attachment>();
		List<Attachment> filesAttachment2List = new ArrayList<Attachment>();
		List<Attachment> filesAttachment3List = new ArrayList<Attachment>();

		if (null != uploadFileList && uploadFileList.size() > 0) {
			for (UploadFile uploadFile : uploadFileList) {
				if ("file_1".endsWith(uploadFile.getFormName())) {
					Attachment filesAttachment = new Attachment();
					filesAttachment.setFile_name(uploadFile.getFileName());
					filesAttachment.setFile_type(uploadFile.getContentType());
					filesAttachment.setFile_size(new Integer(uploadFile.getFileSize()));
					filesAttachment.setSave_path(uploadFile.getFileSavePath());
					filesAttachment.setSave_name(uploadFile.getFileSaveName());
					filesAttachment.setDel_mark(new Short("0"));
					filesAttachment1List.add(filesAttachment);
				} else if ("file_2".endsWith(uploadFile.getFormName())) {
					Attachment filesAttachment = new Attachment();
					filesAttachment.setFile_name(uploadFile.getFileName());
					filesAttachment.setFile_type(uploadFile.getContentType());
					filesAttachment.setFile_size(new Integer(uploadFile.getFileSize()));
					filesAttachment.setSave_path(uploadFile.getFileSavePath());
					filesAttachment.setSave_name(uploadFile.getFileSaveName());
					filesAttachment.setDel_mark(new Short("0"));
					filesAttachment2List.add(filesAttachment);
				} else if ("file_3".endsWith(uploadFile.getFormName())) {
					Attachment filesAttachment = new Attachment();
					filesAttachment.setFile_name(uploadFile.getFileName());
					filesAttachment.setFile_type(uploadFile.getContentType());
					filesAttachment.setFile_size(new Integer(uploadFile.getFileSize()));
					filesAttachment.setSave_path(uploadFile.getFileSavePath());
					filesAttachment.setSave_name(uploadFile.getFileSaveName());
					filesAttachment.setDel_mark(new Short("0"));
					filesAttachment3List.add(filesAttachment);
				}

			}
			entity.getMap().put("filesAttachment1List", filesAttachment1List);
			entity.getMap().put("filesAttachment2List", filesAttachment2List);
			entity.getMap().put("filesAttachment3List", filesAttachment3List);
		}

		entity.getMap().put("model_1", model_1);
		entity.getMap().put("model_2", model_2);
		entity.getMap().put("model_3", model_3);

		entity.getMap().put("is_tj", is_tj);

		entity.getMap().put("proj_id", id);
		entity.getMap().put("node_id", node_id);
		entity.setAudit_date(new Date());
		entity.setAudit_model(peProdUser.getUser_name());
		entity.setAudit_type(1001L);
		entity.setAudit_user_id(new BigDecimal(peProdUser.getId()));
		entity.setProj_id(Long.valueOf(id));

		if (StringUtils.isNotBlank(id)) {
			super.getFacade().getGcxmProjAuditService().createGcxmProjAudit(entity);
			saveMessage(request, "entity.inserted");
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

	public ActionForward chooseModel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String md_name_like = (String) dynaBean.get("md_name_like");
		String pd_desc_like = (String) dynaBean.get("pd_desc_like");
		String title = (String) dynaBean.get("title");

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		PePdModel entity = new PePdModel();
		entity.setIs_del(0);

		if (StringUtils.isNotBlank(md_name_like)) {
			entity.getMap().put("md_name_like", md_name_like);
		}

		if (StringUtils.isNotBlank(pd_desc_like)) {
			entity.getMap().put("pd_desc_like", pd_desc_like);
		}

		Long recordCount = super.getFacade().getPePdModelService().getPePdModelCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<PePdModel> entityList = super.getFacade().getPePdModelService().getPePdModelPaginatedList(entity);

		dynaBean.set("title", title);
		request.setAttribute("entityList", entityList);

		return new ActionForward("/../manager/admin/GcxmProj/chooseModel.jsp");
	}

	public ActionForward deleteFile(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;

		String id = (String) dynaBean.get("id");

		if (StringUtils.isNotBlank(id) && GenericValidator.isLong(id)) {
			Attachment entity = new Attachment();
			entity.setId(new Long(id));
			getFacade().getAttachmentService().removeAttachment(entity);
			saveMessage(request, "entity.deleted");
		}

		super.renderText(response, "success");
		return null;
	}

	public String GetRandomNumber() {

		// 使用SET以此保证写入的数据不重复
		List<String> list = new ArrayList<String>();
		String[] arg = new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };//
		// 随机数
		Random random = new Random();
		int ss = 0;
		for (int i = 0; i < arg.length + 1; i++) {
			while (list.size() < 3) {
				// nextInt返回一个伪随机数，它是取自此随机数生成器序列的、在 0（包括）
				// 和指定值（不包括）之间均匀分布的 int 值。
				ss = random.nextInt(arg.length);
				list.add(arg[ss]);
			}
		}

		String code = "";
		for (String string : list) {
			code = code + string;
		}
		//System.out.println(code);// 0123
		return code;
	}

	/*
	 * public static void main(String[] args) { GcxmProjAction gp=new GcxmProjAction(); gp.GetRandomNumber(); }
	 */

}
