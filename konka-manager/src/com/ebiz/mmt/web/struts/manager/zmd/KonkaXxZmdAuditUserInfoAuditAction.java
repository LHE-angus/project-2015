package com.ebiz.mmt.web.struts.manager.zmd;

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

import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaPeAttachments;
import com.ebiz.mmt.domain.KonkaXxAuditNote;
import com.ebiz.mmt.domain.KonkaXxZmd;
import com.ebiz.mmt.domain.KonkaXxZmdAuditUserHis;
import com.ebiz.mmt.domain.KonkaXxZmdAuditUserInfo;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Hu Hao
 * @version 2013-03-15
 */

public class KonkaXxZmdAuditUserInfoAuditAction extends BaseZmdAction {

	protected ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);

		PeProdUser user_id = super.getSessionUserInfo(request);

		// 判断是否是专卖店总部管理员和工作人员的角色
		// PeRoleUser _peRoleUser = new PeRoleUser();
		// _peRoleUser.setUser_id(user_id.getId());
		// List<PeRoleUser> peRoleUserList =
		// this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);
		List<PeRoleUser> peRoleUserList = getPeRoleList(user_id.getId());
		String role_ids = ",";
		boolean role_id_gt_400 = false;// 分公司人员
		Boolean role_id_btw_300_400 = false;// 分公司人员
		if (peRoleUserList.size() > 0) {
			for (PeRoleUser temp : peRoleUserList) {
				role_ids = role_ids + temp.getRole_id() + ",";
				if (temp.getRole_id() < 400) {
					role_id_gt_400 = true;
				}
				if ((temp.getRole_id() >= 300 && temp.getRole_id() < 400)
						|| (temp.getRole_id() >= 30 && temp.getRole_id() < 40)) {
					role_id_btw_300_400 = true;
				}
			}
		}
		if (!role_id_gt_400) {
			String msg = super.getMessage(request, "popedom.check.invalid");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		Pager pager = (Pager) dynaBean.get("pager");
		String add_date_ge = (String) dynaBean.get("add_date_ge");
		String add_date_le = (String) dynaBean.get("add_date_le");
		String dept_id = (String) dynaBean.get("dept_id");
		String audit_status = (String) dynaBean.get("audit_status");
		String arc_state = (String) dynaBean.get("arc_state");
		String user_name_like = (String) dynaBean.get("user_name_like");
		String zmd_sn_like = (String) dynaBean.get("zmd_sn_like");
		KonkaXxZmdAuditUserInfo entity = new KonkaXxZmdAuditUserInfo();

		if (role_id_btw_300_400) {
			KonkaDept konkaDept = super.getKonkaDeptForFgs(user_id.getDept_id());
			if (null != konkaDept) {
				entity.setDept_id(konkaDept.getDept_id());
				request.setAttribute("dept_id", konkaDept.getDept_id());
				request.setAttribute("dept_name", konkaDept.getDept_name());
			}
		} else {
			if (StringUtils.isNotBlank(dept_id)) {
				entity.setDept_id(Long.valueOf(dept_id));
			}
		}

		entity.getMap().put("is_not_20300", true);

		if (StringUtils.isNotBlank(add_date_ge)) {
			entity.getMap().put("add_date_ge", add_date_ge);
		}
		if (StringUtils.isNotBlank(add_date_le)) {
			entity.getMap().put("add_date_le", add_date_le);
		}
		if (StringUtils.isNotBlank(audit_status)) {
			entity.setAudit_status(Long.valueOf(audit_status));
		}
		if (StringUtils.isNotBlank(arc_state)) {
			entity.getMap().put("arc_state", arc_state);
		}
		if (StringUtils.isNotBlank(user_name_like)) {
			entity.getMap().put("user_name_like", user_name_like);
		}
		if (StringUtils.isNotBlank(zmd_sn_like)) {
			entity.getMap().put("zmd_sn_like", zmd_sn_like);
		}

		Long recordCount = getFacade().getKonkaXxZmdAuditUserInfoService()
				.getKonkaXxZmdAuditUserInfoAndZmdCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<KonkaXxZmdAuditUserInfo> entityList = getFacade().getKonkaXxZmdAuditUserInfoService()
				.getKonkaXxZmdAuditUserInfoAndZmdPaginatedList(entity);
		if (null != entityList && entityList.size() > 0) {
			for (KonkaXxZmdAuditUserInfo temp : entityList) {

				String is_zmd_user_audit = "-1";// 资质审核
				String is_zmd_audit = "-1";// 备案审核

				KonkaXxZmdAuditUserHis kHis = new KonkaXxZmdAuditUserHis();
				kHis.setZmd_user_id(temp.getZmd_user_id());
				kHis.getMap().put("max_id", true);
				List<KonkaXxZmdAuditUserHis> kHisList = super.getFacade().getKonkaXxZmdAuditUserHisService()
						.getKonkaXxZmdAuditUserHisList(kHis);
				if (kHisList.size() > 0 && null != kHisList.get(0).getAudit_type()
						&& null != kHisList.get(0).getAudit_next_node_id()) {

					if ("130000".equals(kHisList.get(0).getAudit_type().toString())
							&& role_ids.contains("," + kHisList.get(0).getAudit_next_node_id().toString() + ",")
							&& (temp.getAudit_status() == 130100 || temp.getAudit_status() == 130110)) {
						is_zmd_user_audit = "1";
						temp.getMap().put("role_id", kHisList.get(0).getAudit_next_node_id());
					}

					if (null != temp.getMap().get("arc_state")
							&& ("20100".equals(temp.getMap().get("arc_state").toString()) || "20110".equals(temp
									.getMap().get("arc_state").toString())|| "20200".equals(temp.getMap().get("arc_state").toString()))) {
						if ("20000".equals(kHisList.get(0).getAudit_type().toString())
								&& role_ids.contains("," + kHisList.get(0).getAudit_next_node_id().toString() + ",")) {
							is_zmd_audit = "1";
							temp.getMap().put("role_id", kHisList.get(0).getAudit_next_node_id());
						}
					}
				}
				temp.getMap().put("is_zmd_user_audit", is_zmd_user_audit);
				temp.getMap().put("is_zmd_audit", is_zmd_audit);

			}
			request.setAttribute("entityList", entityList);
		}

		// 获取分公司列表

		request.setAttribute("konkaDeptList", super.getKonkaDepts());
		setBaseTypeListByParIdToRequest(130000L, request);// 专卖店客户资质审核状态
		setBaseTypeListByParIdToRequest(20000L, request);// 专卖店备案状态

		return mapping.findForward("list");
	}

	public ActionForward audit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		saveToken(request);

		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);

		String zmd_user_id = (String) dynaBean.get("zmd_user_id");
		String role_id = (String) dynaBean.get("role_id");

		if (!GenericValidator.isLong(zmd_user_id) || !GenericValidator.isLong(role_id)) {
			String msg = super.getMessage(request, "errors.parm");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		KonkaXxZmdAuditUserInfo entity = new KonkaXxZmdAuditUserInfo();// 获取客户资质信息
		entity.setZmd_user_id(Long.valueOf(zmd_user_id));
		entity = super.getFacade().getKonkaXxZmdAuditUserInfoService().getKonkaXxZmdAuditUserInfo(entity);
		if (entity == null) {
			String msg = super.getMessage(request, "entity.missing");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		super.copyProperties(form, entity);

		KonkaDept konkaDept = new KonkaDept();// 取分公司名称
		konkaDept.setDept_id(entity.getDept_id());
		List<KonkaDept> konkaDeptList = super.getFacade().getKonkaDeptService().getKonkaDeptList(konkaDept);
		if (konkaDeptList.size() > 0) {
			request.setAttribute("dept_name", konkaDeptList.get(0).getDept_name());
		}

		if (null != entity.getMainly_resume() && !"".equals(entity.getMainly_resume())) {
			request.setAttribute("mainly_resume", entity.getMainly_resume().replaceAll("&lt;br /&gt;", "</br>"));
		}

		// PeProdUser user_id = super.getSessionUserInfo(request);

		// 判断是否是专卖店总部管理员和工作人员的角色
		// PeRoleUser _peRoleUser = new PeRoleUser();
		// _peRoleUser.setUser_id(user_id.getId());
		// List<PeRoleUser> peRoleUserList =
		// this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);

		// boolean role_id_eq_200 = false;// 总部管理员
		// if (peRoleUserList.size() > 0) {
		// for (PeRoleUser temp : peRoleUserList) {
		// if (temp.getRole_id() == 200) {
		// role_id_eq_200 = true;
		// }
		// }
		// }

		// 附件
		KonkaPeAttachments attachment = new KonkaPeAttachments();
		attachment.setLink_id(entity.getZmd_user_id());
		attachment.setLink_tab("Zmd_Audit_User");
		attachment.setIs_del(0l);
		request.setAttribute("attachmentList", super.getFacade().getKonkaPeAttachmentsService()
				.getKonkaPeAttachmentsList(attachment));

		if ("220".equals(role_id)) {
			request.setAttribute("is_gz_user", "1");
		}
		if ("200".equals(role_id)) {
			request.setAttribute("is_admin", "1");
		}

		// 驳回时显示驳回的具体角色
		KonkaXxAuditNote auditNote = new KonkaXxAuditNote();
		auditNote.getMap().put("role_id_value", role_id);
		auditNote.setAudit_type(10L);
		List<KonkaXxAuditNote> konkaXxAuditNoteList = super.getFacade().getKonkaXxAuditNoteService()
				.getKonkaXxAuditNoteList(auditNote);

		request.setAttribute("konkaXxAuditNoteList", konkaXxAuditNoteList);

		dynaBean.set("role_id_value", role_id);
		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		if (!isTokenValid(request)) {
			saveError(request, "errors.token");
			return list(mapping, form, request, response);
		}

		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);

		String zmd_user_id = (String) dynaBean.get("zmd_user_id");
		String audit_status = (String) dynaBean.get("audit_status");
		String audit_opinion = (String) dynaBean.get("audit_opinion");
		String zmd_sn = (String) dynaBean.get("zmd_sn");
		String mod_id = (String) dynaBean.get("mod_id");
		String role_id_value = (String) dynaBean.get("role_id_value");
		String audit_user_role = (String) dynaBean.get("audit_user_role");

		PeProdUser user_id = super.getSessionUserInfo(request);

		if (!GenericValidator.isLong(zmd_user_id) || !GenericValidator.isLong(role_id_value)
				|| !GenericValidator.isLong(audit_status)) {
			String msg = super.getMessage(request, "errors.parm");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		KonkaXxZmdAuditUserInfo entity = new KonkaXxZmdAuditUserInfo();
		entity.setZmd_user_id(Long.valueOf(zmd_user_id));

		KonkaXxZmdAuditUserHis kAuditUserHis = new KonkaXxZmdAuditUserHis();
		kAuditUserHis.setZmd_user_id(Long.valueOf(zmd_user_id));
		// 审核通过
		if ("1".equals(audit_status)) {

			KonkaXxAuditNote auditNote = new KonkaXxAuditNote();
			auditNote.setAudit_type(10L);
			auditNote.setAudit_node_id(Long.valueOf(role_id_value));
			auditNote = super.getFacade().getKonkaXxAuditNoteService().getKonkaXxAuditNote(auditNote);

			if (null == auditNote) {
				String msg = super.getMessage(request, "entity.missing");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			}

			// 历史记录表
			kAuditUserHis.setAudit_status_desc("审核通过");
			kAuditUserHis.setAudit_desc("资质申请");
			kAuditUserHis.setAudit_type(130000L);
			kAuditUserHis.setAudit_user_id(user_id.getId());
			kAuditUserHis.setAudit_user_name(user_id.getUser_name());
			kAuditUserHis.setAudit_date(new Date());
			if (auditNote.getIs_audit_end() == 1) {
				entity.setAudit_status(130300L);

				// 历史记录表
				kAuditUserHis.setIs_end(1);
				kAuditUserHis.setAudit_status(130300L);
			} else {
				entity.setAudit_status(130110L);

				// 历史记录表
				kAuditUserHis.setIs_end(0);
				kAuditUserHis.setAudit_next_node_id(auditNote.getAudit_next_node_id());
				kAuditUserHis.setAudit_next_node_name(auditNote.getAudit_next_node_name());
				kAuditUserHis.setAudit_status(130110L);
			}
		}
		// 驳回
		if ("2".equals(audit_status)) {

			entity.setAudit_status(130200L);

			if (!GenericValidator.isLong(audit_user_role)) {
				String msg = super.getMessage(request, "errors.parm");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			}

			KonkaXxAuditNote auditNote = new KonkaXxAuditNote();
			auditNote.setAudit_type(10L);
			auditNote.setAudit_node_id(Long.valueOf(audit_user_role));
			auditNote = super.getFacade().getKonkaXxAuditNoteService().getKonkaXxAuditNote(auditNote);

			if (null == auditNote) {
				String msg = super.getMessage(request, "entity.missing");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			}

			// 历史记录表
			kAuditUserHis.setAudit_status_desc("驳回");
			kAuditUserHis.setAudit_desc("资质申请");
			kAuditUserHis.setAudit_type(130000L);
			kAuditUserHis.setAudit_user_id(user_id.getId());
			kAuditUserHis.setAudit_user_name(user_id.getUser_name());
			kAuditUserHis.setAudit_date(new Date());
			kAuditUserHis.setIs_end(0);
			if (auditNote.getAudit_seq() == 0) {
				kAuditUserHis.setAudit_status(130100L);
				kAuditUserHis.setAudit_next_node_id(auditNote.getAudit_next_node_id());
				kAuditUserHis.setAudit_next_node_name(auditNote.getAudit_next_node_name());
				// kAuditUserHis.setAudit_next_node_id(220L);
				// kAuditUserHis.setAudit_next_node_name("[专卖店]总部工作人员");
			} else {
				kAuditUserHis.setAudit_status(130110L);
				kAuditUserHis.setAudit_next_node_id(auditNote.getAudit_node_id());
				kAuditUserHis.setAudit_next_node_name(auditNote.getAudit_node_name());
			}

		}

		if (StringUtils.isNotBlank(audit_opinion)) {
			entity.setAudit_opinion(audit_opinion);
			kAuditUserHis.setAudit_option(audit_opinion);
		}
		if (StringUtils.isNotBlank(zmd_sn)) {
			entity.setZmd_sn(zmd_sn);
		}

		entity.setAudit_id(user_id.getId());
		entity.setAudit_name(user_id.getUser_name());
		entity.setAudit_date(new Date());

		entity.setKonkaXxZmdAuditUserHis(kAuditUserHis);
		// super.getFacade().getKonkaXxZmdAuditUserInfoService().modifyKonkaXxZmdAuditUserInfo(entity);
		super.getFacade().getKonkaXxZmdAuditUserInfoService().modifyKonkaXxZmdAuditUserInfoAndHistory(entity);

		saveMessage(request, "entity.updated");

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		return forward;
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);

		String zmd_user_id = (String) dynaBean.get("zmd_user_id");

		if (!GenericValidator.isLong(zmd_user_id)) {
			String msg = super.getMessage(request, "errors.parm");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		KonkaXxZmdAuditUserInfo entity = new KonkaXxZmdAuditUserInfo();// 获取客户资质信息
		entity.setZmd_user_id(Long.valueOf(zmd_user_id));
		entity = super.getFacade().getKonkaXxZmdAuditUserInfoService().getKonkaXxZmdAuditUserInfo(entity);
		if (entity == null) {
			String msg = super.getMessage(request, "entity.missing");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		super.copyProperties(form, entity);

		// 附件
		KonkaPeAttachments attachment = new KonkaPeAttachments();
		attachment.setLink_id(entity.getZmd_user_id());
		attachment.setLink_tab("Zmd_Audit_User");
		attachment.setIs_del(0l);
		request.setAttribute("attachmentList", super.getFacade().getKonkaPeAttachmentsService()
				.getKonkaPeAttachmentsList(attachment));

		KonkaDept konkaDept = new KonkaDept();// 取分公司名称
		konkaDept.setDept_id(entity.getDept_id());
		List<KonkaDept> konkaDeptList = super.getFacade().getKonkaDeptService().getKonkaDeptList(konkaDept);
		if (konkaDeptList.size() > 0) {
			request.setAttribute("dept_name", konkaDeptList.get(0).getDept_name());
		}

		if (null != entity.getMainly_resume() && !"".equals(entity.getMainly_resume())) {
			request.setAttribute("mainly_resume", entity.getMainly_resume().replaceAll("&lt;br /&gt;", "</br>"));
		}

		setBaseTypeListByParIdToRequest(130000L, request);// 专卖店客户资质审核状态

		// 审核意见
		KonkaXxZmdAuditUserHis konkaXxZmdAuditUserHis = new KonkaXxZmdAuditUserHis();
		konkaXxZmdAuditUserHis.setZmd_user_id(Long.valueOf(zmd_user_id));
		konkaXxZmdAuditUserHis.setAudit_type(130000L);
		konkaXxZmdAuditUserHis.getMap().put("order_by_id", true);
		List<KonkaXxZmdAuditUserHis> kHis = super.getFacade().getKonkaXxZmdAuditUserHisService()
				.getKonkaXxZmdAuditUserHisList(konkaXxZmdAuditUserHis);
		request.setAttribute("kHis", kHis);

		return mapping.findForward("view");
	}

	public ActionForward hisList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);

		String zmd_user_id = (String) dynaBean.get("zmd_user_id");

		if (!GenericValidator.isLong(zmd_user_id)) {
			String msg = super.getMessage(request, "errors.parm");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		KonkaXxZmdAuditUserInfo konkaXxZmdAuditUserInfo = new KonkaXxZmdAuditUserInfo();// 获取客户姓名
		konkaXxZmdAuditUserInfo.setZmd_user_id(Long.valueOf(zmd_user_id));
		konkaXxZmdAuditUserInfo = super.getFacade().getKonkaXxZmdAuditUserInfoService().getKonkaXxZmdAuditUserInfo(
				konkaXxZmdAuditUserInfo);

		if (konkaXxZmdAuditUserInfo == null) {
			String msg = super.getMessage(request, "entity.missing");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		request.setAttribute("zmd_user_name", konkaXxZmdAuditUserInfo.getUser_name());

		KonkaXxZmdAuditUserHis entity = new KonkaXxZmdAuditUserHis();// 历史审核记录
		entity.setZmd_user_id(Long.valueOf(zmd_user_id));
		List<KonkaXxZmdAuditUserHis> entityList = getFacade().getKonkaXxZmdAuditUserHisService()
				.getKonkaXxZmdAuditUserHisForZmdList(entity);

		if (null != entityList && entityList.size() > 0) {
			request.setAttribute("entityList", entityList);
		}

		return new ActionForward("/../manager/zmd/KonkaXxZmdAuditUserInfoAudit/list_his.jsp");
	}

	public ActionForward validateZmsSn(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;

		String zmd_sn = (String) dynaBean.get("zmd_sn");
		KonkaXxZmd entity = new KonkaXxZmd();
		String isExist = "null";

		if (StringUtils.isNotBlank(zmd_sn)) {
			entity.setZmd_sn(zmd_sn);
			List<KonkaXxZmd> entityList = getFacade().getKonkaXxZmdService().getKonkaXxZmdList(entity);
			if (entityList.size() == 0) {// 可用
				isExist = String.valueOf("0");
			} else {

				isExist = String.valueOf("11");
			}
		}

		super.render(response, isExist, "text/x-json;charset=UTF-8");
		return null;
	}

}
