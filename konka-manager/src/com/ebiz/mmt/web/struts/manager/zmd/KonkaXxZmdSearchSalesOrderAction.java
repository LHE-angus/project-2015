package com.ebiz.mmt.web.struts.manager.zmd;

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
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.JfScorts;
import com.ebiz.mmt.domain.JfScortsDetails;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaXxBaseType;
import com.ebiz.mmt.domain.KonkaXxSellBill;
import com.ebiz.mmt.domain.KonkaXxSellBillDetails;
import com.ebiz.mmt.domain.KonkaXxSellBillDetailsDst;
import com.ebiz.mmt.domain.KonkaXxZmd;
import com.ebiz.mmt.domain.KonkaXxZmdUsers;
import com.ebiz.mmt.domain.MemberInfo;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Wu,ShangLong
 * @version 2012-1-9
 */
public class KonkaXxZmdSearchSalesOrderAction extends BaseZmdAction {

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
		String dept_id_s = (String) dynaBean.get("dept_id");

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		KonkaXxBaseType konkaXxBaseType = new KonkaXxBaseType();
		konkaXxBaseType.setPar_id(50000L); // 付款方式
		List<KonkaXxBaseType> pay_way_arr = super.getFacade().getKonkaXxBaseTypeService().getKonkaXxBaseTypeList(
				konkaXxBaseType);
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
					zmd.setIs_del(0);
					zmd.getMap().put("zmds", zmds.toArray());
					List<KonkaXxZmd> zmdList = super.getFacade().getKonkaXxZmdService().getKonkaXxZmdList(zmd);

					request.setAttribute("zmdList", zmdList);
				} else {
					String msg = getMessage(request, "konka.xx.zmd.user.not.bind.zmd");
					saveDirectlyError(request, msg);
					return mapping.findForward("list");
				}
			} else {
				Long dept_id = super.getKonkaDeptForFgs(user_info.getDept_id()).getDept_id();

				entity.setDept_id(dept_id);

				// 初始化分公司其他人员下专卖店信息
				KonkaXxZmd zmd = new KonkaXxZmd();
				zmd.setDept_id(dept_id);
				zmd.setArc_state(20300);
				zmd.setIs_del(0);
				List<KonkaXxZmd> zmdList = super.getFacade().getKonkaXxZmdService().getKonkaXxZmdList(zmd);

				request.setAttribute("zmdList", zmdList);
			}
		} else {
			dynaBean.set("dept_type", "1");
			KonkaXxZmd zmd = new KonkaXxZmd();
			zmd.setArc_state(20300);
			zmd.setIs_del(0);
			List<KonkaXxZmd> zmdList = super.getFacade().getKonkaXxZmdService().getKonkaXxZmdList(zmd);
			request.setAttribute("zmdList", zmdList);
		}
		// 页面角色控制 end

		// 选择的条件字段
		if (GenericValidator.isLong(zmd_id)) {
			entity.setZmd_id(Long.valueOf(zmd_id));
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
			if(!"1".equals(sell_state)){
			entity.setSell_state(Long.valueOf(sell_state));
			}else{
			entity.getMap().put("audit_state", 1);	
			}
		}
		if (StringUtils.isNotBlank(add_user_realname_like)) {
			entity.getMap().put("add_user_realname_like", add_user_realname_like);
		}
		if (StringUtils.isNotBlank(buyer_name_like)) {
			entity.getMap().put("buyer_name_like", buyer_name_like);
		}
		if (StringUtils.isNotBlank(dept_id_s)) {
			entity.setDept_id(Long.valueOf(dept_id_s));
		}
		if (GenericValidator.isLong(pay_way)) {
			entity.setPay_way(Long.valueOf(pay_way));
		}

		Long recordCount = super.getFacade().getKonkaXxSellBillService().getKonkaXxSellBillCountfordetails(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setCount(pager.getRowCount());
		entity.getRow().setFirst(pager.getFirstRow());

//		List<KonkaXxSellBill> entityList = super.getFacade().getKonkaXxSellBillService()
//				.getKonkaXxSellBillPaginatedList(entity);

		List<KonkaXxSellBill> entityList = super.getFacade().getKonkaXxSellBillService()
		.getKonkaXxSellBillPaginatedListfordetails(entity);	
		request.setAttribute("entityList", entityList);
		
//	pager.init(recordCount, Integer.parseInt(recordCount.toString()), pager.getRequestPage());
		entity.getRow().setCount(Integer.parseInt(recordCount.toString()));
		entity.getRow().setFirst(pager.getFirstRow());
		List<KonkaXxSellBill> entityListforExcel = super.getFacade().getKonkaXxSellBillService()
		.getKonkaXxSellBillPaginatedListfordetails(entity);	
		
		
		request.setAttribute("entityListforExcel", entityListforExcel);
		
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
			String msg = getMessage(request, "jf.user.sn.empty");
			saveError(request, msg);
			return mapping.findForward("list");
		}

		MemberInfo memberInfo = new MemberInfo();
		memberInfo.setUser_sn(konkaXxSellBill.getConsumer_grade_num());
		List<MemberInfo> memberInfoList = super.getFacade().getMemberInfoService().getMemberInfoList(memberInfo);

		if (memberInfoList.size() == 0 || null == memberInfoList) {
			String msg = getMessage(request, "jf.user.sn.empty");
			saveError(request, msg);
			return mapping.findForward("list");
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
			saveError(request, msg);
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

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String sell_bill_id = (String) dynaBean.get("sell_bill_id");
		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}
		KonkaXxSellBill konkaXxSellBill = new KonkaXxSellBill();
		KonkaXxSellBillDetails konkaXxSellBilldetails = new KonkaXxSellBillDetails();
		if(StringUtils.isNotBlank(sell_bill_id)){
			konkaXxSellBill.setSell_bill_id(Long.valueOf(sell_bill_id));
			konkaXxSellBilldetails.setSell_bill_id(Long.valueOf(sell_bill_id));
		}
		super.getFacade().getKonkaXxSellBillService().removeKonkaXxSellBill(konkaXxSellBill);
		List<KonkaXxSellBillDetails> detailslist=super.getFacade().getKonkaXxSellBillDetailsService().getKonkaXxSellBillDetailsList(konkaXxSellBilldetails);
		if(null !=detailslist){
			for(KonkaXxSellBillDetails kksbd: detailslist){
				super.getFacade().getKonkaXxSellBillDetailsService().removeKonkaXxSellBillDetails(kksbd);
			}
		}
		
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		//pathBuffer.append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}
	
	public ActionForward toExcel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		try {
			super.toExcel(mapping, form, request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
