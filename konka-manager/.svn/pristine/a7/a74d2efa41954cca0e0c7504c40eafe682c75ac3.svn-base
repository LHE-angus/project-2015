package com.ebiz.mmt.web.struts.manager.zmd;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.BaseProvinceListFour;
import com.ebiz.mmt.domain.JfScorts;
import com.ebiz.mmt.domain.JfScortsDetails;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaXxLogistics;
import com.ebiz.mmt.domain.KonkaXxPd;
import com.ebiz.mmt.domain.KonkaXxSellBill;
import com.ebiz.mmt.domain.KonkaXxSellBillDetails;
import com.ebiz.mmt.domain.KonkaXxZmd;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.r3.KHXD;
import com.ebiz.mmt.r3.ReturnInfo;
import com.ebiz.mmt.web.Constants;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Ren, zhong
 * @version 2012-01-11
 */
public class KonkaXxZmdAuditSalesOrderAction extends BaseZmdAction {

	DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;

		Pager pager = (Pager) dynaBean.get("pager");
		String zmd_sn = (String) dynaBean.get("zmd_sn");
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
		super.copyProperties(entity, form);

		PeProdUser user_info = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		List<PeRoleUser> peRoleUserList = getPeRoleList(user_info.getId());

		Boolean role_id_eq_350 = false;
		if (peRoleUserList.size() > 0) {
			for (PeRoleUser temp : peRoleUserList) {
				if (temp.getRole_id() == 350)
					role_id_eq_350 = true;
			}
		}

		if (!role_id_eq_350) {
			String msg1 = super.getMessage(request, "konka.xx.zmd.role.error");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg1 + "');history.back();}");
			return null;
		}
		
		Long dept_id = getKonkaDeptForFgs(user_info.getDept_id()).getDept_id();
		entity.setDept_id(dept_id);

		// 选择的条件字段
		if (StringUtils.isNotBlank(zmd_sn)) {
			entity.setZmd_sn(zmd_sn);
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
			if ("20".equals(sell_state)) {
				entity.setSell_state(null);
				entity.getMap().put("is_audit_pass", true);
			} else {
				entity.setSell_state(Long.valueOf(sell_state));
			}
		} else {
			entity.setSell_state(10L);
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
		if (null != entityList && entityList.size() > 0) {
			for (KonkaXxSellBill kxsb : entityList) {
				String add_date = df.format(kxsb.getAdd_date());
				kxsb.getMap().put("add_date", add_date);

				BaseProvinceListFour bp = new BaseProvinceListFour();
				bp.setP_index(kxsb.getBuyer_p_index());
				bp.setDel_mark(0);
				bp = getFacade().getBaseProvinceListFourService().getBaseProvinceListFour(bp);
				if (null != bp) {
					kxsb.getMap().put("full_name", bp.getFull_name());
				}
			}
		}

		request.setAttribute("entityList", entityList);

		KonkaXxZmd konkaXxZmd = new KonkaXxZmd();
		konkaXxZmd.setArc_state(20300);
		konkaXxZmd.setIs_del(0);
		konkaXxZmd.setDept_id(dept_id);
		// konkaXxZmd.getMap().put("zmdNotIn", ui.getDept_id());
		List<KonkaXxZmd> zmdList = getFacade().getKonkaXxZmdService().getKonkaXxZmdList(konkaXxZmd);
		request.setAttribute("zmdList", zmdList);

		if ("1".equals(msg)) {
			msg = super.getMessage(request, "konka.zmd.audit.sales.order.success");
			dynaBean.set("msg", msg);
		} else if ("2".equals(msg)) {
			msg = super.getMessage(request, "konka.zmd.sales.order.logistics.success");
			dynaBean.set("msg", msg);
		}

		return mapping.findForward("list");
	}

	public ActionForward Audit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
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
			renderJavaScript(response, "alert('参数丢失！');history.back();");
			return null;
		}
		super.copyProperties(form, konkaXxSellBill);

		// KonkaXxZmd konkaXxZmd = new KonkaXxZmd();
		// konkaXxZmd.setZmd_id(konkaXxSellBill.getZmd_id());
		// konkaXxZmd.setArc_state(20300);
		// konkaXxZmd =
		// getFacade().getKonkaXxZmdService().getKonkaXxZmd(konkaXxZmd);
		// if (null != konkaXxZmd) {
		// dynaBean.set("cur_credit_line", konkaXxZmd.getCur_credit_line());
		// dynaBean.set("total_credit_line", konkaXxZmd.getTotal_credit_line());
		// }

		if (null != konkaXxSellBill.getSell_post_p_index()) {
			BaseProvinceListFour baseProvinceListFour = new BaseProvinceListFour();
			baseProvinceListFour.setP_index(konkaXxSellBill.getSell_post_p_index());
			baseProvinceListFour = getFacade().getBaseProvinceListFourService().getBaseProvinceListFour(
					baseProvinceListFour);
			if (null != baseProvinceListFour) {
				dynaBean.set("sell_post_area", baseProvinceListFour.getFull_name());
			}
		}

		if (null != konkaXxSellBill.getBuyer_p_index()) {

			BaseProvinceListFour baseProvinceListFour = new BaseProvinceListFour();
			baseProvinceListFour.setP_index(konkaXxSellBill.getBuyer_p_index());
			baseProvinceListFour = getFacade().getBaseProvinceListFourService().getBaseProvinceListFour(
					baseProvinceListFour);
			if (null != baseProvinceListFour) {

				dynaBean.set("buyer_area", baseProvinceListFour.getFull_name());
			}
		}

		KonkaDept konkaDept = new KonkaDept();
		konkaDept.setDept_id(Long.valueOf(konkaXxSellBill.getDept_id()));
		konkaDept = getFacade().getKonkaDeptService().getKonkaDept(konkaDept);
		if (null == konkaDept) {
			return null;
		}
		dynaBean.set("dept_name", konkaDept.getDept_name());

		List<KonkaXxLogistics> konkaXxLogisticsList = new ArrayList<KonkaXxLogistics>();
		BigDecimal wl_fee = new BigDecimal("0");

		KonkaXxSellBillDetails konkaXxSellBillDetails = new KonkaXxSellBillDetails();
		konkaXxSellBillDetails.setSell_bill_id(konkaXxSellBill.getSell_bill_id());
		konkaXxSellBillDetails.setLock_stock_state(1);
		List<KonkaXxSellBillDetails> konkaXxSellBillDetailsList = getFacade().getKonkaXxSellBillDetailsService()
				.getKonkaXxSellBillDetailsList(konkaXxSellBillDetails);
		if (null != konkaXxSellBillDetailsList && konkaXxSellBillDetailsList.size() > 0) {
			for (KonkaXxSellBillDetails kxsbd : konkaXxSellBillDetailsList) {
				KonkaXxPd konkaXxPd = new KonkaXxPd();
				konkaXxPd.setDept_id(konkaXxSellBill.getDept_id());
				konkaXxPd.setMd_name(kxsbd.getMd_name());
				konkaXxPd = getFacade().getKonkaXxPdService().getKonkaXxPd(konkaXxPd);
				if (null != konkaXxPd) {
					kxsbd.getMap().put("price_min", konkaXxPd.getPrice_min());
				}

				// KonkaXxSellBillDetailsDst kxsbdd = new
				// KonkaXxSellBillDetailsDst();
				// kxsbdd.setSell_bill_details_id(kxsbd.getSell_bill_details_id());
				// List<KonkaXxSellBillDetailsDst> list =
				// getFacade().getKonkaXxSellBillDetailsDstService()
				// .getKonkaXxSellBillDetailsDstList(kxsbdd);
				// if (null != list && list.size() > 0) {
				// for (KonkaXxSellBillDetailsDst dst : list) {
				// Long storeNum = super.getStocksRealTime(dst.getMd_name(),
				// dst.getFactory_id(), dst
				// .getStore_id())
				// + dst.getCounts();
				// dst.getMap().put("storeNum", storeNum);
				// }
				// }
				// kxsbd.getMap().put("dstList", list);

				if (null != konkaXxSellBill.getSell_post_p_index() && null != kxsbd.getMd_name()) {
					Long p_index = konkaXxSellBill.getSell_post_p_index();
					String p_index_s = StringUtils.substring(String.valueOf(p_index), 0, 6);

					KonkaXxLogistics logistics = new KonkaXxLogistics();
					logistics.setP_index_e(Long.valueOf(p_index_s));
					logistics.setDept_id(konkaXxSellBill.getDept_id());
					logistics.setMd_name(kxsbd.getMd_name());
					KonkaXxLogistics _logistics = super.getFacade().getKonkaXxLogisticsService().getKonkaXxLogistics(
							logistics);

					BigDecimal pd_totle_fee = null;
					if (null == _logistics) {
						logistics.setFee(new BigDecimal("-1"));
						_logistics = logistics;
					} else if (null != _logistics.getFee()) {
						pd_totle_fee = _logistics.getFee().multiply(new BigDecimal(kxsbd.getCounts()));
						wl_fee = wl_fee.add(pd_totle_fee);
					}

					_logistics.getMap().put("xs_counts", kxsbd.getCounts());
					_logistics.getMap().put("pd_totle_fee", pd_totle_fee);

					konkaXxLogisticsList.add(_logistics);
				}
			}

		}
		request.setAttribute("konkaXxSellBillDetailsList", konkaXxSellBillDetailsList);
		request.setAttribute("konkaXxLogisticsList", konkaXxLogisticsList);
		request.setAttribute("wl_fee", wl_fee);

		// 积分
		if (!"".equals(konkaXxSellBill.getConsumer_grade_num()) && null != konkaXxSellBill.getConsumer_grade_num()) {
			JfScortsDetails jfScortsDetails = new JfScortsDetails();
			jfScortsDetails.setOut_sys_id(sell_bill_id);
			jfScortsDetails.setUser_sn(konkaXxSellBill.getConsumer_grade_num());
			jfScortsDetails.setOut_sys_name("KONKA_XX_SELL_BILL");
			List<JfScortsDetails> jfScortsDetailsList = super.getFacade().getJfScortsDetailsService()
					.getJfScortsDetailsList(jfScortsDetails);
			if (jfScortsDetailsList.size() > 0) {
				BigDecimal count = new BigDecimal(0);
				request.setAttribute("jfScortsDetailsList", jfScortsDetailsList);
				for (JfScortsDetails temp1 : jfScortsDetailsList) {
					if (null != temp1.getScorts()) {
						count = count.add(temp1.getScorts());
					}
				}
				request.setAttribute("bill_count", count);
			}

			JfScorts jfScorts = new JfScorts();
			jfScorts.setUser_sn(konkaXxSellBill.getConsumer_grade_num());
			List<JfScorts> jfScortsList = super.getFacade().getJfScortsService().getJfScortsList(jfScorts);
			if (jfScortsList.size() > 0) {
				request.setAttribute("jf_value", jfScortsList.get(0).getTotal_scorts());
			} else {
				request.setAttribute("jf_value", 0);
			}
		}

		// 调R3接口，返回客户账期
		KonkaXxZmd konkaXxZmd = new KonkaXxZmd();
		konkaXxZmd.setZmd_id(konkaXxSellBill.getZmd_id());
		konkaXxZmd = super.getFacade().getKonkaXxZmdService().getKonkaXxZmd(konkaXxZmd);

		ReturnInfo<KHXD> info = new ReturnInfo<KHXD>();
		if (null != konkaXxZmd && konkaXxZmd.getR3_id() != null) {
			request.setAttribute("r3_id", konkaXxZmd.getR3_id());
			// List<KHXD> khxdList =
			// super.getFacade().getR3WebInterfaceService().getKhxd("KF01",
			// null, "10",
			// new String[] { konkaXxZmd.getR3_id() });

			List<KHXD> khxdList = new ArrayList<KHXD>();
			info = getFacade().getR3WebInterfaceService().getKhxd("KF01", null, "10",
					new String[] { konkaXxZmd.getR3_id() });
			if (info.getSuccess() == true)
				khxdList = info.getDataResult();

			if (khxdList.size() > 0) {
				request.setAttribute("zqzed", khxdList.get(0).getZSYED());
			}
		}

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
		String delivery = (String) dynaBean.get("delivery");
		String msg = "";
		String printBillId = "";

		if (StringUtils.isBlank(sell_bill_id)) {
			renderJavaScript(response, "alert('参数丢失！');history.back();");
			return null;
		}

		KonkaXxSellBill entity = new KonkaXxSellBill();
		super.copyProperties(entity, form);

		PeProdUser ui = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

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
			renderJavaScript(response, "alert('参数丢失！');history.back();");
			return null;
		}

		if (StringUtils.isNotBlank(sell_bill_id)) {
			// if (!this.validateBalance(request, response,
			// Long.valueOf(sell_bill_id))) {
			// return null;
			// }

			entity.setSell_bill_id(Long.valueOf(sell_bill_id));
			entity.setAudit_date(new Date());
			entity.setAudit_user_id(ui.getId());
			entity.setAudit_real_name(ui.getReal_name());

			if ("20".equals(entity.getSell_state().toString())) { // 审核通过
				// 页面是否发货选项已隐藏，下面已没有作用Begin
				printBillId = sell_bill_id;
				if ("0".equals(delivery)) { // 选择发货
					KonkaXxSellBillDetails details = new KonkaXxSellBillDetails();
					details.getMap().put("auditDelivery", sell_bill_id);
					details.setLock_stock_state(2);

					printBillId = sell_bill_id;

					entity.setDist_date(new Date());
					// entity.setSell_state(30L); // 置为已发货
					entity.setSell_state(20L);
					entity.setKonkaXxSellBillDetails(details);
				}
				// 页面是否发货选项已隐藏End
			}

			super.getFacade().getKonkaXxSellBillService().modifyKonkaXxSellBillForAuditOrder(entity);
			msg = "1";
		}

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&").append("mod_id=" + mod_id);
		pathBuffer.append("&").append("msg=" + msg);
		pathBuffer.append("&").append("printBillId=" + printBillId);
		pathBuffer.append("&").append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward plAudit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;

		String mod_id = (String) dynaBean.get("mod_id");
		// String sell_state = (String) dynaBean.get("sell_state");
		String sell_bill_id_hidden = (String) dynaBean.get("sell_bill_id_hidden");
		String[] pkss = request.getParameterValues("pkss");
		String msg = "";

		PeProdUser ui = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

		KonkaXxSellBill entity = new KonkaXxSellBill();
		super.copyProperties(entity, form);
		entity.setAudit_date(new Date());
		entity.getMap().put("audit_uesr_id", ui.getId());
		entity.getMap().put("audit_user_name", ui.getReal_name());

		if (ArrayUtils.isNotEmpty(pkss)) {
			// 判断余额
			// for (int i = 0; i < pkss.length; i++) {
			// if (!validateBalance(request, response, Long.valueOf(pkss[i]))) {
			// return null;
			// }
			// }

			entity.getMap().put("pkss", pkss);
			super.getFacade().getKonkaXxSellBillService().modifyKonkaXxSellBillForAuditOrder(entity);
			msg = "1";
		} else {
			if (StringUtils.isNotBlank(sell_bill_id_hidden)) {
				// // 判断余额
				// if (!validateBalance(request, response,
				// Long.valueOf(sell_bill_id_hidden))) {
				// return null;
				// }

				entity.setSell_bill_id(Long.valueOf(sell_bill_id_hidden));
				super.getFacade().getKonkaXxSellBillService().modifyKonkaXxSellBillForAuditOrder(entity);
				msg = "1";
			}
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

	public ActionForward saveLogisticsCosts(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;

		String sell_bill_id = (String) dynaBean.get("dts_sell_bill_id");
		// String account = (String) dynaBean.get("account");
		String fee_of_post = (String) dynaBean.get("fee_of_post"); // 物流费
		String mod_id = (String) dynaBean.get("mod_id");
		String msg = "";

		KonkaXxSellBill konkaXxSellBill = new KonkaXxSellBill();
		if (StringUtils.isNotBlank(sell_bill_id)) {
			konkaXxSellBill.setSell_bill_id(Long.valueOf(sell_bill_id));
			BigDecimal fee_of_p = new BigDecimal(fee_of_post);
			konkaXxSellBill.setFee_of_post(fee_of_p);
			konkaXxSellBill.getMap().put("wlqr", "true");
			super.getFacade().getKonkaXxSellBillService().modifyKonkaXxSellBill(konkaXxSellBill);
			msg = "2";
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

	// private boolean validateBalance(HttpServletRequest request,
	// HttpServletResponse response, Long sell_bill_id) {
	//
	// KonkaXxSellBill konkaXxSellBill = new KonkaXxSellBill();
	// konkaXxSellBill.setSell_bill_id(sell_bill_id);
	// konkaXxSellBill =
	// getFacade().getKonkaXxSellBillService().getKonkaXxSellBill(konkaXxSellBill);
	// if (null != konkaXxSellBill) {
	// KonkaXxZmd xxZmd = new KonkaXxZmd();
	// xxZmd.setZmd_id(konkaXxSellBill.getZmd_id());
	// xxZmd.setArc_state(20300);
	// xxZmd = getFacade().getKonkaXxZmdService().getKonkaXxZmd(xxZmd);
	// if (null != xxZmd) {
	// Double total_money = konkaXxSellBill.getTotal_money().doubleValue(); //
	// 该订单销售总金额
	// // Double cur_credit_line =
	// // xxZmd.getCur_credit_line().doubleValue(); //信用额度
	// Double total_credit_line = xxZmd.getTotal_credit_line().doubleValue(); //
	// 当前余额
	// if (total_credit_line < total_money) {
	// String ms = getMessage(request, "konka.xx.zmd.balance.lt.total.money",
	// new String[] { xxZmd
	// .getZmd_sn() });
	// super.renderJavaScript(response, "alert('" + ms + "');history.back();");
	// return false;
	// }
	// }
	// }
	//
	// return true;
	// }

}
