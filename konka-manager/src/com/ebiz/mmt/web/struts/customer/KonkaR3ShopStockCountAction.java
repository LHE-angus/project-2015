package com.ebiz.mmt.web.struts.customer;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.JBaseGoods;
import com.ebiz.mmt.domain.JBaseStore;
import com.ebiz.mmt.domain.JStocks;
import com.ebiz.mmt.domain.JStocksStack;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.ssi.web.struts.bean.Pager;

public class KonkaR3ShopStockCountAction extends BaseClientJxcAction {

	public static DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	public static DateFormat dfm = new SimpleDateFormat("yyyyMMdd");

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);
		Pager pager = (Pager) dynaBean.get("pager");
		String store_id = (String) dynaBean.get("store_id");

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.CUSTOMER_USER_INFO);
		if (null == user) {
			return null;
		} else if (null == user.getCust_id()) {
			return null;
		}

		JBaseGoods entity = new JBaseGoods();
		super.copyProperties(entity, form);
		entity.setC_id(user.getCust_id());
		entity.getMap().put("goodsIdsIn", user.getCust_id());
		entity.getMap().put("storeId", store_id);
		Long recordCount = super.getFacade().getJBaseGoodsService().getJBaseGoodsWithStockCount(entity);
		pager.init(recordCount, 10, pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<JBaseGoods> entityList = getFacade().getJBaseGoodsService().getJBaseGoodsWithStockPaginatedList(entity);
		if (null != entityList && entityList.size() > 0) {
			for (JBaseGoods jbg : entityList) {
				Integer totle_count = 0;
				BigDecimal totle_cost = new BigDecimal(0);

				JStocks jStocks = new JStocks();
				jStocks.setGoods_id(jbg.getGoods_id());
				if (StringUtils.isNotBlank(store_id)) {
					jStocks.setStore_id(Long.valueOf(store_id));
				}
				List<JStocks> jStocksList = getFacade().getJStocksService().getJStocksList(jStocks);
				if (null != jStocksList && jStocksList.size() > 0) {
					for (JStocks js : jStocksList) {
						totle_count += js.getStocks().intValue();

						JBaseStore jbs = new JBaseStore();
						jbs.setStore_id(js.getStore_id());
						jbs = getFacade().getJBaseStoreService().getJBaseStore(jbs);
						js.getMap().put("store_name", jbs.getStore_name());

						JStocksStack jStocksStack = new JStocksStack();
						jStocksStack.setGoods_id(js.getGoods_id());
						jStocksStack.setStore_id(js.getStore_id());
						jStocksStack.setStatus(0);
						List<JStocksStack> jssList = getFacade().getJStocksStackService()
						        .getJStocksStackForSskcResultList(jStocksStack);
						if (null != jssList && jssList.size() > 0) {
							BigDecimal ck_cost = new BigDecimal(0);
							for (JStocksStack jss : jssList) {
								BigDecimal goods_cost = jss.getGoods_cost();
								BigDecimal count = new BigDecimal(jss.getMap().get("num").toString());

								ck_cost = ck_cost.add(goods_cost.multiply(count));
								totle_cost = totle_cost.add(goods_cost.multiply(count));
							}
							js.getMap().put("ck_cost", ck_cost);
						}
						js.getMap().put("jssList", jssList);
					}
				}
				jbg.getMap().put("jStocksList", jStocksList);
				jbg.getMap().put("totle_count", totle_count);
				jbg.getMap().put("totle_cost", totle_cost);
			}
		}
		request.setAttribute("entityList", entityList);

		JBaseStore jBaseStore = new JBaseStore();
		jBaseStore.setC_id(user.getCust_id());
		List<JBaseStore> jBaseStoreList = getFacade().getJBaseStoreService().getJBaseStoreList(jBaseStore);
		request.setAttribute("jBaseStoreList", jBaseStoreList);

		JBaseGoods jBaseGoods = new JBaseGoods();
		jBaseGoods.setC_id(user.getCust_id());
		List<JBaseGoods> jBaseGoodsList = getFacade().getJBaseGoodsService().getJBaseGoodsList(jBaseGoods);
		request.setAttribute("jBaseGoodsList", jBaseGoodsList);

		JStocks stocks = new JStocks();
		stocks.getMap().put("cust_id_eq", user.getCust_id());
		List<JStocks> jStocksList = super.getFacade().getJStocksService().getJStocksList(stocks);
		for (JStocks s : jStocksList) {
			dynaBean.set(s.getStore_id() + "_" + s.getGoods_id(), s);
		}

		return mapping.findForward("list");
	}
}
