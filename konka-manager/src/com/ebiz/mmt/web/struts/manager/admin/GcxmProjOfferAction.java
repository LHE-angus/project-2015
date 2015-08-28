package com.ebiz.mmt.web.struts.manager.admin;

import java.math.BigDecimal;
import java.util.ArrayList;
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
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.util.EncryptUtils;
import com.ebiz.ssi.web.struts.bean.Pager;
import com.ebiz.ssi.web.struts.bean.UploadFile;

public class GcxmProjOfferAction extends BaseAction {
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
		String offer_offer_model_like = (String) dynaBean.get("offer_offer_model_like");
		String fgs_id = (String) dynaBean.get("fgs_id");
		String offer_create_date_start = (String) dynaBean.get("offer_create_date_start");
		String offer_create_date_end = (String) dynaBean.get("offer_create_date_end");
		String offer_create_name_like = (String) dynaBean.get("offer_create_name_like");
		String offer_info_state = (String) dynaBean.get("offer_info_state");
		String is_validate = (String) dynaBean.get("is_validate");
		String delivery_date_start = (String) dynaBean.get("delivery_date_start");
		String delivery_date_end = (String) dynaBean.get("delivery_date_end");
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

		KonkaDept fgs_dept = super.getSuperiorForDeptType(userInfo.getDept_id(), 3);
		if (fgs_dept != null) {
			dynaBean.set("fgs_dept_name", fgs_dept.getDept_name());
		}

		if (StringUtils.isNotBlank(proj_type)) {
			entity.setProj_type(Long.valueOf(proj_type));
		}
		if (StringUtils.isNotBlank(proj_code_like)) {
			entity.getMap().put("proj_code_like", proj_code_like);
		}
		if (StringUtils.isNotBlank(proj_name_like)) {
			entity.getMap().put("proj_name_like", proj_name_like);
		}
		if (StringUtils.isNotBlank(offer_create_date_start)) {
			entity.getMap().put("offer_create_date_start", offer_create_date_start + " 00:00:00");
		}
		if (StringUtils.isNotBlank(offer_create_date_end)) {
			entity.getMap().put("offer_create_date_end", offer_create_date_end + " 23:59:59");
		}
		if (StringUtils.isNotBlank(offer_create_name_like)) {
			entity.getMap().put("offer_create_name_like", offer_create_name_like);
		}
		if (StringUtils.isNotBlank(offer_info_state)) {
			if (offer_info_state.equals("-2")) {
				entity.getMap().put("info_state_is_null", true);
			} else {
				entity.getMap().put("offer_info_state", offer_info_state);
			}
		}
		if (StringUtils.isNotBlank(fgs_id)) {
			entity.setFgs_id(Long.valueOf(fgs_id));
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
		if (StringUtils.isNotBlank(delivery_date_start)) {
			entity.getMap().put("delivery_date_start", delivery_date_start + " 00:00:00");
		}
		if (StringUtils.isNotBlank(delivery_date_end)) {
			entity.getMap().put("delivery_date_end", delivery_date_end + " 23:59:59");
		}
		if (StringUtils.isNotBlank(offer_offer_model_like)) {
			entity.getMap().put("offer_offer_model_like", offer_offer_model_like);
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

		entity.setInfo_state(1);// 已审核完成的
		entity.setDel_mark(0);
		//String offer_del_mark = "0";
		//entity.getMap().put("offer_del_mark", offer_del_mark);

		Long recordCount = getFacade().getGcxmProjService().getGcxmProjCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<GcxmProj> entityList = getFacade().getGcxmProjService().getGcxmProjPaginatedList(entity);
		for (GcxmProj gcxmProj : entityList) {
			// 获取3个model
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
			// 获取分公司
			KonkaDept kd = new KonkaDept();
			kd.setDept_id(gcxmProj.getFgs_id());
			kd = super.getFacade().getKonkaDeptService().getKonkaDept(kd);
			if (kd != null) {
				gcxmProj.getMap().put("dept_name", kd.getDept_name());
			}
			// 权限
			boolean is_audit = false;
			if (gcxmProj.getGcxmProjOffer() != null && gcxmProj.getGcxmProjOffer().getId() != null) {
				GcxmProjAuditNode pn = new GcxmProjAuditNode();
				pn.setProj_id(gcxmProj.getGcxmProjOffer().getId());
				pn = super.getFacade().getGcxmProjAuditNodeService().getGcxmProjAuditNode(pn);
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

		super.getModPopeDom(form, request);
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String mod_id = (String) dynaBean.get("mod_id");
		String proj_type = (String) dynaBean.get("proj_type");
		String proj_code_like = (String) dynaBean.get("proj_code_like");
		String proj_name_like = (String) dynaBean.get("proj_name_like");
		String offer_offer_model_like = (String) dynaBean.get("offer_offer_model_like");
		String fgs_id = (String) dynaBean.get("fgs_id");
		String offer_create_date_start = (String) dynaBean.get("offer_create_date_start");
		String offer_create_date_end = (String) dynaBean.get("offer_create_date_end");
		String offer_create_name_like = (String) dynaBean.get("offer_create_name_like");
		String offer_info_state = (String) dynaBean.get("offer_info_state");
		String is_validate = (String) dynaBean.get("is_validate");
		String delivery_date_start = (String) dynaBean.get("delivery_date_start");
		String delivery_date_end = (String) dynaBean.get("delivery_date_end");
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

		KonkaDept fgs_dept = super.getSuperiorForDeptType(userInfo.getDept_id(), 3);
		if (fgs_dept != null) {
			dynaBean.set("fgs_dept_name", fgs_dept.getDept_name());
		}

		if (StringUtils.isNotBlank(proj_type)) {
			entity.setProj_type(Long.valueOf(proj_type));
		}
		if (StringUtils.isNotBlank(proj_code_like)) {
			entity.getMap().put("proj_code_like", proj_code_like);
		}
		if (StringUtils.isNotBlank(proj_name_like)) {
			entity.getMap().put("proj_name_like", proj_name_like);
		}
		if (StringUtils.isNotBlank(offer_create_date_start)) {
			entity.getMap().put("offer_create_date_start", offer_create_date_start + " 00:00:00");
		}
		if (StringUtils.isNotBlank(offer_create_date_end)) {
			entity.getMap().put("offer_create_date_end", offer_create_date_end + " 23:59:59");
		}
		if (StringUtils.isNotBlank(offer_create_name_like)) {
			entity.getMap().put("offer_create_name_like", offer_create_name_like);
		}
		if (StringUtils.isNotBlank(offer_info_state)) {
			if (offer_info_state.equals("-2")) {
				entity.getMap().put("info_state_is_null", true);
			} else {
				entity.getMap().put("offer_info_state", offer_info_state);
			}
		}
		if (StringUtils.isNotBlank(fgs_id)) {
			entity.setFgs_id(Long.valueOf(fgs_id));
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
		if (StringUtils.isNotBlank(delivery_date_start)) {
			entity.getMap().put("delivery_date_start", delivery_date_start + " 00:00:00");
		}
		if (StringUtils.isNotBlank(delivery_date_end)) {
			entity.getMap().put("delivery_date_end", delivery_date_end + " 23:59:59");
		}
		if (StringUtils.isNotBlank(offer_offer_model_like)) {
			entity.getMap().put("offer_offer_model_like", offer_offer_model_like);
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

		entity.setInfo_state(1);// 已审核完成的
		entity.setDel_mark(0);
		//String offer_del_mark = "0";
		//entity.getMap().put("offer_del_mark", offer_del_mark);

		Long recordCount = getFacade().getGcxmProjService().getGcxmProjCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<GcxmProj> entityList = getFacade().getGcxmProjService().getGcxmProjPaginatedList(entity);
		for (GcxmProj gcxmProj : entityList) {
			// 获取3个model
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
			// 获取分公司
			KonkaDept kd = new KonkaDept();
			kd.setDept_id(gcxmProj.getFgs_id());
			kd = super.getFacade().getKonkaDeptService().getKonkaDept(kd);
			if (kd != null) {
				gcxmProj.getMap().put("dept_name", kd.getDept_name());
			}
			// 权限
			boolean is_audit = false;
			if (gcxmProj.getGcxmProjOffer() != null && gcxmProj.getGcxmProjOffer().getId() != null) {
				GcxmProjAuditNode pn = new GcxmProjAuditNode();
				pn.setProj_id(gcxmProj.getGcxmProjOffer().getId());
				pn = super.getFacade().getGcxmProjAuditNodeService().getGcxmProjAuditNode(pn);
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

		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename=" + EncryptUtils.encodingFileName("工程项目报价")
				+ ".xls");
		return new ActionForward("/../manager/admin/GcxmProjOffer/sheet.jsp");

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
		String process_id = (String) dynaBean.get("process_id");

		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		if (null == peProdUser) {
			return null;
		}

		GcxmProjOffer entity = new GcxmProjOffer();
		super.copyProperties(entity, form);

		KonkaDept konkaDept = new KonkaDept();
		konkaDept.setDept_id(peProdUser.getDept_id());
		konkaDept = super.getFacade().getKonkaDeptService().getKonkaDept(konkaDept);

		// 分公司取得
		KonkaDept fgs_dept = null;
		if (null != konkaDept.getDept_type() && konkaDept.getDept_type() > 2) {
			fgs_dept = super.getSuperiorForDeptType(peProdUser.getDept_id(), 3);

		}

		GcxmProjOffer gf = new GcxmProjOffer();
		gf.setProj_id(id);
		gf = super.getFacade().getGcxmProjOfferService().getGcxmProjOffer(gf);

		List<UploadFile> uploadFileList = super.uploadFile(form, true, 0, new int[] { 240 });
		List<Attachment> filesAttachmentList = new ArrayList<Attachment>();

		if (null != uploadFileList && uploadFileList.size() > 0) {
			for (UploadFile uploadFile : uploadFileList) {
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

		if (gf == null) {

			entity.getMap().put("process_id", process_id);
			entity.setProj_id(id);
			// entity.setCreate_date(new Date());
			entity.setCreate_name(peProdUser.getUser_name());
			entity.setCreate_user_id(peProdUser.getId());

			entity.setDel_mark(0);// 0未删除 1已删除
			// entity.setFgs_id(fgs_dept==null?0L:fgs_dept.getDept_id());

			super.getFacade().getGcxmProjOfferService().createGcxmProjOffer(entity);

			saveMessage(request, "entity.inserted");
		} else {

			entity.setId(gf.getId());
			super.getFacade().getGcxmProjOfferService().modifyGcxmProjOffer(entity);
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

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String[] pks = (String[]) dynaBean.get("pks");
		String mod_id = (String) dynaBean.get("mod_id");

		if (!StringUtils.isBlank(id) && GenericValidator.isLong(id)) {

			GcxmProjOffer pj = new GcxmProjOffer();
			pj.setProj_id(id);
			pj = super.getFacade().getGcxmProjOfferService().getGcxmProjOffer(pj);

			if (pj == null) {
				super.renderJavaScript(response, "window.onload=function(){alert('该项目还没进行报价，不能删除！');history.back();}");
				return null;
			}

			GcxmProjOffer entity = new GcxmProjOffer();
			entity.setId(pj.getId());
			entity.setDel_mark(1);
			getFacade().getGcxmProjOfferService().modifyGcxmProjOffer(entity);
			saveMessage(request, "entity.deleted");
		} else if (!ArrayUtils.isEmpty(pks)) {
			Boolean is_auth = false;
			for (String string : pks) {
				GcxmProjOffer pj = new GcxmProjOffer();
				pj.setProj_id(string);
				pj = super.getFacade().getGcxmProjOfferService().getGcxmProjOffer(pj);
				if (pj == null) {
					is_auth = true;
					break;
				}
			}
			if (is_auth) {
				super.renderJavaScript(response,
						"window.onload=function(){alert('选中的项目存在没有报价的，不能删除！');history.back();}");
				return null;
			}

			for (String xx : pks) {
				GcxmProjOffer entity = new GcxmProjOffer();
				GcxmProjOffer pj = new GcxmProjOffer();
				pj.setProj_id(xx);
				pj = super.getFacade().getGcxmProjOfferService().getGcxmProjOffer(pj);

				entity.setId(pj.getId());
				entity.setDel_mark(1);
				getFacade().getGcxmProjOfferService().modifyGcxmProjOffer(entity);
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

		GcxmProjOffer pj = new GcxmProjOffer();
		pj.setProj_id(id);
		pj = super.getFacade().getGcxmProjOfferService().getGcxmProjOffer(pj);

		if (pj == null) {
			super.renderJavaScript(response, "window.onload=function(){alert('该项目还没进行报价，不能无效！');history.back();}");
			return null;
		}

		if (!StringUtils.isBlank(id) && GenericValidator.isLong(id)) {
			//报价的单据状态改为无效
			GcxmProjOffer offer = new GcxmProjOffer();
			offer.setId(pj.getId());
			offer.setIs_validate(1);
			getFacade().getGcxmProjOfferService().modifyGcxmProjOffer(offer);
			//上报的单据状态改为无效
			GcxmProj entity = new GcxmProj();
			entity.setId(new Long(pj.getProj_id()));
			entity.setIs_validate(1);
			getFacade().getGcxmProjService().modifyGcxmProj(entity);
			//结果的单据状态改为无效
			GcxmProjSupply supply = new GcxmProjSupply();
			supply.setProj_id(pj.getProj_id());
			supply = getFacade().getGcxmProjSupplyService().getGcxmProjSupply(supply);
			supply.setId(supply.getId());
			supply.setIs_validate(1);
			getFacade().getGcxmProjSupplyService().modifyGcxmProjSupply(supply);
			
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

		GcxmProjOffer pj = new GcxmProjOffer();
		pj.setProj_id(id);
		pj = super.getFacade().getGcxmProjOfferService().getGcxmProjOffer(pj);

		if (pj == null) {
			super.renderJavaScript(response, "window.onload=function(){alert('该项目还没进行报价，不能无效！');history.back();}");
			return null;
		}

		if (!StringUtils.isBlank(id) && GenericValidator.isLong(id)) {
			GcxmProjOffer entity = new GcxmProjOffer();
			entity.getMap().put("peProdUser", peProdUser);
			entity.setId(pj.getId());
			entity.setInfo_state(-1L);
			super.getFacade().getGcxmProjOfferService().modifyGcxmProjOfferForCh(entity);
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
		saveToken(request);

		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;

		String id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id");

		PeProdUser userInfo = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
		
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
				} else if (pu.getRole_id().intValue() >= 30 && pu.getRole_id().intValue() <= 188) {
					fgs = true;// 分公司
					break;
				} else {
					qt = true;
				}
			}
		}

		if (!GenericValidator.isLong(id)) {
			saveError(request, "errors.long", id);
			return mapping.findForward("list");
		}

		GcxmProj entity = new GcxmProj();
		entity.setId(Long.valueOf(id));
		entity = super.getFacade().getGcxmProjService().getGcxmProj(entity);

		super.copyProperties(form, entity);

		GcxmProjOffer of = new GcxmProjOffer();
		of.setProj_id(id);
		of = super.getFacade().getGcxmProjOfferService().getGcxmProjOffer(of);

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
		dynaBean.set("user_name", of == null ? userInfo.getUser_name() : of.getCreate_name());

		GcxmProjOffer gf = new GcxmProjOffer();
		gf.setProj_id(id);
		gf = super.getFacade().getGcxmProjOfferService().getGcxmProjOffer(gf);

		if (gf != null) {
			// 附件
			Attachment attachment = new Attachment();
			attachment.setLink_id(gf.getId());
			attachment.setLink_tab("GCXM_PROJ_OFFER");
			attachment.setDel_mark(new Short("0"));
			request.setAttribute("attachmentList", getFacade().getAttachmentService().getAttachmentList(attachment));
			dynaBean.set("offer_id", gf.getId());
		}

		List<String> modelList = new ArrayList<String>();

		GcxmProjTj pj = new GcxmProjTj();
		pj.setProj_id(id);
		List<GcxmProjTj> pjList = super.getFacade().getGcxmProjTjService().getGcxmProjTjList(pj);
		if (pjList != null && pjList.size() > 0) {
			for (GcxmProjTj gt : pjList) {
				if (gt.getMemo().equals("1")) {
					dynaBean.set("model_1", gt.getModel());
					dynaBean.set("fj_1", gt.getFj_url());
					dynaBean.set("fj_name_1", gt.getFj_name());
					modelList.add(gt.getModel());

				} else if (gt.getMemo().equals("2")) {
					dynaBean.set("model_2", gt.getModel());
					dynaBean.set("fj_2", gt.getFj_url());
					dynaBean.set("fj_name_2", gt.getFj_name());
					modelList.add(gt.getModel());
				} else if (gt.getMemo().equals("3")) {
					dynaBean.set("model_3", gt.getModel());
					dynaBean.set("fj_3", gt.getFj_url());
					dynaBean.set("fj_name_3", gt.getFj_name());
					modelList.add(gt.getModel());
				}
			}
		}

		String models = StringUtils.join(modelList, ",");
		dynaBean.set("models", models);

		GcxmAuditProcess gp = new GcxmAuditProcess();
		gp.setIs_del(0);
		gp.setAudit_type(1002L);
		// 根据当前登录人获取创建人分公司相同的流程
		if(zb){
			
		} else if (fgs) {
			kd = new KonkaDept();
			kd = getKonkaDeptForFgs(userInfo.getDept_id());
			
			List<Long> deptInList=new ArrayList<Long>();
			deptInList.add(0L);
			deptInList.add(kd.getDept_id());
			gp.getMap().put("dept_id_0", deptInList);
		} else {
				String msg = super.getMessage(request, "您没有查看权限");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
		}
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
		Date now = new Date();
		dynaBean.set("delivery_date", of == null ? null : of.getDelivery_date());
		dynaBean.set("create_date", of == null ? now : of.getCreate_date());
		dynaBean.set("mod_id", mod_id);

		return mapping.findForward("input");
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

		GcxmProjOffer offer = new GcxmProjOffer();
		offer.setProj_id(id);
		offer = super.getFacade().getGcxmProjOfferService().getGcxmProjOffer(offer);
		super.copyProperties(form, entity);

		// 附件
		Attachment attachment = new Attachment();
		attachment.setLink_id(offer.getId());
		attachment.setLink_tab("GCXM_PROJ_OFFER");
		attachment.setDel_mark(new Short("0"));
		request.setAttribute("attachmentList", getFacade().getAttachmentService().getAttachmentList(attachment));
		
		List<String> modelList = new ArrayList<String>();

		GcxmProjTj pj = new GcxmProjTj();
		pj.setProj_id(id);
		List<GcxmProjTj> pjList = super.getFacade().getGcxmProjTjService().getGcxmProjTjList(pj);
		if (pjList != null && pjList.size() > 0) {
			for (GcxmProjTj gt : pjList) {
				if (gt.getMemo().equals("1")) {
					dynaBean.set("model_1", gt.getModel());
					dynaBean.set("fj_1", gt.getFj_url());
					dynaBean.set("fj_name_1", gt.getFj_name());
					modelList.add(gt.getModel());

				} else if (gt.getMemo().equals("2")) {
					dynaBean.set("model_2", gt.getModel());
					dynaBean.set("fj_2", gt.getFj_url());
					dynaBean.set("fj_name_2", gt.getFj_name());
					modelList.add(gt.getModel());
				} else if (gt.getMemo().equals("3")) {
					dynaBean.set("model_3", gt.getModel());
					dynaBean.set("fj_3", gt.getFj_url());
					dynaBean.set("fj_name_3", gt.getFj_name());
					modelList.add(gt.getModel());
				}
			}
		}

		String models = StringUtils.join(modelList, ",");
		dynaBean.set("models", models);

		GcxmProjAudit pt = new GcxmProjAudit();
		pt.setProj_id(offer.getId());
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

		GcxmProjAuditNode pn = new GcxmProjAuditNode();
		pn.setProj_id(offer.getId());
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

		return new ActionForward("/../manager/admin/GcxmProjOffer/audit.jsp");

	}

	public ActionForward auditSave(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String id = (String) dynaBean.get("id");
		String node_id = (String) dynaBean.get("node_id");

		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		if (null == peProdUser) {
			return null;
		}

		GcxmProjOffer pj = new GcxmProjOffer();
		pj.setProj_id(id);
		pj = super.getFacade().getGcxmProjOfferService().getGcxmProjOffer(pj);

		if (pj == null) {
			super.renderJavaScript(response, "window.onload=function(){alert('该项目还没进行报价，不能无效！');history.back();}");
			return null;
		}

		GcxmProjAuditNode pn = new GcxmProjAuditNode();
		pn.setProj_id(pj.getId());
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

		entity.getMap().put("proj_id", pj.getId());
		entity.getMap().put("node_id", node_id);
		entity.setAudit_date(new Date());
		entity.setAudit_model(peProdUser.getUser_name());
		entity.setAudit_type(1002L);
		entity.setAudit_user_id(new BigDecimal(peProdUser.getId()));
		entity.setProj_id(pj.getId());

		if (StringUtils.isNotBlank(id)) {
			super.getFacade().getGcxmProjAuditService().createGcxmProjOfferAudit(entity);
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

	/*
	 * public static void main(String[] args) { GcxmProjAction gp=new GcxmProjAction(); gp.GetRandomNumber(); }
	 */

}
