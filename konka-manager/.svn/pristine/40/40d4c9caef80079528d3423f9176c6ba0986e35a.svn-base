package com.ebiz.mmt.web.struts.customer;

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
import org.apache.commons.lang.ArrayUtils;
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
import com.ebiz.mmt.domain.BranchAssign;
import com.ebiz.mmt.domain.EcBaseExpress;
import com.ebiz.mmt.domain.EcBindingPdOrdeDetails;
import com.ebiz.mmt.domain.EcOrderExpressInfo;
import com.ebiz.mmt.domain.EcUser;
import com.ebiz.mmt.domain.JBaseGoods;
import com.ebiz.mmt.domain.JBasePartner;
import com.ebiz.mmt.domain.JBill;
import com.ebiz.mmt.domain.JBillDetails;
import com.ebiz.mmt.domain.KonkaBcompPd;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaJxcBaseAddr;
import com.ebiz.mmt.domain.KonkaMobileImpStore;
import com.ebiz.mmt.domain.KonkaOrderAuditProcess;
import com.ebiz.mmt.domain.KonkaOrderInfoDetails;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.PePdModel;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.domain.PshowOrdeAudit;
import com.ebiz.mmt.domain.PshowOrdeDetails;
import com.ebiz.mmt.domain.PshowOrder;
import com.ebiz.mmt.r3.KNVP;
import com.ebiz.mmt.r3.ReturnInfo;
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
public class PshowOrderSend2Action extends BaseAction {

	@Override
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
		String order_from = "2";// (String) dynaBean.get("order_from");
		String pay_way = (String) dynaBean.get("pay_way");
		String add_time_start = (String) dynaBean.get("add_time_start");
		String add_time_end = (String) dynaBean.get("add_time_end");
		String is_ps = (String) dynaBean.get("is_ps");
		String r3_code = (String) dynaBean.get("r3_code");
		String touch_type = (String) dynaBean.get("touch_type");

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.CUSTOMER_USER_INFO);
		if (null == user) {
			return null;
		}
		if (null == user.getCust_id()) {
			super.renderJavaScript(response, "window.onload=function(){alert('该用户没有分配客户！');history.back();}");
			return null;
		}

		PshowOrder entity = new PshowOrder();
		entity.setOrder_from(2);// 触网订单
		entity.getMap().put("cust_id", user.getCust_id());
		if (StringUtils.isNotBlank(is_ps)) {
			entity.setIs_ps(Integer.valueOf(is_ps));
		}
		if (StringUtils.isNotBlank(r3_code)) {
			entity.getMap().put("r3_code", r3_code);
		}
		if (StringUtils.isNotBlank(dept_id)) {
			// entity.setOpr_dept_id(Long.valueOf(dept_id));
			entity.getMap().put("fgs_dept_id", dept_id);
		}
		if (StringUtils.isNotBlank(trade_no_like)) {
			entity.getMap().put("trade_no_like", trade_no_like);
		}
		if (StringUtils.isNotBlank(add_time_start)) {
			entity.getMap().put("add_time_start", add_time_start + " 00:00:00");
		}
		if (StringUtils.isNotBlank(add_time_end)) {
			entity.getMap().put("add_time_end", add_time_end + " 23:59:59");
		}
		if (StringUtils.isNotBlank(pay_way)) {
			entity.setPay_way(Integer.valueOf(pay_way));
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
		if (StringUtils.isNotBlank(touch_type)) {
			if (touch_type.equals("2")) {
				entity.setPay_way(9);
				dynaBean.set("touch_type", "2");
			} else if (touch_type.equals("1")) {
				entity.getMap().put("cust_pay_way", true);
				dynaBean.set("touch_type", "1");
			}
		}
		if (StringUtils.isNotBlank(buyer_mp_like)) {
			entity.getMap().put("buyer_mp_like", buyer_mp_like);
		}
		if (StringUtils.isNotBlank(order_from)) {
			if ("9".equals(order_from)) {
				entity.setOrder_from(2);
				entity.setPay_way(Integer.valueOf(order_from));
			}

		}
		// entity.setState(30);// 只有下发状态下（总部处理或者分公司处理），才可以发货

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
		sysdept.getMap().put("order_by_dept_name", true);
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
		String order_from = "2";// (String) dynaBean.get("order_from");
		String pay_way = (String) dynaBean.get("pay_way");
		String add_time_start = (String) dynaBean.get("add_time_start");
		String add_time_end = (String) dynaBean.get("add_time_end");
		String is_ps = (String) dynaBean.get("is_ps");
		String r3_code = (String) dynaBean.get("r3_code");
		String touch_type = (String) dynaBean.get("touch_type");

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.CUSTOMER_USER_INFO);
		if (null == user) {
			return null;
		}
		if (null == user.getCust_id()) {
			super.renderJavaScript(response, "window.onload=function(){alert('该用户没有分配客户！');history.back();}");
			return null;
		}

		PshowOrder entity = new PshowOrder();
		entity.setOrder_from(2);// 触网订单
		entity.getMap().put("cust_id", user.getCust_id());
		if (StringUtils.isNotBlank(is_ps)) {
			entity.setIs_ps(Integer.valueOf(is_ps));
		}
		if (StringUtils.isNotBlank(r3_code)) {
			entity.getMap().put("r3_code", r3_code);
		}
		if (StringUtils.isNotBlank(dept_id)) {
			entity.setOpr_dept_id(Long.valueOf(dept_id));
		}
		if (StringUtils.isNotBlank(trade_no_like)) {
			entity.getMap().put("trade_no_like", trade_no_like);
		}
		if (StringUtils.isNotBlank(add_time_start)) {
			entity.getMap().put("add_time_start", add_time_start + " 00:00:00");
		}
		if (StringUtils.isNotBlank(add_time_end)) {
			entity.getMap().put("add_time_end", add_time_end + " 23:59:59");
		}
		if (StringUtils.isNotBlank(pay_way)) {
			entity.setPay_way(Integer.valueOf(pay_way));
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
		if (StringUtils.isNotBlank(touch_type)) {
			if (touch_type.equals("2")) {
				entity.setPay_way(9);
				dynaBean.set("touch_type", "2");
			} else if (touch_type.equals("1")) {
				entity.getMap().put("cust_pay_way", true);
				dynaBean.set("touch_type", "1");
			}
		}
		if (StringUtils.isNotBlank(buyer_name_like)) {
			entity.getMap().put("buyer_name_like", buyer_name_like);
		}
		if (StringUtils.isNotBlank(buyer_mp_like)) {
			entity.getMap().put("buyer_mp_like", buyer_mp_like);
		}
		if (StringUtils.isNotBlank(order_from)) {
			if ("9".equals(order_from)) {
				entity.setOrder_from(2);
				entity.setPay_way(Integer.valueOf(order_from));
			}

		}

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
		e.setCell(3, "触网类型");
		e.setCell(4, "下单分公司");
		e.setCell(5, "下单人姓名");
		e.setCell(6, "客户R3编码");
		e.setCell(7, "购买人姓名");
		e.setCell(8, "购买人地区");
		e.setCell(9, "支付单号");
		e.setCell(10, "购买人手机");
		e.setCell(11, "支付方式");
		e.setCell(12, "运单号");
		e.setCell(13, "订单商品");
		e.setCell(14, "数量");
		e.setCell(15, "应付金额");
		e.setCell(16, "下单时间");
		e.setCell(17, "二次配送");

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
			String touch_type1 = "";
			if (pds.getPay_way().intValue() == 9) {
				touch_type1 = "触网2";
			} else {
				touch_type1 = "触网1";
			}
			e.setCell(3, touch_type1);

			String xd_dept_name = "";
			String r3_code_1 = "";
			EcUser ec = new EcUser();
			ec.setId(pds.getOrder_user_id());
			ec = super.getFacade().getEcUserService().getEcUser(ec);

			if (null != ec) {
				KonkaDept kd = new KonkaDept();
				kd.setDept_type(3);
				kd.setDept_id(ec.getDept_id());
				kd = super.getFacade().getKonkaDeptService().getKonkaDept(kd);
				if (null != kd) {
					xd_dept_name = kd.getDept_name();
				}
			}

			if (null != ec && ec.getCust_id() != null) {
				KonkaR3Shop ks = new KonkaR3Shop();
				ks.setId(ec.getCust_id());
				ks = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(ks);
				if (ks != null && ks.getR3_code() != null) {
					r3_code_1 = ks.getR3_code();
				}
			}
			e.setCell(4, xd_dept_name);
			e.setCell(5, pds.getOrder_user_name());
			e.setCell(6, r3_code_1);
			e.setCell(7, pds.getBuyer_name());
			e.setCell(8, (String) pds.getMap().get("full_name"));
			e.setCell(9, pds.getTrade_no());
			e.setCell(10, pds.getBuyer_mp());
			if ("0".equals(pds.getPay_way().toString())) {
				e.setCell(11, "货到付款");
			} else if ("1".equals(pds.getPay_way().toString())) {
				e.setCell(11, "银行付款");
			} else if ("2".equals(pds.getPay_way().toString())) {
				e.setCell(11, "支付宝");
			} else if ("3".equals(pds.getPay_way().toString())) {
				e.setCell(11, "银联");
			} else if ("4".equals(pds.getPay_way().toString())) {
				e.setCell(11, "财付通");
			} else if ("5".equals(pds.getPay_way().toString())) {
				e.setCell(11, "民生银行");
			} else if ("9".equals(pds.getPay_way().toString())) {
				e.setCell(11, "线下处理");
			}
			e.setCell(12, (String) pds.getMap().get("log_sn"));
			BigDecimal pay_price = pds.getPay_price();
			if (pay_price == null) {
				pay_price = new BigDecimal("0.0");
			}
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
			e.setCell(13, pd_sn);
			e.setCell(14, num);
			e.setCell(15, pds.getPay_price().toString());

			String ph = "";
			if (pds.getIs_ps() == 0) {
				ph = "否";
			} else if (pds.getIs_ps() == 1) {
				ph = "是";
			}
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			e.setCell(16, sf.format(pds.getAdd_date()));
			e.setCell(17, ph);

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

		PshowOrder entity = new PshowOrder();
		if (StringUtils.isNotBlank(id)) {
			entity.setId(Long.valueOf(id));
		}

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.CUSTOMER_USER_INFO);
		if (null == user) {
			return null;
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

		request.setAttribute("t_num", t_num);
		request.setAttribute("t_price", t_price);
		request.setAttribute("pshowOrdeDetails", pshowordedetails);

		return new ActionForward("/../customer/PshowOrderSend2/send.jsp");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String id = (String) dynaBean.get("id");
		String buyer_name = (String) dynaBean.get("buyer_name");
		String buyer_tel = (String) dynaBean.get("buyer_tel");
		String buyer_mp = (String) dynaBean.get("buyer_mp");
		String remark1 = (String) dynaBean.get("remark1");
		String express_id = (String) dynaBean.get("express_id");
		String code = (String) dynaBean.get("code");
		String order_from = (String) dynaBean.get("order_from");
		String order_to = (String) dynaBean.get("order_to");
		String logistic_sn = (String) dynaBean.get("logistic_sn");

		if (isCancelled(request)) {
			return list(mapping, form, request, response);
		}
		if (!isTokenValid(request)) {
			saveError(request, "errors.token");
			return list(mapping, form, request, response);
		}
		resetToken(request);

		if (!GenericValidator.isLong(id)) {
			super.saveError(request, "errors.long", "id");
			return this.list(mapping, form, request, response);
		}

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.CUSTOMER_USER_INFO);
		if (null == user) {
			return null;
		}
		// PshowOrder p1 = new PshowOrder();
		// p1.setId(Long.valueOf(id));
		// p1 = super.getFacade().getPshowOrderService().getPshowOrder(p1);
		// if (p1.getState() != 30) {
		// super.renderJavaScript(response,
		// "window.onload=function(){alert('订单状态已经发生改变！');history.back();}");
		// return null;
		// }

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
		if (StringUtils.isNotBlank(express_id)) {
			entity.setExpress_id(Long.valueOf(express_id));
		}
		if (StringUtils.isNotBlank(code)) {
			entity.setCode(code);
		}

		entity.setState(40);
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

		// 审核记录表插入记录
		PshowOrdeAudit poa = new PshowOrdeAudit();
		poa.setOper_date(new Date());
		poa.setOrder_id(Long.valueOf(id));
		poa.setState(40);
		poa.setRemark(remark1);
		poa.setOpr_user_id(user.getId());
		poa.setOpr_user_real_name(user.getUser_name());
		java.math.BigDecimal bd1 = new java.math.BigDecimal(t_price);
		poa.setTotal_price(bd1);

		// 根据不同的分公司，取发货人的联系地址和联系方式
		// if (null != user.getDept_id()) {
		// KonkaDept konkaDept = new KonkaDept();
		// konkaDept.setDept_id(user.getDept_id());
		// konkaDept =
		// super.getFacade().getKonkaDeptService().getKonkaDept(konkaDept);
		//
		// // 分公司取得
		// if (null != konkaDept.getDept_type() && konkaDept.getDept_type() > 2)
		// {
		// KonkaDept fgs_dept = super.getSuperiorForDeptType(user.getDept_id(),
		// 3);
		// if (null != fgs_dept) {
		// if (fgs_dept.getDept_sn().equalsIgnoreCase("KF22")) {// 合肥分公司
		// entity.getMap().put("fgs", "1");
		// } else if (fgs_dept.getDept_sn().equalsIgnoreCase("KF46")) {// 东莞分公司
		// entity.getMap().put("fgs", "2");
		// }
		// }
		// }
		//
		// }

		PshowOrder t = new PshowOrder();
		t.setId(Long.valueOf(id));
		t = super.getFacade().getPshowOrderService().getPshowOrder(t);

		// 插入销量表
		JBill jbill = new JBill();
		jbill.setBill_type(20);
		Date today = new Date();
		String bill_sn = "20" + "-" + DateFormatUtils.format(today, "yyyyMMdd") + "-";
		Long seq_jbill_id = super.getFacade().getJBillService().getSeqJBillId();
		bill_sn = bill_sn + StringUtils.leftPad(seq_jbill_id.toString(), 8, "0");
		jbill.setBill_sn(bill_sn);
		jbill.setRec_money(t.getPay_price());
		jbill.setDiscount(new BigDecimal("100"));
		jbill.setMoney(t.getPay_price());
		jbill.setOpr_date(today);
		if (null != user.getCust_id()) {
			jbill.setC_id(user.getCust_id());
		}
		jbill.setPlan_hand_time(today);

		// 插入销量明细
		List<JBillDetails> jBillDetailsList = new ArrayList<JBillDetails>();
		if (null != pshowordedetails && pshowordedetails.size() > 0) {
			for (PshowOrdeDetails dd : pshowordedetails) {
				JBillDetails jBillDetails = new JBillDetails();
				jBillDetails.setStore_id(182L);// 默认取总库

				KonkaBcompPd kb = new KonkaBcompPd();
				kb.setId(dd.getPd_id());
				List<KonkaBcompPd> kbList = super.getFacade().getKonkaBcompPdService().getKonkaBcompPdList(kb);
				if (null != kbList && kbList.size() > 0) {
					kb = kbList.get(0);
				}
				PePdModel ppm = new PePdModel();
				ppm.setPd_id(Long.valueOf(kb.getPd_spec()));
				ppm = super.getFacade().getPePdModelService().getPePdModel(ppm);

				JBaseGoods jb = new JBaseGoods();
				jb.setC_id(user.getCust_id());
				jb.setGoods_name(ppm.getMd_name());
				jb.setGoods_stutes(0);
				jb = super.getFacade().getJBaseGoodsService().getJBaseGoods(jb);

				if (null == jb) {
					super.renderJavaScript(response, "window.onload=function(){alert('对不起！请去维护型号" + ppm.getMd_name()
					        + "的库存');history.back();}");
					return null;
				}

				jBillDetails.setGoods_id(jb.getGoods_id());
				jBillDetails.setNum(dd.getNum());
				jBillDetails.setPrice(dd.getPrice());
				jBillDetails.setMoney(dd.getTotal_price());

				jBillDetailsList.add(jBillDetails);

			}
		}

		jbill.setjBillDetailsList(jBillDetailsList);

		// 新增往来单位
		JBasePartner jBasePartner = new JBasePartner();
		if (null != t.getBuyer_mp()) {
			jBasePartner.setLink_tel(t.getBuyer_mp());
		}
		if (null != t.getBuyer_name()) {
			jBasePartner.setPartner_name(t.getBuyer_name()); // 往来单位名称
		}
		if (null != user.getCust_id()) {
			jBasePartner.setC_id(user.getCust_id()); // 客户ID
		}
		if (null != t.getBuyer_name()) {
			jBasePartner.setLink_name(t.getBuyer_name());
		}
		if (null != t.getBuyer_mp()) {
			jBasePartner.setLink_mobile(t.getBuyer_mp());
		}
		if (null != t.getBuyer_mp()) {
			jBasePartner.setConsignee_tel(t.getBuyer_mp());
		}
		if (null != t.getBuyer_name()) {
			jBasePartner.setConsignee_name(t.getBuyer_name());
		}
		if (null != t.getBuyer_p_index()) {
			jBasePartner.setConsignee_p_index(t.getBuyer_p_index().toString());
		}
		if (null != t.getBuyer_addr()) {
			jBasePartner.setConsignee_street(t.getBuyer_addr());
		}
		jBasePartner.setPartner_type(1); // 伙伴类型: 0-供应商 1-客户, 10,供应商和客户
		jBasePartner.setPartner_obj(0); // 伙伴对象:0-个人 1-组织/单位
		String partner_sn = "";
		if (null != user.getCust_id()) {
			long time = new Date().getTime();
			String ss = String.valueOf(time);
			partner_sn = "WLDW-KH-GR-" + user.getCust_id() + "-" + ss.substring(ss.length() - 6, ss.length());
		}
		jBasePartner.setPartner_sn(partner_sn);
		jbill.setjBasePartner(jBasePartner);

		super.getFacade().getPshowOrderService().createPshowOrdeAuditAndModifyPshowOrder3(entity, poa, jbill);

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

		return new ActionForward("/../customer/PshowOrderSend2/print.jsp");
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

		return new ActionForward("/../customer/PshowOrderSend2/print2.jsp");
	}

	public ActionForward showPrint3(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		dynaBean.set("id", id);

		return new ActionForward("/../customer/PshowOrderSend2/print3.jsp");
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

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		logger.info("id---------->" + id);

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

		// EcOrderExpressInfo ee = new EcOrderExpressInfo();
		// ee.setTrade_index(entity.getTrade_index());
		// ee =
		// super.getFacade().getEcOrderExpressInfoService().getEcOrderExpressInfo(ee);

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
		t_price = entity.getTotal_price().doubleValue() - entity.getDedu_price().doubleValue();

		PshowOrdeAudit psa = new PshowOrdeAudit();
		psa.setOrder_id(Long.valueOf(id));
		psa.getMap().put("orderByDate", true);
		List<PshowOrdeAudit> PshowOrdeAudits = super.getFacade().getPshowOrdeAuditService().getPshowOrdeAuditList(psa);

		request.setAttribute("t_num", t_num);
		request.setAttribute("t_price", t_price);
		request.setAttribute("pshowOrdeDetails", pshowordedetails);
		request.setAttribute("PshowOrdeAudits", PshowOrdeAudits);

		return mapping.findForward("view");
	}

	/**
	 *@author WANG,KUNLIN
	 *@date 2014-4-10
	 */
	public ActionForward SwitchR3Input(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id");
		PshowOrder pshoworder = new PshowOrder();
		if (StringUtils.isNotBlank(id)) {
			pshoworder.setId(Long.valueOf(id));
		}

		String CUSTID = (String) dynaBean.get("CUSTID"); // 业务提交请求的客户ID

		HttpSession session = request.getSession();

		PeProdUser user = null;
		if (GenericValidator.isLong(CUSTID)) {
			user = new PeProdUser();
			user.getRow().setCount(1);
			user.setCust_id(Long.valueOf(CUSTID));
			List<PeProdUser> userList = super.getFacade().getPeProdUserService().getPeProdUserList(user);
			if (null == userList || 0 == userList.size()) {
			}
			user = userList.get(0);
			session.setAttribute(Constants.CUSTOMER_USER_INFO, user);
		} else {
			user = (PeProdUser) session.getAttribute(Constants.CUSTOMER_USER_INFO);
		}
		if (null == user) {
			return null;
		}

		String cust_id = (String) dynaBean.get("cust_id");
		// 取收货地址
		KonkaJxcBaseAddr konkaJxcBaseAddr = new KonkaJxcBaseAddr();
		konkaJxcBaseAddr.setIs_del(0);

		KonkaR3Shop r3shop = new KonkaR3Shop();
		r3shop.setId(user.getCust_id());
		r3shop = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(r3shop);
		if (null != r3shop) {
			konkaJxcBaseAddr.setR3_id(r3shop.getId());
			konkaJxcBaseAddr.setR3_code(r3shop.getR3_code());
		}

		List<KonkaJxcBaseAddr> konkaJxcBaseAddrList = super.getFacade().getKonkaJxcBaseAddrService()
		        .getKonkaJxcBaseAddrList(konkaJxcBaseAddr);
		request.setAttribute("konkaJxcBaseAddrList", konkaJxcBaseAddrList);

		if (null != konkaJxcBaseAddrList && konkaJxcBaseAddrList.size() > 0) {
			for (KonkaJxcBaseAddr kba : konkaJxcBaseAddrList) {
				if (kba.getIs_default() == 0) {
					dynaBean.set("send_type", 2);
					dynaBean.set("user_name", kba.getUser_name());
					dynaBean.set("user_tel", kba.getUser_tel());
					dynaBean.set("user_phone", kba.getUser_phone());
					if (StringUtils.isNotBlank(kba.getUser_p_index().toString())) {
						String p_index = kba.getUser_p_index().toString();
						if (!p_index.endsWith("00")) {
							if (p_index.length() == 6) {
								dynaBean.set("province", StringUtils.substring(p_index, 0, 2) + "0000");
								dynaBean.set("city", StringUtils.substring(p_index, 0, 4) + "00");
								dynaBean.set("country", p_index);
							} else if (p_index.length() == 8) {
								dynaBean.set("province", StringUtils.substring(p_index, 0, 2) + "0000");
								dynaBean.set("city", StringUtils.substring(p_index, 0, 4) + "00");
								dynaBean.set("country", StringUtils.substring(p_index, 0, 6));
								dynaBean.set("town", p_index);
							}
						} else if (p_index.endsWith("0000")) {
							dynaBean.set("province", p_index);
						} else if (p_index.endsWith("00")) {
							dynaBean.set("province", StringUtils.substring(p_index, 0, 2) + "0000");
							dynaBean.set("city", p_index);
						}
					}
					dynaBean.set("user_addr", kba.getUser_addr());
					dynaBean.set("user_remark", kba.getUser_remark());
				}
			}

		}
		Long current_cust_id = null;

		if (null != user.getCust_id()) {
			// 当前登录人是客户
			current_cust_id = user.getCust_id();
		} else {
			// 当前登录人不是客户
			PeRoleUser _peRoleUser = new PeRoleUser();
			_peRoleUser.setUser_id(user.getId());
			List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);

			boolean role_id_eq_60 = false;
			for (PeRoleUser peRoleUser : peRoleUserList) {
				if (peRoleUser.getRole_id() == 60L) {
					role_id_eq_60 = true;
				}
			}
			if (user.getCust_id() == null && role_id_eq_60) {
				if (!GenericValidator.isLong(cust_id)) {
					return null;
				}
				current_cust_id = Long.valueOf(cust_id);
			}
		}

		if (null == current_cust_id) {
			return null;
		}

		request.setAttribute("cust_id", current_cust_id);

		KonkaR3Shop konkaR3Shop = new KonkaR3Shop();
		konkaR3Shop.setId(current_cust_id);
		konkaR3Shop = getFacade().getKonkaR3ShopService().getKonkaR3Shop(konkaR3Shop);
		if (null == konkaR3Shop) {
			super.renderJavaScript(response, "alert('" + super.getMessage(request, "konka.r3.customer.none")
			        + "');history.back();");
			return null;
		}

		// 获取流程列表
		KonkaDept kd = super.getKonkaDeptByCustomerId(current_cust_id);
		KonkaDept dept = super.getSuperiorForDeptType(kd.getDept_id(), 3);
		List<KonkaOrderAuditProcess> processList = new ArrayList<KonkaOrderAuditProcess>();
		// Boolean flag = null;
		if (dept != null) {

			KonkaR3Shop konkaR3Shop_ = new KonkaR3Shop();
			konkaR3Shop_.setId(current_cust_id);
			konkaR3Shop_.setIs_del(0l);
			konkaR3Shop_ = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(konkaR3Shop_);
			if (null != konkaR3Shop_) {
				if (StringUtils.isNotBlank(konkaR3Shop_.getCustomer_type())) {// 判断是否是有客户类型
					KonkaOrderAuditProcess process = new KonkaOrderAuditProcess();
					process.getMap().put("par_add_dept_id", dept.getDept_id());
					process.setCustomer_type(konkaR3Shop_.getCustomer_type());
					process.setIs_del(0);
					process.setIs_stop(0);
					processList = super.getFacade().getKonkaOrderAuditProcessService().getKonkaOrderAuditProcessList(
					        process);
					if (null == processList || processList.size() == 0) {
						// 没有分公司自定义的流程取统一流程，即分公司自定义流程
						KonkaOrderAuditProcess ap_public = new KonkaOrderAuditProcess();
						ap_public.setCustomer_type(konkaR3Shop_.getCustomer_type());
						ap_public.setAdd_dept_id(0L);
						ap_public.setIs_del(0);
						ap_public.setIs_stop(0);
						List<KonkaOrderAuditProcess> ap_publicauditProcesseList = getFacade()
						        .getKonkaOrderAuditProcessService().getKonkaOrderAuditProcessList(ap_public);
						processList.addAll(ap_publicauditProcesseList);
					}
					if (null == processList || processList.size() == 0) {
						// 没有分公司自定义的流程取统一流程，即分公司流程
						KonkaOrderAuditProcess ap_public = new KonkaOrderAuditProcess();
						ap_public.setCustomer_type(konkaR3Shop_.getCustomer_type());
						ap_public.setAdd_dept_id(0L);
						ap_public.setIs_del(0);
						ap_public.setIs_stop(0);
						List<KonkaOrderAuditProcess> ap_publicauditProcesseList = getFacade()
						        .getKonkaOrderAuditProcessService().getKonkaOrderAuditProcessList(ap_public);
						processList.addAll(ap_publicauditProcesseList);
					}
					if (null == processList || processList.size() == 0) {
						// 没有分公司流程取统一流程，即总公司流程
						KonkaOrderAuditProcess ap_public = new KonkaOrderAuditProcess();
						ap_public.getMap().put("par_add_dept_id", dept.getDept_id());
						ap_public.setIs_del(0);
						ap_public.setIs_stop(0);
						ap_public.getMap().put("customer_type_is_null", true);
						List<KonkaOrderAuditProcess> ap_publicauditProcesseList = getFacade()
						        .getKonkaOrderAuditProcessService().getKonkaOrderAuditProcessList(ap_public);
						processList.addAll(ap_publicauditProcesseList);
					}

					if (null == processList || processList.size() == 0) {
						// 没有分公司自定义的流程取统一流程，即总公司自定义流程
						KonkaOrderAuditProcess ap_public = new KonkaOrderAuditProcess();
						ap_public.setAdd_dept_id(0L);
						ap_public.setIs_del(0);
						ap_public.setIs_stop(0);
						ap_public.getMap().put("customer_type_is_null", true);
						List<KonkaOrderAuditProcess> ap_publicauditProcesseList = getFacade()
						        .getKonkaOrderAuditProcessService().getKonkaOrderAuditProcessList(ap_public);
						processList.addAll(ap_publicauditProcesseList);
					}

					// flag = true;
					request.setAttribute("customer_type", konkaR3Shop_.getCustomer_type());
				} else {
					KonkaOrderAuditProcess process = new KonkaOrderAuditProcess();
					process.getMap().put("par_add_dept_id", dept.getDept_id());
					process.getMap().put("customer_type_is_null", true);
					process.setIs_del(0);
					process.setIs_stop(0);
					processList = super.getFacade().getKonkaOrderAuditProcessService().getKonkaOrderAuditProcessList(
					        process);
					if (null == processList || processList.size() == 0) {
						// 没有分公司统一流程，即分公司优先级高
						KonkaOrderAuditProcess ap_public = new KonkaOrderAuditProcess();
						ap_public.getMap().put("customer_type_is_null", true);
						ap_public.setAdd_dept_id(0L);
						ap_public.setIs_del(0);
						ap_public.setIs_stop(0);
						List<KonkaOrderAuditProcess> ap_publicauditProcesseList = getFacade()
						        .getKonkaOrderAuditProcessService().getKonkaOrderAuditProcessList(ap_public);
						processList.addAll(ap_publicauditProcesseList);
					}
					// flag = false;
				}
			}
			request.setAttribute("processList", processList);
			// request.setAttribute("flag", flag);
		}

		// 取订单流程
		// KonkaDept dept = super.getKonkaDeptByCustomerId(current_cust_id);
		// KonkaOrderAuditProcess ap = new KonkaOrderAuditProcess();
		// ap.getMap().put("par_add_dept_id", dept.getDept_id());
		// ap.setIs_del(0);
		// List<KonkaOrderAuditProcess> auditProcesseList =
		// getFacade().getKonkaOrderAuditProcessService()
		// .getKonkaOrderAuditProcessList(ap);
		//
		// if (null == auditProcesseList || auditProcesseList.size() == 0) {
		// // 没有分公司自定义的流程取统一流程，即分公司优先级高
		// KonkaOrderAuditProcess ap_public = new KonkaOrderAuditProcess();
		// ap_public.setAdd_dept_id(0L);
		// ap_public.setIs_del(0);
		// List<KonkaOrderAuditProcess> ap_publicauditProcesseList =
		// getFacade().getKonkaOrderAuditProcessService()
		// .getKonkaOrderAuditProcessList(ap_public);
		// auditProcesseList.addAll(ap_publicauditProcesseList);
		// }
		//
		// request.setAttribute("processList", auditProcesseList);

		// 查询客户用户信息
		// PeProdUser u = new PeProdUser();
		// u.setCust_id(current_cust_id);
		// u = super.getFacade().getPeProdUserService().getPeProdUser(u);
		//
		// if (null != u) {
		// request.setAttribute("u", u);
		// if (null != u.getP_index()) {
		// super.setprovinceAndcityAndcountryToFrom(dynaBean, u.getP_index());
		// }
		// }

		dynaBean.set("brandId", Constants.KONKA_BRAND_ID);
		dynaBean.set("trade_index", new BaseClientJxcAction().generateTradeIndex());// 交易流水号
		dynaBean.set("today", DateFormatUtils.format(new Date(), "yyyy-MM-dd"));// 订单日期
		dynaBean.set("cg_order_date", new Date());// 订单日期
		dynaBean.set("user_shop_name", konkaR3Shop.getCustomer_name());
		dynaBean.set("r3_code", konkaR3Shop.getR3_code());

		dynaBean.set("userName", user.getReal_name());
		// dynaBean.set("userAddr", user.getLink_addr());

		// dynaBean.set("userZip", entp.getPostcode());
		// dynaBean.set("userAddr", entp.getAddr());
		// dynaBean.set("userTel", entp.getTel());
		// dynaBean.set("userPIndex", user.getStdEntpMain().getP_index());

		String fgsSN = konkaR3Shop.getBranch_area_name_2();
		String fgsSN2 = konkaR3Shop.getR3_sales_org_code();
		request.setAttribute("sales_org", StringUtils.isBlank(fgsSN2) ? fgsSN : fgsSN2);

		/** 取网点业务员 */
		BranchAssign bagn = new BranchAssign();
		bagn.setKonka_r3_id(current_cust_id);
		bagn = getFacade().getBranchAssignService().getBranchAssign(bagn);
		if (null != bagn && null != bagn.getUser_id()) {
			PeProdUser ywy = new PeProdUser();
			ywy.setId(bagn.getUser_id());
			ywy = getFacade().getPeProdUserService().getPeProdUserResult(ywy);
			request.setAttribute("ywy_user_name", ywy.getReal_name());
		}

		if (super.isCallR3Interface(request)) {
			String sales_org = StringUtils.isBlank(fgsSN2) ? fgsSN : fgsSN2;
			// List<KNVP> knvpList =
			// super.getFacade().getR3WebInterfaceService().getKnvpsList(sales_org,
			// Constants.default_vtweg, Constants.default_spart,
			// konkaR3Shop.getR3_code());
			//
			List<KNVP> knvpList = new ArrayList<KNVP>();
			ReturnInfo<KNVP> info = new ReturnInfo<KNVP>();
			info = super
					.getFacade()
					.getR3WebInterfaceService()
					.getKnvpsList(sales_org,
			        Constants.default_vtweg, Constants.default_spart, konkaR3Shop.getR3_code());
			if (info.getSuccess() == true) {
				knvpList = info.getDataResult();
			}

			// AG:售达方
			// RE:出票方
			// RG:付款方
			// WE:送达方

			List<KNVP> agList = new ArrayList<KNVP>();
			List<KNVP> reList = new ArrayList<KNVP>();
			List<KNVP> rgList = new ArrayList<KNVP>();
			List<KNVP> weList = new ArrayList<KNVP>();

			if (null != knvpList) {
				for (KNVP cur : knvpList) {
					if ("AG".equalsIgnoreCase(cur.getPARVW())) {
						agList.add(cur);
					} else if ("RE".equalsIgnoreCase(cur.getPARVW())) {
						reList.add(cur);
					} else if ("RG".equalsIgnoreCase(cur.getPARVW())) {
						rgList.add(cur);
					} else if ("WE".equalsIgnoreCase(cur.getPARVW())) {
						weList.add(cur);
					}
				}
			}

			request.setAttribute("agList", agList);
			request.setAttribute("reList", reList);
			request.setAttribute("rgList", rgList);
			request.setAttribute("weList", weList);

			if (agList.size() == 0) {
				request.setAttribute("ag", konkaR3Shop.getR3_code());
			}
			if (reList.size() == 0) {
				request.setAttribute("re", konkaR3Shop.getR3_code());
			}
			if (rgList.size() == 0) {
				request.setAttribute("rg", konkaR3Shop.getR3_code());
			}
			if (weList.size() == 0) {
				KonkaMobileImpStore s = new KonkaMobileImpStore();
				s.setR3_shdf_sn(konkaR3Shop.getR3_code());
				List<KonkaMobileImpStore> sList = super.getFacade().getKonkaMobileImpStoreService()
				        .getKonkaMobileImpStoreListForDistinctSdf(s);

				List<KNVP> __weList = new ArrayList<KNVP>();
				for (KonkaMobileImpStore cur : sList) {
					KNVP k = new KNVP();
					k.setKUNN2(cur.getR3_sdf_sn());
					__weList.add(k);
				}
				request.setAttribute("weList", __weList);
				if (__weList.size() == 0) {
					request.setAttribute("we", konkaR3Shop.getR3_code());
				}
			}
		} else {
			request.setAttribute("ag", konkaR3Shop.getR3_code());
			request.setAttribute("re", konkaR3Shop.getR3_code());
			request.setAttribute("rg", konkaR3Shop.getR3_code());

			KonkaMobileImpStore s = new KonkaMobileImpStore();
			s.setR3_shdf_sn(konkaR3Shop.getR3_code());
			List<KonkaMobileImpStore> sList = super.getFacade().getKonkaMobileImpStoreService()
			        .getKonkaMobileImpStoreListForDistinctSdf(s);

			List<KNVP> weList = new ArrayList<KNVP>();
			for (KonkaMobileImpStore cur : sList) {
				KNVP k = new KNVP();
				k.setKUNN2(cur.getR3_sdf_sn());
				weList.add(k);
			}
			request.setAttribute("weList", weList);
		}
		// 订单详细表

		PshowOrdeDetails psd = new PshowOrdeDetails();
		psd.setOrder_id(Long.valueOf(id));
		List<PshowOrdeDetails> pshowordedetails = super.getFacade().getPshowOrdeDetailsService()
		        .getPshowOrdeForPDSNDetailsList(psd);
		setNaviStringToRequestScope(form, request);
		List<KonkaOrderInfoDetails> konkaOrderInfoDetailsList = new ArrayList<KonkaOrderInfoDetails>();

		for (PshowOrdeDetails ps : pshowordedetails) {
			KonkaOrderInfoDetails konkaorderinfodeatils = new KonkaOrderInfoDetails();
			KonkaBcompPd konkabcomppd = new KonkaBcompPd();
			konkabcomppd.setId(ps.getPd_id());
			konkabcomppd = super.getFacade().getKonkaBcompPdService().getKonkaBcompPd(konkabcomppd);
			konkaorderinfodeatils.setPd_id(Long.valueOf(konkabcomppd.getPd_spec()));
			konkaorderinfodeatils.setPd_name(konkabcomppd.getPd_sn());
			// 取型号、大类信息
			PePdModel pePdModel = new PePdModel();
			pePdModel.setBrand_id(Constants.KONKA_BRAND_ID);
			pePdModel.setIs_del(0);
			pePdModel.setPd_id(ps.getPd_id());
			pePdModel = getFacade().getPePdModelService().getPePdModel(pePdModel);
			if (null != pePdModel) {

				konkaorderinfodeatils.setBrand_id(pePdModel.getBrand_id());
				konkaorderinfodeatils.setBrand_name(Constants.KONKA_BRAND_NAME);
				konkaorderinfodeatils.setPd_type_id(pePdModel.getCls_id());
				konkaorderinfodeatils.setGood_price(BigDecimal.valueOf(super.getPdmodelPrice(pePdModel.getMd_name(),
				        konkaR3Shop.getR3_code())));
				konkaorderinfodeatils.setGood_sum_price(BigDecimal.valueOf(super.getPdmodelPrice(
				        pePdModel.getMd_name(), konkaR3Shop.getR3_code())));

				if (null != pePdModel.getCls_id() && "".equals(pePdModel.getCls_id())
				        && null != super.getBasePdClazz(pePdModel.getCls_id()))
					konkaorderinfodeatils.setPd_type_name(super.getBasePdClazz(pePdModel.getCls_id()).getCls_name());
			}
			konkaorderinfodeatils.setGood_count(Integer.decode(ps.getNum().toString()));
			konkaorderinfodeatils.setGood_unit("台");

			konkaOrderInfoDetailsList.add(konkaorderinfodeatils);

		}
		request.setAttribute("konkaOrderInfoDetailsList", konkaOrderInfoDetailsList);

		request.setAttribute("call_r3_interface", super.isCallR3Interface(request));
		request.setAttribute("pshow_id", id);
		// //System.out.println(id+"这个是id");

		// //System.out.println("执行结束");
		// 生成销售记录结束

		return new ActionForward("/../customer/JxcKonkaOrderRegister/input1.jsp");

	}

	public ActionForward switchR3All(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		String[] pks = request.getParameterValues("pks");

		String CUSTID = (String) dynaBean.get("CUSTID"); // 业务提交请求的客户ID

		HttpSession session = request.getSession();

		PeProdUser user = null;
		if (GenericValidator.isLong(CUSTID)) {
			user = new PeProdUser();
			user.getRow().setCount(1);
			user.setCust_id(Long.valueOf(CUSTID));
			List<PeProdUser> userList = super.getFacade().getPeProdUserService().getPeProdUserList(user);
			if (null == userList || 0 == userList.size()) {
			}
			user = userList.get(0);
			session.setAttribute(Constants.CUSTOMER_USER_INFO, user);
		} else {
			user = (PeProdUser) session.getAttribute(Constants.CUSTOMER_USER_INFO);
		}
		if (null == user) {
			return null;
		}
		if (ArrayUtils.isEmpty(pks)) {
			return null;
		}
		List<KonkaOrderInfoDetails> konkaOrderInfoDetailsList = new ArrayList<KonkaOrderInfoDetails>();

		String cust_id = (String) dynaBean.get("cust_id");
		// 取收货地址
		KonkaJxcBaseAddr konkaJxcBaseAddr = new KonkaJxcBaseAddr();
		konkaJxcBaseAddr.setIs_del(0);
		KonkaR3Shop r3shop = new KonkaR3Shop();
		r3shop.setId(user.getCust_id());
		r3shop = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(r3shop);
		if (null != r3shop) {
			konkaJxcBaseAddr.setR3_id(r3shop.getId());
			konkaJxcBaseAddr.setR3_code(r3shop.getR3_code());
		}
		List<KonkaJxcBaseAddr> konkaJxcBaseAddrList = super.getFacade().getKonkaJxcBaseAddrService()
		        .getKonkaJxcBaseAddrList(konkaJxcBaseAddr);
		request.setAttribute("konkaJxcBaseAddrList", konkaJxcBaseAddrList);

		if (null != konkaJxcBaseAddrList && konkaJxcBaseAddrList.size() > 0) {
			for (KonkaJxcBaseAddr kba : konkaJxcBaseAddrList) {
				if (kba.getIs_default() == 0) {
					dynaBean.set("send_type", 2);
					dynaBean.set("user_name", kba.getUser_name());
					dynaBean.set("user_tel", kba.getUser_tel());
					dynaBean.set("user_phone", kba.getUser_phone());
					if (StringUtils.isNotBlank(kba.getUser_p_index().toString())) {
						String p_index = kba.getUser_p_index().toString();
						if (!p_index.endsWith("00")) {
							if (p_index.length() == 6) {
								dynaBean.set("province", StringUtils.substring(p_index, 0, 2) + "0000");
								dynaBean.set("city", StringUtils.substring(p_index, 0, 4) + "00");
								dynaBean.set("country", p_index);
							} else if (p_index.length() == 8) {
								dynaBean.set("province", StringUtils.substring(p_index, 0, 2) + "0000");
								dynaBean.set("city", StringUtils.substring(p_index, 0, 4) + "00");
								dynaBean.set("country", StringUtils.substring(p_index, 0, 6));
								dynaBean.set("town", p_index);
							}
						} else if (p_index.endsWith("0000")) {
							dynaBean.set("province", p_index);
						} else if (p_index.endsWith("00")) {
							dynaBean.set("province", StringUtils.substring(p_index, 0, 2) + "0000");
							dynaBean.set("city", p_index);
						}
					}
					dynaBean.set("user_addr", kba.getUser_addr());
					dynaBean.set("user_remark", kba.getUser_remark());
				}
			}

		}

		Long current_cust_id = null;

		if (null != user.getCust_id()) {
			// 当前登录人是客户
			current_cust_id = user.getCust_id();
		} else {
			// 当前登录人不是客户
			PeRoleUser _peRoleUser = new PeRoleUser();
			_peRoleUser.setUser_id(user.getId());
			List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);

			boolean role_id_eq_60 = false;
			for (PeRoleUser peRoleUser : peRoleUserList) {
				if (peRoleUser.getRole_id() == 60L) {
					role_id_eq_60 = true;
				}
			}
			if (user.getCust_id() == null && role_id_eq_60) {
				if (!GenericValidator.isLong(cust_id)) {
					return null;
				}
				current_cust_id = Long.valueOf(cust_id);
			}
		}

		if (null == current_cust_id) {
			return null;
		}

		request.setAttribute("cust_id", current_cust_id);

		KonkaR3Shop konkaR3Shop = new KonkaR3Shop();
		konkaR3Shop.setId(current_cust_id);
		konkaR3Shop = getFacade().getKonkaR3ShopService().getKonkaR3Shop(konkaR3Shop);
		if (null == konkaR3Shop) {
			super.renderJavaScript(response, "alert('" + super.getMessage(request, "konka.r3.customer.none")
			        + "');history.back();");
			return null;
		}

		// 获取流程列表
		KonkaDept kd = super.getKonkaDeptByCustomerId(current_cust_id);
		KonkaDept dept = super.getSuperiorForDeptType(kd.getDept_id(), 3);
		List<KonkaOrderAuditProcess> processList = new ArrayList<KonkaOrderAuditProcess>();
		// Boolean flag = null;
		if (dept != null) {

			KonkaR3Shop konkaR3Shop_ = new KonkaR3Shop();
			konkaR3Shop_.setId(current_cust_id);
			konkaR3Shop_.setIs_del(0l);
			konkaR3Shop_ = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(konkaR3Shop_);
			if (null != konkaR3Shop_) {
				if (StringUtils.isNotBlank(konkaR3Shop_.getCustomer_type())) {// 判断是否是有客户类型
					KonkaOrderAuditProcess process = new KonkaOrderAuditProcess();
					process.getMap().put("par_add_dept_id", dept.getDept_id());
					process.setCustomer_type(konkaR3Shop_.getCustomer_type());
					process.setIs_del(0);
					process.setIs_stop(0);
					processList = super.getFacade().getKonkaOrderAuditProcessService().getKonkaOrderAuditProcessList(
					        process);
					if (null == processList || processList.size() == 0) {
						// 没有分公司自定义的流程取统一流程，即分公司自定义流程
						KonkaOrderAuditProcess ap_public = new KonkaOrderAuditProcess();
						ap_public.setCustomer_type(konkaR3Shop_.getCustomer_type());
						ap_public.setAdd_dept_id(0L);
						ap_public.setIs_del(0);
						ap_public.setIs_stop(0);
						List<KonkaOrderAuditProcess> ap_publicauditProcesseList = getFacade()
						        .getKonkaOrderAuditProcessService().getKonkaOrderAuditProcessList(ap_public);
						processList.addAll(ap_publicauditProcesseList);
					}
					if (null == processList || processList.size() == 0) {
						// 没有分公司自定义的流程取统一流程，即分公司流程
						KonkaOrderAuditProcess ap_public = new KonkaOrderAuditProcess();
						ap_public.setCustomer_type(konkaR3Shop_.getCustomer_type());
						ap_public.setAdd_dept_id(0L);
						ap_public.setIs_del(0);
						ap_public.setIs_stop(0);
						List<KonkaOrderAuditProcess> ap_publicauditProcesseList = getFacade()
						        .getKonkaOrderAuditProcessService().getKonkaOrderAuditProcessList(ap_public);
						processList.addAll(ap_publicauditProcesseList);
					}
					if (null == processList || processList.size() == 0) {
						// 没有分公司流程取统一流程，即总公司流程
						KonkaOrderAuditProcess ap_public = new KonkaOrderAuditProcess();
						ap_public.getMap().put("par_add_dept_id", dept.getDept_id());
						ap_public.setIs_del(0);
						ap_public.setIs_stop(0);
						ap_public.getMap().put("customer_type_is_null", true);
						List<KonkaOrderAuditProcess> ap_publicauditProcesseList = getFacade()
						        .getKonkaOrderAuditProcessService().getKonkaOrderAuditProcessList(ap_public);
						processList.addAll(ap_publicauditProcesseList);
					}

					if (null == processList || processList.size() == 0) {
						// 没有分公司自定义的流程取统一流程，即总公司自定义流程
						KonkaOrderAuditProcess ap_public = new KonkaOrderAuditProcess();
						ap_public.setAdd_dept_id(0L);
						ap_public.setIs_del(0);
						ap_public.setIs_stop(0);
						ap_public.getMap().put("customer_type_is_null", true);
						List<KonkaOrderAuditProcess> ap_publicauditProcesseList = getFacade()
						        .getKonkaOrderAuditProcessService().getKonkaOrderAuditProcessList(ap_public);
						processList.addAll(ap_publicauditProcesseList);
					}

					request.setAttribute("customer_type", konkaR3Shop_.getCustomer_type());
				} else {
					KonkaOrderAuditProcess process = new KonkaOrderAuditProcess();
					process.getMap().put("par_add_dept_id", dept.getDept_id());
					process.getMap().put("customer_type_is_null", true);
					process.setIs_del(0);
					process.setIs_stop(0);
					processList = super.getFacade().getKonkaOrderAuditProcessService().getKonkaOrderAuditProcessList(
					        process);
					if (null == processList || processList.size() == 0) {
						// 没有分公司统一流程，即分公司优先级高
						KonkaOrderAuditProcess ap_public = new KonkaOrderAuditProcess();
						ap_public.getMap().put("customer_type_is_null", true);
						ap_public.setAdd_dept_id(0L);
						ap_public.setIs_del(0);
						ap_public.setIs_stop(0);
						List<KonkaOrderAuditProcess> ap_publicauditProcesseList = getFacade()
						        .getKonkaOrderAuditProcessService().getKonkaOrderAuditProcessList(ap_public);
						processList.addAll(ap_publicauditProcesseList);
					}
				}
			}
			request.setAttribute("processList", processList);
		}
		dynaBean.set("brandId", Constants.KONKA_BRAND_ID);
		dynaBean.set("trade_index", new BaseClientJxcAction().generateTradeIndex());// 交易流水号
		dynaBean.set("today", DateFormatUtils.format(new Date(), "yyyy-MM-dd"));// 订单日期
		dynaBean.set("cg_order_date", new Date());// 订单日期
		dynaBean.set("user_shop_name", konkaR3Shop.getCustomer_name());
		dynaBean.set("r3_code", konkaR3Shop.getR3_code());
		dynaBean.set("userName", user.getReal_name());
		String fgsSN = konkaR3Shop.getBranch_area_name_2();
		String fgsSN2 = konkaR3Shop.getR3_sales_org_code();
		request.setAttribute("sales_org", StringUtils.isBlank(fgsSN2) ? fgsSN : fgsSN2);

		String sales_org = StringUtils.isBlank(fgsSN2) ? fgsSN : fgsSN2;
		/** 取网点业务员 */
		BranchAssign bagn = new BranchAssign();
		bagn.setKonka_r3_id(current_cust_id);
		bagn = getFacade().getBranchAssignService().getBranchAssign(bagn);
		if (null != bagn && null != bagn.getUser_id()) {
			PeProdUser ywy = new PeProdUser();
			ywy.setId(bagn.getUser_id());
			ywy = getFacade().getPeProdUserService().getPeProdUserResult(ywy);
			request.setAttribute("ywy_user_name", ywy.getReal_name());
		}

		if (super.isCallR3Interface(request)) {
			// List<KNVP> knvpList =
			// super.getFacade().getR3WebInterfaceService().getKnvpsList(fgsSN,
			// Constants.default_vtweg, Constants.default_spart,
			// konkaR3Shop.getR3_code());

			List<KNVP> knvpList = new ArrayList<KNVP>();
			ReturnInfo<KNVP> info = new ReturnInfo<KNVP>();
			info = super
					.getFacade()
					.getR3WebInterfaceService()
					.getKnvpsList(sales_org,
			        Constants.default_vtweg, Constants.default_spart, konkaR3Shop.getR3_code());
			if (info.getSuccess() == true) {
				knvpList = info.getDataResult();
			}

			// AG:售达方
			// RE:出票方
			// RG:付款方
			// WE:送达方

			List<KNVP> agList = new ArrayList<KNVP>();
			List<KNVP> reList = new ArrayList<KNVP>();
			List<KNVP> rgList = new ArrayList<KNVP>();
			List<KNVP> weList = new ArrayList<KNVP>();

			if (null != knvpList) {
				for (KNVP cur : knvpList) {
					if ("AG".equalsIgnoreCase(cur.getPARVW())) {
						agList.add(cur);
					} else if ("RE".equalsIgnoreCase(cur.getPARVW())) {
						reList.add(cur);
					} else if ("RG".equalsIgnoreCase(cur.getPARVW())) {
						rgList.add(cur);
					} else if ("WE".equalsIgnoreCase(cur.getPARVW())) {
						weList.add(cur);
					}
				}
			}

			request.setAttribute("agList", agList);
			request.setAttribute("reList", reList);
			request.setAttribute("rgList", rgList);
			request.setAttribute("weList", weList);

			if (agList.size() == 0) {
				request.setAttribute("ag", konkaR3Shop.getR3_code());
			}
			if (reList.size() == 0) {
				request.setAttribute("re", konkaR3Shop.getR3_code());
			}
			if (rgList.size() == 0) {
				request.setAttribute("rg", konkaR3Shop.getR3_code());
			}
			if (weList.size() == 0) {
				KonkaMobileImpStore s = new KonkaMobileImpStore();
				s.setR3_shdf_sn(konkaR3Shop.getR3_code());
				List<KonkaMobileImpStore> sList = super.getFacade().getKonkaMobileImpStoreService()
				        .getKonkaMobileImpStoreListForDistinctSdf(s);

				List<KNVP> __weList = new ArrayList<KNVP>();
				for (KonkaMobileImpStore cur : sList) {
					KNVP k = new KNVP();
					k.setKUNN2(cur.getR3_sdf_sn());
					__weList.add(k);
				}
				request.setAttribute("weList", __weList);
				if (__weList.size() == 0) {
					request.setAttribute("we", konkaR3Shop.getR3_code());
				}
			}
		} else {
			request.setAttribute("ag", konkaR3Shop.getR3_code());
			request.setAttribute("re", konkaR3Shop.getR3_code());
			request.setAttribute("rg", konkaR3Shop.getR3_code());

			KonkaMobileImpStore s = new KonkaMobileImpStore();
			s.setR3_shdf_sn(konkaR3Shop.getR3_code());
			List<KonkaMobileImpStore> sList = super.getFacade().getKonkaMobileImpStoreService()
			        .getKonkaMobileImpStoreListForDistinctSdf(s);

			List<KNVP> weList = new ArrayList<KNVP>();
			for (KonkaMobileImpStore cur : sList) {
				KNVP k = new KNVP();
				k.setKUNN2(cur.getR3_sdf_sn());
				weList.add(k);
			}
			request.setAttribute("weList", weList);
		}
		// 查出商品详细信息并且存到konkaOrderInfoDetailsList这个list中

		for (String pd : pks) {

			PshowOrdeDetails pshowordedetails = new PshowOrdeDetails();
			pshowordedetails.setOrder_id(Long.valueOf(pd));
			List<PshowOrdeDetails> pshowordedetailslist = super.getFacade().getPshowOrdeDetailsService()
			        .getPshowOrdeDetailsList(pshowordedetails);
			for (PshowOrdeDetails ps : pshowordedetailslist) {
				KonkaOrderInfoDetails konkaorderinfodeatils = new KonkaOrderInfoDetails();
				KonkaBcompPd konkabcomppd = new KonkaBcompPd();
				konkabcomppd.setId(ps.getPd_id());
				konkabcomppd = super.getFacade().getKonkaBcompPdService().getKonkaBcompPd(konkabcomppd);
				konkaorderinfodeatils.setPd_id(Long.valueOf(konkabcomppd.getPd_spec()));
				konkaorderinfodeatils.setPd_name(konkabcomppd.getPd_sn());
				// 取型号、大类信息
				PePdModel pePdModel = new PePdModel();
				pePdModel.setBrand_id(Constants.KONKA_BRAND_ID);
				pePdModel.setIs_del(0);
				pePdModel.setPd_id(ps.getPd_id());
				pePdModel = getFacade().getPePdModelService().getPePdModel(pePdModel);
				if (null != pePdModel) {

					konkaorderinfodeatils.setBrand_id(pePdModel.getBrand_id());
					konkaorderinfodeatils.setBrand_name(Constants.KONKA_BRAND_NAME);
					konkaorderinfodeatils.setPd_type_id(pePdModel.getCls_id());
					konkaorderinfodeatils.setGood_price(BigDecimal.valueOf(super.getPdmodelPrice(
					        pePdModel.getMd_name(), konkaR3Shop.getR3_code())));
					konkaorderinfodeatils.setGood_sum_price(BigDecimal.valueOf(super.getPdmodelPrice(pePdModel
					        .getMd_name(), konkaR3Shop.getR3_code())));
					if (null != pePdModel.getCls_id() && "".equals(pePdModel.getCls_id())
					        && null != super.getBasePdClazz(pePdModel.getCls_id()))
						konkaorderinfodeatils
						        .setPd_type_name(super.getBasePdClazz(pePdModel.getCls_id()).getCls_name());
				}
				konkaorderinfodeatils.setGood_count(Integer.decode(ps.getNum().toString()));

				konkaorderinfodeatils.setGood_unit("台");

				konkaOrderInfoDetailsList.add(konkaorderinfodeatils);

			}

		}
		request.setAttribute("konkaOrderInfoDetailsList", konkaOrderInfoDetailsList);

		request.setAttribute("call_r3_interface", super.isCallR3Interface(request));
		String pks1 = "";

		for (String p : pks) {
			pks1 = pks1 + p + ",";
		}
		request.setAttribute("pshowids", pks1);
		return new ActionForward("/../customer/JxcKonkaOrderRegister/input1.jsp");
	}

}
