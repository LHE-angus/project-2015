package com.ebiz.mmt.web.struts.manager.spgl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.EcOrderExpressInfo;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.domain.PshowOrdeDetails;
import com.ebiz.mmt.domain.PshowOrder;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.util.EncryptUtils;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author tudp
 * @version 2013-09-28
 */
public class EcJieSuanSumAction extends BaseAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String show_value = (String) dynaBean.get("show_value");
		String month_start = (String) dynaBean.get("month_start");
		String month_end = (String) dynaBean.get("month_end");
		String dept_id = (String) dynaBean.get("dept_id");
		String order_from = (String) dynaBean.get("order_from");
		String rebates_status = (String) dynaBean.get("rebates_status");// 返利状态：0待确认，1
		String state = (String) dynaBean.get("state");
		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}
		if (!"1".equals(show_value)) {
			request
			        .setAttribute("sybDeptInfoList", super
			                .getDeptInfoListWithOutLimit(mapping, form, request, response));
			return mapping.findForward("list");
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
		PshowOrdeDetails entity = new PshowOrdeDetails();
		entity.getMap().put("add_date_start", month_start);
		entity.getMap().put("add_date_end", month_end);

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
		entity.getRow().setFirst(new Integer(0));
		entity.getRow().setCount(recordCount.intValue());

		List<PshowOrdeDetails> entityList = super.getFacade().getPshowOrdeDetailsService()
		        .getPshowOrdeDetailsForFgsPaginatedList(entity);
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
			map.put("integral", pds.getIntegral());
			map.put("state", pds.getMap().get("order_state"));
			map.put("department", pds.getMap().get("department"));

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
			Integer pay_way = p == null ? null : p.intValue();// 支付方式0：货到付款1：银行转账
			// 2: 支付宝 3:银联
			// 5:民生易支付
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
			if (pay_price == null) {
				pay_price = total_price;
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
				map.put("price_sj", price_sj);
				map.put("yyfy", yyfy);
				map.put("bflr", bflr);
			}
			map.put("price_wl", price_wl);
			map.put("pay_way", pay_way);
			map.put("zfb", zfb);
			map.put("fl", fl);
			map.put("zffy", zffy);
			list.add(map);
		}

		Map map1 = new HashMap();
		Map allMap = new HashMap();
		for (int i = 0; i < list.size(); i++) {
			map1 = new HashMap();
			map1 = list.get(i);
			String pd_sn = map1.get("pd_sn").toString();
			Map map2 = allMap.get(pd_sn) == null ? new HashMap() : (HashMap) allMap.get(pd_sn);
			// total_price
			BigDecimal total_price1 = map1.get("total_price") == null ? new BigDecimal("0.0") : (BigDecimal) map1
			        .get("total_price");
			BigDecimal total_price2 = map2.get("total_price") == null ? new BigDecimal("0.0") : (BigDecimal) map2
			        .get("total_price");
			total_price2 = total_price1.add(total_price2);
			map2.put("total_price", total_price2);

			// pay_price
			BigDecimal pay_price1 = map1.get("pay_price") == null ? new BigDecimal("0.0") : (BigDecimal) map1
			        .get("pay_price");
			BigDecimal pay_price2 = map2.get("pay_price") == null ? new BigDecimal("0.0") : (BigDecimal) map2
			        .get("pay_price");
			pay_price2 = pay_price1.add(pay_price2);
			map2.put("pay_price", pay_price2);

			// dedu_price
			BigDecimal dedu_price1 = map1.get("dedu_price") == null ? new BigDecimal("0.0") : (BigDecimal) map1
			        .get("dedu_price");
			BigDecimal dedu_price2 = map2.get("dedu_price") == null ? new BigDecimal("0.0") : (BigDecimal) map2
			        .get("dedu_price");
			dedu_price2 = dedu_price1.add(dedu_price2);
			map2.put("dedu_price", dedu_price2);

			// rebates
			BigDecimal rebates1 = map1.get("rebates") == null ? new BigDecimal("0.0") : (BigDecimal) map1
			        .get("rebates");
			BigDecimal rebates2 = map2.get("rebates") == null ? new BigDecimal("0.0") : (BigDecimal) map2
			        .get("rebates");
			rebates2 = rebates1.add(rebates2);
			map2.put("rebates", rebates2);

			// integral
			BigDecimal integral1 = map1.get("integral") == null ? new BigDecimal("0.0") : (BigDecimal) map1
			        .get("integral");
			BigDecimal integral2 = map2.get("integral") == null ? new BigDecimal("0.0") : (BigDecimal) map2
			        .get("integral");
			integral2 = integral1.add(integral2);
			map2.put("integral", integral2);

			// price_wl
			BigDecimal price_wl1 = map1.get("price_wl") == null ? new BigDecimal("0.0") : (BigDecimal) map1
			        .get("price_wl");
			BigDecimal price_wl2 = map2.get("price_wl") == null ? new BigDecimal("0.0") : (BigDecimal) map2
			        .get("price_wl");
			price_wl2 = price_wl1.add(price_wl2);
			map2.put("price_wl", price_wl2);

			// zffy
			BigDecimal zffy1 = map1.get("zffy") == null ? new BigDecimal("0.0") : (BigDecimal) map1.get("zffy");
			BigDecimal zffy2 = map2.get("zffy") == null ? new BigDecimal("0.0") : (BigDecimal) map2.get("zffy");
			zffy2 = zffy1.add(zffy2);
			map2.put("zffy", zffy2);

			// num
			Long num1 = map1.get("num") == null ? new Long("0") : (Long) map1.get("num");
			Long num2 = map2.get("num") == null ? new Long("0") : (Long) map2.get("num");
			num2 = new Long(num1.intValue() + num2.intValue());
			map2.put("num", num2);
			map2.put("department", map1.get("department"));

			map2.put("pd_sn", pd_sn);
			allMap.put(pd_sn, map2);
		}
		if (allMap.values().size() > 0) {
			request.setAttribute("entityList", allMap.values().toArray());
		}
		request.setAttribute("sybDeptInfoList", super.getDeptInfoListWithOutLimit(mapping, form, request, response));

		return mapping.findForward("list");
	}

	public ActionForward excel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String show_value = (String) dynaBean.get("show_value");
		String month_start = (String) dynaBean.get("month_start");
		String month_end = (String) dynaBean.get("month_end");
		String dept_id = (String) dynaBean.get("dept_id");
		String order_from = (String) dynaBean.get("order_from");
		String rebates_status = (String) dynaBean.get("rebates_status");// 返利状态：0待确认，1
		String state = (String) dynaBean.get("state");
		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}
		if (!"1".equals(show_value)) {
			return mapping.findForward("list");
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
		PshowOrdeDetails entity = new PshowOrdeDetails();
		entity.getMap().put("add_date_start", month_start);
		entity.getMap().put("add_date_end", month_end);

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
		entity.getRow().setFirst(new Integer(0));
		entity.getRow().setCount(recordCount.intValue());

		List<PshowOrdeDetails> entityList = super.getFacade().getPshowOrdeDetailsService()
		        .getPshowOrdeDetailsForFgsPaginatedList(entity);
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
			map.put("integral", pds.getIntegral());
			map.put("state", pds.getMap().get("order_state"));

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
			Integer pay_way = p == null ? null : p.intValue();// 支付方式0：货到付款1：银行转账
			// 2: 支付宝 3:银联
			// 5:民生易支付
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
			if (pay_price == null) {
				pay_price = total_price;
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
				map.put("price_sj", price_sj);
				map.put("yyfy", yyfy);
				map.put("bflr", bflr);
			}
			map.put("price_wl", price_wl);
			map.put("pay_way", pay_way);
			map.put("zfb", zfb);
			map.put("fl", fl);
			map.put("zffy", zffy);
			list.add(map);
		}

		Map map1 = new HashMap();
		Map allMap = new HashMap();
		for (int i = 0; i < list.size(); i++) {
			map1 = new HashMap();
			map1 = list.get(i);
			String pd_sn = map1.get("pd_sn").toString();
			Map map2 = allMap.get(pd_sn) == null ? new HashMap() : (HashMap) allMap.get(pd_sn);
			// total_price
			BigDecimal total_price1 = map1.get("total_price") == null ? new BigDecimal("0.0") : (BigDecimal) map1
			        .get("total_price");
			BigDecimal total_price2 = map2.get("total_price") == null ? new BigDecimal("0.0") : (BigDecimal) map2
			        .get("total_price");
			total_price2 = total_price1.add(total_price2);
			map2.put("total_price", total_price2);

			// pay_price
			BigDecimal pay_price1 = map1.get("pay_price") == null ? new BigDecimal("0.0") : (BigDecimal) map1
			        .get("pay_price");
			BigDecimal pay_price2 = map2.get("pay_price") == null ? new BigDecimal("0.0") : (BigDecimal) map2
			        .get("pay_price");
			pay_price2 = pay_price1.add(pay_price2);
			map2.put("pay_price", pay_price2);

			// dedu_price
			BigDecimal dedu_price1 = map1.get("dedu_price") == null ? new BigDecimal("0.0") : (BigDecimal) map1
			        .get("dedu_price");
			BigDecimal dedu_price2 = map2.get("dedu_price") == null ? new BigDecimal("0.0") : (BigDecimal) map2
			        .get("dedu_price");
			dedu_price2 = dedu_price1.add(dedu_price2);
			map2.put("dedu_price", dedu_price2);

			// rebates
			BigDecimal rebates1 = map1.get("rebates") == null ? new BigDecimal("0.0") : (BigDecimal) map1
			        .get("rebates");
			BigDecimal rebates2 = map2.get("rebates") == null ? new BigDecimal("0.0") : (BigDecimal) map2
			        .get("rebates");
			rebates2 = rebates1.add(rebates2);
			map2.put("rebates", rebates2);

			// integral
			BigDecimal integral1 = map1.get("integral") == null ? new BigDecimal("0.0") : (BigDecimal) map1
			        .get("integral");
			BigDecimal integral2 = map2.get("integral") == null ? new BigDecimal("0.0") : (BigDecimal) map2
			        .get("integral");
			integral2 = integral1.add(integral2);
			map2.put("integral", integral2);

			// price_wl
			BigDecimal price_wl1 = map1.get("price_wl") == null ? new BigDecimal("0.0") : (BigDecimal) map1
			        .get("price_wl");
			BigDecimal price_wl2 = map2.get("price_wl") == null ? new BigDecimal("0.0") : (BigDecimal) map2
			        .get("price_wl");
			price_wl2 = price_wl1.add(price_wl2);
			map2.put("price_wl", price_wl2);

			// zffy
			BigDecimal zffy1 = map1.get("zffy") == null ? new BigDecimal("0.0") : (BigDecimal) map1.get("zffy");
			BigDecimal zffy2 = map2.get("zffy") == null ? new BigDecimal("0.0") : (BigDecimal) map2.get("zffy");
			zffy2 = zffy1.add(zffy2);
			map2.put("zffy", zffy2);

			// num
			Long num1 = map1.get("num") == null ? new Long("0") : (Long) map1.get("num");
			Long num2 = map2.get("num") == null ? new Long("0") : (Long) map2.get("num");
			num2 = new Long(num1.intValue() + num2.intValue());
			map2.put("num", num2);

			map2.put("pd_sn", pd_sn);
			allMap.put(pd_sn, map2);
		}
		if (allMap.values().size() > 0) {
			request.setAttribute("entityList", allMap.values().toArray());
		}
		request.setAttribute("sybDeptInfoList", super.getDeptInfoListWithOutLimit(mapping, form, request, response));

		String fname = EncryptUtils.encodingFileName("销售机型汇总" + month_start + "至" + month_end + ".xls");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename=" + fname);

		return mapping.findForward("view");
	}
}
