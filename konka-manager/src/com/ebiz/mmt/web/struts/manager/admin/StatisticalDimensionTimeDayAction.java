package com.ebiz.mmt.web.struts.manager.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.StatisticalDimensionTimeDay;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Xiao,GuoJian
 * @version 2014-11-25
 */
public class StatisticalDimensionTimeDayAction extends BaseAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		super.getModPopeDom(form, request);

		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String mod_id = (String) dynaBean.get("mod_id");
		String day_start = (String) dynaBean.get("day_start");
		String day_end = (String) dynaBean.get("day_end");
		String week = (String) dynaBean.get("week");
		String month = (String) dynaBean.get("month");
		String quarter = (String) dynaBean.get("quarter");
		String year = (String) dynaBean.get("year");
		String month_cw = (String) dynaBean.get("month_cw");
		String quarter_cw = (String) dynaBean.get("quarter_cw");
		String year_cw = (String) dynaBean.get("year_cw");
		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);

		StatisticalDimensionTimeDay entity = new StatisticalDimensionTimeDay();
		entity.setIs_del(0);
		if (GenericValidator.isInt(week)) {
			entity.setWeek(Integer.parseInt(week));
		}
		if (GenericValidator.isInt(month)) {
			entity.setMonth(Integer.parseInt(month));
		}
		if (GenericValidator.isInt(quarter)) {
			entity.setQuarter(Integer.parseInt(quarter));
		}
		if (GenericValidator.isInt(year)) {
			entity.setYear(Integer.parseInt(year));
		}
		if (GenericValidator.isInt(month_cw)) {
			entity.setMonth_cw(Integer.parseInt(month_cw));
		}
		if (GenericValidator.isInt(quarter_cw)) {
			entity.setQuarter_cw(Integer.parseInt(quarter_cw));
		}
		if (GenericValidator.isInt(year_cw)) {
			entity.setYear_cw(Integer.parseInt(year_cw));
		}
		if (StringUtils.isNotBlank(day_start)) {
			entity.getMap().put("day_start", day_start + " 00:00:00");
		}
		if (StringUtils.isNotBlank(day_end)) {
			entity.getMap().put("day_end", day_end + " 23:59:59");
		}

		Long recordCount = getFacade().getStatisticalDimensionTimeDayService().getStatisticalDimensionTimeDayCount(
				entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<StatisticalDimensionTimeDay> entityList = super.getFacade().getStatisticalDimensionTimeDayService()
				.getStatisticalDimensionTimeDayPaginatedList(entity);

		request.setAttribute("entityList", entityList);

		return mapping.findForward("list");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// if (null == super.checkUserModPopeDom(form, request, "1")) {//
		// 左边菜单栏导航树
		// return super.checkPopedomInvalid(request, response);
		// }
		saveToken(request);
		setNaviStringToRequestScope(form, request);// 导航
		// //System.out.println("==================1=================");
		// super.getFacade().getStatisticalDimensionTimeDayService().initStatisticalDimensionTimeDay();
		// //System.out.println("==================2=================");
		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		resetToken(request);

		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String type = (String) dynaBean.get("type");
		String start_date = (String) dynaBean.get("start_date");
		String end_date = (String) dynaBean.get("end_date");
		String month_cw = (String) dynaBean.get("month_cw");
		String quarter_cw = (String) dynaBean.get("quarter_cw");
		String year_cw = (String) dynaBean.get("year_cw");

		StatisticalDimensionTimeDay entity = new StatisticalDimensionTimeDay();
		if (StringUtils.isBlank(start_date)) {
			super.renderJavaScript(response, "alert('开始时间不能为空！');history.back();");
			return null;
		}
		if (StringUtils.isBlank(end_date)) {
			super.renderJavaScript(response, "alert('结束时间不能为空！');history.back();");
			return null;
		}
		if (StringUtils.isBlank(type)) {
			super.renderJavaScript(response, "alert('维护类别不能为空！');history.back();");
			return null;
		} else {// 不为空的话，判断是什么类型
			if ("1".equals(type)) {
				entity.setMonth_cw(Integer.parseInt(month_cw));
			} else if ("2".equals(type)) {
				entity.setQuarter_cw(Integer.parseInt(quarter_cw));
			} else if ("3".equals(type)) {
				entity.setYear_cw(Integer.parseInt(year_cw));
			} else if ("4".equals(type)) {
				entity.setSpring_festival(1);
			}
		}

		entity.getMap().put("start_date", start_date + " 00:00:00");
		entity.getMap().put("end_date", end_date + " 00:00:00");

		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
		super.getFacade().getStatisticalDimensionTimeDayService().modifyStatisticalDimensionTimeDayByDate(entity);
		saveMessage(request, "entity.updated");

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		return forward;
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "2")) {
			return super.checkPopedomInvalid(request, response);
		}
		saveToken(request);
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		StatisticalDimensionTimeDay entity = new StatisticalDimensionTimeDay();
		entity.setId(Long.valueOf(id));
		entity = super.getFacade().getStatisticalDimensionTimeDayService().getStatisticalDimensionTimeDay(entity);

		if (null == entity) {
			saveMessage(request, "entity.missing");
			return mapping.findForward("list");
		}
		entity.setQueryString(super.serialize(request, "id", "method"));
		super.copyProperties(form, entity);

		return mapping.findForward("input");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}
		saveToken(request);
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");

		StatisticalDimensionTimeDay entity = new StatisticalDimensionTimeDay();
		entity.setId(Long.valueOf(id));
		entity = super.getFacade().getStatisticalDimensionTimeDayService().getStatisticalDimensionTimeDay(entity);

		if (null == entity) {
			saveMessage(request, "entity.missing");
			return mapping.findForward("list");
		}
		entity.setQueryString(super.serialize(request, "id", "method"));
		super.copyProperties(form, entity);

		return mapping.findForward("view");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "4")) {
			return super.checkPopedomInvalid(request, response);
		}

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String[] pks = (String[]) dynaBean.get("pks");
		String mod_id = (String) dynaBean.get("mod_id");

		if (!StringUtils.isBlank(id) && GenericValidator.isLong(id)) {
			StatisticalDimensionTimeDay entity = new StatisticalDimensionTimeDay();
			entity.setId(Long.valueOf(id));
			getFacade().getStatisticalDimensionTimeDayService().removeStatisticalDimensionTimeDay(entity);
			saveMessage(request, "entity.deleted");
		} else if (!ArrayUtils.isEmpty(pks)) {
			StatisticalDimensionTimeDay entity = new StatisticalDimensionTimeDay();
			entity.getMap().put("pks", pks);
			getFacade().getStatisticalDimensionTimeDayService().removeStatisticalDimensionTimeDay(entity);
			saveMessage(request, "entity.deleted");
		}
		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&").append("mod_id=").append(mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "id", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

}
