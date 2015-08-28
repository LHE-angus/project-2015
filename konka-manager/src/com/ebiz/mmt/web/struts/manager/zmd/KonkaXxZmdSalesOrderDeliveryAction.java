package com.ebiz.mmt.web.struts.manager.zmd;

import java.math.BigDecimal;
import java.util.ArrayList;
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
import com.ebiz.mmt.domain.KonkaXxDistEmployee;
import com.ebiz.mmt.domain.KonkaXxPd;
import com.ebiz.mmt.domain.KonkaXxSellBill;
import com.ebiz.mmt.domain.KonkaXxSellBillDetails;
import com.ebiz.mmt.domain.KonkaXxSellBillDetailsDst;
import com.ebiz.mmt.domain.KonkaXxZmd;
import com.ebiz.mmt.domain.KonkaXxZmdStore;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Ren, zhong
 * @version 2012-01-11
 */
public class KonkaXxZmdSalesOrderDeliveryAction extends BaseZmdAction {

	protected ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.delivery(mapping, form, request, response);
	}

	public ActionForward delivery(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String add_date_ge = (String) dynaBean.get("add_date_ge");
		String add_date_le = (String) dynaBean.get("add_date_le");
		String buyer_name_like = (String) dynaBean.get("buyer_name_like");
		String sell_state = (String) dynaBean.get("sell_state");
		String msg = request.getParameter("msg");

		Pager pager = (Pager) dynaBean.get("pager");
		PeProdUser ui = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

		KonkaXxSellBill entity = new KonkaXxSellBill();
		super.copyProperties(entity, form);

		KonkaDept konkaDept = getKonkaDeptForFgs(ui.getDept_id());

		if (konkaDept != null)
			entity.setDept_id(konkaDept.getDept_id());
		entity.getMap().put("sell_state_in", new String[] { "20", "30" });

		entity.getMap().put("add_date_ge", add_date_ge);
		entity.getMap().put("add_date_le", add_date_le);
		entity.getMap().put("buyer_name_like", buyer_name_like);
		entity.getMap().put("sell_state", sell_state);

		Long recordCount = getFacade().getKonkaXxSellBillService().getKonkaXxSellBillCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setCount(pager.getRowCount());
		entity.getRow().setFirst(pager.getFirstRow());
		List<KonkaXxSellBill> list = getFacade().getKonkaXxSellBillService().getKonkaXxSellBillPaginatedList(entity);
		if (null != list && list.size() > 0) {
			for (KonkaXxSellBill sb : list) {
				KonkaXxSellBillDetailsDst dst = new KonkaXxSellBillDetailsDst();
				dst.setSell_bill_id(Long.valueOf(sb.getSell_bill_id()));
				List<KonkaXxSellBillDetailsDst> dstList = getFacade().getKonkaXxSellBillDetailsDstService()
						.getKonkaXxSellBillDetailsDstForPrintOutOrders(dst);

				// sb.setKonkaXxSellBillDetailsDstList(dstList);
				sb.getMap().put("dstList", dstList);
			}
		}
		request.setAttribute("entityList", list);

		KonkaXxZmd konkaXxZmd = new KonkaXxZmd();
		konkaXxZmd.setArc_state(20300);
		if (konkaDept != null)
			konkaXxZmd.setDept_id(konkaDept.getDept_id());
		// konkaXxZmd.getMap().put("zmdNotIn", ui.getDept_id());
		List<KonkaXxZmd> zmdList = getFacade().getKonkaXxZmdService().getKonkaXxZmdList(konkaXxZmd);
		request.setAttribute("zmdList", zmdList);

		KonkaXxDistEmployee distEmployee = new KonkaXxDistEmployee();
		if (konkaDept != null)
			distEmployee.setDept_id(konkaDept.getDept_id());
		distEmployee.setIs_del(0);
		List<KonkaXxDistEmployee> distEmployeeList = getFacade().getKonkaXxDistEmployeeService()
				.getKonkaXxDistEmployeeList(distEmployee);
		request.setAttribute("distEmployeeList", distEmployeeList);

		if ("1".equals(msg)) {
			msg = super.getMessage(request, "konka.zmd.sales.order.delivery.success");
			dynaBean.set("msg", msg);
		}

		return new ActionForward("/../manager/zmd/KonkaXxZmdSalesOrderDelivery/delivery.jsp");
	}

	public ActionForward saveDeliveryWithDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String sell_bill_id = (String) dynaBean.get("dist_sell_bill_id");

		if (StringUtils.isBlank(sell_bill_id)) {
			renderJavaScript(response, "alert('参数丢失');history.back();");
			return null;
		}

		PeProdUser ui = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

		KonkaXxSellBill entity = new KonkaXxSellBill();
		super.copyProperties(entity, form);

		entity.setSell_bill_id(Long.valueOf(sell_bill_id));
		entity.setDist_user_id(ui.getId());
		entity.setDist_real_name(ui.getReal_name());
		entity.setDist_date(new Date());
		entity.setSell_state(30L);

		KonkaXxSellBill bill = new KonkaXxSellBill();
		bill.setSell_bill_id(Long.valueOf(sell_bill_id));
		bill = getFacade().getKonkaXxSellBillService().getKonkaXxSellBill(bill);
		if (null != bill) {
			if ("3".equals(bill.getPay_way().toString())) { // 货到付款
				KonkaXxDistEmployee employee = new KonkaXxDistEmployee();
				employee.setDist_employee_id(entity.getDist_employee_id());
				employee.setIs_del(0);
				employee = getFacade().getKonkaXxDistEmployeeService().getKonkaXxDistEmployee(employee);
				if (null != employee) {
					entity.setDist_payee(employee.getReal_name());
					entity.setDist_payee_id(employee.getIdcard());
				}
			}
		}

		KonkaXxSellBillDetails details = new KonkaXxSellBillDetails();
		details.getMap().put("auditDelivery", sell_bill_id);
		details.setLock_stock_state(2);
		entity.setKonkaXxSellBillDetails(details);

		super.getFacade().getKonkaXxSellBillService().modifyKonkaXxSellBillWithDetailsLock(entity);
		String msg = "1";

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("successDelivery").getPath());
		pathBuffer.append("&").append("mod_id=" + mod_id);
		pathBuffer.append("&").append("msg=" + msg);
		pathBuffer.append("&").append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String zmd_sn_like = (String) dynaBean.get("zmd_sn_like");
		String zmd_id = (String) dynaBean.get("zmd_id");
		String pd_cls_name_like = (String) dynaBean.get("pd_cls_name_like");
		String md_name_like = (String) dynaBean.get("md_name_like");
		String add_date_ge = (String) dynaBean.get("add_date_ge");
		String add_date_le = (String) dynaBean.get("add_date_le");
		String buyer_name_like = (String) dynaBean.get("buyer_name_like");
		String sell_state = (String) dynaBean.get("sell_state");
		String lock_stock_state = (String) dynaBean.get("lock_stock_state");
		String msg = request.getParameter("msg");

		Pager pager = (Pager) dynaBean.get("pager");

		KonkaXxSellBillDetails entity = new KonkaXxSellBillDetails();
		super.copyProperties(entity, form);

		entity.getMap().put("zmd_sn_like", zmd_sn_like);
		entity.getMap().put("zmd_id", zmd_id);
		entity.getMap().put("pd_cls_name_like", pd_cls_name_like);
		entity.getMap().put("md_name_like", md_name_like);
		entity.getMap().put("add_date_ge", add_date_ge);
		entity.getMap().put("add_date_le", add_date_le);
		entity.getMap().put("buyer_name_like", buyer_name_like);
		entity.getMap().put("sell_state", sell_state);
		entity.getMap().put("lock_stock_state", lock_stock_state);

		PeProdUser user_info = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		// PeRoleUser role_info = super.getRoleInfoByThisLogin(request);

		KonkaDept kDept = getKonkaDeptForFgs(user_info.getDept_id());

		List<PeRoleUser> peRoleUserList = getPeRoleList(user_info.getId());

		Boolean role_id_btw_300_400 = false;
		if (peRoleUserList.size() > 0) {
			for (PeRoleUser temp : peRoleUserList) {
				if (temp.getRole_id() >= 300 && temp.getRole_id() < 400) {
					role_id_btw_300_400 = true;
				}
			}
		}
		// long role_id = role_info.getRole_id();
		if (role_id_btw_300_400) { // 分公司人员
			if (kDept != null)
				entity.getMap().put("dept_id", kDept.getDept_id());
		}

		// entity.getMap().put("b_sell_state", 20);
		// entity.getMap().put("e_sell_state", 30);
		entity.getMap().put("sell_state_in", new String[] { "20", "30" });

		Long recordCount = super.getFacade().getKonkaXxSellBillDetailsService()
				.getkonkaXxSellBillDetailsForSalesOrdersCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setCount(pager.getRowCount());
		entity.getRow().setFirst(pager.getFirstRow());
		List<KonkaXxSellBillDetails> list = getFacade().getKonkaXxSellBillDetailsService()
				.getKonkaXxSellBillDetailsForSalesOrdersForResultList(entity);
		request.setAttribute("list", list);

		KonkaXxZmd konkaXxZmd = new KonkaXxZmd();
		konkaXxZmd.setArc_state(20300);
		if (kDept != null)
			konkaXxZmd.setDept_id(kDept.getDept_id());
		// konkaXxZmd.getMap().put("zmdNotIn", ui.getDept_id());
		List<KonkaXxZmd> zmdList = getFacade().getKonkaXxZmdService().getKonkaXxZmdList(konkaXxZmd);
		request.setAttribute("zmdList", zmdList);

		if ("1".equals(msg)) {
			msg = super.getMessage(request, "konka.zmd.sales.order.delivery.success");
			dynaBean.set("msg", msg);
		} else if ("2".equals(msg)) {
			msg = super.getMessage(request, "konka.zmd.sales.order.logistics.success");
			dynaBean.set("msg", msg);
		}

		return mapping.findForward("list");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String sell_bill_id = (String) dynaBean.get("dts_sell_bill_id");
		String sell_bill_details_id = (String) dynaBean.get("dts_sell_bill_details_id");
		String pd_cls_name = (String) dynaBean.get("dts_pd_cls_name");
		String md_name = (String) dynaBean.get("dts_md_name");
		String[] dts_counts = request.getParameterValues("dts_counts"); // 出库数量
		String[] factory_id = request.getParameterValues("factory_id"); // 工厂
		String[] store_id = request.getParameterValues("store_id"); // 仓位编号
		String[] store_desc = request.getParameterValues("store_desc"); // 仓位名称
		String[] dst_remarks = request.getParameterValues("dst_remarks"); // 仓位名称
		String fix_fee = (String) dynaBean.get("fix_fee"); // 安装费
		String account = (String) dynaBean.get("account");
		String fee_of_post = (String) dynaBean.get("fee_of_post"); // 物流费
		String mod_id = (String) dynaBean.get("mod_id");
		String msg = "";

		PeProdUser user_info = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

		if (StringUtils.isBlank(sell_bill_id)) {
			return null;
		}
		if (StringUtils.isBlank(account)) { // 商品发货
			KonkaXxSellBillDetailsDst entity = new KonkaXxSellBillDetailsDst();

			if (StringUtils.isNotBlank(sell_bill_details_id)) {
				List<KonkaXxSellBillDetailsDst> list = new ArrayList<KonkaXxSellBillDetailsDst>();
				if (StringUtils.isNotBlank(sell_bill_id)) {
					entity.setSell_bill_id(Long.valueOf(sell_bill_id));
				}
				entity.setSell_bill_details_id(Long.valueOf(sell_bill_details_id));

				if (null != dts_counts && dts_counts.length > 0) {
					for (int i = 0; i < dts_counts.length; i++) {
						if (StringUtils.isNotBlank(dts_counts[i]) && (!"0".equals(dts_counts[i]))) {
							KonkaXxSellBillDetailsDst kxsbdd = new KonkaXxSellBillDetailsDst();
							kxsbdd.setSell_bill_details_id(Long.valueOf(sell_bill_details_id));
							if (StringUtils.isNotBlank(sell_bill_id)) {
								kxsbdd.setSell_bill_id(Long.valueOf(sell_bill_id));
							}
							kxsbdd.setPd_cls_name(pd_cls_name);
							kxsbdd.setMd_name(md_name);
							kxsbdd.setFactory_id(factory_id[i]);
							kxsbdd.setStore_id(store_id[i]);
							kxsbdd.setStore_name(store_desc[i]);
							kxsbdd.setCounts(Long.valueOf(dts_counts[i]));
							kxsbdd.setDst_remarks(dst_remarks[i]);
							kxsbdd.setDst_date(new Date());
							kxsbdd.setDst_user_id(user_info.getId());
							kxsbdd.setDst_user_name(user_info.getUser_name());

							KonkaXxSellBillDetails kxsbd = new KonkaXxSellBillDetails();
							kxsbd.setSell_bill_details_id(Long.valueOf(sell_bill_details_id));
							kxsbd.setLock_stock_state(2);
							BigDecimal fix = new BigDecimal(fix_fee);
							kxsbd.setFix_fee(fix);
							kxsbdd.setKonkaXxSellBillDetails(kxsbd);

							list.add(kxsbdd);
						}
					}
				}
				entity.setKonkaXxSellBillDetailsDstList(list);
				super.getFacade().getKonkaXxSellBillDetailsDstService().createKonkaXxSellBillDetailsDstForPdStock(
						entity);
				msg = "1";
			}
		} else { // 保存销售单物流费
			KonkaXxSellBill konkaXxSellBill = new KonkaXxSellBill();
			if (StringUtils.isNotBlank(sell_bill_id)) {
				konkaXxSellBill.setSell_bill_id(Long.valueOf(sell_bill_id));
				BigDecimal fee_of_p = new BigDecimal(fee_of_post);
				konkaXxSellBill.setFee_of_post(fee_of_p);
				konkaXxSellBill.getMap().put("wlqr", "true");
				super.getFacade().getKonkaXxSellBillService().modifyKonkaXxSellBill(konkaXxSellBill);
				msg = "2";
			}
		}

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&").append("mod_id=" + mod_id);
		pathBuffer.append("&").append("msg=" + msg);
		// pathBuffer.append("&").append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward saveDelivery(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;

		String sell_bill_id = (String) dynaBean.get("sell_bill_id");
		String sell_bill_details_id = (String) dynaBean.get("sell_bill_details_id");
		String mod_id = (String) dynaBean.get("mod_id");
		String msg = "";

		if (StringUtils.isBlank(sell_bill_id) || StringUtils.isBlank(sell_bill_details_id)) {
			renderJavaScript(response, "alert('参数丢失！');history.back();");
			return null;
		}

		KonkaXxSellBillDetails entity = new KonkaXxSellBillDetails();
		entity.setSell_bill_id(Long.valueOf(sell_bill_id));
		entity.setSell_bill_details_id(Long.valueOf(sell_bill_details_id));
		entity.setLock_stock_state(2);
		super.getFacade().getKonkaXxSellBillDetailsService().modifyKonkaXxSellBillDetailsWithSellState(entity);
		msg = "1";

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&").append("mod_id=" + mod_id);
		pathBuffer.append("&").append("msg=" + msg);
		// pathBuffer.append("&").append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward getPdStoresList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;

		String sell_bill_details_id = (String) dynaBean.get("sell_bill_details_id");
		String sell_bill_id = (String) dynaBean.get("sell_bill_id");
		String zmd_id = (String) dynaBean.get("zmd_id");
		String md_name = (String) dynaBean.get("md_name");
		// String zmd_sn = (String) dynaBean.get("zmd_sn");

		StringBuffer sb = new StringBuffer("[{");
		if (StringUtils.isNotBlank(zmd_id) && StringUtils.isNotBlank(sell_bill_details_id)
				&& StringUtils.isNotBlank(md_name)) {
			KonkaXxZmd konkaXxZmd = new KonkaXxZmd();
			konkaXxZmd.setZmd_id(Long.valueOf(zmd_id));
			konkaXxZmd.setIs_del(0);
			konkaXxZmd = getFacade().getKonkaXxZmdService().getKonkaXxZmd(konkaXxZmd);

			KonkaXxPd konkaXxPd = new KonkaXxPd();
			konkaXxPd.setMd_name(md_name);
			if (null != konkaXxZmd) {
				konkaXxPd.setDept_id(konkaXxZmd.getDept_id());
			}
			konkaXxPd = getFacade().getKonkaXxPdService().getKonkaXxPd(konkaXxPd);
			if (null != konkaXxPd) {
				sb.append("\"fix_fee\":\"").append(konkaXxPd.getFix_fee().toString()).append("\",");
			}

			KonkaXxSellBillDetailsDst kxsbdd = new KonkaXxSellBillDetailsDst();
			kxsbdd.setSell_bill_details_id(Long.valueOf(sell_bill_details_id));
			List<KonkaXxSellBillDetailsDst> dstList = getFacade().getKonkaXxSellBillDetailsDstService()
					.getKonkaXxSellBillDetailsDstList(kxsbdd);

			KonkaXxZmdStore konkaXxZmdStore = new KonkaXxZmdStore();
			konkaXxZmdStore.getMap().put("zmd_id", zmd_id);
			List<KonkaXxZmdStore> list = getFacade().getKonkaXxZmdStoreService()
					.getSalesOrderDeliveryForStocksResultList(konkaXxZmdStore);
			if (null != list && list.size() > 0) {
				for (KonkaXxZmdStore kxzs : list) {
					if (null != dstList && dstList.size() > 0) {
						for (KonkaXxSellBillDetailsDst k : dstList) {
							if (StringUtils.equals(kxzs.getFactory_id(), k.getFactory_id())
									&& StringUtils.equals(kxzs.getStore_id(), k.getStore_id())) {
								kxzs.getMap().put("dstCount", k.getCounts().toString());
							}
						}
					}
				}

				sb.append("\"count\":\"").append(list.size()).append("\",");
				sb.append("\"list\":{");
				for (int i = 0; i < list.size(); i++) {
					// 查询库存
					// Long storeNum = super.getStocks(md_name,
					// list.get(i).getFactory_id(), list.get(i).getStore_id());
					Long storeNum = super.getStocksRealTime(md_name, list.get(i).getFactory_id(), list.get(i)
							.getStore_id());
					if (StringUtils.isNotBlank(sell_bill_id)) {
						KonkaXxSellBillDetailsDst dd = new KonkaXxSellBillDetailsDst();
						dd.setSell_bill_id(Long.valueOf(sell_bill_id));
						dd.setMd_name(md_name);
						dd.setFactory_id(list.get(i).getFactory_id());
						dd.setStore_id(list.get(i).getStore_id());
						dd = getFacade().getKonkaXxSellBillDetailsDstService().getKonkaXxSellBillDetailsDst(dd);
						if (dd != null) {
							storeNum = storeNum + dd.getCounts();
						}
					}

					sb.append("\"store" + i + "\":{");
					sb.append("\"vs\":\"").append(i + 1).append("\",");
					sb.append("\"factory_id\":\"").append(list.get(i).getFactory_id()).append("\",");
					sb.append("\"storeNum\":\"").append(storeNum.toString()).append("\",");
					sb.append("\"dstCount\":\"").append(list.get(i).getMap().get("dstCount")).append("\",");
					sb.append("\"store_id\":\"").append(list.get(i).getStore_id()).append("\",");
					if (i + 1 < list.size()) {
						sb.append("\"store_desc\":\"" + list.get(i).getMap().get("store_desc") + "\"},");
					} else {
						sb.append("\"store_desc\":\"" + list.get(i).getMap().get("store_desc") + "\"}");
					}
				}
				sb.append("}},{");
			} else {
				sb.append("\"count\":\"0\"},{");
			}
		}

		// super.getStocks(md_name, factory_id, store_id)
		sb.append("\"end\":\"rz\"}]");

		super.renderJson(response, sb.toString());
		return null;
	}

	public ActionForward getPdStocks(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// DynaBean dynaBean = (DynaBean) form;

		// String md_name = (String) dynaBean.get("md_name");
		// String factory_id = (String) dynaBean.get("factory_id");
		// String store_id = (String) dynaBean.get("store_id");

		StringBuffer sb = new StringBuffer("[{");

		sb.append("\"end\":\"rz\"}]");

		super.renderJson(response, sb.toString());
		return null;
	}

}
