package com.ebiz.mmt.web.struts.manager.pshow;

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

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	        throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	        throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String trade_index_like = (String) dynaBean.get("trade_index_like");
		String order_user_name_like = (String) dynaBean.get("order_user_name_like");
		String state = (String) dynaBean.get("state");
		String buyer_name_like = (String) dynaBean.get("buyer_name_like");
		String buyer_mp_like = (String) dynaBean.get("buyer_mp_like");

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
			if (peRoleUser.getRole_id() < 30L) {
				role_id_eq_30 = true;
			}
			// 分公司管理员
			if (peRoleUser.getRole_id() >= 30L && peRoleUser.getRole_id() < 60L) {
				role_id_gt_30_lt_60 = true;
			}
		}
		PshowOrder entity = new PshowOrder();
		// 总部可以查看所有订单
		// 分公司只能看到分公司的订单
		if (role_id_gt_30_lt_60) {
			entity.setOpr_dept_id(user.getDept_id());
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
		entity.getMap().put("edit_state", true);

		Long recordCount = super.getFacade().getPshowOrderService().getPshowOrdeForDeptNameAndFullNameListCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<PshowOrder> entityList = super.getFacade().getPshowOrderService().getPshowOrdeForDeptNameAndFullNameList(entity);

		request.setAttribute("entityList", entityList);

		return mapping.findForward("list");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	        throws Exception {
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
		List<PshowOrdeDetails> pshowordedetails = super.getFacade().getPshowOrdeDetailsService().getPshowOrdeForPDSNDetailsList(psd);

		int t_num = 0;
		double t_price = 0.00;
		for (PshowOrdeDetails ps : pshowordedetails) {
			t_num += ps.getNum();
			t_price += ps.getTotal_price().doubleValue();
		}

		request.setAttribute("t_num", t_num);
		request.setAttribute("t_price", t_price);
		request.setAttribute("pshowOrdeDetails", pshowordedetails);

		return new ActionForward("/pshow/PshowOrderEdit/edit.jsp");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	        throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String id = (String) dynaBean.get("id");
		String buyer_name = (String) dynaBean.get("buyer_name");
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
		if (StringUtils.isNotBlank(buyer_name)) {
			entity.setBuyer_name(buyer_name);
		}
		if (StringUtils.isNotBlank(buyer_tel)) {
			entity.setBuyer_tel(buyer_tel);
		}
		if (StringUtils.isNotBlank(buyer_mp)) {
			entity.setBuyer_mp(buyer_mp);
		}
		if (StringUtils.isNotBlank(deliver_way)) {
			entity.setDeliver_way(Integer.valueOf(deliver_way));
		}
		if (StringUtils.isNotBlank(pay_way)) {
			entity.setPay_way(Integer.valueOf(pay_way));
		}
		if (StringUtils.isNotBlank(state)) {
			entity.setState(Integer.valueOf(state));
		}
		if (StringUtils.isNotBlank(trade_no)) {
			entity.setTrade_no(trade_no);
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

		super.getFacade().getPshowOrderService().modifyPshowOrder(entity);
		entity.setQueryString(super.serialize(request, "id", "method"));
		super.copyProperties(form, entity);

		PshowOrdeDetails psd = new PshowOrdeDetails();
		psd.setOrder_id(Long.valueOf(id));
		List<PshowOrdeDetails> pshowordedetails = super.getFacade().getPshowOrdeDetailsService().getPshowOrdeDetailsList(psd);

		int t_num = 0;
		double t_price = 0.00;
		for (PshowOrdeDetails ps : pshowordedetails) {
			t_num += ps.getNum();
			t_price += ps.getTotal_price().doubleValue();
		}

		PshowOrdeAudit psa = new PshowOrdeAudit();
		psa.setOrder_id(Long.valueOf(id));
		psa.setState(Integer.valueOf(state));
		psa.setOpr_user_id(user.getId());
		psa.setOpr_user_real_name(user.getUser_name());
		java.math.BigDecimal bd1 = new java.math.BigDecimal(t_price);
		psa.setTotal_price(bd1);
		super.getFacade().getPshowOrdeAuditService().createPshowOrdeAudit(psa);

		request.setAttribute("t_num", t_num);
		request.setAttribute("t_price", t_price);
		request.setAttribute("pshowOrdeDetails", pshowordedetails);

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		return forward;
	}

}
