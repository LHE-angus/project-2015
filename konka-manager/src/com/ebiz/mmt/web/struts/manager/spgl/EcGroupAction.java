package com.ebiz.mmt.web.struts.manager.spgl;

import java.util.ArrayList;
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

import com.ebiz.mmt.domain.EcGroup;
import com.ebiz.mmt.domain.EcUser;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

public class EcGroupAction extends BaseAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// if (null == super.checkUserModPopeDom(form, request, "0")) {
		// return super.checkPopedomInvalid(request, response);
		// }
		super.getModPopeDom(form, request);
		setNaviStringToRequestScope(form, request);

		return mapping.findForward("list");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// if (null == super.checkUserModPopeDom(form, request, "1")) {
		// return super.checkPopedomInvalid(request, response);
		// }
		super.getModPopeDom(form, request);
		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);

		String id = (String) dynaBean.get("id"); // 组织ID
		String is_edit = (String) dynaBean.get("is_edit");// 修改
		String par_id_for_zb = (String) dynaBean.get("par_id_for_zb");// 没有组织的时候，默认添加一个最大的组织

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
			request.setAttribute("is_zb", 1);
		}

		request.setAttribute("sybDeptInfoList", super.getDeptInfoListWithOutLimit(mapping, form, request, response));
		request.setAttribute("queryString", super.serialize(request, "id", "method"));
		if (StringUtils.isBlank(id) && StringUtils.isNotBlank(par_id_for_zb)) {
			dynaBean.set("add_for_zb", true);
			dynaBean.set("edit", "0");// 添加
			dynaBean.set("par_id", "-1");
			return new ActionForward("/../manager/spgl/EcGroup/form2.jsp");
		} else if (StringUtils.isNotBlank(id) && StringUtils.isBlank(is_edit)) {
			EcGroup ec = new EcGroup();
			ec.setId(Long.valueOf(id));
			ec = super.getFacade().getEcGroupService().getEcGroup(ec);
			if (ec != null) {
				dynaBean.set("par_id", ec.getId());
			}
			dynaBean.set("edit", "0");// 添加
			EcGroup ee = new EcGroup();
			ee.getMap().put("par_id", ec.getId());
			List<EcGroup> ecGroupList = super.getFacade().getEcGroupService().getEcGroupForTreeNameList(ee);
			request.setAttribute("ecGroupList", ecGroupList);
			return new ActionForward("/../manager/spgl/EcGroup/form2.jsp");
		} else if (StringUtils.isNotBlank(id) && StringUtils.isNotBlank(is_edit)) {
			EcGroup ec = new EcGroup();
			ec.setId(Long.valueOf(id));
			ec = super.getFacade().getEcGroupService().getEcGroup(ec);
			dynaBean.set("edit", "1");// 修改
			copyProperties(form, ec);

			EcGroup ee = new EcGroup();
			List<EcGroup> ecGroupList = super.getFacade().getEcGroupService().getEcGroupForTreeNameList(ee);
			request.setAttribute("ecGroupList", ecGroupList);

			dynaBean.set("par_id", ec.getPar_id());
			dynaBean.set("mod_id", dynaBean.get("mod_id"));

			return new ActionForward("/../manager/spgl/EcGroup/form2.jsp");
		}

		return mapping.findForward("input");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// if (null == super.checkUserModPopeDom(form, request, "2")) {
		// return super.checkPopedomInvalid(request, response);
		// }
		super.getModPopeDom(form, request);
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String id = (String) dynaBean.get("id");

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

		//System.out.println("id3333333333333----->" + id);

		String par_id = (String) dynaBean.get("par_id");
		// String contain_subdepts = (String) dynaBean.get("contain_subdepts");

		// 获取登录用户 企业信息
		// PeProdUser userInfo = (PeProdUser) super.getSessionAttribute(request,
		// Constants.USER_INFO);
		// 获取登录用户 角色

		EcGroup eg = new EcGroup();
		eg.setId(Long.valueOf(id));
		eg = super.getFacade().getEcGroupService().getEcGroup(eg);

		EcGroup eg2 = new EcGroup();
		eg2.setPar_id(-1L);
		Long count = super.getFacade().getEcGroupService().getEcGroupCount(eg2);

		if (eg == null && par_id.equals("-1") && count == 0) {
			dynaBean.set("operate_type", 1);// 没有组织，新增
		} else {
			dynaBean.set("operate_type", 0);// 有组织，新增子组织或者修改该组织
			if (eg != null) {
				dynaBean.set("dept_name", eg.getGroup_name());

				EcUser entity = new EcUser();
				entity.getMap().put("group_id", id);
				entity.setIs_del(0);
				entity.setUser_type(2);
				entity.getMap().put("add_date_gt", "2015-01-09");
				entity.setIs_epp_fgs(0);// 非EPP分公司会员
				Long recordCount = super.getFacade().getEcUserService().getEcUserNewCount(entity);
				pager.init(recordCount, 10, pager.getRequestPage());
				entity.getRow().setFirst(pager.getFirstRow());
				entity.getRow().setCount(pager.getRowCount());
				List<EcUser> entityList = super.getFacade().getEcUserService().getEcUserNewPaginatedList(entity);
				if (zb) {
					request.setAttribute("entityList", entityList);
				} else if (!zb & fgs) {
					if (id.equals("0") || id.equals("1000")) {
						request.setAttribute("entityList", null);
					} else {
						request.setAttribute("entityList", entityList);
					}
				}

			} else {
				eg2.setPar_id(-1L);
				eg2 = super.getFacade().getEcGroupService().getEcGroup(eg2);
				dynaBean.set("id", eg2.getId().toString());
				dynaBean.set("dept_name", eg2.getGroup_name());

				EcUser entity = new EcUser();
				entity.getMap().put("group_id", eg2.getId());
				entity.setUser_type(2);
				entity.getMap().put("add_date_gt", "2015-01-09");
				entity.setIs_del(0);
				entity.setIs_epp_fgs(0);// 非EPP分公司会员
				Long recordCount = super.getFacade().getEcUserService().getEcUserNewCount(entity);
				pager.init(recordCount, 10, pager.getRequestPage());
				entity.getRow().setFirst(pager.getFirstRow());
				entity.getRow().setCount(pager.getRowCount());
				List<EcUser> entityList = super.getFacade().getEcUserService().getEcUserNewPaginatedList(entity);
				if (zb) {
					request.setAttribute("entityList", entityList);
				} else if (!zb & fgs) {
					if (id.equals("0") || id.equals("1000")) {
						request.setAttribute("entityList", null);
					} else {
						request.setAttribute("entityList", entityList);
					}
				}
			}
		}

		dynaBean.set("mod_id", dynaBean.get("mod_id"));
		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String edit = (String) dynaBean.get("edit");
		String par_id = (String) dynaBean.get("par_id");
		String id = (String) dynaBean.get("id");
		String link_dept_id = (String) dynaBean.get("link_dept_id");

		super.getModPopeDom(form, request);

		EcGroup entity = new EcGroup();

		super.copyProperties(entity, form);

		// 获取登录用户 企业信息
		PeProdUser SxUserInfo = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

		if (StringUtils.isNotBlank(edit) && edit.equals("0")) {

			// if (StringUtils.isNotBlank(link_dept_id)) {
			// EcGroup ef = new EcGroup();
			// ef.setLink_dept_id(Long.valueOf(link_dept_id));
			// ef.setPar_id(Long.valueOf(par_id));
			// Long count =
			// super.getFacade().getEcGroupService().getEcGroupCount(ef);
			// if (count > 0) {
			// super.renderJavaScript(response,
			// "window.onload=function(){alert('关联部门重复了！');history.back();}");
			// return null;
			// }
			// }

			if (!par_id.equals("-1")) {
				EcGroup ef = new EcGroup();
				ef.setPar_id(-1L);
				ef = super.getFacade().getEcGroupService().getEcGroup(ef);
				if (!par_id.equals(ef.getId().toString())) {
					super.renderJavaScript(response, "window.onload=function(){alert('只能添加二级部门！');history.back();}");
					return null;
				}

			}

			if (par_id.equals("-1")) {
				entity.setPar_id(-1L);
			}
			entity.setDel_mark(0);
			entity.setAdd_user_id(SxUserInfo.getId());
			entity.setAdd_date(new Date());
			super.getFacade().getEcGroupService().createEcGroup(entity);
			super.saveMessage(request, "entity.inserted");
			request.setAttribute("add_dept", "add_dept");
		} else if (StringUtils.isNotBlank(edit) && edit.equals("1")) {
			// if (StringUtils.isNotBlank(link_dept_id)) {
			// EcGroup ef = new EcGroup();
			// ef.setId(Long.valueOf(id));
			// ef = super.getFacade().getEcGroupService().getEcGroup(ef);
			// if (ef.getLink_dept_id() == null) {
			// EcGroup eg = new EcGroup();
			// eg.setLink_dept_id(Long.valueOf(link_dept_id));
			// Long count =
			// super.getFacade().getEcGroupService().getEcGroupCount(eg);
			// if (count > 0) {
			// super.renderJavaScript(response,
			// "window.onload=function(){alert('关联部门重复了！');history.back();}");
			// return null;
			// }
			// } else if (!ef.getLink_dept_id().toString().equals(link_dept_id))
			// {
			// EcGroup eg = new EcGroup();
			// eg.setLink_dept_id(Long.valueOf(link_dept_id));
			// Long count =
			// super.getFacade().getEcGroupService().getEcGroupCount(eg);
			// if (count > 0) {
			// super.renderJavaScript(response,
			// "window.onload=function(){alert('关联部门重复了！');history.back();}");
			// return null;
			// }
			// }
			// }
			if (StringUtils.isBlank(link_dept_id)) {
				entity.getMap().put("link_is_null", true);
				entity.setLink_dept_id(null);
			}

			super.getFacade().getEcGroupService().modifyEcGroup(entity);
			super.saveMessage(request, "entity.updated");
			request.setAttribute("add_dept", "add_dept");
		}

		dynaBean.set("id", "" + entity.getId());
		//System.out.println("id----->" + entity.getId());
		dynaBean.set("mod_id", "" + dynaBean.get("mod_id"));
		return edit(mapping, form, request, response);
	}

	public ActionForward showDeptInfoTree(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");

		// 获取登录用户信息
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

		EcGroup entity = new EcGroup();

		EcGroup eg = new EcGroup();
		eg.setPar_id(-1L);
		eg = super.getFacade().getEcGroupService().getEcGroup(eg);
		if (eg != null) {
			entity.getMap().put("id", eg.getId());
		} else {
			entity.getMap().put("id", 0);
		}

		if (zb) {

		} else if (!zb && fgs) {
			List<String> ids = new ArrayList<String>();
			Long fgs_id = super.getSuperiorForDeptType(user.getDept_id(), 3).getDept_id();
			ids.add("0");
			ids.add(fgs_id.toString());
			entity.getMap().put("fgs_ids_in", ids);
		}

		List<EcGroup> groupInfoList = super.getFacade().getEcGroupService().getEcGroupForTreePaginatedList(entity);

		String treeNodes = getTreeNodesFromDeptInfoAndModIdList(groupInfoList, mod_id);

		request.setAttribute("treeNodes", treeNodes);

		return new ActionForward("/../manager/spgl/EcGroup/deptInfoTree.jsp");
	}

	public ActionForward showNavi(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		return new ActionForward("/../manager/spgl/EcGroup/navi.jsp");
	}

	/**
	 * @author WangKunLin 2014-11-15 加入了mod_id,方便是用添加 修改等权限控制
	 * @param deptInfoList
	 * @param mod_id
	 * @return String
	 */

	public String getTreeNodesFromDeptInfoAndModIdList(List<EcGroup> deptInfoList, String mod_id) {

		StringBuffer sb = new StringBuffer();

		for (EcGroup konkaDept : deptInfoList) {

			String _id = String.valueOf(konkaDept.getId());
			String _par_id = String.valueOf(konkaDept.getPar_id());

			if ("0".equals(_par_id)) {
				if (StringUtils.equals(_id, _par_id)) {
					_par_id = "-1";
				}
			}
			String _text = StringUtils.replace(konkaDept.getGroup_name(), ":", "&#58;");
			if (StringUtils.isEmpty(_text)) {
				continue;
			}
			String _hint = _text;

			sb.append("\ntree.nodes[\"").append(_par_id).append("_").append(_id).append("\"]=\"");
			sb.append("text:").append(_text).append(";");
			if (_hint.length() > 0) {
				sb.append("hint:").append(_hint).append(";");
			}

			sb.append("url:").append("EcGroup.do").append(";");

			sb.append("data:").append("method=edit");
			sb.append("&mod_id=").append(mod_id);
			sb.append("&par_id=").append(_par_id);
			sb.append("&id=").append(_id).append(";");

			sb.append("\";");
		}
		logger.info("sb---------->" + sb.toString());

		return sb.toString();
	}

	public ActionForward validateGroupName(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		String group_name = (String) dynaBean.get("group_name");
		EcGroup entity = new EcGroup();
		String isExist = "null";

		if (StringUtils.isNotBlank(group_name)) {
			entity.setGroup_name(group_name);
			Long count = super.getFacade().getEcGroupService().getEcGroupCount(entity);
			if (count > 0) {
				isExist = String.valueOf("1");
			} else {
				isExist = String.valueOf("0");
			}
		}
		super.render(response, isExist, "text/x-json;charset=UTF-8");
		return null;

	}

	public ActionForward validateDeptSN(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String dept_sn = (String) dynaBean.get("dept_sn");
		KonkaDept entity = new KonkaDept();
		String isExist = "null";
		if (StringUtils.isNotBlank(dept_sn)) {
			entity.setDept_sn(dept_sn);
			entity = super.getFacade().getKonkaDeptService().getKonkaDept(entity);
			if (null == entity) {
				isExist = String.valueOf("0");
			} else {
				isExist = String.valueOf("1");
			}
		}
		super.render(response, isExist, "text/x-json;charset=UTF-8");
		return null;
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");

		if (!GenericValidator.isLong(id)) {
			saveError(request, "errors.long", new String[] { id });
			return mapping.findForward("edit");
		}

		KonkaR3Shop entity = new KonkaR3Shop();
		entity.setId(Long.valueOf(id));
		entity = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(entity);

		if (null == entity) {
			saveError(request, "errors.long", new String[] { id });
			return mapping.findForward("edit");
		}
		entity.setQueryString(super.serialize(request, "id", "mod_id"));
		request.setAttribute("entity", entity);

		return mapping.findForward("view");
	}

	public ActionForward listForDept(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String selects = (String) dynaBean.get("selects");

		KonkaDept entity = new KonkaDept();
		entity.setDept_type(3);
		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

		if (peProdUser.getDept_id() != 0) {// 非管理员
			entity.setDept_id(peProdUser.getDept_id());
		}

		List<KonkaDept> entityList = super.getFacade().getKonkaDeptService().getKonkaDeptList(entity);
		request.setAttribute("entityList", entityList);

		if (StringUtils.isNotBlank(selects)) {
			List<KonkaDept> konkaDeptList = new ArrayList<KonkaDept>();
			String[] arr = selects.split(",");
			for (int i = 0; i < arr.length; i++) {
				KonkaDept konkadept = new KonkaDept();
				konkadept.setDept_id(Long.valueOf(arr[i]));
				konkaDeptList.add(konkadept);
			}
			request.setAttribute("konkaDeptList", konkaDeptList);
		}

		return new ActionForward("/../manager/admin/KonkaDept/dept_list.jsp");
	}

}
