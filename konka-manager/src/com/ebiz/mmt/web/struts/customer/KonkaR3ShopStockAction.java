package com.ebiz.mmt.web.struts.customer;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.JBaseGoods;
import com.ebiz.mmt.domain.JBaseStore;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.org.apache.commons.lang3.StringUtils;

/**
 * @author Jiang,JiaYong
 * @version 2013-08-29
 */
public class KonkaR3ShopStockAction extends BaseAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.view(mapping, form, request, response);
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		// String song_r3_code = (String) dynaBean.get("song_r3_code");// 送达方
		// String is_sdf = (String) dynaBean.get("is_sdf");// 是否送达方
		String store_id = (String) dynaBean.get("store_id");// 仓库ID
		String goods_id = (String) dynaBean.get("goods_id");// 仓库ID
		// 获取登录用户 企业信息
		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.CUSTOMER_USER_INFO);

		if (null == peProdUser.getCust_id()) {
			return null;
		}

		KonkaR3Shop konkaR3Shop = new KonkaR3Shop();
		konkaR3Shop.setId(peProdUser.getCust_id());
		konkaR3Shop = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(konkaR3Shop);

		request.setAttribute("konkaR3Shop", konkaR3Shop);

		KonkaR3Shop krs = new KonkaR3Shop();
		if (StringUtils.isNotBlank(store_id)) {
			krs.getMap().put("store_id", store_id);
		} else {
			krs.getMap().put("r3_code", konkaR3Shop.getR3_code());
		}
		if (StringUtils.isNotBlank(goods_id)) {
			krs.getMap().put("goods_id", goods_id);
		}
		krs.setR3_code(konkaR3Shop.getR3_code());
		List<KonkaR3Shop> entityList = super.getFacade().getKonkaR3ShopService().getKonkaR3ShopStockByR3CodeList(krs);
		for (KonkaR3Shop kr3shop : entityList) {
			JBaseGoods jbg = new JBaseGoods();
			if (StringUtils.isNotBlank(store_id)) {// 送达方
				jbg.getMap().put("store_id", store_id);
			} else {
				jbg.getMap().put("r3_code", konkaR3Shop.getR3_code());
			}
			// 产品初始化信息
			JBaseGoods jGoods = new JBaseGoods();
			jGoods.setC_id(peProdUser.getCust_id());
			jGoods.setGoods_name(kr3shop.getMap().get("pd_name").toString());
			List<JBaseGoods> jGoodsList = getFacade().getJBaseGoodsService().getJBaseGoodsList(jGoods);
			if (jGoodsList.size() == 1) {
				jGoods = jGoodsList.get(0);
			} else {
				logger.info("未知道商品");
			}
			jbg.getMap().put("goods_id", jGoods.getGoods_id());
			jbg.getMap().put("md_name", jGoods.getGoods_name());
			jbg.getMap().put("r3_id", konkaR3Shop.getId());
			jbg = super.getFacade().getJBaseGoodsService().getJBaseGoodsForComeOutNumNew(jbg);
			if (jbg != null) {
				kr3shop.getMap().put("come_num", jbg.getMap().get("come_num"));
				kr3shop.getMap().put("out_num", jbg.getMap().get("out_num"));
				kr3shop.getMap().put("stocks", jbg.getMap().get("init_num"));
			}
		}
		// JBaseStoreR3 storeR3 = new JBaseStoreR3();
		// storeR3.setR3_code(konkaR3Shop.getR3_code());
		// storeR3.setIs_del(0);
		// List<JBaseStoreR3> storer3List =
		// super.getFacade().getJBaseStoreR3Service().getJBaseStoreR3List(storeR3);
		// request.setAttribute("storer3List", storer3List);

		JBaseStore jBaseStore = new JBaseStore();
		jBaseStore.getMap().put("order_by_is_dft_buy_store_desc", true);
		jBaseStore.setC_id(peProdUser.getCust_id());
		jBaseStore.setIs_del(0);
		List<JBaseStore> jBaseStoreList = super.getFacade().getJBaseStoreService().getJBaseStoreList(jBaseStore);
		request.setAttribute("jBaseStoreList", jBaseStoreList);
		JBaseGoods goods = new JBaseGoods();
		goods.setC_id(peProdUser.getCust_id());
		goods.setGoods_stutes(0);
		List<JBaseGoods> goodsList = super.getFacade().getJBaseGoodsService().getJBaseGoodsList(goods);
		if (null != goodsList && goodsList.size() > 0) {
			request.setAttribute("jBaseGoodsList", goodsList);
		}
		request.setAttribute("entityList", entityList);
		return mapping.findForward("view");
	}

	public ActionForward view_new(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String store_id = (String) dynaBean.get("store_id");// 仓库ID
		String goods_id = (String) dynaBean.get("goods_id");// 仓库ID
		String group_by_store = (String) dynaBean.get("group_by_store");
		// 获取登录用户 企业信息
		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.CUSTOMER_USER_INFO);

		if (null == peProdUser.getCust_id()) {
			return null;
		}

		KonkaR3Shop konkaR3Shop = new KonkaR3Shop();
		konkaR3Shop.setId(peProdUser.getCust_id());
		konkaR3Shop = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(konkaR3Shop);

		request.setAttribute("konkaR3Shop", konkaR3Shop);
		request.setAttribute("group_by_store", group_by_store);

		JBaseGoods jBaseGoods = new JBaseGoods();
		if (StringUtils.isNotBlank(store_id)) {
			jBaseGoods.getMap().put("store_id", store_id);
		} else {
			jBaseGoods.getMap().put("r3_code", konkaR3Shop.getR3_code());
		}
		if (StringUtils.isNotBlank(goods_id)) {
			jBaseGoods.getMap().put("goods_id", goods_id);
		}
		jBaseGoods.getMap().put("r3_id", peProdUser.getCust_id());
		List<JBaseGoods> entityList = null;
		// 先按仓库分组  再按商品分组
		if(StringUtils.isNotBlank(group_by_store)){
			entityList = super.getFacade().getJBaseGoodsService()
					.getJBaseGoodsForComeOutNumWithMoney2(jBaseGoods);
		}
		// 直接按商品排序 汇总
		else{
			entityList = super.getFacade().getJBaseGoodsService()
					.getJBaseGoodsForComeOutNumWithMoney(jBaseGoods);
		}

		JBaseStore jBaseStore = new JBaseStore();
		jBaseStore.getMap().put("order_by_is_dft_buy_store_desc", true);
		jBaseStore.setC_id(peProdUser.getCust_id());
		jBaseStore.setIs_del(0);
		List<JBaseStore> jBaseStoreList = super.getFacade().getJBaseStoreService().getJBaseStoreList(jBaseStore);
		request.setAttribute("jBaseStoreList", jBaseStoreList);
		JBaseGoods goods = new JBaseGoods();
		goods.setC_id(peProdUser.getCust_id());
		goods.setGoods_stutes(0);
		List<JBaseGoods> goodsList = super.getFacade().getJBaseGoodsService().getJBaseGoodsList(goods);
		if (null != goodsList && goodsList.size() > 0) {
			request.setAttribute("jBaseGoodsList", goodsList);
		}
		request.setAttribute("entityList", entityList);
		return new ActionForward("/../customer/KonkaR3ShopStock/view_new.jsp");
		// return mapping.findForward("view");
	}

	/**
	 * 库存查询明细 2014-08-19
	 */
	public ActionForward viewDetail(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String store_id = (String) dynaBean.get("store_id");// 仓库ID
		String goods_id = (String) dynaBean.get("goods_id");// 仓库ID
		String direction = (String) dynaBean.get("direction");// 0：进 1：销
		//System.out.println("store_id=" + store_id + "  goods_id=" + goods_id + "   direction=" + direction);
		// 获取登录用户 企业信息
		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.CUSTOMER_USER_INFO);

		if (null == peProdUser.getCust_id()) {
			return null;
		}

		KonkaR3Shop konkaR3Shop = new KonkaR3Shop();
		konkaR3Shop.setId(peProdUser.getCust_id());
		konkaR3Shop = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(konkaR3Shop);

		request.setAttribute("konkaR3Shop", konkaR3Shop);

		JBaseGoods jBaseGoods = new JBaseGoods();
		if (StringUtils.isNotBlank(store_id)) {
			jBaseGoods.getMap().put("store_id", store_id);
		} else {
			jBaseGoods.getMap().put("r3_code", konkaR3Shop.getR3_code());
		}
		if (StringUtils.isNotBlank(goods_id)) {
			jBaseGoods.getMap().put("goods_id", goods_id);
		} else {
			super.renderJavaScript(response, "alert('数据丢失！');history.back();");
			return null;
		}
		if (StringUtils.isNotBlank(direction)) {
			jBaseGoods.getMap().put("direction", direction);
		} else {
			jBaseGoods.getMap().put("direction", 0);
		}
		jBaseGoods.getMap().put("r3_id", peProdUser.getCust_id());
		List<JBaseGoods> entityList = super.getFacade().getJBaseGoodsService()
				.getJBaseGoodsForComeOutNumWithMoneyDetail(jBaseGoods);

		//if (null != entityList && entityList.size() > 0) {
		//	if (entityList.get(0).getMap().get("direction") != null) {
		//		request.setAttribute("direction", entityList.get(0).getMap().get("direction"));
		//	}
		//}
		request.setAttribute("direction", direction);
		request.setAttribute("entityList", entityList);
		return new ActionForward("/../customer/KonkaR3ShopStock/view_detail.jsp");
	}
}