package com.ebiz.mmt.web.struts.manager.zmd;

import java.math.BigDecimal;
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
import com.ebiz.mmt.domain.KonkaXxZmd;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Jiang,JiaYong
 * @version 2012-1-9
 */
public class KonkaXxZmdResAction extends BaseZmdAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;

		Pager pager = (Pager) dynaBean.get("pager");
		// 查询条件
		String audit_date_start = (String) dynaBean.get("audit_date_start");
		String audit_date_end = (String) dynaBean.get("audit_date_end");
		String dept_id = (String) dynaBean.get("dept_id");
		String allToExcel = (String) dynaBean.get("allToExcel");

		// 当前角色
		PeProdUser user_info = super.getSessionUserInfo(request);
		List<PeRoleUser> peRoleUserList = getPeRoleList(user_info.getId());

		Boolean role_id_gt_400 = false;
		Boolean role_id_btw_300_400 = false;
		Boolean role_id_btw_200_300_ge_30 = false;
		if (peRoleUserList.size() > 0) {
			for (PeRoleUser temp : peRoleUserList) {
				if (temp.getRole_id() <= 400) {
					role_id_gt_400 = true;
				}
				if (temp.getRole_id() >= 300 && temp.getRole_id() < 400) {
					role_id_btw_300_400 = true;
				}
				if (temp.getRole_id() >= 200 && temp.getRole_id() < 300 || temp.getRole_id() < 30) {
					role_id_btw_200_300_ge_30 = true;
				}
			}
		}
		// PeRoleUser peRoleUser = super.getRoleInfoByThisLogin(request);
		if (!role_id_gt_400) {
			String msg = super.getMessage(request, "konka.xx.zmd.role.error");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}
		request.setAttribute("role_id_btw_200_300_ge_30", role_id_btw_200_300_ge_30);
		KonkaXxZmd entity = new KonkaXxZmd();
		entity.setArc_state(20300);// 去备案通过的 KONKA_XX_BASE_TYPE中的值，写死
		entity.setIs_del(0); // 查询未删除
		if (StringUtils.isNotBlank(audit_date_start))
			entity.getMap().put("audit_date_start", audit_date_start);
		if (StringUtils.isNotBlank(audit_date_end))
			entity.getMap().put("audit_date_end", audit_date_end);

		if (role_id_btw_200_300_ge_30) { // 新兴渠道部及新兴渠道部以上用户
			if (GenericValidator.isLong(dept_id)) {
				entity.setDept_id(Long.valueOf(dept_id));
			}

			// 取分公司信息列表
			KonkaDept konkaDept = new KonkaDept();
			konkaDept.setDept_type(3); // 只取分公司的
			konkaDept.setPar_id(0L);
			List<KonkaDept> konkaDeptList = super.getFacade().getKonkaDeptService().getKonkaDeptList(konkaDept);
			request.setAttribute("konkaDeptList", konkaDeptList);
		} else if (role_id_btw_300_400) { // 分公司及分公司以下用户
			KonkaDept kDept = getKonkaDeptForFgs(user_info.getDept_id());
			if (kDept != null)
				entity.setDept_id(kDept.getDept_id());
		}

		Long recordCount = getFacade().getKonkaXxZmdService().getKonkaXxZmdCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<KonkaXxZmd> entityList = getFacade().getKonkaXxZmdService()
				.getKonkaXxZmdPaginatedListIncludeRelevanceInfo(entity);
		request.setAttribute("entityList", entityList);

		// 处理合计 start...
		BigDecimal busi_area = new BigDecimal("0"); // 面积
		BigDecimal rent_fee = new BigDecimal("0"); // 年租金费
		BigDecimal money_of_dcrt = new BigDecimal("0"); // 投入装修费
		BigDecimal money_of_sell_by_year_plan = new BigDecimal("0"); // 预计年销额
		BigDecimal money_of_deposit = new BigDecimal("0"); // 专卖店押金
		for (KonkaXxZmd t : entityList) {
			busi_area = busi_area.add(t.getBusi_area());
			rent_fee = rent_fee.add(t.getRent_fee());
			money_of_dcrt = money_of_dcrt.add(t.getMoney_of_dcrt());
			money_of_sell_by_year_plan = money_of_sell_by_year_plan.add(t.getMoney_of_sell_by_year_plan());
			money_of_deposit = money_of_deposit.add(t.getMoney_of_deposit());
		}
		dynaBean.set("busi_area", busi_area.toString());
		dynaBean.set("rent_fee", rent_fee.toString());
		dynaBean.set("money_of_dcrt", money_of_dcrt.toString());
		dynaBean.set("money_of_sell_by_year_plan", money_of_sell_by_year_plan.toString());
		dynaBean.set("money_of_deposit", money_of_deposit.toString());
		// 处理合计 end.

		// 判断再次进入是否是在页面点击了全部导出
		if (StringUtils.isNotBlank(allToExcel) && "ebiz".equals(allToExcel)) {
			entity.getRow().setCount(recordCount.intValue());
			List<KonkaXxZmd> allExcelList = getFacade().getKonkaXxZmdService()
					.getKonkaXxZmdPaginatedListIncludeRelevanceInfo(entity);
			request.setAttribute("allExcelList", allExcelList);

			// 处理合计 start...
			BigDecimal excel_busi_area = new BigDecimal("0"); // 面积
			BigDecimal excel_rent_fee = new BigDecimal("0"); // 年租金费
			BigDecimal excel_money_of_dcrt = new BigDecimal("0"); // 投入装修费
			BigDecimal excel_money_of_sell_by_year_plan = new BigDecimal("0"); // 预计年销额
			BigDecimal excel_money_of_deposit = new BigDecimal("0"); // 专卖店押金
			for (KonkaXxZmd t : allExcelList) {
				excel_busi_area = excel_busi_area.add(t.getBusi_area());
				excel_rent_fee = excel_rent_fee.add(t.getRent_fee());
				excel_money_of_dcrt = excel_money_of_dcrt.add(t.getMoney_of_dcrt());
				excel_money_of_sell_by_year_plan = excel_money_of_sell_by_year_plan.add(t
						.getMoney_of_sell_by_year_plan());
				excel_money_of_deposit = excel_money_of_deposit.add(t.getMoney_of_deposit());
			}
			dynaBean.set("excel_busi_area", excel_busi_area.toString());
			dynaBean.set("excel_rent_fee", excel_rent_fee.toString());
			dynaBean.set("excel_money_of_dcrt", excel_money_of_dcrt.toString());
			dynaBean.set("excel_money_of_sell_by_year_plan", excel_money_of_sell_by_year_plan.toString());
			dynaBean.set("excel_money_of_deposit", excel_money_of_deposit.toString());
			// 处理合计 end.
		}

		dynaBean.set("role_id_btw_200_300_ge_30", role_id_btw_200_300_ge_30);
		dynaBean.set("dept_id", dept_id);
		dynaBean.set("audit_date_start", audit_date_start);
		dynaBean.set("audit_date_end", audit_date_end);
		dynaBean.set("allToExcel", allToExcel);
		dynaBean.set("now_date", DateFormatUtils.format(new Date(), "yyyy-MM-dd"));
		return mapping.findForward("list");
	}
}
