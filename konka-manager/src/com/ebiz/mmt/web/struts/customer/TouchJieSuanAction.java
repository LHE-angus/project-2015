package com.ebiz.mmt.web.struts.customer;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
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
import com.ebiz.mmt.domain.PshowOrdeAudit;
import com.ebiz.mmt.domain.PshowOrdeDetails;
import com.ebiz.mmt.domain.PshowOrder;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.util.ExcelUtil;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author tudp
 * @version 2013-09-28
 */
public class TouchJieSuanAction extends BaseAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		Date today = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(today);
		dynaBean.set("year", String.valueOf(calendar.get(Calendar.YEAR)));
		dynaBean.set("month_start", String.valueOf(calendar.get(Calendar.MONTH) + 1));
		dynaBean.set("month_end", String.valueOf(calendar.get(Calendar.MONTH) + 1));
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String year = (String) dynaBean.get("year");
		String month_start = (String) dynaBean.get("month_start");
		String month_end = (String) dynaBean.get("month_end");
		String dept_id = (String) dynaBean.get("dept_id");
		String xd_dept_id = (String) dynaBean.get("xd_dept_id");
		String order_from = "2";// (String) dynaBean.get("order_from");
		String rebates_status = (String) dynaBean.get("rebates_status");// 返利状态：0待确认，1
		// 提现
		// 已发放，2
		// 提现
		// 等待发放
		// 3
		// 已兑换成积分
		String state = (String) dynaBean.get("state");
		String trade_index_like = (String) dynaBean.get("trade_index_like");
		String pay_way_search = (String) dynaBean.get("pay_way_search");
		String r3_code = (String) dynaBean.get("r3_code");
		String pd_sn_like = (String) dynaBean.get("pd_sn_like");
		String touch_type = (String) dynaBean.get("touch_type");
		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.CUSTOMER_USER_INFO);
		if (null == user) {
			return null;
		}

		// 月份区间
		if (StringUtils.isBlank(month_start)) {
			month_start = "1";
		}
		if (StringUtils.isBlank(month_end)) {
			month_end = "12";
		}
		int month_start_num = Integer.valueOf(month_start);
		int month_end_num = Integer.valueOf(month_end);
		int start_num = 0;
		int end_num = 0;
		if (month_start_num < month_end_num) {
			start_num = month_start_num;
			end_num = month_end_num;
		} else {
			start_num = month_end_num;
			end_num = month_end_num;
		}
		PshowOrdeDetails entity = new PshowOrdeDetails();
		String m_s = year + "-";
		String m_e = year + "-";
		if (start_num < 10) {
			m_s = m_s + "0" + start_num;
		} else {
			m_s = m_s + start_num;
		}
		if (end_num < 10) {
			m_e = m_e + "0" + end_num;
		} else {
			m_e = m_e + end_num;
		}
		entity.getMap().put("start_date", m_s);
		entity.getMap().put("end_date", m_e);

		if (StringUtils.isNotBlank(order_from)) {
			entity.getMap().put("order_from", order_from);
		}
		if (StringUtils.isNotBlank(xd_dept_id)) {
			entity.getMap().put("xd_dept_id", Long.valueOf(xd_dept_id));
		}
		if (StringUtils.isNotBlank(dept_id)) {
			entity.getMap().put("fh_fgs_dept_id", Long.valueOf(dept_id));
		}
		if (StringUtils.isNotBlank(trade_index_like)) {
			entity.getMap().put("trade_index_like", trade_index_like);
		}
		if (StringUtils.isNotBlank(pay_way_search)) {
			entity.getMap().put("pay_way", Long.valueOf(pay_way_search));
		}
		if (!"".equals(rebates_status) && rebates_status != null) {
			entity.setRebates_status(new Integer(rebates_status));
		}
		if (StringUtils.isNotBlank(r3_code)) {
			entity.getMap().put("r3_code", r3_code);
		}
		if (StringUtils.isNotBlank(pd_sn_like)) {
			entity.getMap().put("pd_sn_like", pd_sn_like);
		}
		if (StringUtils.isNotBlank(touch_type)) {
			if (touch_type.equals("2")) {
				entity.getMap().put("pay_way", "9");
				dynaBean.set("touch_type", "2");
			} else if (touch_type.equals("1")) {
				entity.getMap().put("pay_way_not_eq", "9");
				dynaBean.set("touch_type", "1");
			}
		}

		// R3客户
		entity.getMap().put("cust_id", user.getCust_id());

		// 查询r3客户所在分公司
		PeProdUser p_user = new PeProdUser();
		p_user.getMap().put("user_id", user.getId());
		p_user = super.getFacade().getPeProdUserService().getPeProdUserForFgs(p_user);

		logger.info("分公司---->" + p_user.getMap().get("fgs_id").toString());
		// entity.getMap().put("xd_dept_id",
		// p_user.getMap().get("fgs_id").toString());

		// entity.getMap().put("m_user_id", user.getId());

		// 订单状态 默认取 40已发货和 60确认收货
		if (StringUtils.isNotBlank(state)) {
			entity.getMap().put("state_in", new String[] { state });
		} else {
			entity.getMap().put("state_in", new String[] { "40", "60" });
		}

		Long recordCount = super.getFacade().getPshowOrdeDetailsService().getPshowOrdeDetailsForFgsCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<PshowOrdeDetails> entityList = super.getFacade().getPshowOrdeDetailsService()
		        .getPshowOrdeDetailsForFgsPaginatedList(entity);

		HashMap map = new HashMap();
		List<HashMap> list = new ArrayList<HashMap>();
		for (PshowOrdeDetails pds : entityList) {
			map = new HashMap();
			String fh_dept_name = (String) pds.getMap().get("fh_dept_name");// 发货分公司
			String dh_dept_name = (String) pds.getMap().get("dh_dept_name");// 收货人所在分公司
			String xd_dept_name = (String) pds.getEcUser().getMap().get("dept_name");// 下单分公司
			String user_r3_code = (String) pds.getEcUser().getMap().get("r3_code");// 下单人r3_code
			String trade_index = (String) pds.getMap().get("trade_index");// 订单号
			Date add_date = (Date) pds.getMap().get("add_date");// 订单号
			String pd_sn = (String) pds.getMap().get("pd_sn");// 产品型号
			BigDecimal total_price = pds.getTotal_price();// 销售收入（销售总价格）
			BigDecimal pay_price = (BigDecimal) pds.getMap().get("pay_price");// /应付金额
			BigDecimal dedu_price = (BigDecimal) pds.getMap().get("dedu_price");// /抵扣金额
			BigDecimal rebates = pds.getRebates() == null ? new BigDecimal(0.0) : pds.getRebates();// 佣金
			Long num = pds.getNum(); // 数量
			Integer pd_state = pds.getState();
			map.put("fh_dept_name", fh_dept_name);
			map.put("dh_dept_name", dh_dept_name);
			map.put("xd_dept_name", xd_dept_name);
			map.put("user_r3_code", user_r3_code);
			map.put("add_date", add_date);
			map.put("trade_index", trade_index);
			map.put("pd_sn", pd_sn);
			map.put("total_price", total_price);
			map.put("pay_price", pay_price);
			map.put("dedu_price", dedu_price);
			map.put("rebates", rebates);
			map.put("rebates_status", pds.getRebates_status());
			map.put("order_from", pds.getMap().get("order_from"));
			map.put("num", num);
			map.put("pd_state", pd_state);
			map.put("integral", pds.getIntegral());
			map.put("state", pds.getMap().get("order_state"));
			map.put("real_name", pds.getEcUser().getReal_name());// 下单人真实姓名

			EcOrderExpressInfo ec = new EcOrderExpressInfo();
			PshowOrder pp = new PshowOrder();
			pp.setId(pds.getOrder_id());
			ec.setTrade_index(trade_index);
			ec = super.getFacade().getEcOrderExpressInfoService().getEcOrderExpressInfo(ec);
			if (ec != null) {
				map.put("logistic_sn", ec.getLogistic_sn());
			}

			BigDecimal price_wl = (BigDecimal) pds.getMap().get("price_wl");// 物流费用
			if (price_wl == null) {
				price_wl = new BigDecimal("0.0");
			}
			BigDecimal p = (BigDecimal) pds.getMap().get("pay_way");
			Integer pay_way = p == null ? null : p.intValue();// 支付方式0：货到付款1：在线支付
			// 2: 支付宝 3:银联 5:
			// 民生易支付
			// 计算支付费用
			BigDecimal zfb = (BigDecimal) pds.getMap().get("zfb");// 支付宝费率
			BigDecimal zfb_total = (BigDecimal) pds.getMap().get("zfb_total");// 支付
			// 宝交易量
			if (zfb_total == null) {
				zfb_total = new BigDecimal("0.0");
			}
			BigDecimal fl = new BigDecimal("0.0");
			if (pay_way != null) {
				if (pay_way.intValue() == 2) {
					if (zfb_total == null) {
						zfb_total = new BigDecimal("0.0");
					}
					if (zfb_total.floatValue() < 60000) {
						zfb = new BigDecimal("0.012");
					} else if (zfb_total.floatValue() < 500000) {
						zfb = new BigDecimal("0.010");
					} else if (zfb_total.floatValue() < 1000000) {
						zfb = new BigDecimal("0.009");
					} else if (zfb_total.floatValue() < 2000000) {
						zfb = new BigDecimal("0.008");
					} else {
						zfb = new BigDecimal("0.007");
					}
					fl = zfb;
				} else if (pay_way.intValue() == 5) {
					fl = new BigDecimal("0.0015");
				}
			}
			BigDecimal zffy = pay_price.multiply(fl);// 支付费用

			BigDecimal cash_price = (BigDecimal) pds.getMap().get("cash_price");// 现款价（成本价）
			BigDecimal price_ml = new BigDecimal("0.0");
			BigDecimal price_sj = new BigDecimal("0.0");
			if (cash_price != null) {
				price_ml = total_price.subtract(cash_price.multiply(new BigDecimal(num)));// 毛利
				price_sj = price_ml.multiply(new BigDecimal(0.17));// 税金（增值税金=毛利*17%）
				// 计算运营费用
				BigDecimal yyfy_0 = price_ml.subtract(price_sj).subtract(price_wl).subtract(zffy).subtract(rebates);// 毛利-税金-物流费-支付费用-佣金
				BigDecimal yyfy = yyfy_0.multiply(new BigDecimal(0.01));// 运营费用=（毛利-税金-物流费-支付费用-佣金）*1%
				BigDecimal bflr = yyfy_0.subtract(yyfy);// 应划拨利润=毛利-税金-物流费-支付费用-佣金-运营费用
				map.put("cash_price", cash_price);
				map.put("price_ml", price_ml);
				map.put("price_sj", price_sj.setScale(2, BigDecimal.ROUND_HALF_UP));
				map.put("yyfy", yyfy.setScale(2, BigDecimal.ROUND_HALF_UP));
				map.put("bflr", bflr);
			}
			map.put("price_wl", price_wl);
			map.put("pay_way", pay_way);
			map.put("zfb", zfb);
			map.put("fl", fl);
			map.put("zffy", zffy.setScale(2, BigDecimal.ROUND_HALF_UP));
			list.add(map);
		}
		request.setAttribute("entityList", list);
		// request.setAttribute("sybDeptInfoList",
		// super.getDeptInfoListWithOutLimit(mapping, form, request, response));

		KonkaDept sysdept = new KonkaDept();
		sysdept.setDept_type(new Integer(3));
		sysdept.getMap().put("order_by_dept_name", true);
		request.setAttribute("deptInfoList", getFacade().getKonkaDeptService().getKonkaDeptList(sysdept));

		return mapping.findForward("list");
	}

	public ActionForward excel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String year = (String) dynaBean.get("year");
		String month_start = (String) dynaBean.get("month_start");
		String month_end = (String) dynaBean.get("month_end");
		String dept_id = (String) dynaBean.get("dept_id");
		String order_from = "2";// (String) dynaBean.get("order_from");
		String rebates_status = (String) dynaBean.get("rebates_status");
		String state = (String) dynaBean.get("state");
		String xd_dept_id = (String) dynaBean.get("xd_dept_id");
		String trade_index_like = (String) dynaBean.get("trade_index_like");
		String pay_way_search = (String) dynaBean.get("pay_way_search");
		String r3_code = (String) dynaBean.get("r3_code");
		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.CUSTOMER_USER_INFO);
		if (null == user) {
			return null;
		}

		// 月份区间
		if (StringUtils.isBlank(month_start)) {
			month_start = "1";
		}
		if (StringUtils.isBlank(month_end)) {
			month_end = "12";
		}
		int month_start_num = Integer.valueOf(month_start);
		int month_end_num = Integer.valueOf(month_end);
		int start_num = 0;
		int end_num = 0;
		if (month_start_num < month_end_num) {
			start_num = month_start_num;
			end_num = month_end_num;
		} else {
			start_num = month_end_num;
			end_num = month_end_num;
		}
		PshowOrdeDetails entity = new PshowOrdeDetails();
		String m_s = year + "-";
		String m_e = year + "-";
		if (start_num < 10) {
			m_s = m_s + "0" + start_num;
		} else {
			m_s = m_s + start_num;
		}
		if (end_num < 10) {
			m_e = m_e + "0" + end_num;
		} else {
			m_e = m_e + end_num;
		}
		entity.getMap().put("start_date", m_s);
		entity.getMap().put("end_date", m_e);
		if (StringUtils.isNotBlank(order_from)) {
			entity.getMap().put("order_from", order_from);
		}
		if (StringUtils.isNotBlank(xd_dept_id)) {
			entity.getMap().put("xd_dept_id", Long.valueOf(xd_dept_id));
		}
		if (StringUtils.isNotBlank(dept_id)) {
			entity.getMap().put("fh_fgs_dept_id", Long.valueOf(dept_id));
		}
		if (!"".equals(rebates_status) && rebates_status != null) {
			entity.setRebates_status(new Integer(rebates_status));
		}
		if (StringUtils.isNotBlank(trade_index_like)) {
			entity.getMap().put("trade_index_like", trade_index_like);
		}
		if (StringUtils.isNotBlank(pay_way_search)) {
			entity.getMap().put("pay_way", Long.valueOf(pay_way_search));
		}
		if (StringUtils.isNotBlank(r3_code)) {
			entity.getMap().put("r3_code", r3_code);
		}
		entity.getMap().put("m_user_id", user.getId());
		// 订单状态 默认取 40已发货和 60确认收货
		if (StringUtils.isNotBlank(state)) {
			entity.getMap().put("state_in", new String[] { state });
		} else {
			entity.getMap().put("state_in", new String[] { "40", "60" });
		}

		// R3客户
		entity.getMap().put("cust_id", user.getCust_id());

		Long recordCount = super.getFacade().getPshowOrdeDetailsService().getPshowOrdeDetailsForFgsCount(entity);
		// pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(new Integer(0));
		entity.getRow().setCount(new Integer(recordCount.intValue()));

		List<PshowOrdeDetails> entityList = super.getFacade().getPshowOrdeDetailsService()
		        .getPshowOrdeDetailsForFgsPaginatedList(entity);
		ExcelUtil e = new ExcelUtil();
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet();
		int r = 0;
		e.setWorkbook(workbook);
		e.setSheet(sheet);
		e.createRow(r);
		e.setCell(0, "序号");
		e.setCell(1, "发货分公司");
		e.setCell(2, "到货分公司");
		e.setCell(3, "下单分公司");
		e.setCell(4, "R3_CODE");
		e.setCell(5, "交易流水号");
		e.setCell(6, "下单日期");
		e.setCell(7, "产品型号");
		e.setCell(8, "销售价格");
		e.setCell(9, "抵扣金额");
		e.setCell(10, "实付金额");
		e.setCell(11, "数量");
		e.setCell(12, "现款价");
		e.setCell(13, "佣金");
		e.setCell(14, "奖励积分");
		e.setCell(15, "物流单号");
		e.setCell(16, "物流费用");
		e.setCell(17, "支付方式 ");
		e.setCell(18, "支付费用 ");
		e.setCell(19, "订单来源 ");
		e.setCell(20, "订单状态 ");
		e.setCell(21, "下单人");

		for (PshowOrdeDetails pds : entityList) {
			r++;
			String fh_dept_name = (String) pds.getMap().get("fh_dept_name");// 发货分公司
			String dh_dept_name = (String) pds.getMap().get("dh_dept_name");// 收货人所在分公司
			String xd_dept_name = (String) pds.getEcUser().getMap().get("dept_name");// 下单分公司
			String user_r3_code = (String) pds.getEcUser().getMap().get("r3_code");// 下单分公司
			String trade_index = (String) pds.getMap().get("trade_index");// 订单号
			Date add_date = (Date) pds.getMap().get("add_date");// 订单号
			String pd_sn = (String) pds.getMap().get("pd_sn");// 产品型号
			BigDecimal total_price = pds.getTotal_price();// 销售收入（销售总价格）
			BigDecimal pay_price = (BigDecimal) pds.getMap().get("pay_price");// /应付金额
			BigDecimal dedu_price = (BigDecimal) pds.getMap().get("dedu_price");// /抵扣金额
			BigDecimal rebates = pds.getRebates() == null ? new BigDecimal(0.0) : pds.getRebates();// 佣金
			Long num = pds.getNum(); // 数量
			BigDecimal integral = pds.getIntegral() == null ? new BigDecimal(0.0) : pds.getIntegral();
			Integer pd_state = pds.getState();
			String real_name = pds.getEcUser().getReal_name();// 真实姓名

			BigDecimal cash_price = (BigDecimal) pds.getMap().get("cash_price");// 现款价（成本价）
			e.createRow(r);
			e.setCell(0, r);
			e.setCell(1, fh_dept_name);
			e.setCell(2, dh_dept_name);
			e.setCell(3, xd_dept_name);
			e.setCell(4, user_r3_code);
			e.setCell(5, trade_index);
			Calendar add_datecal = Calendar.getInstance();
			add_datecal.setTime(add_date);
			e.setCell(6, add_datecal);
			e.setCell(7, pd_sn);
			e.setCell(8, total_price.toString());
			e.setCell(9, dedu_price.toString());
			e.setCell(10, pay_price.toString());
			e.setCell(11, num);

			BigDecimal price_wl = (BigDecimal) pds.getMap().get("price_wl");// 物流费用
			if (price_wl == null) {
				price_wl = new BigDecimal("0.0");
			}
			BigDecimal p = (BigDecimal) pds.getMap().get("pay_way");
			Integer pay_way = p == null ? null : p.intValue();// 支付方式0：货到付款1：在线支付
			BigDecimal zfb = (BigDecimal) pds.getMap().get("zfb");// 支付宝费率
			BigDecimal zfb_total = (BigDecimal) pds.getMap().get("zfb_total");// 支付
			// 宝交易量
			BigDecimal fl = new BigDecimal("0.0");
			if (pay_way != null) {
				if (pay_way.intValue() == 2) {
					if (zfb_total == null) {
						zfb_total = new BigDecimal("0.0");
					}
					if (zfb_total.floatValue() < 60000) {
						zfb = new BigDecimal("0.012");
					} else if (zfb_total.floatValue() < 500000) {
						zfb = new BigDecimal("0.010");
					} else if (zfb_total.floatValue() < 1000000) {
						zfb = new BigDecimal("0.009");
					} else if (zfb_total.floatValue() < 2000000) {
						zfb = new BigDecimal("0.008");
					} else {
						zfb = new BigDecimal("0.007");
					}
					fl = zfb;
				} else if (pay_way.intValue() == 5) {
					fl = new BigDecimal("0.0015");
				}
			}
			BigDecimal zffy = pay_price.multiply(fl);// 支付费用 =实际支付金额*费率
			if (cash_price != null) {
				e.setCell(12, cash_price.toString());
				BigDecimal price_ml = total_price.subtract(cash_price.multiply(new BigDecimal(num)));// 毛利
				BigDecimal price_sj = price_ml.multiply(new BigDecimal(0.17));// 税金（增值税金=毛利*17%）
				// 计算运营费用
				BigDecimal yyfy_0 = price_ml.subtract(price_sj).subtract(price_wl).subtract(zffy).subtract(rebates);// 毛利-税金-物流费-支付费用-佣金
				BigDecimal yyfy = yyfy_0.multiply(new BigDecimal(0.01)); // 运营费用=（毛利-税金-物流费-支付费用-佣金）*1%
				BigDecimal bflr = yyfy_0.subtract(yyfy);// 应划拨利润=毛利-税金-物流费-支付费用-佣金-运营费用
			}
			e.setCell(13, rebates.toString());
			e.setCell(14, integral.setScale(0, BigDecimal.ROUND_HALF_UP).toString());

			EcOrderExpressInfo ec = new EcOrderExpressInfo();
			PshowOrder pp = new PshowOrder();
			pp.setId(pds.getOrder_id());
			ec.setTrade_index(trade_index);
			ec = super.getFacade().getEcOrderExpressInfoService().getEcOrderExpressInfo(ec);
			if (ec != null) {
				e.setCell(15, ec.getLogistic_sn());
			} else {
				e.setCell(15, "");
			}

			e.setCell(16, price_wl.toString());
			String pay_way_name = "";
			if (pay_way.intValue() == 0) {
				pay_way_name = "货到付款";
			} else if (pay_way.intValue() == 1) {
				pay_way_name = "银行汇款";
			} else if (pay_way.intValue() == 2) {
				pay_way_name = "支付宝";
			} else if (pay_way.intValue() == 3) {
				pay_way_name = "银联";
			} else if (pay_way.intValue() == 4) {
				pay_way_name = "财付通";
			} else if (pay_way.intValue() == 5) {
				pay_way_name = "民生银行";
			} else if (pay_way.intValue() == 9) {
				pay_way_name = "货线下处理";
			}
			e.setCell(17, pay_way_name);
			e.setCell(18, zffy.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
			String orderfrom = pds.getMap().get("order_from").toString();
			if ("1".equals(orderfrom)) {
				e.setCell(19, "工卡");
			} else if ("2".equals(orderfrom)) {
				if (pay_way.intValue() == 9) {
					e.setCell(19, "触网2");
				} else {
					e.setCell(19, "触网1");
				}
			} else if ("3".equals(orderfrom)) {
				e.setCell(19, "其他");
			}
			Integer order_state = new Integer(pds.getMap().get("order_state").toString());
			String state_name = "";
			if (pd_state.intValue() == 2) {
				state_name = "(换货商品)";
			}
			if (order_state.intValue() == 40) {
				e.setCell(20, "发货中" + state_name);
			} else if (order_state.intValue() == 60) {
				e.setCell(20, "已确认收货" + state_name);
			}
			e.setCell(21, real_name);
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
		// String id = (String) dynaBean.get("id");
		String trade_index = (String) dynaBean.get("trade_index");

		PshowOrder entity = new PshowOrder();
		// entity.setId(Long.valueOf(id));
		entity.setTrade_index(trade_index);
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
		psd.setOrder_id(entity.getId());
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
		psa.setOrder_id(entity.getId());
		psa.getMap().put("orderByDate", true);
		List<PshowOrdeAudit> PshowOrdeAudits = super.getFacade().getPshowOrdeAuditService().getPshowOrdeAuditList(psa);

		request.setAttribute("t_num", t_num);
		request.setAttribute("t_price", t_price);
		request.setAttribute("pshowOrdeDetails", pshowordedetails);
		request.setAttribute("PshowOrdeAudits", PshowOrdeAudits);
		// 子订单查询
		PshowOrder zOrder = new PshowOrder();
		zOrder.setPar_order_id(entity.getId());
		List<PshowOrder> zOrderlist = super.getFacade().getPshowOrderService().getPshowOrderIncludeDetailsList(zOrder);
		request.setAttribute("zOrderlist", zOrderlist);

		return mapping.findForward("view");
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
		return new ActionForward("/../customer/TouchJieSuan/sheet.jsp");
	}

}
