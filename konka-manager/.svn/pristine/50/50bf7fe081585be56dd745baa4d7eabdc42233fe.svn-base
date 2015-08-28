package com.ebiz.mmt.web.struts.manager.spgl;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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
import com.ebiz.mmt.web.util.ExcelUtil;
import com.ebiz.ssi.util.EncryptUtils;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Pan,Gang
 * @version 2013-08-16
 */
public class PshowOrderConfirm2Action extends BaseAction {

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
		// if (role_id_eq_30) {
		// entity.setOpr_dept_id(user.getDept_id());
		// }
		// if (role_id_gt_30_lt_60) {
		// entity.setOpr_dept_id(super.getSuperiorForDeptType(user.getDept_id(),
		// 3).getDept_id());
		// }
		// 总部和分公司之外的不能查看
		if (!role_id_gt_30_lt_60 && !role_id_eq_30) {
			String msg = super.getMessage(request, "konka.r3.roleError");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		if (StringUtils.isNotBlank(trade_index_like)) {
			String[] trade_index_in = trade_index_like.split(",");
			if (null != trade_index_in && trade_index_in.length > 0) {
				entity.getMap().put("trade_index_in", trade_index_in);
			}
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
		entity.setState(60);// 
		entity.setPay_way(0);// 总部财务 确认收款，只能看到货到付款的订单
		entity.setIs_hdfk(0);// 财务未点击确认的
		entity.getMap().put("cust_pay_way", true);

		Long recordCount = super.getFacade().getPshowOrderService().getPshowOrdeForDeptNameAndFullNameListCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<PshowOrder> entityList = super.getFacade().getPshowOrderService().getPshowOrdeForDeptNameAndFullNameList(
		        entity);

		int total_num = 0;
		BigDecimal total_p = new BigDecimal("0.00");
		entity.getRow().setFirst(0);
		entity.getRow().setCount(recordCount.intValue());
		List<PshowOrder> entityList2 = super.getFacade().getPshowOrderService().getPshowOrdeForDeptNameAndFullNameList(
		        entity);
		if (null != entityList2 && entityList2.size() > 0) {
			for (PshowOrder pp : entityList2) {
				int t_num = 0;
				PshowOrdeDetails psd = new PshowOrdeDetails();
				psd.setOrder_id(Long.valueOf(pp.getId()));
				List<PshowOrdeDetails> pshowordedetails = super.getFacade().getPshowOrdeDetailsService()
				        .getPshowOrdeForPDSNDetailsList(psd);
				pp.setPshowOrdeDetailsList(pshowordedetails);
				if (null != pshowordedetails && pshowordedetails.size() > 0) {
					for (PshowOrdeDetails ps : pshowordedetails) {
						t_num += ps.getNum();
					}
				}
				total_num = total_num + t_num;
				if (pp.getPay_price() != null) {
					total_p = total_p.add(pp.getPay_price());
				}
			}
		}

		request.setAttribute("totolCount", recordCount);
		request.setAttribute("total_num", total_num);
		request.setAttribute("total_p", total_p);

		request.setAttribute("entityList", entityList);

		return mapping.findForward("list");
	}

	public ActionForward excel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String trade_index_like = (String) dynaBean.get("trade_index_like");
		String order_user_name_like = (String) dynaBean.get("order_user_name_like");
		String state = (String) dynaBean.get("state");
		String buyer_name_like = (String) dynaBean.get("buyer_name_like");
		String buyer_mp_like = (String) dynaBean.get("buyer_mp_like");
		String dept_id = (String) dynaBean.get("dept_id");
		String trade_no_like = (String) dynaBean.get("trade_no_like");
		String order_from = (String) dynaBean.get("order_from");
		String pay_way = (String) dynaBean.get("pay_way");
		String add_time_start = (String) dynaBean.get("add_time_start");
		String add_time_end = (String) dynaBean.get("add_time_end");
		String is_ps = (String) dynaBean.get("is_ps");

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
		logger.info("++++++++++++++" + role_id_gt_30_lt_60);

		PshowOrder entity = new PshowOrder();
		// 总部可以查看所有订单
		// 分公司只能看到分公司的订单
		if (role_id_gt_30_lt_60) {
			entity.setOpr_dept_id(super.getSuperiorForDeptType(user.getDept_id(), 3).getDept_id());
		}
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
		if (StringUtils.isNotBlank(is_ps)) {
			entity.setIs_ps(Integer.valueOf(is_ps));
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
		if (StringUtils.isNotBlank(dept_id)) {
			entity.setOpr_dept_id(Long.valueOf(dept_id));
		}
		if (StringUtils.isNotBlank(trade_no_like)) {
			entity.getMap().put("trade_no_like", trade_no_like);
		}
		if (StringUtils.isNotBlank(order_from)) {
			entity.setOrder_from(Integer.valueOf(order_from));
		}
		if (StringUtils.isNotBlank(pay_way)) {
			entity.setPay_way(Integer.valueOf(pay_way));
		}
		if (StringUtils.isNotBlank(add_time_start)) {
			entity.getMap().put("add_time_start", add_time_start + " 00:00:00");
		}
		if (StringUtils.isNotBlank(add_time_end)) {
			entity.getMap().put("add_time_end", add_time_end + " 23:59:59");
		}

		entity.setState(60);// 
		entity.setPay_way(0);// 总部财务 确认收款，只能看到货到付款的订单
		entity.setIs_hdfk(0);// 财务未点击确认的
		entity.getMap().put("cust_pay_way", true);

		Long recordCount = super.getFacade().getPshowOrderService().getPshowOrdeForDeptNameAndFullNameListCount(entity);

		entity.getRow().setFirst(new Integer(0));
		entity.getRow().setCount(new Integer(recordCount.intValue()));

		List<PshowOrder> entityList = super.getFacade().getPshowOrderService().getPshowOrdeForDeptNameAndFullNameList(
		        entity);

		ExcelUtil e = new ExcelUtil();
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet();
		int r = 0;
		e.setWorkbook(workbook);
		e.setSheet(sheet);
		e.createRow(r);
		e.setCell(0, "序号");
		e.setCell(1, "交易流水号");
		e.setCell(2, "订单状态");
		e.setCell(3, "当前处理的部门");
		e.setCell(4, "订单类型");
		e.setCell(5, "下单人姓名");
		e.setCell(6, "购买人姓名");
		e.setCell(7, "购买人地区");
		e.setCell(8, "支付单号");
		e.setCell(9, "购买人手机号码");
		e.setCell(10, "支付方式");
		e.setCell(11, "物流费用");
		e.setCell(12, "运单号");
		e.setCell(13, "订单总金额");
		e.setCell(14, "发货分公司");
		e.setCell(15, "二次配货");
		e.setCell(16, "数量");

		for (PshowOrder pds : entityList) {
			r++;
			e.createRow(r);
			e.setCell(0, r);
			e.setCell(1, pds.getTrade_index());
			String s_state = "";
			if (pds.getState() == -30) {
				s_state = "已退货";
			} else if (pds.getState() == -20) {
				s_state = "审核未通过";
			} else if (pds.getState() == -10) {
				s_state = "已关闭";
			} else if (pds.getState() == 0) {
				s_state = "已预订";
			} else if (pds.getState() == 10) {
				s_state = "已付款";
			} else if (pds.getState() == 20) {
				s_state = "审核通过";
			} else if (pds.getState() == 30) {
				s_state = "下发处理";
			} else if (pds.getState() == 40) {
				s_state = "商家发货";
			} else if (pds.getState() == 50) {
				s_state = "客户已换货";
			} else if (pds.getState() == 60) {
				s_state = "确认收货";
			}
			e.setCell(2, s_state);
			e.setCell(3, (String) pds.getMap().get("dept_name"));
			if ("1".equals(pds.getOrder_from().toString())) {
				e.setCell(4, "工卡");
			} else if ("2".equals(pds.getOrder_from().toString())) {
				e.setCell(4, "触网");
			} else if ("3".equals(pds.getOrder_from().toString())) {
				e.setCell(4, "其他");
			}
			e.setCell(5, pds.getOrder_user_name());
			e.setCell(6, pds.getBuyer_name());
			e.setCell(7, (String) pds.getMap().get("full_name"));
			e.setCell(8, pds.getTrade_no());
			e.setCell(9, pds.getBuyer_mp());
			if ("0".equals(pds.getPay_way().toString())) {
				e.setCell(10, "货到付款");
			} else if ("1".equals(pds.getPay_way().toString())) {
				e.setCell(10, "银行付款");
			} else if ("2".equals(pds.getPay_way().toString())) {
				e.setCell(10, "支付宝");
			} else if ("3".equals(pds.getPay_way().toString())) {
				e.setCell(10, "银联");
			} else if ("4".equals(pds.getPay_way().toString())) {
				e.setCell(10, "财付通");
			} else if ("5".equals(pds.getPay_way().toString())) {
				e.setCell(10, "民生银行");
			}

			BigDecimal logistic_price = (BigDecimal) pds.getMap().get("logistic_price");// 物流费用
			if (logistic_price == null) {
				logistic_price = new BigDecimal("0.0");
			}
			e.setCell(11, logistic_price.toString());
			e.setCell(12, (String) pds.getMap().get("log_sn"));
			BigDecimal pay_price = pds.getPay_price();
			if (pay_price == null) {
				pay_price = new BigDecimal("0.0");
			}
			e.setCell(13, pay_price.toString());
			e.setCell(14, (String) pds.getMap().get("dept_name"));
			String ph = "";
			if (pds.getIs_ps() == 0) {
				ph = "否";
			} else if (pds.getIs_ps() == 1) {
				ph = "是";
			}
			e.setCell(15, ph);

			int t_num = 0;
			PshowOrdeDetails psd = new PshowOrdeDetails();
			psd.setOrder_id(Long.valueOf(pds.getId()));
			List<PshowOrdeDetails> pshowordedetails = super.getFacade().getPshowOrdeDetailsService()
			        .getPshowOrdeForPDSNDetailsList(psd);
			if (null != pshowordedetails && pshowordedetails.size() > 0) {
				for (PshowOrdeDetails ps : pshowordedetails) {
					t_num += ps.getNum();
				}
			}
			e.setCell(16, t_num);

		}
		// 输出
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename=" + EncryptUtils.encodingFileName("货到付款确认收货表")
				+ ".xls");
		OutputStream out = response.getOutputStream();
		e.exportXLS(out);
		return null;
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

		return new ActionForward("/spgl/PshowOrderConfirm2/form.jsp");
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
		if (p1.getState() != 60 || p1.getIs_hdfk() != 0) {
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

		// 更新订单表
		PshowOrder entity = new PshowOrder();
		entity.setQueryString(super.serialize(request, "id", "method"));
		super.copyProperties(form, entity);
		if (StringUtils.isNotBlank(id)) {
			entity.setId(Long.valueOf(id));
		}
		entity.setState(Integer.valueOf(state2));
		entity.setIs_hdfk(1);// 表示财务已经收到顺丰货款

		PshowOrder pp = new PshowOrder();
		pp.setId(Long.valueOf(id));
		pp = super.getFacade().getPshowOrderService().getPshowOrder(pp);

		// 审核记录表插入记录
		PshowOrdeAudit poa = new PshowOrdeAudit();
		poa.setOper_date(new Date());
		poa.setOrder_id(Long.valueOf(id));
		poa.setState(70);
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

	public ActionForward saveBatch(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String[] pks = (String[]) dynaBean.get("pks"); 
		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}
		super.getFacade().getPshowOrderService().createPshowOrdeAuditAndModifyPshowOrderForBatch(user, pks);
		super.saveMessage(request, "entity.updated");

		return new ActionForward("/../manager/spgl/PshowOrderConfirm2.do?method=list");
	}
	
	public ActionForward index(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form; 
		String trade_index_all = (String) dynaBean.get("trade_index_all"); 
		if(trade_index_all!=null){
			trade_index_all=trade_index_all.trim(); 
		}
		
		if(trade_index_all!=null&&!"".equals(trade_index_all)){ 
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
			if (!role_id_gt_30_lt_60 && !role_id_eq_30) {
				String msg = super.getMessage(request, "konka.r3.roleError");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			}
	 
			if (StringUtils.isNotBlank(trade_index_all)) {
				entity.getMap().put("trade_index_all", trade_index_all.split(","));
			} 
			entity.setState(60);// 
			entity.setPay_way(0);// 总部财务 确认收款，只能看到货到付款的订单
			entity.setIs_hdfk(0);// 财务未点击确认的
			entity.getMap().put("cust_pay_way", true);
	
			Long recordCount = super.getFacade().getPshowOrderService().getPshowOrdeForDeptNameAndFullNameListCount(entity); 
			entity.getRow().setFirst(0);
			entity.getRow().setCount(recordCount.intValue());
			List<PshowOrder> entityList = super.getFacade().getPshowOrderService().getPshowOrdeForDeptNameAndFullNameList(entity);
	 
			request.setAttribute("entityList", entityList);
		}
		return new ActionForward("/spgl/PshowOrderConfirm2/index.jsp");
	}

}
