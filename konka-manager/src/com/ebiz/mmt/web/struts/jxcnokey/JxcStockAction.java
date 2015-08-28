package com.ebiz.mmt.web.struts.jxcnokey;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.BasePdType;
import com.ebiz.mmt.domain.JxcPd;
import com.ebiz.mmt.domain.StdEntpMain;
import com.ebiz.mmt.domain.UserInfo;

/**
 * @author Qin,Yue
 * @version 2011-10-10
 */
public class JxcStockAction extends BaseJxcAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		// add_date_st = DateFormatUtils.format(new Date(), "yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_MONTH, 1);
		String add_date_st = DateFormatUtils.format(c.getTime(), "yyyy-MM-dd"); // 取当月第一天
		dynaBean.set("add_date_st", add_date_st);
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		String add_date_en = DateFormatUtils.format(calendar.getTime(), "yyyy-MM-dd");
		dynaBean.set("add_date_en", add_date_en);
		BasePdType basePdType = new BasePdType();
		basePdType.setDel_mark(new Short((short) 0));
		basePdType.setIs_model(new Short((short) 1));
		List<BasePdType> basePdTypeList = super.getFacade().getBasePdTypeService().getBasePdTypeList(basePdType);
		request.setAttribute("basePdTypeList", basePdTypeList);
		return mapping.findForward("list");
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		// Pager pager = (Pager) dynaBean.get("pager");
		String keySeq = (String) dynaBean.get("keySeq");
		String add_date_st = (String) dynaBean.get("add_date_st");
		String add_date_en = (String) dynaBean.get("add_date_en");
		String pd_type = (String) dynaBean.get("pd_type");
		String brand_name_like = (String) dynaBean.get("brand_name_like");
		String name_like = (String) dynaBean.get("name_like");
		String is_show_0_store = (String) dynaBean.get("is_show_0_store");

		request.setAttribute("not_validate_record", "true");
		UserInfo user = super.getUserInfoFromWebService(request, response);
		if (null == user) {
			return null;
		}
		Long shop_id = user.getEntp_shop().getShop_id();

		dynaBean.set("keySeq", keySeq);

		if (StringUtils.isBlank(add_date_st)) {
			// add_date_st = DateFormatUtils.format(new Date(), "yyyy-MM-dd");
			Calendar c = Calendar.getInstance();
			c.set(Calendar.DAY_OF_MONTH, 1);
			add_date_st = DateFormatUtils.format(c.getTime(), "yyyy-MM-dd"); // 取当月第一天
			dynaBean.set("add_date_st", add_date_st);
		}
		if (StringUtils.isBlank(add_date_en)) {
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
			add_date_en = DateFormatUtils.format(calendar.getTime(), "yyyy-MM-dd");
			dynaBean.set("add_date_en", add_date_en);
		}

		Double qcjc_count_total = 0d;
		Double qcjc_money_total = 0d;
		Double rcjh_count_total = 0d;
		Double rcjh_money_total = 0d;
		Double rcch_count_total = 0d;
		Double rcch_money_total = 0d;
		Double pc_count_total = 0d;
		Double pc_money_total = 0d;
		Double qmjc_count_total = 0d;
		Double qmjc_money_total = 0d;

		JxcPd entity = new JxcPd();
		super.copyProperties(entity, form);

		// entity.getMap().put("date_st", add_date_st);
		entity.getMap().put("shop_id", shop_id);
		entity.getMap().put("pd_type", pd_type);
		entity.getMap().put("brand_name_like", brand_name_like);
		entity.getMap().put("name_like", name_like);
		entity.getMap().put("rcch_date_st", add_date_st);
		entity.getMap().put("rcch_date_en", add_date_en);

		// Long recordCount =
		// getFacade().getJxcPdService().getStockRcjhAndRcchCount(entity);
		// pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		// entity.getRow().setFirst(pager.getFirstRow());
		// entity.getRow().setCount(pager.getRowCount());

		List<JxcPd> list = getFacade().getJxcPdService().getStockRcjhAndRcchList(entity);
		List<JxcPd> jxcPdList = new ArrayList<JxcPd>();
		for (JxcPd jxcPd : list) {
			Long pd_id = jxcPd.getId();
			// 期初结存
			JxcPd qcjc_pd = new JxcPd();
			qcjc_pd.getMap().put("pd_id", pd_id);
			qcjc_pd.getMap().put("qcjc_date", add_date_st);
			qcjc_pd = getFacade().getJxcPdService().getQcjcForStock(qcjc_pd);
			BigDecimal qcjc_count = (BigDecimal) qcjc_pd.getMap().get("qcjc_count");
			BigDecimal qcjc_money = (BigDecimal) qcjc_pd.getMap().get("qcjc_money");
			if (qcjc_count == null) {
				qcjc_count = new BigDecimal("0");
			}
			if (qcjc_money == null) {
				qcjc_money = new BigDecimal("0");
			}
			jxcPd.setQcjc_pd(qcjc_pd);

			// 日常进货
			BigDecimal rcjh_count = (BigDecimal) jxcPd.getMap().get("jh_count");
			BigDecimal rcjh_sum_money = (BigDecimal) jxcPd.getMap().get("jh_sum_money");
			if (rcjh_count == null) {
				rcjh_count = new BigDecimal("0");
			}
			if (rcjh_sum_money == null) {
				rcjh_sum_money = new BigDecimal("0");
			}
			// 日常出货
			BigDecimal rcch_count = (BigDecimal) jxcPd.getMap().get("ch_count");
			BigDecimal rcch_sum_money = (BigDecimal) jxcPd.getMap().get("ch_sum_money");
			if (rcch_count == null) {
				rcch_count = new BigDecimal("0");
			}
			if (rcch_sum_money == null) {
				rcch_sum_money = new BigDecimal("0");
			}
			// 盘存、盘亏
			BigDecimal pc_count = (BigDecimal) jxcPd.getMap().get("pc_count");
			BigDecimal pc_sum_money = (BigDecimal) jxcPd.getMap().get("pc_sum_money");
			if (pc_count == null) {
				pc_count = new BigDecimal("0");
			}
			if (pc_sum_money == null) {
				pc_sum_money = new BigDecimal("0");
			}

			// 期末结存
			Long qmjc_count = Long.valueOf(qcjc_count.add(rcjh_count).add(pc_count).subtract(rcch_count).toString());
			Double qmjc_money = qcjc_money.doubleValue() + rcjh_sum_money.doubleValue() + pc_sum_money.doubleValue()
					- rcch_sum_money.doubleValue();
			jxcPd.setQmjc_count(qmjc_count.doubleValue());
			jxcPd.setQmjc_money(qmjc_money);

			// 合计期初结存
			qcjc_count_total += qcjc_count.doubleValue();
			qcjc_money_total += qcjc_money.doubleValue();

			// 合计日常进货
			rcjh_count_total += rcjh_count.doubleValue();
			rcjh_money_total += rcjh_sum_money.doubleValue();

			// 合计日常出货
			rcch_count_total += rcch_count.doubleValue();
			rcch_money_total += rcch_sum_money.doubleValue();

			// 合计盘存盘亏
			pc_count_total += pc_count.doubleValue();
			pc_money_total += pc_sum_money.doubleValue();

			// 合计期末结存
			qmjc_count_total += qmjc_count;
			qmjc_money_total += qmjc_money;

			if (!StringUtils.isNotBlank(is_show_0_store)) {// 显示零库存产品
				if (qmjc_count.doubleValue() != 0) {
					jxcPdList.add(jxcPd);
				}
			} else {
				jxcPdList.add(jxcPd);
			}
		}
		BasePdType basePdType = new BasePdType();
		basePdType.setDel_mark(new Short((short) 0));
		basePdType.setIs_model(new Short((short) 1));
		List<BasePdType> basePdTypeList = super.getFacade().getBasePdTypeService().getBasePdTypeList(basePdType);
		request.setAttribute("basePdTypeList", basePdTypeList);

		dynaBean.set("qcjc_count_total", qcjc_count_total);
		dynaBean.set("qcjc_money_total", qcjc_money_total);
		dynaBean.set("rcjh_count_total", rcjh_count_total);
		dynaBean.set("rcjh_money_total", rcjh_money_total);
		dynaBean.set("rcch_count_total", rcch_count_total);
		dynaBean.set("rcch_money_total", rcch_money_total);
		dynaBean.set("pc_count_total", pc_count_total);
		dynaBean.set("pc_money_total", pc_money_total);
		dynaBean.set("qmjc_count_total", qmjc_count_total);
		dynaBean.set("qmjc_money_total", qmjc_money_total);

		request.setAttribute("entityList", jxcPdList);
		return mapping.findForward("list");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String keySeq = (String) dynaBean.get("keySeq");
		String id = (String) dynaBean.get("id");
		// String brand_name = (String) dynaBean.get("brand_name");
		// String pd_type_name = (String) dynaBean.get("pd_type_name");
		// String name = (String) dynaBean.get("name");
		String add_date_st = (String) dynaBean.get("add_date_st");
		String add_date_en = (String) dynaBean.get("add_date_en");

		request.setAttribute("not_validate_record", "true");
		UserInfo user = super.getUserInfoFromWebService(request, response);
		if (null == user) {
			return null;
		}
		Long shop_id = user.getEntp_shop().getShop_id();

		dynaBean.set("keySeq", keySeq);

		JxcPd entity = new JxcPd();
		entity.setId(Long.valueOf(id));
		entity = getFacade().getJxcPdService().getJxcPd(entity);
		if (null == entity) {
			return null;
		}

		// 查期初数量和成本价：

		JxcPd pd = new JxcPd();
		pd.setId(Long.valueOf(id));
		pd = getFacade().getJxcPdService().getJxcPd(pd);
		if (null == pd) {
			renderJavaScript(response, "alert('该产品不存在！请您先在产品库添加该产品');history.back();");
			return null;
		}
		JxcPd qcjc_pd = new JxcPd();
		qcjc_pd.getMap().put("pd_id", id);
		qcjc_pd.getMap().put("qcjc_date", add_date_st);
		qcjc_pd = getFacade().getJxcPdService().getQcjcForStock(qcjc_pd);
		BigDecimal qcjc_count = (BigDecimal) qcjc_pd.getMap().get("qcjc_count");
		BigDecimal qcjc_money = (BigDecimal) qcjc_pd.getMap().get("qcjc_money");
		BigDecimal init_cost_price = new BigDecimal(0); // 期初单价
		if (qcjc_count.intValue() != 0) {
			init_cost_price = qcjc_money.divide(qcjc_count);
		}
		Long init_count = qcjc_count.longValue(); // 期初库存
		Double money = qcjc_money.doubleValue();
		dynaBean.set("init_count", qcjc_count);
		dynaBean.set("cb", init_cost_price);
		dynaBean.set("qc_money", money);

		// 查明细BEGIN
		JxcPd jxc_Pd = new JxcPd();
		jxc_Pd.getMap().put("pd_id", id);
		jxc_Pd.getMap().put("shop_id", shop_id);
		jxc_Pd.getMap().put("date_st", add_date_st);
		jxc_Pd.getMap().put("date_en", add_date_en);
		List<JxcPd> stockList = getFacade().getJxcPdService().selectStockDetailsListForPc(jxc_Pd);
		Double qmjc_count = Double.valueOf(init_count);
		Double qmjc_price;
		Double qmjc_money;
		Double q_count = Double.valueOf(init_count);

		if (null != stockList && stockList.size() > 0) {
			Double rcjh_count_total = 0d;
			Double rcjh_money_total = 0d;
			Double rcch_count_total = 0d;
			Double rcch_money_total = 0d;
			Double pc_count_total = 0d;
			Double pc_money_total = 0d;
			Double qmjc_count_total = 0d;
			Double qmjc_money_total = 0d;
			for (JxcPd jxcPd : stockList) {
				BigDecimal type = (BigDecimal) jxcPd.getMap().get("type");
				if (type.intValue() == 0) { // 进货
					BigDecimal price = (BigDecimal) jxcPd.getMap().get("price");
					BigDecimal count = (BigDecimal) jxcPd.getMap().get("count");
					Double rcjh_money = price.doubleValue() * count.doubleValue(); // 日常进货金额=单价*数量
					qmjc_count = qmjc_count + count.doubleValue();

					rcjh_count_total += count.doubleValue();
					rcjh_money_total += rcjh_money;
					dynaBean.set("rcjh_count_total", rcjh_count_total);
					dynaBean.set("rcjh_money_total", rcjh_money_total);

					// 期末结存单价
					money = money + rcjh_money;
					q_count = q_count + count.doubleValue();
					qmjc_price = money / q_count;
					qmjc_money = qmjc_price * qmjc_count;

					jxcPd.setRcjh_money(rcjh_money);
					jxcPd.setQmjc_count(qmjc_count);
					jxcPd.setQmjc_price(qmjc_price);
					jxcPd.setQmjc_money(qmjc_money);
				} else if (type.intValue() == 1 || type.intValue() == 3) { // 出货、分销
					BigDecimal cost_price = (BigDecimal) jxcPd.getMap().get("cost_price");
					BigDecimal count = (BigDecimal) jxcPd.getMap().get("count");
					Double rcch_money = cost_price.doubleValue() * count.doubleValue(); // 日常出货（成本）金额=成本单价*数量
					qmjc_count = qmjc_count - count.doubleValue();
					money = money - rcch_money;
					q_count = q_count - count.doubleValue();

					rcch_count_total += count.doubleValue();
					rcch_money_total += rcch_money;
					dynaBean.set("rcch_count_total", rcch_count_total);
					dynaBean.set("rcch_money_total", rcch_money_total);

					// 期末结存单价
					qmjc_price = cost_price.doubleValue();
					qmjc_money = qmjc_price * qmjc_count;

					jxcPd.setRcch_money(rcch_money);
					jxcPd.setQmjc_count(qmjc_count);
					jxcPd.setQmjc_price(qmjc_price);
					jxcPd.setQmjc_money(qmjc_money);
				} else if (type.intValue() == 2) {// 盘存
					BigDecimal cost_price = (BigDecimal) jxcPd.getMap().get("cost_price");
					BigDecimal count = (BigDecimal) jxcPd.getMap().get("count");
					Double pc_money = cost_price.doubleValue() * count.doubleValue(); // 盘存金额（成本）金额=成本单价*数量
					qmjc_count = qmjc_count + count.doubleValue();
					money = money + pc_money;
					q_count = q_count + count.doubleValue();

					pc_count_total += count.doubleValue();
					pc_money_total += pc_money;
					dynaBean.set("pc_count_total", pc_count_total);
					dynaBean.set("pc_money_total", pc_money_total);

					// 期末结存单价
					qmjc_price = cost_price.doubleValue();
					qmjc_money = qmjc_price * qmjc_count;

					jxcPd.setPc_money(pc_money);
					jxcPd.setQmjc_count(qmjc_count);
					jxcPd.setQmjc_price(qmjc_price);
					jxcPd.setQmjc_money(qmjc_money);
				}
			}
			Integer i = stockList.size();
			qmjc_count_total = stockList.get(i - 1).getQmjc_count();
			qmjc_money_total = stockList.get(i - 1).getQmjc_money();
			dynaBean.set("qmjc_count_total", qmjc_count_total);
			dynaBean.set("qmjc_money_total", qmjc_money_total);
		}

		request.setAttribute("stockList", stockList);
		// 查明细END

		JxcPd jxcPd = new JxcPd();
		jxcPd.getMap().put("pd_id", id);
		jxcPd.getMap().put("shop_id", shop_id);
		jxcPd.getMap().put("date_st", add_date_st);
		jxcPd.getMap().put("date_en", add_date_en);
		List<JxcPd> JhStorkList = getFacade().getJxcPdService().getSskcDetailsForStork(jxcPd);
		request.setAttribute("JhStorkList", JhStorkList);

		JxcPd jPd = new JxcPd();
		jPd.getMap().put("pd_id", id);
		jPd.getMap().put("shop_id", shop_id);
		jPd.getMap().put("date_st", add_date_st);
		jPd.getMap().put("date_en", add_date_en);
		List<JxcPd> XsStorkList = getFacade().getJxcPdService().getSsxsDetailsForStork(jPd);
		request.setAttribute("XsStorkList", XsStorkList);

		dynaBean.set("brand_name", entity.getBrand_name());
		dynaBean.set("pd_type_name", entity.getPd_type_name());
		dynaBean.set("name", entity.getName());
		return mapping.findForward("view");
	}

	public ActionForward toExcel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		response.setCharacterEncoding("utf-8");
		String name = request.getParameter("hiddenName");
		String html = request.getParameter("hiddenHtml");
		try {
			response.setContentType("application/vnd.ms-excel");

			response.addHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(name, "UTF-8")
					+ ".xls\"");
			// response.setContentType("application/vnd.ms-word");
			// response.addHeader("Content-Disposition",
			// "attachment; filename=\"" + URLEncoder.encode(name, "UTF-8")
			// + ".doc\"");

			PrintWriter out = response.getWriter();
			out
					.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">");
			out.println("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
			out.println("<head>");
			out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
			out.println("<meta http-equiv=\"MSThemeCompatible\" content=\"no\" />");
			out.println("<meta name=\"MSSmartTagsPreventParsing\" content=\"true\" />");
			out.println("<title>" + name + "</title>");
			out.println("</head>");

			html = html.replace("border=0", "border=1");
			// html = html.replace("<A href=\"[^>]*?\">([^<]*?)<\/A>", "$1");

			out.println(html);
			out.println("</body>");
			out.println("</html>");
			out.println("<body>");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public ActionForward toExcelForList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws RowsExceededException, WriteException, IOException {
		DynaBean dynaBean = (DynaBean) form;
		// String keySeq = (String) dynaBean.get("keySeq");

		request.setAttribute("not_validate_record", "true");
		UserInfo user = super.getUserInfoFromWebService(request, response);
		if (null == user) {
			return null;
		}
		Long shop_id = user.getEntp_shop().getShop_id();

		String pd_type = (String) dynaBean.get("pd_type");
		String add_date_lt = (String) dynaBean.get("add_date_lt");
		String add_date_gt = (String) dynaBean.get("add_date_gt");
		String brand_name_like = (String) dynaBean.get("brand_name_like");
		String name_like = (String) dynaBean.get("name_like");

		StdEntpMain stdEntpMain = super.getStdEntpMainByShopId(shop_id);
		String userName = stdEntpMain.getEntp_name();
		String tel = stdEntpMain.getTel();

		JxcPd entity = new JxcPd();

		// entity.getMap().put("date_st", add_date_st);
		entity.getMap().put("shop_id", shop_id);
		entity.getMap().put("pd_type", pd_type);
		entity.getMap().put("brand_name_like", brand_name_like);
		entity.getMap().put("name_like", name_like);
		entity.getMap().put("rcch_date_st", add_date_lt);
		entity.getMap().put("rcch_date_en", add_date_gt);
		List<JxcPd> list = getFacade().getJxcPdService().getStockRcjhAndRcchList(entity);

		List<JxcPd> jxcPdList = new ArrayList<JxcPd>();
		for (JxcPd jxcPd : list) {
			Long pd_id = jxcPd.getId();
			// 期初结存
			JxcPd qcjc_pd = new JxcPd();
			qcjc_pd.getMap().put("pd_id", pd_id);
			qcjc_pd.getMap().put("qcjc_date", add_date_lt);
			qcjc_pd = getFacade().getJxcPdService().getQcjcForStock(qcjc_pd);
			BigDecimal qcjc_count = (BigDecimal) qcjc_pd.getMap().get("qcjc_count");
			BigDecimal qcjc_money = (BigDecimal) qcjc_pd.getMap().get("qcjc_money");
			if (qcjc_count == null) {
				qcjc_count = new BigDecimal("0");
			}
			if (qcjc_money == null) {
				qcjc_money = new BigDecimal("0");
			}
			jxcPd.setQcjc_pd(qcjc_pd);

			// 日常进货
			BigDecimal rcjh_count = (BigDecimal) jxcPd.getMap().get("jh_count");
			BigDecimal rcjh_sum_money = (BigDecimal) jxcPd.getMap().get("jh_sum_money");
			if (rcjh_count == null) {
				rcjh_count = new BigDecimal("0");
			}
			if (rcjh_sum_money == null) {
				rcjh_sum_money = new BigDecimal("0");
			}
			// 日常出货
			BigDecimal rcch_count = (BigDecimal) jxcPd.getMap().get("ch_count");
			BigDecimal rcch_sum_money = (BigDecimal) jxcPd.getMap().get("ch_sum_money");
			if (rcch_count == null) {
				rcch_count = new BigDecimal("0");
			}
			if (rcch_sum_money == null) {
				rcch_sum_money = new BigDecimal("0");
			}

			// 期末结存
			Long qmjc_count = Long.valueOf(qcjc_count.add(rcjh_count).subtract(rcch_count).toString());
			Double qmjc_money = qcjc_money.doubleValue() + rcjh_sum_money.doubleValue() - rcch_sum_money.doubleValue();
			jxcPd.setQmjc_count(qmjc_count.doubleValue());
			jxcPd.setQmjc_money(qmjc_money);
			if (qmjc_count.intValue() != 0) {
				jxcPdList.add(jxcPd);
			}
		}
		logger.info("===userName:{}", userName);
		createExcelFileForList(request, response, userName, tel, jxcPdList, add_date_gt, add_date_lt);
		return null;
	}

	private void createExcelFileForList(HttpServletRequest request, HttpServletResponse response, String userName,
			String tel, List<JxcPd> list, String add_date_gt, String add_date_lt) throws RowsExceededException,
			WriteException, IOException {
		if (list == null || list.size() < 0) {
			return;
		}

		WritableWorkbook wwb = null;
		String sFileName = DateFormatUtils.format(new Date(), "yyyyMMddhhmmss") + ".xls";
		// 获取系统实际路径
		String SystemPath = getServlet().getServletContext().getRealPath(String.valueOf(File.separatorChar));
		File fDownloadExcel = new File(SystemPath + "files/download_excel/");
		if (!fDownloadExcel.exists()) {
			FileUtils.forceMkdir(fDownloadExcel);
		}
		// 首先要使用Workbook类的工厂方法创建一个可写入的工作薄(Workbook)对象
		wwb = Workbook.createWorkbook(new File(SystemPath + "files/download_excel/" + sFileName));

		if (wwb != null) {
			WritableCellFormat cellFormatRight = new WritableCellFormat();
			cellFormatRight.setAlignment(Alignment.RIGHT);
			WritableCellFormat cellFormatCenter = new WritableCellFormat();
			cellFormatCenter.setAlignment(Alignment.CENTRE);
			WritableCellFormat cellFormatLeft = new WritableCellFormat();
			cellFormatLeft.setAlignment(Alignment.LEFT);
			WritableCellFormat wc = new jxl.write.WritableCellFormat(new jxl.write.NumberFormat("0"));
			WritableCellFormat wcf = new jxl.write.WritableCellFormat(new jxl.write.NumberFormat("￥0.00"));
			// 创建一个可写入的工作表
			// Label(column,row,content)其中column代表单元格的第column+1列，第row+1行,
			// 单元格的内容是content
			WritableSheet sheet = wwb.createSheet(userName.concat("库存成本帐"), 0);
			int row = 0;// 行
			// int column = 0;// 列

			sheet.mergeCells(0, row, 11, row);// 合并单元格(左列，左行，右列，右行)
			// 合并第row行，从第0列到第7列
			if (StringUtils.isBlank(tel)) {
				tel = "";
			}
			String info0 = "联系人：".concat(userName).concat("  联系电话：").concat(tel);
			Label label0 = new Label(0, row, info0);
			label0.setCellFormat(cellFormatRight);
			sheet.addCell(label0);

			row++;
			sheet.mergeCells(0, row, 11, row);
			Label label1 = new Label(0, row, "库存成本帐");
			label1.setCellFormat(cellFormatCenter);
			sheet.addCell(label1);

			row++;
			sheet.mergeCells(0, row, 11, row);
			String s = queryDate(add_date_lt, add_date_gt);
			label1 = new Label(0, row, s);
			label1.setCellFormat(cellFormatRight);
			sheet.addCell(label1);

			row++;
			sheet.mergeCells(0, row, 0, row + 1);
			Label label2 = new Label(0, row, "货品代码");
			label2.setCellFormat(cellFormatCenter);
			sheet.addCell(label2);
			sheet.mergeCells(1, row, 1, row + 1);
			Label label3 = new Label(1, row, "品名规格");
			label3.setCellFormat(cellFormatCenter);
			sheet.addCell(label3);
			sheet.mergeCells(2, row, 2, row + 1);
			Label label4 = new Label(2, row, "品牌");
			label4.setCellFormat(cellFormatCenter);
			sheet.addCell(label4);
			sheet.mergeCells(3, row, 3, row + 1);
			Label label5 = new Label(3, row, "单位");
			label5.setCellFormat(cellFormatCenter);
			sheet.addCell(label5);
			sheet.mergeCells(4, row, 5, row);
			Label label6 = new Label(4, row, "期初结存");
			label6.setCellFormat(cellFormatCenter);
			sheet.addCell(label6);
			sheet.mergeCells(6, row, 7, row);
			Label label7 = new Label(6, row, "日常进货");
			label7.setCellFormat(cellFormatCenter);
			sheet.addCell(label7);
			sheet.mergeCells(8, row, 9, row);
			Label label8 = new Label(8, row, "日常出货（成本）");
			label8.setCellFormat(cellFormatCenter);
			sheet.addCell(label8);
			sheet.mergeCells(10, row, 11, row);
			Label label9 = new Label(10, row, "期末结存");
			label9.setCellFormat(cellFormatCenter);
			sheet.addCell(label9);

			row++;
			sheet.addCell(new Label(4, row, "数量"));
			sheet.addCell(new Label(5, row, "金额"));
			sheet.addCell(new Label(6, row, "数量"));
			sheet.addCell(new Label(7, row, "金额"));
			sheet.addCell(new Label(8, row, "数量"));
			sheet.addCell(new Label(9, row, "金额"));
			sheet.addCell(new Label(10, row, "数量"));
			sheet.addCell(new Label(11, row, "金额"));

			row++;
			Double qcjc_count_total = 0d;
			Double qcjc_money_total = 0d;
			Double rcjh_count_total = 0d;
			Double rcjh_money_total = 0d;
			Double rcch_count_total = 0d;
			Double rcch_money_total = 0d;
			Double qmjc_count_total = 0d;
			Double qmjc_money_total = 0d;
			for (int i = 0; i < list.size(); i++) {
				JxcPd jxcPd = list.get(i);
				String name = jxcPd.getName();
				sheet.addCell(new Label(0, row, name));
				String pd_type_name = jxcPd.getPd_type_name();
				sheet.addCell(new Label(1, row, pd_type_name));
				String brand_name = jxcPd.getBrand_name();
				sheet.addCell(new Label(2, row, brand_name));
				String unit = jxcPd.getUnit();
				if (StringUtils.isBlank(unit)) {
					sheet.addCell(new Label(3, row, ""));
				} else {
					sheet.addCell(new Label(3, row, unit));
				}

				// 期初结存
				JxcPd qcjc_pd = jxcPd.getQcjc_pd();
				BigDecimal qcjc_count = (BigDecimal) qcjc_pd.getMap().get("qcjc_count");
				BigDecimal qcjc_money = (BigDecimal) qcjc_pd.getMap().get("qcjc_money");
				if (qcjc_count == null) {
					qcjc_count = new BigDecimal("0");
				}
				if (qcjc_money == null) {
					qcjc_money = new BigDecimal("0");
				}
				qcjc_count_total += qcjc_count.doubleValue();
				qcjc_money_total += qcjc_money.doubleValue();
				sheet.addCell(new jxl.write.Number(4, row, qcjc_count.doubleValue(), wc));
				sheet.addCell(new jxl.write.Number(5, row, qcjc_money.doubleValue(), wcf));

				// 日常进货
				BigDecimal rcjh_count = (BigDecimal) jxcPd.getMap().get("jh_count");
				BigDecimal rcjh_sum_money = (BigDecimal) jxcPd.getMap().get("jh_sum_money");
				if (rcjh_count == null) {
					rcjh_count = new BigDecimal("0");
				}
				if (rcjh_sum_money == null) {
					rcjh_sum_money = new BigDecimal("0");
				}
				rcjh_count_total += rcjh_count.doubleValue();
				rcjh_money_total += rcjh_sum_money.doubleValue();
				sheet.addCell(new jxl.write.Number(6, row, rcjh_count.doubleValue(), wc));
				sheet.addCell(new jxl.write.Number(7, row, rcjh_sum_money.doubleValue(), wcf));

				// 日常出货
				BigDecimal rcch_count = (BigDecimal) jxcPd.getMap().get("ch_count");
				BigDecimal rcch_sum_money = (BigDecimal) jxcPd.getMap().get("ch_sum_money");
				if (rcch_count == null) {
					rcch_count = new BigDecimal("0");
				}
				if (rcch_sum_money == null) {
					rcch_sum_money = new BigDecimal("0");
				}
				rcch_count_total += rcch_count.doubleValue();
				rcch_money_total += rcch_sum_money.doubleValue();
				sheet.addCell(new jxl.write.Number(8, row, rcch_count.doubleValue(), wc));
				sheet.addCell(new jxl.write.Number(9, row, rcch_sum_money.doubleValue(), wcf));

				// 期末结存

				Long qmjc_count = jxcPd.getQmjc_count().longValue();
				Double qmjc_money = jxcPd.getQmjc_money().doubleValue();
				qmjc_count_total += qmjc_count;
				qmjc_money_total += qmjc_money;
				sheet.addCell(new jxl.write.Number(10, row, qmjc_count.doubleValue(), wc));
				sheet.addCell(new jxl.write.Number(11, row, qmjc_money.doubleValue(), wcf));

				row++;
			}

			sheet.mergeCells(0, row, 3, row);
			sheet.addCell(new Label(0, row, "合计"));
			sheet.addCell(new jxl.write.Number(4, row, qcjc_count_total.doubleValue(), wc));
			sheet.addCell(new jxl.write.Number(5, row, qcjc_money_total.doubleValue(), wcf));
			sheet.addCell(new jxl.write.Number(6, row, rcjh_count_total.doubleValue(), wc));
			sheet.addCell(new jxl.write.Number(7, row, rcjh_money_total.doubleValue(), wcf));
			sheet.addCell(new jxl.write.Number(8, row, rcch_count_total.doubleValue(), wc));
			sheet.addCell(new jxl.write.Number(9, row, rcch_money_total.doubleValue(), wcf));
			sheet.addCell(new jxl.write.Number(10, row, qmjc_count_total.doubleValue(), wc));
			sheet.addCell(new jxl.write.Number(11, row, qmjc_money_total.doubleValue(), wcf));

			row++;
			sheet.mergeCells(0, row, 11, row);
			String info = "签收人：";
			Label label = new Label(0, row, info);
			label.setCellFormat(cellFormatCenter);
			sheet.addCell(label);
			// 从内存中写入文件中
			wwb.write();
			// 关闭资源，释放内存
			wwb.close();
		}
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename=" + sFileName);
		FileInputStream fileInputStream = new FileInputStream(SystemPath + "files/download_excel/" + sFileName);
		OutputStream out = response.getOutputStream();
		int i = 0;
		while ((i = fileInputStream.read()) != -1) {
			out.write(i);
		}
		fileInputStream.close();
		out.close();
	}

	public ActionForward toExcelForDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws RowsExceededException, WriteException, IOException {
		DynaBean dynaBean = (DynaBean) form;
		// String keySeq = (String) dynaBean.get("keySeq");

		request.setAttribute("not_validate_record", "true");
		UserInfo user = super.getUserInfoFromWebService(request, response);
		if (null == user) {
			return null;
		}
		Long shop_id = user.getEntp_shop().getShop_id();

		String id = (String) dynaBean.get("id");
		String add_date_lt = (String) dynaBean.get("add_date_lt");
		String add_date_gt = (String) dynaBean.get("add_date_gt");
		StdEntpMain stdEntpMain = super.getStdEntpMainByShopId(shop_id);
		String userName = stdEntpMain.getEntp_name();
		String tel = stdEntpMain.getTel();

		// 查期初数量和成本价：
		JxcPd pd = new JxcPd();
		pd.setId(Long.valueOf(id));
		pd = getFacade().getJxcPdService().getJxcPd(pd);
		if (null == pd) {
			renderJavaScript(response, "alert('该产品不存在！请您先在产品库添加该产品');history.back();");
			return null;
		}
		String pd_type_name = pd.getPd_type_name();
		String brand_name = pd.getBrand_name();
		String name = pd.getName();

		JxcPd qcjc_pd = new JxcPd();
		qcjc_pd.getMap().put("pd_id", id);
		qcjc_pd.getMap().put("qcjc_date", add_date_lt);
		qcjc_pd = getFacade().getJxcPdService().getQcjcForStock(qcjc_pd);
		BigDecimal qcjc_count = (BigDecimal) qcjc_pd.getMap().get("qcjc_count");
		BigDecimal qcjc_money = (BigDecimal) qcjc_pd.getMap().get("qcjc_money");
		BigDecimal init_cost_price = qcjc_money.divide(qcjc_count); // 期初单价
		Long init_count = qcjc_count.longValue(); // 期初库存
		Double money = qcjc_money.doubleValue();

		// 查明细BEGIN
		JxcPd jxc_Pd = new JxcPd();
		jxc_Pd.getMap().put("pd_id", id);
		jxc_Pd.getMap().put("shop_id", shop_id);
		jxc_Pd.getMap().put("date_st", add_date_lt);
		jxc_Pd.getMap().put("date_en", add_date_gt);
		List<JxcPd> stockList = getFacade().getJxcPdService().getStockDetailsList(jxc_Pd);
		Double qmjc_count = Double.valueOf(init_count);
		Double q_count = Double.valueOf(init_count);

		createExcelFileForDetails(request, response, userName, tel, stockList, add_date_gt, add_date_lt, init_count,
				init_cost_price, money, qmjc_count, q_count, pd_type_name, brand_name, name);
		return null;
	}

	private void createExcelFileForDetails(HttpServletRequest request, HttpServletResponse response, String userName,
			String tel, List<JxcPd> stockList, String add_date_gt, String add_date_lt, Long init_count,
			BigDecimal init_cost_price, Double money, Double qmjc_count, Double q_count, String pd_type_name,
			String brand_name, String name) throws RowsExceededException, WriteException, IOException {
		if (stockList == null || stockList.size() < 0) {
			return;
		}
		WritableWorkbook wwb = null;
		String sFileName = DateFormatUtils.format(new Date(), "yyyyMMddhhmmss") + ".xls";
		// 获取系统实际路径
		String SystemPath = getServlet().getServletContext().getRealPath(String.valueOf(File.separatorChar));
		File fDownloadExcel = new File(SystemPath + "files/download_excel/");
		if (!fDownloadExcel.exists()) {
			FileUtils.forceMkdir(fDownloadExcel);
		}
		// 首先要使用Workbook类的工厂方法创建一个可写入的工作薄(Workbook)对象
		wwb = Workbook.createWorkbook(new File(SystemPath + "files/download_excel/" + sFileName));

		if (wwb != null) {
			WritableCellFormat cellFormatRight = new WritableCellFormat();
			cellFormatRight.setAlignment(Alignment.RIGHT);
			WritableCellFormat cellFormatCenter = new WritableCellFormat();
			cellFormatCenter.setAlignment(Alignment.CENTRE);
			WritableCellFormat cellFormatLeft = new WritableCellFormat();
			cellFormatLeft.setAlignment(Alignment.LEFT);
			WritableCellFormat wc = new jxl.write.WritableCellFormat(new jxl.write.NumberFormat("0"));
			WritableCellFormat wcf = new jxl.write.WritableCellFormat(new jxl.write.NumberFormat("￥0.00"));
			// 创建一个可写入的工作表
			// Label(column,row,content)其中column代表单元格的第column+1列，第row+1行,
			// 单元格的内容是content
			WritableSheet sheet = wwb.createSheet(userName.concat("库存明细帐"), 0);
			int row = 0;// 行
			// int column = 0;// 列

			sheet.mergeCells(0, row, 11, row);// 合并单元格(左列，左行，右列，右行)
			// 合并第row行，从第0列到第7列
			String info0 = "联系人：".concat(userName).concat("  联系电话：").concat(tel);
			Label label0 = new Label(0, row, info0);
			label0.setCellFormat(cellFormatRight);
			sheet.addCell(label0);

			row++;
			sheet.mergeCells(0, row, 11, row);
			Label label1 = new Label(0, row, "库存明细帐");
			label1.setCellFormat(cellFormatCenter);
			sheet.addCell(label1);

			row++;
			logger.info("add_date_lt:{}", add_date_lt);
			logger.info("add_date_gt:{}", add_date_gt);
			sheet.mergeCells(0, row, 11, row);
			String s = "品名规格：".concat(brand_name).concat(pd_type_name).concat(name).concat(
					queryDate(add_date_lt, add_date_gt));
			label1 = new Label(0, row, s);
			label1.setCellFormat(cellFormatLeft);
			sheet.addCell(label1);

			row++;
			sheet.mergeCells(0, row, 0, row + 1);
			Label label2 = new Label(0, row, "业务类别");
			label2.setCellFormat(cellFormatCenter);
			sheet.addCell(label2);
			sheet.mergeCells(1, row, 1, row + 1);
			Label label3 = new Label(1, row, "业务日期");
			label3.setCellFormat(cellFormatCenter);
			sheet.addCell(label3);
			sheet.mergeCells(2, row, 2, row + 1);
			Label label4 = new Label(2, row, "交易单位");
			label4.setCellFormat(cellFormatCenter);
			sheet.addCell(label4);
			sheet.mergeCells(3, row, 5, row);
			Label label5 = new Label(3, row, "日常进货");
			label5.setCellFormat(cellFormatCenter);
			sheet.addCell(label5);
			sheet.mergeCells(6, row, 8, row);
			Label label6 = new Label(6, row, "日常出货（成本）");
			label6.setCellFormat(cellFormatCenter);
			sheet.addCell(label6);
			sheet.mergeCells(9, row, 11, row);
			Label label7 = new Label(9, row, "期末结存（成本）");
			label7.setCellFormat(cellFormatCenter);
			sheet.addCell(label7);

			row++;
			sheet.addCell(new Label(3, row, "数量"));
			sheet.addCell(new Label(4, row, "单价"));
			sheet.addCell(new Label(5, row, "金额"));
			sheet.addCell(new Label(6, row, "数量"));
			sheet.addCell(new Label(7, row, "单价"));
			sheet.addCell(new Label(8, row, "金额"));
			sheet.addCell(new Label(9, row, "数量"));
			sheet.addCell(new Label(10, row, "单价"));
			sheet.addCell(new Label(11, row, "金额"));

			row++;
			sheet.addCell(new Label(0, row, "期初"));
			sheet.addCell(new jxl.write.Number(9, row, init_count.doubleValue(), wc));
			sheet.addCell(new jxl.write.Number(10, row, init_cost_price.doubleValue(), wcf));
			sheet.addCell(new jxl.write.Number(11, row, money.doubleValue(), wcf));

			row++;
			// 填充产品价格
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Double qmjc_price;
			Double qmjc_money;
			Double rcjh_count_total = 0d;
			Double rcjh_money_total = 0d;
			Double rcch_count_total = 0d;
			Double rcch_money_total = 0d;
			Double qmjc_count_total = 0d;
			Double qmjc_money_total = 0d;
			for (int i = 0; i < stockList.size(); i++) {
				// column = 0;
				JxcPd jxcPd = stockList.get(i);
				BigDecimal type = (BigDecimal) jxcPd.getMap().get("type");
				if (type.intValue() == 0) { // 进货
					BigDecimal price = (BigDecimal) jxcPd.getMap().get("price");
					BigDecimal count = (BigDecimal) jxcPd.getMap().get("count");
					Double rcjh_money = price.doubleValue() * count.doubleValue(); // 日常进货金额=单价*数量
					qmjc_count = qmjc_count + count.doubleValue();

					rcjh_count_total += count.doubleValue();
					rcjh_money_total += rcjh_money;

					// 期末结存单价
					money = money + rcjh_money;
					q_count = q_count + count.doubleValue();
					qmjc_price = money / q_count;
					qmjc_money = qmjc_price * qmjc_count;

					qmjc_count_total = qmjc_count;
					qmjc_money_total = qmjc_money;

					sheet.addCell(new Label(0, row, "进货"));
					Date biz_date = (Date) jxcPd.getMap().get("biz_date");
					sheet.addCell(new Label(1, row, format.format(biz_date)));
					sheet.addCell(new Label(2, row, (String) jxcPd.getMap().get("name")));
					sheet.addCell(new jxl.write.Number(3, row, count.doubleValue(), wc));
					sheet.addCell(new jxl.write.Number(4, row, price.doubleValue(), wcf));
					sheet.addCell(new jxl.write.Number(5, row, rcjh_money.doubleValue(), wcf));
					sheet.addCell(new jxl.write.Number(9, row, qmjc_count.doubleValue(), wc));
					sheet.addCell(new jxl.write.Number(10, row, qmjc_price.doubleValue(), wcf));
					sheet.addCell(new jxl.write.Number(11, row, qmjc_money.doubleValue(), wcf));
					row++;
				} else if (type.intValue() == 1) { // 出货
					BigDecimal cost_price = (BigDecimal) jxcPd.getMap().get("cost_price");
					BigDecimal count = (BigDecimal) jxcPd.getMap().get("count");
					Double rcch_money = cost_price.doubleValue() * count.doubleValue(); // 日常出货（成本）金额=成本单价*数量
					qmjc_count = qmjc_count - count.doubleValue();
					money = money - rcch_money;
					q_count = q_count - count.doubleValue();

					rcch_count_total += count.doubleValue();
					rcch_money_total += rcch_money;

					// 期末结存单价
					qmjc_price = cost_price.doubleValue();
					qmjc_money = qmjc_price * qmjc_count;

					qmjc_count_total = qmjc_count;
					qmjc_money_total = qmjc_money;

					sheet.addCell(new Label(0, row, "出货"));
					Date biz_date = (Date) jxcPd.getMap().get("biz_date");
					sheet.addCell(new Label(1, row, format.format(biz_date)));
					sheet.addCell(new Label(2, row, (String) jxcPd.getMap().get("name")));
					sheet.addCell(new jxl.write.Number(6, row, count.doubleValue(), wc));
					sheet.addCell(new jxl.write.Number(7, row, cost_price.doubleValue(), wcf));
					sheet.addCell(new jxl.write.Number(8, row, rcch_money.doubleValue(), wcf));
					sheet.addCell(new jxl.write.Number(9, row, qmjc_count.doubleValue(), wc));
					sheet.addCell(new jxl.write.Number(10, row, qmjc_price.doubleValue(), wcf));
					sheet.addCell(new jxl.write.Number(11, row, qmjc_money.doubleValue(), wcf));
					row++;
				}
			}

			sheet.mergeCells(0, row, 2, row);
			sheet.addCell(new Label(0, row, "合计"));
			sheet.addCell(new jxl.write.Number(3, row, rcjh_count_total.doubleValue(), wc));
			sheet.addCell(new jxl.write.Number(5, row, rcjh_money_total.doubleValue(), wcf));
			sheet.addCell(new jxl.write.Number(6, row, rcch_count_total.doubleValue(), wc));
			sheet.addCell(new jxl.write.Number(8, row, rcch_money_total.doubleValue(), wcf));
			sheet.addCell(new jxl.write.Number(9, row, qmjc_count_total.doubleValue(), wc));
			sheet.addCell(new jxl.write.Number(11, row, qmjc_money_total.doubleValue(), wcf));

			row++;
			sheet.mergeCells(0, row, 11, row);
			String info = "签收人：";
			Label label = new Label(0, row, info);
			label.setCellFormat(cellFormatCenter);
			sheet.addCell(label);
			// 从内存中写入文件中
			wwb.write();
			// 关闭资源，释放内存
			wwb.close();
		}
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename=" + sFileName);
		FileInputStream fileInputStream = new FileInputStream(SystemPath + "files/download_excel/" + sFileName);
		OutputStream out = response.getOutputStream();
		int i = 0;
		while ((i = fileInputStream.read()) != -1) {
			out.write(i);
		}
		fileInputStream.close();
		out.close();
	}
}
