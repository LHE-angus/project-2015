package com.ebiz.mmt.web.struts.inter.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.alibaba.fastjson.JSON;
import com.ebiz.mmt.domain.KonkaInterfaceAccessLog;
import com.ebiz.mmt.domain.KonkaInterfaceBindsUser;
import com.ebiz.mmt.domain.KonkaOrderAuditProcess;
import com.ebiz.mmt.domain.KonkaOrderAuditProcessNode;
import com.ebiz.mmt.domain.KonkaOrderInfo;
import com.ebiz.mmt.domain.KonkaOrderInfoAudit;
import com.ebiz.mmt.web.struts.inter.form.InterUser;
import com.ebiz.mmt.web.struts.inter.form.KonkaOrderApplyNumMoneyForm;
import com.ebiz.mmt.web.struts.inter.form.KonkaOrderAuditForm;
import com.ebiz.mmt.web.struts.inter.form.KonkaOrderAuditProcessForm;
import com.ebiz.mmt.web.struts.inter.form.KonkaOrderProcessForm;
import com.ebiz.mmt.web.struts.inter.form.KonkaOrderProcessNodeForm;
import com.ebiz.mmt.web.util.IpUtils;
import com.ebiz.org.apache.commons.lang3.StringUtils;

/**
 * @author Wang,KunLin
 * @version 2014-09-24
 * @see 客户审核数据相关信息数据
 */
public class KonkaOIAPInterfaceAction extends BaseInterAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String order_id = (String) dynaBean.get("order_id");// 订单号
		// String trade_index = (String)dynaBean.get("trade_index");//订单流水号

		InterUser user = super.getInterUser(form, request);

		KonkaInterfaceBindsUser entity = new KonkaInterfaceBindsUser();
		entity.setUser_id(user.getUser_id());
		entity.setLicenses_sn(user.getLicenses_sn());
		entity.setUser_key(user.getUser_key());
		entity = super.getFacade().getKonkaInterfaceBindsUserService().getKonkaInterfaceBindsUser(entity);

		// 处理具体数据
		KonkaOrderAuditProcessForm obj = new KonkaOrderAuditProcessForm();

		// 如果不是授权的账号不予进行后续操作
		if (null == entity) {
			obj.setReturn_state(1);
			obj.setReturn_error("user_id user_key licenses_sn does not match...");
			KonkaInterfaceAccessLog log = new KonkaInterfaceAccessLog();
			log.setAdd_date(new Date());
			log.setError_msg("用户授权验证不通过");
			log.setIp(IpUtils.getRemortIP(request));
			log.setLicenses_sn(user.getLicenses_sn());
			log.setReq_state(1);
			log.setReq_url(request.getRequestURL().toString() + "?" + request.getQueryString());
			log.setReq_mod_name("查询订单的审核信息-申请信息-流程信息");
			log.setUser_id(user.getUser_id());
			log.setUser_key(user.getUser_key());
			super.getFacade().getKonkaInterfaceAccessLogService().createKonkaInterfaceAccessLog(log);
			super.renderJson(response, JSON.toJSONString(obj));
			return null;
		}

		// 订单号码或者流水号必须传一个
		if (StringUtils.isBlank(order_id)) {
			obj.setReturn_state(1);
			obj.setReturn_error("args order_id  is must...");
			KonkaInterfaceAccessLog log = new KonkaInterfaceAccessLog();
			log.setAdd_date(new Date());
			log.setError_msg("查询订单的审核信息-申请信息-流程信息没有传入参数");
			log.setIp(IpUtils.getRemortIP(request));
			log.setLicenses_sn(entity.getLicenses_sn());
			log.setReq_state(1);
			log.setReq_url(request.getRequestURL().toString() + "?" + request.getQueryString());
			log.setReq_mod_name("查询订单的审核信息-申请信息-流程信息");
			log.setUser_id(user.getUser_id());
			log.setUser_key(user.getUser_key());
			super.getFacade().getKonkaInterfaceAccessLogService().createKonkaInterfaceAccessLog(log);
			request.setAttribute("errorMsg", "订单ID为空！");
			return mapping.findForward("errorMsg");
		}

		KonkaOrderInfo KonkaOrderInfo = new KonkaOrderInfo();
		// if(StringUtils.isNotBlank(trade_index) ){//传入流水号
		// KonkaOrderInfo.setTrade_index(trade_index);
		// }
		if (StringUtils.isNotBlank(order_id) && GenericValidator.isLong(order_id)) {// 传入订单号
			KonkaOrderInfo.setId(Long.valueOf(order_id));
		}
		KonkaOrderInfo = super.getFacade().getKonkaOrderInfoService().getKonkaOrderInfo(KonkaOrderInfo);
		if (null == KonkaOrderInfo) {
			obj.setReturn_state(1);
			obj.setReturn_error("the Order does not exist...");
			KonkaInterfaceAccessLog log = new KonkaInterfaceAccessLog();
			log.setAdd_date(new Date());
			log.setError_msg("查询订单的审核信息-申请信息-流程信息，传入的订单ID找不到对应订单");
			log.setIp(IpUtils.getRemortIP(request));
			log.setLicenses_sn(entity.getLicenses_sn());
			log.setReq_state(1);
			log.setReq_url(request.getRequestURL().toString() + "?" + request.getQueryString());
			log.setReq_mod_name("查询订单的审核信息-申请信息-流程信息");
			log.setUser_id(user.getUser_id());
			log.setUser_key(user.getUser_key());
			super.getFacade().getKonkaInterfaceAccessLogService().createKonkaInterfaceAccessLog(log);

			request.setAttribute("errorMsg", "没有找到订单ID对应的记录！");
			return mapping.findForward("errorMsg");
		}
		request.setAttribute("order", KonkaOrderInfo);
		// 申请信息
		KonkaOrderApplyNumMoneyForm numMoney = new KonkaOrderApplyNumMoneyForm();
		numMoney.setOrderapplynum(null == KonkaOrderInfo.getOrder_num() ? 0 : KonkaOrderInfo.getOrder_num());
		numMoney.setOrderapplymoney(null == KonkaOrderInfo.getMoney() ? new BigDecimal(0) : KonkaOrderInfo.getMoney());
		obj.setApplyNumMoney(numMoney);

		// 流程信息
		KonkaOrderAuditProcess auditProcess = new KonkaOrderAuditProcess();
		if (null != KonkaOrderInfo.getProcess_id()) {
			auditProcess.setId(KonkaOrderInfo.getProcess_id());

			auditProcess = super.getFacade().getKonkaOrderAuditProcessService().getKonkaOrderAuditProcess(auditProcess);
			if (null != auditProcess) {
				KonkaOrderProcessForm processForm = new KonkaOrderProcessForm();
				processForm.setId(auditProcess.getId());
				processForm.setAdd_date(auditProcess.getAdd_date());
				processForm.setAdd_user_id(auditProcess.getAdd_user_id());
				processForm.setAdd_dept_id(auditProcess.getAdd_dept_id());
				processForm.setDel_user_id(auditProcess.getDel_user_id());
				processForm.setDel_date(auditProcess.getDel_date());
				processForm.setIs_del(auditProcess.getIs_del());
				processForm.setAdd_user_name(auditProcess.getAdd_user_name());
				processForm.setAdd_dept_name(auditProcess.getAdd_dept_name());

				processForm.setProcess_desc(auditProcess.getProcess_desc());
				processForm.setCustomer_type(auditProcess.getCustomer_type());
				processForm.setIs_stop(auditProcess.getIs_stop());
				processForm.setUsed_field(auditProcess.getUsed_field());
				processForm.setUsed_field_name("正常订单");
				if (null != auditProcess.getUsed_field() && 1 == auditProcess.getUsed_field()) {
					processForm.setUsed_field_name("变更订单");
				}
				obj.setProcess(processForm);
			}
		}
		// 流程节点信息
		KonkaOrderAuditProcessNode processnode = new KonkaOrderAuditProcessNode();
		if (null != KonkaOrderInfo.getProcess_id()) {
			processnode.setAudit_proces_id(KonkaOrderInfo.getProcess_id());

			List<KonkaOrderAuditProcessNode> processnodeList = super.getFacade().getKonkaOrderAuditProcessNodeService()
					.getKonkaOrderAuditProcessNodeList(processnode);

			if (null != processnodeList && processnodeList.size() > 0) {
				List<KonkaOrderProcessNodeForm> processnodeFormList = new ArrayList<KonkaOrderProcessNodeForm>();
				for (KonkaOrderAuditProcessNode node : processnodeList) {
					KonkaOrderProcessNodeForm nodeform = new KonkaOrderProcessNodeForm();
					nodeform.setId(node.getId());
					nodeform.setIs_del(node.getIs_del());
					nodeform.setIs_del_name("未删除");
					if (null != node.getIs_del() && 1 == node.getIs_del()) {
						nodeform.setIs_del_name("已删除");
					}
					nodeform.setDel_date(node.getDel_date());
					nodeform.setDel_user_id(node.getDel_user_id());
					nodeform.setAdd_dept_id(node.getAdd_dept_id());
					nodeform.setAdd_user_id(node.getAdd_user_id());
					nodeform.setAdd_date(node.getAdd_date());
					nodeform.setIs_update_authority(node.getIs_update_authority());
					nodeform.setAudit_level(node.getAudit_level());
					nodeform.setRole_name(node.getRole_name());
					nodeform.setRole_id(node.getRole_id());
					nodeform.setAudit_proces_id(node.getAudit_proces_id());
					nodeform.setAdd_user_name(node.getAdd_user_name());
					nodeform.setNext_node(node.getNext_node());
					nodeform.setAudit_proc_cond(node.getAudit_proc_cond());
					processnodeFormList.add(nodeform);
				}
				obj.setProcessNodeList(processnodeFormList);
			}
		}
		// 审核历史记录信息
		KonkaOrderInfoAudit konkaOrderInfoAudit = new KonkaOrderInfoAudit();
		if (null != KonkaOrderInfo.getId()) {
			konkaOrderInfoAudit.setLink_id(KonkaOrderInfo.getId());
			List<KonkaOrderInfoAudit> auditList = super.getFacade().getKonkaOrderInfoAuditService()
					.getKonkaOrderInfoAuditList(konkaOrderInfoAudit);
			if (null != auditList && auditList.size() > 0) {
				List<KonkaOrderAuditForm> additFormList = new ArrayList<KonkaOrderAuditForm>();
				for (KonkaOrderInfoAudit audit : auditList) {
					KonkaOrderAuditForm auditform = new KonkaOrderAuditForm();
					auditform.setId(audit.getId());
					auditform.setCur_node_id(audit.getCur_node_id());
					auditform.setNext_node_id(audit.getNext_node_id());
					auditform.setAgent_audit_dept_name(audit.getAgent_audit_dept_name());
					auditform.setAgent_audit_dept_id(audit.getAgent_audit_dept_id());
					auditform.setAudit_dept_name(audit.getAudit_dept_name());
					auditform.setAudit_dept_id(audit.getAudit_dept_id());

					auditform.setAudit_result(audit.getAudit_result());
					if (null != audit.getAudit_result()) {
						switch (audit.getAudit_result()) {
						case 1:
							auditform.setAudit_result_name("审核通过");
						case -1:
							auditform.setAudit_result_name("驳回（至审核人）");
						case 0:
							auditform.setAudit_result_name("驳回（至制单）");
						case -9:
							auditform.setAudit_result_name("（客户）撤销");
						case 10:
							auditform.setAudit_result_name("订单变更");
						}
					}
					auditform.setAgent_audit_user(audit.getAgent_audit_user());
					auditform.setAgent_audit_user_id(audit.getAgent_audit_user_id());
					auditform.setAudit_datetime(audit.getAudit_datetime());
					auditform.setAudit_comment(audit.getAudit_comment());
					auditform.setAudit_user(audit.getAudit_user());
					auditform.setAudit_user_id(audit.getAudit_user_id());
					auditform.setAudit_level(audit.getAudit_level());

					auditform.setAudit_type(audit.getAudit_type());

					auditform.setLink_id(audit.getLink_id());

					additFormList.add(auditform);
				}
				obj.setAuditList(additFormList);
			}
		}

		// 记录日志
		KonkaInterfaceAccessLog log = new KonkaInterfaceAccessLog();
		log.setAdd_date(new Date());
		// log.setError_msg("查询订单的审核信息-申请信息-流程信息信息查询成功");
		log.setIp(IpUtils.getRemortIP(request));
		log.setLicenses_sn(entity.getLicenses_sn());
		log.setReq_state(0);
		log.setReq_url(request.getRequestURL().toString() + "?" + request.getQueryString());
		log.setReq_mod_name("查询订单的审核信息-申请信息-流程信息");
		log.setUser_id(user.getUser_id());
		log.setUser_key(user.getUser_key());
		super.getFacade().getKonkaInterfaceAccessLogService().createKonkaInterfaceAccessLog(log);

		// 设置订单进度条
		setOrderProgress(form, request);

		// obj.setReturn_state(0);
		// super.renderJson(response, JSON.toJSONString(obj));
		// return null;
		super.copyProperties(form, obj);
		return mapping.findForward("list");
	}
}
