package com.ebiz.mmt.web.struts.manager.zmd;

import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.json.JSONObject;
import org.springframework.mail.SimpleMailMessage;

import com.ebiz.mmt.domain.BaseProvinceListFour;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaXxBaseType;
import com.ebiz.mmt.domain.KonkaXxDistEmployee;
import com.ebiz.mmt.domain.KonkaXxPd;
import com.ebiz.mmt.domain.KonkaXxSellBill;
import com.ebiz.mmt.domain.KonkaXxSellBillDetails;
import com.ebiz.mmt.domain.KonkaXxSellBillDetailsDst;
import com.ebiz.mmt.domain.KonkaXxStdStore;
import com.ebiz.mmt.domain.KonkaXxZmd;
import com.ebiz.mmt.domain.KonkaXxZmdPdStore;
import com.ebiz.mmt.domain.KonkaXxZmdUsers;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.util.DESPlus;

/**
 * @author Jiang, JiaYong
 * @version 2013-04-24
 */
public class KonkaXxZmdOrderAddAction extends BaseZmdAction {

	private static DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

	protected ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.add(mapping, form, request, response);
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String msg = (String) dynaBean.get("msg");
		String billId = (String) dynaBean.get("billId");
		String nn_state = (String) dynaBean.get("nn_state");

		super.setBaseTypeListByParIdToRequest(70000L, request);
		super.setBaseTypeListByParIdToRequest(90000L, request);

		PeProdUser ui = super.getSessionUserInfo(request);

		KonkaXxZmdUsers konkaXxZmdUsers = new KonkaXxZmdUsers();
		konkaXxZmdUsers.setUser_id(ui.getId());
		konkaXxZmdUsers.getRow().setCount(2);
		List<KonkaXxZmdUsers> konkaXxZmdUsersList = getFacade().getKonkaXxZmdUsersService().getKonkaXxZmdUsersList(
				konkaXxZmdUsers);
		if (null == konkaXxZmdUsersList || konkaXxZmdUsersList.size() == 0) {
			String m = getMessage(request, "konka.zmd.userinfo.missing");
			super.renderJavaScript(response, "window.onload=function(){alert('" + m + "');history.back();}");
			return null;
		}
		if (konkaXxZmdUsersList.size() == 2) {
			String m = getMessage(request, "konka.zmd.userinfo.too.many");
			super.renderJavaScript(response, "window.onload=function(){alert('" + m + "');history.back();}");
			return null;
		}

		KonkaXxZmd konkaXxZmd = new KonkaXxZmd();
		konkaXxZmd.setZmd_id(konkaXxZmdUsersList.get(0).getZmd_id());
		konkaXxZmd.setIs_del(0);
		konkaXxZmd.getRow().setCount(2);
		List<KonkaXxZmd> konkaXxZmdList = getFacade().getKonkaXxZmdService().getKonkaXxZmdList(konkaXxZmd);
		if (null == konkaXxZmdList || konkaXxZmdList.size() == 0) {
			String m = getMessage(request, "konka.zmd.missing");
			super.renderJavaScript(response, "window.onload=function(){alert('" + m + "');history.back();}");
			return null;
		}
		if (konkaXxZmdList.size() == 2) {
			String m = getMessage(request, "konka.zmd.too.many");
			super.renderJavaScript(response, "window.onload=function(){alert('" + m + "');history.back();}");
			return null;
		}
		dynaBean.set("konkaXxZmd", konkaXxZmdList.get(0));
		dynaBean.set("zmd_id", konkaXxZmdList.get(0).getZmd_id());
		dynaBean.set("zmd_sn", konkaXxZmdList.get(0).getZmd_sn());
		dynaBean.set("zmd_p_index", konkaXxZmdList.get(0).getP_index());
		// dynaBean.set("zmd_total_credit_line",
		// konkaXxZmdList.get(0).getTotal_credit_line());//专卖店信用额度
		dynaBean.set("reserverd_stock", konkaXxZmdList.get(0).getReserverd_stock());// 保留库存数量，是指专卖店可销售单个产品型号的最低库存值，低于这个库存值不能进行销售。

		KonkaDept konkaDept = new KonkaDept();
		konkaDept.setDept_id(konkaXxZmdList.get(0).getDept_id());
		konkaDept.getRow().setCount(2);
		List<KonkaDept> konkaDeptList = getFacade().getKonkaDeptService().getKonkaDeptList(konkaDept);
		if (null == konkaDeptList || konkaDeptList.size() == 0) {
			String m = getMessage(request, "konka.zmd.deptinfo.missing");
			super.renderJavaScript(response, "window.onload=function(){alert('" + m + "');history.back();}");
			return null;
		}
		if (konkaDeptList.size() == 2) {
			String m = getMessage(request, "konka.zmd.deptinfo.too.many");
			super.renderJavaScript(response, "window.onload=function(){alert('" + m + "');history.back();}");
			return null;
		}
		dynaBean.set("dept_id", konkaDeptList.get(0).getDept_id());
		dynaBean.set("dept_name", konkaDeptList.get(0).getDept_name());

		KonkaXxPd konkaXxPd = new KonkaXxPd();
		konkaXxPd.setDept_id(konkaDeptList.get(0).getDept_id());
		konkaXxPd.getMap().put("isSales", df.format(new Date()));
		konkaXxPd.setAudit_state(1);
		List<KonkaXxPd> pdIdList = getFacade().getKonkaXxPdService().getKonkaXxPdList(konkaXxPd);
		request.setAttribute("pdIdList", pdIdList);

		if ("1".equals(msg)) {
			msg = super.getMessage(request, "konka.zmd.save.sales.order.success");
			dynaBean.set("msg", msg);
		} else if ("2".equals(msg)) {
			msg = super.getMessage(request, "konka.zmd.update.sales.order.success");
			dynaBean.set("msg", msg);
		}
		dynaBean.set("billId", billId);
		dynaBean.set("nn_state", nn_state);

		return mapping.findForward("input");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String sell_bill_id = (String) dynaBean.get("sell_bill_id");
		super.setBaseTypeListByParIdToRequest(70000L, request);
		super.setBaseTypeListByParIdToRequest(90000L, request);

		PeProdUser ui = super.getSessionUserInfo(request);
		if (null == ui) {
			String m = getMessage(request, "konka.zmd.user.info.missing");
			super.renderJavaScript(response, "window.onload=function(){alert('" + m + "');history.back();}");
			return null;
		}

		if (StringUtils.isBlank(sell_bill_id)) {
			String m = getMessage(request, "konka.xx.zmd.sill.bill.lost");
			super.renderJavaScript(response, "window.onload=function(){alert('" + m + "');history.back();}");
			return null;
		}
		setOrderNavigationRequest(Long.valueOf(sell_bill_id), request);

		KonkaXxSellBill entity = new KonkaXxSellBill();
		entity.setSell_bill_id(Long.valueOf(sell_bill_id));
		entity.setOrder_type(2);
		entity.getRow().setCount(2);
		List<KonkaXxSellBill> list = getFacade().getKonkaXxSellBillService().getKonkaXxSellBillList(entity);
		if (null == list || list.size() == 0) {
			String m = getMessage(request, "konka.xx.zmd.sill.bill.lost");
			super.renderJavaScript(response, "window.onload=function(){alert('" + m + "');history.back();}");
			return null;
		} else if (list.size() == 2) {
			String m = getMessage(request, "konka.xx.zmd.sill.bill.error");
			super.renderJavaScript(response, "window.onload=function(){alert('" + m + "');history.back();}");
			return null;
		}
		KonkaXxSellBill sellBill = list.get(0);

		KonkaXxZmd konkaXxZmd = new KonkaXxZmd();
		konkaXxZmd.setZmd_id(sellBill.getZmd_id());
		konkaXxZmd.setIs_del(0);
		konkaXxZmd.getRow().setCount(2);
		List<KonkaXxZmd> konkaXxZmdList = getFacade().getKonkaXxZmdService().getKonkaXxZmdList(konkaXxZmd);
		if (null == konkaXxZmdList || konkaXxZmdList.size() == 0) {
			String m = getMessage(request, "konka.zmd.missing");
			super.renderJavaScript(response, "window.onload=function(){alert('" + m + "');history.back();}");
			return null;
		}
		if (konkaXxZmdList.size() == 2) {
			String m = getMessage(request, "konka.zmd.too.many");
			super.renderJavaScript(response, "window.onload=function(){alert('" + m + "');history.back();}");
			return null;
		}
		dynaBean.set("reserverd_stock", konkaXxZmdList.get(0).getReserverd_stock());// 保留库存数量，是指专卖店可销售单个产品型号的最低库存值，低于这个库存值不能进行销售。

		KonkaXxSellBillDetails sellBillDetails = new KonkaXxSellBillDetails();
		sellBillDetails.setSell_bill_id(sellBill.getSell_bill_id());
		List<KonkaXxSellBillDetails> sellBillDetailsList = getFacade().getKonkaXxSellBillDetailsService()
				.getKonkaXxSellBillDetailsList(sellBillDetails);
		if (null != sellBillDetailsList && sellBillDetailsList.size() > 0) {
			for (KonkaXxSellBillDetails kxsbd : sellBillDetailsList) {
				KonkaXxPd konkaXxPd = new KonkaXxPd();
				konkaXxPd.setMd_name(kxsbd.getMd_name());
				konkaXxPd.setDept_id(sellBill.getDept_id());
				konkaXxPd.setAudit_state(1);
				konkaXxPd = getFacade().getKonkaXxPdService().getKonkaXxPd(konkaXxPd);
				if (null != konkaXxPd) {
					kxsbd.getMap().put("dept_pd_id", konkaXxPd.getDept_pd_id());
					kxsbd.getMap().put("price_max", konkaXxPd.getPrice_max());
					kxsbd.getMap().put("price_min", konkaXxPd.getPrice_min());
					kxsbd.getMap().put("price_ref", konkaXxPd.getPrice_ref());
				}

				// 查询产品库存
				Long pdStocksNum = getStocks(kxsbd.getMd_name(), sellBill.getZmd_id());
				kxsbd.getMap().put("pdStocksNum", pdStocksNum + kxsbd.getCounts());

				/**
				 * 出库信息数据封装
				 * [@@@@@]出库数量[#####]工厂ID[#####]仓库ID[#####]仓库名称[@@@@@]出库数量
				 * [#####]工厂ID[#####]仓库ID[#####]仓库名称........
				 */
				KonkaXxSellBillDetailsDst sellBillDetailsDst = new KonkaXxSellBillDetailsDst();
				sellBillDetailsDst.setSell_bill_details_id(kxsbd.getSell_bill_details_id());
				List<KonkaXxSellBillDetailsDst> sellBillDetailsDstList = getFacade()
						.getKonkaXxSellBillDetailsDstService().getKonkaXxSellBillDetailsDstList(sellBillDetailsDst);
				if (null != sellBillDetailsDstList && sellBillDetailsDstList.size() > 0) {
					String[] arrs = new String[sellBillDetailsDstList.size()];
					for (int i = 0; i < sellBillDetailsDstList.size(); i++) {
						KonkaXxSellBillDetailsDst bxsbdd = sellBillDetailsDstList.get(i);
						String[] arr = { bxsbdd.getCounts().toString(), bxsbdd.getFactory_id().toString(),
								bxsbdd.getStore_id().toString(), bxsbdd.getStore_name() };
						String s = StringUtils.join(arr, "[#####]");

						arrs[i] = s;
					}
					String ck = StringUtils.join(arrs, "[@@@@@]");
					kxsbd.getMap().put("ck", ck);

				}

			}
		}
		request.setAttribute("sellBillDetailsList", sellBillDetailsList);

		KonkaDept konkaDept = new KonkaDept();
		konkaDept.setDept_id(sellBill.getDept_id());
		konkaDept = getFacade().getKonkaDeptService().getKonkaDept(konkaDept);
		dynaBean.set("dept_name", konkaDept.getDept_name());

		super.copyProperties(form, sellBill);

		KonkaXxPd konkaXxPd = new KonkaXxPd();
		konkaXxPd.setDept_id(sellBill.getDept_id());
		// konkaXxPd.getMap().put("isNotDemo", 70400);
		konkaXxPd.getMap().put("isSales", df.format(new Date()));
		konkaXxPd.setAudit_state(1);
		List<KonkaXxPd> pdIdList = getFacade().getKonkaXxPdService().getKonkaXxPdList(konkaXxPd);
		request.setAttribute("pdIdList", pdIdList);
		dynaBean.set("zmd_p_index", sellBill.getBuyer_p_index());
		return mapping.findForward("input");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		String sell_bill_id = (String) dynaBean.get("sell_bill_id");

		if (StringUtils.isBlank(sell_bill_id)) {
			String msg = getMessage(request, "konka.zmd.sales.order.sellBillId.missing");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
		}

		setOrderNavigationRequest(Long.valueOf(sell_bill_id), request);

		KonkaXxSellBill konkaXxSellBill = new KonkaXxSellBill();
		konkaXxSellBill.setSell_bill_id(Long.valueOf(sell_bill_id));
		konkaXxSellBill.setOrder_type(2);
		konkaXxSellBill = getFacade().getKonkaXxSellBillService().getKonkaXxSellBill(konkaXxSellBill);
		if (null == konkaXxSellBill) {
			return null;
		}
		super.copyProperties(form, konkaXxSellBill);

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

		Integer total_counts = 0;

		KonkaXxSellBillDetails konkaXxSellBillDetails = new KonkaXxSellBillDetails();
		konkaXxSellBillDetails.setSell_bill_id(konkaXxSellBill.getSell_bill_id());
		// konkaXxSellBillDetails.setLock_stock_state(1);
		List<KonkaXxSellBillDetails> konkaXxSellBillDetailsList = getFacade().getKonkaXxSellBillDetailsService()
				.getKonkaXxSellBillDetailsList(konkaXxSellBillDetails);
		if (null != konkaXxSellBillDetailsList && konkaXxSellBillDetailsList.size() > 0) {
			for (KonkaXxSellBillDetails kxsbd : konkaXxSellBillDetailsList) {
				KonkaXxSellBillDetailsDst kxsbdd = new KonkaXxSellBillDetailsDst();
				kxsbdd.setSell_bill_details_id(kxsbd.getSell_bill_details_id());
				List<KonkaXxSellBillDetailsDst> list = getFacade().getKonkaXxSellBillDetailsDstService()
						.getKonkaXxSellBillDetailsDstList(kxsbdd);
				kxsbd.getMap().put("dstList", list);

				kxsbd.getMap().put("all_money", kxsbd.getPrice().multiply(new BigDecimal(kxsbd.getCounts())));

				total_counts += kxsbd.getCounts();
			}

		}
		request.setAttribute("konkaXxSellBillDetailsList", konkaXxSellBillDetailsList);
		request.setAttribute("total_counts", total_counts);

		if (konkaXxSellBill.getDist_employee_id() != null) {
			KonkaXxDistEmployee user = new KonkaXxDistEmployee();
			user.setDist_employee_id(konkaXxSellBill.getDist_employee_id());
			user.setIs_del(0);
			user = getFacade().getKonkaXxDistEmployeeService().getKonkaXxDistEmployee(user);
			if (null != user) {
				dynaBean.set("dist_employee_name", user.getReal_name());
			}
		}

		super.setBaseTypeListByParIdToRequest(50000L, request);
		super.setBaseTypeListByParIdToRequest(70000L, request);
		super.setBaseTypeListByParIdToRequest(90000L, request);

		return mapping.findForward("view");
	}

	public ActionForward confirm(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String sell_bill_id = (String) dynaBean.get("sell_bill_id");

		if (StringUtils.isBlank(sell_bill_id)) {
			super.renderJavaScript(response, "");
			return null;
		}

		KonkaXxSellBill entity = new KonkaXxSellBill();
		entity.setSell_bill_id(Long.valueOf(sell_bill_id));
		entity.setSell_state(10L);
		entity.setSubmit_type(1);

		super.getFacade().getKonkaXxSellBillService().modifyKonkaXxSellBill(entity);
		super.renderJavaScript(response,
				"window.onload=function(){location.href='KonkaXxZmdOrderAdd.do?method=add&msg=2';}");
		return null;
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String sell_bill_id = (String) dynaBean.get("sell_bill_id");
		String sell_state = (String) dynaBean.get("sell_state");
		String province = (String) dynaBean.get("province");
		String city = (String) dynaBean.get("city");
		String country = (String) dynaBean.get("country");
		String town = (String) dynaBean.get("town");
		String buyer_province = (String) dynaBean.get("province");
		String buyer_city = (String) dynaBean.get("city");
		String buyer_country = (String) dynaBean.get("country");
		String buyer_town = (String) dynaBean.get("town");
		String[] pdIdList = request.getParameterValues("pdIds");
		String[] pd_cls = request.getParameterValues("pd_cls");
		String[] pd_cls_name = request.getParameterValues("pd_cls_name");
		String[] md_name = request.getParameterValues("md_name");
		String[] counts = request.getParameterValues("counts");
		String[] price = request.getParameterValues("price");
		String[] xs_remarks = request.getParameterValues("xs_remarks");
		String[] ck = request.getParameterValues("ck");
		String[] pd_type = request.getParameterValues("pd_type");
		String dept_name = (String) dynaBean.get("dept_name");
		String zmd_sn = (String) dynaBean.get("zmd_sn");
		String sell_rec_name = (String) dynaBean.get("sell_rec_name");

		PeProdUser ui = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

		KonkaXxSellBill konkaXxSellBill = new KonkaXxSellBill();
		super.copyProperties(konkaXxSellBill, form);
		konkaXxSellBill.setBuyer_name(sell_rec_name);
		konkaXxSellBill.setOrder_type(2); // 1-消费者购买，2-专卖店购买

		String p_index = "";
		if (StringUtils.isNotBlank(town)) {
			p_index = town;
		} else if (StringUtils.isNotBlank(country)) {
			p_index = country;
		} else if (StringUtils.isNotBlank(city)) {
			p_index = city;
		} else {
			p_index = province;
		}
		if (StringUtils.isNotBlank(p_index)) {
			konkaXxSellBill.setSell_post_p_index(Long.valueOf(p_index));
		}

		if (StringUtils.isBlank(sell_state)) { // 新增订单
			konkaXxSellBill.setSell_state(0L);
		} else { // 修改订单
			if ("0".equals(sell_state)) { // 修改前的订单状态为：未付款
				konkaXxSellBill.setSell_state(0L);
			} else if ("10".equals(sell_state)) { // 修改前的订单状态为：待审核
				if ("0".equals(konkaXxSellBill.getSubmit_type().toString())) { // 未支付提交
					// 变为已支付提交
					konkaXxSellBill.setSell_state(10L);
				} else if ("1".equals(konkaXxSellBill.getSubmit_type().toString())) {
					konkaXxSellBill.setSell_state(0L);
				}
			} else if ("20".equals(sell_state)) { // 修改前的订单状态为：审核通过
				konkaXxSellBill.setSell_state(20L);
			} else if ("21".equals(sell_state)) { // 修改前的订单状态为：审核不通过
				if ("0".equals(konkaXxSellBill.getSubmit_type().toString())) { // 未支付提交
					// 变为已支付提交
					konkaXxSellBill.setSell_state(10L);
				} else if ("1".equals(konkaXxSellBill.getSubmit_type().toString())) {
					konkaXxSellBill.setSell_state(0L);
				}
			}
		}

		String buyer_p_index = "";
		if (StringUtils.isNotBlank(buyer_town)) {
			buyer_p_index = buyer_town;
		} else if (StringUtils.isNotBlank(buyer_country)) {
			buyer_p_index = buyer_country;
		} else if (StringUtils.isNotBlank(buyer_city)) {
			buyer_p_index = buyer_city;
		} else {
			buyer_p_index = buyer_province;
		}
		if (StringUtils.isNotBlank(p_index)) {
			konkaXxSellBill.setBuyer_p_index(Long.valueOf(buyer_p_index));
		}

		konkaXxSellBill.setAdd_user_id(ui.getId());
		konkaXxSellBill.setAdd_user_realname(ui.getReal_name());
		konkaXxSellBill.setAdd_date(new Date());

		List<KonkaXxSellBillDetails> konkaXxSellBillDetailList = new ArrayList<KonkaXxSellBillDetails>();
		if (null != pdIdList && pdIdList.length > 0) {
			for (int i = 1; i < pdIdList.length; i++) {
				KonkaXxSellBillDetails kxsbd = new KonkaXxSellBillDetails();
				if (StringUtils.isNotBlank(pd_cls[i])) {
					kxsbd.setPd_cls(Long.valueOf(pd_cls[i]));
				}
				kxsbd.setPd_cls_name(pd_cls_name[i]);
				kxsbd.setMd_name(md_name[i]);
				if (StringUtils.isNotBlank(counts[i - 1])) {
					kxsbd.setCounts(Integer.valueOf(counts[i - 1]));
				}
				if (StringUtils.isNotBlank(price[i - 1])) {
					BigDecimal pdPrice = new BigDecimal(price[i - 1]);
					kxsbd.setPrice(pdPrice);
				}
				kxsbd.setXs_remarks(xs_remarks[i]);
				kxsbd.setLock_stock_state(1);
				if (StringUtils.isNotBlank(pd_type[i])) {
					kxsbd.setPd_type(Long.valueOf(pd_type[i]));
				}

				// 发货库位选择
				String[] md_arrs = StringUtils.splitByWholeSeparator(ck[i], "[@@@@@]");
				List<KonkaXxSellBillDetailsDst> konkaXxSellBillDetailsDstList = new ArrayList<KonkaXxSellBillDetailsDst>();
				for (int j = 0; j < md_arrs.length; j++) {
					String[] md_arr = StringUtils.splitByWholeSeparator(md_arrs[j], "[#####]");
					String dts_counts = md_arr[0];
					String factory_id = md_arr[1];
					String store_id = md_arr[2];
					String store_desc = md_arr[3];

					KonkaXxSellBillDetailsDst kxsbdd = new KonkaXxSellBillDetailsDst();
					kxsbdd.setFactory_id(factory_id);
					kxsbdd.setStore_id(store_id);
					kxsbdd.setStore_name(store_desc);
					kxsbdd.setCounts(Long.valueOf(dts_counts));
					kxsbdd.setPd_cls_name(pd_cls_name[i]);
					kxsbdd.setMd_name(md_name[i]);
					kxsbdd.setDst_date(new Date());
					kxsbdd.setDst_user_id(ui.getId());
					kxsbdd.setDst_user_name(ui.getReal_name());

					konkaXxSellBillDetailsDstList.add(kxsbdd);
				}
				kxsbd.setKonkaXxSellBillDetailsDstList(konkaXxSellBillDetailsDstList);

				// 判断产品价格上限下限

				KonkaXxPd kxp = new KonkaXxPd();
				kxp.setDept_id(konkaXxSellBill.getDept_id());
				kxp.setMd_name(md_name[i]);
				kxp.getRow().setCount(2);
				kxp.setAudit_state(1);
				List<KonkaXxPd> konkaXxPdList = getFacade().getKonkaXxPdService().getKonkaXxPdList(kxp);
				if (null == konkaXxPdList || konkaXxPdList.size() == 0) {
					String m = getMessage(request, "konka.zmd.dept.pd.missing", new String[] { md_name[i] });
					renderJavaScript(response, "window.onload=function(){alert('" + m + "');history.back();}");
					return null;
				}
				if (konkaXxPdList.size() == 2) {
					String m = getMessage(request, "konka.zmd.dept.pd.too.many", new String[] { md_name[i] });
					renderJavaScript(response, "window.onload=function(){alert('" + m + "');history.back();}");
					return null;
				}
				Double pr = Double.valueOf(price[i - 1]);
				if (null != konkaXxPdList.get(0).getPrice_min()) {
					Double pr_min = konkaXxPdList.get(0).getPrice_min().doubleValue();
					if (pr < pr_min) {
						String m = getMessage(request, "konka.zmd.pd.price.below.min", new String[] { md_name[i],
								pr_min.toString() });
						renderJavaScript(response, "window.onload=function(){alert('" + m + "');history.back();}");
						return null;
					}
				}
				konkaXxSellBillDetailList.add(kxsbd);
			}
		}
		konkaXxSellBill.setKonkaXxSellBillDetailList(konkaXxSellBillDetailList);

		// 调接口查询库存v.2012-04-16(Begin)
		List<KonkaXxPd> pdList = new ArrayList<KonkaXxPd>();
		if (null != md_name && md_name.length > 0) {
			for (int j = 1; j < md_name.length; j++) {
				KonkaXxPd pd = new KonkaXxPd();
				pd.setMd_name(md_name[j]);
				pdList.add(pd);
			}
		}
		List<KonkaXxPd> konkaXxPdsList = super.getKonkaXxPdListWithStocks(pdList, konkaXxSellBill.getZmd_id());
		if (null != konkaXxPdsList && konkaXxPdsList.size() > 0) {
			for (int k = 0; k < konkaXxPdsList.size(); k++) { // 遍历产品
				KonkaXxPd pd = konkaXxPdsList.get(k);

				Long pdStocksNum = 0L;// 定义该产品型号[库存]总量变量
				List<KonkaXxZmdPdStore> pdStoreList = pd.getKonkaXxZmdPdStoreList();
				if (null != pdStoreList && pdStoreList.size() > 0) {
					for (KonkaXxZmdPdStore kxzps : pdStoreList) {
						pdStocksNum += kxzps.getStock_count();
					}
				}
				if (StringUtils.isNotBlank(sell_bill_id)) {// 修改订单
					KonkaXxSellBillDetails details = new KonkaXxSellBillDetails();
					details.setSell_bill_id(Long.valueOf(sell_bill_id));
					details.setMd_name(pd.getMd_name());
					details = getFacade().getKonkaXxSellBillDetailsService().getKonkaXxSellBillDetails(details);
					if (null != details) {
						pdStocksNum += details.getCounts();
					}
				}

				Long pd_counts = 0L;// 定义该产品型号[购买]数量变量
				List<KonkaXxSellBillDetails> billDetailList = konkaXxSellBill.getKonkaXxSellBillDetailList();
				for (KonkaXxSellBillDetails bd : billDetailList) {
					if (StringUtils.equals(pd.getMd_name(), bd.getMd_name())) {
						pd_counts = bd.getCounts().longValue();
					}
				}

				if (pdStocksNum < pd_counts) {// 判断库存和购买数量
					String msg = getMessage(request, "konka.zmd.save.sales.order.no.stocks.error", new String[] { pd
							.getMd_name() });
					renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
					return null;
				}
			}
		}
		// 调接口查询库存v.2012-04-16(End)

		String nn = "1";
		Long billId = 0L;
		String nn_state = "0";
		if (StringUtils.isBlank(sell_bill_id)) {
			billId = super.getFacade().getKonkaXxSellBillService().createKonkaXxSellBillWithDetails(konkaXxSellBill);

			// 订单提交且付款，发送邮件给分公司财务提醒其审核Begin
			if (konkaXxSellBill.getSell_state() == 10L) { // 待审核
				String bill_id = StringUtils.leftPad(billId.toString(), 12, "0");
				// String bill_id_test = StringUtils.leftPad("888", 12, "0");

				String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
				String ctx = request.getContextPath();

				KonkaXxBaseType type = new KonkaXxBaseType();
				type.setPar_id(70000L);
				type.setIs_del(0);
				List<KonkaXxBaseType> typeList = getFacade().getKonkaXxBaseTypeService().getKonkaXxBaseTypeList(type);

				// List<String> userList = new ArrayList<String>();
				// String[] userArrs = userList.toArray(new
				// String[userList.size()]);// 邮件接收人地址

				try {
					PeRoleUser peRoleUser = new PeRoleUser();
					peRoleUser.setRole_id(350L);
					List<PeRoleUser> peRoleUserList = getFacade().getPeRoleUserService().getPeRoleUserList(peRoleUser);
					if (null != peRoleUserList && peRoleUserList.size() > 0) {
						for (PeRoleUser pru : peRoleUserList) {
							PeProdUser user = new PeProdUser();
							user.setId(pru.getUser_id());
							user.setDept_id(konkaXxSellBill.getDept_id());
							user = getFacade().getPeProdUserService().getPeProdUserResult(user);
							if (null != user) {
								String mail_addr = user.getEmail();
								if (StringUtils.isNotBlank(mail_addr)) {
									StringBuffer link = new StringBuffer();
									link.append(path).append(ctx);
									link.append("/manager/zmd/KonkaXxZmdAuditSalesOrder.do");
									link.append("?method=Audit");
									link.append("&sell_bill_id=" + billId);
									link.append("&mod_id=802004");
									link.append("&user_id=" + user.getId());
									link.append("&marker=auditSalesOrder");

									String link_view = new DESPlus().encrypt(link.toString());

									Map<String, Object> model = new HashMap<String, Object>();
									model.put("link", link);
									model.put("link_view", link_view);
									model.put("sell_bill_id", bill_id);
									model.put("entity", konkaXxSellBill);
									model.put("typeList", typeList);
									model.put("date", new Date());
									model.put("dept_name", dept_name);
									model.put("user_name", user.getUser_name());

									SimpleMailMessage mailMessage = new SimpleMailMessage();
									mailMessage.setFrom("renzhong@easy-biz.com.cn");
									mailMessage.setTo(mail_addr);
									mailMessage.setSubject("订单提醒：" + zmd_sn + "专卖店销售单" + bill_id + "等待您尽快审核");// 邮件主题：“订单提醒：XXX专卖店销售单XXX等待您尽快审核”
									super.getFacade().getMailService().send(mailMessage, "mail/remindAudit.ftl", model);
								}
							}
						}
					}
				} catch (Exception e) {
					logger.warn("+++++++++++++++++++++++++++++++++++++++++++zmd_sn = {}", zmd_sn);
				}

			}
			// 订单提交且付款，发送邮件给分公司财务提醒其审核End
		} else {
			konkaXxSellBill.setSell_bill_id(Long.valueOf(sell_bill_id));
			konkaXxSellBill.setOrder_type(2);
			nn = "2";
			//super.getFacade().getKonkaXxSellBillService().modifyKonkaXxSellBillWithDetails(konkaXxSellBill);
		}

		super.renderJavaScript(response,
				"window.onload=function(){location.href='KonkaXxZmdOrderAdd.do?method=add&msg=" + nn + "&billId="
						+ billId + "&nn_state=" + nn_state + "';}");
		return null;
	}

	public ActionForward getPdStocks(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;

		String zmd_id = (String) dynaBean.get("zmd_id");
		String md_name = (String) dynaBean.get("md_name");

		StringBuffer sb = new StringBuffer("[{");
		Long pdStocksNum = 0L;
		if (StringUtils.isNotBlank(zmd_id) && StringUtils.isNotBlank(md_name)) {
			// Long pdStocksNum = super.getStocks(URLDecoder.decode(md_name,
			// "utf-8"), Long.valueOf(zmd_id));
			List<KonkaXxPd> pdList = new ArrayList<KonkaXxPd>();
			KonkaXxPd konkaXxPd = new KonkaXxPd();
			konkaXxPd.setMd_name(md_name);
			pdList.add(konkaXxPd);
			List<KonkaXxPd> konkaXxPdList = super.getKonkaXxPdListWithStocks(pdList, Long.valueOf(zmd_id));
			if (null != konkaXxPdList && konkaXxPdList.size() > 0) {
				for (KonkaXxPd kxp : konkaXxPdList) {
					if (StringUtils.equals(md_name, kxp.getMd_name())) {
						List<KonkaXxZmdPdStore> pdStoreList = kxp.getKonkaXxZmdPdStoreList();
						if (null != pdStoreList && pdStoreList.size() > 0) {
							for (KonkaXxZmdPdStore kxzps : pdStoreList) {
								pdStocksNum += kxzps.getStock_count();
							}
						}
					}
				}
			}
		}
		sb.append("\"pdStocksNum\":\"").append(pdStocksNum).append("\"},{");
		sb.append("\"end\":\"rz\"}]");
		super.renderJson(response, sb.toString());
		return null;
	}

	public ActionForward getPdStoresList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;

		String zmd_id = (String) dynaBean.get("zmd_id");
		String md_name = (String) dynaBean.get("md_name");
		String sell_bill_id = (String) dynaBean.get("sell_bill_id");
		md_name = URLDecoder.decode(md_name, "utf-8");

		StringBuffer sb = new StringBuffer("[{");

		if (StringUtils.isNotBlank(zmd_id) && StringUtils.isNotBlank(md_name)) {
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

			List<KonkaXxPd> pdList = new ArrayList<KonkaXxPd>();
			KonkaXxPd pd = new KonkaXxPd();
			pd.setMd_name(md_name);
			pdList.add(pd);
			List<KonkaXxPd> konkaXxPdList = super.getKonkaXxPdListWithStocks(pdList, Long.valueOf(zmd_id));
			if (null != konkaXxPdList && konkaXxPdList.size() > 0) {
				for (KonkaXxPd kxp : konkaXxPdList) {
					if (StringUtils.equals(md_name, kxp.getMd_name())) {
						List<KonkaXxZmdPdStore> pdStoreList = kxp.getKonkaXxZmdPdStoreList(); // 产品仓库信息list
						if (null != pdStoreList && pdStoreList.size() > 0) {
							sb.append("\"count\":\"").append(pdStoreList.size()).append("\",");
							sb.append("\"list\":{");
							for (int i = 0; i < pdStoreList.size(); i++) {
								Long storeNum = pdStoreList.get(i).getStock_count();
								// 修改销售单时，库存应加上[该订单][该产品]已销售数量
								if (StringUtils.isNotBlank(sell_bill_id)) {
									KonkaXxSellBillDetailsDst dd = new KonkaXxSellBillDetailsDst();
									dd.setSell_bill_id(Long.valueOf(sell_bill_id));
									dd.setMd_name(md_name);
									dd.setFactory_id(pdStoreList.get(i).getFactory_id());
									dd.setStore_id(pdStoreList.get(i).getStore_id());
									dd = getFacade().getKonkaXxSellBillDetailsDstService()
											.getKonkaXxSellBillDetailsDst(dd);
									if (dd != null) {
										storeNum = storeNum + dd.getCounts();
									}
								}

								sb.append("\"store" + i + "\":{");
								sb.append("\"vs\":\"").append(i + 1).append("\",");
								sb.append("\"factory_id\":\"").append(pdStoreList.get(i).getFactory_id()).append("\",");
								sb.append("\"storeNum\":\"").append(storeNum.toString()).append("\",");
								// sb.append("\"store_id\":\"").append(pdStoreList.get(i).getStore_id()).append("\",");

								KonkaXxStdStore store = new KonkaXxStdStore();
								store.setFactory_id(pdStoreList.get(i).getFactory_id());
								store.setStore_id(pdStoreList.get(i).getStore_id());
								store = getFacade().getKonkaXxStdStoreService().getKonkaXxStdStore(store);
								if (null != store) {
									sb.append("\"store_desc\":\"" + store.getStore_desc() + "\",");
								}

								if (i + 1 < pdStoreList.size()) {
									sb.append("\"store_id\":\"" + pdStoreList.get(i).getStore_id() + "\"},");
								} else {
									sb.append("\"store_id\":\"" + pdStoreList.get(i).getStore_id() + "\"}");
								}
							}
							sb.append("}},{");
						} else {
							sb.append("\"count\":\"0\"},{");
						}
					}
				}
			}
		}

		sb.append("\"end\":\"rz\"}]");
		super.renderJson(response, sb.toString());
		return null;
	}

	public ActionForward getBuyerInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		String zmd_id = (String) dynaBean.get("zmd_id");
		String buyer_id = (String) dynaBean.get("buyer_id");

		JSONObject result = new JSONObject();
		result.put("count", 0);
		if (StringUtils.isNotBlank(zmd_id) && StringUtils.isNotBlank(buyer_id)) {
			KonkaXxSellBill entity = new KonkaXxSellBill();
			entity.setZmd_id(Long.valueOf(zmd_id));
			entity.setBuyer_id(buyer_id);
			String[] arr = { zmd_id, buyer_id };
			entity.getMap().put("last_add_date", arr);
			entity = getFacade().getKonkaXxSellBillService().getKonkaXxSellBill(entity);

			if (null != entity) {
				// BaseProvinceListFour baseProvinceListFour = new
				// BaseProvinceListFour();
				String buyer_p_index = entity.getBuyer_p_index().toString();
				String buyer_province = StringUtils.substring(buyer_p_index, 0, 2).concat("0000");
				String buyer_city = StringUtils.substring(buyer_p_index, 0, 4).concat("00");
				String buyer_country = StringUtils.substring(buyer_p_index, 0, 6);
				String buyer_town = "";
				if (StringUtils.length(buyer_p_index) == 8) {
					buyer_town = buyer_p_index;
				}

				result.put("count", 1);
				result.put("buyer_name", entity.getBuyer_name());
				result.put("buyer_sex", entity.getBuyer_sex());
				if (null != entity.getBuyer_birthday()) {
					String buyer_birthday = df.format(entity.getBuyer_birthday());
					result.put("buyer_birthday", buyer_birthday);
				}
				result.put("buyer_tel", entity.getBuyer_tel());
				result.put("buyer_phone", entity.getBuyer_phone());
				result.put("buyer_p_index", entity.getBuyer_p_index());
				result.put("buyer_province", buyer_province);
				result.put("buyer_city", buyer_city);
				result.put("buyer_country", buyer_country);
				result.put("buyer_town", buyer_town);
				result.put("buyer_link_addr", entity.getBuyer_link_addr());
				result.put("buyer_memo", entity.getBuyer_memo());
			}
		}

		super.renderJson(response, result.toString());
		return null;
	}

	public ActionForward getUserInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;

		String user_id = (String) dynaBean.get("user_id");

		JSONObject result = new JSONObject();
		result.put("count", "0");
		if (StringUtils.isNotBlank(user_id)) {
			PeProdUser user = new PeProdUser();
			user.setId(Long.valueOf(user_id));
			user.setIs_del(0);
			user = getFacade().getPeProdUserService().getPeProdUser(user);
			if (null != user) {
				result.put("user_name", user.getReal_name());
				// result.put("dept_name", user.getMap().get("dept_name"));

				KonkaDept dept = new KonkaDept();
				dept.setDept_id(user.getDept_id());
				dept = getFacade().getKonkaDeptService().getKonkaDept(dept);
				if (null != dept) {
					result.put("dept_name", dept.getDept_desc());
				}
				result.put("email", user.getEmail());
				result.put("tel", user.getLink_tel());
				result.put("mobile", user.getLink_phone());
				result.put("qq", user.getLink_qq());
				result.put("count", "1");
			}
		}

		super.renderJson(response, result.toString());
		return null;
	}
}
