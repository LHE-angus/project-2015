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
import com.ebiz.mmt.domain.YwtAttendanceDay;
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
public class YwtAttendanceDayAction extends BaseAction {

	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		Map<String, Object> allmap = new HashMap<String, Object>();
		// 默认显示当前1月的时间区间
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
		//web端取session
		PeProdUser userInfo = null;
		//版本号
		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");
		
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
			List<SysModule> sysModuleList = getFacade().getSysModuleService().getSysModuleList(sysModule);
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
	
	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);
		Pager pager = (Pager) dynaBean.get("pager");
		String mod_id = String.valueOf(dynaBean.get("mod_id"));
		String _real_name_like = (String)dynaBean.get("_real_name_like");
		String _sign_in_date = (String) dynaBean.get("_sign_in_date");//
		String _sign_out_date = (String) dynaBean.get("_sign_out_date");//
		String _is_lock = (String) dynaBean.get("_is_lock");//
		
		String excel_all = (String) dynaBean.get("excel_all");//
		String page =(String) dynaBean.get("page");
		//手机端控制
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
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		YwtAttendanceDay entity = new YwtAttendanceDay();

		boolean flag = false;
		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(userInfo.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);
		for (PeRoleUser peRoleUser : peRoleUserList) {
			if (peRoleUser.getRole_id().equals(new Long(60))) {
				flag = true;
			}
		}
		if (flag) {// 如果是业务员，只能看到自己的拜访记录
			entity.setAdd_user_id(userInfo.getId());
		} else {

			Long _dept_id = 0L;
			int max_dlevel = super.getMaxDLevel(userInfo.getId()); // 获取当前用户的最高可视部门级别
			logger.info("Max level : {}", max_dlevel);
			switch (max_dlevel) {
			case 9:
				// 集团及以下部门可见
				_dept_id = 0L; // 0表示部门根节点，即“多媒体事业本部”
				break;
			case 8:
				// 分公司及以下部门可见

				KonkaDept dept_fgs = super.getKonkaDeptForFgs(userInfo.getDept_id()); // 查询部门分公司
				if (null != dept_fgs && null != dept_fgs.getDept_id()) {
					_dept_id = dept_fgs.getDept_id(); // 分公司部门ID
					entity.getMap().put("dept_id_start", _dept_id);
					// entity.getMap().put("fgs_dept_value", _dept_id);
				}
				break;
			case 7:
				// 我所在的部门及以下部门可见
				_dept_id = userInfo.getDept_id(); // 默认为当前用户所在部门
				entity.getMap().put("dept_id_start", _dept_id);
				break;
			case 0:
				_dept_id = userInfo.getDept_id(); // 默认为当前用户所在部门
				// entity.getMap().put("dept_id_start", __dept_id);
				entity.getMap().put("filter_by_ywy_id_eq", userInfo.getId());
				break;
			default:
				// 出错
			}
			entity.getMap().put("session_user_id", userInfo.getId());

		}
		
		if (StringUtils.isNotBlank(_real_name_like)) {
			entity.getMap().put("_real_name_like", _real_name_like);
			dynaBean.set("_real_name_like", _real_name_like);
		}
		if (StringUtils.isNotBlank(_sign_in_date)) {
			entity.getMap().put("_sign_in_date", _sign_in_date);
			dynaBean.set("_sign_in_date", _sign_in_date);
		}
		if (StringUtils.isNotBlank(_sign_out_date)) {
			entity.getMap().put("_sign_out_date", _sign_out_date);
			dynaBean.set("_sign_out_date", _sign_out_date);
		}
		if (Integer.valueOf(_is_lock)!=-1) {
			entity.getMap().put("_is_lock", _is_lock);
		}

		List<YwtAttendanceDay> entityList = null;
		Long recordCount = super.getFacade().getYwtAttendanceDayService().getYwtAttendanceDayLBCount(entity);
		
		// 导出
		if (StringUtils.isNotBlank(excel_all) && "1".equals(excel_all)) {

			if (recordCount.intValue() > 20000) {
				renderJavaScript(response, "alert('" + super.getMessage(request, "export.too.many")
						+ "！');history.back();");
				return null;
			}
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename="
					+ EncryptUtils.encodingFileName("考勤休息时间设定") + ".xls");
			entity.getRow().setFirst(0);
			entity.getRow().setCount(recordCount.intValue());

			List<YwtAttendanceDay> entityList1 = super.getFacade().getYwtAttendanceDayService()
					.getYwtAttendanceDayLBPaginatedList(entity);
			request.setAttribute("allList", entityList1);
			return new ActionForward("/admin/YwtAttendanceDay/report_list.jsp");
		}
		
		pager.init(recordCount, pager.getPageSize(), page);
        //有值传来表示手机端调的
		if (StringUtils.isNotBlank(first)) {
			entity.getRow().setFirst(Integer.valueOf(first));
		}else {
			entity.getRow().setFirst(pager.getFirstRow());
		}
		if (StringUtils.isNotBlank(count)) {
			entity.getRow().setCount(Integer.valueOf(count));
		}else {
			entity.getRow().setCount(pager.getRowCount());
		}
		entityList = super.getFacade().getYwtAttendanceDayService().getYwtAttendanceDayLBPaginatedList(entity);
		for (YwtAttendanceDay YwtAttendanceDay : entityList) {
			if (null!=YwtAttendanceDay.getSign_in_date()) {
				YwtAttendanceDay.getMap().put("sign_in_date",
						df.format(YwtAttendanceDay.getSign_in_date()));
			}
			if (null!=YwtAttendanceDay.getSign_out_date()) {
				YwtAttendanceDay.getMap().put("sign_out_date",
						df.format(YwtAttendanceDay.getSign_out_date()));
			}
			if (null!=YwtAttendanceDay.getAdd_date()) {
				YwtAttendanceDay.getMap().put("add_date",
						df.format(YwtAttendanceDay.getAdd_date()));
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
		m.put("mod_id", mod_id);
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

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
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
		PeProdUser entity=new PeProdUser();
		if (null!=userInfo.getId()) {
			entity.setId(userInfo.getId());
		}
		if (null!=userInfo.getDept_id()) {
			entity.setDept_id(userInfo.getDept_id());
		}
		//查用户下拉列表
		List<PeProdUser> entityList =  super.getFacade().getPeProdUserService().getPeProdUserBy_id_deptid(entity);
		
		List<Map<String, Object>> userListMap = new ArrayList<Map<String, Object>>();
		Map<String, Object> usertemp=null;

		if (null != entityList) {
			for (PeProdUser peProdUser : entityList) {
				usertemp=new HashMap<String, Object>();
				usertemp.put("id", peProdUser.getId()+"#"+peProdUser.getReal_name());
				usertemp.put("real_name", peProdUser.getReal_name());
				userListMap.add(usertemp);
			}
			allmap.put("userlist", userListMap);
		}
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


	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
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
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		String id = (String) dynaBean.get("id");// 
		String mod_id = (String) dynaBean.get("mod_id");//
		String[] add_user_id_name = request.getParameterValues("add_user_id_name");// 客户id和名称放一起格式：id#name
		String sign_in_date = (String) dynaBean.get("sign_in_date");// 开始休息时间
		String sign_out_date = (String) dynaBean.get("sign_out_date");// 结束休息时间
		String memo = (String) dynaBean.get("memo");
		
		List<YwtAttendanceDay> list=new ArrayList<YwtAttendanceDay>();
		
        //客户信息
		if (null!=add_user_id_name&&add_user_id_name.length>0) {
			YwtAttendanceDay attendanceDay = null;
			for (int i = 0; i < add_user_id_name.length; i++) {
				attendanceDay = new YwtAttendanceDay();
				if (StringUtils.isNotBlank(id)&&StringUtils.isNumeric(id)) {
					attendanceDay.setId(Long.valueOf(id));
				}else {
					if (null!=userInfo.getId()) {
						attendanceDay.setAdd_user_id(userInfo.getId());
					}
					attendanceDay.setAdd_date(new Date());
				}
				String temp[] = add_user_id_name[i].split("#");
				attendanceDay.setUser_id(Long.valueOf(temp[0]));
				attendanceDay.setReal_name(temp[1]);
				if (StringUtils.isNotBlank(sign_in_date)) {
					attendanceDay.setSign_in_date(sdf.parse(sign_in_date));
				}
				if (StringUtils.isNotBlank(sign_out_date)) {
					attendanceDay.setSign_out_date(sdf.parse(sign_out_date));
				}
				if (StringUtils.isNotBlank(memo)) {
					attendanceDay.setMemo(memo);
				}
				attendanceDay.setIs_lock(Long.valueOf(0));
				list.add(attendanceDay);
			}
		}
		Map<String, Object> allmap = new HashMap<String, Object>();
		for (YwtAttendanceDay ywtAttendanceDay : list) {
			if (null!=ywtAttendanceDay&&null!=ywtAttendanceDay.getId()) {
				int newid=super.getFacade().getYwtAttendanceDayService()
						.modifyYwtAttendanceDay(ywtAttendanceDay);
				 allmap.put("result", newid);
				 allmap.put("msg", "修改成功");
			} else {
				 Long newid=super.getFacade().getYwtAttendanceDayService().createYwtAttendanceDay(ywtAttendanceDay);
				 allmap.put("result", newid);
				 allmap.put("msg", "添加成功");
			}
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
	
	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
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
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);
		for (PeRoleUser peRoleUser : peRoleUserList) {
			if (peRoleUser.getRole_id().equals(new Long(60))) {
				flag = true;
			}
		}
//		存放数据
		Map<String, Object> allmap = new HashMap<String, Object>();
		
		PeProdUser entity=new PeProdUser();
		if (null!=userInfo.getId()) {
			entity.setId(userInfo.getId());
		}
		if (null!=userInfo.getDept_id()) {
			entity.setDept_id(userInfo.getDept_id());
		}
		
		//查用户下拉列表 初始化数据
        List<PeProdUser> entityList =  super.getFacade().getPeProdUserService().getPeProdUserBy_id_deptid(entity);
		
		List<Map<String, Object>> userListMap = new ArrayList<Map<String, Object>>();
		Map<String, Object> usertemp=null;

		if (null != entityList) {
			for (PeProdUser peProdUser : entityList) {
				usertemp=new HashMap<String, Object>();
				usertemp.put("id", peProdUser.getId()+"#"+peProdUser.getReal_name());
				usertemp.put("real_name", peProdUser.getReal_name());
				userListMap.add(usertemp);
			}
			allmap.put("userlist", userListMap);
		}
		
		YwtAttendanceDay attendanceDay = new YwtAttendanceDay();
		if (StringUtils.isNotBlank(id)) {
			attendanceDay.setId(Long.valueOf(id));
			attendanceDay.getRow().setFirst(0);
			attendanceDay.getRow().setCount(1);
		}
		attendanceDay=super.getFacade().getYwtAttendanceDayService().getYwtAttendanceDay(attendanceDay);
		allmap.put("entity", attendanceDay);
		
		if (null!=attendanceDay.getSign_in_date()) {
			allmap.put("sign_in_date",df.format(attendanceDay.getSign_in_date()));
		}
		if (null!=attendanceDay.getSign_out_date()) {
			allmap.put("sign_out_date",df.format(attendanceDay.getSign_out_date()));
		}
		
		if (null!=attendanceDay.getUser_id()&&null!=attendanceDay.getReal_name()) {
			String []user_id_name_arry={attendanceDay.getUser_id()+"#"+attendanceDay.getReal_name()};
			allmap.put("user_id_name_arry", user_id_name_arry);
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
	
	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
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
		YwtAttendanceDay entity = new YwtAttendanceDay();
		if (StringUtils.isNotBlank(id)) {
			entity.setId(Long.valueOf(id));
			entity.setIs_lock(Long.valueOf(1));
			int delcountid=super.getFacade().getYwtAttendanceDayService().modifyYwtAttendanceDay(entity);
			allmap.put("result", delcountid);
			allmap.put("mod_id", mod_id);
			if (delcountid>0) {
				allmap.put("msg", "删除成功！");
			}else {
				allmap.put("msg", "删除失败！");
			}
		}else {
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
	
	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
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
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);
		for (PeRoleUser peRoleUser : peRoleUserList) {
			if (peRoleUser.getRole_id().equals(new Long(60))) {
				flag = true;
			}
		}
//		存放数据
		Map<String, Object> allmap = new HashMap<String, Object>();
		
		PeProdUser entity=new PeProdUser();
		if (null!=userInfo.getId()) {
			entity.setId(userInfo.getId());
		}
		if (null!=userInfo.getDept_id()) {
			entity.setDept_id(userInfo.getDept_id());
		}
		
		//查用户下拉列表 初始化数据
        List<PeProdUser> entityList =  super.getFacade().getPeProdUserService().getPeProdUserBy_id_deptid(entity);
		
		List<Map<String, Object>> userListMap = new ArrayList<Map<String, Object>>();
		Map<String, Object> usertemp=null;

		if (null != entityList) {
			for (PeProdUser peProdUser : entityList) {
				usertemp=new HashMap<String, Object>();
				usertemp.put("id", peProdUser.getId()+"#"+peProdUser.getReal_name());
				usertemp.put("real_name", peProdUser.getReal_name());
				userListMap.add(usertemp);
			}
			allmap.put("userlist", userListMap);
		}
		
		YwtAttendanceDay attendanceDay = new YwtAttendanceDay();
		if (StringUtils.isNotBlank(id)) {
			attendanceDay.setId(Long.valueOf(id));
			attendanceDay.getRow().setFirst(0);
			attendanceDay.getRow().setCount(1);
		}
		attendanceDay=super.getFacade().getYwtAttendanceDayService().getYwtAttendanceDay(attendanceDay);
		allmap.put("entity", attendanceDay);
		
		if (null!=attendanceDay.getSign_in_date()) {
			allmap.put("sign_in_date",df.format(attendanceDay.getSign_in_date()));
		}
		if (null!=attendanceDay.getSign_out_date()) {
			allmap.put("sign_out_date",df.format(attendanceDay.getSign_out_date()));
		}
		
		if (null!=attendanceDay.getUser_id()&&null!=attendanceDay.getReal_name()) {
			String []user_id_name_arry={attendanceDay.getUser_id()+"#"+attendanceDay.getReal_name()};
			allmap.put("user_id_name_arry", user_id_name_arry);
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

}
