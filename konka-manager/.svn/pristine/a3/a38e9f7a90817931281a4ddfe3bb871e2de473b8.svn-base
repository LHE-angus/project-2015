package com.ebiz.mmt.web.struts.customer;

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

import com.ebiz.mmt.domain.JBaseGoods;
import com.ebiz.mmt.domain.JBaseStore;
import com.ebiz.mmt.domain.JBaseType;
import com.ebiz.mmt.domain.PePdModel;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Jiang,JiaYong
 * @version 2013-06-14
 */
public class JBaseGoodsAction extends BaseClientJxcAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String goods_type = (String) dynaBean.get("goods_type"); // 商品类型
		String goods_stutes = (String) dynaBean.get("goods_stutes"); // 商品状态
		String goods_sn_like = (String) dynaBean.get("goods_sn_like"); // 商品条码
		String goods_name_like = (String) dynaBean.get("goods_name_like"); // 商品名称
		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.CUSTOMER_USER_INFO);
		if (null == user) {
			return null;
		}

		JBaseGoods entity = new JBaseGoods();
		entity.setC_id(user.getCust_id());
		if (GenericValidator.isLong(goods_type)) {
			entity.setGoods_type(Long.valueOf(goods_type));
		}
		// if (GenericValidator.isLong(goods_stutes)) {
		// entity.setGoods_stutes(Integer.valueOf(goods_stutes));
		// }
		if (StringUtils.isNotBlank(goods_stutes)) {
			entity.setGoods_stutes(Integer.valueOf(goods_stutes));
		} else {
			entity.setGoods_stutes(0);
		}

		logger.info("goods_stutes {}", goods_stutes);

		entity.getMap().put("goods_sn_like", goods_sn_like);
		entity.getMap().put("goods_name_like", goods_name_like);

		Long recordCount = super.getFacade().getJBaseGoodsService().getJBaseGoodsCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<JBaseGoods> entityList = super.getFacade().getJBaseGoodsService().getJBaseGoodsPaginatedList(entity);
		request.setAttribute("entityList", entityList);

		// 查询商品类型 10001
		JBaseType t = new JBaseType();
		t.setC_id(user.getCust_id());
		t.setIs_del(0);
		t.setPar_id(10001L);
		List<JBaseType> list10001 = super.getFacade().getJBaseTypeService().getJBaseTypeList(t);
		request.setAttribute("list10001", list10001);

		// 查询商品单位 10002
		JBaseType tt = new JBaseType();
		tt.setC_id(user.getCust_id());
		tt.setIs_del(0);
		tt.setPar_id(10002L);
		List<JBaseType> list10002 = super.getFacade().getJBaseTypeService().getJBaseTypeList(tt);
		request.setAttribute("list10002", list10002);

		return mapping.findForward("list");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String partner_goods_id = (String) dynaBean.get("partner_goods_id");
		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.CUSTOMER_USER_INFO);
		if (null == user) {
			return null;
		}

		// 查询商品类型 10001
		JBaseType t = new JBaseType();
		t.setIs_del(0);
		t.setC_id(user.getCust_id());
		t.setPar_id(10001L);
		List<JBaseType> list10001 = super.getFacade().getJBaseTypeService().getJBaseTypeList(t);
		request.setAttribute("list10001", list10001);

		// 查询商品单位 10002
		JBaseType tt = new JBaseType();
		tt.setIs_del(0);
		tt.setC_id(user.getCust_id());
		tt.setPar_id(10002L);
		List<JBaseType> list10002 = super.getFacade().getJBaseTypeService().getJBaseTypeList(tt);
		request.setAttribute("list10002", list10002);

		PePdModel pdModel = new PePdModel();
		pdModel.setIs_del(0);
		pdModel.setIs_sell(1);
		pdModel.getMap().put("OrderByMd", true);
		List<PePdModel> konkaPdModelList = super.getFacade().getPePdModelService().getPePdModelList(pdModel);
		request.setAttribute("konkaPdModelList", konkaPdModelList);

		if (GenericValidator.isLong(partner_goods_id)) {
			JBaseGoods entity = new JBaseGoods();
			entity.setGoods_id(Long.valueOf(partner_goods_id));

			entity = super.getFacade().getJBaseGoodsService().getJBaseGoods(entity);
			entity.setInit_count(0L);
			entity.setBuy_price(entity.getSell_price());
			entity.setSell_price(null);
			entity.setGoods_id(null);
			entity.setC_id(null);

			super.copyProperties(form, entity);

		}
		request.setAttribute("jBaseStoreList", super.getJBaseStores(request, 0));
		return mapping.findForward("input");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.CUSTOMER_USER_INFO);
		if (null == user) {
			return null;
		}

		DynaBean dynaBean = (DynaBean) form;
		String goods_id = (String) dynaBean.get("goods_id");

		if (!GenericValidator.isLong(goods_id)) {
			super.saveError(request, "errors.param");
			return mapping.findForward("list");
		}

		JBaseGoods entity = new JBaseGoods();
		entity.setGoods_id(Long.valueOf(goods_id));
		entity = super.getFacade().getJBaseGoodsService().getJBaseGoods(entity);
		entity.setQueryString(super.serialize(request, "bill_id", "method"));
		super.copyProperties(form, entity);

		// 查询商品类型 10001
		JBaseType t = new JBaseType();
		t.setC_id(user.getCust_id());
		t.setIs_del(0);
		t.setPar_id(10001L);
		List<JBaseType> list10001 = super.getFacade().getJBaseTypeService().getJBaseTypeList(t);
		request.setAttribute("list10001", list10001);

		// 查询商品单位 10002
		JBaseType tt = new JBaseType();
		tt.setC_id(user.getCust_id());
		tt.setIs_del(0);
		tt.setPar_id(10002L);
		List<JBaseType> list10002 = super.getFacade().getJBaseTypeService().getJBaseTypeList(tt);
		request.setAttribute("list10002", list10002);

		PePdModel pdModel = new PePdModel();
		pdModel.setIs_del(0);
		pdModel.setIs_sell(1);
		pdModel.getMap().put("OrderByMd", true);
		List<PePdModel> konkaPdModelList = super.getFacade().getPePdModelService().getPePdModelList(pdModel);
		request.setAttribute("konkaPdModelList", konkaPdModelList);
		request.setAttribute("jBaseStoreList", super.getJBaseStores(request, 0));
		return mapping.findForward("input");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String goods_id = (String) dynaBean.get("goods_id");

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.CUSTOMER_USER_INFO);
		if (null == user) {
			return null;
		}

		if (!GenericValidator.isLong(goods_id)) {
			super.saveError(request, "errors.param");
			return mapping.findForward("list");
		}

		JBaseGoods entity = new JBaseGoods();
		entity.setGoods_id(Long.valueOf(goods_id));
		entity = super.getFacade().getJBaseGoodsService().getJBaseGoods(entity);
		entity.setQueryString(super.serialize(request, "bill_id", "method"));
		super.copyProperties(form, entity);

		// 查询商品类型 10001
		JBaseType t = new JBaseType();
		t.setC_id(user.getCust_id());
		t.setIs_del(0);
		t.setPar_id(10001L);
		List<JBaseType> list10001 = super.getFacade().getJBaseTypeService().getJBaseTypeList(t);
		request.setAttribute("list10001", list10001);

		// 查询商品单位 10002
		JBaseType tt = new JBaseType();
		tt.setC_id(user.getCust_id());
		tt.setIs_del(0);
		tt.setPar_id(10002L);
		List<JBaseType> list10002 = super.getFacade().getJBaseTypeService().getJBaseTypeList(tt);
		request.setAttribute("list10002", list10002);

		return mapping.findForward("view");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		resetToken(request);
		DynaBean dynaBean = (DynaBean) form;
		String goods_id = (String) dynaBean.get("goods_id");
		String mod_id = (String) dynaBean.get("mod_id");
		String returnUrl = (String) dynaBean.get("returnUrl");
		String store_id = (String) dynaBean.get("store_id");

		PeProdUser user = (PeProdUser) getSessionAttribute(request, Constants.CUSTOMER_USER_INFO);
		JBaseGoods entity = new JBaseGoods();
		super.copyProperties(entity, form);

		JBaseGoods g = new JBaseGoods();
		g.setGoods_name(entity.getGoods_name());
		g.setC_id(user.getCust_id());

		if (GenericValidator.isLong(goods_id)) {
			g.getMap().put("goods_id_is_not_eq", goods_id);
			Long count = super.getFacade().getJBaseGoodsService().getJBaseGoodsCount(g);
			if (count > 0) {
				super.saveError(request, "konka.jgoods.error");
			} else {
				super.getFacade().getJBaseGoodsService().modifyJBaseGoods(entity);
				saveMessage(request, "entity.updated");
			}
		} else {
			Long count = super.getFacade().getJBaseGoodsService().getJBaseGoodsCount(g);
			if (count > 0) {
				super.saveError(request, "konka.jgoods.error");
			} else {

				entity.setC_id(user.getCust_id());
				if (StringUtils.isNotBlank(store_id)) {
					entity.getMap().put("store_id", store_id);
				} else {
					JBaseStore js = new JBaseStore();
					js.setC_id(user.getCust_id());
					js.setIs_dft_buy_store(1);
					js = super.getFacade().getJBaseStoreService().getJBaseStore(js);
					if(null!=js && null!= js.getStore_id()){
						entity.getMap().put("store_id", js.getStore_id());
					}
				}

				super.getFacade().getJBaseGoodsService().createJBaseGoods(entity);
				saveMessage(request, "entity.inserted");
			}
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
		String goods_id = (String) dynaBean.get("goods_id");
		String mod_id = (String) dynaBean.get("mod_id");

		if (!GenericValidator.isLong(goods_id)) {
			super.saveError(request, "errors.param");
			return mapping.findForward("list");
		}

		JBaseGoods entity = new JBaseGoods();
		entity.setGoods_id(Long.valueOf(goods_id));
		super.getFacade().getJBaseGoodsService().removeJBaseGoods(entity);

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
		String goods_id = (String) dynaBean.get("goods_id");
		String mod_id = (String) dynaBean.get("mod_id");
		String goods_stutes = (String) dynaBean.get("goods_stutes");

		if (!GenericValidator.isLong(goods_id)) {
			super.saveError(request, "errors.param");
			return mapping.findForward("list");
		}

		JBaseGoods entity = new JBaseGoods();
		entity.setGoods_id(Long.valueOf(goods_id));
		entity.setGoods_stutes(0);
		super.getFacade().getJBaseGoodsService().modifyJBaseGoods(entity);

		saveMessage(request, "konka.restart.success");

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&goods_stutes=" + goods_stutes);
		pathBuffer.append("&");
		// pathBuffer.append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

}
