package com.ebiz.mmt.web.struts.customer;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.*;
import com.ebiz.mmt.web.Constants;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Xiao, GuoJian
 * @version 2014-07-20
 */
public class JxcOutInDetailAction extends BaseClientJxcAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String out_r3_code = (String) dynaBean.get("out_r3_code"); // 移出客户
		String out_store_id = (String) dynaBean.get("out_store_id"); // 移出仓库ID
		String in_r3_code = (String) dynaBean.get("in_r3_code"); // 移入客户
		String in_store_id = (String) dynaBean.get("in_store_id"); // 移入仓库ID
		String out_date_s = (String) dynaBean.get("out_date_s"); // 初始化时间
		String out_date_e = (String) dynaBean.get("out_date_e"); // 初始化时间
		String goods_name_like = (String) dynaBean.get("goods_name_like"); // 商品名称
		String trans_index_like = (String) dynaBean.get("trans_index_like"); // 移仓/调拨编码
		String out_r3_code_like = (String) dynaBean.get("out_r3_code_like"); // 移出客户R3编码
		String in_r3_code_like = (String) dynaBean.get("in_r3_code_like"); // 移入客户R3编码
		String type = (String) dynaBean.get("type"); // 类型
		String state = (String) dynaBean.get("state"); // 确认状态
		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.CUSTOMER_USER_INFO);
		if (null == user) {
			super.renderJavaScript(response, "alert('出现未知错误，请联系管理员！');history.back();");
			return null;
		}

		JxcOutInDetail entity = new JxcOutInDetail();
		if (GenericValidator.isLong(out_store_id)) {
			entity.setOut_store_id(Long.valueOf(out_store_id));
		}
		if (GenericValidator.isLong(in_store_id)) {
			entity.setIn_store_id(Long.valueOf(in_store_id));
		}
		if (StringUtils.isNotBlank(out_date_s)) {
			entity.getMap().put("out_date_s", out_date_s + " 00:00:00");
		}
		if (StringUtils.isNotBlank(out_date_e)) {
			entity.getMap().put("out_date_e", out_date_e + " 23:59:59");
		}
		if (StringUtils.isNotBlank(goods_name_like)) {
			entity.getMap().put("goods_name_like", goods_name_like.trim());
		}
		if (StringUtils.isNotBlank(out_r3_code_like)) {
			entity.getMap().put("out_r3_code_like", out_r3_code_like.trim());
		}
		if (StringUtils.isNotBlank(in_r3_code_like)) {
			entity.getMap().put("in_r3_code_like", in_r3_code_like.trim());
		}
		if (StringUtils.isNotBlank(trans_index_like)) {
			entity.getMap().put("trans_index_like", trans_index_like.trim());
		}
		if (StringUtils.isNotBlank(type)) {
			entity.setTrans_type(Integer.parseInt(type));
		}
		if (StringUtils.isNotBlank(state)) {
			entity.setState(Integer.parseInt(state));
		}

		KonkaR3Shop r3shop = new KonkaR3Shop();
		r3shop.setId(user.getCust_id());
		r3shop = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(r3shop);
		if ("0".equals(type)) {// 调入
			entity.setIn_r3_code(r3shop.getR3_code());
		} else if ("1".equals(type)) {// 调出
			entity.setOut_r3_code(r3shop.getR3_code());
		} else if ("10".equals(type)) {// 移出
			entity.setOut_r3_code(r3shop.getR3_code());
			entity.setIn_r3_code(r3shop.getR3_code());
		}
		if (StringUtils.isNotBlank(out_r3_code)) {
			entity.setOut_r3_code(out_r3_code);
			KonkaR3Shop r3shop1 = new KonkaR3Shop();
			r3shop1.setR3_code(out_r3_code);
			r3shop1 = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(r3shop1);

			// 获取仓库名称
			JBaseStore jBaseStore = new JBaseStore();
			jBaseStore.getMap().put("order_by_is_dft_buy_store_desc", true);
			jBaseStore.setC_id(r3shop1.getId());
			jBaseStore.setIs_del(0);
			List<JBaseStore> jBaseStoreList = super.getFacade().getJBaseStoreService().getJBaseStoreList(jBaseStore);
			request.setAttribute("outjBaseStoreList", jBaseStoreList);
		}
		if (StringUtils.isNotBlank(in_r3_code)) {
			entity.setIn_r3_code(in_r3_code);
			KonkaR3Shop r3shop1 = new KonkaR3Shop();
			r3shop1.setR3_code(in_r3_code);
			r3shop1 = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(r3shop1);

			// 获取仓库名称
			JBaseStore jBaseStore = new JBaseStore();
			jBaseStore.getMap().put("order_by_is_dft_buy_store_desc", true);
			jBaseStore.setC_id(r3shop1.getId());
			jBaseStore.setIs_del(0);
			List<JBaseStore> jBaseStoreList = super.getFacade().getJBaseStoreService().getJBaseStoreList(jBaseStore);
			request.setAttribute("injBaseStoreList", jBaseStoreList);
		}

		Long recordCount = super.getFacade().getJxcOutInDetailService().getJxcOutInDetailCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<JxcOutInDetail> entityList = super.getFacade().getJxcOutInDetailService()
				.getJxcOutInDetailPaginatedList(entity);
		request.setAttribute("entityList", entityList);

		if ("0".equals(type)) {// 调入
			KonkaDept dept = super.getKonkaDeptByCustomerId(Long.valueOf(user.getCust_id()));
			KonkaR3Shop shop = new KonkaR3Shop();
			shop.setIs_del(0l);
			shop.setBranch_area_name_2(dept.getDept_sn());
			shop.setIs_konka(1);
			shop.setIs_sdf(0);
			List<KonkaR3Shop> shopList = super.getFacade().getKonkaR3ShopService().getKonkaR3ShopList(shop);
			request.setAttribute("outr3shopList", shopList);// 调出客户列表
			JBaseStore jBaseStore = new JBaseStore();
			jBaseStore.getMap().put("order_by_is_dft_buy_store_desc", true);
			jBaseStore.setC_id(user.getCust_id());
			jBaseStore.setIs_del(0);
			List<JBaseStore> jBaseStoreList = super.getFacade().getJBaseStoreService().getJBaseStoreList(jBaseStore);
			request.setAttribute("injBaseStoreList", jBaseStoreList);
			// request.setAttribute("injBaseStoreList",
			// super.getJBaseStores(request, 0));
			shop.setId(user.getCust_id());
			List<KonkaR3Shop> shopList1 = super.getFacade().getKonkaR3ShopService().getKonkaR3ShopList(shop);
			request.setAttribute("inr3shopList", shopList1);// 调入客户列表，即当前客户自己，方面动态级联出仓库
		} else if ("1".equals(type)) {// 调出
			KonkaDept dept = super.getKonkaDeptByCustomerId(Long.valueOf(user.getCust_id()));
			KonkaR3Shop shop = new KonkaR3Shop();
			shop.setIs_del(0l);
			shop.setBranch_area_name_2(dept.getDept_sn());
			shop.setIs_konka(1);
			shop.setIs_sdf(0);
			List<KonkaR3Shop> shopList = super.getFacade().getKonkaR3ShopService().getKonkaR3ShopList(shop);
			request.setAttribute("inr3shopList", shopList);// 调入客户列表
			JBaseStore jBaseStore = new JBaseStore();
			jBaseStore.getMap().put("order_by_is_dft_buy_store_desc", true);
			jBaseStore.setC_id(user.getCust_id());
			jBaseStore.setIs_del(0);
			List<JBaseStore> jBaseStoreList = super.getFacade().getJBaseStoreService().getJBaseStoreList(jBaseStore);
			request.setAttribute("outjBaseStoreList", jBaseStoreList);
			// request.setAttribute("outjBaseStoreList",
			// super.getJBaseStores(request, 0));
			shop.setId(user.getCust_id());
			List<KonkaR3Shop> shopList1 = super.getFacade().getKonkaR3ShopService().getKonkaR3ShopList(shop);
			request.setAttribute("outr3shopList", shopList1);// 调入客户列表，即当前客户自己，方面动态级联出仓库
		} else if ("10".equals(type) || "11".equals(type)) {
			JBaseStore jBaseStore = new JBaseStore();
			jBaseStore.getMap().put("order_by_is_dft_buy_store_desc", true);
			jBaseStore.setC_id(user.getCust_id());
			jBaseStore.setIs_del(0);
			List<JBaseStore> jBaseStoreList = super.getFacade().getJBaseStoreService().getJBaseStoreList(jBaseStore);
			request.setAttribute("jBaseStoreList", jBaseStoreList);
			// request.setAttribute("jBaseStoreList",
			// super.getJBaseStores(request, 0));
			return new ActionForward("/../customer/JxcOutInDetail/list10.jsp");
		}
		return mapping.findForward("list");
	}

	public ActionForward confirmList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String out_r3_code = (String) dynaBean.get("out_r3_code"); // 移出客户
		String out_store_id = (String) dynaBean.get("out_store_id"); // 移出仓库ID
		String in_r3_code = (String) dynaBean.get("in_r3_code"); // 移入客户
		String in_store_id = (String) dynaBean.get("in_store_id"); // 移入仓库ID
		String out_date_s = (String) dynaBean.get("out_date_s"); // 初始化时间
		String out_date_e = (String) dynaBean.get("out_date_e"); // 初始化时间
		String goods_name_like = (String) dynaBean.get("goods_name_like"); // 商品名称
		String trans_index_like = (String) dynaBean.get("trans_index_like"); // 移仓/调拨编码
		String out_r3_code_like = (String) dynaBean.get("out_r3_code_like"); // 移出客户R3编码
		String in_r3_code_like = (String) dynaBean.get("in_r3_code_like"); // 移入客户R3编码
		String type = (String) dynaBean.get("type"); // 类型
		String state = (String) dynaBean.get("state"); // 确认状态
		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.CUSTOMER_USER_INFO);
		if (null == user) {
			super.renderJavaScript(response, "alert('出现未知错误，请联系管理员！');history.back();");
			return null;
		}

		JxcOutInDetail entity = new JxcOutInDetail();
		if (GenericValidator.isLong(out_store_id)) {
			entity.setOut_store_id(Long.valueOf(out_store_id));
		}
		if (GenericValidator.isLong(in_store_id)) {
			entity.setIn_store_id(Long.valueOf(in_store_id));
		}
		if (StringUtils.isNotBlank(out_date_s)) {
			entity.getMap().put("out_date_s", out_date_s + " 00:00:00");
		}
		if (StringUtils.isNotBlank(out_date_e)) {
			entity.getMap().put("out_date_e", out_date_e + " 23:59:59");
		}
		if (StringUtils.isNotBlank(goods_name_like)) {
			entity.getMap().put("goods_name_like", goods_name_like.trim());
		}
		if (StringUtils.isNotBlank(out_r3_code_like)) {
			entity.getMap().put("out_r3_code_like", out_r3_code_like.trim());
		}
		if (StringUtils.isNotBlank(in_r3_code_like)) {
			entity.getMap().put("in_r3_code_like", in_r3_code_like.trim());
		}
		if (StringUtils.isNotBlank(trans_index_like)) {
			entity.getMap().put("trans_index_like", trans_index_like.trim());
		}
		if (StringUtils.isNotBlank(type)) {
			entity.setTrans_type(Integer.parseInt(type));
		}
		if (StringUtils.isNotBlank(state)) {
			entity.setState(Integer.parseInt(state));
		}

		KonkaR3Shop r3shop = new KonkaR3Shop();
		r3shop.setId(user.getCust_id());
		r3shop = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(r3shop);
		if ("0".equals(type)) {// 对自己来说是调出
			entity.setOut_r3_code(r3shop.getR3_code());
		} else if ("1".equals(type)) {// 对自己来说是调入
			entity.setIn_r3_code(r3shop.getR3_code());
		}
		if (StringUtils.isNotBlank(out_r3_code)) {
			entity.setOut_r3_code(out_r3_code);
			KonkaR3Shop r3shop1 = new KonkaR3Shop();
			r3shop1.setR3_code(out_r3_code);
			r3shop1 = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(r3shop1);

			// 获取仓库名称
			JBaseStore jBaseStore = new JBaseStore();
			jBaseStore.getMap().put("order_by_is_dft_buy_store_desc", true);
			jBaseStore.setC_id(r3shop1.getId());
			jBaseStore.setIs_del(0);
			List<JBaseStore> jBaseStoreList = super.getFacade().getJBaseStoreService().getJBaseStoreList(jBaseStore);
			request.setAttribute("outjBaseStoreList", jBaseStoreList);
		}
		if (StringUtils.isNotBlank(in_r3_code)) {
			entity.setIn_r3_code(in_r3_code);
			KonkaR3Shop r3shop1 = new KonkaR3Shop();
			r3shop1.setR3_code(in_r3_code);
			r3shop1 = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(r3shop1);

			// 获取仓库名称
			JBaseStore jBaseStore = new JBaseStore();
			jBaseStore.getMap().put("order_by_is_dft_buy_store_desc", true);
			jBaseStore.setC_id(r3shop1.getId());
			jBaseStore.setIs_del(0);
			List<JBaseStore> jBaseStoreList = super.getFacade().getJBaseStoreService().getJBaseStoreList(jBaseStore);
			request.setAttribute("injBaseStoreList", jBaseStoreList);
		}

		Long recordCount = super.getFacade().getJxcOutInDetailService().getJxcOutInDetailCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<JxcOutInDetail> entityList = super.getFacade().getJxcOutInDetailService()
				.getJxcOutInDetailPaginatedList(entity);
		request.setAttribute("entityList", entityList);

		if ("1".equals(type)) {// 调入
			KonkaDept dept = super.getKonkaDeptByCustomerId(Long.valueOf(user.getCust_id()));
			KonkaR3Shop shop = new KonkaR3Shop();
			shop.setIs_del(0l);
			shop.setBranch_area_name_2(dept.getDept_sn());
			shop.setIs_konka(1);
			shop.setIs_sdf(0);
			List<KonkaR3Shop> shopList = super.getFacade().getKonkaR3ShopService().getKonkaR3ShopList(shop);
			request.setAttribute("outr3shopList", shopList);// 调出客户列表
			request.setAttribute("injBaseStoreList", super.getJBaseStores(request, 0));
			shop.setId(user.getCust_id());
			List<KonkaR3Shop> shopList1 = super.getFacade().getKonkaR3ShopService().getKonkaR3ShopList(shop);
			request.setAttribute("inr3shopList", shopList1);// 调入客户列表，即当前客户自己，方面动态级联出仓库
		} else if ("0".equals(type)) {// 调出
			KonkaDept dept = super.getKonkaDeptByCustomerId(Long.valueOf(user.getCust_id()));
			KonkaR3Shop shop = new KonkaR3Shop();
			shop.setIs_del(0l);
			shop.setBranch_area_name_2(dept.getDept_sn());
			shop.setIs_konka(1);
			shop.setIs_sdf(0);
			List<KonkaR3Shop> shopList = super.getFacade().getKonkaR3ShopService().getKonkaR3ShopList(shop);
			request.setAttribute("inr3shopList", shopList);// 调入客户列表
			request.setAttribute("outjBaseStoreList", super.getJBaseStores(request, 0));
			shop.setId(user.getCust_id());
			List<KonkaR3Shop> shopList1 = super.getFacade().getKonkaR3ShopService().getKonkaR3ShopList(shop);
			request.setAttribute("outr3shopList", shopList1);// 调入客户列表，即当前客户自己，方面动态级联出仓库
		}

		return new ActionForward("/../customer/JxcOutInDetail/confirmList.jsp");
	}

	/**
	 * Xiao,GuoJian 保存仓库和型号到绑定
	 */
	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		super.saveToken(request);
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String type = (String) dynaBean.get("type"); // 类型
		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.CUSTOMER_USER_INFO);
		if (null == user) {
			super.renderJavaScript(response, "alert('数据丢失，请联系管理员！');history.back();");
			return null;
		}
		if (StringUtils.isBlank(type)) {
			super.renderJavaScript(response, "alert('数据丢失，请联系管理员！');history.back();");
			return null;
		}
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		request.setAttribute("nowdate", format.format(date));// 当前时间
		Calendar cale = Calendar.getInstance();
		cale.setTime(date);
		cale.add(Calendar.DATE, -30);
		request.setAttribute("beforedate", format.format(cale.getTime()));

		request.setAttribute("jBaseGoodsList", super.getJBaseGoodsList(user.getCust_id(), 0));
		request.setAttribute("jBaseStoreList", super.getJBaseStores(request, 0));
		if ("0".equals(type) || "1".equals(type)) {// 调拨
			request.setAttribute("type", type);
			// --start---获取同分公司的所有客户-----------------------------
			KonkaDept dept = super.getKonkaDeptByCustomerId(Long.valueOf(user.getCust_id()));
			KonkaR3Shop shop = new KonkaR3Shop();
			shop.setIs_del(0l);
			shop.setBranch_area_name_2(dept.getDept_sn());
			shop.setIs_konka(1);
			shop.setIs_sdf(0);
			List<KonkaR3Shop> shopList = super.getFacade().getKonkaR3ShopService().getKonkaR3ShopList(shop);
			request.setAttribute("r3shopList", shopList);
			// ---end----获取同分公司的所有客户-----------------------------
			JBaseStore store = new JBaseStore();
			store.setIs_del(0);
			store.setC_id(user.getCust_id());
			store.setIs_dft_buy_store(1);
			store.setIs_dft_sell_store(1);
			store.getRow().setCount(1);
			List<JBaseStore> list = super.getFacade().getJBaseStoreService().getJBaseStoreList(store);
			if (null != list && list.size() > 0) {
				store = list.get(0);
				if ("0".equals(type)) {
					dynaBean.set("in_store_id", store.getStore_id());
				} else {
					dynaBean.set("out_store_id", store.getStore_id());
				}
			}

		} else {
			JBaseStore store = new JBaseStore();
			store.setIs_del(0);
			store.setC_id(user.getCust_id());
			store.setIs_dft_buy_store(1);
			store.setIs_dft_sell_store(1);
			store.getRow().setCount(1);
			List<JBaseStore> list = super.getFacade().getJBaseStoreService().getJBaseStoreList(store);
			if (null != list && list.size() > 0) {
				store = list.get(0);
				dynaBean.set("out_store_id", store.getStore_id());
			}
			return new ActionForward("/../customer/JxcOutInDetail/form" + type + ".jsp");
		}

		return mapping.findForward("input");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		super.saveToken(request);
		setNaviStringToRequestScope(form, request);
		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.CUSTOMER_USER_INFO);
		if (null == user) {
			super.renderJavaScript(response, "alert('出现未知错误，请联系管理员！');history.back();");
			return null;
		}

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");

		if (!GenericValidator.isLong(id)) {
			super.renderJavaScript(response, "alert('出现未知错误，请联系管理员！');history.back();");
			return null;
		}

		JBaseGoodsInitStock entity = new JBaseGoodsInitStock();
		entity.setId(Long.valueOf(id));
		entity = super.getFacade().getJBaseGoodsInitStockService().getJBaseGoodsInitStock(entity);
		entity.setQueryString(super.serialize(request, "id", "method"));
		super.copyProperties(form, entity);

		request.setAttribute("jBaseGoodsList", super.getJBaseGoodsList(user.getCust_id(), 0));
		request.setAttribute("jBaseStoreList", super.getJBaseStores(request, 0));
		return new ActionForward("/../customer/JBaseGoodsInitStock/edit.jsp");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.CUSTOMER_USER_INFO);
		if (null == user) {
			super.renderJavaScript(response, "alert('出现未知错误，请联系管理员！');history.back();");
			return null;
		}

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");

		if (!GenericValidator.isLong(id)) {
			super.renderJavaScript(response, "alert('出现未知错误，请联系管理员！');history.back();");
			return null;
		}

		JxcOutInDetail entity = new JxcOutInDetail();
		entity.setId(Long.valueOf(id));
		entity.getRow().setCount(1);
		entity = super.getFacade().getJxcOutInDetailService().getJxcOutInDetail(entity);
		if (null == entity) {
			super.renderJavaScript(response, "alert('出现未知错误，请联系管理员！');history.back();");
			return null;
		}

		super.copyProperties(form, entity);
		// return new ActionForward("/../customer/JxcOutInDetail/view.jsp");
		return mapping.findForward("view");
	}

	public ActionForward confirm(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		super.saveToken(request);
		setNaviStringToRequestScope(form, request);
		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.CUSTOMER_USER_INFO);
		if (null == user) {
			super.renderJavaScript(response, "alert('出现错误，请联系管理员！');history.back();");
			return null;
		}

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String type = (String) dynaBean.get("type");

		if (!GenericValidator.isLong(id)) {
			super.renderJavaScript(response, "alert('数据丢失，请联系管理员！');history.back();");
			return null;
		}
		if (!GenericValidator.isLong(type)) {
			super.renderJavaScript(response, "alert('数据丢失，请联系管理员！');history.back();");
			return null;
		}

		JxcOutInDetail entity = new JxcOutInDetail();
		entity.setId(Long.valueOf(id));
		entity = super.getFacade().getJxcOutInDetailService().getJxcOutInDetail(entity);
		if (null == entity) {
			super.renderJavaScript(response, "alert('出现未知错误，请联系管理员！');history.back();");
			return null;
		}
		JBaseStore store = new JBaseStore();
		// if ("0".equals(type)) {// 调出
		store.setC_id(user.getCust_id());
		// } else {// 调入
		// KonkaR3Shop r3shop = new KonkaR3Shop();
		// r3shop.setR3_code(entity.getOut_r3_code());
		// r3shop.setIs_del(0l);
		// r3shop =
		// super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(r3shop);
		// if (null != r3shop && r3shop.getId() != null) {
		// store.setC_id(r3shop.getId());
		// } else {
		// super.renderJavaScript(response,
		// "alert('出现未知错误，请联系管理员！');history.back();");
		// return null;
		// }
		// }
		store.setIs_del(0);
		List<JBaseStore> list = super.getFacade().getJBaseStoreService().getJBaseStoreList(store);
		if (null != list && list.size() > 0) {
			request.setAttribute("jBaseStoreList", list);
		} else {
			super.renderJavaScript(response, "alert('数据丢失，请联系管理员！');history.back();");
			return null;
		}

		super.copyProperties(form, entity);
		return new ActionForward("/../customer/JxcOutInDetail/confirm.jsp");
		// return mapping.findForward("confirm");
	}

	// 保存移仓
	public ActionForward save10(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (!super.isTokenValid(request, false)) {
			super.renderJavaScript(response, "alert('" + super.getMessage(request, "errors.token")
					+ "');history.back();");
			return null;
		}
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String out_date = (String) dynaBean.get("out_date");
		String[] out_store_ids = request.getParameterValues("out_store_id");
		String[] in_store_ids = request.getParameterValues("in_store_id");
		String[] out_goods_ids = request.getParameterValues("out_goods_id");
		String[] in_nums = request.getParameterValues("in_num");
		String[] memos = request.getParameterValues("memo");

		PeProdUser user = (PeProdUser) getSessionAttribute(request, Constants.CUSTOMER_USER_INFO);
		if (null == user) {
			super.renderJavaScript(response, "alert('出现未知错误，请联系管理员！');history.back();");
			return null;
		}
		Date _date = DateUtils.parseDate(out_date, new String[] { "yyyy-MM-dd" });
		if (in_store_ids != null && in_store_ids.length > 0) {
			List<JxcOutInDetail> entityList = new ArrayList<JxcOutInDetail>();
			for (int i = 0; i < out_store_ids.length; i++) {
				String out_store_id = out_store_ids[i];
				String in_store_id = in_store_ids[i];
				String out_goods_id = out_goods_ids[i];
				String in_num = in_nums[i];
				String memo = memos[i];
				logger.info("---out_store_id=" + out_store_id + "    in_store_id=" + in_store_id + "     out_goods_in="
						+ out_goods_id + "    in_num=" + in_num);
				if (StringUtils.isNotBlank(out_store_id) && StringUtils.isNotBlank(in_store_id)
						&& StringUtils.isNotBlank(out_goods_id) && StringUtils.isNotBlank(in_num)) {
					JxcOutInDetail entity = new JxcOutInDetail();
					entity.setAdd_date(new Date());
					entity.setTrans_type(10);// 移仓(移出)
					entity.setOut_store_id(Long.parseLong(out_store_id));
					entity.setIn_store_id(Long.parseLong(in_store_id));
					entity.setMemo(memo);
					entity.setOut_goods_id(Long.parseLong(out_goods_id));
					entity.setOut_date(_date);
					if (user.getId() != null && user.getCust_id() != null) {
						KonkaR3Shop r3shop = new KonkaR3Shop();
						r3shop.setId(user.getCust_id());
						r3shop = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(r3shop);
						if (null != r3shop && null != r3shop.getR3_code()) {
							entity.setOut_r3_code(r3shop.getR3_code());
							entity.setIn_r3_code(r3shop.getR3_code());
						}
						entity.setAdd_user_id(user.getId());

						if (entity.getOut_goods_id() != null) {
							entity.setIn_goods_id(entity.getOut_goods_id());
							JBaseGoods goods = new JBaseGoods();
							goods.setC_id(user.getCust_id());
							goods.setGoods_id(entity.getOut_goods_id());
							goods = super.getFacade().getJBaseGoodsService().getJBaseGoods(goods);
							if (null != goods && null != goods.getGoods_name()) {
								entity.setGoods_name(goods.getGoods_name());
							}
						}
					}

					// 先验证在移入仓库中是否已经初始化这个商品
					JBaseGoodsInitStock initStock1 = new JBaseGoodsInitStock();
					initStock1.setC_id(user.getCust_id());
					initStock1.setGoods_id(Long.parseLong(out_goods_id));
					initStock1.setStore_id(Long.parseLong(in_store_id));
					List<JBaseGoodsInitStock> initStockList1 = super.getFacade().getJBaseGoodsInitStockService()
							.getJBaseGoodsInitStockList(initStock1);
					if (initStockList1 == null || initStockList1.size() < 1) {
						super.renderJavaScript(response,
								"alert('请先在仓库中初始化库存！');window.location.href='JBaseGoodsInitStock.do?method=add&mod_id="
										+ mod_id + "&returnUrl=JxcOutInDetail.do?type=10';");
						return null;
					}

					// 判断商品库存是否超出原仓库的库存
					if (entity.getGoods_name() != null) {
						entity.setIn_num(Integer.parseInt(in_num));
						int stocks = Integer.parseInt(super.getKhStocksWithMoney(entity.getOut_r3_code(),
								entity.getGoods_name(), entity.getOut_store_id())
								+ "");
						if (entity.getIn_num() > stocks) {
							super.renderJavaScript(response, "alert('数量不能大于移出仓库中的库存！');history.back();");
							return null;
						}
					}

					// 其次再获取商品的价格等信息
					JBaseGoodsInitStock initStock = new JBaseGoodsInitStock();
					initStock.setC_id(user.getCust_id());
					initStock.setGoods_id(entity.getOut_goods_id());
					initStock.setStore_id(entity.getOut_store_id());
					List<JBaseGoodsInitStock> initStockList = super.getFacade().getJBaseGoodsInitStockService()
							.getJBaseGoodsInitStockList(initStock);
					if (initStockList != null && initStockList.size() > 0) {
						initStock = initStockList.get(0);
						entity.setIn_price(initStock.getBuy_price());
						entity.setOut_price(initStock.getBuy_price());
						BigDecimal num = new BigDecimal(entity.getIn_num());
						entity.setIn_money(num.multiply(initStock.getBuy_price()));
						entity.setOut_money(num.multiply(initStock.getBuy_price()));
					} else {
						super.renderJavaScript(response, "alert('请先在仓库中初始化库存！');history.back();");
						return null;
					}
					KonkaDept dept = super.getKonkaDeptByCustomerId(Long.valueOf(user.getCust_id()));

					entity.setSubcomp_id(dept.getDept_id());
					entity.setIs_del(0);
					entity.setTrans_index(super.generateJxcTransIndex("YC"));
					entity.setState(1);// 为方便计算库存，此次默认是已经确认的
					entity.setConfirm_date(new Date());
					entityList.add(entity);
					try {
						// 调入仓库加，调出的减掉
						super.getFacade()
								.getJxcFifoStocksService()
								.stock_in(entity.getIn_store_id(), entity.getGoods_name(), entity.getIn_price(),
										entity.getIn_num(), entity.getAdd_date(), 90);
						// 移仓
						super.getFacade()
								.getJxcFifoStocksService()
								.stock_out(entity.getOut_store_id(), entity.getGoods_name(), entity.getOut_price(),
										entity.getIn_num(), entity.getAdd_date(), 580);
					} catch (Exception e) {
						e.printStackTrace();
					}

				}
			}
			if (entityList != null && entityList.size() > 0) {
				super.getFacade().getJxcOutInDetailService().createJxcOutInDetailList(entityList);
				super.resetToken(request);
				super.saveMessage(request, "entity.inserted");

			}
		} else {
			super.renderJavaScript(response, "alert('请至少添加一条记录！');history.back();");
			return null;
		}

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&type=" + 10);
		pathBuffer.append("&");
		// pathBuffer.append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	// 保存移仓
	public ActionForward save10_old(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (!super.isTokenValid(request, true)) {
			super.renderJavaScript(response, "alert('" + super.getMessage(request, "errors.token")
					+ "');history.back();");
			return null;
		}
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");

		PeProdUser user = (PeProdUser) getSessionAttribute(request, Constants.CUSTOMER_USER_INFO);
		if (null == user) {
			super.renderJavaScript(response, "alert('出现位置错误，请联系管理员！');history.back();");
			return null;
		}

		JxcOutInDetail entity = new JxcOutInDetail();
		super.copyProperties(entity, form);
		entity.setAdd_date(new Date());
		entity.setTrans_type(10);// 移仓(移出)
		if (user.getId() != null && user.getCust_id() != null) {
			KonkaR3Shop r3shop = new KonkaR3Shop();
			r3shop.setId(user.getCust_id());
			r3shop = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(r3shop);
			if (null != r3shop && null != r3shop.getR3_code()) {
				entity.setOut_r3_code(r3shop.getR3_code());
				entity.setIn_r3_code(r3shop.getR3_code());
			}
			entity.setAdd_user_id(user.getId());

			if (entity.getOut_goods_id() != null) {
				entity.setIn_goods_id(entity.getOut_goods_id());
				JBaseGoods goods = new JBaseGoods();
				goods.setC_id(user.getCust_id());
				goods.setGoods_id(entity.getOut_goods_id());
				goods = super.getFacade().getJBaseGoodsService().getJBaseGoods(goods);
				if (null != goods && null != goods.getGoods_name()) {
					entity.setGoods_name(goods.getGoods_name());
				}
			}
		}

		// 先验证在移入仓库中是否已经初始化这个商品
		JBaseGoodsInitStock initStock1 = new JBaseGoodsInitStock();
		initStock1.setC_id(user.getCust_id());
		initStock1.setGoods_id(entity.getOut_goods_id());
		initStock1.setStore_id(entity.getIn_store_id());
		List<JBaseGoodsInitStock> initStockList1 = super.getFacade().getJBaseGoodsInitStockService()
				.getJBaseGoodsInitStockList(initStock1);
		if (initStockList1 == null || initStockList1.size() < 1) {
			super.renderJavaScript(response,
					"alert('请先在仓库中初始化库存！');window.location.href='JBaseGoodsInitStock.do?method=add&mod_id=" + mod_id
							+ "&returnUrl=JxcOutInDetail.do?type=10';");
			return null;
		}

		// 判断商品库存是否超出原仓库的库存
		if (entity.getGoods_name() != null) {
			entity.getIn_num();
			int stocks = Integer.parseInt(super.getKhStocks(entity.getOut_r3_code(), entity.getGoods_name(),
					entity.getOut_store_id()).toString());
			if (entity.getIn_num() > stocks) {
				super.renderJavaScript(response, "alert('数量不能大于移出仓库中的库存，！');history.back();");
				return null;
			}
		}

		// 其次再获取商品的价格等信息
		JBaseGoodsInitStock initStock = new JBaseGoodsInitStock();
		initStock.setC_id(user.getCust_id());
		initStock.setGoods_id(entity.getOut_goods_id());
		initStock.setStore_id(entity.getOut_store_id());
		List<JBaseGoodsInitStock> initStockList = super.getFacade().getJBaseGoodsInitStockService()
				.getJBaseGoodsInitStockList(initStock);
		if (initStockList != null && initStockList.size() > 0) {
			initStock = initStockList.get(0);
			entity.setIn_price(initStock.getBuy_price());
			entity.setOut_price(initStock.getBuy_price());
			BigDecimal num = new BigDecimal(entity.getIn_num());
			entity.setIn_money(num.multiply(initStock.getBuy_price()));
			entity.setOut_money(num.multiply(initStock.getBuy_price()));
		} else {
			super.renderJavaScript(response, "alert('请先在仓库中初始化库存！');history.back();");
			return null;
		}
		KonkaDept dept = super.getKonkaDeptByCustomerId(Long.valueOf(user.getCust_id()));

		entity.setSubcomp_id(dept.getDept_id());
		entity.setIs_del(0);
		entity.setTrans_index(super.generateJxcTransIndex("YC"));
		entity.setState(1);// 为方便计算库存，此次默认是已经确认的
		entity.setConfirm_date(new Date());

		super.getFacade().getJxcOutInDetailService().createJxcOutInDetail(entity);
		super.saveMessage(request, "entity.inserted");

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&type=" + 10);
		pathBuffer.append("&");
		// pathBuffer.append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	// 保存调拨（调入调出）
	public ActionForward save0and1(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (!super.isTokenValid(request, false)) {
			super.renderJavaScript(response, "alert('" + super.getMessage(request, "errors.token")
					+ "');history.back();");
			return null;
		}
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String type = (String) dynaBean.get("type");
		String out_date = (String) dynaBean.get("out_date");
		Date _date = DateUtils.parseDate(out_date, new String[] { "yyyy-MM-dd" });

		PeProdUser user = (PeProdUser) getSessionAttribute(request, Constants.CUSTOMER_USER_INFO);
		if (null == user) {
			super.renderJavaScript(response, "alert('出现未知错误，请联系管理员！');history.back();");
			return null;
		}
		if (StringUtils.isEmpty(type)) {
			super.renderJavaScript(response, "alert('出现未知错误，请联系管理员！');history.back();");
			return null;
		}
		KonkaDept dept = super.getKonkaDeptByCustomerId(Long.valueOf(user.getCust_id()));
		if ("0".equals(type)) {// 调入
			String[] out_r3_codes = request.getParameterValues("out_r3_code");
			String[] out_goods_ids = request.getParameterValues("out_goods_id");
			String[] in_store_ids = request.getParameterValues("in_store_id");
			String[] in_nums = request.getParameterValues("in_num");
			String[] memos = request.getParameterValues("memo");

			if (out_r3_codes != null) {
				KonkaR3Shop r3shop = new KonkaR3Shop();
				r3shop.setId(user.getCust_id());
				r3shop = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(r3shop);
				List<JxcOutInDetail> entityList = new ArrayList<JxcOutInDetail>();
				for (int i = 0; i < out_r3_codes.length; i++) {
					String out_r3_code = out_r3_codes[i];
					String out_goods_id = out_goods_ids[i];
					String in_store_id = in_store_ids[i];
					String in_num = in_nums[i];
					String memo = memos[i];
					logger.info("out_r3_code=" + out_r3_code + "    out_goods_id=" + out_goods_id + "     in_store_id="
							+ in_store_id + "    in_num=" + in_num + "    memo=" + memo);
					if (StringUtils.isNotBlank(out_r3_code) && StringUtils.isNotBlank(out_goods_id)
							&& StringUtils.isNotBlank(out_goods_id) && StringUtils.isNotBlank(in_num)) {
						JxcOutInDetail entity = new JxcOutInDetail();
						entity.setAdd_user_id(user.getId());
						entity.setAdd_date(new Date());
						entity.setTrans_type(Integer.parseInt(type));// 调拨
						entity.setIn_r3_code(r3shop.getR3_code());
						entity.setSubcomp_id(dept.getDept_id());
						entity.setIs_del(0);
						entity.setState(0);
						entity.setOut_date(_date);
						entity.setIn_num(Integer.parseInt(in_num));
						entity.setMemo(memo);
						entity.setOut_r3_code(out_r3_code);
						entity.setOut_goods_id(Long.parseLong(out_goods_id));
						entity.setIn_store_id(Long.parseLong(in_store_id));

						KonkaR3Shop r3shop1 = new KonkaR3Shop();
						r3shop1.setR3_code(out_r3_code);
						r3shop1 = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(r3shop1);

						// 获取商品名称
						JBaseGoods goods = new JBaseGoods();
						goods.setC_id(r3shop1.getId());
						goods.setGoods_id(Long.parseLong(out_goods_id));
						goods.setGoods_stutes(0);
						goods = super.getFacade().getJBaseGoodsService().getJBaseGoods(goods);
						if (null == goods || null == goods.getGoods_name()) {
							super.renderJavaScript(response, "alert('出现未知错误，请联系管理员！');history.back();");
							return null;
						}
						entity.setGoods_name(goods.getGoods_name());
						entity.setTrans_index(super.generateJxcTransIndex("DR"));
						// 判断客户自己中是否有商品数据
						JBaseGoods goods1 = new JBaseGoods();
						goods1.setC_id(user.getCust_id());
						goods1.setGoods_name(goods.getGoods_name());
						goods1.setGoods_stutes(0);
						goods1 = super.getFacade().getJBaseGoodsService().getJBaseGoods(goods1);
						if (null != goods1 && null != goods1.getGoods_id()) {
							JBaseGoodsInitStock initStock = new JBaseGoodsInitStock();
							initStock.setC_id(user.getCust_id());
							initStock.setGoods_id(goods1.getGoods_id());
							initStock.setStore_id(Long.parseLong(in_store_id));
							List<JBaseGoodsInitStock> initStockList = super.getFacade().getJBaseGoodsInitStockService()
									.getJBaseGoodsInitStockList(initStock);
							if (initStockList != null && initStockList.size() > 0) {
								entity.setIn_goods_id(goods1.getGoods_id());
							} else {
								super.renderJavaScript(response,
										"alert('请先在仓库中初始化库存！');window.location.href='JBaseGoodsInitStock.do?method=add&mod_id="
												+ mod_id + "&returnUrl=JxcOutInDetail.do?type=0';");
								return null;
							}
						} else {
							super.renderJavaScript(response, "alert('请先添加商品数据！');history.back();");
							return null;
						}
						entityList.add(entity);
					}
				}
				super.getFacade().getJxcOutInDetailService().createJxcOutInDetailList(entityList);
				super.resetToken(request);
				super.saveMessage(request, "entity.inserted");
			} else {
				super.renderJavaScript(response, "alert('请至少添加一条数据！');history.back();");
				return null;
			}

		} else if ("1".equals(type)) {// 调出
			String[] out_store_ids = request.getParameterValues("out_store_id");
			String[] out_goods_ids = request.getParameterValues("out_goods_id");
			String[] in_r3_codes = request.getParameterValues("in_r3_code");
			String[] in_nums = request.getParameterValues("in_num");
			String[] memos = request.getParameterValues("memo");

			if (null != in_r3_codes) {
				KonkaR3Shop r3shop = new KonkaR3Shop();
				r3shop.setId(user.getCust_id());
				r3shop = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(r3shop);
				List<JxcOutInDetail> entityList = new ArrayList<JxcOutInDetail>();
				for (int i = 0; i < in_r3_codes.length; i++) {
					String out_store_id = out_store_ids[i];
					String out_goods_id = out_goods_ids[i];
					String in_r3_code = in_r3_codes[i];
					String in_num = in_nums[i];
					String memo = memos[i];
					logger.info("out_store_id=" + out_store_id + "    out_goods_id=" + out_goods_id
							+ "     in_r3_code=" + in_r3_code + "    in_num=" + in_num + "    memo=" + memo);
					if (StringUtils.isNotBlank(out_store_id) && StringUtils.isNotBlank(out_goods_id)
							&& StringUtils.isNotBlank(in_r3_code) && StringUtils.isNotBlank(in_num)) {
						JxcOutInDetail entity = new JxcOutInDetail();
						entity.setAdd_user_id(user.getId());
						entity.setAdd_date(new Date());
						entity.setTrans_type(Integer.parseInt(type));// 调拨
						entity.setOut_r3_code(r3shop.getR3_code());
						entity.setSubcomp_id(dept.getDept_id());
						entity.setIs_del(0);
						entity.setState(0);
						entity.setOut_date(_date);
						entity.setIn_num(Integer.parseInt(in_num));
						entity.setMemo(memo);
						entity.setOut_store_id(Long.parseLong(out_store_id));
						entity.setOut_goods_id(Long.parseLong(out_goods_id));
						entity.setIn_r3_code(in_r3_code);

						JBaseGoods goods = new JBaseGoods();
						goods.setC_id(user.getCust_id());
						goods.setGoods_id(Long.parseLong(out_goods_id));
						goods.setGoods_stutes(0);
						goods = super.getFacade().getJBaseGoodsService().getJBaseGoods(goods);
						if (null != goods && null != goods.getGoods_name()) {
							entity.setGoods_name(goods.getGoods_name());
						}
						entity.setTrans_index(super.generateJxcTransIndex("DC"));

						JBaseGoodsInitStock initStock = new JBaseGoodsInitStock();
						initStock.setC_id(user.getCust_id());
						initStock.setGoods_id(Long.parseLong(out_goods_id));
						initStock.setStore_id(Long.parseLong(out_store_id));
						List<JBaseGoodsInitStock> initStockList = super.getFacade().getJBaseGoodsInitStockService()
								.getJBaseGoodsInitStockList(initStock);
						if (initStockList != null && initStockList.size() > 0) {
							initStock = initStockList.get(0);
							entity.setIn_price(initStock.getBuy_price());
							entity.setOut_price(initStock.getBuy_price());
							BigDecimal num = new BigDecimal(in_num);
							entity.setIn_money(num.multiply(initStock.getBuy_price()));
							entity.setOut_money(num.multiply(initStock.getBuy_price()));
						} else {
							super.renderJavaScript(response,
									"alert('请先在仓库中初始化库存！');window.location.href='JBaseGoodsInitStock.do?method=add&mod_id="
											+ mod_id + "&returnUrl=JxcOutInDetail.do?type=1';");
							return null;
						}
						entityList.add(entity);
					}
				}
				super.getFacade().getJxcOutInDetailService().createJxcOutInDetailList(entityList);
				super.resetToken(request);
				super.saveMessage(request, "entity.inserted");
			} else {
				super.renderJavaScript(response, "alert('请至少添加一条数据！');history.back();");
				return null;
			}
		}

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&type=" + type);
		pathBuffer.append("&");
		// pathBuffer.append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	// 保存调拨确认（调入调出）
	public ActionForward saveConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (!super.isTokenValid(request, true)) {
			super.renderJavaScript(response, "alert('" + super.getMessage(request, "errors.token")
					+ "');history.back();");
			return null;
		}
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String type = (String) dynaBean.get("type");
		String in_store_id = (String) dynaBean.get("in_store_id");// 调入确认
		String out_store_id = (String) dynaBean.get("out_store_id");// 调出确认
		String state = (String) dynaBean.get("state");// 确认情况
		String id = (String) dynaBean.get("id");// 确认情况

		PeProdUser user = (PeProdUser) getSessionAttribute(request, Constants.CUSTOMER_USER_INFO);
		if (null == user) {
			super.renderJavaScript(response, "alert('数据丢失，请联系管理员！');history.back();");
			return null;
		}
		if (StringUtils.isEmpty(type)) {
			super.renderJavaScript(response, "alert('数据丢失，请联系管理员！');history.back();");
			return null;
		}
		if (StringUtils.isEmpty(id)) {
			super.renderJavaScript(response, "alert('数据丢失，请联系管理员！');history.back();");
			return null;
		}

		JxcOutInDetail detail = new JxcOutInDetail();
		detail.setId(Long.parseLong(id));
		detail = super.getFacade().getJxcOutInDetailService().getJxcOutInDetail(detail);
		if (null == detail) {
			super.renderJavaScript(response, "alert('数据丢失，请联系管理员！');history.back();");
			return null;
		}

		JxcOutInDetail entity = new JxcOutInDetail();
		if (StringUtils.isNotBlank(state)) {
			if ("1".equals(state)) {// 确认
				if ("0".equals(type)) {// 调出确认
					// 判断客户自己中是否已经初始化库存
					JBaseGoods goods = new JBaseGoods();
					goods.setC_id(user.getCust_id());
					goods.setGoods_name(detail.getGoods_name());
					goods.getRow().setCount(1);
					goods = super.getFacade().getJBaseGoodsService().getJBaseGoods(goods);
					if (null != goods && null != goods.getGoods_id()) {// 先判断商品数据中是否存在
						JBaseGoodsInitStock initStock = new JBaseGoodsInitStock();
						initStock.setC_id(user.getCust_id());
						initStock.setGoods_id(goods.getGoods_id());
						initStock.setStore_id(Long.parseLong(out_store_id));
						List<JBaseGoodsInitStock> initStockList = super.getFacade().getJBaseGoodsInitStockService()
								.getJBaseGoodsInitStockList(initStock);
						if (initStockList != null && initStockList.size() > 0) {// 其次判断初始化库存中是否存在
							JBaseGoodsInitStock stock = initStockList.get(0);
							entity.setOut_price(stock.getBuy_price());
							entity.setOut_money(stock.getBuy_price().multiply(new BigDecimal(detail.getIn_num())));
							entity.setIn_price(stock.getBuy_price());
							entity.setIn_money(stock.getBuy_price().multiply(new BigDecimal(detail.getIn_num())));
							entity.setOut_store_id(Long.parseLong(out_store_id));
							entity.setOut_goods_id(stock.getGoods_id());
							long now_stock = super.getKhStocksWithMoney(detail.getOut_r3_code(),
									detail.getGoods_name(), Long.parseLong(out_store_id));// 获取客户自己的库存
							if (now_stock < detail.getIn_num()) {// 判断当前库存是否小于调入数量
								super.renderJavaScript(response, "alert('库存不足，请增加库存！');history.back();");
								return null;
							}

						} else {
							super.renderJavaScript(response, "alert('请先在仓库中初始化库存！');history.back();");
							return null;
						}
					} else {
						super.renderJavaScript(response, "alert('请先添加商品数据！');history.back();");
						return null;
					}
				} else if ("1".equals(type)) {// 调入确认
					// 判断客户自己中是否已经初始化库存
					JBaseGoods goods = new JBaseGoods();
					goods.setC_id(user.getCust_id());
					goods.setGoods_name(detail.getGoods_name());
					goods.getRow().setCount(1);
					goods = super.getFacade().getJBaseGoodsService().getJBaseGoods(goods);
					if (null != goods && null != goods.getGoods_id()) {// 先判断商品数据中是否存在
						JBaseGoodsInitStock initStock = new JBaseGoodsInitStock();
						initStock.setC_id(user.getCust_id());
						initStock.setGoods_id(goods.getGoods_id());
						initStock.setStore_id(Long.parseLong(in_store_id));
						List<JBaseGoodsInitStock> initStockList = super.getFacade().getJBaseGoodsInitStockService()
								.getJBaseGoodsInitStockList(initStock);
						if (initStockList != null && initStockList.size() > 0) {// 其次判断初始化库存中是否存在
							JBaseGoodsInitStock stock = initStockList.get(0);
							// entity.setIn_price(stock.getBuy_price());
							// entity.setIn_money(stock.getBuy_price().multiply(new
							// BigDecimal(detail.getIn_num())));
							entity.setIn_store_id(Long.parseLong(in_store_id));
							entity.setIn_goods_id(stock.getGoods_id());
							long now_stock = super.getKhStocksWithMoney(detail.getOut_r3_code(),
									detail.getGoods_name(), detail.getOut_store_id());// 获取调出客户的库存
							if (now_stock < detail.getIn_num()) {// 判断当前库存是否小于调入数量
								super.renderJavaScript(response, "alert('库存不足，请联系调出客户！');history.back();");
								return null;
							}
						} else {
							super.renderJavaScript(response, "alert('请先在仓库中初始化库存！');history.back();");
							return null;
						}
					} else {
						super.renderJavaScript(response, "alert('请先添加商品数据！');history.back();");
						return null;
					}

				}
			}
			entity.setState(Integer.parseInt(state));
		} else {
			super.renderJavaScript(response, "alert('数据丢失，请联系管理员！');history.back();");
			return null;
		}

		entity.setId(Long.parseLong(id));
		entity.setConfirm_date(new Date());
		super.getFacade().getJxcOutInDetailService().modifyJxcOutInDetail(entity);

		JxcOutInDetail entity_new = new JxcOutInDetail();
		entity_new.setId(Long.parseLong(id));
		entity = super.getFacade().getJxcOutInDetailService().getJxcOutInDetail(entity_new);
		if (null != entity.getState() && entity.getState() == 1) {
			if (entity.getTrans_type() == 0 || entity.getTrans_type() == 1) {

				// 调入客户入仓
				super.getFacade()
						.getJxcFifoStocksService()
						.stock_in(entity.getIn_store_id(), entity.getGoods_name(), entity.getIn_price(),
								entity.getIn_num(), entity.getAdd_date(), 80);
				// 调出客户出仓
				super.getFacade()
						.getJxcFifoStocksService()
						.stock_out(entity.getOut_store_id(), entity.getGoods_name(), entity.getOut_price(),
								entity.getIn_num(), entity.getAdd_date(), 570);

			} else {
				// 移仓
				super.getFacade()
						.getJxcFifoStocksService()
						.stock_in(entity.getIn_store_id(), detail.getGoods_name(), entity.getIn_price(),
								entity.getIn_num(), entity.getAdd_date(), 90);
				// 移仓
				super.getFacade()
						.getJxcFifoStocksService()
						.stock_out(entity.getOut_store_id(), detail.getOut_goods_id(), entity.getOut_price(),
								entity.getIn_num(), entity.getAdd_date(), 580);

			}
		}

		super.saveMessage(request, "konka.jxcoutindetail.confirm.success");

		return new ActionForward("/../customer/manager/JxcOutInDetail.do?method=confirmList&type=" + type + "&mod_id="
				+ mod_id);
	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward ajaxGetGoodsList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String r3_code = (String) dynaBean.get("r3_code");
		StringBuffer sb = new StringBuffer("{");
		sb = sb.append("\"list\":[");
		if (StringUtils.isBlank(r3_code)) {
			sb = sb.append("]}");
			super.renderJson(response, sb.toString());
			return null;
		}

		KonkaR3Shop r3shop = new KonkaR3Shop();
		r3shop.setR3_code(r3_code);
		r3shop = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(r3shop);

		// 获取商品名称
		JBaseGoods goods = new JBaseGoods();
		goods.setC_id(r3shop.getId());
		goods.setGoods_stutes(0);

		List<JBaseGoods> goodsList = super.getFacade().getJBaseGoodsService().getJBaseGoodsList(goods);

		if (null != goodsList && goodsList.size() > 0) {
			for (JBaseGoods temp : goodsList) {
				sb = sb.append("{");
				sb = sb.append("\"goods_id\":\"").append(temp.getGoods_id()).append("\",");
				sb = sb.append("\"goods_name\":\"").append(temp.getGoods_name()).append("\",");
				sb = sb.append("\"is_konka\":\"").append(temp.getIs_konka()).append("\"");
				sb = sb.append("},");
			}
		}
		String sb_str = StringUtils.removeEnd(sb.toString(), ",") + "]}";
		logger.info("sb_str {}", sb_str);
		super.renderJson(response, sb_str);
		return null;
	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author Xiao, GuoJian
	 * @desc 根据客户获取客户的所有仓库
	 */
	public ActionForward ajaxGetStoreList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String r3_code = (String) dynaBean.get("r3_code");
		StringBuffer sb = new StringBuffer("{");
		sb = sb.append("\"list\":[");
		if (StringUtils.isBlank(r3_code)) {
			sb = sb.append("]}");
			super.renderJson(response, sb.toString());
			return null;
		}

		KonkaR3Shop r3shop = new KonkaR3Shop();
		r3shop.setR3_code(r3_code);
		r3shop = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(r3shop);

		// 获取仓库名称
		JBaseStore jBaseStore = new JBaseStore();
		jBaseStore.getMap().put("order_by_is_dft_buy_store_desc", true);
		jBaseStore.setC_id(r3shop.getId());
		jBaseStore.setIs_del(0);
		List<JBaseStore> jBaseStoreList = super.getFacade().getJBaseStoreService().getJBaseStoreList(jBaseStore);

		if (null != jBaseStoreList && jBaseStoreList.size() > 0) {
			for (JBaseStore temp : jBaseStoreList) {
				sb = sb.append("{");
				sb = sb.append("\"store_id\":\"").append(temp.getStore_id()).append("\",");
				sb = sb.append("\"store_name\":\"").append(temp.getStore_name()).append("\"");
				sb = sb.append("},");
			}
		}
		String sb_str = StringUtils.removeEnd(sb.toString(), ",") + "]}";
		logger.info("sb_str {}", sb_str);
		super.renderJson(response, sb_str);
		return null;
	}

	// 根据store_id获得该仓库中的商品
	public ActionForward ajaxGetGoodsListForStoreId(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String store_id = (String) dynaBean.get("store_id");
		StringBuffer sb = new StringBuffer("{");
		sb = sb.append("\"list\":[");
		if (StringUtils.isBlank(store_id)) {
			sb = sb.append("]}");
			super.renderJson(response, sb.toString());
			return null;
		}

		// 获取商品名称
		JBaseGoodsInitStock initStock = new JBaseGoodsInitStock();
		initStock.setStore_id(Long.parseLong(store_id));
		initStock.setInit_state(0);
		List<JBaseGoodsInitStock> initStockList = super.getFacade().getJBaseGoodsInitStockService()
				.getJBaseGoodsInitStockList(initStock);

		if (null != initStockList && initStockList.size() > 0) {
			for (JBaseGoodsInitStock temp : initStockList) {
				sb = sb.append("{");
				sb = sb.append("\"goods_id\":\"").append(temp.getGoods_id()).append("\",");
				sb = sb.append("\"goods_name\":\"").append(temp.getMap().get("goods_name")).append("\"");
				sb = sb.append("},");
			}
		}
		String sb_str = StringUtils.removeEnd(sb.toString(), ",") + "]}";
		logger.info("sb_str {}", sb_str);
		super.renderJson(response, sb_str);
		return null;
	}
}
