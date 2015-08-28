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

import com.ebiz.mmt.domain.Attachment;
import com.ebiz.mmt.domain.GcxmProj;
import com.ebiz.mmt.domain.GcxmProjAudit;
import com.ebiz.mmt.domain.GcxmProjCompet;
import com.ebiz.mmt.domain.GcxmProjOffer;
import com.ebiz.mmt.domain.GcxmProjTj;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.util.EncryptUtils;
import com.ebiz.ssi.web.struts.bean.Pager;

public class GcxmProjCompetAction extends BaseAction {
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
		String brand_name_like = (String) dynaBean.get("brand_name_like");
		String fgs_id = (String) dynaBean.get("fgs_id");
		String supply_create_date_start = (String) dynaBean.get("supply_create_date_start");
		String supply_create_date_end = (String) dynaBean.get("supply_create_date_end");
		String create_name_like = (String) dynaBean.get("create_name_like");
		String supply_info_state = (String) dynaBean.get("supply_info_state");
		String compet_model_like = (String) dynaBean.get("compet_model_like");

		PeProdUser userInfo = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
		if (null == userInfo) {
			String msg = super.getMessage(request, "user.session.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		GcxmProjCompet entity = new GcxmProjCompet();

		KonkaDept konkaDept = new KonkaDept();
		konkaDept.setDept_id(userInfo.getDept_id());
		konkaDept = super.getFacade().getKonkaDeptService().getKonkaDept(konkaDept);

		// 分公司取得
		KonkaDept fgs_dept = super.getSuperiorForDeptType(userInfo.getDept_id(), 3);
		if (fgs_dept != null) {
			dynaBean.set("fgs_dept_name", fgs_dept.getDept_name());
		}

		if (StringUtils.isNotBlank(proj_type)) {
			entity.getMap().put("proj_type", proj_type);
		}
		if (StringUtils.isNotBlank(compet_model_like)) {
			entity.getMap().put("compet_model_like", compet_model_like);
		}
		if (StringUtils.isNotBlank(proj_code_like)) {
			entity.getMap().put("proj_code_like", proj_code_like);
		}
		if (StringUtils.isNotBlank(brand_name_like)) {
			entity.getMap().put("brand_name_like", brand_name_like);
		}
		if (StringUtils.isNotBlank(proj_name_like)) {
			entity.getMap().put("proj_name_like", proj_name_like);
		}
		if (StringUtils.isNotBlank(supply_create_date_start)) {
			entity.getMap().put("supply_create_date_start", supply_create_date_start + " 00:00:00");
		}
		if (StringUtils.isNotBlank(supply_create_date_end)) {
			entity.getMap().put("supply_create_date_end", supply_create_date_end + " 23:59:59");
		}
		if (StringUtils.isNotBlank(create_name_like)) {
			entity.getMap().put("create_name_like", create_name_like);
		}
		if (StringUtils.isNotBlank(supply_info_state)) {
			if (supply_info_state.equals("-2")) {
				entity.getMap().put("supply_state_is_null", true);
			} else {
				entity.getMap().put("supply_info_state", supply_info_state);
			}
		}
		if (StringUtils.isNotBlank(fgs_id)) {
			entity.getMap().put("fgs_id", fgs_id);
		}

		/*boolean zb = false;
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
			entity.getMap().put("fgs_id", fgs_dept.getDept_id());
		} else if (qt) {
			entity.getMap().put("create_user_id", userInfo.getId());
		}*/

		Long recordCount = getFacade().getGcxmProjCompetService().getGcxmProjCompetForProjCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<GcxmProjCompet> entityList = getFacade().getGcxmProjCompetService().getGcxmProjCompetForProjPaginatedList(
				entity);

		request.setAttribute("entityList", entityList);
		dynaBean.set("mod_id", mod_id);
		
		//获取所有分公司
		KonkaDept kd=new KonkaDept();
		kd.setIs_del(0);
		kd.setDept_type(3);
		List<KonkaDept> sybDeptInfoList=super.getFacade().getKonkaDeptService().getKonkaDeptList(kd);

		request.setAttribute("sybDeptInfoList", sybDeptInfoList);

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
		String brand_name_like = (String) dynaBean.get("brand_name_like");
		String fgs_id = (String) dynaBean.get("fgs_id");
		String supply_create_date_start = (String) dynaBean.get("supply_create_date_start");
		String supply_create_date_end = (String) dynaBean.get("supply_create_date_end");
		String create_name_like = (String) dynaBean.get("create_name_like");
		String supply_info_state = (String) dynaBean.get("supply_info_state");
		String compet_model_like = (String) dynaBean.get("compet_model_like");

		PeProdUser userInfo = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
		if (null == userInfo) {
			String msg = super.getMessage(request, "user.session.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		GcxmProjCompet entity = new GcxmProjCompet();

		KonkaDept konkaDept = new KonkaDept();
		konkaDept.setDept_id(userInfo.getDept_id());
		konkaDept = super.getFacade().getKonkaDeptService().getKonkaDept(konkaDept);

		// 分公司取得
		KonkaDept fgs_dept = super.getSuperiorForDeptType(userInfo.getDept_id(), 3);
		if (fgs_dept != null) {
			dynaBean.set("fgs_dept_name", fgs_dept.getDept_name());
		}

		if (StringUtils.isNotBlank(proj_type)) {
			entity.getMap().put("proj_type", proj_type);
		}
		if (StringUtils.isNotBlank(compet_model_like)) {
			entity.getMap().put("compet_model_like", compet_model_like);
		}
		if (StringUtils.isNotBlank(proj_code_like)) {
			entity.getMap().put("proj_code_like", proj_code_like);
		}
		if (StringUtils.isNotBlank(brand_name_like)) {
			entity.getMap().put("brand_name_like", brand_name_like);
		}
		if (StringUtils.isNotBlank(proj_name_like)) {
			entity.getMap().put("proj_name_like", proj_name_like);
		}
		if (StringUtils.isNotBlank(supply_create_date_start)) {
			entity.getMap().put("supply_create_date_start", supply_create_date_start + " 00:00:00");
		}
		if (StringUtils.isNotBlank(supply_create_date_end)) {
			entity.getMap().put("supply_create_date_end", supply_create_date_end + " 23:59:59");
		}
		if (StringUtils.isNotBlank(create_name_like)) {
			entity.getMap().put("create_name_like", create_name_like);
		}
		if (StringUtils.isNotBlank(supply_info_state)) {
			if (supply_info_state.equals("-2")) {
				entity.getMap().put("supply_state_is_null", true);
			} else {
				entity.getMap().put("supply_info_state", supply_info_state);
			}
		}
		if (StringUtils.isNotBlank(fgs_id)) {
			entity.getMap().put("fgs_id", fgs_id);
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
			entity.getMap().put("fgs_id", fgs_dept.getDept_id());
		} else if (qt) {
			entity.getMap().put("create_user_id", userInfo.getId());
		}

		Long recordCount = getFacade().getGcxmProjCompetService().getGcxmProjCompetForProjCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<GcxmProjCompet> entityList = getFacade().getGcxmProjCompetService().getGcxmProjCompetForProjPaginatedList(
				entity);

		request.setAttribute("entityList", entityList);
		dynaBean.set("mod_id", mod_id);

		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename=" + EncryptUtils.encodingFileName("竞品价格查询")
				+ ".xls");
		return new ActionForward("/../manager/admin/GcxmProjCompet/sheet.jsp");

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

		GcxmProjCompet gct = new GcxmProjCompet();
		gct.setId(Long.valueOf(id));
		gct = super.getFacade().getGcxmProjCompetService().getGcxmProjCompet(gct);

		if (gct == null) {
			return null;
		}

		id = gct.getProj_id().toString();

		GcxmProj entity = new GcxmProj();
		entity.setId(gct.getProj_id());
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

}
