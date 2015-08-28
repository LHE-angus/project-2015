package com.ebiz.mmt.web.struts.manager.zmd;

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
import com.ebiz.mmt.domain.KonkaXxSellBill;
import com.ebiz.mmt.domain.KonkaXxZmd;
import com.ebiz.mmt.domain.KonkaXxZmdUsers;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Wu,ShangLong
 * @version 2012-1-11
 */
public class KonkaXxZmdDistJsSearchAction extends BaseZmdAction {

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
		String js_dist_date_ge = (String) dynaBean.get("js_dist_date_ge");
		String js_dist_date_le = (String) dynaBean.get("js_dist_date_le");
		String js_dist_user_realname_like = (String) dynaBean.get("js_dist_user_realname_like");
		String js_dist_state = (String) dynaBean.get("js_dist_state");
		String sell_bill_id = (String) dynaBean.get("sell_bill_id");
		String allToExcel = (String) dynaBean.get("allToExcel");

		KonkaXxSellBill entity = new KonkaXxSellBill();

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
				if (temp.getRole_id() == 390) {
					role_id_eq_390 = true;
				}
				if ((temp.getRole_id() >= 300 && temp.getRole_id() < 400)
						|| (temp.getRole_id() >= 30 && temp.getRole_id() < 40)) {
					role_id_btw_300_400 = true;
				}
				if ((temp.getRole_id() >= 200 && temp.getRole_id() < 300) || (temp.getRole_id() <= 30)) {
					role_id_btw_200_300 = true;
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

			request.setAttribute("dept_name", kd.getDept_name());

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

			KonkaDept kd = new KonkaDept();
			kd.setDept_id(getKonkaDeptForFgs(user_info.getDept_id()).getDept_id());
			kd = super.getFacade().getKonkaDeptService().getKonkaDept(kd);

			request.setAttribute("dept_name", kd.getDept_name());

		} else if (role_id_btw_200_300) { // 总部人员
			KonkaXxZmd zmd = new KonkaXxZmd();
			if (GenericValidator.isLong(dept_id)) {
				// 初始化业务员下面专卖店的下拉框
				zmd.setDept_id(Long.valueOf(dept_id));
			}
			List<KonkaXxZmd> zmdList = super.getFacade().getKonkaXxZmdService().getKonkaXxZmdList(zmd);
			request.setAttribute("zmdList", zmdList);
		}
		// 页面角色控制 end

		// 默认结算状态为“已结算”
		js_dist_state = null == js_dist_state ? "1" : js_dist_state;
		dynaBean.set("js_dist_state", js_dist_state);

		// 选择的条件字段
		if (GenericValidator.isLong(dept_id)) {
			entity.setDept_id(Long.valueOf(dept_id));
		}
		if (GenericValidator.isLong(zmd_id)) {
			entity.setZmd_id(Long.valueOf(zmd_id));
		}
		if (StringUtils.isNotBlank(js_dist_date_ge)) {
			entity.getMap().put("js_dist_date_ge", js_dist_date_ge);
		}
		if (StringUtils.isNotBlank(js_dist_date_le)) {
			entity.getMap().put("js_dist_date_le", js_dist_date_le);
		}
		if (StringUtils.isNotBlank(js_dist_user_realname_like)) {
			entity.getMap().put("js_dist_user_realname_like", js_dist_user_realname_like);
		}
		if (GenericValidator.isInt(js_dist_state)) {
			entity.setJs_dist_state(Integer.valueOf(js_dist_state));
		}
		if (GenericValidator.isLong(sell_bill_id)) {
			entity.setSell_bill_id(Long.valueOf(sell_bill_id));
		}
		entity.getMap().put("order_by_dept", true); // 排序dept_id,zmd_id,sell_bill_id升序;

		Long recordCount = super.getFacade().getKonkaXxSellBillService().getKonkaXxSellBillCountForJs(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setCount(pager.getRowCount());
		entity.getRow().setFirst(pager.getFirstRow());

		List<KonkaXxSellBill> entityList = super.getFacade().getKonkaXxSellBillService()
				.getKonkaXxSellBillForJSPaginatedList(entity);

		// 导出全部数据
		if (StringUtils.isNotBlank(allToExcel) && "ebiz".equals(allToExcel)) {
			entity.getRow().setCount(recordCount.intValue());
			entity.getRow().setFirst(0);
			List<KonkaXxSellBill> entityList_all = super.getFacade().getKonkaXxSellBillService()
					.getKonkaXxSellBillForJSPaginatedList(entity);

			BigDecimal total_js_all = new BigDecimal("0"); // 总销售额
			if (null != entityList_all && entityList_all.size() > 0) {
				for (KonkaXxSellBill temp : entityList_all) {
					if (null != temp.getFee_of_post()) {
						total_js_all = total_js_all.add(temp.getFee_of_post());
					}
				}
				request.setAttribute("total_js_all", total_js_all.toString());
			}

			request.setAttribute("entityList_all", entityList_all);
		}

		request.setAttribute("entityList", entityList);

		request.setAttribute("konkaDepts", super.getKonkaDepts());
		dynaBean.set("now_date", DateFormatUtils.format(new Date(), "yyyy-MM-dd"));

		return mapping.findForward("list");

	}
}
