package com.ebiz.mmt.web.struts.manager.admin;

import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.ebiz.mmt.domain.StatisticalDimensionTimeMonth;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Xiao,GuoJian
 * @version 2014-11-06
 */
public class StatisticalDimensionTimeMonthAction extends BaseAction {

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
		String year = (String) dynaBean.get("year");
		String month = (String) dynaBean.get("month");
		String is_cw = (String) dynaBean.get("is_cw");
		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);

		StatisticalDimensionTimeMonth entity = new StatisticalDimensionTimeMonth();
		entity.setIs_del(0);
		if (StringUtils.isNotBlank(year)) {
			entity.setYear(Integer.parseInt(year));
		}
		if (StringUtils.isNotBlank(month)) {
			entity.setMonth(Integer.parseInt(month));
		}
		if (StringUtils.isNotBlank(is_cw)) {
			entity.setIs_cw(Integer.parseInt(is_cw));
		}

		Long recordCount = getFacade().getStatisticalDimensionTimeMonthService().getStatisticalDimensionTimeMonthCount(
				entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<StatisticalDimensionTimeMonth> entityList = super.getFacade().getStatisticalDimensionTimeMonthService()
				.getStatisticalDimensionTimeMonthPaginatedList(entity);

		request.setAttribute("entityList", entityList);

		return mapping.findForward("list");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "1")) {// 左边菜单栏导航树
			return super.checkPopedomInvalid(request, response);
		}
		saveToken(request);
		setNaviStringToRequestScope(form, request);// 导航
		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		resetToken(request);

		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String id = (String) dynaBean.get("id");
		String year = (String) dynaBean.get("year");
		String month = (String) dynaBean.get("month");
		String start_date = (String) dynaBean.get("start_date");
		String end_date = (String) dynaBean.get("end_date");
		String is_cw = (String) dynaBean.get("is_cw");

		StatisticalDimensionTimeMonth entity = new StatisticalDimensionTimeMonth();
		if (GenericValidator.isInt(year)) {
			entity.setYear(Integer.parseInt(year));
		}
		if (GenericValidator.isInt(month)) {
			entity.setMonth(Integer.parseInt(month));
		}
		if (GenericValidator.isInt(is_cw)) {
			entity.setIs_cw(Integer.parseInt(is_cw));
		}
		entity.setIs_del(0);
		Long count = super.getFacade().getStatisticalDimensionTimeMonthService()
				.getStatisticalDimensionTimeMonthCount(entity);
		if (count != null && count > 0) {// 说明之前有这个年度，不准添加
			super.renderJavaScript(response, "alert('此年的月度已经存在，请重新添加！');history.back();");
			return null;
		}

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (StringUtils.isNotBlank(start_date) && StringUtils.isNotBlank(end_date)) {
			start_date = start_date + " 00:00:00";
			end_date = end_date + " 23:59:59";
			entity.setStart_date(dateFormat.parse(start_date));
			entity.setEnd_date(dateFormat.parse(end_date));
		}

		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
		entity.setAdd_user_id(ui.getId());
		entity.setAdd_date(new Date());
		if (GenericValidator.isLong(id)) {// 老数据更新
			entity.setId(Long.parseLong(id));
			super.getFacade().getStatisticalDimensionTimeMonthService().modifyStatisticalDimensionTimeMonth(entity);
			saveMessage(request, "entity.updated");
		} else {// 新数据插入
			super.getFacade().getStatisticalDimensionTimeMonthService().createStatisticalDimensionTimeMonth(entity);
			saveMessage(request, "entity.inserted");
		}

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
		StatisticalDimensionTimeMonth entity = new StatisticalDimensionTimeMonth();
		entity.setId(Long.valueOf(id));
		entity = super.getFacade().getStatisticalDimensionTimeMonthService().getStatisticalDimensionTimeMonth(entity);

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

		StatisticalDimensionTimeMonth entity = new StatisticalDimensionTimeMonth();
		entity.setId(Long.valueOf(id));
		entity = super.getFacade().getStatisticalDimensionTimeMonthService().getStatisticalDimensionTimeMonth(entity);

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
			StatisticalDimensionTimeMonth entity = new StatisticalDimensionTimeMonth();
			entity.setId(Long.valueOf(id));
			getFacade().getStatisticalDimensionTimeMonthService().removeStatisticalDimensionTimeMonth(entity);
			saveMessage(request, "entity.deleted");
		} else if (!ArrayUtils.isEmpty(pks)) {
			StatisticalDimensionTimeMonth entity = new StatisticalDimensionTimeMonth();
			entity.getMap().put("pks", pks);
			getFacade().getStatisticalDimensionTimeMonthService().removeStatisticalDimensionTimeMonth(entity);
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
