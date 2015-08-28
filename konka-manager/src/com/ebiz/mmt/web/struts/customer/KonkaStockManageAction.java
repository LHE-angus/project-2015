package com.ebiz.mmt.web.struts.customer;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import ch.qos.logback.core.joran.action.Action;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ebiz.mmt.domain.JBaseGoods;
import com.ebiz.mmt.domain.JBaseStore;
import com.ebiz.mmt.domain.JBaseType;
import com.ebiz.mmt.domain.JStocks;
import com.ebiz.mmt.domain.JStocksStack;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.ssi.web.struts.bean.Pager;

public class KonkaStockManageAction extends BaseClientJxcAction {

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
		String type_id = (String) dynaBean.get("type_id");
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
		entity.setIs_konka(0); // 非康佳产品
		entity.getMap().put("goodsIdsIn", user.getCust_id());
		entity.getMap().put("storeId", store_id);
		if (GenericValidator.isLong(type_id)) {
			entity.setGoods_type(Long.valueOf(type_id));
		}
		
		// Long recordCount =
		// super.getFacade().getJBaseGoodsService().getJBaseGoodsCount(entity);
		Long recordCount = super.getFacade().getJBaseGoodsService().getJBaseGoodsWithStockCount(entity);
		pager.init(recordCount, 5, pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		// List<JBaseGoods> entityList =
		// getFacade().getJBaseGoodsService().getJBaseGoodsPaginatedList(entity);
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
						js.getMap().put("store_sn", jbs.getStore_sn());
						js.getMap().put("store_addr", jbs.getStore_addr());
						js.getMap().put("is_dft_buy_store", jbs.getIs_dft_buy_store());
						js.getMap().put("is_dft_sell_store", jbs.getIs_dft_sell_store());

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

		JBaseGoods jBaseGoods = new JBaseGoods();
		jBaseGoods.setC_id(user.getCust_id());
		jBaseGoods.setIs_konka(0);
		List<JBaseGoods> jBaseGoodsList = getFacade().getJBaseGoodsService().getJBaseGoodsList(jBaseGoods);
		request.setAttribute("jBaseGoodsList", jBaseGoodsList);

		// 取仓库
		JBaseStore jBaseStore = new JBaseStore();
		jBaseStore.setC_id(user.getCust_id());
		List<JBaseStore> jBaseStoreList = getFacade().getJBaseStoreService().getJBaseStoreList(jBaseStore);
		request.setAttribute("jBaseStoreList", jBaseStoreList);

		// 取仓库
		JBaseType jBaseType = new JBaseType();
		jBaseType.setC_id(user.getCust_id());
		jBaseType.setPar_id(10001L);
		List<JBaseType> jBaseTypeList = getFacade().getJBaseTypeService().getJBaseTypeList(jBaseType);
		request.setAttribute("jBaseTypeList", jBaseTypeList);

		return mapping.findForward("list");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String goods_id = (String) dynaBean.get("goods_id");
		String store_id = (String) dynaBean.get("store_id");
		logger.info("_______________________goods_id = " + goods_id + "___________store_id = " + store_id);

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.CUSTOMER_USER_INFO);
		if (null == user) {
			return null;
		} else if (user.getCust_id() == null) {
			return null;
		}

		if (StringUtils.isBlank(store_id) || StringUtils.isBlank(goods_id)) {
			return null;
		}

		JStocks jStocks = new JStocks();
		jStocks.setGoods_id(Long.valueOf(goods_id));
		jStocks.setStore_id(Long.valueOf(store_id));
		jStocks = getFacade().getJStocksService().getJStocks(jStocks);
		if (null != jStocks) {
			dynaBean.set("stocks", jStocks.getStocks());
		}

		JBaseStore jBaseStore = new JBaseStore();
		jBaseStore.setStore_id(Long.valueOf(store_id));
		jBaseStore = getFacade().getJBaseStoreService().getJBaseStore(jBaseStore);
		if (null != jBaseStore) {
			dynaBean.set("store_name", jBaseStore.getStore_name());
		}

		JBaseGoods jBaseGoods = new JBaseGoods();
		jBaseGoods.setGoods_id(Long.valueOf(goods_id));
		jBaseGoods = getFacade().getJBaseGoodsService().getJBaseGoods(jBaseGoods);
		if (null != jBaseGoods) {
			dynaBean.set("goods_name", jBaseGoods.getGoods_name());
		}

		dynaBean.set("goods_id", goods_id);
		dynaBean.set("store_id", store_id);
		request.setAttribute("today", df.format(new Date()));
		request.setAttribute("cust_id", user.getCust_id());

		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String cust_id = (String) dynaBean.get("cust_id");
		String opr_date = (String) dynaBean.get("opr_date");
		String store_id = (String) dynaBean.get("store_id");
		String goods_id = (String) dynaBean.get("goods_id");
		String stocks = (String) dynaBean.get("stocks");
		String ver_stocks = (String) dynaBean.get("ver_stocks");
		String memo = (String) dynaBean.get("memo");
		String[] pyBuyPrices = request.getParameterValues("pyBuyPrice");
		String[] stackIds = request.getParameterValues("stack_id");

		logger.info("______________________" + cust_id);
		logger.info("______________________" + opr_date);
		logger.info("______________________" + store_id);
		logger.info("______________________" + goods_id);
		logger.info("______________________" + stocks);
		logger.info("______________________" + ver_stocks);

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.CUSTOMER_USER_INFO);
		if (null == user) {
			return null;
		} else if (null == user.getCust_id() || Integer.valueOf(cust_id) != user.getCust_id().intValue()) {
			return null;
		}

		if (StringUtils.isNotBlank(opr_date) && StringUtils.isNotBlank(store_id) && StringUtils.isNotBlank(goods_id)
				&& StringUtils.isNotBlank(stocks) && StringUtils.isNotBlank(ver_stocks)) {
			Long result = super
					.getFacade()
					.getKonkaCustomerPublicService()
					.createStockVerify(df.parse(opr_date), store_id, goods_id, stocks, ver_stocks, user.getCust_id(),
							memo, pyBuyPrices, stackIds);
			if (0 == result.intValue()) { // 成功插入
				saveMessage(request, "konka.jstockverify.inserted.success");
			} else if (-1 == result.intValue()) { // 盘点日期选择非法
				saveError(request, "konka.jstockverify.oprdate.error");
			}
		} else {
			saveError(request, "konka.jstockverify.params.missing");
		}
		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		// pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request,
		// "id", "pks", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		return forward;
	}

	public Action getPkGoodsAndStoresList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws ParseException {
		DynaBean dynaBean = (DynaBean) form;
		String store_id = (String) dynaBean.get("store_id");
		String good_id = (String) dynaBean.get("good_id");
		logger.info("___________________________________store_id = " + store_id);
		logger.info("____________________________________good_id = " + good_id);
		String state = "0";
		BigDecimal total_cost = new BigDecimal(0);

		JSONObject result = new JSONObject();
		JSONArray objList = new JSONArray();
		if (StringUtils.isNotBlank(store_id) && StringUtils.isNotBlank(good_id)) {
			HttpSession session = request.getSession();
			PeProdUser user = (PeProdUser) session.getAttribute(Constants.CUSTOMER_USER_INFO);

			JStocksStack entity = new JStocksStack();
			entity.setStore_id(Long.valueOf(store_id));
			entity.setGoods_id(Long.valueOf(good_id));
			if (null != user && null != user.getCust_id()) {
				entity.setC_id(user.getCust_id());
			}
			entity.setStatus(0);
			entity.getMap().put("order_by_stack_id_asc", "true");
			List<JStocksStack> list = getFacade().getJStocksStackService().getJStocksStackList(entity);
			if (null != list && list.size() > 0) {
				state = "1";
				for (JStocksStack jss : list) {
					// 单据编号
					String bill_id_push = jss.getBill_id_push();
					// 业务类型
					String type = StringUtils.split(bill_id_push, "-")[0];
					String business_type = "";
					if ("CG".equals(type)) {
						business_type = "采购";
					} else if ("CGTH".equals(type)) {
						business_type = "采购退货";
					} else if ("XS".equals(type)) {
						business_type = "销售";
					} else if ("XSTH".equals(type)) {
						business_type = "销售退货";
					} else if ("PK".equals(type)) {
						business_type = "盘亏";
					} else if ("PY".equals(type)) {
						business_type = "盘盈";
					} else if ("KSXF".equals(type)) {
						business_type = "库实相符";
					}

					String time = StringUtils.split(bill_id_push, "-")[1];
					Date bill_date = dfm.parse(time);

					JSONObject obj = new JSONObject();
					obj.put("goods_cost", jss.getGoods_cost());
					obj.put("bill_id_push", jss.getBill_id_push());
					obj.put("business_type", business_type);
					obj.put("bill_date", df.format(bill_date));
					obj.put("stack_id", jss.getStack_id());
					objList.add(obj);

					BigDecimal price_cost = jss.getGoods_cost();
					total_cost = total_cost.add(price_cost);
				}
				result.put("list", objList);
			} else {
				state = "-1";
			}
		}
		result.put("state", state);

		DecimalFormat decimalFormat = new DecimalFormat("##,###,###.00");
		result.put("total_cost", decimalFormat.format(total_cost));
		logger.info("__________________________result = " + result.toString());
		renderJson(response, result.toString());
		return null;
	}

}
