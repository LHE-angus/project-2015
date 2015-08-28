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
import com.ebiz.mmt.domain.BranchAssign;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaInterfaceAccessLog;
import com.ebiz.mmt.domain.KonkaInterfaceBindsUser;
import com.ebiz.mmt.domain.KonkaOrderAuditProcess;
import com.ebiz.mmt.domain.KonkaOrderInfo;
import com.ebiz.mmt.domain.KonkaOrderInfoDetails;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.struts.inter.form.InterUser;
import com.ebiz.mmt.web.struts.inter.form.KonkaOrderInfoDetailsSearch;
import com.ebiz.mmt.web.struts.inter.form.KonkaOrderInfoViewForm;
import com.ebiz.mmt.web.util.IpUtils;
import com.ebiz.org.apache.commons.lang3.StringUtils;

public class KonkaOrderInfoViewAction extends BaseInterAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String order_id = (String) dynaBean.get("order_id");// 客户R3编码
		InterUser user = super.getInterUser(form, request);

		KonkaInterfaceBindsUser bingdsuser = new KonkaInterfaceBindsUser();
		bingdsuser.setUser_id(user.getUser_id());
		bingdsuser.setLicenses_sn(user.getLicenses_sn());
		bingdsuser.setUser_key(user.getUser_key());

		bingdsuser = super.getFacade().getKonkaInterfaceBindsUserService().getKonkaInterfaceBindsUser(bingdsuser);
		KonkaOrderInfoViewForm obj = new KonkaOrderInfoViewForm();
		if (null == bingdsuser) {
			KonkaInterfaceAccessLog log = new KonkaInterfaceAccessLog();
			log.setAdd_date(new Date());
			log.setError_msg("未找到绑定数据");
			log.setIp(IpUtils.getRemortIP(request));
			log.setLicenses_sn(user.getLicenses_sn());
			log.setReq_state(1);
			log.setReq_url(request.getRequestURL().toString() + "?" + request.getQueryString());
			log.setReq_mod_name("订单详情页面接口");
			log.setUser_id(user.getUser_id());
			log.setUser_key(user.getUser_key());
			super.getFacade().getKonkaInterfaceAccessLogService().createKonkaInterfaceAccessLog(log);
			obj.setReturn_state(1);
			obj.setReturn_error("user_id user_key licenses_sn does not match...");
			super.renderJson(response, JSON.toJSONString(obj));
			return null;
		}
		if (StringUtils.isBlank(order_id) || !GenericValidator.isLong(order_id)) {
			KonkaInterfaceAccessLog log = new KonkaInterfaceAccessLog();
			log.setAdd_date(new Date());
			log.setError_msg("order_id is null");
			log.setIp(IpUtils.getRemortIP(request));
			log.setLicenses_sn(user.getLicenses_sn());
			log.setReq_state(1);
			log.setReq_url(request.getRequestURL().toString() + "?" + request.getQueryString());
			log.setReq_mod_name("订单详情页面接口");
			log.setUser_id(user.getUser_id());
			log.setUser_key(user.getUser_key());
			super.getFacade().getKonkaInterfaceAccessLogService().createKonkaInterfaceAccessLog(log);
			obj.setReturn_state(1);
			obj.setReturn_error("order_id is not null...");
			request.setAttribute("errorMsg", "订单ID为空！");
			return mapping.findForward("errorMsg");
		}
		order_id = changeToUTF8(order_id);
		KonkaOrderInfo order = new KonkaOrderInfo();
		order.setId(Long.valueOf(order_id));
		order = super.getFacade().getKonkaOrderInfoService().getKonkaOrderInfo(order);
		if (null == order) {
			KonkaInterfaceAccessLog log = new KonkaInterfaceAccessLog();
			log.setAdd_date(new Date());
			log.setError_msg("未查询到记录");
			log.setIp(IpUtils.getRemortIP(request));
			log.setLicenses_sn(user.getLicenses_sn());
			log.setReq_state(1);
			log.setReq_url(request.getRequestURL().toString() + "?" + request.getQueryString());
			log.setReq_mod_name("订单详情页面接口");
			log.setUser_id(user.getUser_id());
			log.setUser_key(user.getUser_key());
			super.getFacade().getKonkaInterfaceAccessLogService().createKonkaInterfaceAccessLog(log);
			obj.setReturn_state(1);
			obj.setReturn_error("order is error");
			request.setAttribute("errorMsg", "没有找到订单ID对应的记录！");
			return mapping.findForward("errorMsg");
		}

		obj.setTrade_index(order.getTrade_index());
		obj.setCustmomer_name(order.getUser_shop_name());
		obj.setR3_code(order.getAg());

		// 添加流程---------start---------------
		KonkaDept dept = super.getKonkaDeptByCustomerId(Long.valueOf(order.getCust_id()));
		if (dept != null) {
			KonkaOrderAuditProcess process = new KonkaOrderAuditProcess();
			process.getMap().put("par_add_dept_id", dept.getDept_id());
			process.setIs_del(0);
			List<KonkaOrderAuditProcess> processList = super.getFacade().getKonkaOrderAuditProcessService()
					.getKonkaOrderAuditProcessList(process);

			KonkaOrderAuditProcess ap_public = new KonkaOrderAuditProcess();
			ap_public.setAdd_dept_id(0L);
			ap_public.setIs_del(0);
			List<KonkaOrderAuditProcess> ap_publicauditProcesseList = getFacade().getKonkaOrderAuditProcessService()
					.getKonkaOrderAuditProcessList(ap_public);
			processList.addAll(ap_publicauditProcesseList);
			if (null != processList && processList.size() > 0) {
				String process_name = "";
				for (KonkaOrderAuditProcess pces : processList) {
					if (order != null && pces != null && pces.getId() != null && order.getProcess_id() != null) {
						if (order.getProcess_id().longValue() == pces.getId().longValue()) {
							if (pces.getAdd_dept_id() == 0) {
								process_name = process_name + "★[统一流程] ";
							}
							if (pces.getAdd_dept_id() != 0) {
								process_name = process_name + pces.getAdd_dept_name();
							}
							process_name = process_name + pces.getProcess_desc();
						}
					}
				}
				if (StringUtils.isNotEmpty(process_name)) {
					obj.setProcess_name(process_name);
				} else {
					obj.setProcess_name("未确定订单流程");
				}
			} else {
				obj.setProcess_name("未确定订单流程");
			}
		}
		// 添加流程---------end---------------
		if (null != order.getAudit_state()) {
			if (0 == order.getAudit_state()) {
				obj.setOrder_state("未审核");
			} else if (1 == order.getAudit_state()) {
				obj.setOrder_state("审核中");
			} else if (2 == order.getAudit_state()) {
				obj.setOrder_state("审核未通过");
			} else if (3 == order.getAudit_state()) {
				obj.setOrder_state("审核通过");
			} else if (4 == order.getAudit_state()) {
				obj.setOrder_state("订单已作废");
			}
		}
		obj.setMake_order_user_name(order.getAdd_user_name());
		obj.setThird_cg_order_num(order.getThird_cg_order_num());
		obj.setAdd_date(order.getAdd_date());
		obj.setOrder_date(order.getOrder_date());
		if (null == order.getIs_delivered()) {
			obj.setIs_delivered("出货中");
		} else if (0 == order.getIs_delivered()) {
			obj.setIs_delivered("未发货");
		} else if (1 == order.getIs_delivered()) {
			obj.setIs_delivered("已发货");
		}
		/** 取网点业务员 */
		BranchAssign bagn = new BranchAssign();
		bagn.setKonka_r3_id(order.getCust_id());
		bagn = getFacade().getBranchAssignService().getBranchAssign(bagn);
		if (null != bagn && null != bagn.getUser_id()) {
			PeProdUser ywy = new PeProdUser();
			ywy.setId(bagn.getUser_id());
			ywy = getFacade().getPeProdUserService().getPeProdUserResult(ywy);
			obj.setYwy_user_name(ywy.getReal_name());
		}
		obj.setCg_order_date(order.getCg_order_date());
		obj.setRemark(order.getRemark());
		obj.setOrder_num(order.getOrder_num());
		obj.setMoney(order.getMoney());
		if (null != order.getPay_type()) {
			if (4 == order.getPay_type()) {
				obj.setPay_type_name("现汇");
			} else if (5 == order.getPay_type()) {
				obj.setPay_type_name("帐期");
			} else if (6 == order.getPay_type()) {
				obj.setPay_type_name("承兑");
			} else if (45 == order.getPay_type()) {
				obj.setPay_type_name("现汇、帐期");
			} else if (46 == order.getPay_type()) {
				obj.setPay_type_name("现汇、承兑");
			} else if (56 == order.getPay_type()) {
				obj.setPay_type_name("帐期、承兑");
			} else if (456 == order.getPay_type()) {
				obj.setPay_type_name("现汇、帐期、承兑");
			}
		}
		if (null != order.getSend_type()) {
			if (1 == order.getSend_type()) {
				obj.setSend_type_name("自提");
			} else if (2 == order.getSend_type()) {
				obj.setSend_type_name("配送");
			}
		}
		obj.setUser_tel(order.getUser_tel() != null ? order.getUser_tel() : "未填写");
		String full_name = super.getPIndexName(order.getUser_p_index(), "");
		obj.setFullName(StringUtils.isNotEmpty(full_name) ? full_name : "未填写");
		obj.setUser_addr(order.getUser_addr() != null ? order.getUser_addr() : "未填写");
		obj.setUser_remark(order.getUser_remark() != null ? order.getUser_remark() : "未填写");
		obj.setUser_name(order.getUser_name() != null ? order.getUser_name() : "未填写");
		obj.setUser_phone(order.getUser_phone() != null ? order.getUser_phone() : "未填写");
		obj.setDoc_type(order.getDoc_type());
		obj.setFxqd(10);
		obj.setAg(order.getAg());
		obj.setRg(order.getRg());
		obj.setSales_org(order.getSales_org());
		obj.setProduct_group("10");
		obj.setRe(order.getRe());
		obj.setWe(order.getWe());

		KonkaOrderInfoDetails infodetails = new KonkaOrderInfoDetails();
		infodetails.setOrder_id(order.getId());
		List<KonkaOrderInfoDetails> detailsList = super.getFacade().getKonkaOrderInfoDetailsService()
				.getKonkaOrderInfoDetailsList(infodetails);
		List<KonkaOrderInfoDetailsSearch> dsList = new ArrayList<KonkaOrderInfoDetailsSearch>();
		long good_count = 0;
		BigDecimal apply_total_money = new BigDecimal("0");
		if (null != detailsList && detailsList.size() > 0) {
			for (KonkaOrderInfoDetails details : detailsList) {
				if (null != details) {
					KonkaOrderInfoDetailsSearch detailsSearch = new KonkaOrderInfoDetailsSearch();
					detailsSearch.setPd_name(details.getPd_name());
					detailsSearch.setGood_count(details.getGood_count());
					detailsSearch.setGood_unit(details.getGood_unit());
					detailsSearch.setGood_price(details.getGood_price());
					detailsSearch.setGood_sum_price(details.getGood_sum_price());
					detailsSearch.setGood_discount_price(details.getGood_discount_price());
					detailsSearch.setGood_discount(details.getGood_discount());
					detailsSearch.setStore_key(details.getStore_key());
					detailsSearch.setPd_remark(details.getPd_remark());
					good_count += details.getGood_count().longValue();
					apply_total_money = apply_total_money.add(new BigDecimal(details.getGood_count()).multiply(details
							.getGood_price()));
					detailsSearch
							.setGood_discount_sum_price(details
									.getGood_sum_price()
									.multiply(
											details.getGood_discount() != null ? details.getGood_discount()
													: new BigDecimal(0))
									.divide(new BigDecimal(100))
									.add(details.getGood_discount_price() != null ? details.getGood_discount_price()
											: new BigDecimal(0))
									.add(details.getGood_sum_price() != null ? details.getGood_sum_price()
											: new BigDecimal(0)));
					dsList.add(detailsSearch);
				}
			}
		}
		obj.setApply_number(new Long(Long.toString(good_count)));
		obj.setApply_total_money(apply_total_money);
		obj.setKonkaOrderInfoDetailsSearchList(dsList);

		KonkaInterfaceAccessLog log = new KonkaInterfaceAccessLog();
		log.setAdd_date(new Date());
		log.setIp(IpUtils.getRemortIP(request));
		log.setLicenses_sn(user.getLicenses_sn());
		log.setReq_state(0);
		log.setReq_url(request.getRequestURL().toString() + "?" + request.getQueryString());
		log.setReq_mod_name("订单详情页面接口");
		log.setUser_id(user.getUser_id());
		log.setUser_key(user.getUser_key());
		super.getFacade().getKonkaInterfaceAccessLogService().createKonkaInterfaceAccessLog(log);
		// obj.setReturn_state(0);
		// super.renderJson(response, JSON.toJSONString(obj));
		super.copyProperties(form, obj);
		return mapping.findForward("view");
	}
}
