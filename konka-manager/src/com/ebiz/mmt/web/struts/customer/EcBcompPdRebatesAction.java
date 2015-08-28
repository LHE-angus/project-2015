package com.ebiz.mmt.web.struts.customer;

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

import com.ebiz.mmt.domain.EcBindingPdOrdeDetails;
import com.ebiz.mmt.domain.EcUser;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PshowOrdeAudit;
import com.ebiz.mmt.domain.PshowOrdeDetails;
import com.ebiz.mmt.domain.PshowOrder;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Liu,ZhiXiang
 * @version 2013-09-11
 */
public class EcBcompPdRebatesAction extends BaseAction {
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
		String trade_no_like = (String) dynaBean.get("trade_no_like");
		String excel_all = (String) dynaBean.get("excel_all");

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.CUSTOMER_USER_INFO);
		if (null == user) {
			return null;
		}

		PshowOrder entity = new PshowOrder();
		EcUser ec = new EcUser();
		ec.setC_id(user.getId());
		List<EcUser> ecList = super.getFacade().getEcUserService().getEcUserList(ec);
		if (null == ecList || ecList.size() == 0) {
			super.renderJavaScript(response, "window.onload=function(){alert('该用户没用绑定会员！');history.back();}");
			return null;
		} else {
			EcUser ec1 = new EcUser();
			ec1 = ecList.get(0);
			entity.setOrder_user_id(ec1.getId());
			entity.setState(60);
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
		if (StringUtils.isNotBlank(trade_no_like)) {
			entity.getMap().put("trade_no_like", trade_no_like);
		}
		// entity.setState(60);// 已收货

		Long recordCount = super.getFacade().getPshowOrderService().getPshowOrdeForDeptNameAndFullNameListCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<PshowOrder> entityList = super.getFacade().getPshowOrderService().getPshowOrdeForDeptNameAndFullNameList(
		        entity);
		for (PshowOrder tt : entityList) {
			double t_rebates = 0.00;
			PshowOrdeDetails psd = new PshowOrdeDetails();
			psd.setOrder_id(tt.getId());
			List<PshowOrdeDetails> pshowordedetails = super.getFacade().getPshowOrdeDetailsService()
			        .getPshowOrdeForPDSNDetailsList(psd);
			for (PshowOrdeDetails ps : pshowordedetails) {
				if (null != ps.getRebates()) {
					t_rebates += ps.getRebates().doubleValue();
				}
			}
			tt.getMap().put("t_rebates", t_rebates);

		}

		request.setAttribute("entityList", entityList);

		if (StringUtils.isNotBlank(excel_all) && "1".equals(excel_all)) {
			entity.getRow().setCount(recordCount.intValue());
			List<PshowOrder> entityList1 = super.getFacade().getPshowOrderService()
			        .getPshowOrdeForDeptNameAndFullNameList(entity);
			double t_rebates2 = 0.00;
			for (PshowOrder tt : entityList1) {
				PshowOrdeDetails psd = new PshowOrdeDetails();
				psd.setOrder_id(tt.getId());
				List<PshowOrdeDetails> pshowordedetails = super.getFacade().getPshowOrdeDetailsService()
				        .getPshowOrdeForPDSNDetailsList(psd);
				for (PshowOrdeDetails ps : pshowordedetails) {
					if (null != ps.getRebates()) {
						t_rebates2 += ps.getRebates().doubleValue();
					}
				}
				tt.getMap().put("t_rebates2", t_rebates2);

			}
			dynaBean.set("excel_all", excel_all);
			logger.info("allList-------------->{}", entityList1.size());
			request.setAttribute("allList", entityList1);
		}

		return mapping.findForward("list");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");

		if (!GenericValidator.isLong(id)) {
			String msg = super.getMessage(request, "param.isEmpty", new String[] { "Id" });
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		PshowOrder entity = new PshowOrder();
		entity.setId(Long.valueOf(id));

		entity = super.getFacade().getPshowOrderService().getPshowOrder(entity);
		entity.setQueryString(super.serialize(request, "id", "method"));
		super.copyProperties(form, entity);

		// 地区全称显示
		if (null != entity.getBuyer_p_index()) {
			request.setAttribute("p_index_name", super.getPIndexName(entity.getBuyer_p_index().toString(), ""));
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

		PshowOrdeAudit psa = new PshowOrdeAudit();
		psa.setOrder_id(Long.valueOf(id));
		psa.getMap().put("orderByDate", true);
		List<PshowOrdeAudit> PshowOrdeAudits = super.getFacade().getPshowOrdeAuditService().getPshowOrdeAuditList(psa);

		request.setAttribute("t_num", t_num);
		request.setAttribute("t_price", t_price);
		request.setAttribute("pshowOrdeDetails", pshowordedetails);
		request.setAttribute("PshowOrdeAudits", PshowOrdeAudits);

		return new ActionForward("/../customer/EcBcompPdRebates/view1.jsp");
	}

}