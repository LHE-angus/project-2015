package com.ebiz.mmt.web.struts.manager.spgl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.EcSenderInfo;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Pan,Gang
 * @version 2013-09-23
 */
public class EcSenderInfoAction extends BaseAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);
		Pager pager = (Pager) dynaBean.get("pager");
		String real_name_like = (String) dynaBean.get("real_name_like");
		String dept_name_like = (String) dynaBean.get("dept_name_like");

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(user.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);
		boolean role_id_eq_30 = false;
		boolean role_id_gt_30_lt_60 = false;

		for (PeRoleUser peRoleUser : peRoleUserList) {
			// 总部管理员
			if (peRoleUser.getRole_id() < 30L || peRoleUser.getRole_id().intValue() == 140317
			        || peRoleUser.getRole_id().intValue() == 1001) {
				role_id_eq_30 = true;
				request.setAttribute("is_admin", "0");
			}
			// 分公司管理员
			if (peRoleUser.getRole_id() >= 30L && peRoleUser.getRole_id() <= 188L) {
				role_id_gt_30_lt_60 = true;
			}
		}
		logger.info("+++" + role_id_eq_30);

		EcSenderInfo entity = new EcSenderInfo();

		// 分公司只能看自己添加本分公司的数据
		if (role_id_gt_30_lt_60) {
			entity.setDept_id(super.getSuperiorForDeptType(user.getDept_id(), 3).getDept_id());
			entity.setAdd_user_id(user.getId().toString());
		}

		if (StringUtils.isNotBlank(real_name_like)) {
			entity.getMap().put("real_name_like", real_name_like);

		}
		if (StringUtils.isNotBlank(dept_name_like)) {
			entity.getMap().put("dept_name_like", dept_name_like);

		}

		Long recordCount = super.getFacade().getEcSenderInfoService().getEcSenderInfoCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<EcSenderInfo> entityList = super.getFacade().getEcSenderInfoService().getEcSenderInfoPaginatedList(entity);
		request.setAttribute("entityList", entityList);

		request.setAttribute("user_id", user.getId());

		return mapping.findForward("list");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {

		setNaviStringToRequestScope(form, request);

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		request.setAttribute("sybDeptInfoList", super.getDeptInfoListWithOutLimit(mapping, form, request, response));
		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id");
		String dept_id = (String) dynaBean.get("dept_id");

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		EcSenderInfo entity = new EcSenderInfo();
		super.copyProperties(entity, form);

		if (StringUtils.isEmpty(id)) {
			entity.setAdd_date(new Date());
			if (StringUtils.isNotBlank(dept_id)) {
				KonkaDept konkaDept = new KonkaDept();
				konkaDept.setDept_id(Long.valueOf(dept_id));
				konkaDept = super.getFacade().getKonkaDeptService().getKonkaDept(konkaDept);
				if (konkaDept != null && konkaDept.getDept_name() != null) {
					entity.setDept_name(konkaDept.getDept_name());
				}
			}
			entity.setAdd_user_id(user.getId().toString());
			entity.setAdd_user_name(user.getUser_name());
			super.getFacade().getEcSenderInfoService().createEcSenderInfo(entity);
			super.saveMessage(request, "entity.inserted");
		} else {
			if (StringUtils.isNotBlank(dept_id)) {
				KonkaDept konkaDept = new KonkaDept();
				konkaDept.setDept_id(Long.valueOf(dept_id));
				konkaDept = super.getFacade().getKonkaDeptService().getKonkaDept(konkaDept);
				if (konkaDept != null && konkaDept.getDept_name() != null) {
					entity.setDept_name(konkaDept.getDept_name());
				}
			}
			super.getFacade().getEcSenderInfoService().modifyEcSenderInfo(entity);
			super.saveMessage(request, "entity.updated");
		}

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;

	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id");

		if (!GenericValidator.isLong(id)) {
			super.saveError(request, "errors.param");
			return mapping.findForward("list");
		}

		EcSenderInfo entity = new EcSenderInfo();
		entity.setId(Long.valueOf(id));
		super.getFacade().getEcSenderInfoService().removeEcSenderInfo(entity);

		saveMessage(request, "entity.deleted");

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "id", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		if (!GenericValidator.isLong(id)) {
			super.saveError(request, "errors.long", "id");
			return this.list(mapping, form, request, response);
		}

		EcSenderInfo entity = new EcSenderInfo();
		entity.setId(Long.valueOf(id));

		entity = super.getFacade().getEcSenderInfoService().getEcSenderInfo(entity);
		entity.setQueryString(super.serialize(request, "id", "method"));

		super.copyProperties(form, entity);

		request.setAttribute("sybDeptInfoList", super.getDeptInfoListWithOutLimit(mapping, form, request, response));

		return mapping.findForward("input");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");

		if (!GenericValidator.isLong(id)) {
			return this.list(mapping, form, request, response);
		}

		EcSenderInfo entity = new EcSenderInfo();
		entity.setId(Long.valueOf(id));
		entity = super.getFacade().getEcSenderInfoService().getEcSenderInfo(entity);

		if (null == entity) {
			return this.list(mapping, form, request, response);
		}

		super.copyProperties(form, entity);

		return mapping.findForward("view");
	}
}
