package com.ebiz.mmt.web.struts.webservice;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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

import com.ebiz.mmt.domain.Attachment;
import com.ebiz.mmt.domain.KonkaBaseTypeData;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.domain.SysModule;
import com.ebiz.mmt.domain.YwtTask;
import com.ebiz.mmt.domain.YwtTaskReceive;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.struts.MmtFilePathConfig;
import com.ebiz.mmt.web.util.DESPlus;
import com.ebiz.mmt.web.util.StringHelper;
import com.ebiz.ssi.util.EncryptUtils;
import com.ebiz.ssi.web.struts.bean.Pager;
import com.ebiz.ssi.web.struts.bean.UploadFile;

/**
 * @author Pan,Gang
 * @version 2013-09-24
 */
public class YwtTaskAction extends BaseAction {

	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> allmap = new HashMap<String, Object>();
		// 默认显示当前1月的时间区间
		Date today = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM");
		String day_first = theFirstDayOfCurrentMonth();
		String day_last = df.format(today);
		String []current_yyyy_mm = df1.format(today).split("-");
		
        //allmap.put("_begin_begin_date", day_first);
        //allmap.put("_end_finsh_date", day_last);
		allmap.put("_year", current_yyyy_mm[0]);//默认正常
	    allmap.put("_month", current_yyyy_mm[1]);//默认 未完成
        allmap.put("_is_del", -1);//默认正常
        allmap.put("_task_state", -1);//默认 未完成
        allmap.put("_priority", -1);//默认 高
        
        
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		//手机端控制
		// 用户名
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
			List<SysModule> sysModuleList = getFacade().getSysModuleService().getSysModuleList(sysModule);
			naviString = StringHelper.getNaviString(sysModuleList, " > ");
		}
		allmap.put("local_str", naviString);
		
		
		KonkaBaseTypeData taskType=new KonkaBaseTypeData();
		taskType.setType_name("任务类型");
		taskType.setIs_del(0);
		taskType=super.getFacade().getKonkaBaseTypeDataService().getKonkaBaseTypeData(taskType);
		
		List<KonkaBaseTypeData> taskTypeList=null;
		if (null!=taskType && null!=taskType.getType_id()) {
			Long parTypeId=taskType.getType_id();
			taskType=new KonkaBaseTypeData();
			taskType.setPar_type_id(parTypeId);
			taskTypeList = super.getFacade().getKonkaBaseTypeDataService().getKonkaBaseTypeDataList(taskType);
		}
		allmap.put("taskTypeList", taskTypeList);
		
		
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
		//System.out.print("twttaskList");
		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);
		Pager pager = (Pager) dynaBean.get("pager");
		String id = (String) dynaBean.get("id");
		String mod_id = String.valueOf(dynaBean.get("mod_id"));
		String _fgs_dept_id = (String)dynaBean.get("_fgs_dept_id");
		String _real_name_like = (String)dynaBean.get("_real_name_like");
		String _assigned_user_name_like = (String)dynaBean.get("_assigned_user_name_like");
		String _begin_begin_date = (String)dynaBean.get("_begin_begin_date");
		String _end_finsh_date = (String) dynaBean.get("_end_finsh_date");//
		String _year = (String) dynaBean.get("_year");//
		String _month = (String) dynaBean.get("_month");//
		String _task_name_like = (String)dynaBean.get("_task_name_like");
		String _par_task_name_like = (String)dynaBean.get("_par_task_name_like");
		String _task_state = (String) dynaBean.get("_task_state");
		String _priority = (String) dynaBean.get("_priority");
		String _task_type = (String) dynaBean.get("_task_type");
		
		String _is_del = (String) dynaBean.get("_is_del");
		String _par_task_id = (String) dynaBean.get("_par_task_id");//当前任务的id 用于查找子任务
		
		String excel_all = (String) dynaBean.get("excel_all");//
		String page =(String) dynaBean.get("page");
		//手机端控制
		String first = (String) dynaBean.get("first");
		String count = (String) dynaBean.get("count");
		// 用户名
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
		YwtTask entity = new YwtTask();
		if (StringUtils.isNotBlank(id)) {
			entity.setId(Long.valueOf(id));
		}
		if (null!=_is_del&&Integer.valueOf(_is_del)!=-1) {
			entity.setIs_del(Long.valueOf(_is_del));
		}
		if (null!=_task_state&&Integer.valueOf(_task_state)!=-1) {
			entity.setTask_state(Long.valueOf(_task_state));
		}
		if (null!=_priority&&Integer.valueOf(_priority)!=-1) {
			entity.setPriority((Long.valueOf(_priority)));
		}
		if (StringUtils.isNotBlank(_fgs_dept_id)) {
			entity.getMap().put("_fgs_dept_id", _fgs_dept_id);
		}
		if (StringUtils.isNotBlank(_real_name_like)) {
			entity.getMap().put("_real_name_like", StringUtils.trim(_real_name_like));
		}
		if (StringUtils.isNotBlank(_assigned_user_name_like)) {
			entity.getMap().put("_assigned_user_name_like", StringUtils.trim(_assigned_user_name_like));
		}
		if (StringUtils.isNotBlank(_task_name_like)) {
			entity.getMap().put("_task_name_like", StringUtils.trim(_task_name_like));
		}
		if (StringUtils.isNotBlank(_par_task_name_like)) {
			entity.getMap().put("_par_task_name_like", StringUtils.trim(_par_task_name_like));
		}
		if (StringUtils.isNotBlank(_begin_begin_date)) {
			entity.getMap().put("_begin_begin_date", _begin_begin_date+" 00:00:00");
		}
		if (StringUtils.isNotBlank(_end_finsh_date)) {
			entity.getMap().put("_end_finsh_date", _end_finsh_date+" 23:59:59");
		}
		if (null!=_year&&!"-1".equals(_year)&&null!=_month&&!"-1".equals(_month)) {
			entity.getMap().put("_add_date", _year+"-"+_month);
		}
		if (StringUtils.isNotBlank(_par_task_id)&StringUtils.isNotBlank(_par_task_id)) {
			entity.setPar_task_id(Long.valueOf(_par_task_id));
		}
		if (StringUtils.isNotBlank(_task_type)&&StringUtils.isNumeric(_task_type)) {
			entity.setTask_type(Long.valueOf(_task_type));
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
		if (flag) {// 如果是业务员，只能看到自己的拜访记录
			entity.setUser_id(userInfo.getId());
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
		
		List<YwtTask> entityList = null;
		Long recordCount = super.getFacade().getYwtTaskService().getYwtTaskLBCount(entity);
		
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
					+ EncryptUtils.encodingFileName("任务发布列表") + ".xls");
			entity.getRow().setFirst(0);
			entity.getRow().setCount(recordCount.intValue());

			List<YwtTask> entityList1 = super.getFacade().getYwtTaskService()
					.getYwtTaskLBPaginatedList(entity);
			request.setAttribute("allList", entityList1);
			return new ActionForward("/../manager/admin/YwtTask/report_list.jsp");
		}
		
		if (StringUtils.isNotBlank(page)) {
			pager.init(recordCount, pager.getPageSize(), page);
		}
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
			entityList = super.getFacade().getYwtTaskService().getYwtTaskLBPaginatedList(entity);
		//查子任务
		if(null!=_par_task_id )
		{
			entityList = super.getFacade().getYwtTaskService().selectxiaji(entity);
			recordCount=Long.valueOf(entityList.size());
			pager.init(recordCount, pager.getPageSize(), page);
		}
		for (YwtTask YwtTask : entityList) {
			if (null!=YwtTask.getAdd_date()) {
				YwtTask.getMap().put("add_date",
						df.format(YwtTask.getAdd_date()));
			}
			if (null!=YwtTask.getBegin_date()) {
				YwtTask.getMap().put("begin_date",
						df.format(YwtTask.getBegin_date()));
			}
			if (null!=YwtTask.getFinsh_date()) {
				YwtTask.getMap().put("finsh_date",
						df.format(YwtTask.getFinsh_date()));
			}
			if (null!=YwtTask.getDel_date()) {
				YwtTask.getMap().put("del_date",
						df.format(YwtTask.getDel_date()));
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
	public ActionForward reveInit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> allmap = new HashMap<String, Object>();
		// 默认显示当前1月的时间区间
		Date today = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String day_first = theFirstDayOfCurrentMonth();
		String day_last = df.format(today);
        //allmap.put("_begin_add_date", day_first);
        //allmap.put("_end_add_date", day_last);
        allmap.put("_is_receive", -1);
        allmap.put("_audit_state", 1);
        allmap.put("_state", 0);
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		//手机端控制
		// 用户名
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
	public ActionForward reveList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String task_id=(String)dynaBean.get("task_id");
		request.setAttribute("task_id", task_id);
		return new ActionForward("/../manager/admin/YwtTask/auditList.jsp");
	}
	public ActionForward reveList1(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		//返回结果存放
		Map<String, Object> allmap = new HashMap<String, Object>();
		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);
		Pager pager = (Pager) dynaBean.get("pager");
		String mod_id = String.valueOf(dynaBean.get("mod_id"));
		String task_id = (String) dynaBean.get("task_id");//
		String _begin_add_date = (String)dynaBean.get("_begin_add_date");
		String _end_add_date = (String) dynaBean.get("_end_add_date");//
		String _real_name_like = (String)dynaBean.get("_real_name_like");
		String _task_name_like = (String)dynaBean.get("_task_name_like");
	     String _is_receive = (String) dynaBean.get("_is_receive");//
    	String _audit_state = (String) dynaBean.get("_audit_state");//
		String _state = (String) dynaBean.get("_state");//
		String page =(String) dynaBean.get("page");
		//手机端控制
		String first = (String) dynaBean.get("first");
		String count = (String) dynaBean.get("count");
		// 用户名
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
			//return null;
		}
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		YwtTaskReceive ywtTaskReceive = new YwtTaskReceive();
		if (StringUtils.isNotBlank(_real_name_like)) {
			ywtTaskReceive.getMap().put("_real_name_like",StringUtils.trim(_real_name_like));
		}
		if (StringUtils.isNotBlank(_task_name_like)) {
			ywtTaskReceive.getMap().put("_task_name_like",StringUtils.trim(_task_name_like));
		}
		if (StringUtils.isNotBlank(_begin_add_date)) {
			ywtTaskReceive.getMap().put("_begin_add_date", _begin_add_date+" 00:00:00");
		}
		if (StringUtils.isNotBlank(_end_add_date)) {
			ywtTaskReceive.getMap().put("_end_add_date", _end_add_date+" 23:59:59");
		}
		if (null!=_is_receive&&Integer.valueOf(_is_receive)!=-1) {
			ywtTaskReceive.setIs_receive(Long.valueOf(_is_receive));
		}
		if (null!=_audit_state&&Integer.valueOf(_audit_state)!=-1) {
			ywtTaskReceive.setAudit_state(Long.valueOf(_audit_state));
		}
		if (null!=_state&&Integer.valueOf(_state)!=-1) {
			ywtTaskReceive.setState(Long.valueOf(_state));
		}
		//ywtTaskReceive.setUser_id(userInfo.getId());
		if (StringUtils.isNotEmpty(task_id)) {
			ywtTaskReceive.setTask_id(Long.valueOf(task_id));

			List<YwtTaskReceive> entityList = null;
			Long recordCount = super.getFacade().getYwtTaskReceiveService()
					.getYwtTaskReceiveLBCount(ywtTaskReceive);

			if (StringUtils.isNotBlank(page)) {
				pager.init(recordCount, pager.getPageSize(), page);
			}
			// 有值传来表示手机端调的
			if (StringUtils.isNotBlank(first)) {
				ywtTaskReceive.getRow().setFirst(Integer.valueOf(first));
			} else {
				ywtTaskReceive.getRow().setFirst(pager.getFirstRow());
			}
			if (StringUtils.isNotBlank(count)) {
				ywtTaskReceive.getRow().setCount(Integer.valueOf(count));
			} else {
				ywtTaskReceive.getRow().setCount(pager.getRowCount());
			}
			entityList = super.getFacade().getYwtTaskReceiveService()
					.getYwtTaskReceiveLBPaginatedList(ywtTaskReceive);
			for (YwtTaskReceive YwtTaskReceive : entityList) {
				if (null != YwtTaskReceive.getAdd_date()) {
					YwtTaskReceive.getMap().put("add_date",
							df.format(YwtTaskReceive.getAdd_date()));
				}
				if (null != YwtTaskReceive.getReve_finsh_date()) {
					YwtTaskReceive.getMap().put("reve_finsh_date",
							df.format(YwtTaskReceive.getReve_finsh_date()));
				}
			}

			// 封装成JSON字符串

			allmap.put("total", recordCount);
			if (entityList == null) {
				String[] str = {};
				allmap.put("rows", str);
			} else {
				allmap.put("rows", entityList);
			}
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
	public ActionForward audit_save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		Map<String, Object> allmap = new HashMap<String, Object>();
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
		String id = (String) dynaBean.get("id");//任务ID
		String is_receive = (String) dynaBean.get("is_receive");//完成状态
		String audit_info = (String) dynaBean.get("audit_info");//完成状态
		
		YwtTaskReceive YwtTaskReceive=new YwtTaskReceive();//任务表
		this.copyProperties(YwtTaskReceive, dynaBean);
		if (StringUtils.isNotBlank(id)) {
			
			if (!"2".equals(is_receive)) {
				YwtTaskReceive.setAudit_user_id(userInfo.getId());
				YwtTaskReceive.setAudit_name(userInfo.getReal_name());
				YwtTaskReceive.setAudit_date(new Date());
				YwtTaskReceive.setAudit_state(0L);
				//审核意见
				if (StringUtils.isNotEmpty(audit_info)) {
					YwtTaskReceive.setAudit_info(audit_info);
				}
			}
			//设置为未接收
			if(StringUtils.isNotBlank(is_receive)){
				YwtTaskReceive.setIs_receive(Long.valueOf(is_receive));
			}
			int updatecount=super.getFacade().getYwtTaskReceiveService()
					.modifyYwtTaskReceive(YwtTaskReceive);
			 if (updatecount>0) {
				 allmap.put("id",updatecount);
				 allmap.put("res","审核成功！");
			 }else {
				 allmap.put("res","审核失败！");
			 }
		}else{
			allmap.put("res","审核信息获取失败！");
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

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String inuser_id = (String) dynaBean.get("user_id");
		// 密码
		String inuserpass = (String) dynaBean.get("userpass");
		//版本号
		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");
		//web端取session
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
		//添加日期默认当天
		Date today = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String begin_date = df.format(today);
		Map<String, Object> allmap = new HashMap<String, Object>();
		allmap.put("begin_date", begin_date);
		allmap.put("assigned_user_id", userInfo.getId());
		allmap.put("assigned_user_name", userInfo.getReal_name());
		PeProdUser entity=new PeProdUser();
		if (null!=userInfo.getId()) {
			entity.setId(userInfo.getId());
		}
		if (null!=userInfo.getDept_id()) {
			entity.setDept_id(userInfo.getDept_id());
		}
		//查用户下拉列表
		List<PeProdUser> entityList =  super.getFacade().getPeProdUserService().getPeProdUserBy_id_deptid(entity);
		
		List<Map<String, Object>> reveUserListMap = new ArrayList<Map<String, Object>>();
		Map<String, Object> usertemp=null;

		if (null != entityList) {
			for (PeProdUser peProdUser : entityList) {
				usertemp=new HashMap<String, Object>();
				usertemp.put("id", peProdUser.getId()+"&"+peProdUser.getReal_name());
				usertemp.put("real_name", peProdUser.getReal_name());
				reveUserListMap.add(usertemp);
			}
			allmap.put("reveUserList", reveUserListMap);
		}
		KonkaBaseTypeData taskType=new KonkaBaseTypeData();
		taskType.setType_name("任务类型");
		taskType.setIs_del(0);
		taskType=super.getFacade().getKonkaBaseTypeDataService().getKonkaBaseTypeData(taskType);
		
		List<KonkaBaseTypeData> taskTypeList=null;
		if (null!=taskType && null!=taskType.getType_id()) {
			Long parTypeId=taskType.getType_id();
			taskType=new KonkaBaseTypeData();
			taskType.setPar_type_id(parTypeId);
			taskTypeList = super.getFacade().getKonkaBaseTypeDataService().getKonkaBaseTypeDataList(taskType);
		}
		allmap.put("taskTypeList", taskTypeList);
		
		
		List<YwtTaskReceive> revetasklist=null;
		revetasklist=getReceiveTask(userInfo.getId(),null);
		if(null!=revetasklist){
			allmap.put("revetasklist", revetasklist);
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
			return null;
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		String id = (String) dynaBean.get("id");//任务ID
		String par_task_id = (String) dynaBean.get("par_task_id");//任务ID
		String mod_id = (String) dynaBean.get("mod_id");//
		
		String task_name = (String) dynaBean.get("task_name");//任务名称
		String task_type = (String) dynaBean.get("task_type");//任务类型
		String task_state = (String) dynaBean.get("task_state");//任务状态  0：未开始/1：进行中/2：已完成
		
		String content = (String) dynaBean.get("content");// 
		String assigned_user_id = (String) dynaBean.get("assigned_user_id");//任务交办人
		String assigned_user_name = (String) dynaBean.get("assigned_user_name");//任务交办人
		String[] receive_user_id_name = request.getParameterValues("receive_user_id");// 接收人id和名称放一起格式：id&name
		String receive_user_id_name_mobile=(String) dynaBean.get("receive_user_id_name_mobile");
		String begin_date = (String) dynaBean.get("begin_date");// 预计完成时间
		String finsh_date = (String) dynaBean.get("finsh_date");// 预计完成时间
		String remark = (String) dynaBean.get("remark");// 备注
		String is_del = (String) dynaBean.get("is_del");//是否删除
		String priority = (String) dynaBean.get("priority");//是否删除
		String data_source=(String) dynaBean.get("data_source");//
		
		YwtTask ywtTask=new YwtTask();//任务表
		this.copyProperties(ywtTask, dynaBean);
		if (StringUtils.isEmpty(id)) {
			ywtTask.setUser_id(userInfo.getId());
			ywtTask.setReal_name(userInfo.getReal_name());
			ywtTask.setAdd_date(new Date());
		}
		ywtTask.setDept_id(userInfo.getDept_id());
		if (StringUtils.isNotBlank(par_task_id)) {
			if ("0".equals(par_task_id)) {
				ywtTask.setPar_task_id(0L);
				ywtTask.setPar_task_name("顶级");
			}else {
				ywtTask.setPar_task_id(Long.valueOf(par_task_id));
				List<YwtTaskReceive> par_task_name_01= getReceive(userInfo.getId(), Long.valueOf(par_task_id));
				if (null!=par_task_name_01 && par_task_name_01.size()>0 && null!=par_task_name_01.get(0) && StringUtils.isNotBlank(par_task_name_01.get(0).getTask_name())) {
					ywtTask.setPar_task_name(par_task_name_01.get(0).getTask_name());
				}else {
					super.renderJson(response, "父任务名称不能为空!");
					return null;
				}
			}
		}else {
			super.renderJson(response, "父任务Id不能为空!");
			return null;
		}
		
		if (StringUtils.isNotBlank(task_name)) {
			ywtTask.setTask_name(task_name);
		}else {
			super.renderJson(response, "任务名称不能为空!");
			return null;
		}
		if (StringUtils.isNotBlank(task_type)) {
			ywtTask.setTask_type(Long.valueOf(task_type));
		}else {
			super.renderJson(response, "任务类型不能为空!");
			return null;
		}
		if (null!=content&&content.length()>500) {
			super.renderJson(response, "内容不能超过500个字!");
			return null;
		}else {
			ywtTask.setContent(content);
		}
		String tempreve[]=null;
		if (!(null!=data_source&&"1".equals(data_source))) {
			if (null==receive_user_id_name||receive_user_id_name.length<1) {
				super.renderJson(response,"至少选择一个接收人!");
				return null;
		    }	
		}else {
			if (receive_user_id_name_mobile.length()>0) {
				tempreve=receive_user_id_name_mobile.split(",");
			}else {
				super.renderJson(response,"至少选择一个接收人!");
				return null;
			}
			
		}
		

		if (StringUtils.isNotBlank(assigned_user_id)) {
			ywtTask.setAssigned_user_id(Long.valueOf(assigned_user_id));
		}
		if (StringUtils.isNotBlank(begin_date)) {
			ywtTask.setBegin_date(sdf.parse(begin_date));
		}else {
			super.renderJson(response,"开始时间不能为空!");
			return null;
		}
		if (StringUtils.isNotBlank(finsh_date)) {
			ywtTask.setFinsh_date(sdf.parse(finsh_date));
		}else {
			super.renderJson(response,"完成日期不能为空!");
			return null;
		}
	
		if (null!=remark&&remark.length()>500) {
			super.renderJson(response,"备注不能超过500个字!");
			return null;
		}else {
			ywtTask.setRemark(remark);
		}
		if (StringUtils.isNotBlank(is_del)) {
			ywtTask.setIs_del(Long.valueOf(is_del));
		}else {
			super.renderJson(response,"状态不能为空!");
			return null;
		}
		if (StringUtils.isNotBlank(priority)) {
			ywtTask.setPriority(Long.valueOf(priority));
		}else {
			super.renderJson(response,"优先级不能为空!");
			return null;
		}
		if (StringUtils.isEmpty(task_state)) {
			ywtTask.setTask_state(Long.valueOf("0"));
		}else {
			ywtTask.setTask_state(Long.valueOf(task_state));
		}
		
		List<YwtTaskReceive> tempReveclist = new ArrayList<YwtTaskReceive>();
		YwtTaskReceive newYwtTaskReceive = null;
		List<String> newsReve1=new ArrayList<String>();
		//电脑端
		if (!(null!=data_source&&"1".equals(data_source))) {
			newsReve1 = Arrays.asList(receive_user_id_name);
		}else{//手机端 *替换成&符号
			List<String> newsReve1Mobile=Arrays.asList(tempreve);
			for (int i = 0; i < newsReve1Mobile.size(); i++) {
				newsReve1.add(StringUtils.replace(newsReve1Mobile.get(i), "*", "&"));
			}
		}
		List<String> newsReve=new ArrayList<String>(newsReve1);
		
		//修改任务
		if (StringUtils.isNotBlank(id)) {
			ywtTask.setId(Long.valueOf(id));
			//取当前任务的分配人员
			YwtTaskReceive oldReceiveEntity= new YwtTaskReceive();
			oldReceiveEntity.setTask_id(Long.valueOf(""+id));
			List<YwtTaskReceive> oldReceiveList = super.getFacade().getYwtTaskReceiveService().getYwtTaskReceiveList(oldReceiveEntity);
			
			if (null != newsReve && newsReve.size() > 0) {
				for (int i = 0; i < newsReve.size(); i++) {
					String new_receive[] = newsReve.get(i).split("&");
					for (int j = 0; j < oldReceiveList.size(); j++) {
						//新数据可以在老数据中找到
						if (new_receive[0].equals(""+oldReceiveList.get(j).getUser_id())) {
							tempReveclist.add(oldReceiveList.get(j));
							newsReve.remove(i);
						}
					}
				}
				for (int j = 0; j < newsReve.size(); j++) {
					String new_receive[] = newsReve.get(j).split("&");
					newYwtTaskReceive = new YwtTaskReceive();// 任务接[]表
					newYwtTaskReceive.setUser_id(Long.valueOf(new_receive[0]));//创建人ID
					if (StringUtils.isNotBlank(task_name)) {
						newYwtTaskReceive.setTask_name(task_name);// 任务名称
					}
					newYwtTaskReceive.setAdd_date(new Date());
					newYwtTaskReceive.setState(Long.valueOf(1)); //默认未完成
					newYwtTaskReceive.setIs_receive(2L); //默认未接受
					newYwtTaskReceive.setAudit_state(1L);
					//查找接收人部门
					PeProdUser receiveUser=new PeProdUser();
					receiveUser.setId(Long.valueOf(new_receive[0]));
					receiveUser=super.getFacade().getPeProdUserService().getPeProdUser(receiveUser);
					if (null!=receiveUser&&null!=receiveUser.getDept_id()) {
						newYwtTaskReceive.setDept_id(receiveUser.getDept_id());//接收部门
					}
					newYwtTaskReceive.setDept_id(userInfo.getDept_id());//接收部门
					tempReveclist.add(newYwtTaskReceive);
			    }
				
			}
			ywtTask.setYwtTaskReceive(tempReveclist);
		}else {
			ywtTask.setAdd_date(new Date());
			if (null != newsReve && newsReve.size() > 0) {
				
				for (int i = 0; i < newsReve.size(); i++) {
					String new_receive[] = newsReve.get(i).split("&");
							newYwtTaskReceive = new YwtTaskReceive();// 任务接[]表
							newYwtTaskReceive.setUser_id(Long.valueOf(new_receive[0]));//创建人ID
							if (StringUtils.isNotBlank(task_name)) {
								newYwtTaskReceive.setTask_name(task_name);// 任务名称
							}
							newYwtTaskReceive.setState(1L); //默认未完成
							newYwtTaskReceive.setIs_receive(2L); //默认未接受
							newYwtTaskReceive.setAudit_state(1L);
							//查找接收人部门
							PeProdUser receiveUser=new PeProdUser();
							receiveUser.setId(Long.valueOf(new_receive[0]));
							receiveUser=super.getFacade().getPeProdUserService().getPeProdUser(receiveUser);
							if (null!=receiveUser&&null!=receiveUser.getDept_id()) {
								newYwtTaskReceive.setDept_id(receiveUser.getDept_id());//接收部门
							}
							newYwtTaskReceive.setDept_id(userInfo.getDept_id());//接收部门
							tempReveclist.add(newYwtTaskReceive);
				}
			}
			ywtTask.setYwtTaskReceive(tempReveclist);
		}
		if (!(null!=data_source&&"1".equals(data_source))) {
			// 拿到上传的图片附件
			List<UploadFile> uploadFileList = super.uploadFile(form, MmtFilePathConfig.OTHERS_PATH, true, 0,
					new int[] { 240 });
			List<Attachment> attachmentList = new ArrayList<Attachment>();
			Attachment attachment = null;
			if (null != uploadFileList && uploadFileList.size() > 0) {
				for (UploadFile uploadFile : uploadFileList) {
					attachment = new Attachment();
					attachment.setFile_name(uploadFile.getFileName());
					attachment.setFile_type(uploadFile.getContentType());
					attachment.setFile_size(new Integer(uploadFile.getFileSize()));
					attachment.setSave_path(uploadFile.getFileSavePath());
					attachment.setSave_name(uploadFile.getFileSaveName());
					attachment.setLink_tab("YWT_TASK");
					attachment.setFile_desc(uploadFile.getFormName());
					Short isdel = new Short("0");
					attachment.setDel_mark(isdel);
					attachmentList.add(attachment);
				}
			}
			ywtTask.setAttachment(attachmentList);
		}
		Map<String, Object> allmap = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(id)) {
			super.getFacade().getYwtTaskService()
					.modifyYwtTask_YwtTaskReceive(ywtTask);
			allmap.put("id", id);
			allmap.put("res", "修改成功");
			allmap.put("link_table", "YWT_TASK");
		} else {
			 Integer newid= super.getFacade().getYwtTaskService().createYwtTask_YwtTaskReceive(ywtTask);
			 allmap.put("id", newid);
			 allmap.put("res", "添加成功");
			 allmap.put("link_table", "YWT_TASK");
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
		if (null == userInfo) {
			return null;
		}
		/*boolean flag = false;
		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(userInfo.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);
		for (PeRoleUser peRoleUser : peRoleUserList) {
			if (peRoleUser.getRole_id().equals(new Long(60))) {
				flag = true;
			}
		}*/
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
				usertemp.put("id", peProdUser.getId()+"&"+peProdUser.getReal_name());
				usertemp.put("real_name", peProdUser.getReal_name());
				userListMap.add(usertemp);
			}
			allmap.put("reveUserList", userListMap);
		}
		KonkaBaseTypeData taskType=new KonkaBaseTypeData();
		taskType.setType_name("任务类型");
		taskType.setIs_del(0);
		taskType=super.getFacade().getKonkaBaseTypeDataService().getKonkaBaseTypeData(taskType);
		
		List<KonkaBaseTypeData> taskTypeList=null;
		if (null!=taskType && null!=taskType.getType_id()) {
			Long parTypeId=taskType.getType_id();
			taskType=new KonkaBaseTypeData();
			taskType.setPar_type_id(parTypeId);
			taskTypeList = super.getFacade().getKonkaBaseTypeDataService().getKonkaBaseTypeDataList(taskType);
		}
		allmap.put("taskTypeList", taskTypeList);
		
		List<YwtTaskReceive> revetasklist=null;
		revetasklist=getReceiveTask(userInfo.getId(),null);
		if(null!=revetasklist){
			allmap.put("revetasklist", revetasklist);
		}
		YwtTask ywtTask = new YwtTask();
		if (StringUtils.isNotBlank(id)) {
			ywtTask.setId(Long.valueOf(id));
			ywtTask.getRow().setFirst(0);
			ywtTask.getRow().setCount(1);
		}
		ywtTask=super.getFacade().getYwtTaskService().getYwtTask(ywtTask);
		allmap.put("entity", ywtTask);
		if (null!=ywtTask&&null!=ywtTask.getAssigned_user_id()) {
			PeProdUser assignedUser=new PeProdUser();
			assignedUser.setId(ywtTask.getAssigned_user_id());
			assignedUser=super.getFacade().getPeProdUserService().getPeProdUser(assignedUser);
			if (null!=assignedUser&&null!=assignedUser.getReal_name()) {
				allmap.put("assigned_user_id", ywtTask.getAssigned_user_id());
				allmap.put("assigned_user_name",assignedUser.getReal_name());
			}
		}
		if (null!=ywtTask&&null!=ywtTask.getBegin_date()) {
			allmap.put("begin_date",
					df.format(ywtTask.getBegin_date()));
		}
		if (null!=ywtTask&&null!=ywtTask.getFinsh_date()) {
			allmap.put("finsh_date",
					df.format(ywtTask.getFinsh_date()));
		}
		if (null!=ywtTask&&null!=ywtTask.getDel_date()) {
			allmap.put("del_date",
					df.format(ywtTask.getDel_date()));
		}
		//查找分配当前任务的用户
		if (null!=ywtTask&&null!=ywtTask.getId()) {
			YwtTaskReceive receiveUser=new YwtTaskReceive();
			receiveUser.setTask_id(ywtTask.getId());
			List<YwtTaskReceive> receivelist=super.getFacade().getYwtTaskReceiveService().getYwtTaskReceiveList(receiveUser);
			List<String> reveUserList = new ArrayList<String>();
			PeProdUser tempUser=new PeProdUser();
			for (YwtTaskReceive ywtTaskReceive : receivelist) {
				if (null!=ywtTaskReceive&&null!=ywtTaskReceive.getUser_id()) {
					tempUser=new PeProdUser();
					tempUser.setId(ywtTaskReceive.getUser_id());
					tempUser=super.getFacade().getPeProdUserService().getPeProdUser(tempUser);
					if (null!=tempUser) {
						reveUserList.add(tempUser.getId()+"&"+tempUser.getReal_name());
					}
				}
			}
			
			String[]newsReve_arry=new String[reveUserList.size()];
			for (int i = 0; i < reveUserList.size(); i++) {
				newsReve_arry[i] = reveUserList.get(i);
			}
			allmap.put("receive_user_id_name_arry",newsReve_arry);
		}
		
		// 拿到图片
		Attachment attachment = new Attachment();
		attachment.setLink_tab("YWT_TASK");
		attachment.setLink_id(Long.valueOf(id));
		attachment.setDel_mark(new Short("0"));
		List<Attachment> attList = super.getFacade().getAttachmentService().getAttachmentList(attachment);
		allmap.put("fj_paths", attList);
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
		// 用户名
		String inuser_id = (String) dynaBean.get("user_id");
		// 密码
		String inuserpass = (String) dynaBean.get("userpass");
		//版本号
		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");
		//手机端通过用户查
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
		YwtTask entity = new YwtTask();
		if (StringUtils.isNotBlank(id)) {
			entity.setId(Long.valueOf(id));
			entity.setIs_del(Long.valueOf(1));
			entity.setDel_date(new Date());
			entity.setDel_user_id(userInfo.getId());
			int delcountid=super.getFacade().getYwtTaskService().modifyYwtTask(entity);
			allmap.put("result", delcountid);
			allmap.put("mod_id", mod_id);
			if (delcountid>0) {
				allmap.put("res", id);
				allmap.put("msg", "删除成功！");
			}else {
				allmap.put("res", id);
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
	
	public ActionForward updateIsdel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
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
		// 用户名
		String inuser_id = (String) dynaBean.get("user_id");
		// 密码
		String inuserpass = (String) dynaBean.get("userpass");
		//版本号
		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");
		//手机端通过用户查
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
		YwtTask entity = new YwtTask();
		if (StringUtils.isNotBlank(id)) {
			entity.setId(Long.valueOf(id));
			entity.setIs_del(Long.valueOf(0));
			entity.setDel_date(new Date());
			entity.setDel_user_id(userInfo.getId());
			int delcountid=super.getFacade().getYwtTaskService().modifyYwtTask(entity);
			allmap.put("result", delcountid);
			allmap.put("mod_id", mod_id);
			if (delcountid>0) {
				allmap.put("res", id);
				allmap.put("msg", "启用成功！");
			}else {
				allmap.put("res", id);
				allmap.put("msg", "启用失败！");
			}
		}else {
			allmap.put("msg", "未选择要启用的数据！");
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
		if (null == userInfo) {
			return null;
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
				usertemp.put("id", peProdUser.getId()+"&"+peProdUser.getReal_name());
				usertemp.put("real_name", peProdUser.getReal_name());
				userListMap.add(usertemp);
			}
			allmap.put("userlist", userListMap);
		}
		KonkaBaseTypeData taskType=new KonkaBaseTypeData();
		taskType.setType_name("任务类型");
		taskType=super.getFacade().getKonkaBaseTypeDataService().getKonkaBaseTypeData(taskType);
		
		List<KonkaBaseTypeData> taskTypeList=null;
		if (null!=taskType && null!=taskType.getType_id()) {
			Long parTypeId=taskType.getType_id();
			taskType=new KonkaBaseTypeData();
			taskType.setPar_type_id(parTypeId);
			taskTypeList = super.getFacade().getKonkaBaseTypeDataService().getKonkaBaseTypeDataList(taskType);
		}
		allmap.put("taskTypeList", taskTypeList);
		
		YwtTask ywtTask = new YwtTask();
		if (StringUtils.isNotBlank(id)) {
			ywtTask.setId(Long.valueOf(id));
			ywtTask.getRow().setFirst(0);
			ywtTask.getRow().setCount(1);
		}
		ywtTask=super.getFacade().getYwtTaskService().getYwtTask(ywtTask);
		allmap.put("entity", ywtTask);
		if (null!=ywtTask&&null!=ywtTask.getAssigned_user_id()) {
			PeProdUser assignedUser=new PeProdUser();
			assignedUser.setId(ywtTask.getAssigned_user_id());
			assignedUser=super.getFacade().getPeProdUserService().getPeProdUser(assignedUser);
			if (null!=assignedUser&&null!=assignedUser.getReal_name()) {
				allmap.put("assigned_user_id", ywtTask.getAssigned_user_id());
				allmap.put("assigned_user_name",assignedUser.getReal_name());
			}
		}
		if (null!=ywtTask.getBegin_date()) {
			allmap.put("begin_date",
					df.format(ywtTask.getBegin_date()));
		}
		if (null!=ywtTask.getFinsh_date()) {
			allmap.put("finsh_date",
					df.format(ywtTask.getFinsh_date()));
		}
		if (null!=ywtTask.getDel_date()) {
			allmap.put("del_date",
					df.format(ywtTask.getDel_date()));
		}
		//查找分配当前任务的用户
		if (null!=ywtTask&&null!=ywtTask.getId()) {
			YwtTaskReceive receiveUser=new YwtTaskReceive();
			receiveUser.setTask_id(ywtTask.getId());
			List<YwtTaskReceive> receivelist=super.getFacade().getYwtTaskReceiveService().getYwtTaskReceiveList(receiveUser);
			List<String> reveUserList = new ArrayList<String>();
			PeProdUser tempUser=new PeProdUser();
			for (YwtTaskReceive ywtTaskReceive : receivelist) {
				if (null!=ywtTaskReceive&&null!=ywtTaskReceive.getUser_id()) {
					tempUser=new PeProdUser();
					tempUser.setId(ywtTaskReceive.getUser_id());
					tempUser=super.getFacade().getPeProdUserService().getPeProdUser(tempUser);
					if (null!=tempUser) {
						reveUserList.add(tempUser.getId()+"&"+tempUser.getReal_name());
					}
				}
			}
			
			String[]receive_user_id_name_arry=new String[reveUserList.size()];
			for (int i = 0; i < reveUserList.size(); i++) {
				receive_user_id_name_arry[i] = reveUserList.get(i);
			}
			allmap.put("receive_user_id_name_arry",reveUserList.toArray());
		}
		
		// 拿到图片
		Attachment attachment = new Attachment();
		attachment.setLink_tab("YWT_TASK");
		attachment.setLink_id(Long.valueOf(id));
		attachment.setDel_mark(new Short("0"));
		List<Attachment> attList = super.getFacade().getAttachmentService().getAttachmentList(attachment);
		allmap.put("fj_paths", attList);
		
		
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
	
	//打开任务分配人列表
	public ActionForward selectUser(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String curr_task_id=(String)dynaBean.get("curr_task_id");
		request.setAttribute("curr_task_id", curr_task_id);
		return new ActionForward("/../manager/admin/YwtTask/selectUser.jsp");
	}
   //待分配用户列表
	public ActionForward noChooseUser(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		//选择单或多个部门
		String select_depts = (String) dynaBean.get("select_depts");
		//String select_depts1[] = (String[])dynaBean.get("select_depts");
		//根据用户真实姓名模糊搜索
		String no_choose_user_like = (String) dynaBean.get("no_choose_user_like");
		//任务id
		String curr_task_id = (String) dynaBean.get("curr_task_id");
		//用户id
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
		
        //存放数据
		Map<String, Object> allmap = new HashMap<String, Object>();
		
		
		//当前用户的下级部门信息
		if (null!=userInfo.getDept_id()) {
			KonkaDept dept=new KonkaDept();
			dept.setPar_id(userInfo.getDept_id());
			List<KonkaDept> reveDeptList = super.getFacade()
					.getKonkaDeptService().getKonkaDeptListWithUserDeptId(dept);
			allmap.put("reveDeptList", reveDeptList);
		}
        //待接收用户列表
		YwtTask onChooseUser=new YwtTask();
        //初次选择当前用户部门下的的所有用户
		if (null!=userInfo.getId()&&null!=userInfo.getDept_id()) {
			onChooseUser.getMap().put("currUserId", userInfo.getId());
			onChooseUser.getMap().put("currUserDept", userInfo.getDept_id());
		}
		
		if (null!=select_depts&&StringUtils.isNotBlank(select_depts)) {
			String []deptArry=select_depts.split(",");
			onChooseUser.getMap().put("select_depts", deptArry);
		}
		
		if (null!=no_choose_user_like&&StringUtils.isNotBlank(no_choose_user_like)) {
			onChooseUser.getMap().put("no_choose_user_like",
					no_choose_user_like);
		}
		
		if (null!=curr_task_id&&StringUtils.isNotBlank(curr_task_id)) {
			onChooseUser.getMap().put("curr_task_id", curr_task_id);
		}
		List<Map<String, String>> noChooseMapList = new ArrayList<Map<String, String>>();
		noChooseMapList = super.getFacade().getYwtTaskService().getNoChooseList(onChooseUser);
		if (null!=noChooseMapList) {
			allmap.put("noChooseMapList", noChooseMapList);
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
	
	 //已分配用户列表
	public ActionForward chooseUser(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
        //任务id
		String curr_task_id = (String) dynaBean.get("curr_task_id");
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
        //存放数据
		Map<String, Object> allmap = new HashMap<String, Object>();
		
		//已接收当前用户列表
		if (null!=curr_task_id&&StringUtils.isNotBlank(curr_task_id)) {
			YwtTask chooseUser = new YwtTask();
			chooseUser.getMap().put("curr_task_id", curr_task_id);
			List<Map<String, String>> chooseMapList = new ArrayList<Map<String, String>>();
			chooseMapList = super.getFacade().getYwtTaskService()
					.getChooseList(chooseUser);
				allmap.put("chooseMapList", chooseMapList);
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
	
	/**
	 * 获取到接受的任务
	 * @return
	 */
	private List<YwtTaskReceive> getReceiveTask(Long user_id,Long task_id){
		List<YwtTaskReceive> templist=new ArrayList<YwtTaskReceive>();
		YwtTaskReceive ywtTaskReceive = new YwtTaskReceive();
		if (null == user_id) {
			return null;
		}else {
			ywtTaskReceive.setUser_id(Long.valueOf(user_id));
		}
		if (null!=task_id) {
			ywtTaskReceive.setTask_id(task_id);
		}
		ywtTaskReceive.setIs_receive(0L);
		ywtTaskReceive.setState(1L);
		ywtTaskReceive.setAudit_state(1L);
		YwtTaskReceive kk=new YwtTaskReceive();
		kk.setTask_id(0L);
		kk.setTask_name("顶级");
		templist.add(kk);
		List<YwtTaskReceive> entityList = super.getFacade().getYwtTaskReceiveService().getYwtTaskReceiveList(ywtTaskReceive);
		for (int i = 0; i < entityList.size(); i++) {
			templist.add(entityList.get(i));
		}
		return templist;
	}
	//获取父任务名字
	private List<YwtTaskReceive> getReceive(Long user_id,Long task_id){
		List<YwtTaskReceive> templist=new ArrayList<YwtTaskReceive>();
		YwtTaskReceive ywtTaskReceive = new YwtTaskReceive();
		if (null == user_id) {
			return null;
		}else {
			ywtTaskReceive.setUser_id(Long.valueOf(user_id));
		}
		if (null!=task_id) {
			ywtTaskReceive.setTask_id(task_id);
		}
		List<YwtTaskReceive> entityList = super.getFacade().getYwtTaskReceiveService().selectYwtTaskReceiveTaskName(ywtTaskReceive);
		//System.out.println(entityList);
		for (int i = 0; i < entityList.size(); i++) {
			templist.add(entityList.get(i));
		}
		return templist;
	}
	
	//取得当前月第一天年月日
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
