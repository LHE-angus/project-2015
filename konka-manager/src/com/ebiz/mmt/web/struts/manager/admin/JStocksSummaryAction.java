package com.ebiz.mmt.web.struts.manager.admin;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.alibaba.fastjson.JSON;
import com.ebiz.mmt.domain.JStocksSummary;
import com.ebiz.mmt.domain.KonkaBaseTypeData;
import com.ebiz.mmt.domain.KonkaCategory;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaOrderMessageProcess;
import com.ebiz.mmt.domain.OperLog;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.domain.YwtTaskReceive;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.util.EncryptUtils;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Xiao,GuoJian
 * @version 2014-08-15
 */
public class JStocksSummaryAction extends BaseAction {
	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		setNaviStringToRequestScope(form, request);
		super.getModPopeDom(form, request);

		PeProdUser ui = (PeProdUser) super.getSessionAttribute(request,
				Constants.USER_INFO);

		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");

		String customer_name_like = (String) dynaBean.get("customer_name_like");
		String handle_name_like = (String) dynaBean.get("handle_name_like");// 经办名称
		String r3_code_like = (String) dynaBean.get("r3_code_like");
		String goods_name_like = (String) dynaBean.get("goods_name_like");
		String dept_sn = (String) dynaBean.get("dept_sn");
		String excel_to_all = (String) dynaBean.get("excel_to_all");
		String type = (String) dynaBean.get("type");// 0:客户库存汇总 1:月度库存
		String year = (String) dynaBean.get("year");
		String month = (String) dynaBean.get("month");
		String cur_min_num = (String) dynaBean.get("cur_min_num");
		String cur_max_num = (String) dynaBean.get("cur_max_num");
		// 一级客户类型
		String customer_type1 = (String) dynaBean.get("v_customer_type1");

		String is_allow_back = (String) dynaBean.get("is_allow_back");

		request.setAttribute("is_allow_back", is_allow_back);

		// 二级客户类型
		String customer_type2 = (String) dynaBean.get("v_customer_type2");

		String area_name = (String) dynaBean.get("area_name");

		JStocksSummary entity = new JStocksSummary();

		if (StringUtils.isNotBlank(type)) {
			entity.setType(Integer.parseInt(type));
		} else {
			super.renderJavaScript(response, "alert('参数丢失！');history.back();");
			return null;
		}

		if (StringUtils.isNotBlank(customer_name_like))
			entity.getMap()
					.put("customer_name_like", customer_name_like.trim());

		if (StringUtils.isNotBlank(r3_code_like))
			entity.getMap().put("r3_code_like", r3_code_like.trim());
		if (StringUtils.isNotBlank(cur_min_num)) {
			entity.getMap().put("cur_min_num", Integer.valueOf(cur_min_num));
			dynaBean.set("cur_min_num", cur_min_num);
		}
		if (StringUtils.isNotBlank(cur_max_num)) {
			entity.getMap().put("cur_max_num", Integer.valueOf(cur_max_num));
			dynaBean.set("cur_max_num", cur_max_num);
		}
		// 添加客户类型筛选条件
		if (StringUtils.isNotBlank(customer_type2)) {
			entity.getMap().put("cus_type2", customer_type2);
			dynaBean.set("customer_type2", customer_type2);
		} else {
			if (StringUtils.isNotBlank(customer_type1)) {
				entity.getMap().put("cus_type1", customer_type1);
				dynaBean.set("customer_type1", customer_type1);
			}
		}
		if (StringUtils.isNotBlank(goods_name_like))
			entity.getMap().put("goods_name_like", goods_name_like.trim());

		if (StringUtils.isNotBlank(dept_sn))
			entity.getMap().put("dept_sn", dept_sn);

		if (StringUtils.isNotBlank(handle_name_like)) {
			entity.getMap().put("handle_name_like_1", handle_name_like.trim());
			dynaBean.set("handle_name_like_1", handle_name_like.trim());
		}
		if (StringUtils.isNotBlank(area_name)) {
			entity.getMap().put("area_name", area_name);
		}

		SimpleDateFormat formaty = new SimpleDateFormat("yyyy");
		// SimpleDateFormat formatm = new SimpleDateFormat("MM");
		Date now = new Date();
		if ("1".equals(type)) {
			String this_year = null;
			String this_month = null;

			// 取当前时间

			if (StringUtils.isNotBlank(year) && StringUtils.isNotBlank(month)) {
				this_year = year;
				this_month = month;
			} else {
				// this_year = formaty.format(now);// 当前年份
				// this_month = formatm.format(now);// 当前月份
				Calendar calendar = Calendar.getInstance();
				calendar.add(Calendar.MONTH, -1);// 月份减1
				this_year = DateFormatUtils.format(calendar.getTime(), "yyyy");// 当前年
				this_month = DateFormatUtils.format(calendar.getTime(), "MM");// 上一个月
				// 去年
			}

			// 今年数据时间段
			String this_date_s = this_year + "-" + this_month + "-01 00:00:00";
			String this_date_e = this_year
					+ "-"
					+ this_month
					+ "-"
					+ getMaxDay(Integer.valueOf(this_year),
							Integer.valueOf(this_month)) + " 23:59:59";
			entity.getMap().put("add_date_s", this_date_s);
			entity.getMap().put("add_date_e", this_date_e);
			dynaBean.set("year", this_year);
			dynaBean.set("month", this_month);
		}

		// 数据级别判断开始
		Long __dept_id = ui.getDept_id(); // 默认为当前用户所在部门
		int max_dlevel = super.getMaxDLevel(ui.getId()); // 获取当前用户的最高可视部门级别
		logger.info("Max level : {}", max_dlevel);
		request.setAttribute("max_dlevel", max_dlevel);
		switch (max_dlevel) {
		case 9:
			// 集团及以下部门可见
			__dept_id = 0L; // 0表示部门根节点，即“多媒体事业本部”

			// 分公司列表
			KonkaDept kd = new KonkaDept();
			kd.setDept_type(3);
			List<KonkaDept> kdList = super.getFacade().getKonkaDeptService()
					.getKonkaDeptList(kd);
			request.setAttribute("kdList", kdList);

			break;
		case 8:
			// 分公司及以下部门可见
			KonkaDept dept_fgs = super.getKonkaDeptForFgs(ui.getDept_id()); // 查询部门分公司
			if (null != dept_fgs && null != dept_fgs.getDept_sn()) {
				entity.getMap().put("dept_sn", dept_fgs.getDept_sn());
			}
			break;
		case 7:
			// 我所在的部门及以下部门可见
			__dept_id = ui.getDept_id(); // 默认为当前用户所在部门
			entity.getMap().put("dept_id_start", __dept_id);
			break;
		case 0:
			__dept_id = ui.getDept_id(); // 默认为当前用户所在部门
			entity.getMap().put("filter_by_ywy_id_eq", ui.getId());
			break;
		default:
			// 出错
		}
		entity.getMap().put("session_user_id", ui.getId());

		Long recordCount = super.getFacade().getJStocksSummaryService()
				.getJStocksSummaryForR3ShopCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		
		if (StringUtils.isNotBlank(excel_to_all)) {

			// 为“总部财务部”角色开放导出记录数限制
			PeRoleUser roleUser = new PeRoleUser();
			roleUser.setUser_id(ui.getId());
			List<PeRoleUser> roleUserList = getFacade().getPeRoleUserService()
					.getPeRoleUserList(roleUser);

			boolean is_exit = true;
			for (PeRoleUser t : roleUserList) {
				// 特殊角色可以导出超出30000条记录 
				// 20150612 按xufeng要求增加
				if (t.getRole_id() == 29 || t.getRole_id() == 10
						|| t.getRole_id() == 19) {
					is_exit = false;
					break;
				}
			}
			if (is_exit) {
				if (recordCount > 30000) {// 导出数据不能超过30000条
					super.renderJavaScript(response,
							"alert('导出记录不能超过30000条，请缩小查找范围！');history.back();");
					return null;
				}
			}

			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename="
					+ EncryptUtils.encodingFileName("客户库存数据") + ".xls");

			entity.getRow().setCount(recordCount.intValue());
			List<JStocksSummary> entityList1 = getFacade()
					.getJStocksSummaryService().getJStocksSummaryForR3ShopList(
							entity);
			request.setAttribute("allList", entityList1);
			return mapping.findForward("view");
		}
		// 客户类型
		KonkaCategory kc = new KonkaCategory();
		kc.setC_type(10);
		kc.setIs_del(0);
		request.setAttribute("konkaCategoryList", super.getFacade()
				.getKonkaCategoryService().getKonkaCategoryList(kc));

		List<JStocksSummary> entityList = super.getFacade()
				.getJStocksSummaryService()
				.getJStocksSummaryForR3ShopPaginatedList(entity);
		Object totlePageNum = super.getFacade().getJStocksSummaryService()
				.getJStocksSummaryForR3ShopSumCount(entity);

		if (null != totlePageNum) {
			dynaBean.set("totlePageNum", totlePageNum);
		} else {
			dynaBean.set("totlePageNum", "0");
		}
		if (entityList != null && entityList.size() > 0) {
			if (entityList.get(0) != null
					&& entityList.get(0).getAdd_date() != null) {
				request.setAttribute("add_date", entityList.get(0)
						.getAdd_date());
			}
		}
		request.setAttribute("entityList", entityList);
		// 遍历年份，取前10年
		ArrayList<String> yearList = new ArrayList<String>();

		for (int y = 0; y < 10; y++) {
			yearList.add((Integer.valueOf(formaty.format(now)) - y) + "");
		}
		request.setAttribute("yearList", yearList);

		List<KonkaDept> konkaDeptList = super.getDeptInfoListWithOutLimit(
				mapping, form, request, response);
		request.setAttribute("konkaDeptList", konkaDeptList);

		return mapping.findForward("list");
	}

	/**
	 * 客户库存预警
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward custRepertoryReportList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		setNaviStringToRequestScope(form, request);
		super.getModPopeDom(form, request);

		PeProdUser ui = (PeProdUser) super.getSessionAttribute(request,
				Constants.USER_INFO);

		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");

		String dept_id = (String) dynaBean.get("dept_id");// 分公司
		String l4_dept_id = (String) dynaBean.get("l4_dept_id");// 部门
		String l5_dept_id = (String) dynaBean.get("l5_dept_id");// 经办名称
		String r3_code_like = (String) dynaBean.get("r3_code_like");
		String customer_name_like = (String) dynaBean.get("customer_name_like");
		// 一级客户类型
		String customer_type1 = (String) dynaBean.get("v_customer_type1");
		// 二级客户类型
		String customer_type2 = (String) dynaBean.get("v_customer_type2");
		String ywy_user_name_like = (String) dynaBean.get("ywy_user_name_like");
		String goods_name_like = (String) dynaBean.get("goods_name_like");
		String ls_lock = (String) dynaBean.get("ls_lock");
		String goods_name_type = (String) dynaBean.get("goods_name_type");
		String excel_to_all = (String) dynaBean.get("excel_to_all");
		String yujing = (String) dynaBean.get("yujing");
		String type = (String) dynaBean.get("type");// 0:客户库存汇总 1:月度库存

		String weekDayTime = (String) dynaBean.get("weekDayTime");
		String weekNum = (String) dynaBean.get("weekNum");

		JStocksSummary entity = new JStocksSummary();

		if (StringUtils.isNotBlank(dept_id)) {
			entity.getMap().put("dept_id", Integer.valueOf(dept_id.trim()));
			dynaBean.set("dept_id", dept_id.trim());
		}
		if (StringUtils.isNotBlank(l4_dept_id)) {
			entity.getMap().put("l4_dept_id",
					Integer.valueOf(l4_dept_id.trim()));
			dynaBean.set("l4_dept_id", l4_dept_id.trim());
		}
		if (StringUtils.isNotBlank(l5_dept_id)) {
			entity.getMap().put("l5_dept_id",
					Integer.valueOf(l5_dept_id.trim()));
			dynaBean.set("l5_dept_id", l5_dept_id.trim());
		}
		if (StringUtils.isBlank(weekNum)) {
			KonkaBaseTypeData taskType = new KonkaBaseTypeData();
			taskType.setType_id(100020L);
			taskType = super.getFacade().getKonkaBaseTypeDataService()
					.getKonkaBaseTypeData(taskType);
			if (null != taskType && null != taskType.getField1()) {
				entity.getMap().put("weekNum",
						Integer.valueOf(taskType.getField1()));
				weekNum = taskType.getField1();
			}
			dynaBean.set("weekNum", weekNum);
		} else {
			entity.getMap().put("weekNum", Integer.valueOf(weekNum));
			dynaBean.set("weekNum", weekNum);
		}

		if (StringUtils.isBlank(weekDayTime)) {
			DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.WEEK_OF_YEAR, -Integer.valueOf(weekNum)
					.intValue());
			String time = df2.format(calendar.getTime());
			entity.getMap().put("weekDayTime", time);
			dynaBean.set("weekDayTime", weekDayTime);
		} else {
			entity.getMap().put("weekDayTime", weekDayTime);
			dynaBean.set("weekDayTime", weekDayTime);
		}

		if (StringUtils.isNotBlank(customer_name_like)) {
			entity.getMap()
					.put("customer_name_like", customer_name_like.trim());
			dynaBean.set("customer_name_like", customer_name_like);
		}
		if (StringUtils.isNotBlank(r3_code_like)) {
			entity.getMap().put("r3_code_like", r3_code_like.trim());
		}
		// 添加客户类型筛选条件
		if (StringUtils.isNotBlank(customer_type2)) {
			entity.getMap().put("cus_type2", customer_type2);
			dynaBean.set("v_customer_type2", customer_type2);
		} else {
			if (StringUtils.isNotBlank(customer_type1)) {
				entity.getMap().put("cus_type1", customer_type1);
				dynaBean.set("v_customer_type1", customer_type1);
			}
		}
		if (StringUtils.isNotBlank(goods_name_like))
			entity.getMap().put("goods_name_like", goods_name_like.trim());

		if (StringUtils.isNotBlank(ywy_user_name_like))
			entity.getMap()
					.put("ywy_user_name_like", ywy_user_name_like.trim());

		if (StringUtils.isNotBlank(ls_lock)) {
			entity.getMap().put("ls_lock", Integer.valueOf(ls_lock));
			dynaBean.set("ls_lock", ls_lock);
		}
		if (StringUtils.isNotBlank(goods_name_type)) {
			entity.getMap().put("goods_name_type", goods_name_type);
			dynaBean.set("goods_name_type", goods_name_type);
		}
		if ("1".equals(yujing)) {
			entity.getMap().put("upAvg", 1);
			dynaBean.set("yujing", "1");
		} else if ("2".equals(yujing)) {
			entity.getMap().put("downAvg", 2);
			dynaBean.set("yujing", "2");
		}

		// 数据级别判断开始
		Long __dept_id = ui.getDept_id(); // 默认为当前用户所在部门
		int max_dlevel = super.getMaxDLevel(ui.getId()); // 获取当前用户的最高可视部门级别
		logger.info("Max level : {}", max_dlevel);
		request.setAttribute("max_dlevel", max_dlevel);
		switch (max_dlevel) {
		case 9:
			// 集团及以下部门可见
			__dept_id = 0L; // 0表示部门根节点，即“多媒体事业本部”

			// 分公司列表
			KonkaDept kd = new KonkaDept();
			kd.setDept_type(3);
			List<KonkaDept> kdList = super.getFacade().getKonkaDeptService()
					.getKonkaDeptList(kd);
			request.setAttribute("kdList", kdList);

			break;
		case 8:
			// 分公司及以下部门可见
			KonkaDept dept_fgs = super.getKonkaDeptForFgs(ui.getDept_id()); // 查询部门分公司
			if (null != dept_fgs && null != dept_fgs.getDept_sn()) {
				entity.getMap().put("dept_id_start", dept_fgs.getDept_id());
			}
			break;
		case 7:
			// 我所在的部门及以下部门可见
			__dept_id = ui.getDept_id(); // 默认为当前用户所在部门
			entity.getMap().put("dept_id_start", __dept_id);
			break;
		case 0:
			__dept_id = ui.getDept_id(); // 默认为当前用户所在部门
			entity.getMap().put("filter_by_ywy_id_eq", ui.getId());
			break;
		default:
			// 出错
		}
		entity.getMap().put("session_user_id", ui.getId());

		Long recordCount = super.getFacade().getJStocksSummaryService()
				.getCustRepertoryReportListCount(entity);
		pager.setPageSize(100);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		if (StringUtils.isNotBlank(excel_to_all)) {

			// 为“总部财务部”角色开放导出记录数限制
			PeRoleUser roleUser = new PeRoleUser();
			roleUser.setUser_id(ui.getId());
			List<PeRoleUser> roleUserList = getFacade().getPeRoleUserService()
					.getPeRoleUserList(roleUser);

			/*
			 * boolean is_exit = true; for(PeRoleUser t : roleUserList){
			 * if(t.getRole_id()==29){ is_exit = false; break; } } if(is_exit){
			 * if (recordCount > 30000) {// 导出数据不能超过30000条
			 * super.renderJavaScript(response,
			 * "alert('导出记录不能超过30000条，请缩小查找范围！');history.back();"); return null;
			 * } }
			 */
			if (recordCount > 30000) {// 导出数据不能超过30000条
				super.renderJavaScript(response,
						"alert('导出记录不能超过30000条，请缩小查找范围！');history.back();");
				return null;
			}
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename="
					+ EncryptUtils.encodingFileName("客户库存预警") + ".xls");
			entity.getRow().setFirst(0);
			entity.getRow().setCount(recordCount.intValue());
			List<Map<String, Object>> entityList1 = getFacade()
					.getJStocksSummaryService().getCustRepertoryReportList(
							entity);
			request.setAttribute("allList", entityList1);
			return new ActionForward(
					"/admin/JStocksSummary/reportCustRepertoryReportList.jsp");
		}
		// 客户类型
		KonkaCategory kc = new KonkaCategory();
		kc.setC_type(10);
		kc.setIs_del(0);
		request.setAttribute("konkaCategoryList", super.getFacade()
				.getKonkaCategoryService().getKonkaCategoryList(kc));

		List<Map<String, Object>> entityList = super.getFacade()
				.getJStocksSummaryService().getCustRepertoryReportList(entity);
		request.setAttribute("entityList", entityList);

		return new ActionForward(
				"/admin/JStocksSummary/custRepertoryReportList.jsp");
	}

	/**
	 * 客户型号统计报表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward custGoodsNameReportList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		setNaviStringToRequestScope(form, request);
		super.getModPopeDom(form, request);

		PeProdUser user = (PeProdUser) super.getSessionAttribute(request,
				Constants.USER_INFO);

		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String isfirst = (String) dynaBean.get("isfirst");// 第一次进来
		String dept_id = (String) dynaBean.get("dept_id");// 分公司

		String begin_year = (String) dynaBean.get("begin_year");// 开始年
		String begin_month = (String) dynaBean.get("begin_month");// 开始月

		String end_year = (String) dynaBean.get("end_year");// 结束年
		String end_month = (String) dynaBean.get("end_month");// 结束月

		String r3_code_like = (String) dynaBean.get("r3_code_like");// 客户编码
		String customer_name_like = (String) dynaBean.get("customer_name_like");// 客户名称
		// 一级客户类型
		String customer_type1 = (String) dynaBean.get("v_customer_type1");// 客户大类型
		// 二级客户类型
		String customer_type2 = (String) dynaBean.get("v_customer_type2");// 客户小类型
		String user_name_like = (String) dynaBean.get("user_name_like");// 业务员
		String goods_name_like = (String) dynaBean.get("goods_name_like");// 型号
		String excel_to_all = (String) dynaBean.get("excel_to_all");// 导出
		JStocksSummary entity = new JStocksSummary();

		String begin_hhmmss = " 00:00:00";
		String end_hhmmss = " 23:59:59";
		if (StringUtils.isNotBlank(dept_id)) {
			entity.getMap().put("dept_id", Integer.valueOf(dept_id.trim()));
			dynaBean.set("dept_id", dept_id.trim());
		}
		if (StringUtils.isNotBlank(begin_year)) {
			dynaBean.set("begin_year", begin_year);
		}
		if (StringUtils.isNotBlank(begin_month)) {
			dynaBean.set("begin_month", begin_month);
		}
		if (StringUtils.isNotBlank(end_year)) {
			dynaBean.set("end_year", end_year);
		}
		if (StringUtils.isNotBlank(end_month)) {
			dynaBean.set("end_month", end_month);
		}

		String init_begin_time = "";
		String init_end_time = "";

		String come_begin_time = "";
		String come_end_time = "";

		String out_begin_time = "";
		String out_end_time = "";

		String dynamic_init_begin_time = "";
		String dynamic_init_end_time = "";

		String dynamic_come_begin_time = "";
		String dynamic_come_end_time = "";

		String dynamic_out_begin_time = "";
		String dynamic_out_end_time = "";

		Calendar calendar = Calendar.getInstance();

		if (StringUtils.isEmpty(isfirst)) {
			entity.getMap().put("isCurrMonth", "isCurrMonth");
			// entity.getMap().put("isNotCurrMonth", "isNotCurrMonth");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			init_begin_time = calendar.get(Calendar.YEAR) + "-"
					+ (calendar.get(Calendar.MONTH) + 1) + "-" + "01"
					+ begin_hhmmss;
			init_end_time = sdf.format(calendar.getTime());

			// 取第一个月的
			entity.getMap().put("dynamic_init_begin_time", init_begin_time);
			entity.getMap().put("dynamic_init_end_time", init_end_time);

			// 去全部时间段的
			entity.getMap().put("dynamic_come_begin_time", init_begin_time);
			entity.getMap().put("dynamic_come_end_time", init_end_time);

			// 去最后一个月的
			entity.getMap().put("dynamic_out_begin_time", init_begin_time);
			entity.getMap().put("dynamic_out_end_time", init_end_time);

			dynaBean.set("begin_year", "" + calendar.get(Calendar.YEAR));
			dynaBean.set("begin_month", "" + (calendar.get(Calendar.MONTH) + 1));
			dynaBean.set("end_year", "" + calendar.get(Calendar.YEAR));
			dynaBean.set("end_month", "" + (calendar.get(Calendar.MONTH) + 1));

			int total_day = daysBetween(init_begin_time, init_end_time) + 1;
			entity.getMap().put("total_num", total_day);
			dynaBean.set("isfirst", "isfirst");
		} else {

			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String curyear = "" + calendar.get(Calendar.YEAR);
			String curmonth = (calendar.get(Calendar.MONTH) + 1) > 9 ? ""
					+ (calendar.get(Calendar.MONTH) + 1) : "0"
					+ (calendar.get(Calendar.MONTH) + 1);

			calendar.set(Integer.valueOf(end_year),
					Integer.valueOf(end_month) - 1, Integer.valueOf("01"));
			calendar.set(Calendar.MONTH, 1);
			come_end_time = sdf1.format(calendar.getTime());

			// 上月 年 月
			String up_year = "" + calendar.get(Calendar.YEAR);
			String up_month = (calendar.get(Calendar.MONTH) + 1) > 9 ? ""
					+ (calendar.get(Calendar.MONTH) + 1) : "0"
					+ (calendar.get(Calendar.MONTH) + 1);

			/**
			 * 如果选择是当月
			 */
			if ((begin_year + begin_month).equals(end_year + end_month)
					&& Integer.valueOf(curmonth) == Integer.valueOf(end_month)) {
				entity.getMap().put("isCurrMonth", "isCurrMonth");
				// entity.getMap().put("isNotCurrMonth", "isNotCurrMonth");
				SimpleDateFormat sdf2 = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");

				init_begin_time = calendar.get(Calendar.YEAR) + "-"
						+ (calendar.get(Calendar.MONTH) + 1) + "-" + "01"
						+ begin_hhmmss;
				init_end_time = sdf2.format(calendar.getTime());

				// 取第一个月的
				entity.getMap().put("dynamic_init_begin_time", init_begin_time);
				entity.getMap().put("dynamic_init_end_time", init_end_time);

				// 去全部时间段的
				entity.getMap().put("dynamic_come_begin_time", init_begin_time);
				entity.getMap().put("dynamic_come_end_time", init_end_time);

				// 去最后一个月的
				entity.getMap().put("dynamic_out_begin_time", init_begin_time);
				entity.getMap().put("dynamic_out_end_time", init_end_time);

				int total_day = daysBetween(init_begin_time, init_end_time) + 1;
				entity.getMap().put("total_num", total_day);

			}
			/**
			 * 如果的区间 结束不是月末
			 */
			if (!(curyear + Integer.valueOf(curmonth)).equals(end_year
					+ Integer.valueOf(end_month))) {
				// entity.getMap().put("isCurrMonth", "isCurrMonth");
				entity.getMap().put("isNotCurrMonth", "isNotCurrMonth");

				// 开始时间和结束时间是否是同一个月
				boolean beginequalsend = (begin_year + begin_month)
						.equals(end_year + end_month);

				// 初始化数据时间段
				init_begin_time = begin_year + "-" + begin_month + "-" + "01"
						+ begin_hhmmss;
				int init_max1 = getMaxDay(Integer.valueOf(begin_year),
						Integer.valueOf(begin_month));
				init_end_time = begin_year + "-" + begin_month + "-"
						+ init_max1 + end_hhmmss;
				// 进数据时间段
				come_begin_time = begin_year + "-" + begin_month + "-" + "01"
						+ begin_hhmmss;
				int comm_max1 = getMaxDay(Integer.valueOf(end_year),
						Integer.valueOf(end_month));
				if (beginequalsend) {
					come_end_time = begin_year + "-" + begin_month + "-"
							+ comm_max1 + end_hhmmss;
				} else {
					come_end_time = end_year + "-" + end_month + "-"
							+ comm_max1 + end_hhmmss;
				}

				// 销数据时间段
				out_begin_time = begin_year + "-" + begin_month + "-" + "01"
						+ begin_hhmmss;
				int out_max1 = getMaxDay(Integer.valueOf(end_year),
						Integer.valueOf(end_month));
				if (beginequalsend) {
					out_end_time = begin_year + "-" + begin_month + "-"
							+ comm_max1 + end_hhmmss;
				} else {
					out_end_time = end_year + "-" + end_month + "-" + out_max1
							+ end_hhmmss;
				}

				// 取第一个月的
				entity.getMap().put("init_begin_time", init_begin_time);
				entity.getMap().put("init_end_time", init_end_time);

				// 去全部时间段的
				entity.getMap().put("come_begin_time", come_begin_time);
				entity.getMap().put("come_end_time", come_end_time);

				// 去最后一个月的
				entity.getMap().put("out_begin_time", out_begin_time);
				entity.getMap().put("out_end_time", out_end_time);

				int total_day = daysBetween(init_begin_time, out_end_time) + 1;
				entity.getMap().put("total_num", total_day);
			}

			/**
			 * 如果的区间 结束是月末
			 */
			if (curyear.equals(end_year)
					&& Integer.valueOf(curmonth) == Integer.valueOf(end_month)) {

				entity.getMap().put("isNotCurrMonth", "isNotCurrMonth");
				// -------------------------月度数据查询--------------------------------------------
				// 初始化数据时间段
				init_begin_time = begin_year + "-" + begin_month + "-" + "01"
						+ begin_hhmmss;
				int init_max1 = getMaxDay(Integer.valueOf(begin_year),
						Integer.valueOf(begin_month));
				init_end_time = begin_year + "-" + begin_month + "-"
						+ init_max1 + end_hhmmss;

				// 进数据时间段
				come_begin_time = begin_year + "-" + begin_month + "-" + "01"
						+ begin_hhmmss;
				int comm_max1 = getMaxDay(Integer.valueOf(up_year),
						Integer.valueOf(up_month));
				come_end_time = up_year + "-" + up_month + "-" + comm_max1
						+ end_hhmmss;
				// 销数据时间段
				out_begin_time = begin_year + "-" + begin_month + "-" + "01"
						+ begin_hhmmss;
				int out_max1 = getMaxDay(Integer.valueOf(up_year),
						Integer.valueOf(up_month));
				out_end_time = up_year + "-" + up_month + "-" + out_max1
						+ end_hhmmss;

				// 取第一个月的
				entity.getMap().put("init_begin_time", init_begin_time);
				entity.getMap().put("init_end_time", init_end_time);

				// 去全部时间段的
				entity.getMap().put("come_begin_time", come_begin_time);
				entity.getMap().put("come_end_time", come_end_time);

				// 取最后一个月的
				entity.getMap().put("out_begin_time", out_begin_time);
				entity.getMap().put("out_end_time", out_end_time);
				// ---------------------------------------------------------------------

				// -----------------------------实时数据----------------------------------------
				entity.getMap().put("isCurrMonth", "isCurrMonth");

				SimpleDateFormat sdf2 = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				Date now1 = new Date();
				// 初始化数据时间段
				dynamic_init_begin_time = end_year + "-" + end_month + "-"
						+ "01" + begin_hhmmss;
				dynamic_init_end_time = sdf2.format(now1);// 不需要的数据
				// 进数据时间段
				dynamic_come_begin_time = end_year + "-" + end_month + "-"
						+ "01" + begin_hhmmss;
				dynamic_come_end_time = sdf2.format(now1);// 当前时间
				// 销数据时间段
				dynamic_out_begin_time = end_year + "-" + end_month + "-"
						+ "01" + begin_hhmmss;
				dynamic_out_end_time = sdf2.format(now1);// 当前时间

				// 取第一个月的
				// entity.getMap().put("dynamic_init_begin_time",
				// dynamic_init_begin_time);
				// entity.getMap().put("dynamic_init_end_time",
				// dynamic_init_end_time);

				// 去全部时间段的
				entity.getMap().put("dynamic_come_begin_time",
						dynamic_come_begin_time);
				entity.getMap().put("dynamic_come_end_time",
						dynamic_come_end_time);

				// 去最后一个月的
				entity.getMap().put("dynamic_out_begin_time",
						dynamic_out_begin_time);
				entity.getMap().put("dynamic_out_end_time",
						dynamic_out_end_time);
				int total_day = daysBetween(init_begin_time,
						dynamic_come_end_time) + 1;
				entity.getMap().put("total_num", total_day);
			}
		}

		if (StringUtils.isNotBlank(customer_name_like)) {
			entity.getMap()
					.put("customer_name_like", customer_name_like.trim());
			dynaBean.set("customer_name_like", customer_name_like);
		}
		if (StringUtils.isNotBlank(r3_code_like)) {
			entity.getMap().put("r3_code_like", r3_code_like.trim());
			dynaBean.set("r3_code_like", r3_code_like);
		}
		// 添加客户类型筛选条件
		if (StringUtils.isNotBlank(customer_type2)) {
			entity.getMap().put("cus_type2", customer_type2);
			dynaBean.set("v_customer_type2", customer_type2);
		} else {
			if (StringUtils.isNotBlank(customer_type1)) {
				entity.getMap().put("cus_type1", customer_type1);
				dynaBean.set("v_customer_type1", customer_type1);
			}
		}
		if (StringUtils.isNotBlank(goods_name_like)) {
			entity.getMap().put("goods_name_like", goods_name_like.trim());
			dynaBean.set("goods_name_like", goods_name_like);
		}

		if (StringUtils.isNotBlank(user_name_like)) {
			entity.getMap().put("user_name_like", user_name_like.trim());
		}
		// 数据级别判断开始
		Long _dept_id = 0L;
		int max_dlevel = super.getMaxDLevel(user.getId()); // 获取当前用户的最高可视部门级别
		logger.info("Max level : {}", max_dlevel);
		switch (max_dlevel) {
		case 9:
			// 集团及以下部门可见
			_dept_id = 0L; // 0表示部门根节点，即“多媒体事业本部”
			break;
		case 8:
			// 分公司及以下部门可见

			KonkaDept dept_fgs = super.getKonkaDeptForFgs(user.getDept_id()); // 查询部门分公司
			if (null != dept_fgs && null != dept_fgs.getDept_id()) {
				_dept_id = dept_fgs.getDept_id(); // 分公司部门ID
				entity.getMap().put("dept_id_start", _dept_id);
				// entity.getMap().put("fgs_dept_value", _dept_id);
			}
			break;
		case 7:
			// 我所在的部门及以下部门可见
			_dept_id = user.getDept_id(); // 默认为当前用户所在部门
			entity.getMap().put("dept_id_start", _dept_id);
			break;
		case 0:
			_dept_id = user.getDept_id(); // 默认为当前用户所在部门
			// entity.getMap().put("dept_id_start", __dept_id);
			entity.getMap().put("filter_by_ywy_id_eq", user.getId());
			break;
		default:
			// 出错
		}
		entity.getMap().put("session_user_id", user.getId());

		Long recordCount = super.getFacade().getJStocksSummaryService()
				.getCustGoodsNameReportCount(entity);
		pager.setPageSize(100);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		if (StringUtils.isNotBlank(excel_to_all)) {

			// 为“总部财务部”角色开放导出记录数限制
			PeRoleUser roleUser = new PeRoleUser();
			roleUser.setUser_id(user.getId());

			if (recordCount > 30000) {// 导出数据不能超过30000条
				super.renderJavaScript(response,
						"alert('导出记录不能超过30000条，请缩小查找范围！');history.back();");
				return null;
			}
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename="
					+ EncryptUtils.encodingFileName("客户型号统计报表") + ".xls");
			entity.getRow().setFirst(0);
			entity.getRow().setCount(recordCount.intValue());
			List<Map<String, Object>> entityList1 = getFacade()
					.getJStocksSummaryService().getCustGoodsNameReportList(
							entity);
			request.setAttribute("allList", entityList1);
			return new ActionForward(
					"/admin/JStocksSummary/reportCustRepertoryReportList.jsp");
		}
		// 客户类型
		KonkaCategory kc = new KonkaCategory();
		kc.setC_type(10);
		kc.setIs_del(0);
		request.setAttribute("konkaCategoryList", super.getFacade()
				.getKonkaCategoryService().getKonkaCategoryList(kc));
		// 分公司
		List<KonkaDept> deptList = super.getDeptInfoListWithOutLimit(mapping,
				form, request, response);
		request.setAttribute("deptList", deptList);

		List<Map<String, Object>> entityList = super.getFacade()
				.getJStocksSummaryService().getCustGoodsNameReportList(entity);
		request.setAttribute("entityList", entityList);

		return new ActionForward(
				"/admin/JStocksSummary/custGoodsNameReportList.jsp");
	}

	/**
	 * 计算年月的最大天数
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public int getMaxDay(int year, int month) {
		int maxDay = 0;
		int day = 1;
		/**
		 * 与其他语言环境敏感类一样，Calendar 提供了一个类方法 getInstance， 以获得此类型的一个通用的对象。Calendar 的
		 * getInstance 方法返回一 个 Calendar 对象，其日历字段已由当前日期和时间初始化：
		 */
		Calendar calendar = Calendar.getInstance();
		/**
		 * 实例化日历各个字段,这里的day为实例化使用
		 */
		calendar.set(year, month - 1, day);
		/**
		 * Calendar.Date:表示一个月中的某天 calendar.getActualMaximum(int
		 * field):返回指定日历字段可能拥有的最大值
		 */
		maxDay = calendar.getActualMaximum(Calendar.DATE);
		return maxDay;
	}

	/**
	 * 字符串的日期格式的计算
	 */
	public static int daysBetween(String smdate, String bdate)
			throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(sdf.parse(smdate));
		long time1 = cal.getTimeInMillis();
		cal.setTime(sdf.parse(bdate));
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);
		return Integer.parseInt(String.valueOf(between_days));
	}

	/**
	 * 计算两个日期之间相差的天数
	 * 
	 * @param smdate
	 *            较小的时间
	 * @param bdate
	 *            较大的时间
	 * @return 相差天数
	 * @throws ParseException
	 */
	public static int daysBetween(Date smdate, Date bdate)
			throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		smdate = sdf.parse(sdf.format(smdate));
		bdate = sdf.parse(sdf.format(bdate));
		Calendar cal = Calendar.getInstance();
		cal.setTime(smdate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(bdate);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);
		return Integer.parseInt(String.valueOf(between_days));
	}

	public ActionForward getJBDataBydeptCode(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String dept_sn = (String) dynaBean.get("dept_sn"); // 分公司
		List<String> jbList = new ArrayList<String>();

		if (dept_sn != null && dept_sn.length() > 0) {

			List<KonkaDept> kklist = new ArrayList<KonkaDept>();

			KonkaDept kk2 = new KonkaDept();

			KonkaDept kk = new KonkaDept();
			kk.setIs_del(0);
			kk.setDept_sn(dept_sn);
			kklist = super.getFacade().getKonkaDeptService()
					.getKonkaDeptList(kk);
			if (kklist != null && kklist.size() == 1) {
				kk = kklist.get(0);
				kk2.getMap().put("fgs_dept_id", kk.getDept_id());
				kk2.setIs_del(0);
				kklist.clear();
				kklist = super.getFacade().getKonkaDeptService()
						.getKonkaDeptAndJbNameList(kk2);

				request.setAttribute("kkList", kklist);
			}

			for (KonkaDept x : kklist) {
				jbList.add(x.getDept_name());
			}
			super.renderJson(response, JSON.toJSONString(jbList));
		} else {
			super.renderJson(response, JSON.toJSONString(jbList));
		}

		return null;
	}

	/**
	 * 单个推送信息到手机端手机端信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward pushMessage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		PeProdUser user_info = (PeProdUser) super.getSessionAttribute(request,
				Constants.USER_INFO);

		Map<String, Object> allmap = new HashMap<String, Object>();
		String r3_code_like = (String) dynaBean.get("r3_code");// 客户编码
		String goods_name_like = (String) dynaBean.get("goods_name");// 商品新型号
		JStocksSummary entity = new JStocksSummary();
		KonkaBaseTypeData taskType = new KonkaBaseTypeData();
		taskType.setType_id(100020L);
		taskType = super.getFacade().getKonkaBaseTypeDataService()
				.getKonkaBaseTypeData(taskType);
		if (null != taskType && null != taskType.getField1()) {
			entity.getMap().put("weekNum",
					Integer.valueOf(taskType.getField1()));
			entity.getMap().put("r3_code_like", r3_code_like);// 客户编码
			entity.getMap().put("goods_name_like", goods_name_like);// 商品新型号
			entity.getRow().setFirst(0);
			entity.getRow().setCount(1);
			List<Map<String, Object>> entityList = super.getFacade()
					.getJStocksSummaryService()
					.getCustRepertoryReportList(entity);
			// 一个客户型号需要推送的数据
			for (int j = 0; j < entityList.size(); j++) {
				Map<String, Object> map = entityList.get(j);
				String user_id = "" + map.get("USER_ID");
				String cust_name = "" + map.get("CUSTOMER_NAME");
				String r3_code = "" + map.get("R3_CODE");
				String dept_id = "" + map.get("L4_DEPT_ID");
				String goods_name = "" + map.get("GOODS_NAME");
				String total_cur_num = "" + map.get("TOTLE_CUR_NUM");
				String avg_num = "" + map.get("AVG_NUM");
				String total_num = "" + map.get("TOTAL_NUM");

				boolean isNotBoth = false;
				String alert = "库存提醒";
				String title = "库存提醒";
				Integer type = 4;
				String url = "http://qdgl.konka.com/webservice/JStocksSummary.do?method=messageView1";
				String message = "";
				List<String> aliasList = new ArrayList<String>();// 推送用户列表

				if (Double.valueOf(total_cur_num) < Double.valueOf(avg_num)) {
					isNotBoth = true;
					message = "客户 ：" + cust_name + "\n型号 ：" + goods_name
							+ " 库存紧张 赶紧补货！" + "\n当前剩余库存 ：" + total_cur_num
							+ " 台！";
				}
				if (Double.valueOf(total_cur_num) > Double.valueOf(total_num)) {
					isNotBoth = true;
					message = "客户 ：" + cust_name + "\n型号 ：" + goods_name
							+ " 库存过于充足！" + "\n当前剩余库存 ：" + total_cur_num + " 台！";
				}

				if (StringUtils.isNotBlank(user_id)) {
					// 业务员总经理查找
					PeProdUser user = new PeProdUser();
					user.getMap().put("dept_id", dept_id);
					user.getMap().put("role_id", 34);
					List<PeProdUser> userlist = super.getFacade()
							.getPeProdUserService()
							.getPeProdUserByDeptIdAndRoleIdResult(user);
					aliasList.add(user_id);
					for (PeProdUser peProdUser : userlist) {
						aliasList.add("" + peProdUser.getId());
					}
					/**
					 * android 端推送消息
					 */
					for (String userId : aliasList) {
						KonkaOrderMessageProcess konKaOrderMessageProcess = new KonkaOrderMessageProcess();
						konKaOrderMessageProcess.setC_user_id(Long
								.valueOf(user_id));
						konKaOrderMessageProcess.setBc_user_id(Long
								.valueOf(userId));// 被催办人用户ID
						konKaOrderMessageProcess.setAdd_date(new Date());// 添加时间
						konKaOrderMessageProcess.setState(0);// 状态
						konKaOrderMessageProcess.setMessage_type(4);// 类型（0订单，1。。。2。。。）
						konKaOrderMessageProcess.setTitle("库存提醒");// 标题
						konKaOrderMessageProcess.setRemark(message);// 备注
						konKaOrderMessageProcess.setIs_send(0);// 是否推送
						super.getFacade()
								.getKonkaOrderMessageProcessService()
								.createKonkaOrderMessageProcess(
										konKaOrderMessageProcess);
					}
					if (null != aliasList && aliasList.size() > 0) {
						url += "&bc_user_id=" + user_id;
						super.getFacade()
								.getIosPushMessageService()
								.SendMessage(alert, title, type, url, message,
										aliasList);
					}
				}
			}
		}
		allmap.put("res", "已推送");
		JSONArray jsonArray = JSONArray.fromObject(allmap);
		int start = jsonArray.toString().indexOf("[");
		int end = jsonArray.toString().lastIndexOf("}");
		String jsonStr = jsonArray.toString().substring(start + 1, end + 1);

		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(jsonStr);
		out.flush();
		out.close();
		return null;
	}

	/**
	 * 批量推送信息到手机端手机端信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward pushMessages(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		PeProdUser user_info = (PeProdUser) super.getSessionAttribute(request,
				Constants.USER_INFO);

		Pager pager = (Pager) dynaBean.get("pager");
		Map<String, Object> allmap = new HashMap<String, Object>();
		String[] pks = request.getParameterValues("pks");// 拜访类型

		if (pks == null || pks.length <= 0) {
			super.renderJavaScript(response,
					"alert('请选择一个推送选项！');history.back();");
			return null;
		}
		// 多选的 需要推送的客户新号
		for (String pksStr : pks) {
			String[] tempStringArry = pksStr.split("#");
			String r3_code_like = tempStringArry[0];
			String goods_name_like = tempStringArry[1];
			JStocksSummary entity = new JStocksSummary();
			KonkaBaseTypeData taskType = new KonkaBaseTypeData();
			taskType.setType_id(100020L);
			taskType = super.getFacade().getKonkaBaseTypeDataService()
					.getKonkaBaseTypeData(taskType);
			if (null != taskType && null != taskType.getField1()) {
				entity.getMap().put("weekNum",
						Integer.valueOf(taskType.getField1()));
				entity.getMap().put("r3_code_like", r3_code_like);// 客户编码
				entity.getMap().put("goods_name_like", goods_name_like);// 商品新型号
				entity.getRow().setFirst(0);
				entity.getRow().setCount(1);
				List<Map<String, Object>> entityList = super.getFacade()
						.getJStocksSummaryService()
						.getCustRepertoryReportList(entity);
				// 一个客户型号需要推送的数据
				for (int j = 0; j < entityList.size(); j++) {
					Map<String, Object> map = entityList.get(j);
					String user_id = "" + map.get("USER_ID");
					String cust_name = "" + map.get("CUSTOMER_NAME");
					String r3_code = "" + map.get("R3_CODE");
					String dept_id = "" + map.get("L4_DEPT_ID");
					String goods_name = "" + map.get("GOODS_NAME");
					String total_cur_num = "" + map.get("TOTLE_CUR_NUM");
					String avg_num = "" + map.get("AVG_NUM");
					String total_num = "" + map.get("TOTAL_NUM");

					boolean isNotBoth = false;
					String alert = "库存提醒";
					String title = "库存提醒";
					Integer type = 4;
					String url = "http://qdgl.konka.com/webservice/JStocksSummary.do?method=messageView1";
					String message = "";
					List<String> aliasList = new ArrayList<String>();// 推送用户列表

					if (Double.valueOf(total_cur_num) < Double.valueOf(avg_num)) {
						isNotBoth = true;
						message = "客户 ：" + cust_name + "\n型号 ：" + goods_name
								+ " 库存紧张 赶紧补货！" + "\n当前剩余库存 ：" + total_cur_num
								+ " 台！";
					}
					if (Integer.valueOf(total_cur_num) > Integer
							.valueOf(total_num)) {
						isNotBoth = true;
						message = "客户 ：" + cust_name + "\n型号 ：" + goods_name
								+ " 库存过于充足！" + "\n当前剩余库存 ：" + total_cur_num
								+ " 台！";
					}

					if (StringUtils.isNotBlank(user_id)) {
						// 业务员总经理查找
						PeProdUser user = new PeProdUser();
						user.getMap().put("dept_id", dept_id);
						user.getMap().put("role_id", 34);
						List<PeProdUser> userlist = super.getFacade()
								.getPeProdUserService()
								.getPeProdUserByDeptIdAndRoleIdResult(user);
						aliasList.add(user_id);
						for (PeProdUser peProdUser : userlist) {
							aliasList.add("" + peProdUser.getId());
						}
						/**
						 * android 端推送消息
						 */
						for (String userId : aliasList) {
							KonkaOrderMessageProcess konKaOrderMessageProcess = new KonkaOrderMessageProcess();
							konKaOrderMessageProcess.setC_user_id(Long
									.valueOf(user_id));
							konKaOrderMessageProcess.setBc_user_id(Long
									.valueOf(userId));// 被催办人用户ID
							konKaOrderMessageProcess.setAdd_date(new Date());// 添加时间
							konKaOrderMessageProcess.setState(0);// 状态
							konKaOrderMessageProcess.setMessage_type(4);// 类型（0订单，1。。。2。。。）
							konKaOrderMessageProcess.setTitle("库存提醒");// 标题
							konKaOrderMessageProcess.setRemark(message);// 备注
							konKaOrderMessageProcess.setIs_send(0);// 是否推送
							super.getFacade()
									.getKonkaOrderMessageProcessService()
									.createKonkaOrderMessageProcess(
											konKaOrderMessageProcess);
						}
						if (null != aliasList && aliasList.size() > 0) {
							url += "&bc_user_id=" + user_id;
							super.getFacade()
									.getIosPushMessageService()
									.SendMessage(alert, title, type, url,
											message, aliasList);
						}
					}
				}
			}
		}
		return custRepertoryReportList(mapping, form, request, response);
	}

}
