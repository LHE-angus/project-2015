package com.ebiz.mmt.web.struts.customer;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.BranchAssign;
import com.ebiz.mmt.domain.JfScorts;
import com.ebiz.mmt.domain.JfScortsDetails;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaJxcBaseAddr;
import com.ebiz.mmt.domain.KonkaMobileImpStore;
import com.ebiz.mmt.domain.KonkaOrderAuditProcess;
import com.ebiz.mmt.domain.KonkaOrderInfoDetails;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.KonkaXxBaseType;
import com.ebiz.mmt.domain.KonkaXxSellBill;
import com.ebiz.mmt.domain.KonkaXxSellBillDetails;
import com.ebiz.mmt.domain.KonkaXxSellBillDetailsDst;
import com.ebiz.mmt.domain.KonkaXxZmd;
import com.ebiz.mmt.domain.KonkaXxZmdUsers;
import com.ebiz.mmt.domain.MemberInfo;
import com.ebiz.mmt.domain.PePdModel;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.r3.KNVP;
import com.ebiz.mmt.r3.ReturnInfo;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.manager.zmd.BaseZmdAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Wu,ShangLong
 * @version 2012-1-9
 */
public class KonkaXxZmdSearchSalesOrderAction extends BaseZmdAction {

	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String zmd_id = (String) dynaBean.get("zmd_id");
		String sell_bill_id = (String) dynaBean.get("sell_bill_id");
		String add_date_ge = (String) dynaBean.get("add_date_ge");
		String add_date_le = (String) dynaBean.get("add_date_le");
		String sell_state = (String) dynaBean.get("sell_state");
		String add_user_realname_like = (String) dynaBean.get("add_user_realname_like");
		String buyer_name_like = (String) dynaBean.get("buyer_name_like");
		String pay_way = (String) dynaBean.get("pay_way");
		String order_type = (String) dynaBean.get("order_type");
		String stock_from = (String) dynaBean.get("stock_from");
		String sell_man_like = (String) dynaBean.get("sell_man_like");

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		KonkaXxBaseType konkaXxBaseType = new KonkaXxBaseType();
		konkaXxBaseType.setPar_id(50000L); // 付款方式
		List<KonkaXxBaseType> pay_way_arr = super.getFacade().getKonkaXxBaseTypeService()
				.getKonkaXxBaseTypeList(konkaXxBaseType);
		request.setAttribute("pay_way_arr", pay_way_arr);

		KonkaXxSellBill entity = new KonkaXxSellBill();
		entity.setOrder_type(1);
		if (GenericValidator.isInt(order_type)) {
			entity.setOrder_type(Integer.valueOf(order_type));
		}
		if (GenericValidator.isInt(stock_from)) {
			entity.setStock_from(Integer.valueOf(stock_from));
		}

		PeProdUser user_info = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		// PeRoleUser role_info = super.getRoleInfoByThisLogin(request);
		// PeRoleUser role_info = super.getRoleInfoByThisLogin(request);
		List<PeRoleUser> peRoleUserList = getPeRoleList(user_info.getId());

		Boolean role_id_eq_400 = false;
		Boolean role_id_eq_390 = false;
		Boolean role_id_btw_300_400 = false;

		if (peRoleUserList.size() > 0) {
			for (PeRoleUser temp : peRoleUserList) {
				if (temp.getRole_id() == 400) {
					role_id_eq_400 = true;
				}
				if (temp.getRole_id() == 390) {
					role_id_eq_390 = true;
				}
				if ((temp.getRole_id() >= 300 && temp.getRole_id() < 400)
						|| (temp.getRole_id() >= 30 && temp.getRole_id() < 40)) {
					role_id_btw_300_400 = true;
				}
			}
		}

		// 页面角色控制 start
		// long role_id = role_info.getRole_id();
		request.setAttribute("role_id_eq_400", role_id_eq_400);

		if (role_id_eq_400) { // 专卖店人员
			KonkaXxZmdUsers konkaXxZmdUsers = new KonkaXxZmdUsers();
			konkaXxZmdUsers.setUser_id(user_info.getId());

			List<KonkaXxZmdUsers> konkaXxZmdUsersList = super.getFacade().getKonkaXxZmdUsersService()
					.getKonkaXxZmdUsersList(konkaXxZmdUsers);
			if (null != konkaXxZmdUsersList && konkaXxZmdUsersList.size() > 0) {
				entity.setZmd_id(konkaXxZmdUsersList.get(0).getZmd_id());
			} else {
				String msg = getMessage(request, "konka.xx.zmd.user.not.bind.zmd");
				saveDirectlyError(request, msg);
				return mapping.findForward("list");
			}

			KonkaXxZmd zmd = new KonkaXxZmd();
			zmd.setZmd_id(konkaXxZmdUsersList.get(0).getZmd_id());
			zmd = super.getFacade().getKonkaXxZmdService().getKonkaXxZmd(zmd);

			KonkaDept kd = new KonkaDept();
			kd.setDept_id(zmd.getDept_id());
			kd = super.getFacade().getKonkaDeptService().getKonkaDept(kd);

			request.setAttribute("zmd_sn", zmd.getZmd_sn());

		} else if (role_id_btw_300_400) { // 分公司人员
			if (role_id_eq_390) { // 分公司业务员
				KonkaXxZmdUsers konkaXxZmdUsers = new KonkaXxZmdUsers();
				konkaXxZmdUsers.setUser_id(user_info.getId());

				List<KonkaXxZmdUsers> konkaXxZmdUsersList = super.getFacade().getKonkaXxZmdUsersService()
						.getKonkaXxZmdUsersList(konkaXxZmdUsers);
				if (null != konkaXxZmdUsersList && konkaXxZmdUsersList.size() > 0) {
					List<Long> zmds = new ArrayList<Long>();
					for (KonkaXxZmdUsers temp : konkaXxZmdUsersList) {
						zmds.add(temp.getZmd_id());
					}

					entity.getMap().put("zmds", zmds.toArray());

					// 初始化业务员下面专卖店的下拉框
					KonkaXxZmd zmd = new KonkaXxZmd();
					zmd.getMap().put("zmds", zmds.toArray());
					List<KonkaXxZmd> zmdList = super.getFacade().getKonkaXxZmdService().getKonkaXxZmdList(zmd);

					request.setAttribute("zmdList", zmdList);
				} else {
					String msg = getMessage(request, "konka.xx.zmd.user.not.bind.zmd");
					saveDirectlyError(request, msg);
					return mapping.findForward("list");
				}
			} else {
				KonkaDept kDept = getKonkaDeptForFgs(user_info.getDept_id());

				if (kDept != null)
					entity.setDept_id(kDept.getDept_id());

				// 初始化业务员下面专卖店的下拉框
				KonkaXxZmd zmd = new KonkaXxZmd();
				if (kDept != null)
					zmd.setDept_id(kDept.getDept_id());
				List<KonkaXxZmd> zmdList = super.getFacade().getKonkaXxZmdService().getKonkaXxZmdList(zmd);

				request.setAttribute("zmdList", zmdList);
			}
		}
		// 页面角色控制 end

		// 选择的条件字段
		if (GenericValidator.isLong(zmd_id)) {
			entity.setZmd_id(Long.valueOf(zmd_id));
		}
		if (GenericValidator.isLong(sell_bill_id)) {
			entity.setSell_bill_id(Long.valueOf(sell_bill_id));
		}
		if (GenericValidator.isLong(sell_bill_id)) {
			entity.setSell_bill_id(Long.valueOf(sell_bill_id));
		}
		// 开单时间设置Begin
		Date now = new Date();
		if (StringUtils.isBlank(add_date_ge)) {
			Date lastDay = DateUtils.addMonths(now, -1);
			add_date_ge = df.format(lastDay);
			entity.getMap().put("add_date_ge", add_date_ge);
		} else {
			entity.getMap().put("add_date_ge", add_date_ge);
		}
		dynaBean.set("add_date_ge", add_date_ge);
		if (StringUtils.isBlank(add_date_le)) {
			add_date_le = df.format(now);
			entity.getMap().put("add_date_le", add_date_le);
		} else {
			entity.getMap().put("add_date_le", add_date_le);
		}
		dynaBean.set("add_date_le", add_date_le);
		// 开单时间设置End
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
		if (StringUtils.isNotBlank(sell_man_like))
			entity.getMap().put("sell_man_like", sell_man_like);

		Long recordCount = super.getFacade().getKonkaXxSellBillService().getKonkaXxSellBillCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setCount(pager.getRowCount());
		entity.getRow().setFirst(pager.getFirstRow());

		List<KonkaXxSellBill> entityList = super.getFacade().getKonkaXxSellBillService()
				.getKonkaXxSellBillPaginatedList(entity);

		request.setAttribute("entityList", entityList);

		return mapping.findForward("list");

	}

	public ActionForward showDetail(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String sell_bill_id = (String) dynaBean.get("sell_bill_id");

		if (!GenericValidator.isLong(sell_bill_id)) {
			String msg = getMessage(request, "konka.xx.zmd.sell.id.not.num");
			saveError(request, msg);
			return mapping.findForward("list");
		}

		KonkaXxSellBill konkaXxSellBill = new KonkaXxSellBill();
		konkaXxSellBill.setSell_bill_id(Long.valueOf(sell_bill_id));
		konkaXxSellBill = super.getFacade().getKonkaXxSellBillService().getKonkaXxSellBill(konkaXxSellBill);

		if (null == konkaXxSellBill) {
			String msg = getMessage(request, "konka.xx.zmd.sell.id.not.exist");
			saveError(request, msg);
			return mapping.findForward("list");
		}

		request.setAttribute("sell_bill_id", konkaXxSellBill.getSell_bill_id().toString());
		request.setAttribute("zmd_sn", konkaXxSellBill.getZmd_sn());
		request.setAttribute("pay_way", konkaXxSellBill.getPay_way());
		request.setAttribute("sell_date", konkaXxSellBill.getSell_date());
		request.setAttribute("add_user_realname", konkaXxSellBill.getAdd_user_realname());

		KonkaDept kDept = new KonkaDept();
		kDept.setDept_id(konkaXxSellBill.getDept_id());
		kDept = super.getFacade().getKonkaDeptService().getKonkaDept(kDept);
		request.setAttribute("dept_name", kDept.getDept_name());

		KonkaXxSellBillDetails entity = new KonkaXxSellBillDetails();
		entity.setSell_bill_id(Long.valueOf(sell_bill_id));

		List<KonkaXxSellBillDetails> entityList = super.getFacade().getKonkaXxSellBillDetailsService()
				.getKonkaXxSellBillDetailsList(entity);

		if (null != entityList && entityList.size() > 0) {
			request.setAttribute("detail_count", entityList.size());
		}

		BigDecimal total_pay = new BigDecimal("0"); // 总销售额
		if (null != entityList && entityList.size() > 0) {
			for (KonkaXxSellBillDetails temp : entityList) {
				total_pay = total_pay.add(temp.getPrice().multiply(new BigDecimal(temp.getCounts().toString())));

				// 查询销售单明细中产品出仓库位信息
				KonkaXxSellBillDetailsDst dst = new KonkaXxSellBillDetailsDst();
				dst.setSell_bill_details_id(temp.getSell_bill_details_id());

				List<KonkaXxSellBillDetailsDst> dstList = super.getFacade().getKonkaXxSellBillDetailsDstService()
						.getKonkaXxSellBillDetailsDstList(dst);

				if (null != dstList && dstList.size() > 0) {
					StringBuffer store_info = new StringBuffer();
					for (KonkaXxSellBillDetailsDst dst_temp : dstList) {
						String store_name = dst_temp.getStore_name();
						Long counts = dst_temp.getCounts();
						store_info.append(store_name).append("(").append(counts).append("),");
					}

					String store_name = store_info.toString().substring(0, store_info.toString().length() - 1);
					temp.getMap().put("store_name", store_name);
				}
			}
			request.setAttribute("total_pay", total_pay.toString());
		}

		request.setAttribute("entityList", entityList);
		dynaBean.set("now_date", DateFormatUtils.format(new Date(), "yyyy-MM-dd"));

		return mapping.findForward("view");
	}

	public ActionForward jfEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String sell_bill_id = (String) dynaBean.get("sell_bill_id");

		if (!GenericValidator.isLong(sell_bill_id)) {
			String msg = getMessage(request, "errors.long");
			saveError(request, msg);
			return mapping.findForward("list");
		}

		KonkaXxSellBill konkaXxSellBill = new KonkaXxSellBill();
		konkaXxSellBill.setSell_bill_id(Long.valueOf(sell_bill_id));
		konkaXxSellBill = super.getFacade().getKonkaXxSellBillService().getKonkaXxSellBill(konkaXxSellBill);

		if (null == konkaXxSellBill) {
			String msg = getMessage(request, "konka.xx.zmd.sell.id.not.exist");
			saveError(request, msg);
			return mapping.findForward("list");
		}

		if (null == konkaXxSellBill.getConsumer_grade_num() || "".equals(konkaXxSellBill.getConsumer_grade_num())) {
			// super.renderJavaScript(response,
			// "alert('对不起，该会员卡不存在。');history.back();");
			// return null;
			String msg = getMessage(request, "jf.user.sn.empty");
			saveDirectlyError(request, msg);
			return this.list(mapping, form, request, response);
		}

		MemberInfo memberInfo = new MemberInfo();
		memberInfo.setUser_sn(konkaXxSellBill.getConsumer_grade_num());
		List<MemberInfo> memberInfoList = super.getFacade().getMemberInfoService().getMemberInfoList(memberInfo);

		if (memberInfoList.size() == 0 || null == memberInfoList) {
			String msg = getMessage(request, "jf.user.sn.empty");
			saveDirectlyError(request, msg);
			return this.list(mapping, form, request, response);
		}

		JfScorts jfScorts = new JfScorts();
		jfScorts.setUser_sn(memberInfo.getUser_sn());
		List<JfScorts> jfScortsList = super.getFacade().getJfScortsService().getJfScortsList(jfScorts);

		if (null == jfScortsList || jfScortsList.size() == 0) {
			super.saveError(request, "errors.long", "id");
			return this.list(mapping, form, request, response);
		} else {
			dynaBean.set("user_sn", jfScorts.getUser_sn());
			dynaBean.set("total_scorts", jfScortsList.get(0).getTotal_scorts());
		}
		dynaBean.set("sell_bill_id", sell_bill_id);

		return mapping.findForward("input");
	}

	public ActionForward jfSave(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String sell_bill_id = (String) dynaBean.get("sell_bill_id");

		if (!GenericValidator.isLong(sell_bill_id)) {
			String msg = getMessage(request, "errors.long");
			saveDirectlyError(request, msg);
			return mapping.findForward("list");
		}

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		JfScortsDetails entity = new JfScortsDetails();
		entity.setOut_sys_id(sell_bill_id);
		entity.setOut_sys_name("KONKA_XX_SELL_BILL");
		entity.setJf_cate(2);

		List<JfScortsDetails> entityList = super.getFacade().getJfScortsDetailsService().getJfScortsDetailsList(entity);

		super.copyProperties(entity, form);

		KonkaDept kDept = getKonkaDeptForFgs(user.getDept_id());
		if (kDept != null)
			entity.setDept_id(kDept.getDept_id());
		if (entityList.size() > 0) {
			entity.setId(entityList.get(0).getId());
			super.getFacade().getJfScortsDetailsService().modifyJfScortsDetails(entity);
		} else {
			entity.setAdd_user_id(user.getId());
			entity.setStatus(0);
			super.getFacade().getJfScortsDetailsService().createJfScortsDetails(entity);
		}

		super.saveMessage(request, "jf.edit.success");

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	@Override
	public ActionForward toExcel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		try {
			super.toExcel(mapping, form, request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * @author:Wang,KunLin
	 * 
	 * @version:2014-4-26
	 * 
	 * @将订单转为在线下单流程后可以同步的R3单
	 */
	public ActionForward switchR3(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		DynaBean dynaBean = (DynaBean) form;
		String sell_bill_id = (String) dynaBean.get("sell_bill_id");
		if (!GenericValidator.isLong(sell_bill_id)) {
			String msg = getMessage(request, "errors.long");
			saveDirectlyError(request, msg);
			return mapping.findForward("list");
		}
		String CUSTID = (String) dynaBean.get("CUSTID"); // 业务提交请求的客户ID

		HttpSession session = request.getSession();

		PeProdUser user = null;
		if (GenericValidator.isLong(CUSTID)) {
			user = new PeProdUser();
			user.getRow().setCount(1);
			user.setCust_id(Long.valueOf(CUSTID));
			List<PeProdUser> userList = super.getFacade().getPeProdUserService().getPeProdUserList(user);
			if (null == userList || 0 == userList.size()) {
			}
			user = userList.get(0);
			session.setAttribute(Constants.CUSTOMER_USER_INFO, user);
		} else {
			user = (PeProdUser) session.getAttribute(Constants.CUSTOMER_USER_INFO);
		}
		if (null == user) {
			return null;
		}

		String cust_id = (String) dynaBean.get("cust_id");
		KonkaXxSellBill konkaXxSellBill = new KonkaXxSellBill();
		konkaXxSellBill.setSell_bill_id(Long.valueOf(sell_bill_id));
		konkaXxSellBill = super.getFacade().getKonkaXxSellBillService().getKonkaXxSellBill(konkaXxSellBill);
		dynaBean.set("user_name", konkaXxSellBill.getSell_rec_name());// 收货人姓名
																		// af.map.sell_rec_name
		dynaBean.set("user_addr", konkaXxSellBill.getSell_post_addr());// 送货地址
																		// af.map.sell_post_area
		dynaBean.set("user_phone", konkaXxSellBill.getSell_rec_link_mp());// 收货人电话
																			// af.map.sell_rec_link_mp
		// 送货人地址（三级连动）Sell_post_p_index()

		dynaBean.set("send_type", 1);
		if (konkaXxSellBill.getSell_post_p_index() != null) {
			dynaBean.set("send_type", 2);

			String p_index = konkaXxSellBill.getSell_post_p_index().toString();
			if (!p_index.endsWith("00")) {
				if (p_index.length() == 6) {
					dynaBean.set("province", StringUtils.substring(p_index, 0, 2) + "0000");
					dynaBean.set("city", StringUtils.substring(p_index, 0, 4) + "00");
					dynaBean.set("country", p_index);
				} else if (p_index.length() == 8) {
					dynaBean.set("province", StringUtils.substring(p_index, 0, 2) + "0000");
					dynaBean.set("city", StringUtils.substring(p_index, 0, 4) + "00");
					dynaBean.set("country", StringUtils.substring(p_index, 0, 6));
					dynaBean.set("town", p_index);
				}
			} else if (p_index.endsWith("0000")) {
				dynaBean.set("province", p_index);
			} else if (p_index.endsWith("00")) {
				dynaBean.set("province", StringUtils.substring(p_index, 0, 2) + "0000");
				dynaBean.set("city", p_index);
			}

		}

		Long current_cust_id = null;

		if (null != user.getCust_id()) {
			// 当前登录人是客户
			current_cust_id = user.getCust_id();
		} else {
			// 当前登录人不是客户
			PeRoleUser _peRoleUser = new PeRoleUser();
			_peRoleUser.setUser_id(user.getId());
			List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);

			boolean role_id_eq_60 = false;
			for (PeRoleUser peRoleUser : peRoleUserList) {
				if (peRoleUser.getRole_id() == 60L) {
					role_id_eq_60 = true;
				}
			}
			if (user.getCust_id() == null && role_id_eq_60) {
				if (!GenericValidator.isLong(cust_id)) {
					return null;
				}
				current_cust_id = Long.valueOf(cust_id);
			}
		}

		if (null == current_cust_id) {
			return null;
		}

		request.setAttribute("cust_id", current_cust_id);

		KonkaR3Shop konkaR3Shop = new KonkaR3Shop();
		konkaR3Shop.setId(current_cust_id);
		konkaR3Shop = getFacade().getKonkaR3ShopService().getKonkaR3Shop(konkaR3Shop);
		if (null == konkaR3Shop) {
			super.renderJavaScript(response, "alert('" + super.getMessage(request, "konka.r3.customer.none")
					+ "');history.back();");
			return null;
		}

		// 获取流程列表
		KonkaDept kd = super.getKonkaDeptByCustomerId(current_cust_id);
		KonkaDept dept = super.getSuperiorForDeptType(kd.getDept_id(), 3);
		List<KonkaOrderAuditProcess> processList = new ArrayList<KonkaOrderAuditProcess>();
		// Boolean flag = null;
		if (dept != null) {

			KonkaR3Shop konkaR3Shop_ = new KonkaR3Shop();
			konkaR3Shop_.setId(current_cust_id);
			konkaR3Shop_.setIs_del(0l);
			konkaR3Shop_ = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(konkaR3Shop_);
			if (null != konkaR3Shop_) {
				if (StringUtils.isNotBlank(konkaR3Shop_.getCustomer_type())) {// 判断是否是有客户类型
					KonkaOrderAuditProcess process = new KonkaOrderAuditProcess();
					process.getMap().put("par_add_dept_id", dept.getDept_id());
					process.setCustomer_type(konkaR3Shop_.getCustomer_type());
					process.setIs_del(0);
					process.setIs_stop(0);
					processList = super.getFacade().getKonkaOrderAuditProcessService()
							.getKonkaOrderAuditProcessList(process);
					if (null == processList || processList.size() == 0) {
						// 没有分公司自定义的流程取统一流程，即分公司自定义流程
						KonkaOrderAuditProcess ap_public = new KonkaOrderAuditProcess();
						ap_public.setCustomer_type(konkaR3Shop_.getCustomer_type());
						ap_public.setAdd_dept_id(0L);
						ap_public.setIs_del(0);
						ap_public.setIs_stop(0);
						List<KonkaOrderAuditProcess> ap_publicauditProcesseList = getFacade()
								.getKonkaOrderAuditProcessService().getKonkaOrderAuditProcessList(ap_public);
						processList.addAll(ap_publicauditProcesseList);
					}
					if (null == processList || processList.size() == 0) {
						// 没有分公司自定义的流程取统一流程，即分公司流程
						KonkaOrderAuditProcess ap_public = new KonkaOrderAuditProcess();
						ap_public.setCustomer_type(konkaR3Shop_.getCustomer_type());
						ap_public.setAdd_dept_id(0L);
						ap_public.setIs_del(0);
						ap_public.setIs_stop(0);
						List<KonkaOrderAuditProcess> ap_publicauditProcesseList = getFacade()
								.getKonkaOrderAuditProcessService().getKonkaOrderAuditProcessList(ap_public);
						processList.addAll(ap_publicauditProcesseList);
					}
					if (null == processList || processList.size() == 0) {
						// 没有分公司流程取统一流程，即总公司流程
						KonkaOrderAuditProcess ap_public = new KonkaOrderAuditProcess();
						ap_public.getMap().put("par_add_dept_id", dept.getDept_id());
						ap_public.setIs_del(0);
						ap_public.setIs_stop(0);
						ap_public.getMap().put("customer_type_is_null", true);
						List<KonkaOrderAuditProcess> ap_publicauditProcesseList = getFacade()
								.getKonkaOrderAuditProcessService().getKonkaOrderAuditProcessList(ap_public);
						processList.addAll(ap_publicauditProcesseList);
					}

					if (null == processList || processList.size() == 0) {
						// 没有分公司自定义的流程取统一流程，即总公司自定义流程
						KonkaOrderAuditProcess ap_public = new KonkaOrderAuditProcess();
						ap_public.setAdd_dept_id(0L);
						ap_public.setIs_del(0);
						ap_public.setIs_stop(0);
						ap_public.getMap().put("customer_type_is_null", true);
						List<KonkaOrderAuditProcess> ap_publicauditProcesseList = getFacade()
								.getKonkaOrderAuditProcessService().getKonkaOrderAuditProcessList(ap_public);
						processList.addAll(ap_publicauditProcesseList);
					}

					request.setAttribute("customer_type", konkaR3Shop_.getCustomer_type());
				} else {
					KonkaOrderAuditProcess process = new KonkaOrderAuditProcess();
					process.getMap().put("par_add_dept_id", dept.getDept_id());
					process.getMap().put("customer_type_is_null", true);
					process.setIs_del(0);
					process.setIs_stop(0);
					processList = super.getFacade().getKonkaOrderAuditProcessService()
							.getKonkaOrderAuditProcessList(process);
					if (null == processList || processList.size() == 0) {
						// 没有分公司统一流程，即分公司优先级高
						KonkaOrderAuditProcess ap_public = new KonkaOrderAuditProcess();
						ap_public.getMap().put("customer_type_is_null", true);
						ap_public.setAdd_dept_id(0L);
						ap_public.setIs_del(0);
						ap_public.setIs_stop(0);
						List<KonkaOrderAuditProcess> ap_publicauditProcesseList = getFacade()
								.getKonkaOrderAuditProcessService().getKonkaOrderAuditProcessList(ap_public);
						processList.addAll(ap_publicauditProcesseList);
					}
				}
			}
			request.setAttribute("processList", processList);
		}
		dynaBean.set("brandId", Constants.KONKA_BRAND_ID);
		dynaBean.set("trade_index", new BaseClientJxcAction().generateTradeIndex());// 交易流水号
		dynaBean.set("today", DateFormatUtils.format(new Date(), "yyyy-MM-dd"));// 订单日期
		dynaBean.set("cg_order_date", new Date());// 订单日期
		dynaBean.set("user_shop_name", konkaR3Shop.getCustomer_name());
		dynaBean.set("r3_code", konkaR3Shop.getR3_code());
		dynaBean.set("userName", user.getReal_name());
		String fgsSN = konkaR3Shop.getBranch_area_name_2();
		String fgsSN2 = konkaR3Shop.getR3_sales_org_code();
		request.setAttribute("sales_org", StringUtils.isBlank(fgsSN2) ? fgsSN : fgsSN2);

		/** 取网点业务员 */
		BranchAssign bagn = new BranchAssign();
		bagn.setKonka_r3_id(current_cust_id);
		bagn = getFacade().getBranchAssignService().getBranchAssign(bagn);
		if (null != bagn && null != bagn.getUser_id()) {
			PeProdUser ywy = new PeProdUser();
			ywy.setId(bagn.getUser_id());
			ywy = getFacade().getPeProdUserService().getPeProdUserResult(ywy);
			request.setAttribute("ywy_user_name", ywy.getReal_name());
		}

		if (super.isCallR3Interface(request)) {
			String sales_org = StringUtils.isBlank(fgsSN2) ? fgsSN : fgsSN2;
			// List<KNVP> knvpList =
			// super.getFacade().getR3WebInterfaceService().getKnvpsList(fgsSN,
			// Constants.default_vtweg, Constants.default_spart,
			// konkaR3Shop.getR3_code());
			//

			List<KNVP> knvpList = new ArrayList<KNVP>();
			ReturnInfo<KNVP> info = new ReturnInfo<KNVP>();
			info = super
					.getFacade()
					.getR3WebInterfaceService()
					.getKnvpsList(sales_org, Constants.default_vtweg, Constants.default_spart, konkaR3Shop.getR3_code());
			if (info.getSuccess() == true) {
				knvpList = info.getDataResult();
			}

			// AG:售达方
			// RE:出票方
			// RG:付款方
			// WE:送达方

			List<KNVP> agList = new ArrayList<KNVP>();
			List<KNVP> reList = new ArrayList<KNVP>();
			List<KNVP> rgList = new ArrayList<KNVP>();
			List<KNVP> weList = new ArrayList<KNVP>();

			if (null != knvpList) {
				for (KNVP cur : knvpList) {
					if ("AG".equalsIgnoreCase(cur.getPARVW())) {
						agList.add(cur);
					} else if ("RE".equalsIgnoreCase(cur.getPARVW())) {
						reList.add(cur);
					} else if ("RG".equalsIgnoreCase(cur.getPARVW())) {
						rgList.add(cur);
					} else if ("WE".equalsIgnoreCase(cur.getPARVW())) {
						weList.add(cur);
					}
				}
			}

			request.setAttribute("agList", agList);
			request.setAttribute("reList", reList);
			request.setAttribute("rgList", rgList);
			request.setAttribute("weList", weList);

			if (agList.size() == 0) {
				request.setAttribute("ag", konkaR3Shop.getR3_code());
			}
			if (reList.size() == 0) {
				request.setAttribute("re", konkaR3Shop.getR3_code());
			}
			if (rgList.size() == 0) {
				request.setAttribute("rg", konkaR3Shop.getR3_code());
			}
			if (weList.size() == 0) {
				KonkaMobileImpStore s = new KonkaMobileImpStore();
				s.setR3_shdf_sn(konkaR3Shop.getR3_code());
				List<KonkaMobileImpStore> sList = super.getFacade().getKonkaMobileImpStoreService()
						.getKonkaMobileImpStoreListForDistinctSdf(s);

				List<KNVP> __weList = new ArrayList<KNVP>();
				for (KonkaMobileImpStore cur : sList) {
					KNVP k = new KNVP();
					k.setKUNN2(cur.getR3_sdf_sn());
					__weList.add(k);
				}
				request.setAttribute("weList", __weList);
				if (__weList.size() == 0) {
					request.setAttribute("we", konkaR3Shop.getR3_code());
				}
			}
		} else {
			request.setAttribute("ag", konkaR3Shop.getR3_code());
			request.setAttribute("re", konkaR3Shop.getR3_code());
			request.setAttribute("rg", konkaR3Shop.getR3_code());

			KonkaMobileImpStore s = new KonkaMobileImpStore();
			s.setR3_shdf_sn(konkaR3Shop.getR3_code());
			List<KonkaMobileImpStore> sList = super.getFacade().getKonkaMobileImpStoreService()
					.getKonkaMobileImpStoreListForDistinctSdf(s);

			List<KNVP> weList = new ArrayList<KNVP>();
			for (KonkaMobileImpStore cur : sList) {
				KNVP k = new KNVP();
				k.setKUNN2(cur.getR3_sdf_sn());
				weList.add(k);
			}
			request.setAttribute("weList", weList);
		}
		List<KonkaOrderInfoDetails> konkaOrderInfoDetailsList = new ArrayList<KonkaOrderInfoDetails>();

		// 查出商品详细信息并且存到konkaOrderInfoDetailsList这个list中
		KonkaXxSellBillDetails entity = new KonkaXxSellBillDetails();
		entity.setSell_bill_id(Long.valueOf(sell_bill_id));
		List<KonkaXxSellBillDetails> entityList = super.getFacade().getKonkaXxSellBillDetailsService()
				.getKonkaXxSellBillDetailsList(entity);
		if (null != entityList && entityList.size() > 0) {
			for (KonkaXxSellBillDetails temp : entityList) {
				KonkaOrderInfoDetails orderdetail = new KonkaOrderInfoDetails();

				// 取型号、大类信息

				PePdModel pePdModel = new PePdModel();
				pePdModel.setIs_del(0);
				pePdModel.setMd_name(temp.getMd_name());
				// pePdModel.setPd_id(temp.getPd_cls());
				List<PePdModel> pePdModelList = getFacade().getPePdModelService().getPePdModelList(pePdModel);

				if (null != pePdModelList && pePdModelList.size() > 0) {
					orderdetail.setPd_id(pePdModelList.get(0).getPd_id());
					orderdetail.setPd_name(pePdModelList.get(0).getMd_name());
					orderdetail.setBrand_id(pePdModelList.get(0).getBrand_id());
					orderdetail.setBrand_name(Constants.KONKA_BRAND_NAME);
					orderdetail.setPd_type_id(pePdModelList.get(0).getCls_id());
					// orderdetail.setGood_price(BigDecimal.valueOf(super.getPdmodelPrice(pePdModel.getMd_name(),konkaR3Shop.getR3_code())));
					// orderdetail.setGood_sum_price(BigDecimal.valueOf(super.getPdmodelPrice(pePdModel.getMd_name(),konkaR3Shop.getR3_code())));

					// konkaOrderInfoDetails.setPd_type_name(super.getBasePdClazz(pePdModelList.get(0).getCls_id())
					// .getCls_name());
				}
				orderdetail.setGood_count(temp.getCounts());
				orderdetail.setGood_price(temp.getPrice());
				orderdetail.setGood_sum_price(temp.getPrice());
				orderdetail.setGood_unit("台");
				konkaOrderInfoDetailsList.add(orderdetail);
			}
		}
		request.setAttribute("konkaOrderInfoDetailsList", konkaOrderInfoDetailsList);

		request.setAttribute("call_r3_interface", super.isCallR3Interface(request));
		request.setAttribute("sell_bill_id", sell_bill_id);
		return new ActionForward("/../customer/JxcKonkaOrderRegister/input4.jsp");
	}

	/*
	 * @author:Wang,KunLin
	 * 
	 * @version:2014-5-4
	 * 
	 * @将订单转为在线下单流程后可以同步的R3单
	 */
	public ActionForward switchR3All(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		DynaBean dynaBean = (DynaBean) form;
		String[] pks = request.getParameterValues("pks");

		String CUSTID = (String) dynaBean.get("CUSTID"); // 业务提交请求的客户ID

		HttpSession session = request.getSession();

		PeProdUser user = null;
		if (GenericValidator.isLong(CUSTID)) {
			user = new PeProdUser();
			user.getRow().setCount(1);
			user.setCust_id(Long.valueOf(CUSTID));
			List<PeProdUser> userList = super.getFacade().getPeProdUserService().getPeProdUserList(user);
			if (null == userList || 0 == userList.size()) {
			}
			user = userList.get(0);
			session.setAttribute(Constants.CUSTOMER_USER_INFO, user);
		} else {
			user = (PeProdUser) session.getAttribute(Constants.CUSTOMER_USER_INFO);
		}
		if (null == user) {
			return null;
		}
		if (ArrayUtils.isEmpty(pks)) {
			return null;
		}

		String cust_id = (String) dynaBean.get("cust_id");
		// 取收货地址
		KonkaJxcBaseAddr konkaJxcBaseAddr = new KonkaJxcBaseAddr();
		konkaJxcBaseAddr.setIs_del(0);
		KonkaR3Shop r3shop = new KonkaR3Shop();
		r3shop.setId(user.getCust_id());
		r3shop = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(r3shop);
		if (null != r3shop) {
			konkaJxcBaseAddr.setR3_id(r3shop.getId());
			konkaJxcBaseAddr.setR3_code(r3shop.getR3_code());
		}
		List<KonkaJxcBaseAddr> konkaJxcBaseAddrList = super.getFacade().getKonkaJxcBaseAddrService()
				.getKonkaJxcBaseAddrList(konkaJxcBaseAddr);
		request.setAttribute("konkaJxcBaseAddrList", konkaJxcBaseAddrList);

		if (null != konkaJxcBaseAddrList && konkaJxcBaseAddrList.size() > 0) {
			for (KonkaJxcBaseAddr kba : konkaJxcBaseAddrList) {
				if (kba.getIs_default() == 0) {
					dynaBean.set("send_type", 2);
					dynaBean.set("user_name", kba.getUser_name());
					dynaBean.set("user_tel", kba.getUser_tel());
					dynaBean.set("user_phone", kba.getUser_phone());
					if (StringUtils.isNotBlank(kba.getUser_p_index().toString())) {
						String p_index = kba.getUser_p_index().toString();
						if (!p_index.endsWith("00")) {
							if (p_index.length() == 6) {
								dynaBean.set("province", StringUtils.substring(p_index, 0, 2) + "0000");
								dynaBean.set("city", StringUtils.substring(p_index, 0, 4) + "00");
								dynaBean.set("country", p_index);
							} else if (p_index.length() == 8) {
								dynaBean.set("province", StringUtils.substring(p_index, 0, 2) + "0000");
								dynaBean.set("city", StringUtils.substring(p_index, 0, 4) + "00");
								dynaBean.set("country", StringUtils.substring(p_index, 0, 6));
								dynaBean.set("town", p_index);
							}
						} else if (p_index.endsWith("0000")) {
							dynaBean.set("province", p_index);
						} else if (p_index.endsWith("00")) {
							dynaBean.set("province", StringUtils.substring(p_index, 0, 2) + "0000");
							dynaBean.set("city", p_index);
						}
					}
					dynaBean.set("user_addr", kba.getUser_addr());
					dynaBean.set("user_remark", kba.getUser_remark());
				}
			}

		}

		Long current_cust_id = null;

		if (null != user.getCust_id()) {
			// 当前登录人是客户
			current_cust_id = user.getCust_id();
		} else {
			// 当前登录人不是客户
			PeRoleUser _peRoleUser = new PeRoleUser();
			_peRoleUser.setUser_id(user.getId());
			List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);

			boolean role_id_eq_60 = false;
			for (PeRoleUser peRoleUser : peRoleUserList) {
				if (peRoleUser.getRole_id() == 60L) {
					role_id_eq_60 = true;
				}
			}
			if (user.getCust_id() == null && role_id_eq_60) {
				if (!GenericValidator.isLong(cust_id)) {
					return null;
				}
				current_cust_id = Long.valueOf(cust_id);
			}
		}

		if (null == current_cust_id) {
			return null;
		}

		request.setAttribute("cust_id", current_cust_id);

		KonkaR3Shop konkaR3Shop = new KonkaR3Shop();
		konkaR3Shop.setId(current_cust_id);
		konkaR3Shop = getFacade().getKonkaR3ShopService().getKonkaR3Shop(konkaR3Shop);
		if (null == konkaR3Shop) {
			super.renderJavaScript(response, "alert('" + super.getMessage(request, "konka.r3.customer.none")
					+ "');history.back();");
			return null;
		}

		// 获取流程列表
		KonkaDept kd = super.getKonkaDeptByCustomerId(current_cust_id);
		KonkaDept dept = super.getSuperiorForDeptType(kd.getDept_id(), 3);
		List<KonkaOrderAuditProcess> processList = new ArrayList<KonkaOrderAuditProcess>();
		// Boolean flag = null;
		if (dept != null) {

			KonkaR3Shop konkaR3Shop_ = new KonkaR3Shop();
			konkaR3Shop_.setId(current_cust_id);
			konkaR3Shop_.setIs_del(0l);
			konkaR3Shop_ = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(konkaR3Shop_);
			if (null != konkaR3Shop_) {
				if (StringUtils.isNotBlank(konkaR3Shop_.getCustomer_type())) {// 判断是否是有客户类型
					KonkaOrderAuditProcess process = new KonkaOrderAuditProcess();
					process.getMap().put("par_add_dept_id", dept.getDept_id());
					process.setCustomer_type(konkaR3Shop_.getCustomer_type());
					process.setIs_del(0);
					process.setIs_stop(0);
					processList = super.getFacade().getKonkaOrderAuditProcessService()
							.getKonkaOrderAuditProcessList(process);
					if (null == processList || processList.size() == 0) {
						// 没有分公司自定义的流程取统一流程，即分公司自定义流程
						KonkaOrderAuditProcess ap_public = new KonkaOrderAuditProcess();
						ap_public.setCustomer_type(konkaR3Shop_.getCustomer_type());
						ap_public.setAdd_dept_id(0L);
						ap_public.setIs_del(0);
						ap_public.setIs_stop(0);
						List<KonkaOrderAuditProcess> ap_publicauditProcesseList = getFacade()
								.getKonkaOrderAuditProcessService().getKonkaOrderAuditProcessList(ap_public);
						processList.addAll(ap_publicauditProcesseList);
					}
					if (null == processList || processList.size() == 0) {
						// 没有分公司自定义的流程取统一流程，即分公司流程
						KonkaOrderAuditProcess ap_public = new KonkaOrderAuditProcess();
						ap_public.setCustomer_type(konkaR3Shop_.getCustomer_type());
						ap_public.setAdd_dept_id(0L);
						ap_public.setIs_del(0);
						ap_public.setIs_stop(0);
						List<KonkaOrderAuditProcess> ap_publicauditProcesseList = getFacade()
								.getKonkaOrderAuditProcessService().getKonkaOrderAuditProcessList(ap_public);
						processList.addAll(ap_publicauditProcesseList);
					}
					if (null == processList || processList.size() == 0) {
						// 没有分公司流程取统一流程，即总公司流程
						KonkaOrderAuditProcess ap_public = new KonkaOrderAuditProcess();
						ap_public.getMap().put("par_add_dept_id", dept.getDept_id());
						ap_public.setIs_del(0);
						ap_public.setIs_stop(0);
						ap_public.getMap().put("customer_type_is_null", true);
						List<KonkaOrderAuditProcess> ap_publicauditProcesseList = getFacade()
								.getKonkaOrderAuditProcessService().getKonkaOrderAuditProcessList(ap_public);
						processList.addAll(ap_publicauditProcesseList);
					}

					if (null == processList || processList.size() == 0) {
						// 没有分公司自定义的流程取统一流程，即总公司自定义流程
						KonkaOrderAuditProcess ap_public = new KonkaOrderAuditProcess();
						ap_public.setAdd_dept_id(0L);
						ap_public.setIs_del(0);
						ap_public.setIs_stop(0);
						ap_public.getMap().put("customer_type_is_null", true);
						List<KonkaOrderAuditProcess> ap_publicauditProcesseList = getFacade()
								.getKonkaOrderAuditProcessService().getKonkaOrderAuditProcessList(ap_public);
						processList.addAll(ap_publicauditProcesseList);
					}

					request.setAttribute("customer_type", konkaR3Shop_.getCustomer_type());
				} else {
					KonkaOrderAuditProcess process = new KonkaOrderAuditProcess();
					process.getMap().put("par_add_dept_id", dept.getDept_id());
					process.getMap().put("customer_type_is_null", true);
					process.setIs_del(0);
					process.setIs_stop(0);
					processList = super.getFacade().getKonkaOrderAuditProcessService()
							.getKonkaOrderAuditProcessList(process);
					if (null == processList || processList.size() == 0) {
						// 没有分公司统一流程，即分公司优先级高
						KonkaOrderAuditProcess ap_public = new KonkaOrderAuditProcess();
						ap_public.getMap().put("customer_type_is_null", true);
						ap_public.setAdd_dept_id(0L);
						ap_public.setIs_del(0);
						ap_public.setIs_stop(0);
						List<KonkaOrderAuditProcess> ap_publicauditProcesseList = getFacade()
								.getKonkaOrderAuditProcessService().getKonkaOrderAuditProcessList(ap_public);
						processList.addAll(ap_publicauditProcesseList);
					}
				}
			}
			request.setAttribute("processList", processList);
		}
		dynaBean.set("brandId", Constants.KONKA_BRAND_ID);
		dynaBean.set("trade_index", new BaseClientJxcAction().generateTradeIndex());// 交易流水号
		dynaBean.set("today", DateFormatUtils.format(new Date(), "yyyy-MM-dd"));// 订单日期
		dynaBean.set("cg_order_date", new Date());// 订单日期
		dynaBean.set("user_shop_name", konkaR3Shop.getCustomer_name());
		dynaBean.set("r3_code", konkaR3Shop.getR3_code());
		dynaBean.set("userName", user.getReal_name());
		String fgsSN = konkaR3Shop.getBranch_area_name_2();
		String fgsSN2 = konkaR3Shop.getR3_sales_org_code();
		request.setAttribute("sales_org", StringUtils.isBlank(fgsSN2) ? fgsSN : fgsSN2);

		String sales_org = StringUtils.isBlank(fgsSN2) ? fgsSN : fgsSN2;

		/** 取网点业务员 */
		BranchAssign bagn = new BranchAssign();
		bagn.setKonka_r3_id(current_cust_id);
		bagn = getFacade().getBranchAssignService().getBranchAssign(bagn);
		if (null != bagn && null != bagn.getUser_id()) {
			PeProdUser ywy = new PeProdUser();
			ywy.setId(bagn.getUser_id());
			ywy = getFacade().getPeProdUserService().getPeProdUserResult(ywy);
			request.setAttribute("ywy_user_name", ywy.getReal_name());
		}

		if (super.isCallR3Interface(request)) {
			// List<KNVP> knvpList =
			// super.getFacade().getR3WebInterfaceService().getKnvpsList(fgsSN,
			// Constants.default_vtweg, Constants.default_spart,
			// konkaR3Shop.getR3_code());
			//

			List<KNVP> knvpList = new ArrayList<KNVP>();
			ReturnInfo<KNVP> info = new ReturnInfo<KNVP>();
			info = super
					.getFacade()
					.getR3WebInterfaceService()
					.getKnvpsList(sales_org, Constants.default_vtweg, Constants.default_spart, konkaR3Shop.getR3_code());
			if (info.getSuccess() == true) {
				knvpList = info.getDataResult();
			}

			// AG:售达方
			// RE:出票方
			// RG:付款方
			// WE:送达方

			List<KNVP> agList = new ArrayList<KNVP>();
			List<KNVP> reList = new ArrayList<KNVP>();
			List<KNVP> rgList = new ArrayList<KNVP>();
			List<KNVP> weList = new ArrayList<KNVP>();

			if (null != knvpList) {
				for (KNVP cur : knvpList) {
					if ("AG".equalsIgnoreCase(cur.getPARVW())) {
						agList.add(cur);
					} else if ("RE".equalsIgnoreCase(cur.getPARVW())) {
						reList.add(cur);
					} else if ("RG".equalsIgnoreCase(cur.getPARVW())) {
						rgList.add(cur);
					} else if ("WE".equalsIgnoreCase(cur.getPARVW())) {
						weList.add(cur);
					}
				}
			}

			request.setAttribute("agList", agList);
			request.setAttribute("reList", reList);
			request.setAttribute("rgList", rgList);
			request.setAttribute("weList", weList);

			if (agList.size() == 0) {
				request.setAttribute("ag", konkaR3Shop.getR3_code());
			}
			if (reList.size() == 0) {
				request.setAttribute("re", konkaR3Shop.getR3_code());
			}
			if (rgList.size() == 0) {
				request.setAttribute("rg", konkaR3Shop.getR3_code());
			}
			if (weList.size() == 0) {
				KonkaMobileImpStore s = new KonkaMobileImpStore();
				s.setR3_shdf_sn(konkaR3Shop.getR3_code());
				List<KonkaMobileImpStore> sList = super.getFacade().getKonkaMobileImpStoreService()
						.getKonkaMobileImpStoreListForDistinctSdf(s);

				List<KNVP> __weList = new ArrayList<KNVP>();
				for (KonkaMobileImpStore cur : sList) {
					KNVP k = new KNVP();
					k.setKUNN2(cur.getR3_sdf_sn());
					__weList.add(k);
				}
				request.setAttribute("weList", __weList);
				if (__weList.size() == 0) {
					request.setAttribute("we", konkaR3Shop.getR3_code());
				}
			}
		} else {
			request.setAttribute("ag", konkaR3Shop.getR3_code());
			request.setAttribute("re", konkaR3Shop.getR3_code());
			request.setAttribute("rg", konkaR3Shop.getR3_code());

			KonkaMobileImpStore s = new KonkaMobileImpStore();
			s.setR3_shdf_sn(konkaR3Shop.getR3_code());
			List<KonkaMobileImpStore> sList = super.getFacade().getKonkaMobileImpStoreService()
					.getKonkaMobileImpStoreListForDistinctSdf(s);

			List<KNVP> weList = new ArrayList<KNVP>();
			for (KonkaMobileImpStore cur : sList) {
				KNVP k = new KNVP();
				k.setKUNN2(cur.getR3_sdf_sn());
				weList.add(k);
			}
			request.setAttribute("weList", weList);
		}
		List<KonkaOrderInfoDetails> konkaOrderInfoDetailsList = new ArrayList<KonkaOrderInfoDetails>();

		// 查出商品详细信息并且存到konkaOrderInfoDetailsList这个list中

		for (String pd : pks) {
			KonkaXxSellBillDetails entity = new KonkaXxSellBillDetails();
			entity.setSell_bill_id(Long.valueOf(pd));
			List<KonkaXxSellBillDetails> entityList = super.getFacade().getKonkaXxSellBillDetailsService()
					.getKonkaXxSellBillDetailsList(entity);
			if (null != entityList && entityList.size() > 0) {
				for (KonkaXxSellBillDetails temp : entityList) {
					KonkaOrderInfoDetails orderdetail = new KonkaOrderInfoDetails();

					// 取型号、大类信息

					PePdModel pePdModel = new PePdModel();
					pePdModel.setIs_del(0);
					pePdModel.setMd_name(temp.getMd_name());
					// pePdModel.setPd_id(temp.getPd_cls());
					List<PePdModel> pePdModelList = getFacade().getPePdModelService().getPePdModelList(pePdModel);

					if (null != pePdModelList && pePdModelList.size() > 0) {
						orderdetail.setPd_id(pePdModelList.get(0).getPd_id());
						orderdetail.setPd_name(pePdModelList.get(0).getMd_name());
						orderdetail.setBrand_id(pePdModelList.get(0).getBrand_id());
						orderdetail.setBrand_name(Constants.KONKA_BRAND_NAME);
						orderdetail.setPd_type_id(pePdModelList.get(0).getCls_id());
						// orderdetail.setGood_price(BigDecimal.valueOf(super.getPdmodelPrice(pePdModel.getMd_name(),konkaR3Shop.getR3_code())));
						// orderdetail.setGood_sum_price(BigDecimal.valueOf(super.getPdmodelPrice(pePdModel.getMd_name(),konkaR3Shop.getR3_code())));
						// konkaOrderInfoDetails.setPd_type_name(super.getBasePdClazz(pePdModelList.get(0).getCls_id())
						// .getCls_name());
					}
					orderdetail.setGood_price(temp.getPrice());
					orderdetail.setGood_sum_price(temp.getPrice());
					orderdetail.setGood_count(temp.getCounts());
					orderdetail.setGood_unit("台");
					konkaOrderInfoDetailsList.add(orderdetail);
				}
			}

		}
		request.setAttribute("konkaOrderInfoDetailsList", konkaOrderInfoDetailsList);

		request.setAttribute("call_r3_interface", super.isCallR3Interface(request));
		String pks1 = "";

		for (String p : pks) {
			pks1 = pks1 + p + ",";
		}
		request.setAttribute("sellids", pks1);
		return new ActionForward("/../customer/JxcKonkaOrderRegister/input4.jsp");
	}

}
