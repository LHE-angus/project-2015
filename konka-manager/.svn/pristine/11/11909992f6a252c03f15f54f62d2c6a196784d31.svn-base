package com.ebiz.mmt.web.struts.manager.zmd;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.KonkaXxZmd;
import com.ebiz.mmt.domain.KonkaXxZmdUsers;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Hu,Hao
 * @version 2013-06-19
 */
public class KonkaXxZmdUsersAction extends BaseZmdAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String zmd_sn_like = (String) dynaBean.get("zmd_sn_like");
		String zmd_fp = (String) dynaBean.get("zmd_fp");

		PeProdUser ui = super.getSessionUserInfo(request);

		List<PeRoleUser> peRoleUserList = getPeRoleList(ui.getId());
		Boolean role_id_eq_300 = false;
		if (peRoleUserList.size() > 0) {
			for (PeRoleUser temp : peRoleUserList) {
				if (temp.getRole_id() == 300) {
					role_id_eq_300 = true;
				}
			}
		}

		KonkaXxZmd entity = new KonkaXxZmd();

		if (StringUtils.isNotBlank(zmd_sn_like)) {
			entity.getMap().put("zmd_sn_like", zmd_sn_like);
		}

		if ("1".equals(zmd_fp)) {
			entity.getMap().put("zmd_fp_1", true);
		} else if ("2".equals(zmd_fp)) {
			entity.getMap().put("zmd_fp_2", true);
		} else {
			dynaBean.set("zmd_fp", 1);
			entity.getMap().put("zmd_fp_1", true);
		}

		if (role_id_eq_300) {
			entity.setDept_id(getKonkaDeptForFgs(ui.getDept_id()).getDept_id());
		} else {
			String msg = getMessage(request, "konka.xx.zmd.role.error");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		entity.setArc_state(20300);// 备案审核通过
		entity.setIs_del(0);

		Long recordCount = getFacade().getKonkaXxZmdService().getKonkaXxZmdForRoleCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<KonkaXxZmd> entityList = getFacade().getKonkaXxZmdService().getKonkaXxZmdForRolePaginatedList(entity);
		request.setAttribute("entityList", entityList);

		return mapping.findForward("list");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String zmd_ids = (String) dynaBean.get("zmd_ids");
		// String mod_id = (String) dynaBean.get("mod_id");

		if (StringUtils.isNotBlank(zmd_ids)) {
			String msg = getMessage(request, "konka.xx.zmd.role.error");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}
		String[] zmd_ids_value = zmd_ids.split("##");
		List<KonkaXxZmd> konkaXxZmdList = new ArrayList<KonkaXxZmd>();
		for (int i = 0; i < zmd_ids_value.length; i++) {
			KonkaXxZmd konkaXxZmd = new KonkaXxZmd();
			konkaXxZmd.setR3_id(zmd_ids_value[i].split("_")[0]);
			konkaXxZmd.setZmd_id(Long.valueOf(zmd_ids_value[i].split("_")[1]));
			konkaXxZmdList.add(konkaXxZmd);
		}

		return null;
	}

	public ActionForward zmdUserFp(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String zmd_id = (String) dynaBean.get("zmd_id");
		String r3_id = (String) dynaBean.get("r3_id");
		String mod_id = (String) dynaBean.get("mod_id");

		if (!StringUtils.isNotBlank(r3_id) || !GenericValidator.isLong(zmd_id)) {
			String msg = getMessage(request, "errors.parm");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		KonkaR3Shop konkaR3Shop = new KonkaR3Shop();
		konkaR3Shop.setR3_code(r3_id);
		List<KonkaR3Shop> konkaR3ShopList = super.getFacade().getKonkaR3ShopService().getKonkaR3ShopList(konkaR3Shop);

		if (konkaR3ShopList.size() == 0 || null == konkaR3ShopList) {
			String msg = getMessage(request, "konka.r3.shop.empty");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}
		KonkaXxZmdUsers entity = new KonkaXxZmdUsers();
		entity.setZmd_id(Long.valueOf(zmd_id));

		super.getFacade().getKonkaXxZmdUsersService().modifyKonkaXxZmdUsersForZmdFp(entity,
				konkaR3ShopList.get(0).getId());

		saveMessage(request, "konka.zmd.user.fp");

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		return forward;
	}
}
