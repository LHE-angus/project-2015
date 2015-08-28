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

public class GcxmProjQueryAction extends BaseAction {
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
		String info_state = (String) dynaBean.get("info_state");
		String is_validate = (String) dynaBean.get("is_validate");
		String delivery_date_start = (String) dynaBean.get("delivery_date_start");
		String delivery_date_end = (String) dynaBean.get("delivery_date_end");
		String supply_model_like = (String) dynaBean.get("supply_model_like");
		String is_win = (String) dynaBean.get("is_win");
		String offer_date_start = (String) dynaBean.get("offer_date_start");
		String offer_date_end = (String) dynaBean.get("offer_date_end");
		String size_like = (String) dynaBean.get("size_like");
		String buyer_like = (String) dynaBean.get("buyer_like");

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

		if (StringUtils.isNotBlank(proj_type)) {
			entity.setProj_type(Long.valueOf(proj_type));
		}
		if (StringUtils.isNotBlank(is_win)) {
			if(is_win.equals("3")){
				entity.getMap().put("is_win_eq_null", true);
			}else{
				entity.setIs_win(Integer.valueOf(is_win));
			}
		}
		if (StringUtils.isNotBlank(supply_model_like)) {
			entity.getMap().put("supply_model_like", supply_model_like);
		}
		if (StringUtils.isNotBlank(proj_name_like)) {
			entity.getMap().put("proj_name_like", proj_name_like);
		}
		if (StringUtils.isNotBlank(proj_code_like)) {
			entity.getMap().put("proj_code_like", proj_code_like);
		}
		if (StringUtils.isNotBlank(buyer_like)) {
			entity.getMap().put("buyer_like", buyer_like);
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

		entity.setDel_mark(0);
		//entity.getMap().put("info_state_is_not_null", true);
		//entity.getMap().put("info_state_is_1_or_0", true);
		//entity.getMap().put("is_validate_true", true);
		//entity.getMap().put("info_state_in_1_and_0", true);

		Long recordCount = getFacade().getGcxmProjService().getGcxmProjCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<GcxmProj> entityList = getFacade().getGcxmProjService().getGcxmProjPaginatedList(entity);
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

		}

		request.setAttribute("entityList", entityList);
		dynaBean.set("mod_id", mod_id);

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
		String info_state = (String) dynaBean.get("info_state");
		String is_validate = (String) dynaBean.get("is_validate");
		String delivery_date_start = (String) dynaBean.get("delivery_date_start");
		String delivery_date_end = (String) dynaBean.get("delivery_date_end");
		String supply_model_like = (String) dynaBean.get("supply_model_like");
		String is_win = (String) dynaBean.get("is_win");
		String offer_date_start = (String) dynaBean.get("offer_date_start");
		String offer_date_end = (String) dynaBean.get("offer_date_end");
		String size_like = (String) dynaBean.get("size_like");
		String buyer_like = (String) dynaBean.get("buyer_like");

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

		if (StringUtils.isNotBlank(proj_type)) {
			entity.setProj_type(Long.valueOf(proj_type));
		}
		if (StringUtils.isNotBlank(is_win)) {
			if(is_win.equals("3")){
				entity.getMap().put("is_win_eq_null", true);
			}else{
				entity.setIs_win(Integer.valueOf(is_win));
			}
		}
		if (StringUtils.isNotBlank(supply_model_like)) {
			entity.getMap().put("supply_model_like", supply_model_like);
		}
		if (StringUtils.isNotBlank(proj_name_like)) {
			entity.getMap().put("proj_name_like", proj_name_like);
		}
		if (StringUtils.isNotBlank(proj_code_like)) {
			entity.getMap().put("proj_code_like", proj_code_like);
		}
		if (StringUtils.isNotBlank(buyer_like)) {
			entity.getMap().put("buyer_like", buyer_like);
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

		entity.setDel_mark(0);
		//entity.getMap().put("info_state_is_not_null", true);
		//entity.getMap().put("info_state_is_1_or_0", true);
		//entity.getMap().put("is_validate_true", true);
		//entity.getMap().put("info_state_in_1_and_0", true);

		Long recordCount = getFacade().getGcxmProjService().getGcxmProjCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<GcxmProj> entityList = getFacade().getGcxmProjService().getGcxmProjPaginatedList(entity);
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

		}

		request.setAttribute("entityList", entityList);
		dynaBean.set("mod_id", mod_id);

		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename=" + EncryptUtils.encodingFileName("项目查询")
				+ ".xls");
		return new ActionForward("/../manager/admin/GcxmProjQuery/sheet.jsp");

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

	/*
	 * public static void main(String[] args) { GcxmProjAction gp=new GcxmProjAction(); gp.GetRandomNumber(); }
	 */

}
