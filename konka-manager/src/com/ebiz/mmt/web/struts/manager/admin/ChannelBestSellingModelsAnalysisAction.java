package com.ebiz.mmt.web.struts.manager.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.ChannelDataImport;
import com.ebiz.mmt.domain.ImportDataTypes;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.struts.BaseAction;

/**
 * 客户管理 > 渠道统计分析 > 畅销机型（演示）
 * 
 * @author Wang Hao
 */
public class ChannelBestSellingModelsAnalysisAction extends BaseAction {

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

		ImportDataTypes importDataTypes = new ImportDataTypes();
		importDataTypes.setPar_id(0);
		List<ImportDataTypes> idtList = super.getFacade().getImportDataTypesService()
				.getImportDataTypesList(importDataTypes);
		request.setAttribute("idtList", idtList);
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_MONTH, 1);
		String add_date_st = DateFormatUtils.format(c.getTime(), "yyyy-MM-dd"); // 取当月第一天
		dynaBean.set("add_date_st", add_date_st);
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		String add_date_en = DateFormatUtils.format(calendar.getTime(), "yyyy-MM-dd");
		dynaBean.set("add_date_en", add_date_en);

		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(super.getSessionUserInfo(request).getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);

		boolean role_id_ge_30 = false;
		for (PeRoleUser peRoleUser: peRoleUserList) {
			if (peRoleUser.getRole_id() >= 30L) {
				role_id_ge_30 = true;
				break;
			}
		}
		
		KonkaR3Shop r3 = new KonkaR3Shop();
		r3.setIs_del(0L);
		if(role_id_ge_30){
			KonkaDept dept = new KonkaDept();
			dept.setDept_id(super.getSessionUserInfo(request).getDept_id());
			dept = getFacade().getKonkaDeptService().getKonkaDept(dept);
			if(dept.getDept_type() == 3){// 分公司
				r3.setBranch_area_name(dept.getDept_name());
				dynaBean.set("branch_area_name_select", dept.getDept_name());
				dynaBean.set("branch_area_name_link", dept.getDept_name());
			}
			else if(dept.getDept_type() == 4|| dept.getDept_type() == 5){//经营部
				r3.setBranch_area_name(super.getSuperiorForDeptType(dept.getDept_id(), 3).getDept_name());
				dynaBean.set("branch_area_name_select", super.getSuperiorForDeptType(dept.getDept_id(), 3).getDept_name());
				dynaBean.set("branch_area_name_link", super.getSuperiorForDeptType(dept.getDept_id(), 3).getDept_name());
			}
		}
		List<KonkaR3Shop> BranchList = getFacade().getKonkaR3ShopService().getKonkaR3ShopGroupByBranchAreaName(r3);

		request.setAttribute("BranchList", BranchList);
		dynaBean.set("type", "1");

		return mapping.findForward("list");
	}

	public ActionForward getModelList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);
		String column_11 = (String) dynaBean.get("column_11");
		ChannelDataImport entity = new ChannelDataImport();
		if (StringUtils.isNotBlank(column_11)) {
			entity.getMap().put("column_11", column_11);
		}
		entity.getMap().put("count3", true);

		String firstRow = (String) dynaBean.get("firstRow");
		String pageSize = (String) dynaBean.get("pageSize");
		Long recordCount = super.getFacade().getChannelDataImportService().getChannelDataImportCount(entity);
		entity.getRow().setFirst(Integer.valueOf(firstRow) - 1);
		entity.getRow().setCount(Integer.valueOf(pageSize));
		List<ChannelDataImport> modelList = super.getFacade().getChannelDataImportService()
				.getChannelDataImportPaginatedListForModel(entity);
		String tr = toJson(modelList, recordCount);
		logger.info("JSON Output : [{}]", tr);

		super.render(response, tr.toString(), "text/x-json;charset=UTF-8");
		return null;
	}

	public String toJson(List<ChannelDataImport> modelList, Long count) {
		StringBuffer jsonBuffer = new StringBuffer();
		if (null != modelList && modelList.size() > 0) {
			for (ChannelDataImport temp : modelList) {
				jsonBuffer.append("{");
				jsonBuffer.append("\"model_name\":\"").append(temp.getMap().get("model")).append("\"");
				jsonBuffer.append("},");
			}
		}
		String listStr = StringUtils.removeEnd(jsonBuffer.toString(), ",");
		StringBuffer json = new StringBuffer("{");
		json.append("\"list\" : [").append(listStr).append("],");
		json.append("\"count\":\"").append(count).append("\"");
		json.append("}");
		return json.toString();
	}

//	public String getTaskName(String name) {
//		TaskPara tp = new TaskPara();
//		tp.setTask_name(name);
//		tp = getFacade().getTaskParaService().getTaskPara(tp);
//		if (null == tp) {
//			return name;
//		}
//		return tp.getTask_name();
//	}

	/*
	 * 该网点/分公司/分大区机型数量取得，占比设定
	 */

	public void setTaskSum(Map<String, Object> map, Long allSum, Long t_sum, ChannelDataImport cdi) {
		// 该单客户 /客户群/ 分公司/ 分大区/ 经办 的数量
		Long sum = super.getFacade().getChannelDataImportService().getChannelDataImportForSum(cdi);
		if (sum != null && sum != 0) {
			Double value = Double.valueOf(t_sum) * 100 / Double.valueOf(sum);
			map.put("t_per0", value);
		} else {
			map.put("t_per0", 100);
		}
		map.put("t_sum", t_sum);
		// 所有单客户 /客户群/ 分公司/ 分大区/ 经办 的数量
		if (allSum != null && allSum != 0L) {
			Double value = Double.valueOf(t_sum) * 100 / Double.valueOf(allSum);
			map.put("t_per1", value);
		} else {
			map.put("t_per1", 100);
		}
	}

	/*
	 * 该机型数量取得
	 */
	public Long setModelSum(ChannelDataImport cdi) {
		Long sum = super.getFacade().getChannelDataImportService().getChannelDataImportForSum(cdi);
		if (sum == null)
			return 0L;
		return sum;
	}

	public ActionForward listStat(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String model_List = (String) dynaBean.get("model_list");
		super.encodeCharacterForGetMethod(dynaBean, request);

		if (StringUtils.isBlank(model_List)) {
			return null;
		}

		String arr[] = model_List.split(",");
		request.setAttribute("arr", Arrays.asList(arr));

		String type = (String) dynaBean.get("type");
		String key = (String) dynaBean.get("keyWord");
		String date_start = (String) dynaBean.get("date_start");
		String date_end = (String) dynaBean.get("date_end");
		String handle_name = (String) dynaBean.get("handle_name");
		String branch_area_name_link = (String) dynaBean.get("branch_area_name_link");
		String branch_area_name = (String) dynaBean.get("fgs");
		ChannelDataImport cdi1 = new ChannelDataImport();
		ChannelDataImport cdi = new ChannelDataImport();
		if (StringUtils.isNotBlank(key)) {
			key = java.net.URLDecoder.decode(key, "utf-8");
		}
		if (StringUtils.isNotBlank(handle_name)) {
			handle_name = java.net.URLDecoder.decode(handle_name, "utf-8");
		}
		if (StringUtils.isNotBlank(branch_area_name_link)) {
			branch_area_name_link = java.net.URLDecoder.decode(branch_area_name_link, "utf-8");
		}
		if (StringUtils.isNotBlank(branch_area_name)) {
			branch_area_name = java.net.URLDecoder.decode(branch_area_name, "utf-8");
		}

		if (StringUtils.isNotBlank(date_start)) {
			String s = date_start.replaceAll("-", "");
			String s_date = s.substring(0, 8);
			cdi.getMap().put("date_start", s_date);
			cdi1.getMap().put("date_start", s_date);
		}
		if (StringUtils.isNotBlank(date_end)) {
			String s = date_end.replaceAll("-", "");
			String e_date = s.substring(0, 8);
			cdi.getMap().put("date_end", e_date);
			cdi1.getMap().put("date_end", e_date);
		}

		cdi1.getMap().put("e", true);
		Long allSum = super.getFacade().getChannelDataImportService().getChannelDataImportForSum(cdi1);

		KonkaR3Shop r3 = new KonkaR3Shop();
		KonkaDept dept = new KonkaDept();
		List<Map<String, Object>> task_list = new ArrayList<Map<String, Object>>();
		
		PeRoleUser peRoleUser = new PeRoleUser();
		peRoleUser.setUser_id(super.getSessionUserInfo(request).getId());
		peRoleUser = this.getFacade().getPeRoleUserService().getPeRoleUser(peRoleUser);
	
		String firstRow = (String) dynaBean.get("firstRow2");
		String pageSize = (String) dynaBean.get("pageSize2");
		Long recordCount = 0L;
		if ("1".equals(type)) {
			r3.getMap().put("is_jyb", null);
			r3.getMap().put("is_in", true);

			if (StringUtils.isNotBlank(key)) {
				r3.getMap().put("r3_code", key);
			}

			if (peRoleUser.getRole_id() >= 20) { // 不是系统管理员组全部要加上网点分配
				r3.getMap().put("dept_id", this.getSessionUserInfo(request).getDept_id());
				r3.getMap().put("user_id", this.getSessionUserInfo(request).getId());
			}
			if (peRoleUser.getRole_id() == 60) { // 业务员只能看到分配给自己的
				r3.getMap().put("dept_id", -1);
				r3.getMap().put("user_id", this.getSessionUserInfo(request).getId());
			}

			recordCount = getFacade().getKonkaR3ShopService().getKonkaR3ShopCountForHandle(r3);
			r3.getRow().setFirst(Integer.valueOf(firstRow) - 1);
			r3.getRow().setCount(Integer.valueOf(pageSize));
			List<KonkaR3Shop> entityList = super.getFacade().getKonkaR3ShopService()
					.getKonkaR3ShopPaginatedListGroupByName(r3);

			for (KonkaR3Shop t : entityList) {
				String task_name = t.getCustomer_name();
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("task_name", task_name);

				List<Long> sumList = new ArrayList<Long>();
				Long t_sum = 0L;
				for (int i = 0; i < arr.length; i++) {
					cdi.getMap().put("a", true);
					cdi.setColumn_11(arr[i]);
					log.info(t.getR3_code());
					cdi.setColumn_1(t.getR3_code());
					Long sum = setModelSum(cdi);
					sumList.add(sum);
					t_sum += sum;
				}
				map.put("sumList", sumList);

				cdi1.getMap().put("e", "");
				cdi1.getMap().put("a1", true);
				cdi1.setColumn_1(t.getR3_code());
				setTaskSum(map, allSum, t_sum, cdi1);
				task_list.add(map);
			}
		} else if ("3".equals(type)) {
			dept.setPar_id((long) 1);
			if (StringUtils.isNotBlank(branch_area_name)) {
				dept.getMap().put("dept_name_like", branch_area_name);
				dynaBean.set("branch_area_name_select", branch_area_name);
			}
			recordCount = getFacade().getKonkaDeptService().getKonkaDeptCount(dept);
			dept.getRow().setFirst(Integer.valueOf(firstRow) - 1);
			dept.getRow().setCount(Integer.valueOf(pageSize));
			List<KonkaDept> entityList = super.getFacade().getKonkaDeptService().getKonkaDeptPaginatedList(dept);

			for (KonkaDept t : entityList) {
				String task_name = t.getDept_name();
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("task_name", task_name);
				List<Long> sumList = new ArrayList<Long>();
				Long t_sum = 0L;
				for (int i = 0; i < arr.length; i++) {

					cdi.getMap().put("c", true);
					cdi.getMap().put("is_fgs", true);
					cdi.getMap().put("is_jyb", null);
					cdi.setColumn_11(arr[i]);
					cdi.getMap().put("fgs", t.getDept_name());
					Long sum = setModelSum(cdi);
					sumList.add(sum);
					t_sum += sum;
				}
				map.put("sumList", sumList);

				cdi1.getMap().put("e", "");
				cdi.getMap().put("is_fgs", true);
				cdi.getMap().put("is_jyb", null);

				cdi1.getMap().put("c1", true);
				cdi1.getMap().put("fgs", t.getDept_name());
				setTaskSum(map, allSum, t_sum, cdi1);
				task_list.add(map);
			}
		} else if ("5".equals(type)) {
			KonkaR3Shop r3Shop = new KonkaR3Shop();
			r3Shop.setIs_del(0L);
			if(StringUtils.isNotBlank(branch_area_name_link)){
				r3Shop.setBranch_area_name(branch_area_name_link);
				dynaBean.set("branch_area_name_link", branch_area_name_link);
			}
			if(StringUtils.isNotBlank(handle_name)){
				r3Shop.setHandle_name(handle_name);
				dynaBean.set("handle_name", handle_name);
			}
			List<KonkaR3Shop> entityList = getFacade().getKonkaR3ShopService().getKonkaR3ShopGroupByHandleName(r3Shop);

			for (KonkaR3Shop t : entityList) {
				String task_name = t.getHandle_name();
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("task_name", task_name);
				List<Long> sumList = new ArrayList<Long>();
				Long t_sum = 0L;
				for (int i = 0; i < arr.length; i++) {

					cdi.getMap().put("c", true);
					cdi.getMap().put("is_jyb", true);
					cdi.getMap().put("is_fgs", null);
					cdi.setColumn_11(arr[i]);
					cdi.getMap().put("fgs", t.getHandle_name());
					Long sum = setModelSum(cdi);
					sumList.add(sum);
					t_sum += sum;
				}
				map.put("sumList", sumList);

				cdi1.getMap().put("e", "");
				cdi.getMap().put("is_jyb", true);
				cdi.getMap().put("is_fgs", null);
				cdi1.getMap().put("c1", true);
				cdi1.getMap().put("fgs", t.getHandle_name());
				setTaskSum(map, allSum, t_sum, cdi1);
				task_list.add(map);
			}
		}

		String json = toJsonStat(task_list, recordCount);
		logger.info("JSON Output : [{}]", json);
		super.render(response, json, "text/x-json;charset=UTF-8");
		return null;
	}

	@SuppressWarnings("unchecked")
	public String toJsonStat(List<Map<String, Object>> task_list, Long recordCount) {
		StringBuffer jsonBuffer = new StringBuffer();
		if (null != task_list && task_list.size() > 0) {
			for (Map<String, Object> map : task_list) {
				String task_name = (String) map.get("task_name");
				List<Long> sumList = (List<Long>) map.get("sumList");
				Long t_sum = (Long) map.get("t_sum");
				Double t_per0 = (Double) Double.valueOf(map.get("t_per0").toString());
				Double t_per1 = (Double) Double.valueOf(map.get("t_per1").toString());

				jsonBuffer.append("{");
				jsonBuffer.append("\"task_name\":\"").append(task_name).append("\",");
				jsonBuffer.append("\"t_sum\":\"").append(t_sum).append("\",");
				jsonBuffer.append("\"t_per0\":\"").append(t_per0).append("\",");
				jsonBuffer.append("\"t_per1\":\"").append(t_per1).append("\",");

				StringBuffer jb_sub = new StringBuffer();
				jb_sub.append("\"sumList\":[");
				if (sumList != null && sumList.size() > 0) {
					for (int i = 0; i < sumList.size(); i++) {
						Long value = sumList.get(i);
						jb_sub.append(value).append(",");
					}
				}
				jsonBuffer.append(StringUtils.removeEnd(jb_sub.toString(), ","));
				jsonBuffer.append("]");
				jsonBuffer.append("},");
			}
		}
		String listStr = StringUtils.removeEnd(jsonBuffer.toString(), ",");
		StringBuffer json = new StringBuffer("{");
		json.append("\"list\" : [").append(listStr).append("],");
		json.append("\"count\":\"").append(recordCount).append("\"");
		json.append("}");
		return json.toString();
	}

	public ActionForward toExcel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		super.HtmltoExcel(request, response);
		return null;
	}
}
