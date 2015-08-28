package com.ebiz.mmt.web.struts.manager.admin;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.alibaba.fastjson.JSON;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaRoleDataLevel;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleInfo;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Hui,Gang
 */
public class PeRoleInfoAction extends BaseAction {
	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "1")) {
			return super.checkPopedomInvalid(request, response);
		}

		DynaBean dynaBean = (DynaBean) form;

		// 获取登录用户 企业信息
		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

		PeRoleUser pru = new PeRoleUser();
		pru.setUser_id(peProdUser.getId());
		List<PeRoleUser> pruList = super.getFacade().getPeRoleUserService().getPeRoleUserList(pru);

		Boolean role_id_gt_30_btw_200_300 = false;
		if (pruList.size() > 0) {
			for (PeRoleUser temp : pruList) {
				if (temp.getRole_id() < 30 || (temp.getRole_id() >= 200 && temp.getRole_id() < 300)) {
					role_id_gt_30_btw_200_300 = true;
				}
			}
		}

		KonkaDept konkaDept = new KonkaDept();// 分公司
		konkaDept.setDept_type(3);

		KonkaDept kDept = new KonkaDept();// 分公司

		Long dept_id = null;
		if (role_id_gt_30_btw_200_300) {// 总部
			dept_id = 0L;
			request.setAttribute("is_admin", true);

		} else {// 分公司
			KonkaDept fgs = getKonkaDeptForFgs(peProdUser.getDept_id());
			dept_id = fgs.getDept_id();
			konkaDept.setDept_id(dept_id);
			kDept.getMap().put("dept_id", dept_id);

			dynaBean.set("dept_id", dept_id.toString());
			dynaBean.set("dept_name", fgs.getDept_name());
			dynaBean.set("dept_sn", fgs.getDept_sn());
		}

		konkaDept.getMap().put("order_by_dept_name", true);
		List<KonkaDept> konkaDeptList = super.getFacade().getKonkaDeptService().getKonkaDeptList(konkaDept);
		request.setAttribute("konkaDeptList", konkaDeptList);

		List<KonkaDept> kTreeList = super.getFacade().getKonkaDeptService().getKonkaDeptListWithTreeNameForProdUser(
				kDept);
		request.setAttribute("kTreeList", kTreeList);

		request.setAttribute("dept_value", dept_id);
		dynaBean.set("is_add_true", "1");
		saveToken(request);
		setNaviStringToRequestScope(form, request);

		return mapping.findForward("input");
	}

	/**
	 * 查询职务管理列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}
		setNaviStringToRequestScope(form, request);
		super.getModPopeDom(form, request);

		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);
		String dept_id = (String) dynaBean.get("dept_id");

		// 获取登录用户 企业信息
		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

		PeRoleUser peRoleUser = new PeRoleUser();
		peRoleUser.setUser_id(peProdUser.getId());
		List<PeRoleUser> peRoleUserList = super.getFacade().getPeRoleUserService().getPeRoleUserList(peRoleUser);
		// if (null == peRoleUserList || peRoleUserList.size() > 1) {
		// super.renderJavaScript(response,
		// "alert('当前登录用户没有设置职务或设置了多个职务!');history.back();");
		// return null;
		// }
		Boolean role_id_eq_10 = false;
		for (PeRoleUser temp : peRoleUserList) {
			if (temp.getRole_id() == 10) {
				role_id_eq_10 = true;
			}
		}

		// peRoleUser = peRoleUserList.get(0);

		Pager pager = (Pager) dynaBean.get("pager");
		String role_name_like = (String) dynaBean.get("role_name_like");

		PeRoleInfo entity = new PeRoleInfo();

		// logger.info("dept_id==========={}",dept_id);
		if (StringUtils.isNotBlank(dept_id)) {
			entity.setDept_id(Long.valueOf(dept_id));
		}

		KonkaDept fgs = super.getSuperiorForDeptType(peProdUser.getDept_id(), 3);

		// if (super.isAdministrator(peProdUser)) {
		if (role_id_eq_10) {
			request.setAttribute("is_admin", true);
			KonkaDept konkaDept = new KonkaDept();
			konkaDept.getMap().put("order_by_dept_name", true);
			konkaDept.setDept_type(3);
			List<KonkaDept> konkaDeptList = super.getFacade().getKonkaDeptService().getKonkaDeptList(konkaDept);

			request.setAttribute("konkaDeptList", konkaDeptList);
		} else {
			if (null != fgs) {
				entity.setDept_id(fgs.getDept_id());
			}
		}

		entity.setIs_del(0);
		entity.getMap().put("role_name_like", role_name_like);

		Long recordCount = getFacade().getPeRoleInfoService().getPeRoleInfoCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<PeRoleInfo> entityList = getFacade().getPeRoleInfoService().getPeRoleInfoForDeptNamePaginatedList(entity);
		request.setAttribute("entityList", entityList);

		return mapping.findForward("list");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "2")) {
			return super.checkPopedomInvalid(request, response);
		}

		saveToken(request);
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String role_id = (String) dynaBean.get("role_id");

		if (!GenericValidator.isLong(role_id)) {
			this.saveError(request, "errors.long", new String[] { role_id });
			return mapping.findForward("list");
		}

		PeRoleInfo entity = new PeRoleInfo();
		entity.setRole_id(Long.valueOf(role_id));
		entity = super.getFacade().getPeRoleInfoService().getPeRoleInfo(entity);
		if (null == entity) {
			saveMessage(request, "entity.missing");
			return mapping.findForward("list");
		}

		// the line below is added for pagination
		entity.setQueryString(super.serialize(request, "role_id", "method"));
		// end

		// 获取登录用户 企业信息
		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

		PeRoleUser pru = new PeRoleUser();
		pru.setUser_id(peProdUser.getId());
		List<PeRoleUser> pruList = super.getFacade().getPeRoleUserService().getPeRoleUserList(pru);

		Boolean role_id_gt_30_btw_200_300 = false;
		if (pruList.size() > 0) {
			for (PeRoleUser temp : pruList) {
				if (temp.getRole_id() < 30 || (temp.getRole_id() >= 200 && temp.getRole_id() < 300)) {
					role_id_gt_30_btw_200_300 = true;
				}
			}
		}

		KonkaDept konkaDept = new KonkaDept();// 分公司
		konkaDept.setDept_type(3);

		KonkaDept kDept = new KonkaDept();// 分公司

		Long dept_id = null;
		if (role_id_gt_30_btw_200_300) {// 总部
			dept_id = 0L;
			request.setAttribute("is_admin", true);

		} else {// 分公司
			KonkaDept fgs = getKonkaDeptForFgs(peProdUser.getDept_id());
			dept_id = fgs.getDept_id();

			konkaDept.setDept_id(dept_id);
			kDept.getMap().put("dept_id", dept_id);

			dynaBean.set("dept_id", dept_id.toString());
			dynaBean.set("dept_name", fgs.getDept_name());
			dynaBean.set("dept_sn", fgs.getDept_sn());
		}

		konkaDept.getMap().put("order_by_dept_name", true);
		List<KonkaDept> konkaDeptList = super.getFacade().getKonkaDeptService().getKonkaDeptList(konkaDept);
		request.setAttribute("konkaDeptList", konkaDeptList);

		List<KonkaDept> kTreeList = super.getFacade().getKonkaDeptService().getKonkaDeptListWithTreeNameForProdUser(
				kDept);
		request.setAttribute("kTreeList", kTreeList);

		request.setAttribute("dept_value", dept_id);

		super.copyProperties(form, entity);

		KonkaRoleDataLevel kDataLevel = new KonkaRoleDataLevel();
		kDataLevel.setRole_id(Long.valueOf(role_id));
		List<KonkaRoleDataLevel> kDataLevelList = super.getFacade().getKonkaRoleDataLevelService()
				.getKonkaRoleDataLevelList(kDataLevel);

		request.setAttribute("kDataLevelList", kDataLevelList);

		return mapping.findForward("input");
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
		// String add_role_with_role_id = (String)
		// dynaBean.get("add_role_with_role_id");
		String role_dept_ids = (String) dynaBean.get("role_dept_ids");
		String role_id = (String) dynaBean.get("role_id");
		String is_add_true = (String) dynaBean.get("is_add_true");

		PeRoleInfo entity = new PeRoleInfo();
		super.copyProperties(entity, form);

		List<KonkaRoleDataLevel> kLevelList = new ArrayList<KonkaRoleDataLevel>();
		if (StringUtils.isNotBlank(role_dept_ids)) {
			String[] dept_ids = StringUtils.split(role_dept_ids, ",");
			for (int i = 0; i < dept_ids.length; i++) {
				KonkaRoleDataLevel kLevel = new KonkaRoleDataLevel();
				kLevel.setDept_id(Long.valueOf(dept_ids[i].split("##")[0]));
				kLevel.setDept_name(dept_ids[i].split("##")[1]);
				kLevelList.add(kLevel);

				if (null != entity.getRole_id()) {
					kLevel.setRole_id(entity.getRole_id());
				}
				if (null != entity.getRole_name()) {
					kLevel.setRole_name(entity.getRole_name());
				}
			}
		}
		entity.setkLevelLsit(kLevelList);

		if (!GenericValidator.isLong(role_id)) {// insert
			// if ("1".equals(add_role_with_role_id)) {
			super.getFacade().getPeRoleInfoService().createPeRoleInfoWithoutAutoKey(entity);
			// } else {
			// super.getFacade().getPeRoleInfoService().createPeRoleInfo(entity);
			// }
			saveMessage(request, "entity.inserted");
		} else if (GenericValidator.isLong(role_id)) {// update

			if (StringUtils.isNotBlank(is_add_true)) {
				// 手写id的
				super.getFacade().getPeRoleInfoService().createPeRoleInfoWithoutAutoKey(entity);
				saveMessage(request, "entity.inserted");
			} else {
				getFacade().getPeRoleInfoService().modifyPeRoleInfoForDept(entity);
				saveMessage(request, "entity.updated");
			}

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

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "4")) {
			return super.checkPopedomInvalid(request, response);
		}

		DynaBean dynaBean = (DynaBean) form;

		String role_id = (String) dynaBean.get("role_id");
		String[] pks = (String[]) dynaBean.get("pks");
		String mod_id = (String) dynaBean.get("mod_id");

		if (!StringUtils.isBlank(role_id) && GenericValidator.isLong(role_id)) {
			PeRoleInfo entity = new PeRoleInfo();
			entity.setRole_id(new Long(role_id));
			super.getFacade().getPeRoleInfoService().removePeRoleInfo(entity);
			saveMessage(request, "entity.deleted");
		} else if (!ArrayUtils.isEmpty(pks)) {
			PeRoleInfo entity = new PeRoleInfo();
			entity.getMap().put("pks", pks);
			super.getFacade().getPeRoleInfoService().removePeRoleInfo(entity);
			saveMessage(request, "entity.deleted");
		}

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "role_id", "pks", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end

		return forward;
	}

	public ActionForward listForRole(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String selects = (String) dynaBean.get("selects");
		String dept_sn = (String) dynaBean.get("dept_sn");
		String role_name_like = (String) dynaBean.get("role_name_like");

		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

		PeRoleInfo entity = new PeRoleInfo();
		entity.setIs_del(0);

		if (StringUtils.isNotBlank(dept_sn)) {
			entity.setDept_sn(dept_sn);
		}
		if (StringUtils.isNotBlank(role_name_like)) {
			entity.getMap().put("role_name_like", role_name_like);
		}

		if (peProdUser.getDept_id() != 0) {// 非管理员
			KonkaDept konkaDept = new KonkaDept();
			konkaDept.setDept_id(peProdUser.getDept_id());
			List<KonkaDept> konkaDeptList = super.getFacade().getKonkaDeptService().getKonkaDeptList(konkaDept);

			if (konkaDeptList.size() > 0) {
				entity.setDept_sn(konkaDeptList.get(0).getDept_sn());
			}
		} else {
			request.setAttribute("is_admin", true);

			KonkaDept konkaDepts = new KonkaDept();
			konkaDepts.setDept_type(3);
			List<KonkaDept> konkaDeptsList = super.getFacade().getKonkaDeptService().getKonkaDeptList(konkaDepts);
			request.setAttribute("konkaDeptList", konkaDeptsList);

		}

		List<PeRoleInfo> entityList = super.getFacade().getPeRoleInfoService().getPeRoleInfoList(entity);
		request.setAttribute("entityList", entityList);

		if (StringUtils.isNotBlank(selects)) {
			List<PeRoleInfo> peRoleInfoList = new ArrayList<PeRoleInfo>();
			String[] arr = selects.split(",");
			for (int i = 0; i < arr.length; i++) {
				PeRoleInfo peRoleInfo = new PeRoleInfo();
				peRoleInfo.setRole_id(Long.valueOf(arr[i]));
				peRoleInfoList.add(peRoleInfo);
			}
			request.setAttribute("peRoleInfoList", peRoleInfoList);
		}

		return new ActionForward("/../manager/admin/PeRoleInfo/role_list.jsp");
	}

	public ActionForward validateRoleId(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;

		StringBuffer oper = new StringBuffer("{\"result\":");
		String role_id = (String) dynaBean.get("role_id");
		PeRoleInfo entity = new PeRoleInfo();
		entity.setRole_id(Long.valueOf(role_id));
		// entity.setIs_del(0);
		Long count = super.getFacade().getPeRoleInfoService().getPeRoleInfoCount(entity);
		if ("0".equals(count.toString())) {
			oper.append(false);
		} else {
			oper.append(true);
		}
		oper.append("}");
		super.render(response, oper.toString(), "text/x-json;charset=UTF-8");
		return null;
	}

	public ActionForward getQueryDeptNames(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		String dept_id = (String) dynaBean.get("dept_id");

		KonkaDept kDept = new KonkaDept();
		if (StringUtils.isNotBlank(dept_id)) {
			kDept.getMap().put("dept_id", dept_id);
		}
		List<KonkaDept> entityList = super.getFacade().getKonkaDeptService().getKonkaDeptListWithTreeNameForProdUser(
				kDept);

		super.renderTextJsonOrJsonp(request, response, JSON.toJSONString(entityList));
		return null;
	}
}