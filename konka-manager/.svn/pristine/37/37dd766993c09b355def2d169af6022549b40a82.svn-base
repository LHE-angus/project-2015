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
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.BaseProvinceListFour;
import com.ebiz.mmt.domain.EcBindingPdOrdeDetails;
import com.ebiz.mmt.domain.EcRule;
import com.ebiz.mmt.domain.EcRuleGoods;
import com.ebiz.mmt.domain.EcUser;
import com.ebiz.mmt.domain.KonkaBcompPd;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.domain.PshowOrdeAudit;
import com.ebiz.mmt.domain.PshowOrdeDetails;
import com.ebiz.mmt.domain.PshowOrder;
import com.ebiz.mmt.domain.SfhkRelEppOrder;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Pan,Gang
 * @version 2013-08-16
 */
public class PshowOrderAuditAction extends BaseAction {

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
		if (role_id_gt_30_lt_60) {
			entity.setOpr_dept_id(super.getSuperiorForDeptType(user.getDept_id(), 3).getDept_id());
		}
		// 总部和分公司之外的不能查看
		if (!role_id_gt_30_lt_60 && !role_id_eq_30) {
			String msg = super.getMessage(request, "konka.r3.roleError");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
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
			entity.setOrder_from(Integer.valueOf(order_from));
		}
		// 只有已付款的订单(或者是货到付款的订单)才可以审核
		//
		// entity.getMap().put("audit_state", true);
		entity.setState(10);
		entity.getMap().put("cust_pay_way", true);

		Long recordCount = super.getFacade().getPshowOrderService().getPshowOrdeForDeptNameAndFullNameListCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<PshowOrder> entityList = super.getFacade().getPshowOrderService().getPshowOrdeForDeptNameAndFullNameList(
		        entity);

		request.setAttribute("entityList", entityList);

		return mapping.findForward("list");
	}

	public ActionForward audit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
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

		EcUser ecUser = new EcUser();
		ecUser.setId(entity.getOrder_user_id());
		ecUser = super.getFacade().getEcUserService().getEcUser(ecUser);

		// 地区全称显示
		if (null != entity.getBuyer_p_index()) {
			BaseProvinceListFour baseProvinceListFour = new BaseProvinceListFour();
			baseProvinceListFour.setP_index(entity.getBuyer_p_index());
			baseProvinceListFour = super.getFacade().getBaseProvinceListFourService().getBaseProvinceListFour(
			        baseProvinceListFour);

			String p_index_name = baseProvinceListFour.getFull_name();

			request.setAttribute("p_index_name", p_index_name);
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

		List<PshowOrdeDetails> pshowordedetails2 = new ArrayList<PshowOrdeDetails>();
		int t_num = 0;
		double t_price = 0.00;
		for (PshowOrdeDetails ps : pshowordedetails) {
			t_num += ps.getNum();
			t_price += ps.getTotal_price().doubleValue();
			if (null != ps.getRebates_status() && ps.getRebates_status().intValue() == 4) {
				pshowordedetails2.add(ps);
			}

			EcRuleGoods ecg = new EcRuleGoods();
			ecg.setGoods_id(Long.valueOf(ps.getPd_id()));
			List<EcRuleGoods> ecgList = super.getFacade().getEcRuleGoodsService().getEcRuleGoodsList(ecg);

			BigDecimal rule_price_2 = new BigDecimal("0.00");
			if (null != ecgList && ecgList.size() > 0) {
				List<EcRule> egList = new ArrayList<EcRule>();
				for (EcRuleGoods ecRuleGoods : ecgList) {
					EcRule er = new EcRule();
					er.setRule_type(3);// 优惠费用
					er.setId(ecRuleGoods.getRule_id());
					if (ecUser.getUser_type().intValue() == 2) {
						er.setDept_id(ecUser.getDept_id());
						er.setOwn_sys(2);
					} else if (ecUser.getUser_type().intValue() == 1) {
						er.setOwn_sys(1);
					}
					er = super.getFacade().getEcRuleService().getEcRule(er);
					if (null != er) {
						egList.add(er);
					}
				}
				if (null != egList && egList.size() > 0) {

					// 根据当前用户的pindex找到所在的省份
					BaseProvinceListFour bf = new BaseProvinceListFour();
					bf.setP_level(1);
					bf.getMap().put("pindex", entity.getBuyer_p_index().toString());
					List<BaseProvinceListFour> bfList = super.getFacade().getBaseProvinceListFourService()
					        .getBaseProvinceListFourForProvinceList(bf);
					if (null != bfList && bfList.size() > 0) {
						bf = bfList.get(0);
					}

					// 把包含该pindex的rule_price放到list
					List<Double> list = new ArrayList<Double>();
					for (EcRule ec1 : egList) {
						if (ec1.getIs_price().intValue() == 1 && ec1.getInfo_state().intValue() == 1
						        && null != ec1.getRule_area_allow() && !"".equals(ec1.getRule_area_allow())) {
							if (ec1.getRule_area_allow().contains(bf.getP_index().toString())) {
								list.add(ec1.getRule_price().doubleValue());
							}
						}
					}

					// 对list进行排序，从大到小
					if (null != list && list.size() > 0) {
						for (int k = 0; k < list.size() - 1; k++) {
							for (int j = 1; j < list.size() - k; j++) {
								Double a;
								if ((list.get(j - 1)).compareTo(list.get(j)) < 0) { // 比较两个整数的大小

									a = list.get(j - 1);
									list.set((j - 1), list.get(j));
									list.set(j, a);
								}
							}
						}
						rule_price_2 = rule_price_2.add(new BigDecimal(list.get(0).toString()));// 取最大的一个
						ps.getMap().put("sub_price", rule_price_2);
					}

				}

			}

		}

		PshowOrdeAudit pat = new PshowOrdeAudit();
		// pat.setState(entity.getState());
		pat.setOrder_id(Long.valueOf(id));
		pat.getMap().put("orderByDate", true);

		SfhkRelEppOrder sf = new SfhkRelEppOrder();
		sf.setEpp_order_id(id);
		sf = super.getFacade().getSfhkRelEppOrderService().getSfhkRelEppOrder(sf);
		if (sf != null) {
			dynaBean.set("sfhk_order", sf.getSf_order_id());
		} else {
			dynaBean.set("sfhk_order", "");
		}

		List<PshowOrdeAudit> pshowOrdeAudits = super.getFacade().getPshowOrdeAuditService().getPshowOrdeAuditList(pat);
		if (pshowOrdeAudits.size() > 0 && null != pshowOrdeAudits.get(0)) {
			request.setAttribute("audit_remark", pshowOrdeAudits.get(0).getRemark());
		} else {
			request.setAttribute("audit_remark", "未审核");
		}
		request.setAttribute("pshowOrdeAudits", pshowOrdeAudits);

		request.setAttribute("t_num", t_num);
		request.setAttribute("t_price", t_price);
		request.setAttribute("pshowOrdeDetails", pshowordedetails);
		request.setAttribute("pshowOrdeDetails2", pshowordedetails2);

		return mapping.findForward("input");
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
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String id = (String) dynaBean.get("id");
		String state1 = (String) dynaBean.get("state1");
		String remark1 = (String) dynaBean.get("remark1");

		if (!GenericValidator.isLong(id)) {
			super.saveError(request, "errors.long", "id");
			return this.list(mapping, form, request, response);
		}

		PshowOrder p1 = new PshowOrder();
		p1.setId(Long.valueOf(id));
		p1 = super.getFacade().getPshowOrderService().getPshowOrder(p1);
		if (p1.getState() != 10) {
			super.renderJavaScript(response, "window.onload=function(){alert('订单状态已经发生改变！');history.back();}");
			return null;
		}

		// 当前登录人审核
		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);

		PshowOrdeDetails psd = new PshowOrdeDetails();
		psd.setOrder_id(Long.valueOf(id));
		List<PshowOrdeDetails> pshowordedetails = super.getFacade().getPshowOrdeDetailsService()
		        .getPshowOrdeDetailsList(psd);

		int t_num = 0;
		double t_price = 0.00;
		int pro_type = 0;// 特别处理团购
		for (PshowOrdeDetails ps : pshowordedetails) {
			KonkaBcompPd kp = new KonkaBcompPd();
			kp.setId(ps.getPd_id());
			kp = super.getFacade().getKonkaBcompPdService().getKonkaBcompPd(kp);
			if (kp != null && kp.getProd_type().intValue() == 8) {
				pro_type = 1;
			}

			t_num += ps.getNum();
			t_price += ps.getTotal_price().doubleValue();
		}

		request.setAttribute("t_num", t_num);
		request.setAttribute("t_price", t_price);
		request.setAttribute("pshowOrdeDetails", pshowordedetails);

		// 更新订单表
		PshowOrder entity = new PshowOrder();
		entity.setQueryString(super.serialize(request, "id", "method"));
		super.copyProperties(form, entity);
		if (StringUtils.isNotBlank(id)) {
			entity.setId(Long.valueOf(id));
		}
		entity.setState(Integer.valueOf(state1));
		if (Integer.valueOf(state1) == 20) {
			entity.getMap().put("content", "客户1，您好，你的订单号是111，预计6天可以到货");
		}

		PshowOrder pp = new PshowOrder();
		pp.setId(Long.valueOf(id));
		pp = super.getFacade().getPshowOrderService().getPshowOrder(pp);

		// 审核记录表插入记录
		PshowOrdeAudit poa = new PshowOrdeAudit();
		poa.setRemark(remark1);
		poa.setOper_date(new Date());
		poa.setOrder_id(Long.valueOf(id));
		poa.setState(Integer.valueOf(state1));
		poa.setOpr_user_id(user.getId());
		poa.setOpr_user_real_name(user.getUser_name());
		poa.setState(Integer.valueOf(state1));
		// java.math.BigDecimal bd1 = new java.math.BigDecimal(t_price);
		poa.setTotal_price(pp.getPay_price());

		if (pro_type == 0) {
			super.getFacade().getPshowOrderService().createPshowOrdeAuditAndModifyPshowOrder(entity, poa);
		} else if (pro_type == 1) {
			entity.getMap().put("tuangou", "2");
			super.getFacade().getPshowOrderService().createPshowOrdeAuditAndModifyPshowOrderForTuan(entity, poa);
		}

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		return forward;
	}
}
