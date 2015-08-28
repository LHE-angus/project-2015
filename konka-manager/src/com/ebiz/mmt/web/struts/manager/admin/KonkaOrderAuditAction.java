package com.ebiz.mmt.web.struts.manager.admin;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.alibaba.fastjson.JSON;
import com.ebiz.mmt.domain.Attachment;
import com.ebiz.mmt.domain.BranchAssign;
import com.ebiz.mmt.domain.EcMessage;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaDeptTree;
import com.ebiz.mmt.domain.KonkaMobileTerminalFeedback;
import com.ebiz.mmt.domain.KonkaOrderAuditProcess;
import com.ebiz.mmt.domain.KonkaOrderAuditProcessNode;
import com.ebiz.mmt.domain.KonkaOrderInfo;
import com.ebiz.mmt.domain.KonkaOrderInfoAudit;
import com.ebiz.mmt.domain.KonkaOrderInfoDetails;
import com.ebiz.mmt.domain.KonkaOrderInfoMessageSend;
import com.ebiz.mmt.domain.KonkaOrderMessageProcess;
import com.ebiz.mmt.domain.KonkaR3DeptStore;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.r3.ReturnInfo;
import com.ebiz.mmt.r3.StockCheckResult;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Wu,ShangLong
 * @version 2013-08-05
 */
public class KonkaOrderAuditAction extends BaseAction {
	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 判断session是否超时
		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request,
				Constants.USER_INFO);
		if (null == peProdUser) {
			String msg = super.getMessage(request, "user.session.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('"
					+ msg + "');history.back();}");
			return null;
		}
		setNaviStringToRequestScope(form, request);

		// DynaBean dynaBean = (DynaBean) form;
		// // 默认显示当前1周的时间区间
		// Date today = new Date();
		// SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		// Calendar calendar = Calendar.getInstance();
		// calendar.setTime(today);
		//
		// calendar.add(Calendar.DATE, -7);
		// String day_first = df.format(calendar.getTime());
		// String day_last = df.format(today);
		// dynaBean.set("order_date_start", day_first);// 默认当前日期的前7天
		// dynaBean.set("order_date_end", day_last);
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 判断session是否超时
		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request,
				Constants.USER_INFO);
		if (null == peProdUser) {
			String msg = super.getMessage(request, "user.session.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('"
					+ msg + "');history.back();}");
			return null;
		}

		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String order_date_start = (String) dynaBean.get("order_date_start");
		String order_date_end = (String) dynaBean.get("order_date_end");
		String trade_index_like = (String) dynaBean.get("trade_index_like");// 流程类型
		String user_shop_name_like = (String) dynaBean
				.get("user_shop_name_like");// 订单审核状态
		String process_id = (String) dynaBean.get("process_id");

		String pay_type = (String) dynaBean.get("pay_type");
		String send_type = (String) dynaBean.get("send_type");
		String pd_name_like = (String) dynaBean.get("pd_name_like");
		String ag_like = (String) dynaBean.get("ag_like");
		
        String l3_dept_id = (String) dynaBean.get("l3_dept_id");
        String l4_dept_id = (String) dynaBean.get("l4_dept_id");
        String l5_dept_id = (String) dynaBean.get("l5_dept_id");
        String order_by_index = (String) dynaBean.get("order_by_index");
        request.setAttribute("order_by_index", order_by_index);
    
		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(peProdUser.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade()
				.getPeRoleUserService().getPeRoleUserList(_peRoleUser);

		boolean role_id_eq_60 = false;
		for (PeRoleUser peRoleUser : peRoleUserList) {
			if (peRoleUser.getRole_id() == 60L) {
				role_id_eq_60 = true;
				break;
			}
		}

		KonkaOrderInfo entity = new KonkaOrderInfo();
		entity.setIs_del(0);
		entity.setIs_submit(1);
		entity.getMap().put("where_by_process_id", "true");
		//排序
		String order_by_string="";
		if(StringUtils.isNotBlank(order_by_index)){
			switch(Integer.parseInt(order_by_index)){
			case 1:order_by_string=" a.order_date asc";
			break;
			case 2:order_by_string=" a.order_date desc";
			break;
			case 3:order_by_string=" a.cust_id asc";
			break;
			case 4:order_by_string=" a.cust_id desc";
			break;
			case 5:order_by_string=" a.order_num asc";
			break;
			case 6:order_by_string=" a.order_num desc";
			break;
			case 7:order_by_string=" a.money asc";
			break;
			case 8:order_by_string=" a.money desc";
			break;
			default:
				order_by_string="";
			break;
			}
		}
		entity.getMap().put("order_by_string", order_by_string);
		
		
		
		
		
		
		// 处理业务员特例
		if (role_id_eq_60) {
			// 业务员，业务员只能看见其下客户的待审核订单
			entity.getMap().put("querybycust_userid_eq", peProdUser.getId()); // 按客户查询
		}
		entity.getMap().put("querybyrole_userid_eq", peProdUser.getId()); // 按下一个审核角色查询

		// 数据级别判断开始
		Long dept_id = peProdUser.getDept_id(); // 默认为当前用户所在部门
		int max_dlevel = super.getMaxDLevel(peProdUser.getId()); // 获取当前用户的最高可视部门级别
		request.setAttribute("max_dlevel", max_dlevel);
		switch (max_dlevel) {
		case 9:
			// 集团及以下部门可见
			dept_id = 0L; // 0表示部门根节点，即“多媒体事业本部”
			break;
		case 8:
			// 分公司及以下部门可见
			KonkaDept dept_fgs = super
					.getKonkaDeptForFgs(Long.valueOf(dept_id)); // 查询部门分公司
			if (null != dept_fgs && null != dept_fgs.getDept_id()) {
				dept_id = dept_fgs.getDept_id(); // 分公司部门ID
			}
			break;
		case 7:
			// 我所在的部门及以下部门可见
			dept_id = peProdUser.getDept_id(); // 默认为当前用户所在部门
			break;
		case 0:
			entity.getMap().put("querybycust_userid_eq", peProdUser.getId()); // 按客户查询
			break;
		default:
			// 出错
		}
		// 数据级别判断结束

		entity.getMap().put("par_or_children_dept_id", dept_id);
		entity.getMap().put("session_user_id", peProdUser.getId());// 获取当前客户所查看的数据部门
		entity.setIs_submit(1); // 0:表示暂存；1：表示正式提交
		if (StringUtils.isNotBlank(order_date_start)) {
			entity.getMap().put("start_date", order_date_start + " 00:00:00");
		}
		if (StringUtils.isNotBlank(order_date_end)) {
			entity.getMap().put("end_date", order_date_end + " 23:59:59");
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
		if (StringUtils.isNotBlank(l5_dept_id)) {
            entity.getMap().put("par_dept_id", l5_dept_id);
        } else if (StringUtils.isNotBlank(l4_dept_id)) {
            entity.getMap().put("par_dept_id", l4_dept_id);
        } else if (StringUtils.isNotBlank(l3_dept_id)) {
            entity.getMap().put("par_dept_id", l3_dept_id);
        }
		
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		
		if (StringUtils.isBlank(order_date_start) && StringUtils.isBlank(order_date_end)) {
			order_date_end = dateformat.format(calendar.getTime());// 结束时间
			calendar.add(Calendar.MONTH, -1);// 初始化为当月1号
			order_date_start = dateformat.format(calendar.getTime());// 开始时间
			entity.getMap().put("start_date", order_date_start+" 00:00:00");
			entity.getMap().put("start_end", order_date_end+" 23:59:59");
		}

		dynaBean.set("order_date_start", order_date_start);
		dynaBean.set("order_date_end", order_date_end);
		
		Long recordCount = super.getFacade().getKonkaOrderInfoService()
				.getKonkaOrderInfoSearchforR3CodeCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setCount(pager.getRowCount());
		entity.getRow().setFirst(pager.getFirstRow());

		List<KonkaOrderInfo> entityList = super.getFacade()
				.getKonkaOrderInfoService()
				.getKonkaOrderInfoResultForR3CodePaginatedList(entity);

		// for(KonkaOrderInfo konkaorderinfo:entityList){
		// if("1".equals(konkaorderinfo.getKh_confirm_state())){
		// KonkaOrderInfoMessageSend konkaorderinfomessage=new
		// KonkaOrderInfoMessageSend();
		// konkaorderinfomessage.setOrder_id(konkaorderinfo.getId());
		// Long
		// messagecount=super.getFacade().getKonkaOrderInfoMessageSendService().getKonkaOrderInfoMessageSendCount(konkaorderinfomessage);
		// if(messagecount !=null){
		// konkaorderinfo.getMap().put("messagecount", messagecount);
		// List<KonkaOrderInfoMessageSend>
		// KonkaOrderInfoMessageSendList=super.getFacade().getKonkaOrderInfoMessageSendService().getKonkaOrderInfoMessageSendList(konkaorderinfomessage);
		// if(KonkaOrderInfoMessageSendList !=null){
		// konkaorderinfo.getMap().put("KonkaOrderInfoMessageSendList",
		// KonkaOrderInfoMessageSendList);
		// }}}
		// }
		
		// 得到分公司
		for (KonkaOrderInfo konkaOrderInfo : entityList) {
			if (null != konkaOrderInfo.getAdd_dept_id()) {
				KonkaDept konkaDept = new KonkaDept();
				konkaDept.setDept_id(konkaOrderInfo.getAdd_dept_id());
				konkaDept.getMap().put("dept_type_eq", 3);
				konkaDept = super.getFacade().getKonkaDeptService().getKonkaDeptSuperiorByDeptId(konkaDept);
				if (null != konkaDept && null != konkaDept.getDept_name() && !"".equals(konkaDept.getDept_name())) {
					konkaOrderInfo.getMap().put("fgsName", konkaDept.getDept_name());
				}
			}
		}

		// 得到经办
		for (KonkaOrderInfo konkaOrderInfo2 : entityList) {
			if (null != konkaOrderInfo2.getAdd_dept_id()) {
				KonkaDept konkaDept2 = new KonkaDept();
				konkaDept2.setDept_id(konkaOrderInfo2.getAdd_dept_id());
				konkaDept2.getMap().put("dept_type_eq", 4);
				konkaDept2 = super.getFacade().getKonkaDeptService().getKonkaDeptSuperiorByDeptId(konkaDept2);
				if (null != konkaDept2 && !("".equals(konkaDept2))) {
					String deptName = konkaDept2.getDept_name();
					if (null != deptName && !("".equals(deptName))) {
						konkaOrderInfo2.getMap().put("jbName", konkaDept2.getDept_name());
					}
				} else {
					KonkaDept konkaDept3 = new KonkaDept();
					konkaDept3.setDept_id(konkaOrderInfo2.getAdd_dept_id());
					konkaDept3.getMap().put("dept_type_eq", 5);
					konkaDept3 = super.getFacade().getKonkaDeptService().getKonkaDeptSuperiorByDeptId(konkaDept3);
					if (null != konkaDept3 && !"".equals(konkaDept3)) {
						String deptName = konkaDept3.getDept_name();
						if (null != deptName && !("".equals(deptName))) {
							konkaOrderInfo2.getMap().put("jbName", konkaDept3.getDept_name());
						}
					}
				}
			}
		}
		
		request.setAttribute("entityList", entityList);

        request.setAttribute("cs_par_id", peProdUser.getDept_id());
        // 分公司下显示的是从分公司下面经营部级别开始的
		KonkaDeptTree t = new KonkaDeptTree();
        t.setDept_id(peProdUser.getDept_id());
        t = super.getFacade().getKonkaDeptTreeService().getKonkaDeptTree(t);
        if (null != t) {
            request.setAttribute("cs_par_id",t.getRoot_id());
        }

		// 获取流程列表
		KonkaDept dept = super.getSuperiorForDeptType(peProdUser.getDept_id(),
				3);
		if (dept != null) {
			KonkaOrderAuditProcess process = new KonkaOrderAuditProcess();
			process.getMap().put("par_add_dept_id", dept.getDept_id());
			process.setIs_del(0);
			List<KonkaOrderAuditProcess> processList = super.getFacade()
					.getKonkaOrderAuditProcessService()
					.getKonkaOrderAuditProcessList(process);

			KonkaOrderAuditProcess ap_public = new KonkaOrderAuditProcess();
			ap_public.setAdd_dept_id(0L);
			ap_public.setIs_del(0);
			List<KonkaOrderAuditProcess> ap_publicauditProcesseList = getFacade()
					.getKonkaOrderAuditProcessService()
					.getKonkaOrderAuditProcessList(ap_public);
			processList.addAll(ap_publicauditProcesseList);
			request.setAttribute("processList", processList);
		}

		return mapping.findForward("list");
	}

	public ActionForward audit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		setNaviStringToRequestScope(form, request);
		super.saveToken(request);

		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request,
				Constants.USER_INFO);
		PeRoleUser peRoleUser = super.getRoleInfoByUserId(peProdUser.getId());

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");// 查询类型

		// 订单
		KonkaOrderInfo entity = new KonkaOrderInfo();
		entity.setId(Long.valueOf(id));
		entity = super.getFacade().getKonkaOrderInfoService()
				.getKonkaOrderInfo(entity);

		KonkaMobileTerminalFeedback kf = new KonkaMobileTerminalFeedback();
		kf.setTrade_index(entity.getTrade_index());
		kf = super.getFacade().getKonkaMobileTerminalFeedbackService()
				.getKonkaMobileTerminalFeedback(kf);
		if (kf != null) {
			dynaBean.set("content1", kf.getContent());
			if (kf.getContent() != null) {
				dynaBean.set("show", true);
				dynaBean.set("t_id", kf.getId());
			}
		}

		// 订单明细
		KonkaOrderInfoDetails konkaOrderInfoDetails = new KonkaOrderInfoDetails();
		konkaOrderInfoDetails.setOrder_id(Long.valueOf(id));
		List<KonkaOrderInfoDetails> konkaOrderInfoDetailsList = getFacade()
				.getKonkaOrderInfoDetailsService()
				.getKonkaOrderInfoDetailsList(konkaOrderInfoDetails);

		if (null != entity) {

			// 判断是否具有条件
			Boolean flag = false;
			Boolean is_not_first = false;
			if (null != entity.getNext_node_id()) {// 证明已经选择了流程
				KonkaOrderAuditProcessNode node = new KonkaOrderAuditProcessNode();
				node.setId(entity.getNext_node_id());
				node.setIs_del(0);
				node = super.getFacade().getKonkaOrderAuditProcessNodeService()
						.getKonkaOrderAuditProcessNode(node);
				if (null != node) {
					is_not_first = true;
					if (node.getAudit_proc_cond() == 1) {
						flag = true;
					}
				}

			}
			// 取售达方编码
			request.setAttribute("has_proc_cond", flag);
			request.setAttribute("is_not_first", is_not_first);
			KonkaR3Shop konkaR3Shop = new KonkaR3Shop();
			konkaR3Shop.setId(entity.getCust_id());
			konkaR3Shop = getFacade().getKonkaR3ShopService().getKonkaR3Shop(
					konkaR3Shop);
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
				ywy = getFacade().getPeProdUserService().getPeProdUserResult(
						ywy);
				request.setAttribute("ywy_user_name", ywy.getReal_name());
			}
		}
		entity.setKonkaOrderInfoDetailsList(konkaOrderInfoDetailsList);

		// 审核记录
		KonkaOrderInfoAudit konkaOrderInfoAudit = new KonkaOrderInfoAudit();
		konkaOrderInfoAudit.setLink_id(Long.valueOf(id));
		List<KonkaOrderInfoAudit> konkaOrderInfoAuditWithRoleInfoList = getFacade()
				.getKonkaOrderInfoAuditService()
				.getKonkaOrderInfoAuditAndRoleList(konkaOrderInfoAudit);

		if (null != konkaOrderInfoAuditWithRoleInfoList
				&& konkaOrderInfoAuditWithRoleInfoList.size() > 0) {
			entity
					.setKonkaOrderInfoAuditList(konkaOrderInfoAuditWithRoleInfoList);
		}

		entity.setQueryString(super.serialize(request, "id", "method"));

		request.setAttribute("fullName", super.getPIndexName(entity
				.getUser_p_index(), ""));
		super.copyProperties(form, entity);

		// 审核流程列表
		if (null != entity.getProcess_id()) {
			KonkaOrderAuditProcessNode node = new KonkaOrderAuditProcessNode();
			node.setAudit_proces_id(entity.getProcess_id());
			List<KonkaOrderAuditProcessNode> nodeList = super.getFacade()
					.getKonkaOrderAuditProcessNodeService()
					.getKonkaOrderAuditProcessNodeList(node);
			request.setAttribute("nodeList", nodeList);
		}

		if (entity.getDoc_type().equals("ZFRE")) {
			// 退货的
			// 获取流程列表
			KonkaDept dept = super.getSuperiorForDeptType(entity.getAdd_dept_id(), 3);
			List<KonkaOrderAuditProcess> processList = new ArrayList<KonkaOrderAuditProcess>();
			 Boolean flag = null;

			if (dept != null) {
				KonkaR3Shop konkaR3Shop = new KonkaR3Shop();
				konkaR3Shop.setId(entity.getCust_id());
				konkaR3Shop.setIs_del(0l);
				konkaR3Shop = super.getFacade().getKonkaR3ShopService()
						.getKonkaR3Shop(konkaR3Shop);
				if (null != konkaR3Shop) {
					if (StringUtils.isNotBlank(konkaR3Shop.getCustomer_type())) {// 判断是否是有客户类型
						KonkaOrderAuditProcess process = new KonkaOrderAuditProcess();
						process.getMap().put("par_add_dept_id",
								dept.getDept_id());
						process.setCustomer_type(konkaR3Shop.getCustomer_type());
						process.setIs_del(0);
						process.setIs_stop(0);
						process.setUsed_field(2);
						processList = super.getFacade()
								.getKonkaOrderAuditProcessService()
								.getKonkaOrderAuditProcessList(process);
						if (null == processList || processList.size() == 0) {
							// 没有分公司自定义的流程取统一流程，即分公司自定义流程
							KonkaOrderAuditProcess ap_public = new KonkaOrderAuditProcess();
							ap_public.setCustomer_type(konkaR3Shop
									.getCustomer_type());
							ap_public.setAdd_dept_id(0L);
							ap_public.setIs_del(0);
							ap_public.setIs_stop(0);
							ap_public.setUsed_field(2);
							List<KonkaOrderAuditProcess> ap_publicauditProcesseList = getFacade()
									.getKonkaOrderAuditProcessService()
									.getKonkaOrderAuditProcessList(ap_public);
							processList.addAll(ap_publicauditProcesseList);
						}
						if (null == processList || processList.size() == 0) {
							// 没有分公司自定义的流程取统一流程，即分公司流程
							KonkaOrderAuditProcess ap_public = new KonkaOrderAuditProcess();
							ap_public.setCustomer_type(konkaR3Shop
									.getCustomer_type());
							ap_public.setAdd_dept_id(0L);
							ap_public.setIs_del(0);
							ap_public.setIs_stop(0);
							ap_public.setUsed_field(2);
							List<KonkaOrderAuditProcess> ap_publicauditProcesseList = getFacade()
									.getKonkaOrderAuditProcessService()
									.getKonkaOrderAuditProcessList(ap_public);
							processList.addAll(ap_publicauditProcesseList);
						}
						if (null == processList || processList.size() == 0) {
							// 没有分公司流程取统一流程，即总公司流程
							KonkaOrderAuditProcess ap_public = new KonkaOrderAuditProcess();
							ap_public.getMap().put("par_add_dept_id",
									dept.getDept_id());
							ap_public.setIs_del(0);
							ap_public.setIs_stop(0);
							ap_public.setUsed_field(2);

							List<KonkaOrderAuditProcess> ap_publicauditProcesseList = getFacade()
									.getKonkaOrderAuditProcessService()
									.getKonkaOrderAuditProcessList(ap_public);
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
									.getKonkaOrderAuditProcessService()
									.getKonkaOrderAuditProcessList(ap_public);
							processList.addAll(ap_publicauditProcesseList);
						}

						 flag = true;
						// request.setAttribute("customer_type",
						// konkaR3Shop.getCustomer_type());
					} else {
						KonkaOrderAuditProcess process = new KonkaOrderAuditProcess();
						process.getMap().put("par_add_dept_id",
								dept.getDept_id());

						process.setIs_del(0);
						process.setIs_stop(0);
						process.setUsed_field(2);
						processList = super.getFacade()
								.getKonkaOrderAuditProcessService()
								.getKonkaOrderAuditProcessList(process);
						if (null == processList || processList.size() == 0) {
							// 没有分公司统一流程，即分公司优先级高
							KonkaOrderAuditProcess ap_public = new KonkaOrderAuditProcess();

							ap_public.setAdd_dept_id(0L);
							ap_public.setIs_del(0);
							ap_public.setIs_stop(0);
							ap_public.setUsed_field(2);
							List<KonkaOrderAuditProcess> ap_publicauditProcesseList = getFacade()
									.getKonkaOrderAuditProcessService()
									.getKonkaOrderAuditProcessList(ap_public);
							processList.addAll(ap_publicauditProcesseList);
						}
						 flag = false;
					}
				}
				request.setAttribute("processList", processList);
				 request.setAttribute("flag", flag);
			}

		} else {

			// 获取流程列表
			KonkaDept dept = super.getSuperiorForDeptType(entity.getAdd_dept_id(), 3);
			List<KonkaOrderAuditProcess> processList = new ArrayList<KonkaOrderAuditProcess>();
			Boolean flag = null;
			if (dept != null) {

				KonkaR3Shop konkaR3Shop = new KonkaR3Shop();
				konkaR3Shop.setId(entity.getCust_id());
				konkaR3Shop.setIs_del(0l);
				konkaR3Shop = super.getFacade().getKonkaR3ShopService()
						.getKonkaR3Shop(konkaR3Shop);
				if (null != konkaR3Shop) {
					if (StringUtils.isNotBlank(konkaR3Shop.getCustomer_type())) {// 判断是否是有客户类型
						KonkaOrderAuditProcess process = new KonkaOrderAuditProcess();
						process.getMap().put("par_add_dept_id",
								dept.getDept_id());
						process
								.setCustomer_type(konkaR3Shop
										.getCustomer_type());
						process.setIs_del(0);
						process.setIs_stop(0);
						process.setUsed_field(0);
						processList = super.getFacade()
								.getKonkaOrderAuditProcessService()
								.getKonkaOrderAuditProcessList(process);
						if (null == processList || processList.size() == 0) {
							// 没有分公司自定义的流程取统一流程，即分公司自定义流程
							KonkaOrderAuditProcess ap_public = new KonkaOrderAuditProcess();
							ap_public.setCustomer_type(konkaR3Shop
									.getCustomer_type());
							ap_public.setAdd_dept_id(0L);
							ap_public.setIs_del(0);
							ap_public.setIs_stop(0);
							ap_public.setUsed_field(0);
							List<KonkaOrderAuditProcess> ap_publicauditProcesseList = getFacade()
									.getKonkaOrderAuditProcessService()
									.getKonkaOrderAuditProcessList(ap_public);
							processList.addAll(ap_publicauditProcesseList);
						}
						if (null == processList || processList.size() == 0) {
							// 没有分公司自定义的流程取统一流程，即分公司流程
							KonkaOrderAuditProcess ap_public = new KonkaOrderAuditProcess();
							ap_public.setCustomer_type(konkaR3Shop
									.getCustomer_type());
							ap_public.setAdd_dept_id(0L);
							ap_public.setIs_del(0);
							ap_public.setIs_stop(0);
							ap_public.setUsed_field(0);
							List<KonkaOrderAuditProcess> ap_publicauditProcesseList = getFacade()
									.getKonkaOrderAuditProcessService()
									.getKonkaOrderAuditProcessList(ap_public);
							processList.addAll(ap_publicauditProcesseList);
						}
						if (null == processList || processList.size() == 0) {
							// 没有分公司流程取统一流程，即总公司流程
							KonkaOrderAuditProcess ap_public = new KonkaOrderAuditProcess();
							ap_public.getMap().put("par_add_dept_id",
									dept.getDept_id());
							ap_public.setIs_del(0);
							ap_public.setIs_stop(0);
							ap_public.setUsed_field(0);
							ap_public.getMap().put("customer_type_is_null",
									true);
							List<KonkaOrderAuditProcess> ap_publicauditProcesseList = getFacade()
									.getKonkaOrderAuditProcessService()
									.getKonkaOrderAuditProcessList(ap_public);
							processList.addAll(ap_publicauditProcesseList);
						}

						if (null == processList || processList.size() == 0) {
							// 没有分公司自定义的流程取统一流程，即总公司自定义流程
							KonkaOrderAuditProcess ap_public = new KonkaOrderAuditProcess();
							ap_public.setAdd_dept_id(0L);
							ap_public.setIs_del(0);
							ap_public.setIs_stop(0);
							ap_public.setUsed_field(0);
							ap_public.getMap().put("customer_type_is_null",
									true);
							List<KonkaOrderAuditProcess> ap_publicauditProcesseList = getFacade()
									.getKonkaOrderAuditProcessService()
									.getKonkaOrderAuditProcessList(ap_public);
							processList.addAll(ap_publicauditProcesseList);
						}

						flag = true;
						request.setAttribute("customer_type", konkaR3Shop
								.getCustomer_type());
					} else {
						KonkaOrderAuditProcess process = new KonkaOrderAuditProcess();
						process.getMap().put("par_add_dept_id",
								dept.getDept_id());
						process.getMap().put("customer_type_is_null", true);
						process.setIs_del(0);
						process.setIs_stop(0);
						process.setUsed_field(0);
						processList = super.getFacade()
								.getKonkaOrderAuditProcessService()
								.getKonkaOrderAuditProcessList(process);
						if (null == processList || processList.size() == 0) {
							// 没有分公司统一流程，即分公司优先级高
							KonkaOrderAuditProcess ap_public = new KonkaOrderAuditProcess();
							ap_public.getMap().put("customer_type_is_null",
									true);
							ap_public.setAdd_dept_id(0L);
							ap_public.setIs_del(0);
							ap_public.setIs_stop(0);
							ap_public.setUsed_field(0);
							List<KonkaOrderAuditProcess> ap_publicauditProcesseList = getFacade()
									.getKonkaOrderAuditProcessService()
									.getKonkaOrderAuditProcessList(ap_public);
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

		Boolean flag_Zb_role = this.getZbRole(peRoleUser.getRole_id()
				.toString());
		request.setAttribute("flag_Zb_role", flag_Zb_role);

		//角色是否是85 财务意见
		Boolean flag_cw_role = this.getCwRole(peProdUser.getId());
		request.setAttribute("flag_cw_role", flag_cw_role);
		//角色是否是28 51 78业务意见
		Boolean flag_yw_role = this.getYwRole(peProdUser.getId());
		request.setAttribute("flag_yw_role", flag_yw_role);
		//角色是否是28 51 78业务意见
		
		//角色是否是38 41
		Boolean  flag_cw_yw_role= this.getCwYwRole(peProdUser.getId());
		request.setAttribute("flag_cw_yw_role", flag_cw_yw_role);
		
		// 查询分公司仓库
		KonkaDept fgs = super
				.getSuperiorForDeptType(entity.getAdd_dept_id(), 3);
		if (null != fgs) {
			KonkaR3DeptStore s = new KonkaR3DeptStore();
			s.setDept_sn(fgs.getDept_sn());
			s.setIs_del(0);
			List<KonkaR3DeptStore> storeList = super.getFacade()
					.getKonkaR3DeptStoreService().getKonkaR3DeptStoreList(s);
			request.setAttribute("storeList", storeList);
		}

		// 拿到附件
		Attachment attachment = new Attachment();
		attachment.setLink_id(Long.valueOf(id));
		attachment.setLink_tab("KONKA_ORDER_INFO");
		attachment.setDel_mark(Short.valueOf("0"));
		List<Attachment> attachmentList = getFacade().getAttachmentService()
				.getAttachmentList(attachment);
		request.setAttribute("attachmentList", attachmentList);

		// else{
		// KonkaR3DeptStore s = new KonkaR3DeptStore();
		//
		// s.setId(konkaOrderInfoDetailsList.get(0).getStore_num());
		//
		// List<KonkaR3DeptStore> storeList =
		// super.getFacade().getKonkaR3DeptStoreService()
		// .getKonkaR3DeptStoreList(s);
		// //System.out.println(storeList+"是不是");
		// request.setAttribute("storeList", storeList);
		// }
		request.setAttribute("weeks",
				check_for_stock("check_for_stock") == 0 ? 4
						: check_for_stock("check_for_stock"));

		if (entity != null && entity.getDoc_type().equals("ZFRE")) {// 判断如果是退货订单
			return new ActionForward(
					"/../manager/admin/KonkaOrderAudit/formTH.jsp");
		}

		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (!super.isTokenValid(request, true)) {
			super.saveMessage(request, "errors.token");
			return mapping.findForward("list");
		}

		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String order_id = (String) dynaBean.get("order_id");
		String audit_result = (String) dynaBean.get("audit_result");
		String audit_comment = (String) dynaBean.get("audit_comment");
		String node_id = (String) dynaBean.get("node_id");
		String process_id = (String) dynaBean.get("process_id");
		String process_state = (String) dynaBean.get("process_state");
		String queryString = (String) dynaBean.get("queryString");
		String flag = (String) dynaBean.get("flag");
		String customer_type = (String) dynaBean.get("customer_type");
		String audit_proc_cond_flag = (String) dynaBean
				.get("audit_proc_cond_flag");
		String flag_gc = (String) dynaBean.get("flag_Zb_role");// 是否可选工厂
		String doc_type = (String) dynaBean.get("doc_type");
		String is_need_to_manager = (String) dynaBean.get("is_need_to_manager");
		String sale_count_01_add = (String) dynaBean.get("sale_count_01_add");

		String[] details_ids = request.getParameterValues("details_id");
		String[] good_counts = request.getParameterValues("good_count");
		String[] good_prices = request.getParameterValues("good_price");
		String[] good_sum_prices = request.getParameterValues("good_sum_price");
		String[] good_discounts = request.getParameterValues("good_discount");
		String[] good_discount_prices = request
				.getParameterValues("good_discount_price");
		String[] pd_remarks = request.getParameterValues("pd_remark");
		String[] pd_trade_indexs = request.getParameterValues("pd_trade_index");
		String[] store_keys = request.getParameterValues("store_key");

		if (null == order_id) {
			saveError(request, "errors.long", new String[] { order_id });
			return mapping.findForward("list");
		}

		KonkaOrderInfo order = new KonkaOrderInfo();
		order.setId(Long.valueOf(order_id));
		order = super.getFacade().getKonkaOrderInfoService().getKonkaOrderInfo(
				order);
		if (StringUtils.isNotEmpty(doc_type)) {
			order.setDoc_type(doc_type);// 销售凭证类型可改
		}
		if(StringUtils.isNotBlank(is_need_to_manager)){
			order.setIs_need_to_manager(Integer.parseInt(is_need_to_manager));
		}
		if(StringUtils.isNotBlank(sale_count_01_add)){
			order.setSale_count_01_add(new BigDecimal(sale_count_01_add));
		}
		// 拿当前用户的角色
		PeProdUser userInfo = (PeProdUser) super.getSessionAttribute(request,
				Constants.USER_INFO);
		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(userInfo.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade()
				.getPeRoleUserService().getPeRoleUserList(_peRoleUser);
		Boolean role_flag = false;
		if (null == order.getNext_audit_role_id()) {
			// 还没指定审核流程的单子
			role_flag = true;
		} else {
			if (null != peRoleUserList) {
				for (PeRoleUser user : peRoleUserList) {
					if (user.getRole_id() != null
							&& order.getNext_audit_role_id().equals(
									user.getRole_id())) {
						role_flag = true;// 只要这个审核角色是当前用户的角色之一就可以继续审核
					}
				}
			}
		}
		if (!role_flag) {// 如果当年用户的角色不具备审核所需要的角色就跳回
			saveError(request, "konka.order.audit_role", new String[] { order
					.getTrade_index() });
			return mapping.findForward("list");
		}
		// 客户下单添加订单类型处理
		if (null != order) {
			if (StringUtils.isNotEmpty(process_id)) {
				order.setProcess_id(Long.valueOf(process_id));
				if (StringUtils.isNotEmpty(process_state))
					order.setProcess_state(Integer.valueOf(process_state));

				// 取审核流程
				KonkaOrderAuditProcessNode node = super.getNextProcessNode(Long
						.valueOf(process_id), null);
				order.setNext_audit_role_id(node.getRole_id());
				order.setNext_node_id(node.getId());
				// 修改状态
				super.getFacade().getKonkaOrderInfoService()
						.modifyKonkaOrderInfo(order);

				// 审核流程列表
				KonkaOrderAuditProcessNode node1 = new KonkaOrderAuditProcessNode();
				node1.setAudit_proces_id(order.getProcess_id());
				List<KonkaOrderAuditProcessNode> nodeList = super.getFacade()
						.getKonkaOrderAuditProcessNodeService()
						.getKonkaOrderAuditProcessNodeList(node1);
				request.setAttribute("nodeList", nodeList);
			} else {
				super.getFacade().getKonkaOrderInfoService()
						.modifyKonkaOrderInfo(order);
			}
		}
		KonkaOrderInfoDetails d = new KonkaOrderInfoDetails();
		d.setOrder_id(order.getId());
		List<KonkaOrderInfoDetails> konkaOrderInfoDetailsList = super
				.getFacade().getKonkaOrderInfoDetailsService()
				.getKonkaOrderInfoDetailsList(d);
		order.setKonkaOrderInfoDetailsList(konkaOrderInfoDetailsList);

		PeProdUser user = (PeProdUser) super.getSessionAttribute(request,
				Constants.USER_INFO);
		KonkaDept konkaDept = super.getKonkaDeptById(user.getDept_id());
		String dept_name = "";// 审核人部门名称
		if (konkaDept != null && konkaDept.getDept_name() != null) {
			dept_name = konkaDept.getDept_name();
		}

		
		// 获取下一个审核节点

		
		
		Long next_node_id = 0L;
		if (StringUtils.isNotBlank(node_id)) {
			next_node_id = Long.valueOf(node_id);
		} else {
			KonkaOrderAuditProcessNode next_node = super.getNextProcessNode(
					order.getProcess_id(), order.getNext_node_id(), order);
			
			next_node_id = (null == next_node) ? -1L : next_node.getId();
		}

		// 审核信息保存
		KonkaOrderInfoAudit entity = new KonkaOrderInfoAudit();
		entity.setLink_id(Long.valueOf(order_id));
		entity.setAudit_level(getNextLevelOfOrder(Long.valueOf(order_id),
				Integer.valueOf(audit_result)));
		entity.setAudit_type(0);// 审批类别：0审批，1会签
		entity.setAudit_user_id(user.getId());
		entity.setAudit_user(user.getUser_name());
		entity.setAudit_dept_id(user.getDept_id());
		entity.setAudit_dept_name(dept_name);
		entity.setAudit_result(Integer.valueOf(audit_result));
		entity.setAudit_comment(audit_comment);
		entity.setCur_node_id(order.getNext_node_id());
		entity.setNext_node_id(next_node_id);
		entity.getMap().put("audit_proc_cond_flag", audit_proc_cond_flag);

		if ("0".equals(audit_result)) {
			KonkaOrderAuditProcessNode cur_order_first_node = super
					.getNextProcessNode(order, null);
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
				String pd_trade_index = "";
				if (pd_trade_indexs != null) {
					pd_trade_index = pd_trade_indexs[i];
				}
				String store_key = "";
				if (flag_gc.equals("false")) {
					store_key = store_keys[i];
				}

				KonkaOrderInfoDetails details = new KonkaOrderInfoDetails();
				details.setId(Long.valueOf(id));
				details = super.getFacade().getKonkaOrderInfoDetailsService()
						.getKonkaOrderInfoDetails(details);

				// -------修改过就显示出来---start---
				// if
				// (!details.getGood_count().equals(Integer.valueOf(good_count)))
				// {
				// is_update_flag = true;
				// }
				// if (!details.getGood_price().equals(new
				// BigDecimal(good_price))) {
				// is_update_flag = true;
				// }
				// if (!details.getGood_discount_price().equals(new
				// BigDecimal(good_discount_price))) {
				// is_update_flag = true;
				// }
				// if (!details.getGood_discount().equals(new
				// BigDecimal(good_discount))) {
				// is_update_flag = true;
				// }
				// -------修改过就显示出来----end-----
				// -------修改过就显示出来（数据大小相等的话不变）---start---
				if (!details.getGood_count()
						.equals(Integer.valueOf(good_count))) {
					is_update_flag = true;
				}
				if (details.getGood_price().compareTo(
						new BigDecimal(good_price)) != 0) {
					is_update_flag = true;
				}
				if (details.getGood_discount_price().compareTo(
						new BigDecimal(good_discount_price)) != 0) {
					is_update_flag = true;
				}
				if (details.getGood_discount().compareTo(
						new BigDecimal(good_discount)) != 0) {
					is_update_flag = true;
				}
				if (StringUtils.isNotBlank(details.getPd_remark())) {
					if (StringUtils.isNotBlank(pd_remark)) {
						if (!details.getPd_remark().equals(pd_remark)) {
							// is_update_flag = true;
							details.setPd_remark(pd_remark);
						}
					}else{
						details.setPd_remark("");
					}
				} else {
					if (StringUtils.isNotBlank(pd_remark)) {
						// is_update_flag = true;
						details.setPd_remark(pd_remark);
					}else{
						details.setPd_remark("");
					}
				}

				// -------修改过就显示出来（数据大小相等的话改变）---start---

				// if (!StringUtils.equals(details.getStore_key(), store_key)) {
				// logger.info("*************{}, {}", details.getStore_key(),
				// store_key);
				// is_update_flag = true;
				// }
				// details.setPd_remark(pd_remark);
				details.setGood_count(Integer.valueOf(good_count));
				details.setGood_price(new BigDecimal(good_price));
				details.setGood_sum_price(new BigDecimal(good_sum_price));
				details.setGood_discount(new BigDecimal(good_discount));
				details.setGood_discount_price(new BigDecimal(
						good_discount_price));

				details.setPd_trade_index(pd_trade_index);
				if (flag_gc.equals("false")) {
					details.setStore_key(store_key);
				}

				orderDetailsList.add(details);

				total_count += Long
						.valueOf(StringUtils.isBlank(good_count) ? "0"
								: good_count);
				total_money = total_money.add(new BigDecimal(StringUtils
						.isBlank(good_sum_price) ? "0" : good_sum_price));
				total_discount_price = total_discount_price.add(details
						.getGood_discount_price().add(
								details.getGood_discount().multiply(
										details.getGood_sum_price()).divide(
										new BigDecimal(100), 2,
										RoundingMode.HALF_UP)));
			}

			entity.getMap().put("order_num", total_count);
			entity.getMap().put("money", total_money.toString());
			entity.getMap().put("good_discount_price",
					total_discount_price.toString());

		}

		Boolean flag_ = false;// 标志位

		if (null != orderDetailsList && orderDetailsList.size() > 0) {
			entity.getMap().put("orderDetailsList", orderDetailsList);
			entity.getMap().put("is_update_flag", is_update_flag);

			flag_ = super.getFacade().getKonkaOrderInfoAuditService()
					.is_Four_Week(order, orderDetailsList);
		}

		entity.getMap().put("flag", flag_);
		entity.getMap().put("todo_title",
				super.getMessage(request, "order.audit.todo_title"));
		if (flag.equals("true") && StringUtils.isNotBlank(customer_type)) {// 是大客户
			getFacade().getKonkaOrderInfoAuditService()
					.createKonkaOrderInfoAuditNewForNewProcess(entity);
		} else {
			getFacade().getKonkaOrderInfoAuditService()
					.createKonkaOrderInfoAuditNew(entity);
		}

		saveMessage(request, "order.audit.success");

		/*
		 * 修改催办信息(改为已经被处理过)
		 */
		KonkaOrderMessageProcess konkaOrderMessageProcess = new KonkaOrderMessageProcess();
		konkaOrderMessageProcess.setLink_id(Long.valueOf(order_id));
		konkaOrderMessageProcess.setLink_table("konka_order_info");
		konkaOrderMessageProcess.setIs_send(0);
		List<KonkaOrderMessageProcess> kkmpList = super.getFacade()
				.getKonkaOrderMessageProcessService()
				.getKonkaOrderMessageProcessList(konkaOrderMessageProcess);
		if (null != kkmpList) {
			for (KonkaOrderMessageProcess kkms : kkmpList) {
				kkms.setIs_send(1);
				super.getFacade().getKonkaOrderMessageProcessService()
						.modifyKonkaOrderMessageProcess(kkms);
			}
		}


		// 判断是否要发短信
		KonkaOrderInfo order1 = new KonkaOrderInfo();
		order1.setId(Long.valueOf(order_id));
		order1 = super.getFacade().getKonkaOrderInfoService()
				.getKonkaOrderInfo(order1);
		/** 取网点业务员 */

		if ("-1".equals(order1.getKh_confirm_state())
				&& order1.getAudit_state() == 3) {
			KonkaOrderInfoMessageSend t = new KonkaOrderInfoMessageSend();
			BranchAssign bagn = new BranchAssign();
			bagn.setKonka_r3_id(order1.getCust_id());
			bagn = getFacade().getBranchAssignService().getBranchAssign(bagn);
			if (null != bagn && null != bagn.getUser_id()) {
				PeProdUser sender = new PeProdUser();
				sender.setId(bagn.getUser_id());
				sender = getFacade().getPeProdUserService()
						.getPeProdUserResult(sender);
				t.setSender_name(sender.getReal_name());
				t.setSender_code(sender.getId().toString());
				t.setSender_phone(sender.getLink_phone());
			}
			t.setOrder_id(Long.valueOf(order_id));
			PeProdUser receiver = new PeProdUser();
			receiver.setId(order1.getAdd_user_id());
			receiver = super.getFacade().getPeProdUserService()
					.getPeProdUserResult(receiver);
			t.setReceiver_name(receiver.getReal_name());
			t.setReceiver_code(receiver.getId().toString());
			t.setReceiver_phone(receiver.getLink_phone());
			t.setSend_date(new Date());

			if (null != receiver.getLink_phone()) {
				// //System.out.println(m+"条记录");
				SimpleDateFormat dateformat2 = new SimpleDateFormat(
						"yyyy年MM月dd日");
				String date = dateformat2.format(new Date());
				String msg = receiver.getReal_name() + "您好,您的订单["
						+ order1.getTrade_index() + "]已于[" + date
						+ " ]进行内容变更，请使用您的账号登陆查询![vip.konka.com]"
						+ "。若有疑问，请及时联系康佳业务员[" + t.getSender_phone() + "]";

				if (getSendMessageResult(receiver.getLink_phone(), msg)) {
					super.getFacade().getKonkaOrderInfoMessageSendService()
							.createKonkaOrderInfoMessageSend(t);
                    // -1订单已被修改（等待客户确认），
                    // 0订单未被修改（初始状态），
                    // 1客户已确认.此状态在客户撤回后需要重置
					order1.setKh_confirm_state(1);
					super.getFacade().getKonkaOrderInfoService()
							.modifyKonkaOrderInfo(order1);
					EcMessage ecm = new EcMessage();
					ecm.setAdd_date(new Date());
					ecm.setContent(msg);
					ecm.setMobile(receiver.getLink_phone());
					ecm.setOrder_id(order1.getId().toString());
					ecm.setSend_date(new Date());
					ecm.setOrder_state(1);
					ecm.setSend_state(0);
					super.getFacade().getEcMessageService()
							.createEcMessage(ecm);
				}
			}
		}


		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(queryString));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	/**
	 * @author Pan, gang
	 */
	public Integer getNextLevelOfOrder(Long order_id,
			Integer current_audit_result) {
		// 审批结果：1同意，-1驳回，0-驳回（到客户，重新制单）， -9 撤回（客户操作）
		if (1 > current_audit_result) {
			return current_audit_result;
		}

		Integer next_level = null;
		// 获取当前最大的审核级别
		KonkaOrderInfoAudit audit = new KonkaOrderInfoAudit();
		audit.setLink_id(order_id);
		audit.getMap().put("order_by_audit_datetime", "desc");
		List<KonkaOrderInfoAudit> konkaOrderInfoAuditList = super.getFacade()
				.getKonkaOrderInfoAuditService().getKonkaOrderInfoAuditList(
						audit);
		if (null == konkaOrderInfoAuditList
				|| konkaOrderInfoAuditList.size() == 0) {
			next_level = 1;
		} else {
			// 获得上次审核级别
			Integer last_level = konkaOrderInfoAuditList.get(0)
					.getAudit_level();
			next_level = last_level + 1;
		}
		return next_level;
	}

	public ActionForward AjaxgetFlag(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");

		KonkaOrderAuditProcessNode node = new KonkaOrderAuditProcessNode();

		node.setAudit_proces_id(Long.valueOf(id));
		node.setIs_del(0);
		node.setRole_id(60l);// 业务员
		node = super.getFacade().getKonkaOrderAuditProcessNodeService()
				.getKonkaOrderAuditProcessNode(node);
		String flag = "0";
		if (null != node) {
			if (node.getAudit_proc_cond() == 1) {
				flag = "1";
			}
		}
		super.renderText(response, flag);
		return null;
	}

	public Boolean getZbRole(String role_id) {

		if (StringUtils.isBlank(role_id)) {
			return false;
		}
		if (role_id.equals("10") || role_id.equals("20")
				|| role_id.equals("21") || role_id.equals("22") || role_id.equals("51") || role_id.equals("52")) {
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
				if (c.getRole_id().toString().equals("28")||c.getRole_id().toString().equals("51")||c.getRole_id().toString().equals("78")) {
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

	/**
	 * @author Wang kunlin check stock
	 */
	public ActionForward checkStockForAjax(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (!super.isCallR3Interface(request)) {
			return null;
		}

		DynaBean dynaBean = (DynaBean) form;
		String pd_name = (String) dynaBean.get("pd_name");// 型号
		String stock_key = (String) dynaBean.get("stock_key");// 型号

		if (StringUtils.isEmpty(stock_key) || StringUtils.isEmpty(pd_name)) {
			return null;
		}
		List<StockCheckResult> cResult = new ArrayList<StockCheckResult>();
		ReturnInfo<StockCheckResult> info = new ReturnInfo<StockCheckResult>();
		if (stock_key != null && !"".equals(stock_key.trim())) {
			int beginIndex = stock_key.indexOf("[");
			int endIndex = stock_key.indexOf("]");
			String storeAndLoc = stock_key.substring(beginIndex + 1, endIndex);
			String s[] = storeAndLoc.split("-");
			String store = s[0];
			String loc = s[1];
			logger.info(store);
			logger.info(loc);
			logger.info(pd_name);
			// cResult =
			// getFacade().getR3WebInterfaceService().checkStock2(store, loc,
			// pd_name);
			info = getFacade().getR3WebInterfaceService().checkStock2(store,
					loc, pd_name);
			if (info.getSuccess())
				cResult = info.getDataResult();
			if (cResult.size() > 0) {
				logger.info("json string : {}", JSON.toJSONString(cResult
						.get(0)));
				super.renderJson(response, JSON.toJSONString(cResult.get(0)));
			}
		}
		return null;
	}

}