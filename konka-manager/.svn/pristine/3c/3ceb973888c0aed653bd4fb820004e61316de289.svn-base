package com.ebiz.mmt.web.struts.manager.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.struts.BaseAction;

public class SelectKonkaR3ShopAction extends BaseAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);
		String keyword = (String) dynaBean.get("keyword");
		String selects = (String) dynaBean.get("selects");
		String category_r3 = (String) dynaBean.get("category_r3");
		String dls_r3_id = (String) dynaBean.get("dls_r3_id");
		dynaBean.set("dls_r3_id", dls_r3_id);

		KonkaR3Shop entity = new KonkaR3Shop();

		if (StringUtils.isNotBlank(category_r3)) {
			entity.getMap().put("category_r3", 1);
			dynaBean.set("category_r3", category_r3);
		}

		if (StringUtils.isNotBlank(selects)) {
			entity.getMap().put("selects", selects);
			request.setAttribute("selectList", super.getFacade().getKonkaR3ShopService().getKonkaR3ShopList(entity));
			entity.getMap().put("selects", null);
			if (StringUtils.isNotBlank(dls_r3_id)) {
				selects = selects + "," + dls_r3_id;
			}
			entity.getMap().put("r3_not_selects", selects);
		} else if (StringUtils.isBlank(selects) && StringUtils.isNotBlank(dls_r3_id)) {
			entity.getMap().put("r3_not_selects", dls_r3_id);
		}
		if (keyword != null) {
			entity.getMap().put("keyword", keyword);
		}

		PeProdUser peProdUser = super.getSessionUserInfo(request);

		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(peProdUser.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);

		boolean role_id_ge_30 = false;
		for (PeRoleUser peRoleUser : peRoleUserList) {
			if (peRoleUser.getRole_id() >= 30L) {
				role_id_ge_30 = true;
			}
		}

		if (role_id_ge_30) {// 若登录用户属于分公司及分公司以下的
			KonkaDept dept = super.getSuperiorForDeptType(peProdUser.getDept_id(), 3);
			dept.setDept_id(peProdUser.getDept_id());
			dept = getFacade().getKonkaDeptService().getKonkaDept(dept);
			if (StringUtils.isNotBlank(dept.getM_areas_names())) {
				entity.getMap().put("branch_name_like", dept.getM_areas_names());
			} else {
				entity.getMap().put("branch_name_like", "1");
			}
		}

		entity.getRow().setCount(500);
		List<KonkaR3Shop> entityList = getFacade().getKonkaR3ShopService().getKonkaR3ShopList(entity);
		request.setAttribute("entityList", entityList);
		return mapping.findForward("list");
	}

	public ActionForward getQueryNames(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String keyword = (String) dynaBean.get("keyword");
		String category_r3 = (String) dynaBean.get("category_r3");
		String dls_r3_id = (String) dynaBean.get("dls_r3_id");
		String pageCount = (String) dynaBean.get("pageCount");
		StringBuffer sb = new StringBuffer("[");
		int count = 500; // 每次最多取出数量

		if (StringUtils.isNotBlank(pageCount)) {
			count = Integer.valueOf(pageCount);
		}
		KonkaR3Shop entity = new KonkaR3Shop();
		if (StringUtils.isNotBlank(category_r3)) {
			entity.getMap().put("category_r3", 1);
			dynaBean.set("category_r3", category_r3);
		}
		if (StringUtils.isNotBlank(dls_r3_id)) {
			entity.getMap().put("r3_not_selects", dls_r3_id);
		}

		PeProdUser peProdUser = super.getSessionUserInfo(request);
		
		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(peProdUser.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);

		boolean role_id_ge_30 = false;
		for (PeRoleUser peRoleUser : peRoleUserList) {
			if (peRoleUser.getRole_id() >= 30L) {
				role_id_ge_30 = true;
			}
		}

		if (role_id_ge_30) {// 若登录用户属于分公司及分公司以下的
			KonkaDept dept = super.getSuperiorForDeptType(peProdUser.getDept_id(), 3);
			dept.setDept_id(peProdUser.getDept_id());
			dept = getFacade().getKonkaDeptService().getKonkaDept(dept);
			if (StringUtils.isNotBlank(dept.getM_areas_names())) {
				entity.getMap().put("branch_name_like", dept.getM_areas_names());
			} else {
				entity.getMap().put("branch_name_like", "1");
			}
		}

		entity.getMap().put("keyword", keyword);
		entity.getRow().setCount(count);
		List<KonkaR3Shop> entityList = getFacade().getKonkaR3ShopService().getKonkaR3ShopList(entity);
		for (KonkaR3Shop t : entityList) {
			sb.append("{\"id\":\"" + String.valueOf(t.getId()) + "\",");
			sb.append("\"name\":\"" + StringEscapeUtils.escapeJavaScript(t.getCustomer_name()) + "\"},");
		}
		sb.append("{\"end\":\"\"}]");
		super.renderJson(response, sb.toString());

		return null;
	}

	// public ActionForward ajaxMerchant(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	// HttpServletResponse response) throws Exception {
	// String id = request.getParameter("id");
	// List<KonkaEntpShop> sList = new ArrayList<KonkaEntpShop>();
	// KonkaEntpShop model = new KonkaEntpShop();
	// if (StringUtils.isNotEmpty(id))
	// model.setShop_id(new Long(id));
	// sList = super.getFacade().getMmtEntpShopService().getKonkaEntpShopList(model);
	// String jsStr = JSON.toJSONString(sList);
	// jsStr = jsStr.substring(0, jsStr.length() - 1);
	// jsStr = jsStr.substring(1, jsStr.length());
	// super.renderJson(response, jsStr);
	// return null;
	// }
}
