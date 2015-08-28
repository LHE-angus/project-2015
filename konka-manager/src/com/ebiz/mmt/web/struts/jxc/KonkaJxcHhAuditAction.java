package com.ebiz.mmt.web.struts.jxc;

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

import com.ebiz.mmt.domain.KonkaJxcHhRecord;
import com.ebiz.mmt.domain.KonkaJxcStoreInfo;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleInfo;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Ren,Peng
 * @version 2011-10-18
 */
public class KonkaJxcHhAuditAction extends BaseJxcAction {
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
		super.getModPopeDom(form, request);

		PeProdUser user = super.getSessionUserInfo(request);
		if (null == user) {
			return null;
		}

		DynaBean dynaBean = (DynaBean) form;
		String pd_name_like = (String) dynaBean.get("pd_name_like");
		Pager pager = (Pager) dynaBean.get("pager");

		KonkaJxcHhRecord entity = new KonkaJxcHhRecord();
		super.copyProperties(entity, dynaBean);
		entity.setIs_del(0);
		entity.setBrand_id(Constants.KONKA_BRAND_ID);//换货只限康佳品牌
		if (StringUtils.isNotBlank(pd_name_like)) {
			entity.getMap().put("pd_name_like", pd_name_like);
		}
		entity.getMap().put("son_dept_id", user.getDept_id());
		PeRoleInfo role = super.getPeRoleInfoByUserId(user.getId());
		if (role.getRole_id().intValue() == 60) {//业务员,根据user_id查询分配网点
			entity.getMap().put("user_id", user.getId());
		} else {//查找下级所有部门，分配的所有网点
			entity.getMap().put("dept_id", user.getDept_id());
		}
		Long recordCount = super.getFacade().getKonkaJxcHhRecordService().getKonkaJxcHhRecordCount(entity);
		pager.init(recordCount, 10, pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<KonkaJxcHhRecord> entityList = super.getFacade().getKonkaJxcHhRecordService()
				.getKonkaJxcHhRecordPaginatedList(entity);
		request.setAttribute("entityList", entityList);

		// 审批人类型
		if (role.getRole_id().intValue() == 20) {
			request.setAttribute("audit_user_type", "20");// 审批人类型：事业部
		}
		if (role.getRole_id().intValue() == 30) {
			request.setAttribute("audit_user_type", "30");// 审批人类型：分公司
		}
		return mapping.findForward("list");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (!isSessionLoginForKonkaJxc(request)) {
			return forwardNoSessionForKonkaJxc(request, response);
		}
		resetToken(request);

		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");

		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

		KonkaJxcHhRecord entity = new KonkaJxcHhRecord();
		super.copyProperties(entity, dynaBean);
		entity.setAudit_dept_id(peProdUser.getDept_id());
		entity.setAudit_user_id(peProdUser.getId());
		entity.setApproval_date(new Date());
		entity.setUpdate_date(new Date());
		entity.setUpdate_user_id(peProdUser.getId());

		super.getFacade().getKonkaJxcHhRecordService().modifyKonkaJxcHhRecord(entity);
		super.saveMessage(request, "entity.updated");

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

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (!isSessionLoginForKonkaJxc(request)) {
			return forwardNoSessionForKonkaJxc(request, response);
		}
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");

		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

		if (!GenericValidator.isLong(id)) {
			return this.list(mapping, form, request, response);
		}

		KonkaJxcHhRecord entity = new KonkaJxcHhRecord();
		entity.setId(Long.valueOf(id));
		entity = super.getFacade().getKonkaJxcHhRecordService().getKonkaJxcHhRecord(entity);

		if (null == entity) {
			return this.list(mapping, form, request, response);
		}

		// 审批人类型
		PeRoleUser roleUser = new PeRoleUser();
		roleUser.setUser_id(peProdUser.getId());
		List<PeRoleUser> roleUserList = super.getFacade().getPeRoleUserService().getPeRoleUserList(roleUser);

		PeRoleInfo roleInfo = new PeRoleInfo();
		roleInfo.setIs_del(0);
		List<PeRoleInfo> roleInfoList = super.getFacade().getPeRoleInfoService().getPeRoleInfoList(roleInfo);
		for (PeRoleUser pr : roleUserList) {
			boolean flag = false;
			for (PeRoleInfo pri : roleInfoList) {
				if (pr.getRole_id().equals(pri.getRole_id())) {
					flag = true;
					entity.setAudit_user_type(pri.getRole_id());
					break;
				}
			}
			if (flag == true)
				break;
		}

		// 仓库
		List<KonkaJxcStoreInfo> storeList = getStoreInfoListByDeptId(peProdUser.getDept_id());
		request.setAttribute("storeList", storeList);

		entity.setQueryString(super.serialize(request, "id", "mod_id"));
		entity.setIs_audit(1);
		super.copyProperties(form, entity);

		return mapping.findForward("input");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (!isSessionLoginForKonkaJxc(request)) {
			return forwardNoSessionForKonkaJxc(request, response);
		}
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");

		if (!GenericValidator.isLong(id)) {
			return this.list(mapping, form, request, response);
		}

		KonkaJxcHhRecord entity = new KonkaJxcHhRecord();
		entity.setId(Long.valueOf(id));
		entity = super.getFacade().getKonkaJxcHhRecordService().getKonkaJxcHhRecord(entity);

		if (null == entity) {
			return this.list(mapping, form, request, response);
		}

		super.copyProperties(form, entity);

		return mapping.findForward("view");
	}
}