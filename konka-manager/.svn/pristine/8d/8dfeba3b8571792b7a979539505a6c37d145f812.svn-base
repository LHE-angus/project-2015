package com.ebiz.mmt.web.struts.manager.zmd;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaXxDistAccountRec;
import com.ebiz.mmt.domain.KonkaXxSellBill;
import com.ebiz.mmt.domain.PeProdUser;
/**
 * @author Hu Hao
 * @version 2012-04-01
 */
public class KonkaXxDistAccountAddAction extends BaseZmdAction {
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		return this.add(mapping, form, request, response);
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// if (null == super.checkUserModPopeDom(form, request, "0")) {
		// return super.checkPopedomInvalid(request, response);
		// }

		PeProdUser user_info = super.getSessionUserInfo(request);
		Boolean role_id_eq_360 = getRoleIdFlag(user_info.getId(), 360L);

		if (role_id_eq_360) {
			KonkaXxSellBill konkaXxSellBill = new KonkaXxSellBill();

			konkaXxSellBill.setDept_id(getKonkaDeptForFgs(user_info.getDept_id()).getDept_id());
			konkaXxSellBill.setPay_way(3l);
			konkaXxSellBill.setSell_state(40l);
			konkaXxSellBill.getMap().put("dist_id_is_null", true);
			List<KonkaXxSellBill> konkaXxSellBillList = super.getFacade().getKonkaXxSellBillService()
					.getKonkaXxSellBillList(konkaXxSellBill);
			request.setAttribute("konkaXxSellBillList", konkaXxSellBillList);
			return mapping.findForward("input");
		} else {
			String msg = super.getMessage(request, "konka.xx.zmd.role.error");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
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

		KonkaXxDistAccountRec entity = new KonkaXxDistAccountRec();
		super.copyProperties(entity, form);

		if (StringUtils.isNotBlank(todo_money)) {
			entity.setFee(new BigDecimal(todo_money));
		}
		entity.getMap().put("bill_ids", bill_ids);
		entity.setDept_id(getKonkaDeptForFgs(user_info.getDept_id()).getDept_id());
		entity.setState(0);
		super.getFacade().getKonkaXxDistAccountRecService().createKonkaXxDistAccountRecWithSellBill(entity);
		saveMessage(request, "entity.inserted");

		return new ActionForward("/../manager/zmd/KonkaXxDistAccountRec.do?mod_id=808204", true);
	}
}
