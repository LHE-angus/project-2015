package com.ebiz.mmt.web.struts.manager.spgl;

import java.util.ArrayList;
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

import com.ebiz.mmt.domain.BaseProvinceListFour;
import com.ebiz.mmt.domain.EcBaseStore;
import com.ebiz.mmt.domain.EcGroup;
import com.ebiz.mmt.domain.EcStoreArea;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Pan,Gang
 * @version 2013-09-16
 */
public class EcBaseStoreAction extends BaseAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String store_type = (String) dynaBean.get("store_type");
		String store_name_like = (String) dynaBean.get("store_name_like");

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
		EcBaseStore entity = new EcBaseStore();
		if (!zb && fgs) {

			EcGroup eg = new EcGroup();
			eg.setLink_dept_id(super.getSuperiorForDeptType(user.getDept_id(), 3).getDept_id());
			List<EcGroup> deptList = super.getFacade().getEcGroupService().getEcGroupList(eg);
			List<String> ids = new ArrayList<String>();
			if (deptList != null && deptList.size() > 0) {
				for (EcGroup string : deptList) {
					ids.add(string.getId().toString());
				}
				entity.getMap().put("group_id_in", ids);
				entity.setPlat_sys(1);
				entity.setOwn_sys(2);
			} else {
				super.renderJavaScript(response, "window.onload=function(){alert('你没有权限查看库存！');history.back();}");
				return null;
			}
		}

		if (StringUtils.isNotBlank(store_type)) {
			entity.setStore_type(Integer.valueOf(store_type));
		}
		if (StringUtils.isNotBlank(store_name_like)) {
			entity.getMap().put("store_name_like", store_name_like);
		}

		Long recordCount = super.getFacade().getEcBaseStoreService().getEcBaseStoreForDeptCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<EcBaseStore> entityList = super.getFacade().getEcBaseStoreService()
		        .getEcBaseStoreForDeptNamePaginatedList(entity);
		for (EcBaseStore ecBaseStore : entityList) {
			if (ecBaseStore.getDept_id() != null) {
				EcGroup ef = new EcGroup();
				ef.setId(ecBaseStore.getDept_id());
				ef = super.getFacade().getEcGroupService().getEcGroup(ef);
				if (ef != null) {
					ecBaseStore.getMap().put("group_name", ef.getGroup_name());
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
			request.setAttribute("is_admin", "1");
			EcGroup eg = new EcGroup();
			List<EcGroup> deptList = super.getFacade().getEcGroupService().getEcGroupList(eg);
			request.setAttribute("groupList", deptList);
		} else if (fgs) {
			request.setAttribute("is_fgs", "1");
			KonkaDept konkaDept = new KonkaDept();
			konkaDept.setDept_id(user.getDept_id());
			konkaDept = super.getFacade().getKonkaDeptService().getKonkaDept(konkaDept);

			// 分公司取得
			if (null != konkaDept.getDept_type() && konkaDept.getDept_type() > 2) {
				KonkaDept fgs_dept = super.getSuperiorForDeptType(user.getDept_id(), 3);
				dynaBean.set("fgs_id", fgs_dept.getDept_id());

				request.setAttribute("is_fgs", "1");
				EcGroup eg = new EcGroup();
				eg.setLink_dept_id(fgs_dept.getDept_id());
				List<EcGroup> deptList = super.getFacade().getEcGroupService().getEcGroupList(eg);

				request.setAttribute("groupList", deptList);

			}
		} else {
			super.renderJavaScript(response, "window.onload=function(){alert('你没有权限查看库存！');history.back();}");
			return null;
		}

		BaseProvinceListFour baseProvinceListFour = new BaseProvinceListFour();
		baseProvinceListFour.setP_level(1);
		List<BaseProvinceListFour> provinceList = super.getFacade().getBaseProvinceListFourService()
		        .getBaseProvinceListFourList(baseProvinceListFour);

		dynaBean.set("store_type", "0");
		request.setAttribute("provinceList", provinceList);
		request.setAttribute("sybDeptInfoList", super.getDeptInfoListWithOutLimit(mapping, form, request, response));

		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String store_id = (String) dynaBean.get("store_id");
		String mod_id = (String) dynaBean.get("mod_id");
		String dept_id = (String) dynaBean.get("dept_id");
		String store_type = (String) dynaBean.get("store_type");
		String remarks = (String) dynaBean.get("remarks");
		String e_id = (String) dynaBean.get("e_id");
		String store_name = (String) dynaBean.get("store_name");
		String own_sys = (String) dynaBean.get("own_sys");
		String dept_id_2 = (String) dynaBean.get("dept_id_2");
		String plat_sys = (String) dynaBean.get("plat_sys");

		EcBaseStore entity = new EcBaseStore();
		super.copyProperties(entity, form);

		if (StringUtils.isNotBlank(dept_id)) {
			entity.setDept_id(Long.valueOf(dept_id));
		}

		if (StringUtils.isEmpty(store_id)) {
			// 验证仓库名唯一性
			EcBaseStore ee = new EcBaseStore();
			ee.setStore_name(store_name);
			ee.setStore_type(Integer.valueOf(store_type));
			Long count1 = super.getFacade().getEcBaseStoreService().getEcBaseStoreCount(ee);
			if (count1 > 0) {
				String msg = super.getMessage(request, "konka.store.name.error");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			}

			if (store_type.equals("0")) {// 全国仓库
				// 唯一性验证
				EcBaseStore ec = new EcBaseStore();
				if (StringUtils.isNotBlank(dept_id_2)) {
					ec.setDept_id(Long.valueOf(dept_id_2));
				}
				ec.setStore_type(0);
				ec.setOwn_sys(Integer.valueOf(own_sys));
				ec.setPlat_sys(Integer.valueOf(plat_sys));
				Long count = super.getFacade().getEcBaseStoreService().getEcBaseStoreCount(ec);
				if (count > 0) {
					String msg = super.getMessage(request, "konka.store.error");
					super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
					return null;
				}

				if (StringUtils.isNotBlank(dept_id_2)) {
					entity.setDept_id(Long.valueOf(dept_id_2));
				}
				super.getFacade().getEcBaseStoreService().createEcBaseStore(entity);
				super.saveMessage(request, "entity.inserted");
			} else if (store_type.equals("1")) {// 区域仓库

				// 区域仓库的省份 不能交叉
				// EcBaseStore ee = new EcBaseStore();
				// ee.setStore_type(1);
				// List<EcBaseStore> ecBaseStoreList =
				// super.getFacade().getEcBaseStoreService()
				// .getEcBaseStoreForAreaForList(ee);
				// if (null != ecBaseStoreList && 0 != ecBaseStoreList.size()) {
				// for (EcBaseStore ecBaseStore : ecBaseStoreList) {
				// if (null != ecBaseStore.getMap().get("pindex_id")
				// &&
				// e_id.contains(ecBaseStore.getMap().get("pindex_id").toString()
				// + "@")) {
				// String msg = super.getMessage(request, "konka.store.error");
				// super.renderJavaScript(response,
				// "window.onload=function(){alert('" + msg
				// + "');history.back();}");
				// return null;
				// }
				// }
				// }

				super.getFacade().getEcBaseStoreService().createEcBaseStoreWithPindexArea(entity, e_id);
				super.saveMessage(request, "entity.inserted");
			} else if (store_type.equals("2")) {// 分公司仓库

				// 分公司仓 唯一性验证
				EcBaseStore ec = new EcBaseStore();
				ec.setStore_type(2);
				ec.setPlat_sys(Integer.valueOf(plat_sys));
				ec.setDept_id(Long.valueOf(dept_id));
				ec.setOwn_sys(Integer.valueOf(own_sys));
				Long count = super.getFacade().getEcBaseStoreService().getEcBaseStoreCount(ec);
				if (count > 0) {
					String msg = super.getMessage(request, "konka.store.error");
					super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
					return null;
				}

				KonkaDept dept = new KonkaDept();
				dept.setDept_id(Long.valueOf(dept_id));
				dept = super.getFacade().getKonkaDeptService().getKonkaDept(dept);

				// // 查询分公司管辖区域
				// List<EcStoreArea> ecStoreAreaList = new
				// ArrayList<EcStoreArea>();
				// BaseProvinceListFour bpl = new BaseProvinceListFour();
				// if (StringUtils.isBlank(dept.getM_areas_ids())) {
				// String msg = super.getMessage(request, "ec.dept.area.error");
				// super.renderJavaScript(response,
				// "window.onload=function(){alert('" + msg +
				// "');history.back();}");
				// return null;
				// }
				// bpl.getMap().put("p_index_in",
				// dept.getM_areas_ids().split(","));
				// List<BaseProvinceListFour> bplList =
				// super.getFacade().getBaseProvinceListFourService()
				// .getBaseProvinceListFourList(bpl);
				// for (BaseProvinceListFour t1 : bplList) {
				// EcStoreArea t = new EcStoreArea();
				// t.setPindex_id(t1.getP_index());
				// t.setP_name(t1.getP_name());
				// ecStoreAreaList.add(t);
				// }
				// entity.setEcStoreAreaList(ecStoreAreaList);
				entity.setRemarks(remarks);
				super.getFacade().getEcBaseStoreService().createEcBaseStoreWithPindex(entity);

			}
		} else {
			if (store_type.equals("1")) {
				super.getFacade().getEcBaseStoreService().modifyEcBaseStoreAndArea(entity, store_id, e_id);
				super.saveMessage(request, "entity.updated");
			} else {
				super.getFacade().getEcBaseStoreService().modifyEcBaseStore(entity);
				super.saveMessage(request, "entity.updated");
			}
		}
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&").append("mod_id=" + mod_id);
		pathBuffer.append("&");
		// pathBuffer.append("&").append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("store_id");
		String mod_id = (String) dynaBean.get("mod_id");

		if (!GenericValidator.isLong(id)) {
			super.saveError(request, "errors.param");
			return mapping.findForward("list");
		}

		EcBaseStore ec = new EcBaseStore();
		ec.setStore_id(Long.valueOf(id));
		super.getFacade().getEcBaseStoreService().removeEcBaseStore(ec);

		saveMessage(request, "entity.deleted");

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.serialize(request, "id", "method"));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward selectProvince(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String store_id = (String) dynaBean.get("store_id");

		if (StringUtils.isBlank(store_id)) {
			EcBaseStore ec = new EcBaseStore();
			request.setAttribute("entityList1", null);
			List<EcBaseStore> entityList2 = super.getFacade().getEcBaseStoreService()
			        .getEcBaseStoreForPindexAndPnameList(ec);
			request.setAttribute("entityList2", entityList2);
		} else {
			// 回显已经选择的省份

			EcStoreArea ea = new EcStoreArea();
			ea.setStore_id(Long.valueOf(store_id));
			Long count = super.getFacade().getEcStoreAreaService().getEcStoreAreaCount(ea);
			if (count > 0) {
				EcBaseStore ec1 = new EcBaseStore();
				ec1.setStore_id(Long.valueOf(store_id));
				List<EcBaseStore> entityList1 = super.getFacade().getEcBaseStoreService()
				        .getEcBaseStoreForPindexIdForList(ec1);
				request.setAttribute("entityList1", entityList1);
				// 可以选择的省份
				EcBaseStore ec2 = new EcBaseStore();
				List<EcBaseStore> entityList2 = super.getFacade().getEcBaseStoreService()
				        .getEcBaseStoreForPindexAndPnameList(ec2);
				request.setAttribute("entityList2", entityList2);
			} else {
				EcBaseStore ec = new EcBaseStore();
				request.setAttribute("entityList1", null);
				List<EcBaseStore> entityList2 = super.getFacade().getEcBaseStoreService()
				        .getEcBaseStoreForPindexAndPnameList(ec);
				request.setAttribute("entityList2", entityList2);
			}

		}

		return new ActionForward("/../manager/spgl/EcBaseStore/selectProvince.jsp");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String store_id = (String) dynaBean.get("store_id");
		String mod_id = (String) dynaBean.get("mod_id");

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
			request.setAttribute("is_admin", "1");
			EcGroup eg = new EcGroup();
			List<EcGroup> deptList = super.getFacade().getEcGroupService().getEcGroupList(eg);
			request.setAttribute("groupList", deptList);
		} else if (fgs) {
			request.setAttribute("is_fgs", "1");
			KonkaDept konkaDept = new KonkaDept();
			konkaDept.setDept_id(user.getDept_id());
			konkaDept = super.getFacade().getKonkaDeptService().getKonkaDept(konkaDept);

			// 分公司取得
			if (null != konkaDept.getDept_type() && konkaDept.getDept_type() > 2) {
				KonkaDept fgs_dept = super.getSuperiorForDeptType(user.getDept_id(), 3);
				dynaBean.set("fgs_id", fgs_dept.getDept_id());

				request.setAttribute("is_fgs", "1");
				EcGroup eg = new EcGroup();
				eg.setLink_dept_id(fgs_dept.getDept_id());
				List<EcGroup> deptList = super.getFacade().getEcGroupService().getEcGroupList(eg);

				request.setAttribute("groupList", deptList);

			}
		} else {
			super.renderJavaScript(response, "window.onload=function(){alert('你没有权限查看库存！');history.back();}");
			return null;
		}

		if (GenericValidator.isLong(store_id)) {

			BaseProvinceListFour entity = new BaseProvinceListFour();
			if (StringUtils.isNotBlank(store_id)) {
				entity.getMap().put("store_id", store_id);
			} else {
				entity.getMap().put("store_id", null);
			}
			List<BaseProvinceListFour> entityList1 = super.getFacade().getBaseProvinceListFourService()
			        .getBaseProvinceListFourForProvinceInList(entity);
			request.setAttribute("entityList1", entityList1);

			EcBaseStore ec = new EcBaseStore();
			ec.setStore_id(Long.valueOf(store_id));
			ec = super.getFacade().getEcBaseStoreService().getEcBaseStore(ec);
			super.copyProperties(form, ec);
			if (ec.getDept_id() != null) {
				dynaBean.set("dept_id_2", ec.getDept_id());

			}
		} else {
			this.saveError(request, "errors.long", new String[] { store_id });
			StringBuffer pathBuffer = new StringBuffer();
			pathBuffer.append(mapping.findForward("success").getPath());
			pathBuffer.append("&mod_id=").append(mod_id);
			pathBuffer.append("&");
			ActionForward forward = new ActionForward(pathBuffer.toString(), true);
			// end
			return forward;
		}

		request.setAttribute("sybDeptInfoList", super.getDeptInfoListWithOutLimit(mapping, form, request, response));
		return mapping.findForward("input");

	}

	public ActionForward getHadBindingPindexId(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String store_id = (String) dynaBean.get("store_id");

		if (!GenericValidator.isLong(store_id)) {
			super.renderJson(response, "{\"pindex_id\":\"\"}");
			return null;
		}

		EcBaseStore entity = new EcBaseStore();
		entity.setStore_id(Long.valueOf(store_id));
		List<EcBaseStore> entityList = super.getFacade().getEcBaseStoreService().getEcBaseStoreForPindexIdForList(
		        entity);
		String s = "";
		for (EcBaseStore ec : entityList) {
			s += ec.getMap().get("pindex_id").toString() + "@";
		}
		super.renderJson(response, "{\"pindex_id\":\"" + s + "\"}");
		return null;
	}

}
