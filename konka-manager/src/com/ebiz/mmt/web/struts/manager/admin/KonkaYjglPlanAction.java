package com.ebiz.mmt.web.struts.manager.admin;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaBaseTypeData;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaYjglPlan;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.MobileBaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

public class KonkaYjglPlanAction extends MobileBaseAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}

		setNaviStringToRequestScope(form, request);

		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		if (null == peProdUser) {
			return null;
		}

		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");

		String dept_id = (String) dynaBean.get("dept_id");
		String plan_year = (String) dynaBean.get("plan_year");
		String add_time_start = (String) dynaBean.get("add_time_start");
		String add_time_end = (String) dynaBean.get("add_time_end");

		KonkaYjglPlan entity = new KonkaYjglPlan();
		super.copyProperties(entity, form);

		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(peProdUser.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);
		boolean role_id_eq_30 = false;
		boolean role_id_gt_30_lt_60 = false;

		for (PeRoleUser peRoleUser : peRoleUserList) {
			// 总部管理员
			if (peRoleUser.getRole_id() < 30L) {
				role_id_eq_30 = true;
				break;
			}
			// 分公司管理员
			if (peRoleUser.getRole_id() >= 30L) {
				role_id_gt_30_lt_60 = true;
				break;
			}
		}
		if (role_id_eq_30) {

		} else if (role_id_gt_30_lt_60) {
			entity.setDept_id(super.getSuperiorForDeptType(peProdUser.getDept_id(), 3).getDept_id());
		}

		if (StringUtils.isNotBlank(dept_id)) {
			entity.setDept_id(Long.valueOf(dept_id));
		}
		if (StringUtils.isNotBlank(plan_year)) {
			entity.setPlan_year(Integer.valueOf(plan_year));
		}
		if (StringUtils.isNotBlank(add_time_start)) {
			entity.getMap().put("add_time_start", add_time_start + " 00:00:00");
		}
		if (StringUtils.isNotBlank(add_time_end)) {
			entity.getMap().put("add_time_end", add_time_end + " 23:59:59");
		}

		Long recordCount = super.getFacade().getKonkaYjglPlanService().getKonkaYjglPlanAndDeptNameCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<KonkaYjglPlan> entityList = super.getFacade().getKonkaYjglPlanService()
		        .getKonkaYjglPlanAndDeptNamePaginatedList(entity);
		for (KonkaYjglPlan konkaYjglPlan : entityList) {
			KonkaBaseTypeData kp = new KonkaBaseTypeData();
			kp.setType_id(konkaYjglPlan.getType_id());
			kp = super.getFacade().getKonkaBaseTypeDataService().getKonkaBaseTypeData(kp);
			if (kp != null) {
				konkaYjglPlan.getMap().put("type_name", kp.getType_name());
			}
		}

		request.setAttribute("entityList", entityList);

		// 取分公司列表
		List<KonkaDept> deptList = getDeptInfoListWithOutLimit(mapping, form, request, response);
		request.setAttribute("deptList", deptList);

		return mapping.findForward("list");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		saveToken(request);

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		KonkaBaseTypeData type = new KonkaBaseTypeData();
		type.setPar_type_id(100012L);
		List<KonkaBaseTypeData> typeList = super.getFacade().getKonkaBaseTypeDataService().getKonkaBaseTypeDataList(
		        type);
		request.setAttribute("typeList", typeList);

		// 取分公司列表
		List<KonkaDept> deptList = getDeptInfoListWithOutLimit(mapping, form, request, response);
		request.setAttribute("deptList", deptList);

		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		request.setAttribute("date", sf.format(date));

		return mapping.findForward("input");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {

		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");

		KonkaYjglPlan entity = new KonkaYjglPlan();
		entity.setId(Long.valueOf(id));
		entity = getFacade().getKonkaYjglPlanService().getKonkaYjglPlan(entity);
		if (null == entity) {
			return null;
		}
		super.copyProperties(form, entity);

		KonkaDept konkaDept = new KonkaDept();
		konkaDept.setDept_id(Long.valueOf(entity.getDept_id()));
		konkaDept = getFacade().getKonkaDeptService().getKonkaDept(konkaDept);
		if (null == konkaDept) {
			return null;
		}
		dynaBean.set("dept_name", konkaDept.getDept_name());

		KonkaBaseTypeData kt = new KonkaBaseTypeData();
		kt.setType_id(entity.getType_id());
		kt = super.getFacade().getKonkaBaseTypeDataService().getKonkaBaseTypeData(kt);
		if (kt != null) {
			dynaBean.set("type_name", kt.getType_name());
		}

		return mapping.findForward("view");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		if (!GenericValidator.isLong(id)) {
			super.saveError(request, "errors.long", "id");
			return this.list(mapping, form, request, response);
		}

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		KonkaBaseTypeData type = new KonkaBaseTypeData();
		type.setPar_type_id(100012L);
		List<KonkaBaseTypeData> typeList = super.getFacade().getKonkaBaseTypeDataService().getKonkaBaseTypeDataList(
		        type);
		request.setAttribute("typeList", typeList);

		// 取分公司列表
		List<KonkaDept> deptList = getDeptInfoListWithOutLimit(mapping, form, request, response);
		request.setAttribute("deptList", deptList);

		KonkaYjglPlan entity = new KonkaYjglPlan();
		entity.setId(Long.valueOf(id));
		entity = super.getFacade().getKonkaYjglPlanService().getKonkaYjglPlan(entity);
		entity.setQueryString(super.serialize(request, "id", "method"));

		super.copyProperties(form, entity);

		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		request.setAttribute("date", sf.format(date));

		// dynaBean.set("dept_id", entity.getDept_id().toString());
		// dynaBean.set("yjed_year", entity.getYjed_year().toString());
		// dynaBean.set("is_add", "0");

		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String id = (String) dynaBean.get("id");
		String dept_id = (String) dynaBean.get("dept_id");
		String type_id = (String) dynaBean.get("type_id");
		String plan_year = (String) dynaBean.get("plan_year");

		if (StringUtils.isBlank(id)) {
			if (isCancelled(request)) {
				return list(mapping, form, request, response);
			}
			if (!isTokenValid(request)) {
				saveError(request, "errors.token");
				return list(mapping, form, request, response);
			}
			resetToken(request);
		}

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		KonkaYjglPlan entity = new KonkaYjglPlan();
		super.copyProperties(entity, form);
		if (StringUtils.isEmpty(id)) {

			KonkaYjglPlan kp = new KonkaYjglPlan();
			kp.setDept_id(Long.valueOf(dept_id));
			kp.setPlan_year(Integer.valueOf(plan_year));
			kp.setType_id(Long.valueOf(type_id));
			Long count = super.getFacade().getKonkaYjglPlanService().getKonkaYjglPlanCount(kp);
			if (count > 0) {
				super.renderJavaScript(response, "alert('同一个分公司同一年份同一个类型只能有一条数据！');history.back();");
				return null;
			}

			entity.setAdd_date(new Date());
			entity.setXf_date(new Date());
			entity.setIs_del(0);
			entity.setAdd_user_id(user.getId());
			// entity.setIs_confirm(0);
			// entity.setIs_fp(0);
			super.getFacade().getKonkaYjglPlanService().createKonkaYjglPlan(entity);
			super.saveMessage(request, "entity.inserted");
		} else {
			KonkaYjglPlan ky = new KonkaYjglPlan();
			ky.setId(Long.valueOf(id));
			ky = super.getFacade().getKonkaYjglPlanService().getKonkaYjglPlan(ky);
			if (ky != null) {
				entity.setId(Long.valueOf(id));
				entity.setDept_id(ky.getDept_id());
				entity.setType_id(ky.getType_id());
				entity.setPlan_year(ky.getPlan_year());
				entity.setUpdate_time(new Date());
				entity.setIs_del(0);
				entity.setUpdate_user_id(user.getId());
				super.getFacade().getKonkaYjglPlanService().modifyKonkaYjglPlan(entity);
				super.saveMessage(request, "entity.updated");
			} else {
				return null;
			}

		}

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;

	}

}