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
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.domain.PshowOrdeAudit;
import com.ebiz.mmt.domain.PshowOrdeDetails;
import com.ebiz.mmt.domain.PshowOrder;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author TUDP
 * @version 2014-04-24
 */
public class PshowOrderExchangeAction extends BaseAction {

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
			if ( peRoleUser.getRole_id() < 30L ||peRoleUser.getRole_id().intValue()==140317 ||peRoleUser.getRole_id().intValue()==1001 ) {
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
			entity.setOrder_from(Integer.valueOf(order_from));
		}
		// 已发货的订单，才可以退货换货
		entity.getMap().put("state_in", new Integer[] { 40, 60 });
		entity.getMap().put("cust_pay_way", true);// 排除线下处理的订单

		Long recordCount = super.getFacade().getPshowOrderService().getPshowOrdeForDeptNameAndFullNameListCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<PshowOrder> entityList = super.getFacade().getPshowOrderService().getPshowOrdeForDeptNameAndFullNameList(
		        entity);
		request.setAttribute("entityList", entityList);
		request.setAttribute("sybDeptInfoList", super.getDeptInfoListWithOutLimit(mapping, form, request, response));

		return mapping.findForward("list");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
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

		int t_num = 0;
		double t_price = 0.00;
		for (PshowOrdeDetails ps : pshowordedetails) {
			t_num += ps.getNum();
			t_price += ps.getTotal_price().doubleValue();
		}

		request.setAttribute("t_num", t_num);
		request.setAttribute("t_price", t_price);
		request.setAttribute("pshowOrdeDetails", pshowordedetails);

		PshowOrdeAudit psa = new PshowOrdeAudit();
		psa.setOrder_id(Long.valueOf(id));
		psa.getMap().put("orderByDate", true);
		List<PshowOrdeAudit> pshowOrdeAudits = super.getFacade().getPshowOrdeAuditService().getPshowOrdeAuditList(psa);
		request.setAttribute("pshowOrdeAudits", pshowOrdeAudits);
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
		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);

		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String id = (String) dynaBean.get("id");
		String state1 = "80";// (String) dynaBean.get("state1");
		String remark1 = (String) dynaBean.get("remark1");
		String[] details_ids = ((String) dynaBean.get("details_ids")).split(",");
		String[] details_nums = ((String) dynaBean.get("details_nums")).split(",");
		String order_type = (String) dynaBean.get("order_type");
		String new_total_price = (String) dynaBean.get("new_total_price");
		String exchange_info = (String) dynaBean.get("exchange_info");
		if (!GenericValidator.isLong(id)) {
			super.saveError(request, "errors.long", "id");
			return this.list(mapping, form, request, response);
		}
		// 获取原订单信息
		PshowOrder order = new PshowOrder();
		order.setId(Long.valueOf(id));
		order = super.getFacade().getPshowOrderService().getPshowOrder(order);
		order.setPar_order_id(order.getId());

		// 重新计算订单明细
		PshowOrdeDetails psd = new PshowOrdeDetails();
		psd.setOrder_id(Long.valueOf(id));
		List<PshowOrdeDetails> pshowordedetails = new ArrayList<PshowOrdeDetails>();
		BigDecimal total_price = new BigDecimal("0.0");
		BigDecimal rebates = new BigDecimal("0.0");
		BigDecimal integral = new BigDecimal("0.0");//总积分
		BigDecimal all_pay_integral = new BigDecimal("0.0");//总积分

		BigDecimal new_pay_price = new BigDecimal("0.0");
		BigDecimal old_pay_price = order.getPay_price();

		if (new_total_price != null&&!"".equals(new_total_price)) {
			new_pay_price = new BigDecimal(new_total_price);
		}
		if (new_pay_price.floatValue() > old_pay_price.floatValue()) {
			String msg = "提示：新订单应付金额不能大于原订单应付金额!";
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		// 取产品的增值服务
		EcBindingPdOrdeDetails ec = new EcBindingPdOrdeDetails();
		for (int i = 0; i < details_ids.length; i++) {
			List<EcBindingPdOrdeDetails> all_bddetailsList = new ArrayList<EcBindingPdOrdeDetails>();
			psd = new PshowOrdeDetails();
			psd.setBill_item_id(new Long(details_ids[i]));
			psd.setOrder_id(Long.valueOf(id));
			psd = super.getFacade().getPshowOrdeDetailsService().getPshowOrdeDetails(psd);
			Long details_num = new Long(details_nums[i]);
			if (psd != null && psd.getNum().intValue() > details_num.intValue()) {// 重新计算总金额佣金返利
				psd.setTotal_price(psd.getTotal_price().divide(new BigDecimal(psd.getNum())).multiply(
				        new BigDecimal(details_num.intValue())));
			}
			// 计算佣金
			if (psd != null && psd.getNum().intValue() > details_num.intValue() && psd.getRebates() != null
			        && psd.getRebates().intValue() > 0) {
				psd.setRebates(psd.getRebates().divide(new BigDecimal(psd.getNum())).multiply(
				        new BigDecimal(details_num.intValue())));
				psd.getMap().put("rebates", psd.getRebates());
				 
			}
			// 计算积分
			if (psd != null && psd.getNum().intValue() >= details_num.intValue() && psd.getIntegral() != null
			        && psd.getIntegral().intValue() > 0) {
				psd.setIntegral(psd.getIntegral().divide(new BigDecimal(psd.getNum())).multiply(
				        new BigDecimal(details_num.intValue())));
				psd.getMap().put("integral", psd.getIntegral());
				integral = integral.add(psd.getIntegral());				 
			}
			if(psd.getPay_integral()!=null){
			all_pay_integral =all_pay_integral.add(psd.getPay_integral());
			}
			psd.setNum(details_num);
			// psd.setRebates_status(new Integer(0));//返利状态
			psd.setRebates_sender(null);
			psd.setRebates_date(null);
			if ("2".equals(order_type)) {
				psd.setState(new Integer(2));
			} else {
				psd.setState(new Integer(1));
			}
			total_price = total_price.add(psd.getTotal_price());
			// 处理增值服务
			ec = new EcBindingPdOrdeDetails();
			ec.setTrade_index(order.getTrade_index());
			ec.setDetails_id(psd.getBill_item_id());
			List<EcBindingPdOrdeDetails> ecbddetailsList = super.getFacade().getEcBindingPdOrdeDetailsService()
			        .getEcBindingPdOrdeDetailsList(ec);
			if (ecbddetailsList != null && ecbddetailsList.size() > 0) {
				EcBindingPdOrdeDetails ecBindingPdOrdeDetails = new EcBindingPdOrdeDetails();
				for (int z = 0; z < ecbddetailsList.size(); z++) {
					ecBindingPdOrdeDetails = new EcBindingPdOrdeDetails();
					ecBindingPdOrdeDetails = ecbddetailsList.get(z);
					ecBindingPdOrdeDetails.setNum(psd.getNum());
					ecBindingPdOrdeDetails.setTotal_price(ecBindingPdOrdeDetails.getPrice().multiply(
					        new BigDecimal(details_num.intValue())));
					all_bddetailsList.add(ecBindingPdOrdeDetails);
				}
			}
			psd.setEcBindingPdOrdeDetailsList(all_bddetailsList);
			pshowordedetails.add(psd);
		}

		if (new_pay_price.floatValue() < 0.0001 && new_pay_price.floatValue() > total_price.floatValue() + 0.001) {
			String msg = "提示：新订单应付金额错误!";
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		order.setId(null);
		order.setIntegral(integral);
		order.setDedu_price(new BigDecimal("0.0"));
		order.setTotal_price(total_price);
		order.setPay_price(total_price);
		order.getMap().put("pay_price", new_pay_price);
		order.getMap().put("integral", integral);
		order.setAdd_date(new Date());
		order.setTrade_no(null);
		order.setState(new Integer(5));
		order.setPay_integral(all_pay_integral);

		if ("2".equals(order_type)) {
			order.setPay_price(new_pay_price);
			state1 = "90";
		}
		// 审核记录表插入记录
		PshowOrdeAudit poa = new PshowOrdeAudit();
		poa.setRemark(remark1);
		poa.setOper_date(new Date());
		poa.setOrder_id(Long.valueOf(id));
		poa.setOpr_user_id(user.getId());
		poa.setOpr_user_real_name(user.getUser_name());
		poa.setState(Integer.valueOf(state1));
		poa.setRemark(remark1);
		poa.setTotal_price(old_pay_price.subtract(new_pay_price));

		order.getMap().put("exchange_info", exchange_info);//退机类型
		super.getFacade().getPshowOrderService().createPshowOrderForExchange(order, poa, pshowordedetails,
		        new Integer(order_type));
		super.saveMessage(request, "entity.updated");
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		return forward;
	}
}
