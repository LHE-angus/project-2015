package com.ebiz.mmt.web.struts.customer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.JBaseGoods;
import com.ebiz.mmt.domain.JBasePartner;
import com.ebiz.mmt.domain.JBaseType;
import com.ebiz.mmt.domain.JBill;
import com.ebiz.mmt.domain.JBillDetails;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.chart.BaseChart;
import com.ebiz.mmt.domain.chart.MsLineChart;
import com.ebiz.mmt.web.Constants;

/**
 * @author Liu,ZhiXiang
 * @version 2013-6-25
 */
public class JxcXsReportAction extends BaseClientJxcAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String flag_type = (String) dynaBean.get("flag_type");// 100:销售毛利分析,200:客户销售排行,300:产品销售排行,400:采购支出,500:交易明细

		dynaBean.set("chart_type", "1");
		// String mod_id = (String) dynaBean.get("mod_id");

		Date today = new Date();

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(today);

		calendar.add(Calendar.DATE, -30);
		String day_first = df.format(calendar.getTime());
		String day_last = df.format(today);
		dynaBean.set("st_date", day_first);
		dynaBean.set("en_date", day_last);
		if (StringUtils.isNotBlank(flag_type) && "100".equals(flag_type)) {
			// 106010000 销售毛利分析
			dynaBean.set("tj_type", "1");
			return this.xs_time(mapping, form, request, response);
		} else if (StringUtils.isNotBlank(flag_type) && "200".equals(flag_type)) {
			// 106020000 客户销售排行
			return this.xs_partner(mapping, form, request, response);
		} else if (StringUtils.isNotBlank(flag_type) && "300".equals(flag_type)) {
			// 106030000 产品销售排行
			dynaBean.set("tj_type", "3");
			return this.xs_goods(mapping, form, request, response);
		} else if (StringUtils.isNotBlank(flag_type) && "400".equals(flag_type)) {
			// 106040000 采购支出
			dynaBean.set("tj_type", "1");
			return this.cg_time(mapping, form, request, response);
		} else if (StringUtils.isNotBlank(flag_type) && "500".equals(flag_type)) {
			// 106050000 交易明细
			return this.list(mapping, form, request, response);
		} else if (StringUtils.isNotBlank(flag_type) && "600".equals(flag_type)) {
			// dynaBean.set("tj_type", "1");
			return this.xs_store(mapping, form, request, response);
		}

		return null;
	}

	public ActionForward xs_time(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		PeProdUser user = (PeProdUser) getSessionAttribute(request, Constants.CUSTOMER_USER_INFO);
		DynaBean dynaBean = (DynaBean) form;

		String partner_id = (String) dynaBean.get("partner_id");// 客户
		// String store_id = (String) dynaBean.get("store_id");//仓库
		String goods_ids = (String) dynaBean.get("goods_ids");// 商品
		String st_date = (String) dynaBean.get("st_date");// 开始时间
		String en_date = (String) dynaBean.get("en_date");// 结束时间
		String type_id = (String) dynaBean.get("type_id");// 商品类型
		String tj_type = (String) dynaBean.get("tj_type");// 统计方式 1:按日期统计,2:按月份统
		// String chart_type = (String) dynaBean.get("chart_type");//图形报表
		// 1:柱状图,2:折线图

		// 判断客户是R3客户还是网点
		KonkaR3Shop kShop = new KonkaR3Shop();
		kShop.setId(user.getCust_id());
		kShop = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(kShop);

		if (null == kShop) {
			super.renderJavaScript(response, "alert('客户不存在，请联系相关人员处理！');history.back();");
			return null;
		}

		List<?> list = this.getReportList(kShop, st_date, en_date, type_id, goods_ids, partner_id, tj_type, "desc");
		request.setAttribute("entityList", list);

		request.setAttribute("jBaseTypes", super.getJBaseTypes(10001L, user.getCust_id())); // 商品类型
		request.setAttribute("jBasePartners", super.getJBasePartners("1", null, user.getCust_id()));
		request.setAttribute("jBaseGoodsList", super.getJBaseGoodsList(user.getCust_id()));

		return new ActionForward("/../customer/JxcXsReport/xs_time.jsp");
	}

	public ActionForward xs_time_chart(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		PeProdUser user = (PeProdUser) getSessionAttribute(request, Constants.CUSTOMER_USER_INFO);
		DynaBean dynaBean = (DynaBean) form;

		// 判断客户是R3客户还是网点
		KonkaR3Shop kShop = new KonkaR3Shop();
		kShop.setId(user.getCust_id());
		kShop = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(kShop);

		if (null == kShop) {
			super.renderJavaScript(response, "alert('客户不存在，请联系相关人员处理！');history.back();");
			return null;
		}

		String partner_id = (String) dynaBean.get("partner_id");// 客户
		// String store_id = (String) dynaBean.get("store_id");//仓库
		String goods_ids = (String) dynaBean.get("goods_ids");// 商品
		String st_date = (String) dynaBean.get("st_date");// 开始时间
		String en_date = (String) dynaBean.get("en_date");// 结束时间
		String type_id = (String) dynaBean.get("type_id");// 商品类型
		String tj_type = (String) dynaBean.get("tj_type");// 统计方式
		// 1:按日期统计,2:按月份统计
		String chart_type = (String) dynaBean.get("chart_type");// 图形报表
		// 1:柱状图,2:折线图

		List<?> list = this.getReportList(kShop, st_date, en_date, type_id, goods_ids, partner_id, tj_type, "asc");
		List<MsLineChart> msLineChartList = new ArrayList<MsLineChart>();
		List<BaseChart> baseChartList_1 = new ArrayList<BaseChart>();

		if (null != list && list.size() > 0) {

			List<BaseChart> baseChartList_2 = new ArrayList<BaseChart>();
			List<BaseChart> baseChartList_3 = new ArrayList<BaseChart>();
			List<BaseChart> baseChartList_4 = new ArrayList<BaseChart>();
			List<BaseChart> baseChartList_5 = new ArrayList<BaseChart>();

			for (int i = 0; i < list.size(); i++) {
				Object[] obj = (Object[]) list.get(i);
				BaseChart baseChart = new BaseChart();
				baseChart.setCategory_label(obj[0] == null ? "" : obj[0].toString());

				String str = baseChart.getCategory_label();
				if (StringUtils.isNotBlank(tj_type) && "2".equals(tj_type)) {
					if (baseChart.getCategory_label().length() >= 7) {
						str = str.substring(0, 4) + "年" + str.substring(5, 7) + "月";
						baseChart.setCategory_label(str);
					}
				} else {
					if (baseChart.getCategory_label().length() >= 10) {
						// str =
						// str.substring(0,4)+"年"+str.substring(5,7)+"月"+str.substring(8,10)+"日";
						str = str.substring(0, 10);// yyyy-MM-dd
						baseChart.setCategory_label(str);
					}
				}

				baseChartList_1.add(baseChart);
				BaseChart baseChart1 = new BaseChart();
				baseChart1.setValue(obj[1] == null ? "0" : obj[1].toString());
				baseChartList_2.add(baseChart1);
				BaseChart baseChart2 = new BaseChart();
				baseChart2.setValue(obj[2] == null ? "0" : obj[2].toString());
				baseChartList_3.add(baseChart2);
				BaseChart baseChart3 = new BaseChart();
				baseChart3.setValue(obj[3] == null ? "0" : obj[3].toString());
				baseChartList_4.add(baseChart3);
				BaseChart baseChart4 = new BaseChart();
				baseChart4.setValue(obj[4] == null ? "0" : obj[4].toString());
				baseChartList_5.add(baseChart4);

			}

			//
			MsLineChart msLineChart2 = new MsLineChart();
			msLineChart2.setSeries_name("数量");
			msLineChart2.setColor("1D8BD1");
			msLineChart2.setAnchor_border_color("1D8BD1");
			msLineChart2.setAnchor_bg_color("1D8BD1");
			msLineChart2.setBaseChartList(baseChartList_2);
			msLineChartList.add(msLineChart2);
			//
			MsLineChart msLineChart3 = new MsLineChart();
			msLineChart3.setSeries_name("收入");
			msLineChart3.setColor("F1683C");
			msLineChart3.setAnchor_border_color("F1683C");
			msLineChart3.setAnchor_bg_color("F1683C");
			msLineChart3.setBaseChartList(baseChartList_3);
			msLineChartList.add(msLineChart3);
			//
			MsLineChart msLineChart4 = new MsLineChart();
			msLineChart4.setSeries_name("成本");
			msLineChart4.setColor("2AD62A");
			msLineChart4.setAnchor_border_color("2AD62A");
			msLineChart4.setAnchor_bg_color("2AD62A");
			msLineChart4.setBaseChartList(baseChartList_4);
			msLineChartList.add(msLineChart4);
			//
			MsLineChart msLineChart5 = new MsLineChart();
			msLineChart5.setSeries_name("利润");
			msLineChart5.setColor("DBDC25");
			msLineChart5.setAnchor_border_color("DBDC25");
			msLineChart5.setAnchor_bg_color("DBDC25");
			msLineChart5.setBaseChartList(baseChartList_5);
			msLineChartList.add(msLineChart5);

		}
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("baseChartList", baseChartList_1);
		model.put("msLineChartList", msLineChartList);
		model.put("mSColumn3DChartChartList", msLineChartList);
		// model.put("unit", "单位：元");
		String caption = "按日期统计";
		if (StringUtils.isNotBlank(tj_type) && "2".equals(tj_type)) {
			caption = "按月份统计";
		}
		if (StringUtils.isNotBlank(chart_type) && "2".equals(chart_type)) {
			model.put("caption", "销售毛利分析-" + caption + "(折线图)");
			render(response, getFacade().getTemplateService().getContent("chart/MSLine.ftl", model),
					"text/xml;charset=GBK");
			return null;
		}
		model.put("caption", "销售毛利分析-" + caption + "(柱状图)");
		render(response, getFacade().getTemplateService().getContent("chart/MSColumn3D.ftl", model),
				"text/xml;charset=GBK");
		return null;
	}

	public ActionForward xs_store(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		PeProdUser user = (PeProdUser) getSessionAttribute(request, Constants.CUSTOMER_USER_INFO);
		DynaBean dynaBean = (DynaBean) form;

		String partner_id = (String) dynaBean.get("partner_id");// 客户
		String goods_ids = (String) dynaBean.get("goods_ids");// 商品
		String st_date = (String) dynaBean.get("st_date");// 开始时间
		String en_date = (String) dynaBean.get("en_date");// 结束时间
		String type_id = (String) dynaBean.get("type_id");// 商品类型
		String p_level = (String) dynaBean.get("p_level");// 网点级别

		// String tj_type = (String) dynaBean.get("tj_type");//统计方式
		// 1:按日期统计,2:按月份统
		// String chart_type = (String) dynaBean.get("chart_type");//图形报表
		// 1:柱状图,2:折线图

		// 判断客户是R3客户还是网点
		KonkaR3Shop kShop = new KonkaR3Shop();
		kShop.setId(user.getCust_id());
		kShop = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(kShop);

		if (null == kShop) {
			super.renderJavaScript(response, "alert('客户不存在，请联系相关人员处理！');history.back();");
			return null;
		}

		if (kShop.getIs_konka() == 1)
			request.setAttribute("shop_name", kShop.getCustomer_name());

		List<?> list = this.getReportListForStore(kShop, st_date, en_date, type_id, goods_ids, partner_id, p_level);
		request.setAttribute("entityList", list);

		request.setAttribute("jBaseTypes", super.getJBaseTypes(10001L, user.getCust_id())); // 商品类型
		request.setAttribute("jBasePartners", getEntityList(user.getCust_id()));
		request.setAttribute("jBaseGoodsList", super.getJBaseGoodsList(user.getCust_id()));

		return new ActionForward("/../customer/JxcXsReport/xs_store.jsp");
	}

	public ActionForward xs_store_chart(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		PeProdUser user = (PeProdUser) getSessionAttribute(request, Constants.CUSTOMER_USER_INFO);
		DynaBean dynaBean = (DynaBean) form;

		String partner_id = (String) dynaBean.get("partner_id");// 客户
		// String store_id = (String) dynaBean.get("store_id");//仓库
		String goods_ids = (String) dynaBean.get("goods_ids");// 商品
		String st_date = (String) dynaBean.get("st_date");// 开始时间
		String en_date = (String) dynaBean.get("en_date");// 结束时间
		String type_id = (String) dynaBean.get("type_id");// 商品类型
		String p_level = (String) dynaBean.get("p_level");// 网点级别
		// String tj_type = (String) dynaBean.get("tj_type");//统计方式
		// 1:按日期统计,2:按月份统
		String chart_type = (String) dynaBean.get("chart_type");// 图形报表
		// 1:柱状图,2:折线图

		// 判断客户是R3客户还是网点
		KonkaR3Shop kShop = new KonkaR3Shop();
		kShop.setId(user.getCust_id());
		kShop = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(kShop);

		if (null == kShop) {
			super.renderJavaScript(response, "alert('客户不存在，请联系相关人员处理！');history.back();");
			return null;
		}

		List<?> list = this.getReportListForStore(kShop, st_date, en_date, type_id, goods_ids, partner_id, p_level);
		List<MsLineChart> msLineChartList = new ArrayList<MsLineChart>();
		List<BaseChart> baseChartList_1 = new ArrayList<BaseChart>();

		if (null != list && list.size() > 0) {

			// List<BaseChart> baseChartList_2 = new ArrayList<BaseChart>();
			List<BaseChart> baseChartList_3 = new ArrayList<BaseChart>();
			List<BaseChart> baseChartList_4 = new ArrayList<BaseChart>();
			List<BaseChart> baseChartList_5 = new ArrayList<BaseChart>();

			for (int i = 0; i < list.size(); i++) {
				Object[] obj = (Object[]) list.get(i);
				BaseChart baseChart1 = new BaseChart();
				if (kShop.getIs_konka() == 1) {
					baseChart1.setCategory_label(obj[1] == null ? kShop.getCustomer_name() : obj[1].toString());
				} else {
					baseChart1.setCategory_label(obj[1] == null ? "" : obj[1].toString());
				}

				baseChartList_1.add(baseChart1);

				// BaseChart baseChart2 = new BaseChart();
				// baseChart2.setValue(obj[2] == null ? "0" :
				// obj[2].toString());
				// baseChartList_2.add(baseChart2);

				BaseChart baseChart3 = new BaseChart();
				baseChart3.setValue(obj[3] == null ? "0" : obj[3].toString());
				baseChartList_3.add(baseChart3);

				BaseChart baseChart4 = new BaseChart();
				baseChart4.setValue(obj[4] == null ? "0" : obj[4].toString());
				baseChartList_4.add(baseChart4);

				BaseChart baseChart5 = new BaseChart();
				baseChart5.setValue(obj[5] == null ? "0" : obj[5].toString());
				baseChartList_5.add(baseChart5);

			}

			// //
			// MsLineChart msLineChart2 = new MsLineChart();
			// msLineChart2.setSeries_name("数量");
			// msLineChart2.setColor("1D8BD1");
			// msLineChart2.setAnchor_border_color("1D8BD1");
			// msLineChart2.setAnchor_bg_color("1D8BD1");
			// msLineChart2.setBaseChartList(baseChartList_2);
			// msLineChartList.add(msLineChart2);
			//
			MsLineChart msLineChart3 = new MsLineChart();
			msLineChart3.setSeries_name("收入");
			msLineChart3.setColor("F1683C");
			msLineChart3.setAnchor_border_color("F1683C");
			msLineChart3.setAnchor_bg_color("F1683C");
			msLineChart3.setBaseChartList(baseChartList_3);
			msLineChartList.add(msLineChart3);
			//
			MsLineChart msLineChart4 = new MsLineChart();
			msLineChart4.setSeries_name("成本");
			msLineChart4.setColor("2AD62A");
			msLineChart4.setAnchor_border_color("2AD62A");
			msLineChart4.setAnchor_bg_color("2AD62A");
			msLineChart4.setBaseChartList(baseChartList_4);
			msLineChartList.add(msLineChart4);
			//
			MsLineChart msLineChart5 = new MsLineChart();
			msLineChart5.setSeries_name("利润");
			msLineChart5.setColor("DBDC25");
			msLineChart5.setAnchor_border_color("DBDC25");
			msLineChart5.setAnchor_bg_color("DBDC25");
			msLineChart5.setBaseChartList(baseChartList_5);
			msLineChartList.add(msLineChart5);

		}
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("baseChartList", baseChartList_1);
		model.put("msLineChartList", msLineChartList);
		model.put("mSColumn3DChartChartList", msLineChartList);
		// model.put("unit", "单位：元");
		if (StringUtils.isNotBlank(chart_type) && "2".equals(chart_type)) {
			model.put("caption", "客户销售排行-按客户统计(折线图)");
			render(response, getFacade().getTemplateService().getContent("chart/MSLine.ftl", model),
					"text/xml;charset=GBK");
			return null;
		}
		model.put("caption", "客户销售排行-按客户统计(柱状图)");
		render(response, getFacade().getTemplateService().getContent("chart/MSColumn3D.ftl", model),
				"text/xml;charset=GBK");
		return null;
	}

	public ActionForward xs_partner(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		PeProdUser user = (PeProdUser) getSessionAttribute(request, Constants.CUSTOMER_USER_INFO);
		DynaBean dynaBean = (DynaBean) form;

		String partner_id = (String) dynaBean.get("partner_id");// 客户
		// String store_id = (String) dynaBean.get("store_id");//仓库
		String goods_ids = (String) dynaBean.get("goods_ids");// 商品
		String st_date = (String) dynaBean.get("st_date");// 开始时间
		String en_date = (String) dynaBean.get("en_date");// 结束时间
		String type_id = (String) dynaBean.get("type_id");// 商品类型
		// String tj_type = (String) dynaBean.get("tj_type");//统计方式
		// 1:按日期统计,2:按月份统
		// String chart_type = (String) dynaBean.get("chart_type");//图形报表
		// 1:柱状图,2:折线图

		// 判断客户是R3客户还是网点
		KonkaR3Shop kShop = new KonkaR3Shop();
		kShop.setId(user.getCust_id());
		kShop = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(kShop);

		if (null == kShop) {
			super.renderJavaScript(response, "alert('客户不存在，请联系相关人员处理！');history.back();");
			return null;
		}

		List<?> list = this.getReportListForPartner(kShop, st_date, en_date, type_id, goods_ids, partner_id);
		request.setAttribute("entityList", list);

		request.setAttribute("jBaseTypes", super.getJBaseTypes(10001L, user.getCust_id())); // 商品类型
		request.setAttribute("jBasePartners", super.getJBasePartners("1", null, user.getCust_id()));
		request.setAttribute("jBaseGoodsList", super.getJBaseGoodsList(user.getCust_id()));

		return new ActionForward("/../customer/JxcXsReport/xs_partner.jsp");
	}

	public ActionForward xs_partner_chart(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		PeProdUser user = (PeProdUser) getSessionAttribute(request, Constants.CUSTOMER_USER_INFO);
		DynaBean dynaBean = (DynaBean) form;

		String partner_id = (String) dynaBean.get("partner_id");// 客户
		// String store_id = (String) dynaBean.get("store_id");//仓库
		String goods_ids = (String) dynaBean.get("goods_ids");// 商品
		String st_date = (String) dynaBean.get("st_date");// 开始时间
		String en_date = (String) dynaBean.get("en_date");// 结束时间
		String type_id = (String) dynaBean.get("type_id");// 商品类型
		// String tj_type = (String) dynaBean.get("tj_type");//统计方式
		// 1:按日期统计,2:按月份统
		String chart_type = (String) dynaBean.get("chart_type");// 图形报表
		// 1:柱状图,2:折线图

		// 判断客户是R3客户还是网点
		KonkaR3Shop kShop = new KonkaR3Shop();
		kShop.setId(user.getCust_id());
		kShop = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(kShop);

		if (null == kShop) {
			super.renderJavaScript(response, "alert('客户不存在，请联系相关人员处理！');history.back();");
			return null;
		}

		List<?> list = this.getReportListForPartner(kShop, st_date, en_date, type_id, goods_ids, partner_id);
		List<MsLineChart> msLineChartList = new ArrayList<MsLineChart>();
		List<BaseChart> baseChartList_1 = new ArrayList<BaseChart>();

		if (null != list && list.size() > 0) {

			List<BaseChart> baseChartList_2 = new ArrayList<BaseChart>();
			List<BaseChart> baseChartList_3 = new ArrayList<BaseChart>();
			List<BaseChart> baseChartList_4 = new ArrayList<BaseChart>();
			List<BaseChart> baseChartList_5 = new ArrayList<BaseChart>();

			for (int i = 0; i < list.size(); i++) {
				Object[] obj = (Object[]) list.get(i);
				BaseChart baseChart1 = new BaseChart();
				baseChart1.setCategory_label(obj[1] == null ? "" : obj[1].toString());
				baseChartList_1.add(baseChart1);

				BaseChart baseChart2 = new BaseChart();
				baseChart2.setValue(obj[2] == null ? "0" : obj[2].toString());
				baseChartList_2.add(baseChart2);

				BaseChart baseChart3 = new BaseChart();
				baseChart3.setValue(obj[3] == null ? "0" : obj[3].toString());
				baseChartList_3.add(baseChart3);

				BaseChart baseChart4 = new BaseChart();
				baseChart4.setValue(obj[4] == null ? "0" : obj[4].toString());
				baseChartList_4.add(baseChart4);

				BaseChart baseChart5 = new BaseChart();
				baseChart5.setValue(obj[5] == null ? "0" : obj[5].toString());
				baseChartList_5.add(baseChart5);

			}

			//
			MsLineChart msLineChart2 = new MsLineChart();
			msLineChart2.setSeries_name("数量");
			msLineChart2.setColor("1D8BD1");
			msLineChart2.setAnchor_border_color("1D8BD1");
			msLineChart2.setAnchor_bg_color("1D8BD1");
			msLineChart2.setBaseChartList(baseChartList_2);
			msLineChartList.add(msLineChart2);
			//
			MsLineChart msLineChart3 = new MsLineChart();
			msLineChart3.setSeries_name("收入");
			msLineChart3.setColor("F1683C");
			msLineChart3.setAnchor_border_color("F1683C");
			msLineChart3.setAnchor_bg_color("F1683C");
			msLineChart3.setBaseChartList(baseChartList_3);
			msLineChartList.add(msLineChart3);
			//
			MsLineChart msLineChart4 = new MsLineChart();
			msLineChart4.setSeries_name("成本");
			msLineChart4.setColor("2AD62A");
			msLineChart4.setAnchor_border_color("2AD62A");
			msLineChart4.setAnchor_bg_color("2AD62A");
			msLineChart4.setBaseChartList(baseChartList_4);
			msLineChartList.add(msLineChart4);
			//
			MsLineChart msLineChart5 = new MsLineChart();
			msLineChart5.setSeries_name("利润");
			msLineChart5.setColor("DBDC25");
			msLineChart5.setAnchor_border_color("DBDC25");
			msLineChart5.setAnchor_bg_color("DBDC25");
			msLineChart5.setBaseChartList(baseChartList_5);
			msLineChartList.add(msLineChart5);

		}
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("baseChartList", baseChartList_1);
		model.put("msLineChartList", msLineChartList);
		model.put("mSColumn3DChartChartList", msLineChartList);
		// model.put("unit", "单位：元");
		if (StringUtils.isNotBlank(chart_type) && "2".equals(chart_type)) {
			model.put("caption", "客户销售排行-按客户统计(折线图)");
			render(response, getFacade().getTemplateService().getContent("chart/MSLine.ftl", model),
					"text/xml;charset=GBK");
			return null;
		}
		model.put("caption", "客户销售排行-按客户统计(柱状图)");
		render(response, getFacade().getTemplateService().getContent("chart/MSColumn3D.ftl", model),
				"text/xml;charset=GBK");
		return null;
	}

	public ActionForward xs_goods(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		PeProdUser user = (PeProdUser) getSessionAttribute(request, Constants.CUSTOMER_USER_INFO);
		DynaBean dynaBean = (DynaBean) form;

		String partner_id = (String) dynaBean.get("partner_id");// 客户
		// String store_id = (String) dynaBean.get("store_id");//仓库
		String goods_ids = (String) dynaBean.get("goods_ids");// 商品
		String st_date = (String) dynaBean.get("st_date");// 开始时间
		String en_date = (String) dynaBean.get("en_date");// 结束时间
		String type_id = (String) dynaBean.get("type_id");// 商品类型
		String tj_type = (String) dynaBean.get("tj_type");// 统计方式
		// 3:按商品统计,4:按商品类型统计
		// String chart_type = (String) dynaBean.get("chart_type");//图形报表
		// 1:柱状图,2:折线图

		// 判断客户是R3客户还是网点
		KonkaR3Shop kShop = new KonkaR3Shop();
		kShop.setId(user.getCust_id());
		kShop = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(kShop);

		if (null == kShop) {
			super.renderJavaScript(response, "alert('客户不存在，请联系相关人员处理！');history.back();");
			return null;
		}

		List list = new ArrayList();
		if (StringUtils.isNotBlank(tj_type) && "3".equals(tj_type)) {
			// 按商品统计
			list = this.getReportListForGoods(kShop, st_date, en_date, type_id, goods_ids, partner_id);
		} else if (StringUtils.isNotBlank(tj_type) && "4".equals(tj_type)) {
			// 按商品类型统计
			list = this.getReportListForJBaseType(kShop, st_date, en_date, type_id, goods_ids, partner_id);
		}
		request.setAttribute("entityList", list);

		request.setAttribute("jBaseTypes", super.getJBaseTypes(10001L, user.getCust_id())); // 商品类型
		request.setAttribute("jBasePartners", super.getJBasePartners("1", null, user.getCust_id()));
		request.setAttribute("jBaseGoodsList", super.getJBaseGoodsList(user.getCust_id()));

		return new ActionForward("/../customer/JxcXsReport/xs_goods.jsp");
	}

	public ActionForward xs_goods_chart(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		PeProdUser user = (PeProdUser) getSessionAttribute(request, Constants.CUSTOMER_USER_INFO);
		DynaBean dynaBean = (DynaBean) form;

		String partner_id = (String) dynaBean.get("partner_id");// 客户
		// String store_id = (String) dynaBean.get("store_id");//仓库
		String goods_ids = (String) dynaBean.get("goods_ids");// 商品
		String st_date = (String) dynaBean.get("st_date");// 开始时间
		String en_date = (String) dynaBean.get("en_date");// 结束时间
		String type_id = (String) dynaBean.get("type_id");// 商品类型
		String tj_type = (String) dynaBean.get("tj_type");// 统计方式
		// 3:按商品统计,4:按商品类型统计
		String chart_type = (String) dynaBean.get("chart_type");// 图形报表
		// 1:柱状图,2:折线图

		// 判断客户是R3客户还是网点
		KonkaR3Shop kShop = new KonkaR3Shop();
		kShop.setId(user.getCust_id());
		kShop = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(kShop);

		if (null == kShop) {
			super.renderJavaScript(response, "alert('客户不存在，请联系相关人员处理！');history.back();");
			return null;
		}

		List list = new ArrayList();
		if (StringUtils.isNotBlank(tj_type) && "3".equals(tj_type)) {
			// 按商品统计
			list = this.getReportListForGoods(kShop, st_date, en_date, type_id, goods_ids, partner_id);
		} else if (StringUtils.isNotBlank(tj_type) && "4".equals(tj_type)) {
			// 按商品类型统计
			list = this.getReportListForJBaseType(kShop, st_date, en_date, type_id, goods_ids, partner_id);
		}

		List<MsLineChart> msLineChartList = new ArrayList<MsLineChart>();
		List<BaseChart> baseChartList_1 = new ArrayList<BaseChart>();

		if (null != list && list.size() > 0) {

			List<BaseChart> baseChartList_2 = new ArrayList<BaseChart>();
			List<BaseChart> baseChartList_3 = new ArrayList<BaseChart>();
			// List<BaseChart> baseChartList_4 = new ArrayList<BaseChart>();
			List<BaseChart> baseChartList_5 = new ArrayList<BaseChart>();

			for (int i = 0; i < list.size(); i++) {
				Object[] obj = (Object[]) list.get(i);
				BaseChart baseChart1 = new BaseChart();
				baseChart1.setCategory_label(obj[1] == null ? "" : obj[1].toString());
				baseChartList_1.add(baseChart1);

				BaseChart baseChart2 = new BaseChart();
				baseChart2.setValue(obj[2] == null ? "0" : obj[2].toString());
				baseChartList_2.add(baseChart2);

				BaseChart baseChart3 = new BaseChart();
				baseChart3.setValue(obj[3] == null ? "0" : obj[3].toString());
				baseChartList_3.add(baseChart3);

				// BaseChart baseChart4 = new BaseChart();
				// baseChart4.setValue(obj[4] == null ? "0" :
				// obj[4].toString());
				// baseChartList_4.add(baseChart4);

				BaseChart baseChart5 = new BaseChart();
				baseChart5.setValue(obj[5] == null ? "0" : obj[5].toString());
				baseChartList_5.add(baseChart5);

			}

			//
			MsLineChart msLineChart2 = new MsLineChart();
			msLineChart2.setSeries_name("数量");
			msLineChart2.setColor("1D8BD1");
			msLineChart2.setAnchor_border_color("1D8BD1");
			msLineChart2.setAnchor_bg_color("1D8BD1");
			msLineChart2.setBaseChartList(baseChartList_2);
			msLineChartList.add(msLineChart2);
			//
			MsLineChart msLineChart3 = new MsLineChart();
			msLineChart3.setSeries_name("收入");
			msLineChart3.setColor("F1683C");
			msLineChart3.setAnchor_border_color("F1683C");
			msLineChart3.setAnchor_bg_color("F1683C");
			msLineChart3.setBaseChartList(baseChartList_3);
			msLineChartList.add(msLineChart3);
			//
			// MsLineChart msLineChart4 = new MsLineChart();
			// msLineChart4.setSeries_name("成本");
			// msLineChart4.setColor("2AD62A");
			// msLineChart4.setAnchor_border_color("2AD62A");
			// msLineChart4.setAnchor_bg_color("2AD62A");
			// msLineChart4.setBaseChartList(baseChartList_4);
			// msLineChartList.add(msLineChart4);
			//
			MsLineChart msLineChart5 = new MsLineChart();
			msLineChart5.setSeries_name("利润");
			msLineChart5.setColor("DBDC25");
			msLineChart5.setAnchor_border_color("DBDC25");
			msLineChart5.setAnchor_bg_color("DBDC25");
			msLineChart5.setBaseChartList(baseChartList_5);
			msLineChartList.add(msLineChart5);

		}
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("baseChartList", baseChartList_1);
		model.put("msLineChartList", msLineChartList);
		model.put("mSColumn3DChartChartList", msLineChartList);
		// model.put("unit", "单位：元");
		String caption = "按商品统计";
		if (StringUtils.isNotBlank(tj_type) && "4".equals(tj_type)) {
			caption = "按商品类型统计";
		}
		if (StringUtils.isNotBlank(chart_type) && "2".equals(chart_type)) {
			model.put("caption", "产品销售排行-" + caption + "(折线图)");
			render(response, getFacade().getTemplateService().getContent("chart/MSLine.ftl", model),
					"text/xml;charset=GBK");
			return null;
		}
		model.put("caption", "产品销售排行-" + caption + "(柱状图)");
		render(response, getFacade().getTemplateService().getContent("chart/MSColumn3D.ftl", model),
				"text/xml;charset=GBK");
		return null;
	}

	public ActionForward cg_time(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		PeProdUser user = (PeProdUser) getSessionAttribute(request, Constants.CUSTOMER_USER_INFO);
		DynaBean dynaBean = (DynaBean) form;

		String partner_id = (String) dynaBean.get("partner_id");// 客户
		// String store_id = (String) dynaBean.get("store_id");//仓库
		String goods_ids = (String) dynaBean.get("goods_ids");// 商品
		String st_date = (String) dynaBean.get("st_date");// 开始时间
		String en_date = (String) dynaBean.get("en_date");// 结束时间
		String type_id = (String) dynaBean.get("type_id");// 商品类型
		String tj_type = (String) dynaBean.get("tj_type");// 统计方式 1:按日期统计,2:按月份统
		// String chart_type = (String) dynaBean.get("chart_type");//图形报表
		// 1:柱状图,2:折线图

		// 判断客户是R3客户还是网点
		KonkaR3Shop kShop = new KonkaR3Shop();
		kShop.setId(user.getCust_id());
		kShop = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(kShop);

		if (null == kShop) {
			super.renderJavaScript(response, "alert('客户不存在，请联系相关人员处理！');history.back();");
			return null;
		}

		List<?> list = this
				.getReportListForCG(kShop, st_date, en_date, type_id, goods_ids, partner_id, tj_type, "desc");
		request.setAttribute("entityList", list);

		request.setAttribute("jBaseTypes", super.getJBaseTypes(10001L, user.getCust_id())); // 商品类型
		request.setAttribute("jBasePartners", super.getJBasePartners("0", null, user.getCust_id()));
		request.setAttribute("jBaseGoodsList", super.getJBaseGoodsList(user.getCust_id()));

		return new ActionForward("/../customer/JxcXsReport/cg_time.jsp");
	}

	public ActionForward cg_time_chart(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		PeProdUser user = (PeProdUser) getSessionAttribute(request, Constants.CUSTOMER_USER_INFO);
		DynaBean dynaBean = (DynaBean) form;

		String partner_id = (String) dynaBean.get("partner_id");// 客户
		// String store_id = (String) dynaBean.get("store_id");//仓库
		String goods_ids = (String) dynaBean.get("goods_ids");// 商品
		String st_date = (String) dynaBean.get("st_date");// 开始时间
		String en_date = (String) dynaBean.get("en_date");// 结束时间
		String type_id = (String) dynaBean.get("type_id");// 商品类型
		String tj_type = (String) dynaBean.get("tj_type");// 统计方式
		// 1:按日期统计,2:按月份统计
		String chart_type = (String) dynaBean.get("chart_type");// 图形报表
		// 1:柱状图,2:折线图

		// 判断客户是R3客户还是网点
		KonkaR3Shop kShop = new KonkaR3Shop();
		kShop.setId(user.getCust_id());
		kShop = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(kShop);

		if (null == kShop) {
			super.renderJavaScript(response, "alert('客户不存在，请联系相关人员处理！');history.back();");
			return null;
		}

		List<?> list = this.getReportListForCG(kShop, st_date, en_date, type_id, goods_ids, partner_id, tj_type, "asc");
		List<MsLineChart> msLineChartList = new ArrayList<MsLineChart>();
		List<BaseChart> baseChartList_1 = new ArrayList<BaseChart>();

		if (null != list && list.size() > 0) {

			List<BaseChart> baseChartList_2 = new ArrayList<BaseChart>();
			List<BaseChart> baseChartList_3 = new ArrayList<BaseChart>();

			for (int i = 0; i < list.size(); i++) {
				Object[] obj = (Object[]) list.get(i);
				BaseChart baseChart = new BaseChart();
				baseChart.setCategory_label(obj[0] == null ? "" : obj[0].toString());

				String str = baseChart.getCategory_label();
				if (StringUtils.isNotBlank(tj_type) && "2".equals(tj_type)) {
					if (baseChart.getCategory_label().length() >= 7) {
						str = str.substring(0, 4) + "年" + str.substring(5, 7) + "月";
						baseChart.setCategory_label(str);
					}
				} else {
					if (baseChart.getCategory_label().length() >= 10) {
						// str =
						// str.substring(0,4)+"年"+str.substring(5,7)+"月"+str.substring(8,10)+"日";
						str = str.substring(0, 10);// yyyy-MM-dd
						baseChart.setCategory_label(str);
					}
				}

				baseChartList_1.add(baseChart);
				BaseChart baseChart1 = new BaseChart();
				baseChart1.setValue(obj[1] == null ? "0" : obj[1].toString());
				baseChartList_2.add(baseChart1);
				BaseChart baseChart2 = new BaseChart();
				baseChart2.setValue(obj[2] == null ? "0" : obj[2].toString());
				baseChartList_3.add(baseChart2);

			}

			//
			MsLineChart msLineChart2 = new MsLineChart();
			msLineChart2.setSeries_name("数量");
			msLineChart2.setColor("1D8BD1");
			msLineChart2.setAnchor_border_color("1D8BD1");
			msLineChart2.setAnchor_bg_color("1D8BD1");
			msLineChart2.setBaseChartList(baseChartList_2);
			msLineChartList.add(msLineChart2);
			//
			MsLineChart msLineChart = new MsLineChart();
			msLineChart.setSeries_name("采购成本");
			msLineChart.setColor("2AD62A");
			msLineChart.setAnchor_border_color("2AD62A");
			msLineChart.setAnchor_bg_color("2AD62A");
			msLineChart.setBaseChartList(baseChartList_3);
			msLineChartList.add(msLineChart);

		}
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("baseChartList", baseChartList_1);
		model.put("msLineChartList", msLineChartList);
		model.put("mSColumn3DChartChartList", msLineChartList);
		// model.put("unit", "单位：元");
		String caption = "按日期统计";
		if (StringUtils.isNotBlank(tj_type) && "2".equals(tj_type)) {
			caption = "按月份统计";
		}
		if (StringUtils.isNotBlank(chart_type) && "2".equals(chart_type)) {
			model.put("caption", "采购支出-" + caption + "(折线图)");
			render(response, getFacade().getTemplateService().getContent("chart/MSLine.ftl", model),
					"text/xml;charset=GBK");
			return null;
		}
		model.put("caption", "采购支出-" + caption + "(柱状图)");
		render(response, getFacade().getTemplateService().getContent("chart/MSColumn3D.ftl", model),
				"text/xml;charset=GBK");
		return null;
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		PeProdUser user = (PeProdUser) getSessionAttribute(request, Constants.CUSTOMER_USER_INFO);

		// 判断客户是R3客户还是网点
		KonkaR3Shop kShop = new KonkaR3Shop();
		kShop.setId(user.getCust_id());
		kShop = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(kShop);

		if (null == kShop) {
			super.renderJavaScript(response, "alert('客户不存在，请联系相关人员处理！');history.back();");
			return null;
		}

		DynaBean dynaBean = (DynaBean) form;

		String partner_id = (String) dynaBean.get("partner_id");// 客户
		String goods_id = (String) dynaBean.get("goods_id");// 商品
		String st_date = (String) dynaBean.get("st_date");// 开始时间
		String en_date = (String) dynaBean.get("en_date");// 结束时间
		String type_id = (String) dynaBean.get("type_id");// 商品类型
		String bill_type = (String) dynaBean.get("bill_type");// 10:采购,11:采购退货,20:销售,21:销售退货,30:盘亏,31:盘盈
		String goods_ids = (String) dynaBean.get("goods_ids");
		String bill_types = (String) dynaBean.get("bill_types");

		List<?> list = this.getReportListForDetails(kShop, st_date, en_date, type_id, goods_id, partner_id, bill_type,
				goods_ids, bill_types);
		request.setAttribute("entityList", list);

		request.setAttribute("jBaseTypes", super.getJBaseTypes(10001L, user.getCust_id())); // 商品类型
		request.setAttribute("jBasePartners", super.getJBasePartners("0", null, user.getCust_id()));
		request.setAttribute("jBaseGoodsList", super.getJBaseGoodsList(user.getCust_id()));

		return new ActionForward("/../customer/JxcXsReport/list.jsp");
	}

	public ActionForward xs_goods_details(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		PeProdUser user = (PeProdUser) getSessionAttribute(request, Constants.CUSTOMER_USER_INFO);
		DynaBean dynaBean = (DynaBean) form;

		// 判断客户是R3客户还是网点
		KonkaR3Shop kShop = new KonkaR3Shop();
		kShop.setId(user.getCust_id());
		kShop = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(kShop);

		if (null == kShop) {
			super.renderJavaScript(response, "alert('客户不存在，请联系相关人员处理！');history.back();");
			return null;
		}

		String partner_id = (String) dynaBean.get("partner_id");// 客户
		String goods_id = (String) dynaBean.get("goods_id");// 商品
		String st_date = (String) dynaBean.get("st_date");// 开始时间
		String en_date = (String) dynaBean.get("en_date");// 结束时间
		String type_id = (String) dynaBean.get("type_id");// 商品类型
		String bill_type = (String) dynaBean.get("bill_type");// 10:采购,11:采购退货,20:销售,21:销售退货,30:盘亏,31:盘盈
		String goods_ids = (String) dynaBean.get("goods_ids");
		String bill_types = (String) dynaBean.get("bill_types");

		List<?> list = this.getReportListForDetails(kShop, st_date, en_date, type_id, goods_id, partner_id, bill_type,
				goods_ids, bill_types);
		request.setAttribute("entityList", list);

		return new ActionForward("/../customer/JxcXsReport/xs_goods_details.jsp");
	}

	public ActionForward list_xs(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		PeProdUser user = (PeProdUser) getSessionAttribute(request, Constants.CUSTOMER_USER_INFO);
		DynaBean dynaBean = (DynaBean) form;

		String flag_type = (String) dynaBean.get("flag_type");// 100:销售毛利分析,200:客户销售排行,300:产品销售排行,400:采购支出,500:交易明细
		String partner_id = (String) dynaBean.get("partner_id");// 客户
		// String goods_id = (String) dynaBean.get("goods_id");// 商品
		String st_date = (String) dynaBean.get("st_date");// 开始时间
		String en_date = (String) dynaBean.get("en_date");// 结束时间
		String opr_date = (String) dynaBean.get("opr_date");// 交易时间
		// String type_id = (String) dynaBean.get("type_id");// 商品类型
		String bill_type = (String) dynaBean.get("bill_type");// 10:采购,11:采购退货,20:销售,21:销售退货,30:盘亏,31:盘盈

		if (StringUtils.isNotBlank(flag_type) && ("100".equals(flag_type) || "400".equals(flag_type))) {
			if (StringUtils.isNotBlank(opr_date) && opr_date.length() == 7) {
				st_date = opr_date + "-01";
				opr_date = "";
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				Date date = df.parse(st_date);
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				calendar.add(Calendar.MONTH, 1);
				calendar.add(Calendar.DATE, -1);
				en_date = df.format(calendar.getTime());

			} else {
				st_date = "";
				en_date = "";
			}
		}
		JBill entity = new JBill();
		entity.setC_id(user.getCust_id());
		if (StringUtils.isNotBlank(flag_type) && ("100".equals(flag_type) || "200".equals(flag_type))) {
			entity.getMap().put("bill_types", "20,21");
		} else if (StringUtils.isNotBlank(flag_type) && "400".equals(flag_type)) {
			entity.getMap().put("bill_types", "10,11");
		} else {
			return null;
		}

		// entity.setBill_type(Integer.valueOf(bill_type));
		if (StringUtils.isNotBlank(st_date)) {
			entity.getMap().put("opr_date_gt", st_date);
		}
		if (StringUtils.isNotBlank(en_date)) {
			entity.getMap().put("opr_date_lt", en_date);
		}
		if (StringUtils.isNotBlank(opr_date)) {
			// SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			// entity.setOpr_date(df.parse(opr_date));
			entity.getMap().put("opr_date", opr_date);
		}
		if (StringUtils.isNotBlank(partner_id)) {
			entity.setPartner_id(Long.valueOf(partner_id));
		}
		List<JBill> entityList = super.getFacade().getJBillService().getJBillList(entity);
		if (null != entityList && entityList.size() > 0) {
			for (JBill temp : entityList) {
				JBillDetails details = new JBillDetails();
				details.setBill_id(temp.getBill_id());

				List<JBillDetails> detailsList = super.getFacade().getJBillDetailsService()
						.getJBillDetailsAndGoodsList(details);

				if (null != detailsList && detailsList.size() > 0) {
					for (JBillDetails jdetails : detailsList) {
						JBaseGoods goods = new JBaseGoods();
						goods.setGoods_id(jdetails.getGoods_id());
						goods = super.getFacade().getJBaseGoodsService().getJBaseGoods(goods);
						if (null != goods) {
							JBaseType type = new JBaseType();
							type.setType_id(goods.getGoods_unit());
							type = super.getFacade().getJBaseTypeService().getJBaseType(type);

							jdetails.getMap().put("unit", type.getType_name());
						}

					}
					temp.setjBillDetailsList(detailsList);

				}

				JBasePartner partner = new JBasePartner();
				partner.setPartner_id(temp.getPartner_id());

				partner = super.getFacade().getJBasePartnerService().getJBasePartner(partner);

				if (null != partner) {
					temp.getMap().put("partner_name", partner.getPartner_name());
				}

			}
		}

		request.setAttribute("entityList", entityList);

		// 初始化页面标签
		request.setAttribute("bill_sn_title", super.getMessage(request, "konka.jbill.sn.title." + bill_type));
		request.setAttribute("price_title", super.getMessage(request, "konka.jbill.price.title." + bill_type));
		request.setAttribute("goods_money_title", super.getMessage(request, "konka.jbill.goods.money.title."
				+ bill_type));
		request.setAttribute("rec_money_title", super.getMessage(request, "konka.jbill.rec.money.title." + bill_type));
		request.setAttribute("money_title", super.getMessage(request, "konka.jbill.money.title." + bill_type));

		return new ActionForward("/../customer/JxcXsReport/list_xs.jsp");
	}

	public ActionForward getJBaseGoodsList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		PeProdUser user = (PeProdUser) getSessionAttribute(request, Constants.CUSTOMER_USER_INFO);
		DynaBean dynaBean = (DynaBean) form;

		String goods_ids = (String) dynaBean.get("goods_ids");// 商品
		String type_id = (String) dynaBean.get("type_id");// 商品类型

		JBaseGoods goods = new JBaseGoods();
		goods.setC_id(user.getCust_id());
		goods.setGoods_stutes(0);
		if (StringUtils.isNotBlank(goods_ids)) {
			goods.getMap().put("selects", goods_ids);
			List<JBaseGoods> selectList = super.getFacade().getJBaseGoodsService().getJBaseGoodsList(goods);
			request.setAttribute("selectList", selectList);
		}

		JBaseGoods entity = new JBaseGoods();
		entity.setC_id(user.getCust_id());
		if (StringUtils.isNotBlank(goods_ids)) {
			entity.getMap().put("no_selects", goods_ids);
		}
		if (StringUtils.isNotBlank(type_id)) {
			entity.setGoods_type(Long.valueOf(type_id));
		}

		List<JBaseGoods> noselectList = super.getFacade().getJBaseGoodsService().getJBaseGoodsList(entity);
		request.setAttribute("noselectList", noselectList);

		return new ActionForward("/../customer/JxcXsReport/selectJBaseGoodsList.jsp");
	}

	public List<?> getReportList(KonkaR3Shop kShop, String st_date, String en_date, String type_id, String goods_ids,
			String partner_id, String tj_type, String order_type) {

		Long p_id = null;
		JBasePartner j = new JBasePartner();
		j.setPartner_c_id(kShop.getId());
		List<JBasePartner> jList = super.getFacade().getJBasePartnerService().getJBasePartnerList(j);
		if (jList.size() > 0) {
			p_id = jList.get(0).getPartner_id();
		}

		// 按日统计
		String sql = " select trunc(a.opr_date),";
		if (StringUtils.isNotBlank(tj_type) && "2".equals(tj_type)) {
			// 按月统计
			sql = " select to_char(a.opr_date,'yyyy-MM'),";
		}
		sql += " sum(value(decode(a.bill_type,20,b.num,0)+decode(a.bill_type,21,b.num,0), 0)) as goods_num,";
		sql += " sum(value(decode(a.bill_type,20,b.money,0)-decode(a.bill_type,21,b.money,0), 0)) as goods_money,";
		sql += " sum(value(decode(a.bill_type,20,b.cost,0)+decode(a.bill_type,21,b.cost,0), 0)) as goods_cost,";
		sql += " sum(value(decode(a.bill_type,20,b.money,0)-decode(a.bill_type,21,b.money,0), 0) - value(decode(a.bill_type,20,b.cost,0)+decode(a.bill_type,21,b.cost,0), 0)) as goods_profit ";
		sql += " from j_bill a, j_bill_details b ";
		String where = " where a.bill_id = b.bill_id and a.bill_type in (20,21) and (a.c_id in ("; // bill_type:20

		if (kShop.getIs_konka() == 1) {
			where += "SELECT DISTINCT (partner_c_id) FROM J_BASE_PARTNER WHERE (c_id = " + kShop.getId()
					+ " OR partner_c_id = " + kShop.getId() + ")) or a.c_id = " + kShop.getId() + ")";
		} else {
			where += "SELECT distinct (partner_c_id) FROM J_BASE_PARTNER START WITH partner_id = " + p_id
					+ " CONNECT BY PRIOR partner_id = par_c_id ))";
		}
		// 销售
		if (StringUtils.isNotBlank(partner_id)) {
			where += " and a.partner_id = " + partner_id;
		}
		if (StringUtils.isNotBlank(goods_ids)) {
			where += " and b.goods_id in (" + goods_ids + ")";
		}
		if (StringUtils.isNotBlank(type_id)) {
			where += " and b.goods_id in (select j.goods_id from j_base_goods j where j.goods_stutes = 0 and j.c_id = "
					+ kShop.getId() + " and j.goods_type = " + type_id + ") ";
		}
		if (StringUtils.isNotBlank(st_date)) {
			where += " and a.opr_date >= to_date('" + st_date + " 00:00:00" + "','yyyy-MM-dd hh24:mi:ss')";
		}
		if (StringUtils.isNotBlank(en_date)) {
			where += " and a.opr_date <= to_date('" + en_date + " 23:59:59" + "','yyyy-MM-dd hh24:mi:ss')";
		}
		sql += where;

		if (StringUtils.isNotBlank(tj_type) && "2".equals(tj_type)) {
			sql += " group by to_char(a.opr_date,'yyyy-MM') order by to_char(a.opr_date,'yyyy-MM') " + order_type;
		} else {
			sql += " group by trunc(a.opr_date) order by trunc(a.opr_date) " + order_type;
		}

		List<?> list = super.getFacade().getBaseReportService().getBaseReportForArray(sql);

		return list;
	}

	public List<?> getReportListForPartner(KonkaR3Shop kShop, String st_date, String en_date, String type_id,
			String goods_ids, String partner_id) {

		Long p_id = null;
		JBasePartner j = new JBasePartner();
		j.setPartner_c_id(kShop.getId());
		List<JBasePartner> jList = super.getFacade().getJBasePartnerService().getJBasePartnerList(j);
		if (jList.size() > 0) {
			p_id = jList.get(0).getPartner_id();
		}

		// 按客户统计
		String sql = " select * from (select a.partner_id,(select jbp.partner_name from j_base_partner jbp where jbp.partner_id = a.partner_id) as partner_name,";
		sql += " sum(value(decode(a.bill_type,20,b.num,0)+decode(a.bill_type,21,b.num,0), 0)) as goods_num,";
		sql += " sum(value(decode(a.bill_type,20,b.money,0)-decode(a.bill_type,21,b.money,0), 0)) as goods_money,";
		sql += " sum(value(decode(a.bill_type,20,b.cost,0)+decode(a.bill_type,21,b.cost,0), 0)) as goods_cost,";
		sql += " sum(value(decode(a.bill_type,20,b.money,0)-decode(a.bill_type,21,b.money,0), 0) - value(decode(a.bill_type,20,b.cost,0)+decode(a.bill_type,21,b.cost,0), 0)) as goods_profit ";
		sql += " from j_bill a, j_bill_details b ";

		String where = " where a.bill_id = b.bill_id and a.bill_type in (20,21) and (a.c_id in ( ";// bill_type:20

		if (kShop.getIs_konka() == 1) {
			where += "SELECT DISTINCT (partner_c_id) FROM J_BASE_PARTNER WHERE (c_id = " + kShop.getId()
					+ " OR partner_c_id = " + kShop.getId() + ")) or a.c_id = " + kShop.getId() + ")";
		} else {
			where += "SELECT distinct (partner_c_id) FROM J_BASE_PARTNER START WITH partner_id = " + p_id
					+ " CONNECT BY PRIOR partner_id = par_c_id ))";
		}

		// 销售
		if (StringUtils.isNotBlank(partner_id)) {
			where += " and a.partner_id = " + partner_id;
		}
		if (StringUtils.isNotBlank(goods_ids)) {
			where += " and b.goods_id in (" + goods_ids + ")";
		}
		if (StringUtils.isNotBlank(type_id)) {
			where += " and b.goods_id in (select j.goods_id from j_base_goods j where j.goods_stutes = 0 and j.c_id = "
					+ kShop.getId() + " and j.goods_type = " + type_id + ") ";
		}
		if (StringUtils.isNotBlank(st_date)) {
			where += " and a.opr_date >= to_date('" + st_date + " 00:00:00" + "','yyyy-MM-dd hh24:mi:ss')";
		}
		if (StringUtils.isNotBlank(en_date)) {
			where += " and a.opr_date <= to_date('" + en_date + " 23:59:59" + "','yyyy-MM-dd hh24:mi:ss')";
		}
		sql += where;

		sql += " group by a.partner_id) aa order by aa.goods_num desc ";

		logger.info("sql==============={}", sql);

		List<?> list = super.getFacade().getBaseReportService().getBaseReportForArray(sql);
		return list;
	}

	public List<?> getReportListForStore(KonkaR3Shop kShop, String st_date, String en_date, String type_id,
			String goods_ids, String partner_id, String p_level) {

		Long p_id = null;
		JBasePartner j = new JBasePartner();
		j.setPartner_c_id(kShop.getId());
		List<JBasePartner> jList = super.getFacade().getJBasePartnerService().getJBasePartnerList(j);
		if (jList.size() > 0) {
			p_id = jList.get(0).getPartner_id();
		}

		// 按客户统计
		String sql = " select t.*,c.p_level from (select a.c_id,(select jbp.partner_name from j_base_partner jbp where jbp.partner_c_id = a.c_id) as partner_name,";
		sql += " sum(value(decode(a.bill_type,20,b.num,0)+decode(a.bill_type,21,b.num,0), 0)) as goods_num,";
		sql += " sum(value(decode(a.bill_type,20,b.money,0)-decode(a.bill_type,21,b.money,0), 0)) as goods_money,";
		sql += " sum(value(decode(a.bill_type,20,b.cost,0)+decode(a.bill_type,21,b.cost,0), 0)) as goods_cost,";
		sql += " sum(value(decode(a.bill_type,20,b.money,0)-decode(a.bill_type,21,b.money,0), 0) - value(decode(a.bill_type,20,b.cost,0)+decode(a.bill_type,21,b.cost,0), 0)) as goods_profit ";
		sql += " from j_bill a, j_bill_details b ";

		String where = " where a.bill_id = b.bill_id and a.bill_type in (20,21) and (a.c_id in ( ";// bill_type:20

		if (kShop.getIs_konka() == 1) {
			where += "SELECT DISTINCT (partner_c_id) FROM J_BASE_PARTNER WHERE (c_id = " + kShop.getId()
					+ " OR partner_c_id = " + kShop.getId() + ")) or a.c_id = " + kShop.getId() + ")";
		} else {
			where += "SELECT distinct (partner_c_id) FROM J_BASE_PARTNER START WITH partner_id = " + p_id
					+ " CONNECT BY PRIOR partner_id = par_c_id ))";
		}

		// 销售
		if (StringUtils.isNotBlank(partner_id)) {
			where += " and a.partner_id = " + partner_id;
		}
		if (StringUtils.isNotBlank(goods_ids)) {
			where += " and b.goods_id in (" + goods_ids + ")";
		}
		if (StringUtils.isNotBlank(type_id)) {
			where += " and b.goods_id in (select j.goods_id from j_base_goods j where j.goods_stutes = 0 and j.c_id = "
					+ kShop.getId() + " and j.goods_type = " + type_id + ") ";
		}
		if (StringUtils.isNotBlank(st_date)) {
			where += " and a.opr_date >= to_date('" + st_date + " 00:00:00" + "','yyyy-MM-dd hh24:mi:ss')";
		}
		if (StringUtils.isNotBlank(en_date)) {
			where += " and a.opr_date <= to_date('" + en_date + " 23:59:59" + "','yyyy-MM-dd hh24:mi:ss')";
		}
		sql += where;

		sql += "group by a.c_id )t LEFT JOIN ( SELECT a.partner_c_id,p_level FROM J_BASE_PARTNER a START WITH partner_c_id IN (";

		String where1 = null;
		if (kShop.getIs_konka() == 1) {
			where1 = " SELECT DISTINCT (partner_c_id) FROM J_BASE_PARTNER WHERE (c_id = " + kShop.getId()
					+ " OR partner_c_id = " + kShop.getId() + ") AND   par_c_id = " + kShop.getId();
		} else {

			where1 = "SELECT distinct (partner_c_id) FROM J_BASE_PARTNER WHERE partner_id = " + p_id;
		}
		sql += where1;
		sql += ") CONNECT BY PRIOR partner_id = par_c_id ) c on t.c_id = c.partner_c_id";

		if (null != p_level && StringUtils.isNotBlank(p_level)) {
			sql += " where c.p_level = " + p_level;
		}

		sql += " order by c.p_level asc, t.goods_num desc  ";

		logger.info("sql==============={}", sql);

		List<?> list = super.getFacade().getBaseReportService().getBaseReportForArray(sql);
		return list;
	}

	public List<?> getReportListForGoods(KonkaR3Shop kShop, String st_date, String en_date, String type_id,
			String goods_ids, String partner_id) {

		Long p_id = null;
		JBasePartner j = new JBasePartner();
		j.setPartner_c_id(kShop.getId());
		List<JBasePartner> jList = super.getFacade().getJBasePartnerService().getJBasePartnerList(j);
		if (jList.size() > 0) {
			p_id = jList.get(0).getPartner_id();
		}

		// 按商品统计
		String sql = " select * from (select b.goods_id,(select jbg.goods_name from j_base_goods jbg where jbg.goods_id = b.goods_id) as goods_name,";
		sql += " sum(value(decode(a.bill_type,20,b.num,0)+decode(a.bill_type,21,b.num,0), 0)) as goods_num,";
		sql += " sum(value(decode(a.bill_type,20,b.money,0)-decode(a.bill_type,21,b.money,0), 0)) as goods_money,";
		sql += " sum(value(decode(a.bill_type,20,b.cost,0)+decode(a.bill_type,21,b.cost,0), 0)) as goods_cost,";
		sql += " sum(value(decode(a.bill_type,20,b.money,0)-decode(a.bill_type,21,b.money,0), 0) - value(decode(a.bill_type,20,b.cost,0)+decode(a.bill_type,21,b.cost,0), 0)) as goods_profit, ";
		sql += " (select jbg.goods_type from j_base_goods jbg where jbg.goods_id = b.goods_id) as goods_type ";
		sql += " from j_bill a, j_bill_details b ";

		String where = " where a.bill_id = b.bill_id and a.bill_type in (20,21) and ( a.c_id in ( ";// bill_type:20

		if (kShop.getIs_konka() == 1) {
			where += "SELECT DISTINCT (partner_c_id) FROM J_BASE_PARTNER WHERE (c_id = " + kShop.getId()
					+ " OR partner_c_id = " + kShop.getId() + ")) or a.c_id = " + kShop.getId() + ")";
		} else {
			where += "SELECT distinct (partner_c_id) FROM J_BASE_PARTNER START WITH partner_id = " + p_id
					+ " CONNECT BY PRIOR partner_id = par_c_id ))";
		}

		// 销售
		if (StringUtils.isNotBlank(partner_id)) {
			where += " and a.partner_id = " + partner_id;
		}
		if (StringUtils.isNotBlank(goods_ids)) {
			where += " and b.goods_id in (" + goods_ids + ")";
		}
		if (StringUtils.isNotBlank(type_id)) {
			where += " and b.goods_id in (select j.goods_id from j_base_goods j where j.goods_stutes = 0 and j.c_id = "
					+ kShop.getId() + " and j.goods_type = " + type_id + ") ";
		}
		if (StringUtils.isNotBlank(st_date)) {
			where += " and a.opr_date >= to_date('" + st_date + " 00:00:00" + "','yyyy-MM-dd hh24:mi:ss')";
		}
		if (StringUtils.isNotBlank(en_date)) {
			where += " and a.opr_date <= to_date('" + en_date + " 23:59:59" + "','yyyy-MM-dd hh24:mi:ss')";
		}
		sql += where;

		sql += " group by b.goods_id) aa order by aa.goods_num desc ";

		List<?> list = super.getFacade().getBaseReportService().getBaseReportForArray(sql);
		return list;
	}

	public List<?> getReportListForJBaseType(KonkaR3Shop kShop, String st_date, String en_date, String type_id,
			String goods_ids, String partner_id) {

		Long p_id = null;
		JBasePartner j = new JBasePartner();
		j.setPartner_c_id(kShop.getId());
		List<JBasePartner> jList = super.getFacade().getJBasePartnerService().getJBasePartnerList(j);
		if (jList.size() > 0) {
			p_id = jList.get(0).getPartner_id();
		}

		// 按商品类型统计
		String sql = " select * from (select c.goods_type,(select jbt.type_name from j_base_type jbt where jbt.par_id = 10001 and jbt.c_id = "
				+ kShop.getId() + " and jbt.type_id = c.goods_type) as type_name,";
		sql += " sum(value(decode(a.bill_type,20,b.num,0)+decode(a.bill_type,21,b.num,0), 0)) as goods_num,";
		sql += " sum(value(decode(a.bill_type,20,b.money,0)-decode(a.bill_type,21,b.money,0), 0)) as goods_money,";
		sql += " sum(value(decode(a.bill_type,20,b.cost,0)+decode(a.bill_type,21,b.cost,0), 0)) as goods_cost,";
		sql += " sum(value(decode(a.bill_type,20,b.money,0)-decode(a.bill_type,21,b.money,0), 0) - value(decode(a.bill_type,20,b.cost,0)+decode(a.bill_type,21,b.cost,0), 0)) as goods_profit ";
		sql += " from j_bill a, j_bill_details b, j_base_goods c ";

		String where = " where a.bill_id = b.bill_id and b.goods_id = c.goods_id and a.bill_type in (20,21) and (a.c_id in (";// bill_type:20
		// 销售

		if (kShop.getIs_konka() == 1) {
			where += "SELECT DISTINCT (partner_c_id) FROM J_BASE_PARTNER WHERE (c_id = " + kShop.getId()
					+ " OR partner_c_id = " + kShop.getId() + ")) or a.c_id = " + kShop.getId() + ")";
		} else {
			where += "SELECT distinct (partner_c_id) FROM J_BASE_PARTNER START WITH partner_id = " + p_id
					+ " CONNECT BY PRIOR partner_id = par_c_id ))";
		}

		if (StringUtils.isNotBlank(partner_id)) {
			where += " and a.partner_id = " + partner_id;
		}
		if (StringUtils.isNotBlank(goods_ids)) {
			where += " and b.goods_id in (" + goods_ids + ")";
		}
		if (StringUtils.isNotBlank(type_id)) {
			where += " and b.goods_id in (select j.goods_id from j_base_goods j where j.goods_stutes = 0 and j.c_id = "
					+ kShop.getId() + " and j.goods_type = " + type_id + ") ";
		}
		if (StringUtils.isNotBlank(st_date)) {
			where += " and a.opr_date >= to_date('" + st_date + " 00:00:00" + "','yyyy-MM-dd hh24:mi:ss')";
		}
		if (StringUtils.isNotBlank(en_date)) {
			where += " and a.opr_date <= to_date('" + en_date + " 23:59:59" + "','yyyy-MM-dd hh24:mi:ss')";
		}
		sql += where;

		sql += " group by c.goods_type) aa order by aa.goods_num desc ";

		List<?> list = super.getFacade().getBaseReportService().getBaseReportForArray(sql);
		return list;
	}

	public List<?> getReportListForCG(KonkaR3Shop kShop, String st_date, String en_date, String type_id,
			String goods_ids, String partner_id, String tj_type, String order_type) {

		Long p_id = null;
		JBasePartner j = new JBasePartner();
		j.setPartner_c_id(kShop.getId());
		List<JBasePartner> jList = super.getFacade().getJBasePartnerService().getJBasePartnerList(j);
		if (jList.size() > 0) {
			p_id = jList.get(0).getPartner_id();
		}

		// 按日统计
		String sql = " select trunc(a.opr_date),";
		if (StringUtils.isNotBlank(tj_type) && "2".equals(tj_type)) {
			// 按月统计
			sql = " select to_char(a.opr_date,'yyyy-MM'),";
		}
		sql += " sum(value(decode(a.bill_type,10,b.num,0)+decode(a.bill_type,11,b.num,0), 0)) as goods_num,";
		sql += " sum(value(decode(a.bill_type,10,b.money,0)-decode(a.bill_type,11,b.money,0), 0)) as goods_money ";
		sql += " from j_bill a, j_bill_details b ";
		String where = " where a.bill_id = b.bill_id and a.bill_type in (10,11) and ( a.c_id  in ( ";// bill_type:11

		if (kShop.getIs_konka() == 1) {
			where += "SELECT DISTINCT (partner_c_id) FROM J_BASE_PARTNER WHERE (c_id = " + kShop.getId()
					+ " OR partner_c_id = " + kShop.getId() + ")) or a.c_id = " + kShop.getId() + ")";
		} else {
			where += "SELECT distinct (partner_c_id) FROM J_BASE_PARTNER START WITH partner_id = " + p_id
					+ " CONNECT BY PRIOR partner_id = par_c_id ))";
		}

		// 销售
		if (StringUtils.isNotBlank(partner_id)) {
			where += " and a.partner_id = " + partner_id;
		}
		if (StringUtils.isNotBlank(goods_ids)) {
			where += " and b.goods_id in (" + goods_ids + ")";
		}
		if (StringUtils.isNotBlank(type_id)) {
			where += " and b.goods_id in (select j.goods_id from j_base_goods j where j.goods_stutes = 0 and j.c_id = "
					+ kShop.getId() + " and j.goods_type = " + type_id + ") ";
		}
		if (StringUtils.isNotBlank(st_date)) {
			where += " and a.opr_date >= to_date('" + st_date + " 00:00:00" + "','yyyy-MM-dd hh24:mi:ss')";
		}
		if (StringUtils.isNotBlank(en_date)) {
			where += " and a.opr_date <= to_date('" + en_date + " 23:59:59" + "','yyyy-MM-dd hh24:mi:ss')";
		}
		sql += where;

		if (StringUtils.isNotBlank(tj_type) && "2".equals(tj_type)) {
			sql += " group by to_char(a.opr_date,'yyyy-MM') order by to_char(a.opr_date,'yyyy-MM') " + order_type;
		} else {
			sql += " group by trunc(a.opr_date) order by trunc(a.opr_date) " + order_type;
		}

		logger.info("sql=============={}", sql);
		List<?> list = super.getFacade().getBaseReportService().getBaseReportForArray(sql);
		return list;
	}

	public List<?> getReportListForDetails(KonkaR3Shop kShop, String st_date, String en_date, String type_id,
			String goods_id, String partner_id, String bill_type, String goods_ids, String bill_types) {

		Long p_id = null;
		JBasePartner j = new JBasePartner();
		j.setPartner_c_id(kShop.getId());
		List<JBasePartner> jList = super.getFacade().getJBasePartnerService().getJBasePartnerList(j);
		if (jList.size() > 0) {
			p_id = jList.get(0).getPartner_id();
		}

		String sql = "SELECT t.*,c.P_LEVEL,c.PARTNER_NAME FROM (";

		sql += " select b.goods_id,(select jbg.goods_name from j_base_goods jbg where jbg.goods_id = b.goods_id) as goods_name, ";
		sql += " decode(a.bill_type,10,'采购',11,'采购退货',20,'销售',21,'销售退货',30,'盘亏',31,'盘盈') as bill_type_name,";
		sql += " a.opr_date, b.price, value(b.num,0),value(b.money,0),value(b.cost,0),a.bill_sn,a.bill_type ,a.partner_id,a.c_id";
		sql += " from j_bill a, j_bill_details b ";
		String where = " where a.bill_id = b.bill_id and a.bill_type in (10,11,20,21,30,31) and (a.c_id in (";

		if (kShop.getIs_konka() == 1) {
			where += "SELECT DISTINCT (partner_c_id) FROM J_BASE_PARTNER WHERE (c_id = " + kShop.getId()
					+ " OR partner_c_id = " + kShop.getId() + ")) or a.c_id = " + kShop.getId() + ")";
		} else {
			where += "SELECT distinct (partner_c_id) FROM J_BASE_PARTNER START WITH partner_id = " + p_id
					+ " CONNECT BY PRIOR partner_id = par_c_id ))";
		}

		if (StringUtils.isNotBlank(partner_id)) {
			where += " and a.partner_id = " + partner_id;
		}
		if (StringUtils.isNotBlank(goods_id)) {
			where += " and b.goods_id = " + goods_id;
		}
		if (StringUtils.isNotBlank(goods_ids)) {
			where += " and b.goods_id in (" + goods_ids + ")";
		}
		if (StringUtils.isNotBlank(type_id)) {
			where += " and b.goods_id in (select j.goods_id from j_base_goods j where j.goods_stutes = 0 and j.c_id = "
					+ kShop.getId() + " and j.goods_type = " + type_id + ") ";
		}
		if (StringUtils.isNotBlank(st_date)) {
			where += " and a.opr_date >= to_date('" + st_date + " 00:00:00" + "','yyyy-MM-dd hh24:mi:ss')";
		}
		if (StringUtils.isNotBlank(en_date)) {
			where += " and a.opr_date <= to_date('" + en_date + " 23:59:59" + "','yyyy-MM-dd hh24:mi:ss')";
		}
		if (StringUtils.isNotBlank(bill_type)) {
			where += " and a.bill_type = " + bill_type;
		}
		if (StringUtils.isNotBlank(bill_types)) {
			where += " and a.bill_type in (" + bill_types + ") ";
		}
		sql += where;

		sql += ")t LEFT JOIN ( SELECT a.* FROM J_BASE_PARTNER a START WITH partner_c_id IN (";

		String where1 = null;
		if (kShop.getIs_konka() == 1) {
			where1 = " SELECT DISTINCT (partner_c_id) FROM J_BASE_PARTNER WHERE (c_id = " + kShop.getId()
					+ " OR partner_c_id = " + kShop.getId() + ") AND   par_c_id = " + kShop.getId();
		} else {

			where1 = "SELECT distinct (partner_c_id) FROM J_BASE_PARTNER WHERE partner_id = " + p_id;
		}
		sql += where1;
		sql += ") CONNECT BY PRIOR partner_id = par_c_id ) c on t.c_id = c.partner_c_id";

		sql += " order by t.goods_id, t.bill_type, t.opr_date desc, t.price ";

		logger.info("sql============={}", sql);

		List<?> list = super.getFacade().getBaseReportService().getBaseReportForArray(sql);
		return list;
	}

	public List<JBasePartner> getEntityList(Long cust_id) {

		List<JBasePartner> entityList = new ArrayList<JBasePartner>();

		KonkaR3Shop shop = new KonkaR3Shop();
		shop.setId(cust_id);
		shop = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(shop);

		if (null != shop) {

			JBasePartner entity = new JBasePartner();

			if (shop.getIs_konka() == 0) {
				JBasePartner jp = new JBasePartner();
				jp.setPartner_c_id(cust_id);
				//jp.setIs_del(0);
				List<JBasePartner> jpList = super.getFacade().getJBasePartnerService().getJBasePartnerList(jp);
				if (jpList.size() > 0) {
					entity.getMap().put("is_not_konka", jpList.get(0).getPartner_id());
				} else {
					entity.getMap().put("is_not_konka", -1);
				}

			} else {
				entity.getMap().put("is_konka", cust_id);

				// 如果是R3客户，增加客户的编号和名称
				JBasePartner jbp = new JBasePartner();
				jbp.setPartner_id(cust_id);
				jbp.setPartner_name(shop.getCustomer_name());
				entityList.add(jbp);
			}

			entity.setPartner_type(1);
			entity.setPartner_obj(1);
		//	entity.setIs_del(0);
			List<JBasePartner> entityList1 = super.getFacade().getJBasePartnerService()
					.getJBasePartnerForSonPaginatedList(entity);
			entityList.addAll(entityList1);

		} else {
			entityList = null;
		}
		return entityList;
	}

}