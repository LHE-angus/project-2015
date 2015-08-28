package com.ebiz.mmt.web.struts.manager.spgl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.EcArticleInfo;
import com.ebiz.mmt.domain.EcGroup;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Jiang,JiaYong
 * @version 2012-09-15
 */
public class EcArticleInfoAction extends BaseAction {
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		super.getModPopeDom(form, request);

		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		// String title = (String) dynaBean.get("title");
		String plat_sys = (String) dynaBean.get("plat_sys");
		String dept_id = (String) dynaBean.get("dept_id");

		EcArticleInfo entity = new EcArticleInfo();
		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		boolean zb = false;
		boolean fgs = false;
		PeRoleUser rUser = new PeRoleUser();
		rUser.setUser_id(user.getId());
		List<PeRoleUser> roleUserList = getFacade().getPeRoleUserService().getPeRoleUserList(rUser);
		if (null != roleUserList && roleUserList.size() > 0) {
			for (PeRoleUser pu : roleUserList) {
				if (pu.getRole_id() < 30L || pu.getRole_id().intValue() == 140317 || pu.getRole_id().intValue() == 1001) {
					zb = true;
				} else if (pu.getRole_id().intValue() >= 30 && pu.getRole_id().intValue() <= 188) {
					fgs = true;
				}
			}
		}
		if (zb) {
			EcGroup eg = new EcGroup();
			List<EcGroup> deptList = super.getFacade().getEcGroupService().getEcGroupList(eg);
			request.setAttribute("deptList", deptList);
		} else if (!zb && fgs) {
			EcGroup eg = new EcGroup();
			eg.setLink_dept_id(super.getSuperiorForDeptType(user.getDept_id(), 3).getDept_id());
			List<EcGroup> deptList = super.getFacade().getEcGroupService().getEcGroupList(eg);
			request.setAttribute("deptList", deptList);
			if (deptList != null && deptList.size() > 0) {
				List<String> ids = new ArrayList<String>();
				for (EcGroup ecGroup : deptList) {
					ids.add(ecGroup.getId().toString());
				}
				dynaBean.set("group_id", StringUtils.join(ids, ","));
				dynaBean.set("is_binding", true);
				entity.getMap().put("is_fgs", ids);
			} else {
				entity.getMap().put("is_fgs", new String[] { "999999" });
			}
		}

		super.copyProperties(entity, form);

		if (StringUtils.isNotBlank(dept_id)) {
			entity.setDept_id(Long.valueOf(dept_id));
		}
		if (StringUtils.isNotBlank(plat_sys)) {
			entity.setPlat_sys(Integer.valueOf(plat_sys));
		}

		Long recordCount = super.getFacade().getEcArticleInfoService().getEcArticleInfoCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<EcArticleInfo> entityList = super.getFacade().getEcArticleInfoService()
				.getEcArticleInfoPaginatedList(entity);
		for (EcArticleInfo ee : entityList) {
			if (ee.getDept_id() != null) {
				EcGroup eg = new EcGroup();
				eg.setId(ee.getDept_id());
				eg = super.getFacade().getEcGroupService().getEcGroup(eg);
				if (eg != null) {
					ee.getMap().put("group_name", eg.getGroup_name());
				}
			}
		}

		request.setAttribute("entityList", entityList);
		return mapping.findForward("list");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;

		// the 2 lines below is added for showing @author Xing,XiuDong
		// 2009.06.10

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}
		boolean zb = false;
		boolean fgs = false;
		PeRoleUser rUser = new PeRoleUser();
		rUser.setUser_id(user.getId());
		List<PeRoleUser> roleUserList = getFacade().getPeRoleUserService().getPeRoleUserList(rUser);
		if (null != roleUserList && roleUserList.size() > 0) {
			for (PeRoleUser pu : roleUserList) {
				if (pu.getRole_id() < 30L || pu.getRole_id().intValue() == 178 || pu.getRole_id().intValue() == 140317
						|| pu.getRole_id().intValue() == 1001) {
					zb = true;
				} else if (pu.getRole_id().intValue() >= 30 && pu.getRole_id().intValue() <= 188) {
					fgs = true;
				}
			}
		}

		if (zb) {
			request.setAttribute("is_admin", "1");
			EcGroup eg = new EcGroup();
			List<EcGroup> deptList = super.getFacade().getEcGroupService().getEcGroupList(eg);
			request.setAttribute("deptList", deptList);
		} else if (!zb && fgs) {
			KonkaDept fgs_dept = super.getSuperiorForDeptType(user.getDept_id(), 3);// 先暂用此方法，等待家勇公共方法....
			dynaBean.set("fgs_id", fgs_dept.getDept_id());
			dynaBean.set("fgs_name", fgs_dept.getDept_name());
			request.setAttribute("is_fgs", "1");

			EcGroup eg = new EcGroup();
			eg.setLink_dept_id(fgs_dept.getDept_id());
			List<EcGroup> deptList = super.getFacade().getEcGroupService().getEcGroupList(eg);
			request.setAttribute("deptList", deptList);
		}

		dynaBean.set("del_mark", 0);
		dynaBean.set("queryString", super.serialize(request, "mod_id", "method"));

		return mapping.findForward("input");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		if (GenericValidator.isLong(id)) {

			EcArticleInfo entity = new EcArticleInfo();
			entity.setId(new Long(id));
			entity = getFacade().getEcArticleInfoService().getEcArticleInfo(entity);
			super.copyProperties(form, entity);

			if (entity.getDept_id() != null) {
				EcGroup eg = new EcGroup();
				eg.setId(entity.getDept_id());
				eg = super.getFacade().getEcGroupService().getEcGroup(eg);
				if (eg != null) {
					dynaBean.set("group_name", eg.getGroup_name());
				}
			}

			PeProdUser p = new PeProdUser();
			p.setId(entity.getAdd_user_id());
			p = super.getFacade().getPeProdUserService().getCXYPeProdUser(p);
			dynaBean.set("user_name", p.getUser_name());

			return mapping.findForward("view");
		} else {
			saveMessage(request, "errors.long", new String[] { id });
			return mapping.findForward("list");
		}
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id");

		if (!GenericValidator.isLong(id)) {
			saveError(request, "errors.long", new String[] { id });
			return mapping.findForward("list");
		}

		EcArticleInfo entity = new EcArticleInfo();
		entity.setId(Long.valueOf(id));
		entity = super.getFacade().getEcArticleInfoService().getEcArticleInfo(entity);

		if (null == entity) {
			saveError(request, "errors.long", new String[] { id });
			return mapping.findForward("list");
		}

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}
		boolean zb = false;
		boolean fgs = false;
		PeRoleUser rUser = new PeRoleUser();
		rUser.setUser_id(user.getId());
		List<PeRoleUser> roleUserList = getFacade().getPeRoleUserService().getPeRoleUserList(rUser);
		if (null != roleUserList && roleUserList.size() > 0) {
			for (PeRoleUser pu : roleUserList) {
				if (pu.getRole_id() < 30L || pu.getRole_id().intValue() == 178 || pu.getRole_id().intValue() == 140317
						|| pu.getRole_id().intValue() == 1001) {
					zb = true;
				} else if (pu.getRole_id().intValue() >= 30 && pu.getRole_id().intValue() <= 188) {
					fgs = true;
				}
			}
		}

		if (zb) {
			request.setAttribute("is_admin", "1");
			EcGroup eg = new EcGroup();
			List<EcGroup> deptList = super.getFacade().getEcGroupService().getEcGroupList(eg);
			request.setAttribute("deptList", deptList);
		} else if (!zb && fgs) {
			KonkaDept fgs_dept = super.getSuperiorForDeptType(user.getDept_id(), 3);// 先暂用此方法，等待家勇公共方法....
			dynaBean.set("fgs_id", fgs_dept.getDept_id());
			dynaBean.set("fgs_name", fgs_dept.getDept_name());
			request.setAttribute("is_fgs", "1");

			EcGroup eg = new EcGroup();
			eg.setLink_dept_id(fgs_dept.getDept_id());
			List<EcGroup> deptList = super.getFacade().getEcGroupService().getEcGroupList(eg);
			request.setAttribute("deptList", deptList);
		}

		entity.setQueryString(super.serialize(request, "id", "mod_id"));

		super.copyProperties(form, entity);
		dynaBean.set("mod_id", mod_id);

		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id");

		Date now = new Date();

		PeProdUser userInfo = super.getSessionUserInfo(request);

		EcArticleInfo entity = new EcArticleInfo();
		super.copyProperties(entity, form);

		if (StringUtils.isBlank(id)) {
			entity.setAdd_user_id(userInfo.getId());
			entity.setAdd_date(now);
			super.getFacade().getEcArticleInfoService().createEcArticleInfo(entity);
			saveMessage(request, "entity.inserted");
		} else {
			if (!GenericValidator.isLong(id)) {
				saveError(request, "errors.long", new String[] { id });
				return mapping.findForward("list");
			}
			super.getFacade().getEcArticleInfoService().modifyEcArticleInfo(entity);
			saveMessage(request, "entity.updated");
		}

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&").append("mod_id=" + mod_id);
		pathBuffer.append("&").append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String[] pks = (String[]) dynaBean.get("pks");
		String mod_id = (String) dynaBean.get("mod_id");

		if (!StringUtils.isBlank(id) && GenericValidator.isLong(id)) {
			EcArticleInfo entity = new EcArticleInfo();
			entity.setId(new Long(id));
			entity.setDel_mark(1);
			getFacade().getEcArticleInfoService().modifyEcArticleInfo(entity);
		} else if (!ArrayUtils.isEmpty(pks)) {
			EcArticleInfo entity = new EcArticleInfo();
			entity.setDel_mark(1);
			entity.getMap().put("pks", pks);
			getFacade().getEcArticleInfoService().modifyEcArticleInfo(entity);
		}

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&").append(super.encodeSerializedQueryString(super.serialize(request, "id", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

}
