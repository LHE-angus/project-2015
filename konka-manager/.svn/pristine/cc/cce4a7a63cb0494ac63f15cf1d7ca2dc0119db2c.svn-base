package com.ebiz.mmt.web.struts.manager.admin;

import java.text.SimpleDateFormat;
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

import com.ebiz.mmt.domain.KonkaR3DeptStockInfo;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Hu,Hao
 * @version 2013-12-12
 */
public class KonkaR3DeptStockInfoAction extends BaseAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");

		// 分公司名称:
		String v_class_like = (String) dynaBean.get("v_class_like");

		// 机型(*):
		String v_matnr_like = (String) dynaBean.get("v_matnr_like");

		// 物料组
		String v_matkl = (String) dynaBean.get("v_matkl");

		// 销售片区:
		String v_bzirk = (String) dynaBean.get("v_bzirk");

		String year = (String) dynaBean.get("year");
		String month = (String) dynaBean.get("month");

		// 取当前时间
		SimpleDateFormat formaty = new SimpleDateFormat("yyyy");
		SimpleDateFormat formatm = new SimpleDateFormat("MM");

		KonkaR3DeptStockInfo entity = new KonkaR3DeptStockInfo();

		Date now = new Date();

		String this_year = null;// 当前年份
		String this_month = null;// 当前月份
		if (StringUtils.isNotBlank(year) && StringUtils.isNotBlank(month)) {
			this_year = year;
			this_month = month;
		} else {
			this_year = formaty.format(now);// 当前年份
			this_month = formatm.format(now);// 当前月份
		}

		entity.getMap().put("s_date", this_year + "-" + this_month + "-01 00:00:00");
		entity.getMap().put(
				"e_date",
				this_year + "-" + this_month + "-" + getMaxDay(Integer.valueOf(this_month), Integer.valueOf(this_year))
						+ " 23:59:59");

		if (StringUtils.isNotBlank(v_class_like))
			entity.getMap().put("v_class_like", v_class_like);
		if (StringUtils.isNotBlank(v_matnr_like))
			entity.getMap().put("v_matnr_like", v_matnr_like);
		if (StringUtils.isNotBlank(v_matkl))
			entity.setMatkl(v_matkl);
		if (StringUtils.isNotBlank(v_bzirk))
			entity.setBzirk(v_bzirk);

		entity.getMap().put("order_by_lfimg1", true);

		Long recordCount = super.getFacade().getKonkaR3DeptStockInfoService().getKonkaR3DeptStockInfoCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setCount(pager.getRowCount());
		entity.getRow().setFirst(pager.getFirstRow());
		List<KonkaR3DeptStockInfo> entityList = super.getFacade().getKonkaR3DeptStockInfoService()
				.getKonkaR3DeptStockInfoPaginatedList(entity);

		request.setAttribute("entityList", entityList);

		dynaBean.set("month", this_month);
		dynaBean.set("year", this_year);

		// 遍历年份，取前10年
		ArrayList<String> yearList = new ArrayList<String>();

		for (int y = 0; y < 10; y++) {
			yearList.add((Integer.valueOf(formaty.format(now)) - y) + "");
		}

		request.setAttribute("yearList", yearList);

		return mapping.findForward("list");
	}

	public int getMaxDay(int mm, int year) {
		int day = 0;
		if (mm == 1 || mm == 3 || mm == 5 || mm == 7 || mm == 8 || mm == 10 || mm == 12) {
			day = 31;
		} else if (mm == 2) {
			if (year % 4 == 0) {
				day = 29;
			} else {
				day = 28;
			}
		} else {
			day = 30;
		}
		return day;

	}
}
