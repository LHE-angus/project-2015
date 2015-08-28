package com.ebiz.mmt.web.struts.manager.zmd;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.BaseProvinceListFour;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaXxSellBill;
import com.ebiz.mmt.domain.KonkaXxSellBillDetails;
import com.ebiz.mmt.domain.KonkaXxSellBillDetailsDst;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Ren, zhong
 * @version 2012-01-11
 */
public class KonkaXxZmdCustReceiveConfirmAction extends BaseZmdAction {

	protected ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;

		Pager pager = (Pager) dynaBean.get("pager");
		String zmd_sn_like = (String) dynaBean.get("zmd_sn_like");
		String sell_bill_id = (String) dynaBean.get("sell_bill_id");
		String add_date_ge = (String) dynaBean.get("add_date_ge");
		String add_date_le = (String) dynaBean.get("add_date_le");
		String sell_state = (String) dynaBean.get("sell_state");
		String add_user_realname_like = (String) dynaBean.get("add_user_realname_like");
		String buyer_name_like = (String) dynaBean.get("buyer_name_like");
		String pay_way = (String) dynaBean.get("pay_way");
		String msg = request.getParameter("msg");

		super.setBaseTypeListByParIdToRequest(50000L, request);// 付款方式

		KonkaXxSellBill entity = new KonkaXxSellBill();

		PeProdUser user_info = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		List<PeRoleUser> peRoleUserList = getPeRoleList(user_info.getId());

		Boolean role_id_btw_300_400 = false;
		Boolean role_id_btw_20_30_and_200_300 = false;
		if (peRoleUserList.size() > 0) {
			for (PeRoleUser temp : peRoleUserList) {
				if ((temp.getRole_id() >= 300 && temp.getRole_id() < 400)
						|| (temp.getRole_id() >= 30 && temp.getRole_id() < 40))
					role_id_btw_300_400 = true;

				if ((temp.getRole_id() >= 200 && temp.getRole_id() < 300) || (temp.getRole_id() < 30))
					role_id_btw_20_30_and_200_300 = true;
			}
		}

		if (role_id_btw_300_400) {
			entity.setDept_id(super.getKonkaDeptForFgs(user_info.getDept_id()).getDept_id());
		} else if (!role_id_btw_20_30_and_200_300 && !role_id_btw_300_400) {
			entity.setDept_id(-1L);
		}
		//entity.setDept_id(user_info.getDept_id());
		entity.getMap().put("receive_state_bg", 30);
		entity.getMap().put("receive_state_en", 70);

		// 选择的条件字段
		if (StringUtils.isNotBlank(zmd_sn_like)) {
			entity.getMap().put("zmd_sn_like", zmd_sn_like);
		}
		if (GenericValidator.isLong(sell_bill_id)) {
			entity.setSell_bill_id(Long.valueOf(sell_bill_id));
		}
		if (StringUtils.isNotBlank(add_date_ge)) {
			entity.getMap().put("add_date_ge", add_date_ge);
		}
		if (StringUtils.isNotBlank(add_date_le)) {
			entity.getMap().put("add_date_le", add_date_le);
		}
		if (GenericValidator.isLong(sell_state)) {
			entity.setSell_state(Long.valueOf(sell_state));
		}
		if (StringUtils.isNotBlank(add_user_realname_like)) {
			entity.getMap().put("add_user_realname_like", add_user_realname_like);
		}
		if (StringUtils.isNotBlank(buyer_name_like)) {
			entity.getMap().put("buyer_name_like", buyer_name_like);
		}
		if (GenericValidator.isLong(pay_way)) {
			entity.setPay_way(Long.valueOf(pay_way));
		}

		Long recordCount = super.getFacade().getKonkaXxSellBillService().getKonkaXxSellBillCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setCount(pager.getRowCount());
		entity.getRow().setFirst(pager.getFirstRow());

		List<KonkaXxSellBill> entityList = super.getFacade().getKonkaXxSellBillService()
				.getKonkaXxSellBillPaginatedList(entity);

		request.setAttribute("entityList", entityList);

		if ("1".equals(msg)) {
			msg = super.getMessage(request, "konka.zmd.custom.receive.confirm.success");
			dynaBean.set("msg", msg);
		}

		return mapping.findForward("list");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String sell_bill_id = (String) dynaBean.get("sell_bill_id");

		if (StringUtils.isBlank(sell_bill_id)) {
			String msg = getMessage(request, "konka.zmd.sales.order.sellBillId.missing");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
		}

		KonkaXxSellBill konkaXxSellBill = new KonkaXxSellBill();
		konkaXxSellBill.setSell_bill_id(Long.valueOf(sell_bill_id));
		konkaXxSellBill = getFacade().getKonkaXxSellBillService().getKonkaXxSellBill(konkaXxSellBill);
		if (null == konkaXxSellBill) {
			return null;
		}
		super.copyProperties(form, konkaXxSellBill);

		BaseProvinceListFour postBp = new BaseProvinceListFour();
		postBp.setP_index(konkaXxSellBill.getSell_post_p_index());
		postBp.setDel_mark(0);
		postBp = getFacade().getBaseProvinceListFourService().getBaseProvinceListFour(postBp);
		if (null != postBp) {
			dynaBean.set("post_area", postBp.getFull_name());
		}

		BaseProvinceListFour custBp = new BaseProvinceListFour();
		custBp.setP_index(konkaXxSellBill.getBuyer_p_index());
		custBp.setDel_mark(0);
		custBp = getFacade().getBaseProvinceListFourService().getBaseProvinceListFour(custBp);
		if (null != custBp) {
			dynaBean.set("cust_area", custBp.getFull_name());
		}

		KonkaDept konkaDept = new KonkaDept();
		konkaDept.setDept_id(Long.valueOf(konkaXxSellBill.getDept_id()));
		konkaDept = getFacade().getKonkaDeptService().getKonkaDept(konkaDept);
		if (null == konkaDept) {
			return null;
		}
		dynaBean.set("dept_name", konkaDept.getDept_name());

		KonkaXxSellBillDetails konkaXxSellBillDetails = new KonkaXxSellBillDetails();
		konkaXxSellBillDetails.setSell_bill_id(konkaXxSellBill.getSell_bill_id());
		konkaXxSellBillDetails.setLock_stock_state(2);
		List<KonkaXxSellBillDetails> konkaXxSellBillDetailsList = getFacade().getKonkaXxSellBillDetailsService()
				.getKonkaXxSellBillDetailsList(konkaXxSellBillDetails);
		if (null != konkaXxSellBillDetailsList && konkaXxSellBillDetailsList.size() > 0) {
			for (KonkaXxSellBillDetails kxsbd : konkaXxSellBillDetailsList) {
				KonkaXxSellBillDetailsDst kxsbdd = new KonkaXxSellBillDetailsDst();
				kxsbdd.setSell_bill_details_id(kxsbd.getSell_bill_details_id());
				List<KonkaXxSellBillDetailsDst> list = getFacade().getKonkaXxSellBillDetailsDstService()
						.getKonkaXxSellBillDetailsDstList(kxsbdd);
				kxsbd.getMap().put("dstList", list);
			}

		}
		request.setAttribute("konkaXxSellBillDetailsList", konkaXxSellBillDetailsList);

		super.setBaseTypeListByParIdToRequest(50000L, request);
		super.setBaseTypeListByParIdToRequest(70000L, request);
		super.setBaseTypeListByParIdToRequest(90000L, request);

		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String sell_bill_id = (String) dynaBean.get("sell_bill_id");
		String mod_id = (String) dynaBean.get("mod_id");
		String msg = "";

		KonkaXxSellBill entity = new KonkaXxSellBill();
		super.copyProperties(entity, form);

		PeProdUser ui = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

		if (StringUtils.isNotBlank(sell_bill_id)) {
			entity.setSell_bill_id(Long.valueOf(sell_bill_id));

			// 收货确认时，交易完成
			entity.setSell_state(70L);
			// entity.setSell_state(40L);
			entity.setCheck_recgoods_user_id(ui.getId());
			entity.setCheck_recgoods_real_name(ui.getReal_name());
			entity.setTake_googs_date(new Date()); // 确认收货时间
			super.getFacade().getKonkaXxSellBillService().modifyKonkaXxSellBill(entity);
			msg = "1";
		}

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&").append("mod_id=" + mod_id);
		pathBuffer.append("&").append("msg=" + msg);
		pathBuffer.append("&").append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

}
