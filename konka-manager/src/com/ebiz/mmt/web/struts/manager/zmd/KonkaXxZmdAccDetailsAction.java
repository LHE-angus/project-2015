package com.ebiz.mmt.web.struts.manager.zmd;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaXxZmd;
import com.ebiz.mmt.domain.KonkaXxZmdAccDetails;
import com.ebiz.mmt.domain.KonkaXxZmdUsers;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Jiang,JiaYong
 * @version 2012-4-1
 */
public class KonkaXxZmdAccDetailsAction extends BaseZmdAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;

		// 取当前登录用户
		PeProdUser ui = super.getSessionUserInfo(request);
		// 取用户角色
		// PeRoleUser peRoleUser = super.getRoleInfoByThisLogin(request);
		List<PeRoleUser> peRoleUserList = getPeRoleList(ui.getId());

		Boolean role_id_gt_400 = false;
		Boolean role_id_ge_400 = false;

		if (peRoleUserList.size() > 0) {
			for (PeRoleUser temp : peRoleUserList) {
				if (temp.getRole_id() <= 400) {
					role_id_gt_400 = true;
				}
				if (temp.getRole_id() < 400) {
					role_id_ge_400 = true;
				}
			}
		}

		if (!role_id_gt_400) {
			String msg = super.getMessage(request, "konka.xx.property.exist");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		// 分公司员工登陆
		if (role_id_ge_400) {
			// 查询专卖店
			KonkaXxZmd zmd = new KonkaXxZmd();
			KonkaDept kDept = getKonkaDeptForFgs(ui.getDept_id());
			if (kDept != null)
				zmd.setDept_id(kDept.getDept_id());
			List<KonkaXxZmd> zmdList = super.getFacade().getKonkaXxZmdService().getKonkaXxZmdList(zmd);

			request.setAttribute("zmdList", zmdList);

			String zmd_id = (String) dynaBean.get("zmd_id");
			if (GenericValidator.isLong(zmd_id)) {
				KonkaXxZmd konkaXxZmd = new KonkaXxZmd();
				konkaXxZmd.setZmd_id(Long.valueOf(zmd_id));
				konkaXxZmd = super.getFacade().getKonkaXxZmdService().getKonkaXxZmd(konkaXxZmd);
				if (null == konkaXxZmd) {
					String m = getMessage(request, "konka.zmd.userinfo.missing");
					super.renderJavaScript(response, "window.onload=function(){alert('" + m + "');history.back();}");
					return null;
				}

				request.setAttribute("konkaXxZmd", konkaXxZmd);
			}

			request.setAttribute("is_dept", "1");
		} else { // 专卖店用户登陆
			KonkaXxZmdUsers konkaXxZmdUsers = new KonkaXxZmdUsers();
			konkaXxZmdUsers.setUser_id(ui.getId());
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

			KonkaXxZmd konkaXxZmd = new KonkaXxZmd();
			konkaXxZmd.setZmd_id(konkaXxZmdUsersList.get(0).getZmd_id());
			konkaXxZmd = super.getFacade().getKonkaXxZmdService().getKonkaXxZmd(konkaXxZmd);
			if (null == konkaXxZmd) {
				String m = getMessage(request, "konka.zmd.userinfo.missing");
				super.renderJavaScript(response, "window.onload=function(){alert('" + m + "');history.back();}");
				return null;
			}

			request.setAttribute("konkaXxZmd", konkaXxZmd);
		}

		return mapping.findForward("view");
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;

		Pager pager = (Pager) dynaBean.get("pager");
		// 查询条件
		String do_time_start = (String) dynaBean.get("do_time_start");
		String do_time_end = (String) dynaBean.get("do_time_end");
		String zmd_id = (String) dynaBean.get("zmd_id");

		if (!GenericValidator.isLong(zmd_id)) {
			String m = getMessage(request, "errors.parm");
			super.renderJavaScript(response, "window.onload=function(){alert('" + m + "');history.back();}");
			return null;
		}

		// 取当前登录用户
		PeProdUser ui = super.getSessionUserInfo(request);
		// 取用户角色
		// PeRoleUser peRoleUser = super.getRoleInfoByThisLogin(request);
		List<PeRoleUser> peRoleUserList = getPeRoleList(ui.getId());

		Boolean role_id_gt_400 = false;

		if (peRoleUserList.size() > 0) {
			for (PeRoleUser temp : peRoleUserList) {
				if (temp.getRole_id() <= 400) {
					role_id_gt_400 = true;
				}
			}
		}

		if (!role_id_gt_400) {
			String msg = super.getMessage(request, "konka.xx.property.exist");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		KonkaXxZmd konkaXxZmd = new KonkaXxZmd();
		konkaXxZmd.setZmd_id(Long.valueOf(zmd_id));
		konkaXxZmd = super.getFacade().getKonkaXxZmdService().getKonkaXxZmd(konkaXxZmd);
		request.setAttribute("konkaXxZmd", konkaXxZmd);

		KonkaXxZmdAccDetails entity = new KonkaXxZmdAccDetails();
		entity.setZmd_id(Long.valueOf(zmd_id));
		if (StringUtils.isNotBlank(do_time_start))
			entity.getMap().put("do_time_start", do_time_start + " 00:00:00");
		if (StringUtils.isNotBlank(do_time_end))
			entity.getMap().put("do_time_end", do_time_end + " 23:59:59");

		Long recordCount = getFacade().getKonkaXxZmdAccDetailsService().getKonkaXxZmdAccDetailsCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<KonkaXxZmdAccDetails> entityList = getFacade().getKonkaXxZmdAccDetailsService()
				.getKonkaXxZmdAccDetailsPaginatedList(entity);
		request.setAttribute("entityList", entityList);

		return mapping.findForward("list");
	}
}
