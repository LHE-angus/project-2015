package com.ebiz.mmt.web.struts.manager.admin;

import java.math.BigDecimal;
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

import com.ebiz.mmt.domain.GcxmAuditProcess;
import com.ebiz.mmt.domain.GcxmAuditProcessNode;
import com.ebiz.mmt.domain.GcxmProjAuditNode;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaOrderAuditProcess;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleInfo;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author PanGang
 */
public class GcxmAuditProcessAction extends BaseAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String audit_type = (String) dynaBean.get("audit_type");
		String is_del = (String) dynaBean.get("is_del");

		PeProdUser user = super.getSessionUserInfo(request);
		if (user == null) {
			return null;
		}
		KonkaDept konkaDept = new KonkaDept();
		konkaDept.setDept_id(user.getDept_id());
		konkaDept = super.getFacade().getKonkaDeptService().getKonkaDept(konkaDept);
		// 分公司取得
		KonkaDept fgs_dept = super.getSuperiorForDeptType(user.getDept_id(), 3);
		if (fgs_dept != null) {
			dynaBean.set("fgs_dept_name", fgs_dept.getDept_name());
		}
		
		boolean zb = false;
		boolean fgs = false;
		boolean qt = false;
		PeRoleUser rUser = new PeRoleUser();
		rUser.setUser_id(user.getId());
		List<PeRoleUser> roleUserList = getFacade().getPeRoleUserService().getPeRoleUserList(rUser);
		if (null != roleUserList && roleUserList.size() > 0) {
			for (PeRoleUser pu : roleUserList) {
				if (pu.getRole_id() < 30L || pu.getRole_id().intValue() == 223 || pu.getRole_id().intValue() == 224
						|| pu.getRole_id().intValue() == 225) {
					zb = true;// 总部
					break;
				} else if (pu.getRole_id().intValue() >= 30 && pu.getRole_id().intValue() <= 188) {
					fgs = true;// 分公司
					break;
				} else {
					qt = true;
				}
			}
		}
	
		GcxmAuditProcess entity = new GcxmAuditProcess();

		// 根据当前登录人获取创建人分公司相同的流程
		if(zb){
			
		} else if (fgs) {
			konkaDept = new KonkaDept();
			konkaDept = getKonkaDeptForFgs(user.getDept_id());
			
			List<Long> deptInList=new ArrayList<Long>();
			deptInList.add(0L);
			deptInList.add(konkaDept.getDept_id());
			entity.getMap().put("dept_id_0", deptInList);
		} else {
				String msg = super.getMessage(request, "您没有查看权限");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
		}

		if (StringUtils.isNotBlank(audit_type)) {
			entity.setAudit_type(Long.valueOf(audit_type));
		}
		if (StringUtils.isNotBlank(is_del)) {
			entity.setIs_del(Integer.valueOf(is_del));
		}else{
			entity.setIs_del(0);
		}

		Long recordCount = super.getFacade().getGcxmAuditProcessService().getGcxmAuditProcessCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<GcxmAuditProcess> entityList = getFacade().getGcxmAuditProcessService().getGcxmAuditProcessPaginatedList(
				entity);
		if (entityList != null && entityList.size() > 0) {
			for (GcxmAuditProcess gs : entityList) {
				List<String> ss = new ArrayList<String>();
				GcxmAuditProcessNode gn = new GcxmAuditProcessNode();
				gn.setProcess_id(gs.getId());
				List<GcxmAuditProcessNode> gsList = super.getFacade().getGcxmAuditProcessNodeService()
						.getGcxmAuditProcessNodeList(gn);
				if (gsList != null && gsList.size() > 0) {
					for (GcxmAuditProcessNode gd : gsList) {
						ss.add(gd.getAudit_role_name());
					}
				}
				String process_desc = StringUtils.join(ss, "——>");
				gs.getMap().put("process_desc", process_desc);
			}
		}

		request.setAttribute("entityList", entityList);

		return mapping.findForward("list");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = request.getParameter("mod_id");

		dynaBean.set("mod_id", mod_id);
		dynaBean.set("queryString", super.serialize(request, "mod_id", "method"));

		return mapping.findForward("input");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = request.getParameter("id");

		if (!GenericValidator.isLong(id)) {
			super.saveError(request, "error.param");
			return mapping.findForward("input");
		}

		GcxmAuditProcess entity = new GcxmAuditProcess();
		entity.setId(Long.valueOf(id));
		entity = super.getFacade().getGcxmAuditProcessService().getGcxmAuditProcess(entity);
		super.copyProperties(form, entity);

		GcxmAuditProcessNode gcxmAuditProcessNode = new GcxmAuditProcessNode();
		gcxmAuditProcessNode.setProcess_id(Long.valueOf(id));
		List<GcxmAuditProcessNode> gcxmAuditProcessNodeList = super.getFacade().getGcxmAuditProcessNodeService()
				.getGcxmAuditProcessNodeList(gcxmAuditProcessNode);
		request.setAttribute("gcxmAuditProcessNodeList", gcxmAuditProcessNodeList);

		dynaBean.set("queryString", super.serialize(request, "mod_id", "method"));

		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		PeProdUser user = super.getSessionUserInfo(request);
		if (user == null) {
			return null;
		}
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id");
		String[] audit_role_id = request.getParameterValues("audit_role_id");// 审核角色id

		GcxmAuditProcess entity = new GcxmAuditProcess();
		entity.setIs_del(0);

		super.copyProperties(entity, form);

		logger.info("=======audit_role_id=====" + audit_role_id.length);

		// "",33
		List<GcxmAuditProcessNode> gcxmAuditProcessNodeList = new ArrayList<GcxmAuditProcessNode>();
		if (audit_role_id != null && audit_role_id.length > 0) {
			for (int i = 1; i < audit_role_id.length; i++) {
				GcxmAuditProcessNode pnode = new GcxmAuditProcessNode();
				if (i == 1) {
					pnode.setPre_audit_role_id(null);
					pnode.setPre_audit_role_name(null);
				} else {
					pnode.setPre_audit_role_id(new Long(audit_role_id[i - 1]));
					PeRoleInfo peRoleInfo = new PeRoleInfo();
					peRoleInfo = getPeRoleInfoById(new Long(audit_role_id[i - 1]));
					pnode.setPre_audit_role_name(peRoleInfo.getRole_name());
				}

				if (i == audit_role_id.length - 1) {
					pnode.setNext_audit_role_id(null);
					pnode.setNext_audit_role_name(null);
				} else {
					pnode.setNext_audit_role_id(new Long(audit_role_id[i + 1]));
					PeRoleInfo peRoleInfo = new PeRoleInfo();
					peRoleInfo = getPeRoleInfoById(new Long(audit_role_id[i + 1]));
					pnode.setNext_audit_role_name(peRoleInfo.getRole_name());
				}

				pnode.setAudit_role_id(new Long(audit_role_id[i]));
				PeRoleInfo peRoleInfo = new PeRoleInfo();
				peRoleInfo = getPeRoleInfoById(new Long(audit_role_id[i]));
				pnode.setAudit_role_name(peRoleInfo.getRole_name());
				gcxmAuditProcessNodeList.add(pnode);
			}
		}
		entity.setGcxmAuditProcessNodeList(gcxmAuditProcessNodeList);

		if (!GenericValidator.isLong(id)) {
			entity.setCreate_user_id(new BigDecimal(user.getId().toString()));
			entity.setCreate_user_name(user.getUser_name());
			//获取当前登录用户
			PeProdUser userInfo = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
			if (null == userInfo) {
				String msg = super.getMessage(request, "user.session.isEmpty");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			}
			KonkaDept konkaDept = new KonkaDept();
			konkaDept.setDept_id(userInfo.getDept_id());
			konkaDept = super.getFacade().getKonkaDeptService().getKonkaDept(konkaDept);
			// 分公司取得
			KonkaDept fgs_dept = super.getSuperiorForDeptType(userInfo.getDept_id(), 3);
			if (fgs_dept != null) {
				dynaBean.set("fgs_dept_name", fgs_dept.getDept_name());
			}
			boolean zb = false;
			boolean fgs = false;
			boolean qt = false;
			PeRoleUser rUser = new PeRoleUser();
			rUser.setUser_id(userInfo.getId());
			List<PeRoleUser> roleUserList = getFacade().getPeRoleUserService().getPeRoleUserList(rUser);
			if (null != roleUserList && roleUserList.size() > 0) {
				for (PeRoleUser pu : roleUserList) {
					if (pu.getRole_id() < 30L || pu.getRole_id().intValue() == 223 || pu.getRole_id().intValue() == 224
							|| pu.getRole_id().intValue() == 225) {
						zb = true;// 总部
						break;
					} else if (pu.getRole_id().intValue() == 30 || pu.getRole_id().intValue() == 34) {
						fgs = true;// 分公司
						break;
					} else {
						qt = true;
					}
				}
			}
			if (zb) {
				entity.setDept_id(0L);
			} else {
				entity.setDept_id(fgs_dept.getDept_id());
			}

			entity.setCreate_date(new Date());
			super.getFacade().getGcxmAuditProcessService().createGcxmAuditProcess(entity);
			super.saveMessage(request, "entity.inserted");
		} else {
			super.getFacade().getGcxmAuditProcessService().modifyGcxmAuditProcess(entity);
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
		setNaviStringToRequestScope(form, request);
		PeProdUser user = super.getSessionUserInfo(request);
		if (user == null) {
			return null;
		}
		DynaBean dynaBean = (DynaBean) form;
		// String mod_id = (String) dynaBean.get("mod_id");
		String id = (String) dynaBean.get("id");
		GcxmAuditProcess entity = new GcxmAuditProcess();
		if (GenericValidator.isLong(id)) {
			entity.setId(new Long(id));
			entity = getFacade().getGcxmAuditProcessService().getGcxmAuditProcess(entity);
			if (entity != null) {
				List<GcxmAuditProcessNode> konkaOrderAuditProcessNodeList = new ArrayList<GcxmAuditProcessNode>();
				GcxmAuditProcessNode node = new GcxmAuditProcessNode();
				node.setProcess_id(Long.valueOf(id));
				List<GcxmAuditProcessNode> list_node = getFacade().getGcxmAuditProcessNodeService()
						.getGcxmAuditProcessNodeList(node);
				if (list_node != null && list_node.size() > 0) {
					for (GcxmAuditProcessNode kkoapn : list_node) {
						konkaOrderAuditProcessNodeList.add(kkoapn);
					}
				}
				entity.setGcxmAuditProcessNodeList(konkaOrderAuditProcessNodeList);
			}
		}
		super.copyProperties(form, entity);

		return mapping.findForward("view");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id");
		PeProdUser user = super.getSessionUserInfo(request);

		if (user == null) {
			return null;
		}

		GcxmProjAuditNode gt = new GcxmProjAuditNode();
		gt.setProcess_id(Long.valueOf(id));
		Long count = super.getFacade().getGcxmProjAuditNodeService().getGcxmProjAuditNodeCount(gt);

		if (count > 0) {
			String msg = "该流程正在使用中，不能被删除！";
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		GcxmAuditProcess entity = new GcxmAuditProcess();
		entity.setId(Long.valueOf(id));
		entity.setIs_del(1);
		super.getFacade().getGcxmAuditProcessService().modifyGcxmAuditProcess(entity);
		saveMessage(request, "entity.deleted");

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
	 * @WangKunLin 流程启用功能 2014-5-6
	 */
	public ActionForward start(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

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
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "id", "method")));// "ids",
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward list_role(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

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

		List<PeRoleInfo> peRoleInfoList = getPeRoleInfoListForProcess();
		request.setAttribute("peRoleInfoList", peRoleInfoList);

		return new ActionForward("/admin/GcxmAuditProcess/list_role.jsp");
	}

	protected List<PeRoleInfo> getPeRoleInfoListForProcess() {
		PeRoleInfo peRoleInfo = new PeRoleInfo();
		peRoleInfo.getMap().put("is_pe_prod_user", "true");
		// peRoleInfo.getMap().put("role_id_audit_lt", Constants.ROLE_ID_FGS);// 分公司角色管理员Id
		peRoleInfo.getMap().put("role_id_audit_lt", 10);// 分公司角色管理员Id
		peRoleInfo.getMap().put("role_id_audit_gt", 250);// 业务员角色Id
		peRoleInfo.setIs_del(0);
		// 取角色列表
		List<PeRoleInfo> peRoleInfo_list = getFacade().getPeRoleInfoService().getPeRoleInfoList(peRoleInfo);
		return peRoleInfo_list;
	}

	protected PeRoleInfo getPeRoleInfoById(Long roleId) {
		PeRoleInfo peRoleInfo = new PeRoleInfo();
		peRoleInfo.setRole_id(roleId);
		peRoleInfo.setIs_del(0);
		return this.getFacade().getPeRoleInfoService().getPeRoleInfo(peRoleInfo);
	}

}
