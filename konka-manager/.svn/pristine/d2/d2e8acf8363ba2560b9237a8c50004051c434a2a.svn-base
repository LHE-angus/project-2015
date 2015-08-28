package com.ebiz.mmt.web.struts.manager.zmd;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.tuckey.web.filters.urlrewrite.utils.StringUtils;

import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaXxZmd;
import com.ebiz.mmt.domain.KonkaXxZmdQuota;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;

/**
 * @author Jiang,JiaYong
 * @version 2012-03-19
 */
public class KonkaXxZmdQuotaSetAction extends BaseZmdAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		// 取当前登录用户
		PeProdUser ui = super.getSessionUserInfo(request);
		List<PeRoleUser> peRoleUserList = getPeRoleList(ui.getId());

		Boolean role_id_btw_300_400 = false;
		Boolean role_id_btw_30_40 = false;
		if (peRoleUserList.size() > 0) {
			for (PeRoleUser temp : peRoleUserList) {
				if (temp.getRole_id() >= 300 && temp.getRole_id() < 400) {
					role_id_btw_300_400 = true;
				}
				if (temp.getRole_id() >= 30 && temp.getRole_id() < 40) {
					role_id_btw_30_40 = true;
				}
			}
		}

		// 取用户角色并判断是不是分公司级别的角色
		// PeRoleUser peRoleUser = super.getRoleInfoByThisLogin(request);
		if (!role_id_btw_300_400 || !role_id_btw_30_40) {
			String msg = super.getMessage(request, "konka.xx.zmd.role.error");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		// 查询专卖店
		KonkaXxZmd zmd = new KonkaXxZmd();

		KonkaDept kDept = getKonkaDeptForFgs(ui.getDept_id());
		if (kDept != null)
			zmd.setDept_id(kDept.getDept_id());
		List<KonkaXxZmd> zmdList = super.getFacade().getKonkaXxZmdService().getKonkaXxZmdList(zmd);

		request.setAttribute("zmdList", zmdList);

		return mapping.findForward("list");
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String zmd_id = (String) dynaBean.get("zmd_id");
		String quota_date_year = (String) dynaBean.get("quota_date_year");

		if (!GenericValidator.isLong(zmd_id) || !GenericValidator.isLong(quota_date_year)) {
			String msg = super.getMessage(request, "errors.param");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		KonkaXxZmdQuota entity = new KonkaXxZmdQuota();
		entity.getRow().setCount(20);
		entity.setZmd_id(Long.valueOf(zmd_id));
		entity.getMap().put("quota_date_year", quota_date_year);

		// 取当前登录用户
		PeProdUser ui = super.getSessionUserInfo(request);
		List<PeRoleUser> peRoleUserList = getPeRoleList(ui.getId());

		Boolean role_id_btw_300_400 = false;
		Boolean role_id_btw_30_40 = false;
		if (peRoleUserList.size() > 0) {
			for (PeRoleUser temp : peRoleUserList) {
				if (temp.getRole_id() >= 300 && temp.getRole_id() < 400) {
					role_id_btw_300_400 = true;
				}
				if (temp.getRole_id() >= 30 && temp.getRole_id() < 40) {
					role_id_btw_30_40 = true;
				}
			}
		}

		// 取用户角色并判断是不是分公司级别的角色
		// PeRoleUser peRoleUser = super.getRoleInfoByThisLogin(request);
		if (!role_id_btw_300_400 || !role_id_btw_30_40) {
			String msg = super.getMessage(request, "konka.xx.zmd.role.error");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		// 当前分公司
		KonkaDept kDept = getKonkaDeptForFgs(ui.getDept_id());
		if (kDept != null)
			entity.setDept_id(kDept.getDept_id());

		List<KonkaXxZmdQuota> entityList = super.getFacade().getKonkaXxZmdQuotaService().getKonkaXxZmdQuotaList(entity);
		request.setAttribute("entityList", entityList);

		// 查询专卖店
		KonkaXxZmd zmd = new KonkaXxZmd();
		if (kDept != null)
			zmd.setDept_id(kDept.getDept_id());
		List<KonkaXxZmd> zmdList = super.getFacade().getKonkaXxZmdService().getKonkaXxZmdList(zmd);
		request.setAttribute("zmdList", zmdList);

		return mapping.findForward("list");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String zmd_id = (String) dynaBean.get("zmd_id");
		String quota_date_year = (String) dynaBean.get("quota_date_year");

		if (!GenericValidator.isLong(zmd_id) || !GenericValidator.isLong(quota_date_year)) {
			String msg = super.getMessage(request, "errors.param");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		// 取当前登录用户
		PeProdUser ui = super.getSessionUserInfo(request);

		KonkaXxZmdQuota entity = new KonkaXxZmdQuota();

		KonkaDept kDept = getKonkaDeptForFgs(ui.getDept_id());
		if (kDept != null)
			entity.setDept_id(kDept.getDept_id());
		entity.setZmd_id(Long.valueOf(zmd_id));
		entity.getMap().put("quota_date_year", quota_date_year);

		// 取1-12月的数据
		for (int i = 1; i <= 12; i++) {
			String quota_date_value = (String) dynaBean.get("quota_date_" + i);
			if (StringUtils.isBlank(quota_date_value)) {
				String msg = super.getMessage(request, "errors.param");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			}

			entity.getMap().put("quota_date_" + i, quota_date_value);
		}
		super.getFacade().getKonkaXxZmdQuotaService().createKonkaXxZmdQuotaByCustom(entity);

		saveMessage(request, "entity.inserted");

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&zmd_id=" + zmd_id);
		pathBuffer.append("&quota_date_year=" + quota_date_year);
		pathBuffer.append("&");
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		return forward;
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String zmd_id = (String) dynaBean.get("zmd_id");
		String quota_date_year = (String) dynaBean.get("quota_date_year");

		if (!GenericValidator.isLong(zmd_id) || !GenericValidator.isLong(quota_date_year)) {
			String msg = super.getMessage(request, "errors.param");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		KonkaXxZmd zmd = new KonkaXxZmd();
		zmd.setZmd_id(Long.valueOf(zmd_id));
		zmd = super.getFacade().getKonkaXxZmdService().getKonkaXxZmd(zmd);
		request.setAttribute("konkaXxZmd", zmd);

		return mapping.findForward("input");
	}
}
