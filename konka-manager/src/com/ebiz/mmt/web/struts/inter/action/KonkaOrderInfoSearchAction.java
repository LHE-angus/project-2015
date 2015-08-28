package com.ebiz.mmt.web.struts.inter.action;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaInterfaceAccessLog;
import com.ebiz.mmt.domain.KonkaInterfaceBindsUser;
import com.ebiz.mmt.domain.KonkaOrderInfo;
import com.ebiz.mmt.domain.KonkaR3Zsof;
import com.ebiz.mmt.web.struts.inter.form.InterUser;
import com.ebiz.mmt.web.struts.inter.form.KonkaOrderInfoSearch;
import com.ebiz.mmt.web.struts.inter.form.KonkaOrderInfoSearchForm;
import com.ebiz.mmt.web.util.IpUtils;
import com.ebiz.org.apache.commons.lang3.StringUtils;

public class KonkaOrderInfoSearchAction extends BaseInterAction {
	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String r3_code = (String) dynaBean.get("r3_code");// 客户R3编码
		String order_date_start = (String) dynaBean.get("order_date_start");
		String order_date_end = (String) dynaBean.get("order_date_end");
		InterUser user = super.getInterUser(form, request);

		KonkaInterfaceBindsUser bingdsuser = new KonkaInterfaceBindsUser();
		bingdsuser.setUser_id(user.getUser_id());
		bingdsuser.setLicenses_sn(user.getLicenses_sn());
		bingdsuser.setUser_key(user.getUser_key());

		bingdsuser = super.getFacade().getKonkaInterfaceBindsUserService().getKonkaInterfaceBindsUser(bingdsuser);
		KonkaOrderInfoSearchForm obj = new KonkaOrderInfoSearchForm();
		if (null == bingdsuser) {
			KonkaInterfaceAccessLog log = new KonkaInterfaceAccessLog();
			log.setAdd_date(new Date());
			log.setError_msg("未找到绑定数据");
			log.setIp(IpUtils.getRemortIP(request));
			log.setLicenses_sn(user.getLicenses_sn());
			log.setReq_state(1);
			log.setReq_url(request.getRequestURL().toString() + "?" + request.getQueryString());
			log.setReq_mod_name("订单查询接口");
			log.setUser_id(user.getUser_id());
			log.setUser_key(user.getUser_key());
			super.getFacade().getKonkaInterfaceAccessLogService().createKonkaInterfaceAccessLog(log);
			// obj.setReturn_state(1);
			// obj.setReturn_error("user_id user_key licenses_sn does not match...");
			// super.renderJson(response, JSON.toJSONString(obj));
			// return null;
			request.setAttribute("errorMsg", "未找到绑定数据！");
			return mapping.findForward("errorMsg");
		}
		if (StringUtils.isBlank(r3_code)) {
			KonkaInterfaceAccessLog log = new KonkaInterfaceAccessLog();
			log.setAdd_date(new Date());
			log.setError_msg("R3_CODE is null");
			log.setIp(IpUtils.getRemortIP(request));
			log.setLicenses_sn(user.getLicenses_sn());
			log.setReq_state(1);
			log.setReq_url(request.getRequestURL().toString() + "?" + request.getQueryString());
			log.setReq_mod_name("订单查询接口");
			log.setUser_id(user.getUser_id());
			log.setUser_key(user.getUser_key());
			super.getFacade().getKonkaInterfaceAccessLogService().createKonkaInterfaceAccessLog(log);
			obj.setReturn_state(1);
			obj.setReturn_error("r3_code is not null...");
			// super.renderJson(response, JSON.toJSONString(obj));
			// return null;
			request.setAttribute("errorMsg", "客户编码为空！");
			return mapping.findForward("errorMsg");
		}
		r3_code = changeToUTF8(r3_code);

		// 默认显示当前1月的时间区间
		Date today = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(today);
		calendar.add(Calendar.MONTH, -1);
		String day_first = df.format(calendar.getTime());
		String day_last = df.format(today);

		KonkaOrderInfo entity = new KonkaOrderInfo();
		entity.setAg(r3_code);
		entity.setIs_submit(1); // 0:表示暂存；1：表示正式提交
		entity.setIs_del(0);// 0:表示未删除；1：表示删除

		Pattern p = Pattern.compile("\\d{4}+[-]\\d{1,2}+[-]\\d{1,2}+");
		if (StringUtils.isNotBlank(order_date_start)) {
			Matcher m = p.matcher(order_date_start);
			if (!m.matches()) {
				request.setAttribute("errorMsg", "时间格式不正确！");
				return mapping.findForward("errorMsg");
			}
			entity.getMap().put("start_date", order_date_start + " 00:00:00");
			request.setAttribute("start_date", order_date_start);
		} else {
			entity.getMap().put("start_date", day_first + " 00:00:00");
			request.setAttribute("start_date", day_first);
		}
		if (StringUtils.isNotBlank(order_date_end)) {
			Matcher m = p.matcher(order_date_end);
			if (!m.matches()) {
				request.setAttribute("errorMsg", "时间格式不正确！");
				return mapping.findForward("errorMsg");
			}
			entity.getMap().put("end_date", order_date_end + " 23:59:59");
			request.setAttribute("end_date", order_date_end);
		} else {
			entity.getMap().put("end_date", day_last + " 23:59:59");
			request.setAttribute("end_date", day_last);
		}

		Long recordCount = super.getFacade().getKonkaOrderInfoService().getKonkaOrderInfoForIhsCount(entity);
		// Long recordCount =
		// super.getFacade().getKonkaOrderInfoService().getKonkaOrderInfoAndNextRoleCount(entity);
		if (recordCount > 500) {
			KonkaInterfaceAccessLog log = new KonkaInterfaceAccessLog();
			log.setAdd_date(new Date());
			log.setError_msg("查询数据超过500条");
			log.setIp(IpUtils.getRemortIP(request));
			log.setLicenses_sn(user.getLicenses_sn());
			log.setReq_state(1);
			log.setReq_url(request.getRequestURL().toString() + "?" + request.getQueryString());
			log.setReq_mod_name("订单查询接口");
			log.setUser_id(user.getUser_id());
			log.setUser_key(user.getUser_key());
			super.getFacade().getKonkaInterfaceAccessLogService().createKonkaInterfaceAccessLog(log);
			obj.setReturn_state(1);
			obj.setReturn_error("display up to 500 data...");
			// super.renderJson(response, JSON.toJSONString(obj));
			// return null;
			request.setAttribute("errorMsg", "查询数据超过500条！");
			return mapping.findForward("errorMsg");
		}
		entity.getRow().setFirst(0);
		entity.getRow().setCount(recordCount.intValue());
		List<KonkaOrderInfo> entityList = super.getFacade().getKonkaOrderInfoService()
				.getKonkaOrderInfoForIhsPaginatedList(entity);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		List<KonkaOrderInfoSearch> searchList = new ArrayList<KonkaOrderInfoSearch>();
		if (null != entityList && entityList.size() > 0) {
			for (KonkaOrderInfo konkaOrderInfo : entityList) {
				if (null != konkaOrderInfo) {
					KonkaOrderInfoSearch search = new KonkaOrderInfoSearch();
					search.setId(konkaOrderInfo.getId());
					search.setTrade_index(konkaOrderInfo.getTrade_index());
					search.setOrder_date(format.format(konkaOrderInfo.getOrder_date()));
					search.setR3_code(konkaOrderInfo.getAg());
					search.setCustomer_name(konkaOrderInfo.getUser_shop_name());
					search.setCustomer_type_name(konkaOrderInfo.getMap().get("customer_type_name") == null ? ""
							: konkaOrderInfo.getMap().get("customer_type_name").toString());
					search.setOrder_num(konkaOrderInfo.getOrder_num());
					if (null != konkaOrderInfo.getMoney()) {
						search.setMoney(konkaOrderInfo.getMoney().setScale(2, BigDecimal.ROUND_HALF_UP).toString());
					}
					if (null != konkaOrderInfo.getGood_discount_price()) {
						search.setGood_discount_price(konkaOrderInfo.getGood_discount_price()
								.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
					}
					if (null != konkaOrderInfo.getAudit_state()) {
						if (0 == konkaOrderInfo.getAudit_state()) {
							search.setOrder_state("未审核");
						} else if (1 == konkaOrderInfo.getAudit_state()) {
							search.setOrder_state("审核中");
						} else if (3 == konkaOrderInfo.getAudit_state()) {
							search.setOrder_state("已完结");
						} else if (4 == konkaOrderInfo.getAudit_state()) {
							search.setOrder_state("已作废");
						}
					}
					search.setR3_id(konkaOrderInfo.getR3_id());
					if (null != konkaOrderInfo.getIs_change()) {
						if (0 == konkaOrderInfo.getIs_change()) {
							search.setIs_change("未变更");
						} else if (1 == konkaOrderInfo.getIs_change()) {
							search.setIs_change("变更未同步");
						} else if (2 == konkaOrderInfo.getIs_change()) {
							search.setIs_change("变更并同步");
						}
					}
					if (null != konkaOrderInfo.getIs_delivered()) {
						if (0 == konkaOrderInfo.getIs_delivered()) {
							search.setIs_delivered("未发货");
						} else if (1 == konkaOrderInfo.getIs_delivered()) {
							search.setIs_delivered("已发货");
						}
					}
					search.setVbedl(konkaOrderInfo.getMap().get("vbedl") == null ? "" : konkaOrderInfo.getMap()
							.get("vbedl").toString());

					if (null != konkaOrderInfo.getR3_id()) {
						KonkaR3Zsof konkaR3Zsof = new KonkaR3Zsof();
						konkaR3Zsof.setVbeln(konkaOrderInfo.getR3_id());
						konkaR3Zsof = super.getFacade().getKonkaR3ZsofService().getKonkaR3Zsof(konkaR3Zsof);
						if (konkaR3Zsof != null) {
							if (null != konkaR3Zsof.getLfdat()) {
								if (!("0000-00-00").equals(konkaR3Zsof.getLfdat())) {// 如果有发货时间,发货状态改为已发货
									search.setShipping_date(konkaR3Zsof.getLfdat());// 发货时间
								}
							}
							if (null != konkaR3Zsof.getShdat()) {
								if (!("0000-00-00").equals(konkaR3Zsof.getShdat())) {
									search.setReceiving_date(konkaR3Zsof.getShdat());// 收货时间
								}
							}
						}
					}

					// 得到分公司
					if (null != konkaOrderInfo.getAdd_dept_id()) {
						KonkaDept konkaDept = new KonkaDept();
						konkaDept.setDept_id(konkaOrderInfo.getAdd_dept_id());
						konkaDept.getMap().put("dept_type_eq", 3);
						konkaDept = super.getFacade().getKonkaDeptService().getKonkaDeptSuperiorByDeptId(konkaDept);
						if (null != konkaDept && null != konkaDept.getDept_name()
								&& !"".equals(konkaDept.getDept_name())) {
							search.setBranch_name(konkaDept.getDept_name());
						}
					}

					// 得到经办

					if (null != konkaOrderInfo.getAdd_dept_id()) {
						KonkaDept konkaDept2 = new KonkaDept();
						konkaDept2.setDept_id(konkaOrderInfo.getAdd_dept_id());
						konkaDept2.getMap().put("dept_type_eq", 4);
						konkaDept2 = super.getFacade().getKonkaDeptService().getKonkaDeptSuperiorByDeptId(konkaDept2);
						if (null != konkaDept2) {
							String deptName = konkaDept2.getDept_name();
							if (null != deptName && !("".equals(deptName))) {
								search.setHandle_name(konkaDept2.getDept_name());
							}
						} else {
							KonkaDept konkaDept3 = new KonkaDept();
							konkaDept3.setDept_id(konkaOrderInfo.getAdd_dept_id());
							konkaDept3.getMap().put("dept_type_eq", 5);
							konkaDept3 = super.getFacade().getKonkaDeptService()
									.getKonkaDeptSuperiorByDeptId(konkaDept3);
							if (null != konkaDept3) {
								String deptName = konkaDept3.getDept_name();
								if (null != deptName && !("".equals(deptName))) {
									search.setHandle_name(konkaDept3.getDept_name());
								}
							}
						}
					}
					if (null != konkaOrderInfo.getKh_confirm_state()) {
						if (0 == konkaOrderInfo.getKh_confirm_state()) {
							search.setMessage_confirm_state("未变更");
						} else if (1 == konkaOrderInfo.getKh_confirm_state()) {
							search.setMessage_confirm_state("已发送提醒");
						} else if (-1 == konkaOrderInfo.getKh_confirm_state()) {
							search.setMessage_confirm_state("未发送提醒");
						}
					}
					if (konkaOrderInfo.getProcess_id() != null) {
						search.setProcess_name("已确定");
					} else {
						search.setProcess_name("未确定");
					}
					if (null != konkaOrderInfo.getOrder_type()) {
						if (0 == konkaOrderInfo.getOrder_type()) {
							search.setOrder_type("在线下单");
						} else if (1 == konkaOrderInfo.getOrder_type()) {
							search.setOrder_type("图片下单");
						} else if (2 == konkaOrderInfo.getOrder_type()) {
							search.setOrder_type("触网转单");
                        } else if (4 == konkaOrderInfo.getOrder_type()) {
							search.setOrder_type("从返利转");
                        } else if (5 == konkaOrderInfo.getOrder_type()) {
                            search.setOrder_type("DRP转入");
                        } else {

						}
					}
					search.setAdd_date(format.format(konkaOrderInfo.getAdd_date()));
					search.setNext_audit_role_name(konkaOrderInfo.getMap().get("next_audit_role_name") == null ? "无"
							: konkaOrderInfo.getMap().get("next_audit_role_name").toString());
					searchList.add(search);
				}
			}
		}
		KonkaInterfaceAccessLog log = new KonkaInterfaceAccessLog();
		log.setAdd_date(new Date());
		log.setIp(IpUtils.getRemortIP(request));
		log.setLicenses_sn(user.getLicenses_sn());
		log.setReq_state(0);
		log.setReq_url(request.getRequestURL().toString() + "?" + request.getQueryString());
		log.setReq_mod_name("订单查询接口");
		log.setUser_id(user.getUser_id());
		log.setUser_key(user.getUser_key());
		super.getFacade().getKonkaInterfaceAccessLogService().createKonkaInterfaceAccessLog(log);
		obj.setKonkaOrderInfoSearchList(searchList);
		// obj.setReturn_state(0);
		// super.renderJson(response, JSON.toJSONString(obj));
		// return null;
		request.setAttribute("entityList", searchList);
		request.setAttribute("licenses_sn", user.getLicenses_sn());
		request.setAttribute("user_id", user.getUser_id());
		request.setAttribute("user_key", user.getUser_key());
		return mapping.findForward("list");
	}
}
