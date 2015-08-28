package com.ebiz.mmt.web.struts.manager.admin;

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
public class YwtTaskReceiveAction extends BaseAction {

    @Override
    public ActionForward unspecified(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return this.list(mapping, form, request, response);
    }

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		//System.out.print("receiveList");
	    super.getModPopeDom(form, request);
		// 返回结果存放
		Map<String, Object> allmap = new HashMap<String, Object>();
		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);
		Pager pager = (Pager) dynaBean.get("pager");
		String id = (String) dynaBean.get("id");
		String mod_id = String.valueOf(dynaBean.get("mod_id"));
		String _fgs_dept_id = (String) dynaBean.get("_fgs_dept_id"); //
		String _begin_add_date = (String) dynaBean.get("_begin_add_date");//开始日期
		String _end_add_date = (String) dynaBean.get("_end_add_date");

		String _begin_reve_finsh_date = (String) dynaBean.get("_begin_reve_finsh_date");//完成日期
		String _end_reve_finsh_date = (String) dynaBean.get("_end_reve_finsh_date");//
		String _begin_audit_date = (String) dynaBean.get("_begin_audit_date");//审核日期
		String _end_audit_date = (String) dynaBean.get("_end_audit_date");

		String _reve_real_name_like = (String) dynaBean.get("_reve_real_name_like");
		String _task_id = (String) dynaBean.get("_task_id");// 用来查当前的下级任务  任务ID
		String _task_name_like = (String) dynaBean.get("_task_name_like");//任务名称
		String _par_task_name_like = (String) dynaBean.get("_par_task_name_like");//父任务名称
		String _is_receive = (String) dynaBean.get("_is_receive");//接收状态
		String _audit_state = (String) dynaBean.get("_audit_state");//审核状态
		String _state = (String) dynaBean.get("_state");//  完成状态
		String excel_all = (String) dynaBean.get("excel_all");//
		String page = (String) dynaBean.get("page");

		//web端取session
		PeProdUser userInfo = null;
		
		if (null==userInfo) {
			userInfo = (PeProdUser) super.getSessionAttribute(request,
					Constants.USER_INFO);
		}
		if (null == userInfo) {
			return null;
		}

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		YwtTaskReceive ywtTaskReceive = new YwtTaskReceive();
		if (StringUtils.isNotBlank(id)) {
			ywtTaskReceive.setId(Long.valueOf(id));
		}
		// ywtTaskReceive.setUser_id(userInfo.getId());
		if (StringUtils.isNotBlank(_fgs_dept_id)) {
			ywtTaskReceive.getMap().put("_fgs_dept_id", _fgs_dept_id);
		}
		if (StringUtils.isNotBlank(_reve_real_name_like)) {
			ywtTaskReceive.getMap().put("_reve_real_name_like", StringUtils.trim(_reve_real_name_like));
		}
		if (StringUtils.isNotBlank(_task_id)) {
			ywtTaskReceive.getMap().put("_task_id", StringUtils.trim(_task_id));
			ywtTaskReceive.getMap().put("par_task_id", StringUtils.trim(_task_id));
		}
		if (StringUtils.isNotBlank(_task_name_like)) {
			ywtTaskReceive.getMap().put("_task_name_like", StringUtils.trim(_task_name_like));
		}
		if (StringUtils.isNotBlank(_par_task_name_like)) {
			ywtTaskReceive.getMap().put("_par_task_name_like", StringUtils.trim(_par_task_name_like));
		}
		if (StringUtils.isNotBlank(_begin_add_date)) {
			ywtTaskReceive.getMap().put("_begin_add_date", _begin_add_date + " 00:00:00");
		}
		if (StringUtils.isNotBlank(_end_add_date)) {
			ywtTaskReceive.getMap().put("_end_add_date", _end_add_date + " 23:59:59");
		}

		if (StringUtils.isNotBlank(_begin_reve_finsh_date)) {
			ywtTaskReceive.getMap().put("_begin_reve_finsh_date", _begin_reve_finsh_date + " 00:00:00");
		}
		if (StringUtils.isNotBlank(_end_reve_finsh_date)) {
			ywtTaskReceive.getMap().put("_end_reve_finsh_date", _end_reve_finsh_date + " 23:59:59");
		}

		if (StringUtils.isNotBlank(_begin_audit_date)) {
			ywtTaskReceive.getMap().put("_begin_audit_date", _begin_audit_date + " 00:00:00");
		}
		if (StringUtils.isNotBlank(_end_audit_date)) {
			ywtTaskReceive.getMap().put("_end_audit_date", _end_audit_date + " 23:59:59");
		}

		if (null != userInfo && null != userInfo.getId()) {
			ywtTaskReceive.getMap().put("my_self_user_id", userInfo.getId());
		}
		List<String> is_receive_s = new ArrayList<String>();// 接收状态
		List<String> audit_state_s = new ArrayList<String>(); // 审核状态
		List<String> state_s = new ArrayList<String>(); // 完成状态

		// 任务接收 查找为接受的任务 只查看下发给自己的
		if ("108027".equals(mod_id)) {
			if (null != userInfo.getId()) {
				// 待办任务只能看自己的
				ywtTaskReceive.setUser_id(userInfo.getId());
			}
			//System.out.println("任务接收");
			if (StringUtils.isNotBlank(_is_receive) && (!"-1".equals(_is_receive))) {
				is_receive_s.add(_is_receive);
			} else {
				is_receive_s.add("0");// 接收
				is_receive_s.add("1");// 拒绝
				is_receive_s.add("2");// 未接收
			}
			ywtTaskReceive.getMap().put("is_receive_temp", is_receive_s.toArray());
			audit_state_s.add("1"); // 未审核的
			ywtTaskReceive.getMap().put("audit_state_temp", audit_state_s.toArray());
			state_s.add("1"); // 未完成的
			ywtTaskReceive.getMap().put("state_temp", state_s.toArray());
		}
		// 任务追踪
		if ("108033".equals(mod_id)) {
			if (null != userInfo.getId()) {
				// 已办任务只能看自己的
				ywtTaskReceive.setUser_id(userInfo.getId());
			}
			//System.out.println("任务追踪");
			is_receive_s.add("0");// 接收
			is_receive_s.add("1");// 拒绝
			ywtTaskReceive.getMap().put("is_receive_temp", is_receive_s.toArray());

			if (null != _audit_state && Integer.valueOf(_audit_state) != -1) {
				audit_state_s.add(_audit_state);
				ywtTaskReceive.getMap().put("audit_state", audit_state_s.toArray());
			} else {
				audit_state_s.add("1");// 未审核
				ywtTaskReceive.getMap().put("audit_state_temp", audit_state_s.toArray());
			}
			if (null != _state && Integer.valueOf(_state) != -1) {
				state_s.add(_state);
				ywtTaskReceive.getMap().put("state", state_s.toArray());
			} else {
				state_s.add("0");// 完成
				state_s.add("1");// 未完成
				ywtTaskReceive.getMap().put("state_temp", state_s.toArray());
			}
		}

		if ("108033".equals(mod_id) || "108034".equals(mod_id)) {

			ywtTaskReceive.setUser_id(userInfo.getId()); // 我接收的

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
				ywtTaskReceive.getMap().put("session_user_id", userInfo.getId());
				ywtTaskReceive.getMap().put("my_self_user_id", userInfo.getId());
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
						ywtTaskReceive.getMap().put("dept_id_start", _dept_id);
						// ywtTaskReceive.getMap().put("fgs_dept_value",
						// _dept_id);
					}
					break;
				case 7:
					// 我所在的部门及以下部门可见
					_dept_id = userInfo.getDept_id(); // 默认为当前用户所在部门
					ywtTaskReceive.getMap().put("dept_id_start", _dept_id);
					break;
				case 0:
					_dept_id = userInfo.getDept_id(); // 默认为当前用户所在部门
					// ywtTaskReceive.getMap().put("dept_id_start", __dept_id);
					ywtTaskReceive.getMap().put("filter_by_ywy_id_eq", userInfo.getId());
					break;
				default:
					// 出错
				}
				ywtTaskReceive.getMap().put("session_user_id", userInfo.getId());
				ywtTaskReceive.getMap().put("my_self_user_id", userInfo.getId());
			}
		}
		List<YwtTaskReceive> entityList = null;
		Long recordCount = super.getFacade().getYwtTaskReceiveService().getYwtTaskReceiveLBCount(ywtTaskReceive);

		// 导出
		if (StringUtils.isNotBlank(excel_all) && "1".equals(excel_all)) {

			if (recordCount.intValue() > 20000) {
				renderJavaScript(response, "alert('" + super.getMessage(request, "export.too.many")
						+ "！');history.back();");
				return null;
			}
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename=" + EncryptUtils.encodingFileName("我的任务列表")
					+ ".xls");
			ywtTaskReceive.getRow().setFirst(0);
			ywtTaskReceive.getRow().setCount(recordCount.intValue());

			List<YwtTaskReceive> entityList1 = super.getFacade().getYwtTaskReceiveService()
					.getYwtTaskReceiveLBPaginatedList(ywtTaskReceive);
			request.setAttribute("allList", entityList1);
			return new ActionForward("/../manager/admin/YwtTaskReceive/report_list.jsp");
		}

		if (StringUtils.isNotBlank(page)) {
			pager.init(recordCount, pager.getPageSize(), page);
		}
		 pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		 ywtTaskReceive.getRow().setFirst(pager.getFirstRow());
		 ywtTaskReceive.getRow().setCount(pager.getRowCount());
		
		if(StringUtils.isNotBlank(_task_id))
		{
			entityList = super.getFacade().getYwtTaskReceiveService().selectSubTaskList(ywtTaskReceive);
			page="1";
			recordCount=Long.valueOf(entityList.size());
			pager.init(recordCount, pager.getPageSize(), page);
		}else
		{
			entityList = super.getFacade().getYwtTaskReceiveService().getYwtTaskReceiveLBPaginatedList(ywtTaskReceive);
		}
		

		if (entityList != null && entityList.size() > 0) {
			for (YwtTaskReceive YwtTaskReceive : entityList){
				if (null != YwtTaskReceive && null != YwtTaskReceive.getId()) {
					YwtTaskReceive.getMap().put("id", ywtTaskReceive.getId());
				}
				if (null != YwtTaskReceive.getAdd_date()) {
					YwtTaskReceive.getMap().put("add_date", df.format(YwtTaskReceive.getAdd_date()));
				}
				if (null != YwtTaskReceive.getMap().get("begin_date")) {
					YwtTaskReceive.getMap().put("begin_date", df.format(YwtTaskReceive.getMap().get("begin_date")));
				}
				if(null!=YwtTaskReceive.getMap().get("priority"))
				{
					YwtTaskReceive.getMap().put("priority", YwtTaskReceive.getMap().get("priority"));
				}
				if(null!=YwtTaskReceive.getMap().get("memo"))
				{
					YwtTaskReceive.getMap().put("memo", YwtTaskReceive.getMap().get("memo"));
				}
				if (null != YwtTaskReceive.getMap().get("finsh_date")) {
					YwtTaskReceive.getMap().put("finsh_date", df.format(YwtTaskReceive.getMap().get("finsh_date")));
				}
				if (null != YwtTaskReceive.getReve_finsh_date()) {
					YwtTaskReceive.getMap().put("reve_finsh_date", df.format(YwtTaskReceive.getReve_finsh_date()));
				}
				if (null != YwtTaskReceive.getAudit_date()) {
					YwtTaskReceive.getMap().put("audit_date", df.format(YwtTaskReceive.getAudit_date()));
				}
				if (null != YwtTaskReceive.getAudit_date()) {
					YwtTaskReceive.getMap().put("audit_info", YwtTaskReceive.getAudit_info());
				}
			}
		}
		// 清空状态条件
		is_receive_s = null;
		audit_state_s = null;
		state_s = null;
		// 封装成JSON字符串
		allmap.put("total", recordCount);
		if (entityList == null) {
			String[] str = {};
			allmap.put("rows", str);
		} else {
			allmap.put("rows", entityList);
		}
		allmap.put("mod_id", mod_id);
		request.setAttribute("allmap", allmap);
		request.setAttribute("entityList", entityList);
		if("108033".equals(mod_id)){
			return new ActionForward("/admin/YwtTaskReceive/listNew1.jsp");
		}else if("108027".equals(mod_id))
		{
			return new ActionForward("/admin/YwtTaskReceive/listNew.jsp");
		}else{
			return new ActionForward("/admin/YwtTaskReceive/list2.jsp");
		}
		
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		resetToken(request);
		
		//web端取session
		PeProdUser userInfo = null;
		
		if (null==userInfo) {
			userInfo = (PeProdUser) super.getSessionAttribute(request,
					Constants.USER_INFO);
		}
		if (null == userInfo) {
			return null;
		}
		Map<String, Object> allmap = new HashMap<String, Object>();
		String id = (String) dynaBean.get("id");// 主键id
		String mod_id = (String) dynaBean.get("mod_id");//
		String complete_info = (String) dynaBean.get("complete_info");//接收完成意见
		String is_receive = (String) dynaBean.get("is_receive");// 接收状态
		String state = (String) dynaBean.get("state");// 完成状态
		String audit_info = (String) dynaBean.get("audit_info");//审核意见
		String audit_state = (String) dynaBean.get("audit_state");//审核状态
		YwtTaskReceive YwtTaskReceive = new YwtTaskReceive();// 任务表
		this.copyProperties(YwtTaskReceive, dynaBean);

		if (StringUtils.isNotBlank(is_receive)) {
			YwtTaskReceive.setIs_receive(Long.valueOf(is_receive));
		//	if ("0".equals(is_receive)) {// 接收时间
				YwtTaskReceive.setAdd_date(new Date());
		//	}
		}
		if (StringUtils.isNotBlank(audit_info)) {
			YwtTaskReceive.setAudit_info(audit_info);
			YwtTaskReceive.setAudit_date(new Date());
//			YwtTaskReceive.setAudit_state(Long.valueOf(0));
		}
//		if (StringUtils.isNotBlank(audit_state)) {
//			YwtTaskReceive.setAudit_state(Long.valueOf(audit_state));
//		}
		if (StringUtils.isNotBlank(state)) {
			YwtTaskReceive.setState(Long.valueOf(state));
			if ("0".equals(state)) {
				YwtTaskReceive.setReve_finsh_date(new Date());
			}
		}
		int updatecount = super.getFacade().getYwtTaskReceiveService().modifyYwtTaskReceive(YwtTaskReceive);

		// 维护发任务的表里面的完成状态
		YwtTaskReceive temp1 = new YwtTaskReceive();// 任务表
		temp1.setId(Long.valueOf(id));
		temp1 = super.getFacade().getYwtTaskReceiveService().getYwtTaskReceive(temp1);// 查当前表的task_id
		Long task_id = temp1.getTask_id();
		temp1 = new YwtTaskReceive();// 任务表
		if (null != task_id) {
			temp1.setTask_id(task_id);
			List<YwtTaskReceive> listtemp = super.getFacade().getYwtTaskReceiveService().getYwtTaskReceiveList(temp1);

			int totlecount = listtemp.size(), finishcount = 0, is_receivecount = 0;

			for (YwtTaskReceive ywtTaskReceive2 : listtemp) {
				if (ywtTaskReceive2.getState() == 0L) {// 完成
					finishcount++;
				}
				if (ywtTaskReceive2.getIs_receive() == 2L) {// 为接受
					is_receivecount++;
				}
			}
			YwtTask temptask = new YwtTask();
			if (null != task_id) {
				temptask.setId(task_id);
				// 进心中
				if (is_receivecount > 0) {
					temptask.setTask_state(1L);
				} else {// 未开始
					temptask.setTask_state(0L);
				}
				// 已完成
				if (finishcount == totlecount) {
					temptask.setTask_state(2L);
				}
				super.getFacade().getYwtTaskService().modifyYwtTask(temptask);
			}
		}
		if (updatecount > 0) {
			
			allmap.put("res", "修改成功");
		} else {
			allmap.put("res", "修改失败！");
		}
		allmap.put("mod_id", mod_id);
		request.setAttribute("allmap", allmap);
//		return new ActionForward("/admin/YwtTaskReceive/listNew.jsp");
		    StringBuffer pathBuffer = new StringBuffer();
	        pathBuffer.append("/admin/YwtTaskReceive.do?mod_id=108033");
	        // pathBuffer.append(super.encodeSerializedQueryString(entity.getQueryString()));
	        ActionForward forward = new ActionForward(pathBuffer.toString(), true);
	        // end
	        return forward;
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		saveToken(request);
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String id = (String) dynaBean.get("id");
		if (!GenericValidator.isLong(id)) {
			super.saveError(request, "errors.long", "id");
			return null;
		}
		String finish=(String) dynaBean.get("finish");
		String view=(String) dynaBean.get("view");
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		//web端取session
		PeProdUser userInfo = null;
		
		if (null==userInfo) {
			userInfo = (PeProdUser) super.getSessionAttribute(request,
					Constants.USER_INFO);
		}
		if (null == userInfo) {
			return null;
		}
		if (!(null != id && StringUtils.isNotBlank(id))) {
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
		// 存放数据
		Map<String, Object> allmap = new HashMap<String, Object>();

		PeProdUser entity = new PeProdUser();
		if (null != userInfo.getId()) {
			entity.setId(userInfo.getId());
		}
		if (null != userInfo.getDept_id()) {
			entity.setDept_id(userInfo.getDept_id());
		}
		
		allmap.put("assigned_user_id", userInfo.getId());
		allmap.put("assigned_user_name", userInfo.getReal_name());
		KonkaBaseTypeData taskType = new KonkaBaseTypeData();
		taskType.setType_name("任务类型");
		taskType = super.getFacade().getKonkaBaseTypeDataService().getKonkaBaseTypeData(taskType);

		List<KonkaBaseTypeData> taskTypeList = null;
		if (null != taskType && null != taskType.getType_id()) {
			Long parTypeId = taskType.getType_id();
			taskType = new KonkaBaseTypeData();
			taskType.setPar_type_id(parTypeId);
			taskTypeList = super.getFacade().getKonkaBaseTypeDataService().getKonkaBaseTypeDataList(taskType);
		}
		allmap.put("taskTypeList", taskTypeList);

		YwtTaskReceive YwtTaskReceive = new YwtTaskReceive();
		if (StringUtils.isNotBlank(id)) {
			YwtTaskReceive.setId(Long.valueOf(id));
			YwtTaskReceive.getRow().setFirst(0);
			YwtTaskReceive.getRow().setCount(1);
		}
		// 查用户下拉列表
		List<PeProdUser> entityList = super.getFacade().getPeProdUserService().getPeProdUserBy_id_deptid(entity);

		List<Map<String, Object>> reveUserListMap = new ArrayList<Map<String, Object>>();
		Map<String, Object> usertemp = null;

		if (null != entityList) {
			for (PeProdUser peProdUser : entityList) {
				usertemp = new HashMap<String, Object>();
				usertemp.put("id", peProdUser.getId() + "&" + peProdUser.getReal_name());
				usertemp.put("real_name", peProdUser.getReal_name());
				reveUserListMap.add(usertemp);
			}
			allmap.put("reveUserList", reveUserListMap);
		}
		YwtTaskReceive = super.getFacade().getYwtTaskReceiveService().getYwtTaskReceive(YwtTaskReceive);
		allmap.put("entity", YwtTaskReceive);
		if (null != YwtTaskReceive && null != YwtTaskReceive.getUser_id()) {
			PeProdUser reveUser = new PeProdUser();
			reveUser.setId(YwtTaskReceive.getUser_id());
			reveUser = super.getFacade().getPeProdUserService().getPeProdUser(reveUser);
			if (null != reveUser && null != reveUser.getReal_name()) {
				YwtTaskReceive.getMap().put("reve_name", reveUser.getReal_name());
			}
		}
		if (null != YwtTaskReceive && null != YwtTaskReceive.getAdd_date()) {
			allmap.put("add_date", df.format(YwtTaskReceive.getAdd_date()));
		}
		if (null != YwtTaskReceive && null != YwtTaskReceive.getMap().get("finsh_date")) {
			allmap.put("finsh_date", df.format(YwtTaskReceive.getMap().get("finsh_date")));
		}
		if (null != YwtTaskReceive.getAudit_date()) {
			YwtTaskReceive.getMap().put("audit_info", YwtTaskReceive.getAudit_info());
		}
		YwtTask ywttask = new YwtTask();
		if (null != YwtTaskReceive && null != YwtTaskReceive.getTask_id()) {
			ywttask.setId(YwtTaskReceive.getTask_id());
		}
		ywttask.getRow().setFirst(0);
		ywttask.getRow().setCount(1);
		List<YwtTask> ywttasks = super.getFacade().getYwtTaskService().getYwtTaskLBPaginatedList(ywttask);
		if (ywttasks.size() > 0) {
			ywttask = ywttasks.get(0);
			allmap.put("ywttask", ywttask);
			ywttask.getMap().put("finsh_date", df.format(ywttask.getFinsh_date()));
		}
		
		//查找分配当前任务的用户
		if (null!=ywttask &&null!=ywttask.getId()) {
			YwtTaskReceive receiveUser=new YwtTaskReceive();
			receiveUser.setTask_id(ywttask.getId());
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
			StringBuffer str= new StringBuffer();
			for (int i = 0; i < reveUserList.size(); i++) {
				receive_user_id_name_arry[i] = reveUserList.get(i);
				str.append(reveUserList.get(i)).append(",");
			}
			allmap.put("receive_user_id_name_arry",str);
		}

		// 拿到图片
		if (null != YwtTaskReceive && null != YwtTaskReceive.getTask_id()) {
			Attachment attachment = new Attachment();
			attachment.setLink_tab("YWT_TASK");
			attachment.setLink_id(YwtTaskReceive.getTask_id());
			attachment.setDel_mark(new Short("0"));
			List<Attachment> attList = super.getFacade().getAttachmentService().getAttachmentList(attachment);
			allmap.put("fj_paths", attList);
		}
		request.setAttribute("finish", finish);
		request.setAttribute("view", view);
		allmap.put("mod_id", mod_id);
		request.setAttribute("allmap", allmap);
		return new ActionForward("/admin/YwtTaskReceive/form1.jsp");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String id = (String) dynaBean.get("id");
		String view = (String) dynaBean.get("view");
		if (!GenericValidator.isLong(id)) {
			super.saveError(request, "errors.long", "id");
			return null;
		}
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		//web端取session
		PeProdUser userInfo = null;
		
		if (null==userInfo) {
			userInfo = (PeProdUser) super.getSessionAttribute(request,
					Constants.USER_INFO);
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
		
		
		// 存放数据
		Map<String, Object> allmap = new HashMap<String, Object>();

		PeProdUser entity = new PeProdUser();
		if (null != userInfo.getId()) {
			entity.setId(userInfo.getId());
		}
		if (null != userInfo.getDept_id()) {
			entity.setDept_id(userInfo.getDept_id());
		}
		
		allmap.put("assigned_user_id", userInfo.getId());
		allmap.put("assigned_user_name", userInfo.getReal_name());
		// 查用户下拉列表
		List<PeProdUser> entityList = super.getFacade().getPeProdUserService().getPeProdUserBy_id_deptid(entity);

		List<Map<String, Object>> reveUserListMap = new ArrayList<Map<String, Object>>();
		Map<String, Object> usertemp = null;

		if (null != entityList) {
			for (PeProdUser peProdUser : entityList) {
				usertemp = new HashMap<String, Object>();
				usertemp.put("id", peProdUser.getId() + "&" + peProdUser.getReal_name());
				usertemp.put("real_name", peProdUser.getReal_name());
				reveUserListMap.add(usertemp);
			}
			allmap.put("reveUserList", reveUserListMap);
		}
		KonkaBaseTypeData taskType = new KonkaBaseTypeData();
		taskType.setType_name("任务类型");
		taskType = super.getFacade().getKonkaBaseTypeDataService().getKonkaBaseTypeData(taskType);

		List<KonkaBaseTypeData> taskTypeList = null;
		if (null != taskType && null != taskType.getType_id()) {
			Long parTypeId = taskType.getType_id();
			taskType = new KonkaBaseTypeData();
			taskType.setPar_type_id(parTypeId);
			taskTypeList = super.getFacade().getKonkaBaseTypeDataService().getKonkaBaseTypeDataList(taskType);
		}
		allmap.put("taskTypeList", taskTypeList);

		YwtTaskReceive ywtTaskReceive = new YwtTaskReceive();
		if (StringUtils.isNotBlank(id)) {
			ywtTaskReceive.setId(Long.valueOf(id));
			ywtTaskReceive.getRow().setFirst(0);
			ywtTaskReceive.getRow().setCount(1);
		}
		ywtTaskReceive = super.getFacade().getYwtTaskReceiveService().getYwtTaskReceive(ywtTaskReceive);

		YwtTask ywttask = new YwtTask();
		ywttask.setId(ywtTaskReceive.getTask_id());
		ywttask = super.getFacade().getYwtTaskService().getYwtTask(ywttask);
		if (null != ywttask) {
			allmap.put("ywttask", ywttask);
		}
		allmap.put("par_task_id", ywtTaskReceive.getTask_id());
		allmap.put("par_task_name", ywtTaskReceive.getTask_name());
		allmap.put("entity", ywtTaskReceive);

		if (null != ywtTaskReceive.getAdd_date()) {
			allmap.put("add_date", df.format(ywtTaskReceive.getAdd_date()));
		}
		if (null != ywtTaskReceive.getMap().get("finsh_date")) {
			allmap.put("finsh_date", df.format(ywtTaskReceive.getMap().get("finsh_date")));
		}
		
		//查找分配当前任务的用户
		if (null!=ywttask &&null!=ywttask.getId()) {
			YwtTaskReceive receiveUser=new YwtTaskReceive();
			receiveUser.setTask_id(ywttask.getId());
			List<YwtTaskReceive> receivelist=super.getFacade().getYwtTaskReceiveService().getYwtTaskReceiveList(receiveUser);
			List<String> reveUserList = new ArrayList<String>();
			PeProdUser tempUser=new PeProdUser();
			for (YwtTaskReceive ywtTaskReceiv : receivelist) {
				if (null!=ywtTaskReceiv&&null!=ywtTaskReceiv.getUser_id()) {
					tempUser=new PeProdUser();
					tempUser.setId(ywtTaskReceiv.getUser_id());
					tempUser=super.getFacade().getPeProdUserService().getPeProdUser(tempUser);
					if (null!=tempUser) {
						reveUserList.add(tempUser.getId()+"&"+tempUser.getReal_name());
					}
				}
			}
			
			String[]receive_user_id_name_arry=new String[reveUserList.size()];
			StringBuffer str= new StringBuffer();
			for (int i = 0; i < reveUserList.size(); i++) {
				receive_user_id_name_arry[i] = reveUserList.get(i);
				str.append(reveUserList.get(i)).append(",");
			}
			allmap.put("receive_user_id_name_arry",str);
		}
		// 拿到图片
		Attachment attachment = new Attachment();
		attachment.setLink_tab("YWT_TASK");
		attachment.setLink_id(Long.valueOf(id));
		attachment.setDel_mark(new Short("0"));
		List<Attachment> attList = super.getFacade().getAttachmentService().getAttachmentList(attachment);
		allmap.put("fj_paths", attList);
		allmap.put("mod_id", mod_id);
		request.setAttribute("view", view);
		request.setAttribute("allmap", allmap);
		return new ActionForward("/../manager/admin/YwtTaskReceive/form.jsp");
	}

	public ActionForward addSubTask(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		saveToken(request);
		DynaBean dynaBean = (DynaBean) form;
		PeProdUser userInfo = null;
		
		if (null==userInfo) {
			userInfo = (PeProdUser) super.getSessionAttribute(request,
					Constants.USER_INFO);
		}
		if (null == userInfo) {
			return null;
		}
		String id = (String) dynaBean.get("id");
		YwtTaskReceive reve = new YwtTaskReceive();
		if (StringUtils.isNotBlank(id)) {
			reve.setId(Long.valueOf(id));
			reve = super.getFacade().getYwtTaskReceiveService().getYwtTaskReceive(reve);
		}
		// 添加日期默认当天
		Date today = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String begin_date = df.format(today);
		Map<String, Object> allmap = new HashMap<String, Object>();
		allmap.put("begin_date", begin_date);
		allmap.put("assigned_user_id", userInfo.getId());
		allmap.put("assigned_user_name", userInfo.getReal_name());
		if (null != reve && null != reve.getTask_id()) {
			allmap.put("par_task_id", reve.getTask_id());
			allmap.put("par_task_name", reve.getTask_name());
		} else {
			super.renderJson(response, "创建任务失败！ ");
			return null;
		}

		PeProdUser entity = new PeProdUser();
		if (null != userInfo.getId()) {
			entity.setId(userInfo.getId());
		}
		if (null != userInfo.getDept_id()) {
			entity.setDept_id(userInfo.getDept_id());
		}
		// 查用户下拉列表
		List<PeProdUser> entityList = super.getFacade().getPeProdUserService().getPeProdUserBy_id_deptid(entity);

		List<Map<String, Object>> reveUserListMap = new ArrayList<Map<String, Object>>();
		Map<String, Object> usertemp = null;

		if (null != entityList) {
			for (PeProdUser peProdUser : entityList) {
				usertemp = new HashMap<String, Object>();
				usertemp.put("id", peProdUser.getId() + "&" + peProdUser.getReal_name());
				usertemp.put("real_name", peProdUser.getReal_name());
				reveUserListMap.add(usertemp);
			}
			allmap.put("reveUserList", reveUserListMap);
		}
		KonkaBaseTypeData taskType = new KonkaBaseTypeData();
		taskType.setType_name("任务类型");
		taskType.setIs_del(0);
		taskType = super.getFacade().getKonkaBaseTypeDataService().getKonkaBaseTypeData(taskType);

		List<KonkaBaseTypeData> taskTypeList = null;
		if (null != taskType && null != taskType.getType_id()) {
			Long parTypeId = taskType.getType_id();
			taskType = new KonkaBaseTypeData();
			taskType.setPar_type_id(parTypeId);
			taskTypeList = super.getFacade().getKonkaBaseTypeDataService().getKonkaBaseTypeDataList(taskType);
		}
		allmap.put("taskTypeList", taskTypeList);
		request.setAttribute("allmap", allmap);
		return new ActionForward("/../manager/admin/YwtTaskReceive/form.jsp");
	}

	// 打开任务分配人列表
	public ActionForward selectUser(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String curr_task_id = (String) dynaBean.get("curr_task_id");
		request.setAttribute("curr_task_id", curr_task_id);
		return new ActionForward("/../manager/admin/YwtTaskReceive/selectUser.jsp");
	}

	// 待分配用户列表
	public ActionForward noChooseUser(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		// 选择单或多个部门
		String select_depts = (String) dynaBean.get("select_depts");
		// String select_depts1[] = (String[])dynaBean.get("select_depts");
		// 根据用户真实姓名模糊搜索
		String no_choose_user_like = (String) dynaBean.get("no_choose_user_like");
		// 任务id
		String curr_task_id = (String) dynaBean.get("curr_task_id");
		// 用户id
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

		// 存放数据
		Map<String, Object> allmap = new HashMap<String, Object>();

		// 当前用户的下级部门信息
		if (null != userInfo.getDept_id()) {
			KonkaDept dept = new KonkaDept();
			dept.setPar_id(userInfo.getDept_id());
			List<KonkaDept> reveDeptList = super.getFacade().getKonkaDeptService().getKonkaDeptListWithUserDeptId(dept);
			allmap.put("reveDeptList", reveDeptList);
		}
		// 待接收用户列表
		YwtTask onChooseUser = new YwtTask();
		// 初次选择当前用户部门下的的所有用户
		if (null != userInfo.getId() && null != userInfo.getDept_id()) {
			onChooseUser.getMap().put("currUserId", userInfo.getId());
			onChooseUser.getMap().put("currUserDept", userInfo.getDept_id());
		}

		if (null != select_depts && StringUtils.isNotBlank(select_depts)) {
			String[] deptArry = select_depts.split(",");
			onChooseUser.getMap().put("select_depts", deptArry);
		}

		if (null != no_choose_user_like && StringUtils.isNotBlank(no_choose_user_like)) {
			onChooseUser.getMap().put("no_choose_user_like", no_choose_user_like);
		}

		if (null != curr_task_id && StringUtils.isNotBlank(curr_task_id)) {
			onChooseUser.getMap().put("curr_task_id", curr_task_id);
		}
		List<Map<String, String>> noChooseMapList = new ArrayList<Map<String, String>>();
		noChooseMapList = super.getFacade().getYwtTaskService().getNoChooseList(onChooseUser);
		if (null != noChooseMapList) {
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

	// 保存新建子任务
	public ActionForward saveSubTask(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		//web端取session
		PeProdUser userInfo = null;
		
		if (null==userInfo) {
			userInfo = (PeProdUser) super.getSessionAttribute(request,
					Constants.USER_INFO);
		}

		if (null == userInfo) {
			return null;
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		String id = (String) dynaBean.get("id");// 任务ID
		String par_task_id = (String) dynaBean.get("par_task_id");// 任务ID
		String par_task_name = (String) dynaBean.get("par_task_name");// 任务名称
		String mod_id = (String) dynaBean.get("mod_id");//

		String task_name = (String) dynaBean.get("task_name");// 任务名称
		String task_type = (String) dynaBean.get("task_type");// 任务类型
		String task_state = (String) dynaBean.get("task_state");// 任务状态
																// 0：未开始/1：进行中/2：已完成

		String content = (String) dynaBean.get("content");//
		String assigned_user_id = (String) dynaBean.get("assigned_user_id");// 任务交办人
		String assigned_user_name = (String) dynaBean.get("assigned_user_name");// 任务交办人
		String[] receive_user_id_name = request.getParameterValues("receive_user_id");// 接收人id和名称放一起格式：id&name

		String finsh_date = (String) dynaBean.get("finsh_date");// 预计完成时间
		String remark = (String) dynaBean.get("remark");// 备注
		String is_del = (String) dynaBean.get("is_del");// 是否删除
		String priority = (String) dynaBean.get("priority");// 是否删除

		YwtTask ywtTask = new YwtTask();// 任务表
		this.copyProperties(ywtTask, dynaBean);
		if (StringUtils.isEmpty(id)) {
			ywtTask.setUser_id(userInfo.getId());
			ywtTask.setReal_name(userInfo.getReal_name());
			ywtTask.setAdd_date(new Date());
		}
		ywtTask.setDept_id(userInfo.getDept_id());
		if (StringUtils.isNotBlank(par_task_id)) {
			ywtTask.setPar_task_id(Long.valueOf(par_task_id));
		} else {
			super.renderJson(response, "父任务Id不能为空!");
			return null;
		}
		if (StringUtils.isNotBlank(par_task_name)) {
			ywtTask.setPar_task_name(par_task_name);
		} else {
			super.renderJson(response, "父任务名称不能为空!");
			return null;
		}
		if (StringUtils.isNotBlank(task_name)) {
			ywtTask.setTask_name(task_name);
		} else {
			super.renderJson(response, "任务名称不能为空!");
			return null;
		}
		if (StringUtils.isNotBlank(task_type)) {
			ywtTask.setTask_type(Long.valueOf(task_type));
		} else {
			super.renderJson(response, "任务类型不能为空!");
			return null;
		}
		if (content.length() > 500) {
			super.renderJson(response, "内容不能超过500个字!");
			return null;
		} else {
			ywtTask.setContent(content);
		}
		if (null == receive_user_id_name || receive_user_id_name.length < 1) {
			super.renderJson(response, "至少选择一个接收人!");
			return null;
		}

		if (StringUtils.isNotBlank(assigned_user_id)) {
			ywtTask.setAssigned_user_id(Long.valueOf(assigned_user_id));
		}

		if (StringUtils.isNotBlank(finsh_date)) {
			ywtTask.setFinsh_date(sdf.parse(finsh_date));
		} else {
			super.renderJson(response, "完成日期不能为空!");
			return null;
		}
		if (remark.length() > 500) {
			super.renderJson(response, "备注不能超过500个字!");
			return null;
		} else {
			ywtTask.setRemark(remark);
		}
		if (StringUtils.isNotBlank(is_del)) {
			ywtTask.setIs_del(Long.valueOf(is_del));
		} else {
			super.renderJson(response, "状态不能为空!");
			return null;
		}
		if (StringUtils.isNotBlank(priority)) {
			ywtTask.setPriority(Long.valueOf(priority));
		} else {
			super.renderJson(response, "优先级不能为空!");
			return null;
		}
		if (StringUtils.isEmpty(task_state)) {
			ywtTask.setTask_state(Long.valueOf("0"));
		} else {
			ywtTask.setTask_state(Long.valueOf(task_state));
		}

		List<YwtTaskReceive> tempReveclist = new ArrayList<YwtTaskReceive>();
		YwtTaskReceive newYwtTaskReceive = null;
		List<String> newsReve1 = Arrays.asList(receive_user_id_name);
		List<String> newsReve = new ArrayList<String>(newsReve1);

		// 修改任务
		if (StringUtils.isNotBlank(id)) {
			ywtTask.setId(Long.valueOf(id));
			// 取当前任务的分配人员
			YwtTaskReceive oldReceiveEntity = new YwtTaskReceive();
			oldReceiveEntity.setTask_id(Long.valueOf("" + id));
			List<YwtTaskReceive> oldReceiveList = super.getFacade().getYwtTaskReceiveService()
					.getYwtTaskReceiveList(oldReceiveEntity);

			if (null != newsReve && newsReve.size() > 0) {
				for (int i = 0; i < newsReve.size(); i++) {
					String new_receive[] = newsReve.get(i).split("&");
					for (int j = 0; j < oldReceiveList.size(); j++) {
						// 新数据可以在老数据中找到
						if (new_receive[0].equals("" + oldReceiveList.get(j).getUser_id())) {
							tempReveclist.add(oldReceiveList.get(j));
							newsReve.remove(i);
						}
					}
				}
				for (int j = 0; j < newsReve.size(); j++) {
					String new_receive[] = newsReve.get(j).split("&");
					newYwtTaskReceive = new YwtTaskReceive();// 任务接[]表
					newYwtTaskReceive.setUser_id(Long.valueOf(new_receive[0]));// 创建人ID
					if (StringUtils.isNotBlank(task_name)) {
						newYwtTaskReceive.setTask_name(task_name);// 任务名称
					}
					newYwtTaskReceive.setAdd_date(new Date());
					if (StringUtils.isNotBlank(assigned_user_id)) {
						newYwtTaskReceive.setAudit_user_id(Long.valueOf(assigned_user_id));// 审核人ID
					}
					if (StringUtils.isNotBlank(assigned_user_name)) {
						newYwtTaskReceive.setAudit_name(assigned_user_name);// 审核人姓名
					}
					newYwtTaskReceive.setState(Long.valueOf(1)); // 默认未完成
					newYwtTaskReceive.setIs_receive(2L); // 默认未接受
					newYwtTaskReceive.setAudit_state(1L);
					// 查找接收人部门
					PeProdUser receiveUser = new PeProdUser();
					receiveUser.setId(Long.valueOf(new_receive[0]));
					receiveUser = super.getFacade().getPeProdUserService().getPeProdUser(receiveUser);
					if (null != receiveUser && null != receiveUser.getDept_id()) {
						newYwtTaskReceive.setDept_id(receiveUser.getDept_id());// 接收部门
					}
					newYwtTaskReceive.setDept_id(userInfo.getDept_id());// 接收部门
					tempReveclist.add(newYwtTaskReceive);
				}

			}
			ywtTask.setYwtTaskReceive(tempReveclist);
		} else {
			if (null != newsReve && newsReve.size() > 0) {

				for (int i = 0; i < newsReve.size(); i++) {
					String new_receive[] = newsReve.get(i).split("&");
					//System.out.println("----------" + new_receive[0] + "--------" + new_receive[1]);
					newYwtTaskReceive = new YwtTaskReceive();// 任务接[]表
					newYwtTaskReceive.setUser_id(Long.valueOf(new_receive[0]));// 创建人ID
					if (StringUtils.isNotBlank(task_name)) {
						newYwtTaskReceive.setTask_name(task_name);// 任务名称
					}
					newYwtTaskReceive.setAdd_date(new Date());
					if (StringUtils.isNotBlank(assigned_user_id)) {
						newYwtTaskReceive.setAudit_user_id(Long.valueOf(assigned_user_id));// 审核人ID
					}
					if (StringUtils.isNotBlank(assigned_user_name)) {
						newYwtTaskReceive.setAudit_name(assigned_user_name);// 审核人姓名
					}
					newYwtTaskReceive.setState(1L); // 默认未完成
					newYwtTaskReceive.setIs_receive(2L); // 默认未接受
					newYwtTaskReceive.setAudit_state(1L);
					// 查找接收人部门
					PeProdUser receiveUser = new PeProdUser();
					receiveUser.setId(Long.valueOf(new_receive[0]));
					receiveUser = super.getFacade().getPeProdUserService().getPeProdUser(receiveUser);
					if (null != receiveUser && null != receiveUser.getDept_id()) {
						newYwtTaskReceive.setDept_id(receiveUser.getDept_id());// 接收部门
					}
					newYwtTaskReceive.setDept_id(userInfo.getDept_id());// 接收部门
					tempReveclist.add(newYwtTaskReceive);
				}
			}

			ywtTask.setYwtTaskReceive(tempReveclist);
		}

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
		Map<String, Object> allmap = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(id)) {
			super.getFacade().getYwtTaskService().modifyYwtTask_YwtTaskReceive(ywtTask);
			super.renderJson(response, "修改成功");
			return null;
		} else {
			super.getFacade().getYwtTaskService().createYwtTask_YwtTaskReceive(ywtTask);
			super.renderJson(response, "添加成功");
			return null;
		}
	}

	/**
	 * 获取到接受的任务
	 * 
	 * @return
	 */
	private List<YwtTaskReceive> getReceiveTask(Long user_id) {

		YwtTaskReceive ywtTaskReceive = new YwtTaskReceive();
		if (null == user_id) {
			return null;
		} else {
			ywtTaskReceive.setUser_id(Long.valueOf(user_id));
		}
		ywtTaskReceive.setIs_receive(0L);
		ywtTaskReceive.setState(1L);
		ywtTaskReceive.setAudit_state(1L);
		List<YwtTaskReceive> entityList = super.getFacade().getYwtTaskReceiveService()
				.getYwtTaskReceiveList(ywtTaskReceive);
		return entityList;
	}

	/**
	 * 检查这条任务是不是我的 是我的 有没有子任务 子任务有没有都完成
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward checkCurTask(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		// 用户id
		String inuser_id = (String) dynaBean.get("user_id");
		// 密码
		String inuserpass = (String) dynaBean.get("userpass");

		String task_id = (String) dynaBean.get("take_id");

		String id = (String) dynaBean.get("id");
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

		// 存放数据
		Map<String, Object> allmap = new HashMap<String, Object>();

		YwtTask subtask = new YwtTask();
		subtask.setPar_task_id(Long.valueOf(task_id));
		List<YwtTask> subcount = super.getFacade().getYwtTaskService().getYwtTaskList(subtask);
		if (null != subcount && subcount.size() > 0) {// 判断当前任务是否有子任务
			YwtTaskReceive v1 = new YwtTaskReceive();
			v1.getMap().put("cur_task_id", task_id);
			List<YwtTaskReceive> cursubtasklist = super.getFacade().getYwtTaskReceiveService().getCurTaskSub(v1);
			if (null != cursubtasklist && cursubtasklist.size() > 0) {
				for (YwtTaskReceive ywtTaskReceive : cursubtasklist) {
					if (null != ywtTaskReceive && null != ywtTaskReceive.getState() && ywtTaskReceive.getState() == 1) {
						allmap.put("mark", 1);
						allmap.put("msg", "当前下发的子任务任务未全部完成");
					}
				}
			}
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

	// 取得当前月第一天年月日
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

	// 手机端查用户
}
