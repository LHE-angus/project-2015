package com.ebiz.mmt.web.struts.manager.admin;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
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
import com.ebiz.mmt.domain.ImportDataTypes;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaR3Backmoney;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.domain.TaskPara;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * 客户管理 > 渠道统计分析 > 客户回款统计
 * 
 * @author Gao YongXiang
 */
public class ChannelCustomerPaymentAnalysisAction extends BaseAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}

		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);

		Pager pager = (Pager) dynaBean.get("pager");
		String customer_type = (String) dynaBean.get("customer_type");
		String key = (String) dynaBean.get("keyword");
		String handle_name = (String) dynaBean.get("handle_name");
		String branch_area_name_link = (String) dynaBean.get("branch_area_name_link");
		String branch_area_name = (String) dynaBean.get("branch_area_name_select");

		ImportDataTypes importDataTypes = new ImportDataTypes();
		importDataTypes.setPar_id(0);
		List<ImportDataTypes> idtList = super.getFacade().getImportDataTypesService()
				.getImportDataTypesList(importDataTypes);
		request.setAttribute("idtList", idtList);

		if (null == customer_type) {
			customer_type = "1";
		}
		dynaBean.set("customer_type", customer_type);

		PeProdUser userInfo = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);

		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(userInfo.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);

		boolean role_id_ge_30 = false;
		boolean role_id_ge_20 = false;
		boolean role_id_eq_60 = false;
		boolean role_id_ge_10_lt_30 = false;
		for (PeRoleUser peRoleUser : peRoleUserList) {
			if (peRoleUser.getRole_id() >= 30L) {
				role_id_ge_30 = true;
			}
			if (peRoleUser.getRole_id() >= 20L) {
				role_id_ge_20 = true;
			}
			if (peRoleUser.getRole_id() == 60L) {
				role_id_eq_60 = true;
			}
			if (peRoleUser.getRole_id() >= 10L && peRoleUser.getRole_id() < 30L) {
				role_id_ge_10_lt_30 = true;
			}
		}

		KonkaR3Shop r3Shop = new KonkaR3Shop();
		r3Shop.setIs_del(0L);
		if (role_id_ge_30) {
//			KonkaDept dept = new KonkaDept();
//			dept.setDept_id(super.getSessionUserInfo(request).getDept_id());
//			dept = getFacade().getKonkaDeptService().getKonkaDept(dept);

			KonkaDept fgs = super.getSuperiorForDeptType(userInfo.getDept_id(), 3);
//			if (null == dept.getDept_type()) {
//				
//			} else if (dept.getDept_type() == 3) {// 分公司
//				r3Shop.setBranch_area_name(dept.getDept_name());
//				dynaBean.set("branch_area_name_select", dept.getDept_name());
//				dynaBean.set("branch_area_name_link", dept.getDept_name());
//			} else if (dept.getDept_type() == 4 || dept.getDept_type() == 5) {// 经营部
				r3Shop.setBranch_area_name(fgs.getDept_name());
				dynaBean.set("branch_area_name_select", fgs.getDept_name());
				dynaBean.set("branch_area_name_link", fgs.getDept_name());
//			}
		}
		List<KonkaR3Shop> BranchList = getFacade().getKonkaR3ShopService().getKonkaR3ShopGroupByBranchAreaName(r3Shop);

		request.setAttribute("BranchList", BranchList);

		List<String> namelist = new ArrayList<String>();
		if ("1".equals(customer_type)) {
			KonkaR3Shop r3 = new KonkaR3Shop();
			if (StringUtils.isNotBlank(key)) {
				r3.getMap().put("r3_code_like", key);
			}
			if (role_id_ge_20) { // 不是系统管理员组全部要加上网点分配
				r3.getMap().put("dept_id", this.getSessionUserInfo(request).getDept_id());
				r3.getMap().put("user_id", this.getSessionUserInfo(request).getId());
			}
			if (role_id_eq_60) { // 业务员只能看到分配给自己的
				r3.getMap().put("dept_id", -1);
				r3.getMap().put("user_id", this.getSessionUserInfo(request).getId());
			}
			Long recordCount = getFacade().getKonkaR3ShopService().getKonkaR3ShopCount(r3);
			pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
			r3.getRow().setFirst(pager.getFirstRow());
			r3.getRow().setCount(pager.getRowCount());
			List<KonkaR3Shop> entityList = super.getFacade().getKonkaR3ShopService().getKonkaR3ShopPaginatedList(r3);

			for (KonkaR3Shop t : entityList) {
				namelist.add(t.getCustomer_name());
			}
		}
		// else if ("2".equals(customer_type)) {
		// ImportDataTypes idt = new ImportDataTypes();
		// if (StringUtils.isNotBlank(key)) {
		// idt.getMap().put("type_name_like", key);
		// }
		// idt.setPar_id( 10);
		// idt.setData_type(Long.valueOf(type));
		// Long recordCount =
		// getFacade().getImportDataTypesService().getImportDataTypesCount(idt);
		// pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		// idt.getRow().setFirst(pager.getFirstRow());
		// idt.getRow().setCount(pager.getRowCount());
		// List<ImportDataTypes> entityList =
		// super.getFacade().getImportDataTypesService().getImportDataTypesPaginatedList(idt);
		//
		// for(ImportDataTypes t : entityList){
		// namelist.add(t.getType_name());
		// }
		// }
		else if ("3".equals(customer_type)) {
			KonkaDept dept = new KonkaDept();
			if (StringUtils.isNotBlank(branch_area_name)) {
				dept.getMap().put("dept_name_like", branch_area_name);
				dynaBean.set("branch_area_name_select", branch_area_name);
			}
			if (role_id_ge_10_lt_30) {
				dept.setPar_id(0L);
				Long recordCount = getFacade().getKonkaDeptService().getKonkaDeptCount(dept);
				pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
				dept.getRow().setFirst(pager.getFirstRow());
				dept.getRow().setCount(pager.getRowCount());
				List<KonkaDept> entityList = super.getFacade().getKonkaDeptService().getKonkaDeptPaginatedList(dept);

				for (KonkaDept t : entityList) {
					namelist.add(t.getDept_name());
				}
			} else if (role_id_ge_30) {
				namelist.add(super.getSuperiorForDeptType(super.getSessionUserInfo(request).getDept_id(), 3)
						.getDept_name());
			}
		}
		// else if ("4".equals(customer_type)) {
		// ImportDataTypes idt = new ImportDataTypes();
		// if (StringUtils.isNotBlank(key)) {
		// idt.getMap().put("type_name_like", key);
		// }
		// idt.setPar_id(80);
		// idt.setData_type(Long.valueOf(type));
		//
		// Long recordCount =
		// getFacade().getImportDataTypesService().getImportDataTypesCount(idt);
		// pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		// idt.getRow().setFirst(pager.getFirstRow());
		// idt.getRow().setCount(pager.getRowCount());
		// List<ImportDataTypes> entityList =
		// super.getFacade().getImportDataTypesService().getImportDataTypesPaginatedList(idt);
		//
		// for(ImportDataTypes t : entityList){
		// namelist.add(t.getType_name());
		// }
		// }
		else if ("5".equals(customer_type)) {
			KonkaR3Shop r3 = new KonkaR3Shop();
			r3.setIs_del(0L);
			if (StringUtils.isNotBlank(branch_area_name_link)) {
				r3.setBranch_area_name(branch_area_name_link);
				dynaBean.set("branch_area_name_link", branch_area_name_link);
			}
			if (StringUtils.isNotBlank(handle_name)) {
				r3.setHandle_name(handle_name);
				dynaBean.set("handle_name", handle_name);
			}
			List<KonkaR3Shop> handleList = getFacade().getKonkaR3ShopService().getKonkaR3ShopGroupByHandleName(r3);
			for (KonkaR3Shop t : handleList) {
				namelist.add(t.getHandle_name());
			}
		}

		// 下面的代码是xxd 20130705 修改，处理 namelist 方法
		// "单客户","客户群","分公司","分大区"
		if (namelist.size() != 0) {
			String column_name = "";
			// "单客户","客户群","分公司","分大区"
			switch (Integer.parseInt(customer_type)) {
			case 1:
				column_name = "customer_name";
				break;
			case 2:
				column_name = "customer_type";
				break;
			case 3:
				column_name = "branch_area_name";
				break;
			case 4:
				column_name = "area_name";
				break;
			case 5:
				column_name = "handle_name";
				break;
			default:
				break;
			}
			List<Map<String, Double[]>> values_list = new ArrayList<Map<String, Double[]>>();
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

			for (int i = 1; i < 13; i++) {
				Map<String, Double[]> value_map = null;
				// 12种柱状图
				switch (i) {
				case 1:
					value_map = chart1(namelist);
					break;
				case 2:
					value_map = chart2(column_name, namelist);
					break;
				case 3:
					value_map = chart3(column_name, namelist);
					break;
				case 4:
					value_map = chart4(namelist);
					break;
				case 5:
					value_map = chart5(column_name, namelist);
					break;
				case 6:
					value_map = chart6(column_name, namelist);
					break;
				case 7:
					value_map = chart7(namelist);
					break;
				case 8:
					value_map = chart8(column_name, namelist);
					break;
				case 9:
					value_map = chart9(column_name, namelist);
					break;
				case 10:
					value_map = chart10(namelist);
					break;
				case 11:
					value_map = chart11(column_name, namelist);
					break;
				case 12:
					value_map = chart12(column_name, namelist);
					break;
				default:
					break;
				}
				values_list.add(value_map);
			}

			for (int i = 0; i < namelist.size(); i++) {
				Map<String, Object> p_map = new HashMap<String, Object>();
				for (int j = 0; j < 12; j++) {
					String chart_paras = "";
					Map<String, Double[]> map = values_list.get(j);
					Double value_1[] = map.get("values_1");
					Double value_2[] = map.get("values_2");
					p_map.put("value_1_" + j, value_1[i]);
					p_map.put("value_2_" + j, value_2[i]);
					if (j == 2 || j == 5 || j == 8 || j == 11)
						chart_paras = "%";
					p_map.put("per_" + j, chart_paras);
				}
				p_map.put("task_name", namelist.get(i));
				list.add(p_map);
			}

			request.setAttribute("valueList", list);
		}
		// 处理 namelist 方法 end.

		return mapping.findForward("list");
	}

	public ActionForward chart(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}
		setNaviStringToRequestScope(form, request);
		super.getModPopeDom(form, request);

		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);

		return new ActionForward("/admin/ChannelCustomerPaymentAnalysis/chart.jsp");
	}

	@SuppressWarnings("unchecked")
	public ActionForward column3D(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);
		// "单客户","客户群","分公司","分大区"
		String customer_type = (String) dynaBean.get("customer_type");
		// 12种柱状图
		String chart_type = (String) dynaBean.get("chart_type");
		// 12种柱状图标题
		String[] chartTitles = new String[] { "当月回款任务", "当月回款额", "当月回款完成率", "累计回款任务", "累计回款额", "累计回款完成率", "去年同期任务",
				"去年同期回款", "同比去年同期增长率", "去年同期累计任务", "去年同期累计回款", "同比去年累计增长率" };
		List<String> namelist = (List<String>) request.getSession().getAttribute("namelist");

		String chartStr = "";
		String column_name = "";
		// "单客户","客户群","分公司","分大区"

		if (namelist.size() != 0) {
			switch (Integer.parseInt(customer_type)) {
			case 1:
				column_name = "customer_name";
				break;
			case 2:
				column_name = "customer_type";
				break;
			case 3:
				column_name = "branch_area_name";
				break;
			case 4:
				column_name = "area_name";
				break;
			case 5:
				column_name = "handle_name";
				break;
			default:
				break;
			}

			Map<String, Double[]> value_map = null;
			String chart_paras = "";
			// 12种柱状图
			switch (Integer.parseInt(chart_type)) {
			case 1:
				value_map = chart1(namelist);
				break;
			case 2:
				value_map = chart2(column_name, namelist);
				break;
			case 3:
				chart_paras = "numberSuffix='%'";
				value_map = chart3(column_name, namelist);
				break;
			case 4:
				value_map = chart4(namelist);
				break;
			case 5:
				value_map = chart5(column_name, namelist);
				break;
			case 6:
				chart_paras = "numberSuffix='%'";
				value_map = chart6(column_name, namelist);
				break;
			case 7:
				value_map = chart7(namelist);
				break;
			case 8:
				value_map = chart8(column_name, namelist);
				break;
			case 9:
				chart_paras = "numberSuffix='%'";
				value_map = chart9(column_name, namelist);
				break;
			case 10:
				value_map = chart10(namelist);
				break;
			case 11:
				value_map = chart11(column_name, namelist);
				break;
			case 12:
				chart_paras = "numberSuffix='%'";
				value_map = chart12(column_name, namelist);
				break;
			default:
				break;
			}
			Double value_1[] = value_map.get("values_1");
			Double value_2[] = value_map.get("values_2");
			chartStr = commonChart(chartTitles[Integer.parseInt(chart_type) - 1], chart_paras, namelist, value_1,
					value_2);

			OutputStream out = response.getOutputStream();
			out.write(chartStr.getBytes("UTF-8"));
			out.flush();
		}
		return null;
	}

	public String commonChart(String chart_title, String chart_paras, List<String> nameList, Double values_1[],
			Double values_2[]) {
		StringBuffer resultXML = new StringBuffer();
		resultXML.append("<?xml version='1.0' encoding='UTF-8'?><chart caption='");
		resultXML.append(chart_title); // 标题
		resultXML.append("' subcaption='' ");
		resultXML
				.append(chart_paras)
				.append("xAxisName='' decimalPrecision ='2' showBorder='1' yAxisName='' numberPrefix='' baseFontColor='666666' showValues='1' formatNumberScale='0' baseFont='宋体' BaseFontSize ='12' showNames='0' labelDisplay='Stagger' staggerLines='3'>");

		for (int i = 0; i < nameList.size(); i++) {
			resultXML.append("<set value=\"");
			resultXML.append(values_1[i] / values_2[i]); // value
			resultXML.append("\" ");
			resultXML.append("label=\"");
			resultXML.append(nameList.get(i)); // label
			resultXML.append("\" ");
			resultXML.append("link=\""); // link
			resultXML.append("\"/> ");
		}

		resultXML.append("</chart>");
		// //System.out.println("resultXML:=" + resultXML.toString());
		return resultXML.toString();
	}

	/*
	 * 当月回款任务
	 */
	public Map<String, Double[]> chart1(List<String> nameList) throws Exception {

		// 初始化，取指定年12个月的任务系数
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		int month = now.get(Calendar.MONTH) + 1;
		TaskPara tp = new TaskPara();

		String name_String = "";
		for (int i = 0; i < nameList.size(); i++) {
			name_String = name_String + "'" + nameList.get(i) + "',";
		}
		name_String = StringUtils.removeEnd(name_String, ",");

		tp.getMap().put("column_name", "task_name");
		tp.getMap().put("shop_name_String", name_String);
		tp.setYear(year);
		tp.getMap().put("column_m", month);

		List<TaskPara> list = super.getFacade().getTaskParaService().getTaskParaList(tp);
		Double values_1[] = new Double[nameList.size()];
		Double values_tmp[] = new Double[nameList.size()];
		Map<String, Integer> map = new HashMap<String, Integer>();
		for (int i = 0; i < nameList.size(); i++) {
			map.put(nameList.get(i), i);
			values_1[i] = 0.0;
			values_tmp[i] = 1.0;
		}

		for (int i = 0; i < list.size(); i++) {
			TaskPara tap = list.get(i);
			if (tap.getMap().get("column_m") != null) {
				values_1[map.get(tap.getTask_name())] += Double.valueOf(tap.getMap().get("column_m").toString());
			}
		}

		Map<String, Double[]> value_map = new HashMap<String, Double[]>();
		value_map.put("values_1", values_1);
		value_map.put("values_2", values_tmp);
		return value_map;
	}

	/*
	 * 当月回款额
	 */
	public Map<String, Double[]> chart2(String column_name, List<String> nameList) throws Exception {
		// 初始化，取指定年12个月的销售数据
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		int month = now.get(Calendar.MONTH) + 1;
		KonkaR3Backmoney kr3m = new KonkaR3Backmoney();

		String name_String = "";
		for (int i = 0; i < nameList.size(); i++) {
			name_String = name_String + "'" + nameList.get(i) + "',";
		}
		name_String = StringUtils.removeEnd(name_String, ",");

		kr3m.getMap().put("shop_name_String", name_String);
		kr3m.setYear(Long.valueOf(year));
		kr3m.getMap().put("column_name", column_name);
		kr3m.getMap().put("column_m", month);

		List<KonkaR3Backmoney> list = super.getFacade().getKonkaR3BackmoneyService().getKonkaR3BackmoneyList(kr3m);

		Double values_1[] = new Double[nameList.size()];
		Double values_tmp[] = new Double[nameList.size()];
		Map<String, Integer> map = new HashMap<String, Integer>();
		for (int i = 0; i < nameList.size(); i++) {
			map.put(nameList.get(i), i);
			values_1[i] = 0.0;
			values_tmp[i] = 1.0;
		}
		if ("customer_name".equals(column_name)) {
			for (int i = 0; i < list.size(); i++) {
				KonkaR3Backmoney r3 = list.get(i);
				values_1[map.get(r3.getCustomer_name())] += Double.valueOf(r3.getMap().get("column_m").toString());
			}
		} else if ("customer_type".equals(column_name)) {
			for (int i = 0; i < list.size(); i++) {
				KonkaR3Backmoney r3 = list.get(i);
				values_1[map.get(r3.getCustomer_type())] += Double.valueOf(r3.getMap().get("column_m").toString());
			}
		} else if ("branch_area_name".equals(column_name)) {
			for (int i = 0; i < list.size(); i++) {
				KonkaR3Backmoney r3 = list.get(i);
				values_1[map.get(r3.getBranch_area_name())] += Double.valueOf(r3.getMap().get("column_m").toString());
			}
		} else if ("area_name".equals(column_name)) {
			for (int i = 0; i < list.size(); i++) {
				KonkaR3Backmoney r3 = list.get(i);
				values_1[map.get(r3.getArea_name())] += Double.valueOf(r3.getMap().get("column_m").toString());
			}
		}
		Map<String, Double[]> value_map = new HashMap<String, Double[]>();
		value_map.put("values_1", values_1);
		value_map.put("values_2", values_tmp);
		return value_map;
	}

	/*
	 * 当月回款完成率
	 */
	public Map<String, Double[]> chart3(String column_name, List<String> nameList) throws Exception {
		// 取当前年月
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		int month = now.get(Calendar.MONTH) + 1;
		String name_String = "";
		for (int i = 0; i < nameList.size(); i++) {
			name_String = name_String + "'" + nameList.get(i) + "',";
		}
		name_String = StringUtils.removeEnd(name_String, ",");

		KonkaR3Backmoney kr3m = new KonkaR3Backmoney();
		kr3m.getMap().put("column_name", column_name);
		kr3m.getMap().put("shop_name_String", name_String);
		kr3m.setYear(Long.valueOf(year));
		kr3m.getMap().put("column_m", month);

		List<KonkaR3Backmoney> list1 = super.getFacade().getKonkaR3BackmoneyService().getKonkaR3BackmoneyList(kr3m);

		TaskPara tp = new TaskPara();
		tp.getMap().put("column_name", "task_name");
		tp.getMap().put("shop_name_String", name_String);
		tp.setYear(year);
		tp.getMap().put("column_m", month);
		List<TaskPara> list2 = super.getFacade().getTaskParaService().getTaskParaList(tp);

		// 回款额
		Double values_1[] = new Double[nameList.size()];
		// 回款任务金额
		Double values_2[] = new Double[nameList.size()];
		Map<String, Integer> map = new HashMap<String, Integer>();
		for (int i = 0; i < nameList.size(); i++) {
			map.put(nameList.get(i), i);
			values_1[i] = 0.0;
			values_2[i] = 0.0;
		}

		for (int i = 0; i < list2.size(); i++) {
			TaskPara tk = list2.get(i);
			values_2[map.get(tk.getTask_name())] += Double.valueOf(tk.getMap().get("column_m").toString());
		}

		if ("customer_name".equals(column_name)) {
			for (int i = 0; i < list1.size(); i++) {
				KonkaR3Backmoney r3 = list1.get(i);
				values_1[map.get(r3.getCustomer_name())] += Double.valueOf(r3.getMap().get("column_m").toString());
			}
		} else if ("customer_type".equals(column_name)) {
			for (int i = 0; i < list1.size(); i++) {
				KonkaR3Backmoney r3 = list1.get(i);
				values_1[map.get(r3.getCustomer_type())] += Double.valueOf(r3.getMap().get("column_m").toString());
			}
		} else if ("branch_area_name".equals(column_name)) {
			for (int i = 0; i < list1.size(); i++) {
				KonkaR3Backmoney r3 = list1.get(i);
				values_1[map.get(r3.getBranch_area_name())] += Double.valueOf(r3.getMap().get("column_m").toString());
			}
		} else if ("area_name".equals(column_name)) {
			for (int i = 0; i < list1.size(); i++) {
				KonkaR3Backmoney r3 = list1.get(i);
				values_1[map.get(r3.getArea_name())] += Double.valueOf(r3.getMap().get("column_m").toString());
			}
		}

		// 当月比上月增长值
		for (int i = 0; i < nameList.size(); i++) {
			values_1[i] = values_1[i] * 100;
		}
		Map<String, Double[]> value_map = new HashMap<String, Double[]>();
		value_map.put("values_1", values_1);
		value_map.put("values_2", values_2);

		return value_map;
	}

	// 累计回款任务

	public Map<String, Double[]> chart4(List<String> nameList) throws Exception {

		// 初始化，取指定年12个月的数据
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		TaskPara tp = new TaskPara();

		String name_String = "";
		for (int i = 0; i < nameList.size(); i++) {
			name_String = name_String + "'" + nameList.get(i) + "',";
		}
		name_String = StringUtils.removeEnd(name_String, ",");
		tp.getMap().put("shop_name_String", name_String);

		tp.setYear(year);
		tp.getMap().put("column_name", "task_name");
		String column_y = "";
		for (int i = 1; i < 13; i++) {
			column_y = column_y + "decode(column_" + i + ",null,0,column_" + i + ")+";
		}
		column_y = StringUtils.removeEnd(column_y, "+");

		tp.getMap().put("column_y", column_y);

		List<TaskPara> list = super.getFacade().getTaskParaService().getTaskParaList(tp);
		Double values_1[] = new Double[nameList.size()];
		Double values_tmp[] = new Double[nameList.size()];
		Map<String, Integer> map = new HashMap<String, Integer>();
		for (int i = 0; i < nameList.size(); i++) {
			map.put(nameList.get(i), i);
			values_1[i] = 0.0;
			values_tmp[i] = 1.0;
		}

		for (int i = 0; i < list.size(); i++) {
			TaskPara entiy = list.get(i);
			values_1[map.get(entiy.getTask_name())] += Double.valueOf(entiy.getMap().get("column_y").toString());
		}

		Map<String, Double[]> value_map = new HashMap<String, Double[]>();
		value_map.put("values_1", values_1);
		value_map.put("values_2", values_tmp);
		return value_map;
	}

	/*
	 * 累计回款额
	 */
	public Map<String, Double[]> chart5(String column_name, List<String> nameList) throws Exception {

		// 取当前年月
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		KonkaR3Backmoney kr3m = new KonkaR3Backmoney();

		String name_String = "";
		for (int i = 0; i < nameList.size(); i++) {
			name_String = name_String + "'" + nameList.get(i) + "',";
		}
		name_String = StringUtils.removeEnd(name_String, ",");
		kr3m.getMap().put("shop_name_String", name_String);

		kr3m.setYear(Long.valueOf(year));
		kr3m.getMap().put("column_name", column_name);
		String column_y = "";
		for (int i = 1; i < 13; i++) {
			column_y = column_y + "decode(column_" + i + ",null,0,column_" + i + ")+";
		}
		column_y = StringUtils.removeEnd(column_y, "+");

		kr3m.getMap().put("column_y", column_y);

		List<KonkaR3Backmoney> list = super.getFacade().getKonkaR3BackmoneyService().getKonkaR3BackmoneyList(kr3m);
		Double values_1[] = new Double[nameList.size()];
		Double values_tmp[] = new Double[nameList.size()];
		Map<String, Integer> map = new HashMap<String, Integer>();
		for (int i = 0; i < nameList.size(); i++) {
			map.put(nameList.get(i), i);
			values_1[i] = 0.0;
			values_tmp[i] = 1.0;
		}
		if ("customer_name".equals(column_name)) {
			for (int i = 0; i < list.size(); i++) {
				KonkaR3Backmoney r3 = list.get(i);
				values_1[map.get(r3.getCustomer_name())] += Double.valueOf(r3.getMap().get("column_y").toString());
			}
		} else if ("customer_type".equals(column_name)) {
			for (int i = 0; i < list.size(); i++) {
				KonkaR3Backmoney r3 = list.get(i);
				values_1[map.get(r3.getCustomer_type())] += Double.valueOf(r3.getMap().get("column_y").toString());
			}
		} else if ("branch_area_name".equals(column_name)) {
			for (int i = 0; i < list.size(); i++) {
				KonkaR3Backmoney r3 = list.get(i);
				values_1[map.get(r3.getBranch_area_name())] += Double.valueOf(r3.getMap().get("column_y").toString());
			}
		} else if ("area_name".equals(column_name)) {
			for (int i = 0; i < list.size(); i++) {
				KonkaR3Backmoney r3 = list.get(i);
				values_1[map.get(r3.getArea_name())] += Double.valueOf(r3.getMap().get("column_y").toString());
			}
		}
		Map<String, Double[]> value_map = new HashMap<String, Double[]>();
		value_map.put("values_1", values_1);
		value_map.put("values_2", values_tmp);
		return value_map;
	}

	// 累计回款完成率

	public Map<String, Double[]> chart6(String column_name, List<String> nameList) throws Exception {

		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		String name_String = "";
		for (int i = 0; i < nameList.size(); i++) {
			name_String = name_String + "'" + nameList.get(i) + "',";
		}
		name_String = StringUtils.removeEnd(name_String, ",");

		KonkaR3Backmoney kr3m = new KonkaR3Backmoney();
		kr3m.getMap().put("column_name", column_name);
		kr3m.getMap().put("shop_name_String", name_String);
		kr3m.setYear(Long.valueOf(year));

		TaskPara tp = new TaskPara();
		tp.getMap().put("column_name", "task_name");
		tp.getMap().put("shop_name_String", name_String);
		tp.setYear(year);

		String column_y = "";
		for (int i = 1; i < 13; i++) {
			column_y = column_y + "decode(column_" + i + ",null,0,column_" + i + ")+";
		}
		column_y = StringUtils.removeEnd(column_y, "+");

		kr3m.getMap().put("column_y", column_y);

		List<KonkaR3Backmoney> list1 = super.getFacade().getKonkaR3BackmoneyService().getKonkaR3BackmoneyList(kr3m);

		tp.getMap().put("column_y", column_y);

		List<TaskPara> list2 = super.getFacade().getTaskParaService().getTaskParaList(tp);

		// 回款额
		Double values_1[] = new Double[nameList.size()];
		// 回款任务金额
		Double values_2[] = new Double[nameList.size()];
		Map<String, Integer> map = new HashMap<String, Integer>();
		for (int i = 0; i < nameList.size(); i++) {
			map.put(nameList.get(i), i);
			values_1[i] = 0.0;
			values_2[i] = 0.0;
		}

		for (int i = 0; i < list2.size(); i++) {
			TaskPara tk = list2.get(i);
			values_2[map.get(tk.getTask_name())] += Double.valueOf(tk.getMap().get("column_y").toString());
		}

		if ("customer_name".equals(column_name)) {
			for (int i = 0; i < list1.size(); i++) {
				KonkaR3Backmoney r3 = list1.get(i);
				values_1[map.get(r3.getCustomer_name())] += Double.valueOf(r3.getMap().get("column_y").toString());
			}
		} else if ("customer_type".equals(column_name)) {
			for (int i = 0; i < list1.size(); i++) {
				KonkaR3Backmoney r3 = list1.get(i);
				values_1[map.get(r3.getCustomer_type())] += Double.valueOf(r3.getMap().get("column_y").toString());
			}
		} else if ("branch_area_name".equals(column_name)) {
			for (int i = 0; i < list1.size(); i++) {
				KonkaR3Backmoney r3 = list1.get(i);
				values_1[map.get(r3.getBranch_area_name())] += Double.valueOf(r3.getMap().get("column_y").toString());
			}
		} else if ("area_name".equals(column_name)) {
			for (int i = 0; i < list1.size(); i++) {
				KonkaR3Backmoney r3 = list1.get(i);
				values_1[map.get(r3.getArea_name())] += Double.valueOf(r3.getMap().get("column_y").toString());
			}
		}

		// 今年比去年增长值
		for (int i = 0; i < nameList.size(); i++) {
			values_1[i] = values_1[i] * 100;
		}

		Map<String, Double[]> value_map = new HashMap<String, Double[]>();
		value_map.put("values_1", values_1);
		value_map.put("values_2", values_2);
		return value_map;
	}

	// 去年同期回款任务
	public Map<String, Double[]> chart7(List<String> nameList) throws Exception {

		// 初始化，取指定年12个月的任务系数
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		int month = now.get(Calendar.MONTH) + 1;
		TaskPara tp = new TaskPara();

		String name_String = "";
		for (int i = 0; i < nameList.size(); i++) {
			name_String = name_String + "'" + nameList.get(i) + "',";
		}
		name_String = StringUtils.removeEnd(name_String, ",");

		tp.getMap().put("column_name", "task_name");
		tp.getMap().put("shop_name_String", name_String);
		tp.setYear(year - 1);
		tp.getMap().put("column_m", month);

		List<TaskPara> list = super.getFacade().getTaskParaService().getTaskParaList(tp);
		Double values_1[] = new Double[nameList.size()];
		Double values_tmp[] = new Double[nameList.size()];
		Map<String, Integer> map = new HashMap<String, Integer>();
		for (int i = 0; i < nameList.size(); i++) {
			map.put(nameList.get(i), i);
			values_1[i] = 0.0;
			values_tmp[i] = 1.0;
		}

		for (int i = 0; i < list.size(); i++) {
			TaskPara tap = list.get(i);
			values_1[map.get(tap.getTask_name())] += Double.valueOf(tap.getMap().get("column_m").toString());
		}
		Map<String, Double[]> value_map = new HashMap<String, Double[]>();
		value_map.put("values_1", values_1);
		value_map.put("values_2", values_tmp);
		return value_map;
	}

	/*
	 * 去年同期回款
	 */
	public Map<String, Double[]> chart8(String column_name, List<String> nameList) throws Exception {
		// 取当前年月
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		int month = now.get(Calendar.MONTH) + 1;
		KonkaR3Backmoney kr3m = new KonkaR3Backmoney();

		String name_String = "";
		for (int i = 0; i < nameList.size(); i++) {
			name_String = name_String + "'" + nameList.get(i) + "',";
		}
		name_String = StringUtils.removeEnd(name_String, ",");

		kr3m.getMap().put("shop_name_String", name_String);
		kr3m.setYear(Long.valueOf(year) - 1);
		kr3m.getMap().put("column_name", column_name);
		kr3m.getMap().put("column_m", month);

		List<KonkaR3Backmoney> list = super.getFacade().getKonkaR3BackmoneyService().getKonkaR3BackmoneyList(kr3m);

		Double values_1[] = new Double[nameList.size()];
		Double values_tmp[] = new Double[nameList.size()];
		Map<String, Integer> map = new HashMap<String, Integer>();
		for (int i = 0; i < nameList.size(); i++) {
			map.put(nameList.get(i), i);
			values_1[i] = 0.0;
			values_tmp[i] = 1.0;
		}
		if ("customer_name".equals(column_name)) {
			for (int i = 0; i < list.size(); i++) {
				KonkaR3Backmoney r3 = list.get(i);
				values_1[map.get(r3.getCustomer_name())] += Double.valueOf(r3.getMap().get("column_m").toString());
			}
		} else if ("customer_type".equals(column_name)) {
			for (int i = 0; i < list.size(); i++) {
				KonkaR3Backmoney r3 = list.get(i);
				values_1[map.get(r3.getCustomer_type())] += Double.valueOf(r3.getMap().get("column_m").toString());
			}
		} else if ("branch_area_name".equals(column_name)) {
			for (int i = 0; i < list.size(); i++) {
				KonkaR3Backmoney r3 = list.get(i);
				values_1[map.get(r3.getBranch_area_name())] += Double.valueOf(r3.getMap().get("column_m").toString());
			}
		} else if ("area_name".equals(column_name)) {
			for (int i = 0; i < list.size(); i++) {
				KonkaR3Backmoney r3 = list.get(i);
				values_1[map.get(r3.getArea_name())] += Double.valueOf(r3.getMap().get("column_m").toString());
			}
		}
		Map<String, Double[]> value_map = new HashMap<String, Double[]>();
		value_map.put("values_1", values_1);
		value_map.put("values_2", values_tmp);
		return value_map;
	}

	/*
	 * 同比去年同期增长率
	 */
	public Map<String, Double[]> chart9(String column_name, List<String> nameList) throws Exception {

		// 取当前年月
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		int month = now.get(Calendar.MONTH) + 1;

		String name_String = "";
		for (int i = 0; i < nameList.size(); i++) {
			name_String = name_String + "'" + nameList.get(i) + "',";
		}
		name_String = StringUtils.removeEnd(name_String, ",");

		// 去年同期回款额
		KonkaR3Backmoney kr3m_1 = new KonkaR3Backmoney();
		kr3m_1.getMap().put("shop_name_String", name_String);
		kr3m_1.setYear(Long.valueOf(year) - 1);
		kr3m_1.getMap().put("column_name", column_name);
		kr3m_1.getMap().put("column_m", month);

		List<KonkaR3Backmoney> list_1 = super.getFacade().getKonkaR3BackmoneyService().getKonkaR3BackmoneyList(kr3m_1);

		// 今年当前月回款额
		KonkaR3Backmoney kr3m_2 = new KonkaR3Backmoney();
		kr3m_2.getMap().put("shop_name_String", name_String);
		kr3m_2.setYear(Long.valueOf(year));
		kr3m_2.getMap().put("column_name", column_name);
		kr3m_2.getMap().put("column_m", month);

		List<KonkaR3Backmoney> list_2 = super.getFacade().getKonkaR3BackmoneyService().getKonkaR3BackmoneyList(kr3m_2);

		Double values_1[] = new Double[nameList.size()];
		Double values_2[] = new Double[nameList.size()];
		Double values_sub[] = new Double[nameList.size()];
		Map<String, Integer> map = new HashMap<String, Integer>();
		for (int i = 0; i < nameList.size(); i++) {
			map.put(nameList.get(i), i);
			values_1[i] = 0.0;
			values_2[i] = 0.0;
		}
		if ("customer_name".equals(column_name)) {
			for (int i = 0; i < list_1.size(); i++) {
				KonkaR3Backmoney r3 = list_1.get(i);
				values_1[map.get(r3.getCustomer_name())] += Double.valueOf(r3.getMap().get("column_m").toString());
			}
			for (int i = 0; i < list_2.size(); i++) {
				KonkaR3Backmoney r3 = list_2.get(i);
				values_2[map.get(r3.getCustomer_name())] += Double.valueOf(r3.getMap().get("column_m").toString());
			}
		} else if ("customer_type".equals(column_name)) {
			for (int i = 0; i < list_1.size(); i++) {
				KonkaR3Backmoney r3 = list_1.get(i);
				values_1[map.get(r3.getCustomer_type())] += Double.valueOf(r3.getMap().get("column_m").toString());
			}
			for (int i = 0; i < list_2.size(); i++) {
				KonkaR3Backmoney r3 = list_2.get(i);
				values_2[map.get(r3.getCustomer_type())] += Double.valueOf(r3.getMap().get("column_m").toString());
			}
		} else if ("branch_area_name".equals(column_name)) {
			for (int i = 0; i < list_1.size(); i++) {
				KonkaR3Backmoney r3 = list_1.get(i);
				values_1[map.get(r3.getBranch_area_name())] += Double.valueOf(r3.getMap().get("column_m").toString());
			}
			for (int i = 0; i < list_2.size(); i++) {
				KonkaR3Backmoney r3 = list_2.get(i);
				values_2[map.get(r3.getBranch_area_name())] += Double.valueOf(r3.getMap().get("column_m").toString());
			}
		} else if ("area_name".equals(column_name)) {
			for (int i = 0; i < list_1.size(); i++) {
				KonkaR3Backmoney r3 = list_1.get(i);
				values_1[map.get(r3.getArea_name())] += Double.valueOf(r3.getMap().get("column_m").toString());
			}
			for (int i = 0; i < list_2.size(); i++) {
				KonkaR3Backmoney r3 = list_2.get(i);
				values_2[map.get(r3.getArea_name())] += Double.valueOf(r3.getMap().get("column_m").toString());
			}
		}

		// 今年当前月比去年同期增长值
		for (int i = 0; i < nameList.size(); i++) {
			values_sub[i] = (values_2[i] - values_1[i]) * 100;
		}

		Map<String, Double[]> value_map = new HashMap<String, Double[]>();
		value_map.put("values_1", values_sub);
		value_map.put("values_2", values_1);
		return value_map;
	}

	// 去年同期任务累计
	public Map<String, Double[]> chart10(List<String> nameList) throws Exception {

		// 初始化，取指定年12个月的数据
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		TaskPara tp = new TaskPara();

		String name_String = "";
		for (int i = 0; i < nameList.size(); i++) {
			name_String = name_String + "'" + nameList.get(i) + "',";
		}
		name_String = StringUtils.removeEnd(name_String, ",");
		tp.getMap().put("shop_name_String", name_String);

		tp.setYear(year - 1);
		tp.getMap().put("column_name", "task_name");
		String column_y = "";
		for (int i = 1; i < 13; i++) {
			column_y = column_y + "decode(column_" + i + ",null,0,column_" + i + ")+";
		}
		column_y = StringUtils.removeEnd(column_y, "+");

		tp.getMap().put("column_y", column_y);

		List<TaskPara> list = super.getFacade().getTaskParaService().getTaskParaList(tp);
		Double values_1[] = new Double[nameList.size()];
		Double values_tmp[] = new Double[nameList.size()];
		Map<String, Integer> map = new HashMap<String, Integer>();
		for (int i = 0; i < nameList.size(); i++) {
			map.put(nameList.get(i), i);
			values_1[i] = 0.0;
			values_tmp[i] = 1.0;
		}

		for (int i = 0; i < list.size(); i++) {
			TaskPara entiy = list.get(i);
			values_1[map.get(entiy.getTask_name())] += Double.valueOf(entiy.getMap().get("column_y").toString());
		}

		Map<String, Double[]> value_map = new HashMap<String, Double[]>();
		value_map.put("values_1", values_1);
		value_map.put("values_2", values_tmp);
		return value_map;
	}

	/*
	 * 去年同期累计回款
	 */
	public Map<String, Double[]> chart11(String column_name, List<String> nameList) throws Exception {

		// 取当前年月
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		KonkaR3Backmoney kr3m = new KonkaR3Backmoney();

		String name_String = "";
		for (int i = 0; i < nameList.size(); i++) {
			name_String = name_String + "'" + nameList.get(i) + "',";
		}
		name_String = StringUtils.removeEnd(name_String, ",");
		kr3m.getMap().put("shop_name_String", name_String);

		kr3m.setYear(Long.valueOf(year) - 1);
		kr3m.getMap().put("column_name", column_name);
		String column_y = "";
		for (int i = 1; i < 13; i++) {
			column_y = column_y + "decode(column_" + i + ",null,0,column_" + i + ")+";
		}
		column_y = StringUtils.removeEnd(column_y, "+");

		kr3m.getMap().put("column_y", column_y);

		List<KonkaR3Backmoney> list = super.getFacade().getKonkaR3BackmoneyService().getKonkaR3BackmoneyList(kr3m);
		Double values_1[] = new Double[nameList.size()];
		Double values_tmp[] = new Double[nameList.size()];
		Map<String, Integer> map = new HashMap<String, Integer>();
		for (int i = 0; i < nameList.size(); i++) {
			map.put(nameList.get(i), i);
			values_1[i] = 0.0;
			values_tmp[i] = 1.0;
		}
		if ("customer_name".equals(column_name)) {
			for (int i = 0; i < list.size(); i++) {
				KonkaR3Backmoney r3 = list.get(i);
				values_1[map.get(r3.getCustomer_name())] += Double.valueOf(r3.getMap().get("column_y").toString());
			}
		} else if ("customer_type".equals(column_name)) {
			for (int i = 0; i < list.size(); i++) {
				KonkaR3Backmoney r3 = list.get(i);
				values_1[map.get(r3.getCustomer_type())] += Double.valueOf(r3.getMap().get("column_y").toString());
			}
		} else if ("branch_area_name".equals(column_name)) {
			for (int i = 0; i < list.size(); i++) {
				KonkaR3Backmoney r3 = list.get(i);
				values_1[map.get(r3.getBranch_area_name())] += Double.valueOf(r3.getMap().get("column_y").toString());
			}
		} else if ("area_name".equals(column_name)) {
			for (int i = 0; i < list.size(); i++) {
				KonkaR3Backmoney r3 = list.get(i);
				values_1[map.get(r3.getArea_name())] += Double.valueOf(r3.getMap().get("column_y").toString());
			}
		}

		Map<String, Double[]> value_map = new HashMap<String, Double[]>();
		value_map.put("values_1", values_1);
		value_map.put("values_2", values_tmp);
		return value_map;
	}

	public Map<String, Double[]> chart12(String column_name, List<String> nameList) throws Exception {

		// 取当前年月
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);

		String name_String = "";
		for (int i = 0; i < nameList.size(); i++) {
			name_String = name_String + "'" + nameList.get(i) + "',";
		}
		name_String = StringUtils.removeEnd(name_String, ",");

		String column_y = "";
		for (int i = 1; i < 13; i++) {
			column_y = column_y + "decode(column_" + i + ",null,0,column_" + i + ")+";
		}
		column_y = StringUtils.removeEnd(column_y, "+");

		// 去年回款额
		KonkaR3Backmoney kr3m_1 = new KonkaR3Backmoney();
		kr3m_1.getMap().put("shop_name_String", name_String);
		kr3m_1.setYear(Long.valueOf(year) - 1);
		kr3m_1.getMap().put("column_name", column_name);
		kr3m_1.getMap().put("column_y", column_y);

		List<KonkaR3Backmoney> list_1 = super.getFacade().getKonkaR3BackmoneyService().getKonkaR3BackmoneyList(kr3m_1);

		// 今年回款额
		KonkaR3Backmoney kr3m_2 = new KonkaR3Backmoney();
		kr3m_2.getMap().put("shop_name_String", name_String);
		kr3m_2.setYear(Long.valueOf(year));
		kr3m_2.getMap().put("column_name", column_name);
		kr3m_2.getMap().put("column_y", column_y);

		List<KonkaR3Backmoney> list_2 = super.getFacade().getKonkaR3BackmoneyService().getKonkaR3BackmoneyList(kr3m_2);

		Double values_1[] = new Double[nameList.size()];
		Double values_2[] = new Double[nameList.size()];
		Double values_sub[] = new Double[nameList.size()];
		Map<String, Integer> map = new HashMap<String, Integer>();
		for (int i = 0; i < nameList.size(); i++) {
			map.put(nameList.get(i), i);
			values_1[i] = 0.0;
			values_2[i] = 0.0;
		}

		if ("customer_name".equals(column_name)) {
			for (int i = 0; i < list_1.size(); i++) {
				KonkaR3Backmoney r3 = list_1.get(i);
				values_1[map.get(r3.getCustomer_name())] += Double.valueOf(r3.getMap().get("column_y").toString());
			}
			for (int i = 0; i < list_2.size(); i++) {
				KonkaR3Backmoney r3 = list_2.get(i);
				values_2[map.get(r3.getCustomer_name())] += Double.valueOf(r3.getMap().get("column_y").toString());
			}
		} else if ("customer_type".equals(column_name)) {
			for (int i = 0; i < list_1.size(); i++) {
				KonkaR3Backmoney r3 = list_1.get(i);
				values_1[map.get(r3.getCustomer_type())] += Double.valueOf(r3.getMap().get("column_y").toString());
			}
			for (int i = 0; i < list_2.size(); i++) {
				KonkaR3Backmoney r3 = list_2.get(i);
				values_2[map.get(r3.getCustomer_type())] += Double.valueOf(r3.getMap().get("column_y").toString());
			}
		} else if ("branch_area_name".equals(column_name)) {
			for (int i = 0; i < list_1.size(); i++) {
				KonkaR3Backmoney r3 = list_1.get(i);
				values_1[map.get(r3.getBranch_area_name())] += Double.valueOf(r3.getMap().get("column_y").toString());
			}
			for (int i = 0; i < list_2.size(); i++) {
				KonkaR3Backmoney r3 = list_2.get(i);
				values_2[map.get(r3.getBranch_area_name())] += Double.valueOf(r3.getMap().get("column_y").toString());
			}
		} else if ("area_name".equals(column_name)) {
			for (int i = 0; i < list_1.size(); i++) {
				KonkaR3Backmoney r3 = list_1.get(i);
				values_1[map.get(r3.getArea_name())] += Double.valueOf(r3.getMap().get("column_y").toString());
			}
			for (int i = 0; i < list_2.size(); i++) {
				KonkaR3Backmoney r3 = list_2.get(i);
				values_2[map.get(r3.getArea_name())] += Double.valueOf(r3.getMap().get("column_y").toString());
			}
		}

		// 今年比去年增长值
		for (int i = 0; i < nameList.size(); i++) {
			values_sub[i] = (values_2[i] - values_1[i]) * 100;
		}
		Map<String, Double[]> value_map = new HashMap<String, Double[]>();
		value_map.put("values_1", values_sub);
		value_map.put("values_2", values_1);
		return value_map;
	}

	public ActionForward toExcel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		super.HtmltoExcel(request, response);
		return null;
	}

}
