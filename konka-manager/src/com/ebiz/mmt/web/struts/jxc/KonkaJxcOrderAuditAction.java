package com.ebiz.mmt.web.struts.jxc;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaOrderAuditProcess;
import com.ebiz.mmt.domain.KonkaOrderAuditProcessNode;
import com.ebiz.mmt.domain.KonkaOrderInfo;
import com.ebiz.mmt.domain.KonkaOrderInfoAudit;
import com.ebiz.mmt.domain.KonkaOrderInfoDetails;
import com.ebiz.mmt.domain.KonkaOrderInfoDetailsAudit;
import com.ebiz.mmt.domain.KonkaOrderInfoUpdateRecord;
import com.ebiz.mmt.domain.KonkaPePdModelLowestPrice;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.KonkaR3Zsof;
import com.ebiz.mmt.domain.PePdModel;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleInfo;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.r3.KHXD;
import com.ebiz.mmt.r3.ReturnInfo;
import com.ebiz.mmt.web.Constants;
import com.ebiz.ssi.web.struts.bean.Pager;

public class KonkaJxcOrderAuditAction extends BaseJxcAction {
	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		String search_type_default = (String) dynaBean.get("search_type");// 查询
		if (StringUtils.isBlank(search_type_default)) {//
			search_type_default = "1";
		}
		dynaBean.set("search_type_default", search_type_default);
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (!isSessionLoginForKonkaJxc(request)) {
			return forwardNoSessionForKonkaJxc(request, response);
		}
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}

		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;

		PeProdUser user = super.getSessionUserInfo(request);
		Pager pager = (Pager) dynaBean.get("pager");
		String trade_index_like = (String) dynaBean.get("trade_index_like");
		String process_state = (String) dynaBean.get("process_state");// 流程类型
		String audit_state = (String) dynaBean.get("audit_state_order");// 订单审核状态
		String start_date = (String) dynaBean.get("start_date");
		String end_date = (String) dynaBean.get("end_date");
		String search_type = (String) dynaBean.get("search_type");// 查询类型

		String customer_name_like = (String) dynaBean.get("customer_name_like");// 客户名称模糊搜索
		String fgs_dept_id = (String) dynaBean.get("fgs_dept_id");
		String jyb_dept_id = (String) dynaBean.get("jyb_dept_id");
		String bsc_dept_id = (String) dynaBean.get("bsc_dept_id");

		if (StringUtils.isBlank(search_type)) {
			search_type = "1";
			// dynaBean.set("search_type", "1");
		}
		if (search_type.equals("2")) {
			audit_state = "";
		}

		if (null == user) {
			return null;
		}

		PeRoleInfo role = new PeRoleInfo();
		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(user.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);

		for (PeRoleUser ru : peRoleUserList) {
			if (ru.getRole_id() < 10000L && ru.getRole_id() != 188L && ru.getRole_id() != 60L) {
				role.setRole_id(ru.getRole_id());
			}
		}

		if (null == role.getRole_id()) {
			role = super.getPeRoleInfoByUserId(user.getId());
		}

		// role = super.getPeRoleInfoByUserId(user.getId());

		// 根据用户部门id找 所属分公司
		KonkaDept fgsDept = super.getKonkaDeptByUserDeptId(user.getDept_id());
		if (null == fgsDept) {// 此处部门均为分公司及分公司上级部门
			String msg = "对不起，您没有权限审核订单信息!";
			renderJavaScript(response, "alert('" + msg + "');history.back();");
			return null;
		}
		request.setAttribute("sybDeptInfoList", super.getDeptInfoList(mapping, form, request, response, null));

		// 初始化列表页面的起始时间
		Date now = new Date();
		if (StringUtils.isBlank(start_date)) {
			start_date = DateFormatUtils.format(now, "yyyy-MM-") + "01";
		}
		if (StringUtils.isBlank(end_date)) {
			end_date = DateFormatUtils.format(now, "yyyy-MM-dd");
		}

		String msg = "";// 定义无权限时的页面提示信息
		KonkaOrderInfo konkaOrderInfo = new KonkaOrderInfo();
		super.copyProperties(konkaOrderInfo, form);
		konkaOrderInfo.getMap().put("trade_index_like", trade_index_like);
		konkaOrderInfo.getMap().put("start_date", start_date);
		konkaOrderInfo.getMap().put("end_date", end_date);

		/** add 20120305===== */
		konkaOrderInfo.setIs_del(0);// 未删除
		konkaOrderInfo.setIs_submit(1);// 已提交
		// konkaOrderInfo.setIs_end(0);// 未完结
		/** add 20120305===== */

		// 搜索条件
		konkaOrderInfo.getMap().put("customer_name_like", customer_name_like);
		if (StringUtils.isNotBlank(fgs_dept_id) || StringUtils.isNotBlank(jyb_dept_id)
				|| StringUtils.isNotBlank(bsc_dept_id)) {
			konkaOrderInfo.getMap().put("order_view_for_search", "true");
			konkaOrderInfo.getMap().put("fgs_dept_id_for_search", fgs_dept_id);
			konkaOrderInfo.getMap().put("jyb_dept_id_for_search", jyb_dept_id);
			konkaOrderInfo.getMap().put("bsc_dept_id_for_search", bsc_dept_id);
		}

		// 待审核搜索 加角色状态条件 开始。。。
		if (StringUtils.equals("1", search_type)) {
			// 查询类型 ：1待审核,2已审核
			// 如果是 待审核，加角色状态条件

			if (StringUtils.isNotBlank(audit_state)) {
				if (StringUtils.equals("-1", audit_state)) {// 未通过
					konkaOrderInfo.getMap().put("audit_state", audit_state);
				}
				if (StringUtils.equals("0", audit_state)) {// 未审核
					konkaOrderInfo.getMap().put("audit_state_not_audit", audit_state);
				}
				if (StringUtils.equals("1", audit_state)) {// 已通过
					konkaOrderInfo.getMap().put("audit_state", audit_state);
				}

				// 该角色在流程中的等级==可能多个
				List<KonkaOrderAuditProcessNode> konkaOrderAuditProcessNodeForLevelList = super
						.getKonkaOrderAuditProcessNodeList(fgsDept.getDept_id(), role.getRole_id());
				if (null != konkaOrderAuditProcessNodeForLevelList && konkaOrderAuditProcessNodeForLevelList.size() > 0) {
					Integer[] andit_level_now_users = new Integer[konkaOrderAuditProcessNodeForLevelList.size()];
					for (int s = 0; s < konkaOrderAuditProcessNodeForLevelList.size(); s++) {
						andit_level_now_users[s] = konkaOrderAuditProcessNodeForLevelList.get(s).getAudit_level();
					}

					// 目前流程中的【特殊比普通多一个总经理 且 总经理之前的都相同 之后的多一个财务】
					// ===========在判断【已审核】、【未审核】时===============
					// andit_level_now_users长度为1时，说明是
					// 【分公司总经理】只在一个流程中,【此时要加上审核人角色和分公司id过滤】
					// 长度为 2时，如果相同， 则在两个流程中的审核等级相同那么就是【分公司总经理】审核==之前==的用户；
					// 长度为 2时,如果不同，则是【分公司总经理】审核==之后==的用户，【此时要加上审核人的id过滤】
					if (null != andit_level_now_users && andit_level_now_users.length == 1) {// 总经理
						konkaOrderInfo.getMap().put("audit_role_id", role.getRole_id());// 当前用户的角色id
						konkaOrderInfo.getMap().put("audit_dept_id", user.getDept_id());// 当前用户所在部门id
					} else if (null != andit_level_now_users && andit_level_now_users.length > 1) {
						for (int i = 0; i < andit_level_now_users.length; i++) {
							for (int j = 0; j < i; j++) {
								if (!andit_level_now_users[i].equals(andit_level_now_users[j])) {
									konkaOrderInfo.getMap().put("audit_role_id", role.getRole_id());// 当前用户的id
									konkaOrderInfo.getMap().put("audit_dept_id", user.getDept_id());// 当前用户的id
								}
							}
						}
					}

					if (null != andit_level_now_users && andit_level_now_users.length > 0) {
						konkaOrderInfo.getMap().put("audit_state_levels", andit_level_now_users);// 当前用户角色所在等级
					}
					konkaOrderInfo.getMap().put("fgs_dept_id", fgsDept.getDept_id());// 当前用户所属分公司
					// if(null != andit_level_now_users &&
					// andit_level_now_users.length > 1){//当前角色有一个以上等级
					// konkaOrderInfo.getMap().put("audit_state_level_gt_1",
					// andit_level_now_user);// 当前用户最高等级
					// }

				}
			}
		}
		// // 待审核搜索 加角色状态条件 结束

		// 登录的用户取的订单信息分两个部分
		// 1、根据user_id取的订单===该用户为【业务员或充当为业务员】类型的用户，有管理的网点===
		// 2、根据用户角色对应的审核级别为1和大于1的
		// . ├-->级别为1，就是业务员，只取第一部分数据
		// . └-->级别大于1取前一级（该角色有可能多个等级==注意判断==）审核通过的数据
		// 两部分数据的总和，为该角色看到的订单

		List<KonkaOrderInfo> konkaOrderInfoList = new ArrayList<KonkaOrderInfo>();

		// 根据登录的用户角色、部门和流程类型确定审核级别
		Integer audit_level = -1;
		Integer is_update_authority = -1;

		if (StringUtils.equals("1", search_type)) {// 待审核加角色状态条件
			konkaOrderInfo.getMap().put("action_audit_order", "true");// 待审核订单

			// ----------------------------- A ----------------------------- //
			if (StringUtils.isBlank(process_state)) {// 显示全部流程类型 ：普通订单和特殊订单
				// 根据用户找 所属分公司部门确定订单审核流程定义list
				KonkaOrderAuditProcess konkaOrderAuditProcess = new KonkaOrderAuditProcess();
				konkaOrderAuditProcess.setIs_del(0);
				konkaOrderAuditProcess.setAdd_dept_id(fgsDept.getDept_id());// 分公司的id
				List<KonkaOrderAuditProcess> konkaOrderAuditProcessList = getFacade()
						.getKonkaOrderAuditProcessService().getKonkaOrderAuditProcessList(konkaOrderAuditProcess);
				// 循环list取出，不同流程类型中的订单list，然后合并审核级别
				if (null == konkaOrderAuditProcessList || konkaOrderAuditProcessList.size() == 0) {
					// 该公司没有定义审核流程
					msg = "您所在分公司没有定义订单流程，请通知分公司管理员，添加订单流程定义!";
					renderJavaScript(response, "alert('" + msg + "');history.back();");
					return null;
				}

				List<KonkaOrderAuditProcessNode> nodeList = new ArrayList<KonkaOrderAuditProcessNode>();
				for (int i = 0; i < konkaOrderAuditProcessList.size(); i++) {
					// 查询设置的每个订单流程（特殊、一般）中当前角色所对应的审批节点（位置）
					KonkaOrderAuditProcessNode konkaOrderAuditProcessNode = new KonkaOrderAuditProcessNode();
					konkaOrderAuditProcessNode.setIs_del(0);
					konkaOrderAuditProcessNode.setRole_id(new Long(role.getRole_id()));// 角色，一个节点只能有一个角色
					konkaOrderAuditProcessNode.setAdd_dept_id(fgsDept.getDept_id());// 分公司部门Id
					konkaOrderAuditProcessNode.setAudit_proces_id(konkaOrderAuditProcessList.get(i).getId());
					konkaOrderAuditProcessNode = getFacade().getKonkaOrderAuditProcessNodeService()
							.getKonkaOrderAuditProcessNode(konkaOrderAuditProcessNode);
					if (null != konkaOrderAuditProcessNode) {
						nodeList.add(konkaOrderAuditProcessNode);
					}
				}
				Integer[] nodeAuditLevels = new Integer[nodeList.size()];
				for (int i = 0; i < nodeList.size(); i++) {// 取等级 放到数组中,供取订单使用
					nodeAuditLevels[i] = nodeList.get(i).getAudit_level();
				}

				if (nodeAuditLevels.length == 1) {
					// 数组中只有一个等级时，表示该角色只在一个流程里【目前仅仅是特殊流程里面的总经理】，
					// 取出该流程类型
					KonkaOrderAuditProcessNode koapnForList = new KonkaOrderAuditProcessNode();
					koapnForList.setIs_del(0);
					koapnForList.setAudit_level(nodeAuditLevels[0]);
					koapnForList.setAdd_dept_id(fgsDept.getDept_id());
					koapnForList.setRole_id(role.getRole_id());
					koapnForList = getFacade().getKonkaOrderAuditProcessNodeService().getKonkaOrderAuditProcessNode(
							koapnForList);
					if (null != koapnForList) {// 根据角色反取流程
						KonkaOrderAuditProcess koapForList = new KonkaOrderAuditProcess();
						koapForList.setIs_del(0);
						koapForList.setId(koapnForList.getAudit_proces_id());
						koapForList.setAdd_dept_id(fgsDept.getDept_id());
						koapForList = getFacade().getKonkaOrderAuditProcessService().getKonkaOrderAuditProcess(
								koapForList);
						if (null != koapForList) {// 确定流程类型
							konkaOrderInfo.getMap().put("process_state", koapForList.getProcess_type());
						}
					}
					if (nodeAuditLevels[0] == 1) {// 第一等级==已经加上限制【两个流程中的第一等级都为业务员，因此不可能存在此情况】=====
						konkaOrderInfo.getMap().put("ywy_id", user.getId());// 业务员用户id
						konkaOrderInfo.getMap().put("audit_level_eq_1", "true");// 业务员审核
					} else {// 大于一的等级======【目前仅仅是特殊流程里面的总经理】
						konkaOrderInfo.getMap().put("ywy_id", user.getId());// 该用户充当的业务员
						konkaOrderInfo.getMap().put("audit_level_before", (nodeAuditLevels[0] - 1));// 审核级别前一级别
						konkaOrderInfo.getMap().put("fgs_dept_id", fgsDept.getDept_id());// 该分公司下面的
						konkaOrderInfo.getMap().put("audit_level_gt_1", "true");// 前一级别审核通过的
					}
				} else {
					// 有一个以上等级（即该用户所属角色在多个流程里）或者不在流程中
					if (!ArrayUtils.contains(nodeAuditLevels, 1)) {// 不存在第一等级【除了业务员和总经理外的其他角色】

						// List<Long> orderIdsList = new ArrayList<Long>();//
						// 订单id列表
						// for (int j = 0; j < nodeAuditLevels.length; j++) {
						// nodeAuditLevelslow[j] = nodeAuditLevels[j] - 1;
						// /*******************************
						// 取当前级别对应的前一级别审核通过，且对应的流程后一级节点为当前等级的订单 start
						// *************************************/
						// // 为了区别【总经理】后面的【财务人员==】，根据等级取数据时的问题
						// KonkaOrderInfoAudit audit = new
						// KonkaOrderInfoAudit();
						// audit.setAudit_level(nodeAuditLevelslow[j]);// 前一级别
						// audit.setAudit_result(1);// 审核通过
						// // 有多个分公司的订单===加入分公司部门id过滤
						// audit.getMap().put("fgs_dept_id",
						// fgsDept.getDept_id());
						// List<KonkaOrderInfoAudit> konkaOrderInfoAuditList =
						// getFacade()
						// .getKonkaOrderInfoAuditService().getKonkaOrderInfoAuditList(audit);
						// if (null == konkaOrderInfoAuditList) {
						// break;
						// }
						//
						// for (KonkaOrderInfoAudit cur :
						// konkaOrderInfoAuditList) {// 根据订单审核信息取订单Id并放入orderIds
						// // 根据id取出订单
						// KonkaOrderInfo k = new KonkaOrderInfo();
						// logger.info("=========id========{}:" +
						// cur.getLink_id());
						// k.setId(cur.getLink_id());
						// k =
						// getFacade().getKonkaOrderInfoService().getKonkaOrderInfo(k);
						//
						// if (null == k) {
						// break;
						// }
						//
						// // 根据订单类型
						// // 获取该类型流程的后一级节点信息
						// KonkaOrderAuditProcess koapForLevel = new
						// KonkaOrderAuditProcess();
						// koapForLevel.setIs_del(0);
						// koapForLevel.setProcess_type(k.getProcess_state());
						// koapForLevel.setAdd_dept_id(fgsDept.getDept_id());//
						// 添加分公司部门id过滤===
						// koapForLevel =
						// getFacade().getKonkaOrderAuditProcessService()
						// .getKonkaOrderAuditProcess(koapForLevel);
						// if (null != koapForLevel) {// 根据审核流程取当前节点角色
						// KonkaOrderAuditProcessNode _node = new
						// KonkaOrderAuditProcessNode();
						// _node.setIs_del(0);
						// _node.setAudit_proces_id(koapForLevel.getId());//
						// 流程id
						// _node.setAdd_dept_id(fgsDept.getDept_id());// 分公司部门Id
						// _node.setAudit_level(nodeAuditLevels[j]);// 当前等级
						//
						// List<KonkaOrderAuditProcessNode> _nodeList;
						// _nodeList =
						// getFacade().getKonkaOrderAuditProcessNodeService()
						// .getKonkaOrderAuditProcessNodeList(_node);
						// if (null != _nodeList && _nodeList.size() > 0) {
						// for (KonkaOrderAuditProcessNode node : _nodeList) {
						// if (node.getRole_id().intValue() ==
						// role.getRole_id().intValue()) {// 后一级节点角色相等放入订单id列表中
						// orderIdsList.add(cur.getLink_id());
						// }
						// }
						// }
						// }
						// }
						// /*******************************
						// 取当前级别对应的前一级别审核通过，且对应的流程后一级节点为当前等级的订单 end
						// *************************************/
						// }
						// if (null != orderIdsList && orderIdsList.size() > 0)
						// {
						// int[] orderIds = new int[orderIdsList.size()];
						// for (int p = 0; p < orderIdsList.size(); p++) {
						// orderIds[p] = orderIdsList.get(p).intValue();
						// }
						// int gourpCount = 1000;
						// Integer[][] orderIdsTwoArry = new Integer[new
						// Integer(orderIds.length / gourpCount) +
						// 1][gourpCount];
						// for (int i = 0; i < new Integer(orderIds.length /
						// gourpCount) + 1; i++) {
						// int cols = 0;
						// for (int j = 0; j < gourpCount; j++) {
						// if (i * gourpCount + j < orderIds.length) {
						// cols++;
						// }
						// }
						// orderIdsTwoArry[i] = new Integer[cols];
						// for (int j = 0; j < orderIdsTwoArry[i].length; j++) {
						// orderIdsTwoArry[i][j] = orderIds[i * gourpCount + j];
						// }
						// }
						// StringBuffer sbSql = new StringBuffer();
						// for (int i = 0; i < orderIdsTwoArry.length; i++) {
						// if (i == orderIdsTwoArry.length - 1) {
						// sbSql.append("t.link_id in(").append(StringUtils.join(orderIdsTwoArry[i],
						// ","))
						// .append(")");
						// } else {
						// sbSql.append("t.link_id in(").append(StringUtils.join(orderIdsTwoArry[i],
						// ","))
						// .append(") or ");
						//
						// }
						// }
						//
						// konkaOrderInfo.getMap().put("sqlStr",
						// sbSql.toString());
						//
						// }

						/**
						 * =====此处，角色不在流程中时，也要进行处理==【nodeAuditLevels.length ==0】
						 * 会跳过下面部分代码=============
						 */
						if (ArrayUtils.isNotEmpty(nodeAuditLevels)) {
							Integer[] lastNodeArr = new Integer[nodeAuditLevels.length];
							for (int j = 0; j < nodeAuditLevels.length; j++) {
								lastNodeArr[j] = nodeAuditLevels[j] - 1;
							}

							konkaOrderInfo.getMap().put("filter_ids", "true");
							konkaOrderInfo.getMap().put("filter_ids_fgs_dept_id", fgsDept.getDept_id());
							konkaOrderInfo.getMap().put("filter_ids_audit_result", 1);
							konkaOrderInfo.getMap().put("filter_ids_audit_level_array", lastNodeArr);

							konkaOrderInfo.getMap().put("before_audit_levels", lastNodeArr);// 审核级别前一级别
						}

						/**
						 * =====此处，角色不在流程中时，也要进行处理==【nodeAuditLevels.length
						 * ==0】跳到此处 ==【nodeAuditLevelslow值为空】==
						 * 此时取的数据仅仅是根据ywy_id取的数据===========
						 */
						// PeProdUser u =
						// super.getYwyOfCustomerByCustomerId(cust_id);
						konkaOrderInfo.getMap().put("ywy_id", user.getId());// 该用户充当的业务员
						konkaOrderInfo.getMap().put("fgs_dept_id", fgsDept.getDept_id());// 该部门下面的
						konkaOrderInfo.getMap().put("audit_level_gt_1", "true");// 前一级别审核通过的
						konkaOrderInfo.getMap().put("is_all", "true");// 有两种级别且都为第一级别以上

					} else {// 等级中存在第一等级（流程第一步【仅限业务员】）
						int flag_level = 0;
						for (int q = 0; q < nodeAuditLevels.length; q++) {// 有等级不为1的情况
							if (nodeAuditLevels[q] != 1) {
								flag_level = 1;
							}
						}
						if (flag_level == 0) {// 等级均为1【真正的业务员】===有可能是不同部门的==只取业务员user_id的那部分数据
							konkaOrderInfo.getMap().put("ywy_id", user.getId());// 业务员用户id
							konkaOrderInfo.getMap().put("audit_level_eq_1", "true");// 业务员审核
						} else {// 等级存在1且有其他等级（此情况业务上不存在暂作异常处理）
							msg = "请联系您所在的分公司管理员，检查流程定义!";
							request.setAttribute("msg", msg);
							super.renderJavaScript(response, "alert('" + msg + "');history.back();");
							return null;
						}
					}
				}

				// 执行查询
				Long recordCount = getFacade().getKonkaOrderInfoService().getKonkaOrderInfoCount(konkaOrderInfo);
				pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
				konkaOrderInfo.getRow().setFirst(pager.getFirstRow());
				konkaOrderInfo.getRow().setCount(pager.getRowCount());
				konkaOrderInfoList = getFacade().getKonkaOrderInfoService().getKonkaOrderInfoPaginatedListWithShopName(
						konkaOrderInfo);

				// 循环取审核级别，用于页面上【审核状态】的判断
				if (null != konkaOrderInfoList && konkaOrderInfoList.size() > 0) {
					for (KonkaOrderInfo koi : konkaOrderInfoList) {
						Integer process_state_order = null;// 该订单的流程状态
						Integer audit_level_order = null;// 登录用户所在的 该类型订单 的审核级别
						koi.getMap()
								.put("max_audit_level",
										super.getKonkaOrderAuditProcessNodeMaxLevel(fgsDept.getDept_id(),
												new Long(koi.getId())));// 该订单对应的最高审核级别,判断审核状态
						KonkaOrderAuditProcess koap = new KonkaOrderAuditProcess();
						koap.setIs_del(0);
						koap.setProcess_type(koi.getProcess_state());
						koap.setAdd_dept_id(fgsDept.getDept_id());
						koap = getFacade().getKonkaOrderAuditProcessService().getKonkaOrderAuditProcess(koap);
						if (koi.getProcess_state() == 1 // 普通流程
								&& role.getRole_id() == 34L// 分公司总经理
						) {
							// 总经理充当业务员审核普通流程订单==查询特殊流程中的下一个节点
							// is_update_authority = 0;
							KonkaOrderAuditProcess process = new KonkaOrderAuditProcess();
							process.setIs_del(0);
							process.setProcess_type(2);
							process.setAdd_dept_id(fgsDept.getDept_id());
							process = getFacade().getKonkaOrderAuditProcessService().getKonkaOrderAuditProcess(process);
							if (null != process) {
								KonkaOrderAuditProcessNode node = new KonkaOrderAuditProcessNode();
								node.setIs_del(0);
								node.setAudit_proces_id(process.getId());
								node.setAdd_dept_id(fgsDept.getDept_id());
								node.setRole_id(role.getRole_id());
								node = getFacade().getKonkaOrderAuditProcessNodeService()
										.getKonkaOrderAuditProcessNode(node);

								if (null != node) {
									request.setAttribute("fgs_managerer", true);
									is_update_authority = node.getIs_update_authority();
									audit_level_order = node.getAudit_level();
								}
							}
						}
						if (null != koap) {
							KonkaOrderAuditProcessNode koapn = new KonkaOrderAuditProcessNode();
							koapn.setIs_del(0);
							koapn.setAudit_proces_id(koap.getId());
							koapn.setAdd_dept_id(fgsDept.getDept_id());
							koapn.setRole_id(role.getRole_id());
							koapn = getFacade().getKonkaOrderAuditProcessNodeService().getKonkaOrderAuditProcessNode(
									koapn);

							if (null != koapn) {
								audit_level_order = koapn.getAudit_level();
								is_update_authority = koapn.getIs_update_authority();
							} else {
								request.setAttribute("not_in_process", true);
								// 不在流程中===【非业务员】===业务员肯定在流程中的第一步 ==2012-2-02
								// 非业务员,审核等级为指定角色（暂定销管部的审核等级-1）
								KonkaDept konkaDept = new KonkaDept();
								konkaDept.setDept_id(user.getDept_id());
								konkaDept = getFacade().getKonkaDeptService().getKonkaDept(konkaDept);
								if (null == konkaDept) {
									msg = "对不起，找不到您所属的部门，您不能审核订单!";
									renderJavaScript(response, "alert('" + msg + "');history.back();");
									return null;
								}

								KonkaOrderAuditProcessNode node = new KonkaOrderAuditProcessNode();
								node.setIs_del(0);
								node.setRole_id(36L);// 定义一个常量==================
								node.setAudit_proces_id(koap.getId());
								node = getFacade().getKonkaOrderAuditProcessNodeService()
										.getKonkaOrderAuditProcessNode(node);
								if (null == node) {
									msg = "对不起，您不在流程定义中，订单审核后指定的人员不存在,请联系管理员检查流程定义!";
									renderJavaScript(response, "alert('" + msg + "');history.back();");
									return null;
								}

								audit_level_order = node.getAudit_level() - 1;
								is_update_authority = node.getIs_update_authority();
							}
							process_state_order = koi.getProcess_state();
						}

						koi.getMap().put("audit_level", audit_level_order);
						koi.getMap().put("is_update_authority", is_update_authority);
						koi.getMap().put("process_state", process_state_order);
					}
				}
			} else {// 根据选择的条件显示
				// 订单审核流程定义
				KonkaOrderAuditProcess konkaOrderAuditProcess = new KonkaOrderAuditProcess();
				konkaOrderAuditProcess.setIs_del(0);
				konkaOrderAuditProcess.setAdd_dept_id(fgsDept.getDept_id());// 分公司的id
				konkaOrderAuditProcess.setProcess_type(new Integer(process_state));
				konkaOrderAuditProcess = getFacade().getKonkaOrderAuditProcessService().getKonkaOrderAuditProcess(
						konkaOrderAuditProcess);
				if (null != konkaOrderAuditProcess) {
					// 订单审核流程节点
					KonkaOrderAuditProcessNode konkaOrderAuditProcessNode = new KonkaOrderAuditProcessNode();
					konkaOrderAuditProcessNode.setIs_del(0);
					konkaOrderAuditProcessNode.setAudit_proces_id(konkaOrderAuditProcess.getId());
					konkaOrderAuditProcessNode.setRole_id(role.getRole_id());// 角色
					konkaOrderAuditProcessNode = getFacade().getKonkaOrderAuditProcessNodeService()
							.getKonkaOrderAuditProcessNode(konkaOrderAuditProcessNode);
					if (null != konkaOrderAuditProcessNode) {
						audit_level = konkaOrderAuditProcessNode.getAudit_level();// 审核级别
						is_update_authority = konkaOrderAuditProcessNode.getIs_update_authority();// 修改的权限
					} else {
						// 该角色不在流程中，特殊处理
						// 该角色审核的订单，肯定是他直接管理的网点所提交的
						audit_level = -1;// 默认值也为-1
						is_update_authority = 1;// 默认有修改权限
					}
				} else {
					// 该分公司无该类型审核流程
					msg = "对不起，您所在分公司的该审核流程不存在，请联系分公司管理员添加该类型的审核流程定义!";
					request.setAttribute("msg", msg);
					pager.init(0L, pager.getPageSize(), pager.getRequestPage());
					return mapping.findForward("list");
				}

				konkaOrderInfo.setProcess_state(new Integer(process_state));// 流程类型
				konkaOrderInfo.getMap().put("process_state_select", process_state);// 区别上一步审核通过的订单
																					// 和
																					// 直接管理网点提交的订单
				// 根据审核级别，来取订单信息，显示列表
				if (audit_level == 1) {
					konkaOrderInfo.getMap().put("ywy_id", user.getId());// 业务员用户id
					konkaOrderInfo.getMap().put("audit_level_eq_1", "true");// 业务员审核
				} else if (audit_level > 1) {
					konkaOrderInfo.getMap().put("ywy_id", user.getId());// 该用户充当的业务员
					konkaOrderInfo.getMap().put("audit_level_before", (audit_level - 1));// 审核级别前一级别
					konkaOrderInfo.getMap().put("fgs_dept_id", fgsDept.getDept_id());// 该部门下面的
					konkaOrderInfo.getMap().put("audit_level_gt_1", "true");// 前一级别审核通过的
				} else if (audit_level == -1) {
					// 不在流程中查询订单时,同audit_level == 1时的情况，可以合并
					konkaOrderInfo.getMap().put("ywy_id", user.getId());
					konkaOrderInfo.getMap().put("audit_level_eq_1", "true");
				}
				Long recordCount = getFacade().getKonkaOrderInfoService().getKonkaOrderInfoCount(konkaOrderInfo);
				pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
				konkaOrderInfo.getRow().setFirst(pager.getFirstRow());
				konkaOrderInfo.getRow().setCount(pager.getRowCount());
				konkaOrderInfoList = super.getFacade().getKonkaOrderInfoService()
						.getKonkaOrderInfoPaginatedListWithShopName(konkaOrderInfo);

				// 循环取审核级别
				if (null != konkaOrderInfoList && konkaOrderInfoList.size() > 0) {
					for (KonkaOrderInfo koi : konkaOrderInfoList) {
						koi.getMap()
								.put("max_audit_level",
										super.getKonkaOrderAuditProcessNodeMaxLevel(fgsDept.getDept_id(),
												new Long(koi.getId())));// 该订单对应的最高审核级别,判断审核状态
						koi.getMap().put("audit_level", audit_level);
						koi.getMap().put("process_state", process_state);
						koi.getMap().put("is_update_authority", is_update_authority);
					}
				}
			}

			// ----------------------------- A END.
			// ----------------------------- //

		} else {// 已审核 根据用户ID 查看审核记录中该用户审核过的订单
			if (StringUtils.equals("2", search_type)) {//
				// 根据用户取审核完成的 订单审核记录列表 进而取得订单记录列表 当前
				konkaOrderInfo.getMap().put("history_audit_order", "true");
				konkaOrderInfo.getMap().put("login_user_Id", user.getId());

				Long recordCount = getFacade().getKonkaOrderInfoService().getKonkaOrderInfoCount(konkaOrderInfo);
				pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
				konkaOrderInfo.getRow().setFirst(pager.getFirstRow());
				konkaOrderInfo.getRow().setCount(pager.getRowCount());
				konkaOrderInfoList = super.getFacade().getKonkaOrderInfoService()
						.getKonkaOrderInfoPaginatedListWithShopName(konkaOrderInfo);
				if (null != konkaOrderInfoList && konkaOrderInfoList.size() > 0) {// 判断订单审核状态
					// 判断是否,最后一个审核节点--start--
					KonkaOrderAuditProcess konkaOrderAuditProcess = super.getProcessByFgsDeptIdAndType(
							fgsDept.getDept_id(), konkaOrderInfoList.get(0).getProcess_state());
					KonkaOrderAuditProcessNode konkaOrderAuditProcessNode = super.getProcessNodeByProcessIdAndRoleId(
							konkaOrderAuditProcess.getId(), role.getRole_id());
					if (null != konkaOrderAuditProcessNode) {
						// 该角色在流程中
						Integer now_user_level = konkaOrderAuditProcessNode.getAudit_level();// 当前用户的审核等级
						Long process_max_level = super.getKonkaOrderAuditProcessNodeMaxLevel(fgsDept.getDept_id(),
								new Long(konkaOrderInfoList.get(0).getId()));// 流程最高级别
						// 判断是否,最后一个审核节点--end--

						if (now_user_level.intValue() != process_max_level.intValue()) {// 不是最高等级
							for (KonkaOrderInfo koi : konkaOrderInfoList) {// 根据用户ID和订单Id
																			// 取审核结果
								KonkaOrderInfoAudit koa = new KonkaOrderInfoAudit();
								koa = super.getKonkaOrderInfoAuditByOrderIdAndUserId(user.getId(), koi.getId());
								koi.getMap().put("audit_result", koa.getAudit_result());
							}
						}
					} else {// 不是最高等级
						for (KonkaOrderInfo koi : konkaOrderInfoList) {// 根据用户ID和订单Id
																		// 取审核结果
							KonkaOrderInfoAudit koa = new KonkaOrderInfoAudit();
							koa = super.getKonkaOrderInfoAuditByOrderIdAndUserId(user.getId(), koi.getId());
							koi.getMap().put("audit_result", koa.getAudit_result());
						}

					}
				}
			}
		}

		if (null != konkaOrderInfoList && konkaOrderInfoList.size() > 0) {// 判断操作【审核】按钮
			// 判断是否,最后一个审核节点--start--
			KonkaOrderAuditProcess konkaOrderAuditProcess = super.getProcessByFgsDeptIdAndType(fgsDept.getDept_id(),
					konkaOrderInfoList.get(0).getProcess_state());
			KonkaOrderAuditProcessNode konkaOrderAuditProcessNode = super.getProcessNodeByProcessIdAndRoleId(
					konkaOrderAuditProcess.getId(), role.getRole_id());
			if (null != konkaOrderAuditProcessNode) {
				// 该角色在流程中
				Integer now_user_level = konkaOrderAuditProcessNode.getAudit_level();// 当前用户的审核等级
				Long process_max_level = super.getKonkaOrderAuditProcessNodeMaxLevel(fgsDept.getDept_id(), new Long(
						konkaOrderInfoList.get(0).getId()));// 流程最高级别
				if (now_user_level.intValue() == process_max_level.intValue()) {
					request.setAttribute("is_last_audit", "true");
				}
			}

		}

		request.setAttribute("entityList", konkaOrderInfoList);
		dynaBean.set("start_date", start_date);
		dynaBean.set("end_date", end_date);
		dynaBean.set("search_type", search_type);
		dynaBean.set("search_type_default", search_type);// 默认值
		dynaBean.set("audit_state_order", audit_state);
		return mapping.findForward("list");
	}

	public ActionForward audit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (!isSessionLoginForKonkaJxc(request)) {
			return forwardNoSessionForKonkaJxc(request, response);
		}
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;

		PeProdUser user = super.getSessionUserInfo(request);
		String audit_level = (String) dynaBean.get("audit_level");
		String process_state = (String) dynaBean.get("process_state");
		String is_update_authority = (String) dynaBean.get("is_update_authority");
		String id = (String) dynaBean.get("id");
		if (null == user) {
			return null;
		}
		PeRoleInfo role = super.getPeRoleInfoByUserId(user.getId());
		if (GenericValidator.isLong(id)) {
			// 全部康佳产品
			PePdModel pePdModel = new PePdModel();
			pePdModel.setBrand_id(Constants.KONKA_BRAND_ID);
			pePdModel.setIs_del(0);
			List<PePdModel> pdList = getFacade().getPePdModelService().getPePdModelWithClsNameBrandNameList(pePdModel);

			request.setAttribute("pdList", pdList);

			// 订单基本信息
			KonkaOrderInfo konkaOrderInfo = new KonkaOrderInfo();
			konkaOrderInfo.setId(new Long(id));
			konkaOrderInfo = getFacade().getKonkaOrderInfoService().getKonkaOrderInfo(konkaOrderInfo);

			// 取订单网点名称
			// MmtEntpShop mmtEntpShop = new MmtEntpShop();
			// mmtEntpShop.setShop_id(konkaOrderInfo.getShop_id());
			// mmtEntpShop =
			// getFacade().getMmtEntpShopService().getMmtEntpShop(mmtEntpShop);
			// if (mmtEntpShop != null) {
			// konkaOrderInfo.getMap().put("shop_name",
			// mmtEntpShop.getShop_name());
			// }

			List<KonkaOrderInfoAudit> konkaOrderInfoAuditList = super.getKonkaOrderInfoAuditWithRoleInfoList(new Long(
					id));
			if (null != konkaOrderInfoAuditList && konkaOrderInfoAuditList.size() > 0) {
				konkaOrderInfo.setKonkaOrderInfoAuditList(konkaOrderInfoAuditList);
			}

			super.copyProperties(form, konkaOrderInfo);
			if (null != konkaOrderInfo) {
				// 订单详细信息
				KonkaOrderInfoDetails konkaOrderInfoDetails = new KonkaOrderInfoDetails();
				konkaOrderInfoDetails.setOrder_id(konkaOrderInfo.getId());
				List<KonkaOrderInfoDetails> konkaOrderInfoDetailsList = getFacade().getKonkaOrderInfoDetailsService()
						.getKonkaOrderInfoDetailsList(konkaOrderInfoDetails);

				// 判断是否,最后一个审核节点--start--
				KonkaDept fgsDept = super.getKonkaDeptByUserDeptId(user.getDept_id());
				KonkaOrderAuditProcess konkaOrderAuditProcess = super.getProcessByFgsDeptIdAndType(
						fgsDept.getDept_id(), konkaOrderInfo.getProcess_state());
				KonkaOrderAuditProcessNode konkaOrderAuditProcessNode = super.getProcessNodeByProcessIdAndRoleId(
						konkaOrderAuditProcess.getId(), role.getRole_id());
				if (null != konkaOrderAuditProcessNode) {
					Integer now_user_level = konkaOrderAuditProcessNode.getAudit_level();// 当前用户的审核等级

					Long process_max_level = super.getKonkaOrderAuditProcessNodeMaxLevel(fgsDept.getDept_id(),
							new Long(id));// 流程最高级别
					if (now_user_level.intValue() == process_max_level.intValue()) {
						// 已审核数
						for (int i = 0; i < konkaOrderInfoDetailsList.size(); i++) {
							List<KonkaOrderInfoDetailsAudit> konkaOrderInfoDetailsAuditList_audit = super
									.getKonkaOrderInfoDetailsAuditList(new Long(id), "0", konkaOrderInfoDetailsList
											.get(i).getPd_id().toString());
							int already_audit_good_count = 0;
							for (int j = 0; j < konkaOrderInfoDetailsAuditList_audit.size(); j++) {
								already_audit_good_count += konkaOrderInfoDetailsAuditList_audit.get(j)
										.getAudit_good_count().intValue();
							}
							konkaOrderInfoDetailsList.get(i).getMap()
									.put("already_audit_good_count", already_audit_good_count);
						}
						request.setAttribute("is_last_audit", "true");
					}
				}
				// 判断是否,最后一个审核节点--end--
				request.setAttribute("konkaOrderInfoDetailsList", konkaOrderInfoDetailsList);

				KonkaOrderAuditProcess konkaOrderAuditProcess_ts = new KonkaOrderAuditProcess();
				konkaOrderAuditProcess_ts = super.getSpecialProcessByFgsDeptId(fgsDept.getDept_id());
				if (null != konkaOrderAuditProcess_ts) {// 特殊流程存在
					KonkaOrderAuditProcessNode konkaOrderAuditProcessNode_ts = super
							.getProcessNodeByProcessIdAndRoleId(konkaOrderAuditProcess_ts.getId(), role.getRole_id());
					if (null != konkaOrderAuditProcessNode_ts) {// 在流程 根据级别修改类型
						// 总经理在流程中的级别
						KonkaOrderAuditProcessNode konkaOrderAuditProcessNode_zjl = super
								.getProcessNodeByProcessIdAndRoleId(konkaOrderAuditProcess_ts.getId(), new Long(
										Constants.ROLE_ID_FGS_ZJL));
						if (konkaOrderAuditProcessNode_ts.getAudit_level().intValue() <= konkaOrderAuditProcessNode_zjl
								.getAudit_level() && role.getRole_id().intValue() != Constants.ROLE_ID_FGS_ZJL) {
							request.setAttribute("show_tips", "true");// 提示产品，低于现价
						}

					} else {// 不在流程修改
						request.setAttribute("show_tips", "true");// 提示产品，低于现价
					}
				}
			}

			dynaBean.set("process_state", process_state);
			dynaBean.set("audit_level", audit_level);
			dynaBean.set("is_update_authority", is_update_authority);
			konkaOrderInfo.setQueryString(super.serialize(request, "id", "mod_id"));
			super.copyProperties(form, konkaOrderInfo);
			return mapping.findForward("input");
		} else {
			this.saveError(request, "errors.long", new String[] { id });
			return mapping.findForward("list");
		}
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (!isSessionLoginForKonkaJxc(request)) {
			return forwardNoSessionForKonkaJxc(request, response);
		}
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		// 审核===添加审核记录、修改订单明细、修改订单（同时根据型号和价格，修改流程类型）
		PeProdUser user = super.getSessionUserInfo(request);
		String process_state = (String) dynaBean.get("process_state");
		String id = (String) dynaBean.get("id");
		String trade_index = (String) dynaBean.get("trade_index");
		String mod_id = (String) dynaBean.get("mod_id");
		// String audit_level = (String) dynaBean.get("audit_level");
		String audit_result = (String) dynaBean.get("audit_result");// 审核结果
		String audit_comment = (String) dynaBean.get("audit_comment");// 审核评语
		String[] good_counts = request.getParameterValues("good_count");// 数量
		String[] good_prices = request.getParameterValues("good_price");// 单价
		String[] audit_good_count = request.getParameterValues("this_audit_good_count");// 本次审核数
		String[] audit_good_price = request.getParameterValues("this_audit_good_price");// 本次审核单价
		// String[] order_details_ids =
		// request.getParameterValues("order_details_ids");// 订单详细Ids
		String[] pd_ids = request.getParameterValues("pd_ids");// 订单详细Ids
		String[] good_discounts = request.getParameterValues("good_discount");// 折扣
		String[] good_units = request.getParameterValues("good_unit");// 单位
		String[] pd_remarks = request.getParameterValues("pd_remark");// 备注

		Calendar c = Calendar.getInstance();// 当前时间
		int year = Integer.valueOf(c.get(Calendar.YEAR));// 订单年份
		int month = Integer.valueOf(Calendar.MONTH) - 1;// 订单月份

		if (null == user) {
			return null;
		}
		PeRoleInfo role = super.getPeRoleInfoByUserId(user.getId());

		// 根据用户部门id找 所属分公司
		KonkaDept fgsDept = super.getKonkaDeptByUserDeptId(user.getDept_id());
		if (null == fgsDept) {// 此处部门均为分公司及分公司上级部门
			String msg = "对不起，您没有权限审核订单信息!";
			renderJavaScript(response, "alert('" + msg + "');history.back();");
			return null;
		}
		Long process_max_level = super.getKonkaOrderAuditProcessNodeMaxLevel(fgsDept.getDept_id(), new Long(id));// 流程最高级别
		/**
		 * ======确定【订单审核记录表中】保存的审核等级,以便后一步审核节点能查找到该订单（通过该角色审核等级-1，即上一步审核通过的订单）==
		 * ==========start=====
		 */
		// 2011-02-02
		// 根据流程类型、角色和分公司确定该角色的审核等级（有一个【等于1或大于1】或者没有【不在流程中】）
		// -->等级=1【肯定是业务员】
		// 取用户所在部门--找出该部门角色所在流程的审核等级【注意判断部门角色有无在流程中】
		// -->等级>1【非业务员】
		// 审核记录的【审核等级】为该角色对应的审核等级
		// -->【不在流程里】要特殊处理==目前业务员都在流程中==
		Integer audit_level = null;
		if (StringUtils.isNotBlank(process_state)) {
			KonkaOrderAuditProcess konkaOrderAuditProcess = new KonkaOrderAuditProcess();
			konkaOrderAuditProcess.setIs_del(0);
			konkaOrderAuditProcess.setAdd_dept_id(fgsDept.getDept_id());
			if (Integer.valueOf(process_state) == 1 && role.getRole_id() == 34L) {// 总经理充当业务员审核普通流程订单==查询特殊流程中的下一个节点
				konkaOrderAuditProcess.setProcess_type(new Integer(2));
				konkaOrderAuditProcess = getFacade().getKonkaOrderAuditProcessService().getKonkaOrderAuditProcess(
						konkaOrderAuditProcess);

				if (null == konkaOrderAuditProcess) {
					String msg = "对不起，该类型流程不存在，请联系分公司管理员!";
					renderJavaScript(response, "alert('" + msg + "');history.back();");
					return null;
				}

				KonkaOrderAuditProcessNode konkaOrderAuditProcessNode = new KonkaOrderAuditProcessNode();
				konkaOrderAuditProcessNode.setIs_del(0);
				konkaOrderAuditProcessNode.setRole_id(34L); // 分公司总经理
				konkaOrderAuditProcessNode.setAudit_proces_id(konkaOrderAuditProcess.getId());
				konkaOrderAuditProcessNode = getFacade().getKonkaOrderAuditProcessNodeService()
						.getKonkaOrderAuditProcessNode(konkaOrderAuditProcessNode);
				audit_level = konkaOrderAuditProcessNode.getAudit_level() - 1;
			} else {
				konkaOrderAuditProcess.setProcess_type(new Integer(process_state));
				konkaOrderAuditProcess = getFacade().getKonkaOrderAuditProcessService().getKonkaOrderAuditProcess(
						konkaOrderAuditProcess);
				if (null == konkaOrderAuditProcess) {
					String msg = "对不起，该类型流程不存在，请联系分公司管理员!";
					renderJavaScript(response, "alert('" + msg + "');history.back();");
					return null;
				}

				KonkaOrderAuditProcessNode konkaOrderAuditProcessNode = new KonkaOrderAuditProcessNode();
				konkaOrderAuditProcessNode.setIs_del(0);
				konkaOrderAuditProcessNode.setRole_id(role.getRole_id());
				konkaOrderAuditProcessNode.setAudit_proces_id(konkaOrderAuditProcess.getId());
				konkaOrderAuditProcessNode = getFacade().getKonkaOrderAuditProcessNodeService()
						.getKonkaOrderAuditProcessNode(konkaOrderAuditProcessNode);
				if (null == konkaOrderAuditProcessNode) {
					// 不在流程定义中 直接跳到指定的角色审核（暂定为经管部）
					// 取指定角色的 对应流程类型 的审核等级 -1
					KonkaOrderAuditProcessNode node = new KonkaOrderAuditProcessNode();
					node.setIs_del(0);
					node.setRole_id(36L);// 定义一个常量==================
					node.setAudit_proces_id(konkaOrderAuditProcess.getId());
					node = getFacade().getKonkaOrderAuditProcessNodeService().getKonkaOrderAuditProcessNode(node);
					if (null == node) {
						String msg = "对不起，您不在流程定义中，订单审核后指定的人员不存在,请联系管理员检查流程定义!";
						renderJavaScript(response, "alert('" + msg + "');history.back();");
						return null;
					}
					audit_level = node.getAudit_level() - 1;
				} else {
					// 判断审核等级等于1还是大于1
					if (konkaOrderAuditProcessNode.getAudit_level() == 1) {
						// 1、业务员所在部门在流程中，【订单审核记录表中】该订单审核等级为所在部门角色的等级-1（因为不同部门的业务员审核后，下一步审核角色不同）
						// 2、业务员所在部门不在流程中，【订单审核记录表中】该订单审核等级为指定的角色（暂定为经管部）审核等级-1；
						KonkaDept konkaDept = new KonkaDept();
						konkaDept.setDept_id(user.getDept_id());
						konkaDept = getFacade().getKonkaDeptService().getKonkaDept(konkaDept);
						if (null == konkaDept) {
							String msg = "对不起，该业务员所在部门不存在,请联系管理员检查业务员所在部门!";
							renderJavaScript(response, "alert('" + msg + "');history.back();");
							return null;
						}

						// 取该部门对应的流程中的角色审核等级
						// 该角色 == 部门类型*10 （办事处管理员50，经营部管理员40,分公司管理员30）
						if (null != konkaDept.getDept_type()) {
							KonkaOrderAuditProcess processForMatchRole = new KonkaOrderAuditProcess();
							processForMatchRole.setIs_del(0);
							processForMatchRole.setAdd_dept_id(fgsDept.getDept_id());
							processForMatchRole.setProcess_type(new Integer(process_state));
							processForMatchRole = getFacade().getKonkaOrderAuditProcessService()
									.getKonkaOrderAuditProcess(processForMatchRole);
							if (null != konkaOrderAuditProcess) {
								KonkaOrderAuditProcessNode nodeForMatchRole = new KonkaOrderAuditProcessNode();
								nodeForMatchRole.setIs_del(0);
								nodeForMatchRole.setRole_id(new Long(konkaDept.getDept_type() * 10));// 部门类型*10
								nodeForMatchRole.setAudit_proces_id(konkaOrderAuditProcess.getId());
								nodeForMatchRole = getFacade().getKonkaOrderAuditProcessNodeService()
										.getKonkaOrderAuditProcessNode(nodeForMatchRole);
								if (null != nodeForMatchRole) {
									// 业务员部门角色在流程中
									audit_level = nodeForMatchRole.getAudit_level() - 1;
								} else {
									// 业务员部门不在流程中
									KonkaOrderAuditProcessNode konkaOrderAuditProcessNodeForAuditLevel = new KonkaOrderAuditProcessNode();
									konkaOrderAuditProcessNodeForAuditLevel.setIs_del(0);
									konkaOrderAuditProcessNodeForAuditLevel.setRole_id(36L);// 定义一个常量==================
									konkaOrderAuditProcessNodeForAuditLevel.setAudit_proces_id(konkaOrderAuditProcess
											.getId());
									konkaOrderAuditProcessNodeForAuditLevel = getFacade()
											.getKonkaOrderAuditProcessNodeService().getKonkaOrderAuditProcessNode(
													konkaOrderAuditProcessNodeForAuditLevel);
									if (null == konkaOrderAuditProcessNodeForAuditLevel) {
										String msg = "对不起，您不在流程定义中，订单审核后指定的人员不存在,请联系管理员检查流程定义!";
										renderJavaScript(response, "alert('" + msg + "');history.back();");
										return null;
									}
									audit_level = konkaOrderAuditProcessNodeForAuditLevel.getAudit_level() - 1;
								}
							}
						}
					} else {
						// 非业务员,审核等级为自己所在流程角色的审核等级
						audit_level = konkaOrderAuditProcessNode.getAudit_level();
					}
				}
			}
		}
		/** =====确定【订单审核记录表中】保存的审核等级============end====== */

		if (!GenericValidator.isLong(id)) {
			this.saveError(request, "errors.long", new String[] { id });
			return null;
		}

		KonkaDept konkaDept = super.getKonkaDeptById(user.getDept_id());
		String dept_name = "";// 审核人部门名称
		if (konkaDept != null && konkaDept.getDept_name() != null) {
			dept_name = konkaDept.getDept_name();
		}
		KonkaOrderInfo konkaOrderInfo = new KonkaOrderInfo();
		konkaOrderInfo.setId(new Long(id));
		konkaOrderInfo = getFacade().getKonkaOrderInfoService().getKonkaOrderInfo(konkaOrderInfo);
		if (null == konkaOrderInfo) {
			String msg = "对不起，您审核的订单信息不存在!";
			renderJavaScript(response, "alert('" + msg + "');history.back();");
			return null;
		}

		super.copyProperties(konkaOrderInfo, form);

		KonkaOrderInfoAudit konkaOrderInfoAudit = new KonkaOrderInfoAudit();
		if (null != audit_level) {// 添加审核记录
			konkaOrderInfoAudit.setLink_id(new Long(id));
			konkaOrderInfoAudit.setAudit_user_id(user.getId());
			konkaOrderInfoAudit.setAudit_user(user.getUser_name());
			konkaOrderInfoAudit.setAudit_dept_id(user.getDept_id());
			konkaOrderInfoAudit.setAudit_dept_name(dept_name);
			konkaOrderInfoAudit.setAudit_result(new Integer(audit_result));
			konkaOrderInfoAudit.setAudit_comment(audit_comment);
			konkaOrderInfoAudit.setAudit_level(audit_level);// 审核级别
		}
		/*********************** 修改订单审核状态 start ***************************************/
		if (StringUtils.equals("-1", audit_result)) {// 未通过
			konkaOrderInfo.setAudit_state(2);
		} else {
			if (StringUtils.equals("1", audit_result)) {// 通过

				// 当前角色在流程中的级别
				KonkaOrderAuditProcess koap = super.getProcessByFgsDeptIdAndType(fgsDept.getDept_id(), new Integer(
						process_state));
				if (null != koap) {
					KonkaOrderAuditProcessNode koapn = super.getProcessNodeByProcessIdAndRoleId(koap.getId(),
							role.getRole_id());
					if (null != koapn) {
						if (koapn.getAudit_level().intValue() == process_max_level.intValue()) {// 最高级通过
							// 分批审核情况 比较产品确定状态
							/**
							 * 更新订单基本信息（审核状态）判断规则说明 1、审核时新添加的产品比较产品数和本次审核数
							 * 相等则为完成； 2、本次审核时删除的产品默认审核完成；
							 * 3、修改的产品比较数量大于等于原数的取已审核数据与本次数据比较 小于原数的只取
							 * 本次审核数量与本次提交产品数比较
							 */
							KonkaOrderInfoDetails konkaOrderInfoDetails = new KonkaOrderInfoDetails();
							konkaOrderInfoDetails.setOrder_id(new Long(id));
							List<KonkaOrderInfoDetails> konkaOrderInfoDetailsList = getFacade()
									.getKonkaOrderInfoDetailsService().getKonkaOrderInfoDetailsList(
											konkaOrderInfoDetails);
							if (null != pd_ids && pd_ids.length > 0) {
								int flag_old = 0;// 默认为新增产品
								int flag_end = 1;// 默认为完成
								for (int i = 0; i < pd_ids.length; i++) {
									if (null != konkaOrderInfoDetailsList && konkaOrderInfoDetailsList.size() > 0) {
										for (int qq = 0; qq < konkaOrderInfoDetailsList.size(); qq++) {
											if (Long.valueOf(pd_ids[i]) == konkaOrderInfoDetailsList.get(qq).getPd_id()) {
												flag_old = 1;// 原有产品
												break;
											}
										}
									}
									if (flag_old == 1) {// 修改 与数据比较大小
										KonkaOrderInfoDetails konkaOrderInfoDetails_com = new KonkaOrderInfoDetails();
										konkaOrderInfoDetails_com.setPd_id(new Long(pd_ids[i]));
										konkaOrderInfoDetails_com.setOrder_id(new Long(id));
										konkaOrderInfoDetails_com = getFacade().getKonkaOrderInfoDetailsService()
												.getKonkaOrderInfoDetails(konkaOrderInfoDetails_com);
										if (Integer.valueOf(good_counts[i]) >= konkaOrderInfoDetails_com
												.getGood_count().intValue()) {// 新提交大于原数据
											int num_com = 0;
											List<KonkaOrderInfoDetailsAudit> KonkaOrderInfoDetailsAuditList = super
													.getKonkaOrderInfoDetailsAuditList(new Long(id), "0", pd_ids[i]);
											if (null != KonkaOrderInfoDetailsAuditList
													&& KonkaOrderInfoDetailsAuditList.size() > 0) {
												for (int k = 0; k < KonkaOrderInfoDetailsAuditList.size(); k++) {
													num_com += KonkaOrderInfoDetailsAuditList.get(k)
															.getAudit_good_count() == null ? 0
															: KonkaOrderInfoDetailsAuditList.get(k)
																	.getAudit_good_count().intValue();
												}
											}
											if (num_com + Integer.valueOf(audit_good_count[i]) < Integer
													.valueOf(good_counts[i])) {// 未完成
												flag_end = 0;
												break;
											}

										} else {// 新提交小于原数据
											if (Integer.valueOf(good_counts[i]) > Integer.valueOf(audit_good_count[i])) {// 未完成
												flag_end = 0;
												break;
											}
										}

									} else {// 新增
										if (Integer.valueOf(good_counts[i]) > Integer.valueOf(audit_good_count[i])) {// 未完成
											flag_end = 0;
											break;
										}

									}
								}
								if (flag_end == 1) {// 完全审核
									konkaOrderInfo.setAudit_state(3);
								} else {// 审核中
									konkaOrderInfo.setAudit_state(1);
								}
							}
						} else {// 非最高级的角色
							konkaOrderInfo.setAudit_state(1);
						}
					} else {// 不在流程
						konkaOrderInfo.setAudit_state(1);
					}
				} else {// 不在流程
					konkaOrderInfo.setAudit_state(1);
				}
			}

		}

		/*********************** 修改订单审核状态 end ***************************************/
		konkaOrderInfo.setKonkaOrderInfoAudit(konkaOrderInfoAudit);

		int order_num = 0;// 订单数量
		BigDecimal money = new BigDecimal(0);// 订单金额
		BigDecimal good_discount_price = new BigDecimal(0);// 商品折扣金额

		// 订单明细中有一个类型的单价低于最低限价，就确定该订单为特殊订单
		// Integer process_state2 = 1;// 初始化
		List<KonkaOrderInfoDetails> konkaOrderInfoDetailsList = new ArrayList<KonkaOrderInfoDetails>();

		KonkaOrderInfoDetails konkaOrderInfoDetails_old = new KonkaOrderInfoDetails();
		konkaOrderInfoDetails_old.setOrder_id(new Long(id));
		List<KonkaOrderInfoDetails> konkaOrderInfoDetailsList_old = getFacade().getKonkaOrderInfoDetailsService()
				.getKonkaOrderInfoDetailsList(konkaOrderInfoDetails_old);

		List<KonkaOrderInfoUpdateRecord> konkaOrderInfoUpdateRecordList = new ArrayList<KonkaOrderInfoUpdateRecord>();

		/*********************** 比较订单产品详细信息 添加更新记录 start ***************************************/
		int new_num = pd_ids.length;// 新产品数
		int old_num = konkaOrderInfoDetailsList_old.size();// 旧产品数

		if (new_num > old_num) {// 新产品数 > 旧产品数
			List<String> pd_list = new ArrayList<String>();// 在旧列表中存在，======跟旧列表数据比较，确定旧列表中被删除的产品====
			for (int i = 0; i < new_num; i++) {
				KonkaOrderInfoUpdateRecord konkaOrderInfoUpdateRecord = new KonkaOrderInfoUpdateRecord();
				int flag_equal = 0;// 默认不在旧产品列表中
				int update_type = 0;// 无修改
				for (int j = 0; j < old_num; j++) {
					if (StringUtils.isNotBlank(pd_ids[i])) {
						if (Long.valueOf(pd_ids[i]) == konkaOrderInfoDetailsList_old.get(j).getPd_id()) {
							flag_equal = 1;// 在旧产品列表中 有修改
							if (Integer.valueOf(good_counts[i] == null ? "0" : good_counts[i]) != Integer
									.valueOf(konkaOrderInfoDetailsList_old.get(j).getGood_count())) {// 修改数量
								update_type = 1;
								if (konkaOrderInfoDetailsList_old.get(j).getGood_price().doubleValue() != Double
										.valueOf(good_prices[i] == null ? "0.00" : good_prices[i])) {// 修改价格和数量
									update_type = 3;

								}

							}
							if (konkaOrderInfoDetailsList_old.get(j).getGood_price().doubleValue() != Double
									.valueOf(good_prices[i] == null ? "0.00" : good_prices[i])) {// 修改价格
								update_type = 2;
								if (Integer.valueOf(good_counts[i] == null ? "0" : good_counts[i]) != Integer
										.valueOf(konkaOrderInfoDetailsList_old.get(j).getGood_count())) {// 修改价格和数量
									update_type = 3;
								}

							}
							if (update_type > 0) {// 有修改
								if (update_type == 1) {// 只修改数量
									konkaOrderInfoUpdateRecord.setUpdate_type(1);
									konkaOrderInfoUpdateRecord.setNum_before(new Long(konkaOrderInfoDetailsList_old
											.get(j).getGood_count()));
									konkaOrderInfoUpdateRecord.setNum_after(new Long(good_counts[i] == null ? "0"
											: good_counts[i]));
								}
								if (update_type == 2) {// 修改价格
									konkaOrderInfoUpdateRecord.setUpdate_type(2);
									konkaOrderInfoUpdateRecord.setPrice_after(new BigDecimal(Double
											.valueOf(good_prices[i] == null ? "0.00" : good_prices[i])));
									konkaOrderInfoUpdateRecord.setPrice_before(konkaOrderInfoDetailsList_old.get(j)
											.getGood_price());
								}
								if (update_type == 3) {// 修改价格和数量
									konkaOrderInfoUpdateRecord.setUpdate_type(3);
									konkaOrderInfoUpdateRecord.setPrice_after(new BigDecimal(Double
											.valueOf(good_prices[i] == null ? "0.00" : good_prices[i])));
									konkaOrderInfoUpdateRecord.setPrice_before(konkaOrderInfoDetailsList_old.get(j)
											.getGood_price());
									konkaOrderInfoUpdateRecord.setNum_before(new Long(konkaOrderInfoDetailsList_old
											.get(j).getGood_count()));
									konkaOrderInfoUpdateRecord.setNum_after(new Long(good_counts[i] == null ? "0"
											: good_counts[i]));
								}

								// 基本信息取数据数据

								konkaOrderInfoUpdateRecord.setPd_type_id(konkaOrderInfoDetailsList_old.get(j)
										.getPd_type_id());
								konkaOrderInfoUpdateRecord.setPd_type_name(konkaOrderInfoDetailsList_old.get(j)
										.getPd_type_name());
								konkaOrderInfoUpdateRecord.setBrand_id(konkaOrderInfoDetailsList_old.get(j)
										.getBrand_id());
								konkaOrderInfoUpdateRecord.setBrand_name(konkaOrderInfoDetailsList_old.get(j)
										.getBrand_name());
								konkaOrderInfoUpdateRecord.setPd_id(konkaOrderInfoDetailsList_old.get(j).getPd_id());
								konkaOrderInfoUpdateRecord
										.setPd_name(konkaOrderInfoDetailsList_old.get(j).getPd_name());
							}

							pd_list.add(konkaOrderInfoDetailsList_old.get(j).getPd_id().toString());
							break;// 跳出本次循环
						} else {// 不在旧列表中在新列表中 新增产品的情况
							flag_equal = 0;
						}
					}
				}
				konkaOrderInfoUpdateRecord.setUpdate_user_id(user.getId());
				konkaOrderInfoUpdateRecord.setUpdate_user_name(user.getUser_name());
				konkaOrderInfoUpdateRecord.setUpdate_user_dept_id(user.getDept_id());
				konkaOrderInfoUpdateRecord.setUpdate_user_dept_name(dept_name);
				konkaOrderInfoUpdateRecord.setUpdate_role_id(role.getRole_id());
				konkaOrderInfoUpdateRecord.setUpdate_role_name(role.getRole_name());
				konkaOrderInfoUpdateRecord.setOrder_id(new Long(id));
				konkaOrderInfoUpdateRecord.setTrade_index(trade_index);
				if (flag_equal == 0) {// 不在旧列表中在新列表中 新增产品的情况
					// 有新增 变更记录 类型为4
					konkaOrderInfoUpdateRecord.setUpdate_type(4);
					konkaOrderInfoUpdateRecord.setPrice_after(new BigDecimal(Double
							.valueOf(good_prices[i] == null ? "0.00" : good_prices[i])));
					konkaOrderInfoUpdateRecord.setPrice_before(new BigDecimal(0));
					konkaOrderInfoUpdateRecord.setNum_before(0l);
					konkaOrderInfoUpdateRecord.setNum_after(new Long(good_counts[i] == null ? "0" : good_counts[i]));
					// 基本信息取数据数据

					// 取型号、大类信息
					PePdModel pePdModel = new PePdModel();
					pePdModel.setBrand_id(Constants.KONKA_BRAND_ID);
					pePdModel.setIs_del(0);
					pePdModel.setPd_id(Long.valueOf(pd_ids[i]));
					pePdModel = getFacade().getPePdModelService().getPePdModel(pePdModel);
					if (null != pePdModel) {
						konkaOrderInfoUpdateRecord.setPd_name(pePdModel.getMd_name());
						konkaOrderInfoUpdateRecord.setBrand_id(pePdModel.getBrand_id());
						konkaOrderInfoUpdateRecord.setBrand_name(Constants.KONKA_BRAND_NAME);
						konkaOrderInfoUpdateRecord.setPd_type_id(pePdModel.getCls_id());
						konkaOrderInfoUpdateRecord.setPd_type_name(super.getBasePdClazz(pePdModel.getCls_id())
								.getCls_name());
					}

					konkaOrderInfoUpdateRecord.setPd_id(new Long(pd_ids[i]));// 取列表

					konkaOrderInfoUpdateRecordList.add(konkaOrderInfoUpdateRecord);

				}
				if (flag_equal == 1) {// 既在旧列表中又在新列表有修改的产品 修改产品的情况
					if (update_type > 0) {
						konkaOrderInfoUpdateRecordList.add(konkaOrderInfoUpdateRecord);
					}
				}

			}

			// 需要记录的，删除掉的产品-----------------start-------
			List<String> new_del_pd_list = new ArrayList<String>();

			for (int i = 0; i < konkaOrderInfoDetailsList_old.size(); i++) {// 在旧列表不在新列表
																			// 删除的情况
				int flag_is_del = 0;// 不需要记录删除
				for (int j = 0; j < pd_list.size(); j++) {// 在旧列表不在新列表 删除的情况
					if (konkaOrderInfoDetailsList_old.get(i).getPd_id().toString().equals(pd_list.get(j))) {
						flag_is_del = 0;
						break;
					} else {
						flag_is_del = 1;
					}

				}
				if (flag_is_del == 1) {
					new_del_pd_list.add(konkaOrderInfoDetailsList_old.get(i).getPd_id().toString());
				}
			}

			if (null != new_del_pd_list && new_del_pd_list.size() > 0) {// 记录审核时删除的产品
				for (int i = 0; i < new_del_pd_list.size(); i++) {
					KonkaOrderInfoUpdateRecord konkaOrderInfoUpdateRecordAdd = new KonkaOrderInfoUpdateRecord();

					KonkaOrderInfoDetails konkaOrderInfoDetails = new KonkaOrderInfoDetails();
					konkaOrderInfoDetails.setPd_id(new Long(new_del_pd_list.get(i)));
					konkaOrderInfoDetails.setOrder_id(new Long(id));
					konkaOrderInfoDetails = getFacade().getKonkaOrderInfoDetailsService().getKonkaOrderInfoDetails(
							konkaOrderInfoDetails);

					konkaOrderInfoUpdateRecordAdd.setUpdate_type(5);
					konkaOrderInfoUpdateRecordAdd.setPrice_after(new BigDecimal(0));
					konkaOrderInfoUpdateRecordAdd.setPrice_before(konkaOrderInfoDetails.getGood_price());
					konkaOrderInfoUpdateRecordAdd.setNum_before(new Long(konkaOrderInfoDetails.getGood_count()));
					konkaOrderInfoUpdateRecordAdd.setNum_after(0l);
					// 基本信息取数据数据
					konkaOrderInfoUpdateRecordAdd.setPd_type_id(konkaOrderInfoDetails.getPd_type_id());
					konkaOrderInfoUpdateRecordAdd.setPd_type_name(konkaOrderInfoDetails.getPd_type_name());
					konkaOrderInfoUpdateRecordAdd.setBrand_id(konkaOrderInfoDetails.getBrand_id());
					konkaOrderInfoUpdateRecordAdd.setBrand_name(konkaOrderInfoDetails.getBrand_name());
					konkaOrderInfoUpdateRecordAdd.setPd_id(konkaOrderInfoDetails.getPd_id());
					konkaOrderInfoUpdateRecordAdd.setPd_name(konkaOrderInfoDetails.getPd_name());
					konkaOrderInfoUpdateRecordAdd.setOrder_id(new Long(id));
					konkaOrderInfoUpdateRecordAdd.setTrade_index(trade_index);

					konkaOrderInfoUpdateRecordAdd.setUpdate_user_id(user.getId());
					konkaOrderInfoUpdateRecordAdd.setUpdate_user_name(user.getUser_name());
					konkaOrderInfoUpdateRecordAdd.setUpdate_user_dept_id(user.getDept_id());
					konkaOrderInfoUpdateRecordAdd.setUpdate_user_dept_name(dept_name);
					konkaOrderInfoUpdateRecordAdd.setUpdate_role_id(role.getRole_id());
					konkaOrderInfoUpdateRecordAdd.setUpdate_role_name(role.getRole_name());

					konkaOrderInfoUpdateRecordList.add(konkaOrderInfoUpdateRecordAdd);

				}
			}
			// 需要记录的，删除掉的产品-----------------end-------
			konkaOrderInfo.setKonkaOrderInfoUpdateRecordList(konkaOrderInfoUpdateRecordList);
		} else if (new_num <= old_num) {// 新产品数 <= 旧产品数
			List<String> pd_list = new ArrayList<String>();// 新旧列表中都存在，=跟新列表比较，确定增加的产品====================
			for (int j = 0; j < old_num; j++) {
				KonkaOrderInfoUpdateRecord konkaOrderInfoUpdateRecord = new KonkaOrderInfoUpdateRecord();
				int flag_equal = 0;// 默认不在新产品列表中
				int update_type = 0;// 无修改
				for (int i = 0; i < new_num; i++) {
					if (Long.valueOf(pd_ids[i]) == konkaOrderInfoDetailsList_old.get(j).getPd_id()) {// 新旧列表中都存在的产品
						flag_equal = 1;// 在新产品列表中 有修改
						if (Integer.valueOf(good_counts[i] == null ? "0" : good_counts[i]) != Integer
								.valueOf(konkaOrderInfoDetailsList_old.get(j).getGood_count())) {// 修改数量
							update_type = 1;
							if (konkaOrderInfoDetailsList_old.get(j).getGood_price().doubleValue() != Double
									.valueOf(good_prices[i] == null ? "0.00" : good_prices[i])) {// 修改价格和数量
								update_type = 3;

							}

						}
						if (konkaOrderInfoDetailsList_old.get(j).getGood_price().doubleValue() != Double
								.valueOf(good_prices[i] == null ? "0.00" : good_prices[i])) {// 修改价格
							update_type = 2;
							if (Integer.valueOf(good_counts[i] == null ? "0" : good_counts[i]) != Integer
									.valueOf(konkaOrderInfoDetailsList_old.get(j).getGood_count())) {// 修改价格和数量
								update_type = 3;
							}

						}
						if (update_type > 0) {// 有修改
							if (update_type == 1) {// 只修改数量
								konkaOrderInfoUpdateRecord.setUpdate_type(1);
								konkaOrderInfoUpdateRecord.setNum_before(new Long(konkaOrderInfoDetailsList_old.get(j)
										.getGood_count()));
								konkaOrderInfoUpdateRecord.setNum_after(new Long(good_counts[i] == null ? "0"
										: good_counts[i]));
							}
							if (update_type == 2) {// 修改价格
								konkaOrderInfoUpdateRecord.setUpdate_type(2);
								konkaOrderInfoUpdateRecord.setPrice_after(new BigDecimal(Double
										.valueOf(good_prices[i] == null ? "0.00" : good_prices[i])));
								konkaOrderInfoUpdateRecord.setPrice_before(konkaOrderInfoDetailsList_old.get(j)
										.getGood_price());
							}
							if (update_type == 3) {// 修改价格和数量
								konkaOrderInfoUpdateRecord.setUpdate_type(3);
								konkaOrderInfoUpdateRecord.setPrice_after(new BigDecimal(Double
										.valueOf(good_prices[i] == null ? "0.00" : good_prices[i])));
								konkaOrderInfoUpdateRecord.setPrice_before(konkaOrderInfoDetailsList_old.get(j)
										.getGood_price());
								konkaOrderInfoUpdateRecord.setNum_before(new Long(konkaOrderInfoDetailsList_old.get(j)
										.getGood_count()));
								konkaOrderInfoUpdateRecord.setNum_after(new Long(good_counts[i] == null ? "0"
										: good_counts[i]));
							}

							// 基本信息取数据数据

							konkaOrderInfoUpdateRecord.setPd_type_id(konkaOrderInfoDetailsList_old.get(j)
									.getPd_type_id());
							konkaOrderInfoUpdateRecord.setPd_type_name(konkaOrderInfoDetailsList_old.get(j)
									.getPd_type_name());
							konkaOrderInfoUpdateRecord.setBrand_id(konkaOrderInfoDetailsList_old.get(j).getBrand_id());
							konkaOrderInfoUpdateRecord.setBrand_name(konkaOrderInfoDetailsList_old.get(j)
									.getBrand_name());
							konkaOrderInfoUpdateRecord.setPd_id(konkaOrderInfoDetailsList_old.get(j).getPd_id());
							konkaOrderInfoUpdateRecord.setPd_name(konkaOrderInfoDetailsList_old.get(j).getPd_name());
						}

						pd_list.add(pd_ids[i]);
						break;// 跳出本次循环
					} else {// 不在新产品中
						flag_equal = 0;
					}
				}
				konkaOrderInfoUpdateRecord.setUpdate_user_id(user.getId());
				konkaOrderInfoUpdateRecord.setUpdate_user_name(user.getUser_name());
				konkaOrderInfoUpdateRecord.setUpdate_user_dept_id(user.getDept_id());
				konkaOrderInfoUpdateRecord.setUpdate_user_dept_name(dept_name);
				konkaOrderInfoUpdateRecord.setUpdate_role_id(role.getRole_id());
				konkaOrderInfoUpdateRecord.setUpdate_role_name(role.getRole_name());

				konkaOrderInfoUpdateRecord.setOrder_id(new Long(id));
				konkaOrderInfoUpdateRecord.setTrade_index(trade_index);
				if (flag_equal == 0) {// 不在新列表中在旧列表中 删除产品的情况
					// 旧产品删除 变更记录 类型为5
					konkaOrderInfoUpdateRecord.setUpdate_type(5);
					konkaOrderInfoUpdateRecord.setPrice_after(new BigDecimal(0));
					konkaOrderInfoUpdateRecord.setPrice_before(konkaOrderInfoDetailsList_old.get(j).getGood_price());
					konkaOrderInfoUpdateRecord.setNum_before(new Long(konkaOrderInfoDetailsList_old.get(j)
							.getGood_count()));
					konkaOrderInfoUpdateRecord.setNum_after(0l);
					// 基本信息取数据数据

					KonkaOrderInfoDetails konkaOrderInfoDetails = new KonkaOrderInfoDetails();
					konkaOrderInfoDetails.setPd_id(new Long(konkaOrderInfoDetailsList_old.get(j).getPd_id()));
					konkaOrderInfoDetails.setOrder_id(new Long(id));
					konkaOrderInfoDetails = getFacade().getKonkaOrderInfoDetailsService().getKonkaOrderInfoDetails(
							konkaOrderInfoDetails);

					konkaOrderInfoUpdateRecord.setPd_type_id(konkaOrderInfoDetails.getPd_type_id());
					konkaOrderInfoUpdateRecord.setPd_type_name(konkaOrderInfoDetails.getPd_type_name());
					konkaOrderInfoUpdateRecord.setBrand_id(konkaOrderInfoDetails.getBrand_id());
					konkaOrderInfoUpdateRecord.setBrand_name(konkaOrderInfoDetails.getBrand_name());
					konkaOrderInfoUpdateRecord.setPd_id(konkaOrderInfoDetails.getPd_id());
					konkaOrderInfoUpdateRecord.setPd_name(konkaOrderInfoDetails.getPd_name());
					konkaOrderInfoUpdateRecord.setOrder_id(new Long(id));
					konkaOrderInfoUpdateRecord.setTrade_index(trade_index);
					konkaOrderInfoUpdateRecordList.add(konkaOrderInfoUpdateRecord);

				}
				if (flag_equal == 1) {// 既在旧列表中又在新列表有修改的产品 修改产品的情况
					if (update_type > 0) {
						konkaOrderInfoUpdateRecordList.add(konkaOrderInfoUpdateRecord);
					}
				}

			}

			// 处理新增产品
			List<String> new_add_pd_list = new ArrayList<String>();// 需要记录的，新增的产品

			for (int i = 0; i < pd_ids.length; i++) {// 不在旧产品中，在新产品中，新增
				int flag_is_add = 1;// 需要新增
				for (int j = 0; j < pd_list.size(); j++) {
					if (pd_ids[i].equals(pd_list.get(j))) {
						flag_is_add = 0;
						break;
					} else {
						flag_is_add = 1;
					}

				}
				if (flag_is_add == 1) {// 新增产品
					new_add_pd_list.add(pd_ids[i]);
				}
			}

			if (null != new_add_pd_list && new_add_pd_list.size() > 0) {// 记录审核时新增的产品
				for (int i = 0; i < new_add_pd_list.size(); i++) {
					KonkaOrderInfoUpdateRecord konkaOrderInfoUpdateRecordAdd = new KonkaOrderInfoUpdateRecord();
					// 不在旧列表中在新列表中 新增产品的情况
					// 有新增 变更记录 类型为4
					konkaOrderInfoUpdateRecordAdd.setUpdate_type(4);
					konkaOrderInfoUpdateRecordAdd.setPrice_after(new BigDecimal(Double
							.valueOf(good_prices[i] == null ? "0.00" : good_prices[i])));
					konkaOrderInfoUpdateRecordAdd.setPrice_before(new BigDecimal(0));
					konkaOrderInfoUpdateRecordAdd.setNum_before(0l);
					konkaOrderInfoUpdateRecordAdd.setNum_after(new Long(good_counts[i] == null ? "0" : good_counts[i]));
					// 基本信息取数据数据

					// 取型号、大类信息
					PePdModel pePdModel = new PePdModel();
					pePdModel.setBrand_id(Constants.KONKA_BRAND_ID);
					pePdModel.setIs_del(0);
					pePdModel.setPd_id(Long.valueOf(pd_ids[i]));
					pePdModel = getFacade().getPePdModelService().getPePdModel(pePdModel);
					if (null != pePdModel) {
						konkaOrderInfoUpdateRecordAdd.setPd_name(pePdModel.getMd_name());
						konkaOrderInfoUpdateRecordAdd.setBrand_id(pePdModel.getBrand_id());
						konkaOrderInfoUpdateRecordAdd.setBrand_name(Constants.KONKA_BRAND_NAME);
						konkaOrderInfoUpdateRecordAdd.setPd_type_id(pePdModel.getCls_id());
						konkaOrderInfoUpdateRecordAdd.setPd_type_name(super.getBasePdClazz(pePdModel.getCls_id())
								.getCls_name());
					}
					konkaOrderInfoUpdateRecordAdd.setOrder_id(new Long(id));
					konkaOrderInfoUpdateRecordAdd.setTrade_index(trade_index);
					konkaOrderInfoUpdateRecordAdd.setPd_id(new Long(pd_ids[i]));// 取列表

					konkaOrderInfoUpdateRecordAdd.setUpdate_user_id(user.getId());
					konkaOrderInfoUpdateRecordAdd.setUpdate_user_name(user.getUser_name());
					konkaOrderInfoUpdateRecordAdd.setUpdate_user_dept_id(user.getDept_id());
					konkaOrderInfoUpdateRecordAdd.setUpdate_user_dept_name(dept_name);
					konkaOrderInfoUpdateRecordAdd.setUpdate_role_id(role.getRole_id());
					konkaOrderInfoUpdateRecordAdd.setUpdate_role_name(role.getRole_name());
					konkaOrderInfoUpdateRecordList.add(konkaOrderInfoUpdateRecordAdd);
				}
			}
			konkaOrderInfo.setKonkaOrderInfoUpdateRecordList(konkaOrderInfoUpdateRecordList);
		}

		/*********************** 比较订单产品详细信息 添加更新记录 end ***************************************/

		/*********************** 删除订单原有产品详细信息 start ***************************************/
		if (null != konkaOrderInfoDetailsList_old && konkaOrderInfoDetailsList_old.size() > 0) {// 删除原有数据
			List<KonkaOrderInfoDetails> konkaOrderInfoDetailsList_del = new ArrayList<KonkaOrderInfoDetails>();
			KonkaOrderInfoDetails konkaOrderInfoDetails_del = new KonkaOrderInfoDetails();
			for (int ii = 0; ii < konkaOrderInfoDetailsList_old.size(); ii++) {
				konkaOrderInfoDetails_del = konkaOrderInfoDetailsList_old.get(ii);
				konkaOrderInfoDetailsList_del.add(konkaOrderInfoDetails_del);
			}
			konkaOrderInfo.setKonkaOrderInfoDetailsListForDel(konkaOrderInfoDetailsList_del);
		}

		/*********************** 删除订单原有产品详细信息 end ***************************************/

		/*********************** 添加订单新产品详细信息及审核记录表 start ***************************************/
		if (pd_ids != null && pd_ids.length > 0) {

			for (int i = 0; i < pd_ids.length; i++) {
				order_num += Integer.valueOf(good_counts[i]);
				// money = money.add(new BigDecimal(good_prices[i]));
				money = money.add(new BigDecimal(good_prices[i]).multiply(new BigDecimal(good_counts[i])));
				good_discount_price = good_discount_price.add(new BigDecimal(good_discounts[i]).divide(
						new BigDecimal(100)).multiply(money));
				// ===============折让金额====
				KonkaOrderInfoDetails konkaOrderInfoDetails = new KonkaOrderInfoDetails();
				konkaOrderInfoDetails.setPd_id(Long.valueOf(pd_ids[i]));
				// 取型号、大类信息
				PePdModel pePdModel = new PePdModel();
				pePdModel.setBrand_id(Constants.KONKA_BRAND_ID);
				pePdModel.setIs_del(0);
				pePdModel.setPd_id(Long.valueOf(pd_ids[i]));
				pePdModel = getFacade().getPePdModelService().getPePdModel(pePdModel);
				if (null != pePdModel) {
					konkaOrderInfoDetails.setPd_name(pePdModel.getMd_name());
					konkaOrderInfoDetails.setBrand_id(pePdModel.getBrand_id());
					konkaOrderInfoDetails.setBrand_name(Constants.KONKA_BRAND_NAME);
					konkaOrderInfoDetails.setPd_type_id(pePdModel.getCls_id());
					konkaOrderInfoDetails.setPd_type_name(super.getBasePdClazz(pePdModel.getCls_id()).getCls_name());
				}
				konkaOrderInfoDetails.setPd_remark(pd_remarks[i]);
				konkaOrderInfoDetails.setOrder_id(new Long(id));
				konkaOrderInfoDetails.setGood_count(Integer.valueOf(good_counts[i]));
				// orderNum += Long.valueOf(good_counts[i]);

				konkaOrderInfoDetails.setGood_price(new BigDecimal(good_prices[i]));
				konkaOrderInfoDetails.setGood_unit(good_units[i]);
				// konkaOrderInfoDetails.setGood_sum_price(new
				// BigDecimal(sum_prices[i]));
				konkaOrderInfoDetails.setGood_sum_price(new BigDecimal(good_prices[i]).multiply(new BigDecimal(
						good_counts[i])));
				// orderMoney =
				// orderMoney.add(konkaOrderInfoDetails.getGood_sum_price());

				konkaOrderInfoDetails.setGood_discount(new BigDecimal(good_discounts[i]));
				// konkaOrderInfoDetails.setGood_discount_price(new
				// BigDecimal(discount_prices[i]));

				konkaOrderInfoDetails.setGood_discount_price(konkaOrderInfoDetails.getGood_sum_price()
						.multiply(new BigDecimal(good_discounts[i])).divide(new BigDecimal("100")));

				// orderDiscountPrice =
				// orderDiscountPrice.add(konkaOrderInfoDetails.getGood_discount_price());
				konkaOrderInfoDetailsList.add(konkaOrderInfoDetails);

			}

			int flag_process_state = Integer.valueOf(process_state);
			for (int i = 0; i < pd_ids.length; i++) {
				// 取网点对应的分公司部门ID
				logger.info("********************************************pd_ids:{}", pd_ids[i]);
				if (null == konkaOrderInfo.getShop_id()) {
					break;
				}
				HashMap<String, String> result = super.getKonkaDeptIdAndType(konkaOrderInfo.getShop_id(), true);
				if (StringUtils.equals(result.get("result_code"), "0")) {
					break;
				}

				String[] deptIds = result.get("r3_dept_ids").split(",");
				Long deptId = new Long(deptIds[0]);// 目前保证一个网点只匹配和分配一个分公司下
				if (null != deptId && deptId.intValue() != -1) {// deptId=-1l,说明没有找到上级分公司（暂时没做限制添加订单的处理）

					// 判断是否定义特殊流程，如果没定义则不改变订单状态为特殊流程状态

					KonkaOrderAuditProcess konkaOrderAuditProcess = super.getSpecialProcessByFgsDeptId(deptId);
					if (null != konkaOrderAuditProcess) {

						// 取分公司的产品限价
						KonkaPePdModelLowestPrice konkaPePdModelLowestPrice = new KonkaPePdModelLowestPrice();
						konkaPePdModelLowestPrice.setAdd_dept_id(deptId);
						konkaPePdModelLowestPrice.setPd_id(new Long(pd_ids[i]));
						konkaPePdModelLowestPrice.setSet_year(year);
						konkaPePdModelLowestPrice.setSet_month(month);
						konkaPePdModelLowestPrice.setIs_del(0);
						konkaPePdModelLowestPrice = getFacade().getKonkaPePdModelLowestPriceService()
								.getKonkaPePdModelLowestPrice(konkaPePdModelLowestPrice);
						/**
						 * 订单类型修改说明 1、总经理之后角色不能修改 2、不在流程定义中的用户角色不受级别限制全部修改
						 */
						KonkaOrderAuditProcess konkaOrderAuditProcess_ts = new KonkaOrderAuditProcess();
						konkaOrderAuditProcess_ts = super.getSpecialProcessByFgsDeptId(deptId);
						if (null == konkaOrderAuditProcess_ts) {
							String msg = "特殊流程定义未完成或删除，请联系管理员!";
							renderJavaScript(response, "alert('" + msg + "');history.back();");
							return null;
						}

						// 特殊流程存在
						KonkaOrderAuditProcessNode konkaOrderAuditProcessNode_ts = super
								.getProcessNodeByProcessIdAndRoleId(konkaOrderAuditProcess_ts.getId(),
										role.getRole_id());
						if (null != konkaOrderAuditProcessNode_ts) {// 在流程
																	// 根据级别修改类型
							// 总经理在流程中的级别
							KonkaOrderAuditProcessNode konkaOrderAuditProcessNode_zjl = super
									.getProcessNodeByProcessIdAndRoleId(konkaOrderAuditProcess_ts.getId(), new Long(
											Constants.ROLE_ID_FGS_ZJL));
							if (konkaOrderAuditProcessNode_ts.getAudit_level().intValue() <= konkaOrderAuditProcessNode_zjl
									.getAudit_level() && role.getRole_id().intValue() != Constants.ROLE_ID_FGS_ZJL) {
								if (null != konkaPePdModelLowestPrice) {
									if (new BigDecimal(good_prices[i]).longValue() < konkaPePdModelLowestPrice
											.getLowest_price().longValue()) {
										// konkaOrderInfo.setProcess_state(2);//
										// 特殊流程（产品价格低于了部门产品的最低限价）
										flag_process_state = 2;
										break;
									} else {
										flag_process_state = 1;
									}
								}
							}

						} else {// 不在流程修改
							if (null != konkaPePdModelLowestPrice) {
								if (new BigDecimal(good_prices[i]).longValue() < konkaPePdModelLowestPrice
										.getLowest_price().longValue()) {
									// konkaOrderInfo.setProcess_state(2);//
									// 特殊流程（产品价格低于了部门产品的最低限价）
									flag_process_state = 2;
									break;
								} else {
									flag_process_state = 1;
								}
							}
						}

					}
				}

			}
			konkaOrderInfo.setProcess_state(flag_process_state);
			konkaOrderInfo.setKonkaOrderInfoDetailsList(konkaOrderInfoDetailsList);
		}

		/*********************** 添加订单新产品详细信息及审核记录表 end ***************************************/

		/*********************** 总后一个结点分批审核 start ***************************************/
		/**
		 * 操作说明 1、更新原有审核产品数据（删除的产品更改标志位） 2、添加审核产品记录（记录本次审核产品情况） 3、更新订单基本信息（审核状态）
		 */

		// 当前角色在流程中的级别
		KonkaOrderAuditProcess koap = super.getProcessByFgsDeptIdAndType(fgsDept.getDept_id(), new Integer(
				process_state));
		if (null != koap) {
			KonkaOrderAuditProcessNode koapn = super
					.getProcessNodeByProcessIdAndRoleId(koap.getId(), role.getRole_id());
			if (null != koapn) {
				if (koapn.getAudit_level().intValue() == process_max_level.intValue()) {// //最高审核级别
																						// 分批审核
					// 更新原有审核产品数据（删除的产品更改标志位）
					List<KonkaOrderInfoDetailsAudit> konkaOrderInfoDetailsAuditList_update = super
							.getKonkaOrderInfoDetailsAuditList(new Long(id), "", "");
					if (null != konkaOrderInfoDetailsAuditList_update
							&& konkaOrderInfoDetailsAuditList_update.size() > 0) {
						List<KonkaOrderInfoDetailsAudit> konkaOrderInfoDetailsAuditList_del = new ArrayList<KonkaOrderInfoDetailsAudit>();
						for (int i = 0; i < konkaOrderInfoDetailsAuditList_update.size(); i++) {
							Long pd_id_old = konkaOrderInfoDetailsAuditList_update.get(i).getPd_id();
							int flag_update = 1;
							for (int j = 0; j < pd_ids.length; j++) {
								if (pd_id_old == Long.valueOf(pd_ids[j])) {
									flag_update = 0;
									break;
								}
							}
							if (flag_update == 1) {// 删除产品型号更新记录
								KonkaOrderInfoDetailsAudit konkaOrderInfoDetailsAudit = konkaOrderInfoDetailsAuditList_update
										.get(i);
								konkaOrderInfoDetailsAudit.setIs_del(1);
								konkaOrderInfoDetailsAuditList_del.add(konkaOrderInfoDetailsAudit);
							}
							konkaOrderInfo
									.setKonkaOrderInfoDetailsAuditListForUpdate(konkaOrderInfoDetailsAuditList_del);
						}

					}

					// 添加审核产品数据到审核表 更改状态
					if (null != pd_ids && pd_ids.length > 0) {
						List<KonkaOrderInfoDetailsAudit> konkaOrderInfoDetailsAuditList = new ArrayList<KonkaOrderInfoDetailsAudit>();
						for (int i = 0; i < pd_ids.length; i++) {
							KonkaOrderInfoDetailsAudit konkaOrderInfoDetailsAudit_add = new KonkaOrderInfoDetailsAudit();
							konkaOrderInfoDetailsAudit_add.setPd_id(Long.valueOf(pd_ids[i]));
							// 取型号、大类信息
							PePdModel pePdModel = new PePdModel();
							pePdModel.setBrand_id(Constants.KONKA_BRAND_ID);
							pePdModel.setIs_del(0);
							pePdModel.setPd_id(Long.valueOf(pd_ids[i]));
							pePdModel = getFacade().getPePdModelService().getPePdModel(pePdModel);
							if (null != pePdModel) {
								konkaOrderInfoDetailsAudit_add.setPd_name(pePdModel.getMd_name());
								konkaOrderInfoDetailsAudit_add.setBrand_id(pePdModel.getBrand_id());
								konkaOrderInfoDetailsAudit_add.setBrand_name(Constants.KONKA_BRAND_NAME);
								konkaOrderInfoDetailsAudit_add.setPd_type_id(pePdModel.getCls_id());
								konkaOrderInfoDetailsAudit_add.setPd_type_name(super.getBasePdClazz(
										pePdModel.getCls_id()).getCls_name());
							}
							konkaOrderInfoDetailsAudit_add.setIs_del(0);
							konkaOrderInfoDetailsAudit_add.setOrder_id(new Long(id));
							konkaOrderInfoDetailsAudit_add.setAudit_good_count(new Integer(audit_good_count[i]));
							konkaOrderInfoDetailsAudit_add.setAudit_good_price(new BigDecimal(audit_good_price[i]));
							konkaOrderInfoDetailsAudit_add.setGood_unit(good_units[i]);

							konkaOrderInfoDetailsAudit_add.setGood_sum_price(new BigDecimal(good_prices[i])
									.multiply(new BigDecimal(good_counts[i])));

							konkaOrderInfoDetailsAudit_add.setGood_discount(new BigDecimal(good_discounts[i]));

							konkaOrderInfoDetailsAudit_add.setGood_discount_price(konkaOrderInfoDetailsAudit_add
									.getGood_discount().multiply(konkaOrderInfoDetailsAudit_add.getGood_sum_price())
									.divide(new BigDecimal("100")));

							konkaOrderInfoDetailsAuditList.add(konkaOrderInfoDetailsAudit_add);
						}
						konkaOrderInfo.setKonkaOrderInfoDetailsAuditList(konkaOrderInfoDetailsAuditList);
					}

					// 更新状态 在管理端进行同步

					// 下面的代码是XingXiuDong @2013/6/25添加，在终审后将保存数据到R3系统
					// if (super.isCallR3Interface(request)) {
					// ReturnInfo ret =
					// super.getFacade().getR3WebInterfaceService().saveKonkaOrderInfo(konkaOrderInfo,
					// user.getUser_name());
					// if (null != ret.getItemNO()) {
					// // 调用接口创建订单成功，更新本地状态
					// KonkaOrderInfo updated = new KonkaOrderInfo();
					// updated.setId(konkaOrderInfo.getId());
					// updated.setR3_id(Long.valueOf(ret.getItemNO()));
					// updated.setSync_state(1);
					//
					// super.getFacade().getKonkaOrderInfoService().modifyKonkaOrderInfo(konkaOrderInfo);
					// }
					// }
					// @2013/6/25添加 结束
				}
			}
		}

		/*********************** 总后一个结点分批审核 end ***************************************/

		// 修改订单的流程类型

		// konkaOrderInfo.setProcess_state(process_state2);// 流程类型
		konkaOrderInfo.setOrder_num(new Long(order_num));
		konkaOrderInfo.setMoney(money);
		konkaOrderInfo.setGood_discount_price(good_discount_price);
		getFacade().getKonkaOrderInfoService().modifyKonkaOrderInfo(konkaOrderInfo);
		saveMessage(request, "entity.updated");

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		// pathBuffer.append("&process_state=" + process_state);
		pathBuffer.append("&");
		// pathBuffer.append(super.encodeSerializedQueryString(konkaOrderInfo.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		logger.info("konkaOrderInfo:{}", pathBuffer.toString());
		return forward;

	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (!isSessionLoginForKonkaJxc(request)) {
			return forwardNoSessionForKonkaJxc(request, response);
		}
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;

		PeProdUser user = super.getSessionUserInfo(request);
		// String audit_level = (String) dynaBean.get("audit_level");
		String id = (String) dynaBean.get("id");
		if (null == user) {
			return null;
		}

		if (!GenericValidator.isLong(id)) {
			this.saveError(request, "errors.long", new String[] { id });
			return new ActionForward("/KonkaJxcOrderAudit/list.jsp");
		}

		// 订单基本信息
		KonkaOrderInfo konkaOrderInfo = new KonkaOrderInfo();
		konkaOrderInfo.setId(new Long(id));
		konkaOrderInfo = getFacade().getKonkaOrderInfoService().getKonkaOrderInfo(konkaOrderInfo);

		if (null == konkaOrderInfo) {
			this.saveError(request, "errors.long", new String[] { id });
			return new ActionForward("/KonkaJxcOrderAudit/list.jsp");
		}

		List<KonkaOrderInfoAudit> konkaOrderInfoAuditList = super.getKonkaOrderInfoAuditWithRoleInfoList(new Long(id));
		if (null != konkaOrderInfoAuditList && konkaOrderInfoAuditList.size() > 0) {
			konkaOrderInfo.setKonkaOrderInfoAuditList(konkaOrderInfoAuditList);
		}

		// 根据用户部门id找 所属分公司
		KonkaDept fgsDept = super.getSuperiorForDeptType(user.getDept_id(), 3);
		if (null == fgsDept) {
			return null;
		}

		Long max_level = super.getKonkaOrderAuditProcessNodeMaxLevel(fgsDept.getDept_id(), new Long(id));
		konkaOrderInfo.getMap().put("max_audit_level", max_level);// 该订单对应的最高审核级别,判断审核状态
		super.copyProperties(form, konkaOrderInfo);

		// 订单详细信息
		KonkaOrderInfoDetails konkaOrderInfoDetails = new KonkaOrderInfoDetails();
		konkaOrderInfoDetails.setOrder_id(konkaOrderInfo.getId());
		List<KonkaOrderInfoDetails> konkaOrderInfoDetailsList = getFacade().getKonkaOrderInfoDetailsService()
				.getKonkaOrderInfoDetailsList(konkaOrderInfoDetails);
		request.setAttribute("konkaOrderInfoDetailsList", konkaOrderInfoDetailsList);

		/* 订单审核信息 start */
		// 取订单当前所处的审核流程(随时可能变动)

		// 根据订单id，取所对应的分公司
		KonkaDept konkaDeptForFGS = super.getKonkaFGSByOrderId(konkaOrderInfo.getId());
		// 取订单当前所处的审核流程(随时可能变动)
		KonkaOrderAuditProcess konkaOrderAuditProcess = new KonkaOrderAuditProcess();
		konkaOrderAuditProcess.setAdd_dept_id(konkaDeptForFGS.getDept_id());//
		konkaOrderAuditProcess.setProcess_type(konkaOrderInfo.getProcess_state());
		konkaOrderAuditProcess.setIs_del(0);
		konkaOrderAuditProcess = getFacade().getKonkaOrderAuditProcessService().getKonkaOrderAuditProcess(
				konkaOrderAuditProcess);

		if (null == konkaOrderAuditProcess) {
			// 审核流程还没定义
			konkaOrderInfo.getMap().put("audit_role_name", "等待审核");
		}

		List<KonkaOrderInfoAudit> list = super.getKonkaOrderInfoAuditWithRoleInfoList(konkaOrderInfo.getId());// 订单审核信息列表
		if (konkaOrderInfo.getAudit_state() != 0) {
			// 有审核
			if (konkaOrderInfo.getAudit_state() == 1) {// 审核中，显示当前审核角色的
														// 下一步审核角色
				KonkaOrderAuditProcessNode entity = new KonkaOrderAuditProcessNode();
				entity.setAudit_proces_id(konkaOrderAuditProcess.getId());
				entity.setAudit_level(list.get(0).getAudit_level() + 1);
				List<KonkaOrderAuditProcessNode> nextKonkaOrderAuditProcessNodeList = getFacade()
						.getKonkaOrderAuditProcessNodeService().getKonkaOrderAuditProcessNodeList(entity);
				String[] nextRoleNames = new String[nextKonkaOrderAuditProcessNodeList.size()];
				for (int i = 0; i < nextKonkaOrderAuditProcessNodeList.size(); i++) {
					nextRoleNames[i] = nextKonkaOrderAuditProcessNodeList.get(i).getRole_name();
				}
				konkaOrderInfo.getMap().put("audit_role_name", StringUtils.join(nextRoleNames, ","));

			} else if (konkaOrderInfo.getAudit_state() == 2 || konkaOrderInfo.getAudit_state() == 3) {// 审核通过或者未通过，显示最后审核角色
				konkaOrderInfo.getMap().put("audit_role_name", list.get(0).getMap().get("role_name"));
			}
			request.setAttribute("konkaOrderAuditList", list);
		} else {
			// 未审核,显示当前审核角色 即该网点分配的用户角色
			// Long konka_shop_id = null;
			// if (null != konkaOrderInfo.getShop_id()) {
			// KonkaR3MmtMatch konkaR3MmtMatch = new KonkaR3MmtMatch();
			// konkaR3MmtMatch.setMmt_shop_id(konkaOrderInfo.getShop_id());
			// konkaR3MmtMatch =
			// getFacade().getKonkaR3MmtMatchService().getKonkaR3MmtMatch(konkaR3MmtMatch);
			// if (null != konkaR3MmtMatch) {
			// konka_shop_id = konkaR3MmtMatch.getR3_shop_id();
			// }
			// } else if (null != konkaOrderInfo.getCust_id()) {
			// konka_shop_id = konkaOrderInfo.getCust_id();
			// }
			// BranchAssign branchAssign = new BranchAssign();
			// branchAssign.setBranch_type(1);
			// branchAssign.setKonka_r3_id(konka_shop_id);
			// branchAssign =
			// getFacade().getBranchAssignService().getBranchAssign(branchAssign);
			// if (null != branchAssign) {
			// PeRoleUser peRoleUser = new PeRoleUser();
			// peRoleUser.setUser_id(branchAssign.getUser_id());
			// // peRoleUser =
			// getFacade().getPeRoleUserService().getPeRoleUser(peRoleUser);
			// List<PeRoleUser> roleUserList =
			// super.getFacade().getPeRoleUserService().getPeRoleUserList(peRoleUser);
			// if (null != peRoleUser) {// 查找角色名称
			// PeRoleInfo peRoleInfo = new PeRoleInfo();
			// peRoleInfo.setRole_id(peRoleUser.getRole_id());
			// peRoleInfo =
			// getFacade().getPeRoleInfoService().getPeRoleInfo(peRoleInfo);
			// if (null != peRoleInfo) {
			// konkaOrderInfo.getMap().put("audit_role_name",
			// peRoleInfo.getRole_name());
			// }
			// }
			// }

			konkaOrderInfo.getMap().put("audit_role_name", "业务员");
		}
		super.copyProperties(form, konkaOrderInfo);
		return mapping.findForward("view");
		/* 订单审核信息 end */

	}

	public ActionForward print(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;

		PeProdUser user = super.getSessionUserInfo(request);
		String orderId = (String) dynaBean.get("id");
		String printType = (String) dynaBean.get("printType");

		Pager pager = (Pager) dynaBean.get("pager");
		if (null == user) {
			return null;
		}

		// 如果是a4打印，不需要分页
		if (printType != null && !"".equals(printType.trim()) && printType.equals("a4")) {

		} else {
			pager.setPageSize(7);
		}

		// 根据用户部门id找 所属分公司
		KonkaDept fgsDept = super.getKonkaDeptByUserDeptId(user.getDept_id());
		// print date
		String nowDate = DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
		// order date
		String orderdate = "";
		//
		long s_order_num = 0;
		double s_dis_price = 0;
		double s_price = 0;
		// 每一行折后金额 = 总金额(单价*数量) + 折扣金额 + 总金额 *折扣率
		double af_dis_price = 0;// 每一行的折后金额
		double s_af_dis_price = 0;// 折后金额合计

		// 机型的仓位,取第一个机型的storeKey
		String storeKey = "";

		// los 物流交货单号
		String los = "";
		// 订单行
		List<KonkaOrderInfoDetails> konkaOrderInfoDetailsList = new ArrayList<KonkaOrderInfoDetails>();

		if (orderId != null && !"".equals(orderId.trim())) {
			// 订单基本信息
			KonkaOrderInfo konkaOrderInfo = new KonkaOrderInfo();
			konkaOrderInfo.setId(new Long(orderId));
			konkaOrderInfo = getFacade().getKonkaOrderInfoService().getKonkaOrderInfo(konkaOrderInfo);
			if (konkaOrderInfo.getPrintCount() == null) {
				konkaOrderInfo.setPrintCount(0);
			}

			// 总经理审核人
			String s34 = "";
			// 财务经理
			String s39 = "";
			// 业务主管
			String s50r40 = "";
			// 业务员
			String s60 = "";

			request.setAttribute("orderinfo", konkaOrderInfo);// 1
			if (null != konkaOrderInfo) {
				// 1.送达方,因为打印页面要加送达方信息,所以加的.
				String we = konkaOrderInfo.getWe();
				orderdate = DateFormatUtils.format(konkaOrderInfo.getAdd_date(), "yyyy年MM月dd日 HH:mm:ss");
				if (null != we) {
					KonkaR3Shop konkaR3Shop = new KonkaR3Shop();
					konkaR3Shop.setR3_code(we);
					konkaR3Shop = getFacade().getKonkaR3ShopService().getKonkaR3Shop(konkaR3Shop);
					if (null != konkaR3Shop) {
						request.setAttribute("konkaR3Shop", konkaR3Shop);// 2
					}

					// 2.取账期信息,如果客户为空,那么不取.
					List<KHXD> khxdList = new ArrayList<KHXD>();
					// List<KHXD> khxdList =
					// getFacade().getR3WebInterfaceService().getKhxd("KF01",
					// null, "10",
					// new String[] { konkaOrderInfo.getAg() });

					ReturnInfo<KHXD> info = new ReturnInfo<KHXD>();
					info = getFacade().getR3WebInterfaceService().getKhxd("KF01", null, "10",
							new String[] { konkaOrderInfo.getAg() });
					if (info.getSuccess() == true)
						khxdList = info.getDataResult();
					if (khxdList.size() > 0) {
						KHXD khxd = new KHXD();
						khxd = khxdList.get(0);
						// request.setAttribute("SKFOR", khxd.getSKFOR());//
						// 余额应收
						// request.setAttribute("KLIMK", khxd.getKLIMK());//
						// 信贷限额
						request.setAttribute("khxd", khxd);
					}
				}
				super.copyProperties(form, konkaOrderInfo);
				// 3.订单明细信息
				KonkaOrderInfoDetails konkaOrderInfoDetails = new KonkaOrderInfoDetails();
				konkaOrderInfoDetails.setOrder_id(konkaOrderInfo.getId());
                konkaOrderInfoDetails.getMap().put("good_count_gt0", "gt0");// 订单行数量大于0

				// 如果是a4打印，不需要分页
				Long totalCount = getFacade().getKonkaOrderInfoDetailsService().getKonkaOrderInfoDetailsCount(
						konkaOrderInfoDetails);

				if (printType != null && !"".equals(printType.trim()) && printType.equals("a4")) {
					konkaOrderInfoDetailsList = getFacade().getKonkaOrderInfoDetailsService()
							.getKonkaOrderInfoDetailsList(konkaOrderInfoDetails);
				} else {
					pager.init(totalCount, pager.getPageSize(), pager.getRequestPage());
					konkaOrderInfoDetails.getRow().setFirst(pager.getFirstRow());
					konkaOrderInfoDetails.getRow().setCount(pager.getRowCount());

					konkaOrderInfoDetailsList = getFacade().getKonkaOrderInfoDetailsService()
							.getKonkaOrderInfoDetailsPaginatedList(konkaOrderInfoDetails);
				}

				// 4.单的审核的历史记录,包括审核人,审核的时间点,审核意见
				KonkaOrderInfoAudit konkaOrderInfoAudit = new KonkaOrderInfoAudit();
				konkaOrderInfoAudit.setLink_id(Long.valueOf(orderId));
				List<KonkaOrderInfoAudit> konkaOrderInfoAuditWithRoleInfoList = getFacade()
						.getKonkaOrderInfoAuditService().getKonkaOrderInfoAuditAndRoleList(konkaOrderInfoAudit);
				konkaOrderInfo.setKonkaOrderInfoAuditList(konkaOrderInfoAuditWithRoleInfoList);

				// //////////////////////////单据必须是审核中1,审核完结3,已作废4的才计算//////////////////////////////////////////
				// 5.计算出最新的审核人,审核时间.如果没有审核,那么为"",倒序检索信息
				if (konkaOrderInfo.getAudit_state() == 1 || konkaOrderInfo.getAudit_state() == 3
						|| konkaOrderInfo.getAudit_state() == 4) {
					if (null != konkaOrderInfoAuditWithRoleInfoList && konkaOrderInfoAuditWithRoleInfoList.size() > 0) {
						List<KonkaOrderInfoAudit> auditList = new ArrayList<KonkaOrderInfoAudit>();
						auditList.addAll(konkaOrderInfoAuditWithRoleInfoList);
						Collections.reverse(auditList);
						SimpleDateFormat sdf = new SimpleDateFormat("MM/dd HH:mm");
						for (KonkaOrderInfoAudit temp : auditList) {
							// 总经理
							if (temp.getMap().get("role_id") != null && !temp.getMap().get("role_id").equals("")
									&& temp.getAudit_result() == 1) {
								if (Long.valueOf(temp.getMap().get("role_id") + "") == 34L && "".equals(s34)) {
									s34 = temp.getAudit_user() + " " + sdf.format(temp.getAudit_datetime());
								}
							}
							// 财务经理
							if (temp.getMap().get("role_id") != null && !temp.getMap().get("role_id").equals("")
									&& temp.getAudit_result() == 1) {
								if (Long.valueOf(temp.getMap().get("role_id") + "") == 39L && "".equals(s39)) {
									s39 = temp.getAudit_user() + " " + sdf.format(temp.getAudit_datetime());
								}
							}
							// 业务主管
							if (temp.getMap().get("role_id") != null && !temp.getMap().get("role_id").equals("")
									&& temp.getAudit_result() == 1) {
								if (Long.valueOf(temp.getMap().get("role_id") + "") == 40L
										|| Long.valueOf(temp.getMap().get("role_id") + "") == 50L) {
									if ("".equals(s50r40)) {
										s50r40 = temp.getAudit_user() + " " + sdf.format(temp.getAudit_datetime());
									}
								}
							}
							// 业务员
							if (temp.getMap().get("role_id") != null && !temp.getMap().get("role_id").equals("")
									&& temp.getAudit_result() == 1) {
								if (Long.valueOf(temp.getMap().get("role_id") + "") == 60L && "".equals(s60)) {
									s60 = temp.getAudit_user() + " " + sdf.format(temp.getAudit_datetime());
								}
							}

						}
					}
					request.setAttribute("s34", s34);
					request.setAttribute("s39", s39);
					request.setAttribute("s50r40", s50r40);
					request.setAttribute("s60", s60);

				}
				// //////////////////////////单据必须是审核中,审核完结,已作废的才计算//////////////////////////////////////////

				if (konkaOrderInfoDetailsList != null && konkaOrderInfoDetailsList.size() > 0) {
					storeKey = konkaOrderInfoDetailsList.get(0).getStore_key();
				}

				for (KonkaOrderInfoDetails kd : konkaOrderInfoDetailsList) {
					if (kd.getGood_count() != 0) {
						s_order_num += kd.getGood_count();
					}
					if (kd.getGood_discount() == null)
						kd.setGood_discount(new BigDecimal(0));
					if (kd.getGood_sum_price() == null)
						kd.setGood_sum_price(new BigDecimal(0));
					if (kd.getGood_discount_price() == null)
						kd.setGood_discount_price(new BigDecimal(0));

					s_price += kd.getGood_sum_price().doubleValue();
					s_dis_price += (kd.getGood_discount().divide(new BigDecimal(100))).multiply(kd.getGood_sum_price())
							.doubleValue() + kd.getGood_discount_price().doubleValue();
					af_dis_price = kd.getGood_sum_price().doubleValue()
							+ kd.getGood_discount_price().doubleValue()
							+ kd.getGood_sum_price().multiply(kd.getGood_discount().divide(new BigDecimal(100)))
									.doubleValue();
					kd.getMap().put("af_dis_price", af_dis_price);
					s_af_dis_price += af_dis_price;

				}

				konkaOrderInfo.setKonkaOrderInfoDetailsList(konkaOrderInfoDetailsList);
				if (konkaOrderInfo.getR3_id() != null) {
					KonkaR3Zsof konkaR3Zsof = new KonkaR3Zsof();
					konkaR3Zsof.setVbeln(konkaOrderInfo.getR3_id());
					konkaR3Zsof = super.getFacade().getKonkaR3ZsofService().getKonkaR3Zsof(konkaR3Zsof);
					if (konkaR3Zsof != null) {
						los = konkaR3Zsof.getVbedl();
					}
				} else {
					los = "";
				}
				request.setAttribute("los", los);// los 物流号
				request.setAttribute("storeKey", storeKey);// 数量合计
				request.setAttribute("s_order_num", s_order_num);// 数量合计
				// //折扣金额
				// if(null!=konkaOrderInfo.getGood_discount_price() &&
				// GenericValidator.isDouble(konkaOrderInfo.getGood_discount_price().toString())){
				// s_dis_price=Double.parseDouble(konkaOrderInfo.getGood_discount_price().toString());
				// }
				request.setAttribute("s_dis_price", s_dis_price);// 折扣金额合计
				request.setAttribute("s_price", s_price);// 金额合计
				request.setAttribute("s_af_dis_price", s_af_dis_price);// 折后金额合计
				request.setAttribute("totalCount", totalCount);
                request.setAttribute("recordCount", konkaOrderInfoDetailsList.size());// 取出的行数目
				request.setAttribute("orderdate", orderdate);
				request.setAttribute("konkaOrderInfoDetailsList", konkaOrderInfoDetailsList);// 3
				// 直接算出最新的审核人与审核时间,格式:姓名-月/日 时:分
				// 目前a4纸需要打印审核列表
				request.setAttribute("konkaOrderInfoAuditList", konkaOrderInfo.getKonkaOrderInfoAuditList());// 3
			}
			request.setAttribute("fgsDept", fgsDept);// 4
			request.setAttribute("nowDate", nowDate);// 5
			// 如果是a4打印，不需要分页
			if (printType != null && !"".equals(printType.trim()) && printType.equals("a4")) {
                return new ActionForward("/../jxc/KonkaJxcOrderAudit/_print_order_a4.jsp");// 无分页信息
			} else {
                return new ActionForward("/../jxc/KonkaJxcOrderAudit/_print_order.jsp");// 分页打印

			}
		} else {
			this.saveError(request, "errors.long", new String[] { orderId });
			// 如果是a4打印，不需要分页
			if (printType != null && !"".equals(printType.trim()) && printType.equals("a4")) {

				return new ActionForward("/../jxc/KonkaJxcOrderAudit/_print_order_a4.jsp");
			} else {
				return new ActionForward("/../jxc/KonkaJxcOrderAudit/_print_order.jsp");

			}
		}

	}

	public void countPrint(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		DynaBean dynaBean = (DynaBean) form;
		String orderId = (String) dynaBean.get("orderId");
		if (orderId != null && !"".equals(orderId)) {
			KonkaOrderInfo ko = new KonkaOrderInfo();
			ko.setId(Long.valueOf(orderId));
			ko = super.getFacade().getKonkaOrderInfoService().getKonkaOrderInfo(ko);
			if (ko != null) {
				KonkaOrderInfo po = new KonkaOrderInfo();
				int count = ko.getPrintCount() == null ? 0 : ko.getPrintCount();
				po.setPrintCount(count + 1);
				po.setId(ko.getId());
				super.getFacade().getKonkaOrderInfoService().modifyKonkaOrderInfo(po);
			}
		}
	}

}
