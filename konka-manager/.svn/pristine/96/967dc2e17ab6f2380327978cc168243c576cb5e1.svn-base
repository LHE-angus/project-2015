package com.ebiz.mmt.web.struts.manager.zmd;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaXxSellBill;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Li,Yuan
 * @version 2012-01-11
 */
public class KonkaXxDistJsAction extends BaseZmdAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);

		Pager pager = (Pager) dynaBean.get("pager");
		pager.setPageSize(10);

		String zmd_sn_like = (String) dynaBean.get("zmd_sn_like");
		String sell_date_begin = (String) dynaBean.get("sell_date_begin");
		String sell_date_end = (String) dynaBean.get("sell_date_end");
		String sell_bill_id = (String) dynaBean.get("sell_bill_id");

		KonkaXxSellBill entity = new KonkaXxSellBill();

		if (GenericValidator.isLong(sell_bill_id)) {
			entity.setSell_bill_id(Long.valueOf(sell_bill_id));
		}

		entity.getMap().put("zmd_sn_like", zmd_sn_like);
		entity.getMap().put("sell_date_begin", sell_date_begin);
		entity.getMap().put("sell_date_end", sell_date_end);
		// 当前分公司
		KonkaDept kDept = getKonkaDeptForFgs(super.getSessionUserInfo(request).getDept_id());
		if (kDept != null)
			entity.setDept_id(kDept.getDept_id());
		// 销售单状态 --->30用户已确认收货
		// entity.setSell_state(Long.valueOf(30));
		entity.getMap().put("sell_state_ge", 70);
		// 财务未结算销售单
		entity.setJs_dist_state(0);

		entity.setOrder_type(1);
		entity.setStock_from(1);

		Long recordCount = getFacade().getKonkaXxSellBillService().getKonkaXxSellBillCountForJs(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		entity.getMap().put("order_by_dept", true);
		List<KonkaXxSellBill> entityList = getFacade().getKonkaXxSellBillService()
				.getKonkaXxSellBillForJSPaginatedList(entity);
		request.setAttribute("entityList", entityList);

		return mapping.findForward("list");
	}

	public ActionForward jiesMethod(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String[] pks = (String[]) dynaBean.get("pks");
		String sell_bill_id = (String) dynaBean.get("sell_bill_id");

		KonkaXxSellBill entity = new KonkaXxSellBill();
		entity.setJs_dist_state(1);
		// entity.setSell_state(70L);
		entity.setJs_dist_date(new Date());
		entity.setJs_dist_user_id(super.getSessionUserInfo(request).getId());
		entity.setJs_dist_user_realname(super.getSessionUserInfo(request).getReal_name());
		if (GenericValidator.isLong(sell_bill_id)) {
			entity.setSell_bill_id(Long.valueOf(sell_bill_id));
			getFacade().getKonkaXxSellBillService().modifyKonkaXxSellBill(entity);
		} else if (!ArrayUtils.isEmpty(pks)) {
			entity.getMap().put("pks", pks);
			getFacade().getKonkaXxSellBillService().modifyKonkaXxSellBill(entity);
		}
		saveMessage(request, "konka.entity.jies.success");

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "id", "pks", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}
}
