package com.ebiz.mmt.web.struts.manager.admin;

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
import com.ebiz.mmt.domain.KonkaMobileCategory;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.KonkaR3Store;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Jiang,JiaYong
 * @version 2013-05-09
 */
public class KonkaStoreR3ShopAction extends BaseAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}

		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		super.getModPopeDom(form, request);
		String store_name_like = (String) dynaBean.get("store_name_like");
		String code_like = (String) dynaBean.get("code_like");
		String is_match = (String) dynaBean.get("is_match");

		Pager pager = (Pager) dynaBean.get("pager");

		KonkaR3Store entity = new KonkaR3Store();
		entity.getMap().put("store_name_like", store_name_like);
		entity.getMap().put("code_like", code_like);
		if ("1".equals(is_match)) {
			entity.getMap().put("r3_code_not_null", true);
		} else if ("0".equals(is_match)) {
			entity.getMap().put("r3_code_null", true);
		}

		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		String dept_id = "";
		if (peProdUser != null) {
			KonkaMobileCategory _t = new KonkaMobileCategory();
			_t.setC_index(peProdUser.getDept_id());
			dept_id = super.getFacade().getKonkaMobileCategoryService().getKonkaMobileDept(_t);
		}
		if (null != dept_id) {// 分公司用户
			entity.setDept_id(Long.valueOf(dept_id));
		} else {// 总部用户
			request.setAttribute("isHeadquarters", "true");
		}
		Long recordCount = getFacade().getKonkaR3StoreService().getKonkaR3StoreCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<KonkaR3Store> entityList = getFacade().getKonkaR3StoreService().getKonkaR3StorePaginatedList(entity);
		for (KonkaR3Store konkaR3Store : entityList) {
			if (null != konkaR3Store.getP_index()) {
				konkaR3Store.getMap().put("p_name", super.getPIndexName(konkaR3Store.getP_index().toString(), ""));
			}
			if (StringUtils.isNotEmpty(konkaR3Store.getR3_code())) {
				KonkaR3Shop krs = new KonkaR3Shop();
				krs.setR3_code(konkaR3Store.getR3_code());
				krs = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(krs);
				if (null != krs) {
					konkaR3Store.getMap().put("r3_shop_name", krs.getCustomer_name());
				}
			}
		}
		request.setAttribute("entityList", entityList);

		return mapping.findForward("list");
	}

	public ActionForward listPeProdUser(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		saveToken(request);
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String user_name_like = (String) dynaBean.get("user_name_like");

		PeProdUser entity = new PeProdUser();
		entity.setIs_del(0);
		entity.setPosition_id(Long.valueOf(0));
		if (StringUtils.isNotBlank(user_name_like)) {
			entity.getMap().put("user_name_like", user_name_like);
		}
		Long recordCount = super.getFacade().getPeProdUserService().getPeProdUserCount(entity);
		pager.init(recordCount, Integer.valueOf("45"), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(Integer.valueOf("45"));
		List<PeProdUser> entityList = super.getFacade().getPeProdUserService().getPeProdUserPaginatedList(entity);
		request.setAttribute("entityList", entityList);
		return new ActionForward("/admin/PeShop/list_peproduser.jsp");
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
		String store_id = (String) dynaBean.get("store_id");
		String r3_code = (String) dynaBean.get("r3_code");

		KonkaR3Store entity = new KonkaR3Store();
		super.copyProperties(entity, form);
		if (StringUtils.isNotBlank(r3_code) || StringUtils.isNotBlank(store_id)) {
			super.getFacade().getKonkaR3StoreService().modifyKonkaR3Store(entity);
			saveMessage(request, "entity.updated");
		}

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
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
		dynaBean.set("queryString", super.serialize(request, "store_id", "method"));
		String store_id = (String) dynaBean.get("store_id");

		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		String dept_id = "";
		if (peProdUser != null) {
			KonkaMobileCategory _t = new KonkaMobileCategory();
			_t.setC_index(peProdUser.getDept_id());
			dept_id = super.getFacade().getKonkaMobileCategoryService().getKonkaMobileDept(_t);
		}
		if (null != dept_id) { // 分公司用户
			request.setAttribute("isHeadquarters", "false");
		} else { // 总部用户
			request.setAttribute("isHeadquarters", "true");
		}

		if (GenericValidator.isLong(store_id)) {
			KonkaR3Store konkaR3Store = new KonkaR3Store();
			konkaR3Store.setStore_id(Long.valueOf(store_id));
			konkaR3Store = super.getFacade().getKonkaR3StoreService().getKonkaR3Store(konkaR3Store);
			if (null != konkaR3Store && null != konkaR3Store.getP_index()) {
				konkaR3Store.getMap().put("p_name", super.getPIndexName(konkaR3Store.getP_index().toString(), ""));
			}
			request.setAttribute("konkaR3Store", konkaR3Store);

			KonkaDept kd = new KonkaDept();
			kd.setDept_type(3);
			kd.setPar_id(0L);
			kd.getMap().put("order_by_dept_name", true);
			List<KonkaDept> konkaDeptList = super.getFacade().getKonkaDeptService().getKonkaDeptList(kd);
			request.setAttribute("konkaDeptList", konkaDeptList);

			return mapping.findForward("input");
		} else {
			saveError(request, "errors.long");
			return mapping.findForward("list");
		}
	}

}