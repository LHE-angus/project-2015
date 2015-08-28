package com.ebiz.mmt.web.struts.manager.admin;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.ChannelDataImport;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaSalesDept;
import com.ebiz.mmt.domain.OperLog;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.domain.TaskPara;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.util.EncryptUtils;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * 订单明细包括分公司对客户的订单明细 ,也包括总部对分公司的订单明细,大客户的订单明细. 这些明细数据可以用于做单据统计用
 * 
 * @author zhou
 * @version 20150311 删除不必要的方法
 */

public class ChannelDataImportAction extends BaseAction {

	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 1.检查当前菜单连接是否有权限
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}

		// 2.导航信息
		setNaviStringToRequestScope(form, request);

		// 3.条件过滤
		DynaBean dynaBean = (DynaBean) form;
		String salesCode = (String) dynaBean.get("salesCode");
		String keyword = (String) dynaBean.get("keyword");
		String customer_name_like = (String) dynaBean.get("customer_name_like");
		String column_8_like = (String) dynaBean.get("column_8_like");
		// 单据类型
		String column_9 = (String) dynaBean.get("column_9");
		// 型号
		String column_11 = (String) dynaBean.get("column_11");
		
		//分公司
		String dept_id = (String) dynaBean.get("dept_id");
		//区域
		String area_name = (String) dynaBean.get("area_name");

		// 订单日期范围
		String s_date = (String) dynaBean.get("s_date");
		String e_date = (String) dynaBean.get("e_date");

		// 凭证日期
		String startDate1 = (String) dynaBean.get("startDate1");
		String endDate1 = (String) dynaBean.get("endDate1");

		// 判断是否是第一次查询
		String is_first = (String) dynaBean.get("is_first");

		SimpleDateFormat fmt1 = new SimpleDateFormat("yyyy-MM-dd");

		// 是否excel
		String excel_all = (String) dynaBean.get("excel_all");

		// 判断是否从客户管理里面跳入进来的
		String is_kh = (String) dynaBean.get("is_kh");
		dynaBean.set("is_kh", is_kh);

		PeProdUser ui = new PeProdUser();
		ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);

		ChannelDataImport entity = new ChannelDataImport();
		entity.getMap().put("count1", true);
		entity.setColumn_25(salesCode);

		if (StringUtils.isNotBlank(keyword)) {
			entity.getMap().put("keyword", keyword);
		}
		if (StringUtils.isNotBlank(area_name)) {
			entity.getMap().put("area_name", area_name);
		}
		if (StringUtils.isNotBlank(customer_name_like)) {
			entity.getMap().put("customer_name_like", customer_name_like);
		}
		if (StringUtils.isNotBlank(column_9)) {
			entity.setColumn_9(column_9.toUpperCase());
		}
		if (StringUtils.isNotBlank(column_8_like)) {
			entity.getMap().put("column_8_like", column_8_like);
		}
		if (StringUtils.isNotBlank(column_11)) {
			entity.getMap().put("md_name_like", column_11);
		}
		if (StringUtils.isNotBlank(s_date)) {
			entity.getMap().put("s_date", s_date + " 00:00:00");
		} else {
			if (StringUtils.isBlank(is_first)) {
				entity.getMap().put("s_date", ChannelDataImportAction.theFirstDayOfCurrentMonth() + " 00:00:00");
				dynaBean.set("s_date", ChannelDataImportAction.theFirstDayOfCurrentMonth());
			}
			// dynaBean.set(s_date,
			// ChannelDataImportAction.theFirstDayOfCurrentMonth());
		}

		if (StringUtils.isNotBlank(e_date)) {
			entity.getMap().put("e_date", e_date + " 23:59:59");
		} else {
			if (StringUtils.isBlank(is_first)) {
				entity.getMap().put("e_date", fmt1.format(new Date()) + " 23:59:59");
				dynaBean.set("e_date", fmt1.format(new Date()));
			}

			// dynaBean.set(e_date,
			// ChannelDataImportAction.theFirstDayOfCurrentMonth());
		}

		if (StringUtils.isNotBlank(startDate1)) {
			entity.getMap().put("startDate1", startDate1 + " 00:00:00");
		}
		// else {
		// if (StringUtils.isBlank(is_first)) {
		// entity.getMap().put("startDate1",
		// ChannelDataImportAction.theFirstDayOfCurrentMonth() + " 00:00:00");
		// dynaBean.set("startDate1",
		// ChannelDataImportAction.theFirstDayOfCurrentMonth());
		// }
		// dynaBean.set(startDate1,
		// ChannelDataImportAction.theFirstDayOfCurrentMonth());
		// }

		if (StringUtils.isNotBlank(endDate1)) {
			entity.getMap().put("endDate1", endDate1 + " 23:59:59");
		}
		// else {
		// if (StringUtils.isBlank(is_first)) {
		// entity.getMap().put("endDate1", fmt1.format(new Date()) +
		// " 23:59:59");
		// dynaBean.set("endDate1", fmt1.format(new Date()));
		// }
		// dynaBean.set(endDate1,
		// ChannelDataImportAction.theFirstDayOfCurrentMonth());
		// }

		// PeRoleUser _peRoleUser = new PeRoleUser();
		// _peRoleUser.setUser_id(ui.getId());
		// List<PeRoleUser> peRoleUserList =
		// this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);
		//

		// List<PeRoleUser> peRoleUserList = (List<PeRoleUser>)
		// request.getSession().getAttribute("roleUserList");
		// boolean role_id_lt_30 = false;
		// boolean role_id_ge_30_lt_60 = false;
		// boolean role_id_eq_60 = false;
		// for (PeRoleUser peRoleUser : peRoleUserList) {
		// if (peRoleUser.getRole_id() < 30) {
		// // KONKA_PE_ROLE_info 10
		// // 10 系统管理员
		// // 20 事业部管理员
		// // 21 事业部领导
		// // 22 事业部市场部管理员
		// // 28 渠道管理部人员
		// // 29 分公司-数据主管
		// role_id_lt_30 = true;
		// }
		// if (peRoleUser.getRole_id() >= 30L && peRoleUser.getRole_id() < 60L)
		// {
		// // 30 分公司管理员
		// // 31 分公司领导
		// // 32 分公司工作人员
		// // 34 分公司总经理
		// // 35 分公司财务部
		// // 36 分公司销管部
		// // 37 分公司市场部
		// // 38 分公司产品会计
		// // 39 分公司财务经理
		// // 40 经营部管理员
		// // 50 办事处管理员
		// role_id_ge_30_lt_60 = true;
		// }
		// if (peRoleUser.getRole_id() == 60L) {
		// // 业务员
		// role_id_eq_60 = true;
		// }
		// }

		super.encodeCharacterForGetMethod(dynaBean, request);

		Pager pager = (Pager) dynaBean.get("pager");
		// if (role_id_lt_30) {
		//
		// } else if (role_id_ge_30_lt_60) {
		// KonkaDept kd = new KonkaDept();
		// kd.setDept_id(ui.getDept_id());
		// kd = super.getFacade().getKonkaDeptService().getKonkaDept(kd);
		// // 数据查询权限
		// entity.getMap().put("is_fgs_user", true);
		// entity.getMap().put("fgs_dept_id", ui.getDept_id());
		// entity.getMap().put("fgs_user_id", ui.getId());
		// entity.getMap().put("count1", null);
		// } else if (role_id_eq_60) {
		// entity.getMap().put("count1", true);
		// entity.getMap().put("ywy_user_id", ui.getId());
		// } else {
		// String msg = super.getMessage(request, "popedom.check.invalid");
		// super.renderJavaScript(response, "window.onload=function(){alert('" +
		// msg + "');history.back();}");
		// return null;
		// }
		
		// 数据级别判断开始
		Long __dept_id = ui.getDept_id(); // 默认为当前用户所在部门
		int max_dlevel = super.getMaxDLevel(ui.getId()); // 获取当前用户的最高可视部门级别
		logger.info("Max level : {}", max_dlevel);
		request.setAttribute("max_dlevel", max_dlevel);
		switch (max_dlevel) {
		case 9:
			// 集团及以下部门可见
			__dept_id = 0L; // 0表示部门根节点，即“多媒体事业本部”
			
			break;
		case 8:
			// 分公司及以下部门可见
			KonkaDept dept_fgs = super.getKonkaDeptForFgs(ui.getDept_id()); // 查询部门分公司
			if (null != dept_fgs && null != dept_fgs.getDept_id()) {
				__dept_id = dept_fgs.getDept_id(); // 分公司部门ID
				entity.getMap().put("dept_id_start", __dept_id);
				//entity.getMap().put("dept_sn", dept_fgs.getDept_sn());
				entity.getMap().put("count1", null);
				
				//默认当前分公司
				request.setAttribute("current_fgs_code", __dept_id);
				request.setAttribute("show_fgs", true);
				
			}
			break;
		case 7:
			// 我所在的部门及以下部门可见
			__dept_id = ui.getDept_id(); // 默认为当前用户所在部门
			entity.getMap().put("dept_id_start", __dept_id);
			entity.getMap().put("count1", null);
			
			//默认当前分公司
			request.setAttribute("current_fgs_code", __dept_id);
			request.setAttribute("show_fgs", true);
			
			break;
		case 0:
			__dept_id = ui.getDept_id(); // 默认为当前用户所在部门
			// entity.getMap().put("dept_id_start", __dept_id);
			entity.getMap().put("filter_by_ywy_id_eq", ui.getId());
			entity.getMap().put("count1", null);
			
			//默认当前分公司
			request.setAttribute("current_fgs_code", __dept_id);
			request.setAttribute("show_fgs", true);
			break;
		default:
			// 出错
		}
		entity.getMap().put("session_user_id", ui.getId());
		// 数据级别判断结束
		// TODO what is it ?
		
		//设置分公司编码
		if (StringUtils.isNotBlank(dept_id)) {
			entity.getMap().put("dept_id", dept_id);
		}
		Long recordCount = super.getFacade().getChannelDataImportService().getChannelDataImportCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<ChannelDataImport> entityList = super.getFacade().getChannelDataImportService()
				.getChannelDataImportPaginatedList(entity);

		if (entityList != null && entityList.size() > 0) {
			Double pagerCount = 0d;
			BigDecimal pagerMoney = new BigDecimal(0);
			for (ChannelDataImport t : entityList) {
				if (t.getColumn_27() != null) {
					pagerCount = pagerCount + t.getColumn_27().doubleValue();
				}
				pagerMoney = pagerMoney.add(t.getColumn_30() == null ? BigDecimal.valueOf(0f) : t.getColumn_30());
			}
			request.setAttribute("pagerCount", pagerCount);
			request.setAttribute("pagerMoney", pagerMoney);

			// 统计总数
			if (StringUtils.isNotBlank(dept_id)) {
				entity.getMap().put("manager_dept_id", dept_id);
			}
			HashMap<BigDecimal, BigDecimal> map = super.getFacade().getChannelDataImportService()
					.getChannelDataImportAllCountAndAllMoney(entity);

			if (map.get("ALL_COUNT") != null && map.get("ALL_MONEY") != null) {
				request.setAttribute("allCount", map.get("ALL_COUNT").toString());
				request.setAttribute("allMoney", map.get("ALL_MONEY").toString());
			}
			if (map.get("ALL_COUNT") == null && map.get("ALL_MONEY") == null) {
				request.setAttribute("allCount", "0.0");
				request.setAttribute("allMoney", "0");
			}
		} else {
			request.setAttribute("pagerCount", 0);
			request.setAttribute("pagerMoney", 0);
			request.setAttribute("allCount", "0.0");
			request.setAttribute("allMoney", "0");
		}

		// excel导出处理
		if (StringUtils.isNotBlank(excel_all) && "1".equals(excel_all)) {
			
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename=" + EncryptUtils.encodingFileName("R3订单明细")
					+ ".xls");
			
			entity.getRow().setCount(recordCount.intValue());
			List<ChannelDataImport> entityList1 = super.getFacade().getChannelDataImportService()
					.getChannelDataImportPaginatedList(entity);
			request.setAttribute("entityList1", entityList1);
			
			return mapping.findForward("view");
		}

		request.setAttribute("entityList", entityList);
		request.setAttribute("recordCount", recordCount);
		return mapping.findForward("list");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		saveToken(request);
		setNaviStringToRequestScope(form, request);

		return mapping.findForward("input");
	}


	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;

		String id = (String) dynaBean.get("id");
		String column_8 = (String) dynaBean.get("column_8");
		String[] pks = (String[]) dynaBean.get("pks");
		String mod_id = (String) dynaBean.get("mod_id");

		if (!StringUtils.isBlank(id) && GenericValidator.isLong(id)) {
			ChannelDataImport entity = new ChannelDataImport();
			entity.setId(new Long(id));
			super.getFacade().getChannelDataImportService().removeChannelDataImport(entity);
			super.createSysOperLog(request, "CHANNEL_DATA_IMPORT", entity.getId(), mod_id, "删除:删除了一条订单号为" + column_8
					+ "的数据", BeanUtils.describe(entity).toString());
			saveMessage(request, "entity.deleted");
		} else if (!ArrayUtils.isEmpty(pks)) {
			TaskPara entity = new TaskPara();
			entity.getMap().put("pks", pks);
			super.getFacade().getTaskParaService().removeTaskPara(entity);
			saveMessage(request, "entity.deleted");
		}

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super
				.encodeSerializedQueryString(super.serialize(request, "id", "pks", "column_8", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end

		return forward;
	}

	public ActionForward logList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}
		setNaviStringToRequestScope(form, request);
		super.getModPopeDom(form, request);

		DynaBean dynaBean = (DynaBean) form;
		PeProdUser ui = new PeProdUser();
		ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
		super.encodeCharacterForGetMethod(dynaBean, request);

		PeRoleUser peRoleUser = new PeRoleUser();
		peRoleUser.setUser_id(ui.getId());
		peRoleUser = this.getFacade().getPeRoleUserService().getPeRoleUser(peRoleUser);
		Pager pager = (Pager) dynaBean.get("pager");

		OperLog operLog = new OperLog();
		operLog.setLink_tab("CHANNEL_DATA_IMPORT");
		Long recordCount = super.getFacade().getOperLogService().getOperLogCount(operLog);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		operLog.getRow().setFirst(pager.getFirstRow());
		operLog.getRow().setCount(pager.getRowCount());

		List<OperLog> operLogList = super.getFacade().getOperLogService().getOperLogPaginatedList(operLog);
		request.setAttribute("operLogList", operLogList);
		return new ActionForward("/admin/ChannelDataImport/log.jsp");

	}




	/**
	 * <p>
	 * 总部管理员手工同步 (系统同步按总部管理员角色来处理,以后不把订单同步功能分配给分公司操作!)
	 * </p>
	 * <p>
	 * 1.每次同步,都从当前月第一天开始同步,所以同步前把1号开始的数据先删掉.(能想到的最快的一种方法了)
	 * </p>
	 * <p>
	 * 2.删数据和插入接口过来的数据放在同一个service里面做
	 * </p>
	 * <p>
	 * 3.每次同步一个销售组织,提交后再进行下一个销售组织同步
	 * </p>
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward ZbSync(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;


		HttpSession session = request.getSession(false);
		PeProdUser userInfo = (PeProdUser) session.getAttribute(Constants.USER_INFO);

		String mod_id = (String) dynaBean.get("mod_id");// 205500

		// 重置数据标记
		String resetflag = null;// 是否重置数据

		int days = Integer.valueOf((String) dynaBean.get("days"));

		if (days > 30) {
			resetflag = "resetflag";
		}

		Long isize = 0l;
		Long msize = 0l;

		// 需要同步销售组织集合
		Set<String> syncVkorgList = new HashSet<String>();
		PeProdUser user = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
		int roleId = 0;
		if (user.getRole_id() != null && user.getRole_id().length() > 0) {
			roleId = Integer.valueOf(user.getRole_id());
		}
		// 只有总部人员 角色 0<= roleid <30 ,200<= roleid <300
		if ((roleId >= 30 && roleId < 200) || (roleId >= 300)) {
			super.renderJavaScript(response, "alert('对不起，您没有权限操作此模块！');history.back();");
			return null;
		}

		// 取出所有销售组织
		List<KonkaSalesDept> konkaSalesDeptList = new ArrayList<KonkaSalesDept>();
		KonkaSalesDept konkaSalesDept = new KonkaSalesDept();
		konkaSalesDept.setIs_del(0);
		konkaSalesDeptList = super.getFacade().getKonkaSalesDeptService().getKonkaSalesDeptList(konkaSalesDept);

		for (KonkaSalesDept kd : konkaSalesDeptList) {
			syncVkorgList.add(kd.getSales_org_code());// 为了去重,减少数据操作
		}
		// 执行结果
		Map<String, Long> map = new HashMap<String, Long>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// 计算当前日期往前推N天的时间
		Calendar c = Calendar.getInstance();
		String year = c.get(Calendar.YEAR) + "";
		c.add(Calendar.DATE, -days);
		Date s_date = c.getTime();

		// =========================同步参数 正式 start=============================//
		// 产品组
		String v_vtweg = "10";
		// 分销渠道
		String v_spart = "10";
		// 同步开始时间
		String v_audat_begin = sdf.format(s_date);
		// 同步截止时间
		String v_audat_end = sdf.format(new Date());
		//
		String v_kunnr = null;
		// String v_kunnr = "F1067DQJD";
		// =========================同步参数 正式 end=============================//

		//添加记录日志
		OperLog operLog = new OperLog();
		operLog.setLink_tab("CHANNEL_DATA_IMPORT");
		operLog.setOper_uname(userInfo.getUser_name());
		operLog.setOper_time(new Date());
		operLog.setPpdm_name("开始执行手动同步R3订单明细");
		operLog.setOper_type("同步产品库 ");
		operLog.setLink_id(Long.parseLong("888885"));
		operLog.setOper_ip(userInfo.getLast_login_ip());
		operLog.setOper_uid(userInfo.getId());
		super.getFacade().getOperLogService().createOperLog(operLog);
		
		/** 1. product start **/
		// 系统稳定后,使用定时器同步,只会同步当前日期,再往前推一个月得到区间数据 **/
		if (resetflag == null || resetflag.equals("")) {
			// syncVkorgList.clear();
			// syncVkorgList.add("F067");
			// v_audat_begin = "2013-11-18";
			// v_audat_end = "2013-11-18";
			map = super
					.getFacade()
					.getChannelDataImportService()
					.createOrModifySyncChannelDataForfgsTj(syncVkorgList,
					v_vtweg, v_spart, v_audat_begin, v_audat_end, v_kunnr);
			isize += map.get("isize");
			msize += map.get("msize");
		}
		/** product end */

		/** 2. reset start 重置数据 */
		if (resetflag != null && (resetflag.length() > 0)) {
			// 同步两年数据 ---每个销售组织两年一个月一个月的同步
			v_audat_begin = "";
			v_audat_end = "";
			LinkedHashMap<String, String> DateHashMap = getDateMap(Integer.parseInt(year) - 1, Integer.parseInt(year));
			for (String korg : syncVkorgList) {
				Set<String> korgList = new HashSet<String>();
				korgList.clear();
				korgList.add(korg);
				// 按销售组织,一个月一个月的同步
				for (Entry<String, String> s : DateHashMap.entrySet()) {
					v_audat_begin = s.getKey();
					v_audat_end = s.getValue();
					// 处理数据
					map = super
							.getFacade()
							.getChannelDataImportService()
							.createOrModifySyncChannelDataForfgsTj(korgList,
							v_vtweg, v_spart, v_audat_begin, v_audat_end, v_kunnr);

					// 统计被影响的数据
					isize += map.get("isize");
					msize += map.get("msize");
				}
			}
			syncVkorgList.clear();
		}
		/** reset end */
		
		//添加记录日志
		operLog.setLink_tab("CHANNEL_DATA_IMPORT");
		operLog.setOper_time(new Date());
		operLog.setOper_uname(userInfo.getUser_name());
		operLog.setPpdm_name("结束执行手动同步R3订单明细");
		operLog.setOper_type("同步产品库 ");
		operLog.setLink_id(Long.parseLong("888885"));
		operLog.setOper_ip(userInfo.getLast_login_ip());
		operLog.setOper_uid(userInfo.getId());
		super.getFacade().getOperLogService().createOperLog(operLog);

		// 记录操作
		createOpLog((isize + msize), userInfo);
		syncVkorgList.clear();
		saveMessage(request, "prodadmin.md.tb.success", new String(String.valueOf(isize + msize)));

        return mapping.findForward("list");

	}

	private void createOpLog(long effectNumber, PeProdUser userInfo) {

		OperLog t = new OperLog();
		t.setPpdm_name("进销存-进货管理-R3订单");
		t.setOper_time(new Timestamp(Calendar.getInstance().getTime().getTime()));
		t.setOper_ip("0:0:0:0:0:0:0:1");

		t.setOper_desc(effectNumber + "条数据被影响.[user]");
		t.setLink_tab("CHANNEL_DATA_IMPORT_2");
		t.setLink_id(0l);
		t.setOper_type("INSERT");
		t.setOper_uid(userInfo.getId());
		t.setOper_uname(userInfo.getUser_name());
		getFacade().getOperLogService().createOperLog(t);
	}

	public static synchronized LinkedHashMap<String, String> getDateMap(int... year) {

		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		for (int i = 0; i < year.length; i++) {// year
			if (year[i] <= 1970)
				throw new IllegalArgumentException("the year that you input can not le 1970");
			if (year[i] >= 9999)
				throw new IllegalArgumentException("the year that you input can not gt 9999");
			for (int j = 0; j <= 12; j++) {// month
				Calendar c = Calendar.getInstance();
				c.set(Calendar.YEAR, year[i]);
				c.set(Calendar.MONTH, j);
				String y = String.valueOf(year[i]);
				String m = "";
				String d = "";// the first day of a month
				String d2 = "";// the last day of a month
				if (c.get(Calendar.MONTH) + 1 >= 10) {
					m = String.valueOf(c.get(Calendar.MONTH) + 1);
				} else {
					m = "0" + String.valueOf(c.get(Calendar.MONTH) + 1);
				}
				d = "0" + c.getActualMinimum(Calendar.DAY_OF_MONTH);
				d2 = "" + c.getActualMaximum(Calendar.DAY_OF_MONTH);

				String start_day = y + "-" + m + "-" + d;
				String last_day = y + "-" + m + "-" + d2;

				map.put(start_day, last_day);
			}
		}
		return map;
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
