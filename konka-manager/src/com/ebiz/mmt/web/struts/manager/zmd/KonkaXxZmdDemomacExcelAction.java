package com.ebiz.mmt.web.struts.manager.zmd;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaXxPd;
import com.ebiz.mmt.domain.KonkaXxZmd;
import com.ebiz.mmt.domain.KonkaXxZmdUsers;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;

/**
 * @author Li,Yuan
 * @version 2012-01-10
 */
public class KonkaXxZmdDemomacExcelAction extends BaseZmdAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);
		String cls_id = (String) dynaBean.get("cls_id");
		String md_name = (String) dynaBean.get("md_name");
		String zmd_sn = (String) dynaBean.get("zmd_sn");
		String state = (String) dynaBean.get("state");
		String counts = (String) dynaBean.get("counts");

		KonkaXxPd entity = new KonkaXxPd();
		entity.getMap().put("cls_id_like", cls_id);
		entity.getMap().put("md_name_like", md_name);
		entity.getMap().put("zmd_sn", zmd_sn);
		entity.getMap().put("state", state);

		// 用户角色
		PeProdUser ui = super.getSessionUserInfo(request);
		List<PeRoleUser> peRoleUserList = getPeRoleList(ui.getId());

		Boolean role_id_gt_400 = false;
		Boolean role_id_btw_300_400 = false;
		Boolean role_id_eq_400 = false;
		if (peRoleUserList.size() > 0) {
			for (PeRoleUser temp : peRoleUserList) {
				if (temp.getRole_id() <= 400) {
					role_id_gt_400 = true;
				}
				if ((temp.getRole_id() >= 300 && temp.getRole_id() < 400)
						|| (temp.getRole_id() < 40 && temp.getRole_id() >= 30)) {
					role_id_btw_300_400 = true;
				}
				if (temp.getRole_id() == 400) {
					role_id_eq_400 = true;
				}
			}
		}
		if (!role_id_gt_400) {
			String msg = super.getMessage(request, "konka.xx.zmd.role.error");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}
		// PeRoleUser role_info = super.getRoleInfoByThisLogin(request);
		// long role_id = role_info.getRole_id();
		if (role_id_eq_400) {
			KonkaXxZmdUsers konkaXxZmdUsers = new KonkaXxZmdUsers();
			konkaXxZmdUsers.setUser_id(ui.getId());
			List<KonkaXxZmdUsers> konkaXxZmdUsersList = super.getFacade().getKonkaXxZmdUsersService()
					.getKonkaXxZmdUsersList(konkaXxZmdUsers);
			if (null != konkaXxZmdUsersList && konkaXxZmdUsersList.size() > 0) {
				Long[] zmd_ids = new Long[konkaXxZmdUsersList.size()];
				for (int i = 0; i < konkaXxZmdUsersList.size(); i++) {
					zmd_ids[i] = konkaXxZmdUsersList.get(i).getZmd_id();
				}
				entity.getMap().put("zmd_ids", zmd_ids);
			}
		}
		if (role_id_btw_300_400) {
			entity.setDept_id(getKonkaDeptForFgs(super.getSessionUserInfo(request).getDept_id()).getDept_id());
		}

		if (GenericValidator.isInt(counts)) {
			entity.getRow().setCount(Integer.parseInt(counts));
		}
		List<KonkaXxPd> entityList = getFacade().getKonkaXxPdService().getKonkaXxPdToExcelList(entity);
		request.setAttribute("entityList", entityList);

		request.setAttribute("bpcList", getBasePdClazzList());

		BigDecimal sumCounts = new BigDecimal(0);
		if (null != entityList && entityList.size() > 0) {
			for (KonkaXxPd konkaXxPd : entityList) {
				if (null != konkaXxPd.getMap().get("counts") && !"".equals(konkaXxPd.getMap().get("counts").toString())) {
					sumCounts = sumCounts.add(new BigDecimal(konkaXxPd.getMap().get("counts").toString()));
				}
			}
		}

		KonkaXxZmd konkaXxZmd = new KonkaXxZmd();
		konkaXxZmd.setArc_state(20300);
		if (role_id_eq_400) {
			KonkaXxZmdUsers konkaXxZmdUsers = new KonkaXxZmdUsers();
			konkaXxZmdUsers.setUser_id(ui.getId());
			List<KonkaXxZmdUsers> konkaXxZmdUsersList = super.getFacade().getKonkaXxZmdUsersService()
					.getKonkaXxZmdUsersList(konkaXxZmdUsers);
			if (null != konkaXxZmdUsersList && konkaXxZmdUsersList.size() > 0) {
				Long[] zmd_ids = new Long[konkaXxZmdUsersList.size()];
				for (int i = 0; i < konkaXxZmdUsersList.size(); i++) {
					zmd_ids[i] = konkaXxZmdUsersList.get(i).getZmd_id();
				}
				konkaXxZmd.getMap().put("zmds", zmd_ids);
			}
		} else {
			KonkaDept kDept = getKonkaDeptForFgs(super.getSessionUserInfo(request).getDept_id());
			if (kDept != null)
				konkaXxZmd.setDept_id(kDept.getDept_id());
		}
		List<KonkaXxZmd> konkaXxZmdList = super.getFacade().getKonkaXxZmdService().getKonkaXxZmdList(konkaXxZmd);
		request.setAttribute("konkaXxZmdList", konkaXxZmdList);
		request.setAttribute("sumCounts", sumCounts);
		return mapping.findForward("list");
	}
}
