package com.ebiz.mmt.web.struts.manager.admin;

import java.io.OutputStream;
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

import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.domain.chart.BaseChart;
import com.ebiz.mmt.domain.chart.MsLineChart;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;

/**
 * @author Liu,ZhiXiang
 * @version 2013-07-05
 */
public class KonkaR3OrderRankAction extends BaseAction {
    public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
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
                dynaBean.set("dept_type", "1");
                dynaBean.set("rank_type", "200");
                dynaBean.set("dept_id", konkaDept.getDept_id().toString());
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

    public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        setNaviStringToRequestScope(form, request);
        DynaBean dynaBean = (DynaBean) form;

        String rank_type = (String) dynaBean.get("rank_type");
        Date today = new Date();

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);

        // dynaBean.set("year_start", String.valueOf(calendar.get(Calendar.YEAR)));
        // dynaBean.set("year_end", String.valueOf(calendar.get(Calendar.YEAR)));
        dynaBean.set("year", String.valueOf(calendar.get(Calendar.YEAR)));
        dynaBean.set("month_start", String.valueOf(calendar.get(Calendar.MONTH) + 1));
        dynaBean.set("month_end", String.valueOf(calendar.get(Calendar.MONTH) + 1));

        calendar.add(Calendar.DATE, -30);
        String day_first = df.format(calendar.getTime());
        String day_last = df.format(today);
        dynaBean.set("sell_date_start", day_first);
        dynaBean.set("sell_date_end", day_last);

        dynaBean.set("tj_type", "1");// 默认按销售额排名

        PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
        if (null == peProdUser) {
            String msg = super.getMessage(request, "user.session.isEmpty");
            super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
            return null;
        } else if (peProdUser.getUser_type() == 0) {
            // 康佳总部
            dynaBean.set("dept_id", "28");// 深圳分公司
        }

        if (StringUtils.isNotBlank(rank_type) && "100".equals(rank_type)) {
            // 100:分公司排名
            return this.list_100(mapping, form, request, response);
        } else if (StringUtils.isNotBlank(rank_type) && "200".equals(rank_type)) {
            // 200:经办排名
            // dynaBean.set("dept_id", "28");// 深圳分公司
            return this.list_200(mapping, form, request, response);
        } else if (StringUtils.isNotBlank(rank_type) && "300".equals(rank_type)) {
            // 300:业务员排名
            return this.list_300(mapping, form, request, response);
        } else if (StringUtils.isNotBlank(rank_type) && "400".equals(rank_type)) {
            // 400:客户排名
            return this.list_400(mapping, form, request, response);
        }
        return null;
    }

    public ActionForward list_100(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        setNaviStringToRequestScope(form, request);
        DynaBean dynaBean = (DynaBean) form;
        String rank_type = (String) dynaBean.get("rank_type");
        String tj_type = (String) dynaBean.get("tj_type");// 1:按销售额排名，2:按销售量排名，3:按任务完成排名
        String show_top = (String) dynaBean.get("show_top");// 排名显示数量
        // if (StringUtils.isBlank(tj_type)) {
        tj_type = "3";
        dynaBean.set("tj_type", "3");
        // }
        // String sell_date_start = (String) dynaBean.get("sell_date_start");
        // String sell_date_end = (String) dynaBean.get("sell_date_end");
        String year = (String) dynaBean.get("year");
        String month_start = (String) dynaBean.get("month_start");
        String month_end = (String) dynaBean.get("month_end");
        String year_start = year;
        String year_end = year;
        log.info("*********month_start处理前：" + month_start);
        if (StringUtils.isNotBlank(month_start)) {
            switch (Integer.valueOf(month_start)) {
                case 1:
                    month_start = "01";
                    break;
                case 2:
                    month_start = "02";
                    break;
                case 3:
                    month_start = "03";
                    break;
                case 4:
                    month_start = "04";
                    break;
                case 5:
                    month_start = "05";
                    break;
                case 6:
                    month_start = "06";
                    break;
                case 7:
                    month_start = "07";
                    break;
                case 8:
                    month_start = "08";
                    break;
                case 9:
                    month_start = "09";
                    break;
                default:
                    break;
            }
        }
        log.info("*********month_start处理后：" + month_start);
        if (StringUtils.isNotBlank(month_end)) {
            switch (Integer.valueOf(month_end)) {
                case 1:
                    month_end = "01";
                    break;
                case 2:
                    month_end = "02";
                    break;
                case 3:
                    month_end = "03";
                    break;
                case 4:
                    month_end = "04";
                    break;
                case 5:
                    month_end = "05";
                    break;
                case 6:
                    month_end = "06";
                    break;
                case 7:
                    month_end = "07";
                    break;
                case 8:
                    month_end = "08";
                    break;
                case 9:
                    month_end = "09";
                    break;
                default:
            }
        }

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

        List<?> entityList = this.getR3OrderRankForRatio_100(rank_type, tj_type, year_start, month_start, year_end, month_end, dept_id, show_top);
        request.setAttribute("entityList", entityList);
        request.setAttribute("char_x_heigth", entityList.size() * 30);
        return new ActionForward("/admin/KonkaR3OrderRank/list_100.jsp");

    }

    public ActionForward chart_100(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        DynaBean dynaBean = (DynaBean) form;

        String rank_type = (String) dynaBean.get("rank_type");
        String tj_type = (String) dynaBean.get("tj_type");// 1:按销售额排名，2:按销售量排名，3:按任务完成排名
        String show_top = (String) dynaBean.get("show_top");// 排名显示数量
        if (StringUtils.isBlank(tj_type)) {
            tj_type = "3";
        }
        // String sell_date_start = (String) dynaBean.get("sell_date_start");
        // String sell_date_end = (String) dynaBean.get("sell_date_end");
        String year = (String) dynaBean.get("year");
        String month_start = (String) dynaBean.get("month_start");
        String month_end = (String) dynaBean.get("month_end");
        String year_start = year;
        String year_end = year;
        if (StringUtils.isNotBlank(month_start)) {
            switch (Integer.valueOf(month_start)) {
                case 1:
                    month_start = "01";
                    break;
                case 2:
                    month_start = "02";
                    break;
                case 3:
                    month_start = "03";
                    break;
                case 4:
                    month_start = "04";
                    break;
                case 5:
                    month_start = "05";
                    break;
                case 6:
                    month_start = "06";
                    break;
                case 7:
                    month_start = "07";
                    break;
                case 8:
                    month_start = "08";
                    break;
                case 9:
                    month_start = "09";
                    break;
                default:
                    break;
            }
        }
        if (StringUtils.isNotBlank(month_end)) {
            switch (Integer.valueOf(month_end)) {
                case 1:
                    month_end = "01";
                    break;
                case 2:
                    month_end = "02";
                    break;
                case 3:
                    month_end = "03";
                    break;
                case 4:
                    month_end = "04";
                    break;
                case 5:
                    month_end = "05";
                    break;
                case 6:
                    month_end = "06";
                    break;
                case 7:
                    month_end = "07";
                    break;
                case 8:
                    month_end = "08";
                    break;
                case 9:
                    month_end = "09";
                    break;
                default:
                    break;
            }
        }

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

        List<?> list = this.getR3OrderRankForRatio_100(rank_type, tj_type, year_start, month_start, year_end, month_end, dept_id, show_top);
        List<MsLineChart> msLineChartList = new ArrayList<MsLineChart>();
        List<MsLineChart> mSColumn3DChartChartList = new ArrayList<MsLineChart>();
        List<BaseChart> baseChartList = new ArrayList<BaseChart>();

        if (null != list && list.size() > 0) {

            List<BaseChart> baseChartList_1 = new ArrayList<BaseChart>();
            List<BaseChart> baseChartList_2 = new ArrayList<BaseChart>();
            List<BaseChart> baseChartList_3 = new ArrayList<BaseChart>();

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
                    BaseChart baseChart2 = new BaseChart();
                    baseChart2.setValue(obj[3] == null ? "0" : obj[3].toString());
                    baseChartList_1.add(baseChart2);
                } else if ("3".equals(tj_type)) {
                    // 任务销售额
                    BaseChart baseChart1 = new BaseChart();
                    baseChart1.setValue(obj[5] == null ? "0" : obj[5].toString());
                    baseChartList_1.add(baseChart1);
                    // 实际销售额
                    BaseChart baseChart2 = new BaseChart();
                    baseChart2.setValue(obj[2] == null ? "0" : obj[2].toString());
                    baseChartList_2.add(baseChart2);
                    // 任务完成%
                    BaseChart baseChart3 = new BaseChart();
                    baseChart3.setValue(obj[6] == null ? "0" : obj[6].toString());
                    baseChartList_3.add(baseChart3);
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
                mSColumn3DChartChartList.add(msLineChart1);
            } else if ("2".equals(tj_type)) {
                //
                MsLineChart msLineChart2 = new MsLineChart();
                msLineChart2.setSeries_name("销售量（台）");
                msLineChart2.setColor("F1683C");
                msLineChart2.setAnchor_border_color("F1683C");
                msLineChart2.setAnchor_bg_color("F1683C");
                msLineChart2.setBaseChartList(baseChartList_1);
                mSColumn3DChartChartList.add(msLineChart2);
            } else if ("3".equals(tj_type)) {
                //
                MsLineChart msLineChart1 = new MsLineChart();
                msLineChart1.setSeries_name("任务销售额（万元）");
                msLineChart1.setColor("F1683C");
                msLineChart1.setAnchor_border_color("F1683C");
                msLineChart1.setAnchor_bg_color("F1683C");
                msLineChart1.setBaseChartList(baseChartList_1);
                mSColumn3DChartChartList.add(msLineChart1);
                //
                MsLineChart msLineChart2 = new MsLineChart();
                msLineChart2.setSeries_name("实际销售额（万元）");
                msLineChart2.setColor("3366FF");
                msLineChart2.setAnchor_border_color("3366FF");
                msLineChart2.setAnchor_bg_color("3366FF");
                msLineChart2.setBaseChartList(baseChartList_2);
                mSColumn3DChartChartList.add(msLineChart2);
                //
                MsLineChart msLineChart3 = new MsLineChart();
                msLineChart3.setSeries_name("任务完成%");
                msLineChart3.setColor("FFFF31");
                msLineChart3.setAnchor_border_color("FFFF31");
                msLineChart3.setAnchor_bg_color("FFFF31");
                msLineChart3.setBaseChartList(baseChartList_3);
                msLineChartList.add(msLineChart3);
            }

        }
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("baseChartList", baseChartList);

        if ("3".equals(tj_type)) {
            model.put("mSColumn3DChartChartList", mSColumn3DChartChartList);
            model.put("msLineChartList", msLineChartList);
        } else {
            model.put("mSColumn3DChartChartList", mSColumn3DChartChartList);
        }

        // model.put("unit", "单位：元");
        String caption = "销售额";
        if (StringUtils.isNotBlank(tj_type) && "2".equals(tj_type)) {
            caption = "销售量";
        } else if (StringUtils.isNotBlank(tj_type) && "3".equals(tj_type)) {
            caption = "任务完成%";
        }
        // if (StringUtils.isNotBlank(chart_type) && "2".equals(chart_type)) {
        // model.put("caption", "销售毛利分析-" + caption + "(折线图)");
        // render(response, getFacade().getTemplateService().getContent("chart/MSLine.ftl", model),
        // "text/xml;charset=GBK");
        // return null;
        // }
        model.put("caption", "R3销售排名(" + caption + ")");
        model.put("formatNumberScale", "0");
        model.put("numberSuffix", "万元");
        render(response, getFacade().getTemplateService().getContent("chart/MSStackedColumn2DLineDY.ftl", model), "text/xml;charset=GBK");
        return null;
    }

    public ActionForward list_200(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        setNaviStringToRequestScope(form, request);

        DynaBean dynaBean = (DynaBean) form;
        String rank_type = (String) dynaBean.get("rank_type");
        String tj_type = (String) dynaBean.get("tj_type");// 1:按销售额排名，2:按销售量排名，3:按任务完成排名
        String show_top = (String) dynaBean.get("show_top");// 排名显示数量
        tj_type = "3";
        dynaBean.set("tj_type", "3");

        String year = (String) dynaBean.get("year");
        String month_start = (String) dynaBean.get("month_start");
        String month_end = (String) dynaBean.get("month_end");
        String year_start = year;
        String year_end = year;
        if (StringUtils.isNotBlank(month_start)) {
            switch (Integer.valueOf(month_start)) {
                case 1:
                    month_start = "01";
                    break;
                case 2:
                    month_start = "02";
                    break;
                case 3:
                    month_start = "03";
                    break;
                case 4:
                    month_start = "04";
                    break;
                case 5:
                    month_start = "05";
                    break;
                case 6:
                    month_start = "06";
                    break;
                case 7:
                    month_start = "07";
                    break;
                case 8:
                    month_start = "08";
                    break;
                case 9:
                    month_start = "09";
                    break;
                default:
                    break;
            }
        }
        if (StringUtils.isNotBlank(month_end)) {
            switch (Integer.valueOf(month_end)) {
                case 1:
                    month_end = "01";
                    break;
                case 2:
                    month_end = "02";
                    break;
                case 3:
                    month_end = "03";
                    break;
                case 4:
                    month_end = "04";
                    break;
                case 5:
                    month_end = "05";
                    break;
                case 6:
                    month_end = "06";
                    break;
                case 7:
                    month_end = "07";
                    break;
                case 8:
                    month_end = "08";
                    break;
                case 9:
                    month_end = "09";
                    break;
                default:
                    break;
            }
        }

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

        List<?> entityList = this.getR3OrderRankForRatio_200(rank_type, tj_type, year_start, month_start, year_end, month_end, dept_id);
        request.setAttribute("entityList", entityList);
        request.setAttribute("char_x_heigth", entityList.size() * 30);
        return new ActionForward("/admin/KonkaR3OrderRank/list_200.jsp");
    }

    public ActionForward chart_200(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        DynaBean dynaBean = (DynaBean) form;

        String rank_type = (String) dynaBean.get("rank_type");
        String tj_type = (String) dynaBean.get("tj_type");// 1:按销售额排名，2:按销售量排名，3:按任务完成排名
        if (StringUtils.isBlank(tj_type)) {
            tj_type = "3";
        }
        String year = (String) dynaBean.get("year");
        String month_start = (String) dynaBean.get("month_start");
        String month_end = (String) dynaBean.get("month_end");
        String year_start = year;
        String year_end = year;
        if (StringUtils.isNotBlank(month_start)) {
            switch (Integer.valueOf(month_start)) {
                case 1:
                    month_start = "01";
                    break;
                case 2:
                    month_start = "02";
                    break;
                case 3:
                    month_start = "03";
                    break;
                case 4:
                    month_start = "04";
                    break;
                case 5:
                    month_start = "05";
                    break;
                case 6:
                    month_start = "06";
                    break;
                case 7:
                    month_start = "07";
                    break;
                case 8:
                    month_start = "08";
                    break;
                case 9:
                    month_start = "09";
                    break;
                default:
                    break;
            }
        }
        if (StringUtils.isNotBlank(month_end)) {
            switch (Integer.valueOf(month_end)) {
                case 1:
                    month_end = "01";
                    break;
                case 2:
                    month_end = "02";
                    break;
                case 3:
                    month_end = "03";
                    break;
                case 4:
                    month_end = "04";
                    break;
                case 5:
                    month_end = "05";
                    break;
                case 6:
                    month_end = "06";
                    break;
                case 7:
                    month_end = "07";
                    break;
                case 8:
                    month_end = "08";
                    break;
                case 9:
                    month_end = "09";
                    break;
                default:
                    break;
            }
        }
        String dept_id = (String) dynaBean.get("dept_id");

        List<?> list = this.getR3OrderRankForRatio_200(rank_type, tj_type, year_start, month_start, year_end, month_end, dept_id);
        List<MsLineChart> msLineChartList = new ArrayList<MsLineChart>();
        List<MsLineChart> mSColumn3DChartChartList = new ArrayList<MsLineChart>();
        List<BaseChart> baseChartList = new ArrayList<BaseChart>();

        if (null != list && list.size() > 0) {

            List<BaseChart> baseChartList_1 = new ArrayList<BaseChart>();
            List<BaseChart> baseChartList_2 = new ArrayList<BaseChart>();
            List<BaseChart> baseChartList_3 = new ArrayList<BaseChart>();

            for (int i = 0; i < list.size(); i++) {
                Object[] obj = (Object[]) list.get(i);

                BaseChart baseChart = new BaseChart();
                baseChart.setCategory_label(obj[1] == null ? "" : obj[1].toString());
                baseChartList.add(baseChart);
                // SELECT z1.dept_id,z1.dept_name, z2.ALL_NUM, z2.ALL_PRICE, z2.JB_RATIO,
                // z2.RW_MONEY, z2.SALE
                if ("1".equals(tj_type)) {
                    // 销售额
                    BaseChart baseChart1 = new BaseChart();
                    baseChart1.setValue(obj[3] == null ? "0" : obj[3].toString());
                    baseChartList_1.add(baseChart1);
                } else if ("2".equals(tj_type)) {
                    // 销售量
                    BaseChart baseChart1 = new BaseChart();
                    baseChart1.setValue(obj[2] == null ? "0" : obj[2].toString());
                    baseChartList_1.add(baseChart1);
                } else if ("3".equals(tj_type)) {
                    // 任务销售额
                    BaseChart baseChart1 = new BaseChart();
                    baseChart1.setValue(obj[5] == null ? "0" : obj[5].toString());
                    baseChartList_1.add(baseChart1);
                    // 实际销售额
                    BaseChart baseChart2 = new BaseChart();
                    baseChart2.setValue(obj[3] == null ? "0" : obj[3].toString());
                    baseChartList_2.add(baseChart2);
                    // 任务完成%
                    BaseChart baseChart3 = new BaseChart();
                    baseChart3.setValue(obj[6] == null ? "0" : obj[6].toString());
                    baseChartList_3.add(baseChart3);
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
                mSColumn3DChartChartList.add(msLineChart1);
            } else if ("2".equals(tj_type)) {
                //
                MsLineChart msLineChart1 = new MsLineChart();
                msLineChart1.setSeries_name("销售量（台）");
                msLineChart1.setColor("F1683C");
                msLineChart1.setAnchor_border_color("F1683C");
                msLineChart1.setAnchor_bg_color("F1683C");
                msLineChart1.setBaseChartList(baseChartList_1);
                mSColumn3DChartChartList.add(msLineChart1);
            } else if ("3".equals(tj_type)) {
                //
                MsLineChart msLineChart1 = new MsLineChart();
                msLineChart1.setSeries_name("任务销售额（万元）");
                msLineChart1.setColor("F1683C");
                msLineChart1.setAnchor_border_color("F1683C");
                msLineChart1.setAnchor_bg_color("F1683C");
                msLineChart1.setBaseChartList(baseChartList_1);
                mSColumn3DChartChartList.add(msLineChart1);
                //
                MsLineChart msLineChart2 = new MsLineChart();
                msLineChart2.setSeries_name("实际销售额（万元）");
                msLineChart2.setColor("3366FF");
                msLineChart2.setAnchor_border_color("3366FF");
                msLineChart2.setAnchor_bg_color("3366FF");
                msLineChart2.setBaseChartList(baseChartList_2);
                mSColumn3DChartChartList.add(msLineChart2);
                //
                MsLineChart msLineChart3 = new MsLineChart();
                msLineChart3.setSeries_name("任务完成%");
                msLineChart3.setColor("FFFF31");
                msLineChart3.setAnchor_border_color("FFFF31");
                msLineChart3.setAnchor_bg_color("FFFF31");
                msLineChart3.setBaseChartList(baseChartList_3);
                msLineChartList.add(msLineChart3);
            }

        }
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("baseChartList", baseChartList);

        if ("3".equals(tj_type)) {
            model.put("mSColumn3DChartChartList", mSColumn3DChartChartList);
            model.put("msLineChartList", msLineChartList);
        } else {
            model.put("mSColumn3DChartChartList", mSColumn3DChartChartList);
        }
        String caption = "销售额";
        if (StringUtils.isNotBlank(tj_type) && "2".equals(tj_type)) {
            caption = "销售量";
        } else if (StringUtils.isNotBlank(tj_type) && "3".equals(tj_type)) {
            caption = "任务完成%";
        }
        model.put("caption", "R3销售排名(" + caption + ")");
        model.put("formatNumberScale", "0");
        model.put("numberSuffix", "万元");
        render(response, getFacade().getTemplateService().getContent("chart/MSStackedColumn2DLineDY.ftl", model), "text/xml;charset=GBK");
        return null;
    }

    public ActionForward list_300(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
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

        List<?> entityList = this.getR3OrderRankFor300(rank_type, tj_type, sell_date_start, sell_date_end, dept_id, l4_dept_id, l5_dept_id, ywy_user_name, show_top);
        request.setAttribute("entityList", entityList);
        request.setAttribute("char_x_heigth", entityList.size() * 30);
        return new ActionForward("/admin/KonkaR3OrderRank/list_300.jsp");
    }

    public ActionForward chart_300(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
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

        List<?> list = this.getR3OrderRankFor300(rank_type, tj_type, sell_date_start, sell_date_end, dept_id, l4_dept_id, l5_dept_id, ywy_user_name, show_top);
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
        model.put("caption", "R3销售排名(" + caption + ")");
        // render(response, getFacade().getTemplateService().getContent("chart/MSBar3D.ftl", model),
        // "text/xml;charset=GBK");
        String xmlStr = getFacade().getTemplateService().getContent("chart/MSBar3D.ftl", model);
        OutputStream out = response.getOutputStream();
        out.write(xmlStr.getBytes("UTF-8"));
        out.flush();
        return null;
    }

    public ActionForward list_400(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
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

        List<?> entityList = this.getR3OrderRankFor400(rank_type, tj_type, sell_date_start, sell_date_end, dept_id, l4_dept_id, l5_dept_id, ywy_user_name, customer_name, show_top);
        request.setAttribute("entityList", entityList);
        request.setAttribute("char_x_heigth", entityList.size() * 30);
        return new ActionForward("/admin/KonkaR3OrderRank/list_400.jsp");
    }

    public ActionForward chart_400(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
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

        List<?> list = this.getR3OrderRankFor400(rank_type, tj_type, sell_date_start, sell_date_end, dept_id, l4_dept_id, l5_dept_id, ywy_user_name, customer_name, show_top);
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
        model.put("caption", "R3销售排名(" + caption + ")");
        // render(response, getFacade().getTemplateService().getContent("chart/MSBar3D.ftl", model),
        // "text/xml;charset=GBK");
        String xmlStr = getFacade().getTemplateService().getContent("chart/MSBar3D.ftl", model);
        OutputStream out = response.getOutputStream();
        out.write(xmlStr.getBytes("UTF-8"));
        out.flush();
        return null;
    }

    public List<?> getR3OrderRankFor100(String rank_type, String tj_type, String sell_date_start, String sell_date_end, String dept_id, String show_top) {
        List<String> array = new ArrayList<String>();

        String sql = " select t_.* from (select aa.* from (select t.dept_id,t.dept_name,value(sum(t.pd_total_money), 0) as all_price,value(sum(t.pd_count), 0) as all_num ";
        sql += " from v_a_details_of_purchase t where 1 = 1 ";

        if (StringUtils.isNotBlank(sell_date_start)) {
            sql += " and t.create_date >= to_date(?,'yyyy-MM-dd hh24:mi:ss') ";
            array.add(sell_date_start + " 00:00:00");
        }
        if (StringUtils.isNotBlank(sell_date_end)) {
            sql += " and t.create_date <= to_date(?,'yyyy-MM-dd hh24:mi:ss') ";
            array.add(sell_date_end + " 23:59:59");
        }
        if (StringUtils.isNotBlank(dept_id)) {
            sql += " and t.dept_id = ? ";
            array.add(dept_id);
        }

        sql += " group by t.dept_id, t.dept_name order by t.dept_name asc, t.dept_id asc) aa where 1 = 1 ";

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

    public List<?> getR3OrderRankFor200(String rank_type, String tj_type, String sell_date_start, String sell_date_end, String dept_id, String l4_dept_id, String l5_dept_id,
            String show_top) {
        List<String> array = new ArrayList<String>();

        String sql = " select t_.* from ( select aa.* from ( ";
        if (StringUtils.isNotBlank(l4_dept_id)) {
            sql +=
                    " select t.dept_id,t.dept_name,t.L4_DEPT_ID,t.L4_DEPT_NAME,t.L5_DEPT_ID,t.L5_DEPT_NAME,value(sum(t.pd_total_money), 0) as all_price,value(sum(t.pd_count), 0) as all_num ";
        } else {
            sql += " select t.dept_id,t.dept_name,t.L4_DEPT_ID,t.L4_DEPT_NAME,value(sum(t.pd_total_money), 0) as all_price,value(sum(t.pd_count), 0) as all_num ";
        }

        sql += " from v_a_details_of_purchase t where 1 = 1 and t.l4_dept_id is not null ";
        if (StringUtils.isNotBlank(l4_dept_id)) {
            sql += " and t.l5_dept_id is not null ";
        } else {
            sql += " and t.l4_dept_id is not null ";
        }
        if (StringUtils.isNotBlank(sell_date_start)) {
            sql += " and t.create_date >= to_date(?,'yyyy-MM-dd hh24:mi:ss') ";
            array.add(sell_date_start + " 00:00:00");
        }
        if (StringUtils.isNotBlank(sell_date_end)) {
            sql += " and t.create_date <= to_date(?,'yyyy-MM-dd hh24:mi:ss') ";
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
            sql += " group by t.dept_id, t.dept_name,t.L4_DEPT_ID,t.L4_DEPT_NAME,t.L5_DEPT_ID,t.L5_DEPT_NAME";
            sql += " order by t.dept_name asc, t.dept_id asc, t.L4_DEPT_ID asc,t.L4_DEPT_NAME asc,t.L5_DEPT_ID asc,t.L5_DEPT_NAME asc ";
        } else {
            sql += " group by t.dept_id, t.dept_name,t.L4_DEPT_ID,t.L4_DEPT_NAME ";
            sql += " order by t.dept_name asc, t.dept_id asc, t.L4_DEPT_ID asc,t.L4_DEPT_NAME asc ";
        }
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

    public List<?> getR3OrderRankFor300(String rank_type, String tj_type, String sell_date_start, String sell_date_end, String dept_id, String l4_dept_id, String l5_dept_id,
            String ywy_user_name, String show_top) {
        List<String> array = new ArrayList<String>();

        String sql = " select t_.* from ( select aa.* from ( ";
        sql += " select t.USER_ID,t.YWY_USER_NAME,value(sum(t.pd_total_money), 0) as all_price,value(sum(t.pd_count), 0) as all_num ";
        sql += " from v_a_details_of_purchase t where 1 = 1 and t.USER_ID is not null ";
        if (StringUtils.isNotBlank(sell_date_start)) {
            sql += " and t.create_date >= to_date(?,'yyyy-MM-dd hh24:mi:ss') ";
            array.add(sell_date_start + " 00:00:00");
        }
        if (StringUtils.isNotBlank(sell_date_end)) {
            sql += " and t.create_date <= to_date(?,'yyyy-MM-dd hh24:mi:ss') ";
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
            sql += " and t.ywy_user_name like '%'||?||'%' ";
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

    public List<?> getR3OrderRankFor400(String rank_type, String tj_type, String sell_date_start, String sell_date_end, String dept_id, String l4_dept_id, String l5_dept_id,
            String ywy_user_name, String customer_name, String show_top) {
        List<String> array = new ArrayList<String>();

        String sql = " select t_.* from ( select aa.* from ( ";
        sql += " select t.KONKA_R3_ID,t.customer_name,value(sum(t.pd_total_money), 0) as all_price,value(sum(t.pd_count), 0) as all_num ";
        sql += " from v_a_details_of_purchase t where 1 = 1 and t.KONKA_R3_ID is not null ";
        if (StringUtils.isNotBlank(sell_date_start)) {
            sql += " and t.create_date >= to_date(?,'yyyy-MM-dd hh24:mi:ss') ";
            array.add(sell_date_start + " 00:00:00");
        }
        if (StringUtils.isNotBlank(sell_date_end)) {
            sql += " and t.create_date <= to_date(?,'yyyy-MM-dd hh24:mi:ss') ";
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
            sql += " and t.ywy_user_name like '%'||?||'%' ";
            array.add(ywy_user_name);
        }
        if (StringUtils.isNotBlank(customer_name)) {
            sql += " and t.customer_name like '%'||?||'%' ";
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

    public List<?> getR3OrderRankForRatio_100(String rank_type, String tj_type, String year_start, String month_start, String year_end, String month_end, String dept_id,
            String show_top) {
        List<String> array = new ArrayList<String>();
        String sql =
                " select t_.* from ( select dd.dept_id,dd.dept_name,dd.all_price/10000 as all_price,dd.all_num,dd.fgs_ratio,dd.rw_money/10000 as rw_money,dd.sale from ( select cc.*,round(decode(cc.rw_money, 0, '0', cc.all_price / cc.rw_money), 4) * 100 as sale";
        sql +=
                " from (select kk.dept_id,kk.dept_name,value(aa.all_price,0) as all_price,value(aa.all_num,0) as all_num,value(bb.fgs_ratio,0) as fgs_ratio,value(bb.rw_money,0) as rw_money ";
        sql += " from konka_dept kk left join (select t.dept_id,t.dept_name,sum(t.pd_total_money) as all_price,sum(t.pd_count) as all_num ";
        sql += " from V_A_DETAILS_OF_PURCHASE_SaleOrg t where 1 = 1 ";

        // R3销售时间的选择 t.create_date
        if (StringUtils.isNotBlank(year_start) && StringUtils.isNotBlank(month_start)) {
            sql += " and to_char(t.create_date, 'yyyy-MM') >= ? ";
            array.add(year_start + "-" + month_start);
        }
        if (StringUtils.isNotBlank(year_end) && StringUtils.isNotBlank(month_end)) {
            sql += " and to_char(t.create_date, 'yyyy-MM') <= ? ";
            array.add(year_end + "-" + month_end);
        }
        if (StringUtils.isNotBlank(dept_id)) {
            sql += " and t.dept_id = ? ";
            array.add(dept_id);
        }
        sql += " group by t.dept_id, t.dept_name ) aa on kk.dept_id = aa.dept_id ";
        sql += " left join (select (select k.dept_id from konka_dept k where k.dept_sn = a.fgs_sn) as dept_id,round(value(sum(a.ratio), 0),6) as fgs_ratio, ";
        sql += " value(sum(a.ratio), 0) * (select value(sum(b.m), 0) from konka_plan_money b where 1 = 1  and b.p = 1 ";

        // 任务销售额的时间选择
        if (StringUtils.isNotBlank(year_start) && StringUtils.isNotBlank(month_start)) {
            sql += " and b.y >= ? ";
            array.add(year_start + month_start);
        }
        if (StringUtils.isNotBlank(year_end) && StringUtils.isNotBlank(month_end)) {
            sql += " and b.y <= ? ";
            array.add(year_end + month_end);
        }
        sql += " ) * 10000 as rw_money from konka_plan_ratio a where 1 = 1 and a.fgs_sn = a.dept_sn ";

        // 任务系数的年度选择
        if (StringUtils.isNotBlank(year_start)) {
            sql += " and a.y = ? ";
            array.add(year_start);
        }
        if (StringUtils.isNotBlank(dept_id)) {
            sql += " and a.fgs_sn = (select d.dept_sn from konka_dept d where d.dept_id = ?) ";
            array.add(dept_id);
        }
        sql += " group by a.fgs_sn) bb on kk.dept_id = bb.dept_id where 1 = 1 and kk.dept_type = 3 ";
        if (StringUtils.isNotBlank(dept_id)) {
            sql += " and kk.dept_id = ? ";
            array.add(dept_id);
        }
        sql += " ) cc ) dd where 1 = 1 ";

        if (StringUtils.isNotBlank(tj_type) && "2".equals(tj_type)) {
            sql += " order by dd.sale desc,dd.all_num desc,dd.all_price desc,dd.dept_name asc,dd.dept_id asc ";
        } else {
            sql += " order by dd.sale desc,dd.all_price desc,dd.all_num desc,dd.dept_name asc,dd.dept_id asc ";
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

    // 各分公司部门任务与实际销售排名
    private List<?> getR3OrderRankForRatio_200(String rank_type, String tj_type, String year_start, String month_start, String year_end, String month_end, String dept_id) {
        List<String> array = new ArrayList<String>();
        String sql = "";
        sql += "SELECT z1.dept_id,z1.dept_name, z2.ALL_NUM, z2.ALL_PRICE, z2.JB_RATIO, z2.RW_MONEY, z2.SALE ";
        sql += "        FROM (SELECT dept.dept_name, dept.dept_id FROM KONKA_DEPT dept ";
        sql += "        where dept.DEPT_TYPE in(3,4,5) and dept.IS_DEL = 0 ";
        if (StringUtils.isNotBlank(dept_id)) {
            sql += " START WITH dept.dept_id = ? ";
            sql += "        CONNECT BY PRIOR dept.dept_id = dept.PAR_ID) z1 ";
            array.add(dept_id);// 1268
        }
        sql += "LEFT JOIN (SELECT   x.DEPT_ID, ";
        sql += "                    x.ALL_NUM, ";
        sql += "                    round(x.ALL_PRICE / 10000, 4) all_price, ";
        sql += "                    z.JB_RATIO, ";
        sql += "                    round(z.RW_MONEY, 4) RW_MONEY, ";
        sql += "                    round(decode(z.rw_money,0,'0',x.all_price / (z.rw_money*10000)),4) AS sale ";
        sql += "            FROM ( ";// 结算数据
        sql += "            SELECT decode(a.BSC_ID,NULL,decode(a.JYB_ID,NULL,decode(a.FGS_ID, NULL, a.FGS_ID, NULL),a.JYB_ID),a.BSC_ID) dept_id, ";
        sql += "            sum(nvl(c.COLUMN_12, 0)) all_num, ";
        sql += "            sum(nvl(c.COLUMN_14, 0)) all_price ";
        sql += "            FROM BRANCH_ASSIGN a ";
        sql += "            LEFT JOIN konka_r3_shop b ON a.KONKA_R3_ID = b.id ";
        sql += "            LEFT JOIN CHANNEL_DATA_IMPORT c ON b.r3_code =c.COLUMN_1 ";
        sql += "            WHERE a.FGS_ID IS NOT NULL ";
        // --结算条件,分配绑定

        // 任务销售额的时间选择
        if (StringUtils.isNotBlank(year_start) && StringUtils.isNotBlank(month_start)) {
            sql += "            AND to_char(c.COLUMN_26, 'yyyyMM') >= ? ";
            array.add(year_start + month_start);
        }
        if (StringUtils.isNotBlank(year_end) && StringUtils.isNotBlank(month_end)) {
            sql += "            AND to_char(c.COLUMN_26, 'yyyyMM') <= ? ";
            array.add(year_end + month_end);
        }

        sql += "            AND b.is_konka = 1 ";
        sql += "            AND b.BRANCH_AREA_NAME_2 NOT IN ('KFDFD001', 'KF001', 'KFSF001') ";// 测试客户
        sql += "            AND b.IS_SDF = 0 ";
        sql += "            GROUP BY decode(a.BSC_ID,NULL,decode(a.JYB_ID,NULL,decode(a.FGS_ID,NULL,a.FGS_ID,NULL),a.JYB_ID),a.BSC_ID)";
        sql += "            ) x ";// 结算数据
        sql += "LEFT JOIN (SELECT kd.dept_id AS xx_dept_id, ";// 任务系统和任务额
        sql += "            round(value(sum(kpr.ratio), 0), 6) AS jb_ratio, ";
        sql += "            value(sum(kpr.ratio), 0) *  (SELECT value(sum(kpm.m), 0) ";
        sql += "                                                FROM konka_plan_money kpm ";
        sql += "                                                WHERE kpm.p = 1 ";
        // 任务销售额的时间选择
        if (StringUtils.isNotBlank(year_start) && StringUtils.isNotBlank(month_start)) {
            sql += "                                                AND kpm.y >= ? ";
            array.add(year_start + month_start);
        }
        if (StringUtils.isNotBlank(year_end) && StringUtils.isNotBlank(month_end)) {
            sql += "                                                AND kpm.y <= ?";
            array.add(year_end + month_end);
        }

        sql += "                                         ) AS rw_money ";
        sql += "            FROM konka_dept kd ";
        sql += "            LEFT JOIN konka_plan_ratio kpr ON kd.DEPT_SN =kpr.DEPT_SN ";
        sql += "            WHERE 1 = 1 ";
        // 任务系数的年度选择
        if (StringUtils.isNotBlank(year_start)) {
            sql += "            AND kpr.y = ? ";
            array.add(year_start);
        }
        sql += "            AND kpr.DEPT_SN IS NOT NULL ";
        sql += "            GROUP BY kpr.DEPT_SN, kd.dept_id) z ON x.DEPT_ID =z.xx_dept_id ";
        sql += "            WHERE x.DEPT_ID IS NOT NULL) z2 ON z1.dept_id = z2.DEPT_ID ";

        // log.info("**********sql:" + sql);
        // if (null != array && array.size() > 0) {
        // String array_string = "";
        // for (int i = 0; i < array.size(); i++) {
        // array_string += array.get(i) + ",";
        // }
        // log.info("**********array:" + array.size());
        // log.info("**********array_string:" + array_string);
        // }

        log.info("execute sql :" + sql);
        List<?> list = super.getFacade().getBaseReportService().getBaseReportForBindToArray(sql, array.toArray());
        return list;
    }
}
