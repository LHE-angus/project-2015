package com.ebiz.mmt.web.struts.customer;

import com.ebiz.mmt.domain.*;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.util.EncryptUtils;
import com.ebiz.ssi.web.struts.bean.Pager;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

public class JxcFifoStocksAction extends BaseAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.listAccount(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.listDetails(mapping, form, request, response);
	}

	public ActionForward listDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		setNaviStringToRequestScope(form, request);

		// 判断session是否超时
		PeProdUser ui = (PeProdUser) getSessionAttribute(request, Constants.CUSTOMER_USER_INFO);
		if (null == ui) {
			String msg = super.getMessage(request, "user.session.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}
		KonkaR3Shop shop = new KonkaR3Shop();
		shop.setId(ui.getCust_id());
		shop = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(shop);
		if (null == shop || null == shop.getR3_code()) {
			super.renderJavaScript(response, "alert('客户信息有误，请联系相关人员处理！');history.back();");
			return null;
		}
		request.setAttribute("konkaR3Shop", shop);
		DynaBean dynaBean = (DynaBean) form;
		String r3_code = shop.getR3_code();
		String goods_name_like = (String) dynaBean.get("goods_name_like");

		String start_date = (String) dynaBean.get("start_date");
		String end_date = (String) dynaBean.get("end_date");
		String mod_name = (String) dynaBean.get("mod_name");
		String excel_to_all = (String) dynaBean.get("excel_to_all");
		String store_id = (String) dynaBean.get("store_id");
		String stock_state = (String) dynaBean.get("stock_state");

		Pager pager = (Pager) dynaBean.get("pager");

		JxcFifoStocks entity = new JxcFifoStocks();
		if (StringUtils.isNotBlank(r3_code)) {
			entity.setR3_code(r3_code);
		}
		if (StringUtils.isNotBlank(stock_state) && GenericValidator.isInt(stock_state)) {
			entity.setStock_state(Integer.parseInt(stock_state));
		}
		if (StringUtils.isNotBlank(goods_name_like)) {
			entity.getMap().put("goods_name_like", goods_name_like);
		}
		if (StringUtils.isNotBlank(mod_name)) {
			entity.setGoods_model(mod_name);
		}
		if (StringUtils.isNotBlank(start_date)) {
			entity.getMap().put("in_start_date", start_date);
		}
		if (StringUtils.isNotBlank(end_date)) {
			entity.getMap().put("in_end_date", end_date);
		}
		if (StringUtils.isNotBlank(store_id) && GenericValidator.isLong(store_id)) {
			entity.setStock_in_store(Long.valueOf(store_id));
		}
		Long recordCount = super.getFacade().getJxcFifoStocksService().getJxcFifoStocksManagerDetailsCount(entity);

		// 导出功能
		if (StringUtils.isNotBlank(excel_to_all)) {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename=" + EncryptUtils.encodingFileName("先进先出明细")
					+ ".xls");

			entity.getRow().setFirst(0);
			entity.getRow().setCount(recordCount.intValue());
			List<JxcFifoStocks> entityList = super.getFacade().getJxcFifoStocksService()
					.getJxcFifoStocksManagerDetailsPaginatedList(entity);
			request.setAttribute("entityList", entityList);
			return new ActionForward("/../customer/JxcFifoStocks/list_details_report.jsp");
		}

		pager.setPageSize(10);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<JxcFifoStocks> entityList = super.getFacade().getJxcFifoStocksService()
				.getJxcFifoStocksManagerDetailsPaginatedList(entity);

		request.setAttribute("entityList", entityList);

		// 取仓库
		JBaseStore jBaseStore = new JBaseStore();
		jBaseStore.setC_id(ui.getCust_id());
		jBaseStore.setIs_del(0);
		List<JBaseStore> jBaseStoreList = getFacade().getJBaseStoreService().getJBaseStoreList(jBaseStore);
		request.setAttribute("jBaseStoreList", jBaseStoreList);

		// 取客户商品库产品
		JBaseGoods jBaseGoods = new JBaseGoods();
		jBaseGoods.setC_id(ui.getCust_id());
		List<JBaseGoods> jBaseGoodsList = getFacade().getJBaseGoodsService().getJBaseGoodsList(jBaseGoods);
		request.setAttribute("jBaseGoodsList", jBaseGoodsList);

		request.setAttribute("now_date", new Date());

		return new ActionForward("/../customer/JxcFifoStocks/list_details.jsp");
	}

	public ActionForward listAccount(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		setNaviStringToRequestScope(form, request);
		// 判断session是否超时
		PeProdUser ui = (PeProdUser) getSessionAttribute(request, Constants.CUSTOMER_USER_INFO);
		if (null == ui) {
			String msg = super.getMessage(request, "user.session.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}
		KonkaR3Shop shop = new KonkaR3Shop();
		shop.setId(ui.getCust_id());
		shop = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(shop);
		if (null == shop || null == shop.getR3_code()) {
			super.renderJavaScript(response, "alert('客户信息有误，请联系相关人员处理！');history.back();");
			return null;
		}
		DynaBean dynaBean = (DynaBean) form;
		String r3_code = shop.getR3_code();
		String goods_name_like = (String) dynaBean.get("goods_name_like");
		String store_id = (String) dynaBean.get("store_id");
		String start_date = (String) dynaBean.get("start_date");
		String end_date = (String) dynaBean.get("end_date");
		String mod_name = (String) dynaBean.get("mod_name");
		String excel_to_all = (String) dynaBean.get("excel_to_all");
		String group_by_store = (String) dynaBean.get("group_by_store");

		String stock_state = (String) dynaBean.get("stock_state");

		Pager pager = (Pager) dynaBean.get("pager");

		JxcFifoStocks entity = new JxcFifoStocks();
		if (StringUtils.isNotBlank(r3_code)) {
			entity.setR3_code(r3_code);
		}
		if (StringUtils.isNotBlank(stock_state) && GenericValidator.isInt(stock_state)) {
			entity.setStock_state(Integer.parseInt(stock_state));
		}
		if (StringUtils.isNotBlank(goods_name_like)) {
			entity.getMap().put("goods_name_like", goods_name_like);
		}
		if (StringUtils.isNotBlank(mod_name)) {
			entity.setGoods_model(mod_name);
		}
		if (StringUtils.isNotBlank(start_date)) {
			entity.getMap().put("in_start_date", start_date);
		}
		if (StringUtils.isNotBlank(end_date)) {
			entity.getMap().put("in_end_date", end_date);
		}
		if (StringUtils.isNotBlank(group_by_store)) {
			entity.getMap().put("group_by_store", group_by_store);
			System.out.println(group_by_store);
			request.setAttribute("group_by_store", group_by_store);
		}
		if (StringUtils.isNotBlank(store_id) && GenericValidator.isLong(store_id)) {
			entity.setStock_in_store(Long.valueOf(store_id));
		}

		Long recordCount = super.getFacade().getJxcFifoStocksService().getJxcFifoStocksManagerAccountCount(entity);
		if (recordCount.intValue() == 0) {
			request.setAttribute("entityList", null);
		} else {
			pager.setPageSize(recordCount.intValue());
			pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
			entity.getRow().setFirst(pager.getFirstRow());
			entity.getRow().setCount(pager.getRowCount());
			List<JxcFifoStocks> entityList = super.getFacade().getJxcFifoStocksService()
					.getJxcFifoStocksManagerAccountPaginatedList(entity);
			request.setAttribute("entityList", entityList);
		}

		request.setAttribute("konkaR3Shop", shop);

		// 取仓库
		JBaseStore jBaseStore = new JBaseStore();
		jBaseStore.setC_id(ui.getCust_id());
		jBaseStore.setIs_del(0);
		List<JBaseStore> jBaseStoreList = getFacade().getJBaseStoreService().getJBaseStoreList(jBaseStore);
		request.setAttribute("jBaseStoreList", jBaseStoreList);

		// 取客户商品库产品
		JBaseGoods jBaseGoods = new JBaseGoods();
		jBaseGoods.setC_id(ui.getCust_id());
		List<JBaseGoods> jBaseGoodsList = getFacade().getJBaseGoodsService().getJBaseGoodsList(jBaseGoods);
		request.setAttribute("jBaseGoodsList", jBaseGoodsList);

		return new ActionForward("/../customer/JxcFifoStocks/list_count.jsp");
	}

	public ActionForward viewDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		setNaviStringToRequestScope(form, request);

		// 判断session是否超时
		PeProdUser ui = (PeProdUser) getSessionAttribute(request, Constants.CUSTOMER_USER_INFO);
		if (null == ui) {
			String msg = super.getMessage(request, "user.session.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}
		KonkaR3Shop shop = new KonkaR3Shop();
		shop.setId(ui.getCust_id());
		shop = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(shop);
		if (null == shop || null == shop.getR3_code()) {
			super.renderJavaScript(response, "alert('客户信息有误，请联系相关人员处理！');history.back();");
			return null;
		}

		request.setAttribute("konkaR3Shop", shop);

		DynaBean dynaBean = (DynaBean) form;
		String r3_code = shop.getR3_code();
		String goods_name_like = (String) dynaBean.get("goods_name_like");
		String store_id = (String) dynaBean.get("store_id");
		String stock_state = (String) dynaBean.get("stock_state");
		String direction = (String) dynaBean.get("direction");

		JxcFifoStocks entity = new JxcFifoStocks();

		if (StringUtils.isNotBlank(direction)) {
			entity.getMap().put("direction", direction);

			if ("10".equals(direction)) {
				entity.getMap().put("direction_one", "one");
			} else if ("20".equals(direction)) {
				entity.getMap().put("direction_two", "two");
			} else if ("30".equals(direction)) {
				entity.getMap().put("direction_three", "three");
				entity.getMap().put("direction_ext", "ext");

			} else if ("40".equals(direction)) {
				entity.getMap().put("direction_four", "four");
				entity.getMap().put("direction_ext", "ext");
			}
		}
		if (StringUtils.isNotBlank(r3_code)) {
			entity.setR3_code(r3_code);
		}
		if (StringUtils.isNotBlank(store_id)) {

			entity.setStock_in_store(Long.valueOf(store_id));

		}
		if (StringUtils.isNotBlank(goods_name_like)) {
			entity.getMap().put("goods_name_like", goods_name_like);
		}

		// 查询详细信息
		List<JxcFifoStocks> entityList = super.getFacade().getJxcFifoStocksService()
				.getJxcFifoStocksViewDetailsList(entity);

		request.setAttribute("entityList", entityList);

		return new ActionForward("/../customer/JxcFifoStocks/view_details.jsp");
	}

}
