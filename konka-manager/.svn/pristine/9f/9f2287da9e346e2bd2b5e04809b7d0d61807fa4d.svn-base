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
import com.ebiz.mmt.domain.KonkaJxcStoreInfo;
import com.ebiz.mmt.domain.KonkaXxGoods;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Jiang,JiaYong
 * @version 2012-01-10
 */
public class KonkaXxGoodsAction extends BaseZmdAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;

		Pager pager = (Pager) dynaBean.get("pager");
		// 查询条件
		String add_date_start = (String) dynaBean.get("add_date_start");
		String add_date_end = (String) dynaBean.get("add_date_end");
		String dept_id = (String) dynaBean.get("dept_id");

		// 取当前登录用户
		PeProdUser ui = super.getSessionUserInfo(request);
		// 取用户角色
		PeRoleUser peRoleUser = super.getRoleInfoByThisLogin(request);
		List<PeRoleUser> peRoleUserList = getPeRoleList(ui.getId());

		Boolean role_id_gt_400 = false;
		Boolean role_id_200_btw_300 = false;
		Boolean role_id_ge_30 = false;
		Boolean role_id_btw_300_400 = false;
		Boolean role_id_btw_30_40 = false;

		if (peRoleUserList.size() > 0) {
			for (PeRoleUser temp : peRoleUserList) {
				if (temp.getRole_id() <= 400) {
					role_id_gt_400 = true;
				}
				if (temp.getRole_id() >= 200 && temp.getRole_id() < 300) {
					role_id_200_btw_300 = true;
				}
				if (temp.getRole_id() < 30) {
					role_id_ge_30 = true;
				}
				if (temp.getRole_id() >= 300 && temp.getRole_id() < 400) {
					role_id_btw_300_400 = true;
				}
				if (temp.getRole_id() >= 30 && temp.getRole_id() < 40) {
					role_id_btw_30_40 = true;
				}
			}
		}
		request.setAttribute("role_id_200_btw_300", role_id_200_btw_300);
		request.setAttribute("role_id_ge_30", role_id_ge_30);
		request.setAttribute("role_id_btw_300_400", role_id_btw_300_400);
		request.setAttribute("role_id_btw_30_40", role_id_btw_30_40);

		if (!role_id_gt_400) {
			String msg = super.getMessage(request, "konka.xx.zmd.role.error");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		KonkaXxGoods entity = new KonkaXxGoods();
		if (StringUtils.isNotBlank(add_date_start))
			entity.getMap().put("add_date_start", add_date_start);
		if (StringUtils.isNotBlank(add_date_end))
			entity.getMap().put("add_date_end", add_date_end);

		if (role_id_200_btw_300 || role_id_ge_30) { // 新兴渠道部及新兴渠道部以上用户
			if (GenericValidator.isLong(dept_id)) {
				entity.setDept_id(Long.valueOf(dept_id));
			}

			// 取分公司信息列表
			KonkaDept konkaDept = new KonkaDept();
			konkaDept.setDept_type(3); // 只取分公司的
			konkaDept.setPar_id(0L);
			List<KonkaDept> konkaDeptList = super.getFacade().getKonkaDeptService().getKonkaDeptList(konkaDept);
			request.setAttribute("konkaDeptList", konkaDeptList);
		} else if (role_id_btw_300_400) { // 分公司及分公司以下用户
			entity.setDept_id(getKonkaDeptForFgs(ui.getDept_id()).getDept_id());
		}

		Long recordCount = getFacade().getKonkaXxGoodsService().getKonkaXxGoodsCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<KonkaXxGoods> entityList = getFacade().getKonkaXxGoodsService()
				.getKonkaXxGoodsPaginatedListIncludeRelevanceInfo(entity);
		request.setAttribute("entityList", entityList);

		dynaBean.set("role_id", peRoleUser.getRole_id());
		dynaBean.set("dept_id", dept_id);
		dynaBean.set("add_date_start", add_date_start);
		dynaBean.set("add_date_end", add_date_end);

		return mapping.findForward("list");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "1")) {
			return super.checkPopedomInvalid(request, response);
		}
		saveToken(request);
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		dynaBean.set("queryString", super.serialize(request, "goods_id", "method"));

		// 取当前登录用户
		PeProdUser ui = super.getSessionUserInfo(request);
		// 取用户角色
		// PeRoleUser peRoleUser = super.getRoleInfoByThisLogin(request);
		List<PeRoleUser> peRoleUserList = getPeRoleList(ui.getId());

		Boolean role_id_gt_400 = false;
		Boolean role_id_btw_30_40 = false;
		Boolean role_id_btw_300_400 = false;

		if (peRoleUserList.size() > 0) {
			for (PeRoleUser temp : peRoleUserList) {
				if (temp.getRole_id() <= 400) {
					role_id_gt_400 = true;
				}
				if (temp.getRole_id() >= 30 && temp.getRole_id() < 40) {
					role_id_btw_30_40 = true;
				}
				if (temp.getRole_id() >= 300 && temp.getRole_id() < 400) {
					role_id_btw_300_400 = true;
				}
			}
		}

		// 取用户角色
		// PeRoleUser peRoleUser = super.getRoleInfoByThisLogin(request);
		if (!role_id_gt_400) {
			String msg = super.getMessage(request, "konka.xx.zmd.role.error");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		// 取仓库信息
		if (role_id_btw_30_40 || role_id_btw_300_400) {
			KonkaJxcStoreInfo store = new KonkaJxcStoreInfo();
			store.setAdd_dept_id(getKonkaDeptForFgs(ui.getDept_id()).getDept_id());
			store.setIs_del(0);
			List<KonkaJxcStoreInfo> storeList = super.getFacade().getKonkaJxcStoreInfoService()
					.getKonkaJxcStoreInfoList(store);

			request.setAttribute("storeList", storeList);
		} else {
			String msg = super.getMessage(request, "konka.xx.zmd.role.error");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

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

		KonkaXxGoods entity = new KonkaXxGoods();
		super.copyProperties(entity, form);
		
		Long dept_id = getKonkaDeptForFgs(super.getSessionUserInfo(request).getDept_id()).getDept_id();
		
		KonkaXxGoods entity1 = new KonkaXxGoods();
		entity1.setDept_id(dept_id);
		entity1.setGoods_name(entity.getGoods_name());
		entity1.setStore(entity.getStore());
		entity1.getMap().put("goods_id_not_eq", entity.getGoods_id());
		List<KonkaXxGoods> entity1List = super.getFacade().getKonkaXxGoodsService().getKonkaXxGoodsList(entity1);
		if (null != entity1List && entity1List.size() > 0) {
			saveError(request, "konka.zmd.wl.repit", entity.getGoods_name());
			return this.list(mapping, form, request, response);
		}

		if (null == entity.getGoods_id()) {
			entity.setAdd_user_id(super.getSessionUserInfo(request).getId());
			entity.setDept_id(dept_id);

			getFacade().getKonkaXxGoodsService().createKonkaXxGoods(entity);
			saveMessage(request, "entity.inserted");
		} else {
			getFacade().getKonkaXxGoodsService().modifyKonkaXxGoods(entity);
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
		String goods_id = (String) dynaBean.get("goods_id");

		if (GenericValidator.isLong(goods_id)) {
			KonkaXxGoods entity = new KonkaXxGoods();
			entity.setGoods_id(Long.valueOf(goods_id));
			entity = getFacade().getKonkaXxGoodsService().getKonkaXxGoods(entity);
			if (null == entity) {
				saveMessage(request, "entity.missing");
				return mapping.findForward("list");
			}
			entity.setQueryString(super.serialize(request, "goods_id", "method"));
			super.copyProperties(form, entity);

			// 取当前登录用户
			PeProdUser ui = super.getSessionUserInfo(request);
			// 取当前用户角色
			// PeRoleUser peRoleUser = super.getRoleInfoByThisLogin(request);
			List<PeRoleUser> peRoleUserList = getPeRoleList(ui.getId());

			Boolean role_id_btw_30_40 = false;
			Boolean role_id_btw_300_400 = false;

			if (peRoleUserList.size() > 0) {
				for (PeRoleUser temp : peRoleUserList) {
					if (temp.getRole_id() >= 30 && temp.getRole_id() < 40) {
						role_id_btw_30_40 = true;
					}
					if (temp.getRole_id() >= 300 && temp.getRole_id() < 400) {
						role_id_btw_300_400 = true;
					}
				}
			}
			// 取仓库信息
			if (role_id_btw_300_400 || role_id_btw_30_40) {
				KonkaJxcStoreInfo store = new KonkaJxcStoreInfo();
				store.setAdd_dept_id(getKonkaDeptForFgs(ui.getDept_id()).getDept_id());
				store.setIs_del(0);
				List<KonkaJxcStoreInfo> storeList = super.getFacade().getKonkaJxcStoreInfoService()
						.getKonkaJxcStoreInfoList(store);

				request.setAttribute("storeList", storeList);
			} else {
				String msg = super.getMessage(request, "konka.xx.zmd.role.error");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			}

			return mapping.findForward("input");
		} else {
			saveError(request, "errors.long", goods_id);
			return mapping.findForward("list");
		}
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "2")) {
			return super.checkPopedomInvalid(request, response);
		}
		saveToken(request);
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String goods_id = (String) dynaBean.get("goods_id");

		if (GenericValidator.isLong(goods_id)) {
			KonkaXxGoods entity = new KonkaXxGoods();
			entity.setGoods_id(Long.valueOf(goods_id));
			entity = getFacade().getKonkaXxGoodsService().getKonkaXxGoods(entity);
			if (null == entity) {
				saveMessage(request, "entity.missing");
				return mapping.findForward("list");
			}
			entity.setQueryString(super.serialize(request, "goods_id", "method"));
			super.copyProperties(form, entity);

			// 取仓库信息
			KonkaJxcStoreInfo store = new KonkaJxcStoreInfo();
			store.setId(entity.getStore());
			store = super.getFacade().getKonkaJxcStoreInfoService().getKonkaJxcStoreInfo(store);
			request.setAttribute("store", store);

			return mapping.findForward("view");
		} else {
			String msg = super.getMessage(request, "errors");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}
	}
}
