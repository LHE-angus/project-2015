package com.ebiz.mmt.web.struts.manager.zmd;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaXxBaseType;
import com.ebiz.mmt.domain.KonkaXxZmd;
import com.ebiz.mmt.domain.KonkaXxZmdHdApSb;
import com.ebiz.mmt.domain.KonkaXxZmdHdSet;
import com.ebiz.mmt.domain.KonkaXxZmdSpPlanRes;
import com.ebiz.mmt.domain.KonkaXxZmdUsers;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Hu,Hao
 * @version 2013-04-19
 */
public class KonkaXxZmdHdApSbAction extends BaseZmdAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;

		Pager pager = (Pager) dynaBean.get("pager");
		String dept_id = (String) dynaBean.get("dept_id");

		KonkaXxZmdHdApSb entity = new KonkaXxZmdHdApSb();

		// 上报类型 1-促销活动安排上报，2-促销活动总结上报
		//entity.setSb_type(Integer.valueOf(1));

		PeProdUser user_id = super.getSessionUserInfo(request);
		Long dept_id_value = null;
		KonkaDept kDept = getKonkaDeptForFgs(user_id.getDept_id());
		if (kDept != null)
			dept_id_value = kDept.getDept_id();
		
		// 取用户角色
		List<PeRoleUser> peRoleUserList = getPeRoleList(user_id.getId());
		Boolean role_id_gt_400 = false;
		Boolean role_id_eq_400 = false;
		Boolean role_id_btw_300_400 = false;
		if (peRoleUserList.size() > 0) {
			for (PeRoleUser temp : peRoleUserList) {
				if (temp.getRole_id() <= 400) {
					role_id_gt_400 = true;
				}
				if (temp.getRole_id() == 400) {
					role_id_eq_400 = true;
				}
				if (temp.getRole_id() >= 300 && temp.getRole_id() < 400) {
					role_id_btw_300_400 = true;
				}
			}
		}

		//PeRoleUser peRoleUser = super.getRoleInfoByThisLogin(request);
		if (!role_id_gt_400) {
			String msg = super.getMessage(request, "konka.xx.zmd.role.error");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		if (role_id_btw_300_400) {// 分公司人员
			entity.getMap().put("dept_id", dept_id_value);
			request.setAttribute("dept_id", dept_id_value);
		} else if (role_id_eq_400) {// 专卖店管理员

			KonkaXxZmdUsers konkaXxZmdUsers = new KonkaXxZmdUsers();
			konkaXxZmdUsers.setUser_id(user_id.getId());
			konkaXxZmdUsers.getRow().setCount(2);
			List<KonkaXxZmdUsers> konkaXxZmdUsersList = getFacade().getKonkaXxZmdUsersService().getKonkaXxZmdUsersList(
					konkaXxZmdUsers);
			if (null == konkaXxZmdUsersList || konkaXxZmdUsersList.size() == 0) {
				String m = getMessage(request, "konka.zmd.userinfo.missing");
				super.renderJavaScript(response, "window.onload=function(){alert('" + m + "');history.back();}");
				return null;
			}
			if (konkaXxZmdUsersList.size() == 2) {
				String m = getMessage(request, "konka.zmd.userinfo.too.many");
				super.renderJavaScript(response, "window.onload=function(){alert('" + m + "');history.back();}");
				return null;
			}
			entity.setZmd_id(konkaXxZmdUsersList.get(0).getZmd_id());
		} else {
			request.setAttribute("not_zmd_dept", true);
			if (StringUtils.isNotBlank(dept_id)) {
				entity.getMap().put("dept_id", dept_id);
			}
		}

		Long recordCount = super.getFacade().getKonkaXxZmdHdApSbService().getKonkaXxZmdHdApSbForHdNameCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<KonkaXxZmdHdApSb> entityList = super.getFacade().getKonkaXxZmdHdApSbService()
				.getKonkaXxZmdHdApSbForHdNamePaginatedList(entity);

		request.setAttribute("entityList", entityList);

		request.setAttribute("konkaDeptList", getKonkaDepts());
		return mapping.findForward("list");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String dept_id = (String) dynaBean.get("dept_id");

		if (!GenericValidator.isLong(dept_id)) {
			String msg = super.getMessage(request, "param.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		KonkaXxZmd entity = new KonkaXxZmd();
		entity.setDept_id(Long.valueOf(dept_id));
		List<KonkaXxZmd> entityList = super.getFacade().getKonkaXxZmdService().getKonkaXxZmdList(entity);
		request.setAttribute("entityList", entityList);

		KonkaXxZmdHdSet hd = new KonkaXxZmdHdSet();
		hd.setDept_id(Long.valueOf(dept_id));
		List<KonkaXxZmdHdSet> hdList = super.getFacade().getKonkaXxZmdHdSetService().getKonkaXxZmdHdSetList(hd);

		dynaBean.set("dept_id", dept_id);

		request.setAttribute("hdList", hdList);
		request.setAttribute("res_list", getBaseTypeListByParId(120000L));
		request.setAttribute("res_null", true);

		return mapping.findForward("input");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String sp_hd_id = (String) dynaBean.get("sp_hd_id");
		String dept_id = (String) dynaBean.get("dept_id");

		if (!GenericValidator.isLong(sp_hd_id)) {
			String msg = super.getMessage(request, "param.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		KonkaXxZmdHdApSb konkaXxZmdHdApSb = new KonkaXxZmdHdApSb();
		konkaXxZmdHdApSb.setSp_hd_id(Long.valueOf(sp_hd_id));
		konkaXxZmdHdApSb = super.getFacade().getKonkaXxZmdHdApSbService().getKonkaXxZmdHdApSb(konkaXxZmdHdApSb);

		if (null == konkaXxZmdHdApSb) {
			saveMessage(request, "entity.missing");
			return mapping.findForward("list");
		} else {
			super.copyProperties(form, konkaXxZmdHdApSb);

			// 初始化日期
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			if (null != konkaXxZmdHdApSb.getPlan_start_date()) {
				konkaXxZmdHdApSb.getMap().put("plan_start_date", format.format(konkaXxZmdHdApSb.getPlan_start_date()));
			}
			if (null != konkaXxZmdHdApSb.getPlan_end_date()) {
				konkaXxZmdHdApSb.getMap().put("plan_end_date", format.format(konkaXxZmdHdApSb.getPlan_end_date()));
			}

			KonkaXxZmdSpPlanRes konkaXxZmdSpPlanRes = new KonkaXxZmdSpPlanRes();
			konkaXxZmdSpPlanRes.setSp_hd_id(Long.valueOf(sp_hd_id));
			konkaXxZmdSpPlanRes.setSb_type(1);
			List<KonkaXxZmdSpPlanRes> konkaXxZmdSpPlanResList = super.getFacade().getKonkaXxZmdSpPlanResService()
					.getKonkaXxZmdSpPlanResList(konkaXxZmdSpPlanRes);
			if (konkaXxZmdSpPlanResList.size() > 0) {
				request.setAttribute("res_list", konkaXxZmdSpPlanResList);
			} else {
				request.setAttribute("res_null", true);
				request.setAttribute("res_list", getBaseTypeListByParId(120000L));
			}
		}

		KonkaXxZmd entity = new KonkaXxZmd();// 取专卖店列表
		entity.setDept_id(Long.valueOf(dept_id));
		List<KonkaXxZmd> entityList = super.getFacade().getKonkaXxZmdService().getKonkaXxZmdList(entity);
		request.setAttribute("entityList", entityList);

		KonkaXxZmdHdSet hd = new KonkaXxZmdHdSet();// 取活动列表
		hd.setDept_id(Long.valueOf(dept_id));
		List<KonkaXxZmdHdSet> hdList = super.getFacade().getKonkaXxZmdHdSetService().getKonkaXxZmdHdSetList(hd);

		dynaBean.set("dept_id", dept_id);

		request.setAttribute("hdList", hdList);
		// request.setAttribute("res_list", getBaseTypeListByParId(120000L));

		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String dept_id = (String) dynaBean.get("dept_id");
		String sp_hd_id = (String) dynaBean.get("sp_hd_id");
		String plan_start_date = (String) dynaBean.get("plan_start_date");
		String plan_end_date = (String) dynaBean.get("plan_end_date");
		String plan_money = (String) dynaBean.get("plan_money");
		String plan_outputs_money = (String) dynaBean.get("plan_outputs_money");
		String mod_id = (String) dynaBean.get("mod_id");

		if (!GenericValidator.isLong(dept_id)) {
			String msg = super.getMessage(request, "param.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		PeProdUser user_id = super.getSessionUserInfo(request);

		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");// 日期初始化

		KonkaXxZmdHdApSb entity = new KonkaXxZmdHdApSb();

		super.copyProperties(entity, form);

		if (StringUtils.isNotBlank(dept_id)) {
			entity.setDept_id(Long.valueOf(dept_id));
		}
		if (StringUtils.isNotBlank(plan_outputs_money)) {
			entity.setPlan_outputs_money(new BigDecimal(plan_outputs_money));
		}
		if (StringUtils.isNotBlank(plan_money)) {
			entity.setPlan_money(new BigDecimal(plan_money));
		}
		if (StringUtils.isNotBlank(plan_start_date)) {
			entity.setPlay_start_date(format.parse(plan_start_date));
		}
		if (StringUtils.isNotBlank(plan_end_date)) {
			entity.setPlay_end_date(format.parse(plan_end_date));
		}

		List<KonkaXxBaseType> konkaXxBaseTypeList = getBaseTypeListByParId(120000L);

		if (konkaXxBaseTypeList.size() > 0) {
			List<KonkaXxZmdSpPlanRes> kxzspList = new ArrayList<KonkaXxZmdSpPlanRes>();
			for (KonkaXxBaseType temp : konkaXxBaseTypeList) {
				KonkaXxZmdSpPlanRes kxzsp = new KonkaXxZmdSpPlanRes();
				kxzsp.setTr_res_id(temp.getType_id());
				kxzsp.setTr_res_name(temp.getType_name());
				kxzsp.setSb_type(1);// sb_type 为1，活动上报
				BeanUtils.setProperty(kxzsp, "tr_res_money", dynaBean.get("tr_res_money_" + temp.getType_id()));
				kxzspList.add(kxzsp);
			}
			entity.setKonkaXxZmdSpPlanResList(kxzspList);
		}


		if (!GenericValidator.isLong(sp_hd_id)) {
			entity.setSb_type(1);
			entity.setAdd_date(new Date());
			entity.setAdd_user_id(user_id.getId());
			super.getFacade().getKonkaXxZmdHdApSbService().createKonkaXxZmdHdApSbForRes(entity);
			saveMessage(request, "entity.updated");
		} else {
			super.getFacade().getKonkaXxZmdHdApSbService().modifyKonkaXxZmdHdApSbForRes(entity);
			saveMessage(request, "entity.inserted");
		}

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		return forward;
	}
	
	public ActionForward validateHdName(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;

		String hd_name = (String) dynaBean.get("hd_name");
		KonkaXxZmdHdApSb entity = new KonkaXxZmdHdApSb();
		String isExist = "null";

		if (StringUtils.isNotBlank(hd_name)) {
			entity.setHd_name(hd_name);
			List<KonkaXxZmdHdApSb> entityList = getFacade().getKonkaXxZmdHdApSbService().getKonkaXxZmdHdApSbList(entity);
			if (entityList.size() == 0) {// 可用
				isExist = String.valueOf("0");
			} else {

				isExist = String.valueOf("11");
			}
		}

		super.render(response, isExist, "text/x-json;charset=UTF-8");
		return null;
	}
}
