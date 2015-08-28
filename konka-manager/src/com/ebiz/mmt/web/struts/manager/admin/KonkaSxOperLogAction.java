package com.ebiz.mmt.web.struts.manager.admin;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

import com.ebiz.mmt.domain.OperLog;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

public class KonkaSxOperLogAction extends BaseAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String begindate = "";
		String enddate = "";
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendarbegin = Calendar.getInstance();
		calendarbegin.set(Calendar.MONTH, 1);
		calendarbegin.set(Calendar.DAY_OF_MONTH, calendarbegin
				.getActualMinimum(Calendar.DAY_OF_MONTH));
		begindate = df.format(calendarbegin.getTime());
		dynaBean.set("oper_starttime", begindate);
		Calendar calendarend = Calendar.getInstance();
		enddate = df.format(calendarend.getTime());
		dynaBean.set("oper_endtime", enddate);
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}

		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);
		String user_name = (String) dynaBean.get("user_name");
		String oper_starttime = (String) dynaBean.get("oper_starttime");
		String oper_endtime = (String) dynaBean.get("oper_endtime");

		Pager pager = (Pager) dynaBean.get("pager");
		pager.setPageSize(15);

		OperLog entity = new OperLog();
		if (StringUtils.isNotBlank(user_name)) {
			entity.getMap().put("user_name", user_name);
		}

		if (StringUtils.isNotBlank(oper_starttime)) {
			entity.getMap().put("oper_starttime", oper_starttime + " 00:00:00");
		}
		if (StringUtils.isNotBlank(oper_endtime)) {
			entity.getMap().put("oper_endtime", oper_endtime + " 23:59:59");
		}

		if (super.getRoleInfoByThisLogin(request).getRole_id() != 10) {
			entity.setOper_uname(super.getSessionUserInfo(request).getUser_name());
		}
		Long recordCount = getFacade().getKonkaSxOperLogService().getKonkaSxOperLogCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<OperLog> entityList = getFacade().getKonkaSxOperLogService().getKonkaSxOperLogPaginatedList(entity);
		request.setAttribute("entityList", entityList);

		return mapping.findForward("list");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (isCancelled(request)) {
			return list(mapping, form, request, response);
		}
		if (!isTokenValid(request)) {
			saveError(request, "errors.token");
			return list(mapping, form, request, response);
		}
		resetToken(request);

		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");

		OperLog entity = new OperLog();
		super.copyProperties(entity, form);

		if (null == entity.getId()) {
			getFacade().getKonkaSxOperLogService().createKonkaSxOperLog(entity);
			saveMessage(request, "entity.inserted");
		} else {
			getFacade().getKonkaSxOperLogService().modifyKonkaSxOperLog(entity);
			saveMessage(request, "entity.updated");
		}

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "4")) {
			return super.checkPopedomInvalid(request, response);
		}

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String[] pks = (String[]) dynaBean.get("pks");

		if (!StringUtils.isBlank(id) && GenericValidator.isLong(id)) {
			OperLog entity = new OperLog();
			entity.setId(new Long(id));
			getFacade().getKonkaSxOperLogService().modifyKonkaSxOperLog(entity);
			saveMessage(request, "entity.deleted");
		} else if (!ArrayUtils.isEmpty(pks)) {
			OperLog entity = new OperLog();
			entity.getMap().put("pks", pks);
			getFacade().getKonkaSxOperLogService().modifyKonkaSxOperLog(entity);
			saveMessage(request, "entity.deleted");
		}
		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "id", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");

		if (GenericValidator.isLong(id)) {
			OperLog entity = new OperLog();
			entity.setId(new Long(id));
			entity = getFacade().getKonkaSxOperLogService().getKonkaSxOperLog(entity);

			if (null == entity) {
				saveMessage(request, "entity.missing");
				return mapping.findForward("list");
			}

			super.copyProperties(form, entity);
			return mapping.findForward("view");
		} else {
			saveError(request, "errors.long", id);
			return mapping.findForward("list");
		}
	}
	
	/**
	 * Xiao,Guojina 
	 * 查询系统日志，包括
	 * 定时同步R3客户
	 * 定时同步产品库
	 * 定时同步回款
	 * 定时同步账期
	 * 定时同步R3订单明细
	 * 定时同步集采数据 
	 * 定时同步物流信息
	 * 定时同步存销比与毛利分析接口数据
	 * 同步业绩划拨
	 * 定时同步R3客户分类
	 * 定时发送邮件
	 * 定时同步一次顺丰物流运费
	 * 定时同步订单在顺丰的状态
	 * 定时同步分公司调拨计划评估
	 * 同步客户产品数据
	 * 客户库存汇总
	 * 月度库存查询
	 * 业务汇总月统计数据

	 * @throws Exception
	 */
	public ActionForward listForTb(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}

		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);
		//默认当月时间段
		String flag = (String) dynaBean.get("flag");
		if(flag!=null){
			String begindate = "";
			String enddate = "";
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Calendar calendarbegin = Calendar.getInstance();
			calendarbegin.set(Calendar.MONTH, 1);
			calendarbegin.set(Calendar.DAY_OF_MONTH, calendarbegin
					.getActualMinimum(Calendar.DAY_OF_MONTH));
			begindate = df.format(calendarbegin.getTime());
			dynaBean.set("oper_starttime", begindate);
			Calendar calendarend = Calendar.getInstance();
			enddate = df.format(calendarend.getTime());
			dynaBean.set("oper_endtime", enddate);
		}
		String user_name = (String) dynaBean.get("user_name");
		String oper_starttime = (String) dynaBean.get("oper_starttime");
		String oper_endtime = (String) dynaBean.get("oper_endtime");
		String link_id = (String) dynaBean.get("link_id");
		

		Pager pager = (Pager) dynaBean.get("pager");
		pager.setPageSize(15);

		OperLog entity = new OperLog();
		if (StringUtils.isNotBlank(user_name)) {
			entity.getMap().put("user_name", user_name);
		}

		if (StringUtils.isNotBlank(oper_starttime)) {
			entity.getMap().put("oper_starttime", oper_starttime + " 00:00:00");
		}
		if (StringUtils.isNotBlank(oper_endtime)) {
			entity.getMap().put("oper_endtime", oper_endtime + " 23:59:59");
		}
		
		if (StringUtils.isNotBlank(link_id)) {
			entity.setLink_id(Long.parseLong(link_id));
		}

		if (super.getRoleInfoByThisLogin(request).getRole_id() != 10) {
			entity.setOper_uname(super.getSessionUserInfo(request).getUser_name());
		}
		entity.getMap().put("is_forTb", "true");//十几个特殊的日志记录
		Long recordCount = getFacade().getKonkaSxOperLogService().getKonkaSxOperLogCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<OperLog> entityList = getFacade().getKonkaSxOperLogService().getKonkaSxOperLogPaginatedList(entity);
		request.setAttribute("entityList", entityList);

		return new ActionForward("/admin/KonkaSxOperLog/listForTb.jsp");
	}
}