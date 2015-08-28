package com.ebiz.mmt.web.struts.manager.leader;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.DecimalFormat;
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
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.JStocksSummary;
import com.ebiz.mmt.domain.KonkaCategory;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.domain.VOrgOfDept;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.util.EncryptUtils;
import com.ebiz.ssi.web.struts.bean.Pager;
import com.github.abel533.echarts.AxisPointer;
import com.github.abel533.echarts.Legend;
import com.github.abel533.echarts.Title;
import com.github.abel533.echarts.Toolbox;
import com.github.abel533.echarts.Tooltip;
import com.github.abel533.echarts.axis.Axis;
import com.github.abel533.echarts.axis.AxisLabel;
import com.github.abel533.echarts.axis.ValueAxis;
import com.github.abel533.echarts.code.AxisType;
import com.github.abel533.echarts.code.LineType;
import com.github.abel533.echarts.code.PointerType;
import com.github.abel533.echarts.code.Trigger;
import com.github.abel533.echarts.feature.Feature;
import com.github.abel533.echarts.style.LineStyle;
import com.github.abel533.echarts.util.EnhancedOption;
import com.ibm.db2.jcc.b.re;

/**
 * 库存周转分析
 * 
 * @author Wang Hao
 */
public class JcfxReportKczzfxAction extends BaseAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}
		// 默认选择当前年月
		Date date = new Date(); // 当月使用
		SimpleDateFormat formaty2 = new SimpleDateFormat("yyyy-MM");
		String year_month[] = formaty2.format(date).split("-");
		dynaBean.set("year", year_month[0]);
		dynaBean.set("month", year_month[1]);

		// 初始化默认当前人所在的分公司
		KonkaDept fgs = new KonkaDept();
		fgs.setDept_id(user.getDept_id());
		fgs = super.getFacade().getKonkaDeptService().getKonkaDept(fgs);
		if (null != fgs && null != fgs.getDept_id()) {
			// dynaBean.set("branch_area_name_2", ""+fgs.getDept_sn());
		}
		String is_bi = (String) dynaBean.get("is_bi");
		if (StringUtils.isNotBlank(is_bi)) {
			request.setAttribute("is_bi", is_bi);
		}
		return this.view(mapping, form, request, response);
	}

	/**
	 * 财务客户大类部库存周转
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String mod_id = (String) dynaBean.get("mod_id");
		String year = (String) dynaBean.get("year");
		String month = (String) dynaBean.get("month");
		String branch_area_name_2 = (String) dynaBean.get("branch_area_name_2");
		String excel_all = (String) dynaBean.get("excel_all");
		SimpleDateFormat formaty2 = new SimpleDateFormat("yyyy-MM");
		Date date = new Date(); // 当月使用
		String is_cur_month = "yes";// 不为空表示是单月 // 为空表示不是当月

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}
		boolean flag = false;
		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(user.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);
		for (PeRoleUser peRoleUser : peRoleUserList) {
			if (peRoleUser.getRole_id().equals(new Long(60))) {
				flag = true;
			}
		}

		JStocksSummary entity = new JStocksSummary();
		// 当月
		if (StringUtils.isNotBlank(year) && StringUtils.isNotBlank(month)) {
			entity.getMap().put("cur_month", year + month);
			dynaBean.set("year", year);
			dynaBean.set("month", month);

			if ((year + "-" + month).equals(formaty2.format(date))) {
				is_cur_month = "yes";
				entity.getMap().put("is_cur_month", is_cur_month);
				dynaBean.set("type", "0");
			} else {
				is_cur_month = null;
				dynaBean.set("type", "1");
			}

		} else {
			String year_month[] = formaty2.format(date).split("-");
			entity.getMap().put("cur_month", year_month[0] + year_month[1]);
			dynaBean.set("year", year_month[0]);
			dynaBean.set("month", year_month[1]);
			is_cur_month = "yes";
			entity.getMap().put("is_cur_month", is_cur_month);
			dynaBean.set("type", "0");
		}

		// 分公司
		if (StringUtils.isNotBlank(branch_area_name_2)) {
			entity.getMap().put("branch_area_name_2", branch_area_name_2);
			dynaBean.set("branch_area_name_2", branch_area_name_2);
		}
		// 查看自己的
		if (null != user.getId()) {
			entity.getMap().put("add_user_id", user.getId());
		}
		/*
		 * if (flag) {// 如果是业务员，只能看到自己的拜访记录 entity.setAdd_user_id(user.getId()); } else { Long _dept_id = 0L; int
		 * max_dlevel = super.getMaxDLevel(user.getId()); // 获取当前用户的最高可视部门级别 logger.info("Max level : {}", max_dlevel);
		 * switch (max_dlevel) { case 9: // 集团及以下部门可见 _dept_id = 0L; // 0表示部门根节点，即“多媒体事业本部” break; case 8: // 分公司及以下部门可见
		 * KonkaDept dept_fgs = super.getKonkaDeptForFgs(user.getDept_id()); // 查询部门分公司 if (null != dept_fgs && null !=
		 * dept_fgs.getDept_id()) { _dept_id = dept_fgs.getDept_id(); // 分公司部门ID entity.getMap().put("dept_id_start",
		 * _dept_id); // entity.getMap().put("fgs_dept_value", _dept_id); } break; case 7: // 我所在的部门及以下部门可见 _dept_id =
		 * user.getDept_id(); // 默认为当前用户所在部门 entity.getMap().put("dept_id_start", _dept_id); break; case 0: _dept_id =
		 * user.getDept_id(); // 默认为当前用户所在部门 // entity.getMap().put("dept_id_start", __dept_id);
		 * entity.getMap().put("filter_by_ywy_id_eq", user.getId()); break; default: // 出错 }
		 * entity.getMap().put("session_user_id", user.getId()); }
		 */

		List<Map<String, Object>> entityList = null;
		entityList = super.getFacade().getJStocksSummaryService().getJcfxParkhList(entity);
		if (entityList != null && entityList.size() > 0) {
			if (null != entityList.get(0) && null != entityList.get(0).get("ADD_DATE")) {
				request.setAttribute("add_date", entityList.get(0).get("ADD_DATE"));
			}
		}
		request.setAttribute("mod_id", mod_id);
		request.setAttribute("entityList", entityList);
		String is_bi = (String) dynaBean.get("is_bi");
		if (StringUtils.isNotBlank(is_bi)) {
			request.setAttribute("is_bi", is_bi);
		}
		/**
		 * 遍历年份，取前10年
		 */
		ArrayList<String> yearList = new ArrayList<String>();
		SimpleDateFormat formaty = new SimpleDateFormat("yyyy");
		for (int y = 0; y < 10; y++) {
			yearList.add((Integer.valueOf(formaty.format(date)) - y) + "");
		}
		request.setAttribute("yearList", yearList);

		/**
		 * 分公司
		 */
		dynaBean.set("isNotEpp", "isNotEpp");
		request.setAttribute("sybDeptInfoList", super.getDeptInfoListWithOutLimit(mapping, form, request, response));
		return mapping.findForward("view");
	}

	/**
	 * 财务客户小类部库存周转
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward view1(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String mod_id = (String) dynaBean.get("mod_id");
		String year = (String) dynaBean.get("year");
		String month = (String) dynaBean.get("month");
		String branch_area_name_2 = (String) dynaBean.get("branch_area_name_2");
		String par_index = (String) dynaBean.get("par_index");
		String excel_all = (String) dynaBean.get("excel_all");
		SimpleDateFormat formaty2 = new SimpleDateFormat("yyyy-MM");
		String is_cur_month = "yes";// 不为空表示是单月 // 为空表示不是当月
		Date date = new Date(); // 当月使用

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}
		boolean flag = false;
		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(user.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);
		for (PeRoleUser peRoleUser : peRoleUserList) {
			if (peRoleUser.getRole_id().equals(new Long(60))) {
				flag = true;
			}
		}
		JStocksSummary entity = new JStocksSummary();
		// 当月
		if (StringUtils.isNotBlank(year) && StringUtils.isNotBlank(month)) {
			entity.getMap().put("cur_month", year + month);
			dynaBean.set("year", year);
			dynaBean.set("month", month);

			if ((year + "-" + month).equals(formaty2.format(date))) {
				is_cur_month = "yes";
				entity.getMap().put("is_cur_month", is_cur_month);
				dynaBean.set("type", "0");
			} else {
				is_cur_month = null;
				dynaBean.set("type", "1");
			}
		} else {
			String year_month[] = formaty2.format(date).split("-");
			entity.getMap().put("cur_month", year_month[0] + year_month[1]);
			dynaBean.set("year", year_month[0]);
			dynaBean.set("month", year_month[1]);
			is_cur_month = "yes";
			entity.getMap().put("is_cur_month", is_cur_month);
			dynaBean.set("type", "0");
		}
		// 分公司
		if (StringUtils.isNotBlank(branch_area_name_2)) {
			entity.getMap().put("branch_area_name_2", branch_area_name_2);
			dynaBean.set("branch_area_name_2", branch_area_name_2);
		}

		// 父类型id
		if (StringUtils.isNotBlank(par_index)) {
			entity.getMap().put("par_index", par_index);
			dynaBean.set("par_index", par_index);
		}

		// 查看自己的
		if (null != user.getId()) {
			entity.getMap().put("add_user_id", user.getId());
		}
		/*
		 * if (flag) {// 如果是业务员，只能看到自己的拜访记录 entity.setAdd_user_id(user.getId()); } else { Long _dept_id = 0L; int
		 * max_dlevel = super.getMaxDLevel(user.getId()); // 获取当前用户的最高可视部门级别 logger.info("Max level : {}", max_dlevel);
		 * switch (max_dlevel) { case 9: // 集团及以下部门可见 _dept_id = 0L; // 0表示部门根节点，即“多媒体事业本部” break; case 8: // 分公司及以下部门可见
		 * KonkaDept dept_fgs = super.getKonkaDeptForFgs(user.getDept_id()); // 查询部门分公司 if (null != dept_fgs && null !=
		 * dept_fgs.getDept_id()) { _dept_id = dept_fgs.getDept_id(); // 分公司部门ID entity.getMap().put("dept_id_start",
		 * _dept_id); // entity.getMap().put("fgs_dept_value", _dept_id); } break; case 7: // 我所在的部门及以下部门可见 _dept_id =
		 * user.getDept_id(); // 默认为当前用户所在部门 entity.getMap().put("dept_id_start", _dept_id); break; case 0: _dept_id =
		 * user.getDept_id(); // 默认为当前用户所在部门 // entity.getMap().put("dept_id_start", __dept_id);
		 * entity.getMap().put("filter_by_ywy_id_eq", user.getId()); break; default: // 出错 }
		 * entity.getMap().put("session_user_id", user.getId()); }
		 */

		List<Map<String, Object>> entityList = null;

		entityList = super.getFacade().getJStocksSummaryService().getJcfxKhList(entity);
		if (entityList != null && entityList.size() > 0) {
			if (null != entityList.get(0) && null != entityList.get(0).get("ADD_DATE")) {
				request.setAttribute("add_date", entityList.get(0).get("ADD_DATE"));
			}
		}
		request.setAttribute("mod_id", mod_id);
		request.setAttribute("entityList", entityList);

		String is_bi = (String) dynaBean.get("is_bi");
		if (StringUtils.isNotBlank(is_bi)) {
			request.setAttribute("is_bi", is_bi);
		}
		/**
		 * 遍历年份，取前10年
		 */
		ArrayList<String> yearList = new ArrayList<String>();
		SimpleDateFormat formaty = new SimpleDateFormat("yyyy");
		for (int y = 0; y < 10; y++) {
			yearList.add((Integer.valueOf(formaty.format(date)) - y) + "");
		}
		request.setAttribute("yearList", yearList);

		/**
		 * 分公司
		 */
		dynaBean.set("isNotEpp", "isNotEpp");
		request.setAttribute("sybDeptInfoList", super.getDeptInfoListWithOutLimit(mapping, form, request, response));

		return new ActionForward("/leader/JcfxReportKczzfx/view1.jsp");
	}

	/**
	 * 财务客户小类部库存周转 AJAX图形化界面显示 注意：细客户周转天数图形
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward ajaxview1(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String year = (String) dynaBean.get("year");
		String month = (String) dynaBean.get("month");
		String branch_area_name_2 = (String) dynaBean.get("branch_area_name_2");
		String par_index = (String) dynaBean.get("par_index");
		SimpleDateFormat formaty2 = new SimpleDateFormat("yyyy-MM");
		String is_cur_month = "yes";// 不为空表示是单月 // 为空表示不是当月

		Date date = new Date(); // 当月使用
		// 封装成JSON字符串
		Map<String, Object> allmap = new HashMap<String, Object>();

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}
		boolean flag = false;
		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(user.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);
		for (PeRoleUser peRoleUser : peRoleUserList) {
			if (peRoleUser.getRole_id().equals(new Long(60))) {
				flag = true;
			}
		}
		JStocksSummary entity = new JStocksSummary();
		// 当月
		if (StringUtils.isNotBlank(year) && StringUtils.isNotBlank(month)) {
			entity.getMap().put("cur_month", year + month);
			dynaBean.set("year", year);
			dynaBean.set("month", month);

			if ((year + "-" + month).equals(formaty2.format(date))) {
				is_cur_month = "yes";
				entity.getMap().put("is_cur_month", is_cur_month);
			} else {
				is_cur_month = null;
			}
		} else {
			String year_month[] = formaty2.format(date).split("-");
			entity.getMap().put("cur_month", year_month[0] + year_month[1]);
			dynaBean.set("year", year_month[0]);
			dynaBean.set("month", year_month[1]);
			is_cur_month = "yes";
			entity.getMap().put("is_cur_month", is_cur_month);
		}
		// 分公司
		if (StringUtils.isNotBlank(branch_area_name_2)) {
			entity.getMap().put("branch_area_name_2", branch_area_name_2);
			dynaBean.set("branch_area_name_2", branch_area_name_2);
		}
		// 父类型id
		if (StringUtils.isNotBlank(par_index)) {
			entity.getMap().put("par_index", par_index);
			dynaBean.set("par_index", par_index);
		}
		// 查看自己的
		if (null != user.getId()) {
			entity.getMap().put("add_user_id", user.getId());
		}
		/*
		 * if (flag) {// 如果是业务员，只能看到自己的拜访记录 entity.setAdd_user_id(user.getId()); } else { Long _dept_id = 0L; int
		 * max_dlevel = super.getMaxDLevel(user.getId()); // 获取当前用户的最高可视部门级别 logger.info("Max level : {}", max_dlevel);
		 * switch (max_dlevel) { case 9: // 集团及以下部门可见 _dept_id = 0L; // 0表示部门根节点，即“多媒体事业本部” break; case 8: // 分公司及以下部门可见
		 * KonkaDept dept_fgs = super.getKonkaDeptForFgs(user.getDept_id()); // 查询部门分公司 if (null != dept_fgs && null !=
		 * dept_fgs.getDept_id()) { _dept_id = dept_fgs.getDept_id(); // 分公司部门ID entity.getMap().put("dept_id_start",
		 * _dept_id); // entity.getMap().put("fgs_dept_value", _dept_id); } break; case 7: // 我所在的部门及以下部门可见 _dept_id =
		 * user.getDept_id(); // 默认为当前用户所在部门 entity.getMap().put("dept_id_start", _dept_id); break; case 0: _dept_id =
		 * user.getDept_id(); // 默认为当前用户所在部门 // entity.getMap().put("dept_id_start", __dept_id);
		 * entity.getMap().put("filter_by_ywy_id_eq", user.getId()); break; default: // 出错 }
		 * entity.getMap().put("session_user_id", user.getId()); }
		 */

		List<Map<String, Object>> entityList = null;

		entityList = super.getFacade().getJStocksSummaryService().getJcfxKhList(entity);
		List<String> kh_arry = new ArrayList<String>();
		List<Double> cur_day_arry = new ArrayList<Double>();
		List<Double> target_day_arry = new ArrayList<Double>();

		DecimalFormat fromatNumber = new DecimalFormat("0");// 格式化小数 取整
		for (Map<String, Object> map : entityList) {
			kh_arry.add("" + map.get("C_NAME"));
			// fromatNumber.format(map.get("CUR_DAY").toString());

			cur_day_arry.add(Double.valueOf(fromatNumber.format((BigDecimal) (map.get("CUR_DAY")))));
			// fromatNumber.format(map.get("TARGET_DAY").toString());
			target_day_arry.add((Double.valueOf("" + map.get("TARGET_DAY"))));
		}
		double max_cur_day = 0.0;
		double min_cur_day = 0.0;

		String max_kh = "";
		String min_kh = "";
		for (int i = 0; i < cur_day_arry.size(); i++) {
			if (cur_day_arry.get(i) > max_cur_day) {
				max_cur_day = cur_day_arry.get(i);
				max_kh = kh_arry.get(i);
			}
			if (cur_day_arry.get(i) < min_cur_day) {
				min_cur_day = cur_day_arry.get(i);
				min_kh = kh_arry.get(i);
			}
		}
		allmap.put("max_cur_day", max_cur_day);
		allmap.put("max_kh", max_kh);
		allmap.put("min_cur_day", min_cur_day);
		allmap.put("min_kh", min_kh);

		allmap.put("kh_arry", kh_arry.toArray());
		allmap.put("cur_day_arry", cur_day_arry.toArray());
		allmap.put("target_day_arry", target_day_arry.toArray());

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
	 * 财务客户部库存周转
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward view2(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String mod_id = (String) dynaBean.get("mod_id");
		String year = (String) dynaBean.get("year");
		String month = (String) dynaBean.get("month");
		String branch_area_name_2 = (String) dynaBean.get("branch_area_name_2");
		String c_index = (String) dynaBean.get("c_index");// 细类型id
		String excel_all = (String) dynaBean.get("excel_all");
		SimpleDateFormat formaty1 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat formaty2 = new SimpleDateFormat("yyyy-MM");
		String is_cur_month = "yes";// 不为空表示是单月 // 为空表示不是当月

		Date date = new Date(); // 当月使用

		Calendar uptime = Calendar.getInstance();// 上月使用
		uptime.add(Calendar.MONTH, -1);

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}
		boolean flag = false;
		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(user.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);
		for (PeRoleUser peRoleUser : peRoleUserList) {
			if (peRoleUser.getRole_id().equals(new Long(60))) {
				flag = true;
			}
		}
		JStocksSummary entity = new JStocksSummary();
		// 当月
		if (StringUtils.isNotBlank(year) && StringUtils.isNotBlank(month)) {
			entity.getMap().put("cur_month", year + month);
			dynaBean.set("year", year);
			dynaBean.set("month", month);
			if ((year + "-" + month).equals(formaty2.format(date))) {
				is_cur_month = "yes";
				entity.getMap().put("is_cur_month", is_cur_month);
				dynaBean.set("type", "0");
			} else {
				is_cur_month = null;
				dynaBean.set("type", "1");
			}

		} else {
			String year_month[] = formaty2.format(date).split("-");
			entity.getMap().put("cur_month", year_month[0] + year_month[1]);
			dynaBean.set("year", year_month[0]);
			dynaBean.set("month", year_month[1]);
			is_cur_month = "yes";
			entity.getMap().put("is_cur_month", is_cur_month);
			dynaBean.set("type", "0");
		}
		// 分公司
		if (StringUtils.isNotBlank(branch_area_name_2)) {
			entity.getMap().put("branch_area_name_2", branch_area_name_2);
			dynaBean.set("branch_area_name_2", branch_area_name_2);
		}
		// 小类型找客户
		if (StringUtils.isNotBlank(c_index)) {
			entity.getMap().put("c_index", c_index);
			dynaBean.set("c_index", c_index);
		}

		// 查看自己的
		if (null != user.getId()) {
			entity.getMap().put("add_user_id", user.getId());
		}


		List<Map<String, Object>> entityList = null;

		entityList = super.getFacade().getJStocksSummaryService().getJcfxCustList(entity);
		if (entityList != null && entityList.size() > 0) {
			if (null != entityList.get(0) && null != entityList.get(0).get("ADD_DATE")) {
				request.setAttribute("add_date", entityList.get(0).get("ADD_DATE"));
			}
		}
		request.setAttribute("mod_id", mod_id);
		request.setAttribute("entityList", entityList);

		String is_bi = (String) dynaBean.get("is_bi");
		if (StringUtils.isNotBlank(is_bi)) {
			request.setAttribute("is_bi", is_bi);
		}
		/**
		 * 遍历年份，取前10年
		 */
		ArrayList<String> yearList = new ArrayList<String>();
		SimpleDateFormat formaty = new SimpleDateFormat("yyyy");
		for (int y = 0; y < 10; y++) {
			yearList.add((Integer.valueOf(formaty.format(date)) - y) + "");
		}
		request.setAttribute("yearList", yearList);

		/**
		 * 分公司
		 */
		dynaBean.set("isNotEpp", "isNotEpp");
		request.setAttribute("sybDeptInfoList", super.getDeptInfoListWithOutLimit(mapping, form, request, response));

		return new ActionForward("/leader/JcfxReportKczzfx/view2.jsp");
	}

	/**
	 * 临时废弃 但 方法可用 财务客户部库存周转 注意： 客户细类分组 图形显示 细类 客户散点
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward ajaxview2(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String year = (String) dynaBean.get("year");
		String month = (String) dynaBean.get("month");
		String c_index = (String) dynaBean.get("c_index");// 细类型id
		String branch_area_name_2 = (String) dynaBean.get("branch_area_name_2");
		SimpleDateFormat formaty2 = new SimpleDateFormat("yyyy-MM");
		Date date = new Date(); // 当月使用

		String is_cur_month = "yes";// 不为空表示是单月 // 为空表示不是当月

		// 封装成JSON字符串
		Map<String, Object> allmap = new HashMap<String, Object>();
		Calendar uptime = Calendar.getInstance();// 上月使用
		uptime.add(Calendar.MONTH, -1);

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}
		boolean flag = false;
		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(user.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);
		for (PeRoleUser peRoleUser : peRoleUserList) {
			if (peRoleUser.getRole_id().equals(new Long(60))) {
				flag = true;
			}
		}
		JStocksSummary entity = new JStocksSummary();
		// 当月
		if (StringUtils.isNotBlank(year) && StringUtils.isNotBlank(month)) {
			entity.getMap().put("cur_month", year + month);
			dynaBean.set("year", year);
			dynaBean.set("month", month);

			if ((year + "-" + month).equals(formaty2.format(date))) {
				is_cur_month = "yes";
				entity.getMap().put("is_cur_month", is_cur_month);
			} else {
				is_cur_month = null;
			}
		} else {
			String year_month[] = formaty2.format(date).split("-");
			entity.getMap().put("cur_month", year_month[0] + year_month[1]);
			dynaBean.set("year", year_month[0]);
			dynaBean.set("month", year_month[1]);

			is_cur_month = "yes";
			entity.getMap().put("is_cur_month", is_cur_month);
		}

		// 分公司
		if (StringUtils.isNotBlank(branch_area_name_2)) {
			entity.getMap().put("branch_area_name_2", branch_area_name_2);
			dynaBean.set("branch_area_name_2", branch_area_name_2);
		}
		// 小类型找客户
		if (StringUtils.isNotBlank(c_index)) {
			entity.getMap().put("c_index", c_index);
			dynaBean.set("c_index", c_index);
		}
		// 查看自己的
		if (null != user.getId()) {
			entity.getMap().put("add_user_id", user.getId());
		}

		List<Map<String, Object>> entityList = null;
		entityList = super.getFacade().getJStocksSummaryService().getJcfxCustList(entity);
		/*
		 * //客户细类数组 //多个个客户细类下客户的二维数据数组 List<Map<String, Object>>listkh=new ArrayList<Map<String,Object>>();
		 * DecimalFormat fromatNumber = new DecimalFormat("0");//格式化小数 取整 //单个存放系类客户二维数组的数据 map //放细类的 List<String>
		 * kh_name_arry=new ArrayList<String>(); for (Map<String, Object> mapdata : entityList) { String
		 * typeName=""+mapdata.get("CUST_TYPE"); Map<String, Object> mapkh=null; for (Map<String, Object> map : listkh)
		 * { if(map.get("name").equals(typeName)){ mapkh=map; } } if(null!=mapkh){ List<Object> curlistcust=
		 * (ArrayList<Object>)(mapkh.get("values")); List<Object> khs=new ArrayList<Object>();
		 * khs.add(Double.valueOf(fromatNumber.format((BigDecimal)(mapdata.get("CUR_DAY")))));
		 * khs.add(Double.valueOf(fromatNumber.format((BigDecimal)(mapdata.get("END_MONEY"))))); curlistcust.add(khs);
		 * mapkh.put("values",curlistcust); }else{ mapkh=new HashMap<String, Object>(); mapkh.put("name",typeName);
		 * List<Object> curlistcust= new ArrayList<Object>(); List<Object> khs=new ArrayList<Object>();
		 * khs.add(Double.valueOf(fromatNumber.format((BigDecimal)(mapdata.get("CUR_DAY")))));
		 * khs.add(Double.valueOf(fromatNumber.format((BigDecimal)(mapdata.get("END_MONEY"))))); curlistcust.add(khs);
		 * mapkh.put("values",curlistcust); listkh.add(mapkh); //新的细类需要添加 kh_name_arry.add(typeName); } }
		 * allmap.put("kh_name_arry", kh_name_arry); allmap.put("listkh", listkh);
		 */
		allmap.put("entityList", entityList);
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

	// 到客户型号 直接取值不按现款价算
	public ActionForward view3(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		setNaviStringToRequestScope(form, request);
		super.getModPopeDom(form, request);

		PeProdUser ui = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

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

		JStocksSummary entity = new JStocksSummary();

		if (StringUtils.isNotBlank(type)) {
			entity.setType(Integer.parseInt(type));
			dynaBean.set("type", type);
		} else {
			super.renderJavaScript(response, "alert('参数丢失！');history.back();");
			return null;
		}

		if (StringUtils.isNotBlank(customer_name_like))
			entity.getMap().put("customer_name_like", customer_name_like.trim());

		if (StringUtils.isNotBlank(r3_code_like)) {
			entity.setR3_code(r3_code_like);
			dynaBean.set("r3_code_like", r3_code_like);
		}
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
			String this_date_e = this_year + "-" + this_month + "-"
					+ getMaxDay(Integer.valueOf(this_year), Integer.valueOf(this_month)) + " 23:59:59";
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
			List<KonkaDept> kdList = super.getFacade().getKonkaDeptService().getKonkaDeptList(kd);
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

		Long recordCount = super.getFacade().getJStocksSummaryService().getJStocksSummaryForR3ShopCount(entity);

		pager.init(recordCount, recordCount.intValue(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		if (StringUtils.isNotBlank(excel_to_all)) {

			// 为“总部财务部”角色开放导出记录数限制
			PeRoleUser roleUser = new PeRoleUser();
			roleUser.setUser_id(ui.getId());
			List<PeRoleUser> roleUserList = getFacade().getPeRoleUserService().getPeRoleUserList(roleUser);

			boolean is_exit = true;
			for (PeRoleUser t : roleUserList) {
				if (t.getRole_id() == 29) {
					is_exit = false;
					break;
				}
			}
			if (is_exit) {
				if (recordCount > 30000) {// 导出数据不能超过30000条
					super.renderJavaScript(response, "alert('导出记录不能超过30000条，请缩小查找范围！');history.back();");
					return null;
				}
			}

			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename=" + EncryptUtils.encodingFileName("客户库存数据")
					+ ".xls");

			entity.getRow().setCount(recordCount.intValue());
			List<JStocksSummary> entityList1 = getFacade().getJStocksSummaryService().getJStocksSummaryForR3ShopList(
					entity);
			request.setAttribute("allList", entityList1);
			return mapping.findForward("view");
		}
		// 客户类型
		KonkaCategory kc = new KonkaCategory();
		kc.setC_type(10);
		kc.setIs_del(0);
		request.setAttribute("konkaCategoryList", super.getFacade().getKonkaCategoryService().getKonkaCategoryList(kc));

		List<JStocksSummary> entityList = super.getFacade().getJStocksSummaryService()
				.getJStocksSummaryForR3ShopPaginatedList(entity);
		Object totlePageNum = super.getFacade().getJStocksSummaryService().getJStocksSummaryForR3ShopSumCount(entity);

		if (null != totlePageNum) {
			dynaBean.set("totlePageNum", totlePageNum);
		} else {
			dynaBean.set("totlePageNum", "0");
		}
		if (entityList != null && entityList.size() > 0) {
			JStocksSummary firestEntity = entityList.get(0);
			if (firestEntity != null) {
				if (null != firestEntity.getAdd_date()) {
					request.setAttribute("add_date", firestEntity.getAdd_date());
				}
				String custmsg = "";
				if (null != firestEntity.getMap().get("dept_name")) {
					custmsg += "分公司名称：" + firestEntity.getMap().get("dept_name") + "&nbsp&nbsp&nbsp";
				}
				if (null != firestEntity.getR3_code()) {
					custmsg += "客户编码：" + firestEntity.getR3_code() + "&nbsp&nbsp&nbsp";
				}
				if (null != firestEntity.getCustomer_name()) {
					custmsg += "客户名称：" + firestEntity.getCustomer_name() + "&nbsp&nbsp&nbsp";
				}
				if (null != firestEntity.getMap().get("par_cust_type_name")) {
					custmsg += "客户类型：" + firestEntity.getMap().get("par_cust_type_name") + "&nbsp&nbsp&nbsp";
				}
				if (null != firestEntity.getMap().get("cust_type_name")) {
					custmsg += "客户细分类型：" + firestEntity.getMap().get("cust_type_name") + "&nbsp&nbsp&nbsp";
				}
				if (null != firestEntity.getMap().get("handle_name")) {
					custmsg += "经办名称：" + firestEntity.getMap().get("handle_name") + "&nbsp&nbsp&nbsp";
				}
				request.setAttribute("custmsg", custmsg);
			}
		}
		request.setAttribute("entityList", entityList);
		// 遍历年份，取前10年
		ArrayList<String> yearList = new ArrayList<String>();

		for (int y = 0; y < 10; y++) {
			yearList.add((Integer.valueOf(formaty.format(now)) - y) + "");
		}
		request.setAttribute("yearList", yearList);

		List<KonkaDept> konkaDeptList = super.getDeptInfoListWithOutLimit(mapping, form, request, response);
		request.setAttribute("konkaDeptList", konkaDeptList);
		return new ActionForward("/leader/JcfxReportKczzfx/view3.jsp");
	}

	/**
	 * yyyy-MM 当月
	 * 
	 * @param datestr
	 * @return 上月
	 */
	private static String getLastDate(String datestr) {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMM");
		Date date;
		Calendar cal = Calendar.getInstance();
		try {
			date = df.parse(datestr);
			cal.setTime(date);
			cal.add(Calendar.MARCH, -1);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return df.format(cal.getTime());
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
		 * 与其他语言环境敏感类一样，Calendar 提供了一个类方法 getInstance， 以获得此类型的一个通用的对象。Calendar 的 getInstance 方法返回一 个 Calendar
		 * 对象，其日历字段已由当前日期和时间初始化：
		 */
		Calendar calendar = Calendar.getInstance();
		/**
		 * 实例化日历各个字段,这里的day为实例化使用
		 */
		calendar.set(year, month - 1, day);
		/**
		 * Calendar.Date:表示一个月中的某天 calendar.getActualMaximum(int field):返回指定日历字段可能拥有的最大值
		 */
		maxDay = calendar.getActualMaximum(Calendar.DATE);
		return maxDay;
	}
}
