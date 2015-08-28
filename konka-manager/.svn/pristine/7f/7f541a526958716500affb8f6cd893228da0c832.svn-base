package com.ebiz.mmt.web.struts;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.ebiz.mmt.domain.functioncharts.BaseChart;
import com.ebiz.mmt.domain.functioncharts.MSColumn3DChart;
import com.ebiz.mmt.domain.functioncharts.TrendLine;


/**
 * @author Jiang,JiaYong
 * @date 2012-09-01
 * @desc Fusioncharts图形报表数据转换控制
 */
public class FlashReportControlAction extends BaseAction {
	private HttpClient httpClient = null;

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String dataUrl = (String) dynaBean.get("dataUrl");
		String width = (String) dynaBean.get("width");
		String height = (String) dynaBean.get("height");
		String type = (String) dynaBean.get("type");
		String colspan = (String) dynaBean.get("colspan");
		String trendlines = (String) dynaBean.get("trendlines");
		String decimals = (String) dynaBean.get("decimals");
		String unitSite = (String) dynaBean.get("unitSite");
		String showBorder = (String) dynaBean.get("showBorder");
		String formatNumberScale = (String) dynaBean.get("formatNumberScale");

		request.setAttribute("dataUrl", dataUrl);
		request.setAttribute("width", width);
		request.setAttribute("height", height);
		request.setAttribute("type", type);
		request.setAttribute("colspan", colspan);
		if (StringUtils.isNotBlank(trendlines)) {
			request.setAttribute("trendlines", URLDecoder.decode(new String(trendlines.getBytes("ISO-8859-1"), "utf-8"), "utf-8"));
		}
		request.setAttribute("decimals", decimals);
		request.setAttribute("unitSite", unitSite);
		request.setAttribute("showBorder", showBorder);
		request.setAttribute("formatNumberScale", formatNumberScale);
		return mapping.findForward("list");
	}

	public ActionForward getData(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String dataUrl = (String) dynaBean.get("dataUrl"); // 原始数据url
		String colspan = (String) dynaBean.get("colspan"); // 需要显示数据的列,ex:1,3,6
		String type = (String) dynaBean.get("type"); // 显示的图标类型
		String trendlines = (String) dynaBean.get("trendlines"); // 警戒线
		String decimals = (String) dynaBean.get("decimals"); // 数字精度
		String unitSite = (String) dynaBean.get("unitSite"); // 单位前缀或者后缀
		String showBorder = (String) dynaBean.get("showBorder"); // 单位前缀或者后缀
		String formatNumberScale = (String) dynaBean.get("formatNumberScale"); // 是否进行千位格式化

		// 数据是否为空判断
		if (StringUtils.isEmpty(dataUrl) || StringUtils.isEmpty(colspan) || StringUtils.isEmpty(type)) {
			return null;
		}

		// 获取原始JSON数据
		logger.info("----dataUrl--before-->{}", dataUrl);
		String json = this.getSourceData(StringUtils.replace(dataUrl, "_!_", "&"));
		logger.info("----json---->{}", json);
		if (null == json) {
			return null;
		}

		// 将字符串转换为JSON对象
		JSONObject jsonobject = null;
		try {
			jsonobject = new JSONObject(json);
		} catch (JSONException e) {
			return null;
		}

		// 封装数据
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("ctx", super.getCtxPath(request));
		model.put("exportFileName", "null".equals(jsonobject.getString("title")) ? "" : jsonobject.getString("title")); // 导出文件名称
		model.put("caption", "null".equals(jsonobject.getString("title")) ? "" : jsonobject.getString("title")); // 主标题
		model.put("subcaption", "null".equals(jsonobject.getString("subTitle")) ? "" : jsonobject.getString("subTitle")); // 副标题
		model.put("decimals", GenericValidator.isLong(decimals) ? decimals : "2"); // 数字精度，默认为2位
		model.put("showBorder", GenericValidator.isLong(showBorder) ? showBorder : "0"); // 是否显示边框
		model.put("formatNumberScale", GenericValidator.isLong(formatNumberScale) ? formatNumberScale : "0"); // 是否进行千位格式化

		// 警戒线数据处理
		List<TrendLine> trendLineList = new ArrayList<TrendLine>();
		if (StringUtils.isNotEmpty(trendlines)) {
			trendlines = URLDecoder.decode(new String(trendlines.getBytes("ISO-8859-1"), "utf-8"), "utf-8");
			String[] trendlinesArr = StringUtils.split(trendlines, ",");
			for (String string : trendlinesArr) {
				String[] ts = StringUtils.split(string, "_");
				TrendLine trendLine = new TrendLine();
				trendLine.setName(ts[0]);
				trendLine.setValue(ts[1]);
				trendLine.setColor(ts[2]);
				if (GenericValidator.isLong(ts[3])) {
					trendLine.setColumnColor(Integer.valueOf(ts[3]));
				} else {
					trendLine.setColumnColor(0);
				}
				trendLineList.add(trendLine);
			}
		}
		model.put("trendLineList", trendLineList);

		// 表头
		JSONObject thead = jsonobject.getJSONObject("thead");
		JSONArray thead_trs = thead.getJSONArray("trs");

		// 表正文数据
		JSONObject tbody = jsonobject.getJSONObject("tbody");
		JSONArray tbody_trs = tbody.getJSONArray("trs");

		// 单柱状、多柱状图，单曲线、多曲线图数据处理
		if ("h".equals(type) || "l".equals(type)) {

			// 处理X轴的显示数据
			List<BaseChart> baseChartList = new ArrayList<BaseChart>();
			for (int i = 0; i < tbody_trs.length(); i++) {
				if (!"1".equals(tbody_trs.getJSONObject(i).getString("trDataType"))) { // 0或者为空(null)-正常行数据，1-合计数据
					JSONArray tbody_trs_tds = tbody_trs.getJSONObject(i).getJSONArray("tds");
					for (int j = 0; j < tbody_trs_tds.length(); j++) {
						JSONObject jo = tbody_trs_tds.getJSONObject(j);
						if ("1".equals(jo.getString("tdDataType"))) { // 0或者为空(null)-正常表格数据，1-标题
							BaseChart baseChart = new BaseChart();
							baseChart.setCategory_label(jo.getString("text"));

							baseChartList.add(baseChart);
						}
					}
				}
			}
			model.put("baseChartList", baseChartList);

			// 处理Y轴的数据，这里跟指定的 colspan 值相关
			List<MSColumn3DChart> mSColumn3DChartChartList = new ArrayList<MSColumn3DChart>();
			String[] colspans = StringUtils.split(colspan, ","); // 需要显示数据的列
			for (String col : colspans) {
				String[] cols = StringUtils.split(col, "_");
				if (!GenericValidator.isLong(cols[0])) { // 是否数字检测
					return null;
				}

				// 处理分类
				MSColumn3DChart mSColumn3DChart = new MSColumn3DChart();
				// 判断和处理THEAD是否存在，不存在以第一个数据为分类
				if (0 == thead_trs.length()) {
					mSColumn3DChart.setSeries_name(cols[0]);
				} else { // 表格头存在则处理需要显示的列
					JSONArray thead_trs_tds = thead_trs.getJSONObject(0).getJSONArray("tds");
					mSColumn3DChart.setSeries_name(thead_trs_tds.getJSONObject(Integer.valueOf(cols[0]) - 1).getString("text"));
				}

				// 处理分类数据
				List<BaseChart> mSColumn3DBaseChartList = new ArrayList<BaseChart>();
				for (int i = 0; i < tbody_trs.length(); i++) {
					if (!"1".equals(tbody_trs.getJSONObject(i).getString("trDataType"))) { // 0或者为空(null)-正常行数据，1-合计数据，这里过滤合计数据
						JSONArray tbody_trs_tds = tbody_trs.getJSONObject(i).getJSONArray("tds");

						BaseChart baseChart = new BaseChart();
						baseChart.setValue(tbody_trs_tds.getJSONObject(Integer.valueOf(cols[0]) - 1).getString("text"));
						baseChart.setLink(tbody_trs_tds.getJSONObject(Integer.valueOf(cols[0]) - 1).getString("link"));
						// 处理柱状图的颜色是否跟警示线的颜色相同
						if(GenericValidator.isDouble(baseChart.getValue())){
							for (TrendLine t : trendLineList) {
								// 预警线的颜色，作为小于预警值“柱状”的颜色（正数）
								if(t.getColumnColor() == 1 && Double.valueOf(t.getValue()) >= 0d && Double.valueOf(baseChart.getValue()) >= 0d && Double.valueOf(baseChart.getValue()) <= Double.valueOf(t.getValue())){
									baseChart.setColor(t.getColor());
								}
								
								// 预警线的颜色，作为大于预警值“柱状”的颜色（正数）
								if(t.getColumnColor() == 2 && Double.valueOf(t.getValue()) >= 0d && Double.valueOf(baseChart.getValue()) >= 0d && Double.valueOf(baseChart.getValue()) >= Double.valueOf(t.getValue())){
									baseChart.setColor(t.getColor());
								}
								
								// 预警线的颜色，作为大于预警值“柱状”的颜色（负数）
								if(t.getColumnColor() == -1 && Double.valueOf(t.getValue()) <= 0d && Double.valueOf(baseChart.getValue()) <= 0d && Double.valueOf(baseChart.getValue()) >= Double.valueOf(t.getValue())){
									baseChart.setColor(t.getColor());
								}
								
								// 预警线的颜色，作为小于预警值“柱状”的颜色（负数）
								if(t.getColumnColor() == -2 && Double.valueOf(t.getValue()) <= 0d && Double.valueOf(baseChart.getValue()) <= 0d && Double.valueOf(baseChart.getValue()) <= Double.valueOf(t.getValue())){
									baseChart.setColor(t.getColor());
							    }
							}
						}
						
						mSColumn3DBaseChartList.add(baseChart);

						// 处理数据前缀后缀
						String unit = tbody_trs_tds.getJSONObject(Integer.valueOf(cols[0]) - 1).getString("unit");
						if (StringUtils.isNotBlank(unit) && !"null".equals(unit)) {
							if (StringUtils.isNotEmpty(unitSite) && "1".equals(unitSite)) {
								model.put("numberSuffix", unit); // 数字后缀
							} else {
								model.put("numberPrefix", unit); // 数字前缀
							}
						}
					}
					mSColumn3DChart.setBaseChartList(mSColumn3DBaseChartList);
				}
				mSColumn3DChartChartList.add(mSColumn3DChart);
			}
			model.put("mSColumn3DChartChartList", mSColumn3DChartChartList);

			// 转换数据为XML输出
			String xmlData = getFacade().getTemplateService().getContent("chart/MSColumn3D_H_L.ftl", model);
			logger.info("----xmlData--MSColumn3D_H_L->{}", xmlData);
			renderXmlWithEncoding(response, xmlData, "GBK");
			return null;
		}

		// 饼状图数据处理，colspans为多个数据的时候只有第一个列数据有效，比如(4,5,8)有效数字为4或者(4_p,5_p)
		if ("p".equals(type)) {
			String[] colspans = StringUtils.split(colspan, ","); // 需要显示数据的列
			String[] cols = StringUtils.split(colspans[0], "_");
			if (!GenericValidator.isLong(cols[0])) { // 是否数字检测
				return null;
			}

			// 处理X轴的显示数据
			List<BaseChart> baseChartList = new ArrayList<BaseChart>();
			for (int i = 0; i < tbody_trs.length(); i++) {
				if (!"1".equals(tbody_trs.getJSONObject(i).getString("trDataType"))) { // 0或者为空(null)-正常行数据，1-合计数据
					JSONArray tbody_trs_tds = tbody_trs.getJSONObject(i).getJSONArray("tds");
					for (int j = 0; j < tbody_trs_tds.length(); j++) {
						JSONObject jo = tbody_trs_tds.getJSONObject(j);
						if ("1".equals(jo.getString("tdDataType"))) { // 0或者为空(null)-正常表格数据，1-标题
							BaseChart baseChart = new BaseChart();
							baseChart.setLabel(jo.getString("text"));
							baseChart.setValue(tbody_trs_tds.getJSONObject(Integer.valueOf(cols[0]) - 1).getString("text"));
							baseChart.setLink(tbody_trs_tds.getJSONObject(Integer.valueOf(cols[0]) - 1).getString("link"));
							baseChart.setIs_sliced(0);
							baseChartList.add(baseChart);
						}
					}
				}
			}
			model.put("baseChartList", baseChartList);

			// 转换数据为XML输出
			String xmlData = getFacade().getTemplateService().getContent("chart/Pie3D.ftl", model);
			logger.info("----xmlData-pie3d-->{}", xmlData);
			renderXmlWithEncoding(response, xmlData, "GBK");
			return null;
		}

		// 单柱状、多柱状图，单曲线、多曲线图数据处理
		if ("hl".equals(type) || "lh".equals(type)) {

			// 处理X轴的显示数据
			List<BaseChart> baseChartList = new ArrayList<BaseChart>();
			for (int i = 0; i < tbody_trs.length(); i++) {
				if (!"1".equals(tbody_trs.getJSONObject(i).getString("trDataType"))) { // 0或者为空(null)-正常行数据，1-合计数据
					JSONArray tbody_trs_tds = tbody_trs.getJSONObject(i).getJSONArray("tds");
					for (int j = 0; j < tbody_trs_tds.length(); j++) {
						JSONObject jo = tbody_trs_tds.getJSONObject(j);
						if ("1".equals(jo.getString("tdDataType"))) { // 0或者为空(null)-正常表格数据，1-标题
							BaseChart baseChart = new BaseChart();
							baseChart.setCategory_label(jo.getString("text"));

							baseChartList.add(baseChart);
						}
					}
				}
			}
			model.put("baseChartList", baseChartList);

			// 处理Y轴的数据，这里跟指定的 colspan 值相关
			List<MSColumn3DChart> mSColumn3DChartChartList_p = new ArrayList<MSColumn3DChart>();
			List<MSColumn3DChart> mSColumn3DChartChartList_s = new ArrayList<MSColumn3DChart>();
			String[] colspans = StringUtils.split(colspan, ","); // 需要显示数据的列
			for (String col : colspans) {
				String[] cols = StringUtils.split(col, "_");
				if (cols.length != 2 || !GenericValidator.isLong(cols[0])) { // 是否数字检测
					return null;
				}

				// 处理分类
				MSColumn3DChart mSColumn3DChart = new MSColumn3DChart();
				// 判断和处理THEAD是否存在，不存在以需要显示的列为分类
				if (0 == thead_trs.length()) {
					mSColumn3DChart.setSeries_name(cols[0]);
				} else { // 表格头存在则处理需要显示的列
					JSONArray thead_trs_tds = thead_trs.getJSONObject(0).getJSONArray("tds");
					mSColumn3DChart.setSeries_name(thead_trs_tds.getJSONObject(Integer.valueOf(cols[0]) - 1).getString("text"));
				}

				// 处理分类数据
				List<BaseChart> mSColumn3DBaseChartList = new ArrayList<BaseChart>();
				for (int i = 0; i < tbody_trs.length(); i++) {
					if (!"1".equals(tbody_trs.getJSONObject(i).getString("trDataType"))) { // 0或者为空(null)-正常行数据，1-合计数据，这里过滤合计数据
						JSONArray tbody_trs_tds = tbody_trs.getJSONObject(i).getJSONArray("tds");

						BaseChart baseChart = new BaseChart();
						baseChart.setValue(tbody_trs_tds.getJSONObject(Integer.valueOf(cols[0]) - 1).getString("text"));

						mSColumn3DBaseChartList.add(baseChart);

						// 处理数据前缀后缀
						String unit = tbody_trs_tds.getJSONObject(Integer.valueOf(cols[0]) - 1).getString("unit");
						if (StringUtils.isNotBlank(unit) && !"null".equals(unit) && ("p".equals(cols[1]) || "P".equals(cols[1]))) {
							if (StringUtils.isNotEmpty(unitSite) && "1".equals(unitSite)) {
								model.put("numberSuffix", unit); // 数字后缀
							} else {
								model.put("numberPrefix", unit); // 数字前缀
							}
						}
					}
					mSColumn3DChart.setBaseChartList(mSColumn3DBaseChartList);
				}

				if ("p".equals(cols[1]) || "P".equals(cols[1])) {
					mSColumn3DChartChartList_p.add(mSColumn3DChart);
				}
				if ("s".equals(cols[1]) || "S".equals(cols[1])) {
					mSColumn3DChartChartList_s.add(mSColumn3DChart);
				}
			}
			model.put("mSColumn3DChartChartList_p", mSColumn3DChartChartList_p);
			model.put("mSColumn3DChartChartList_s", mSColumn3DChartChartList_s);

			// 转换数据为XML输出
			String xmlData = getFacade().getTemplateService().getContent("chart/MSColumn3D_H_L_Merge.ftl", model);
			logger.info("----xmlData--MSColumn3D_H_L_Merge->{}", xmlData);
			renderXmlWithEncoding(response, xmlData, "GBK");
			return null;
		}

		return null;
	}

	private String getSourceData(String dataUrl) {
		logger.info("----dataUrl--after-->{}", dataUrl);
		HttpResponse response = executeGetMethod(dataUrl);
		if (response != null && response.getEntity() != null) {
			String json = null;
			try {
				json = EntityUtils.toString(response.getEntity());
			} catch (IOException e) {
				logger.error(e.getMessage());
				logger.error("url = " + dataUrl);
				logger.error("method = getData");
			}

			return json;
		}
		return null;
	}

	private HttpClient getHttpClient() {
		if (httpClient == null) {
			HttpParams params = new BasicHttpParams();
			// Increase max total connection to 200
			ConnManagerParams.setMaxTotalConnections(params, 200);
			// Increase default max connection per route to 20
			ConnPerRouteBean connPerRoute = new ConnPerRouteBean(20);
			// Increase max connections for localhost:80 to 50
			HttpHost localhost = new HttpHost("locahost", 80);
			connPerRoute.setMaxForRoute(new HttpRoute(localhost), 50);
			ConnManagerParams.setMaxConnectionsPerRoute(params, connPerRoute);

			SchemeRegistry schemeRegistry = new SchemeRegistry();
			schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
			schemeRegistry.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), 443));

			ClientConnectionManager cm = new ThreadSafeClientConnManager(params, schemeRegistry);
			httpClient = new DefaultHttpClient(cm, params);
		}
		return httpClient;
	}

	private HttpResponse executeGetMethod(String url) {
		try {
			HttpGet httpget = new HttpGet(url);
			HttpResponse response = getHttpClient().execute(httpget);
			return response;
		} catch (ClientProtocolException e) {
			logger.error("method = executeGetMethod, url = {}", url);
			logger.error("ClientProtocolException : " + e.getMessage());
		} catch (IOException e) {
			logger.error("method = executeGetMethod, url = {}", url);
		}
		return null;
	}
}
