package com.ebiz.mmt.web.struts.manager.spgl;

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
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.EcBindingPdOrdeDetails;
import com.ebiz.mmt.domain.EcOrderExpressInfo;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.domain.PshowOrdeDetails;
import com.ebiz.mmt.domain.PshowOrder;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.util.ExcelUtil;
import com.ebiz.ssi.util.EncryptUtils;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author tudp
 * @version 2013-09-28
 */
public class EcJieSuanAction extends BaseAction {

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
		String order_from = (String) dynaBean.get("order_from");
		String rebates_status = (String) dynaBean.get("rebates_status");// 返利状态：0待确认，1 提现 已发放，2 提现等待发放 ，3已兑换成积分
		String state = (String) dynaBean.get("state");
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
			if (peRoleUser.getRole_id() < 30L || peRoleUser.getRole_id().intValue()==140317 || peRoleUser.getRole_id().intValue()==1001 ) {
				role_id_eq_30 = true;
			}
			// 分公司管理员
			if (peRoleUser.getRole_id() >= 30L && peRoleUser.getRole_id() < 60L) {
				role_id_gt_30_lt_60 = true;
			}
		}
		// 总部可以查看所有订单
		// 分公司只能看到分公司的订单

		// 总部和分公司之外的不能查看
		if (!role_id_gt_30_lt_60 && !role_id_eq_30) {
			String msg = super.getMessage(request, "konka.r3.roleError");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
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
		if (role_id_gt_30_lt_60) {
			entity.getMap().put("fh_fgs_dept_id", user.getDept_id());
		}
		if (StringUtils.isNotBlank(dept_id)) {
			entity.getMap().put("fh_fgs_dept_id", Long.valueOf(dept_id));
		}
		if (!"".equals(rebates_status) && rebates_status != null) {
			entity.setRebates_status(new Integer(rebates_status));
		}
		entity.getMap().put("m_user_id", user.getId());

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

		// 工号销售结算明细总表
		// 发货分公司 收货人所在分公司 订单号 产品型号 销售收入（销售总价格） 数量 现款价（成本价） 毛利 税金（增值税金=毛利*17%）
		// 物流（接口导入） 支付费用（支付宝，民生银行） 佣金 运营费用 应划拨利润 
		// 销售毛利=收入（销售总额）-成本
		// 增值税金=销售毛利*17%
		// 运营费用=（毛利-税金-物流费-支付费用-佣金）*1%
		// 应划拨利润=毛利-税金-物流费-支付费用-佣金-运营费用
		HashMap map = new HashMap();
		List<HashMap> list = new ArrayList<HashMap>();
		for (PshowOrdeDetails pds : entityList) {
			map = new HashMap();
			String fh_dept_name = (String) pds.getMap().get("fh_dept_name");// 发货分公司
			String dh_dept_name = (String) pds.getMap().get("dh_dept_name");// 收货人所在分公司
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
			map.put("pay_integral", pds.getPay_integral());
			map.put("state", pds.getMap().get("order_state"));
			map.put("real_name", pds.getEcUser().getReal_name());// 真实姓名

			EcBindingPdOrdeDetails ppd = new EcBindingPdOrdeDetails();
			ppd.setDetails_id(pds.getBill_item_id());
			List<EcBindingPdOrdeDetails> ppdList = super.getFacade().getEcBindingPdOrdeDetailsService()
			        .getEcBindingPdOrdeDetailsList(ppd);
			String pd_fuwu = "";
			if (null != ppdList && ppdList.size() > 0) {
				for (EcBindingPdOrdeDetails ecBindingPdOrdeDetails : ppdList) {
					pd_fuwu = pd_fuwu + ecBindingPdOrdeDetails.getGoods_name() + "*" + ecBindingPdOrdeDetails.getNum()
					        + "=" + ecBindingPdOrdeDetails.getPrice() + ",";
				}
			}
			map.put("pd_fuwu", pd_fuwu);// 绑定服务套餐

			EcOrderExpressInfo ec = new EcOrderExpressInfo();
			PshowOrder pp = new PshowOrder();
			pp.setId(pds.getOrder_id());
			ec.setTrade_index(trade_index);
			ec = super.getFacade().getEcOrderExpressInfoService().getEcOrderExpressInfo(ec);
			if (ec != null)
				map.put("logistic_sn", ec.getLogistic_sn());

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
		request.setAttribute("sybDeptInfoList", super.getDeptInfoListWithOutLimit(mapping, form, request, response));

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
		String order_from = (String) dynaBean.get("order_from");
		String rebates_status = (String) dynaBean.get("rebates_status");
		String state = (String) dynaBean.get("state");

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
		// 总部可以查看所有订单
		// 分公司只能看到分公司的订单
		// 总部和分公司之外的不能查看
		if (!role_id_gt_30_lt_60 && !role_id_eq_30) {
			String msg = super.getMessage(request, "konka.r3.roleError");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
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
		if (role_id_gt_30_lt_60) {
			entity.getMap().put("fh_fgs_dept_id", user.getDept_id());
		}
		if (StringUtils.isNotBlank(dept_id)) {
			entity.getMap().put("fh_fgs_dept_id", Long.valueOf(dept_id));
		}
		if (!"".equals(rebates_status) && rebates_status != null) {
			entity.setRebates_status(new Integer(rebates_status));
		}
		entity.getMap().put("m_user_id", user.getId());
		// 订单状态 默认取 40已发货和 60确认收货
		if (StringUtils.isNotBlank(state)) {
			entity.getMap().put("state_in", new String[] { state });
		} else {
			entity.getMap().put("state_in", new String[] { "40", "60" });
		}

		Long recordCount = super.getFacade().getPshowOrdeDetailsService().getPshowOrdeDetailsForFgsCount(entity);
		// pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(new Integer(0));
		entity.getRow().setCount(new Integer(recordCount.intValue()));

		List<PshowOrdeDetails> entityList = super.getFacade().getPshowOrdeDetailsService()
		        .getPshowOrdeDetailsForFgsPaginatedList(entity);

		// 工号销售结算明细总表
		// 发货分公司 收货人所在分公司 订单号 产品型号 销售收入（销售总价格） 数量 现款价（成本价） 毛利 税金（增值税金=毛利*17%）
		// 物流（接口导入） 支付费用（支付宝，民生银行） 佣金 运营费用 应划拨利润
		// 销售毛利=收入（销售总额）-成本
		// 增值税金=销售毛利*17%
		// 运营费用=（毛利-税金-物流费-支付费用-佣金）*1%
		// 应划拨利润=毛利-税金-物流费-支付费用-佣金-运营费用
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
		e.setCell(3, "交易流水号");
		e.setCell(4, "下单日期");
		e.setCell(5, "产品型号");
		e.setCell(6, "销售价格");
		e.setCell(7, "抵扣金额");
		e.setCell(8, "实付金额");
		e.setCell(9, "数量");
		e.setCell(10, "现款价");
		// e.setCell(9, "毛利");
		e.setCell(11, "佣金");
		e.setCell(12, "付款积分");
		e.setCell(13, "奖励积分");
		// e.setCell(11, "税金");
		e.setCell(14, "物流单号");
		e.setCell(15, "物流费用");
		e.setCell(16, "支付费用 ");
		// e.setCell(14, "平台运营费用");
		// e.setCell(15, "应划拨利润");
		e.setCell(17, "订单来源 ");
		e.setCell(18, "订单状态 ");
		e.setCell(19, "下单人");
		e.setCell(20, "延保服务");

		for (PshowOrdeDetails pds : entityList) {
			r++;
			String fh_dept_name = (String) pds.getMap().get("fh_dept_name");// 发货分公司
			String dh_dept_name = (String) pds.getMap().get("dh_dept_name");// 收货人所在分公司
			String trade_index = (String) pds.getMap().get("trade_index");// 订单号
			Date add_date = (Date) pds.getMap().get("add_date");// 订单号
			String pd_sn = (String) pds.getMap().get("pd_sn");// 产品型号
			BigDecimal total_price = pds.getTotal_price();// 销售收入（销售总价格）
			BigDecimal pay_price = (BigDecimal) pds.getMap().get("pay_price");// /应付金额
			BigDecimal dedu_price = (BigDecimal) pds.getMap().get("dedu_price");// /抵扣金额
			BigDecimal rebates = pds.getRebates() == null ? new BigDecimal(0.0) : pds.getRebates();// 佣金
			Long num = pds.getNum(); // 数量
			BigDecimal integral = pds.getIntegral() == null ? new BigDecimal(0.0) : pds.getIntegral();
			BigDecimal pay_integral = pds.getPay_integral() == null ? new BigDecimal(0.0) : pds.getPay_integral();
			Integer pd_state = pds.getState();
			String real_name = pds.getEcUser().getReal_name();// 真实姓名

			BigDecimal cash_price = (BigDecimal) pds.getMap().get("cash_price");// 现款价（成本价）
			e.createRow(r);
			e.setCell(0, r);
			e.setCell(1, fh_dept_name);
			e.setCell(2, dh_dept_name);
			e.setCell(3, trade_index);
			Calendar add_datecal = Calendar.getInstance();
			add_datecal.setTime(add_date);
			e.setCell(4, add_datecal);
			e.setCell(5, pd_sn);
			e.setCell(6, total_price.toString());
			e.setCell(7, dedu_price.toString());
			e.setCell(8, pay_price.toString());
			e.setCell(9, num);

			BigDecimal price_wl = (BigDecimal) pds.getMap().get("price_wl");// 物流费用
			if (price_wl == null) {
				price_wl = new BigDecimal("0.0");
			}
			BigDecimal p = (BigDecimal) pds.getMap().get("pay_way");
			Integer pay_way = p == null ? null : p.intValue();// 支付方式0：货到付款1：在线支付
			// 2: 支付宝 3:银联
			// 5:民生易支付

			// 计算支付费用
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
				e.setCell(10, cash_price.toString());
				BigDecimal price_ml = total_price.subtract(cash_price.multiply(new BigDecimal(num)));// 毛利
				BigDecimal price_sj = price_ml.multiply(new BigDecimal(0.17));// 税金（增值税金=毛利*17%）
				// 计算运营费用
				BigDecimal yyfy_0 = price_ml.subtract(price_sj).subtract(price_wl).subtract(zffy).subtract(rebates);// 毛利-税金-物流费-支付费用-佣金
				BigDecimal yyfy = yyfy_0.multiply(new BigDecimal(0.01)); // 运营费用=（毛利-税金-物流费-支付费用-佣金）*1%
				BigDecimal bflr = yyfy_0.subtract(yyfy);// 应划拨利润=毛利-税金-物流费-支付费用-佣金-运营费用
			}
			e.setCell(11, rebates.toString());
			e.setCell(12, pay_integral.setScale(0, BigDecimal.ROUND_HALF_UP).toString());
			e.setCell(13, integral.setScale(0, BigDecimal.ROUND_HALF_UP).toString());

			EcOrderExpressInfo ec = new EcOrderExpressInfo();
			PshowOrder pp = new PshowOrder();
			pp.setId(pds.getOrder_id());
			ec.setTrade_index(trade_index);
			ec = super.getFacade().getEcOrderExpressInfoService().getEcOrderExpressInfo(ec);
			if (ec != null) {
				e.setCell(14, ec.getLogistic_sn());
			} else {
				e.setCell(14, "");
			}

			e.setCell(15, price_wl.toString());
			e.setCell(16, zffy.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
			String orderfrom = pds.getMap().get("order_from").toString();
			if ("1".equals(orderfrom)) {
				e.setCell(17, "工卡");
			} else if ("2".equals(orderfrom)) {
				e.setCell(17, "触网");
			} else if ("3".equals(orderfrom)) {
				e.setCell(17, "其他");
			}
			Integer order_state = new Integer(pds.getMap().get("order_state").toString());
			String state_name = "";
			if (pd_state.intValue() == 2) {
				state_name = "(换货商品)";
			}
			if (order_state.intValue() == 40) {
				e.setCell(18, "发货中" + state_name);
			} else if (order_state.intValue() == 60) {
				e.setCell(18, "已确认收货" + state_name);
			}
			e.setCell(19, real_name);

			EcBindingPdOrdeDetails ppd = new EcBindingPdOrdeDetails();
			ppd.setDetails_id(pds.getBill_item_id());
			List<EcBindingPdOrdeDetails> ppdList = super.getFacade().getEcBindingPdOrdeDetailsService()
			        .getEcBindingPdOrdeDetailsList(ppd);
			String pd_fuwu = "";
			if (null != ppdList && ppdList.size() > 0) {
				for (EcBindingPdOrdeDetails ecBindingPdOrdeDetails : ppdList) {
					pd_fuwu = pd_fuwu + ecBindingPdOrdeDetails.getGoods_name() + "*" + ecBindingPdOrdeDetails.getNum()
					        + "=" + ecBindingPdOrdeDetails.getPrice() + ",";
				}
			}
			e.setCell(20, pd_fuwu);// 绑定服务套餐

		}
		// 输出
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename=" + EncryptUtils.encodingFileName("销售结算表")
				+ ".xls");
		OutputStream out = response.getOutputStream();
		e.exportXLS(out);
		return null;
	}
}
