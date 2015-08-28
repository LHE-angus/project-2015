package com.ebiz.mmt.web.struts.manager.zmd;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.KonkaXxAuditNote;
import com.ebiz.mmt.domain.KonkaXxZmdAuditUserHis;
import com.ebiz.mmt.domain.KonkaXxZmdAuditUserInfo;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.MmtFilePathConfig;
import com.ebiz.ssi.web.struts.bean.Pager;
import com.ebiz.ssi.web.struts.bean.UploadFile;

/**
 * @author Hu Hao
 * @version 2013-03-13
 */
public class KonkaXxZmdAuditUserInfoAction extends BaseZmdAction {

	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		return this.zmdaudit(mapping, form, request, response);
	}

	protected ActionForward zmdaudit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;

		// 用户角色
		PeProdUser user_id = super.getSessionUserInfo(request);// 用户信息
		List<PeRoleUser> peRoleUserList = getPeRoleList(user_id.getId());
		// Boolean role_id_gt_400 = false;
		Boolean role_id_btw_300_400 = false;
		Boolean role_id_eq_300 = false;
		if (peRoleUserList.size() > 0) {
			for (PeRoleUser temp : peRoleUserList) {
				if (temp.getRole_id() == 300) {
					role_id_eq_300 = true;
				}
				if ((temp.getRole_id() >= 300 && temp.getRole_id() < 400)
						|| (temp.getRole_id() >= 30 && temp.getRole_id() < 40)) {
					role_id_btw_300_400 = true;
				}
			}
		}

		if (!role_id_eq_300) {
			String msg = super.getMessage(request, "konka.xx.zmd.role.error");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		Pager pager = (Pager) dynaBean.get("pager");
		String add_date_ge = (String) dynaBean.get("add_date_ge");
		String add_date_le = (String) dynaBean.get("add_date_le");
		String audit_status = (String) dynaBean.get("audit_status");
		String arc_state = (String) dynaBean.get("arc_state");
		String user_name_like = (String) dynaBean.get("user_name_like");
		String zmd_sn_like = (String) dynaBean.get("zmd_sn_like");
		KonkaXxZmdAuditUserInfo entity = new KonkaXxZmdAuditUserInfo();

		if (role_id_btw_300_400) {// 分公司人员
			KonkaDept konkaDept = super.getKonkaDeptForFgs(user_id.getDept_id());
			if (null != konkaDept) {
				entity.setDept_id(konkaDept.getDept_id());
				request.setAttribute("dept_id", konkaDept.getDept_id());
				request.setAttribute("dept_name", konkaDept.getDept_name());
			}
		}
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
		} else {
			entity.getMap().put("arc_state", arc_state);
		}
		if (StringUtils.isNotBlank(user_name_like)) {
			entity.getMap().put("user_name_like", user_name_like);
		}
		if (StringUtils.isNotBlank(zmd_sn_like)) {
			entity.getMap().put("zmd_sn_like", zmd_sn_like);
		}

		entity.getMap().put("is_not_20300", true);

		Long recordCount = getFacade().getKonkaXxZmdAuditUserInfoService()
				.getKonkaXxZmdAuditUserInfoAndZmdCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<KonkaXxZmdAuditUserInfo> entityList = getFacade().getKonkaXxZmdAuditUserInfoService()
				.getKonkaXxZmdAuditUserInfoAndZmdPaginatedList(entity);

		if (null != entityList && entityList.size() > 0) {
			for (KonkaXxZmdAuditUserInfo temp : entityList) {

				// 客户资质状态：130100-待审核，130200-审核未通过，130300-审核通过
				if (temp.getAudit_status() == 130300 || temp.getAudit_status() == 130110) {
					temp.getMap().put("is_edit", "1");// 不可以修改客户资质信息
				} else {
					temp.getMap().put("is_edit", "-1");// 可以修改客户资质信息
				}

				if ((null != temp.getMap().get("arc_state") && !"20300".equals(temp.getMap().get("arc_state")
						.toString()))
						|| null == temp.getMap().get("arc_state")) {
					temp.getMap().put("is_del", "1");
				}

				if (temp.getAudit_status() == 130300 && (temp.getZmd_sn() != null || !temp.getZmd_sn().equals(""))) {
					temp.getMap().put("is_apply", "1");// 提交专卖店备案申请
				} else {
					temp.getMap().put("is_apply", "-1");// 审核未完成，不能提交专卖店备案申请
				}
			}
			request.setAttribute("entityList", entityList);
		}

		request.setAttribute("konkaDeptList", super.getKonkaDepts());// 获取分公司列表
		setBaseTypeListByParIdToRequest(130000L, request);// 专卖店客户资质审核状态
		setBaseTypeListByParIdToRequest(20000L, request);// 专卖店备案状态

		return new ActionForward("/../manager/zmd/KonkaXxZmdAuditUserInfo/user_zmd_list.jsp");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;

		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

		List<PeRoleUser> peRoleUserList = getPeRoleList(peProdUser.getId());

		Boolean role_id_gt_400 = false;
		Boolean role_id_eq_300 = false;// 分公司专卖店管理员
		if (peRoleUserList.size() > 0) {
			for (PeRoleUser temp : peRoleUserList) {
				if (temp.getRole_id() <= 400) {
					role_id_gt_400 = true;
				}
				if (temp.getRole_id() == 300) {
					role_id_eq_300 = true;
				}
			}
		}

		if (!role_id_gt_400 || !role_id_eq_300) {
			String msg = super.getMessage(request, "konka.xx.zmd.role.error");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		// 取分公司名称
		KonkaDept konkaDept = new KonkaDept();
		konkaDept = super.getKonkaDeptForFgs(peProdUser.getDept_id());

		if (null == konkaDept || null == konkaDept.getDept_name()) {
			String msg = super.getMessage(request, "konka.xx.zmd.role.error");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		} else {
			request.setAttribute("dept_name", konkaDept.getDept_name());
			dynaBean.set("dept_id", konkaDept.getDept_id());
		}

		// 获取当前时间
		dynaBean.set("add_date", new Date());

		dynaBean.set("add_value", "2");

		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String dept_id = (String) dynaBean.get("dept_id");
		String mainly_resume = (String) dynaBean.get("mainly_resume");
		String add_date = (String) dynaBean.get("add_date");
		String reg_date = (String) dynaBean.get("reg_date");
		String birthday = (String) dynaBean.get("birthday");
		String reg_money = (String) dynaBean.get("reg_money");
		String zmd_user_id = (String) dynaBean.get("zmd_user_id");
		String edit_value = (String) dynaBean.get("edit_value");

		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

		KonkaXxZmdAuditUserInfo entity = new KonkaXxZmdAuditUserInfo();

		super.copyProperties(entity, form);

		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		if (null == dept_id || "".equals(dept_id) || null == mod_id || "".equals(mod_id)) {
			String msg = super.getMessage(request, "entity.missing");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		entity.setDept_id(Long.valueOf(dept_id));
		if (StringUtils.isNotBlank(add_date)) {
			entity.setAdd_date(format.parse(add_date));
		}
		if (StringUtils.isNotBlank(reg_date)) {
			entity.setReg_date(format.parse(reg_date));
		}
		if (StringUtils.isNotBlank(add_date)) {
			entity.setAdd_date(format.parse(add_date));
		}
		if (StringUtils.isNotBlank(birthday)) {
			entity.setBirthday(format.parse(birthday));
		}
		if (StringUtils.isNotBlank(reg_money)) {
			entity.setReg_money(new BigDecimal(reg_money));
		}
		if (StringUtils.isNotBlank(mainly_resume)) {
			entity.setMainly_resume(mainly_resume);
		}

		// 附件
		List<UploadFile> uploadFileList = super.uploadFile(form, MmtFilePathConfig.OTHERS_PATH, true, 0, new int[] { 240 });
		List<KonkaPeAttachments> attachmentList = new ArrayList<KonkaPeAttachments>();
		KonkaPeAttachments attachment = null;
		for (UploadFile uploadFile : uploadFileList) {
			attachment = new KonkaPeAttachments();
			attachment.setFile_name(uploadFile.getFileName());
			attachment.setFile_type(uploadFile.getContentType());
			attachment.setFile_size(new Long(uploadFile.getFileSize()));
			attachment.setSave_path(uploadFile.getFileSavePath());
			attachment.setSave_name(uploadFile.getFileSaveName());
			attachment.setIs_del(0l);
			attachment.setLink_tab("Zmd_Audit_User");
			attachment.setAdd_user_name(peProdUser.getUser_name());
			attachment.setAdd_user_id(peProdUser.getId());
			attachmentList.add(attachment);
		}
		entity.setAttachmentList(attachmentList);

		entity.setAdd_user_id(peProdUser.getId());
		entity.setAdd_user_name(peProdUser.getUser_name());

		if (GenericValidator.isLong(zmd_user_id)) {
			if (StringUtils.isNotBlank(edit_value)) {
				entity.setAudit_status(130300L);
			} else {
				entity.setAudit_status(130100L);
			}
			entity.setZmd_user_id(Long.valueOf(zmd_user_id));
			super.getFacade().getKonkaXxZmdAuditUserInfoService().modifyKonkaXxZmdAuditUserInfoForFiles(entity);
			saveMessage(request, "entity.updated");
		} else {
			// 初始化资质申请历史记录
			List<KonkaXxZmdAuditUserHis> konkaXxZmdAuditUserHisList = new ArrayList<KonkaXxZmdAuditUserHis>();
			KonkaXxAuditNote konkaXxAuditNote = new KonkaXxAuditNote();
			konkaXxAuditNote.setAudit_type(10L);// 资质审核
			konkaXxAuditNote.setAudit_node_id(300L);
			List<KonkaXxAuditNote> konkaXxAuditNoteList = super.getFacade().getKonkaXxAuditNoteService()
					.getKonkaXxAuditNoteList(konkaXxAuditNote);
			if (konkaXxAuditNoteList.size() > 0) {
				KonkaXxZmdAuditUserHis konkaXxZmdAuditUserHis = new KonkaXxZmdAuditUserHis();
				konkaXxZmdAuditUserHis.setIs_end(0);
				konkaXxZmdAuditUserHis.setAudit_next_node_id(konkaXxAuditNoteList.get(0).getAudit_next_node_id());
				konkaXxZmdAuditUserHis.setAudit_next_node_name(konkaXxAuditNoteList.get(0).getAudit_next_node_name());
				konkaXxZmdAuditUserHis.setAudit_status(130100L);
				konkaXxZmdAuditUserHis.setAudit_desc("资质申请");
				konkaXxZmdAuditUserHis.setAudit_type(130000L);
				konkaXxZmdAuditUserHis.setAudit_status_desc("提交资质申请");
				konkaXxZmdAuditUserHis.setAudit_user_id(peProdUser.getId());
				konkaXxZmdAuditUserHis.setAudit_user_name("--");
				konkaXxZmdAuditUserHis.setAudit_date(new Date());
				konkaXxZmdAuditUserHisList.add(konkaXxZmdAuditUserHis);
			}
			entity.setKonkaXxZmdAuditUserHisList(konkaXxZmdAuditUserHisList);
			entity.setAudit_status(130100L);
			super.getFacade().getKonkaXxZmdAuditUserInfoService().createKonkaXxZmdAuditUserInfoForFiles(entity);
			saveMessage(request, "entity.inserted");
		}

		StringBuffer pathBuffer = new StringBuffer();
		if (StringUtils.isNotBlank(edit_value)) {
			pathBuffer.append("KonkaXxZmdAuditUserInfo.do?method=list");
		} else {
			pathBuffer.append("KonkaXxZmdAuditUserInfo.do?");
		}
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		return forward;
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);

		// 用户角色
		PeProdUser user_id = super.getSessionUserInfo(request);
		// PeRoleUser peRoleUser = super.getRoleInfoByThisLogin(request);
		List<PeRoleUser> peRoleUserList = getPeRoleList(user_id.getId());

		Boolean role_id_gt_400 = false;
		Boolean role_id_btw_300_400 = false;
		Boolean role_id_btw_200_300 = false;
		if (peRoleUserList.size() > 0) {
			for (PeRoleUser temp : peRoleUserList) {
				if (temp.getRole_id() <= 400) {
					role_id_gt_400 = true;
				}
				if ((temp.getRole_id() >= 200 && temp.getRole_id() < 300) || temp.getRole_id() < 30) {
					role_id_btw_200_300 = true;
				}
				if ((temp.getRole_id() >= 300 && temp.getRole_id() < 400)
						|| (temp.getRole_id() < 40 && temp.getRole_id() >= 30)) {
					role_id_btw_300_400 = true;
				}
			}
		}
		if (!role_id_gt_400) {
			String msg = super.getMessage(request, "konka.xx.zmd.role.error");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		Pager pager = (Pager) dynaBean.get("pager");
		String dept_id = (String) dynaBean.get("dept_id");
		String add_date_ge = (String) dynaBean.get("add_date_ge");
		String add_date_le = (String) dynaBean.get("add_date_le");
		String user_name_like = (String) dynaBean.get("user_name_like");
		String zmd_sn_like = (String) dynaBean.get("zmd_sn_like");

		KonkaXxZmdAuditUserInfo entity = new KonkaXxZmdAuditUserInfo();

		if (role_id_btw_300_400) {// 分公司人员
			KonkaDept kDept = getKonkaDeptForFgs(user_id.getDept_id());
			entity.setDept_id(kDept.getDept_id());
			request.setAttribute("dept_id", kDept.getDept_id());
			request.setAttribute("dept_name", kDept.getDept_name());
		} else if (role_id_btw_200_300) {// 总部人员
			if (StringUtils.isNotBlank(dept_id)) {
				entity.setDept_id(Long.valueOf(dept_id));
			}
			request.setAttribute("is_admin", "1");
		}

		if (StringUtils.isNotBlank(add_date_ge)) {
			entity.getMap().put("add_date_ge", add_date_ge);
		}
		if (StringUtils.isNotBlank(add_date_le)) {
			entity.getMap().put("add_date_le", add_date_le);
		}
		if (StringUtils.isNotBlank(user_name_like)) {
			entity.getMap().put("user_name_like", user_name_like);
		}
		if (StringUtils.isNotBlank(zmd_sn_like)) {
			entity.getMap().put("zmd_sn_like", zmd_sn_like);
		}
		entity.getMap().put("arc_state", 20300);

		Long recordCount = getFacade().getKonkaXxZmdAuditUserInfoService().getKonkaXxZmdAndUserInfoCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<KonkaXxZmdAuditUserInfo> entityList = getFacade().getKonkaXxZmdAuditUserInfoService()
				.getKonkaXxZmdAndUserInfoPaginatedList(entity);

		// 全部导出
		entity.getRow().setCount(recordCount.intValue());
		List<KonkaXxZmdAuditUserInfo> entityList1 = getFacade().getKonkaXxZmdAuditUserInfoService()
				.getKonkaXxZmdAndUserInfoPaginatedList(entity);

		request.setAttribute("entityList1", entityList1);
		if (null != entityList && entityList.size() > 0) {

			for (KonkaXxZmdAuditUserInfo cur : entityList) {

				String r3_id = (String) cur.getMap().get("r3_id");

				if (null == r3_id) {
					continue;
				}

				KonkaR3Shop s = new KonkaR3Shop();
				s.setR3_code(r3_id.toUpperCase());
				s = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(s);
				cur.getMap().put("konkaR3Shop", s);

				PeProdUser u = new PeProdUser();
				u.getMap().put("zmd_id_eq", cur.getMap().get("zmd_id"));
				u = super.getFacade().getPeProdUserService().getPeProdUser(u);
				cur.getMap().put("peProdUser", u);

			}

			request.setAttribute("entityList", entityList);
		}

		// 获取分公司列表
		request.setAttribute("konkaDeptList", super.getKonkaDepts());

		setBaseTypeListByParIdToRequest(130000L, request);// 专卖店客户资质审核状态
		setBaseTypeListByParIdToRequest(20000L, request);// 专卖店备案状态
		setBaseTypeListByParIdToRequest(100000L, request);// 专卖店备案状态
		return mapping.findForward("list");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);

		String zmd_user_id = (String) dynaBean.get("zmd_user_id");
		String edit_value = (String) dynaBean.get("edit_value");
		dynaBean.set("edit_value", edit_value);

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
		attachment.setLink_id(Long.valueOf(zmd_user_id));
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
			dynaBean.set("mainly_resume", entity.getMainly_resume().replaceAll("&lt;br /&gt;", "\n"));
		}

		dynaBean.set("add_value", "-2");
		return mapping.findForward("input");
	}

	public ActionForward deleteFile(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;

		String id = (String) dynaBean.get("id");

		if (StringUtils.isNotBlank(id) && GenericValidator.isLong(id)) {
			KonkaPeAttachments entity = new KonkaPeAttachments();
			entity.setId(new Long(id));
			super.getFacade().getKonkaPeAttachmentsService().removeKonkaPeAttachments(entity);
			// saveMessage(request, "entity.deleted");
		}

		super.renderText(response, "success");
		return null;
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String is_f = (String) dynaBean.get("is_f");

		String zmd_user_id = (String) dynaBean.get("zmd_user_id");

		if (!GenericValidator.isLong(zmd_user_id)) {
			String msg = super.getMessage(request, "errors.parm");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		KonkaXxZmdAuditUserInfo entity = new KonkaXxZmdAuditUserInfo();
		entity.setZmd_user_id(Long.valueOf(zmd_user_id));
		entity.setIs_del(1);
		super.getFacade().getKonkaXxZmdAuditUserInfoService().modifyKonkaXxZmdAuditUserInfo(entity);
		saveMessage(request, "entity.deleted");

		StringBuffer pathBuffer = new StringBuffer();
		if (StringUtils.isNotBlank(is_f)) {
			pathBuffer.append("KonkaXxZmdAuditUserInfo.do?method=list");
		} else {
			pathBuffer.append("KonkaXxZmdAuditUserInfo.do?");
		}
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		return forward;
	}
}
