package com.ebiz.mmt.web.struts.manager.admin;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import com.ebiz.mmt.domain.KonkaMobileSailData;
import com.ebiz.mmt.domain.KonkaStoreTaskFinishReport;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

public class KonkaMobileSailDataStoreTaskFinishAction extends BaseAction {

	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		setNaviStringToRequestScope(form, request);
		PeProdUser userInfo = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
		if (null == userInfo) {
			String msg = super.getMessage(request, "user.session.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}
		KonkaDept konkaDept = new KonkaDept();
		konkaDept.setDept_type(3);
		// 多媒体事业本部 0 系统管理员
		if (userInfo.getDept_id() != 0) {
			konkaDept.setDept_id(userInfo.getDept_id());
		}
		List<KonkaDept> deptList = super.getFacade().getKonkaDeptService().getKonkaDeptList(konkaDept);
		request.setAttribute("deptList", deptList);

		// 遍历年份，取前10年
		ArrayList<String> yearList = new ArrayList<String>();
		SimpleDateFormat formaty = new SimpleDateFormat("yyyy");

		for (int y = 0; y < 10; y++) {
			yearList.add((Integer.valueOf(formaty.format(new Date())) - y) + "");
		}
		request.setAttribute("yearList", yearList);


		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		pager.init(0l, 20, "0");
		dynaBean.set("pager", pager);

		// not null
		String year1 = yearList.get(0);// 2014
		int month1 = new Date().getMonth() + 1;// 02
		String year2 = yearList.get(0);// 2014
		int month2 = new Date().getMonth() + 1;// 02
		// request.
		dynaBean.set("year1", year1);
		dynaBean.set("month1", month1 < 10 ? "0" + month1 : month1);
		dynaBean.set("year2", year2);
		dynaBean.set("month2", month2 < 10 ? "0" + month2 : month2);
		
		return mapping.findForward("list");
	}

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		setNaviStringToRequestScope(form, request);
		PeProdUser userInfo = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
		if (null == userInfo) {
			String msg = super.getMessage(request, "user.session.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		// 分公司数据过滤??
		KonkaDept konkaDept = new KonkaDept();
		konkaDept.setDept_type(3);
		// 多媒体事业本部 0 系统管理员
		if (userInfo.getDept_id() != 0) {
			konkaDept.setDept_id(userInfo.getDept_id());
		}
		List<KonkaDept> deptList = super.getFacade().getKonkaDeptService().getKonkaDeptList(konkaDept);
		request.setAttribute("deptList", deptList);

		// 遍历年份，取前10年
		ArrayList<String> yearList = new ArrayList<String>();
		SimpleDateFormat formaty = new SimpleDateFormat("yyyy");

		for (int y = 0; y < 10; y++) {
			yearList.add((Integer.valueOf(formaty.format(new Date())) - y) + "");
		}
		request.setAttribute("yearList", yearList);

		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");

		// options

		String dept_id = (String) dynaBean.get("dept_id");
		String store_name = (String) dynaBean.get("store_name");
		String store_type = (String) dynaBean.get("store_type");

		// not null
		String year1 = (String) dynaBean.get("year1");// 2014
		String month1 = (String) dynaBean.get("month1");// 02
		String year2 = (String) dynaBean.get("year2");// 2014
		String month2 = (String) dynaBean.get("month2");// 03

		KonkaMobileSailData kd = new KonkaMobileSailData();

		Calendar c1 = Calendar.getInstance();
		c1.set(Calendar.YEAR, Integer.valueOf(year1));
		c1.set(Calendar.MONTH, Integer.valueOf(month1) - 1);

		Calendar c2 = Calendar.getInstance();
		c2.set(Calendar.YEAR, Integer.valueOf(year2));
		c2.set(Calendar.MONTH, Integer.valueOf(month2) - 1);
		// 虚拟一天
		c2.set(Calendar.DATE, 1);
		int s = c1.getActualMinimum(Calendar.DAY_OF_MONTH);
		int e = c2.getActualMaximum(Calendar.DAY_OF_MONTH);

		String s_date = "";
		String e_date = "";
		if (s < 10) {
			s_date = year1 + "-" + month1 + "-" + "0" + s;
		} else {
			s_date = year1 + "-" + month1 + "-" + s;
		}
		if (e < 10) {
			e_date = year2 + "-" + month2 + "-" + "0" + e;
		} else {
			e_date = year2 + "-" + month2 + "-" + e;
		}

		kd.getMap().put("dept_id", dept_id);

		if (StringUtils.isNotEmpty(store_name)) {
			kd.getMap().put("store_name", store_name.trim());
		}
		kd.getMap().put("store_type", store_type);
		kd.getMap().put("date_begin", s_date + " 00:00:01");
		kd.getMap().put("date_end", e_date + " 23:59:59");


		kd.getMap().put("year1", year1);
		kd.getMap().put("month1", month1);
		kd.getMap().put("year2", year2);
		kd.getMap().put("month2", month2);

		Long recordCount = super.getFacade().getKonkaMobileSailDataService().getKonkaStoreTaskFinishReportForCount(kd);
		pager.init(recordCount, 20, pager.getRequestPage());
		kd.getRow().setFirst(pager.getFirstRow());
		kd.getRow().setCount(pager.getRowCount());

		List<KonkaStoreTaskFinishReport> list = super.getFacade().getKonkaMobileSailDataService()
				.getKonkaStoreTaskFinishReport(kd);

		request.setAttribute("entityList", list);

		return mapping.findForward("list");

	}

}
