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

import com.ebiz.mmt.domain.EcGroup;
import com.ebiz.mmt.domain.EcLogoInfo;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;
import com.ebiz.ssi.web.struts.bean.UploadFile;

/**
 * @author Jiang,JiaYong
 * @version 2012-09-15
 */
public class EcLogoInfoAction extends BaseAction {
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
		String plat_sys = (String) dynaBean.get("plat_sys");
		String dept_id = (String) dynaBean.get("dept_id");
		// String title = (String) dynaBean.get("title");
		String add_user_name_like = (String) dynaBean.get("add_user_name_like");

		EcLogoInfo entity = new EcLogoInfo();
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
			entity.setPlat_sys(1);
		}

		if (StringUtils.isNotBlank(dept_id)) {
			entity.setDept_id(Long.valueOf(dept_id));
		}
		if (StringUtils.isNotBlank(plat_sys)) {
			entity.setPlat_sys(Integer.valueOf(plat_sys));
		}
		if (StringUtils.isNotBlank(add_user_name_like)) {
			entity.getMap().put("add_user_name_like", add_user_name_like);
		}

		super.copyProperties(entity, form);

		Long recordCount = super.getFacade().getEcLogoInfoService().getEcLogoInfoCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<EcLogoInfo> entityList = super.getFacade().getEcLogoInfoService().getEcLogoInfoPaginatedList(entity);
		for (EcLogoInfo ee : entityList) {
			if (ee.getDept_id() != null) {
				EcGroup eg = new EcGroup();
				eg.setId(ee.getDept_id());
				eg = super.getFacade().getEcGroupService().getEcGroup(eg);
				if (eg != null) {
					ee.getMap().put("group_name", eg.getGroup_name());
				}
			}

			PeProdUser pp = new PeProdUser();
			pp.setId(ee.getAdd_user_id());
			pp = super.getFacade().getPeProdUserService().getPeProdUser(pp);
			if (pp != null) {
				ee.getMap().put("add_user_name", pp.getUser_name());
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

			EcLogoInfo entity = new EcLogoInfo();
			entity.setId(new Long(id));
			entity = getFacade().getEcLogoInfoService().getEcLogoInfo(entity);
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

		EcLogoInfo entity = new EcLogoInfo();
		entity.setId(Long.valueOf(id));
		entity = super.getFacade().getEcLogoInfoService().getEcLogoInfo(entity);

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
		String del_mark = (String) dynaBean.get("del_mark");

		Date now = new Date();

		PeProdUser userInfo = super.getSessionUserInfo(request);

		EcLogoInfo entity = new EcLogoInfo();
		super.copyProperties(entity, form);

		List<UploadFile> uploadFileList = super.uploadFile(form, "files/lunbotu/", true, 0, new int[] { 60, 240, 400,
				480, 720, 800 });
		String ctx = super.getCtxPath(request);

		for (UploadFile uploadFile : uploadFileList) {
			if ("image_path".equalsIgnoreCase(uploadFile.getFormName())) {
				entity.setLogo_url(ctx + "/" + uploadFile.getFileSavePath());
			}
		}

		if (StringUtils.isBlank(id)) {
			if (del_mark.equals("0")) {
				EcLogoInfo en = new EcLogoInfo();
				en.setOwn_sys(2);
				en.setDel_mark(0);
				en.setPlat_sys(entity.getPlat_sys());
				en.setDept_id(entity.getDept_id());
				Long count = super.getFacade().getEcLogoInfoService().getEcLogoInfoCount(en);
				if (count > 0) {
					super.renderJavaScript(response,
							"window.onload=function(){alert('每个组织只能有一个发布中的LOGO！');history.back();}");
					return null;
				}
			}

			entity.setAdd_user_id(userInfo.getId());
			entity.setAdd_date(now);
			super.getFacade().getEcLogoInfoService().createEcLogoInfo(entity);
			saveMessage(request, "entity.inserted");
		} else {
			if (!GenericValidator.isLong(id)) {
				saveError(request, "errors.long", new String[] { id });
				return mapping.findForward("list");
			}

			EcLogoInfo old = new EcLogoInfo();
			old.setId(Long.valueOf(id));
			old = super.getFacade().getEcLogoInfoService().getEcLogoInfo(old);

			if (del_mark.equals("0") && old.getDel_mark().intValue() == 1) {
				EcLogoInfo en = new EcLogoInfo();
				en.setOwn_sys(2);
				en.setDel_mark(0);
				en.setPlat_sys(entity.getPlat_sys());
				en.setDept_id(entity.getDept_id());
				Long count = super.getFacade().getEcLogoInfoService().getEcLogoInfoCount(en);
				if (count > 0) {
					super.renderJavaScript(response,
							"window.onload=function(){alert('每个组织只能有一个发布中的LOGO！');history.back();}");
					return null;
				}
			}

			entity.setUpdate_date(new Date());

			super.getFacade().getEcLogoInfoService().modifyEcLogoInfo(entity);
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
			EcLogoInfo entity = new EcLogoInfo();
			entity.setId(new Long(id));
			entity.setDel_mark(1);
			getFacade().getEcLogoInfoService().modifyEcLogoInfo(entity);
		} else if (!ArrayUtils.isEmpty(pks)) {
			EcLogoInfo entity = new EcLogoInfo();
			entity.setDel_mark(1);
			entity.getMap().put("pks", pks);
			getFacade().getEcLogoInfoService().modifyEcLogoInfo(entity);
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
