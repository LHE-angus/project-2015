package com.ebiz.mmt.web.struts.manager.zmd;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaXxSellBill;
import com.ebiz.mmt.domain.KonkaXxZmdRemitRec;
import com.ebiz.mmt.domain.KonkaXxZmdUsers;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;

/**
 * @author Hu Hao
 * @version 2012-03-22
 */
public class KonkaXxZmdRemitAddAction extends BaseZmdAction {
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		return this.add(mapping, form, request, response);
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		// if (null == super.checkUserModPopeDom(form, request, "0")) {
		// return super.checkPopedomInvalid(request, response);
		// }
		PeProdUser user_info = super.getSessionUserInfo(request);
		// PeRoleUser peRoleUser = super.getRoleInfoByThisLogin(request);
		List<PeRoleUser> peRoleUserList = getPeRoleList(user_info.getId());

		Boolean role_id_gt_400 = false;
		Boolean role_id_eq_400 = false;
		if (peRoleUserList.size() > 0) {
			for (PeRoleUser temp : peRoleUserList) {
				if (temp.getRole_id() <= 400) {
					role_id_gt_400 = true;
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

		if (role_id_eq_400) {

			KonkaXxSellBill konkaXxSellBill = new KonkaXxSellBill();

			KonkaXxZmdUsers konkaXxZmdUsers = new KonkaXxZmdUsers();// 取专卖店ID
			konkaXxZmdUsers.setUser_id(user_info.getId());
			List<KonkaXxZmdUsers> konkaXxZmdUsersList = super.getFacade().getKonkaXxZmdUsersService()
					.getKonkaXxZmdUsersList(konkaXxZmdUsers);
			if (null != konkaXxZmdUsersList && konkaXxZmdUsersList.size() > 0) {
				konkaXxSellBill.setZmd_id(konkaXxZmdUsersList.get(0).getZmd_id());
				konkaXxSellBill.setOrder_type(1);
				konkaXxSellBill.setStock_from(1);
				konkaXxSellBill.getMap().put("remit_rec_id_is_null", true);
				konkaXxSellBill.getMap().put("pay_way_is_select", true);
				List<KonkaXxSellBill> konkaXxSellBillList = super.getFacade().getKonkaXxSellBillService()
						.getKonkaXxSellBillList(konkaXxSellBill);
				request.setAttribute("konkaXxSellBillList", konkaXxSellBillList);

				return mapping.findForward("input");
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String bill_ids = (String) dynaBean.get("bill_ids");
		String todo_money = (String) dynaBean.get("total");

		PeProdUser user_info = super.getSessionUserInfo(request);

		KonkaXxZmdUsers konkaXxZmdUsers = new KonkaXxZmdUsers();// 取专卖店ID
		konkaXxZmdUsers.setUser_id(user_info.getId());
		List<KonkaXxZmdUsers> konkaXxZmdUsersList = super.getFacade().getKonkaXxZmdUsersService()
				.getKonkaXxZmdUsersList(konkaXxZmdUsers);

		KonkaXxZmdRemitRec entity = new KonkaXxZmdRemitRec();
		super.copyProperties(entity, form);

		if (StringUtils.isNotBlank(todo_money)) {
			entity.setTodo_money(new BigDecimal(todo_money));
		}
		entity.setZmd_id(konkaXxZmdUsersList.get(0).getZmd_id());
		entity.setPlan_date(new Date());
		entity.getMap().put("bill_ids", bill_ids);
		KonkaDept kDept = getKonkaDeptForFgs(user_info.getDept_id());
		if (kDept != null)
			entity.setDept_id(kDept.getDept_id());
		entity.setState(0);
		super.getFacade().getKonkaXxZmdRemitRecService().createKonkaXxZmdRemitRec(entity);
		saveMessage(request, "entity.inserted");

		return new ActionForward("/../manager/zmd/KonkaXxZmdRemitRec.do?mod_id=830202", true);
	}
}
