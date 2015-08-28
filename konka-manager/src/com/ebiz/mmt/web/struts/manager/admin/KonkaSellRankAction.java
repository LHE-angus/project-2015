package com.ebiz.mmt.web.struts.manager.admin;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ebiz.mmt.domain.*;
import com.ebiz.mmt.web.util.StringHelper;
import com.ebiz.ssi.util.EncryptUtils;
import net.sf.json.JSONArray;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.chart.BaseChart;
import com.ebiz.mmt.domain.chart.MsLineChart;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;

/**
 * @author Liu,ZhiXiang
 * @version 2013-07-02
 */
public class KonkaSellRankAction extends BaseAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;

		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		if (null == peProdUser) {
			String msg = super.getMessage(request, "user.session.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		} else if (peProdUser.getUser_type() == 0) {
			// 康佳总部
			dynaBean.set("dept_type", "1");
			dynaBean.set("rank_type", "100");
			return this.list(mapping, form, request, response);
		} else if (peProdUser.getUser_type() == 1) {
			// 康佳分公司
			KonkaDept konkaDept = new KonkaDept();
			konkaDept.setDept_id(peProdUser.getDept_id());
			konkaDept = super.getFacade().getKonkaDeptService().getKonkaDept(konkaDept);
			if (null == konkaDept) {
				String msg = super.getMessage(request, "popedom.check.invalid");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			} else if (konkaDept.getDept_type() == 3) {

				PeRoleUser p = new PeRoleUser();
				p.setRole_id(new Long(34));// 分公司总经理
				p.setUser_id(peProdUser.getId());
				List<?> list_ru = super.getFacade().getPeRoleUserService().getPeRoleUserList(p);
				if (null != list_ru && list_ru.size() > 0) {
					// 分公司总经理
					dynaBean.set("role_id", "34");
					dynaBean.set("rank_type", "100");
					return this.list(mapping, form, request, response);
				}
				// 分公司
				dynaBean.set("dept_type", "3");
				dynaBean.set("rank_type", "200");
				return this.list(mapping, form, request, response);
			} else if (konkaDept.getDept_type() == 4 || konkaDept.getDept_type() == 5) {
				// 经营处、办事处
				String msg = super.getMessage(request, "popedom.check.invalid");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			} else {
				String msg = super.getMessage(request, "popedom.check.invalid");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			}
		}
		return null;
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;

		String rank_type = (String) dynaBean.get("rank_type");// 100:零售分公司排名
		Date today = new Date();

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(today);

		calendar.add(Calendar.DATE, -30);
		String day_first = df.format(calendar.getTime());
		String day_last = df.format(today);
		dynaBean.set("sell_date_start", day_first);// 默认当前日期的前30天
		dynaBean.set("sell_date_end", day_last);

		dynaBean.set("tj_type", "1");// 默认按销售额排名
		if (StringUtils.isNotBlank(rank_type) && "100".equals(rank_type)) {
			// 100:零售分公司排名
			return this.list_100(mapping, form, request, response);
		} else if (StringUtils.isNotBlank(rank_type) && "200".equals(rank_type)) {
			// 200:零售经办排名
			return this.list_200(mapping, form, request, response);
		} else if (StringUtils.isNotBlank(rank_type) && "300".equals(rank_type)) {
			// 300:零售业务员排名
			return this.list_300(mapping, form, request, response);
		} else if (StringUtils.isNotBlank(rank_type) && "400".equals(rank_type)) {
			// 400:零售客户排名
			return this.list_400(mapping, form, request, response);
		} else if (StringUtils.isNotBlank(rank_type) && "500".equals(rank_type)) {
			// 500:零售促销员排名
			return this.list_500(mapping, form, request, response);
		}
		return null;
	}

	public ActionForward list_100(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String rank_type = (String) dynaBean.get("rank_type");
		String tj_type = (String) dynaBean.get("tj_type");// 1:按销售额排名，2:按销售量排名
		String show_top = (String) dynaBean.get("show_top");// 排名显示数量
		if (StringUtils.isBlank(tj_type)) {
			tj_type = "1";
			dynaBean.set("tj_type", "1");
		}
		String sell_date_start = (String) dynaBean.get("sell_date_start");
		String sell_date_end = (String) dynaBean.get("sell_date_end");

		String dept_id = "";
		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		if (null == peProdUser) {
			String msg = super.getMessage(request, "user.session.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		} else if (peProdUser.getUser_type() == 0) {
			// 康佳总部
			dynaBean.set("dept_type", "1");
		} else if (peProdUser.getUser_type() == 1) {
			// 康佳分公司
			KonkaDept konkaDept = new KonkaDept();
			konkaDept.setDept_id(peProdUser.getDept_id());
			konkaDept = super.getFacade().getKonkaDeptService().getKonkaDept(konkaDept);
			if (null == konkaDept) {
				String msg = super.getMessage(request, "popedom.check.invalid");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			} else if (konkaDept.getDept_type() == 3) {
				PeRoleUser p = new PeRoleUser();
				p.setRole_id(new Long(34));// 分公司总经理
				p.setUser_id(peProdUser.getId());
				List<?> list_ru = super.getFacade().getPeRoleUserService().getPeRoleUserList(p);
				if (null != list_ru && list_ru.size() > 0) {
					// 分公司总经理
					dynaBean.set("role_id", "34");
				} else {
					// 分公司
					dynaBean.set("dept_type", "3");
					dynaBean.set("dept_id", konkaDept.getDept_id().toString());
					dept_id = konkaDept.getDept_id().toString();
				}
			} else if (konkaDept.getDept_type() == 4 || konkaDept.getDept_type() == 5) {
				// 经营处、办事处
				String msg = super.getMessage(request, "popedom.check.invalid");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			} else {
				String msg = super.getMessage(request, "popedom.check.invalid");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			}
		}

		List<?> entityList = this.getSellRankFor100(rank_type, tj_type, sell_date_start, sell_date_end, dept_id,
				show_top);
		request.setAttribute("entityList", entityList);
		request.setAttribute("char_x_heigth", entityList.size() * 30);
		return new ActionForward("/admin/KonkaSellRank/list_100.jsp");

	}

	public ActionForward chart_100(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;

		String rank_type = (String) dynaBean.get("rank_type");
		String tj_type = (String) dynaBean.get("tj_type");// 1:按销售额排名，2:按销售量排名
		String show_top = (String) dynaBean.get("show_top");// 排名显示数量
		if (StringUtils.isBlank(tj_type)) {
			tj_type = "1";
		}
		String sell_date_start = (String) dynaBean.get("sell_date_start");
		String sell_date_end = (String) dynaBean.get("sell_date_end");

		String dept_id = "";
		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		if (null == peProdUser) {
			String msg = super.getMessage(request, "user.session.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		} else if (peProdUser.getUser_type() == 0) {
			// 康佳总部
			dynaBean.set("dept_type", "1");
		} else if (peProdUser.getUser_type() == 1) {
			// 康佳分公司
			KonkaDept konkaDept = new KonkaDept();
			konkaDept.setDept_id(peProdUser.getDept_id());
			konkaDept = super.getFacade().getKonkaDeptService().getKonkaDept(konkaDept);
			if (null == konkaDept) {
				String msg = super.getMessage(request, "popedom.check.invalid");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			} else if (konkaDept.getDept_type() == 3) {
				PeRoleUser p = new PeRoleUser();
				p.setRole_id(new Long(34));// 分公司总经理
				p.setUser_id(peProdUser.getId());
				List<?> list_ru = super.getFacade().getPeRoleUserService().getPeRoleUserList(p);
				if (null != list_ru && list_ru.size() > 0) {
					// 分公司总经理
					dynaBean.set("role_id", "34");
				} else {
					// 分公司
					dynaBean.set("dept_type", "3");
					dynaBean.set("dept_id", konkaDept.getDept_id().toString());
					dept_id = konkaDept.getDept_id().toString();
				}
			} else if (konkaDept.getDept_type() == 4 || konkaDept.getDept_type() == 5) {
				// 经营处、办事处
				String msg = super.getMessage(request, "popedom.check.invalid");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			} else {
				String msg = super.getMessage(request, "popedom.check.invalid");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			}
		}

		List<?> list = this.getSellRankFor100(rank_type, tj_type, sell_date_start, sell_date_end, dept_id, show_top);
		List<MsLineChart> msLineChartList = new ArrayList<MsLineChart>();
		List<BaseChart> baseChartList = new ArrayList<BaseChart>();

		if (null != list && list.size() > 0) {

			List<BaseChart> baseChartList_1 = new ArrayList<BaseChart>();
			// List<BaseChart> baseChartList_2 = new ArrayList<BaseChart>();

			for (int i = 0; i < list.size(); i++) {
				Object[] obj = (Object[]) list.get(i);
				BaseChart baseChart = new BaseChart();
				baseChart.setCategory_label(obj[1] == null ? "" : obj[1].toString());

				baseChartList.add(baseChart);

				if ("1".equals(tj_type)) {
					// 销售额
					BaseChart baseChart1 = new BaseChart();
					baseChart1.setValue(obj[2] == null ? "0" : obj[2].toString());
					baseChartList_1.add(baseChart1);
				} else if ("2".equals(tj_type)) {
					// 销售量
					BaseChart baseChart1 = new BaseChart();
					baseChart1.setValue(obj[3] == null ? "0" : obj[3].toString());
					baseChartList_1.add(baseChart1);
				}

			}

			if ("1".equals(tj_type)) {
				//
				MsLineChart msLineChart1 = new MsLineChart();
				msLineChart1.setSeries_name("销售额（元）");
				msLineChart1.setColor("3366FF");
				msLineChart1.setAnchor_border_color("3366FF");
				msLineChart1.setAnchor_bg_color("3366FF");
				msLineChart1.setBaseChartList(baseChartList_1);
				msLineChartList.add(msLineChart1);
			} else if ("2".equals(tj_type)) {
				//
				MsLineChart msLineChart1 = new MsLineChart();
				msLineChart1.setSeries_name("销售量（台）");
				msLineChart1.setColor("F1683C");
				msLineChart1.setAnchor_border_color("F1683C");
				msLineChart1.setAnchor_bg_color("F1683C");
				msLineChart1.setBaseChartList(baseChartList_1);
				msLineChartList.add(msLineChart1);
			}

		}
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("baseChartList", baseChartList);
		model.put("msLineChartList", msLineChartList);
		model.put("mSColumn3DChartChartList", msLineChartList);
		// model.put("unit", "单位：元");
		String caption = "销售额";
		if (StringUtils.isNotBlank(tj_type) && "2".equals(tj_type)) {
			caption = "销售量";
		}
		// if (StringUtils.isNotBlank(chart_type) && "2".equals(chart_type)) {
		// model.put("caption", "销售毛利分析-" + caption + "(折线图)");
		// render(response, getFacade().getTemplateService().getContent("chart/MSLine.ftl", model),
		// "text/xml;charset=GBK");
		// return null;
		// }
		model.put("caption", "零售排名(" + caption + ")");
		render(response, getFacade().getTemplateService().getContent("chart/MSBar3D.ftl", model),
				"text/xml;charset=GBK");
		return null;
	}

	public ActionForward list_200(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String rank_type = (String) dynaBean.get("rank_type");
		String tj_type = (String) dynaBean.get("tj_type");// 1:按销售额排名，2:按销售量排名
		String show_top = (String) dynaBean.get("show_top");// 排名显示数量
		if (StringUtils.isBlank(tj_type)) {
			tj_type = "1";
			dynaBean.set("tj_type", "1");
		}
		String sell_date_start = (String) dynaBean.get("sell_date_start");
		String sell_date_end = (String) dynaBean.get("sell_date_end");
		String dept_id = (String) dynaBean.get("dept_id");
		String l4_dept_id = (String) dynaBean.get("l4_dept_id");
		String l5_dept_id = (String) dynaBean.get("l5_dept_id");

		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		if (null == peProdUser) {
			String msg = super.getMessage(request, "user.session.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		} else if (peProdUser.getUser_type() == 0) {
			// 康佳总部
			dynaBean.set("dept_type", "1");
		} else if (peProdUser.getUser_type() == 1) {
			// 康佳分公司
			KonkaDept konkaDept = new KonkaDept();
			konkaDept.setDept_id(peProdUser.getDept_id());
			konkaDept = super.getFacade().getKonkaDeptService().getKonkaDept(konkaDept);
			if (null == konkaDept) {
				String msg = super.getMessage(request, "popedom.check.invalid");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			} else if (konkaDept.getDept_type() == 3) {
				PeRoleUser p = new PeRoleUser();
				p.setRole_id(new Long(34));// 分公司总经理
				p.setUser_id(peProdUser.getId());
				List<?> list_ru = super.getFacade().getPeRoleUserService().getPeRoleUserList(p);
				if (null != list_ru && list_ru.size() > 0) {
					// 分公司总经理
					dynaBean.set("role_id", "34");
				}
				// 分公司
				dynaBean.set("dept_type", "3");
				dynaBean.set("dept_id", konkaDept.getDept_id().toString());
				dept_id = konkaDept.getDept_id().toString();
			} else if (konkaDept.getDept_type() == 4 || konkaDept.getDept_type() == 5) {
				// 经营处、办事处
				String msg = super.getMessage(request, "popedom.check.invalid");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			} else {
				String msg = super.getMessage(request, "popedom.check.invalid");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			}
		}

		List<?> entityList = this.getSellRankFor200(rank_type, tj_type, sell_date_start, sell_date_end, dept_id,
				l4_dept_id, l5_dept_id, show_top);
		request.setAttribute("entityList", entityList);
		request.setAttribute("char_x_heigth", entityList.size() * 30);
		return new ActionForward("/admin/KonkaSellRank/list_200.jsp");

	}

	public ActionForward chart_200(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;

		String rank_type = (String) dynaBean.get("rank_type");
		String tj_type = (String) dynaBean.get("tj_type");// 1:按销售额排名，2:按销售量排名
		String show_top = (String) dynaBean.get("show_top");// 排名显示数量
		if (StringUtils.isBlank(tj_type)) {
			tj_type = "1";
		}
		String sell_date_start = (String) dynaBean.get("sell_date_start");
		String sell_date_end = (String) dynaBean.get("sell_date_end");
		String dept_id = (String) dynaBean.get("dept_id");
		String l4_dept_id = (String) dynaBean.get("l4_dept_id");
		String l5_dept_id = (String) dynaBean.get("l5_dept_id");

		List<?> list = this.getSellRankFor200(rank_type, tj_type, sell_date_start, sell_date_end, dept_id, l4_dept_id,
				l5_dept_id, show_top);
		List<MsLineChart> msLineChartList = new ArrayList<MsLineChart>();
		List<BaseChart> baseChartList = new ArrayList<BaseChart>();

		if (null != list && list.size() > 0) {

			List<BaseChart> baseChartList_1 = new ArrayList<BaseChart>();
			// List<BaseChart> baseChartList_2 = new ArrayList<BaseChart>();

			for (int i = 0; i < list.size(); i++) {
				Object[] obj = (Object[]) list.get(i);
				if (StringUtils.isNotBlank(l4_dept_id)) {
					BaseChart baseChart = new BaseChart();
					baseChart.setCategory_label(obj[5] == null ? "" : obj[5].toString());
					baseChartList.add(baseChart);

					if ("1".equals(tj_type)) {
						// 销售额
						BaseChart baseChart1 = new BaseChart();
						baseChart1.setValue(obj[6] == null ? "0" : obj[6].toString());
						baseChartList_1.add(baseChart1);
					} else if ("2".equals(tj_type)) {
						// 销售量
						BaseChart baseChart1 = new BaseChart();
						baseChart1.setValue(obj[7] == null ? "0" : obj[7].toString());
						baseChartList_1.add(baseChart1);
					}

				} else {
					BaseChart baseChart = new BaseChart();
					baseChart.setCategory_label(obj[3] == null ? "" : obj[3].toString());
					baseChartList.add(baseChart);

					if ("1".equals(tj_type)) {
						// 销售额
						BaseChart baseChart1 = new BaseChart();
						baseChart1.setValue(obj[4] == null ? "0" : obj[4].toString());
						baseChartList_1.add(baseChart1);
					} else if ("2".equals(tj_type)) {
						// 销售量
						BaseChart baseChart1 = new BaseChart();
						baseChart1.setValue(obj[5] == null ? "0" : obj[5].toString());
						baseChartList_1.add(baseChart1);
					}

				}

			}

			if ("1".equals(tj_type)) {
				//
				MsLineChart msLineChart1 = new MsLineChart();
				msLineChart1.setSeries_name("销售额（元）");
				msLineChart1.setColor("3366FF");
				msLineChart1.setAnchor_border_color("3366FF");
				msLineChart1.setAnchor_bg_color("3366FF");
				msLineChart1.setBaseChartList(baseChartList_1);
				msLineChartList.add(msLineChart1);
			} else if ("2".equals(tj_type)) {
				//
				MsLineChart msLineChart1 = new MsLineChart();
				msLineChart1.setSeries_name("销售量（台）");
				msLineChart1.setColor("F1683C");
				msLineChart1.setAnchor_border_color("F1683C");
				msLineChart1.setAnchor_bg_color("F1683C");
				msLineChart1.setBaseChartList(baseChartList_1);
				msLineChartList.add(msLineChart1);
			}

		}
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("baseChartList", baseChartList);
		model.put("msLineChartList", msLineChartList);
		model.put("mSColumn3DChartChartList", msLineChartList);
		// model.put("unit", "单位：元");
		String caption = "销售额";
		if (StringUtils.isNotBlank(tj_type) && "2".equals(tj_type)) {
			caption = "销售量";
		}
		// if (StringUtils.isNotBlank(chart_type) && "2".equals(chart_type)) {
		// model.put("caption", "销售毛利分析-" + caption + "(折线图)");
		// render(response, getFacade().getTemplateService().getContent("chart/MSLine.ftl", model),
		// "text/xml;charset=GBK");
		// return null;
		// }
		model.put("caption", "零售排名(" + caption + ")");
		render(response, getFacade().getTemplateService().getContent("chart/MSBar3D.ftl", model),
				"text/xml;charset=GBK");
		return null;
	}

	public ActionForward list_300(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String rank_type = (String) dynaBean.get("rank_type");
		String tj_type = (String) dynaBean.get("tj_type");// 1:按销售额排名，2:按销售量排名
		String show_top = (String) dynaBean.get("show_top");// 排名显示数量
		if (StringUtils.isBlank(tj_type)) {
			tj_type = "1";
			dynaBean.set("tj_type", "1");
		}
		String sell_date_start = (String) dynaBean.get("sell_date_start");
		String sell_date_end = (String) dynaBean.get("sell_date_end");
		String dept_id = (String) dynaBean.get("dept_id");
		String l4_dept_id = (String) dynaBean.get("l4_dept_id");
		String l5_dept_id = (String) dynaBean.get("l5_dept_id");
		String ywy_user_name = (String) dynaBean.get("ywy_user_name");

		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		if (null == peProdUser) {
			String msg = super.getMessage(request, "user.session.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		} else if (peProdUser.getUser_type() == 0) {
			// 康佳总部
			dynaBean.set("dept_type", "1");
		} else if (peProdUser.getUser_type() == 1) {
			// 康佳分公司
			KonkaDept konkaDept = new KonkaDept();
			konkaDept.setDept_id(peProdUser.getDept_id());
			konkaDept = super.getFacade().getKonkaDeptService().getKonkaDept(konkaDept);
			if (null == konkaDept) {
				String msg = super.getMessage(request, "popedom.check.invalid");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			} else if (konkaDept.getDept_type() == 3) {
				PeRoleUser p = new PeRoleUser();
				p.setRole_id(new Long(34));// 分公司总经理
				p.setUser_id(peProdUser.getId());
				List<?> list_ru = super.getFacade().getPeRoleUserService().getPeRoleUserList(p);
				if (null != list_ru && list_ru.size() > 0) {
					// 分公司总经理
					dynaBean.set("role_id", "34");
				}
				// 分公司
				dynaBean.set("dept_type", "3");
				dynaBean.set("dept_id", konkaDept.getDept_id().toString());
				dept_id = konkaDept.getDept_id().toString();
			} else if (konkaDept.getDept_type() == 4 || konkaDept.getDept_type() == 5) {
				// 经营处、办事处
				String msg = super.getMessage(request, "popedom.check.invalid");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			} else {
				String msg = super.getMessage(request, "popedom.check.invalid");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			}
		}

		List<?> entityList = this.getSellRankFor300(rank_type, tj_type, sell_date_start, sell_date_end, dept_id,
				l4_dept_id, l5_dept_id, ywy_user_name, show_top);
		request.setAttribute("entityList", entityList);
		request.setAttribute("char_x_heigth", entityList.size() * 30);
		return new ActionForward("/admin/KonkaSellRank/list_300.jsp");

	}

	public ActionForward chart_300(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;

		String rank_type = (String) dynaBean.get("rank_type");
		String tj_type = (String) dynaBean.get("tj_type");// 1:按销售额排名，2:按销售量排名
		String show_top = (String) dynaBean.get("show_top");// 排名显示数量
		if (StringUtils.isBlank(tj_type)) {
			tj_type = "1";
		}
		String sell_date_start = (String) dynaBean.get("sell_date_start");
		String sell_date_end = (String) dynaBean.get("sell_date_end");
		String dept_id = (String) dynaBean.get("dept_id");
		String l4_dept_id = (String) dynaBean.get("l4_dept_id");
		String l5_dept_id = (String) dynaBean.get("l5_dept_id");
		String ywy_user_name = (String) dynaBean.get("ywy_user_name");

		if (StringUtils.isNotBlank(ywy_user_name)) {
			ywy_user_name = new String(ywy_user_name.getBytes("iso-8859-1"), "UTF-8");
		}

		List<?> list = this.getSellRankFor300(rank_type, tj_type, sell_date_start, sell_date_end, dept_id, l4_dept_id,
				l5_dept_id, ywy_user_name, show_top);
		List<MsLineChart> msLineChartList = new ArrayList<MsLineChart>();
		List<BaseChart> baseChartList = new ArrayList<BaseChart>();

		if (null != list && list.size() > 0) {

			List<BaseChart> baseChartList_1 = new ArrayList<BaseChart>();
			// List<BaseChart> baseChartList_2 = new ArrayList<BaseChart>();

			for (int i = 0; i < list.size(); i++) {
				Object[] obj = (Object[]) list.get(i);

				BaseChart baseChart = new BaseChart();
				baseChart.setCategory_label(obj[1] == null ? "" : obj[1].toString());
				baseChartList.add(baseChart);

				if ("1".equals(tj_type)) {
					// 销售额
					BaseChart baseChart1 = new BaseChart();
					baseChart1.setValue(obj[2] == null ? "0" : obj[2].toString());
					baseChartList_1.add(baseChart1);
				} else if ("2".equals(tj_type)) {
					// 销售量
					BaseChart baseChart1 = new BaseChart();
					baseChart1.setValue(obj[3] == null ? "0" : obj[3].toString());
					baseChartList_1.add(baseChart1);
				}

			}

			if ("1".equals(tj_type)) {
				//
				MsLineChart msLineChart1 = new MsLineChart();
				msLineChart1.setSeries_name("销售额（元）");
				msLineChart1.setColor("3366FF");
				msLineChart1.setAnchor_border_color("3366FF");
				msLineChart1.setAnchor_bg_color("3366FF");
				msLineChart1.setBaseChartList(baseChartList_1);
				msLineChartList.add(msLineChart1);
			} else if ("2".equals(tj_type)) {
				//
				MsLineChart msLineChart1 = new MsLineChart();
				msLineChart1.setSeries_name("销售量（台）");
				msLineChart1.setColor("F1683C");
				msLineChart1.setAnchor_border_color("F1683C");
				msLineChart1.setAnchor_bg_color("F1683C");
				msLineChart1.setBaseChartList(baseChartList_1);
				msLineChartList.add(msLineChart1);
			}

		}
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("baseChartList", baseChartList);
		model.put("msLineChartList", msLineChartList);
		model.put("mSColumn3DChartChartList", msLineChartList);
		// model.put("unit", "单位：元");
		String caption = "销售额";
		if (StringUtils.isNotBlank(tj_type) && "2".equals(tj_type)) {
			caption = "销售量";
		}
		// if (StringUtils.isNotBlank(chart_type) && "2".equals(chart_type)) {
		// model.put("caption", "销售毛利分析-" + caption + "(折线图)");
		// render(response, getFacade().getTemplateService().getContent("chart/MSLine.ftl", model),
		// "text/xml;charset=GBK");
		// return null;
		// }
		model.put("caption", "零售排名(" + caption + ")");
		// render(response, getFacade().getTemplateService().getContent("chart/MSBar3D.ftl", model),
		// "text/xml;charset=GBK");
		String xmlStr = getFacade().getTemplateService().getContent("chart/MSBar3D.ftl", model);
		OutputStream out = response.getOutputStream();
		out.write(xmlStr.getBytes("UTF-8"));
		out.flush();
		return null;
	}

	public ActionForward list_400(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String rank_type = (String) dynaBean.get("rank_type");
		String tj_type = (String) dynaBean.get("tj_type");// 1:按销售额排名，2:按销售量排名
		String show_top = (String) dynaBean.get("show_top");// 排名显示数量
		if (StringUtils.isBlank(tj_type)) {
			tj_type = "1";
			dynaBean.set("tj_type", "1");
		}
		String sell_date_start = (String) dynaBean.get("sell_date_start");
		String sell_date_end = (String) dynaBean.get("sell_date_end");
		String dept_id = (String) dynaBean.get("dept_id");
		String l4_dept_id = (String) dynaBean.get("l4_dept_id");
		String l5_dept_id = (String) dynaBean.get("l5_dept_id");
		String ywy_user_name = (String) dynaBean.get("ywy_user_name");
		String customer_name = (String) dynaBean.get("customer_name");

		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		if (null == peProdUser) {
			String msg = super.getMessage(request, "user.session.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		} else if (peProdUser.getUser_type() == 0) {
			// 康佳总部
			dynaBean.set("dept_type", "1");
		} else if (peProdUser.getUser_type() == 1) {
			// 康佳分公司
			KonkaDept konkaDept = new KonkaDept();
			konkaDept.setDept_id(peProdUser.getDept_id());
			konkaDept = super.getFacade().getKonkaDeptService().getKonkaDept(konkaDept);
			if (null == konkaDept) {
				String msg = super.getMessage(request, "popedom.check.invalid");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			} else if (konkaDept.getDept_type() == 3) {
				PeRoleUser p = new PeRoleUser();
				p.setRole_id(new Long(34));// 分公司总经理
				p.setUser_id(peProdUser.getId());
				List<?> list_ru = super.getFacade().getPeRoleUserService().getPeRoleUserList(p);
				if (null != list_ru && list_ru.size() > 0) {
					// 分公司总经理
					dynaBean.set("role_id", "34");
				}
				// 分公司
				dynaBean.set("dept_type", "3");
				dynaBean.set("dept_id", konkaDept.getDept_id().toString());
				dept_id = konkaDept.getDept_id().toString();
			} else if (konkaDept.getDept_type() == 4 || konkaDept.getDept_type() == 5) {
				// 经营处、办事处
				String msg = super.getMessage(request, "popedom.check.invalid");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			} else {
				String msg = super.getMessage(request, "popedom.check.invalid");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			}
		}

		List<?> entityList = this.getSellRankFor400(rank_type, tj_type, sell_date_start, sell_date_end, dept_id,
				l4_dept_id, l5_dept_id, ywy_user_name, customer_name, show_top);
		request.setAttribute("entityList", entityList);
		request.setAttribute("char_x_heigth", entityList.size() * 30);
		return new ActionForward("/admin/KonkaSellRank/list_400.jsp");

	}

	public ActionForward chart_400(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;

		String rank_type = (String) dynaBean.get("rank_type");
		String tj_type = (String) dynaBean.get("tj_type");// 1:按销售额排名，2:按销售量排名
		String show_top = (String) dynaBean.get("show_top");// 排名显示数量
		if (StringUtils.isBlank(tj_type)) {
			tj_type = "1";
		}
		String sell_date_start = (String) dynaBean.get("sell_date_start");
		String sell_date_end = (String) dynaBean.get("sell_date_end");
		String dept_id = (String) dynaBean.get("dept_id");
		String l4_dept_id = (String) dynaBean.get("l4_dept_id");
		String l5_dept_id = (String) dynaBean.get("l5_dept_id");
		String ywy_user_name = (String) dynaBean.get("ywy_user_name");
		String customer_name = (String) dynaBean.get("customer_name");

		if (StringUtils.isNotBlank(ywy_user_name)) {
			ywy_user_name = new String(ywy_user_name.getBytes("iso-8859-1"), "UTF-8");
		}
		if (StringUtils.isNotBlank(customer_name)) {
			customer_name = new String(customer_name.getBytes("iso-8859-1"), "UTF-8");
		}

		List<?> list = this.getSellRankFor400(rank_type, tj_type, sell_date_start, sell_date_end, dept_id, l4_dept_id,
				l5_dept_id, ywy_user_name, customer_name, show_top);
		List<MsLineChart> msLineChartList = new ArrayList<MsLineChart>();
		List<BaseChart> baseChartList = new ArrayList<BaseChart>();

		if (null != list && list.size() > 0) {

			List<BaseChart> baseChartList_1 = new ArrayList<BaseChart>();
			// List<BaseChart> baseChartList_2 = new ArrayList<BaseChart>();

			for (int i = 0; i < list.size(); i++) {
				Object[] obj = (Object[]) list.get(i);

				BaseChart baseChart = new BaseChart();
				baseChart.setCategory_label(obj[1] == null ? "" : obj[1].toString());
				baseChartList.add(baseChart);

				if ("1".equals(tj_type)) {
					BaseChart baseChart1 = new BaseChart();
					baseChart1.setValue(obj[2] == null ? "0" : obj[2].toString());
					baseChartList_1.add(baseChart1);
				} else if ("2".equals(tj_type)) {
					BaseChart baseChart1 = new BaseChart();
					baseChart1.setValue(obj[3] == null ? "0" : obj[3].toString());
					baseChartList_1.add(baseChart1);
				}

			}

			if ("1".equals(tj_type)) {
				//
				MsLineChart msLineChart1 = new MsLineChart();
				msLineChart1.setSeries_name("销售额（元）");
				msLineChart1.setColor("3366FF");
				msLineChart1.setAnchor_border_color("3366FF");
				msLineChart1.setAnchor_bg_color("3366FF");
				msLineChart1.setBaseChartList(baseChartList_1);
				msLineChartList.add(msLineChart1);
			} else if ("2".equals(tj_type)) {
				//
				MsLineChart msLineChart1 = new MsLineChart();
				msLineChart1.setSeries_name("销售量（台）");
				msLineChart1.setColor("F1683C");
				msLineChart1.setAnchor_border_color("F1683C");
				msLineChart1.setAnchor_bg_color("F1683C");
				msLineChart1.setBaseChartList(baseChartList_1);
				msLineChartList.add(msLineChart1);
			}

		}
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("baseChartList", baseChartList);
		model.put("msLineChartList", msLineChartList);
		model.put("mSColumn3DChartChartList", msLineChartList);
		// model.put("unit", "单位：元");
		String caption = "销售额";
		if (StringUtils.isNotBlank(tj_type) && "2".equals(tj_type)) {
			caption = "销售量";
		}
		// if (StringUtils.isNotBlank(chart_type) && "2".equals(chart_type)) {
		// model.put("caption", "销售毛利分析-" + caption + "(折线图)");
		// render(response, getFacade().getTemplateService().getContent("chart/MSLine.ftl", model),
		// "text/xml;charset=GBK");
		// return null;
		// }
		model.put("caption", "零售排名(" + caption + ")");
		// render(response, getFacade().getTemplateService().getContent("chart/MSBar3D.ftl", model),
		// "text/xml;charset=GBK");
		String xmlStr = getFacade().getTemplateService().getContent("chart/MSBar3D.ftl", model);
		OutputStream out = response.getOutputStream();
		out.write(xmlStr.getBytes("UTF-8"));
		out.flush();
		return null;
	}

	public ActionForward list_500(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String rank_type = (String) dynaBean.get("rank_type");
		String tj_type = (String) dynaBean.get("tj_type");// 1:按销售额排名，2:按销售量排名
		String show_top = (String) dynaBean.get("show_top");// 排名显示数量
		if (StringUtils.isBlank(tj_type)) {
			tj_type = "1";
			dynaBean.set("tj_type", "1");
		}
		String sell_date_start = (String) dynaBean.get("sell_date_start");
		String sell_date_end = (String) dynaBean.get("sell_date_end");
		String dept_id = (String) dynaBean.get("dept_id");
		String l4_dept_id = (String) dynaBean.get("l4_dept_id");
		String l5_dept_id = (String) dynaBean.get("l5_dept_id");
		String ywy_user_name = (String) dynaBean.get("ywy_user_name");
		String customer_name = (String) dynaBean.get("customer_name");
		String cxy_user_name = (String) dynaBean.get("cxy_user_name");

		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		if (null == peProdUser) {
			String msg = super.getMessage(request, "user.session.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		} else if (peProdUser.getUser_type() == 0) {
			// 康佳总部
			dynaBean.set("dept_type", "1");
		} else if (peProdUser.getUser_type() == 1) {
			// 康佳分公司
			KonkaDept konkaDept = new KonkaDept();
			konkaDept.setDept_id(peProdUser.getDept_id());
			konkaDept = super.getFacade().getKonkaDeptService().getKonkaDept(konkaDept);
			if (null == konkaDept) {
				String msg = super.getMessage(request, "popedom.check.invalid");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			} else if (konkaDept.getDept_type() == 3) {
				PeRoleUser p = new PeRoleUser();
				p.setRole_id(new Long(34));// 分公司总经理
				p.setUser_id(peProdUser.getId());
				List<?> list_ru = super.getFacade().getPeRoleUserService().getPeRoleUserList(p);
				if (null != list_ru && list_ru.size() > 0) {
					// 分公司总经理
					dynaBean.set("role_id", "34");
				}
				// 分公司
				dynaBean.set("dept_type", "3");
				dynaBean.set("dept_id", konkaDept.getDept_id().toString());
				dept_id = konkaDept.getDept_id().toString();
			} else if (konkaDept.getDept_type() == 4 || konkaDept.getDept_type() == 5) {
				// 经营处、办事处
				String msg = super.getMessage(request, "popedom.check.invalid");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			} else {
				String msg = super.getMessage(request, "popedom.check.invalid");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			}
		}

		List<?> entityList = this.getSellRankFor500(rank_type, tj_type, sell_date_start, sell_date_end, dept_id,
				l4_dept_id, l5_dept_id, ywy_user_name, customer_name, cxy_user_name, show_top);
		request.setAttribute("entityList", entityList);
		request.setAttribute("char_x_heigth", entityList.size() * 30);
		return new ActionForward("/admin/KonkaSellRank/list_500.jsp");

	}

	public ActionForward chart_500(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;

		String rank_type = (String) dynaBean.get("rank_type");
		String tj_type = (String) dynaBean.get("tj_type");// 1:按销售额排名，2:按销售量排名
		String show_top = (String) dynaBean.get("show_top");// 排名显示数量
		if (StringUtils.isBlank(tj_type)) {
			tj_type = "1";
		}
		String sell_date_start = (String) dynaBean.get("sell_date_start");
		String sell_date_end = (String) dynaBean.get("sell_date_end");
		String dept_id = (String) dynaBean.get("dept_id");
		String l4_dept_id = (String) dynaBean.get("l4_dept_id");
		String l5_dept_id = (String) dynaBean.get("l5_dept_id");
		String ywy_user_name = (String) dynaBean.get("ywy_user_name");
		String customer_name = (String) dynaBean.get("customer_name");
		String cxy_user_name = (String) dynaBean.get("cxy_user_name");

		if (StringUtils.isNotBlank(ywy_user_name)) {
			ywy_user_name = new String(ywy_user_name.getBytes("iso-8859-1"), "UTF-8");
		}
		if (StringUtils.isNotBlank(customer_name)) {
			customer_name = new String(customer_name.getBytes("iso-8859-1"), "UTF-8");
		}
		if (StringUtils.isNotBlank(cxy_user_name)) {
			cxy_user_name = new String(cxy_user_name.getBytes("iso-8859-1"), "UTF-8");
		}

		List<?> list = this.getSellRankFor500(rank_type, tj_type, sell_date_start, sell_date_end, dept_id, l4_dept_id,
				l5_dept_id, ywy_user_name, customer_name, cxy_user_name, show_top);
		List<MsLineChart> msLineChartList = new ArrayList<MsLineChart>();
		List<BaseChart> baseChartList = new ArrayList<BaseChart>();

		if (null != list && list.size() > 0) {

			List<BaseChart> baseChartList_1 = new ArrayList<BaseChart>();
			// List<BaseChart> baseChartList_2 = new ArrayList<BaseChart>();

			for (int i = 0; i < list.size(); i++) {
				Object[] obj = (Object[]) list.get(i);

				BaseChart baseChart = new BaseChart();
				baseChart.setCategory_label(obj[1] == null ? "" : obj[1].toString());
				baseChartList.add(baseChart);

				if ("1".equals(tj_type)) {
					BaseChart baseChart1 = new BaseChart();
					baseChart1.setValue(obj[2] == null ? "0" : obj[2].toString());
					baseChartList_1.add(baseChart1);
				} else if ("2".equals(tj_type)) {
					BaseChart baseChart1 = new BaseChart();
					baseChart1.setValue(obj[3] == null ? "0" : obj[3].toString());
					baseChartList_1.add(baseChart1);
				}

			}

			if ("1".equals(tj_type)) {
				//
				MsLineChart msLineChart1 = new MsLineChart();
				msLineChart1.setSeries_name("销售额（元）");
				msLineChart1.setColor("3366FF");
				msLineChart1.setAnchor_border_color("3366FF");
				msLineChart1.setAnchor_bg_color("3366FF");
				msLineChart1.setBaseChartList(baseChartList_1);
				msLineChartList.add(msLineChart1);
			} else if ("2".equals(tj_type)) {
				//
				MsLineChart msLineChart1 = new MsLineChart();
				msLineChart1.setSeries_name("销售量（台）");
				msLineChart1.setColor("F1683C");
				msLineChart1.setAnchor_border_color("F1683C");
				msLineChart1.setAnchor_bg_color("F1683C");
				msLineChart1.setBaseChartList(baseChartList_1);
				msLineChartList.add(msLineChart1);
			}

		}
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("baseChartList", baseChartList);
		model.put("msLineChartList", msLineChartList);
		model.put("mSColumn3DChartChartList", msLineChartList);
		// model.put("unit", "单位：元");
		String caption = "销售额";
		if (StringUtils.isNotBlank(tj_type) && "2".equals(tj_type)) {
			caption = "销售量";
		}
		// if (StringUtils.isNotBlank(chart_type) && "2".equals(chart_type)) {
		// model.put("caption", "销售毛利分析-" + caption + "(折线图)");
		// render(response, getFacade().getTemplateService().getContent("chart/MSLine.ftl", model),
		// "text/xml;charset=GBK");
		// return null;
		// }
		model.put("caption", "零售排名(" + caption + ")");
		// render(response, getFacade().getTemplateService().getContent("chart/MSBar3D.ftl", model),
		// "text/xml;charset=GBK");
		String xmlStr = getFacade().getTemplateService().getContent("chart/MSBar3D.ftl", model);
		OutputStream out = response.getOutputStream();
		out.write(xmlStr.getBytes("UTF-8"));
		out.flush();
		return null;

	}

	public List<?> getSellRankFor100(String rank_type, String tj_type, String sell_date_start, String sell_date_end,
			String dept_id, String show_top) {
		List<String> array = new ArrayList<String>();

		String sql = " select t_.* from ( select bb.* from ( select k.dept_id,k.dept_name,value(aa.all_price, 0) as all_price,value(aa.all_num, 0) as all_num,decode(value(aa.all_num, 0),0,0,round(value(aa.all_price, 0)/value(aa.all_num, 0),2)) as price from konka_dept k ";
		sql += " left join ( select t.dept_id,t.dept_name,value(sum(t.all_price), 0) as all_price ,value(sum(t.num), 0) as all_num";
		sql += " from V_A_DETAILS_OF_SALES_C t where 1 = 1 ";
		if (StringUtils.isNotBlank(sell_date_start)) {
			sql += " and t.sale_date >= to_date(?,'yyyy-MM-dd hh24:mi:ss') ";
			array.add(sell_date_start + " 00:00:00");
		}
		if (StringUtils.isNotBlank(sell_date_end)) {
			sql += " and t.sale_date <= to_date(?,'yyyy-MM-dd hh24:mi:ss') ";
			array.add(sell_date_end + " 23:59:59");
		}
		if (StringUtils.isNotBlank(dept_id)) {
			sql += " and t.dept_id = ? ";
			array.add(dept_id);
		}
		sql += " group by t.dept_id, t.dept_name ";
		sql += " ) aa on k.dept_id = aa.dept_id where 1 = 1  and k.dept_type = 3 and k.is_del=0 and k.DEPT_ID not in(2147,2109,2110,2355,673) ";
		if (StringUtils.isNotBlank(dept_id)) {
			sql += " and k.dept_id = ? ";
			array.add(dept_id);
		}
		sql += " ) bb where 1 = 1";

		if (StringUtils.isNotBlank(tj_type) && "2".equals(tj_type)) {
			sql += " order by bb.all_num desc,bb.all_price desc,bb.dept_name asc,bb.dept_id asc ";
		} else {
			sql += " order by bb.all_price desc,bb.all_num desc,bb.dept_name asc,bb.dept_id asc ";
		}

		// 排名显示数量
		sql += " ) t_ where 1 = 1 ";
		if (StringUtils.isNotBlank(show_top)) {
			sql += " and rownum <= ? ";
			array.add(show_top);
		}

		log.info("**********sql:" + sql);
		if (null != array && array.size() > 0) {
			String array_string = "";
			for (int i = 0; i < array.size(); i++) {
				array_string += array.get(i) + ",";
			}
			log.info("**********array:" + array.size());
			log.info("**********array_string:" + array_string);
		}

		List<?> list = super.getFacade().getBaseReportService().getBaseReportForBindToArray(sql, array.toArray());

		return list;
	}

	public List<?> getSellRankFor200(String rank_type, String tj_type, String sell_date_start, String sell_date_end,
			String dept_id, String l4_dept_id, String l5_dept_id, String show_top) {
		List<String> array = new ArrayList<String>();

		String sql = " select t_.* from ( select bb.* from ( ";

		if (StringUtils.isNotBlank(l4_dept_id)) {
			// 办事处
			sql += " select (select k1.dept_id from konka_dept k1 where k1.dept_id = (select k4.par_id from konka_dept k4 where k4.dept_id = k.par_id)) as dept_id, ";
			sql += " (select k1.dept_name from konka_dept k1 where k1.dept_id = (select k4.par_id from konka_dept k4 where k4.dept_id = k.par_id)) as dept_name,k.par_id as l4_dept_id, ";
			sql += " (select kd.dept_name from konka_dept kd where kd.dept_id = k.par_id) as l4_dept_name,k.dept_id as l5_dept_id,k.dept_name as l5_dept_name, ";
			sql += " value(aa.all_price, 0) as all_price,value(aa.all_num, 0) as all_num,decode(value(aa.all_num, 0),0,0,round(value(aa.all_price, 0)/value(aa.all_num, 0),2)) as price from konka_dept k left join ( ";
		} else {
			// 经办
			sql += "select k.par_id as dept_id, (select kd.dept_name from konka_dept kd where kd.dept_id = k.par_id) as dept_name, k.dept_id as l4_dept_id, ";
			sql += " k.dept_name as l4_dept_name,value(aa.all_price, 0) as all_price,value(aa.all_num, 0) as all_num,decode(value(aa.all_num, 0),0,0,round(value(aa.all_price, 0)/value(aa.all_num, 0),2)) as price from konka_dept k left join ( ";

		}

		if (StringUtils.isNotBlank(l4_dept_id)) {
			// 办事处
			sql += " select t.L5_DEPT_ID,t.L5_DEPT_NAME,value(sum(t.all_price), 0) as all_price,value(sum(t.num), 0) as all_num ";
		} else {
			// 经办
			sql += " select t.L4_DEPT_ID,t.L4_DEPT_NAME,value(sum(t.all_price), 0) as all_price,value(sum(t.num), 0) as all_num ";
		}

		sql += " from V_A_DETAILS_OF_SALES_C t where 1 = 1 and t.l4_dept_id is not null ";
		if (StringUtils.isNotBlank(l4_dept_id)) {
			sql += " and t.l5_dept_id is not null ";
		} else {
			sql += " and t.l4_dept_id is not null ";
		}
		if (StringUtils.isNotBlank(sell_date_start)) {
			sql += " and t.sale_date >= to_date(?,'yyyy-MM-dd hh24:mi:ss') ";
			array.add(sell_date_start + " 00:00:00");
		}
		if (StringUtils.isNotBlank(sell_date_end)) {
			sql += " and t.sale_date <= to_date(?,'yyyy-MM-dd hh24:mi:ss') ";
			array.add(sell_date_end + " 23:59:59");
		}
		if (StringUtils.isNotBlank(dept_id)) {
			sql += " and t.dept_id = ? ";
			array.add(dept_id);
		}
		if (StringUtils.isNotBlank(l4_dept_id)) {
			sql += " and t.l4_dept_id = ? ";
			array.add(l4_dept_id);
		}
		if (StringUtils.isNotBlank(l5_dept_id)) {
			sql += " and t.l5_dept_id = ? ";
			array.add(l5_dept_id);
		}

		if (StringUtils.isNotBlank(l4_dept_id)) {
			sql += " group by t.L5_DEPT_ID,t.L5_DEPT_NAME";
			// sql +=
			// " order by t.dept_name asc, t.dept_id asc, t.L4_DEPT_ID asc,t.L4_DEPT_NAME asc,t.L5_DEPT_ID asc,t.L5_DEPT_NAME asc ";
		} else {
			sql += " group by t.L4_DEPT_ID,t.L4_DEPT_NAME ";
			// sql += " order by t.dept_name asc, t.dept_id asc, t.L4_DEPT_ID asc,t.L4_DEPT_NAME asc ";
		}
		sql += " ) aa ";

		if (StringUtils.isNotBlank(l4_dept_id)) {
			// 办事处
			sql += " on k.dept_id = aa.L5_DEPT_ID where 1 = 1 ";
			sql += " and k.dept_id in (select k5.dept_id from konka_dept k5 where k5.par_id in (select k4.dept_id from konka_dept k4 where k4.par_id in (select kd.dept_id from konka_dept kd where kd.dept_type = 3 ";
			if (StringUtils.isNotBlank(dept_id)) {
				sql += " and kd.dept_id = ? ";
				array.add(dept_id);
			}
			sql += " ) ";
			if (StringUtils.isNotBlank(l4_dept_id)) {
				sql += " and k4.dept_id = ? ";
				array.add(l4_dept_id);
			}
			sql += " ) ";
			if (StringUtils.isNotBlank(l5_dept_id)) {
				sql += " and k5.dept_id = ? ";
				array.add(l5_dept_id);
			}
			sql += " ) ";
			if (StringUtils.isNotBlank(l5_dept_id)) {
				sql += " and k.dept_id = ? ";
				array.add(l5_dept_id);
			}
		} else {
			// 经办
			sql += " on k.dept_id = aa.L4_DEPT_ID where 1 = 1 ";
			sql += " and k.par_id in (select kd.dept_id from konka_dept kd where kd.dept_type = 3 ";
			if (StringUtils.isNotBlank(dept_id)) {
				sql += " and kd.dept_id = ? ";
				array.add(dept_id);
			}
			sql += " ) ";
		}
		sql += " ) bb where 1 = 1 ";

		if (StringUtils.isNotBlank(tj_type) && "2".equals(tj_type)) {
			sql += " order by bb.all_num desc,bb.all_price desc ";
		} else {
			sql += " order by bb.all_price desc,bb.all_num desc ";
		}
		if (StringUtils.isNotBlank(l4_dept_id)) {
			// 办事处
			sql += " ,bb.dept_name asc,bb.dept_id asc,bb.l4_dept_id asc,bb.l4_dept_name asc,bb.l5_dept_id asc,bb.l5_dept_name asc ";
		} else {
			// 经办
			sql += " ,bb.dept_name asc,bb.dept_id asc,bb.l4_dept_id asc,bb.l4_dept_name asc ";
		}

		// 排名显示数量
		sql += " ) t_ where 1 = 1 ";
		if (StringUtils.isNotBlank(show_top)) {
			sql += " and rownum <= ? ";
			array.add(show_top);
		}

		log.info("**********sql:" + sql);
		if (null != array && array.size() > 0) {
			String array_string = "";
			for (int i = 0; i < array.size(); i++) {
				array_string += array.get(i) + ",";
			}
			log.info("**********array:" + array.size());
			log.info("**********array_string:" + array_string);
		}

		List<?> list = super.getFacade().getBaseReportService().getBaseReportForBindToArray(sql, array.toArray());

		return list;
	}

	public List<?> getSellRankFor300(String rank_type, String tj_type, String sell_date_start, String sell_date_end,
			String dept_id, String l4_dept_id, String l5_dept_id, String ywy_user_name, String show_top) {
		List<String> array = new ArrayList<String>();

		String sql = " select t_.* from ( select aa.*,decode(value(aa.all_num, 0),0,0,round(value(aa.all_price, 0)/value(aa.all_num, 0),2)) as price from ( ";
		sql += " select t.USER_ID,t.YWY_USER_NAME,value(sum(t.all_price), 0) as all_price,value(sum(t.num), 0) as all_num ";
		sql += " from V_A_DETAILS_OF_SALES_C t where 1 = 1 and t.USER_ID is not null ";
		if (StringUtils.isNotBlank(sell_date_start)) {
			sql += " and t.sale_date >= to_date(?,'yyyy-MM-dd hh24:mi:ss') ";
			array.add(sell_date_start + " 00:00:00");
		}
		if (StringUtils.isNotBlank(sell_date_end)) {
			sql += " and t.sale_date <= to_date(?,'yyyy-MM-dd hh24:mi:ss') ";
			array.add(sell_date_end + " 23:59:59");
		}
		if (StringUtils.isNotBlank(dept_id)) {
			sql += " and t.dept_id = ? ";
			array.add(dept_id);
		}
		if (StringUtils.isNotBlank(l4_dept_id)) {
			sql += " and t.l4_dept_id = ? ";
			array.add(l4_dept_id);
		}
		if (StringUtils.isNotBlank(l5_dept_id)) {
			sql += " and t.l5_dept_id = ? ";
			array.add(l5_dept_id);
		}
		if (StringUtils.isNotBlank(ywy_user_name)) {
			sql += " and t.ywy_user_name like '%' ||?|| '%' ";
			array.add(ywy_user_name);
		}
		sql += " group by t.USER_ID, t.YWY_USER_NAME ";
		sql += " order by t.YWY_USER_NAME asc, t.USER_ID asc ";
		sql += " ) aa where 1 = 1 ";

		if (StringUtils.isNotBlank(tj_type) && "2".equals(tj_type)) {
			sql += " order by aa.all_num desc,aa.all_price desc ";
		} else {
			sql += " order by aa.all_price desc,aa.all_num desc ";
		}

		// 排名显示数量
		sql += " ) t_ where 1 = 1 ";
		if (StringUtils.isNotBlank(show_top)) {
			sql += " and rownum <= ? ";
			array.add(show_top);
		}

		log.info("**********sql:" + sql);
		if (null != array && array.size() > 0) {
			String array_string = "";
			for (int i = 0; i < array.size(); i++) {
				array_string += array.get(i) + ",";
			}
			log.info("**********array:" + array.size());
			log.info("**********array_string:" + array_string);
		}

		List<?> list = super.getFacade().getBaseReportService().getBaseReportForBindToArray(sql, array.toArray());

		return list;
	}

	public List<?> getSellRankFor400(String rank_type, String tj_type, String sell_date_start, String sell_date_end,
			String dept_id, String l4_dept_id, String l5_dept_id, String ywy_user_name, String customer_name,
			String show_top) {
		List<String> array = new ArrayList<String>();

		String sql = " select t_.* from ( select aa.*,decode(value(aa.all_num, 0),0,0,round(value(aa.all_price, 0)/value(aa.all_num, 0),2)) as price from ( ";
		sql += " select t.KONKA_R3_ID,t.customer_name,value(sum(t.all_price), 0) as all_price,value(sum(t.num), 0) as all_num ";
		sql += " from V_A_DETAILS_OF_SALES_C t where 1 = 1 and t.KONKA_R3_ID is not null ";
		if (StringUtils.isNotBlank(sell_date_start)) {
			sql += " and t.sale_date >= to_date(?,'yyyy-MM-dd hh24:mi:ss') ";
			array.add(sell_date_start + " 00:00:00");
		}
		if (StringUtils.isNotBlank(sell_date_end)) {
			sql += " and t.sale_date <= to_date(?,'yyyy-MM-dd hh24:mi:ss') ";
			array.add(sell_date_end + " 23:59:59");
		}
		if (StringUtils.isNotBlank(dept_id)) {
			sql += " and t.dept_id = ? ";
			array.add(dept_id);
		}
		if (StringUtils.isNotBlank(l4_dept_id)) {
			sql += " and t.l4_dept_id = ? ";
			array.add(l4_dept_id);
		}
		if (StringUtils.isNotBlank(l5_dept_id)) {
			sql += " and t.l5_dept_id = ? ";
			array.add(l5_dept_id);
		}
		if (StringUtils.isNotBlank(ywy_user_name)) {
			sql += " and t.ywy_user_name like '%' ||?|| '%' ";
			array.add(ywy_user_name);
		}
		if (StringUtils.isNotBlank(customer_name)) {
			sql += " and t.customer_name like '%' ||?|| '%' ";
			array.add(customer_name);
		}
		sql += " group by t.KONKA_R3_ID, t.customer_name ";
		sql += " order by t.customer_name asc, t.KONKA_R3_ID asc ";
		sql += " ) aa where 1 = 1 ";

		if (StringUtils.isNotBlank(tj_type) && "2".equals(tj_type)) {
			sql += " order by aa.all_num desc,aa.all_price desc ";
		} else {
			sql += " order by aa.all_price desc,aa.all_num desc ";
		}

		// 排名显示数量
		sql += " ) t_ where 1 = 1 ";
		if (StringUtils.isNotBlank(show_top)) {
			sql += " and rownum <= ? ";
			array.add(show_top);
		}

		log.info("**********sql:" + sql);
		if (null != array && array.size() > 0) {
			String array_string = "";
			for (int i = 0; i < array.size(); i++) {
				array_string += array.get(i) + ",";
			}
			log.info("**********array:" + array.size());
			log.info("**********array_string:" + array_string);
		}

		List<?> list = super.getFacade().getBaseReportService().getBaseReportForBindToArray(sql, array.toArray());

		return list;
	}

	public List<?> getSellRankFor500(String rank_type, String tj_type, String sell_date_start, String sell_date_end,
			String dept_id, String l4_dept_id, String l5_dept_id, String ywy_user_name, String customer_name,
			String cxy_user_name, String show_top) {
		List<String> array = new ArrayList<String>();

		String sql = " select t_.* from ( select aa.*,decode(value(aa.all_num, 0),0,0,round(value(aa.all_price, 0)/value(aa.all_num, 0),2)) as price from ( ";
		sql += " select t.CXY_USER_ID,t.cxy_user_name,value(sum(t.all_price), 0) as all_price,value(sum(t.num), 0) as all_num ";
		sql += " from V_A_DETAILS_OF_SALES_C t where 1 = 1 and t.CXY_USER_ID is not null ";
		if (StringUtils.isNotBlank(sell_date_start)) {
			sql += " and t.sale_date >= to_date(?,'yyyy-MM-dd hh24:mi:ss') ";
			array.add(sell_date_start + " 00:00:00");
		}
		if (StringUtils.isNotBlank(sell_date_end)) {
			sql += " and t.sale_date <= to_date(?,'yyyy-MM-dd hh24:mi:ss') ";
			array.add(sell_date_end + " 23:59:59");
		}
		if (StringUtils.isNotBlank(dept_id)) {
			sql += " and t.dept_id = ? ";
			array.add(dept_id);
		}
		if (StringUtils.isNotBlank(l4_dept_id)) {
			sql += " and t.l4_dept_id = ? ";
			array.add(l4_dept_id);
		}
		if (StringUtils.isNotBlank(l5_dept_id)) {
			sql += " and t.l5_dept_id = ? ";
			array.add(l5_dept_id);
		}
		if (StringUtils.isNotBlank(ywy_user_name)) {
			sql += " and t.ywy_user_name like '%' ||?|| '%' ";
			array.add(ywy_user_name);
		}
		if (StringUtils.isNotBlank(customer_name)) {
			sql += " and t.customer_name like '%' ||?|| '%' ";
			array.add(customer_name);
		}
		if (StringUtils.isNotBlank(cxy_user_name)) {
			sql += " and t.cxy_user_name like '%' ||?|| '%' ";
			array.add(cxy_user_name);
		}
		sql += " group by t.CXY_USER_ID, t.cxy_user_name ";
		sql += " order by t.cxy_user_name asc, t.CXY_USER_ID asc ";
		sql += " ) aa where 1 = 1 ";

		if (StringUtils.isNotBlank(tj_type) && "2".equals(tj_type)) {
			sql += " order by aa.all_num desc,aa.all_price desc ";
		} else {
			sql += " order by aa.all_price desc,aa.all_num desc ";
		}

		// 排名显示数量
		sql += " ) t_ where 1 = 1 ";
		if (StringUtils.isNotBlank(show_top)) {
			sql += " and rownum <= ? ";
			array.add(show_top);
		}

		log.info("**********sql:" + sql);
		if (null != array && array.size() > 0) {
			String array_string = "";
			for (int i = 0; i < array.size(); i++) {
				array_string += array.get(i) + ",";
			}
			log.info("**********array:" + array.size());
			log.info("**********array_string:" + array_string);
		}

		List<?> list = super.getFacade().getBaseReportService().getBaseReportForBindToArray(sql, array.toArray());

		return list;
	}


	/**
	 * 改版初始化方法
	 * add by Liang Houen on 2015-08-20
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request,
							  HttpServletResponse response) throws Exception {

		HashMap allmap = new HashMap();
		//位置信息
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");

		String naviString = "";
		if (StringUtils.isNotBlank(mod_id)) {
			SysModule sysModule = new SysModule();
			sysModule.getMap().put("mod_id", mod_id);
			List<SysModule> sysModuleList = getFacade().getSysModuleService().getSysModuleList(sysModule);
			naviString = StringHelper.getNaviString(sysModuleList, " > ");
		}
		allmap.put("local_str", naviString);

		Date today = new Date();

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat mm_fmt = new SimpleDateFormat("yyyy-MM");
		String day_first = mm_fmt.format(today)+"-01";
		String day_last = df.format(today);

		allmap.put("sell_date_start", day_first);
		allmap.put("sell_date_end", day_last);

		//当前用户信息
		PeProdUser ui = super.getSessionUserInfo(request);
		if(ui!=null){
			int max_dlevel = super.getMaxDLevel(ui.getId());
			if(max_dlevel==9){
				allmap.put("dept_id", "");
			}else{
				KonkaDept dept_fgs = super.getKonkaDeptForFgs(ui.getDept_id());
				allmap.put("dept_id", dept_fgs.getDept_id());
			}
		}

		//转换为json数据
		JSONArray jsonArray = JSONArray.fromObject(allmap);

		int start =jsonArray.toString().indexOf("[");
		int end = jsonArray.toString().lastIndexOf("}");

		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(jsonArray.toString().substring(start+1, end+1));
		out.flush();
		out.close();
		return null;
	}

	/**
	 * 查询数据列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward queryDatas(ActionMapping mapping, ActionForm form, HttpServletRequest request,
									HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		Map<String, Object> m = new HashMap<String, Object>();
		String tj_type = (String) dynaBean.get("tj_type");// 1:按销售额排名，2:按销售量排名
		String show_top = (String) dynaBean.get("show_top");// 排名显示数量
		String type_name = (String) dynaBean.get("type_name");// 排名类型
		String sell_date_start = (String) dynaBean.get("sell_date_start");
		String sell_date_end = (String) dynaBean.get("sell_date_end");
		String customer_type1 = (String) dynaBean.get("customer_type1");
		String customer_type2 = (String) dynaBean.get("customer_type2");
		String dept_id = (String) dynaBean.get("dept_id");
		String l4_dept_id = (String) dynaBean.get("l4_dept_id");
		String l5_dept_id = (String) dynaBean.get("l5_dept_id");

		KonkaMobileSailData entity = new KonkaMobileSailData();
		if (StringUtils.isNotBlank(sell_date_start)) {
			entity.getMap().put("sell_date_start", sell_date_start + " 00:00:00");
		}
		if (StringUtils.isNotBlank(sell_date_end)) {
			entity.getMap().put("sell_date_end", sell_date_end + " 23:59:59");
		}
		if (StringUtils.isNotBlank(show_top)) {
			entity.getMap().put("show_top", show_top);
		}
		if("1".equals(tj_type)){
			entity.getMap().put("price_desc", true);
		}
		if("2".equals(tj_type)){
			entity.getMap().put("num_desc", true);
		}
		if (StringUtils.isNotBlank(customer_type1)) {
			entity.getMap().put("customer_type1", customer_type1);
		}
		if (StringUtils.isNotBlank(customer_type2)) {
			entity.getMap().put("customer_type2",customer_type2);
		}
		if (StringUtils.isNotBlank(dept_id)) {
			entity.getMap().put("dept_id",dept_id);
		}
		if (StringUtils.isNotBlank(l4_dept_id)) {
			entity.getMap().put("l4_dept_id",l4_dept_id);
			entity.getMap().put("l5_dept",true);
		}else{
			entity.getMap().put("l4_dept",true);
		}
		if (StringUtils.isNotBlank(l5_dept_id)) {
			entity.getMap().put("l5_dept_id",l5_dept_id);
			entity.getMap().put("l5_dept",true);
		}
		if(StringUtils.isNotBlank(type_name)){
			if("tab1".equals(type_name)){
				entity.getMap().put("tab1",true);
			}
			if("tab2".equals(type_name)){
				entity.getMap().put("tab2",true);
			}
			if("tab3".equals(type_name)){
				entity.getMap().put("tab3",true);
			}
			if("tab4".equals(type_name)){
				entity.getMap().put("tab4",true);
			}
		}

		List<HashMap> entityList = super.getFacade().getKonkaMobileSailDataService().getPMForDeptList(entity);
		//封装成JSON字符串

		m.put("dataList", entityList);
		JSONArray jsonArray = JSONArray.fromObject(m);
		int start =jsonArray.toString().indexOf("[");
		int end = jsonArray.toString().lastIndexOf("}");
		String jsonStr = jsonArray.toString().substring(start+1, end+1);
		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(jsonStr);
		out.flush();
		out.close();
		return null;
	}
}