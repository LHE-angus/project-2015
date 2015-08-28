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
import com.ebiz.mmt.domain.KonkaXxZmdRemitRec;
import com.ebiz.mmt.domain.KonkaXxZmdUsers;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Hu Hao
 * @version 2012-03-22
 */
public class KonkaXxZmdRemitForMyRecAction extends BaseZmdAction {

	protected ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		setNaviStringToRequestScope(form, request);
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);

		PeProdUser user_info = super.getSessionUserInfo(request);
		List<PeRoleUser> peRoleUserList = getPeRoleList(user_info.getId());

		Boolean role_id_gt_400 = false;
		if (peRoleUserList.size() > 0) {
			for (PeRoleUser temp : peRoleUserList) {
				if (temp.getRole_id() <= 400) {
					role_id_gt_400 = true;
				}
			}
		}
		
		if (!role_id_gt_400) {
			String msg = super.getMessage(request, "konka.xx.zmd.role.error");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		Pager pager = (Pager) dynaBean.get("pager");
		String plan_date_ge = (String) dynaBean.get("plan_date_ge");
		String plan_date_le = (String) dynaBean.get("plan_date_le");
		String state = (String) dynaBean.get("state");
		KonkaXxZmdRemitRec entity = new KonkaXxZmdRemitRec();

		if (StringUtils.isNotBlank(plan_date_ge)) {
			entity.getMap().put("plan_date_ge", plan_date_ge);
		}
		if (StringUtils.isNotBlank(plan_date_le)) {
			entity.getMap().put("plan_date_le", plan_date_le);
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
		Long recordCount = super.getFacade().getKonkaXxZmdRemitRecService().getKonkaXxZmdRemitRecCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<KonkaXxZmdRemitRec> entityList = super.getFacade().getKonkaXxZmdRemitRecService().getKonkaXxZmdRemitRecPaginatedList(entity);
		if (entityList.size() > 0 && null != entityList) {
			for (KonkaXxZmdRemitRec temp : entityList) {
				KonkaXxZmd konkaXxZmd = new KonkaXxZmd();
				konkaXxZmd.setZmd_id(temp.getZmd_id());
				konkaXxZmd = super.getFacade().getKonkaXxZmdService().getKonkaXxZmd(konkaXxZmd);
				if (null != konkaXxZmd) {
					temp.getMap().put("zmd_sn", konkaXxZmd.getZmd_sn());
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
		String remit_rec_id = (String) dynaBean.get("remit_rec_id");

		if (!GenericValidator.isLong(remit_rec_id)) {
			this.saveError(request, "errors.long", new String[] { remit_rec_id });
			return mapping.findForward("list");
		}

		KonkaXxSellBill konkaXxSellBill = new KonkaXxSellBill();
		konkaXxSellBill.setRemit_rec_id(Long.valueOf(remit_rec_id));
		List<KonkaXxSellBill> konkaXxSellBillList = super.getFacade().getKonkaXxSellBillService().getKonkaXxSellBillList(konkaXxSellBill);
		request.setAttribute("konkaXxSellBillList", konkaXxSellBillList);

		KonkaXxZmdRemitRec entity = new KonkaXxZmdRemitRec();
		entity.setRemit_rec_id(Long.valueOf(remit_rec_id));
		entity = super.getFacade().getKonkaXxZmdRemitRecService().getKonkaXxZmdRemitRec(entity);
		if (null == entity) {
			saveMessage(request, "entity.missing");
			return mapping.findForward("list");
		}
		super.copyProperties(form, entity);
		return mapping.findForward("view");
	}
}
