package com.ebiz.mmt.web.struts.customer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaXxBaseType;
import com.ebiz.mmt.domain.KonkaXxSellBillDetails;
import com.ebiz.mmt.domain.KonkaXxSellBillDetailsDst;
import com.ebiz.mmt.domain.KonkaXxZmd;
import com.ebiz.mmt.domain.KonkaXxZmdUsers;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.manager.zmd.BaseZmdAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Wu,ShangLong
 * @version 2012-1-10
 */
public class KonkaXxZmdDztzSearchAction extends BaseZmdAction {

	protected ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String dept_id = (String) dynaBean.get("dept_id");
		String zmd_id = (String) dynaBean.get("zmd_id");
		String sell_bill_id = (String) dynaBean.get("sell_bill_id");
		String sell_date_ge = (String) dynaBean.get("sell_date_ge");
		String sell_date_le = (String) dynaBean.get("sell_date_le");
		String sell_man_like = (String) dynaBean.get("sell_man_like");
		String par_pd_cls = (String) dynaBean.get("par_pd_cls");
		String md_name_like = (String) dynaBean.get("md_name_like");
		String allToExcel = (String) dynaBean.get("allToExcel");

		KonkaXxBaseType konkaXxBaseType = new KonkaXxBaseType();
		konkaXxBaseType.setPar_id(50000L); // 付款方式
		List<KonkaXxBaseType> pay_way_arr = super.getFacade().getKonkaXxBaseTypeService().getKonkaXxBaseTypeList(
				konkaXxBaseType);
		request.setAttribute("pay_way_arr", pay_way_arr);

		KonkaXxSellBillDetails entity = new KonkaXxSellBillDetails();

		PeProdUser user_info = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

		List<PeRoleUser> peRoleUserList = getPeRoleList(user_info.getId());
		Boolean role_id_eq_400 = false;
		Boolean role_id_eq_390 = false;
		Boolean role_id_btw_300_400 = false;
		Boolean role_id_btw_200_300 = false;
		if (peRoleUserList.size() > 0) {
			for (PeRoleUser temp : peRoleUserList) {
				if (temp.getRole_id() == 400) {
					role_id_eq_400 = true;
				}
				if (temp.getRole_id() >= 300 && temp.getRole_id() < 400) {
					role_id_btw_300_400 = true;
				}
				if ((temp.getRole_id() >= 200 && temp.getRole_id() < 300) || temp.getRole_id() < 30) {
					role_id_btw_200_300 = true;
				}
				if (temp.getRole_id() == 390) {
					role_id_eq_390 = true;
				}
			}
		}

		// 页面角色控制 start
		if (role_id_eq_400) { // 专卖店人员
			KonkaXxZmdUsers konkaXxZmdUsers = new KonkaXxZmdUsers();
			konkaXxZmdUsers.setUser_id(user_info.getId());

			List<KonkaXxZmdUsers> konkaXxZmdUsersList = super.getFacade().getKonkaXxZmdUsersService()
					.getKonkaXxZmdUsersList(konkaXxZmdUsers);
			if (null != konkaXxZmdUsersList && konkaXxZmdUsersList.size() > 0) {
				entity.getMap().put("zmd_id", konkaXxZmdUsersList.get(0).getZmd_id());
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

			request.setAttribute("dept_name", kd.getDept_name());

			request.setAttribute("zmd_sn", zmd.getZmd_sn());

		} else if (role_id_btw_300_400) { // 分公司人员
			KonkaDept dept = getKonkaDeptForFgs(user_info.getDept_id());
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

				if (dept != null)
					entity.getMap().put("dept_id", dept.getDept_id());

				// 初始化业务员下面专卖店的下拉框
				KonkaXxZmd zmd = new KonkaXxZmd();
				if (dept != null)
					zmd.setDept_id(dept.getDept_id());
				List<KonkaXxZmd> zmdList = super.getFacade().getKonkaXxZmdService().getKonkaXxZmdList(zmd);

				request.setAttribute("zmdList", zmdList);
			}

			// KonkaDept kd = new KonkaDept();
			// if (dept != null)
			// kd.setDept_id(user_info.getDept_id());
			// kd = super.getFacade().getKonkaDeptService().getKonkaDept(kd);
			if (dept != null)
			request.setAttribute("dept_name", dept.getDept_name());

		} else if (role_id_btw_200_300) { // 总部人员

			// 初始化业务员下面专卖店的下拉框
			KonkaXxZmd zmd = new KonkaXxZmd();
			if (GenericValidator.isLong(dept_id)) {
				zmd.setDept_id(Long.valueOf(dept_id));
			}
			List<KonkaXxZmd> zmdList = super.getFacade().getKonkaXxZmdService().getKonkaXxZmdList(zmd);

			request.setAttribute("zmdList", zmdList);

		}
		// 页面角色控制 end

		entity.getMap().put("js_bill_state", 2); // 2：表示已经结算；0：表示未结算。

		// 选择的条件字段
		if (GenericValidator.isLong(dept_id)) {
			entity.getMap().put("dept_id", dept_id);
		}
		if (GenericValidator.isLong(zmd_id)) {
			entity.getMap().put("zmd_id", zmd_id);
		}
		if (GenericValidator.isLong(sell_bill_id)) {
			entity.setSell_bill_id(Long.valueOf(sell_bill_id));
		}
		if (StringUtils.isNotBlank(sell_date_ge)) {
			entity.getMap().put("sell_date_ge", sell_date_ge);
		}
		if (StringUtils.isNotBlank(sell_date_le)) {
			entity.getMap().put("sell_date_le", sell_date_le);
		}
		if (StringUtils.isNotBlank(sell_man_like)) {
			entity.getMap().put("sell_man_like", sell_man_like);
		}
		if (StringUtils.isNotBlank(par_pd_cls)) {
			entity.getMap().put("par_pd_cls", par_pd_cls);
		}
		if (StringUtils.isNotBlank(md_name_like)) {
			entity.getMap().put("md_name_like", md_name_like);
		}

		// 返佣销售单
		entity.getMap().put("order_type", 1);
		entity.getMap().put("stock_from", 1);

		Long recordCount = super.getFacade().getKonkaXxSellBillDetailsService()
				.getKonkaXxSellBillDetailsWithOrderCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setCount(pager.getRowCount());
		entity.getRow().setFirst(pager.getFirstRow());

		List<KonkaXxSellBillDetails> entityList = super.getFacade().getKonkaXxSellBillDetailsService()
				.getKonkaXxSellBillDetailsWithOrderPaginatedList(entity);

		BigDecimal total_pay = new BigDecimal("0"); // 总销售额
		BigDecimal total_fee = new BigDecimal("0"); // 总佣金
		if (null != entityList && entityList.size() > 0) {
			for (KonkaXxSellBillDetails temp : entityList) {
				total_pay = total_pay.add(temp.getPrice().multiply(new BigDecimal(temp.getCounts().toString())));
				total_fee = total_fee.add(temp.getZmd_fee());

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
			request.setAttribute("total_fee", total_fee.toString());
		}

		request.setAttribute("entityList", entityList);
		request.setAttribute("basePdClazzList", getBasePdClazzList());

		// 导出全部数据
		if (StringUtils.isNotBlank(allToExcel) && "ebiz".equals(allToExcel)) {
			entity.getRow().setCount(recordCount.intValue());
			entity.getRow().setFirst(0);
			List<KonkaXxSellBillDetails> entityList_all = super.getFacade().getKonkaXxSellBillDetailsService()
					.getKonkaXxSellBillDetailsWithOrderPaginatedList(entity);

			BigDecimal total_pay_all = new BigDecimal("0"); // 总销售额
			BigDecimal total_fee_all = new BigDecimal("0"); // 总佣金
			if (null != entityList_all && entityList_all.size() > 0) {
				for (KonkaXxSellBillDetails temp : entityList_all) {
					total_pay_all = total_pay_all.add(temp.getPrice().multiply(
							new BigDecimal(temp.getCounts().toString())));
					total_fee_all = total_fee_all.add(temp.getZmd_fee());

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
				request.setAttribute("total_pay_all", total_pay_all.toString());
				request.setAttribute("total_fee_all", total_fee_all.toString());
			}

			request.setAttribute("entityList_all", entityList_all);
		}

		request.setAttribute("konkaDepts", super.getKonkaDepts());
		dynaBean.set("now_date", DateFormatUtils.format(new Date(), "yyyy-MM-dd"));

		return mapping.findForward("list");

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
