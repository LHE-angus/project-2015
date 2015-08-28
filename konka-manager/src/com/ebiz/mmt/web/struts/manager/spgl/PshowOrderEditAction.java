package com.ebiz.mmt.web.struts.manager.spgl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.EcBindingPdOrdeDetails;
import com.ebiz.mmt.domain.EcVouchers;
import com.ebiz.mmt.domain.KonkaBcompPd;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.domain.PshowOrdeAudit;
import com.ebiz.mmt.domain.PshowOrdeDetails;
import com.ebiz.mmt.domain.PshowOrder;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Pan,Gang
 * @version 2013-08-16
 */
public class PshowOrderEditAction extends BaseAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String trade_index_like = (String) dynaBean.get("trade_index_like");
		String order_user_name_like = (String) dynaBean.get("order_user_name_like");
		String state = (String) dynaBean.get("state");
		String buyer_name_like = (String) dynaBean.get("buyer_name_like");
		String buyer_mp_like = (String) dynaBean.get("buyer_mp_like");
		String order_from = (String) dynaBean.get("order_from");
		String dept_id = (String) dynaBean.get("dept_id");

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(user.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);
		boolean role_id_eq_30 = false;
		boolean role_id_gt_30_lt_60 = false;

		for (PeRoleUser peRoleUser : peRoleUserList) {
			// 总部管理员
			if (peRoleUser.getRole_id() < 30L || peRoleUser.getRole_id().intValue() == 140317
			        || peRoleUser.getRole_id().intValue() == 1001) {
				role_id_eq_30 = true;
			}
			// 分公司管理员
			if (peRoleUser.getRole_id() >= 30L && peRoleUser.getRole_id() <= 188L) {
				role_id_gt_30_lt_60 = true;
			}
		}
		PshowOrder entity = new PshowOrder();
		// 总部可以查看所有订单
		// 分公司只能看到分公司的订单
		// if (role_id_gt_30_lt_60) {
		// entity.setOpr_dept_id(super.getSuperiorForDeptType(user.getDept_id(),
		// 3).getDept_id());
		// }
		// // 总部和分公司之外的不能查看
		// if (!role_id_gt_30_lt_60 && !role_id_eq_30) {
		// String msg = super.getMessage(request, "konka.r3.roleError");
		// super.renderJavaScript(response, "window.onload=function(){alert('" +
		// msg + "');history.back();}");
		// return null;
		// }

		if (StringUtils.isNotBlank(dept_id)) {
			entity.setOpr_dept_id(Long.valueOf(dept_id));
		}

		if (StringUtils.isNotBlank(trade_index_like)) {
			entity.getMap().put("trade_index_like", trade_index_like);
		}
		if (StringUtils.isNotBlank(order_user_name_like)) {
			entity.getMap().put("order_user_name_like", order_user_name_like);
		}
		if (StringUtils.isNotBlank(state)) {
			entity.setState(Integer.valueOf(state));
		}
		if (StringUtils.isNotBlank(buyer_name_like)) {
			entity.getMap().put("buyer_name_like", buyer_name_like);
		}
		if (StringUtils.isNotBlank(buyer_mp_like)) {
			entity.getMap().put("buyer_mp_like", buyer_mp_like);
		}
		if (StringUtils.isNotBlank(order_from)) {
			if (order_from.equals("3")) {
				entity.setOrder_from(2);
				entity.getMap().put("sf_order", true);
				dynaBean.set("order_from", "3");
			} else if (order_from.equals("2")) {
				entity.setOrder_from(2);
				entity.getMap().put("cw_order", true);
				dynaBean.set("order_from", "2");
			} else {
				entity.setOrder_from(Integer.valueOf(order_from));
			}
		}
		entity.getMap().put("edit_state", true);
		entity.getMap().put("cust_pay_way", true);

		Long recordCount = super.getFacade().getPshowOrderService().getPshowOrdeForDeptNameAndFullNameListCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<PshowOrder> entityList = super.getFacade().getPshowOrderService().getPshowOrdeForDeptNameAndFullNameList(
		        entity);
		if (entityList != null && entityList.size() > 0) {
			for (PshowOrder pshowOrder : entityList) {
				PshowOrdeDetails psd = new PshowOrdeDetails();
				psd.setOrder_id(Long.valueOf(pshowOrder.getId()));
				List<PshowOrdeDetails> pshowordedetails = super.getFacade().getPshowOrdeDetailsService()
				        .getPshowOrdeForPDSNDetailsList(psd);
				pshowOrder.setPshowOrdeDetailsList(pshowordedetails);
			}
		}

		request.setAttribute("entityList", entityList);
		request.setAttribute("sybDeptInfoList", super.getDeptInfoListWithOutLimit(mapping, form, request, response));

		return mapping.findForward("list");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");

		if (!GenericValidator.isLong(id)) {
			super.saveError(request, "errors.long", "id");
			return this.list(mapping, form, request, response);
		}

		PshowOrder entity = new PshowOrder();
		if (StringUtils.isNotBlank(id)) {
			entity.setId(Long.valueOf(id));
		}
		entity = super.getFacade().getPshowOrderService().getPshowOrder(entity);
		entity.setQueryString(super.serialize(request, "id", "method"));
		super.copyProperties(form, entity);

		// 地区回显
		if (entity.getBuyer_p_index() != null && entity.getBuyer_p_index() != -1) {
			String p_index = entity.getBuyer_p_index().toString();
			if (StringUtils.isNotBlank(p_index)) {
				if (!p_index.endsWith("00")) {
					if (p_index.length() == 6) {
						request.setAttribute("province", StringUtils.substring(p_index, 0, 2) + "0000");
						request.setAttribute("city", StringUtils.substring(p_index, 0, 4) + "00");
						request.setAttribute("country", p_index);
					} else if (p_index.length() == 8) {
						request.setAttribute("province", StringUtils.substring(p_index, 0, 2) + "0000");
						request.setAttribute("city", StringUtils.substring(p_index, 0, 4) + "00");
						request.setAttribute("country", StringUtils.substring(p_index, 0, 6));
						request.setAttribute("town", p_index);
					}
				} else if (p_index.endsWith("0000")) {
					if (p_index.length() == 6) {
						request.setAttribute("province", p_index);
					} else if (p_index.length() == 8) {
						request.setAttribute("province", p_index);
						request.setAttribute("city", StringUtils.substring(p_index, 0, 4) + "00");
					}
				} else if (p_index.endsWith("00")) {
					if (p_index.length() == 6) {
						request.setAttribute("province", StringUtils.substring(p_index, 0, 2) + "0000");
						request.setAttribute("city", p_index);
					} else if (p_index.length() == 8) {
						request.setAttribute("province", StringUtils.substring(p_index, 0, 2) + "000000");
						request.setAttribute("city", StringUtils.substring(p_index, 0, 4) + "0000");
						request.setAttribute("country", StringUtils.substring(p_index, 0, 6) + "00");
					}
				}
			}
		}

		PshowOrdeAudit pat = new PshowOrdeAudit();
		// pat.setState(entity.getState());
		pat.setOrder_id(Long.valueOf(id));
		pat.getMap().put("orderByDate", true);

		List<PshowOrdeAudit> pshowOrdeAudits = super.getFacade().getPshowOrdeAuditService().getPshowOrdeAuditList(pat);
		if (pshowOrdeAudits.size() > 0 && null != pshowOrdeAudits.get(0)) {
			request.setAttribute("audit_remark", pshowOrdeAudits.get(0).getRemark());
		} else {
			request.setAttribute("audit_remark", "未审核");
		}
		request.setAttribute("pshowOrdeAudits", pshowOrdeAudits);

		PshowOrdeDetails psd = new PshowOrdeDetails();
		psd.setOrder_id(Long.valueOf(id));
		List<PshowOrdeDetails> pshowordedetails = super.getFacade().getPshowOrdeDetailsService()
		        .getPshowOrdeForPDSNDetailsList(psd);

		// 取产品的增值服务
		EcBindingPdOrdeDetails ec = new EcBindingPdOrdeDetails();
		ec.setTrade_index(entity.getTrade_index());
		List<EcBindingPdOrdeDetails> bddetailsList = super.getFacade().getEcBindingPdOrdeDetailsService()
		        .getEcBindingPdOrdeDetailsList(ec);
		request.setAttribute("bddetailsList", bddetailsList);

		int t_num = 0;
		double t_price = 0.00;
		for (PshowOrdeDetails ps : pshowordedetails) {
			t_num += ps.getNum();
			t_price += ps.getTotal_price().doubleValue();
		}

		request.setAttribute("t_num", t_num);
		request.setAttribute("t_price", t_price);
		request.setAttribute("pshowOrdeDetails", pshowordedetails);

		dynaBean.set("order_type", null);// 清空默认值

		return new ActionForward("/spgl/PshowOrderEdit/edit.jsp");
	}

	public ActionForward splitOrder(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		saveToken(request);
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");

		if (!GenericValidator.isLong(id)) {
			super.saveError(request, "errors.long", "id");
			return this.list(mapping, form, request, response);
		}

		PshowOrder entity = new PshowOrder();
		if (StringUtils.isNotBlank(id)) {
			entity.setId(Long.valueOf(id));
		}
		entity = super.getFacade().getPshowOrderService().getPshowOrder(entity);
		entity.setQueryString(super.serialize(request, "id", "method"));
		super.copyProperties(form, entity);

		// 地区回显
		if (entity.getBuyer_p_index() != null && entity.getBuyer_p_index() != -1) {
			String p_index = entity.getBuyer_p_index().toString();
			if (StringUtils.isNotBlank(p_index)) {
				if (!p_index.endsWith("00")) {
					if (p_index.length() == 6) {
						request.setAttribute("province", StringUtils.substring(p_index, 0, 2) + "0000");
						request.setAttribute("city", StringUtils.substring(p_index, 0, 4) + "00");
						request.setAttribute("country", p_index);
					} else if (p_index.length() == 8) {
						request.setAttribute("province", StringUtils.substring(p_index, 0, 2) + "0000");
						request.setAttribute("city", StringUtils.substring(p_index, 0, 4) + "00");
						request.setAttribute("country", StringUtils.substring(p_index, 0, 6));
						request.setAttribute("town", p_index);
					}
				} else if (p_index.endsWith("0000")) {
					if (p_index.length() == 6) {
						request.setAttribute("province", p_index);
					} else if (p_index.length() == 8) {
						request.setAttribute("province", p_index);
						request.setAttribute("city", StringUtils.substring(p_index, 0, 4) + "00");
					}
				} else if (p_index.endsWith("00")) {
					if (p_index.length() == 6) {
						request.setAttribute("province", StringUtils.substring(p_index, 0, 2) + "0000");
						request.setAttribute("city", p_index);
					} else if (p_index.length() == 8) {
						request.setAttribute("province", StringUtils.substring(p_index, 0, 2) + "000000");
						request.setAttribute("city", StringUtils.substring(p_index, 0, 4) + "0000");
						request.setAttribute("country", StringUtils.substring(p_index, 0, 6) + "00");
					}
				}
			}
		}

		PshowOrdeAudit pat = new PshowOrdeAudit();
		pat.setState(entity.getState());
		pat.setOrder_id(Long.valueOf(id));
		pat.getMap().put("orderByDate", true);

		List<PshowOrdeAudit> pshowOrdeAudits = super.getFacade().getPshowOrdeAuditService().getPshowOrdeAuditList(pat);
		if (pshowOrdeAudits.size() > 0 && null != pshowOrdeAudits.get(0)) {
			request.setAttribute("audit_remark", pshowOrdeAudits.get(0).getRemark());
		} else {
			request.setAttribute("audit_remark", "未审核");
		}

		PshowOrdeDetails psd = new PshowOrdeDetails();
		psd.setOrder_id(Long.valueOf(id));
		List<PshowOrdeDetails> pshowordedetails = super.getFacade().getPshowOrdeDetailsService()
		        .getPshowOrdeForPDSNDetailsList(psd);

		// 取产品的增值服务
		EcBindingPdOrdeDetails ec = new EcBindingPdOrdeDetails();
		ec.setTrade_index(entity.getTrade_index());
		List<EcBindingPdOrdeDetails> bddetailsList = super.getFacade().getEcBindingPdOrdeDetailsService()
		        .getEcBindingPdOrdeDetailsList(ec);
		request.setAttribute("bddetailsList", bddetailsList);

		int t_num = 0;
		double t_price = 0.00;
		for (PshowOrdeDetails ps : pshowordedetails) {
			t_num += ps.getNum();
			t_price += ps.getTotal_price().doubleValue();
		}

		request.setAttribute("t_num", t_num);
		request.setAttribute("t_price", t_price);
		logger.info("------------------>size" + pshowordedetails.size());
		request.setAttribute("pshowOrdeDetails", pshowordedetails);

		request.setAttribute("pshowOrdeDetails2", pshowordedetails);
		dynaBean.set("id", id);

		EcVouchers ecv = new EcVouchers();
		ecv.setOrder_id(id);
		ecv.setInfo_state(1);
		List<EcVouchers> ecvList = super.getFacade().getEcVouchersService().getEcVouchersList(ecv);

		request.setAttribute("ecvList", ecvList);

		return new ActionForward("/spgl/PshowOrderEdit/form.jsp");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String id = (String) dynaBean.get("id");
		String buyer_name = (String) dynaBean.get("buyer_name");
		String buyer_addr = (String) dynaBean.get("buyer_addr");
		String buyer_tel = (String) dynaBean.get("buyer_tel");
		String buyer_mp = (String) dynaBean.get("buyer_mp");
		String province = (String) dynaBean.get("province");
		String city = (String) dynaBean.get("city");
		String country = (String) dynaBean.get("country");
		String town = (String) dynaBean.get("town");
		String deliver_way = (String) dynaBean.get("deliver_way");
		String pay_way = (String) dynaBean.get("pay_way");
		String trade_no = (String) dynaBean.get("trade_no");
		String state = (String) dynaBean.get("state");
		String total_price = (String) dynaBean.get("total_price");
		String deliver_time = (String) dynaBean.get("deliver_time");
		String pay_price = (String) dynaBean.get("pay_price");
		String remark1 = (String) dynaBean.get("remark1");
		String bill_type = (String) dynaBean.get("bill_type");
		String is_ps = (String) dynaBean.get("is_ps");
		String remark = (String) dynaBean.get("remark");
		String bill_head = (String) dynaBean.get("bill_head");
		String bill_company = (String) dynaBean.get("bill_company");
		String order_type = (String) dynaBean.get("order_type");
		String bill_is_add = (String) dynaBean.get("bill_is_add");
		String logistic_sn = (String) dynaBean.get("logistic_sn");

		// 修改奖励积分和佣金
		String[] detai_ids = request.getParameterValues("detai_id");
		String[] integrals = request.getParameterValues("integral");
		String[] rebates = request.getParameterValues("rebates");
		String[] rebates_status = request.getParameterValues("rebates_status");
		String[] pay_integrals = request.getParameterValues("pay_integral");

		if (!GenericValidator.isLong(id)) {
			super.saveError(request, "errors.long", "id");
			return this.list(mapping, form, request, response);
		}
		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		PshowOrder p1 = new PshowOrder();
		p1.setId(Long.valueOf(id));
		p1 = super.getFacade().getPshowOrderService().getPshowOrder(p1);
		if (p1.getState().intValue() == Integer.valueOf(state).intValue() && Integer.valueOf(state).intValue() == 10) {
			super.renderJavaScript(response, "window.onload=function(){alert('该订单已经被确认，请勿重复操作！');history.back();}");
			return null;
		}

		PshowOrder entity = new PshowOrder();
		if (StringUtils.isNotBlank(id)) {
			entity.setId(Long.valueOf(id));
		}
		if (StringUtils.isNotBlank(bill_head)) {
			entity.setBill_head(Integer.valueOf(bill_head));
		}
		if (StringUtils.isNotBlank(bill_is_add)) {
			entity.setBill_is_add(Integer.valueOf(bill_is_add));
		}
		if (StringUtils.isNotBlank(order_type)) {
			entity.setOrder_type(Integer.valueOf(order_type));
		}
		if (StringUtils.isNotBlank(bill_company)) {
			entity.setBill_company(bill_company);
		}
		if (StringUtils.isNotBlank(buyer_name)) {
			entity.setBuyer_name(buyer_name);
		}
		if (StringUtils.isNotBlank(logistic_sn)) {
			entity.setLogistic_sn(logistic_sn);
		}
		if (StringUtils.isNotBlank(buyer_tel)) {
			entity.setBuyer_tel(buyer_tel);
		}
		if (StringUtils.isNotBlank(bill_type)) {
			entity.setBill_type(Integer.valueOf(bill_type));
		}
		if (StringUtils.isNotBlank(buyer_addr)) {
			entity.setBuyer_addr(buyer_addr);
		}
		if (StringUtils.isNotBlank(buyer_mp)) {
			entity.setBuyer_mp(buyer_mp);
		}
		if (StringUtils.isNotBlank(deliver_way)) {
			entity.setDeliver_way(Integer.valueOf(deliver_way));
		}
		if (StringUtils.isNotBlank(deliver_time)) {
			entity.setDeliver_time(Integer.valueOf(deliver_time));
		}
		if (StringUtils.isNotBlank(pay_way)) {
			entity.setPay_way(Integer.valueOf(pay_way));
		}
		if (StringUtils.isNotBlank(is_ps)) {
			entity.setIs_ps(Integer.valueOf(is_ps));
		}
		if (StringUtils.isNotBlank(state)) {
			entity.setState(Integer.valueOf(state));
		}
		if (StringUtils.isNotBlank(trade_no)) {
			entity.setTrade_no(trade_no);
		}
		if (StringUtils.isNotBlank(total_price)) {
			entity.setTotal_price(new BigDecimal(total_price));
		}
		if (StringUtils.isNotBlank(pay_price)) {
			entity.setPay_price(new BigDecimal(pay_price));
		}
		if (StringUtils.isNotBlank(town)) {
			entity.setBuyer_p_index(Long.valueOf(town));
		} else if (StringUtils.isNotBlank(country) && StringUtils.isBlank(town)) {
			entity.setBuyer_p_index(Long.valueOf(country));
		} else if (StringUtils.isNotBlank(city) && StringUtils.isBlank(country) && StringUtils.isBlank(town)) {
			entity.setBuyer_p_index(Long.valueOf(city));
		} else if (StringUtils.isNotBlank(province) && StringUtils.isBlank(city) && StringUtils.isBlank(country)
		        && StringUtils.isBlank(town)) {
			entity.setBuyer_p_index(Long.valueOf(province));
		}
		entity.setRemark(remark);
		super.copyProperties(entity, form);

		PshowOrdeDetails psd = new PshowOrdeDetails();
		psd.setOrder_id(Long.valueOf(id));
		List<PshowOrdeDetails> pshowordedetails = super.getFacade().getPshowOrdeDetailsService()
		        .getPshowOrdeDetailsList(psd);

		int t_num = 0;
		double t_price = 0.00;
		String tuan = "1";
		for (PshowOrdeDetails ps : pshowordedetails) {
			if (StringUtils.isNotBlank(state) && (state.equals("-10") || state.equals("5"))) {
				KonkaBcompPd kp = new KonkaBcompPd();
				kp.setId(ps.getPd_id());
				kp = super.getFacade().getKonkaBcompPdService().getKonkaBcompPd(kp);
				if (kp.getIs_djq() != null) {
					if (kp != null && kp.getProd_type().intValue() == 8 && kp.getIs_djq().intValue() == 0) {
						tuan = "2";
					} else {
						tuan = "3";
					}
				}

			}
			t_num += ps.getNum();
			t_price += ps.getTotal_price().doubleValue();
		}

		BigDecimal total_int = new BigDecimal("0.00");// 订单总奖励积分
		BigDecimal total_pay_int = new BigDecimal("0.00");// 订单总付款积分
		if (null != detai_ids && detai_ids.length > 0) {
			for (int i = 0; i < detai_ids.length; i++) {
				PshowOrdeDetails pd = new PshowOrdeDetails();

				pd.setBill_item_id(Long.valueOf(detai_ids[i]));
				if (integrals[i] != null) {
					pd.setIntegral(new BigDecimal(integrals[i]));
				} else {
					pd.setIntegral(new BigDecimal("0"));
				}
				if (!order_type.equals("9")) {
					if (rebates_status[i] != null && !rebates_status[i].equals("4")) {
						if (rebates[i] != null) {
							pd.setRebates(new BigDecimal(rebates[i]));
						} else {
							pd.setRebates(new BigDecimal("0"));
						}
					}
				} else {// 大宗采购 不给佣金
					pd.setRebates(new BigDecimal("0"));
				}
				if (pay_integrals[i] != null) {
					pd.setPay_integral(new BigDecimal(pay_integrals[i]));
				} else {
					pd.setPay_integral(new BigDecimal("0"));
				}

				super.getFacade().getPshowOrdeDetailsService().modifyPshowOrdeDetails(pd);
				total_int = total_int.add(new BigDecimal(integrals[i]));
				total_pay_int = total_pay_int.add(new BigDecimal(pay_integrals[i]));
			}
		}

		entity.setIntegral(total_int);
		entity.setPay_integral(total_pay_int);
		entity.getMap().put("tuan", tuan);

		PshowOrdeAudit psa = new PshowOrdeAudit();
		psa.setRemark(remark1);
		psa.setOrder_id(Long.valueOf(id));
		psa.setState(Integer.valueOf(state));
		psa.setOpr_user_id(user.getId());
		psa.setOpr_user_real_name(user.getUser_name());
		// java.math.BigDecimal bd1 = new java.math.BigDecimal(t_price);
		psa.setTotal_price(new BigDecimal(pay_price));
		Long cid = super.getFacade().getPshowOrderService().createPshowOrdeAuditAndModifyPshowOrder(entity, psa);
		// if (cid == 0L) {
		// super.renderJavaScript(response,
		// "window.onload=function(){alert('由于顺丰系统故障，订单未能进入顺丰，请再试一次！');history.back();}");
		// return null;
		// }
		request.setAttribute("t_num", t_num);
		request.setAttribute("t_price", t_price);
		request.setAttribute("pshowOrdeDetails", pshowordedetails);

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		// pathBuffer.append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		return forward;
	}

	public ActionForward save1(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {

		if (isCancelled(request)) {
			return list(mapping, form, request, response);
		}
		if (!isTokenValid(request)) {
			saveError(request, "errors.token");
			return list(mapping, form, request, response);
		}
		resetToken(request);
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String id = (String) dynaBean.get("id");
		String select1 = (String) dynaBean.get("s1");
		String select2 = (String) dynaBean.get("s2");
		String select3 = (String) dynaBean.get("s3");
		String select4 = (String) dynaBean.get("s4");
		String remark = (String) dynaBean.get("remark1");
		logger.info("id------------>" + id);
		logger.info("select1------------>" + select1);
		logger.info("select2------------>" + select2);
		logger.info("select3------------>" + select3);
		logger.info("select4------------>" + select4);
		if (!GenericValidator.isLong(id)) {
			super.saveError(request, "errors.long", "id");
			return this.list(mapping, form, request, response);
		}
		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		PshowOrder entity = new PshowOrder();
		if (StringUtils.isNotBlank(id)) {
			entity.setId(Long.valueOf(id));
		}
		entity = super.getFacade().getPshowOrderService().getPshowOrder(entity);
		super.copyProperties(form, entity);

		List<PshowOrder> pshowList = new ArrayList<PshowOrder>();

		// 交易流水号
		Date now = new Date();
		String trade_index1 = DateFormatUtils.format(now, "yyMMddHHmmss")
		        + StringUtils.substring(String.valueOf(now.getTime()), 7);
		PshowOrder pp1 = new PshowOrder();
		pp1.setTrade_index(trade_index1);
		if (null != entity.getAdd_date()) {
			pp1.setAdd_date(entity.getAdd_date());
		}
		if (null != entity.getBill_content()) {
			pp1.setBill_content(entity.getBill_content());
		}
		if (null != entity.getBill_head()) {
			pp1.setBill_head(entity.getBill_head());
		}
		if (null != entity.getBill_is_add()) {
			pp1.setBill_is_add(entity.getBill_is_add());
		}
		if (null != entity.getBill_type()) {
			pp1.setBill_type(entity.getBill_type());
		}
		if (null != entity.getBuyer_name()) {
			pp1.setBuyer_name(entity.getBuyer_name());
		}
		if (null != entity.getBuyer_addr()) {
			pp1.setBuyer_addr(entity.getBuyer_addr());
		}
		if (null != entity.getBill_company()) {
			pp1.setBill_company(entity.getBill_company());
		}
		if (null != entity.getBuyer_mp()) {
			pp1.setBuyer_mp(entity.getBuyer_mp());
		}
		if (null != entity.getBuyer_tel()) {
			pp1.setBuyer_tel(entity.getBuyer_tel());
		}
		if (null != entity.getBuyer_p_index()) {
			pp1.setBuyer_p_index(entity.getBuyer_p_index());
		}
		if (null != entity.getDeliver_is_call()) {
			pp1.setDeliver_is_call(entity.getDeliver_is_call());
		}
		if (null != entity.getDeliver_time()) {
			pp1.setDeliver_time(entity.getDeliver_time());
		}
		if (null != entity.getDeliver_way()) {
			pp1.setDeliver_way(entity.getDeliver_way());
		}
		if (null != entity.getDept_id()) {
			pp1.setDept_id(entity.getDept_id());
		}
		if (null != entity.getExpress_id()) {
			pp1.setExpress_id(entity.getExpress_id());
		}
		if (null != entity.getIs_del()) {
			pp1.setIs_del(entity.getIs_del());
		}
		if (null != entity.getIs_hdfk()) {
			pp1.setIs_hdfk(entity.getIs_hdfk());
		}
		if (null != entity.getOrder_from()) {
			pp1.setOrder_from(entity.getOrder_from());
		}
		pp1.setOrder_type(0);
		if (null != entity.getOrder_user_id()) {
			pp1.setOrder_user_id(entity.getOrder_user_id());
		}
		if (null != entity.getOrder_user_name()) {
			pp1.setOrder_user_name(entity.getOrder_user_name());
		}
		if (null != entity.getPay_account_id()) {
			pp1.setPay_account_id(entity.getPay_account_id());
		}
		if (null != entity.getPay_way()) {
			pp1.setPay_way(entity.getPay_way());
		}
		if (null != entity.getPay_date()) {
			pp1.setPay_date(entity.getPay_date());
		}
		if (StringUtils.isNotBlank(remark)) {
			pp1.setRemark(remark);
		}
		if (null != entity.getState()) {
			pp1.setState(entity.getState());
		}
		if (null != entity.getTrade_no()) {
			pp1.setTrade_no(entity.getTrade_no());
		}
		if (null != entity.getUuid()) {
			pp1.setUuid(entity.getUuid());
		}
		if (null != entity.getLogistic_sn()) {
			pp1.setLogistic_sn(entity.getLogistic_sn());
		}

		pp1.setPar_order_id(Long.valueOf(id));

		PshowOrder pp2 = new PshowOrder();
		if (null != entity.getAdd_date()) {
			pp2.setAdd_date(entity.getAdd_date());
		}
		if (null != entity.getBill_content()) {
			pp2.setBill_content(entity.getBill_content());
		}
		if (null != entity.getBill_head()) {
			pp2.setBill_head(entity.getBill_head());
		}
		if (null != entity.getBill_is_add()) {
			pp2.setBill_is_add(entity.getBill_is_add());
		}
		if (null != entity.getBill_type()) {
			pp2.setBill_type(entity.getBill_type());
		}
		if (null != entity.getBuyer_name()) {
			pp2.setBuyer_name(entity.getBuyer_name());
		}
		if (null != entity.getBuyer_addr()) {
			pp2.setBuyer_addr(entity.getBuyer_addr());
		}
		if (null != entity.getBill_company()) {
			pp2.setBill_company(entity.getBill_company());
		}
		if (null != entity.getBuyer_mp()) {
			pp2.setBuyer_mp(entity.getBuyer_mp());
		}
		if (null != entity.getBuyer_tel()) {
			pp2.setBuyer_tel(entity.getBuyer_tel());
		}
		if (null != entity.getBuyer_p_index()) {
			pp2.setBuyer_p_index(entity.getBuyer_p_index());
		}
		if (null != entity.getDeliver_is_call()) {
			pp2.setDeliver_is_call(entity.getDeliver_is_call());
		}
		if (null != entity.getDeliver_time()) {
			pp2.setDeliver_time(entity.getDeliver_time());
		}
		if (null != entity.getDeliver_way()) {
			pp2.setDeliver_way(entity.getDeliver_way());
		}
		if (null != entity.getDept_id()) {
			pp2.setDept_id(entity.getDept_id());
		}
		if (null != entity.getExpress_id()) {
			pp2.setExpress_id(entity.getExpress_id());
		}
		if (null != entity.getIs_del()) {
			pp2.setIs_del(entity.getIs_del());
		}
		if (null != entity.getIs_hdfk()) {
			pp2.setIs_hdfk(entity.getIs_hdfk());
		}
		if (null != entity.getOrder_from()) {
			pp2.setOrder_from(entity.getOrder_from());
		}
		if (null != entity.getLogistic_sn()) {
			pp2.setLogistic_sn(entity.getLogistic_sn());
		}
		pp2.setOrder_type(0);
		if (null != entity.getOrder_user_id()) {
			pp2.setOrder_user_id(entity.getOrder_user_id());
		}
		if (null != entity.getOrder_user_name()) {
			pp2.setOrder_user_name(entity.getOrder_user_name());
		}
		if (null != entity.getPay_account_id()) {
			pp2.setPay_account_id(entity.getPay_account_id());
		}
		if (null != entity.getPay_way()) {
			pp2.setPay_way(entity.getPay_way());
		}
		if (null != entity.getPay_date()) {
			pp2.setPay_date(entity.getPay_date());
		}
		if (StringUtils.isNotBlank(remark)) {
			pp2.setRemark(remark);
		}
		if (null != entity.getState()) {
			pp2.setState(entity.getState());
		}
		if (null != entity.getTrade_no()) {
			pp2.setTrade_no(entity.getTrade_no());
		}
		if (null != entity.getUuid()) {
			pp2.setUuid(entity.getUuid());
		}
		pp2.setPar_order_id(Long.valueOf(id));
		Date now2 = new Date();
		String trade_index2 = DateFormatUtils.format(now, "yyMMddHHmmss")
		        + StringUtils.substring(String.valueOf(now2.getTime() + 1), 7);
		pp2.setTrade_index(trade_index2);

		BigDecimal allPrice1 = new BigDecimal("0");
		BigDecimal allIntegral1 = new BigDecimal("0");
		BigDecimal deduPrice1 = new BigDecimal("0");// 佣金抵扣
		BigDecimal all_rebate1 = new BigDecimal("0.0");// 抵用券抵扣

		List<PshowOrdeDetails> pshowOrdeDetailsList1 = new ArrayList<PshowOrdeDetails>();
		if (StringUtils.isNotBlank(select1)) {
			String[] s1 = select1.split(",");
			logger.info("s1---------------->" + s1.length);
			if (null != s1 && s1.length > 0) {
				for (String string : s1) {
					PshowOrdeDetails p1 = new PshowOrdeDetails();
					p1.setBill_item_id(Long.valueOf(string));
					p1 = super.getFacade().getPshowOrdeDetailsService().getPshowOrdeDetails(p1);

					// 取原商品的绑定服务信息
					BigDecimal service_price1 = new BigDecimal("0.0");
					EcBindingPdOrdeDetails pd = new EcBindingPdOrdeDetails();
					pd.setDetails_id(Long.valueOf(string));
					List<EcBindingPdOrdeDetails> ecList = super.getFacade().getEcBindingPdOrdeDetailsService()
					        .getEcBindingPdOrdeDetailsList(pd);
					List<EcBindingPdOrdeDetails> ec1 = new ArrayList<EcBindingPdOrdeDetails>();
					if (null != ecList && ecList.size() > 0) {
						for (EcBindingPdOrdeDetails ecp : ecList) {
							EcBindingPdOrdeDetails ee = new EcBindingPdOrdeDetails();
							if (null != ecp.getBinding_id()) {
								ee.setBinding_id(ecp.getBinding_id());
							}
							ee.setTrade_index(pp1.getTrade_index());
							ee.setState(0);
							ee.setGoods_name(ecp.getGoods_name());
							ee.setNum(ecp.getNum());
							ee.setPrice(ecp.getPrice());
							ee.setTotal_price(ecp.getTotal_price());
							ec1.add(ee);
							service_price1 = service_price1.add(ecp.getTotal_price());
						}
					}
					PshowOrdeDetails p2 = new PshowOrdeDetails();
					if (null != p1.getNum()) {
						p2.setNum(p1.getNum());
					}
					if (null != p1.getPd_id()) {
						p2.setPd_id(p1.getPd_id());
					}
					// if (null != p1.getRebates_status()) {
					// p2.setRebates_status(p1.getRebates_status());
					// }
					if (null != p1.getState()) {
						p2.setState(p1.getState());
					}
					if (null != p1.getStore_id()) {
						p2.setStore_id(p1.getStore_id());
					}
					if (null != p1.getIntegral()) {
						p2.setIntegral(p1.getIntegral());
					}
					if (null != p1.getPd_name()) {
						p2.setPd_name(p1.getPd_name());
					}
					if (null != p1.getPrice()) {
						p2.setPrice(p1.getPrice());
					}
					if (null != p1.getRebates()) {
						p2.setRebates(p1.getRebates());
					}
					if (null != p1.getRebates_date()) {
						p2.setRebates_date(p1.getRebates_date());
					}
					if (null != p1.getRebates_sender()) {
						p2.setRebates_sender(p1.getRebates_sender());
					}
					// if (StringUtils.isNotBlank(remark)) {
					// p2.setRemark(remark);
					// }
					if (null != p1.getTotal_price()) {
						// p2.setTotal_price((p1.getPrice()).multiply(new
						// BigDecimal(p1.getNum().toString())).add(
						// service_price1).setScale(2,
						// BigDecimal.ROUND_HALF_UP));
						p2.setTotal_price(p1.getTotal_price());
					}
					p2.setEcBindingPdOrdeDetailsList(ec1);// 延保服务
					allPrice1 = allPrice1.add(p2.getTotal_price());
					allIntegral1 = allIntegral1.add(p1.getIntegral());// 计算订单总积分
					if (p1.getRebates() != null) {
						all_rebate1 = all_rebate1.add(p1.getRebates());
						p2.setRebates_status(p1.getRebates_status());
					}
					pshowOrdeDetailsList1.add(p2);
				}
			}
			if (entity.getIs_deduction() == 0) {
				deduPrice1 = all_rebate1;
				pp1.setIs_deduction(new Integer(0));
			}
			List<EcVouchers> ecVouchersList1 = new ArrayList<EcVouchers>();
			if (StringUtils.isNotBlank(select3)) {
				String[] s3 = select3.split(",");
				if (null != s3 && s3.length > 0) {
					for (String string : s3) {
						EcVouchers ec = new EcVouchers();
						ec.setId(Long.valueOf(string));
						ec = super.getFacade().getEcVouchersService().getEcVouchers(ec);
						if (null != ec) {
							deduPrice1 = deduPrice1.add(ec.getPrice());
							ecVouchersList1.add(ec);
						}
					}
				}

			}

			pp1.setEcVouchersList(ecVouchersList1);
		}
		pp1.setPshowOrdeDetailsList(pshowOrdeDetailsList1);
		pp1.setTotal_price(allPrice1);
		pp1.setIntegral(allIntegral1);
		BigDecimal payPrice = allPrice1.subtract(deduPrice1);// 订单付款金额=订单总价-抵扣金额
		pp1.setPay_price(payPrice);
		pp1.setDedu_price(deduPrice1);

		BigDecimal allPrice2 = new BigDecimal("0");
		BigDecimal allIntegral2 = new BigDecimal("0");
		BigDecimal all_rebate2 = new BigDecimal("0.0");// 佣金抵扣
		BigDecimal deduPrice2 = new BigDecimal("0.0");// 抵扣券抵扣
		List<PshowOrdeDetails> pshowOrdeDetailsList2 = new ArrayList<PshowOrdeDetails>();
		if (StringUtils.isNotBlank(select2)) {
			String[] s2 = select2.split(",");
			logger.info("s2---------------->" + s2.length);
			if (null != s2 && s2.length > 0) {
				for (String string : s2) {
					PshowOrdeDetails p1 = new PshowOrdeDetails();
					p1.setBill_item_id(Long.valueOf(string));
					p1 = super.getFacade().getPshowOrdeDetailsService().getPshowOrdeDetails(p1);

					EcBindingPdOrdeDetails pd = new EcBindingPdOrdeDetails();
					pd.setDetails_id(Long.valueOf(string));
					List<EcBindingPdOrdeDetails> ecList = super.getFacade().getEcBindingPdOrdeDetailsService()
					        .getEcBindingPdOrdeDetailsList(pd);
					BigDecimal service_price2 = new BigDecimal("0.0");
					List<EcBindingPdOrdeDetails> ec1 = new ArrayList<EcBindingPdOrdeDetails>();
					for (EcBindingPdOrdeDetails ecp : ecList) {
						EcBindingPdOrdeDetails ee = new EcBindingPdOrdeDetails();
						if (null != ecp.getBinding_id()) {
							ee.setBinding_id(ecp.getBinding_id());
						}
						ee.setTrade_index(pp2.getTrade_index());
						ee.setState(0);
						ee.setGoods_name(ecp.getGoods_name());
						ee.setNum(ecp.getNum());
						ee.setPrice(ecp.getPrice());
						ee.setTotal_price(ecp.getTotal_price());
						ec1.add(ee);
						service_price2 = service_price2.add(ecp.getTotal_price());
					}

					PshowOrdeDetails p2 = new PshowOrdeDetails();
					if (null != p1.getNum()) {
						p2.setNum(p1.getNum());
					}
					if (null != p1.getPd_id()) {
						p2.setPd_id(p1.getPd_id());
					}
					// if (null != p1.getRebates_status()) {
					// p2.setRebates_status(p1.getRebates_status());
					// }
					if (null != p1.getState()) {
						p2.setState(p1.getState());
					}
					if (null != p1.getStore_id()) {
						p2.setStore_id(p1.getStore_id());
					}
					if (null != p1.getIntegral()) {
						p2.setIntegral(p1.getIntegral());
					}
					if (null != p1.getPd_name()) {
						p2.setPd_name(p1.getPd_name());
					}
					if (null != p1.getPrice()) {
						p2.setPrice(p1.getPrice());
					}
					if (null != p1.getRebates()) {
						p2.setRebates(p1.getRebates());
					}
					if (null != p1.getRebates_date()) {
						p2.setRebates_date(p1.getRebates_date());
					}
					if (null != p1.getRebates_sender()) {
						p2.setRebates_sender(p1.getRebates_sender());
					}
					// if (StringUtils.isNotBlank(remark)) {
					// p2.setRemark(remark);
					// }
					if (null != p1.getTotal_price()) {
						// p2.setTotal_price((p1.getPrice()).multiply(new
						// BigDecimal(p1.getNum().toString())).add(
						// service_price2).setScale(2,
						// BigDecimal.ROUND_HALF_UP));
						p2.setTotal_price(p1.getTotal_price());
					}

					p2.setEcBindingPdOrdeDetailsList(ec1);// 延保服务
					allPrice2 = allPrice2.add(p2.getTotal_price());
					allIntegral2 = allIntegral2.add(p1.getIntegral());// 计算订单总积分
					if (p1.getRebates() != null) {
						all_rebate2 = all_rebate2.add(p1.getRebates());
						p2.setRebates_status(p1.getRebates_status());
					}
					pshowOrdeDetailsList2.add(p2);
				}
			}
			if (entity.getIs_deduction() == 0) {
				deduPrice2 = all_rebate2;
				pp2.setIs_deduction(new Integer(0));
			}
			List<EcVouchers> ecVouchersList2 = new ArrayList<EcVouchers>();
			if (StringUtils.isNotBlank(select4)) {
				String[] s4 = select4.split(",");
				if (null != s4 && s4.length > 0) {
					for (String string : s4) {
						EcVouchers ec = new EcVouchers();
						ec.setId(Long.valueOf(string));
						ec = super.getFacade().getEcVouchersService().getEcVouchers(ec);
						if (null != ec) {
							deduPrice2 = deduPrice2.add(ec.getPrice());
							ecVouchersList2.add(ec);
						}
					}
				}

			}
			pp2.setEcVouchersList(ecVouchersList2);
		}
		pp2.setPshowOrdeDetailsList(pshowOrdeDetailsList2);
		pp2.setTotal_price(allPrice2);
		pp2.setIntegral(allIntegral2);
		BigDecimal payPrice2 = allPrice2.subtract(deduPrice2);// 订单付款金额=订单总价-抵扣金额
		pp2.setPay_price(payPrice2);
		pp2.setDedu_price(deduPrice2);
		pshowList.add(pp1);
		pshowList.add(pp2);
		super.getFacade().getPshowOrderService().createPshowOrderWithDetailsForSplit(pshowList, id);

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		// pathBuffer.append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		return forward;
	}
}
