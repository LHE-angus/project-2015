package com.ebiz.mmt.web.struts.jxc;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.validator.GenericValidator;
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
import com.ebiz.mmt.domain.KonkaR3MmtMatch;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleInfo;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Zhang,Xufeng
 * @version 2011-12-12
 */
public class KonkaJxcOrderInfoViewAction extends BaseJxcAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
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
		String start_date = (String) dynaBean.get("start_date");
		String end_date = (String) dynaBean.get("end_date");
		String customer_name_like = (String) dynaBean.get("customer_name_like");// 客户名称模糊搜索
		String fgs_dept_id = (String) dynaBean.get("fgs_dept_id");
		String jyb_dept_id = (String) dynaBean.get("jyb_dept_id");
		String bsc_dept_id = (String) dynaBean.get("bsc_dept_id");

		if (null == user) {
			return null;
		}

		PeRoleInfo role = super.getPeRoleInfoByUserId(user.getId());
		if (null == role) {
			return null;
		}
		// 1、事业部，admin角色
		// 2、分公司级别的角色
		if (!(null != role.getRole_id() && role.getRole_id().intValue() < Constants.ROLE_ID_JYB)) {
			String msg = "对不起您没有权限查看订单信息!";
			renderJavaScript(response, "alert('" + msg + "');history.back();");
			return null;
		}

		request.setAttribute("sybDeptInfoList", super.getDeptInfoList(mapping, form, request, response, null));

		// 初始化列表页面的起始时间
		Date now = DateUtils.parseDate("2013-08-09", new String[] { "yyyy-MM-dd" });

		if (StringUtils.isBlank(start_date)) {
			start_date = DateFormatUtils.format(now, "yyyy-MM-") + "01 00:00:00";
		}
		if (StringUtils.isBlank(end_date)) {
			end_date = DateFormatUtils.format(now, "yyyy-MM-dd") + " 00:00:00";
		}

		KonkaOrderInfo konkaOrderInfo = new KonkaOrderInfo();
		super.copyProperties(konkaOrderInfo, form);

		/** add 20120305===== */
		konkaOrderInfo.setIs_del(0);// 未删除
		konkaOrderInfo.setIs_submit(1);// 已提交
		// konkaOrderInfo.setIs_end(0);// 未完结
		/** add 20120305===== */
		konkaOrderInfo.getMap().put("trade_index_like", trade_index_like);
		konkaOrderInfo.getMap().put("start_date", start_date);
		konkaOrderInfo.getMap().put("end_date", end_date);
		// 搜索条件
		konkaOrderInfo.getMap().put("customer_name_like", customer_name_like);
		if (StringUtils.isNotBlank(fgs_dept_id) || StringUtils.isNotBlank(jyb_dept_id)
				|| StringUtils.isNotBlank(bsc_dept_id)) {
			konkaOrderInfo.getMap().put("order_view_for_search", "true");
			konkaOrderInfo.getMap().put("fgs_dept_id_for_search", fgs_dept_id);
			konkaOrderInfo.getMap().put("jyb_dept_id_for_search", jyb_dept_id);
			konkaOrderInfo.getMap().put("bsc_dept_id_for_search", bsc_dept_id);
		}

		if (role.getRole_id().intValue() >= Constants.ROLE_ID_FGS
				&& role.getRole_id().intValue() < Constants.ROLE_ID_JYB) {// 分公司级别，查看订单
			konkaOrderInfo.getMap().put("order_view_fgs", "true");
			KonkaDept fgsDept = super.getKonkaDeptByUserDeptId(user.getDept_id());
			if (null == fgsDept) {
				return null;
			}
			konkaOrderInfo.getMap().put("fgs_dept_id", fgsDept.getDept_id());// 分公司id
		}

		konkaOrderInfo.getMap().put("add_date_lt", "2013/8/9 14:10:51");

		Long recordCount = super.getFacade().getKonkaOrderInfoService().getKonkaOrderInfoCount(konkaOrderInfo);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		konkaOrderInfo.getRow().setFirst(pager.getFirstRow());
		konkaOrderInfo.getRow().setCount(pager.getRowCount());

		List<KonkaOrderInfo> konkaOrderInfoList = super.getFacade().getKonkaOrderInfoService()
				.getKonkaOrderInfoPaginatedListWithShopName(konkaOrderInfo);

		// 确定每个订单当前审核角色
		// 1、有审核记录（记录中最高等级的后一步审核角色，要判断是否在最后一步【根据audit_state】）
		// 2、无审核记录（该网点分配的用户角色）
		for (KonkaOrderInfo o : konkaOrderInfoList) {
			// 根据订单id，取所对应的分公司
			KonkaDept konkaDeptForFGS = super.getKonkaFGSByOrderId(o.getId());
			// 取订单当前所处的审核流程(随时可能变动)
			KonkaOrderAuditProcess process = new KonkaOrderAuditProcess();
			process.setAdd_dept_id(konkaDeptForFGS.getDept_id());//
			process.setProcess_type(o.getProcess_state());
			process.setIs_del(0);
			process = getFacade().getKonkaOrderAuditProcessService().getKonkaOrderAuditProcess(process);

			if (null == process) {
				// 审核流程还没定义
				o.getMap().put("audit_role_name", "等待审核");
				continue;
			}

			List<KonkaOrderInfoAudit> list = super.getKonkaOrderInfoAuditWithRoleInfoList(o.getId());// 订单审核信息列表
			if (o.getAudit_state() != 0 && null != list && list.size() > 0) {
				// 有审核
				if (o.getAudit_state() == 1) {// 审核中，显示当前审核角色的 下一步审核角色
					KonkaOrderAuditProcessNode entity = new KonkaOrderAuditProcessNode();
					entity.setAudit_proces_id(process.getId());
					// 审核等级，最后一级
					Long process_max_level = super.getKonkaOrderAuditProcessNodeMaxLevel(konkaDeptForFGS.getDept_id(),
							new Long(o.getId()));// 流程最高级别
					if (process_max_level == list.get(0).getAudit_level().longValue()) {

						entity.setAudit_level(list.get(0).getAudit_level());
					} else {
						entity.setAudit_level(list.get(0).getAudit_level() + 1);
					}

					List<KonkaOrderAuditProcessNode> nextKonkaOrderAuditProcessNodeList = getFacade()
							.getKonkaOrderAuditProcessNodeService().getKonkaOrderAuditProcessNodeList(entity);
					String[] nextRoleNames = new String[nextKonkaOrderAuditProcessNodeList.size()];
					for (int i = 0; i < nextKonkaOrderAuditProcessNodeList.size(); i++) {
						nextRoleNames[i] = nextKonkaOrderAuditProcessNodeList.get(i).getRole_name();
					}
					o.getMap().put("audit_role_name", StringUtils.join(nextRoleNames, ","));

				} else if (o.getAudit_state() == 2 || o.getAudit_state() == 3) {// 审核通过或者未通过，显示最后审核角色
					o.getMap().put("audit_role_name", list.get(0).getMap().get("role_name"));
				}

			} else {
				// 未审核,显示当前审核角色 即该网点分配的用户角色

				// 获取当前订单的客户
				Long konka_r3_shop_id = null;

				if (null != o.getShop_id()) {
					// 兼容一期的老版本
					KonkaR3MmtMatch konkaR3MmtMatch = new KonkaR3MmtMatch();
					konkaR3MmtMatch.setMmt_shop_id(o.getShop_id());
					konkaR3MmtMatch = getFacade().getKonkaR3MmtMatchService().getKonkaR3MmtMatch(konkaR3MmtMatch);

					if (null != konkaR3MmtMatch) {
						konka_r3_shop_id = konkaR3MmtMatch.getR3_shop_id();
					}
				} else if (null != o.getCust_id()) {
					konka_r3_shop_id = o.getCust_id();
				}

				if (null != konka_r3_shop_id) {
					BranchAssign branchAssign = new BranchAssign();
					branchAssign.setBranch_type(1);
					branchAssign.setKonka_r3_id(konka_r3_shop_id);
					branchAssign = getFacade().getBranchAssignService().getBranchAssign(branchAssign);
					if (null != branchAssign && null != branchAssign.getUser_id()) {
						PeProdUser man = super.getPeProdUserByUserId(branchAssign.getUser_id());
						o.getMap().put("audit_role_name", "业务员（" + man.getUser_name() + "）");
					} else {
						o.getMap().put("audit_role_name", "暂无");
					}
					// if (null != branchAssign) {
					// if (null != branchAssign.getUser_id()) {
					// // 已分配到具体的人员
					// PeRoleUser peRoleUser = new PeRoleUser();
					// // peRoleUser.setUser_id(branchAssign.getUser_id());
					// // peRoleUser =
					// //
					// getFacade().getPeRoleUserService().getPeRoleUser(peRoleUser);
					// peRoleUser =
					// super.getRoleInfoByUserId(branchAssign.getUser_id());
					// if (null != peRoleUser) {// 查找角色名称
					// PeRoleInfo peRoleInfo = new PeRoleInfo();
					// peRoleInfo.setRole_id(peRoleUser.getRole_id());
					// peRoleInfo =
					// getFacade().getPeRoleInfoService().getPeRoleInfo(peRoleInfo);
					// if (null != peRoleInfo) {
					// o.getMap().put("audit_role_name",
					// peRoleInfo.getRole_name());
					// }
					// }
					// } else {
					// // 未分配到具体的人员
					// if (null != branchAssign.getBsc_id() || null !=
					// branchAssign.getJyb_id()
					// || null != branchAssign.getFgs_id()) {
					// // 办事处、经营部、分公司
					// o.getMap().put("audit_role_name", "暂无");
					// }
					// }
					// }
				}
			}
		}

		dynaBean.set("start_date", start_date);
		dynaBean.set("end_date", end_date);
		for (KonkaOrderInfo koi : konkaOrderInfoList) {
			KonkaOrderInfoDetails konkaOrderInfoDetails = new KonkaOrderInfoDetails();
			konkaOrderInfoDetails.setOrder_id(koi.getId());
			List<KonkaOrderInfoDetails> konkaOrderInfoDetailsList = getFacade().getKonkaOrderInfoDetailsService()
					.getKonkaOrderInfoDetailsList(konkaOrderInfoDetails);
			koi.setKonkaOrderInfoDetailsList(konkaOrderInfoDetailsList);
		}
		request.setAttribute("konkaOrderInfoList", konkaOrderInfoList);
		return mapping.findForward("list");

	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (!isSessionLoginForKonkaJxc(request)) {
			return forwardNoSessionForKonkaJxc(request, response);
		}
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String order_id = (String) dynaBean.get("order_id");

		PeProdUser user = super.getSessionUserInfo(request);
		if (null == user) {
			return null;
		}
		if (!GenericValidator.isLong(order_id)) {
			this.saveError(request, "errors.long", new String[] { order_id });
			return new ActionForward("/KonkaJxcOrderInfoView/list.jsp");
		}

		KonkaDept konkaDept = super.getKonkaFGSByOrderId(new Long(order_id));
		if (null == konkaDept) {
			return null;
		}
		// 订单
		KonkaOrderInfo konkaOrderInfo = new KonkaOrderInfo();
		konkaOrderInfo.setId(Long.valueOf(order_id));
		konkaOrderInfo = getFacade().getKonkaOrderInfoService().getKonkaOrderInfo(konkaOrderInfo);
		dynaBean.set("fullName", super.getPIndexFullName(konkaOrderInfo.getUser_p_index()));

		KonkaOrderInfoDetails konkaOrderInfoDetails = new KonkaOrderInfoDetails();
		konkaOrderInfoDetails.setOrder_id(Long.valueOf(order_id));
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

				// 审核等级，最后一级
				Long process_max_level = super.getKonkaOrderAuditProcessNodeMaxLevel(konkaDeptForFGS.getDept_id(),
						new Long(konkaOrderInfo.getId()));// 流程最高级别

				if (list.size() > 0) {
					if (process_max_level == list.get(0).getAudit_level().longValue()) {

						entity.setAudit_level(list.get(0).getAudit_level());
					} else {
						entity.setAudit_level(list.get(0).getAudit_level() + 1);
					}
				}
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

			// 获取当前订单的客户
			Long konka_r3_shop_id = null;
			if (null != konkaOrderInfo.getShop_id()) {
				// 兼容一期的老版本
				KonkaR3MmtMatch konkaR3MmtMatch = new KonkaR3MmtMatch();
				konkaR3MmtMatch.setMmt_shop_id(konkaOrderInfo.getShop_id());
				konkaR3MmtMatch = getFacade().getKonkaR3MmtMatchService().getKonkaR3MmtMatch(konkaR3MmtMatch);

				if (null != konkaR3MmtMatch) {
					konka_r3_shop_id = konkaR3MmtMatch.getR3_shop_id();
				}
			} else if (null != konkaOrderInfo.getCust_id()) {
				konka_r3_shop_id = konkaOrderInfo.getCust_id();
			}

			if (null != konka_r3_shop_id) {
				BranchAssign branchAssign = new BranchAssign();
				branchAssign.setBranch_type(1);
				branchAssign.setKonka_r3_id(konka_r3_shop_id);
				branchAssign = getFacade().getBranchAssignService().getBranchAssign(branchAssign);
				if (null != branchAssign && null != branchAssign.getUser_id()) {
					PeProdUser man = super.getPeProdUserByUserId(branchAssign.getUser_id());
					konkaOrderInfo.getMap().put("audit_role_name", "业务员（" + man.getUser_name() + "）");
				} else {
					konkaOrderInfo.getMap().put("audit_role_name", "暂无");
				}
			}

			// KonkaR3MmtMatch konkaR3MmtMatch = new KonkaR3MmtMatch();
			// konkaR3MmtMatch.setMmt_shop_id(konkaOrderInfo.getShop_id());
			// konkaR3MmtMatch =
			// getFacade().getKonkaR3MmtMatchService().getKonkaR3MmtMatch(konkaR3MmtMatch);
			// if (null != konkaR3MmtMatch) {
			// BranchAssign branchAssign = new BranchAssign();
			// branchAssign.setBranch_type(1);
			// branchAssign.setKonka_r3_id(konkaR3MmtMatch.getR3_shop_id());
			// branchAssign =
			// getFacade().getBranchAssignService().getBranchAssign(branchAssign);
			// if (null != branchAssign) {
			// if (null != branchAssign.getUser_id()) {
			// // 已分配到具体的人员
			// PeRoleUser peRoleUser = new PeRoleUser();
			// // peRoleUser.setUser_id(branchAssign.getUser_id());
			// // peRoleUser =
			// // getFacade().getPeRoleUserService().getPeRoleUser(peRoleUser);
			// peRoleUser =
			// super.getRoleInfoByUserId(branchAssign.getUser_id());
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
			//
			// } else {
			// // 未分配到具体的人员
			// if (null != branchAssign.getBsc_id() || null !=
			// branchAssign.getJyb_id()
			// || null != branchAssign.getFgs_id()) {
			// // 办事处、经营部、分公司
			// konkaOrderInfo.getMap().put("audit_role_name", "暂无");
			// }
			// }
			// }
			// }
		}
		super.copyProperties(form, konkaOrderInfo);
		return mapping.findForward("view");
		/* 订单审核信息 end */

	}

	public ActionForward toDelOrEnd(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (!isSessionLoginForKonkaJxc(request)) {
			return forwardNoSessionForKonkaJxc(request, response);
		}
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String order_id = (String) dynaBean.get("order_id");
		String type_operate = (String) dynaBean.get("type_operate");
		if (null != type_operate && StringUtils.isBlank(type_operate)) {
			dynaBean.set("type_operate", type_operate);
		}

		PeProdUser user = super.getSessionUserInfo(request);
		if (null == user) {
			return null;
		}
		if (GenericValidator.isLong(order_id)) {
			dynaBean.set("id", order_id);
			KonkaDept konkaDept = super.getKonkaFGSByOrderId(new Long(order_id));
			if (null == konkaDept) {
				return null;
			}
			// 订单
			KonkaOrderInfo konkaOrderInfo = new KonkaOrderInfo();
			konkaOrderInfo.setId(Long.valueOf(order_id));
			konkaOrderInfo = getFacade().getKonkaOrderInfoService().getKonkaOrderInfo(konkaOrderInfo);
			dynaBean.set("fullName", super.getPIndexFullName(konkaOrderInfo.getUser_p_index()));

			KonkaOrderInfoDetails konkaOrderInfoDetails = new KonkaOrderInfoDetails();
			konkaOrderInfoDetails.setOrder_id(Long.valueOf(order_id));
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
				if (konkaOrderInfo.getAudit_state() == 1 && null != konkaOrderAuditProcess) {// 审核中，显示当前审核角色的
					// 下一步审核角色

					KonkaOrderAuditProcessNode entity = new KonkaOrderAuditProcessNode();
					entity.setAudit_proces_id(konkaOrderAuditProcess.getId());

					// 审核等级，最后一级
					Long process_max_level = super.getKonkaOrderAuditProcessNodeMaxLevel(konkaDeptForFGS.getDept_id(),
							new Long(konkaOrderInfo.getId()));// 流程最高级别
					if (process_max_level == list.get(0).getAudit_level().longValue()) {

						entity.setAudit_level(list.get(0).getAudit_level());
					} else {
						entity.setAudit_level(list.get(0).getAudit_level() + 1);
					}

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
				Long konka_r3_shop_id = null;
				if (null != konkaOrderInfo.getShop_id()) {
					// 兼容一期的老版本
					KonkaR3MmtMatch konkaR3MmtMatch = new KonkaR3MmtMatch();
					konkaR3MmtMatch.setMmt_shop_id(konkaOrderInfo.getShop_id());
					konkaR3MmtMatch = getFacade().getKonkaR3MmtMatchService().getKonkaR3MmtMatch(konkaR3MmtMatch);

					if (null != konkaR3MmtMatch) {
						konka_r3_shop_id = konkaR3MmtMatch.getR3_shop_id();
					}
				} else if (null != konkaOrderInfo.getCust_id()) {
					konka_r3_shop_id = konkaOrderInfo.getCust_id();
				}
				if (null != konka_r3_shop_id) {
					BranchAssign branchAssign = new BranchAssign();
					branchAssign.setBranch_type(1);
					branchAssign.setKonka_r3_id(konka_r3_shop_id);
					branchAssign = getFacade().getBranchAssignService().getBranchAssign(branchAssign);
					if (null != branchAssign) {

						if (null != branchAssign.getUser_id()) {
							// 已分配到具体的人员
							PeRoleUser peRoleUser = new PeRoleUser();
							// peRoleUser.setUser_id(branchAssign.getUser_id());
							// peRoleUser =
							// getFacade().getPeRoleUserService().getPeRoleUser(peRoleUser);
							peRoleUser = super.getRoleInfoByUserId(branchAssign.getUser_id());
							if (null != peRoleUser) {// 查找角色名称
								PeRoleInfo peRoleInfo = new PeRoleInfo();
								peRoleInfo.setRole_id(peRoleUser.getRole_id());
								peRoleInfo = getFacade().getPeRoleInfoService().getPeRoleInfo(peRoleInfo);
								if (null != peRoleInfo) {
									konkaOrderInfo.getMap().put("audit_role_name", peRoleInfo.getRole_name());
								}
							}
						} else {
							// 未分配到具体的人员
							if (null != branchAssign.getBsc_id() || null != branchAssign.getBsc_id()
									|| null != branchAssign.getBsc_id()) {
								// 办事处、经营部、分公司
								konkaOrderInfo.getMap().put("audit_role_name", "暂无");
							}
						}
					}
				}
			}
			super.copyProperties(form, konkaOrderInfo);
			return new ActionForward("/KonkaJxcOrderInfoView/form_del_or_end.jsp");
			/* 订单审核信息 end */
		} else {
			this.saveError(request, "errors.long", new String[] { order_id });
			return new ActionForward("/KonkaJxcOrderInfoView/list.jsp");
		}
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;

		PeProdUser user = super.getSessionUserInfo(request);
		if (null == user) {
			return null;
		}
		String id = (String) dynaBean.get("id");

		if (!StringUtils.isBlank(id) && GenericValidator.isLong(id)) {
			KonkaOrderInfo entity = new KonkaOrderInfo();
			entity.setId(new Long(id));
			entity.setIs_del(1);
			getFacade().getKonkaOrderInfoService().modifyKonkaOrderInfo(entity);
			saveMessage(request, "entity.updated");
			// the line below is added for pagination
			StringBuffer pathBuffer = new StringBuffer();
			pathBuffer.append(mapping.findForward("success").getPath());
			pathBuffer.append("&");
			pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "id", "method")));
			ActionForward forward = new ActionForward(pathBuffer.toString(), true);
			// end
			return forward;
		} else {
			this.saveError(request, "errors.long", new String[] { id });
			return null;
		}

	}

	public ActionForward end(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;

		PeProdUser user = super.getSessionUserInfo(request);
		if (null == user) {
			return null;
		}
		String id = (String) dynaBean.get("id");

		if (!StringUtils.isBlank(id) && GenericValidator.isLong(id)) {
			KonkaOrderInfo entity = new KonkaOrderInfo();
			entity.setId(new Long(id));
			entity.setIs_end(1);
			getFacade().getKonkaOrderInfoService().modifyKonkaOrderInfo(entity);
			saveMessage(request, "entity.updated");
			// the line below is added for pagination
			StringBuffer pathBuffer = new StringBuffer();
			pathBuffer.append(mapping.findForward("success").getPath());
			pathBuffer.append("&");
			pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "id", "method")));
			ActionForward forward = new ActionForward(pathBuffer.toString(), true);
			// end
			return forward;
		} else {
			this.saveError(request, "errors.long", new String[] { id });
			return null;
		}

	}

	public ActionForward print(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;

		PeProdUser user = super.getSessionUserInfo(request);
		String id = (String) dynaBean.get("id");
		if (null == user) {
			return null;
		}
		// 根据用户部门id找 所属分公司
		KonkaDept fgsDept = super.getKonkaDeptByUserDeptId(user.getDept_id());
		if (null == fgsDept) {
			return null;
		}
		String nowDate = DateFormatUtils.format(new Date(), "yyyy年MM月dd日 HH:mm:ss");
		if (GenericValidator.isLong(id)) {
			// 订单基本信息
			KonkaOrderInfo konkaOrderInfo = new KonkaOrderInfo();
			konkaOrderInfo.setId(new Long(id));
			konkaOrderInfo = getFacade().getKonkaOrderInfoService().getKonkaOrderInfo(konkaOrderInfo);
			//System.out.println(konkaOrderInfo.toString());
			if (null != konkaOrderInfo) {
				// 取订单对应的买卖提网点--匹配的R3网点
				Long konka_r3_shop_id = null;
				if (null != konkaOrderInfo.getShop_id()) {
					// 兼容一期的老版本
					KonkaR3MmtMatch konkaR3MmtMatch = new KonkaR3MmtMatch();
					konkaR3MmtMatch.setMmt_shop_id(konkaOrderInfo.getShop_id());
					konkaR3MmtMatch = getFacade().getKonkaR3MmtMatchService().getKonkaR3MmtMatch(konkaR3MmtMatch);

					if (null != konkaR3MmtMatch) {
						konka_r3_shop_id = konkaR3MmtMatch.getR3_shop_id();
					}
				} else if (null != konkaOrderInfo.getCust_id()) {
					konka_r3_shop_id = konkaOrderInfo.getCust_id();
				}

				if (null != konka_r3_shop_id) {
					// KonkaR3MmtMatch konkaR3MmtMatch = new KonkaR3MmtMatch();
					// konkaR3MmtMatch.setMmt_shop_id(konkaOrderInfo.getShop_id());
					// konkaR3MmtMatch =
					// getFacade().getKonkaR3MmtMatchService().getKonkaR3MmtMatch(konkaR3MmtMatch);

					KonkaR3Shop konkaR3Shop = new KonkaR3Shop();
					konkaR3Shop.setId(konkaOrderInfo.getCust_id());
					konkaR3Shop = getFacade().getKonkaR3ShopService().getKonkaR3Shop(konkaR3Shop);
					if (null != konkaR3Shop) {
						request.setAttribute("konkaR3Shop", konkaR3Shop);

					}
				}
				// 取订单的审核角色，作为审核签名
				List<KonkaOrderInfoAudit> konkaOrderInfoAuditList = super
						.getKonkaOrderInfoAuditWithRoleInfoList(new Long(id));
				if (null != konkaOrderInfoAuditList && konkaOrderInfoAuditList.size() > 0) {
					konkaOrderInfo.setKonkaOrderInfoAuditList(konkaOrderInfoAuditList);
				}
				super.copyProperties(form, konkaOrderInfo);
				// 订单详细信息
				KonkaOrderInfoDetails konkaOrderInfoDetails = new KonkaOrderInfoDetails();
				konkaOrderInfoDetails.setOrder_id(konkaOrderInfo.getId());
				List<KonkaOrderInfoDetails> konkaOrderInfoDetailsList = getFacade().getKonkaOrderInfoDetailsService()
						.getKonkaOrderInfoDetailsList(konkaOrderInfoDetails);
				request.setAttribute("konkaOrderInfoDetailsList", konkaOrderInfoDetailsList);
			}
			request.setAttribute("fgsDept", fgsDept);
			request.setAttribute("nowDate", nowDate);
			return new ActionForward("/../jxc/KonkaJxcOrderInfoView/_print_order.jsp");
		} else {
			this.saveError(request, "errors.long", new String[] { id });
			return new ActionForward("/../jxc/KonkaJxcOrderInfoView/_print_order.jsp");
		}

	}
}
