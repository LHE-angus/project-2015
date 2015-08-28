package com.ebiz.mmt.web.struts.manager.spgl;

import java.io.OutputStream;
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

import com.ebiz.mmt.domain.EcBaseExpress;
import com.ebiz.mmt.domain.EcBindingPdOrdeDetails;
import com.ebiz.mmt.domain.EcOrderExpressInfo;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.domain.PshowOrdeAudit;
import com.ebiz.mmt.domain.PshowOrdeDetails;
import com.ebiz.mmt.domain.PshowOrder;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.util.ExcelUtil;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author TUDP
 * @version 2014-05-12
 */
public class ShOrderLookAction extends BasePshowOrderAction {

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
			if ( peRoleUser.getRole_id() < 30L ||peRoleUser.getRole_id().intValue()==140317 ||peRoleUser.getRole_id().intValue()==1001 ) {
				role_id_eq_30 = true;
				request.setAttribute("is_admin", "0");
			}
			// 分公司管理员
			if (peRoleUser.getRole_id() >= 30L && peRoleUser.getRole_id() <= 188L) {
				role_id_gt_30_lt_60 = true;
			}
		}

		PshowOrder entity = new PshowOrder();

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
		if (StringUtils.isNotBlank(dept_id)) {
			entity.setOpr_dept_id(Long.valueOf(dept_id));
		}
		if (StringUtils.isNotBlank(is_ps)) {
			entity.setIs_ps(Integer.valueOf(is_ps));
		}
		entity.setDeliver_way(1);// 1：选择了安装调试选项
		if (StringUtils.isNotBlank(pay_way)) {
			entity.setPay_way(Integer.valueOf(pay_way));
		}
		if (StringUtils.isNotBlank(trade_no_like)) {
			entity.getMap().put("trade_no_like", trade_no_like);
		}
		if (StringUtils.isNotBlank(order_from)) {
			entity.setOrder_from(Integer.valueOf(order_from));
		}
		if (StringUtils.isNotBlank(add_time_start)) {
			entity.getMap().put("add_time_start", add_time_start + " 00:00:00");
		}
		if (StringUtils.isNotBlank(add_time_end)) {
			entity.getMap().put("add_time_end", add_time_end + " 23:59:59");
		}
		entity.getMap().put("state_in", new Integer[] { 10, 20, 30, 40, 50, 60 });
		Long recordCount = super.getFacade().getPshowOrderService().getPshowOrdeForDeptNameAndFullNameListCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<PshowOrder> entityList = super.getFacade().getPshowOrderService().getPshowOrdeForDeptNameAndFullNameList(
		        entity);
		if (null != entityList && entityList.size() > 0) {
			for (PshowOrder pp : entityList) {
				int t_num = 0;
				EcOrderExpressInfo ec = new EcOrderExpressInfo();
				ec.setTrade_index(pp.getTrade_index());
				ec = super.getFacade().getEcOrderExpressInfoService().getEcOrderExpressInfo(ec);
				if (null != ec) {
					pp.getMap().put("in_sf", true);
				}
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
				pp.getMap().put("total_num", t_num);

			}

		}

		request.setAttribute("entityList", entityList);
		KonkaDept sysdept = new KonkaDept();
		sysdept.setDept_type(new Integer(3));
		request.setAttribute("sybDeptInfoList", getFacade().getKonkaDeptService().getKonkaDeptList(sysdept));

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
			if (peRoleUser.getRole_id() < 30L) {
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
		entity.getMap().put("state_in", new Integer[] { 10, 20, 30, 40, 50, 60 });
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
		e.setCell(8, "购买人手机号码");
		e.setCell(9, "运单号");
		e.setCell(10, "发货分公司");
		e.setCell(11, "二次送");

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
			e.setCell(8, pds.getBuyer_mp());
			e.setCell(9, (String) pds.getMap().get("log_sn"));
			e.setCell(10, (String) pds.getMap().get("dept_name"));
			String ph = "";
			if (pds.getIs_ps() == 0) {
				ph = "否";
			} else if (pds.getIs_ps() == 1) {
				ph = "是";
			}
			e.setCell(11, ph);

		}
		// 输出
		response.setHeader("Content-disposition", "attachment; filename=noname.xls");// 设定输出文件头
		response.setContentType("application/vnd.ms-excel");// 定义输出类型
		OutputStream out = response.getOutputStream();
		e.exportXLS(out);
		return null;
	}

	@SuppressWarnings("unchecked")
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

		EcOrderExpressInfo ee = new EcOrderExpressInfo();
		ee.setTrade_index(entity.getTrade_index());
		ee = super.getFacade().getEcOrderExpressInfoService().getEcOrderExpressInfo(ee);

		if (null != ee) {
			// if (null != ee.getLogistic_content()) {
			// dynaBean.set("order_state", "2");
			// request.setAttribute("logistic_content",
			// ee.getLogistic_content());
			// } else {
			dynaBean.set("order_state", "1");
			// }
		} else {
			dynaBean.set("order_state", "0");
			if (entity.getState() == 40 || entity.getState() == 50 || entity.getState() == 60) {
				if (null != entity.getExpress_id()) {
					EcBaseExpress ec = new EcBaseExpress();
					ec.setExpress_id(entity.getExpress_id());
					ec = super.getFacade().getEcBaseExpressService().getEcBaseExpress(ec);
					if (ec.getExpress_ui_type() == 1) {
						dynaBean.set("is_add", true);
					}
				}

			}

		}

		if (null != ee) {
			dynaBean.set("logistic_sn", ee.getLogistic_sn());
		} else {
			dynaBean.set("logistic_sn", "暂无运单号");
		}

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
		}
		PshowOrdeAudit psa = new PshowOrdeAudit();
		psa.setOrder_id(Long.valueOf(id));
		psa.getMap().put("orderByDate", true);
		List<PshowOrdeAudit> PshowOrdeAudits = super.getFacade().getPshowOrdeAuditService().getPshowOrdeAuditList(psa);

		request.setAttribute("t_num", t_num);
		request.setAttribute("t_price", t_price);
		request.setAttribute("pshowOrdeDetails", pshowordedetails);
		request.setAttribute("PshowOrdeAudits", PshowOrdeAudits);

		// 子订单查询
		PshowOrder zOrder = new PshowOrder();
		zOrder.setPar_order_id(new Long(id));
		List<PshowOrder> zOrderlist = super.getFacade().getPshowOrderService().getPshowOrderIncludeDetailsList(zOrder);
		request.setAttribute("zOrderlist", zOrderlist);

		return mapping.findForward("view");
	}

	public ActionForward lookSfState(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;

		String id = (String) dynaBean.get("id");
		PshowOrder entity = new PshowOrder();
		entity.setId(Long.valueOf(id));
		entity = super.getFacade().getPshowOrderService().getPshowOrder(entity);

		EcOrderExpressInfo ec = new EcOrderExpressInfo();
		ec.setTrade_index(entity.getTrade_index());
		ec = super.getFacade().getEcOrderExpressInfoService().getEcOrderExpressInfo(ec);

		String ss = orderState(ec);
		super.render(response, ss, "text/x-json;charset=UTF-8");
		return null;
	}

	public ActionForward showPrint3(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		dynaBean.set("id", id);

		PshowOrder p = new PshowOrder();
		p.setId(Long.valueOf(id));
		p = super.getFacade().getPshowOrderService().getPshowOrder(p);
		if (null != p) {
			EcOrderExpressInfo ec = new EcOrderExpressInfo();
			ec.setTrade_index(p.getTrade_index());
			ec = super.getFacade().getEcOrderExpressInfoService().getEcOrderExpressInfo(ec);
			if (null == ec) {
				super.renderJavaScript(response, "window.onload=function(){alert('对不起！该订单目前还没有运单号！');history.back();}");
				return null;
			}
		}

		return new ActionForward("/spgl/PshowOrderLook/print3.jsp");
	}

	@SuppressWarnings("unchecked")
	public ActionForward sheet(ActionMapping mapping, ActionForm form, HttpServletRequest request,
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

		EcOrderExpressInfo ee = new EcOrderExpressInfo();
		ee.setTrade_index(entity.getTrade_index());
		ee = super.getFacade().getEcOrderExpressInfoService().getEcOrderExpressInfo(ee);

		if (null != ee) {
			dynaBean.set("order_state", "1");
		} else {
			dynaBean.set("order_state", "0");
			if (entity.getState() == 40 || entity.getState() == 50 || entity.getState() == 60) {
				if (null != entity.getExpress_id()) {
					EcBaseExpress ec = new EcBaseExpress();
					ec.setExpress_id(entity.getExpress_id());
					ec = super.getFacade().getEcBaseExpressService().getEcBaseExpress(ec);
					if (ec.getExpress_ui_type() == 1) {
						dynaBean.set("is_add", true);
					}
				}
			}
		}

		if (null != ee) {
			dynaBean.set("logistic_sn", ee.getLogistic_sn());
		} else {
			dynaBean.set("logistic_sn", "暂无运单号");
		}

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
		}
		PshowOrdeAudit psa = new PshowOrdeAudit();
		psa.setOrder_id(Long.valueOf(id));
		psa.getMap().put("orderByDate", true);
		List<PshowOrdeAudit> PshowOrdeAudits = super.getFacade().getPshowOrdeAuditService().getPshowOrdeAuditList(psa);

		request.setAttribute("t_num", t_num);
		request.setAttribute("t_price", t_price);
		request.setAttribute("pshowOrdeDetails", pshowordedetails);
		request.setAttribute("PshowOrdeAudits", PshowOrdeAudits);
		response.setHeader("Content-disposition", "attachment; filename=noname.xls");// 设定输出文件头
		response.setContentType("application/vnd.ms-excel");// 定义输出类型
		return new ActionForward("/spgl/PshowOrderLook/sheet.jsp");
	}
}
