package com.ebiz.mmt.web.struts.manager.zmd;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.mail.SimpleMailMessage;

import com.ebiz.mmt.domain.BaseProvinceListFour;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaPeAttachments;
import com.ebiz.mmt.domain.KonkaXxAuditNote;
import com.ebiz.mmt.domain.KonkaXxBaseType;
import com.ebiz.mmt.domain.KonkaXxZmd;
import com.ebiz.mmt.domain.KonkaXxZmdAuditUserHis;
import com.ebiz.mmt.domain.KonkaXxZmdGcys;
import com.ebiz.mmt.domain.PeProdUser;

/**
 * @author Zheng,Kun
 * @version 2012-1-10
 */
public class KonkaXxZmdVerificationAction extends BaseZmdAction {

	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.edit(mapping, form, request, response);
	}


	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		saveToken(request);
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String zmd_id = (String) dynaBean.get("zmd_id");
		String role_id = (String) dynaBean.get("role_id");

		if (!GenericValidator.isLong(zmd_id)) {
			this.saveError(request, "errors.long", new String[] { zmd_id });
			return mapping.findForward("list");
		}

		KonkaXxZmd entity = new KonkaXxZmd();
		entity.setZmd_id(Long.valueOf(zmd_id));
		entity = super.getFacade().getKonkaXxZmdService().getKonkaXxZmd(entity);
		if (null == entity) {
			saveMessage(request, "entity.missing");
			return mapping.findForward("list");
		}

		// the line below is added for pagination
		entity.setQueryString(super.serialize(request, "zmd_id", "method"));
		// end

		// 取专卖店所属分公司名称
		KonkaDept konkaDept = new KonkaDept();
		konkaDept.setDept_id(entity.getDept_id());
		konkaDept = super.getFacade().getKonkaDeptService().getKonkaDept(konkaDept);
		entity.getMap().put("dept_name", konkaDept.getDept_name());

		super.copyProperties(form, entity);

		// 工程项目
		KonkaXxZmdGcys konkaXxZmdGcys = new KonkaXxZmdGcys();
		konkaXxZmdGcys.setZmd_id(Long.valueOf(zmd_id));
		List<KonkaXxZmdGcys> konkaXxZmdGcysList = super.getFacade().getKonkaXxZmdGcysService().getKonkaXxZmdGcysList(
				konkaXxZmdGcys);
		request.setAttribute("konkaXxZmdGcysList", konkaXxZmdGcysList);

		BigDecimal total_money = new BigDecimal(0);
		if (konkaXxZmdGcysList.size() > 0) {
			for (KonkaXxZmdGcys temp : konkaXxZmdGcysList) {
				if (null != temp.getTotal() && !"".equals(String.valueOf(temp.getTotal()))) {
					total_money = total_money.add(temp.getTotal());
				}
			}
		}
		request.setAttribute("total_money", total_money);
		// BigDecimal total_money = new BigDecimal(0);
		// if(konkaXxZmdGcysList.size()>0){
		// for(KonkaXxZmdGcys temp : konkaXxZmdGcysList){
		// if(null != temp.getTotal() && "".equals(temp.getTotal())){
		// total_money = total_money.add(temp.getTotal());
		// }
		// }
		// }
		// request.setAttribute("total_money", total_money);

		// 取基础类型表里相对应的数据
		setBaseTypeListByParIdToRequest(10000L, request);
		setBaseTypeListByParIdToRequest(100000L, request);

		// KonkaXxZmdRes konkaXxZmdRes = new KonkaXxZmdRes();
		// konkaXxZmdRes.setZmd_id(Long.valueOf(zmd_id));
		// List<KonkaXxZmdRes> konkaXxZmdResList =
		// super.getFacade().getKonkaXxZmdResService().getKonkaXxZmdResList(
		// konkaXxZmdRes);
		// if (konkaXxZmdResList.size() > 0) {
		// request.setAttribute("konkaXxZmdResList", konkaXxZmdResList);
		// } else {
		// List<KonkaXxZmdRes> arrlist = new ArrayList<KonkaXxZmdRes>();
		// for (int i = 0; i < ZmdRole.res_name.length; i++) {
		// KonkaXxZmdRes res = new KonkaXxZmdRes();
		// res.setRes_name(ZmdRole.res_name[i]);
		// arrlist.add(res);
		// }
		// request.setAttribute("konkaXxZmdResList", arrlist);
		// }

		// PeProdUser user_id = super.getSessionUserInfo(request);
		// // 判断是否是专卖店总部管理员和工作人员的角色
		// PeRoleUser _peRoleUser = new PeRoleUser();
		// _peRoleUser.setUser_id(user_id.getId());
		// List<PeRoleUser> peRoleUserList =
		// this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);
		//
		// boolean role_id_eq_200 = false;// 总部管理员
		// if (peRoleUserList.size() > 0) {
		// for (PeRoleUser temp : peRoleUserList) {
		// if (temp.getRole_id() == 200) {
		// role_id_eq_200 = true;
		// }
		// }
		// }
		if ("200".equals(role_id)) {
			request.setAttribute("is_admin", "1");
		}

		// 附件
		KonkaPeAttachments attachment = new KonkaPeAttachments();
		attachment.setLink_id(entity.getZmd_id());
		attachment.setLink_tab("konka_xx_zmd");
		attachment.setIs_del(0l);
		request.setAttribute("attachmentList", super.getFacade().getKonkaPeAttachmentsService()
				.getKonkaPeAttachmentsList(attachment));

		// 驳回时显示驳回的具体角色
		KonkaXxAuditNote auditNote = new KonkaXxAuditNote();
		auditNote.getMap().put("role_id_value", role_id);
		auditNote.setAudit_type(20L);
		List<KonkaXxAuditNote> konkaXxAuditNoteList = super.getFacade().getKonkaXxAuditNoteService()
				.getKonkaXxAuditNoteList(auditNote);

		request.setAttribute("konkaXxAuditNoteList", konkaXxAuditNoteList);

		dynaBean.set("role_id_value", role_id);

		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");

		if (!isTokenValid(request)) {
			saveError(request, "errors.token");
			return new ActionForward("/KonkaXxZmdAuditUserInfoAudit.do?method=list&mod_id=" + mod_id, true);
		}

		String audit_status = (String) dynaBean.get("audit_status");
		String zmd_id = (String) dynaBean.get("zmd_id");
		String role_id_value = (String) dynaBean.get("role_id_value");
		String money_of_dm_apply = (String) dynaBean.get("money_of_dm_apply");
		String money_of_dcrt_apply = (String) dynaBean.get("money_of_dcrt_apply");
		String audit_comment = (String) dynaBean.get("audit_comment");
		String audit_user_role = (String) dynaBean.get("audit_user_role");
		String link_user_ids = (String) dynaBean.get("link_user_ids");
		String is_send_mail = (String) dynaBean.get("is_send_mail");

		if (!GenericValidator.isLong(zmd_id) || !GenericValidator.isLong(role_id_value)) {
			String msg = super.getMessage(request, "errors.parm");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		KonkaXxZmd konkaXxZmd = new KonkaXxZmd();
		konkaXxZmd.setZmd_id(Long.valueOf(zmd_id));
		konkaXxZmd = super.getFacade().getKonkaXxZmdService().getKonkaXxZmd(konkaXxZmd);
		if (konkaXxZmd == null) {
			String msg = super.getMessage(request, "entity.missing");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		PeProdUser user_id = super.getSessionUserInfo(request);

		KonkaXxZmd entity = new KonkaXxZmd();
		entity.setZmd_id(Long.valueOf(zmd_id));

		KonkaXxZmdAuditUserHis kAuditUserHis = new KonkaXxZmdAuditUserHis();
		kAuditUserHis.setZmd_user_id(Long.valueOf(konkaXxZmd.getZmd_user_id()));
		// 审核通过
		if ("1".equals(audit_status)) {

			KonkaXxAuditNote auditNote = new KonkaXxAuditNote();
			auditNote.setAudit_type(20L);
			auditNote.setAudit_node_id(Long.valueOf(role_id_value));
			auditNote = super.getFacade().getKonkaXxAuditNoteService().getKonkaXxAuditNote(auditNote);

			if (null == auditNote) {
				String msg = super.getMessage(request, "entity.missing");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			}

			// 历史记录表
			kAuditUserHis.setAudit_status_desc("审核通过");
			kAuditUserHis.setAudit_desc("备案申请");
			kAuditUserHis.setAudit_type(20000L);
			kAuditUserHis.setAudit_user_id(user_id.getId());
			kAuditUserHis.setAudit_user_name(user_id.getUser_name());

			kAuditUserHis.setAudit_date(new Date());
			if (auditNote.getIs_audit_end() == 1) {
				entity.setArc_state(20300);

				String str_data = "";

				if (StringUtils.isNotBlank(money_of_dm_apply)
						&& !money_of_dm_apply.equals(String.valueOf(konkaXxZmd.getMoney_of_dm_apply()))) {
					str_data = "样机额度:" + konkaXxZmd.getMoney_of_dm_apply() + "万元调整为" + money_of_dm_apply + "万元";

					entity.setMoney_of_dm_apply(new BigDecimal(money_of_dm_apply));
				}

				if (StringUtils.isNotBlank(money_of_dcrt_apply)
						&& !money_of_dcrt_apply.equals(String.valueOf(konkaXxZmd.getMoney_of_dcrt_apply()))) {
					if (!"".equals(str_data)) {
						str_data = str_data + ",";
					}
					str_data = str_data + "建店费用:" + konkaXxZmd.getMoney_of_dcrt_apply() + "万元调整为" + money_of_dcrt_apply
							+ "万元。";

					entity.setMoney_of_dcrt_apply(new BigDecimal(money_of_dcrt_apply));

				}

				// 历史记录表
				kAuditUserHis.setIs_end(1);
				kAuditUserHis.setAudit_data_desc(str_data);
				kAuditUserHis.setAudit_status(20300L);
			} else {
				entity.setArc_state(20110);

				// 历史记录表
				kAuditUserHis.setIs_end(0);
				kAuditUserHis.setAudit_next_node_id(auditNote.getAudit_next_node_id());
				kAuditUserHis.setAudit_next_node_name(auditNote.getAudit_next_node_name());
				kAuditUserHis.setAudit_status(20110L);
			}
		}
		// 驳回
		if ("2".equals(audit_status)) {

			if (!GenericValidator.isLong(audit_user_role)) {
				String msg = super.getMessage(request, "errors.parm");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			}

			KonkaXxAuditNote auditNote = new KonkaXxAuditNote();
			auditNote.setAudit_type(20L);
			auditNote.setAudit_node_id(Long.valueOf(audit_user_role));
			auditNote = super.getFacade().getKonkaXxAuditNoteService().getKonkaXxAuditNote(auditNote);

			if (null == auditNote) {
				String msg = super.getMessage(request, "entity.missing");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			}

			// 历史记录表
			kAuditUserHis.setAudit_status_desc("驳回");
			kAuditUserHis.setAudit_desc("备案申请");
			kAuditUserHis.setAudit_type(20000L);
			kAuditUserHis.setAudit_user_id(user_id.getId());
			kAuditUserHis.setAudit_user_name(user_id.getUser_name());
			kAuditUserHis.setAudit_date(new Date());
			if (auditNote.getAudit_seq() == 0) {
				kAuditUserHis.setAudit_status(20110L);
				kAuditUserHis.setAudit_next_node_id(auditNote.getAudit_next_node_id());
				kAuditUserHis.setAudit_next_node_name(auditNote.getAudit_next_node_name());
			} else {
				kAuditUserHis.setAudit_status(20100L);
				kAuditUserHis.setAudit_next_node_id(auditNote.getAudit_node_id());
				kAuditUserHis.setAudit_next_node_name(auditNote.getAudit_node_name());
			}
			entity.setArc_state(20200);
		}

		if (StringUtils.isNotBlank(audit_comment)) {
			entity.setAudit_comment(audit_comment);
			kAuditUserHis.setAudit_option(audit_comment);
		}
		entity.setZmd_user_id(konkaXxZmd.getZmd_user_id());
		entity.setAudit_user_id(user_id.getId());
		entity.setAudit_user_name(user_id.getUser_name());
		entity.setAudit_date(new Date());

		entity.setKonkaXxZmdAuditUserHis(kAuditUserHis);

		// 邮件发送
		// start
		if ("1".equals(is_send_mail)) {
			String user_ids = "";
			if (StringUtils.isNotBlank(link_user_ids)) {
				user_ids = link_user_ids.substring(0, link_user_ids.length() - 1);
			} else {
				user_ids = "-1";
			}

			PeProdUser pUser = new PeProdUser();
			pUser.getMap().put("user_id_str", user_ids);
			pUser.getMap().put("zmd_user_id", konkaXxZmd.getZmd_user_id());
			List<PeProdUser> pUsersList = super.getFacade().getPeProdUserService().getPeProdUserList(pUser);

			if (pUsersList.size() > 0) {

				// 分公司
				KonkaDept kDept = new KonkaDept();
				kDept.setDept_id(konkaXxZmd.getDept_id());
				kDept = super.getFacade().getKonkaDeptService().getKonkaDept(kDept);

				// 地址
				BaseProvinceListFour bListFour = new BaseProvinceListFour();
				bListFour.setP_index(konkaXxZmd.getP_index());
				List<BaseProvinceListFour> bListFourList = super.getFacade().getBaseProvinceListFourService()
						.getBaseProvinceListFourList(bListFour);

				// 经营性质
				KonkaXxBaseType kType = new KonkaXxBaseType();
				kType.setType_id(konkaXxZmd.getBusi_type());
				kType = super.getFacade().getKonkaXxBaseTypeService().getKonkaXxBaseType(kType);

				Map<String, Object> model = new HashMap<String, Object>();
				model.put("date", new Date());
				model.put("zmd_sn", konkaXxZmd.getZmd_sn());
				model.put("ku_name", konkaXxZmd.getHost_name());
				model.put("ku_tel", konkaXxZmd.getHost_phone());
				model.put("send_name", "渠道管理系统");
				if (null != kDept) {
					model.put("dept_name", kDept.getDept_name());
				}
				if (bListFourList.size() > 0) {
					model.put("p_name", bListFourList.get(0).getP_name() + konkaXxZmd.getAddr());
				}
				if (null != kType) {
					model.put("busi_name", kType.getType_name());
				}
				model.put("yj_ed", konkaXxZmd.getMoney_of_dm_apply());

				for (PeProdUser temp1 : pUsersList) {
					if (null != temp1.getEmail() && !"".equals(temp1.getEmail())) {
						model.put("user_name", temp1.getReal_name());
						SimpleMailMessage mailMessage = new SimpleMailMessage();
						mailMessage.setFrom("qdglxxxt@konka.com");
						mailMessage.setTo(temp1.getEmail());
						mailMessage.setSubject("专卖店备案：" + konkaXxZmd.getZmd_sn() + "专卖店备案信息");
						try {
							super.getFacade().getMailService().send(mailMessage, "mail/konkazmdaudit.ftl", model);
						} catch (Exception e) {
							continue;
						}
					}
				}
			}

		}
		// end

		super.getFacade().getKonkaXxZmdService().modifyKonkaXxZmdForHis(entity);
		saveMessage(request, "entity.inserted_success");

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append("KonkaXxZmdAuditUserInfoAudit.do?");
		pathBuffer.append("&method=list");
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		return forward;
	}

	public ActionForward toEm(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("date", new Date());
		model.put("zmd_sn", "CSZMD0001");
		model.put("ku_name", "张三");
		model.put("ku_tel", "13855126032");
		model.put("send_name", "渠道管理系统");
		model.put("dept_name", "西安");
		model.put("p_name", "西安市长丰路3003");
		model.put("busi_name", "返利模式");
		model.put("yj_ed", 3);
		model.put("user_name", "古城");

		String[] arrName = { "zhouhaojie@konka.com", "453412029@qq.com","" };
		for (int i = 0; i < arrName.length; i++) {
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setFrom("qdglxxxt@konka.com");
			// mailMessage.setTo("zhouhaojie@konka.com");
			logger.info("arr=============={}", arrName[i]);
			mailMessage.setTo(arrName[i]);
			mailMessage.setSubject("专卖店备案：张三专卖店备案信息");
			// try {
			super.getFacade().getMailService().send(mailMessage, "mail/konkazmdaudit.ftl", model);
			// } catch (Exception e) {
			// continue;
			// }
		}
		return null;
	}

}
