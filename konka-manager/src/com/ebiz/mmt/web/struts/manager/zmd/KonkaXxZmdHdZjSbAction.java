package com.ebiz.mmt.web.struts.manager.zmd;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.ebiz.mmt.domain.KonkaXxZmdSpPlanRes;
import com.ebiz.mmt.domain.KonkaXxZmdUsers;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Hu,Hao
 * @version 2013-04-26
 */
public class KonkaXxZmdHdZjSbAction extends BaseZmdAction {

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
		entity.setSb_type(Integer.valueOf(2));

		PeProdUser user_id = super.getSessionUserInfo(request);
		// 取用户角色
		// PeRoleUser peRoleUser = super.getRoleInfoByThisLogin(request);
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

		if (!role_id_gt_400) {
			String msg = super.getMessage(request, "konka.xx.zmd.role.error");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		if (role_id_btw_300_400) {// 分公司人员
			KonkaDept kDept = getKonkaDeptForFgs(user_id.getDept_id());
			if (kDept != null) {
				entity.getMap().put("dept_id", kDept.getDept_id());
				request.setAttribute("dept_id", kDept.getDept_id());
			}
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
			String msg = super.getMessage(request, "errors.param");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		KonkaXxZmdHdApSb entity = new KonkaXxZmdHdApSb();
		entity.setSb_type(1);
		List<KonkaXxZmdHdApSb> entityList = super.getFacade().getKonkaXxZmdHdApSbService()
				.getKonkaXxZmdHdApSbForHdNameList(entity);

		request.setAttribute("entityList", entityList);

		request.setAttribute("res_list", getBaseTypeListByParId(120000L));
		request.setAttribute("res_null", true);

		return mapping.findForward("input");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String sp_hd_id = (String) dynaBean.get("sp_hd_id");

		if (!GenericValidator.isLong(sp_hd_id)) {
			String msg = super.getMessage(request, "errors.param");
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
			if (null != konkaXxZmdHdApSb.getReal_start_date()) {
				konkaXxZmdHdApSb.getMap().put("real_start_date", format.format(konkaXxZmdHdApSb.getReal_start_date()));
			}
			if (null != konkaXxZmdHdApSb.getReal_end_date()) {
				konkaXxZmdHdApSb.getMap().put("real_end_date", format.format(konkaXxZmdHdApSb.getReal_end_date()));
			}

			KonkaXxZmdSpPlanRes konkaXxZmdSpPlanRes = new KonkaXxZmdSpPlanRes();
			konkaXxZmdSpPlanRes.setSp_hd_id(Long.valueOf(sp_hd_id));
			konkaXxZmdSpPlanRes.setSb_type(2);
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
		entity.setZmd_id(konkaXxZmdHdApSb.getZmd_id());
		List<KonkaXxZmd> entityList = super.getFacade().getKonkaXxZmdService().getKonkaXxZmdList(entity);
		if (entityList.size() > 0) {
			dynaBean.set("zmd_sn", entityList.get(0).getZmd_sn());
		}

		KonkaDept konkaDept = new KonkaDept();
		konkaDept.setDept_id(konkaXxZmdHdApSb.getDept_id());
		List<KonkaDept> konkaDeptList = super.getFacade().getKonkaDeptService().getKonkaDeptList(konkaDept);
		if (konkaDeptList.size() > 0) {
			dynaBean.set("konka_dept", konkaDeptList.get(0).getDept_name());
		}
		request.setAttribute("is_edit", true);
		// request.setAttribute("res_list", getBaseTypeListByParId(120000L));

		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String sp_hd_id = (String) dynaBean.get("sp_hd_id");
		String real_start_date = (String) dynaBean.get("real_start_date");
		String real_end_date = (String) dynaBean.get("real_end_date");
		String real_money = (String) dynaBean.get("real_money");
		String real_outputs_money = (String) dynaBean.get("real_outputs_money");
		String mod_id = (String) dynaBean.get("mod_id");

		if (!GenericValidator.isLong(sp_hd_id)) {
			String msg = super.getMessage(request, "errors.param");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");// 日期初始化

		KonkaXxZmdHdApSb entity = new KonkaXxZmdHdApSb();

		super.copyProperties(entity, form);

		if (StringUtils.isNotBlank(real_outputs_money)) {
			entity.setReal_outputs_money(new BigDecimal(real_outputs_money));
		}
		if (StringUtils.isNotBlank(real_money)) {
			entity.setReal_money(new BigDecimal(real_money));
		}
		if (StringUtils.isNotBlank(real_start_date)) {
			entity.setReal_start_date(format.parse(real_start_date));
		}
		if (StringUtils.isNotBlank(real_end_date)) {
			entity.setReal_end_date(format.parse(real_end_date));
		}

		List<KonkaXxBaseType> konkaXxBaseTypeList = getBaseTypeListByParId(120000L);

		if (konkaXxBaseTypeList.size() > 0) {
			List<KonkaXxZmdSpPlanRes> kxzspList = new ArrayList<KonkaXxZmdSpPlanRes>();
			for (KonkaXxBaseType temp : konkaXxBaseTypeList) {
				KonkaXxZmdSpPlanRes kxzsp = new KonkaXxZmdSpPlanRes();
				kxzsp.setTr_res_id(temp.getType_id());
				kxzsp.setTr_res_name(temp.getType_name());
				kxzsp.setSb_type(2);// sb_type 为1，活动上报
				BeanUtils.setProperty(kxzsp, "tr_res_money", dynaBean.get("tr_res_money_" + temp.getType_id()));
				kxzspList.add(kxzsp);
			}
			entity.setKonkaXxZmdSpPlanResList(kxzspList);
		}

		entity.setSb_type(2);

		super.getFacade().getKonkaXxZmdHdApSbService().modifyKonkaXxZmdHdZjSbForRes(entity);
		saveMessage(request, "entity.inserted");

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		return forward;
	}
}
