package com.ebiz.mmt.web.struts.manager.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleInfo;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Xu,ZhengYang
 */
public class SetRoleInfoAction extends BaseAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "1")) {
			return super.checkPopedomInvalid(request, response);
		}
		saveToken(request);
		setNaviStringToRequestScope(form, request);
		super.getModPopeDom(form, request);

		PeRoleInfo entity = new PeRoleInfo();
		// entity.setOrder_value(0);
		super.copyProperties(form, entity);
		return mapping.findForward("input");
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		super.getModPopeDom(form, request);

		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);

		String role_name_like = (String) dynaBean.get("role_name_like");

		Pager pager = (Pager) dynaBean.get("pager");

		PeRoleInfo entity = new PeRoleInfo();
		super.copyProperties(entity, dynaBean);

		if (null == entity.getIs_del()) {
			entity.setIs_del(0);
			dynaBean.set("is_del", "0");
		}

		entity.getMap().put("role_name_like", role_name_like);

		Long recordCount = getFacade().getPeRoleInfoService().getPeRoleInfoCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<PeRoleInfo> list = getFacade().getPeRoleInfoService().getPeRoleInfoPaginatedList(entity);

		request.setAttribute("entityList", list);
		return mapping.findForward("list");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		if (isCancelled(request)) {
			return list(mapping, form, request, response);
		}
		if (!isTokenValid(request)) {
			saveError(request, "errors.token");
			return list(mapping, form, request, response);
		}
		resetToken(request);

		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");

		PeRoleInfo entity = new PeRoleInfo();
		super.copyProperties(entity, dynaBean);

		if (null == entity.getRole_id()) {
			// entity.setIs_lock(0);
			getFacade().getPeRoleInfoService().createPeRoleInfo(entity);
			super.createSysOperLog(request, "OFFICE_ROLE_INFO", entity.getRole_id(), mod_id, "添加", BeanUtils.describe(
			        entity).toString());
			saveMessage(request, "entity.inserted");
		} else if (null != entity) {
			getFacade().getPeRoleInfoService().modifyPeRoleInfo(entity);
			super.createSysOperLog(request, "OFFICE_ROLE_INFO", entity.getRole_id(), mod_id, "修改", BeanUtils.describe(
			        entity).toString());
			saveMessage(request, "entity.updated");
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

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "2")) {
			return super.checkPopedomInvalid(request, response);
		}
		saveToken(request);
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");

		if (GenericValidator.isLong(id)) {
			PeRoleInfo entity = new PeRoleInfo();
			entity.setRole_id(Long.valueOf(id));
			entity = super.getFacade().getPeRoleInfoService().getPeRoleInfo(entity);
			if (null == entity) {
				saveMessage(request, "entity.missing");
				return mapping.findForward("list");
			}
			// the line below is added for pagination
			entity.setQueryString(super.serialize(request, "id", "method"));
			// end
			super.copyProperties(form, entity);
			return mapping.findForward("input");
		} else {
			this.saveError(request, "errors.long", new String[] { id });
			return mapping.findForward("list");
		}
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "4")) {
			return super.checkPopedomInvalid(request, response);
		}

		DynaBean dynaBean = (DynaBean) form;

		String id = (String) dynaBean.get("id");
		String[] pks = (String[]) dynaBean.get("pks");
		String mod_id = (String) dynaBean.get("mod_id");

		if (!StringUtils.isBlank(id) && GenericValidator.isLong(id)) {
			PeRoleInfo entity = new PeRoleInfo();
			entity.setRole_id(new Long(id));
			entity.setIs_del(1);
			getFacade().getPeRoleInfoService().modifyPeRoleInfo(entity);
			entity = getFacade().getPeRoleInfoService().getPeRoleInfo(entity);
			super.createSysOperLog(request, "OFFICE_ROLE_INFO", entity.getRole_id(), mod_id, "删除", BeanUtils.describe(
			        entity).toString());
			saveMessage(request, "entity.deleted");
		} else if (!ArrayUtils.isEmpty(pks)) {
			PeRoleInfo entity = new PeRoleInfo();
			entity.getMap().put("pks", pks);
			entity.setIs_del(1);
			getFacade().getPeRoleInfoService().modifyPeRoleInfo(entity);

			for (String xx : pks) {
				PeRoleInfo x = new PeRoleInfo();
				x.setRole_id(new Long(xx));
				x = getFacade().getPeRoleInfoService().getPeRoleInfo(x);
				super.createSysOperLog(request, "OFFICE_ROLE_INFO", x.getRole_id(), mod_id, "删除", BeanUtils.describe(x)
				        .toString());
			}

			saveMessage(request, "entity.deleted");
		}

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "id", "pks", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String role_id = (String) dynaBean.get("role_id");
		Pager pager = (Pager) dynaBean.get("pager");
		String dept_id = (String) dynaBean.get("dept_id");

		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

		PeRoleUser peRoleUser = super.getRoleInfoByThisLogin(request);
		request.setAttribute("peRoleUser", peRoleUser);

		PeProdUser userInfo = new PeProdUser();
		userInfo.setIs_xx_user(0L);
		userInfo.setIs_del(0);

		if (peRoleUser.getRole_id() != 10) { // 非系统管理员只能看到自己部门下的用户，管理员能看到所有用户
			// entity.setDept_id(SxPeProdUser.getDept_id());
			if (peRoleUser.getRole_id() < 200 || peRoleUser.getRole_id() >= 300) {
				userInfo.getMap().put("dept_id_in", peProdUser.getDept_id());
				userInfo.getMap().put("user_id", peProdUser.getId());
			}
		}
		if (peRoleUser.getRole_id() >= 200 && peRoleUser.getRole_id() < 300) {
			userInfo.getMap().put("is_pe_prod_user", "");
			userInfo.getMap().put("is_zmd_head", true);// 康佳专卖店总部端
		} else if (peRoleUser.getRole_id() >= 300 && peRoleUser.getRole_id() < 400) {
			userInfo.getMap().put("is_pe_prod_user", "");
			userInfo.getMap().put("is_zmd_dept", true);// 康佳专卖店分公司
		} else if (peRoleUser.getRole_id() >= 1000 && peRoleUser.getRole_id() <= 1100) {
			userInfo.getMap().put("is_pec_user", "true");// 完美终端用户
		} else {
			userInfo.getMap().put("is_pe_prod_user", "true");// 康佳专版中人员管理中查询总数中将非专卖店的去掉所加的条件
		}
		if (StringUtils.isNotBlank(dept_id)) {
			userInfo.getMap().put("dept_id", dept_id);
		}

		userInfo.getMap().put("is_position_dept", true);
		userInfo.getMap().put("role_id", role_id);

		Long recordCount = getFacade().getPeProdUserService().getPeProdUserCount(userInfo);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		userInfo.getRow().setFirst(pager.getFirstRow());
		userInfo.getRow().setCount(pager.getRowCount());
		List<PeProdUser> userInfoList = super.getFacade().getPeProdUserService()
		        .getpeProdUserWithPositionNameAndFullDeptNamePaginatedList(userInfo);
		request.setAttribute("userInfoList", userInfoList);

		if (peRoleUser.getRole_id() == 10) {
			// 部门列表
			KonkaDept dept = new KonkaDept();
			dept.getMap().put("dept_id", peProdUser.getDept_id());
			List<KonkaDept> peDeptList = super.getFacade().getKonkaDeptService()
			        .getKonkaDeptListWithTreeNameForProdUser(dept);
			request.setAttribute("peDeptList", peDeptList);
		}

		return mapping.findForward("view");
	}

	public ActionForward saveRoleInfoUser(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String role_id = (String) dynaBean.get("role_id");
		String mod_id = (String) dynaBean.get("mod_id");
		String url = (String) dynaBean.get("url");
		String[] pks = (String[]) dynaBean.get("pks");

		PeRoleUser pubRoleUser = new PeRoleUser();
		pubRoleUser.setRole_id(new Long(role_id));
		pubRoleUser.getMap().put("user_ids", pks);

		super.getFacade().getPeRoleUserService().createPeRoleUser(pubRoleUser);

		saveMessage(request, "entity.inserted");

		String msg = super.getMessage(request, "entity.updated");
		super.renderJavaScript(response, "alert('" + msg + "');location.href='" + url + ".do?mod_id=" + mod_id + "';");

		return null;
	}

	public ActionForward validateRolename(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		StringBuffer oper = new StringBuffer("{result:");
		String role_name = (String) dynaBean.get("role_name");
		if (!StringUtils.isBlank(role_name)) {
			PeRoleInfo ri = new PeRoleInfo();
			ri.setRole_name(role_name);
			ri.setIs_del(0);
			ri = super.getFacade().getPeRoleInfoService().getPeRoleInfo(ri);
			if (null == ri) {
				oper.append(false);
			} else {
				oper.append(true);
			}
		} else {
			oper.append("error");
		}

		oper.append("}");
		super.render(response, oper.toString(), "text/x-json;charset=UTF-8");
		return null;

	}

}