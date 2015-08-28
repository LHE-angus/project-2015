package com.ebiz.mmt.web.struts.jxc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaCategory;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaOrderAuditProcess;
import com.ebiz.mmt.domain.KonkaOrderAuditProcessNode;
import com.ebiz.mmt.domain.KonkaOrderInfo;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleInfo;
import com.ebiz.mmt.web.Constants;
import com.ebiz.ssi.web.struts.bean.Pager;

public class KonkaJxcOrderAuditProcessDefineAction extends BaseJxcAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (!isSessionLoginForKonkaJxc(request)) {
			return forwardNoSessionForKonkaJxc(request, response);
		}
		// if (null == super.checkUserModPopeDom(form, request, "0")) {
		// return super.checkPopedomInvalid(request, response);
		// }

		setNaviStringToRequestScope(form, request);
		PeProdUser user = super.getSessionUserInfo(request);
		if (user == null) {
			return null;
		}
		PeRoleInfo role = super.getPeRoleInfoByUserId(user.getId());
		KonkaDept konkaDept = new KonkaDept();
		konkaDept.setDept_type(3);
		konkaDept.getMap().put("order_by_dept_name", true);
		List<KonkaDept> konkaDeptList = getFacade().getKonkaDeptService().getKonkaDeptList(konkaDept);

		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		KonkaOrderAuditProcess entity = new KonkaOrderAuditProcess();// 流程主表
		super.copyProperties(entity, form);
		if (role.getRole_id().intValue() >= Constants.ROLE_ID_FGS) {// 分公司以上角色
			// 只有查看权限
			// 分公司管理员才可以操作流程
			request.setAttribute("is_only_look", "false");
			entity.setAdd_dept_id(user.getDept_id());
		} else {
			request.setAttribute("is_show_dept", "true");
			request.setAttribute("konkaDeptList", konkaDeptList);
		}
		Long recordCount = super.getFacade().getKonkaOrderAuditProcessService().getKonkaOrderAuditProcessCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<KonkaOrderAuditProcess> entityList = getFacade().getKonkaOrderAuditProcessService()
				.getKonkaOrderAuditProcessPaginatedList(entity);
		request.setAttribute("entityList", entityList);

		// 网点类型
		KonkaCategory kc = new KonkaCategory();
		kc.setC_type(10);
		request.setAttribute("konkaCategoryList", super.getFacade().getKonkaCategoryService().getKonkaCategoryList(kc));
		return mapping.findForward("list");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		if (!isSessionLoginForKonkaJxc(request)) {
			return forwardNoSessionForKonkaJxc(request, response);
		}
		saveToken(request);
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String mod_id = request.getParameter("mod_id");

		dynaBean.set("mod_id", mod_id);
		dynaBean.set("queryString", super.serialize(request, "mod_id", "method"));

		// 网点类型
		KonkaCategory kc = new KonkaCategory();
		kc.setC_type(10);
		request.setAttribute("konkaCategoryList", super.getFacade().getKonkaCategoryService().getKonkaCategoryList(kc));
		request.setAttribute("flag", true);
		request.setAttribute("weeks", check_for_stock("check_for_stock")==0 ? 4 : check_for_stock("check_for_stock"));
		return mapping.findForward("input");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		if (!isSessionLoginForKonkaJxc(request)) {
			return forwardNoSessionForKonkaJxc(request, response);
		}
		saveToken(request);
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String id = request.getParameter("id");
		// String mod_id = request.getParameter("mod_id");

		if (!GenericValidator.isLong(id)) {
			super.saveError(request, "error.param");
			return mapping.findForward("input");
		}

		KonkaOrderAuditProcess orderAuditProcess = new KonkaOrderAuditProcess();
		orderAuditProcess.setId(Long.valueOf(id));
		orderAuditProcess = super.getFacade().getKonkaOrderAuditProcessService()
				.getKonkaOrderAuditProcess(orderAuditProcess);
		super.copyProperties(form, orderAuditProcess);

		KonkaOrderAuditProcessNode konkaOrderAuditProcessNode = new KonkaOrderAuditProcessNode();
		konkaOrderAuditProcessNode.setAudit_proces_id(Long.valueOf(id));
		List<KonkaOrderAuditProcessNode> orderAuditProcessNodeList = super.getFacade()
				.getKonkaOrderAuditProcessNodeService().getKonkaOrderAuditProcessNodeList(konkaOrderAuditProcessNode);
		request.setAttribute("orderAuditProcessNodeList", orderAuditProcessNodeList);

		dynaBean.set("queryString", super.serialize(request, "mod_id", "method"));

		// 网点类型
		KonkaCategory kc = new KonkaCategory();
		kc.setC_type(10);
		request.setAttribute("konkaCategoryList", super.getFacade().getKonkaCategoryService().getKonkaCategoryList(kc));
		request.setAttribute("weeks", check_for_stock("check_for_stock")==0 ? 4 : check_for_stock("check_for_stock"));
		request.setAttribute("flag", false);
		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (!isSessionLoginForKonkaJxc(request)) {
			return forwardNoSessionForKonkaJxc(request, response);
		}
		setNaviStringToRequestScope(form, request);
		PeProdUser user = super.getSessionUserInfo(request);
		if (user == null) {
			return null;
		}
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id");
		String[] role_ids = request.getParameterValues("role_id");// 角色id
		String[] is_update_authoritys = request.getParameterValues("is_update_authority");// 是否可修改
		String[] next_node = request.getParameterValues("next_node");// 下个审核角色
		String[] node_ids = request.getParameterValues("node_id");// 下个审核角色
		String[] audit_proc_conds = request.getParameterValues("audit_proc_cond");// 审核条件
		KonkaOrderAuditProcess konkaOrderAuditProcess = new KonkaOrderAuditProcess();
		konkaOrderAuditProcess.setAdd_dept_id(new Long(user.getDept_id()));
		konkaOrderAuditProcess.setIs_del(0);

		KonkaOrderAuditProcess entity = new KonkaOrderAuditProcess();// 流程主表
		super.copyProperties(entity, form);

		logger.info("=======audit_proc_cond=====" + audit_proc_conds.length);
		logger.info("=======is_update_authoritys=====" + is_update_authoritys.length);

		List<KonkaOrderAuditProcessNode> konkaOrderAuditProcessNodeList = new ArrayList<KonkaOrderAuditProcessNode>();
		if (role_ids != null && role_ids.length > 0) {
			for (int i = 1; i < role_ids.length; i++) {// 一级循环（按等级）构造流程主表
				// for (int k = i + 1; k < role_ids.length; k++) {
				// if (StringUtils.equals(role_ids[i], role_ids[k])) {//
				// 验证同一流程重复添加角色
				// String send_error = "审核流程角色有重复，请重新选择角色！";
				// super.renderJavaScript(response, "alert('" + send_error +
				// "');history.back();");
				// return null;
				// }
				// }
				String[] role_ids_split = role_ids[i].split(",");
				for (int j = 0; j < role_ids_split.length; j++) {// 二级循环（按角色）构造流程子表
					KonkaOrderAuditProcessNode konkaOrderAuditProcessNode = new KonkaOrderAuditProcessNode();

					if (StringUtils.isNotBlank(node_ids[j])) {
						konkaOrderAuditProcessNode.setId(Long.valueOf(node_ids[i]));
					}

					konkaOrderAuditProcessNode.setRole_id(new Long(role_ids_split[j]));
					PeRoleInfo peRoleInfo = new PeRoleInfo();
					peRoleInfo = super.getPeRoleInfoById(new Long(role_ids_split[j]));
					konkaOrderAuditProcessNode.setRole_name(peRoleInfo.getRole_name());
					konkaOrderAuditProcessNode.setIs_update_authority(new Integer(is_update_authoritys[i]));// 修改权限取一级循环
					konkaOrderAuditProcessNode.setAudit_proc_cond(new Integer(audit_proc_conds[i]));// 修改权限取一级循环
					if (null != next_node[i] && !"".equals(next_node[i])) {
						konkaOrderAuditProcessNode.setNext_node(next_node[i]);// 下个审核角色
					}
					konkaOrderAuditProcessNode.setAudit_level(new Integer(i));// 等级取一级循环
					// konkaOrderAuditProcessNode.setAudit_proces_id(audit_proces_id)
					// service 层处理
					konkaOrderAuditProcessNode.setAdd_user_id(user.getId());
					konkaOrderAuditProcessNode.setAdd_user_name(user.getUser_name());
					konkaOrderAuditProcessNode.setAdd_dept_id(user.getDept_id());
					konkaOrderAuditProcessNodeList.add(konkaOrderAuditProcessNode);
				}
			}
		}
		entity.setKonkaOrderAuditProcessNodeList(konkaOrderAuditProcessNodeList);

		if (!GenericValidator.isLong(id)) {
			entity.setAdd_user_id(user.getId());
			entity.setAdd_user_name(user.getUser_name());
			entity.setAdd_dept_id(user.getDept_id());
			KonkaDept konkaDept = super.getKonkaDeptById(user.getDept_id());
			if (konkaDept != null) {
				entity.setAdd_dept_name(konkaDept.getDept_name());
			}
			getFacade().getKonkaOrderAuditProcessService().createKonkaOrderAuditProcess(entity);
			super.saveMessage(request, "entity.inserted");
		} else {
			getFacade().getKonkaOrderAuditProcessService().modifyKonkaOrderAuditProcess(entity);
			super.saveMessage(request, "entity.updated");
		}

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (!isSessionLoginForKonkaJxc(request)) {
			return forwardNoSessionForKonkaJxc(request, response);
		}
		setNaviStringToRequestScope(form, request);
		PeProdUser user = super.getSessionUserInfo(request);
		if (user == null) {
			return null;
		}
		DynaBean dynaBean = (DynaBean) form;
		// String mod_id = (String) dynaBean.get("mod_id");
		String id = (String) dynaBean.get("id");
		KonkaOrderAuditProcess entity = new KonkaOrderAuditProcess();
		if (GenericValidator.isLong(id)) {
			// super.copyProperties(entity, form);
			entity.setId(new Long(id));
			entity = getFacade().getKonkaOrderAuditProcessService().getKonkaOrderAuditProcess(entity);
			if (entity != null) {
				List<KonkaOrderAuditProcessNode> konkaOrderAuditProcessNodeList = new ArrayList<KonkaOrderAuditProcessNode>();
				KonkaOrderAuditProcessNode node = new KonkaOrderAuditProcessNode();
				node.setAudit_proces_id(entity.getId());
				List<KonkaOrderAuditProcessNode> list_node = getFacade().getKonkaOrderAuditProcessNodeService()
						.getKonkaOrderAuditProcessNodeList(node);
				if (list_node != null && list_node.size() > 0) {
					for (KonkaOrderAuditProcessNode kkoapn : list_node) {
						konkaOrderAuditProcessNodeList.add(kkoapn);
					}
				}
				entity.setKonkaOrderAuditProcessNodeList(konkaOrderAuditProcessNodeList);
			}
		}
		super.copyProperties(form, entity);

		return mapping.findForward("view");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (!isSessionLoginForKonkaJxc(request)) {
			return forwardNoSessionForKonkaJxc(request, response);
		}
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id");
		PeProdUser user = super.getSessionUserInfo(request);

		KonkaDept konkaDept = super.getKonkaDeptByUserDeptId(user.getDept_id());

		boolean delete_able = true;// 可以删除
		if (!StringUtils.isBlank(id) && GenericValidator.isLong(id)) {
			KonkaOrderAuditProcess koau = new KonkaOrderAuditProcess();
			koau.setId(new Long(id));
			koau = super.getFacade().getKonkaOrderAuditProcessService().getKonkaOrderAuditProcess(koau);

			if (null != koau) {
				// 取分公司所有的订单
				if (null != konkaDept) {
					KonkaOrderInfo konkaOrderInfo = new KonkaOrderInfo();
					konkaOrderInfo.getMap().put("order_view_fgs", "true");
					konkaOrderInfo.getMap().put("manager_dept_Id", konkaDept.getDept_id());
					List<KonkaOrderInfo> konkaOrderInfoList = getFacade().getKonkaOrderInfoService()
							.getKonkaOrderInfoListWithShopName(konkaOrderInfo);

					if (null != konkaOrderInfoList && konkaOrderInfoList.size() > 0) {
						for (KonkaOrderInfo koi : konkaOrderInfoList) {
							if (koi.getAudit_state() == 0 || koi.getAudit_state() == 1) {
								delete_able = false;
								break;
							}
						}
					}
				}

				KonkaOrderInfo konkaOrderInfo2 = new KonkaOrderInfo();
				konkaOrderInfo2.setProcess_id(koau.getId());
				if (0L < super.getFacade().getKonkaOrderInfoService().getKonkaOrderInfoCount(konkaOrderInfo2)) {
					delete_able = false;
				}

			}
		}
		if (!delete_able) {
			String msg = "对不起，有订单正在审核中，您不能删除此流程!";
			renderJavaScript(response, "alert('" + msg + "');history.back();");
			return null;
		}

		KonkaOrderAuditProcess entity = new KonkaOrderAuditProcess();
		if (!StringUtils.isBlank(id) && GenericValidator.isLong(id)) {
			entity.setIs_del(1);
			entity.setId(new Long(id));
			entity.setDel_date(new Date());
			entity.setDel_user_id(user.getId());
			if (entity != null) {
				List<KonkaOrderAuditProcessNode> konkaOrderAuditProcessNodeList = new ArrayList<KonkaOrderAuditProcessNode>();
				KonkaOrderAuditProcessNode node = new KonkaOrderAuditProcessNode();
				node.setAudit_proces_id(entity.getId());
				List<KonkaOrderAuditProcessNode> list_node = getFacade().getKonkaOrderAuditProcessNodeService()
						.getKonkaOrderAuditProcessNodeList(node);
				if (list_node != null && list_node.size() > 0) {
					for (KonkaOrderAuditProcessNode kkoapn : list_node) {
						konkaOrderAuditProcessNodeList.add(kkoapn);
					}
				}
				entity.setKonkaOrderAuditProcessNodeList(konkaOrderAuditProcessNodeList);
			}
			getFacade().getKonkaOrderAuditProcessService().modifyKonkaOrderAuditProcess(entity);
			saveMessage(request, "entity.deleted");
		}
		// else if (!ArrayUtils.isEmpty(pks)) {
		// entity.setIs_del(1);
		// entity.setDel_date(new Date());
		// entity.setDel_user_id(ui.getId());
		// entity.getMap().put("pks", pks);
		// getFacade().getKonkaOrderAuditProcessService().modifyKonkaOrderAuditProcess(entity);
		// saveMessage(request, "entity.deleted");
		// }
		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=").append(mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "id", "method")));// "ids",
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;

	}

	public ActionForward stop(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (!isSessionLoginForKonkaJxc(request)) {
			return forwardNoSessionForKonkaJxc(request, response);
		}
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id");

		KonkaOrderAuditProcess entity = new KonkaOrderAuditProcess();
		if (!StringUtils.isBlank(id) && GenericValidator.isLong(id)) {
			entity.setIs_stop(1);
			entity.setId(new Long(id));
			getFacade().getKonkaOrderAuditProcessService().modifyKonkaOrderAuditProcess(entity);
			saveMessage(request, "entity.updated");
		}

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=").append(mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "id", "method")));// "ids",
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}
	/*
	 * @WangKunLin 流程启用功能
	 * 2014-5-6
	 */
	public ActionForward start(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (!isSessionLoginForKonkaJxc(request)) {
			return forwardNoSessionForKonkaJxc(request, response);
		}
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id");

		KonkaOrderAuditProcess entity = new KonkaOrderAuditProcess();
		if (!StringUtils.isBlank(id) && GenericValidator.isLong(id)) {
			entity.setIs_stop(0);
			entity.setId(new Long(id));
			getFacade().getKonkaOrderAuditProcessService().modifyKonkaOrderAuditProcess(entity);
			saveMessage(request, "entity.updated");
		}

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=").append(mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request,"id","method")));// "ids",
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward list_role(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (!isSessionLoginForKonkaJxc(request)) {
			return forwardNoSessionForKonkaJxc(request, response);
		}
		setNaviStringToRequestScope(form, request);
		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		if (peProdUser == null) {
			return null;
		}
		DynaBean dynaBean = (DynaBean) form;
		String selectedIds = (String) dynaBean.get("selectedIds");
		if (StringUtils.isNotBlank(selectedIds)) {
			PeRoleInfo peRoleInfo = new PeRoleInfo();
			peRoleInfo.getMap().put("is_pe_prod_user", "true");
			peRoleInfo.getMap().put("selectedIds", selectedIds);
			List<PeRoleInfo> peRoleInfoSelectedList = getFacade().getPeRoleInfoService().getPeRoleInfoList(peRoleInfo);
			if (peRoleInfoSelectedList != null && peRoleInfoSelectedList.size() > 0) {// 选择过的
				request.setAttribute("peRoleInfoSelectedList", peRoleInfoSelectedList);
			}
		}

		List<PeRoleInfo> peRoleInfoList = super.getPeRoleInfoListForProcess();
		request.setAttribute("peRoleInfoList", peRoleInfoList);
		return new ActionForward("/KonkaJxcOrderAuditProcessDefine/list_role.jsp");
	}

}
