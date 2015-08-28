package com.ebiz.mmt.web.struts;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.alibaba.fastjson.JSON;
import com.ebiz.mmt.domain.Attachment;
import com.ebiz.mmt.domain.BranchAssign;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaMobileTerminalFeedback;
import com.ebiz.mmt.domain.KonkaOrderAuditProcess;
import com.ebiz.mmt.domain.KonkaOrderAuditProcessNode;
import com.ebiz.mmt.domain.KonkaOrderInfo;
import com.ebiz.mmt.domain.KonkaOrderInfoAudit;
import com.ebiz.mmt.domain.KonkaOrderInfoDetails;
import com.ebiz.mmt.domain.KonkaOrderMessageProcess;
import com.ebiz.mmt.domain.KonkaR3DeptStore;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.KonkaXxZmd;
import com.ebiz.mmt.domain.KonkaXxZmdAuditUserInfo;
import com.ebiz.mmt.domain.KonkaoaEventInfo;
import com.ebiz.mmt.domain.KonkaoaFiles;
import com.ebiz.mmt.domain.KonkaoaFilesAuditAgentUser;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.util.DESPlus;
import com.ebiz.ssi.web.struts.bean.Pager;

public class KonkaOrderAuditAction extends MobileBaseAction {

	private static final Boolean check_user_allowd = true;

	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		// 默认显示当前1周的时间区间
		Date today = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(today);

		calendar.add(Calendar.DATE, -7);
		String day_first = df.format(calendar.getTime());
		String day_last = df.format(today);
		dynaBean.set("order_date_start", day_first);// 默认当前日期的前7天
		dynaBean.set("order_date_end", day_last);
		return this.list(mapping, form, request, response);
	}

	/**
	 * 查询待审核订单
	 */
	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String order_date_start = (String) dynaBean.get("order_date_start");
		String order_date_end = (String) dynaBean.get("order_date_end");
		String trade_index_like = (String) dynaBean.get("trade_index_like");// 流程类型
		String user_shop_name_like = (String) dynaBean.get("user_shop_name_like");// 订单审核状态
		String process_id = (String) dynaBean.get("process_id");

		String pay_type = (String) dynaBean.get("pay_type");
		String send_type = (String) dynaBean.get("send_type");
		String pd_name_like = (String) dynaBean.get("pd_name_like");
		String ag_like = (String) dynaBean.get("ag_like");
		 //版本号
		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");

		Pager pager = (Pager) dynaBean.get("pager");

		PeProdUser userInfo = null;

		PeProdUser suser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		if (null != suser) {
			logger.info("session user name: {}", suser.getUser_name());
			userInfo = suser;
		} else if (check_user_allowd) {
			String user_id = (String) dynaBean.get("user_id");
			String username = (String) dynaBean.get("username");
			String userpass = (String) dynaBean.get("userpass");
			
			if (StringUtils.isBlank(user_id) && StringUtils.isBlank(userpass)) {
				super.renderHtml(response, "无效的访问参数！");
				return null;
			}

			userInfo = new PeProdUser();

			if (user_id != null) {
				userInfo.setId(Long.valueOf(user_id));
				userInfo.setPass_word(new DESPlus().encrypt(userpass));
				userInfo = getFacade().getPeProdUserService().getPeProdUser(userInfo);
			} else {
				userInfo = checkUserid(username, userpass,android_version,ios_version);
			}
		}

		if (null == userInfo) {
			super.renderHtml(response, "用户名和密码不匹配，身份认证失败！");
			return null;
		}

		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(userInfo.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);

		boolean role_id_eq_60 = false;
		for (PeRoleUser peRoleUser : peRoleUserList) {
			if (peRoleUser.getRole_id() == 60L) {
				role_id_eq_60 = true;
				break;
			}
		}

		KonkaOrderInfo entity = new KonkaOrderInfo();
		entity.setIs_del(0);
		entity.getMap().put("where_by_process_id", "true");

		// 处理业务员特例
		if (role_id_eq_60) {
			// // 业务员，业务员只能看见其下客户的待审核订单
			entity.getMap().put("querybycust_userid_eq", userInfo.getId()); //
			// 按客户查询
		}
		entity.getMap().put("querybyrole_userid_eq", userInfo.getId()); // 按下一个审核角色查询

		// 数据级别判断开始
		Long dept_id = userInfo.getDept_id(); // 默认为当前用户所在部门
		int max_dlevel = super.getMaxDLevel(userInfo.getId()); // 获取当前用户的最高可视部门级别
		logger.info("Max level : {}", max_dlevel);
		switch (max_dlevel) {
		case 9:
			// 集团及以下部门可见
			dept_id = 0L; // 0表示部门根节点，即“多媒体事业本部”
			break;
		case 8:
			// 分公司及以下部门可见
			KonkaDept dept_fgs = super.getKonkaDeptForFgs(Long.valueOf(dept_id)); // 查询部门分公司
			if (null != dept_fgs && null != dept_fgs.getDept_id()) {
				dept_id = dept_fgs.getDept_id(); // 分公司部门ID
			}
			break;
		case 7:
			// 我所在的部门及以下部门可见
			dept_id = userInfo.getDept_id(); // 默认为当前用户所在部门
			break;
		case 0:
			entity.getMap().put("querybycust_userid_eq", userInfo.getId()); // 按客户查询
			break;
		default:
			// 出错
		}
		// 数据级别判断结束

		entity.getMap().put("par_or_children_dept_id", dept_id);
		entity.getMap().put("session_user_id", userInfo.getId());// 获取当前客户所查看的数据部门
		entity.setIs_submit(1); // 0:表示暂存；1：表示正式提交
		if (StringUtils.isNotBlank(order_date_start)) {
			entity.getMap().put("start_date", order_date_start);
		}
		if (StringUtils.isNotBlank(order_date_end)) {
			entity.getMap().put("end_date", order_date_end);
		}
		if (StringUtils.isNotBlank(trade_index_like)) {
			entity.getMap().put("trade_index_like", trade_index_like);
		}
		if (StringUtils.isNotBlank(process_id)) {
			entity.setProcess_id(Long.valueOf(process_id));
		}
		if (StringUtils.isNotBlank(user_shop_name_like)) {
			entity.getMap().put("user_shop_name_like", user_shop_name_like);
		}
		if (StringUtils.isNotBlank(send_type)) {
			entity.setSend_type(Integer.valueOf(send_type));
		}
		if (StringUtils.isNotBlank(pay_type)) {
			entity.setPay_type(Integer.valueOf(pay_type));
		}
		if (StringUtils.isNotBlank(pd_name_like)) {
			entity.getMap().put("pd_name_like", pd_name_like);
		}
		if (StringUtils.isNotBlank(ag_like)) {
			entity.getMap().put("ag_like", ag_like);
		}

		// 获取流程列表
		KonkaDept dept = super.getSuperiorForDeptType(userInfo.getDept_id(), 3);
		if (dept != null) {
			KonkaOrderAuditProcess process = new KonkaOrderAuditProcess();
			process.getMap().put("par_add_dept_id", dept.getDept_id());
			process.setIs_del(0);
			List<KonkaOrderAuditProcess> processList = super.getFacade().getKonkaOrderAuditProcessService()
					.getKonkaOrderAuditProcessList(process);

			KonkaOrderAuditProcess ap_public = new KonkaOrderAuditProcess();
			ap_public.setAdd_dept_id(0L);
			ap_public.setIs_del(0);
			List<KonkaOrderAuditProcess> ap_publicauditProcesseList = getFacade().getKonkaOrderAuditProcessService()
					.getKonkaOrderAuditProcessList(ap_public);
			processList.addAll(ap_publicauditProcesseList);
			request.setAttribute("processList", processList);
		}

		Long recordCount = super.getFacade().getKonkaOrderInfoService().getKonkaOrderInfoSearchforR3CodeCount(entity);
		pager.setPageSize(5);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<KonkaOrderInfo> entityList = super.getFacade().getKonkaOrderInfoService()
				.getKonkaOrderInfoResultForR3CodePaginatedList(entity);
		request.setAttribute("entityList", entityList);

		// createMobileSysOperLog(request, "KONKA_ORDER_INFO", 720100l,
		// "720100","查询", "手机端-订单审核-查询");
		// return mapping.findForward("list");
		return new ActionForward("/mobile/admin/KonkaOrderAudit/list.jsp");
	}

	/**
	 * 待办事项里的列表
	 */
	public ActionForward listForDbsx(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String order_date_start = (String) dynaBean.get("order_date_start");
		String order_date_end = (String) dynaBean.get("order_date_end");
		String trade_index_like = (String) dynaBean.get("trade_index_like");// 流程类型
		String user_shop_name_like = (String) dynaBean.get("user_shop_name_like");// 订单审核状态
		String process_id = (String) dynaBean.get("process_id");

		String pay_type = (String) dynaBean.get("pay_type");
		String send_type = (String) dynaBean.get("send_type");
		String pd_name_like = (String) dynaBean.get("pd_name_like");
		String ag_like = (String) dynaBean.get("ag_like");

		Pager pager = (Pager) dynaBean.get("pager");
		 //版本号
		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");

		PeProdUser userInfo = null;

		String user_id = (String) dynaBean.get("user_id");
		String username = (String) dynaBean.get("username");
		String userpass = (String) dynaBean.get("userpass");
		if (StringUtils.isBlank(user_id) && StringUtils.isBlank(userpass)) {
			super.renderHtml(response, "无效的访问参数！");
			return null;
		}

		userInfo = new PeProdUser();

		if (user_id != null) {
			userInfo.setId(Long.valueOf(user_id));
			userInfo.setPass_word(new DESPlus().encrypt(userpass));
			userInfo = getFacade().getPeProdUserService().getPeProdUser(userInfo);
		} else {
			userInfo = checkUserid(username, userpass,android_version,ios_version);
		}

		if (null == userInfo || userInfo.getCust_id() != null) {
			super.renderHtml(response, "用户名和密码不匹配！" + username);
			return null;
		}

		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(userInfo.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);

		boolean role_id_eq_60 = false;
		for (PeRoleUser peRoleUser : peRoleUserList) {
			if (peRoleUser.getRole_id() == 60L) {
				role_id_eq_60 = true;
				break;
			}
		}

		KonkaOrderInfo entity = new KonkaOrderInfo();
		entity.setIs_del(0);
		entity.getMap().put("where_by_process_id", "true");

		// 处理业务员特例
		if (role_id_eq_60) {
			// // 业务员，业务员只能看见其下客户的待审核订单
			entity.getMap().put("querybycust_userid_eq", userInfo.getId()); //
			// 按客户查询
		}
		entity.getMap().put("querybyrole_userid_eq", userInfo.getId()); // 按下一个审核角色查询

		// 数据级别判断开始
		Long dept_id = userInfo.getDept_id(); // 默认为当前用户所在部门
		int max_dlevel = super.getMaxDLevel(userInfo.getId()); // 获取当前用户的最高可视部门级别
		logger.info("Max level : {}", max_dlevel);
		switch (max_dlevel) {
		case 9:
			// 集团及以下部门可见
			dept_id = 0L; // 0表示部门根节点，即“多媒体事业本部”
			break;
		case 8:
			// 分公司及以下部门可见
			KonkaDept dept_fgs = super.getKonkaDeptForFgs(Long.valueOf(dept_id)); // 查询部门分公司
			if (null != dept_fgs && null != dept_fgs.getDept_id()) {
				dept_id = dept_fgs.getDept_id(); // 分公司部门ID
			}
			break;
		case 7:
			// 我所在的部门及以下部门可见
			dept_id = userInfo.getDept_id(); // 默认为当前用户所在部门
			break;
		case 0:
			entity.getMap().put("querybycust_userid_eq", userInfo.getId()); // 按客户查询
			break;
		default:
			// 出错
		}
		// 数据级别判断结束

		entity.getMap().put("par_or_children_dept_id", dept_id);
		entity.getMap().put("session_user_id", userInfo.getId());// 获取当前客户所查看的数据部门
		entity.setIs_submit(1); // 0:表示暂存；1：表示正式提交
		if (StringUtils.isNotBlank(order_date_start)) {
			entity.getMap().put("start_date", order_date_start);
		}
		if (StringUtils.isNotBlank(order_date_end)) {
			entity.getMap().put("end_date", order_date_end);
		}
		if (StringUtils.isNotBlank(trade_index_like)) {
			entity.getMap().put("trade_index_like", trade_index_like);
		}
		if (StringUtils.isNotBlank(process_id)) {
			entity.setProcess_id(Long.valueOf(process_id));
		}
		if (StringUtils.isNotBlank(user_shop_name_like)) {
			entity.getMap().put("user_shop_name_like", user_shop_name_like);
		}
		if (StringUtils.isNotBlank(send_type)) {
			entity.setSend_type(Integer.valueOf(send_type));
		}
		if (StringUtils.isNotBlank(pay_type)) {
			entity.setPay_type(Integer.valueOf(pay_type));
		}
		if (StringUtils.isNotBlank(pd_name_like)) {
			entity.getMap().put("pd_name_like", pd_name_like);
		}
		if (StringUtils.isNotBlank(ag_like)) {
			entity.getMap().put("ag_like", ag_like);
		}

		// 获取流程列表
		KonkaDept dept = super.getSuperiorForDeptType(userInfo.getDept_id(), 3);
		if (dept != null) {
			KonkaOrderAuditProcess process = new KonkaOrderAuditProcess();
			process.getMap().put("par_add_dept_id", dept.getDept_id());
			process.setIs_del(0);
			List<KonkaOrderAuditProcess> processList = super.getFacade().getKonkaOrderAuditProcessService()
					.getKonkaOrderAuditProcessList(process);

			KonkaOrderAuditProcess ap_public = new KonkaOrderAuditProcess();
			ap_public.setAdd_dept_id(0L);
			ap_public.setIs_del(0);
			List<KonkaOrderAuditProcess> ap_publicauditProcesseList = getFacade().getKonkaOrderAuditProcessService()
					.getKonkaOrderAuditProcessList(ap_public);
			processList.addAll(ap_publicauditProcesseList);
			request.setAttribute("processList", processList);
		}

		Long recordCount = super.getFacade().getKonkaOrderInfoService().getKonkaOrderInfoSearchforR3CodeCount(entity);
		// pager.setPageSize(Integer.valueOf(recordCount));
		// pager.init(recordCount, recordCount, 1);
		entity.getRow().setFirst(1);
		entity.getRow().setCount(recordCount.intValue());

		List<KonkaOrderInfo> entityList = super.getFacade().getKonkaOrderInfoService()
				.getKonkaOrderInfoResultForR3CodePaginatedList(entity);
		request.setAttribute("entityList", entityList);

		// createMobileSysOperLog(request, "KONKA_ORDER_INFO", 720100l,
		// "720100","查询", "手机端-订单审核-查询");
		// return mapping.findForward("list");
		// return new ActionForward("/mobile/admin/KonkaOrderAudit/list.jsp");

		// List<HashMap> entityList =
		// getFacade().getKonkaR3StoreService().getKonkaR3StoreAndJbasePartnerForMainPage(
		// entity);
		logger.info(entityList.toString());
		JSONArray jsonArray = JSONArray.fromObject(entityList);
		// int start = jsonArray.toString().indexOf("[");
		// int end = jsonArray.toString().lastIndexOf("}");
		// String jsonStr = jsonArray.toString().substring(start + 1, end + 1);
		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(jsonArray.toString());
		out.flush();
		out.close();
		return null;

	}

	public ActionForward listForDbwj(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String url = (String) dynaBean.get("url");

		PeProdUser userInfo = null;

		String user_id = (String) dynaBean.get("user_id");
		String username = (String) dynaBean.get("username");
		String userpass = (String) dynaBean.get("userpass");
		 //版本号
		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");
		if (StringUtils.isBlank(user_id) && StringUtils.isBlank(userpass)) {
			super.renderHtml(response, "无效的访问参数！");
			return null;
		}

		userInfo = new PeProdUser();

		if (user_id != null) {
			userInfo.setId(Long.valueOf(user_id));
			userInfo.setPass_word(new DESPlus().encrypt(userpass));
			userInfo = getFacade().getPeProdUserService().getPeProdUser(userInfo);
		} else {
			userInfo = checkUserid(username, userpass,android_version,ios_version);
		}

		if (null == userInfo || userInfo.getCust_id() != null) {
			super.renderHtml(response, "用户名和密码不匹配！" + username);
			return null;
		}

		request.setAttribute("user_id", userInfo.getId());
		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(userInfo.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);
		request.setAttribute("peRoleUserList", peRoleUserList);

		// boolean role_id_ge_1000_le_1100 = false;
		boolean role_id_eq_10 = false; // 系统管理员
		boolean role_id_eq_30 = false; // 分公司管理员
		boolean role_id_lt_30 = false; // 总部人员
		boolean role_id_eq_188 = false; // 促销员
		boolean role_id_ne_188 = false; // 非促销员
		String role_ids = "-1";
		for (PeRoleUser peRoleUser : peRoleUserList) {
			role_ids = role_ids + "," + peRoleUser.getRole_id();
			// if (peRoleUser.getRole_id() >= 1000L
			// && peRoleUser.getRole_id() <= 1100L) {
			// role_id_ge_1000_le_1100 = true;
			// }
			if (peRoleUser.getRole_id() == 188L) {
				role_id_eq_188 = true;
			}
			if (peRoleUser.getRole_id() == 10L) {
				role_id_eq_10 = true;
			}
			if (peRoleUser.getRole_id() == 30L) {
				role_id_eq_30 = true;
			}
			if (peRoleUser.getRole_id() != 188L) {
				role_id_ne_188 = true;
			}
			if (peRoleUser.getRole_id() < 30 || (peRoleUser.getRole_id() >= 200 & peRoleUser.getRole_id() < 300)) {
				role_id_lt_30 = true;
			}
		}

		if (role_id_eq_188) {
			// super.renderHtml(response, "Bad request. No permissions.");
			return null;
		}

		if (StringUtils.isNotBlank(url)) {
			url = new DESPlus().decrypt(url);
			super.renderJavaScript(response, "window.onload=function(){location.href='" + url + "'}");
			return null;
		}
		// 默认当前月份任务显示
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		request.setAttribute("this_year", String.valueOf(calendar.get(Calendar.YEAR)));
		request.setAttribute("this_month", String.valueOf(calendar.get(Calendar.MONTH) + 1));

		if (role_id_eq_10) {
			// 系统管理员
			KonkaR3Shop s = new KonkaR3Shop();
			List<KonkaR3Shop> staticByFGSList = super.getFacade().getKonkaR3ShopService()
					.getKonkaR3ShopStaticsGroupByFGS(s);
			request.setAttribute("staticByFGSList", staticByFGSList);
		}
		if (!role_id_eq_10 && role_id_eq_30) {
			// 仅是分公司管理员
			boolean data_ok = false;
			KonkaDept dept = null;

			if (null != userInfo.getDept_id()) {
				dept = super.getSuperiorForDeptType(userInfo.getDept_id(), 3);
				if (null != dept) {
					data_ok = true;
				}
			}

			if (data_ok) {
				KonkaR3Shop s = new KonkaR3Shop();
				s.getMap().put("dept_id_eq", dept.getDept_id());
				List<KonkaR3Shop> staticByFGSList = super.getFacade().getKonkaR3ShopService()
						.getKonkaR3ShopStaticsGroupByFGS(s);
				request.setAttribute("staticByFGSList", staticByFGSList);
			}
		}

		if (!role_id_eq_188 || role_id_ne_188) {
			int i = 0;
			List<KonkaoaEventInfo> list = new ArrayList<KonkaoaEventInfo>();
			// 待审文件
			// List<Files> cList = new ArrayList<Files>();
			// Files entity = new KonkaoaFiles();
			// entity.getMap().put("lt_file_status", 2);
			// entity.setIs_del(0);
			// entity.setCur_audit_user_id(ui.getId());
			// cList =
			// super.getFacade().getKonkaoaFilesService().getFilesList(entity);

			List<Long> agentAuditFileIds = null;

			/**
			 * @version Build 2010-12-30
			 * @desc 文件代审批功能实现
			 */
			// 先查当前用户帮哪些用户代审文件，将被代理审批的用户查出来
			KonkaoaFilesAuditAgentUser faau = new KonkaoaFilesAuditAgentUser();
			faau.setIs_del(0);
			faau.setAgent_user_id(userInfo.getId());
			faau.getMap().put("expired_date", "true");
			List<KonkaoaFilesAuditAgentUser> filesAuditAgentUserList = getFacade()
					.getKonkaoaFilesAuditAgentUserService().getKonkaoaFilesAuditAgentUserList(faau);
			if (null != filesAuditAgentUserList && filesAuditAgentUserList.size() > 0) {
				Long[] agent_user_ids = new Long[filesAuditAgentUserList.size()];
				for (i = 0; i < filesAuditAgentUserList.size(); i++) {
					agent_user_ids[i] = filesAuditAgentUserList.get(i).getUser_id();
				}

				// 查出被代理用户的需要审批的文件列表
				KonkaoaFiles files = new KonkaoaFiles();
				files.setIs_del(0);
				files.getMap().put("lt_file_status", 2);
				files.getMap().put("cur_audit_user_ids", agent_user_ids);
				List<KonkaoaFiles> filesList = super.getFacade().getKonkaoaFilesService().getKonkaoaFilesList(files);

				// 将查到的文件列表的文件属性查出，与审批权限表进行比对
				if (null != filesList && filesList.size() > 0) {
					agentAuditFileIds = new ArrayList<Long>();
					for (KonkaoaFiles _files : filesList) {
						KonkaoaFilesAuditAgentUser _faau = new KonkaoaFilesAuditAgentUser();
						_faau.setUser_id(_files.getCur_audit_user_id());
						_faau.setAgent_user_id(userInfo.getId());
						_faau.getMap().put("link_id", _files.getId());
						Long count = super.getFacade().getKonkaoaFilesAuditAgentUserService()
								.getKonkaoaAgentFilesAuditPopedomCount(_faau);
						if (count == 0) {// 有审批权限的
							// _files.getMap().put("agent_user_name",
							// ui.getUser_name());
							// entityList.add(_files);
							// ++recordCount;
							agentAuditFileIds.add(_files.getId());
						}
					}
				}
			}

			KonkaoaFiles entity = new KonkaoaFiles();
			entity.getMap().put("lt_file_status", 2);
			entity.getMap().put("agentAuditFileIds", agentAuditFileIds);

			entity.setIs_del(0);
			entity.setCur_audit_user_id(userInfo.getId());

			entity.getRow().setFirst(0);
			entity.getRow().setCount(5);

			List<KonkaoaFiles> cList = super.getFacade().getKonkaoaFilesService()
					.getKonkaoaFilesPaginatedListForAudit(entity);
			for (KonkaoaFiles t : cList) {
				i++;
				KonkaoaEventInfo _t = new KonkaoaEventInfo();
				_t.setId(t.getId());
				// _t.setMod_id(20030);
				_t.setEnterDate(t.getSubmit_datetime());
				_t.setEventiltle(t.getFile_title());
				_t.setSequence(i);
				_t.setFromPerson(t.getSubmit_user());
				if (t.getFile_type() == 0) {
					_t.setEventType("文件提交");
				} else if (t.getFile_type() == 1) {
					_t.setEventType("费用申请");
				}

				if ("1".equals(t.getMap().get("agent_audit").toString())
						|| "0".equals(t.getMap().get("agent_audit").toString())) {
					if (t.getIs_node() == 1) {// 成都分公司
						_t.setEventDo("<a style=\"cursor:pointer;color:blue;\" onclick=\"javascript:window.location.href='../chengduoa/SelfEventCenter.do?method=edit&mod_id=951000&is_agent="
								+ ("1".equals(t.getMap().get("agent_audit")) ? "1" : "0")
								+ "&agent_user_id="
								+ t.getCur_audit_user_id()
								+ "&file_type="
								+ t.getFile_type()
								+ "&id="
								+ t.getId()
								+ "'\">审批</a>");
					} else {// 非成都分公司
						_t.setEventDo("<a style=\"cursor:pointer;color:blue;\" onclick=\"javascript:window.location.href='../oa/SelfEventCenter.do?method=edit&mod_id=991000&is_agent="
								+ ("1".equals(t.getMap().get("agent_audit")) ? "1" : "0")
								+ "&agent_user_id="
								+ t.getCur_audit_user_id()
								+ "&file_type="
								+ t.getFile_type()
								+ "&id="
								+ t.getId()
								+ "'\">审批</a>");
					}

				} else {
					if (t.getIs_node() == 1) {// 成都分公司
						_t.setEventDo("<a style=\"cursor:pointer;color:blue;\" onclick=\"javascript:window.location.href='../chengduoa/SelfEventCenter.do?method=edit&mod_id=951000&is_agent="
								+ ("1".equals(t.getMap().get("agent_audit")) ? "1" : "0")
								+ "&agent_user_id="
								+ t.getCur_audit_user_id()
								+ "&file_type="
								+ t.getFile_type()
								+ "&id="
								+ t.getId()
								+ "'\">会签</a>");
					} else {// 非成都分公司
						_t.setEventDo("<a style=\"cursor:pointer;color:blue;\" onclick=\"javascript:window.location.href='../oa/SelfEventCenter.do?method=edit&mod_id=991000&is_agent="
								+ ("1".equals(t.getMap().get("agent_audit")) ? "1" : "0")
								+ "&agent_user_id="
								+ t.getCur_audit_user_id()
								+ "&file_type="
								+ t.getFile_type()
								+ "&id="
								+ t.getId()
								+ "'\">会签</a>");
					}
				}
				String title = _t.getEventDo();
				title = title.replaceAll("会签", _t.getEventiltle()).replaceAll("审批", _t.getEventiltle());
				title = title.replace("<a", "<span").replace("</a>", "</span>");
				_t.getMap().put("title", title);
				list.add(_t);
			}

			KonkaoaFiles file5 = new KonkaoaFiles();
			file5.setFile_status(0);
			file5.setIs_del(0);
			file5.setSubmit_user_id(userInfo.getId());
			List<KonkaoaFiles> file5List = super.getFacade().getKonkaoaFilesService().getKonkaoaFilesList(file5);

			for (KonkaoaFiles t : file5List) {
				i++;
				KonkaoaEventInfo _t = new KonkaoaEventInfo();
				_t.setId(t.getId());
				// _t.setMod_id(20030);
				_t.setEnterDate(t.getSubmit_datetime());
				_t.setEventiltle(t.getFile_title());
				_t.setSequence(i);
				_t.setFromPerson(t.getSubmit_user());
				if (t.getFile_type() == 0) {
					_t.setEventType("文件提交");
					if (t.getIs_node() == 1) {// 成都分公司
						_t.setEventDo("<a style=\"cursor:pointer;color:blue;\" onclick=\"javascript:window.location.href='../chengduoa/FilesSubmit.do?method=edit&id="
								+ t.getId() + "'\">审批</a>");
					} else {// 非成都分公司
						_t.setEventDo("<a style=\"cursor:pointer;color:blue;\" onclick=\"javascript:window.location.href='../oa/FilesSubmit.do?method=edit&id="
								+ t.getId() + "'\">审批</a>");
					}
				} else if (t.getFile_type() == 1) {
					_t.setEventType("费用申请");
					if (t.getIs_node() == 1) {// 成都分公司
						_t.setEventDo("<a style=\"cursor:pointer;color:blue;\" onclick=\"javascript:window.location.href='../chengduoa/ExpenseClaims.do?method=edit&file_id="
								+ t.getId() + "'\">审批</a>");
					} else {// 非成都分公司
						_t.setEventDo("<a style=\"cursor:pointer;color:blue;\" onclick=\"javascript:window.location.href='../oa/ExpenseClaims.do?method=edit&file_id="
								+ t.getId() + "'\">审批</a>");
					}
				}
				String title = _t.getEventDo();
				title = title.replaceAll("审批", _t.getEventiltle());
				title = title.replace("<a", "<span").replace("</a>", "</span>");
				_t.getMap().put("title", title);
				list.add(_t);
			}

			Long dept_id = 0L;
			if (!role_id_lt_30) {
				KonkaDept dept_fgs = getKonkaDeptForFgs(userInfo.getDept_id());
				if (null != dept_fgs) {
					dept_id = dept_fgs.getDept_id();
				}
			}

			// 资质审核
			KonkaXxZmdAuditUserInfo kInfo = new KonkaXxZmdAuditUserInfo();
			kInfo.getMap().put("db_role_ids", role_ids);
			kInfo.setDept_id(dept_id);
			List<KonkaXxZmdAuditUserInfo> kInfoList = super.getFacade().getKonkaXxZmdAuditUserInfoService()
					.getKonkaXxZmdAuditUserInfoForRoleIdList(kInfo);
			if (kInfoList.size() > 0) {
				for (KonkaXxZmdAuditUserInfo temp1 : kInfoList) {
					KonkaoaEventInfo _tr = new KonkaoaEventInfo();
					if (null != temp1.getMap().get("role_id")) {
						// _tr.setEventiltle(temp1.getUser_name()+ "客户资质申请");
						_tr.getMap().put("title", temp1.getUser_name() + "客户资质申请");
						_tr.setEnterDate(temp1.getAdd_date());
						_tr.setEventType("专卖店资质申请");
						_tr.setFromPerson(temp1.getAdd_user_name());
						_tr.setEventDo("<a style=\"cursor:pointer;color:blue;\" onclick=\"javascript:window.location.href='../zmd/KonkaXxZmdAuditUserInfoAudit.do?method=audit&zmd_user_id="
								+ temp1.getZmd_user_id()
								+ "&role_id="
								+ temp1.getMap().get("role_id")
								+ "&mod_id=810200" + "'\">审核</a>");
						list.add(_tr);
					}
				}
			}
			// 专卖店备案审核
			KonkaXxZmd zmd = new KonkaXxZmd();
			zmd.getMap().put("db_role_ids", role_ids);
			zmd.setDept_id(dept_id);
			List<KonkaXxZmd> zmdList = super.getFacade().getKonkaXxZmdService().getKonkaXxZmdForRoleIdList(zmd);
			if (zmdList.size() > 0) {
				for (KonkaXxZmd temp1 : zmdList) {
					KonkaoaEventInfo _tr = new KonkaoaEventInfo();
					if (null != temp1.getMap().get("role_id")) {
						// _tr.setEventiltle("专卖店"+temp1.getZmd_sn()+ "备案申请");
						_tr.getMap().put("title", "专卖店" + temp1.getZmd_sn() + "备案申请");
						_tr.setEventType("专卖店资质申请");
						_tr.setFromPerson(temp1.getWrite_man());
						_tr.setEnterDate(temp1.getApply_date());
						_tr.setEventDo("<a style=\"cursor:pointer;color:blue;\" onclick=\"javascript:window.location.href='../zmd/KonkaXxZmdVerification.do?method=edit&zmd_id="
								+ temp1.getZmd_id()
								+ "&role_id="
								+ temp1.getMap().get("role_id")
								+ "&mod_id=810200"
								+ "'\">审核</a>");
						list.add(_tr);
					}

				}
			}
			logger.info(list.toString());
			JSONArray jsonArray = JSONArray.fromObject(list);
			// int start = jsonArray.toString().indexOf("[");
			// int end = jsonArray.toString().lastIndexOf("}");
			// String jsonStr = jsonArray.toString().substring(start + 1, end +
			// 1);
			response.setContentType("application/json;charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter out = response.getWriter();
			out.print(jsonArray.toString());
			out.flush();
			out.close();
		}

		return null;
	}

	/**
	 * 查询待审核订单
	 */
	public ActionForward listForCb(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		// 默认显示当前1周的时间区间
		Date today = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(today);

		calendar.add(Calendar.DATE, -60);
		String day_first = df.format(calendar.getTime());
		String day_last = df.format(today);
		dynaBean.set("order_date_start", day_first);// 默认当前日期的前7天
		dynaBean.set("order_date_end", day_last);
		String order_date_start = (String) dynaBean.get("order_date_start");
		String order_date_end = (String) dynaBean.get("order_date_end");
		String trade_index_like = (String) dynaBean.get("trade_index_like");// 流程类型
		String user_shop_name_like = (String) dynaBean.get("user_shop_name_like");// 订单审核状态
		String process_id = (String) dynaBean.get("process_id");

		String pay_type = (String) dynaBean.get("pay_type");
		String send_type = (String) dynaBean.get("send_type");
		String pd_name_like = (String) dynaBean.get("pd_name_like");
		String ag_like = (String) dynaBean.get("ag_like");
		 //版本号
		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");
		Pager pager = (Pager) dynaBean.get("pager");

		PeProdUser userInfo = null;

		PeProdUser suser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		if (null != suser) {
			logger.info("session user name: {}", suser.getUser_name());
			userInfo = suser;
		} else if (check_user_allowd) {
			String user_id = (String) dynaBean.get("user_id");
			String username = (String) dynaBean.get("username");
			String userpass = (String) dynaBean.get("userpass");
			
			if (StringUtils.isBlank(user_id) && StringUtils.isBlank(userpass)) {
				super.renderHtml(response, "无效的访问参数！");
				return null;
			}

			userInfo = new PeProdUser();

			if (user_id != null) {
				userInfo.setId(Long.valueOf(user_id));
				userInfo.setPass_word(new DESPlus().encrypt(userpass));
				userInfo = getFacade().getPeProdUserService().getPeProdUser(userInfo);
			} else {
				userInfo = checkUserid(username, userpass,android_version,ios_version);
			}

			if (null == userInfo || userInfo.getCust_id() != null) {
				super.renderHtml(response, "用户名和密码不匹配！" + username);
				return null;
			}
		} else if (null == suser) {
			super.renderHtml(response, "您未登录或登录超时了！");
			return null;
		}

		// PeRoleUser _peRoleUser = new PeRoleUser();
		// _peRoleUser.setUser_id(userInfo.getId());
		// List<PeRoleUser> peRoleUserList =
		// this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);
		//
		// boolean role_id_eq_60 = false;
		// for (PeRoleUser peRoleUser : peRoleUserList) {
		// if (peRoleUser.getRole_id() == 60L) {
		// role_id_eq_60 = true;
		// break;
		// }
		// }

		KonkaOrderInfo entity = new KonkaOrderInfo();
		entity.setIs_del(0);
		entity.getMap().put("where_by_process_id", "true");

		// 处理业务员特例
		// if (role_id_eq_60) {
		// // 业务员，业务员只能看见其下客户的待审核订单
		// entity.getMap().put("querybycust_userid_eq", userInfo.getId()); //
		// 按客户查询
		// }
		entity.getMap().put("querybyrole_userid_eq", userInfo.getId()); // 按下一个审核角色查询

		// 数据级别判断开始
		Long dept_id = userInfo.getDept_id(); // 默认为当前用户所在部门
		int max_dlevel = super.getMaxDLevel(userInfo.getId()); // 获取当前用户的最高可视部门级别
		logger.info("Max level : {}", max_dlevel);
		switch (max_dlevel) {
		case 9:
			// 集团及以下部门可见
			dept_id = 0L; // 0表示部门根节点，即“多媒体事业本部”
			break;
		case 8:
			// 分公司及以下部门可见
			KonkaDept dept_fgs = super.getKonkaDeptForFgs(Long.valueOf(dept_id)); // 查询部门分公司
			if (null != dept_fgs && null != dept_fgs.getDept_id()) {
				dept_id = dept_fgs.getDept_id(); // 分公司部门ID
			}
			break;
		case 7:
			// 我所在的部门及以下部门可见
			dept_id = userInfo.getDept_id(); // 默认为当前用户所在部门
			break;
		case 0:
			entity.getMap().put("querybycust_userid_eq", userInfo.getId()); // 按客户查询
			break;
		default:
			// 出错
		}
		// 数据级别判断结束

		entity.getMap().put("par_or_children_dept_id", dept_id);
		entity.setIs_submit(1); // 0:表示暂存；1：表示正式提交
		// if (StringUtils.isNotBlank(order_date_start)) {
		// entity.getMap().put("start_date", order_date_start);
		// }
		// if (StringUtils.isNotBlank(order_date_end)) {
		// entity.getMap().put("end_date", order_date_end);
		// }
		if (StringUtils.isNotBlank(trade_index_like)) {
			entity.getMap().put("trade_index_like", trade_index_like);
		}
		if (StringUtils.isNotBlank(process_id)) {
			entity.setProcess_id(Long.valueOf(process_id));
		}
		if (StringUtils.isNotBlank(user_shop_name_like)) {
			entity.getMap().put("user_shop_name_like", user_shop_name_like);
		}
		if (StringUtils.isNotBlank(send_type)) {
			entity.setSend_type(Integer.valueOf(send_type));
		}
		if (StringUtils.isNotBlank(pay_type)) {
			entity.setPay_type(Integer.valueOf(pay_type));
		}
		if (StringUtils.isNotBlank(pd_name_like)) {
			entity.getMap().put("pd_name_like", pd_name_like);
		}
		if (StringUtils.isNotBlank(ag_like)) {
			entity.getMap().put("ag_like", ag_like);
		}

		// 获取流程列表
		KonkaDept dept = super.getSuperiorForDeptType(userInfo.getDept_id(), 3);
		if (dept != null) {
			KonkaOrderAuditProcess process = new KonkaOrderAuditProcess();
			process.getMap().put("par_add_dept_id", dept.getDept_id());
			process.setIs_del(0);
			List<KonkaOrderAuditProcess> processList = super.getFacade().getKonkaOrderAuditProcessService()
					.getKonkaOrderAuditProcessList(process);

			KonkaOrderAuditProcess ap_public = new KonkaOrderAuditProcess();
			ap_public.setAdd_dept_id(0L);
			ap_public.setIs_del(0);
			List<KonkaOrderAuditProcess> ap_publicauditProcesseList = getFacade().getKonkaOrderAuditProcessService()
					.getKonkaOrderAuditProcessList(ap_public);
			processList.addAll(ap_publicauditProcesseList);
			request.setAttribute("processList", processList);
		}

		Long recordCount = super.getFacade().getKonkaOrderInfoService()
				.getKonkaOrderInfoSearchforR3CodeandforcbCount(entity);
		pager.setPageSize(10);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<KonkaOrderInfo> entityList = super.getFacade().getKonkaOrderInfoService()
				.getKonkaOrderInfoResultForR3CodeforCbPaginatedList(entity);

		// 得到分公司
		for (KonkaOrderInfo konkaOrderInfo : entityList) {
			if (null != konkaOrderInfo.getAdd_dept_id()) {
				KonkaDept konkaDept = new KonkaDept();
				konkaDept.setDept_id(konkaOrderInfo.getAdd_dept_id());
				konkaDept.getMap().put("dept_type_eq", 3);
				konkaDept = super.getFacade().getKonkaDeptService().getKonkaDeptSuperiorByDeptId(konkaDept);
				if (konkaDept != null) {
					if (null != konkaDept.getDept_name() && !"".equals(konkaDept.getDept_name())) {
						konkaOrderInfo.getMap().put("fgsName", konkaDept.getDept_name());
					}
				}
			}
		}

		request.setAttribute("entityList", entityList);

		// createMobileSysOperLog(request, "KONKA_ORDER_INFO", 720100l,
		// "720100","查询", "手机端-订单审核-查询");
		// return mapping.findForward("list");
		return new ActionForward("/mobile/admin/KonkaOrderAudit/listforcb.jsp");
	}

	/**
	 * 查询待审核订单数量
	 */
	public ActionForward listForCbCount(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		// 默认显示当前1周的时间区间
		Date today = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(today);

		calendar.add(Calendar.DATE, -30);
		String day_first = df.format(calendar.getTime());
		String day_last = df.format(today);
		// dynaBean.set("order_date_start", day_first);// 默认当前日期的前7天
		// dynaBean.set("order_date_end", day_last);
		// String order_date_start = (String) dynaBean.get("order_date_start");
		// String order_date_end = (String) dynaBean.get("order_date_end");

		Pager pager = (Pager) dynaBean.get("pager");

		PeProdUser userInfo = null;

		// 用户名
		String inuser_id = (String) dynaBean.get("user_id");
		// 密码
		String inuserpass = (String) dynaBean.get("userpass");
		// 类型
		String type = (String) dynaBean.get("type");
		 //版本号
		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");

		// 时间
		String start_date = (String) dynaBean.get("start_date");

		// 用户名或者密码不存在， 资讯信息类别为公开
		PeProdUser suser = checkUserid(inuser_id, inuserpass,android_version,ios_version);

		if (null != suser) {
			logger.info("session user name: {}", suser.getUser_name());
			userInfo = suser;
		} else {
			super.renderHtml(response, "用户名密码不正确或登录超时");
			return null;
		}

		KonkaOrderInfo entity = new KonkaOrderInfo();
		entity.setIs_del(0);
		entity.getMap().put("where_by_process_id", "true");

		entity.getMap().put("querybyrole_userid_eq", userInfo.getId()); // 按下一个审核角色查询

		// 数据级别判断开始
		Long dept_id = userInfo.getDept_id(); // 默认为当前用户所在部门
		int max_dlevel = super.getMaxDLevel(userInfo.getId()); // 获取当前用户的最高可视部门级别
		logger.info("Max level : {}", max_dlevel);
		switch (max_dlevel) {
		case 9:
			// 集团及以下部门可见
			dept_id = 0L; // 0表示部门根节点，即“多媒体事业本部”
			break;
		case 8:
			// 分公司及以下部门可见
			KonkaDept dept_fgs = super.getKonkaDeptForFgs(Long.valueOf(dept_id)); // 查询部门分公司
			if (null != dept_fgs && null != dept_fgs.getDept_id()) {
				dept_id = dept_fgs.getDept_id(); // 分公司部门ID
			}
			break;
		case 7:
			// 我所在的部门及以下部门可见
			dept_id = userInfo.getDept_id(); // 默认为当前用户所在部门
			break;
		case 0:
			entity.getMap().put("querybycust_userid_eq", userInfo.getId()); // 按客户查询
			break;
		default:
			// 出错
		}
		// 数据级别判断结束

		entity.getMap().put("par_or_children_dept_id", dept_id);
		entity.setIs_submit(1); // 0:表示暂存；1：表示正式提交
		// 获取流程列表
		KonkaDept dept = super.getSuperiorForDeptType(userInfo.getDept_id(), 3);
		if (dept != null) {
			KonkaOrderAuditProcess process = new KonkaOrderAuditProcess();
			process.getMap().put("par_add_dept_id", dept.getDept_id());
			process.setIs_del(0);
			List<KonkaOrderAuditProcess> processList = super.getFacade().getKonkaOrderAuditProcessService()
					.getKonkaOrderAuditProcessList(process);

			KonkaOrderAuditProcess ap_public = new KonkaOrderAuditProcess();
			ap_public.setAdd_dept_id(0L);
			ap_public.setIs_del(0);
			List<KonkaOrderAuditProcess> ap_publicauditProcesseList = getFacade().getKonkaOrderAuditProcessService()
					.getKonkaOrderAuditProcessList(ap_public);
			processList.addAll(ap_publicauditProcesseList);
			request.setAttribute("processList", processList);
		}
		List<KonkaOrderInfo> entityList = super.getFacade().getKonkaOrderInfoService()
				.getKonkaOrderInfoResultForR3CodeforCbList(entity);

		// 得到分公司
		for (KonkaOrderInfo konkaOrderInfo : entityList) {
			if (null != konkaOrderInfo.getAdd_dept_id()) {
				KonkaDept konkaDept = new KonkaDept();
				if (null != konkaOrderInfo.getAdd_dept_id()) {
					konkaDept.setDept_id(konkaOrderInfo.getAdd_dept_id());
					konkaDept.getMap().put("dept_type_eq", 3);
					konkaDept = super.getFacade().getKonkaDeptService().getKonkaDeptSuperiorByDeptId(konkaDept);
					if (konkaDept != null) {
						if (null != konkaDept.getDept_name() && !"".equals(konkaDept.getDept_name())) {
							konkaOrderInfo.getMap().put("fgsName", konkaDept.getDept_name());
						}
					}
				}
			}
		}
		request.setAttribute("entityList", entityList);
		super.renderJson(response, JSON.toJSONString(entityList));
		return null;
	}

	@Deprecated
	public ActionForward listaudit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String order_date_start = (String) dynaBean.get("order_date_start");
		String order_date_end = (String) dynaBean.get("order_date_end");
		String trade_index_like = (String) dynaBean.get("trade_index_like");// 流程类型
		String user_shop_name_like = (String) dynaBean.get("user_shop_name_like");// 订单审核状态
		String process_id = (String) dynaBean.get("process_id");

		String pay_type = (String) dynaBean.get("pay_type");
		String send_type = (String) dynaBean.get("send_type");
		String pd_name_like = (String) dynaBean.get("pd_name_like");
		String ag_like = (String) dynaBean.get("ag_like");
		 //版本号
		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");

		PeProdUser userInfo = null;

		PeProdUser suser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		if (null != suser) {
			logger.info("session user name: {}", suser.getUser_name());
			userInfo = suser;
		} else if (check_user_allowd) {
			String user_id = (String) dynaBean.get("user_id");
			String username = (String) dynaBean.get("username");
			String userpass = (String) dynaBean.get("userpass");
			if (StringUtils.isBlank(user_id) && StringUtils.isBlank(userpass)) {
				super.renderHtml(response, "无效的访问参数！");
				return null;
			}

			userInfo = new PeProdUser();

			if (user_id != null) {
				userInfo.setId(Long.valueOf(user_id));
				userInfo.setPass_word(new DESPlus().encrypt(userpass));
				userInfo = getFacade().getPeProdUserService().getPeProdUser(userInfo);
			} else {
				userInfo = checkUserid(username, userpass,android_version,ios_version);
			}
		}

		if (null == userInfo) {
			super.renderHtml(response, "用户名和密码不匹配，身份认证失败！");
			return null;
		}

		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(userInfo.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);

		boolean role_id_eq_60 = false;
		for (PeRoleUser peRoleUser : peRoleUserList) {
			if (peRoleUser.getRole_id() == 60L) {
				role_id_eq_60 = true;
				break;
			}
		}

		KonkaOrderInfo entity = new KonkaOrderInfo();
		entity.setIs_del(0);
		entity.getMap().put("where_by_process_id", "true");

		// 处理业务员特例
		if (role_id_eq_60) {
			// 业务员，业务员只能看见其下客户的待审核订单
			entity.getMap().put("querybycust_userid_eq", userInfo.getId()); // 按客户查询
		}
		entity.getMap().put("querybyrole_userid_eq", userInfo.getId()); // 按下一个审核角色查询

		entity.getMap().put("par_or_children_dept_id", userInfo.getDept_id());
		entity.setIs_submit(1); // 0:表示暂存；1：表示正式提交
		if (StringUtils.isNotBlank(order_date_start)) {
			entity.getMap().put("start_date", order_date_start);
		}
		if (StringUtils.isNotBlank(order_date_end)) {
			entity.getMap().put("end_date", order_date_end);
		}
		if (StringUtils.isNotBlank(trade_index_like)) {
			entity.getMap().put("trade_index_like", trade_index_like);
		}
		if (StringUtils.isNotBlank(process_id)) {
			entity.setProcess_id(Long.valueOf(process_id));
		}
		if (StringUtils.isNotBlank(user_shop_name_like)) {
			entity.getMap().put("user_shop_name_like", user_shop_name_like);
		}
		if (StringUtils.isNotBlank(send_type)) {
			entity.setSend_type(Integer.valueOf(send_type));
		}
		if (StringUtils.isNotBlank(pay_type)) {
			entity.setPay_type(Integer.valueOf(pay_type));
		}
		if (StringUtils.isNotBlank(pd_name_like)) {
			entity.getMap().put("pd_name_like", pd_name_like);
		}
		if (StringUtils.isNotBlank(ag_like)) {
			entity.getMap().put("ag_like", ag_like);
		}

		Long recordCount = super.getFacade().getKonkaOrderInfoService().getKonkaOrderInfoSearchforR3CodeCount(entity);

		String page = request.getParameter("page");
		String forward = (String) dynaBean.get("forward");
		int currentPage = 1;
		int pageSize = 5;
		if (!StringUtils.isEmpty(page)) {
			currentPage = (Integer.parseInt(page)) + (Integer.parseInt(forward));
		}
		entity.getRow().setFirst((currentPage - 1) * pageSize);
		entity.getRow().setCount(pageSize);

		if (recordCount > 0) {
			dynaBean.set("currentPage", currentPage);
			List<KonkaOrderInfo> entityList = super.getFacade().getKonkaOrderInfoService()
					.getKonkaOrderInfoResultForR3CodePaginatedList(entity);
			request.setAttribute("entityList", entityList);
		}

		// createMobileSysOperLog(request, "KONKA_ORDER_INFO", 720100l,
		// "720100","查询", "手机端-订单审核-查询");
		// return mapping.findForward("list");
		return new ActionForward("/mobile/admin/KonkaOrderAudit/listaudit.jsp");
	}

	/**
	 * 查询已审核订单
	 */
	public ActionForward listorder(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;

		String kh_confirm_state = (String) dynaBean.get("kh_confirm_state");
		String order_date_start = (String) dynaBean.get("order_date_start");
		String order_date_end = (String) dynaBean.get("order_date_end");
		String trade_index_like = (String) dynaBean.get("trade_index_like");// 流程类型
		String user_shop_name_like = (String) dynaBean.get("user_shop_name_like");// 订单审核状态
		String process_id = (String) dynaBean.get("process_id");
		String dept_id = (String) dynaBean.get("dept_id");
		String l4_dept_id = (String) dynaBean.get("l4_dept_id");
		String l5_dept_id = (String) dynaBean.get("l5_dept_id");
		String pay_type = (String) dynaBean.get("pay_type");
		String send_type = (String) dynaBean.get("send_type");
		String audit_state = (String) dynaBean.get("audit_state");
		String pd_name_like = (String) dynaBean.get("pd_name_like");
		String is_delivered = (String) dynaBean.get("is_delivered");
		String ag_like = (String) dynaBean.get("ag_like");// 订单表里的ag 就是客户的R3编码
		Pager pager = (Pager) dynaBean.get("pager");
		 //版本号
		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");
		PeProdUser userInfo = null;

		PeProdUser suser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		if (null != suser) {
			logger.info("session user name: {}", suser.getUser_name());
			userInfo = suser;
		} else if (check_user_allowd) {
			String user_id = (String) dynaBean.get("user_id");
			String username = (String) dynaBean.get("username");
			String userpass = (String) dynaBean.get("userpass");
			if (StringUtils.isBlank(user_id) && StringUtils.isBlank(userpass)) {
				super.renderHtml(response, "无效的访问参数！");
				return null;
			}

			userInfo = new PeProdUser();

			if (user_id != null) {
				userInfo.setId(Long.valueOf(user_id));
				userInfo.setPass_word(new DESPlus().encrypt(userpass));
				userInfo = getFacade().getPeProdUserService().getPeProdUser(userInfo);
			} else {
				userInfo = checkUserid(username, userpass,android_version,ios_version);
			}
		}

		if (null == userInfo) {
			super.renderHtml(response, "用户名和密码不匹配，用户身份认证失败！");
			return null;
		}

		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(userInfo.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);

		dynaBean.set("user_id", String.valueOf(userInfo.getId()));// 用户

		boolean role_id_eq_10 = false; // 是否为系统管理员
		boolean role_id_eq_30 = false; // 是否为分公司管理员
		boolean role_id_eq_34 = false; // 是否为分公司总经理
		boolean role_id_eq_38 = false; // 是否为分公司产品会计
		boolean role_id_eq_39 = false; // 是否为分公司财务经理
		List<Long> role_ids = new ArrayList<Long>(); // 当前登录用户的角色列表
		for (PeRoleUser peRoleUser : peRoleUserList) {
			role_ids.add(peRoleUser.getRole_id());

			if (peRoleUser.getRole_id() == 10L) {
				role_id_eq_10 = true;
			}
			if (peRoleUser.getRole_id() == 30L) {
				role_id_eq_30 = true;
			}
			if (peRoleUser.getRole_id() == 34L) {
				role_id_eq_34 = true;
			}
			if (peRoleUser.getRole_id() == 38L) {
				role_id_eq_38 = true;
			}
			if (peRoleUser.getRole_id() == 39L) {
				role_id_eq_39 = true;
			}
		}

		request.setAttribute("role_id_eq_38", role_id_eq_38);

		// 默认显示当前1周的时间区间
		Date today = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(today);

		// calendar.add(Calendar.DATE, -30);
		calendar.add(Calendar.MONTH, -1);
		String day_first = df.format(calendar.getTime());
		String day_last = df.format(today);

		if (StringUtils.isNotBlank(order_date_start) && StringUtils.isNotBlank(order_date_end)) {
			Date start_date = DateUtils.parseDate(order_date_start, new String[] { "yyyy-MM-dd" });
			Date end_date = DateUtils.parseDate(order_date_end, new String[] { "yyyy-MM-dd" });
			// 判断下单日期间隔是否小于90天
			if ((end_date.getTime() - start_date.getTime()) > 90 * 24 * 60 * 60 * 1000L) { // 超过90天
				String msg = super.getMessage(request, "order.date.over.90.day");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			}
		} else {
			dynaBean.set("order_date_start", day_first);// 默认当前日期的前7天
			dynaBean.set("order_date_end", day_last);
		}

		KonkaOrderInfo entity = new KonkaOrderInfo();
		entity.setIs_submit(1); // 0:表示暂存；1：表示正式提交
		entity.setIs_del(0);// 0:表示未删除；1：表示删除

		if (StringUtils.isNotBlank(order_date_start)) {
			entity.getMap().put("start_date", order_date_start);
		} else {
			entity.getMap().put("start_date", day_first + " 00:00:00");
		}
		if (StringUtils.isNotBlank(order_date_end)) {
			entity.getMap().put("end_date", order_date_end);
		} else {
			entity.getMap().put("end_date", day_last + " 23:59:59");
		}
		if (StringUtils.isNotBlank(trade_index_like)) {
			entity.getMap().put("trade_index_like", trade_index_like);
		}
		if (StringUtils.isNotBlank(process_id)) {
			entity.setProcess_id(Long.valueOf(process_id));
		}
		if (StringUtils.isNotBlank(user_shop_name_like)) {
			entity.getMap().put("user_shop_name_like", user_shop_name_like);
		}
		if (StringUtils.isNotBlank(pay_type)) {
			entity.setPay_type(Integer.valueOf(pay_type));
		}
		if (StringUtils.isNotBlank(send_type)) {
			entity.setSend_type(Integer.valueOf(send_type));
		}
		if (StringUtils.isNotBlank(pd_name_like)) {
			entity.getMap().put("pd_name_like", pd_name_like);
		}
		if (StringUtils.isNotBlank(is_delivered)) {
			entity.setIs_delivered(Integer.valueOf(is_delivered));
		}
		if (StringUtils.isNotBlank(ag_like)) {
			entity.getMap().put("ag_like", ag_like);
		}
		if (StringUtils.isNotBlank(l5_dept_id)) {
			entity.getMap().put("par_dept_id", l5_dept_id);
		} else if (StringUtils.isNotBlank(l4_dept_id)) {
			entity.getMap().put("par_dept_id", l4_dept_id);
		} else if (StringUtils.isNotBlank(dept_id)) {
			entity.getMap().put("par_dept_id", dept_id);
		}

		audit_state = StringUtils.isBlank(audit_state) ? "21" : audit_state;

		if (GenericValidator.isInt(audit_state)) {
			// 按订单状态查询
			int state = Integer.valueOf(audit_state);
			switch (state) {
			case 10: // 系统管理员-审核中
				entity.getMap().put("audit_state_lt", 3);
				break;
			case 11: // 系统管理员-已完成
				entity.getMap().put("audit_state_eq", 3);
				break;
			case 20: // 非系统管理员-待审核
				// entity.getMap().put("audit_state_2", audit_state);
				// entity.getMap().put("audit_state_eq_20", audit_state);
				// break;
			case 21: // 非系统管理员-已审核
				entity.getMap().put("audit_state_2", audit_state);
				entity.getMap().put("audit_state_eq_21", audit_state);
				break;
			}
		}

        // -1订单已被修改（等待客户确认），
        // 0订单未被修改（初始状态），
        // 1客户已确认.此状态在客户撤回后需要重置
		if (StringUtils.isNotBlank(kh_confirm_state)) {
			entity.setKh_confirm_state(Integer.valueOf(kh_confirm_state));
		}

		if (role_id_eq_10) { // 系统管理员登录
			dynaBean.set("dept_type", "1");
			Long recordCount = super.getFacade().getKonkaOrderInfoService().getKonkaOrderInfoAndNextRoleCount(entity);

			pager.setPageSize(5);
			pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
			entity.getRow().setFirst(pager.getFirstRow());
			entity.getRow().setCount(pager.getRowCount());

			List<KonkaOrderInfo> entityList = super.getFacade().getKonkaOrderInfoService()
					.getKonkaOrderInfoAndNextRoleResultForPaginatedList(entity);
			request.setAttribute("entityList", entityList);
		} else { // 非系统管理员登录
			dynaBean.set("dept_type", "2");

			// if (StringUtils.isBlank(audit_state) && (role_id_eq_30 ||
			// role_id_eq_34 || role_id_eq_38 || role_id_eq_39)) {
			// //
			// 这些角色可见分公司所有订单数据，加audit_state的空判断是为了设置仅在未选择“审核状态”查询条件的时候这些角色可以查看该分公司的全部数据
			// KonkaDept fgs_dept =
			// super.getSuperiorForDeptType(userInfo.getDept_id(), 3);
			// if (null != fgs_dept) {
			// entity.getMap().put("par_or_children_dept_id",
			// fgs_dept.getDept_id());
			// }
			// } else {
			// entity.getMap().put("no_sys_man_user_id", userInfo.getId()); //
			// 表示需要当前用户角色审核的订单
			// entity.getMap().put("par_or_children_dept_id",
			// userInfo.getDept_id());
			// }

			// 数据级别判断开始
			Long __dept_id = userInfo.getDept_id(); // 默认为当前用户所在部门
			int max_dlevel = super.getMaxDLevel(userInfo.getId()); // 获取当前用户的最高可视部门级别
			request.setAttribute("max_dlevel", max_dlevel);
			logger.info("Max level : {}", max_dlevel);
			switch (max_dlevel) {
			case 9:
				// 集团及以下部门可见
				__dept_id = 0L; // 0表示部门根节点，即“多媒体事业本部”
				break;
			case 8:
				// 分公司及以下部门可见
				KonkaDept dept_fgs = super.getKonkaDeptForFgs(Long.valueOf(__dept_id)); // 查询部门分公司
				if (null != dept_fgs && null != dept_fgs.getDept_id()) {
					__dept_id = dept_fgs.getDept_id(); // 分公司部门ID
				}
				break;
			case 7:
				// 我所在的部门及以下部门可见
				__dept_id = userInfo.getDept_id(); // 默认为当前用户所在部门
				break;
			case 0:
				break;
			default:
				// 出错
			}
			// 数据级别判断结束

			entity.getMap().put("par_or_children_dept_id", __dept_id);

			if (StringUtils.isBlank(audit_state) && (max_dlevel >= 7)) {
				// 这些角色可见分公司所有订单数据，加audit_state的空判断是为了设置仅在未选择“审核状态”查询条件的时候这些角色可以查看该分公司的全部数据
				// KonkaDept fgs_dept =
				// super.getSuperiorForDeptType(userInfo.getDept_id(), 3);
				// if (null != fgs_dept) {
				// entity.getMap().put("par_or_children_dept_id",
				// fgs_dept.getDept_id());
				// }
			} else {
				entity.getMap().put("no_sys_man_user_id", userInfo.getId()); // 表示需要当前用户角色审核的订单
				// entity.getMap().put("par_or_children_dept_id",
				// userInfo.getDept_id());
			}

			Long recordCount = super.getFacade().getKonkaOrderInfoService().getKonkaOrderInfoWithNoSysManCount(entity);

			pager.setPageSize(5);
			pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
			entity.getRow().setFirst(pager.getFirstRow());
			entity.getRow().setCount(pager.getRowCount());

			List<KonkaOrderInfo> entityList = super.getFacade().getKonkaOrderInfoService()
					.getKonkaOrderInfoWithNoSysManPaginatedList(entity);
			if (null != entityList) {
				for (KonkaOrderInfo orderInfo : entityList) {
					// 0： 待审核 1: 已审核
					orderInfo.getMap().put("states", role_ids.contains(orderInfo.getNext_audit_role_id()) ? 0 : 1);
				}
			}

			request.setAttribute("entityList", entityList);
		}
		return new ActionForward("/mobile/admin/KonkaOrderAudit/listorder.jsp");
	}

	public ActionForward listcustorder(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);
		Pager pager = (Pager) dynaBean.get("pager");
		String cust_id = (String) dynaBean.get("cust_id");

		/**
		 * @ADD BY Jiang,JiaYong
		 * @version 2013-08-24
		 */
		String FROMSALESMAN = (String) dynaBean.get("FROMSALESMAN"); // 状态位，标识是否是业务员提交
		String CUSTID = (String) dynaBean.get("CUSTID"); // 业务提交请求的客户ID

		// 如果是业务员查看明细则添加客户名称在导航菜单中
		if ("1".equals(FROMSALESMAN)) {
			KonkaR3Shop krs = new KonkaR3Shop();
			krs.setId(Long.valueOf(CUSTID));
			krs = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(krs);
			if (null != krs) {
				request.setAttribute("naviString",
						request.getAttribute("naviString") + " &gt; 【" + krs.getCustomer_name() + "】");
			}
		}

		PeProdUser user = null;
		HttpSession session = request.getSession();
		// 业务员处理客户订单信息
		if (GenericValidator.isLong(CUSTID)) {
			user = new PeProdUser();
			user.getRow().setCount(1);
			user.setCust_id(Long.valueOf(CUSTID));
			List<PeProdUser> userList = super.getFacade().getPeProdUserService().getPeProdUserList(user);
			if (null != userList && 0 != userList.size()) {
			}
			user = userList.get(0);
			session.setAttribute(Constants.CUSTOMER_USER_INFO, user);
			request.setAttribute("SESSION_U_ID",
					((PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO)).getId());
		} else {
			user = (PeProdUser) session.getAttribute(Constants.CUSTOMER_USER_INFO);
			request.setAttribute("SESSION_U_ID", user.getId());
		}
		if (null == user) {
			return null;
		}

		Calendar cal = Calendar.getInstance();
		Calendar f = (Calendar) cal.clone();
		f.clear();
		f.set(Calendar.YEAR, cal.get(Calendar.YEAR));
		f.set(Calendar.MONTH, cal.get(Calendar.MONTH));
		String firstday = new SimpleDateFormat("yyyy-MM-dd").format(f.getTime());
		String today = DateFormatUtils.format(new Date(), "yyyy-MM-dd");

		String add_date_gt = (String) dynaBean.get("add_date_gt");
		String add_date_lt = (String) dynaBean.get("add_date_lt");
		String trade_index_like = (String) dynaBean.get("trade_index_like");
		String pay_type = (String) dynaBean.get("pay_type");
		String send_type = (String) dynaBean.get("send_type");
		String audit_state_x = (String) dynaBean.get("audit_state_x");
		String pd_name_like = (String) dynaBean.get("pd_name_like");
		String is_delivered = (String) dynaBean.get("is_delivered");

		if (add_date_gt == null) {
			add_date_gt = firstday;
		}
		if (add_date_lt == null) {
			add_date_lt = today;
		}

		dynaBean.set("add_date_gt", add_date_gt);
		dynaBean.set("add_date_lt", add_date_lt);

		KonkaOrderInfo konkaOrderInfo = new KonkaOrderInfo();
		super.copyProperties(konkaOrderInfo, form);
		konkaOrderInfo.setIs_del(0);
		if (StringUtils.isNotBlank(trade_index_like)) {
			konkaOrderInfo.getMap().put("trade_index_like", trade_index_like);
		}
		if (StringUtils.isNotBlank(cust_id)) {
			konkaOrderInfo.setCust_id(Long.valueOf(cust_id)); // 业务员，直销员登录查看
		} else {
			konkaOrderInfo.setCust_id(user.getCust_id()); //
		}
		if (StringUtils.isNotBlank(pay_type)) {
			konkaOrderInfo.setPay_type(Integer.valueOf(pay_type));
		}
		if (StringUtils.isNotBlank(send_type)) {
			konkaOrderInfo.setSend_type(Integer.valueOf(send_type));
		}
		if (StringUtils.isNotBlank(audit_state_x)) {
			int audit_state = Integer.valueOf(audit_state_x);
			switch (audit_state) {
			case 10:// 未提交
				konkaOrderInfo.setIs_submit(0);
				konkaOrderInfo.setAudit_state(null);
				break;
			case 20:// 审核中
				konkaOrderInfo.setIs_submit(1);
				konkaOrderInfo.getMap().put("audit_state_lt", 3);
				break;
			case 30:// 已审核
				konkaOrderInfo.setIs_submit(1);
				konkaOrderInfo.getMap().put("audit_state_eq", 3);
				break;
			}
		}
		if (StringUtils.isNotBlank(pd_name_like)) {
			konkaOrderInfo.getMap().put("pd_name_like", pd_name_like);
		}
		if (StringUtils.isNotBlank(is_delivered)) {
			konkaOrderInfo.setIs_delivered(Integer.valueOf(is_delivered));
		}

		konkaOrderInfo.getMap().put("start_date", add_date_gt);
		konkaOrderInfo.getMap().put("end_date", add_date_lt);
		Long recordCount = super.getFacade().getKonkaOrderInfoService()
				.getKonkaOrderInfoSearchforPdNameCount(konkaOrderInfo);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		konkaOrderInfo.getRow().setFirst(pager.getFirstRow());
		konkaOrderInfo.getRow().setCount(pager.getRowCount());

		List<KonkaOrderInfo> konkaOrderInfoList = super.getFacade().getKonkaOrderInfoService()
				.getKonkaOrderInfoResultForPaginatedList(konkaOrderInfo);

		// 确定每个订单当前审核角色
		// 1、有审核记录（记录中最高等级的后一步审核角色，要判断是否在最后一步【根据audit_state】）
		// 2、无审核记录（该网点分配的用户角色）
		// ...................

		request.setAttribute("konkaOrderInfoList", konkaOrderInfoList);

		return new ActionForward("/mobile/admin/KonkaOrderAudit/listcustorder.jsp");
	}

	public ActionForward getCountOrder(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;

		String order_date_start = (String) dynaBean.get("order_date_start");
		String order_date_end = (String) dynaBean.get("order_date_end");
		String dept_id = (String) dynaBean.get("dept_id");
		String l4_dept_id = (String) dynaBean.get("l4_dept_id");
		String l5_dept_id = (String) dynaBean.get("l5_dept_id");
		String user_id = (String) dynaBean.get("user_id");
		String userpass = (String) dynaBean.get("userpass");
		 //版本号
		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");

		if (StringUtils.isBlank(user_id) && StringUtils.isBlank(userpass)) {
			super.renderText(response, "用户信息出错，请联系管理员！");
			return null;
		}

		PeProdUser ui = new PeProdUser();

		String username = "";
		if (GenericValidator.isLong(user_id)) {
			ui.setId(Long.valueOf(user_id));
			ui.setPass_word(new DESPlus().encrypt(userpass));
			ui = getFacade().getPeProdUserService().getPeProdUser(ui);
		} else {
			username = request.getParameter("username");
			ui = checkUserid(username, userpass,android_version,ios_version);
		}
		// 密码
		// String userpass = (String) dynaBean.get("userpass");

		if (null == ui || ui.getCust_id() != null) {
			super.renderHtml(response, "用户信息出错，请联系管理员！" + username);
			return null;
		}

		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(ui.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);

		boolean role_id_eq_10 = false; // 是否为系统管理员
		for (PeRoleUser peRoleUser : peRoleUserList) {
			if (peRoleUser.getRole_id() == 10L) {
				role_id_eq_10 = true;
				break;
			}
		}

		// 默认显示当前1周的时间区间
		Date today = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(today);

		calendar.add(Calendar.DATE, -7);
		String day_first = df.format(calendar.getTime());
		String day_last = df.format(today);

		if (StringUtils.isNotBlank(order_date_start) && StringUtils.isNotBlank(order_date_end)) {
			Date start_date = DateUtils.parseDate(order_date_start, new String[] { "yyyy-MM-dd" });
			Date end_date = DateUtils.parseDate(order_date_end, new String[] { "yyyy-MM-dd" });
			// 判断下单日期间隔是否小于90天
			if ((end_date.getTime() - start_date.getTime()) > 90 * 24 * 60 * 60 * 1000L) { // 超过90天
				String msg = super.getMessage(request, "order.date.over.90.day");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			}
		} else {
			dynaBean.set("order_date_start", day_first);// 默认当前日期的前7天
			dynaBean.set("order_date_end", day_last);
		}

		KonkaOrderInfo entity = new KonkaOrderInfo();
		entity.setIs_submit(1); // 0:表示暂存；1：表示正式提交

		if (StringUtils.isNotBlank(order_date_start)) {
			entity.getMap().put("start_date", order_date_start);
		} else {
			entity.getMap().put("start_date", day_first);
		}
		if (StringUtils.isNotBlank(order_date_end)) {
			entity.getMap().put("end_date", order_date_end);
		} else {
			entity.getMap().put("end_date", day_last);
		}

		if (role_id_eq_10) { // 系统管理员登录
			dynaBean.set("dept_type", "1");
			// 获取流程列表
			KonkaOrderAuditProcess process = new KonkaOrderAuditProcess();
			process.setIs_del(0);
			List<KonkaOrderAuditProcess> processList = super.getFacade().getKonkaOrderAuditProcessService()
					.getKonkaOrderAuditProcessList(process);
			request.setAttribute("processList", processList);

			if (StringUtils.isNotBlank(l5_dept_id)) {
				entity.getMap().put("par_dept_id", l5_dept_id);
			} else if (StringUtils.isNotBlank(l4_dept_id)) {
				entity.getMap().put("par_dept_id", l4_dept_id);
			} else if (StringUtils.isNotBlank(dept_id)) {
				entity.getMap().put("par_dept_id", dept_id);
			}

			Long recordCount = super.getFacade().getKonkaOrderInfoService().getKonkaOrderInfoAndNextRoleCount(entity);
			super.renderText(response, String.valueOf(Math.ceil(recordCount)));
		} else { // 非系统管理员登录
			dynaBean.set("dept_type", "2");

			// 获取流程列表
			KonkaOrderAuditProcess process = new KonkaOrderAuditProcess();
			process.getMap().put("par_add_dept_id", ui.getDept_id());
			process.setIs_del(0);
			List<KonkaOrderAuditProcess> processList = super.getFacade().getKonkaOrderAuditProcessService()
					.getKonkaOrderAuditProcessList(process);
			request.setAttribute("processList", processList);

			PeRoleUser role_user = new PeRoleUser();
			role_user.setUser_id(ui.getId());

			List<PeRoleUser> role_userList = super.getFacade().getPeRoleUserService().getPeRoleUserList(role_user);
			List<Long> role_ids = new ArrayList<Long>(); // 当前登录用户的角色列表
			for (PeRoleUser temp : role_userList) {
				role_ids.add(temp.getRole_id());
			}

			entity.getMap().put("no_sys_man_user_id", ui.getId()); // 表示需要当前用户角色审核的订单
			entity.getMap().put("par_or_children_dept_id", ui.getDept_id());

			Long recordCount = super.getFacade().getKonkaOrderInfoService().getKonkaOrderInfoWithNoSysManCount(entity);
			super.renderText(response, String.valueOf(Math.ceil(recordCount)));
		}

		return null;
	}

	public ActionForward getCount(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		String order_date_start = (String) dynaBean.get("order_date_start");
		String order_date_end = (String) dynaBean.get("order_date_end");
		String trade_index = (String) dynaBean.get("trade_index");// 流程类型
		String user_name_like = (String) dynaBean.get("user_name_like");// 订单审核状态
		String process_id = (String) dynaBean.get("process_id");
		String audit_state = (String) dynaBean.get("audit_state");
		 //版本号
		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");
		// super.encodeCharacterForGetMethod(dynaBean, request);

		PeProdUser userInfo = null;

		PeProdUser suser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		if (null != suser) {
			logger.info("session user name: {}", suser.getUser_name());
			userInfo = suser;
		} else {
			String user_id = (String) dynaBean.get("user_id");
			String username = (String) dynaBean.get("username");
			String userpass = (String) dynaBean.get("userpass");
			if (StringUtils.isBlank(user_id) && StringUtils.isBlank(userpass)) {
				super.renderHtml(response, "无效的访问参数！");
				return null;
			}

			userInfo = new PeProdUser();

			if (user_id != null) {
				userInfo.setId(Long.valueOf(user_id));
				userInfo.setPass_word(new DESPlus().encrypt(userpass));
				userInfo = getFacade().getPeProdUserService().getPeProdUser(userInfo);
			} else {
				userInfo = checkUserid(username, userpass,android_version,ios_version);
			}

			if (null == userInfo || userInfo.getCust_id() != null) {
				super.renderHtml(response, "用户名和密码不匹配！" + username);
				return null;
			}
		}

		KonkaOrderInfo entity = new KonkaOrderInfo();
		entity.getMap().put("querybyrole_userid_eq", userInfo.getId()); // 表示需要当前用户角色审核的订单
		entity.getMap().put("par_or_children_dept_id", userInfo.getDept_id());
		entity.setIs_submit(1); // 0:表示暂存；1：表示正式提交
		if (StringUtils.isNotBlank(order_date_start)) {
			entity.getMap().put("start_date", order_date_start);
		}
		if (StringUtils.isNotBlank(order_date_end)) {
			entity.getMap().put("end_date", order_date_end);
		}
		if (StringUtils.isNotBlank(trade_index)) {
			entity.setTrade_index(trade_index);
		}
		if (StringUtils.isNotBlank(audit_state)) {
			entity.setAudit_state(new Integer(audit_state));
		}
		if (StringUtils.isNotBlank(process_id)) {
			entity.setProcess_id(Long.valueOf(process_id));
		}
		if (StringUtils.isNotBlank(user_name_like)) {
			entity.getMap().put("user_name_like", user_name_like);
		}

		// 获取流程列表
		KonkaOrderAuditProcess process = new KonkaOrderAuditProcess();
		process.getMap().put("par_add_dept_id", userInfo.getDept_id());
		process.setIs_del(0);
		List<KonkaOrderAuditProcess> processList = super.getFacade().getKonkaOrderAuditProcessService()
				.getKonkaOrderAuditProcessList(process);
		request.setAttribute("processList", processList);

		Long recordCount = super.getFacade().getKonkaOrderInfoService().getKonkaOrderInfoCount(entity);

		super.renderText(response, String.valueOf(Math.ceil(recordCount)));
		return null;
	}

	public ActionForward audit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// setNaviStringToRequestScope(form, request);
		// super.saveToken(request);
		DynaBean dynaBean = (DynaBean) form;

		PeProdUser userInfo = null;

		// PeProdUser suser = (PeProdUser) super.getSessionAttribute(request,
		// Constants.USER_INFO);

		// PeRoleUser peRoleUser = super.getRoleInfoByUserId(suser.getId());
		 //版本号
		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");
		
		PeProdUser suser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		if (null != suser) {
			logger.info("session user name: {}", suser.getUser_name());
			userInfo = suser;
		} else if (check_user_allowd) {
			String user_id = (String) dynaBean.get("user_id");
			String username = (String) dynaBean.get("username");
			String userpass = (String) dynaBean.get("userpass");
			if (StringUtils.isBlank(user_id) && StringUtils.isBlank(userpass)) {
				super.renderHtml(response, "无效的访问参数！");
				return null;
			}

			userInfo = new PeProdUser();

			if (user_id != null) {
				userInfo.setId(Long.valueOf(user_id));
				userInfo.setPass_word(new DESPlus().encrypt(userpass));
				userInfo = getFacade().getPeProdUserService().getPeProdUser(userInfo);
			} else {
				userInfo = checkUserid(username, userpass,android_version,ios_version);
			}
		}

		if (null == userInfo) {
			super.renderHtml(response, "用户名和密码不匹配，身份认证失败！");
			return null;
		}
		
		PeRoleUser peRoleUser = super.getRoleInfoByUserId(userInfo.getId());

		String id = (String) dynaBean.get("id");// 查询类型
		// 订单
		KonkaOrderInfo entity = new KonkaOrderInfo();
		entity.setId(Long.valueOf(id));
		entity = super.getFacade().getKonkaOrderInfoService().getKonkaOrderInfo(entity);

		KonkaMobileTerminalFeedback kf = new KonkaMobileTerminalFeedback();
		kf.setTrade_index(entity.getTrade_index());
		kf = super.getFacade().getKonkaMobileTerminalFeedbackService().getKonkaMobileTerminalFeedback(kf);
		if (kf != null) {
			dynaBean.set("content1", kf.getContent());
		}

		// 订单明细
		KonkaOrderInfoDetails konkaOrderInfoDetails = new KonkaOrderInfoDetails();
		konkaOrderInfoDetails.setOrder_id(Long.valueOf(id));
		List<KonkaOrderInfoDetails> konkaOrderInfoDetailsList = getFacade().getKonkaOrderInfoDetailsService()
				.getKonkaOrderInfoDetailsList(konkaOrderInfoDetails);
		entity.setKonkaOrderInfoDetailsList(konkaOrderInfoDetailsList);

		if (null != entity) {

			// 判断是否具有条件
			Boolean flag = false;
			Boolean is_not_first = false;
			if (null != entity.getNext_node_id()) {// 证明已经选择了流程
				KonkaOrderAuditProcessNode node = new KonkaOrderAuditProcessNode();
				node.setId(entity.getNext_node_id());
				node.setIs_del(0);
				node = super.getFacade().getKonkaOrderAuditProcessNodeService().getKonkaOrderAuditProcessNode(node);
				if (null != node) {
					is_not_first = true;
					if (node.getAudit_proc_cond() == 1) {
						flag = true;
					}
				}

			}

			request.setAttribute("has_proc_cond", flag);
			request.setAttribute("is_not_first", is_not_first);
		}

		// 审核记录
		KonkaOrderInfoAudit konkaOrderInfoAudit = new KonkaOrderInfoAudit();
		konkaOrderInfoAudit.setLink_id(Long.valueOf(id));
		List<KonkaOrderInfoAudit> konkaOrderInfoAuditWithRoleInfoList = getFacade().getKonkaOrderInfoAuditService()
				.getKonkaOrderInfoAuditAndRoleList(konkaOrderInfoAudit);

		if (null != konkaOrderInfoAuditWithRoleInfoList && konkaOrderInfoAuditWithRoleInfoList.size() > 0) {
			entity.setKonkaOrderInfoAuditList(konkaOrderInfoAuditWithRoleInfoList);
		}

		entity.setQueryString(super.serialize(request, "id", "method"));

		super.copyProperties(form, entity);

		// 审核流程列表
		if (null != entity.getProcess_id()) {
			KonkaOrderAuditProcessNode node = new KonkaOrderAuditProcessNode();
			node.setAudit_proces_id(entity.getProcess_id());
			List<KonkaOrderAuditProcessNode> nodeList = super.getFacade().getKonkaOrderAuditProcessNodeService()
					.getKonkaOrderAuditProcessNodeList(node);
			request.setAttribute("nodeList", nodeList);
		}
		if (entity.getDoc_type().equals("ZFRE")) {

			// 获取流程列表
			KonkaDept dept = super.getSuperiorForDeptType(userInfo.getDept_id(), 3);
			List<KonkaOrderAuditProcess> processList = new ArrayList<KonkaOrderAuditProcess>();
			// Boolean flag = null;

			if (dept != null) {
				KonkaR3Shop konkaR3Shop = new KonkaR3Shop();
				konkaR3Shop.setId(entity.getCust_id());
				konkaR3Shop.setIs_del(0l);
				konkaR3Shop = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(konkaR3Shop);
				if (null != konkaR3Shop) {
					if (StringUtils.isNotBlank(konkaR3Shop.getCustomer_type())) {// 判断是否是有客户类型
						KonkaOrderAuditProcess process = new KonkaOrderAuditProcess();
						process.getMap().put("par_add_dept_id", dept.getDept_id());
						process.setCustomer_type(konkaR3Shop.getCustomer_type());
						process.setIs_del(0);
						process.setIs_stop(0);
						process.setUsed_field(2);
						processList = super.getFacade().getKonkaOrderAuditProcessService()
								.getKonkaOrderAuditProcessList(process);
						if (null == processList || processList.size() == 0) {
							// 没有分公司自定义的流程取统一流程，即分公司自定义流程
							KonkaOrderAuditProcess ap_public = new KonkaOrderAuditProcess();
							ap_public.setCustomer_type(konkaR3Shop.getCustomer_type());
							ap_public.setAdd_dept_id(0L);
							ap_public.setIs_del(0);
							ap_public.setIs_stop(0);
							ap_public.setUsed_field(2);
							List<KonkaOrderAuditProcess> ap_publicauditProcesseList = getFacade()
									.getKonkaOrderAuditProcessService().getKonkaOrderAuditProcessList(ap_public);
							processList.addAll(ap_publicauditProcesseList);
						}
						if (null == processList || processList.size() == 0) {
							// 没有分公司自定义的流程取统一流程，即分公司流程
							KonkaOrderAuditProcess ap_public = new KonkaOrderAuditProcess();
							ap_public.setCustomer_type(konkaR3Shop.getCustomer_type());
							ap_public.setAdd_dept_id(0L);
							ap_public.setIs_del(0);
							ap_public.setIs_stop(0);
							ap_public.setUsed_field(2);
							List<KonkaOrderAuditProcess> ap_publicauditProcesseList = getFacade()
									.getKonkaOrderAuditProcessService().getKonkaOrderAuditProcessList(ap_public);
							processList.addAll(ap_publicauditProcesseList);
						}
						if (null == processList || processList.size() == 0) {
							// 没有分公司流程取统一流程，即总公司流程
							KonkaOrderAuditProcess ap_public = new KonkaOrderAuditProcess();
							ap_public.getMap().put("par_add_dept_id", dept.getDept_id());
							ap_public.setIs_del(0);
							ap_public.setIs_stop(0);
							ap_public.setUsed_field(2);

							List<KonkaOrderAuditProcess> ap_publicauditProcesseList = getFacade()
									.getKonkaOrderAuditProcessService().getKonkaOrderAuditProcessList(ap_public);
							processList.addAll(ap_publicauditProcesseList);
						}

						if (null == processList || processList.size() == 0) {
							// 没有分公司自定义的流程取统一流程，即总公司自定义流程
							KonkaOrderAuditProcess ap_public = new KonkaOrderAuditProcess();
							ap_public.setAdd_dept_id(0L);
							ap_public.setIs_del(0);
							ap_public.setIs_stop(0);
							ap_public.setUsed_field(2);

							List<KonkaOrderAuditProcess> ap_publicauditProcesseList = getFacade()
									.getKonkaOrderAuditProcessService().getKonkaOrderAuditProcessList(ap_public);
							processList.addAll(ap_publicauditProcesseList);
						}

						// flag = true;
						// request.setAttribute("customer_type",
						// konkaR3Shop.getCustomer_type());
					} else {
						KonkaOrderAuditProcess process = new KonkaOrderAuditProcess();
						process.getMap().put("par_add_dept_id", dept.getDept_id());

						process.setIs_del(0);
						process.setIs_stop(0);
						process.setUsed_field(2);
						processList = super.getFacade().getKonkaOrderAuditProcessService()
								.getKonkaOrderAuditProcessList(process);
						if (null == processList || processList.size() == 0) {
							// 没有分公司统一流程，即分公司优先级高
							KonkaOrderAuditProcess ap_public = new KonkaOrderAuditProcess();

							ap_public.setAdd_dept_id(0L);
							ap_public.setIs_del(0);
							ap_public.setIs_stop(0);
							ap_public.setUsed_field(2);
							List<KonkaOrderAuditProcess> ap_publicauditProcesseList = getFacade()
									.getKonkaOrderAuditProcessService().getKonkaOrderAuditProcessList(ap_public);
							processList.addAll(ap_publicauditProcesseList);
						}
						// flag = false;
					}
				}
				request.setAttribute("processList", processList);
				// request.setAttribute("flag", flag);
			}

		} else {

			// 获取流程列表
			KonkaDept dept = super.getSuperiorForDeptType(userInfo.getDept_id(), 3);
			List<KonkaOrderAuditProcess> processList = new ArrayList<KonkaOrderAuditProcess>();
			Boolean flag = null;
			if (dept != null) {

				KonkaR3Shop konkaR3Shop = new KonkaR3Shop();
				konkaR3Shop.setId(entity.getCust_id());
				konkaR3Shop.setIs_del(0l);

				konkaR3Shop = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(konkaR3Shop);
				if (null != konkaR3Shop) {
					if (StringUtils.isNotBlank(konkaR3Shop.getCustomer_type())) {// 判断是否是有客户类型
						KonkaOrderAuditProcess process = new KonkaOrderAuditProcess();
						process.getMap().put("par_add_dept_id", dept.getDept_id());
						process.setCustomer_type(konkaR3Shop.getCustomer_type());
						process.setIs_del(0);
						process.setIs_stop(0);
						process.setUsed_field(0);
						processList = super.getFacade().getKonkaOrderAuditProcessService()
								.getKonkaOrderAuditProcessList(process);
						if (null == processList || processList.size() == 0) {
							// 没有分公司自定义的流程取统一流程，即分公司自定义流程
							KonkaOrderAuditProcess ap_public = new KonkaOrderAuditProcess();
							ap_public.setCustomer_type(konkaR3Shop.getCustomer_type());
							ap_public.setAdd_dept_id(0L);
							ap_public.setIs_del(0);
							ap_public.setIs_stop(0);
							ap_public.setUsed_field(0);
							List<KonkaOrderAuditProcess> ap_publicauditProcesseList = getFacade()
									.getKonkaOrderAuditProcessService().getKonkaOrderAuditProcessList(ap_public);
							processList.addAll(ap_publicauditProcesseList);
						}
						if (null == processList || processList.size() == 0) {
							// 没有分公司自定义的流程取统一流程，即分公司流程
							KonkaOrderAuditProcess ap_public = new KonkaOrderAuditProcess();
							ap_public.setCustomer_type(konkaR3Shop.getCustomer_type());
							ap_public.setAdd_dept_id(0L);
							ap_public.setIs_del(0);
							ap_public.setIs_stop(0);
							ap_public.setUsed_field(0);
							List<KonkaOrderAuditProcess> ap_publicauditProcesseList = getFacade()
									.getKonkaOrderAuditProcessService().getKonkaOrderAuditProcessList(ap_public);
							processList.addAll(ap_publicauditProcesseList);
						}
						if (null == processList || processList.size() == 0) {
							// 没有分公司流程取统一流程，即总公司流程
							KonkaOrderAuditProcess ap_public = new KonkaOrderAuditProcess();
							ap_public.getMap().put("par_add_dept_id", dept.getDept_id());
							ap_public.setIs_del(0);
							ap_public.setIs_stop(0);
							ap_public.setUsed_field(0);
							ap_public.getMap().put("customer_type_is_null", true);
							List<KonkaOrderAuditProcess> ap_publicauditProcesseList = getFacade()
									.getKonkaOrderAuditProcessService().getKonkaOrderAuditProcessList(ap_public);
							processList.addAll(ap_publicauditProcesseList);
						}

						if (null == processList || processList.size() == 0) {
							// 没有分公司自定义的流程取统一流程，即总公司自定义流程
							KonkaOrderAuditProcess ap_public = new KonkaOrderAuditProcess();
							ap_public.setAdd_dept_id(0L);
							ap_public.setIs_del(0);
							ap_public.setIs_stop(0);
							ap_public.setUsed_field(0);
							ap_public.getMap().put("customer_type_is_null", true);
							List<KonkaOrderAuditProcess> ap_publicauditProcesseList = getFacade()
									.getKonkaOrderAuditProcessService().getKonkaOrderAuditProcessList(ap_public);
							processList.addAll(ap_publicauditProcesseList);
						}

						flag = true;
						request.setAttribute("customer_type", konkaR3Shop.getCustomer_type());
					} else {
						KonkaOrderAuditProcess process = new KonkaOrderAuditProcess();
						process.getMap().put("par_add_dept_id", dept.getDept_id());
						process.getMap().put("customer_type_is_null", true);
						process.setIs_del(0);
						process.setIs_stop(0);
						process.setUsed_field(0);
						processList = super.getFacade().getKonkaOrderAuditProcessService()
								.getKonkaOrderAuditProcessList(process);
						if (null == processList || processList.size() == 0) {
							// 没有分公司统一流程，即分公司优先级高
							KonkaOrderAuditProcess ap_public = new KonkaOrderAuditProcess();
							ap_public.getMap().put("customer_type_is_null", true);
							ap_public.setAdd_dept_id(0L);
							ap_public.setIs_del(0);
							ap_public.setIs_stop(0);
							ap_public.setUsed_field(0);
							List<KonkaOrderAuditProcess> ap_publicauditProcesseList = getFacade()
									.getKonkaOrderAuditProcessService().getKonkaOrderAuditProcessList(ap_public);
							processList.addAll(ap_publicauditProcesseList);
						}
						flag = false;
					}
				}
				request.setAttribute("processList", processList);
				request.setAttribute("flag", flag);
			}
		}
		// 判断是否是总部角色

		Boolean flag_Zb_role = this.getZbRole(peRoleUser.getRole_id().toString());
		request.setAttribute("flag_Zb_role", flag_Zb_role);
		// 角色是否是85 财务意见
		Boolean flag_cw_role = this.getCwRole(userInfo.getId());
		request.setAttribute("flag_cw_role", flag_cw_role);
		// 角色是否是28 51 78业务意见
		Boolean flag_yw_role = this.getYwRole(userInfo.getId());
		request.setAttribute("flag_yw_role", flag_yw_role);
		
		
		//角色是否是38 41
		Boolean  flag_cw_yw_role= this.getCwYwRole(peRoleUser.getId());
		request.setAttribute("flag_cw_yw_role", flag_cw_yw_role);
		// 查询分公司仓库
		KonkaDept fgs = super.getSuperiorForDeptType(entity.getAdd_dept_id(), 3);
		if (null != fgs) {
			KonkaR3DeptStore s = new KonkaR3DeptStore();
			s.setDept_sn(fgs.getDept_sn());
			List<KonkaR3DeptStore> storeList = super.getFacade().getKonkaR3DeptStoreService()
					.getKonkaR3DeptStoreList(s);
			request.setAttribute("storeList", storeList);
		}
		// 取售达方编码
		KonkaR3Shop konkaR3Shop = new KonkaR3Shop();
		konkaR3Shop.setId(entity.getCust_id());
		konkaR3Shop = getFacade().getKonkaR3ShopService().getKonkaR3Shop(konkaR3Shop);
		if (null != konkaR3Shop) {
			request.setAttribute("r3_code", konkaR3Shop.getR3_code());
		}
		/** 取网点业务员 */
		BranchAssign bagn = new BranchAssign();
		bagn.setKonka_r3_id(entity.getCust_id());
		bagn = getFacade().getBranchAssignService().getBranchAssign(bagn);
		if (null != bagn && null != bagn.getUser_id()) {
			PeProdUser ywy = new PeProdUser();
			ywy.setId(bagn.getUser_id());
			ywy = getFacade().getPeProdUserService().getPeProdUserResult(ywy);
			request.setAttribute("ywy_user_name", ywy.getReal_name());
		}

		// 拿到附件
		Attachment attachment = new Attachment();
		attachment.setLink_id(Long.valueOf(id));
		attachment.setLink_tab("KONKA_ORDER_INFO");
		attachment.setDel_mark(Short.valueOf("0"));
		List<Attachment> attachmentList = getFacade().getAttachmentService().getAttachmentList(attachment);
		request.setAttribute("attachmentList", attachmentList);

		request.setAttribute("fullName", super.getPIndexName(entity.getUser_p_index(), ""));
		request.setAttribute("weeks", check_for_stock("check_for_stock") == 0 ? 4 : check_for_stock("check_for_stock"));
		return new ActionForward("/mobile/admin/KonkaOrderAudit/form.jsp");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;

		PeProdUser userInfo = null;

		PeProdUser suser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		if (null != suser) {
			logger.info("session user name: {}", suser.getUser_name());
			userInfo = suser;
		} else {
			String user_id = (String) dynaBean.get("user_id");
			String username = (String) dynaBean.get("username");
			String userpass = (String) dynaBean.get("userpass");
			 //版本号
			String android_version=(String) dynaBean.get("android_version");
			String ios_version=(String) dynaBean.get("ios_version");
			if (StringUtils.isBlank(user_id) && StringUtils.isBlank(userpass)) {
				super.renderHtml(response, "无效的访问参数！");
				return null;
			}

			userInfo = new PeProdUser();

			if (user_id != null) {
				userInfo.setId(Long.valueOf(user_id));
				userInfo.setPass_word(new DESPlus().encrypt(userpass));
				userInfo = getFacade().getPeProdUserService().getPeProdUser(userInfo);
			} else {
				userInfo = checkUserid(username, userpass,android_version,ios_version);
			}

			if (null == userInfo || userInfo.getCust_id() != null) {
				super.renderHtml(response, "用户名和密码不匹配！" + username);
				return null;
			}
		}

		String id = (String) dynaBean.get("id");// 查询类型
		// 订单
		KonkaOrderInfo entity = new KonkaOrderInfo();
		entity.setId(Long.valueOf(id));
		entity = super.getFacade().getKonkaOrderInfoService().getKonkaOrderInfo(entity);

		// 订单明细
		KonkaOrderInfoDetails konkaOrderInfoDetails = new KonkaOrderInfoDetails();
		konkaOrderInfoDetails.setOrder_id(Long.valueOf(id));
		List<KonkaOrderInfoDetails> konkaOrderInfoDetailsList = getFacade().getKonkaOrderInfoDetailsService()
				.getKonkaOrderInfoDetailsList(konkaOrderInfoDetails);
		entity.setKonkaOrderInfoDetailsList(konkaOrderInfoDetailsList);

		// 审核记录
		KonkaOrderInfoAudit konkaOrderInfoAudit = new KonkaOrderInfoAudit();
		konkaOrderInfoAudit.setLink_id(Long.valueOf(id));
		List<KonkaOrderInfoAudit> konkaOrderInfoAuditWithRoleInfoList = getFacade().getKonkaOrderInfoAuditService()
				.getKonkaOrderInfoAuditAndRoleList(konkaOrderInfoAudit);

		if (null != konkaOrderInfoAuditWithRoleInfoList && konkaOrderInfoAuditWithRoleInfoList.size() > 0) {
			entity.setKonkaOrderInfoAuditList(konkaOrderInfoAuditWithRoleInfoList);
		}

		entity.setQueryString(super.serialize(request, "id", "method"));

		super.copyProperties(form, entity);

		// 审核流程列表
		if (null != entity.getProcess_id()) {
			KonkaOrderAuditProcessNode node = new KonkaOrderAuditProcessNode();
			node.setAudit_proces_id(entity.getProcess_id());
			List<KonkaOrderAuditProcessNode> nodeList = super.getFacade().getKonkaOrderAuditProcessNodeService()
					.getKonkaOrderAuditProcessNodeList(node);
			request.setAttribute("nodeList", nodeList);
		}

		// 获取流程列表
		KonkaDept dept = super.getSuperiorForDeptType(userInfo.getDept_id(), 3);
		if (dept != null) {
			KonkaOrderAuditProcess process = new KonkaOrderAuditProcess();
			process.getMap().put("par_add_dept_id", dept.getDept_id());
			process.setIs_del(0);
			List<KonkaOrderAuditProcess> processList = super.getFacade().getKonkaOrderAuditProcessService()
					.getKonkaOrderAuditProcessList(process);

			if (null == processList || processList.size() == 0) {
				// 没有分公司自定义的流程取统一流程，即分公司优先级高
				KonkaOrderAuditProcess ap_public = new KonkaOrderAuditProcess();
				ap_public.setAdd_dept_id(0L);
				ap_public.setIs_del(0);
				List<KonkaOrderAuditProcess> ap_publicauditProcesseList = getFacade()
						.getKonkaOrderAuditProcessService().getKonkaOrderAuditProcessList(ap_public);
				processList.addAll(ap_publicauditProcesseList);
			}

			request.setAttribute("processList", processList);
		}

		return new ActionForward("/mobile/admin/KonkaOrderAudit/view.jsp");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// if (!super.isTokenValid(request, true)) {
		// super.saveMessage(request, "errors.token");
		// return mapping.findForward("list");
		// }

		DynaBean dynaBean = (DynaBean) form;
		PeProdUser userInfo = null;
		 //版本号
		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");
		PeProdUser suser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		if (null != suser) {
			logger.info("session user name: {}", suser.getUser_name());
			userInfo = suser;
		} else {
			String user_id = (String) dynaBean.get("user_id");
			String username = (String) dynaBean.get("username");
			String userpass = (String) dynaBean.get("userpass");
			if (StringUtils.isBlank(user_id) && StringUtils.isBlank(userpass)) {
				super.renderHtml(response, "无效的访问参数！");
				return null;
			}

			userInfo = new PeProdUser();

			if (user_id != null && GenericValidator.isLong(user_id)) {
				userInfo.setId(Long.valueOf(user_id));
				userInfo.setPass_word(new DESPlus().encrypt(userpass));
				userInfo = getFacade().getPeProdUserService().getPeProdUser(userInfo);
			} else {
				userInfo = checkUserid(username, userpass,android_version,ios_version);
			}

			if (null == userInfo || userInfo.getCust_id() != null) {
				super.renderHtml(response, "用户名和密码不匹配！" + username);
				return null;
			}
		}

		String mod_id = (String) dynaBean.get("mod_id");
		String order_id = (String) dynaBean.get("order_id");
		String doc_type = (String) dynaBean.get("doc_type");
		String audit_result = (String) dynaBean.get("audit_result");
		String audit_comment = (String) dynaBean.get("audit_comment");
		String node_id = (String) dynaBean.get("node_id");
		String process_id = (String) dynaBean.get("process_id");
		String process_state = (String) dynaBean.get("process_state");
		String queryString = (String) dynaBean.get("queryString");

		String[] details_ids = request.getParameterValues("details_id");
		String[] good_counts = request.getParameterValues("good_count");
		String[] good_prices = request.getParameterValues("good_price");
		String[] good_sum_prices = request.getParameterValues("good_sum_price");
		String[] good_discounts = request.getParameterValues("good_discount");
		String[] good_discount_prices = request.getParameterValues("good_discount_price");
		String[] pd_remarks = request.getParameterValues("pd_remark");

		String flag = (String) dynaBean.get("flag");
		String customer_type = (String) dynaBean.get("customer_type");
		String flag_gc = (String) dynaBean.get("flag_Zb_role");// 是否可选工厂
		String[] store_keys = request.getParameterValues("store_key");
		String is_need_to_manager = (String) dynaBean.get("is_need_to_manager");
		String sale_count_01_add = (String) dynaBean.get("sale_count_01_add");
		if (null == order_id) {
			saveError(request, "errors.long", new String[] { order_id });
			return mapping.findForward("list");
		}

		KonkaOrderInfo order = new KonkaOrderInfo();
		order.setId(Long.valueOf(order_id));
		order = super.getFacade().getKonkaOrderInfoService().getKonkaOrderInfo(order);

		// 拿当前用户的角色
		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(userInfo.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);
		Boolean role_flag = false;
		if (null == order.getNext_audit_role_id()) {
			// 还没指定审核流程的单子
			role_flag = true;
		} else {
			if (null != peRoleUserList) {
				for (PeRoleUser user : peRoleUserList) {
					if (user.getRole_id() != null && order.getNext_audit_role_id().equals(user.getRole_id())) {
						role_flag = true;// 只要这个审核角色是当前用户的角色之一就可以继续审核
					}

				}
			}
		}
		if (!role_flag) {// 如果当年用户的角色不具备审核所需要的角色就跳回
			saveError(request, "konka.order.audit_role", new String[] { order.getTrade_index() });
			return mapping.findForward("list");
		}

		// 客户修改了订单凭证类型
		if (StringUtils.isNotBlank(doc_type) && null != order.getDoc_type() && !doc_type.equals(order.getDoc_type())) {
			order.setDoc_type(doc_type);
			super.getFacade().getKonkaOrderInfoService().modifyKonkaOrderInfo(order);
		}
		// 客户下单添加订单类型处理
		if (StringUtils.isNotEmpty(process_id)) {
			order.setProcess_id(Long.valueOf(process_id));
			if (StringUtils.isNotEmpty(process_state))
				order.setProcess_state(Integer.valueOf(process_state));

			// 取审核流程
			KonkaOrderAuditProcessNode node = super.getNextProcessNode(Long.valueOf(process_id), null);
			order.setNext_audit_role_id(node.getRole_id());
			order.setNext_node_id(node.getId());
			// 修改状态
			super.getFacade().getKonkaOrderInfoService().modifyKonkaOrderInfo(order);

			// 审核流程列表
			KonkaOrderAuditProcessNode node1 = new KonkaOrderAuditProcessNode();
			node1.setAudit_proces_id(Long.valueOf(process_id));
			List<KonkaOrderAuditProcessNode> nodeList = super.getFacade().getKonkaOrderAuditProcessNodeService()
					.getKonkaOrderAuditProcessNodeList(node1);
			request.setAttribute("nodeList", nodeList);
		}

		KonkaOrderInfoDetails d = new KonkaOrderInfoDetails();
		d.setOrder_id(order.getId());
		List<KonkaOrderInfoDetails> konkaOrderInfoDetailsList = super.getFacade().getKonkaOrderInfoDetailsService()
				.getKonkaOrderInfoDetailsList(d);
		order.setKonkaOrderInfoDetailsList(konkaOrderInfoDetailsList);

		if (StringUtils.isNotEmpty(is_need_to_manager)) {
			order.setIs_need_to_manager(Integer.parseInt(is_need_to_manager));
		}
		if (StringUtils.isNotEmpty(sale_count_01_add)) {
			order.setSale_count_01_add(new BigDecimal(sale_count_01_add));
		}

		KonkaDept konkaDept = super.getKonkaDeptById(userInfo.getDept_id());
		String dept_name = "";// 审核人部门名称
		if (konkaDept != null && konkaDept.getDept_name() != null) {
			dept_name = konkaDept.getDept_name();
		}

		// 获取下一个审核节点
		Long next_node_id = 0L;
		if (StringUtils.isNotBlank(node_id)) {
			next_node_id = Long.valueOf(node_id);
		} else {
			KonkaOrderAuditProcessNode next_node = super.getNextProcessNode(order.getProcess_id(),
					order.getNext_node_id(), order);
			next_node_id = (null == next_node) ? -1L : next_node.getId();
		}

		// 审核信息保存
		KonkaOrderInfoAudit entity = new KonkaOrderInfoAudit();
		entity.setLink_id(Long.valueOf(order_id));
		entity.setAudit_level(getNextLevelOfOrder(Long.valueOf(order_id), Integer.valueOf(audit_result)));
		entity.setAudit_type(0);// 审批类别：0审批，1会签
		entity.setAudit_user_id(userInfo.getId());
		entity.setAudit_user(userInfo.getUser_name());
		entity.setAudit_dept_id(userInfo.getDept_id());
		entity.setAudit_dept_name(dept_name);
		entity.setAudit_result(Integer.valueOf(audit_result));
		entity.setAudit_comment(audit_comment);
		entity.setCur_node_id(order.getNext_node_id());
		entity.setNext_node_id(next_node_id);

		if ("0".equals(audit_result)) {
			KonkaOrderAuditProcessNode cur_order_first_node = super.getNextProcessNode(order, null);
			entity.getMap().put("cur_order_first_node", cur_order_first_node);
		}

		// 获取当前审核人的角色
		KonkaOrderAuditProcessNode processNode = new KonkaOrderAuditProcessNode();
		processNode.setId(entity.getCur_node_id());
		processNode = super.getFacade().getKonkaOrderAuditProcessNodeService()
				.getKonkaOrderAuditProcessNode(processNode);

		entity.getMap().put("audit_level_gt", processNode.getAudit_level());
		entity.getMap().put("audit_role_id", processNode.getRole_id());
		entity.getMap().put("audit_role_name", processNode.getRole_name());

		// 订单明细修改
		List<KonkaOrderInfoDetails> orderDetailsList = new ArrayList<KonkaOrderInfoDetails>();
		boolean is_update_flag = false;
		if (null != details_ids) {

			Long total_count = 0L;
			BigDecimal total_money = new BigDecimal("0");
			BigDecimal total_discount_price = new BigDecimal("0");

			for (int i = 0; i < details_ids.length; i++) {
				String id = details_ids[i];
				String good_count = good_counts[i];
				String good_price = good_prices[i];
				String good_sum_price = good_sum_prices[i];
				String good_discount = good_discounts[i];
				String good_discount_price = good_discount_prices[i];
				String pd_remark = pd_remarks[i];
				String store_key = "";
				if (flag_gc != null && flag_gc.equals("false")) {
					store_key = store_keys[i];
				}
				KonkaOrderInfoDetails details = new KonkaOrderInfoDetails();
				details.setId(Long.valueOf(id));
				details = super.getFacade().getKonkaOrderInfoDetailsService().getKonkaOrderInfoDetails(details);

				if (!details.getGood_count().equals(Integer.valueOf(good_count))) {
					is_update_flag = true;
				}
				if (!details.getGood_price().equals(new BigDecimal(good_price))) {
					is_update_flag = true;
				}
				if (!details.getGood_discount_price().equals(new BigDecimal(good_discount_price))) {
					is_update_flag = true;
				}

				details.setGood_count(Integer.valueOf(good_count));
				details.setGood_price(new BigDecimal(good_price));
				details.setGood_sum_price(new BigDecimal(good_sum_price));
				details.setGood_discount(new BigDecimal(good_discount));
				details.setGood_discount_price(new BigDecimal(good_discount_price));
				if (null != pd_remark) {
					details.setPd_remark(pd_remark);
				}
				if (flag_gc != null && flag_gc.equals("false")) {
					details.setStore_key(store_key);
				}
				orderDetailsList.add(details);

				total_count += Long.valueOf(StringUtils.isBlank(good_count) ? "0" : good_count);
				total_money = total_money
						.add(new BigDecimal(StringUtils.isBlank(good_sum_price) ? "0" : good_sum_price));
				total_discount_price = total_discount_price.add(details.getGood_discount_price());
			}

			entity.getMap().put("order_num", total_count);
			entity.getMap().put("money", total_money.toString());
			entity.getMap().put("good_discount_price", total_discount_price.toString());

		}

		Boolean flag_ = false;// 标志位

		if (null != orderDetailsList && orderDetailsList.size() > 0) {
			entity.getMap().put("orderDetailsList", orderDetailsList);
			entity.getMap().put("is_update_flag", is_update_flag);

			flag_ = super.getFacade().getKonkaOrderInfoAuditService().is_Four_Week(order, orderDetailsList);
		}

		entity.getMap().put("flag", flag_);
		entity.getMap().put("todo_title", super.getMessage(request, "order.audit.todo_title"));
		if (flag.equals("true") && StringUtils.isNotBlank(customer_type)) {// 是大客户
			getFacade().getKonkaOrderInfoAuditService().createKonkaOrderInfoAuditNewForNewProcess(entity);
		} else {
			getFacade().getKonkaOrderInfoAuditService().createKonkaOrderInfoAuditNew(entity);
		}
		/*
		 * StringBuilder outStr = new StringBuilder(); outStr.append("审核成功!");
		 * super.renderText(response, outStr.toString()); return null;
		 */

		/*
		 * 修改催办信息(改为已经被处理过)
		 */
		KonkaOrderMessageProcess konkaOrderMessageProcess = new KonkaOrderMessageProcess();
		konkaOrderMessageProcess.setLink_id(Long.valueOf(order_id));
		konkaOrderMessageProcess.setLink_table("konka_order_info");
		konkaOrderMessageProcess.setIs_send(0);
		List<KonkaOrderMessageProcess> kkmpList = super.getFacade().getKonkaOrderMessageProcessService()
				.getKonkaOrderMessageProcessList(konkaOrderMessageProcess);
		if (null != kkmpList) {
			for (KonkaOrderMessageProcess kkms : kkmpList) {
				kkms.setIs_send(1);
				super.getFacade().getKonkaOrderMessageProcessService().modifyKonkaOrderMessageProcess(kkms);
			}
		}
		saveMessage(request, "order.audit.success");

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append("KonkaOrderAudit.do?method=list");
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(queryString));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		//System.out.println();
		return forward;
		// return list( mapping, form, request, response);
	}

	/**
	 * 
	 * @author Pan, gang
	 */
	public Integer getNextLevelOfOrder(Long order_id, Integer current_audit_result) {
		// 审批结果：1同意，-1驳回，0-驳回（到客户，重新制单）， -9 撤回（客户操作）
		if (1 > current_audit_result) {
			return current_audit_result;
		}

		Integer next_level = null;
		// 获取当前最大的审核级别
		KonkaOrderInfoAudit audit = new KonkaOrderInfoAudit();
		audit.setLink_id(order_id);
		audit.getMap().put("order_by_audit_datetime", "desc");
		List<KonkaOrderInfoAudit> konkaOrderInfoAuditList = super.getFacade().getKonkaOrderInfoAuditService()
				.getKonkaOrderInfoAuditList(audit);
		if (null == konkaOrderInfoAuditList || konkaOrderInfoAuditList.size() == 0) {
			next_level = 1;
		} else {
			// 获得上次审核级别
			Integer last_level = konkaOrderInfoAuditList.get(0).getAudit_level();
			next_level = last_level + 1;
		}
		return next_level;
	}

	public Boolean getZbRole(String role_id) {

		if (StringUtils.isBlank(role_id)) {
			return false;
		}
		if (role_id.equals("10") || role_id.equals("20") || role_id.equals("21") || role_id.equals("22")) {
			return true;
		}
		return false;
	}

	public Boolean getCwRole(Long user_id) {
		PeRoleUser peRoleUser = new PeRoleUser();
		peRoleUser.setUser_id(user_id);
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(peRoleUser);
		if (null != peRoleUserList && peRoleUserList.size() > 0) {
			for (PeRoleUser c : peRoleUserList) {
				if (c.getRole_id().toString().equals("85")) {
					return true;
				}
			}
		}
		return false;
	}

	public Boolean getYwRole(Long user_id) {
		PeRoleUser peRoleUser = new PeRoleUser();
		peRoleUser.setUser_id(user_id);
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(peRoleUser);
		if (null != peRoleUserList && peRoleUserList.size() > 0) {
			for (PeRoleUser c : peRoleUserList) {
				if (c.getRole_id().toString().equals("28") || c.getRole_id().toString().equals("51")
						|| c.getRole_id().toString().equals("78")) {
					return true;
				}
			}
		}
		return false;
	}
	public Boolean getCwYwRole(Long user_id) {
		PeRoleUser peRoleUser = new PeRoleUser();
		peRoleUser.setUser_id(user_id);
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(peRoleUser);
		if (null != peRoleUserList && peRoleUserList.size() > 0) {
			for (PeRoleUser c : peRoleUserList) {
				if (c.getRole_id().toString().equals("38") || c.getRole_id().toString().equals("41")
						) {
					return true;
				}
			}
		}
		return false;
	}
}