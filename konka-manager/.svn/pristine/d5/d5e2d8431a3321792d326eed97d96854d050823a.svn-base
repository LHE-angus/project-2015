package com.ebiz.mmt.web.struts.manager.spgl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.alibaba.fastjson.JSON;
import com.ebiz.mmt.domain.BaseProvinceListFour;
import com.ebiz.mmt.domain.EcBaseExpress;
import com.ebiz.mmt.domain.EcBindingPdOrdeDetails;
import com.ebiz.mmt.domain.EcOrderExpressInfo;
import com.ebiz.mmt.domain.EcUser;
import com.ebiz.mmt.domain.EcVouchCode;
import com.ebiz.mmt.domain.KonkaBcompPd;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.domain.PshowOrdeAudit;
import com.ebiz.mmt.domain.PshowOrdeDetails;
import com.ebiz.mmt.domain.PshowOrder;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.util.ExcelUtil;
import com.ebiz.ssi.web.struts.bean.Pager;
import com.sf.integration.warehouse.Order;
import com.sf.integration.warehouse.OrderData;
import com.sf.integration.warehouse.OrderDataDetail;
import com.sf.integration.warehouse.SfOrderService;

/**
 * @author Pan,Gang
 * @version 2013-08-16
 */
public class PshowOrderSendForTuanAction extends BaseAction {

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
				request.setAttribute("is_admin", "0");
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
		logger.info("+++++++++++++++" + user.getDept_id());
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
		if (StringUtils.isNotBlank(dept_id)) {
			entity.setOpr_dept_id(Long.valueOf(dept_id));
		}
		entity.setState(5);// 特殊处理团购订单
		entity.getMap().put("cust_pay_way", true);
		entity.getMap().put("prod_type", 8);
		entity.getMap().put("is_djq", true);

		Long recordCount = super.getFacade().getPshowOrderService().getPshowOrdeForDeptNameAndFullNameListCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<PshowOrder> entityList = super.getFacade().getPshowOrderService().getPshowOrdeForDeptNameAndFullNameList(
		        entity);
		if (null != entityList && entityList.size() > 0) {
			for (PshowOrder pshowOrder : entityList) {
				PshowOrdeDetails pp = new PshowOrdeDetails();
				pp.setOrder_id(pshowOrder.getId());
				List<PshowOrdeDetails> ppList = super.getFacade().getPshowOrdeDetailsService()
				        .getPshowOrdeForPDSNDetailsList(pp);
				pshowOrder.setPshowOrdeDetailsList(ppList);
			}
		}

		request.setAttribute("entityList", entityList);
		request.setAttribute("sybDeptInfoList", super.getDeptInfoListWithOutLimit(mapping, form, request, response));

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
				request.setAttribute("is_admin", "0");
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
		logger.info("+++++++++++++++" + user.getDept_id());
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
		if (StringUtils.isNotBlank(dept_id)) {
			entity.setOpr_dept_id(Long.valueOf(dept_id));
		}
		entity.setState(5);// 只有下发状态下（总部处理或者分公司处理），才可以发货
		entity.getMap().put("cust_pay_way", true);
		entity.getMap().put("prod_type", 8);

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
		e.setCell(5, "工卡号");
		e.setCell(6, "下单人姓名");
		e.setCell(7, "部门");
		e.setCell(8, "购买人姓名");
		e.setCell(9, "购买人地区");
		e.setCell(10, "支付单号");
		e.setCell(11, "购买人手机号码");
		e.setCell(12, "支付方式");
		e.setCell(13, "物流费用");
		e.setCell(14, "运单号");
		e.setCell(15, "订单总金额");
		e.setCell(16, "发货分公司");
		e.setCell(17, "二次配货");
		e.setCell(18, "商品型号");
		e.setCell(19, "数量");
		// e.setCell(18, "详细地址");
		e.setCell(20, "下单时间");
		e.setCell(21, "是否索要发票");
		e.setCell(22, "发票类型");
		e.setCell(23, "发票抬头");
		e.setCell(24, "发票内容");
		e.setCell(25, "发票单位");
		e.setCell(26, "订单备注");
		e.setCell(27, "客户备注");
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

			EcUser ecUser = new EcUser();
			ecUser.setId(pds.getOrder_user_id());
			ecUser = super.getFacade().getEcUserService().getEcUser(ecUser);
			if (null != ecUser) {
				if ("1".equals(pds.getOrder_from().toString())) {
					e.setCell(4, "工卡");
				} else if ("2".equals(pds.getOrder_from().toString()) && ecUser.getDept_id() != null
				        && ecUser.getDept_id() == 2110) {
					e.setCell(4, "顺丰");
				} else if ("2".equals(pds.getOrder_from().toString()) && ecUser.getDept_id() != null
				        && ecUser.getDept_id() != 2110) {
					e.setCell(4, "触网");
				} else if ("3".equals(pds.getOrder_from().toString())) {
					e.setCell(4, "其他");
				}
			}
			e.setCell(5, ecUser.getCard_no());
			e.setCell(6, pds.getOrder_user_name());
			e.setCell(7, ecUser.getDepartment());
			e.setCell(8, pds.getBuyer_name());
			e.setCell(9, (String) pds.getMap().get("full_name") + pds.getBuyer_addr());
			e.setCell(10, pds.getTrade_no());
			e.setCell(11, pds.getBuyer_mp());
			if ("0".equals(pds.getPay_way().toString())) {
				e.setCell(12, "货到付款");
			} else if ("1".equals(pds.getPay_way().toString())) {
				e.setCell(12, "银行付款");
			} else if ("2".equals(pds.getPay_way().toString())) {
				e.setCell(12, "支付宝");
			} else if ("3".equals(pds.getPay_way().toString())) {
				e.setCell(12, "银联");
			} else if ("4".equals(pds.getPay_way().toString())) {
				e.setCell(12, "财付通");
			} else if ("5".equals(pds.getPay_way().toString())) {
				e.setCell(12, "民生银行");
			}

			BigDecimal logistic_price = (BigDecimal) pds.getMap().get("logistic_price");// 物流费用
			if (logistic_price == null) {
				logistic_price = new BigDecimal("0.0");
			}
			e.setCell(13, logistic_price.toString());
			e.setCell(14, (String) pds.getMap().get("log_sn"));
			BigDecimal pay_price = pds.getPay_price();
			if (pay_price == null) {
				pay_price = new BigDecimal("0.0");
			}
			e.setCell(15, pay_price.toString());
			e.setCell(16, (String) pds.getMap().get("dept_name"));
			String ph = "";
			if (pds.getIs_ps() == 0) {
				ph = "否";
			} else if (pds.getIs_ps() == 1) {
				ph = "是";
			}
			e.setCell(17, ph);
			String pd_sn = "";
			int num = 0;
			PshowOrdeDetails pd = new PshowOrdeDetails();
			pd.setOrder_id(pds.getId());
			List<PshowOrdeDetails> pdList = super.getFacade().getPshowOrdeDetailsService().getPshowOrdeDetailsList(pd);
			if (null != pdList && pdList.size() > 0) {
				for (PshowOrdeDetails pshowOrdeDetails : pdList) {
					KonkaBcompPd kp = new KonkaBcompPd();
					kp.setId(pshowOrdeDetails.getPd_id());
					kp = super.getFacade().getKonkaBcompPdService().getKonkaBcompPd(kp);
					if (null != kp && null != kp.getPd_sn() && !"".equals(kp.getPd_sn())) {
						pd_sn = pd_sn + kp.getPd_sn() + ",";
					}
					num += pshowOrdeDetails.getNum();
				}
			}
			e.setCell(18, pd_sn);
			e.setCell(19, num);
			// e.setCell(18, pds.getBuyer_addr());
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			e.setCell(20, sf.format(pds.getAdd_date()));
			if (null != pds.getBill_is_add()) {
				if (pds.getBill_is_add().intValue() == 0) {
					e.setCell(21, "否");
				} else if (pds.getBill_is_add().intValue() == 1) {
					e.setCell(21, "是");
				}
			}
			if (null != pds.getBill_type()) {
				if (pds.getBill_type().intValue() == 0) {
					e.setCell(22, "普通发票");
				} else if (pds.getBill_type().intValue() == 1) {
					e.setCell(22, "增值税发票");
				}
			}

			if (null != pds.getBill_head()) {
				if (pds.getBill_head().intValue() == 0) {
					e.setCell(23, "个人");
				} else if (pds.getBill_head().intValue() == 1) {
					e.setCell(23, "单位");
				}
			}

			if (null != pds.getBill_content()) {
				if (pds.getBill_content().intValue() == 0) {
					e.setCell(24, "明细");
				} else if (pds.getBill_content().intValue() == 1) {
					e.setCell(24, "办公用品");
				} else if (pds.getBill_content().intValue() == 2) {
					e.setCell(24, "电脑配件");
				} else if (pds.getBill_content().intValue() == 3) {
					e.setCell(24, "耗材");
				}
			}

			if (null != pds.getBill_company()) {
				e.setCell(25, pds.getBill_company());
			}
			if (null != pds.getRemark()) {
				e.setCell(26, pds.getRemark());
			}
			if (null != pds.getLogistic_sn()) {
				e.setCell(27, pds.getLogistic_sn());
			}

		}
		// 输出
		response.setHeader("Content-disposition", "attachment; filename=noname.xls");// 设定输出文件头
		response.setContentType("application/vnd.ms-excel");// 定义输出类型
		OutputStream out = response.getOutputStream();
		e.exportXLS(out);
		return null;
	}

	public ActionForward send(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		saveToken(request);
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String v_code = (String) dynaBean.get("v_code");

		PshowOrder entity = new PshowOrder();
		if (StringUtils.isNotBlank(id)) {
			entity.setId(Long.valueOf(id));
		}

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

		// if (role_id_eq_30) {
		// EcBaseExpress ebe = new EcBaseExpress();
		// ebe.setExpress_ui_type(1);
		// List<EcBaseExpress> ecBaseExpressList =
		// super.getFacade().getEcBaseExpressService().getEcBaseExpressList(
		// ebe);
		//
		// request.setAttribute("ecBaseExpressList", ecBaseExpressList);
		// } else if (role_id_gt_30_lt_60) {
		// EcBaseExpress ebe = new EcBaseExpress();
		// ebe.setExpress_ui_type(1);
		// List<EcBaseExpress> ecBaseExpressList =
		// super.getFacade().getEcBaseExpressService().getEcBaseExpressList(
		// ebe);
		//
		// request.setAttribute("ecBaseExpressList", ecBaseExpressList);
		// } else {
		// EcBaseExpress ebe = new EcBaseExpress();
		// ebe.setExpress_ui_type(100);
		// List<EcBaseExpress> ecBaseExpressList =
		// super.getFacade().getEcBaseExpressService().getEcBaseExpressList(
		// ebe);
		//
		// request.setAttribute("ecBaseExpressList", ecBaseExpressList);
		// }

		EcBaseExpress ebe = new EcBaseExpress();
		ebe.setExpress_ui_type(1);
		List<EcBaseExpress> ecBaseExpressList = super.getFacade().getEcBaseExpressService().getEcBaseExpressList(ebe);

		request.setAttribute("ecBaseExpressList", ecBaseExpressList);

		ebe.setExpress_ui_type(100);
		List<EcBaseExpress> ecBaseExpressList2 = super.getFacade().getEcBaseExpressService().getEcBaseExpressList(ebe);

		request.setAttribute("ecBaseExpressList2", ecBaseExpressList2);

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

		PshowOrdeAudit psa = new PshowOrdeAudit();
		psa.setOrder_id(Long.valueOf(id));
		psa.getMap().put("orderByDate", true);
		List<PshowOrdeAudit> PshowOrdeAudits = super.getFacade().getPshowOrdeAuditService().getPshowOrdeAuditList(psa);

		request.setAttribute("PshowOrdeAudits", PshowOrdeAudits);

		request.setAttribute("t_num", t_num);
		dynaBean.set("v_code", v_code);
		request.setAttribute("t_price", t_price);
		request.setAttribute("pshowOrdeDetails", pshowordedetails);

		return new ActionForward("/spgl/PshowOrderSendForTuan/send.jsp");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {

		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String id = (String) dynaBean.get("id");
		if (StringUtils.isBlank(id)) {
			if (isCancelled(request)) {
				return list(mapping, form, request, response);
			}
			if (!isTokenValid(request)) {
				saveError(request, "errors.token");
				return list(mapping, form, request, response);
			}
			resetToken(request);
		}
		String buyer_name = (String) dynaBean.get("buyer_name");
		String buyer_tel = (String) dynaBean.get("buyer_tel");
		String buyer_mp = (String) dynaBean.get("buyer_mp");
		String remark1 = (String) dynaBean.get("remark1");
		String express_id = (String) dynaBean.get("express_id");
		String code = (String) dynaBean.get("code");
		String order_from = (String) dynaBean.get("order_from");
		String order_to = (String) dynaBean.get("order_to");
		String logistic_sn = (String) dynaBean.get("logistic_sn");
		String remark = (String) dynaBean.get("remark");
		String express_id_2 = (String) dynaBean.get("express_id_2");
		logger.info("express_id_2---->" + express_id_2);
		logger.info("express_id---->" + express_id);
		String v_code = (String) dynaBean.get("v_code");

		if (!GenericValidator.isLong(id)) {
			super.saveError(request, "errors.long", "id");
			return this.list(mapping, form, request, response);
		}

		if (!GenericValidator.isLong(v_code)) {
			super.saveError(request, "errors.long", "v_code");
			return this.list(mapping, form, request, response);
		}

		EcVouchCode ev = new EcVouchCode();
		ev.setCode(v_code);
		ev = super.getFacade().getEcVouchCodeService().getEcVouchCode(ev);
		if (ev == null || ev.getIs_userd().intValue() == 1 || ev.getIs_del().intValue() == 1) {
			super.saveError(request, "errors.long", "v_code");
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
		if (p1.getState() != 5) {
			super.renderJavaScript(response, "window.onload=function(){alert('订单状态已经发生改变！');history.back();}");
			return null;
		}

		PshowOrder entity = new PshowOrder();
		if (StringUtils.isNotBlank(id)) {
			entity.setId(Long.valueOf(id));
		}
		if (StringUtils.isNotBlank(buyer_name)) {
			entity.setBuyer_name(buyer_name);
		}
		if (StringUtils.isNotBlank(remark)) {
			entity.setRemark(remark);
		}
		if (StringUtils.isNotBlank(buyer_tel)) {
			entity.setBuyer_tel(buyer_tel);
		}
		if (StringUtils.isNotBlank(buyer_mp)) {
			entity.setBuyer_mp(buyer_mp);
		}
		if (StringUtils.isNotBlank(express_id)) {
			entity.setExpress_id(Long.valueOf(express_id));
		}
		if (StringUtils.isNotBlank(express_id_2)) {
			entity.setExpress_id(Long.valueOf(express_id_2));
		}
		if (StringUtils.isNotBlank(code)) {
			entity.setCode(code);
		}

		entity.setState(60);// 直接确认收货
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

		// PshowOrder pp = new PshowOrder();
		// if (StringUtils.isNotBlank(id)) {
		// pp.setId(Long.valueOf(id));
		// }
		// pp = super.getFacade().getPshowOrderService().getPshowOrder(pp);

		// EcBaseExpress ec = new EcBaseExpress();
		// ec.setExpress_id(Long.valueOf(express_id));
		// ec =
		// super.getFacade().getEcBaseExpressService().getEcBaseExpress(ec);

		// EcOrderExpressInfo ex = new EcOrderExpressInfo();
		// ex.setTrade_index(pp.getTrade_index());
		// ex.setExpress_id(Long.valueOf(express_id));
		// if (null != ec.getExpress_name()) {
		// ex.setExpress_name(ec.getExpress_name());
		// }
		// ex.setLogistic_sn(logistic_sn);
		// ex.setOrder_from(order_from);
		// ex.setOrder_to(order_to);
		// ex.setAdd_date(new Date());

		PshowOrder p = new PshowOrder();
		p.setId(Long.valueOf(id));
		p = super.getFacade().getPshowOrderService().getPshowOrder(p);

		// 审核记录表插入记录
		PshowOrdeAudit poa = new PshowOrdeAudit();
		poa.setOper_date(new Date());
		poa.setOrder_id(Long.valueOf(id));
		poa.setState(60);
		poa.setRemark(remark1);
		poa.setOpr_user_id(user.getId());
		poa.setOpr_user_real_name(user.getUser_name());
		// java.math.BigDecimal bd1 = new java.math.BigDecimal(t_price);
		poa.setTotal_price(p.getPay_price());

		// 根据不同的分公司，取发货人的联系地址和联系方式
		if (null != user.getDept_id()) {
			KonkaDept konkaDept = new KonkaDept();
			konkaDept.setDept_id(user.getDept_id());
			konkaDept = super.getFacade().getKonkaDeptService().getKonkaDept(konkaDept);

			// 分公司取得
			if (null != konkaDept.getDept_type() && konkaDept.getDept_type() > 2) {
				KonkaDept fgs_dept = super.getSuperiorForDeptType(user.getDept_id(), 3);
				if (null != fgs_dept) {
					if (fgs_dept.getDept_sn().equalsIgnoreCase("KF22")) {// 合肥分公司
						entity.getMap().put("fgs", "1");
						logger.info("合肥分公司进来了-------》");
					} else if (fgs_dept.getDept_sn().equalsIgnoreCase("KF46")) {// 东莞分公司
						entity.getMap().put("fgs", "2");
						logger.info("东莞分公司进来了-------》");
					} else if (fgs_dept.getDept_sn().equalsIgnoreCase("KF34")) {// 哈尔滨分公司
						entity.getMap().put("fgs", "3");
						logger.info("哈尔滨分公司进来了-------》");
					}
				}
			}

		}

		entity.getMap().put("code_id", ev.getId());

		super.getFacade().getPshowOrderService().createPshowOrdeAuditAndModifyPshowOrderForTuan(entity, poa);

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		return forward;
	}

	public ActionForward validateSfState(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;

		StringBuffer oper = new StringBuffer("{\"result\":");
		String id = (String) dynaBean.get("id");

		PshowOrder p = new PshowOrder();
		p.setId(Long.valueOf(id));
		p = super.getFacade().getPshowOrderService().getPshowOrder(p);
		PshowOrdeDetails ps = new PshowOrdeDetails();
		ps.setOrder_id(Long.valueOf(id));
		List<PshowOrdeDetails> psList = super.getFacade().getPshowOrdeDetailsService().getPshowOrdeDetailsList(ps);
		if (null != psList && psList.size() > 0) {
			if (null != p) {
				SfOrderService sf = new SfOrderService();
				String sxddOpName = "wmsSailOrderService";
				OrderData orderData = new OrderData();
				orderData.setCheckword("01f18980363f40e48416464baf4cc7c0");
				orderData.setCompany("KONKA");
				orderData.setWarehouse("755DCA");
				orderData.setShop_name("康佳集团店");
				orderData.setErp_order(p.getTrade_index());
				orderData.setOrder_date(DateFormatUtils.format(p.getAdd_date(), "yyyy-MM-dd HH:mm:ss"));
				orderData.setShip_from_attention_to("康佳集团");
				orderData.setShip_from_address("深圳集团总部");
				orderData.setShip_from_postal_code("362200");
				orderData.setShip_from_phone_num("18065575018");
				orderData.setShip_from_tel_num("0595-82905852");
				orderData.setCarrier("顺丰速运");
				orderData.setShip_to_name("康佳");
				orderData.setShip_to_attention_to(p.getBuyer_name());
				orderData.setShip_to_country("中国");
				if (p.getBuyer_p_index() != null && p.getBuyer_p_index() != -1) {
					String p_index = p.getBuyer_p_index().toString();
					if (StringUtils.isNotBlank(p_index)) {
						if (!p_index.endsWith("00")) {
							if (p_index.length() == 6) {
								BaseProvinceListFour baseProvince = new BaseProvinceListFour();
								baseProvince.setP_index(Long.valueOf(StringUtils.substring(p_index, 0, 2) + "0000"));
								baseProvince = super.getFacade().getBaseProvinceListFourService()
								        .getBaseProvinceListFour(baseProvince);
								orderData.setShip_to_province(baseProvince.getP_name());

								BaseProvinceListFour baseProvince1 = new BaseProvinceListFour();
								baseProvince1.setP_index(Long.valueOf(StringUtils.substring(p_index, 0, 4) + "00"));
								baseProvince1 = super.getFacade().getBaseProvinceListFourService()
								        .getBaseProvinceListFour(baseProvince1);
								orderData.setShip_to_city(baseProvince1.getP_name());

								BaseProvinceListFour baseProvince2 = new BaseProvinceListFour();
								baseProvince2.setP_index(Long.valueOf(p_index));
								baseProvince2 = super.getFacade().getBaseProvinceListFourService()
								        .getBaseProvinceListFour(baseProvince2);
								orderData.setShip_to_area(baseProvince2.getP_name());
							} else if (p_index.length() == 8) {
								BaseProvinceListFour baseProvince = new BaseProvinceListFour();
								baseProvince.setP_index(Long.valueOf(StringUtils.substring(p_index, 0, 2) + "0000"));
								baseProvince = super.getFacade().getBaseProvinceListFourService()
								        .getBaseProvinceListFour(baseProvince);
								orderData.setShip_to_province(baseProvince.getP_name());

								BaseProvinceListFour baseProvince1 = new BaseProvinceListFour();
								baseProvince1.setP_index(Long.valueOf(StringUtils.substring(p_index, 0, 4) + "00"));
								baseProvince1 = super.getFacade().getBaseProvinceListFourService()
								        .getBaseProvinceListFour(baseProvince1);
								orderData.setShip_to_city(baseProvince1.getP_name());

								BaseProvinceListFour baseProvince2 = new BaseProvinceListFour();
								baseProvince2.setP_index(Long.valueOf(StringUtils.substring(p_index, 0, 6) + "00"));
								baseProvince2 = super.getFacade().getBaseProvinceListFourService()
								        .getBaseProvinceListFour(baseProvince2);
								orderData.setShip_to_area(baseProvince2.getP_name());

							}
						}
					}
				}
				orderData.setShip_to_address(p.getBuyer_addr());
				orderData.setShip_to_postal_code(p.getBuyer_zip());
				orderData.setShip_to_phone_num(p.getBuyer_tel());
				orderData.setOrder_total_amount(p.getTotal_price().toString());
				orderData.setShip_to_tel_num(p.getBuyer_mp());
				orderData.setMonthly_account("7550144315");
				OrderDataDetail d = new OrderDataDetail();
				List<OrderDataDetail> detailList = new ArrayList<OrderDataDetail>();
				Integer i = 0;
				for (PshowOrdeDetails pd : psList) {
					Integer j = i++;
					d.setErp_order_line_num(j.toString());
					d.setItem(pd.getPd_id().toString());
					d.setItem_name(pd.getPd_name());
					d.setUom("个");
					d.setQty(pd.getNum().toString());
					d.setItem_price(pd.getPrice().toString());
				}
				detailList.add(d);
				orderData.setOrderDataDetailList(detailList);

				String orderDataXml = orderData.toXml();
				//System.out.println("xxxxxxxxxxxxxxxxx");
				//System.out.println(orderDataXml);
				String returnXml;
				returnXml = sf.sfWebService(orderDataXml, sxddOpName);
				//System.out.println("销售订单导入返回xml------>>>");
				//System.out.println(returnXml);
				Document doc = DocumentHelper.parseText(returnXml);
				Element rootElt = doc.getRootElement(); // 获取根节点
				//System.out.println("根节点：" + rootElt.getName());
				String result1 = rootElt.elementTextTrim("result");
				String orderid = rootElt.elementTextTrim("orderid");
				//System.out.println(result1);
				//System.out.println(orderid);
				if (result1.equals("1")) {
					oper.append("1");
					PshowOrder pp = new PshowOrder();
					pp.setId(Long.valueOf(id));
					pp.setLogistic_sn(orderid);
					super.getFacade().getPshowOrderService().modifyPshowOrder(pp);
				} else {
					oper.append("2");
				}

			}
		}
		oper.append("}");
		//System.out.println(oper.toString());
		super.render(response, oper.toString(), "text/x-json;charset=UTF-8");
		return null;
	}

	public ActionForward showPrint(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");

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
		if (null != pshowordedetails && pshowordedetails.size() > 0) {
			for (PshowOrdeDetails pshowOrdeDetails2 : pshowordedetails) {
				KonkaBcompPd pd = new KonkaBcompPd();
				pd.setId(pshowOrdeDetails2.getPd_id());
				pd = super.getFacade().getKonkaBcompPdService().getKonkaBcompPd(pd);
				pshowOrdeDetails2.getMap().put("main_pic", pd.getMain_pic());
			}
		}

		// 取增值服务
		EcBindingPdOrdeDetails ec = new EcBindingPdOrdeDetails();
		ec.setTrade_index(entity.getTrade_index());
		List<EcBindingPdOrdeDetails> bddetailsList = super.getFacade().getEcBindingPdOrdeDetailsService()
		        .getEcBindingPdOrdeDetailsList(ec);
		request.setAttribute("bddetailsList", bddetailsList);

		Date date = new Date();
		request.setAttribute("today", date);

		int t_num = 0;
		double t_price = 0.00;
		for (PshowOrdeDetails ps : pshowordedetails) {
			t_num += ps.getNum();
			t_price += ps.getTotal_price().doubleValue();
		}

		request.setAttribute("t_num", t_num);
		request.setAttribute("t_price", t_price);
		request.setAttribute("pshowOrdeDetails", pshowordedetails);

		return new ActionForward("/spgl/PshowOrderSendForTuan/print.jsp");
	}

	public ActionForward showPrint2(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");

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
		if (null != pshowordedetails && pshowordedetails.size() > 0) {
			for (PshowOrdeDetails pshowOrdeDetails2 : pshowordedetails) {
				KonkaBcompPd pd = new KonkaBcompPd();
				pd.setId(pshowOrdeDetails2.getPd_id());
				pd = super.getFacade().getKonkaBcompPdService().getKonkaBcompPd(pd);
				pshowOrdeDetails2.getMap().put("main_pic", pd.getMain_pic());
			}
		}

		// 取增值服务
		EcBindingPdOrdeDetails ec = new EcBindingPdOrdeDetails();
		ec.setTrade_index(entity.getTrade_index());
		List<EcBindingPdOrdeDetails> bddetailsList = super.getFacade().getEcBindingPdOrdeDetailsService()
		        .getEcBindingPdOrdeDetailsList(ec);
		request.setAttribute("bddetailsList", bddetailsList);

		Date date = new Date();
		request.setAttribute("today", date);

		int t_num = 0;
		double t_price = 0.00;
		for (PshowOrdeDetails ps : pshowordedetails) {
			t_num += ps.getNum();
			t_price += ps.getTotal_price().doubleValue();
		}

		request.setAttribute("t_num", t_num);
		request.setAttribute("t_price", t_price);
		request.setAttribute("pshowOrdeDetails", pshowordedetails);

		return new ActionForward("/spgl/PshowOrderSendForTuan/print2.jsp");
	}

	public ActionForward showPrint3(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		dynaBean.set("id", id);

		return new ActionForward("/spgl/PshowOrderSendForTuan/print3.jsp");
	}

	public ActionForward getLogistic_sn(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String trade_index = (String) dynaBean.get("trade_index");
		String express_id = (String) dynaBean.get("express_id");
		String logistic_sn = "";
		String order_from = "";
		String order_to = "";
		String state = "0";
		// 查找订单
		PshowOrder p = new PshowOrder();
		p.setTrade_index(trade_index);
		p = super.getFacade().getPshowOrderService().getPshowOrder(p);

		EcBaseExpress ee = new EcBaseExpress();
		ee.setExpress_id(Long.valueOf(express_id));
		ee = super.getFacade().getEcBaseExpressService().getEcBaseExpress(ee);

		// 先去物流表里查询是否有运单号，如果有，直接返回运单号
		EcOrderExpressInfo ec = new EcOrderExpressInfo();
		if (StringUtils.isNotBlank(trade_index)) {
			ec.setTrade_index(trade_index);
		}
		ec = super.getFacade().getEcOrderExpressInfoService().getEcOrderExpressInfo(ec);

		if (null != ec && null != ec.getLogistic_sn() && !"".equals(ec.getLogistic_sn())) {
			logistic_sn = ec.getLogistic_sn();
			order_from = ec.getOrder_from();
			order_to = ec.getOrder_to();
			state = "1";
		} else {

			SfOrderService sf = new SfOrderService();
			String sxddmxOpName = "sfexpressService";
			Order order = new Order();
			order.setOrderid(trade_index);
			if (p.getBuyer_p_index() != null && p.getBuyer_p_index() != -1) {
				String p_index = p.getBuyer_p_index().toString();
				if (StringUtils.isNotBlank(p_index)) {
					if (!p_index.endsWith("00")) {
						if (p_index.length() == 6) {
							BaseProvinceListFour baseProvince = new BaseProvinceListFour();
							baseProvince.setP_index(Long.valueOf(StringUtils.substring(p_index, 0, 2) + "0000"));
							baseProvince = super.getFacade().getBaseProvinceListFourService().getBaseProvinceListFour(
							        baseProvince);
							order.setD_province(baseProvince.getP_name());

							BaseProvinceListFour baseProvince1 = new BaseProvinceListFour();
							baseProvince1.setP_index(Long.valueOf(StringUtils.substring(p_index, 0, 4) + "00"));
							baseProvince1 = super.getFacade().getBaseProvinceListFourService().getBaseProvinceListFour(
							        baseProvince1);
							order.setD_city(baseProvince1.getP_name());

							BaseProvinceListFour baseProvince2 = new BaseProvinceListFour();
							baseProvince2.setP_index(Long.valueOf(p_index));
							baseProvince2 = super.getFacade().getBaseProvinceListFourService().getBaseProvinceListFour(
							        baseProvince2);
							order.setD_county(baseProvince2.getP_name());
						} else if (p_index.length() == 8) {
							BaseProvinceListFour baseProvince = new BaseProvinceListFour();
							baseProvince.setP_index(Long.valueOf(StringUtils.substring(p_index, 0, 2) + "0000"));
							baseProvince = super.getFacade().getBaseProvinceListFourService().getBaseProvinceListFour(
							        baseProvince);
							order.setD_province(baseProvince.getP_name());

							BaseProvinceListFour baseProvince1 = new BaseProvinceListFour();
							baseProvince1.setP_index(Long.valueOf(StringUtils.substring(p_index, 0, 4) + "00"));
							baseProvince1 = super.getFacade().getBaseProvinceListFourService().getBaseProvinceListFour(
							        baseProvince1);
							order.setD_city(baseProvince1.getP_name());

							BaseProvinceListFour baseProvince2 = new BaseProvinceListFour();
							baseProvince2.setP_index(Long.valueOf(StringUtils.substring(p_index, 0, 6)));
							baseProvince2 = super.getFacade().getBaseProvinceListFourService().getBaseProvinceListFour(
							        baseProvince2);
							order.setD_county(baseProvince2.getP_name());

						}
					} else if (p_index.endsWith("0000")) {
						if (p_index.length() == 6) {
							BaseProvinceListFour baseProvince = new BaseProvinceListFour();
							baseProvince.setP_index(Long.valueOf(p_index));
							baseProvince = super.getFacade().getBaseProvinceListFourService().getBaseProvinceListFour(
							        baseProvince);
							order.setD_province(baseProvince.getP_name());
							order.setD_city("");
							order.setD_county("");
						} else if (p_index.length() == 8) {
							BaseProvinceListFour baseProvince = new BaseProvinceListFour();
							baseProvince.setP_index(Long.valueOf(p_index));
							baseProvince = super.getFacade().getBaseProvinceListFourService().getBaseProvinceListFour(
							        baseProvince);
							order.setD_province(baseProvince.getP_name());

							BaseProvinceListFour baseProvince1 = new BaseProvinceListFour();
							baseProvince1.setP_index(Long.valueOf(StringUtils.substring(p_index, 0, 4) + "00"));
							baseProvince1 = super.getFacade().getBaseProvinceListFourService().getBaseProvinceListFour(
							        baseProvince1);
							order.setD_city(baseProvince1.getP_name());
							order.setD_county("");
						}
					} else if (p_index.endsWith("00")) {
						if (p_index.length() == 6) {
							BaseProvinceListFour baseProvince = new BaseProvinceListFour();
							baseProvince.setP_index(Long.valueOf(StringUtils.substring(p_index, 0, 2) + "0000"));
							baseProvince = super.getFacade().getBaseProvinceListFourService().getBaseProvinceListFour(
							        baseProvince);
							order.setD_province(baseProvince.getP_name());

							BaseProvinceListFour baseProvince1 = new BaseProvinceListFour();
							baseProvince1.setP_index(Long.valueOf(p_index));
							baseProvince1 = super.getFacade().getBaseProvinceListFourService().getBaseProvinceListFour(
							        baseProvince1);
							order.setD_city(baseProvince1.getP_name());
							order.setD_county("");

						} else if (p_index.length() == 8) {

							BaseProvinceListFour baseProvince = new BaseProvinceListFour();
							baseProvince.setP_index(Long.valueOf(StringUtils.substring(p_index, 0, 2) + "0000"));
							baseProvince = super.getFacade().getBaseProvinceListFourService().getBaseProvinceListFour(
							        baseProvince);
							order.setD_province(baseProvince.getP_name());

							BaseProvinceListFour baseProvince1 = new BaseProvinceListFour();
							baseProvince1.setP_index(Long.valueOf(StringUtils.substring(p_index, 0, 4) + "00"));
							baseProvince1 = super.getFacade().getBaseProvinceListFourService().getBaseProvinceListFour(
							        baseProvince1);
							order.setD_city(baseProvince1.getP_name());

							BaseProvinceListFour baseProvince2 = new BaseProvinceListFour();
							baseProvince2.setP_index(Long.valueOf(StringUtils.substring(p_index, 0, 6)));
							baseProvince2 = super.getFacade().getBaseProvinceListFourService().getBaseProvinceListFour(
							        baseProvince2);
							order.setD_county(baseProvince2.getP_name());

						}
					}
				}
			}

			order.setD_contact(p.getBuyer_name());
			order.setD_tel(p.getBuyer_tel());
			order.setD_mobile(p.getBuyer_mp());
			order.setD_address(p.getBuyer_addr());

			order.setCargo("");
			order.setCargo_count("");
			order.setCargo_unit("");
			order.setCargo_weight("");
			order.setCargo_amount("");
			order.setCargo_total_weight("1");
			order.setD_company("");
			// order.setParcel_quantity(String.valueOf(t_num));// 包裹数
			order.setParcel_quantity("1");// 包裹数
			order.setSendstarttime("");

			// 如果是货到付款
			if (p.getPay_way() == 0) {
				order.setPay_way("0");
				order.setValue(p.getPay_price().toString());
			}

			String xml = order.toXml();
			//System.out.println("订单xml------>{}" + xml);
			String returnXml = sf.sfWebService2(xml, sxddmxOpName);
			//System.out.println("下订单入顺丰--------->{}" + returnXml);
			try {
				Document doc = DocumentHelper.parseText(returnXml);
				Element rootElt = doc.getRootElement();
				if (rootElt.elementTextTrim("Head").equalsIgnoreCase("OK")) {
					Iterator<Element> iter = rootElt.elementIterator("Body");
					while (iter.hasNext()) {
						Element iter2 = iter.next();
						Iterator<Element> iter3 = iter2.elementIterator("OrderResponse");
						while (iter3.hasNext()) {
							Element iter4 = iter3.next();
							if (StringUtils.isNotBlank(iter4.attributeValue("mailno"))) {
								logistic_sn = iter4.attributeValue("mailno");
								order_from = iter4.attributeValue("origincode");
								order_to = iter4.attributeValue("destcode");
								EcOrderExpressInfo ex = new EcOrderExpressInfo();
								ex.setTrade_index(trade_index);
								ex.setExpress_id(Long.valueOf(express_id));
								if (null != ee.getExpress_name()) {
									ex.setExpress_name(ee.getExpress_name());
								}
								ex.setLogistic_sn(logistic_sn);
								ex.setOrder_from(order_from);
								ex.setOrder_to(order_to);
								ex.setAdd_date(new Date());
								super.getFacade().getEcOrderExpressInfoService().createEcOrderExpressInfo(ex);
								state = "1";
								//System.out.println("订单入顺丰成功！！！");
							}
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				logistic_sn = "";
				state = "0";
				System.err.println("报错啦！！" + e);
			}
		}

		Map<String, String> maps = new HashMap<String, String>();
		logger.info("运单号------------->" + logistic_sn);
		maps.put("logistic_sn", logistic_sn);
		maps.put("order_from", order_from);
		maps.put("order_to", order_to);
		maps.put("state", state);
		super.renderJson(response, JSON.toJSONString(maps));
		return null;
	}

	public ActionForward sendMobileMessage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String logistic_sn = (String) dynaBean.get("logistic_sn");
		String mobile = (String) dynaBean.get("mobile");
		String code = "";

		URL url = new URL("http://106.ihuyi.com/webservice/sms.php?method=Submit");
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setDoOutput(true);
		// POST方式
		con.setRequestMethod("POST");
		OutputStream os = con.getOutputStream();

		// 输出流，写数据
		String content = "account=cf_kjjt0&password=XwPYLA&mobile=" + mobile + "&content=您的验证码是：4526。请不要把验证码泄露给其他人。";
		os.write(content.getBytes());
		// 提交用户名和密码.当然你要知道这个网站用户名和密码的变量名称向里面传值
		BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
		// 读取结果
		String line = "";
		String ss = "";
		while ((line = reader.readLine()) != null) {
			// //System.out.println("返回String--------->" + line);
			// 输出返回信息,这是一个字符传 你需要分析字符串来判断是否登陆成功.
			ss = ss + line;
		}
		//System.out.println("sssssssss2--------->" + ss);

		Document doc = DocumentHelper.parseText(ss);
		Element rootElt = doc.getRootElement();
		Iterator<Element> iter = rootElt.elementIterator("code");
		while (iter.hasNext()) {
			Element iter2 = iter.next();
			//System.out.println(iter2.getText());
			code = iter2.getText();
		}
		Map<String, String> maps = new HashMap<String, String>();
		logger.info("短信返回代码------------->" + code);
		maps.put("code", code);
		super.renderJson(response, JSON.toJSONString(maps));

		return null;
	}

	public ActionForward ajaxSave(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String code = (String) dynaBean.get("code");
		String mod_id = (String) dynaBean.get("mod_id");

		StringBuffer sb = new StringBuffer("{");
		sb = sb.append("\"status\":");
		if (user == null) {
			sb = sb.append("-1");
			sb = sb.append("}");
			super.renderJson(response, sb.toString());
			return null;
		}

		// 账户中心用户登录验证
		// if (user.getUser_type().intValue() == 1) {
		// String touch = (String) session.getAttribute("touch");
		// request.setAttribute("touch", touch);
		// }

		PshowOrder pp = new PshowOrder();
		pp.setId(Long.valueOf(id));
		pp = super.getFacade().getPshowOrderService().getPshowOrder(pp);

		EcVouchCode ec = new EcVouchCode();
		ec.setTrade_index(pp.getTrade_index());
		ec.setIs_userd(0);
		ec.setIs_del(0);

		List<EcVouchCode> ecList = super.getFacade().getEcVouchCodeService().getEcVouchCodeAndCodeList(ec);
		if (ecList == null || ecList.size() == 0) {
			sb = sb.append("-1");
			sb = sb.append("}");
			super.renderJson(response, sb.toString());
			return null;
		} else {
			String codes = "";
			ec = ecList.get(0);
			if (ec.getMap().get("code") != null) {
				codes = (String) ec.getMap().get("code");

			}
			if (codes.contains(code)) {
				sb = sb.append("1").append(",");
				sb = sb.append("\"code\":\"").append(code).append("\"");
				sb = sb.append(",\"mod_id\":\"").append(mod_id).append("\"");
				sb = sb.append("}");
				logger.info("sb {}", sb);
				super.renderJson(response, sb.toString());
				return null;
			} else {
				sb = sb.append("-1");
				sb = sb.append("}");
				super.renderJson(response, sb.toString());
				return null;
			}
		}

	}

}
