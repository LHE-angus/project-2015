package com.ebiz.mmt.web.struts.manager.admin;

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
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.JStocksSummary;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.domain.VOrgOfDept;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
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
		//默认选择当前年月
		Date date= new Date(); //当月使用
		SimpleDateFormat formaty2 = new SimpleDateFormat("yyyy-MM");
		String year_month[]=formaty2.format(date).split("-");
		dynaBean.set("year", year_month[0]);
		dynaBean.set("month", year_month[1]);
	
		//初始化默认当前人所在的分公司
		KonkaDept fgs=new KonkaDept();
		fgs.setDept_id(user.getDept_id());
		fgs=super.getFacade().getKonkaDeptService().getKonkaDept(fgs);
		if (null!=fgs&&null!=fgs.getDept_id()) {
			dynaBean.set("branch_area_name_2", ""+fgs.getDept_sn());
		}
		return this.view(mapping, form, request, response);
	}
	/**
	 * 财务客户大类部库存周转
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
		Date date= new Date(); //当月使用
		
		Calendar uptime = Calendar.getInstance();//上月使用
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
		//当月
		if (StringUtils.isNotBlank(year)&&StringUtils.isNotBlank(month)) {
			entity.getMap().put("cur_month", year+month);
			dynaBean.set("year", year);
			dynaBean.set("month", month);
		}else {
			String year_month[]=formaty2.format(date).split("-");
			entity.getMap().put("cur_month", year_month[0]+year_month[1]);
			dynaBean.set("year", year_month[0]);
			dynaBean.set("month", year_month[1]);
		}
		//上月
		if (StringUtils.isNotBlank(year)&&StringUtils.isNotBlank(month)) {
			entity.getMap().put("up_month",getLastDate(year+month));
		}else {
			String up_year_month[]=formaty2.format(uptime.getTime()).split("-");
			entity.getMap().put("up_month",getLastDate(up_year_month[0]+up_year_month[1]));
		}
		//分公司
		if (StringUtils.isNotBlank(branch_area_name_2)) {
			entity.getMap().put("branch_area_name_2", branch_area_name_2);
			dynaBean.set("branch_area_name_2", branch_area_name_2);
		}
		//查看自己的
		if (null!=user.getId()) {
			entity.getMap().put("add_user_id", user.getId());
		}
		/*if (flag) {// 如果是业务员，只能看到自己的拜访记录
			entity.setAdd_user_id(user.getId());
		} else {

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

		}*/

		List<Map<String, Object>> entityList = null;
		entityList = super.getFacade().getJStocksSummaryService().getJcfxParkhList(entity);
		request.setAttribute("mod_id", mod_id);
		request.setAttribute("entityList", entityList);
		
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
		Date date= new Date(); //当月使用
		
		Calendar uptime = Calendar.getInstance();//上月使用
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
		//当月
		if (StringUtils.isNotBlank(year)&&StringUtils.isNotBlank(month)) {
			entity.getMap().put("cur_month", year+month);
			dynaBean.set("year", year);
			dynaBean.set("month", month);
		}else {
			String year_month[]=formaty2.format(date).split("-");
			entity.getMap().put("cur_month", year_month[0]+year_month[1]);
			dynaBean.set("year", year_month[0]);
			dynaBean.set("month", year_month[1]);
		}
		//上月
		if (StringUtils.isNotBlank(year)&&StringUtils.isNotBlank(month)) {
			entity.getMap().put("up_month",getLastDate(year+month));
		}else {
			String up_year_month[]=formaty2.format(uptime.getTime()).split("-");
			entity.getMap().put("up_month",getLastDate(up_year_month[0]+up_year_month[1]));
		}
		//分公司
		if (StringUtils.isNotBlank(branch_area_name_2)) {
			entity.getMap().put("branch_area_name_2", branch_area_name_2);
			dynaBean.set("branch_area_name_2", branch_area_name_2);
		}
		
        //父类型id
		if(StringUtils.isNotBlank(par_index)){
			entity.getMap().put("par_index",par_index);
			dynaBean.set("par_index", par_index);
		}
		
		//查看自己的
		if (null!=user.getId()) {
			entity.getMap().put("add_user_id", user.getId());
		}
		/*if (flag) {// 如果是业务员，只能看到自己的拜访记录
			entity.setAdd_user_id(user.getId());
		} else {

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
		}*/

		List<Map<String, Object>> entityList = null;
	
		entityList = super.getFacade().getJStocksSummaryService().getJcfxKhList(entity);
		request.setAttribute("mod_id", mod_id);
		request.setAttribute("entityList", entityList);
		
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
		
	    return new ActionForward("/admin/JcfxReportKczzfx/view1.jsp");
	}
	
	/**
	 * 财务客户小类部库存周转   AJAX图形化界面显示
	 * 注意：细客户周转天数图形
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
		Date date= new Date(); //当月使用
		// 封装成JSON字符串
		Map<String, Object> allmap = new HashMap<String, Object>();
		Calendar uptime = Calendar.getInstance();//上月使用
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
		//当月
		if (StringUtils.isNotBlank(year)&&StringUtils.isNotBlank(month)) {
			entity.getMap().put("cur_month", year+month);
			dynaBean.set("year", year);
			dynaBean.set("month", month);
		}else {
			String year_month[]=formaty2.format(date).split("-");
			entity.getMap().put("cur_month", year_month[0]+year_month[1]);
			dynaBean.set("year", year_month[0]);
			dynaBean.set("month", year_month[1]);
		}
		//上月
		if (StringUtils.isNotBlank(year)&&StringUtils.isNotBlank(month)) {
			entity.getMap().put("up_month",getLastDate(year+month));
		}else {
			String up_year_month[]=formaty2.format(uptime.getTime()).split("-");
			entity.getMap().put("up_month",getLastDate(up_year_month[0]+up_year_month[1]));
		}
		//分公司
		if (StringUtils.isNotBlank(branch_area_name_2)) {
			entity.getMap().put("branch_area_name_2", branch_area_name_2);
			dynaBean.set("branch_area_name_2", branch_area_name_2);
		}
		  //父类型id
		if(StringUtils.isNotBlank(par_index)){
			entity.getMap().put("par_index",par_index);
			dynaBean.set("par_index", par_index);
		}
		//查看自己的
		if (null!=user.getId()) {
			entity.getMap().put("add_user_id", user.getId());
		}
		/*if (flag) {// 如果是业务员，只能看到自己的拜访记录
			entity.setAdd_user_id(user.getId());
		} else {

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
		}*/

		List<Map<String, Object>> entityList = null;
	
		entityList = super.getFacade().getJStocksSummaryService().getJcfxKhList(entity);
		List<String> kh_arry=new ArrayList<String>();
		List<Double> cur_day_arry=new ArrayList<Double>();
		List<Double> target_day_arry=new ArrayList<Double>();
		
		DecimalFormat fromatNumber = new DecimalFormat("0");//格式化小数   取整
		for (Map<String, Object> map : entityList) {
			kh_arry.add(""+map.get("C_NAME"));
			//fromatNumber.format(map.get("CUR_DAY").toString());

			cur_day_arry.add(Double.valueOf(fromatNumber.format((BigDecimal)(map.get("CUR_DAY")))));
			//fromatNumber.format(map.get("TARGET_DAY").toString());
			target_day_arry.add((Double.valueOf(""+map.get("TARGET_DAY"))));
		}
		double max_cur_day=0.0;
		double min_cur_day=0.0;
		
		String max_kh="";
		String min_kh="";
		for (int i = 0; i < cur_day_arry.size(); i++) {
			if (cur_day_arry.get(i)>max_cur_day) {
				max_cur_day=cur_day_arry.get(i);
				max_kh=kh_arry.get(i);
			}
			if (cur_day_arry.get(i)<min_cur_day) {
				min_cur_day=cur_day_arry.get(i);
				min_kh=kh_arry.get(i);
			}
		}
		allmap.put("max_cur_day",max_cur_day );
		allmap.put("max_kh",max_kh );
		allmap.put("min_cur_day",min_cur_day );
		allmap.put("min_kh",min_kh );
		
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
		String branch_area_name_2=(String)dynaBean.get("branch_area_name_2");
		String c_index = (String) dynaBean.get("c_index");//细类型id
		String excel_all = (String) dynaBean.get("excel_all");
		SimpleDateFormat formaty1 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat formaty2 = new SimpleDateFormat("yyyy-MM");
		Date date= new Date(); //当月使用
		
		Calendar uptime = Calendar.getInstance();//上月使用
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
		//当月
		if (StringUtils.isNotBlank(year)&&StringUtils.isNotBlank(month)) {
			entity.getMap().put("cur_month", year+month);
			dynaBean.set("year", year);
			dynaBean.set("month", month);
		}else {
			String year_month[]=formaty2.format(date).split("-");
			entity.getMap().put("cur_month", year_month[0]+year_month[1]);
			dynaBean.set("year", year_month[0]);
			dynaBean.set("month", year_month[1]);
		}
		//上月
		if (StringUtils.isNotBlank(year)&&StringUtils.isNotBlank(month)) {
			entity.getMap().put("up_month",getLastDate(year+month));
		}else {
			String up_year_month[]=formaty2.format(uptime.getTime()).split("-");
			entity.getMap().put("up_month",getLastDate(up_year_month[0]+up_year_month[1]));
		}
		//分公司
		if (StringUtils.isNotBlank(branch_area_name_2)) {
			entity.getMap().put("branch_area_name_2", branch_area_name_2);
			dynaBean.set("branch_area_name_2", branch_area_name_2);
		}
//		小类型找客户
		if(StringUtils.isNotBlank(c_index)){
			entity.getMap().put("c_index",c_index);
			dynaBean.set("c_index", c_index);
		}
		
		//查看自己的
		if (null!=user.getId()) {
			entity.getMap().put("add_user_id", user.getId());
		}
		/*if (flag) {// 如果是业务员，只能看到自己的拜访记录
			entity.setAdd_user_id(user.getId());
		} else {

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

		}*/

		List<Map<String, Object>> entityList = null;
		/*Long recordCount = super.getFacade().getJcfxKczzKhService()
		        .getJcfxCwbkczzlCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());*/
		entityList = super.getFacade().getJStocksSummaryService().getJcfxCustList(entity);
		request.setAttribute("mod_id", mod_id);
		request.setAttribute("entityList", entityList);
		
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
		
		return new ActionForward("/admin/JcfxReportKczzfx/view2.jsp");
	}
	
	/**
	 * 临时废弃     但  方法可用
	 * 财务客户部库存周转  
	 * 注意： 客户细类分组 
	 * 图形显示  细类 客户散点
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
		String c_index = (String) dynaBean.get("c_index");//细类型id
		String branch_area_name_2 = (String) dynaBean.get("branch_area_name_2");
		SimpleDateFormat formaty2 = new SimpleDateFormat("yyyy-MM");
		Date date= new Date(); //当月使用
		// 封装成JSON字符串
		Map<String, Object> allmap = new HashMap<String, Object>();
		Calendar uptime = Calendar.getInstance();//上月使用
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
		//当月
		if (StringUtils.isNotBlank(year)&&StringUtils.isNotBlank(month)) {
			entity.getMap().put("cur_month", year+month);
			dynaBean.set("year", year);
			dynaBean.set("month", month);
		}else {
			String year_month[]=formaty2.format(date).split("-");
			entity.getMap().put("cur_month", year_month[0]+year_month[1]);
			dynaBean.set("year", year_month[0]);
			dynaBean.set("month", year_month[1]);
		}
		//上月
		if (StringUtils.isNotBlank(year)&&StringUtils.isNotBlank(month)) {
			entity.getMap().put("up_month",getLastDate(year+month));
		}else {
			String up_year_month[]=formaty2.format(uptime.getTime()).split("-");
			entity.getMap().put("up_month",getLastDate(up_year_month[0]+up_year_month[1]));
		}
		
		//分公司
		if (StringUtils.isNotBlank(branch_area_name_2)) {
			entity.getMap().put("branch_area_name_2", branch_area_name_2);
			dynaBean.set("branch_area_name_2", branch_area_name_2);
		}
//		小类型找客户
		if(StringUtils.isNotBlank(c_index)){
			entity.getMap().put("c_index",c_index);
			dynaBean.set("c_index", c_index);
		}
		//查看自己的
		if (null!=user.getId()) {
			entity.getMap().put("add_user_id", user.getId());
		}

		List<Map<String, Object>> entityList = null;
		entityList = super.getFacade().getJStocksSummaryService().getJcfxCustList(entity);
		/*
		//客户细类数组
		//多个个客户细类下客户的二维数据数组
		List<Map<String, Object>>listkh=new ArrayList<Map<String,Object>>();
		
		
		DecimalFormat fromatNumber = new DecimalFormat("0");//格式化小数   取整
		//单个存放系类客户二维数组的数据  map
		
		//放细类的
        List<String> kh_name_arry=new ArrayList<String>();
		for (Map<String, Object> mapdata : entityList) {
			String typeName=""+mapdata.get("CUST_TYPE");
			
			Map<String, Object> mapkh=null;
			
			for (Map<String, Object> map : listkh) {
				if(map.get("name").equals(typeName)){
					mapkh=map;
				}
			}
			if(null!=mapkh){
				List<Object> curlistcust= (ArrayList<Object>)(mapkh.get("values"));
   			    List<Object> khs=new ArrayList<Object>();
   				khs.add(Double.valueOf(fromatNumber.format((BigDecimal)(mapdata.get("CUR_DAY")))));
   				khs.add(Double.valueOf(fromatNumber.format((BigDecimal)(mapdata.get("END_MONEY")))));
	          	curlistcust.add(khs);
	            mapkh.put("values",curlistcust);
			}else{
			mapkh=new HashMap<String, Object>();
			mapkh.put("name",typeName);
			List<Object> curlistcust= new ArrayList<Object>();
			    List<Object> khs=new ArrayList<Object>();
	 				    khs.add(Double.valueOf(fromatNumber.format((BigDecimal)(mapdata.get("CUR_DAY")))));
	 				    khs.add(Double.valueOf(fromatNumber.format((BigDecimal)(mapdata.get("END_MONEY")))));
		        curlistcust.add(khs);
		        mapkh.put("values",curlistcust);
		        listkh.add(mapkh);
		        //新的细类需要添加
		        kh_name_arry.add(typeName);
			}
		}
		
		allmap.put("kh_name_arry", kh_name_arry);
		allmap.put("listkh", listkh);*/
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
	/**
	 * yyyy-MM 当月
	 * @param datestr
	 * @return 上月
	 */
	private static String getLastDate(String datestr){
		SimpleDateFormat df = new SimpleDateFormat("yyyyMM");
		Date date;
		Calendar cal=Calendar.getInstance();
		try {
			date = df.parse(datestr);
			cal.setTime(date);
			cal.add(Calendar.MARCH,-1);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return df.format(cal.getTime());
	}
}
