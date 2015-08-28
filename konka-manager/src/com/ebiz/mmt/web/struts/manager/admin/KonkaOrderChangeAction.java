package com.ebiz.mmt.web.struts.manager.admin;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.BranchAssign;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaOrderAuditProcess;
import com.ebiz.mmt.domain.KonkaOrderAuditProcessNode;
import com.ebiz.mmt.domain.KonkaOrderInfo;
import com.ebiz.mmt.domain.KonkaOrderInfoAudit;
import com.ebiz.mmt.domain.KonkaOrderInfoDetails;
import com.ebiz.mmt.domain.KonkaR3DeptStore;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Wu,ShangLong
 * @version 2013-08-05
 */
public class KonkaOrderChangeAction extends BaseAction {
	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		setNaviStringToRequestScope(form, request);
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
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

		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request,
				Constants.USER_INFO);

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

		Long recordCount = super.getFacade().getKonkaOrderInfoService()
				.getKonkaOrderInfoSearchforR3CodeCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setCount(pager.getRowCount());
		entity.getRow().setFirst(pager.getFirstRow());

		List<KonkaOrderInfo> entityList = super.getFacade()
				.getKonkaOrderInfoService()
				.getKonkaOrderInfoResultForR3CodePaginatedList(entity);
		request.setAttribute("entityList", entityList);

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

		return new ActionForward("/../manager/admin/KonkaOrderAudit/list.jsp");
	}

	public ActionForward change(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		setNaviStringToRequestScope(form, request);
		super.saveToken(request);

		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request,
				Constants.USER_INFO);
		PeRoleUser peRoleUser = super.getRoleInfoByUserId(peProdUser.getId());

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");// 查询类型
		if (StringUtils.isBlank(id)) {
			super.renderJavaScript(response,
					"alert('订单ID为空，请选择订单后进行变更');history.back();");
			return null;
		}
		// 订单
		KonkaOrderInfo entity = new KonkaOrderInfo();
		entity.setId(Long.valueOf(id));
		entity = super.getFacade().getKonkaOrderInfoService()
				.getKonkaOrderInfo(entity);

		// 订单明细
		KonkaOrderInfoDetails konkaOrderInfoDetails = new KonkaOrderInfoDetails();
		konkaOrderInfoDetails.setOrder_id(Long.valueOf(id));
		List<KonkaOrderInfoDetails> konkaOrderInfoDetailsList = getFacade()
				.getKonkaOrderInfoDetailsService()
				.getKonkaOrderInfoDetailsList(konkaOrderInfoDetails);

		if (null != entity) {
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
			// Boolean flag = null;

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

						// flag = true;
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
						// flag = false;
					}
				}
				request.setAttribute("processList", processList);
				// request.setAttribute("flag", flag);
			}

		} else {

			// 获取流程列表
			KonkaDept dept = super.getSuperiorForDeptType(peProdUser
					.getDept_id(), 3);
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
						process.setUsed_field(1);
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
							ap_public.setUsed_field(1);
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
							ap_public.setUsed_field(1);
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
							ap_public.setUsed_field(1);
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
							ap_public.setUsed_field(1);
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
					}
				} else {
					KonkaOrderAuditProcess process = new KonkaOrderAuditProcess();
					process.getMap().put("par_add_dept_id", dept.getDept_id());
					process.getMap().put("customer_type_is_null", true);
					process.setIs_del(0);
					process.setIs_stop(0);
					process.setUsed_field(1);
					processList = super.getFacade()
							.getKonkaOrderAuditProcessService()
							.getKonkaOrderAuditProcessList(process);
					if (null == processList || processList.size() == 0) {
						// 没有分公司统一流程，即分公司优先级高
						KonkaOrderAuditProcess ap_public = new KonkaOrderAuditProcess();
						ap_public.getMap().put("customer_type_is_null", true);
						ap_public.setAdd_dept_id(0L);
						ap_public.setIs_del(0);
						ap_public.setIs_stop(0);
						ap_public.setUsed_field(1);
						List<KonkaOrderAuditProcess> ap_publicauditProcesseList = getFacade()
								.getKonkaOrderAuditProcessService()
								.getKonkaOrderAuditProcessList(ap_public);
						processList.addAll(ap_publicauditProcesseList);
					}
					flag = false;
				}

				request.setAttribute("processList", processList);
				request.setAttribute("flag", flag);
			}
		}
		// 判断是否是总部角色

		Boolean flag_Zb_role = this.getZbRole(peRoleUser.getRole_id()
				.toString());
		request.setAttribute("flag_Zb_role", flag_Zb_role);

		// 查询分公司仓库
		KonkaDept fgs = super
				.getSuperiorForDeptType(peProdUser.getDept_id(), 3);
		if (null != fgs) {
			KonkaR3DeptStore s = new KonkaR3DeptStore();
			s.setDept_sn(fgs.getDept_sn());
			List<KonkaR3DeptStore> storeList = super.getFacade()
					.getKonkaR3DeptStoreService().getKonkaR3DeptStoreList(s);
			request.setAttribute("storeList", storeList);
		}
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
					"/../manager/admin/KonkaOrderChange/formTH.jsp");
		}

		return mapping.findForward("input");
	}

	public ActionForward split(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		setNaviStringToRequestScope(form, request);
		super.saveToken(request);

		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request,
				Constants.USER_INFO);
		PeRoleUser peRoleUser = super.getRoleInfoByUserId(peProdUser.getId());

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");// 查询类型
		if (StringUtils.isBlank(id)) {
			super.renderJavaScript(response,
					"alert('订单ID为空，请选择订单后进行变更');history.back();");
			return null;
		}
		// 订单
		KonkaOrderInfo entity = new KonkaOrderInfo();
		entity.setId(Long.valueOf(id));
		entity = super.getFacade().getKonkaOrderInfoService()
				.getKonkaOrderInfo(entity);

		// 订单明细
		KonkaOrderInfoDetails konkaOrderInfoDetails = new KonkaOrderInfoDetails();
		konkaOrderInfoDetails.setOrder_id(Long.valueOf(id));
		List<KonkaOrderInfoDetails> konkaOrderInfoDetailsList = getFacade()
				.getKonkaOrderInfoDetailsService()
				.getKonkaOrderInfoDetailsList(konkaOrderInfoDetails);

		if (null != entity) {
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

		entity.setQueryString(super.serialize(request, "id", "method"));

		request.setAttribute("fullName", super.getPIndexName(entity
				.getUser_p_index(), ""));
		super.copyProperties(form, entity);

		Boolean flag_Zb_role = this.getZbRole(peRoleUser.getRole_id()
				.toString());
		request.setAttribute("flag_Zb_role", flag_Zb_role);

		// 查询分公司仓库
		KonkaDept fgs = super
				.getSuperiorForDeptType(peProdUser.getDept_id(), 3);
		if (null != fgs) {
			KonkaR3DeptStore s = new KonkaR3DeptStore();
			s.setDept_sn(fgs.getDept_sn());
			List<KonkaR3DeptStore> storeList = super.getFacade()
					.getKonkaR3DeptStoreService().getKonkaR3DeptStoreList(s);
			request.setAttribute("storeList", storeList);
		}

		request.setAttribute("weeks",
				check_for_stock("check_for_stock") == 0 ? 4
						: check_for_stock("check_for_stock"));

		return new ActionForward(
				"/../manager/admin/KonkaOrderChange/split_form.jsp");
	}

	public ActionForward splitSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		return null;
	}

	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

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

		// 拿当前用户的角色
		PeProdUser userInfo = (PeProdUser) super.getSessionAttribute(request,
				Constants.USER_INFO);
		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(userInfo.getId());

		// 获取下一个审核节点
		Long next_node_id = 0L;
		if (StringUtils.isEmpty(process_id)) {
			super.renderJavaScript(response,
					"alert('请选择订单流程!');history.back();");
			return null;
		}

		// 客户下单添加订单类型处理
		if (StringUtils.isNotEmpty(process_id) && null != order) {
			order.setProcess_id(Long.valueOf(process_id));
			if (StringUtils.isNotEmpty(process_state))
				order.setProcess_state(Integer.valueOf(process_state));

			// 取审核流程
			KonkaOrderAuditProcessNode node = new KonkaOrderAuditProcessNode();
			node.setAudit_proces_id(Long.valueOf(process_id));
			node.setAudit_level(1);
			node = super.getFacade().getKonkaOrderAuditProcessNodeService()
					.getKonkaOrderAuditProcessNode(node);
			if (null != node.getRole_id()) {
				order.setNext_audit_role_id(node.getRole_id());
			}
			if (null != node.getId()) {
				order.setNext_node_id(node.getId());
			}

			next_node_id = node.getId();// 新的审核流程的第一个节点就是下一个节点

			// 审核流程列表
			KonkaOrderAuditProcessNode node1 = new KonkaOrderAuditProcessNode();
			node1.setAudit_proces_id(order.getProcess_id());
			List<KonkaOrderAuditProcessNode> nodeList = super.getFacade()
					.getKonkaOrderAuditProcessNodeService()
					.getKonkaOrderAuditProcessNodeList(node1);
			request.setAttribute("nodeList", nodeList);
		}

		PeProdUser user = (PeProdUser) super.getSessionAttribute(request,
				Constants.USER_INFO);
		KonkaDept konkaDept = super.getKonkaDeptById(user.getDept_id());
		String dept_name = "";// 审核人部门名称
		if (konkaDept != null && konkaDept.getDept_name() != null) {
			dept_name = konkaDept.getDept_name();
		}

		// 审核信息保存
		KonkaOrderInfoAudit entity = new KonkaOrderInfoAudit();
		entity.setLink_id(Long.valueOf(order_id));
		entity.setAudit_level(1);
		entity.setAudit_type(2);// 审批类别：0审批，1会签，2变更订单
		entity.setAudit_user_id(user.getId());
		entity.setAudit_user(user.getUser_name());
		entity.setAudit_dept_id(user.getDept_id());
		entity.setAudit_dept_name(dept_name);
		entity.setAudit_result(Integer.valueOf(audit_result));
		entity.setAudit_comment(audit_comment);
		// entity.setCur_node_id(order.getNext_node_id());
		entity.setNext_node_id(next_node_id);
		entity.getMap().put("audit_proc_cond_flag", audit_proc_cond_flag);

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
							 is_update_flag = true;
							details.setPd_remark(pd_remark);
						}
					}
				} else {
					if (StringUtils.isNotBlank(pd_remark)) {
						details.setPd_remark(pd_remark);
					}
				}
				if(null!=store_key  && null==details.getStore_key()){
					 is_update_flag = true;
				}else if(null==store_key && null!=details.getStore_key()){
					 is_update_flag = true;
				}else if(null!=store_key && null!=details.getStore_key()&&!details.getStore_key().equals(store_key)){
					 is_update_flag = true;
				}

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

			// flag_ =
			// super.getFacade().getKonkaOrderInfoAuditService().is_Four_Week(order,
			// orderDetailsList);
		}

		entity.getMap().put("flag", flag_);
		entity.getMap().put("todo_title",
				super.getMessage(request, "order.audit.todo_title"));

		if (is_update_flag) {
			// 修改状态
			super.getFacade().getKonkaOrderInfoService().modifyKonkaOrderInfo(
					order);
			KonkaOrderInfoDetails d = new KonkaOrderInfoDetails();
			d.setOrder_id(order.getId());
			List<KonkaOrderInfoDetails> konkaOrderInfoDetailsList = super
					.getFacade().getKonkaOrderInfoDetailsService()
					.getKonkaOrderInfoDetailsList(d);
			order.setKonkaOrderInfoDetailsList(konkaOrderInfoDetailsList);
			getFacade().getKonkaOrderInfoAuditService()
					.createKonkaOrderInfoAuditNewForChange(entity);
		} else {
			super.renderJavaScript(response,
					"alert('订单的主要信息都没有更改！不予保存该次操作。');history.back();");
			return null;
		}

		// saveMessage(request, "order.audit.success");
		//
		// // the line below is added for pagination
		// StringBuffer pathBuffer = new StringBuffer();
		// pathBuffer.append(mapping.findForward("success").getPath());
		// pathBuffer.append("&mod_id=" + mod_id);
		// pathBuffer.append("&");
		// pathBuffer.append(super.encodeSerializedQueryString(queryString));
		return new ActionForward(
				"/../manager/admin/KonkaOrderSearch.do?method=list&mod_id=205700");

	}

	public Boolean getZbRole(String role_id) {

		if (StringUtils.isBlank(role_id)) {
			return false;
		}
		if (role_id.equals("10") || role_id.equals("20")
				|| role_id.equals("21") || role_id.equals("22")) {
			return true;
		}
		return false;
	}
}