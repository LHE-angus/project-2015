package com.ebiz.mmt.web.struts.customer;

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

import com.ebiz.mmt.domain.JBaseStore;
import com.ebiz.mmt.domain.JBaseStoreR3;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Jiang,JiaYong
 * @version 2013-06-17
 */
public class JBaseStoreAction extends BaseClientJxcAction {

	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String store_sn_like = (String) dynaBean.get("store_sn_like"); // 仓库编码
		String store_name_like = (String) dynaBean.get("store_name_like"); // 仓库名称
		String sale_r3_code_like = (String) dynaBean.get("sale_r3_code_like"); // 送达方编码
		String is_del = (String) dynaBean.get("is_del");

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.CUSTOMER_USER_INFO);
		if (null == user) {
			return null;
		}

		is_del = null == is_del ? "0" : is_del;

		dynaBean.set("is_del", is_del);

		logger.info("is_del: {}", is_del);

		
		
		KonkaR3Shop shop =new KonkaR3Shop();
		shop.setId(user.getCust_id());
		shop=super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(shop);
		if(null!=shop && null!=shop.getR3_code()){
			request.setAttribute("cur_r3_code",shop.getR3_code());
		}
		
		JBaseStore entity = new JBaseStore();
		entity.setC_id(user.getCust_id());

		
		
		
		if (StringUtils.isNotBlank(is_del)) {
			entity.setIs_del(Integer.valueOf(is_del));
		}
		if (StringUtils.isNotBlank(sale_r3_code_like)) {
			entity.getMap().put("sale_r3_code_like", sale_r3_code_like);
		}

		entity.getMap().put("store_sn_like", store_sn_like);
		entity.getMap().put("store_name_like", store_name_like);

		Long recordCount = super.getFacade().getJBaseStoreService().getJBaseStoreCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<JBaseStore> entityList = super.getFacade().getJBaseStoreService().getJBaseStoreForR3PaginatedList(entity);
		request.setAttribute("entityList", entityList);
		return mapping.findForward("list");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		super.saveToken(request);

		// 拿到客户编码（售达方编码）
		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.CUSTOMER_USER_INFO);
		if (null == user || null == user.getCust_id() || "".equals(String.valueOf(user.getCust_id()))) {
			return null;
		}
		KonkaR3Shop konkaR3Shop = new KonkaR3Shop();
		konkaR3Shop.setId(user.getCust_id());
		konkaR3Shop = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(konkaR3Shop);
		if (null != konkaR3Shop) {
			// 拿到客户编码（售达方编码）
			String r3_code = konkaR3Shop.getR3_code();
			request.setAttribute("r3_code", r3_code);
			JBaseStoreR3 jBaseStoreR3 = new JBaseStoreR3();
			jBaseStoreR3.setR3_code(r3_code);
			List<JBaseStoreR3> jBaseStoreR3List = super.getFacade().getJBaseStoreService()
					.getStoreSnWeForList(jBaseStoreR3);
			//
			request.setAttribute("jBaseStoreR3List", jBaseStoreR3List);
		}
		return mapping.findForward("input");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		super.saveToken(request);
		DynaBean dynaBean = (DynaBean) form;
		String store_id = (String) dynaBean.get("store_id");

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.CUSTOMER_USER_INFO);
		if (null == user || null == user.getCust_id() || "".equals(String.valueOf(user.getCust_id()))) {
			return null;
		}
		KonkaR3Shop konkaR3Shop = new KonkaR3Shop();
		konkaR3Shop.setId(user.getCust_id());
		konkaR3Shop = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(konkaR3Shop);

		if (!GenericValidator.isLong(store_id)) {
			super.saveError(request, "errors.param");
			return mapping.findForward("list");
		}

		JBaseStore entity = new JBaseStore();
		entity.setStore_id(Long.valueOf(store_id));
		// entity =
		// super.getFacade().getJBaseStoreService().getJBaseStore(entity);

		entity = super.getFacade().getJBaseStoreService().getJBaseStoreAndDetails(entity);
		entity.setQueryString(super.serialize(request, "store_id", "method"));

		JBaseStoreR3 jBaseStoreR3 = new JBaseStoreR3();
		jBaseStoreR3.setR3_code(konkaR3Shop.getR3_code());
		List<JBaseStoreR3> jBaseStoreR3List = super.getFacade().getJBaseStoreService()
				.getStoreSnWeForList(jBaseStoreR3);
		request.setAttribute("jBaseStoreR3List", jBaseStoreR3List);
		request.setAttribute("store_r3_id", entity.getMap().get("id"));
		if (null != entity.getMap().get("r3_code") && "".equals(entity.getMap().get("r3_code").toString())) {
			request.setAttribute("r3_code", entity.getMap().get("r3_code"));
		} else {
			request.setAttribute("r3_code", konkaR3Shop.getR3_code());
		}
		request.setAttribute("sale_r3_code", entity.getMap().get("sale_r3_code"));

		super.copyProperties(form, entity);
		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (!super.isTokenValid(request, true)) {
			super.saveMessage(request, "errors.token");
			return mapping.findForward("list");
		}
		resetToken(request);
		DynaBean dynaBean = (DynaBean) form;
		String store_id = (String) dynaBean.get("store_id");
		String mod_id = (String) dynaBean.get("mod_id");
		String returnUrl = (String) dynaBean.get("returnUrl");
		String store_r3_id = (String) dynaBean.get("store_r3_id");
		String r3_code = (String) dynaBean.get("r3_code");
		String sale_r3_code = (String) dynaBean.get("sale_r3_code");

		PeProdUser user = (PeProdUser) getSessionAttribute(request, Constants.CUSTOMER_USER_INFO);

		JBaseStore entity = new JBaseStore();
		super.copyProperties(entity, form);
		if (GenericValidator.isLong(store_id)) {// 修改

			if (StringUtils.isNotBlank(store_r3_id)) {
				JBaseStoreR3 jStoreR3 = new JBaseStoreR3();
				jStoreR3.setId(Long.valueOf(store_r3_id));
				jStoreR3.setIs_del(0);
				jStoreR3 = super.getFacade().getJBaseStoreR3Service().getJBaseStoreR3(jStoreR3);
				if (null != jStoreR3 && null != jStoreR3.getSale_r3_code()) {
					if (!jStoreR3.getSale_r3_code().equals(sale_r3_code)) {// 如果修改之前的送达方编码和修改之后的送达方编码不相同
						// 判断送达方编码不为空的情况下是否有重复的记录
						if (StringUtils.isNotBlank(r3_code) && StringUtils.isNotBlank(sale_r3_code)) {
							JBaseStoreR3 storer3 = new JBaseStoreR3();
							storer3.setSale_r3_code(sale_r3_code);
							storer3.setR3_code(r3_code);
							List<JBaseStoreR3> r3List = super.getFacade().getJBaseStoreR3Service()
									.getJBaseStoreR3List(storer3);
							if (null != r3List && r3List.size() > 0) {
								super.renderJavaScript(response, "alert('送达方编码对应的仓库已经存在，请重新选择！');history.back();");
								return null;
							}
						}
					}
				}

				JBaseStoreR3 jBaseStoreR3 = new JBaseStoreR3();
				jBaseStoreR3.setId(Long.valueOf(store_r3_id));
				jBaseStoreR3.setR3_code(r3_code);
				jBaseStoreR3.setSale_r3_code(sale_r3_code);
				jBaseStoreR3.setMemo(null == entity.getRemarks() ? "" : entity.getRemarks());
				super.getFacade().getJBaseStoreR3Service().modifyJBaseStoreR3(jBaseStoreR3);
			} else {
				// 判断送达方编码不为空的情况下是否有重复的记录
				if (StringUtils.isNotBlank(r3_code) && StringUtils.isNotBlank(sale_r3_code)) {
					JBaseStoreR3 storer3 = new JBaseStoreR3();
					storer3.setSale_r3_code(sale_r3_code);
					storer3.setR3_code(r3_code);
					List<JBaseStoreR3> r3List = super.getFacade().getJBaseStoreR3Service().getJBaseStoreR3List(storer3);
					if (null != r3List && r3List.size() > 0) {
						super.renderJavaScript(response, "alert('送达方编码对应的仓库已经存在，请重新选择！');history.back();");
						return null;
					}
				}

				JBaseStoreR3 jBaseStoreR3 = new JBaseStoreR3();
				jBaseStoreR3.setStore_id(entity.getStore_id());
				jBaseStoreR3.setR3_code(r3_code);
				jBaseStoreR3.setSale_r3_code(sale_r3_code);
				jBaseStoreR3.setSale_r3_name(sale_r3_code);
				jBaseStoreR3.setAdd_date(new Date());
				jBaseStoreR3.setIs_del(0);
				jBaseStoreR3.setMemo(null == entity.getRemarks() ? "" : entity.getRemarks());
				super.getFacade().getJBaseStoreR3Service().createJBaseStoreR3(jBaseStoreR3);
			}
			if (StringUtils.isEmpty(entity.getStore_sn())) {
				entity.setStore_sn(this.generateStore_sn(user.getCust_id()));
			}
			entity.setC_id(user.getCust_id());
			super.getFacade().getJBaseStoreService().modifyJBaseStore(entity);
			saveMessage(request, "entity.updated");
		} else {// 新增
			// 判断送达方编码不为空的情况下是否有重复的记录
			if (StringUtils.isNotBlank(r3_code) && StringUtils.isNotBlank(sale_r3_code)) {
				JBaseStoreR3 storer3 = new JBaseStoreR3();
				storer3.setSale_r3_code(sale_r3_code);
				storer3.setR3_code(r3_code);
				List<JBaseStoreR3> r3List = super.getFacade().getJBaseStoreR3Service().getJBaseStoreR3List(storer3);
				if (null != r3List && r3List.size() > 0) {
					super.renderJavaScript(response, "alert('送达方编码对应的仓库已经存在，请重新选择！');history.back();");
					return null;
				}
			}

			entity.setIs_del(0);
			entity.setStore_sn(this.generateStore_sn(user.getCust_id()));
			if (StringUtils.isBlank(entity.getStore_name())) {// 如何仓库名称为空的话，默认自动带入编号
				entity.setStore_name(this.generateStore_sn(user.getCust_id()));
			}
			entity.setC_id(user.getCust_id());
			JBaseStoreR3 jBaseStoreR3 = new JBaseStoreR3();
			jBaseStoreR3.setR3_code(r3_code);
			jBaseStoreR3.setSale_r3_code(sale_r3_code);
			jBaseStoreR3.setSale_r3_name(sale_r3_code);
			jBaseStoreR3.setAdd_date(new Date());
			jBaseStoreR3.setIs_del(0);
			jBaseStoreR3.setMemo(null == entity.getRemarks() ? "" : entity.getRemarks());
			entity.setjBaseStoreR3(jBaseStoreR3);
			super.getFacade().getJBaseStoreService().createJBaseStore(entity);
			saveMessage(request, "entity.inserted");
		}

		// 画面迁移从哪里来哪里去
		if (StringUtils.isNotBlank(returnUrl)) {
			super.toReturnUrl(returnUrl, response);
			return null;
		}
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		// pathBuffer.append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String store_id = (String) dynaBean.get("store_id");
		String mod_id = (String) dynaBean.get("mod_id");

		if (!GenericValidator.isLong(store_id)) {
			super.saveError(request, "errors.param");
			return mapping.findForward("list");
		}

		JBaseStore entity = new JBaseStore();
		entity.setStore_id(Long.valueOf(store_id));
		super.getFacade().getJBaseStoreService().removeJBaseStore(entity);

		JBaseStoreR3 storeR3 = new JBaseStoreR3();
		storeR3.setStore_id(Long.valueOf(store_id));
		List<JBaseStoreR3> storer3List = super.getFacade().getJBaseStoreR3Service().getJBaseStoreR3List(storeR3);
		if (storer3List != null && storer3List.size() == 1) {
			JBaseStoreR3 store = new JBaseStoreR3();
			store.setId(storer3List.get(0).getId());
			super.getFacade().getJBaseStoreR3Service().removeJBaseStoreR3(store);
		} else {
			super.renderJavaScript(response, "alert('出现未知错误，请联系管理员');history.back();");
			return null;
		}

		saveMessage(request, "konka.close.success");

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		// pathBuffer.append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward reStart(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String store_id = (String) dynaBean.get("store_id");
		String mod_id = (String) dynaBean.get("mod_id");
		String is_del = (String) dynaBean.get("is_del");

		if (!GenericValidator.isLong(store_id)) {
			super.saveError(request, "errors.param");
			return mapping.findForward("list");
		}

		JBaseStore entity = new JBaseStore();
		entity.setStore_id(Long.valueOf(store_id));
		entity = super.getFacade().getJBaseStoreService().getJBaseStore(entity);

		entity.setIs_del(0);

		super.getFacade().getJBaseStoreService().modifyJBaseStore(entity);

		JBaseStoreR3 storeR3 = new JBaseStoreR3();
		storeR3.setStore_id(Long.valueOf(store_id));
		List<JBaseStoreR3> storer3List = super.getFacade().getJBaseStoreR3Service().getJBaseStoreR3List(storeR3);
		if (storer3List != null && storer3List.size() == 1) {
			JBaseStoreR3 store = new JBaseStoreR3();
			store.setId(storer3List.get(0).getId());
			store.setIs_del(0);
			super.getFacade().getJBaseStoreR3Service().modifyJBaseStoreR3(store);
		} else {
			super.renderJavaScript(response, "alert('出现未知错误，请联系管理员');history.back();");
			return null;
		}

		saveMessage(request, "konka.restart.success");

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&is_del=" + is_del);
		pathBuffer.append("&");
		// pathBuffer.append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	protected String generateStore_sn(Long cust_id) {
		JBaseStore store = new JBaseStore();
		store.setC_id(cust_id);
		Long count = super.getFacade().getJBaseStoreService().getJBaseStoreCount(store);
		return "CK-" + cust_id + "-" + (count + 1);
	}
}
