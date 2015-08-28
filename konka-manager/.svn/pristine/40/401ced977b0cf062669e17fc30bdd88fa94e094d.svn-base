package com.ebiz.mmt.web.struts.manager.zmd;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaXxZmd;
import com.ebiz.mmt.domain.KonkaXxZmdUsers;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Zheng,Kun
 * @version 2012-1-12
 */

public class KonkaXxZmdClerkAction extends BaseZmdAction {

	private static String CHECK_BOX_CHECKED_VALUE = "on";

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;

		Pager pager = (Pager) dynaBean.get("pager");
		String real_name_like = (String) dynaBean.get("real_name_like");

		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		KonkaXxZmd entity = new KonkaXxZmd();
		
		
		if (StringUtils.isNotBlank(real_name_like)) {
			entity.getMap().put("real_name_like", real_name_like);
		}
		KonkaDept kDept = getKonkaDeptForFgs(peProdUser.getDept_id());
		if (kDept != null)
		entity.setDept_id(kDept.getDept_id());
		Long recordCount = getFacade().getKonkaXxZmdService().getKonkaXxZmdForClerkCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<KonkaXxZmd> entityList = super.getFacade().getKonkaXxZmdService().getKonkaXxZmdForClerkPaginatedList(
				entity);

		setBaseTypeListByParIdToRequest(10000L, request);
		setBaseTypeListByParIdToRequest(20000L, request);
		request.setAttribute("entityList", entityList);
		return mapping.findForward("list");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		saveToken(request);
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String user_id = (String) dynaBean.get("user_id");
		String role_id = (String) dynaBean.get("role_id");

		dynaBean.set("user_id", user_id);
		PeProdUser user = new PeProdUser();
		user.setId(Long.valueOf(user_id));
		user = super.getFacade().getPeProdUserService().getPeProdUser(user);

		dynaBean.set("real_name", user.getReal_name());

		// 取符合条件的人
		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		
		Long dept_id =null;
		KonkaDept kDept = getKonkaDeptForFgs(peProdUser.getDept_id());
		if (kDept != null){
			dept_id = kDept.getDept_id();
		}
		
		peProdUser.getMap().put("role_ids", "400,390");
		peProdUser.getMap().put("dept_id", dept_id);
		List<PeProdUser> peProdUserList = super.getFacade().getPeProdUserService().getPeProdUserWithRoleNameList(
				peProdUser);
		request.setAttribute("peProdUserList", peProdUserList);

		if (GenericValidator.isLong(user_id)) {

			// 回显专卖店
			KonkaXxZmdUsers konkaXxZmdUsers = new KonkaXxZmdUsers();
			konkaXxZmdUsers.setUser_id(Long.valueOf(user_id));
			List<KonkaXxZmdUsers> konkaXxZmdUsersList = super.getFacade().getKonkaXxZmdUsersService()
					.getKonkaXxZmdForWithUserList(konkaXxZmdUsers);
			for (KonkaXxZmdUsers set : konkaXxZmdUsersList) {
				dynaBean.set("zmd_id_" + set.getZmd_id().toString(), set.getZmd_id() == null ? ""
						: CHECK_BOX_CHECKED_VALUE);
			}

			// 取符合条件的专卖店
			KonkaXxZmd kxz = new KonkaXxZmd();
			kxz.setDept_id(dept_id);
			kxz.setArc_state(20300);
			List<KonkaXxZmd> kxzList = super.getFacade().getKonkaXxZmdService().getKonkaXxZmdList(kxz);
			request.setAttribute("kxzList", kxzList);

			// the line below is added for pagination
			kxz.setQueryString(super.serialize(request, "user_id", "method"));
			// end
			request.setAttribute("role_id_1", role_id);
			return mapping.findForward("input");

		} else {
			this.saveError(request, "errors.long", new String[] { user_id });
			return mapping.findForward("list");
		}

	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String zmd_ids = (String) dynaBean.get("zmd_ids");
		String user_id = (String) dynaBean.get("user_id");
		String mod_id = (String) dynaBean.get("mod_id");
		String queryString = (String) dynaBean.get("queryString");

		if (StringUtils.isBlank(zmd_ids)) {
			this.saveError(request, "errors.required", "zmd_ids");
			return mapping.findForward("list");
		}

		KonkaXxZmdUsers entity = new KonkaXxZmdUsers();
		entity.getMap().put("zmd_ids", zmd_ids);
		
		entity.setUser_id(Long.valueOf(user_id));

		super.getFacade().getKonkaXxZmdUsersService().createKonkaXxZmdUsers(entity);
		saveMessage(request, "entity.inserted_success");

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(queryString));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;

	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		// 取符合条件的人员
		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		
		Long dept_id =null;
		KonkaDept kDept = getKonkaDeptForFgs(peProdUser.getDept_id());
		if (kDept != null){
			dept_id = kDept.getDept_id();
		}
		
		peProdUser.getMap().put("role_ids", "400,390");
		peProdUser.getMap().put("dept_id", dept_id);
		List<PeProdUser> peProdUserList = super.getFacade().getPeProdUserService()
				.getPeProdUserWithRoleList(peProdUser);
		for (PeProdUser user : peProdUserList) {
			PeRoleUser role = new PeRoleUser();
			role.setUser_id(user.getId());
			role = super.getFacade().getPeRoleUserService().getPeRoleUser(role); // onle
			user.getMap().put("role", role);
		}
		request.setAttribute("peProdUserList", peProdUserList);

		// 取符合条件的专卖店
		KonkaXxZmd kxz = new KonkaXxZmd();
		kxz.setDept_id(dept_id);
		kxz.setArc_state(20300);
		List<KonkaXxZmd> kxzList = super.getFacade().getKonkaXxZmdService().getKonkaXxZmdList(kxz);
		request.setAttribute("kxzList", kxzList);

		// the line below is added for pagination
		kxz.setQueryString(super.serialize(request, "user_id", "method"));
		// end
		return new ActionForward("/../manager/zmd/KonkaXxZmdClerk/save_form.jsp");
	}
}
