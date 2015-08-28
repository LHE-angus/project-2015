package com.ebiz.mmt.web.struts.jxc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
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
import com.ebiz.mmt.domain.KonkaOrderInfoUpdateRecord;
import com.ebiz.mmt.domain.KonkaPePdModelLowestPrice;
import com.ebiz.mmt.domain.MmtEntpShop;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleInfo;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author du,zhiming
 * @version 2011-11-25
 */
public class KonkaJxcOrderAuditAction1 extends BaseJxcAction {
	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (!isSessionLoginForKonkaJxc(request)) {
			return forwardNoSessionForKonkaJxc(request, response);
		}
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;

		PeProdUser user = super.getSessionUserInfo(request);
		Pager pager = (Pager) dynaBean.get("pager");
		String trade_index_like = (String) dynaBean.get("trade_index_like");
		String process_state = (String) dynaBean.get("process_state");// 流程类型
		String audit_state = (String) dynaBean.get("audit_state");// 订单审核状态
		String start_date = (String) dynaBean.get("start_date");
		String end_date = (String) dynaBean.get("end_date");

		if (null == user) {
			return null;
		}

		PeRoleInfo role = super.getPeRoleInfoByUserId(user.getId());
		if (null == role) {
			return null;
		}
		// 根据用户部门id找 所属分公司
		KonkaDept konkaDept = super.getKonkaDeptByUserDeptId(user.getDept_id());
		if (null == konkaDept) {// 此处部门均为分公司及分公司上级部门
			String msg = "对不起，您没有权限审核订单信息!";
			renderJavaScript(response, "alert('" + msg + "');history.back();");
			return null;
		}

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
			// 角色等级

			List<KonkaOrderAuditProcessNode> konkaOrderAuditProcessNodeForLevelList = super
					.getKonkaOrderAuditProcessNodeList(konkaDept.getDept_id(), role.getRole_id());

			if (null != konkaOrderAuditProcessNodeForLevelList && konkaOrderAuditProcessNodeForLevelList.size() > 0) {
				Integer[] andit_level_now_users = new Integer[konkaOrderAuditProcessNodeForLevelList.size()];
				for (int s = 0; s < konkaOrderAuditProcessNodeForLevelList.size(); s++) {
					andit_level_now_users[s] = konkaOrderAuditProcessNodeForLevelList.get(s).getAudit_level();
				}
				/**
				 * ===========在判断【已审核】、【未审核】时===============
				 * andit_level_now_users长度为1时，说明是
				 * 【分公司总经理】只在一个流程中,【此时要加上审核人的id过滤】 长度为
				 * 2时，如果相同，则在两个流程中的审核等级相同那么就是【分公司总经理】审核==之前==的用户； 长度为 2时,
				 * 如果不同，则是【分公司总经理】审核==之后==的用户，【此时要加上审核人的id过滤】
				 */
				if (null != andit_level_now_users && andit_level_now_users.length == 1) {
					konkaOrderInfo.getMap().put("audit_role_id", role.getRole_id());// 当前用户的角色id
					konkaOrderInfo.getMap().put("audit_dept_id", user.getDept_id());// 当前用户所在部门id
				} else if (null != andit_level_now_users && andit_level_now_users.length > 1) {
					for (int i = 0; i < andit_level_now_users.length; i++) {
						for (int j = 0; j < i; j++) {
							// logger.info("andit_level_now_users{}:" +
							// andit_level_now_users[i]
							// + andit_level_now_users[j]);
							if (!andit_level_now_users[i].equals(andit_level_now_users[j])) {
								konkaOrderInfo.getMap().put("audit_role_id", role.getRole_id());// 当前用户的id
								konkaOrderInfo.getMap().put("audit_dept_id", user.getDept_id());// 当前用户的id
							}
						}
					}

				}
				// Integer andit_level_now_user =
				// konkaOrderAuditProcessNodeForLevelList.get(
				// konkaOrderAuditProcessNodeForLevelList.size() -
				// 1).getAudit_level();
				// if (null != andit_level_now_user) {
				// konkaOrderInfo.getMap().put("audit_state_levels",
				// andit_level_now_users);// 当前用户最高等级--判断未审核时使用
				// }
				if (null != andit_level_now_users && andit_level_now_users.length > 0) {
					konkaOrderInfo.getMap().put("audit_state_levels", andit_level_now_users);// 当前用户等级--判断已审核时使用
				}
				// if(null != andit_level_now_users &&
				// andit_level_now_users.length > 1){//当前角色有一个以上等级
				// konkaOrderInfo.getMap().put("audit_state_level_gt_1",
				// andit_level_now_user);// 当前用户最高等级
				// }
				konkaOrderInfo.getMap().put("manager_dept_Id", konkaDept.getDept_id());// 当前用户所属分公司

			}
		}
		List<KonkaOrderInfo> konkaOrderInfoList = new ArrayList<KonkaOrderInfo>();

		// 根据登录的用户角色、部门和流程类型确定审核级别
		Integer audit_level = -1;
		Integer is_update_authority = -1;

		if (StringUtils.isBlank(process_state)) {// 显示全部
			// 根据用户找 所属分公司部门确定订单审核流程定义list
			KonkaOrderAuditProcess konkaOrderAuditProcess = new KonkaOrderAuditProcess();
			konkaOrderAuditProcess.setIs_del(0);
			konkaOrderAuditProcess.setAdd_dept_id(konkaDept.getDept_id());// 分公司的id
			List<KonkaOrderAuditProcess> konkaOrderAuditProcessList = getFacade().getKonkaOrderAuditProcessService()
					.getKonkaOrderAuditProcessList(konkaOrderAuditProcess);
			// 循环list取出，不同流程类型中的订单list，然后合并审核级别
			if (null != konkaOrderAuditProcessList && konkaOrderAuditProcessList.size() > 0) {
				List<KonkaOrderAuditProcessNode> nodeList = new ArrayList<KonkaOrderAuditProcessNode>();
				for (int i = 0; i < konkaOrderAuditProcessList.size(); i++) {
					KonkaOrderAuditProcessNode konkaOrderAuditProcessNode = new KonkaOrderAuditProcessNode();
					konkaOrderAuditProcessNode.setIs_del(0);
					konkaOrderAuditProcessNode.setRole_id(new Long(role.getRole_id()));// 角色
					konkaOrderAuditProcessNode.setAdd_dept_id(konkaDept.getDept_id());// 分公司部门Id
					konkaOrderAuditProcessNode.setAudit_proces_id(konkaOrderAuditProcessList.get(i).getId());
					konkaOrderAuditProcessNode = getFacade().getKonkaOrderAuditProcessNodeService()
							.getKonkaOrderAuditProcessNode(konkaOrderAuditProcessNode);
					if (null != konkaOrderAuditProcessNode) {
						nodeList.add(konkaOrderAuditProcessNode);
					}
				}
				if (null != nodeList && nodeList.size() > 0) {
					Integer[] nodeAuditLevels = new Integer[nodeList.size()];
					for (int i = 0; i < nodeList.size(); i++) {// 取等级 放到数组中
																// 供取订单使用
						nodeAuditLevels[i] = nodeList.get(i).getAudit_level();

					}
					if (null != nodeAuditLevels && nodeAuditLevels.length > 0) {
						if (nodeAuditLevels.length == 1) {// 数组中只有一个等级时
															// 并根据等级等条件取该等级角色列表
							KonkaOrderAuditProcessNode koapnForList = new KonkaOrderAuditProcessNode();
							koapnForList.setIs_del(0);
							koapnForList.setAudit_level(nodeAuditLevels[0]);
							koapnForList.setAdd_dept_id(konkaDept.getDept_id());
							koapnForList.setRole_id(role.getRole_id());
							koapnForList = getFacade().getKonkaOrderAuditProcessNodeService()
									.getKonkaOrderAuditProcessNode(koapnForList);
							if (null != koapnForList) {// 根据角色反取流程
								KonkaOrderAuditProcess koapForList = new KonkaOrderAuditProcess();
								koapForList.setIs_del(0);
								koapForList.setId(koapnForList.getAudit_proces_id());
								koapForList.setAdd_dept_id(konkaDept.getDept_id());
								koapForList = getFacade().getKonkaOrderAuditProcessService().getKonkaOrderAuditProcess(
										koapForList);
								if (null != koapForList) {// 确定流程类型
									konkaOrderInfo.setProcess_state(koapForList.getProcess_type());
								}
							}
							// audit_level = nodeAuditLevels[0];//用户所属角色审核等级
							if (nodeAuditLevels[0] == 1) {// 第一等级
								konkaOrderInfo.getMap().put("salesmanId", user.getId());// 业务员用户id
								konkaOrderInfo.getMap().put("audit_level_eq_1", "true");// 业务员审核
							} else {// 大于一的等级
								konkaOrderInfo.getMap().put("audit_level", (nodeAuditLevels[0] - 1));// 审核级别上一级别
								konkaOrderInfo.getMap().put("manager_dept_Id", konkaDept.getDept_id());// 该部门下面的
								konkaOrderInfo.getMap().put("audit_level_gt_1", "true");// 上一级别审核通过的
							}
						}
						if (nodeAuditLevels.length > 1) {// 有一个以上等级（即该用户所属角色在多个流程里）
							int flag = 0;
							for (int k = 0; k < nodeAuditLevels.length; k++) {
								if (nodeAuditLevels[k] == 1) {
									flag = 1;
								}
							}
							if (flag == 0) {// 不存在第一等级
								Integer[] nodeAuditLevelslow = new Integer[nodeAuditLevels.length];
								List<Long> orderIdsList = new ArrayList<Long>();// 订单id列表
								for (int j = 0; j < nodeAuditLevels.length; j++) {
									nodeAuditLevelslow[j] = nodeAuditLevels[j] - 1;
									konkaOrderInfo.getMap().put("audit_level_type", nodeAuditLevelslow[j]);
									/*******************************
									 * 取当前级别对应的下一级单位审核并订单对应的流程上级节点为当前等级通过的 订单
									 * start
									 *************************************/
									KonkaOrderInfoAudit konkaOrderInfoAudit = new KonkaOrderInfoAudit();
									konkaOrderInfoAudit.setAudit_level(nodeAuditLevelslow[j]);// 下一级别
									konkaOrderInfoAudit.setAudit_result(1);// 审核通过
									// konkaOrderInfoAudit.setAudit_dept_id(user.get)
									// 通过传递部门信息sql过滤
									List<KonkaOrderInfoAudit> konkaOrderInfoAuditList = getFacade()
											.getKonkaOrderInfoAuditService().getKonkaOrderInfoAuditList(
													konkaOrderInfoAudit);
									if (null != konkaOrderInfoAuditList && konkaOrderInfoAuditList.size() > 0) {
										for (int l = 0; l < konkaOrderInfoAuditList.size(); l++) {// 根据订单审核信息取订单Id并放入orderIds
											// 根据id取出订单
											KonkaOrderInfo konkaOrderInfoForProcessType = new KonkaOrderInfo();
											logger.info("id---" + konkaOrderInfoAuditList.get(l).getLink_id());
											konkaOrderInfoForProcessType.setId(konkaOrderInfoAuditList.get(l)
													.getLink_id());
											konkaOrderInfoForProcessType = getFacade().getKonkaOrderInfoService()
													.getKonkaOrderInfo(konkaOrderInfoForProcessType);

											if (null != konkaOrderInfoForProcessType) {// 根据订单类型
																						// 获取该类型流程的上级节点信息
												KonkaOrderAuditProcess koapForLevel = new KonkaOrderAuditProcess();
												koapForLevel.setIs_del(0);
												koapForLevel.setProcess_type(konkaOrderInfoForProcessType
														.getProcess_state());
												koapForLevel.setAdd_dept_id(konkaDept.getDept_id());
												koapForLevel = getFacade().getKonkaOrderAuditProcessService()
														.getKonkaOrderAuditProcess(koapForLevel);
												if (null != koapForLevel) {// 根据审核流程取当前节点角色
													KonkaOrderAuditProcessNode konkaOrderAuditProcessNodeForRole = new KonkaOrderAuditProcessNode();
													konkaOrderAuditProcessNodeForRole.setIs_del(0);
													konkaOrderAuditProcessNodeForRole.setAudit_proces_id(koapForLevel
															.getId());// 流程id
													konkaOrderAuditProcessNodeForRole.setAdd_dept_id(konkaDept
															.getDept_id());// 分公司部门Id
													konkaOrderAuditProcessNodeForRole
															.setAudit_level(nodeAuditLevels[j]);
													List<KonkaOrderAuditProcessNode> konkaOrderAuditProcessNodeForRoleList = getFacade()
															.getKonkaOrderAuditProcessNodeService()
															.getKonkaOrderAuditProcessNodeList(
																	konkaOrderAuditProcessNodeForRole);
													if (null != konkaOrderAuditProcessNodeForRoleList
															&& konkaOrderAuditProcessNodeForRoleList.size() > 0) {
														for (KonkaOrderAuditProcessNode node : konkaOrderAuditProcessNodeForRoleList) {
															if (node.getRole_id().intValue() == role.getRole_id()
																	.intValue()) {// 上级节点角色相等放入订单id列表中
																orderIdsList.add(konkaOrderInfoAuditList.get(l)
																		.getLink_id());
															}
														}
													}

												}
											}

										}

									}

									/*******************************
									 * 取当前级别对应的下一级单位审核并订单对应的流程上级节点为当前等级通过的 订单
									 * end
									 *************************************/
								}
								if (null != orderIdsList && orderIdsList.size() > 0) {
									Long[] orderIds = new Long[orderIdsList.size()];
									for (int p = 0; p < orderIdsList.size(); p++) {
										orderIds[p] = orderIdsList.get(p);
									}
									konkaOrderInfo.getMap().put("orderIds", orderIds);

								}
								konkaOrderInfo.getMap().put("audit_level", nodeAuditLevelslow);// 审核级别上一级别
								konkaOrderInfo.getMap().put("manager_dept_Id", konkaDept.getDept_id());// 该部门下面的
								konkaOrderInfo.getMap().put("audit_level_gt_1", "true");// 上一级别审核通过的
								konkaOrderInfo.getMap().put("is_all", "true");// 有两种级别且都为第一级别以上

							} else {// 等级中存在第一等级
								int flag_level = 0;
								for (int q = 0; q < nodeAuditLevels.length; q++) {// 有等级不为1的情况
									if (nodeAuditLevels[q] != 1) {
										flag_level = 1;
									}
								}
								if (flag_level == 0) {// 等级均为1

									konkaOrderInfo.getMap().put("salesmanId", user.getId());// 业务员用户id
									konkaOrderInfo.getMap().put("audit_level_eq_1", "true");// 业务员审核
								} else {// 等级存在1且有其他等级（此情况业务上不存在暂作异常处理）
									msg = "请联系您所在的分公司管理员，检查流程定义!";
									request.setAttribute("msg", msg);
									pager.init(0L, pager.getPageSize(), pager.getRequestPage());
									return mapping.findForward("list");
								}
							}

						}

					} else {
						msg = "您所在的用户角色尚未分配审核等级，没有权限审核订单!";
						request.setAttribute("msg", msg);
						pager.init(0L, pager.getPageSize(), pager.getRequestPage());
						return mapping.findForward("list");
					}

				} else {
					// 没有权限审核该类型订单，重新选择流程类型
					msg = "您所在的用户角色不在审核流程里，没有权限审核订单!";
					request.setAttribute("msg", msg);
					pager.init(0L, pager.getPageSize(), pager.getRequestPage());
					return mapping.findForward("list");
				}

			} else {
				// 没有权限审核该类型订单，重新选择流程类型
				msg = "您所在的用户角色不在审核流程里，没有权限审核订单!";
				request.setAttribute("msg", msg);
				pager.init(0L, pager.getPageSize(), pager.getRequestPage());
				return mapping.findForward("list");
			}
			Long recordCount = getFacade().getKonkaOrderInfoService().getKonkaOrderInfoCount(konkaOrderInfo);
			pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
			konkaOrderInfo.getRow().setFirst(pager.getFirstRow());
			konkaOrderInfo.getRow().setCount(pager.getRowCount());
			konkaOrderInfoList = super.getFacade().getKonkaOrderInfoService()
					.getKonkaOrderInfoPaginatedListWithShopName(konkaOrderInfo);
			// 循环取审核级别
			if (null != konkaOrderInfoList && konkaOrderInfoList.size() > 0) {
				Integer process_state_order = null;
				for (KonkaOrderInfo koi : konkaOrderInfoList) {
					koi.getMap().put("max_audit_level",
							super.getKonkaOrderAuditProcessNodeMaxLevel(konkaDept.getDept_id(), new Long(koi.getId())));// 该订单对应的最高审核级别,判断审核状态
					KonkaOrderAuditProcess koap = new KonkaOrderAuditProcess();
					koap.setIs_del(0);
					koap.setProcess_type(koi.getProcess_state());
					koap.setAdd_dept_id(konkaDept.getDept_id());
					koap = getFacade().getKonkaOrderAuditProcessService().getKonkaOrderAuditProcess(koap);
					if (null != koap) {
						KonkaOrderAuditProcessNode koapn = new KonkaOrderAuditProcessNode();
						koapn.setIs_del(0);
						koapn.setAudit_proces_id(koap.getId());
						koapn.setAdd_dept_id(konkaDept.getDept_id());
						koapn.setRole_id(role.getRole_id());
						koapn = getFacade().getKonkaOrderAuditProcessNodeService().getKonkaOrderAuditProcessNode(koapn);
						if (null != koapn) {
							audit_level = koapn.getAudit_level();
							is_update_authority = koapn.getIs_update_authority();
						}
						process_state_order = koi.getProcess_state();
					}

					koi.getMap().put("audit_level", audit_level);
					koi.getMap().put("is_update_authority", is_update_authority);
					koi.getMap().put("process_state", process_state_order);
				}
			}

		} else {// 根据选择的条件显示

			// 订单审核流程定义
			KonkaOrderAuditProcess konkaOrderAuditProcess = new KonkaOrderAuditProcess();
			konkaOrderAuditProcess.setIs_del(0);
			konkaOrderAuditProcess.setAdd_dept_id(konkaDept.getDept_id());// 分公司的id
			konkaOrderAuditProcess.setProcess_type(new Integer(process_state));
			konkaOrderAuditProcess = getFacade().getKonkaOrderAuditProcessService().getKonkaOrderAuditProcess(
					konkaOrderAuditProcess);

			if (null != konkaOrderAuditProcess) {
				// 订单审核流程节点
				KonkaOrderAuditProcessNode konkaOrderAuditProcessNode = new KonkaOrderAuditProcessNode();
				konkaOrderAuditProcessNode.setIs_del(0);
				konkaOrderAuditProcessNode.setAudit_proces_id(konkaOrderAuditProcess.getId());
				konkaOrderAuditProcessNode.setRole_id(role.getRole_id());// 角色
				konkaOrderAuditProcessNode.setAdd_dept_id(konkaDept.getDept_id());// 分公司的id
				konkaOrderAuditProcessNode = getFacade().getKonkaOrderAuditProcessNodeService()
						.getKonkaOrderAuditProcessNode(konkaOrderAuditProcessNode);
				if (null != konkaOrderAuditProcessNode) {
					audit_level = konkaOrderAuditProcessNode.getAudit_level();// 审核级别
					is_update_authority = konkaOrderAuditProcessNode.getIs_update_authority();// 修改的权限
				} else {
					// 没有权限审核该类型订单，重新选择流程类型
					msg = "您没有权限审核该类型订单，重新选择流程类型!";
					request.setAttribute("msg", msg);
					pager.init(0L, pager.getPageSize(), pager.getRequestPage());
					return mapping.findForward("list");
				}
			} else {
				// 没有权限审核该类型订单，重新选择流程类型
				msg = "您没有权限审核该类型订单，重新选择流程类型!";
				request.setAttribute("msg", msg);
				pager.init(0L, pager.getPageSize(), pager.getRequestPage());
				return mapping.findForward("list");
			}

			konkaOrderInfo.setProcess_state(new Integer(process_state));// 流程类型

			// 根据审核级别，来取订单信息，显示列表
			if (audit_level == 1) {
				konkaOrderInfo.getMap().put("salesmanId", user.getId());// 业务员用户id
				konkaOrderInfo.getMap().put("audit_level_eq_1", "true");// 业务员审核
			} else if (audit_level > 1) {
				konkaOrderInfo.getMap().put("audit_level", (audit_level - 1));// 审核级别上一级别
				konkaOrderInfo.getMap().put("manager_dept_Id", konkaDept.getDept_id());// 该部门下面的
				konkaOrderInfo.getMap().put("audit_level_gt_1", "true");// 上一级别审核通过的
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
					koi.getMap().put("max_audit_level",
							super.getKonkaOrderAuditProcessNodeMaxLevel(konkaDept.getDept_id(), new Long(koi.getId())));// 该订单对应的最高审核级别,判断审核状态
					koi.getMap().put("audit_level", audit_level);
					koi.getMap().put("process_state", process_state);
					koi.getMap().put("is_update_authority", is_update_authority);
				}
			}
			// dynaBean.set("process_state", process_state);
			// dynaBean.set("audit_level", audit_level.toString());
			// dynaBean.set("is_update_authority",
			// is_update_authority.toString());

		}
		request.setAttribute("entityList", konkaOrderInfoList);
		dynaBean.set("start_date", start_date);
		dynaBean.set("end_date", end_date);
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
		if (GenericValidator.isLong(id)) {
			// 订单基本信息
			KonkaOrderInfo konkaOrderInfo = new KonkaOrderInfo();
			konkaOrderInfo.setId(new Long(id));
			konkaOrderInfo = getFacade().getKonkaOrderInfoService().getKonkaOrderInfo(konkaOrderInfo);
			// 取订单网点名称
			MmtEntpShop mmtEntpShop = new MmtEntpShop();
			mmtEntpShop.setShop_id(konkaOrderInfo.getShop_id());
			mmtEntpShop = getFacade().getMmtEntpShopService().getMmtEntpShop(mmtEntpShop);
			if (mmtEntpShop != null) {
				konkaOrderInfo.getMap().put("shop_name", mmtEntpShop.getShop_name());
			}

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
				request.setAttribute("konkaOrderInfoDetailsList", konkaOrderInfoDetailsList);
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
		// 审核===添加审核记录、修改订单明细、修改订单（同时型号根据价格，修改流程类型）
		PeProdUser user = super.getSessionUserInfo(request);
		// String process_state = (String) dynaBean.get("process_state");
		String id = (String) dynaBean.get("id");
		String trade_index = (String) dynaBean.get("trade_index");
		String mod_id = (String) dynaBean.get("mod_id");
		String audit_level = (String) dynaBean.get("audit_level");
		String audit_result = (String) dynaBean.get("audit_result");// 审核结果
		String audit_comment = (String) dynaBean.get("audit_comment");// 审核评语
		String[] good_counts = request.getParameterValues("good_counts");// 数量
		String[] good_prices = request.getParameterValues("good_prices");// 单价
		String[] order_details_ids = request.getParameterValues("order_details_ids");// 订单详细Ids
		String[] good_discounts = request.getParameterValues("good_discounts");// 折扣

		if (null == user) {
			return null;
		}
		PeRoleInfo role = super.getPeRoleInfoByUserId(user.getId());

		if (GenericValidator.isLong(id)) {
			KonkaDept konkaDept = super.getKonkaDeptById(user.getDept_id());
			String dept_name = "";// 审核人部门名称
			if (konkaDept != null && konkaDept.getDept_name() != null) {
				dept_name = konkaDept.getDept_name();
			}
			KonkaOrderInfo konkaOrderInfo = new KonkaOrderInfo();
			konkaOrderInfo.setId(new Long(id));
			super.copyProperties(konkaOrderInfo, form);

			KonkaOrderInfoAudit konkaOrderInfoAudit = new KonkaOrderInfoAudit();
			if (StringUtils.isNotBlank(audit_level)) {// 添加审核记录
				konkaOrderInfoAudit.setLink_id(new Long(id));
				konkaOrderInfoAudit.setAudit_user_id(user.getId());
				konkaOrderInfoAudit.setAudit_user(user.getUser_name());
				konkaOrderInfoAudit.setAudit_dept_id(user.getDept_id());
				konkaOrderInfoAudit.setAudit_dept_name(dept_name);
				konkaOrderInfoAudit.setAudit_result(new Integer(audit_result));
				konkaOrderInfoAudit.setAudit_comment(audit_comment);
				konkaOrderInfoAudit.setAudit_level(new Integer(audit_level));// 审核级别
			}
			konkaOrderInfo.setKonkaOrderInfoAudit(konkaOrderInfoAudit);

			int order_num = 0;// 订单数量
			BigDecimal money = new BigDecimal(0);// 订单金额
			BigDecimal good_discount_price = new BigDecimal(0);// 商品折扣金额

			// 订单明细中有一个类型的单价低于最低限价，就确定该订单为特殊订单
			Integer process_state2 = 1;
			List<KonkaOrderInfoDetails> konkaOrderInfoDetailsList = new ArrayList<KonkaOrderInfoDetails>();
			List<KonkaOrderInfoUpdateRecord> konkaOrderInfoUpdateRecordList = new ArrayList<KonkaOrderInfoUpdateRecord>();
			if (order_details_ids != null && order_details_ids.length > 0) {
				for (int i = 0; i < order_details_ids.length; i++) {

					KonkaOrderInfoDetails konkaOrderInfoDetails = new KonkaOrderInfoDetails();
					konkaOrderInfoDetails.setId(new Long(order_details_ids[i]));
					konkaOrderInfoDetails = getFacade().getKonkaOrderInfoDetailsService().getKonkaOrderInfoDetails(
							konkaOrderInfoDetails);
					int update_type = 0;// 修改类型
					if (Integer.valueOf(good_counts[i] == null ? "0" : good_counts[i]) != konkaOrderInfoDetails
							.getGood_count().intValue()) {// 修改数量
						update_type = 1;
						if (konkaOrderInfoDetails.getGood_price().doubleValue() != Double
								.valueOf(good_prices[i] == null ? "0.00" : good_prices[i])) {// 修改价格和数量
							update_type = 3;

						}

					}
					if (konkaOrderInfoDetails.getGood_price().doubleValue() != Double
							.valueOf(good_prices[i] == null ? "0.00" : good_prices[i])) {// 修改价格
						update_type = 2;
						if (Integer.valueOf(good_counts[i] == null ? "0" : good_counts[i]) != konkaOrderInfoDetails
								.getGood_count().intValue()) {// 修改价格和数量
							update_type = 3;
						}

					}

					if (update_type > 0) {// 有修改
						KonkaOrderInfoUpdateRecord konkaOrderInfoUpdateRecord = new KonkaOrderInfoUpdateRecord();
						konkaOrderInfoUpdateRecord.setOrder_id(new Long(id));
						konkaOrderInfoUpdateRecord.setTrade_index(trade_index);
						konkaOrderInfoUpdateRecord.setPd_type_id(konkaOrderInfoDetails.getPd_type_id());
						konkaOrderInfoUpdateRecord.setPd_type_name(konkaOrderInfoDetails.getPd_type_name());
						konkaOrderInfoUpdateRecord.setBrand_id(konkaOrderInfoDetails.getBrand_id());
						konkaOrderInfoUpdateRecord.setBrand_name(konkaOrderInfoDetails.getBrand_name());
						konkaOrderInfoUpdateRecord.setPd_id(konkaOrderInfoDetails.getPd_id());
						konkaOrderInfoUpdateRecord.setPd_name(konkaOrderInfoDetails.getPd_name());

						if (update_type == 1) {// 只修改数量
							konkaOrderInfoUpdateRecord.setUpdate_type(1);
							konkaOrderInfoUpdateRecord.setNum_before(new Long(konkaOrderInfoDetails.getGood_count()));
							konkaOrderInfoUpdateRecord.setNum_after(new Long(good_counts[i] == null ? "0"
									: good_counts[i]));
						}
						if (update_type == 2) {// 修改价格
							konkaOrderInfoUpdateRecord.setUpdate_type(1);
							konkaOrderInfoUpdateRecord.setPrice_after(new BigDecimal(Double
									.valueOf(good_prices[i] == null ? "0.00" : good_prices[i])));
							konkaOrderInfoUpdateRecord.setPrice_before(konkaOrderInfoDetails.getGood_price());
						}
						if (update_type == 3) {// 修改价格和数量
							konkaOrderInfoUpdateRecord.setUpdate_type(3);
							konkaOrderInfoUpdateRecord.setPrice_after(new BigDecimal(Double
									.valueOf(good_prices[i] == null ? "0.00" : good_prices[i])));
							konkaOrderInfoUpdateRecord.setPrice_before(konkaOrderInfoDetails.getGood_price());
							konkaOrderInfoUpdateRecord.setNum_before(new Long(konkaOrderInfoDetails.getGood_count()));
							konkaOrderInfoUpdateRecord.setNum_after(new Long(good_counts[i] == null ? "0"
									: good_counts[i]));
						}
						konkaOrderInfoUpdateRecord.setUpdate_user_id(user.getId());
						konkaOrderInfoUpdateRecord.setUpdate_user_name(user.getUser_name());
						konkaOrderInfoUpdateRecord.setUpdate_user_dept_id(user.getDept_id());
						konkaOrderInfoUpdateRecord.setUpdate_user_dept_name(dept_name);
						konkaOrderInfoUpdateRecord.setUpdate_role_id(role.getRole_id());
						konkaOrderInfoUpdateRecord.setUpdate_role_name(role.getRole_name());
						konkaOrderInfoUpdateRecordList.add(konkaOrderInfoUpdateRecord);
						konkaOrderInfo.setKonkaOrderInfoUpdateRecordList(konkaOrderInfoUpdateRecordList);

					}

					konkaOrderInfoDetails.setGood_count(Integer.valueOf(good_counts[i] == null ? "0" : good_counts[i]));
					konkaOrderInfoDetails.setGood_price(new BigDecimal(Double.valueOf(good_prices[i] == null ? "0.00"
							: good_prices[i])));
					konkaOrderInfoDetails.setGood_sum_price(new BigDecimal(konkaOrderInfoDetails.getGood_price()
							.doubleValue()).multiply(new BigDecimal(Integer.valueOf(good_counts[i] == null ? "0"
							: good_counts[i]))));
					konkaOrderInfoDetails.setGood_discount_price(konkaOrderInfoDetails.getGood_sum_price().multiply(
							new BigDecimal(good_discounts[i]).divide(new BigDecimal(100))));
					konkaOrderInfoDetailsList.add(konkaOrderInfoDetails);

					// 根据大类、品牌、型号、部门、年份和月份确定流程类型
					KonkaPePdModelLowestPrice konkaPePdModelLowestPrice = new KonkaPePdModelLowestPrice();
					konkaPePdModelLowestPrice.setPd_type_id(konkaOrderInfoDetails.getPd_type_id());
					konkaPePdModelLowestPrice.setBrand_id(konkaOrderInfoDetails.getBrand_id());
					konkaPePdModelLowestPrice.setPd_id(konkaOrderInfoDetails.getPd_id());
					konkaPePdModelLowestPrice.setIs_del(0);
					// 根据用户部门id找 所属分公司id
					KonkaDept kd = new KonkaDept();
					kd.getMap().put("dept_id_for_fgs", user.getDept_id());
					kd = getFacade().getKonkaDeptService().getKonkaDept(kd);
					konkaPePdModelLowestPrice.setAdd_dept_id(kd.getDept_id());

					Calendar c = Calendar.getInstance();
					konkaPePdModelLowestPrice.setSet_year(c.get(Calendar.YEAR));
					konkaPePdModelLowestPrice.setSet_month(c.get(Calendar.MONTH) + 1);
					konkaPePdModelLowestPrice = getFacade().getKonkaPePdModelLowestPriceService()
							.getKonkaPePdModelLowestPrice(konkaPePdModelLowestPrice);
					// 判断单价和最低限价的大小
					if (null != konkaPePdModelLowestPrice
							&& konkaPePdModelLowestPrice.getLowest_price().doubleValue() > konkaOrderInfoDetails
									.getGood_price().doubleValue()) {
						process_state2 = 2;

					} else {
						process_state2 = 1;
					}

					order_num += konkaOrderInfoDetails.getGood_count().intValue();
					money = money.add(konkaOrderInfoDetails.getGood_sum_price());
					good_discount_price = good_discount_price.add(konkaOrderInfoDetails.getGood_discount_price());
				}
				konkaOrderInfo.setKonkaOrderInfoDetailsList(konkaOrderInfoDetailsList);
			}

			// 修改订单的流程类型

			konkaOrderInfo.setProcess_state(process_state2);// 流程类型
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

		} else {
			this.saveError(request, "errors.long", new String[] { id });
			return null;
		}
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (!isSessionLoginForKonkaJxc(request)) {
			return forwardNoSessionForKonkaJxc(request, response);
		}
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;

		PeProdUser user = super.getSessionUserInfo(request);
		String audit_level = (String) dynaBean.get("audit_level");
		String id = (String) dynaBean.get("id");
		if (null == user) {
			return null;
		}
		// 根据用户部门id找 所属分公司
		KonkaDept konkaDept = super.getKonkaDeptByUserDeptId(user.getDept_id());
		if (null == konkaDept) {
			return null;
		}
		if (GenericValidator.isLong(id)) {
			// 订单基本信息
			KonkaOrderInfo konkaOrderInfo = new KonkaOrderInfo();
			konkaOrderInfo.setId(new Long(id));
			konkaOrderInfo = getFacade().getKonkaOrderInfoService().getKonkaOrderInfo(konkaOrderInfo);
			// 取订单网点名称
			MmtEntpShop mmtEntpShop = new MmtEntpShop();
			mmtEntpShop.setShop_id(konkaOrderInfo.getShop_id());
			mmtEntpShop = getFacade().getMmtEntpShopService().getMmtEntpShop(mmtEntpShop);
			if (mmtEntpShop != null) {
				konkaOrderInfo.getMap().put("shop_name", mmtEntpShop.getShop_name());
			}

			// KonkaOrderInfoAudit konkaOrderInfoAudit = new
			// KonkaOrderInfoAudit();
			// konkaOrderInfoAudit.setLink_id(konkaOrderInfo.getId());
			List<KonkaOrderInfoAudit> konkaOrderInfoAuditList = super.getKonkaOrderInfoAuditWithRoleInfoList(new Long(
					id));
			if (null != konkaOrderInfoAuditList && konkaOrderInfoAuditList.size() > 0) {
				konkaOrderInfo.setKonkaOrderInfoAuditList(konkaOrderInfoAuditList);
			}
			konkaOrderInfo.getMap().put("max_audit_level",
					super.getKonkaOrderAuditProcessNodeMaxLevel(konkaDept.getDept_id(), new Long(id)));// 该订单对应的最高审核级别,判断审核状态
			super.copyProperties(form, konkaOrderInfo);
			if (null != konkaOrderInfo) {
				// 订单详细信息
				KonkaOrderInfoDetails konkaOrderInfoDetails = new KonkaOrderInfoDetails();
				konkaOrderInfoDetails.setOrder_id(konkaOrderInfo.getId());
				List<KonkaOrderInfoDetails> konkaOrderInfoDetailsList = getFacade().getKonkaOrderInfoDetailsService()
						.getKonkaOrderInfoDetailsList(konkaOrderInfoDetails);
				request.setAttribute("konkaOrderInfoDetailsList", konkaOrderInfoDetailsList);
			}
			dynaBean.set("audit_level", audit_level);
			return mapping.findForward("view");
		} else {
			this.saveError(request, "errors.long", new String[] { id });
			return new ActionForward("/KonkaJxcOrderAudit/list.jsp");
		}
	}
}
