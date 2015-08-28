package com.ebiz.mmt.web.struts.manager.zmd;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
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
import com.ebiz.mmt.domain.KonkaXxZmdQuota;
import com.ebiz.mmt.domain.KonkaXxZmdUsers;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;

/**
 * @author Jiang,JiaYong
 * @version 2012-03-19
 */
public class KonkaXxZmdQuotaAction extends BaseZmdAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		// 取当前登录用户
		PeProdUser ui = super.getSessionUserInfo(request);
		// 取用户角色并判断是不是分公司级别的角色
		List<PeRoleUser> peRoleUserList = getPeRoleList(ui.getId());

		Boolean role_id_gt_400 = false;
		Boolean role_id_eq_400 = false;
		Boolean role_id_btw_300_400 = false;
		if (peRoleUserList.size() > 0) {
			for (PeRoleUser temp : peRoleUserList) {
				if (temp.getRole_id() <= 400) {
					role_id_gt_400 = true;
				}
				if (temp.getRole_id() == 400) {
					role_id_eq_400 = true;
				}
				if (temp.getRole_id() > 300 && temp.getRole_id() <= 400) {
					role_id_btw_300_400 = true;
				}
			}
		}

		if (!role_id_gt_400) {
			String msg = super.getMessage(request, "konka.xx.zmd.role.error");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		request.setAttribute("role_id_eq_400", role_id_eq_400);
		// 分公司员工登陆
		if (role_id_btw_300_400) {
			// 查询专卖店
			KonkaXxZmd zmd = new KonkaXxZmd();

			KonkaDept kDept = getKonkaDeptForFgs(ui.getDept_id());
			if (kDept != null)
				zmd.setDept_id(kDept.getDept_id());
			List<KonkaXxZmd> zmdList = super.getFacade().getKonkaXxZmdService().getKonkaXxZmdList(zmd);

			request.setAttribute("zmdList", zmdList);
		}

		// 专卖店用户
		if (role_id_eq_400) {
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

			request.setAttribute("zmd_id", konkaXxZmdUsersList.get(0).getZmd_id());
		}

		// request.setAttribute("role_id", peRoleUser.getRole_id());
		return mapping.findForward("list");
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String zmd_id = (String) dynaBean.get("zmd_id");
		String quota_date_year = (String) dynaBean.get("quota_date_year");

		if (!GenericValidator.isLong(zmd_id) || !GenericValidator.isLong(quota_date_year)) {
			String msg = super.getMessage(request, "errors.param");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		KonkaXxZmdQuota entity = new KonkaXxZmdQuota();
		entity.getRow().setCount(20);
		entity.setZmd_id(Long.valueOf(zmd_id));
		entity.getMap().put("quota_date_year", quota_date_year);

		// 取当前登录用户
		PeProdUser ui = super.getSessionUserInfo(request);
		// 取用户角色并判断是不是分公司级别的角色
		PeRoleUser peRoleUser = super.getRoleInfoByThisLogin(request);
		if (null == peRoleUser || null == peRoleUser.getRole_id() || peRoleUser.getRole_id() < 300L) {
			String msg = super.getMessage(request, "konka.xx.zmd.role.error");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		// 分公司员工登陆
		if (peRoleUser.getRole_id() < 400L) {
			// 当前分公司
			KonkaDept kDept = getKonkaDeptForFgs(ui.getDept_id());
			if (kDept != null)
				entity.setDept_id(kDept.getDept_id());

			// 查询专卖店
			KonkaXxZmd zmd = new KonkaXxZmd();
			if (kDept != null)
				zmd.setDept_id(kDept.getDept_id());
			List<KonkaXxZmd> zmdList = super.getFacade().getKonkaXxZmdService().getKonkaXxZmdList(zmd);
			request.setAttribute("zmdList", zmdList);
		}

		// 专卖店用户
		if (peRoleUser.getRole_id() >= 400L) {
			// 当前分公司
			entity.setDept_id(ui.getPar_dept_id());

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

			request.setAttribute("zmd_id", konkaXxZmdUsersList.get(0).getZmd_id());
		}

		List<KonkaXxZmdQuota> entityList = super.getFacade().getKonkaXxZmdQuotaService().getKonkaXxZmdQuotaList(entity);

		// 计算实际的销售额和比例
		String year_month = DateFormatUtils.format(new Date(), "yyyyMM");
		for (KonkaXxZmdQuota konkaXxZmdQuota : entityList) {
			this.calculateReqlQuota(konkaXxZmdQuota, year_month);
		}

		request.setAttribute("entityList", entityList);

		request.setAttribute("role_id", peRoleUser.getRole_id());
		return mapping.findForward("list");
	}

	private void calculateReqlQuota(KonkaXxZmdQuota entity, String year_month) throws Exception {
		String quota_month = DateFormatUtils.format(entity.getQuota_date(), "yyyyMM");
		// 如果需要计算的年月大于现在系统的年月，则默认没有销售数据退出
		if (Integer.valueOf(quota_month) > Integer.valueOf(year_month)) {
			return;
		}

		// 计算需要统计的时间
		String add_date_cal_ge = DateFormatUtils.format(entity.getQuota_date(), "yyyy-MM") + "-01 00:00:00";
		String add_date_cal_le = "";
		if ("12".equals(StringUtils.substring(quota_month, 4, 6))) {
			add_date_cal_le = String.valueOf(Integer.valueOf(StringUtils.substring(quota_month, 0, 4)) + 1)
					+ "-01-01 00:00:00";
		} else {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHH:mm:ss");
			add_date_cal_le = DateFormatUtils.format(sdf.parse(String.valueOf(Integer.valueOf(quota_month) + 1)
					+ "0100:00:00"), "yyyy-MM-dd HH:mm:ss");
		}

		// 缺省实际完成额度和完成比例则计算
		if (null == entity.getReal_quota_value() || StringUtils.isBlank(entity.getCompletion_rate())) {
			KonkaXxSellBill ksb = new KonkaXxSellBill();
			ksb.setDept_id(entity.getDept_id());
			ksb.setZmd_id(entity.getZmd_id());
			ksb.getMap().put("sell_state_ge", "30");
			ksb.getMap().put("add_date_cal_ge", add_date_cal_ge);
			ksb.getMap().put("add_date_cal_le", add_date_cal_le);
			String sellMoneySum = super.getFacade().getKonkaXxSellBillService().getKonkaXxSellBillTotalMoneySum(ksb);
			if (null == sellMoneySum) {
				sellMoneySum = "0";
			}

			// 实际销售额（万元）
			entity.setReal_quota_value(new BigDecimal(sellMoneySum).divide(new BigDecimal(10000), 4,
					BigDecimal.ROUND_HALF_UP));
			// 完成比例
			entity.setCompletion_rate(entity.getReal_quota_value().divide(entity.getQuota_value(), 4,
					BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP)
					.toString());

			// 不是当前月，则保存计算结果
			if (!quota_month.equals(year_month)) {
				super.getFacade().getKonkaXxZmdQuotaService().modifyKonkaXxZmdQuota(entity);
			}
		}
	}
}
