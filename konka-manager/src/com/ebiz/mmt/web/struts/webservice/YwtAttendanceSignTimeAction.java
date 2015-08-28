package com.ebiz.mmt.web.struts.webservice;

import java.io.PrintWriter;
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
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.domain.SysModule;
import com.ebiz.mmt.domain.YwtAttendanceSignTime;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.util.DESPlus;
import com.ebiz.mmt.web.util.StringHelper;
import com.ebiz.ssi.util.EncryptUtils;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Pan,Gang
 * @version 2013-09-24
 */
public class YwtAttendanceSignTimeAction extends BaseAction {

	public ActionForward init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		Map<String, Object> allmap = new HashMap<String, Object>();
		// 默认显示当前1月的时间区间q
		Date today = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String day_first = theFirstDayOfCurrentMonth();
		String day_last = df.format(today);
		allmap.put("_sign_in_date", day_first);
		allmap.put("_sign_out_date", day_last);
		allmap.put("_is_lock", 0);

		// 位置信息
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String inuser_id = (String) dynaBean.get("user_id");
		// 密码
		String inuserpass = (String) dynaBean.get("userpass");
		//版本号
		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");
		//web端取session
		PeProdUser userInfo = null;
		
		if (null==userInfo) {
			userInfo = (PeProdUser) super.getSessionAttribute(request,
					Constants.USER_INFO);
		}
		if (null==userInfo) {
			//手机端通过用户查
			userInfo = checkUserid(inuser_id, inuserpass,android_version,ios_version);
		}

		if (null == userInfo) {
			return null;
		}
		String naviString = "";
		if (StringUtils.isNotBlank(mod_id)) {
			SysModule sysModule = new SysModule();
			sysModule.getMap().put("mod_id", mod_id);
			List<SysModule> sysModuleList = getFacade().getSysModuleService()
					.getSysModuleList(sysModule);
			naviString = StringHelper.getNaviString(sysModuleList, " > ");
		}
		allmap.put("local_str", naviString);

		// 转换为json数据
		JSONArray jsonArray = JSONArray.fromObject(allmap);

		int start = jsonArray.toString().indexOf("[");
		int end = jsonArray.toString().lastIndexOf("}");

		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(jsonArray.toString().substring(start + 1, end + 1));
		out.flush();
		out.close();
		return null;
	}

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);
		Pager pager = (Pager) dynaBean.get("pager");
		String mod_id = String.valueOf(dynaBean.get("mod_id"));
		String excel_all = (String) dynaBean.get("excel_all");//
		String page = (String) dynaBean.get("page");
		// 手机端控制
		String first = (String) dynaBean.get("first");
		String count = (String) dynaBean.get("count");
		String inuser_id = (String) dynaBean.get("user_id");
		// 密码
		String inuserpass = (String) dynaBean.get("userpass");
		//版本号
		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");
		//web端取session
		PeProdUser userInfo = null;
		
		if (null==userInfo) {
			userInfo = (PeProdUser) super.getSessionAttribute(request,
					Constants.USER_INFO);
		}
		if (null==userInfo) {
			//手机端通过用户查
			userInfo = checkUserid(inuser_id, inuserpass,android_version,ios_version);
		}

		if (null == userInfo) {
			return null;
		}
		SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
		YwtAttendanceSignTime entity = new YwtAttendanceSignTime();

		/*
		 * boolean flag = false; PeRoleUser _peRoleUser = new PeRoleUser();
		 * _peRoleUser.setUser_id(userInfo.getId()); List<PeRoleUser>
		 * peRoleUserList =
		 * this.getFacade().getPeRoleUserService().getPeRoleUserList
		 * (_peRoleUser); for (PeRoleUser peRoleUser : peRoleUserList) { if
		 * (peRoleUser.getRole_id().equals(new Long(60))) { flag = true; } } if
		 * (flag) {// 如果是业务员，只能看到自己的拜访记录
		 * entity.setAdd_user_id(userInfo.getId()); } else {
		 * 
		 * Long _dept_id = 0L; int max_dlevel =
		 * super.getMaxDLevel(userInfo.getId()); // 获取当前用户的最高可视部门级别
		 * logger.info("Max level : {}", max_dlevel); switch (max_dlevel) { case
		 * 9: // 集团及以下部门可见 _dept_id = 0L; // 0表示部门根节点，即“多媒体事业本部” break; case 8:
		 * // 分公司及以下部门可见
		 * 
		 * KonkaDept dept_fgs = super.getKonkaDeptForFgs(userInfo.getDept_id());
		 * // 查询部门分公司 if (null != dept_fgs && null != dept_fgs.getDept_id()) {
		 * _dept_id = dept_fgs.getDept_id(); // 分公司部门ID
		 * entity.getMap().put("dept_id_start", _dept_id); //
		 * entity.getMap().put("fgs_dept_value", _dept_id); } break; case 7: //
		 * 我所在的部门及以下部门可见 _dept_id = userInfo.getDept_id(); // 默认为当前用户所在部门
		 * entity.getMap().put("dept_id_start", _dept_id); break; case 0:
		 * _dept_id = userInfo.getDept_id(); // 默认为当前用户所在部门 //
		 * entity.getMap().put("dept_id_start", __dept_id);
		 * entity.getMap().put("filter_by_ywy_id_eq", userInfo.getId()); break;
		 * default: // 出错 } entity.getMap().put("session_user_id",
		 * userInfo.getId());
		 * 
		 * }
		 */
		entity = new YwtAttendanceSignTime();
		List<YwtAttendanceSignTime> entityList = new ArrayList<YwtAttendanceSignTime>();
		Long recordCount = super.getFacade().getYwtAttendanceSignTimeService()
				.getYwtAttendanceSignTimeCount(entity);
		entity.setDept_id(userInfo.getDept_id());
		// 导出
		if (StringUtils.isNotBlank(excel_all) && "1".equals(excel_all)) {

			if (recordCount.intValue() > 20000) {
				renderJavaScript(
						response,
						"alert('"
								+ super.getMessage(request, "export.too.many")
								+ "！');history.back();");
				return null;
			}
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename="
					+ EncryptUtils.encodingFileName("签到时间基础表") + ".xls");
			entity.getRow().setFirst(0);
			entity.getRow().setCount(recordCount.intValue());

			List<YwtAttendanceSignTime> entityList1 = super.getFacade()
					.getYwtAttendanceSignTimeService()
					.getYwtAttendanceSignTimePaginatedList(entity);
			request.setAttribute("allList", entityList1);
			return new ActionForward(
					"/admin/YwtAttendanceSignTime/report_list.jsp");
		}

		pager.init(recordCount, pager.getPageSize(), page);
		// 有值传来表示手机端调的
		if (StringUtils.isNotBlank(first)) {
			entity.getRow().setFirst(Integer.valueOf(first));
		} else {
			entity.getRow().setFirst(pager.getFirstRow());
		}
		if (StringUtils.isNotBlank(count)) {
			entity.getRow().setCount(Integer.valueOf(count));
		} else {
			entity.getRow().setCount(pager.getRowCount());
		}
		// 判断是否是总部
		if (entity.getDept_id() == 0) {
			//System.out.println("*************************************总部");
			YwtAttendanceSignTime ySignTime = new YwtAttendanceSignTime();
			ySignTime.setDept_id(entity.getDept_id());
			entityList = super.getFacade().getYwtAttendanceSignTimeService()
					.selectAllYwtAttendanceSignTimeSignTimeList(ySignTime);
		} else {
			//System.out.println("*************************************分公司");
			entityList = super.getFacade().getYwtAttendanceSignTimeService()
					.getYwtAttendanceSignTimePaginatedList(entity);
		}
		if (entityList.size() <= 0 && entity.getDept_id() != null
				&& entity.getDept_id() != 0) {
			entity.setDept_id(Long.valueOf(0));
			//System.out.println("*************************************分公司没有");
			entityList = super.getFacade().getYwtAttendanceSignTimeService()
					.getYwtAttendanceSignTimePaginatedList(entity);
		}

		if (null != entityList && entity.getDept_id() != 0
				&& entityList.size() > 1) {
			entityList.remove(0);
		}
		SimpleDateFormat dateformat1=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		for (YwtAttendanceSignTime YwtAttendanceSignTime : entityList) {
			if (null != YwtAttendanceSignTime.getAm_sign_in_date()) {
				YwtAttendanceSignTime.getMap().put("am_sign_in_date",
						df.format(YwtAttendanceSignTime.getAm_sign_in_date()));
			}
			if (null != YwtAttendanceSignTime.getAm_sign_out_date()) {
				YwtAttendanceSignTime.getMap().put("am_sign_out_date",
						df.format(YwtAttendanceSignTime.getAm_sign_out_date()));
			}
			if (null != YwtAttendanceSignTime.getPm_sign_in_date()) {
				YwtAttendanceSignTime.getMap().put("pm_sign_in_date",
						df.format(YwtAttendanceSignTime.getPm_sign_in_date()));
			}
			if (null != YwtAttendanceSignTime.getPm_sign_out_date()) {
				YwtAttendanceSignTime.getMap().put("pm_sign_out_date",
						df.format(YwtAttendanceSignTime.getPm_sign_out_date()));
			}
			if (null != YwtAttendanceSignTime.getAm_center_pm()) {
				YwtAttendanceSignTime.getMap().put("am_center_pm",
						df.format(YwtAttendanceSignTime.getAm_center_pm()));
			}

			if (null != YwtAttendanceSignTime.getAdd_date()) {
				
				YwtAttendanceSignTime.getMap().put("add_date",
						dateformat1.format(YwtAttendanceSignTime.getAdd_date()));
				}
			if (null != YwtAttendanceSignTime.getDept_id()) {
				request.setAttribute("dept_id",
						df.format(YwtAttendanceSignTime.getDept_id()));
			}
		}
		// 封装成JSON字符串
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("total", recordCount);
		if (entityList == null) {
			String[] str = {};
			m.put("rows", str);
		} else {
			m.put("rows", entityList);
		}
		//System.out.println(entity.getDept_id() + "*************************");
		JSONArray jsonArray = JSONArray.fromObject(m);
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

	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		setNaviStringToRequestScope(form, request);
		String inuser_id = (String) dynaBean.get("user_id");
		// 密码
		String inuserpass = (String) dynaBean.get("userpass");
		//版本号
		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");
		//web端取session
		PeProdUser userInfo = null;
		
		if (null==userInfo) {
			userInfo = (PeProdUser) super.getSessionAttribute(request,
					Constants.USER_INFO);
		}
		if (null==userInfo) {
			//手机端通过用户查
			userInfo = checkUserid(inuser_id, inuserpass,android_version,ios_version);
		}

		if (null == userInfo) {
			return null;
		}
		Map<String, Object> allmap = new HashMap<String, Object>();
		YwtAttendanceSignTime entity = new YwtAttendanceSignTime();

		Long total = null;
		// 是否是总部的人
		int max_dlevel = super.getMaxDLevel(userInfo.getId()); // 获取当前用户的最高可视部门级别
		logger.info("Max level : {}", max_dlevel);
		switch (max_dlevel) {
		case 9:
			// 总部的人进来查看总部添加的考勤时间条数
			entity.setDept_id(0L);
			total = super.getFacade().getYwtAttendanceSignTimeService()
					.getYwtAttendanceSignTimeCount(entity);
			break;
		default:
			// 分公司的人进来查看总部添加的考勤时间条数
			KonkaDept dept_fgs = super
					.getKonkaDeptForFgs(userInfo.getDept_id()); // 查询部门分公司
			entity.setDept_id(dept_fgs.getDept_id());
			total = super.getFacade().getYwtAttendanceSignTimeService()
					.getYwtAttendanceSignTimeCount(entity);
		}
		List<KonkaDept> deptList=new ArrayList<KonkaDept>();
		deptList = super.getFacade().getKonkaDeptService().selectDept();
		allmap.put("deptList", deptList);
		allmap.put("total", total);
		allmap.put("dept_id", userInfo.getDept_id());
		
		// 转换为json数据
		JSONArray jsonArray = JSONArray.fromObject(allmap);

		int start = jsonArray.toString().indexOf("[");
		int end = jsonArray.toString().lastIndexOf("}");

		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(jsonArray.toString().substring(start + 1, end + 1));
		out.flush();
		out.close();
		return null;
	}

	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String inuser_id = (String) dynaBean.get("user_id");
		// 密码
		String inuserpass = (String) dynaBean.get("userpass");
		//版本号
		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");
		//web端取session
		PeProdUser userInfo = null;
		
		if (null==userInfo) {
			userInfo = (PeProdUser) super.getSessionAttribute(request,
					Constants.USER_INFO);
		}
		if (null==userInfo) {
			//手机端通过用户查
			userInfo = checkUserid(inuser_id, inuserpass,android_version,ios_version);
		}
		if (null == userInfo) {
			super.renderHtml(response, "登录超时");
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String id = (String) dynaBean.get("id");//

		String dept_id = (String) dynaBean.get("dept_name");
		//System.out.println(dept_id+" zhuan huang cuo wu !");
		String dept_name="";
		if(dept_id!="" && null!=dept_id && isNumeric(dept_id))
		{
			dept_name=selectDeptName(Long.valueOf(dept_id));
		}
		String mod_id = (String) dynaBean.get("mod_id");//
		String am_sign_in_date = "1990-01-01 "
				+ (String) dynaBean.get("am_sign_in_date");// 开始休息时间
		String am_sign_out_date = "1990-01-01 "
				+ (String) dynaBean.get("am_sign_out_date");// 结束休息时间
		String pm_sign_in_date = "1990-01-01 "
				+ (String) dynaBean.get("pm_sign_in_date");// 开始休息时间
		String pm_sign_out_date = "1990-01-01 "
				+ (String) dynaBean.get("pm_sign_out_date");// 结束休息时间
		String am_center_pm = "1990-01-01 "
				+ (String) dynaBean.get("am_center_pm");// 结束休息时间

		YwtAttendanceSignTime entity = new YwtAttendanceSignTime();
		if (StringUtils.isNotBlank(id) && StringUtils.isNumeric(id)) {
			entity.setId(Long.valueOf(id));
		} else {
			entity.setAdd_user_id(userInfo.getId());
			entity.setUser_id(userInfo.getId());
			entity.setUser_name(userInfo.getUser_name());
		}

		if (StringUtils.isNotBlank(am_sign_in_date)) {
			entity.setAm_sign_in_date(sdf.parse(am_sign_in_date));
		}
		if (StringUtils.isNotBlank(am_sign_out_date)) {
			entity.setAm_sign_out_date(sdf.parse(am_sign_out_date));
		}
		if (StringUtils.isNotBlank(pm_sign_in_date)) {
			entity.setPm_sign_in_date(sdf.parse(pm_sign_in_date));
		}
		if (StringUtils.isNotBlank(pm_sign_out_date)) {
			entity.setPm_sign_out_date(sdf.parse(pm_sign_out_date));
		}
		if (StringUtils.isNotBlank(am_center_pm)) {
			entity.setAm_center_pm(sdf.parse(am_center_pm));
		}
		
		//不存在就退出
		if (null==dept_id) {
			// 已经存在记录，不能再添加
			Map<String, Object> allmap1 = new HashMap<String, Object>();
			// 封装成JSON字符串
			allmap1.put("result", id);
			allmap1.put("msg", "分公司不存在，请核实输入！");
			allmap1.put("mod_id", mod_id);
			JSONArray jsonArray = JSONArray.fromObject(allmap1);
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

		int max_dlevel = super.getMaxDLevel(userInfo.getId()); // 获取当前用户的最高可视部门级别
		logger.info("Max level : {}", max_dlevel);
		switch (max_dlevel) {
		case 9:
			if (StringUtils.isBlank(id)) {
				// 总部的人进来查看总部添加的考勤时间条数
				// entity.setDept_id(0L);
				// entity.setDept_name("总部");
				if (StringUtils.isNotBlank(id) && StringUtils.isNumeric(id)) {
					// 这是修改时候

				} else {
					// 如果是添加，那么把部门信息穿进去，看下是否已经存在记录，不存在接着走下去，存在，说不能再添加
					YwtAttendanceSignTime entity_old = new YwtAttendanceSignTime();
					if (StringUtils.isNotBlank(dept_id) && isNumeric(dept_id)) {
						entity_old.setDept_id(Long.valueOf(dept_id));
					}
					if (StringUtils.isNotBlank(dept_name)) {
						entity_old.setDept_name(dept_name);
					}
					Long count = super.getFacade()
							.getYwtAttendanceSignTimeService()
							.getYwtAttendanceSignTimeCount(entity_old);
					if (count > 0) {
						// 已经存在记录，不能再添加
						Map<String, Object> allmap1 = new HashMap<String, Object>();
						// 封装成JSON字符串
						allmap1.put("result", id);
						allmap1.put("msg", "分公司不存在或记录已有，请核实在添加！");
						allmap1.put("mod_id", mod_id);
						JSONArray jsonArray = JSONArray.fromObject(allmap1);
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
						
					} else {
						if (StringUtils.isNotBlank(dept_id) && isNumeric(dept_id)) {
							entity.setDept_id(Long.valueOf(dept_id));
						}
						if (StringUtils.isNotBlank(dept_name)) {
							entity.setDept_name(dept_name);
						}
					}
				}
			}

			break;
		default:
			if (StringUtils.isBlank(id)) {
				// 分公司的人进来查看总部添加的考勤时间条数
				KonkaDept dept_fgs = super.getKonkaDeptForFgs(userInfo
						.getDept_id()); // 查询部门分公司
				entity.setDept_id(dept_fgs.getDept_id());
				entity.setDept_name(dept_fgs.getDept_name());
			}
		}

		Map<String, Object> allmap = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(id)) {
			super.getFacade().getYwtAttendanceSignTimeService()
					.modifyYwtAttendanceSignTime(entity);
			allmap.put("result", id);
			allmap.put("msg", "修改成功");
				} else {
			entity.setAdd_date(new Date());
			super.getFacade().getYwtAttendanceSignTimeService()
					.createYwtAttendanceSignTime(entity);
			allmap.put("result", id);
			allmap.put("msg", "添加成功");
			
		}
		// 封装成JSON字符串
		allmap.put("mod_id", mod_id);
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

	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String id = (String) dynaBean.get("id");
		if (!GenericValidator.isLong(id)) {
			super.saveError(request, "errors.long", "id");
			return null;
		}
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String inuser_id = (String) dynaBean.get("user_id");
		// 密码
		String inuserpass = (String) dynaBean.get("userpass");
		//版本号
		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");
		//web端取session
		PeProdUser userInfo = null;
		
		if (null==userInfo) {
			userInfo = (PeProdUser) super.getSessionAttribute(request,
					Constants.USER_INFO);
		}
		if (null==userInfo) {
			//手机端通过用户查
			userInfo = checkUserid(inuser_id, inuserpass,android_version,ios_version);
		}
		// 存放数据
		Map<String, Object> allmap = new HashMap<String, Object>();

		YwtAttendanceSignTime ywtAttendanceSignTime = new YwtAttendanceSignTime();
		if (StringUtils.isNotBlank(id)) {
			ywtAttendanceSignTime.setId(Long.valueOf(id));
			ywtAttendanceSignTime.getRow().setFirst(0);
			ywtAttendanceSignTime.getRow().setCount(1);
		}
		ywtAttendanceSignTime = super.getFacade()
				.getYwtAttendanceSignTimeService()
				.getYwtAttendanceSignTime(ywtAttendanceSignTime);
		// 是否是总部的人
		int max_dlevel = super.getMaxDLevel(userInfo.getId()); // 获取当前用户的最高可视部门级别
		logger.info("Max level : {}", max_dlevel);
		switch (max_dlevel) {
		case 9:
			// 集团及以下部门可见
			/*
			 * if(ywtAttendanceSignTime.getDept_id()!=0){ allmap.put("error",
			 * "1"); allmap.put("res", "不可修改分公司考勤时间"); }
			 */
			break;
		default:
			KonkaDept dept_fgs = super
					.getKonkaDeptForFgs(userInfo.getDept_id()); // 查询部门分公司
			if (!dept_fgs.getDept_id().equals(
					ywtAttendanceSignTime.getDept_id())) {
				allmap.put("error", "2");
				allmap.put("res", "只能修改自己分公司考勤时间");
			}
		}
		allmap.put("entity", ywtAttendanceSignTime);

		if (null != ywtAttendanceSignTime.getAm_sign_in_date()) {
			allmap.put("am_sign_in_date",
					df.format(ywtAttendanceSignTime.getAm_sign_in_date()));
		}
		if (null != ywtAttendanceSignTime.getAm_sign_out_date()) {
			allmap.put("am_sign_out_date",
					df.format(ywtAttendanceSignTime.getAm_sign_out_date()));
		}
		if (null != ywtAttendanceSignTime.getPm_sign_in_date()) {
			allmap.put("pm_sign_in_date",
					df.format(ywtAttendanceSignTime.getPm_sign_in_date()));
		}
		if (null != ywtAttendanceSignTime.getPm_sign_out_date()) {
			allmap.put("pm_sign_out_date",
					df.format(ywtAttendanceSignTime.getPm_sign_out_date()));
		}
		if (null != ywtAttendanceSignTime.getAm_center_pm()) {
			allmap.put("am_center_pm",
					df.format(ywtAttendanceSignTime.getAm_center_pm()));
		}
		if (null != ywtAttendanceSignTime.getAdd_date()) {
			allmap.put("add_date",
					df.format(ywtAttendanceSignTime.getAdd_date()));
		}
		allmap.put("mod_id", mod_id);

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

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		Map<String, Object> allmap = new HashMap<String, Object>();

		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String id = (String) dynaBean.get("id");
		if (!GenericValidator.isLong(id)) {
			super.saveError(request, "errors.long", "id");
			return null;
		}
		String inuser_id = (String) dynaBean.get("user_id");
		// 密码
		String inuserpass = (String) dynaBean.get("userpass");
		//版本号
		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");
		//web端取session
		PeProdUser userInfo = null;
		
		if (null==userInfo) {
			userInfo = (PeProdUser) super.getSessionAttribute(request,
					Constants.USER_INFO);
		}
		if (null==userInfo) {
			//手机端通过用户查
			userInfo = checkUserid(inuser_id, inuserpass,android_version,ios_version);
		}
		YwtAttendanceSignTime entity = new YwtAttendanceSignTime();
		if (StringUtils.isNotBlank(id)) {
			entity.setId(Long.valueOf(id));
			int delcountid = super.getFacade()
					.getYwtAttendanceSignTimeService()
					.removeYwtAttendanceSignTime(entity);
			allmap.put("result", delcountid);
			allmap.put("mod_id", mod_id);
			if (delcountid > 0) {
				allmap.put("msg", "删除成功！");
			} else {
				allmap.put("msg", "删除失败！");
			}
		} else {
			allmap.put("msg", "未选择要删除的数据！");
		}

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

	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String id = (String) dynaBean.get("id");
		if (!GenericValidator.isLong(id)) {
			super.saveError(request, "errors.long", "id");
			return null;
		}
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String inuser_id = (String) dynaBean.get("user_id");
		// 密码
		String inuserpass = (String) dynaBean.get("userpass");
		//版本号
		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");
		//web端取session
		PeProdUser userInfo = null;
		
		if (null==userInfo) {
			userInfo = (PeProdUser) super.getSessionAttribute(request,
					Constants.USER_INFO);
		}
		if (null==userInfo) {
			//手机端通过用户查
			userInfo = checkUserid(inuser_id, inuserpass,android_version,ios_version);
		}
		boolean flag = false;
		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(userInfo.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade()
				.getPeRoleUserService().getPeRoleUserList(_peRoleUser);
		for (PeRoleUser peRoleUser : peRoleUserList) {
			if (peRoleUser.getRole_id().equals(new Long(60))) {
				flag = true;
			}
		}
		// 存放数据
		Map<String, Object> allmap = new HashMap<String, Object>();

		PeProdUser entity = new PeProdUser();
		if (null != userInfo.getId()) {
			entity.setId(userInfo.getId());
		}
		if (null != userInfo.getDept_id()) {
			entity.setDept_id(userInfo.getDept_id());
		}

		// 查用户下拉列表 初始化数据
		List<PeProdUser> entityList = super.getFacade().getPeProdUserService()
				.getPeProdUserBy_id_deptid(entity);

		List<Map<String, Object>> userListMap = new ArrayList<Map<String, Object>>();
		Map<String, Object> usertemp = null;

		if (null != entityList) {
			for (PeProdUser peProdUser : entityList) {
				usertemp = new HashMap<String, Object>();
				usertemp.put("id",
						peProdUser.getId() + "#" + peProdUser.getReal_name());
				usertemp.put("real_name", peProdUser.getReal_name());
				userListMap.add(usertemp);
			}
			allmap.put("userlist", userListMap);
		}

		YwtAttendanceSignTime ywtAttendanceSignTime = new YwtAttendanceSignTime();
		if (StringUtils.isNotBlank(id)) {
			ywtAttendanceSignTime.setId(Long.valueOf(id));
			ywtAttendanceSignTime.getRow().setFirst(0);
			ywtAttendanceSignTime.getRow().setCount(1);
		}
		ywtAttendanceSignTime = super.getFacade()
				.getYwtAttendanceSignTimeService()
				.getYwtAttendanceSignTime(ywtAttendanceSignTime);
		allmap.put("entity", ywtAttendanceSignTime);

		if (null != ywtAttendanceSignTime.getAm_sign_in_date()) {
			allmap.put("am_sign_in_date",
					df.format(ywtAttendanceSignTime.getAm_sign_in_date()));
		}
		if (null != ywtAttendanceSignTime.getAm_sign_out_date()) {
			allmap.put("am_sign_out_date",
					df.format(ywtAttendanceSignTime.getAm_sign_out_date()));
		}
		if (null != ywtAttendanceSignTime.getPm_sign_in_date()) {
			allmap.put("pm_sign_in_date",
					df.format(ywtAttendanceSignTime.getPm_sign_in_date()));
		}
		if (null != ywtAttendanceSignTime.getPm_sign_out_date()) {
			allmap.put("pm_sign_out_date",
					df.format(ywtAttendanceSignTime.getPm_sign_out_date()));
		}
		if (null != ywtAttendanceSignTime.getAdd_date()) {
			allmap.put("sign_in_date",
					df.format(ywtAttendanceSignTime.getAdd_date()));
		}

		allmap.put("mod_id", mod_id);

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

	private static String theFirstDayOfCurrentMonth() {
		Calendar c = Calendar.getInstance();

		String y = String.valueOf(c.get(Calendar.YEAR));
		String m = "";
		String d = "";// the first day of a month
		if (c.get(Calendar.MONTH) + 1 >= 10) {
			m = String.valueOf(c.get(Calendar.MONTH) + 1);
		} else {
			m = "0" + String.valueOf(c.get(Calendar.MONTH) + 1);
		}
		d = "0" + c.getActualMinimum(Calendar.DAY_OF_MONTH);
		String start_day = y + "-" + m + "-" + d;
		return start_day;
	}

	// 查dept_nameb
	public String selectDeptName(Long dept_id) throws Exception {
		if (null != dept_id) {
			KonkaDept t = new KonkaDept();
			t.setDept_id(dept_id);
			t.setDept_type(3);
			List<KonkaDept> list = super.getFacade().getKonkaDeptService().getKonkaDeptList(t);
			if(list.size()==1 && null!=list)
			{
				
				return list.get(0).getDept_name();
			}
			else
			{
				return null;
			}
		} else {
			return null;
		}

	}
	//判断String是不是数字
	public static boolean isNumeric(String str){
		  for (int i = 0; i < str.length(); i++){
		   //System.out.println(str.charAt(i));
		   if (!Character.isDigit(str.charAt(i))){
		    return false;
		   }
		  }
		  return true;
		 }

}
