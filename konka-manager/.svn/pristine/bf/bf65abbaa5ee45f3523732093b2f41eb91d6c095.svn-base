package com.ebiz.mmt.web.struts.manager.zmd;

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
import com.ebiz.mmt.domain.KonkaXxDistEmployee;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Jiang,JiaYong
 * @version 2012-4-11
 */
public class KonkaXxDistEmployeeAction extends BaseZmdAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;

		Pager pager = (Pager) dynaBean.get("pager");
		String real_name_like = (String) dynaBean.get("real_name_like");
		String is_del = (String) dynaBean.get("is_del");
		String mobile_phone_like = (String) dynaBean.get("mobile_phone_like");

		KonkaXxDistEmployee entity = new KonkaXxDistEmployee();
		entity.setIs_del(0);
		if (GenericValidator.isLong(is_del)) {
			entity.setIs_del(Integer.valueOf(is_del));
		}
		if (StringUtils.isNotBlank(real_name_like)) {
			entity.getMap().put("real_name_like", real_name_like);
		}
		if (StringUtils.isNotBlank(mobile_phone_like)) {
			entity.getMap().put("mobile_phone_like", mobile_phone_like);
		}
		PeProdUser ui = super.getSessionUserInfo(request);
		// 取用户角色
		Boolean role_id_200_btw_400 = getRoleIdFlag(ui.getId(), 199L, 401L);
		Boolean role_id_30_btw_40 = getRoleIdFlag(ui.getId(), 29L, 40L);
		// PeRoleUser peRoleUser = super.getRoleInfoByThisLogin(request);
		// if (null == peRoleUser || null == peRoleUser.getRole_id() ||
		// peRoleUser.getRole_id() >= 400L) {
		if (!(role_id_200_btw_400 || role_id_30_btw_40)) {
			String msg = super.getMessage(request, "konka.xx.zmd.role.error");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		KonkaDept kDept = getKonkaDeptForFgs(ui.getDept_id());
		
		if (kDept != null)
			entity.setDept_id(kDept.getDept_id());

		Long recordCount = super.getFacade().getKonkaXxDistEmployeeService().getKonkaXxDistEmployeeCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<KonkaXxDistEmployee> entityList = super.getFacade().getKonkaXxDistEmployeeService()
				.gettKonkaXxDistEmployeeForAddUserNamePaginatedList(entity);
		// .getKonkaXxDistEmployeePaginatedList(entity);

		// 查询添加人姓名
		// for (KonkaXxDistEmployee konkaXxDistEmployee : entityList) {
		// PeProdUser peProdUser = new PeProdUser();
		// peProdUser.setId(konkaXxDistEmployee.getAdd_user_id());
		// peProdUser =
		// super.getFacade().getPeProdUserService().getPeProdUser(peProdUser);
		//
		// konkaXxDistEmployee.setAdd_user_name(peProdUser.getReal_name());
		// }

		request.setAttribute("entityList", entityList);

		return mapping.findForward("list");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		PeProdUser ui = super.getSessionUserInfo(request);
		// 取用户角色
		Boolean role_id_200_btw_400 = getRoleIdFlag(ui.getId(), 199L, 401L);
		// PeRoleUser peRoleUser = super.getRoleInfoByThisLogin(request);
		// if (null == peRoleUser || null == peRoleUser.getRole_id() ||
		// peRoleUser.getRole_id() >= 400L) {
		if (!role_id_200_btw_400) {
			String msg = super.getMessage(request, "konka.xx.zmd.role.error");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		KonkaDept kDept = getKonkaDeptForFgs(ui.getDept_id());

		// KonkaDept konkaDept = new KonkaDept();
		// konkaDept.setDept_id(ui.getDept_id());
		// konkaDept =
		// super.getFacade().getKonkaDeptService().getKonkaDept(konkaDept);

		request.setAttribute("konkaDept", kDept);

		return mapping.findForward("input");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String dist_employee_id = (String) dynaBean.get("dist_employee_id");

		if (!GenericValidator.isLong(dist_employee_id)) {
			String msg = super.getMessage(request, "errors.param");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		PeProdUser ui = super.getSessionUserInfo(request);
		List<PeRoleUser> peRoleUserList = getPeRoleList(ui.getId());

		Boolean role_id_gt_400 = false;
		if (peRoleUserList.size() > 0) {
			for (PeRoleUser temp : peRoleUserList) {
				if (temp.getRole_id() <= 400) {
					role_id_gt_400 = true;
				}
			}
		}

		if (!role_id_gt_400) {
			String msg = super.getMessage(request, "konka.xx.zmd.role.error");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		KonkaDept kDept = getKonkaDeptForFgs(ui.getDept_id());
		// KonkaDept konkaDept = new KonkaDept();
		// konkaDept.setDept_id(ui.getDept_id());
		// konkaDept =
		// super.getFacade().getKonkaDeptService().getKonkaDept(konkaDept);
		if (kDept != null)
			request.setAttribute("konkaDept", kDept);

		KonkaXxDistEmployee entity = new KonkaXxDistEmployee();
		entity.setDist_employee_id(Long.valueOf(dist_employee_id));
		entity = super.getFacade().getKonkaXxDistEmployeeService().getKonkaXxDistEmployee(entity);
		super.copyProperties(form, entity);

		request.setAttribute("birthday_format", DateFormatUtils.format(entity.getBirthday(), "yyyy-MM-dd"));

		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String province = (String) dynaBean.get("province");
		String city = (String) dynaBean.get("city");
		String country = (String) dynaBean.get("country");
		String town = (String) dynaBean.get("town");

		KonkaXxDistEmployee entity = new KonkaXxDistEmployee();
		super.copyProperties(entity, form);

		PeProdUser ui = super.getSessionUserInfo(request);
		// 取用户角色
		Boolean role_id_200_btw_400 = getRoleIdFlag(ui.getId(), 199L, 401L);
		// PeRoleUser peRoleUser = super.getRoleInfoByThisLogin(request);
		// if (null == peRoleUser || null == peRoleUser.getRole_id() ||
		// peRoleUser.getRole_id() >= 400L) {
		if (!role_id_200_btw_400) {
			String msg = super.getMessage(request, "konka.xx.zmd.role.error");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		if (GenericValidator.isLong(province))
			entity.setP_index(Long.valueOf(province));
		if (GenericValidator.isLong(city))
			entity.setP_index(Long.valueOf(city));
		if (GenericValidator.isLong(country))
			entity.setP_index(Long.valueOf(country));
		if (GenericValidator.isLong(town))
			entity.setP_index(Long.valueOf(town));

		if (null == entity.getDist_employee_id()) {
			entity.setDept_id(getKonkaDeptForFgs(ui.getDept_id()).getDept_id());
			entity.setAdd_user_id(ui.getId());
			entity.setAdd_date(new Date());

			saveMessage(request, "entity.inserted");
			super.getFacade().getKonkaXxDistEmployeeService().createKonkaXxDistEmployee(entity);
		} else {
			saveMessage(request, "entity.updated");
			super.getFacade().getKonkaXxDistEmployeeService().modifyKonkaXxDistEmployee(entity);
		}

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		return forward;
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String dist_employee_id = (String) dynaBean.get("dist_employee_id");

		KonkaXxDistEmployee entity = new KonkaXxDistEmployee();

		if (StringUtils.isNotBlank(dist_employee_id)) {
			saveMessage(request, "entity.deleted");

			entity.setDist_employee_id(Long.valueOf(dist_employee_id));
			entity.setIs_del(1);
			super.getFacade().getKonkaXxDistEmployeeService().modifyKonkaXxDistEmployee(entity);
		}

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&").append("msg=3");
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "id", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}
}
