package com.ebiz.mmt.web.struts.customer;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.ChannelDataImport;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.util.EncryptUtils;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * 客户管理 > 渠道统计分析 > R3销售数据导入
 * 
 * @author Gao,YongXiang
 */
public class ChannelDataListAction extends BaseAction {

	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		// 2.导航信息
		setNaviStringToRequestScope(form, request);

		// 3.条件过滤
		DynaBean dynaBean = (DynaBean) form;
		String column_8_like = (String) dynaBean.get("column_8_like");

		// 订单日期
		String s_date = (String) dynaBean.get("s_date");
		String e_date = (String) dynaBean.get("e_date");

		// 凭证日期
		String startDate1 = (String) dynaBean.get("startDate1");
		String endDate1 = (String) dynaBean.get("endDate1");

		// 判断是否是第一次查询
		String is_first = (String) dynaBean.get("is_first");

		SimpleDateFormat fmt1 = new SimpleDateFormat("yyyy-MM-dd");

		// 是否excel
		String excel_all = (String) dynaBean.get("excel_all");

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.CUSTOMER_USER_INFO);

		ChannelDataImport entity = new ChannelDataImport();
		entity.getMap().put("count1", true);

		KonkaR3Shop krs = new KonkaR3Shop();
		krs.setId(user.getCust_id());
		krs = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(krs);
		if (null != krs) {
			entity.setColumn_1(krs.getR3_code());
		}

		if (StringUtils.isNotBlank(column_8_like)) {
			entity.getMap().put("column_8_like", column_8_like);
		}
		if (StringUtils.isNotBlank(s_date)) {
			entity.getMap().put("s_date", s_date + " 00:00:00");
		} else {
			if (StringUtils.isBlank(is_first)) {
				entity.getMap().put("s_date", theFirstDayOfCurrentMonth() + " 00:00:00");
				dynaBean.set("s_date", theFirstDayOfCurrentMonth());
			}
		}

		if (StringUtils.isNotBlank(e_date)) {
			entity.getMap().put("e_date", e_date + " 23:59:59");
		} else {
			if (StringUtils.isBlank(is_first)) {
				entity.getMap().put("e_date", fmt1.format(new Date()) + " 23:59:59");
				dynaBean.set("e_date", fmt1.format(new Date()));
			}

		}

		if (StringUtils.isNotBlank(startDate1)) {
			entity.getMap().put("startDate1", startDate1 + " 00:00:00");
		}

		if (StringUtils.isNotBlank(endDate1)) {
			entity.getMap().put("endDate1", endDate1 + " 23:59:59");
		}

		super.encodeCharacterForGetMethod(dynaBean, request);

		Pager pager = (Pager) dynaBean.get("pager");

		// TODO what is it ?
		Long recordCount = super.getFacade().getChannelDataImportService().getChannelDataImportCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<ChannelDataImport> entityList = super.getFacade().getChannelDataImportService()
				.getChannelDataImportPaginatedList(entity);

		if (entityList != null && entityList.size() > 0) {
			Double pagerCount = 0d;
			BigDecimal pagerMoney = new BigDecimal(0);
			for (ChannelDataImport t : entityList) {
				if (t.getColumn_12() != null) {
					pagerCount = pagerCount + Double.valueOf(t.getColumn_12());
				}
				pagerMoney = pagerMoney.add(t.getColumn_14() == null ? BigDecimal.valueOf(0f) : t.getColumn_14());
			}
			request.setAttribute("pagerCount", pagerCount);
			request.setAttribute("pagerMoney", pagerMoney);

			HashMap<BigDecimal, BigDecimal> map = super.getFacade().getChannelDataImportService()
					.getChannelDataImportAllCountAndAllMoney(entity);

			if (map.get("ALL_COUNT") != null && map.get("ALL_MONEY") != null) {
				request.setAttribute("allCount", map.get("ALL_COUNT").toString());
				request.setAttribute("allMoney", map.get("ALL_MONEY").toString());
			}
			if (map.get("ALL_COUNT") == null && map.get("ALL_MONEY") == null) {
				request.setAttribute("allCount", "0.0");
				request.setAttribute("allMoney", "0");
			}
		} else {
			request.setAttribute("pagerCount", 0);
			request.setAttribute("pagerMoney", 0);
			request.setAttribute("allCount", "0.0");
			request.setAttribute("allMoney", "0");
		}

		// excel导出处理
		if (StringUtils.isNotBlank(excel_all) && "1".equals(excel_all)) {

			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename=" + EncryptUtils.encodingFileName("R3订单明细")
					+ ".xls");

			entity.getRow().setCount(recordCount.intValue());
			List<ChannelDataImport> entityList1 = super.getFacade().getChannelDataImportService()
					.getChannelDataImportPaginatedList(entity);
			request.setAttribute("entityList1", entityList1);

			return mapping.findForward("view");
		}

		request.setAttribute("entityList", entityList);
		request.setAttribute("recordCount", recordCount);
		return mapping.findForward("list");
	}

	@Override
	public ActionForward toExcel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		super.HtmltoExcel(request, response);
		return null;
	}

	private static String theFirstDayOfCurrentMonth() {
		Calendar c = Calendar.getInstance();

		String y = String.valueOf(c.get(Calendar.YEAR));
		String m = "";
		String d = "";// the first day of a month
		if (c.get(Calendar.MONTH) + 1 >= 10) {
			m = String.valueOf(c.get(Calendar.MONTH) + 1);
		} else {
			m = "0" + String.valueOf(c.get(Calendar.MONTH) + 1);
		}
		d = "0" + c.getActualMinimum(Calendar.DAY_OF_MONTH);
		String start_day = y + "-" + m + "-" + d;
		return start_day;
	}
}
