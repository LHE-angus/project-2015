package com.ebiz.mmt.web.struts.manager.spgl;

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
import com.ebiz.mmt.domain.KonkaDept;
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
public class PshowOrderConfirmAction extends BaseAction {

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
			if ( peRoleUser.getRole_id() < 30L ||peRoleUser.getRole_id().intValue()==140317 ||peRoleUser.getRole_id().intValue()==1001 ) {
				role_id_eq_30 = true;
			}
			// 分公司管理员
			if (peRoleUser.getRole_id() >= 30L && peRoleUser.getRole_id() <= 188L) {
				role_id_gt_30_lt_60 = true;
			}
		}
		PshowOrder entity = new PshowOrder();
		// 总部可以看到所有
		// 分公司只能看到自己的分公司
		// 白电/小家电 只能看到自己的订单
		if (role_id_eq_30) {
			entity.setOpr_dept_id(null);
		} else if (role_id_gt_30_lt_60) {
			entity.setOpr_dept_id(super.getSuperiorForDeptType(user.getDept_id(), 3).getDept_id());
		} else {
			entity.setOpr_dept_id(user.getDept_id());
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
		entity.setState(40);// 只有发货状态下，才可以确认收货
		// entity.getMap().put("confirm_state", true);
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

	public ActionForward confirm(ActionMapping mapping, ActionForm form, HttpServletRequest request,
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

		// 取增值服务
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

		KonkaDept kd = new KonkaDept();
		kd.getMap().put("dept_for_paixu", entity.getDept_id());
		List<KonkaDept> sybDeptInfoList = super.getFacade().getKonkaDeptService().getKonkaDeptListForPaifa(kd);

		request.setAttribute("t_num", t_num);
		request.setAttribute("t_price", t_price);
		request.setAttribute("pshowOrdeDetails", pshowordedetails);
		request.setAttribute("sybDeptInfoList", sybDeptInfoList);

		return new ActionForward("/spgl/PshowOrderConfirm/form.jsp");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String id = (String) dynaBean.get("id");
		String state2 = (String) dynaBean.get("state2");
		String remark1 = (String) dynaBean.get("remark1");

		if (!GenericValidator.isLong(id)) {
			super.saveError(request, "errors.long", "id");
			return this.list(mapping, form, request, response);
		}

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);

		PshowOrder p1 = new PshowOrder();
		p1.setId(Long.valueOf(id));
		p1 = super.getFacade().getPshowOrderService().getPshowOrder(p1);
		if (p1.getState() != 40) {
			super.renderJavaScript(response, "window.onload=function(){alert('订单状态已经发生改变！');history.back();}");
			return null;
		}

		PshowOrdeDetails psd = new PshowOrdeDetails();
		psd.setOrder_id(Long.valueOf(id));
		List<PshowOrdeDetails> pshowordedetails = super.getFacade().getPshowOrdeDetailsService()
		        .getPshowOrdeDetailsList(psd);

		int t_num = 0;
		double t_price = 0.00;
		for (PshowOrdeDetails ps : pshowordedetails) {
			t_num += ps.getNum();
			t_price += ps.getTotal_price().doubleValue();
		}

		request.setAttribute("t_num", t_num);
		request.setAttribute("t_price", t_price);
		request.setAttribute("pshowOrdeDetails", pshowordedetails);

		PshowOrder pp = new PshowOrder();
		pp.setId(Long.valueOf(id));
		pp = super.getFacade().getPshowOrderService().getPshowOrder(pp);

		// 更新订单表
		PshowOrder entity = new PshowOrder();
		entity.setQueryString(super.serialize(request, "id", "method"));
		super.copyProperties(form, entity);
		if (StringUtils.isNotBlank(id)) {
			entity.setId(Long.valueOf(id));
		}
		entity.setState(Integer.valueOf(state2));

		// 审核记录表插入记录
		PshowOrdeAudit poa = new PshowOrdeAudit();
		poa.setOper_date(new Date());
		poa.setOrder_id(Long.valueOf(id));
		poa.setState(Integer.valueOf(state2));
		poa.setRemark(remark1);
		poa.setOpr_user_id(user.getId());
		poa.setOpr_user_real_name(user.getUser_name());
		// java.math.BigDecimal bd1 = new java.math.BigDecimal(t_price);
		poa.setTotal_price(pp.getPay_price());

		super.getFacade().getPshowOrderService().createPshowOrdeAuditAndModifyPshowOrder(entity, poa);

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		logger.info("QueryString---->{}" + super.encodeSerializedQueryString(entity.getQueryString()));
		pathBuffer.append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		return forward;
	}
	
	public ActionForward index(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form; 
		String trade_index_all = (String) dynaBean.get("trade_index_all"); 
		if(trade_index_all!=null){
			trade_index_all=trade_index_all.trim(); 
		}
		
		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}
		if(trade_index_all!=null&&!"".equals(trade_index_all)){
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
			if (role_id_eq_30) {
				entity.setOpr_dept_id(null);
			} else if (role_id_gt_30_lt_60) {
				entity.setOpr_dept_id(super.getSuperiorForDeptType(user.getDept_id(), 3).getDept_id());
			} else {
				entity.setOpr_dept_id(user.getDept_id());
			}
			if (StringUtils.isNotBlank(trade_index_all)) {
				entity.getMap().put("trade_index_all", trade_index_all.split(","));
			} 
			entity.setState(40);// 只有发货状态下，才可以确认收货 
			entity.getMap().put("cust_pay_way", true);
	
			Long recordCount = super.getFacade().getPshowOrderService().getPshowOrdeForDeptNameAndFullNameListCount(entity); 
			entity.getRow().setFirst(0);
			entity.getRow().setCount(recordCount.intValue());
			List<PshowOrder> entityList = super.getFacade().getPshowOrderService().getPshowOrdeForDeptNameAndFullNameList(entity); 
			request.setAttribute("entityList", entityList);
		}
		return new ActionForward("/spgl/PshowOrderConfirm/index.jsp");
	}
	
	public ActionForward saveAll(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String[] ids = request.getParameterValues("ids");
		String state2 = (String) dynaBean.get("state2");
		String remark1 = (String) dynaBean.get("remark1");

		if (ids==null||ids.length<1) {
			super.saveError(request, "errors.long", "id");
			return this.list(mapping, form, request, response);
		}

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		for(int i=0;i<ids.length;i++){
			PshowOrder p1 = new PshowOrder();
			p1.setId(Long.valueOf(ids[i]));
			p1 = super.getFacade().getPshowOrderService().getPshowOrder(p1);
			if (p1.getState() != 40) {
				continue;
			}		
			PshowOrdeDetails psd = new PshowOrdeDetails();
			psd.setOrder_id(Long.valueOf(ids[i]));
			List<PshowOrdeDetails> pshowordedetails = super.getFacade().getPshowOrdeDetailsService().getPshowOrdeDetailsList(psd);
	
			int t_num = 0;
			double t_price = 0.00;
			for (PshowOrdeDetails ps : pshowordedetails) {
				t_num += ps.getNum();
				t_price += ps.getTotal_price().doubleValue();
			}
	
			request.setAttribute("t_num", t_num);
			request.setAttribute("t_price", t_price);
			request.setAttribute("pshowOrdeDetails", pshowordedetails);
	
			PshowOrder pp = new PshowOrder();
			pp.setId(Long.valueOf(ids[i]));
			pp = super.getFacade().getPshowOrderService().getPshowOrder(pp);
	
			// 更新订单表
			PshowOrder entity = new PshowOrder();
			entity.setQueryString(super.serialize(request, "id", "method"));
			super.copyProperties(form, entity);
			if (StringUtils.isNotBlank(ids[i])) {
				entity.setId(Long.valueOf(ids[i]));
			}
			entity.setState(Integer.valueOf(state2));
	
			// 审核记录表插入记录
			PshowOrdeAudit poa = new PshowOrdeAudit();
			poa.setOper_date(new Date());
			poa.setOrder_id(Long.valueOf(ids[i]));
			poa.setState(Integer.valueOf(state2));
			poa.setRemark(remark1);
			poa.setOpr_user_id(user.getId());
			poa.setOpr_user_real_name(user.getUser_name()); 
			poa.setTotal_price(pp.getPay_price());
	
			super.getFacade().getPshowOrderService().createPshowOrdeAuditAndModifyPshowOrder(entity, poa);
		}
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&"); 
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		return forward;
	}

}
