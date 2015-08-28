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

import com.ebiz.mmt.domain.KonkaXxSellBill;
import com.ebiz.mmt.domain.KonkaXxZmd;
import com.ebiz.mmt.domain.KonkaXxZmdPosRec;
import com.ebiz.mmt.domain.KonkaXxZmdUsers;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Hu Hao
 * @version 2012-5-3
 */
public class KonkaXxZmdPosForMyRecAction extends BaseZmdAction {

	protected ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		setNaviStringToRequestScope(form, request);
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;

		PeProdUser user_info = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		//PeRoleUser peRoleUser = super.getRoleInfoByThisLogin(request);
		Boolean role_flag = super.getRoleIdFlag(user_info.getId(), -1L, 401L);
		if (role_flag) {
			String msg = super.getMessage(request, "konka.xx.zmd.role.error");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		Pager pager = (Pager) dynaBean.get("pager");
		String out_date_ge = (String) dynaBean.get("out_date_ge");
		String out_date_le = (String) dynaBean.get("out_date_le");
		String state = (String) dynaBean.get("state");
		KonkaXxZmdPosRec entity = new KonkaXxZmdPosRec();

		if (StringUtils.isNotBlank(out_date_ge)) {
			entity.getMap().put("out_date_ge", out_date_ge);
		}
		if (StringUtils.isNotBlank(out_date_le)) {
			entity.getMap().put("out_date_le", out_date_le);
		}
		if (StringUtils.isNotBlank(state)) {
			entity.setState(Integer.valueOf(state));
		}

		KonkaXxZmdUsers konkaXxZmdUsers = new KonkaXxZmdUsers();// 取专卖店ID
		konkaXxZmdUsers.setUser_id(user_info.getId());
		List<KonkaXxZmdUsers> konkaXxZmdUsersList = super.getFacade().getKonkaXxZmdUsersService().getKonkaXxZmdUsersList(konkaXxZmdUsers);

		if (konkaXxZmdUsersList.size() > 0) {
			entity.setZmd_id(konkaXxZmdUsersList.get(0).getZmd_id());
		}

		Long recordCount = super.getFacade().getKonkaXxZmdPosRecService().getKonkaXxZmdPosRecCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<KonkaXxZmdPosRec> entityList = super.getFacade().getKonkaXxZmdPosRecService().getKonkaXxZmdPosRecPaginatedList(entity);

		if (entityList.size() > 0 && null != entityList) {
			for (KonkaXxZmdPosRec temp : entityList) {
				KonkaXxZmd konkaXxZmd = new KonkaXxZmd();
				konkaXxZmd.setZmd_id(temp.getZmd_id());
				konkaXxZmd = super.getFacade().getKonkaXxZmdService().getKonkaXxZmd(konkaXxZmd);
				if (null != konkaXxZmd) {
					temp.getMap().put("zmd_sn", konkaXxZmd.getZmd_sn());
					// temp.getMap().put("zmd_", konkaXxZmd.get);
				}
			}
		}

		request.setAttribute("entityList", entityList);
		return mapping.findForward("list");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String pos_id = (String) dynaBean.get("pos_id");

		if (!GenericValidator.isLong(pos_id)) {
			this.saveError(request, "errors.long", new String[] { pos_id });
			return mapping.findForward("list");
		}

		KonkaXxSellBill konkaXxSellBill = new KonkaXxSellBill();
		konkaXxSellBill.setPos_id(Long.valueOf(pos_id));
		List<KonkaXxSellBill> konkaXxSellBillList = super.getFacade().getKonkaXxSellBillService().getKonkaXxSellBillList(konkaXxSellBill);
		request.setAttribute("konkaXxSellBillList", konkaXxSellBillList);

		KonkaXxZmdPosRec entity = new KonkaXxZmdPosRec();
		entity.setPos_id(Long.valueOf(pos_id));
		entity = super.getFacade().getKonkaXxZmdPosRecService().getKonkaXxZmdPosRec(entity);
		if (null == entity) {
			saveMessage(request, "entity.missing");
			return mapping.findForward("list");
		}
		super.copyProperties(form, entity);
		return mapping.findForward("view");
	}
}
