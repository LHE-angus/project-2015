package com.ebiz.mmt.web.struts.customer;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.GiftInfo;
import com.ebiz.mmt.domain.JBaseGoods;
import com.ebiz.mmt.domain.JBasePartner;
import com.ebiz.mmt.domain.JBaseStore;
import com.ebiz.mmt.domain.JBaseType;
import com.ebiz.mmt.domain.JBill;
import com.ebiz.mmt.domain.JBillDetails;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.KonkaR3Store;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.ssi.util.EncryptUtils;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * 
 * @author Wu,ShangLong
 * @version 2013-6-8
 */
public class JBillAction extends BaseClientJxcAction {

	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String bill_type = (String) dynaBean.get("bill_type");
		String to_default_page = (String) dynaBean.get("to_default_page");
		PeProdUser user = (PeProdUser) getSessionAttribute(request, Constants.CUSTOMER_USER_INFO);

		boolean isFanLiZmd = super.isFanLiZmd(user);
		request.setAttribute("isFanLiZmd", isFanLiZmd);
		if (StringUtils.isBlank(to_default_page) && isFanLiZmd && "20".equals(bill_type)) {
			return new ActionForward("/../customer/manager/KonkaXxZmdAddSalesOrder.do?method=add&mod_id=" + mod_id,
					true);
		}

		return this.add(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String bill_type = (String) dynaBean.get("bill_type");  //单据类型
		Pager pager = (Pager) dynaBean.get("pager");
		String bill_sn_like = (String) dynaBean.get("bill_sn_like");  //销售单号
		String opr_date_gt = (String) dynaBean.get("opr_date_gt");  //开始时间
		String opr_date_lt = (String) dynaBean.get("opr_date_lt");  //结束时间
		String p_name_like = (String) dynaBean.get("p_name_like");  //销售单位
		String p_id_like = (String) dynaBean.get("p_id_like");  //销售单位编码
		String bill_state = (String) dynaBean.get("bill_state");  //财务确认状态
		String customer_name_like = (String) dynaBean.get("customer_name_like");  //顾客姓名
		String mobile_like = (String) dynaBean.get("mobile_like");  //顾客电话
		
		String view_type = (String) dynaBean.get("view_type");
		String mod_id = (String) dynaBean.get("mod_id");
		String export = (String) dynaBean.get("export");

		String to_default_page = (String) dynaBean.get("to_default_page");
		PeProdUser user = (PeProdUser) getSessionAttribute(request, Constants.CUSTOMER_USER_INFO);

		boolean isFanLiZmd = super.isFanLiZmd(user);
		request.setAttribute("isFanLiZmd", isFanLiZmd);
		if (StringUtils.isBlank(to_default_page) && isFanLiZmd && "20".equals(bill_type)) {
			return new ActionForward("/../customer/manager/KonkaXxZmdSearchSalesOrder.do?mod_id=" + mod_id, true);
		}

		view_type = null == view_type ? "1" : view_type;

		dynaBean.set("view_type", view_type);

		JBill entity = new JBill();
		entity.setC_id(user.getCust_id());
		if(StringUtils.isNotBlank(bill_type)){
			entity.setBill_type(Integer.valueOf(bill_type));
		}
		if (StringUtils.isNotBlank(bill_sn_like)) {
			entity.getMap().put("bill_sn_like", bill_sn_like);
		}
		if (StringUtils.isNotBlank(opr_date_gt)) {
			entity.getMap().put("opr_date_gt", opr_date_gt);
		}
		if (StringUtils.isNotBlank(opr_date_lt)) {
			entity.getMap().put("opr_date_lt", opr_date_lt);
		}
		if (StringUtils.isNotBlank(p_name_like)) {
			entity.getMap().put("p_name_like", p_name_like);
		}
		if (StringUtils.isNotBlank(p_id_like)) {
			entity.getMap().put("p_id_like", p_id_like);
		}
		if (StringUtils.isNotBlank(bill_state)) {
			entity.setBill_state(Integer.parseInt(bill_state));
		}
		if (StringUtils.isNotBlank(customer_name_like)) {
			entity.getMap().put("customer_name_like", customer_name_like);
		}
		if (StringUtils.isNotBlank(mobile_like)) {
			entity.getMap().put("mobile_like", mobile_like);
		}
		entity.getMap().put("order_by_opt_desc", true);
		
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat exformat = new SimpleDateFormat("yyyyMMddhhmmss");

		if (StringUtils.isBlank(opr_date_gt) && StringUtils.isBlank(opr_date_lt)) {
			opr_date_lt = dateformat.format(calendar.getTime());// 结束时间
			calendar.set(Calendar.DAY_OF_MONTH, calendar
					.getActualMinimum(Calendar.DAY_OF_MONTH));// 初始化为当月1号
			opr_date_gt = dateformat.format(calendar.getTime());// 开始时间
			entity.getMap().put("opr_date_gt", opr_date_gt);
			entity.getMap().put("opr_date_lt", opr_date_lt);
		}

		dynaBean.set("opr_date_gt", opr_date_gt);
		dynaBean.set("opr_date_lt", opr_date_lt);

		Long recordCount = super.getFacade().getJBillService().getSaleDataForkhCount(entity);
		pager.init(recordCount, 10, pager.getRequestPage());
		entity.getRow().setCount(pager.getRowCount());
		entity.getRow().setFirst(pager.getFirstRow());

		//导出
		if(StringUtils.isNotBlank(export)){
			String timestr = exformat.format(calendar.getTime());
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename=" + EncryptUtils.encodingFileName("零售查询-"+timestr)
					+ ".xls");
			entity.getRow().setCount(recordCount.intValue());
			List<HashMap> allList = super.getFacade().getJBillService().getSaleDataForkhList(entity);

			request.setAttribute("allList", allList);
			return new ActionForward("/../customer/JBill/listForReport.jsp");
		}
		

		List<HashMap> entityList = super.getFacade().getJBillService().getSaleDataForkhList(entity);
		request.setAttribute("entityList", entityList);

		GiftInfo gift = new GiftInfo();
		gift.setStatus(0);
		List<GiftInfo> giftInfoList = super.getFacade().getGiftInfoService().getGiftInfoResultForListWithCate(gift);
		request.setAttribute("giftInfoList", giftInfoList);

		// 初始化页面标签
		request.setAttribute("bill_sn_title", super.getMessage(request, "konka.jbill.sn.title." + bill_type));
		request.setAttribute("price_title", super.getMessage(request, "konka.jbill.price.title." + bill_type));
		request.setAttribute("goods_money_title",
				super.getMessage(request, "konka.jbill.goods.money.title." + bill_type));
		request.setAttribute("rec_money_title", super.getMessage(request, "konka.jbill.rec.money.title." + bill_type));
		request.setAttribute("money_title", super.getMessage(request, "konka.jbill.money.title." + bill_type));

		return mapping.findForward("list");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String bill_type = (String) dynaBean.get("bill_type");
		String partner_id = (String) dynaBean.get("partner_id");
		PeProdUser user = (PeProdUser) getSessionAttribute(request, Constants.CUSTOMER_USER_INFO);

		if (!GenericValidator.isLong(bill_type)) {
			super.saveError(request, "errors.param");
			return mapping.findForward("list");
		}

		// 单据编号 采购:CG, 采购退货:CGTH, 销售:XS, 销售退货:XSTH, 盘亏:PK, 盘盈:PY
		String bill_sn = "";
		List<JBaseStore> jBaseStores = new ArrayList<JBaseStore>();
		switch (Integer.valueOf(bill_type)) {
		case 10:
			bill_sn = super.getJBillSn("CG");
			jBaseStores = super.getJBaseStores(request, 0);
			dynaBean.set("history_list_mod_id", 105040400);
			break;
		case 11:
			bill_sn = super.getJBillSn("CGTH");
			jBaseStores = super.getJBaseStores(request, 0);
			dynaBean.set("history_list_mod_id", 105040800);
			break;
		case 20:
			bill_sn = super.getJBillSn("XS");
			jBaseStores = super.getJBaseStores(request, 1);
			dynaBean.set("history_list_mod_id", 110040600);
			break;
		case 21:
			bill_sn = super.getJBillSn("XSTH");
			jBaseStores = super.getJBaseStores(request, 1);
			dynaBean.set("history_list_mod_id", 110040800);
			break;
		}
		dynaBean.set("bill_sn", bill_sn);

		Date today = new Date();
		dynaBean.set("opr_date", today);

		// 默认 折扣为100%
		dynaBean.set("discount", 0);
		
		//客户id
		dynaBean.set("c_id", user.getCust_id());

		//创建人
		request.setAttribute("add_user_name",user.getReal_name());

		request.setAttribute("jBaseStores", jBaseStores);

		//根据分类查询商品类型
		if("10".equals(bill_type) || "11".equals(bill_type)){
			request.setAttribute("jBaseTypes", super.getJBaseTypes(10001L, user.getCust_id(),"康佳电视")); // 商品类型
		}else{
			request.setAttribute("jBaseTypes", super.getJBaseTypes(10001L, user.getCust_id())); // 商品类型
		}

		if (bill_type.equals("10") || bill_type.equals("11")) {// 客户进货，退货
			request.setAttribute("jBasePartners", super.getJBasePartners("0", null, user.getCust_id()));
		} else if (bill_type.equals("20")) {// 客户零售
			request.setAttribute("jBasePartners", super.getJBasePartners("1", null, user.getCust_id()));
			
			//查询当前客户下的所有有效门店    add by Liang Houen on 2015-06-23
			KonkaR3Shop shop = new KonkaR3Shop();
			shop.setId(user.getCust_id());
			shop = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(shop);
			
			List<HashMap> plist = new ArrayList<HashMap>();
			HashMap map = new HashMap();
			map.put("ID", shop.getId());
			map.put("NAME", shop.getCustomer_name());
			plist.add(map);
			
			List<HashMap> slist = super.getFacade().getKonkaR3StoreService().getStoreListByCID(user.getCust_id());
			if(null!=slist){
				plist.addAll(slist);
			}
			request.setAttribute("partnerList",plist);
			
		} else if (bill_type.equals("11")) {//进货退货
			JBasePartner jp = new JBasePartner();
			jp.getMap().put("c_id", user.getCust_id());
			jp.getMap().put("bill_type", "10");
			jp.getMap().put("partner_type_value", "0");
			request.setAttribute("jBasePartners", super.getFacade().getJBasePartnerService()
					.getJBasePartnerForBillList(jp));
		} else if (bill_type.equals("21")) {// 零售退货
			JBasePartner jp = new JBasePartner();
			jp.getMap().put("c_id", user.getCust_id());
			jp.getMap().put("bill_type", "20");
			jp.getMap().put("partner_type_value", "1");
			request.setAttribute("jBasePartners", super.getFacade().getJBasePartnerService()
					.getJBasePartnerForBillList(jp));
		}

		// 初始化页面标签
		request.setAttribute("j_bill_history_list_title",
				super.getMessage(request, "konka.jbill.history.title." + bill_type));
		request.setAttribute("bill_sn_title", super.getMessage(request, "konka.jbill.sn.title." + bill_type));
		request.setAttribute("store_title", super.getMessage(request, "konka.jbill.store.title." + bill_type));
		request.setAttribute("price_title", super.getMessage(request, "konka.jbill.price.title." + bill_type));
		request.setAttribute("goods_money_title",
				super.getMessage(request, "konka.jbill.goods.money.title." + bill_type));
		request.setAttribute("rec_money_title", super.getMessage(request, "konka.jbill.rec.money.title." + bill_type));
		request.setAttribute("money_title", super.getMessage(request, "konka.jbill.money.title." + bill_type));

		dynaBean.set("partner_id", partner_id);


		if ("21".equals(bill_type)) { // 客户端零售退货
			return new ActionForward("/../customer/JBill/xsth_form.jsp");
		}

		if ("20".equals(bill_type)) { // 客户端零售
			GiftInfo gift = new GiftInfo();
			gift.setStatus(0);
			List<GiftInfo> giftInfoList = super.getFacade().getGiftInfoService().getGiftInfoResultForListWithCate(gift);
			request.setAttribute("giftInfoList", giftInfoList);
			return new ActionForward("/../customer/JBill/form_20.jsp");
		}

		if("11".equals(bill_type)){   //其他产品退货登记
			return new ActionForward("/../customer/JBill/other_th_form.jsp");
		}

		return mapping.findForward("input");
	}

	//零售订单修改
	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String bill_type = (String) dynaBean.get("bill_type");
		String bill_id = (String) dynaBean.get("bill_id");
		PeProdUser user = (PeProdUser) getSessionAttribute(request, Constants.CUSTOMER_USER_INFO);

		if (!GenericValidator.isLong(bill_id) || !GenericValidator.isLong(bill_type)) {
			super.saveError(request, "errors.param");
			return mapping.findForward("list");
		}
		List<JBaseStore> jBaseStores = new ArrayList<JBaseStore>();
		switch (Integer.valueOf(bill_type)) {
		case 20:
			jBaseStores = super.getJBaseStores(request, 1);
			dynaBean.set("history_list_mod_id", 110040400);
			break;
		case 21:
			jBaseStores = super.getJBaseStores(request, 1);
			dynaBean.set("history_list_mod_id", 110040800);
			break;
		}

		// dynaBean.set("opr_type", "0"); // opt_type,0-表示新增， 1-表示修改

		if (bill_type.equals("20")) {// 客户销售
			request.setAttribute("jBasePartners", super.getJBasePartners("1", null, user.getCust_id()));
		} else if (bill_type.equals("21")) {// 客户销售退货
			JBasePartner jp = new JBasePartner();
			jp.getMap().put("c_id", user.getCust_id());
			jp.getMap().put("bill_type", "20");
			jp.getMap().put("partner_type_value", "1");
			request.setAttribute("jBasePartners", super.getFacade().getJBasePartnerService()
					.getJBasePartnerForBillList(jp));
		}
		request.setAttribute("jBaseStores", jBaseStores);
		request.setAttribute("jBaseTypes", super.getJBaseTypes(10001L, user.getCust_id())); // 商品类型

		JBill entity = new JBill();
		entity.setBill_id(Long.valueOf(bill_id));

		entity = super.getFacade().getJBillService().getJBill(entity);
		entity.setQueryString(super.serialize(request, "bill_id", "method"));

		JBasePartner jBasePartner = new JBasePartner();
		jBasePartner.setPartner_id(entity.getPartner_id());
		jBasePartner = super.getFacade().getJBasePartnerService().getJBasePartner(jBasePartner);
		entity.setjBasePartner(jBasePartner);

		super.copyProperties(form, entity);
		
		//查询当前客户下的所有有效门店    add by Liang Houen on 2015-06-23
		KonkaR3Shop shop = new KonkaR3Shop();
		shop.setId(user.getCust_id());
		shop = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(shop);
		
		List<HashMap> plist = new ArrayList<HashMap>();
		HashMap map = new HashMap();
		map.put("ID", shop.getId());
		map.put("NAME", shop.getCustomer_name());
		plist.add(map);
		
		List<HashMap> slist = super.getFacade().getKonkaR3StoreService().getStoreListByCID(user.getCust_id());
		if(null!=slist){
			plist.addAll(slist);
		}
		request.setAttribute("partnerList",plist);

		JBillDetails details = new JBillDetails();
		details.setBill_id(entity.getBill_id());
		List<JBillDetails> detailsList = super.getFacade().getJBillDetailsService().getJBillDetailsList(details);

		if (null != detailsList && detailsList.size() > 0) {
			for (JBillDetails temp : detailsList) {
				
				//获取商品列表
				JBaseGoods goods1 = new JBaseGoods();
				goods1.setC_id(user.getCust_id());
				goods1.setGoods_type(temp.getType_id());
				goods1.setGoods_stutes(0);
				List<JBaseGoods> goodsList = super.getFacade().getJBaseGoodsService().getJBaseGoodsList(goods1);
				temp.getMap().put("goodsList", goodsList);
				
				JBaseGoods goods = new JBaseGoods();
				goods.setGoods_id(temp.getGoods_id());
				goods = super.getFacade().getJBaseGoodsService().getJBaseGoods(goods);
				if (null != goods && null != goods.getGoods_name()) {
					temp.getMap().put("goods_name", goods.getGoods_name());
				}

				JBaseType unit = new JBaseType();
				if (null != goods && null != goods.getGoods_unit()) {
					unit.setType_id(goods.getGoods_unit());
					unit = super.getFacade().getJBaseTypeService().getJBaseType(unit);
				}
				if (null != unit && null != unit.getType_name()) {
					temp.getMap().put("unit", unit.getType_name());
				}

				// 获取实时库存
				Long stocks = 0L;
				JBaseStore jbs = new JBaseStore();
				jbs.setStore_id(temp.getStore_id());
				jbs = super.getFacade().getJBaseStoreService().getJBaseStore(jbs);
				KonkaR3Shop r3shop = new KonkaR3Shop();
				r3shop.setId(user.getCust_id());
				r3shop = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(r3shop);
				stocks = super.getKhStocks(r3shop.getR3_code(), goods.getGoods_name(), temp.getStore_id());
				temp.getMap().put("stocks", stocks);
			}
		}

		request.setAttribute("detailsList", detailsList);

		GiftInfo gift = new GiftInfo();
		gift.setStatus(0);
		List<GiftInfo> giftInfoList = super.getFacade().getGiftInfoService().getGiftInfoResultForListWithCate(gift);
		request.setAttribute("giftInfoList", giftInfoList);

		// 初始化页面标签
		request.setAttribute("j_bill_history_list_title",
				super.getMessage(request, "konka.jbill.history.title." + bill_type));
		request.setAttribute("bill_sn_title", super.getMessage(request, "konka.jbill.sn.title." + bill_type));
		request.setAttribute("store_title", super.getMessage(request, "konka.jbill.store.title." + bill_type));
		request.setAttribute("price_title", super.getMessage(request, "konka.jbill.price.title." + bill_type));
		request.setAttribute("goods_money_title",
				super.getMessage(request, "konka.jbill.goods.money.title." + bill_type));
		request.setAttribute("rec_money_title", super.getMessage(request, "konka.jbill.rec.money.title." + bill_type));
		request.setAttribute("money_title", super.getMessage(request, "konka.jbill.money.title." + bill_type));

		String sell_post_p_index = jBasePartner.getConsignee_p_index();
		if (null != sell_post_p_index) {
			String province = StringUtils.substring(sell_post_p_index, 0, 2).concat("0000");
			String city = StringUtils.substring(sell_post_p_index, 0, 4).concat("00");
			String country = StringUtils.substring(sell_post_p_index, 0, 6);
			String town = "";
			if (StringUtils.length(sell_post_p_index) == 8) {
				town = sell_post_p_index;
			}
			dynaBean.set("province", province);
			dynaBean.set("city", city);
			dynaBean.set("country", country);
			dynaBean.set("town", town);
		}

		if ("20".equals(bill_type)) { // 零售
			return new ActionForward("/../customer/JBill/form_20.jsp");
		}
		if ("21".equals(bill_type)) { // 零售退货
			return new ActionForward("/../customer/JBill/xsth_form.jsp");
		}
		if ("11".equals(bill_type)) { // 零售退货
			return new ActionForward("/../customer/JBill/oth_form.jsp");
		}

		return mapping.findForward("input");
	}

	/**
	 * 保存其他产品进货登记
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String bill_id = (String) dynaBean.get("bill_id");
		String method_type = (String) dynaBean.get("method_type");
		String[] store_ids = request.getParameterValues("store_id");
		String[] type_ids = request.getParameterValues("type_id");
		String[] goods_ids = request.getParameterValues("goods_id");
		String[] nums = request.getParameterValues("num");
		String[] prices = request.getParameterValues("price");
		String[] goods_moneys = request.getParameterValues("goods_money");
		String[] goods_cost = request.getParameterValues("goods_cost");
		String[] gift_id = request.getParameterValues("gift_id");
		String[] gift_count = request.getParameterValues("gift_count");
		String[] gift_desc = request.getParameterValues("gift_desc");
		String[] notes = request.getParameterValues("notes");

		PeProdUser user = (PeProdUser) getSessionAttribute(request, Constants.CUSTOMER_USER_INFO);

		String partner_id = (String) dynaBean.get("partner_id");
		String link_name = (String) dynaBean.get("link_name"); // 顾客姓名/单位名称
		String link_mobile = (String) dynaBean.get("link_mobile"); // 联系电话
		String consignee_name = (String) dynaBean.get("consignee_name"); // 收货人
		String consignee_tel = (String) dynaBean.get("consignee_tel"); // 收货人电话
		String consignee_street = (String) dynaBean.get("consignee_street"); // 街道地址
		String partner_sn = (String) dynaBean.get("partner_sn");
		String province = (String) dynaBean.get("province");
		String city = (String) dynaBean.get("city");
		String country = (String) dynaBean.get("country");
		String town = (String) dynaBean.get("town");
		String p_index = "";
		if (StringUtils.isNotBlank(town)) {
			p_index = town;
		} else if (StringUtils.isNotBlank(country)) {
			p_index = country;
		} else if (StringUtils.isNotBlank(city)) {
			p_index = city;
		} else {
			p_index = province;
		}
		
		
		JBill entity = new JBill();
		super.copyProperties(entity, form);
		
		if(20==entity.getBill_type()){
			JBasePartner jBasePartner = new JBasePartner();
			jBasePartner.setPartner_id(Long.valueOf(partner_id));
			jBasePartner = super.getFacade().getJBasePartnerService().getJBasePartner(jBasePartner);
			entity.setjBasePartner(jBasePartner);
		}else if (21==entity.getBill_type()){
			//复制退货订单关联的订单信息
			JBill pbill = new JBill();
			pbill.setBill_sn(entity.getR_bill_sn());
			pbill = super.getFacade().getJBillService().getJBill(pbill);
			if(null!=pbill){
				entity.setPartner_id(pbill.getPartner_id());
				entity.setXs_id(pbill.getXs_id());
			}
		}else if(10==entity.getBill_type() || 11==entity.getBill_type()){
			JBasePartner jBasePartner = new JBasePartner();
			jBasePartner.setPartner_id(Long.valueOf(partner_id));
			jBasePartner = super.getFacade().getJBasePartnerService().getJBasePartner(jBasePartner);
			entity.setjBasePartner(jBasePartner);

			//计算折扣率
			BigDecimal temp1 = new BigDecimal(0);
			BigDecimal temp2 = new BigDecimal(100);
			if(null!=entity.getDis_money()&&null!=entity.getRec_money()&&entity.getRec_money().compareTo(temp1)==1){
				temp1 = entity.getDis_money().divide(entity.getRec_money()).multiply(temp2);
				entity.setDiscount(temp1);
			}else {
				entity.setDiscount(temp1);
			}

		}

		//添加人信息
		entity.setAdd_user_id(user.getId());
		entity.setAdd_user_name(user.getUser_name());
		entity.setBill_state(0);

		if (GenericValidator.isLong(partner_id)) {
			entity.setPartner_id(Long.valueOf(partner_id));
		}


		List<JBillDetails> detailsList = new ArrayList<JBillDetails>();
		if (null != goods_ids) {
			for (int i = 0; i < goods_ids.length; i++) {
				JBillDetails details = new JBillDetails();
				details.setGoods_id(Long.valueOf(goods_ids[i]));
				details.setStore_id(Long.valueOf(store_ids[i]));
				details.setType_id(Long.valueOf(type_ids[i]));

				if (entity.getBill_type() == 11 || entity.getBill_type() == 21) {// 退货
					details.setNum(0 - Long.valueOf(nums[i]));
					if (null != goods_cost && StringUtils.isNotBlank(goods_cost[i])) {
						details.setCost(new BigDecimal(0).subtract(new BigDecimal(goods_cost[i])
								.multiply(new BigDecimal(nums[i]))));
					}
				} else {
					details.setNum(Long.valueOf(nums[i]));
					if (null != goods_cost && StringUtils.isNotBlank(goods_cost[i])) {
						details.setCost(new BigDecimal(goods_cost[i]).multiply(new BigDecimal(nums[i])));
					}
				}
				details.setPrice(new BigDecimal(prices[i]));
				if (null != goods_moneys[i]) {
					details.setMoney(new BigDecimal(goods_moneys[i]));
					details.setRec_money(new BigDecimal(goods_moneys[i]));
				} else {
					details.setMoney(new BigDecimal(prices[i]).multiply(new BigDecimal(nums[i])));
					details.setRec_money(new BigDecimal(prices[i]).multiply(new BigDecimal(nums[i])));
				}
				if (null != gift_id && StringUtils.isNotBlank(gift_id[i])) {
					details.setGift_id(Long.valueOf(gift_id[i]));
				}
				if (null != gift_count && StringUtils.isNotBlank(gift_count[i])) {
					details.setGift_count(Integer.valueOf(gift_count[i]));
				}
				if (null != gift_desc && null != gift_desc[i]) {
					details.setGift_desc(gift_desc[i]);
				}
				if (null != notes) {
					details.setNotes(notes[i]);
				}
				// 获得销售成本
				if (entity.getBill_type() == 11 || entity.getBill_type() == 21) {// 退货
					details.setSale_cost(super.getSaleCost(user.getCust_id(), Long.valueOf(goods_ids[i]),
							Long.valueOf(store_ids[i]), new BigDecimal("0").subtract(new BigDecimal(nums[i]))));
				} else {
					details.setSale_cost(super.getSaleCost(user.getCust_id(), Long.valueOf(goods_ids[i]),
							Long.valueOf(store_ids[i]), new BigDecimal(nums[i])));
				}
				detailsList.add(details);
			}
			entity.setjBillDetailsList(detailsList);
		}
		entity.setC_id(user.getCust_id());
		
		if (StringUtils.isEmpty(bill_id)) {
			super.getFacade().getJBillService().createJBillAndDeatails(entity);
			super.saveMessage(request, "konka.jbill.insert.success");
		} else {
			super.getFacade().getJBillService().modifyJBillAndDeatails(entity);
			super.saveMessage(request, "konka.jbill.update.success");
		}

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		if(entity.getBill_type()==10){
			pathBuffer.append("JBill.do?method=listForOther");
			pathBuffer.append("&mod_id=105040600");
		}else {
			if ("1".equals(method_type)) {
				pathBuffer.append("JBill.do?method=list");
			} else {
				pathBuffer.append("JBill.do?method=add");
			}
			pathBuffer.append("&mod_id=" + mod_id);
		}

		pathBuffer.append("&bill_type=" + entity.getBill_type());
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String bill_id = (String) dynaBean.get("bill_id");
		String bill_sn = (String) dynaBean.get("bill_sn");

		if (!GenericValidator.isLong(bill_id) && StringUtils.isEmpty(bill_sn)) {
			super.saveError(request, "errors.param");
			return mapping.findForward("list");
		}

		JBill entity = new JBill();
		if (GenericValidator.isLong(bill_id)) {
			entity.setBill_id(Long.valueOf(bill_id));
		}
		if (StringUtils.isNotBlank(bill_sn)) {
			entity.setBill_sn(bill_sn);
		}
		entity = super.getFacade().getJBillService().getJBill(entity);

		JBasePartner partner = new JBasePartner();
		partner.setPartner_id(entity.getPartner_id());

		partner = super.getFacade().getJBasePartnerService().getJBasePartner(partner);

		super.copyProperties(form, entity);
		dynaBean.set("partner", partner);
		
		//销售单位名称
		if(null!=entity.getXs_id()){
			//若与c_id相等，则为客户，否则为门店
			if(entity.getC_id().equals(entity.getXs_id())){
				KonkaR3Shop shop = new KonkaR3Shop();
				shop.setId(entity.getC_id());
				shop = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(shop);
				if(null!=shop){
					request.setAttribute("xs_name", shop.getCustomer_name());
				}
			}else{
				KonkaR3Store store = new KonkaR3Store();
				store.setStore_id(entity.getXs_id());
				store = super.getFacade().getKonkaR3StoreService().getKonkaR3Store(store);
				if(null!=store){
					request.setAttribute("xs_name", store.getStore_name());
				}
			}
		}

		JBillDetails details = new JBillDetails();
		details.setBill_id(entity.getBill_id());

		List<JBillDetails> detailsList = super.getFacade().getJBillDetailsService().getJBillDetailsList(details);
		if (null != detailsList && detailsList.size() > 0) {
			for (JBillDetails temp : detailsList) {
				JBaseStore store = new JBaseStore();
				store.setStore_id(temp.getStore_id());
				store = super.getFacade().getJBaseStoreService().getJBaseStore(store);

				temp.getMap().put("store_name", store.getStore_name());

				JBaseGoods goods = new JBaseGoods();
				goods.setGoods_id(temp.getGoods_id());
				goods = super.getFacade().getJBaseGoodsService().getJBaseGoods(goods);

				temp.getMap().put("goods_name", goods.getGoods_name());

				JBaseType type = new JBaseType();
				type.setType_id(goods.getGoods_unit());
				type = super.getFacade().getJBaseTypeService().getJBaseType(type);

				temp.getMap().put("unit", type.getType_name());

				JBaseType type1 = new JBaseType();
				if (null != goods && null != goods.getGoods_type()) {
					type1.setType_id(goods.getGoods_type());
					type1 = super.getFacade().getJBaseTypeService().getJBaseType(type1);
				}

				if (null != type1 && null != type1.getType_name()) {
					temp.getMap().put("type", type1.getType_name());
				}
				
				//赠品
				if(null!=temp.getGift_id()){
					GiftInfo gf = new GiftInfo();
					gf.setGift_id(temp.getGift_id());
					gf = super.getFacade().getGiftInfoService().getGiftInfo(gf);
					if(null!=gf){
						temp.getMap().put("gift_name", gf.getGift_name());
					}else{
						temp.getMap().put("gift_name", "其他");
					}
				}
			}
		}

		request.setAttribute("detailsList", detailsList);

		// 初始化页面标签
		request.setAttribute("bill_sn_title",
				super.getMessage(request, "konka.jbill.sn.title." + entity.getBill_type()));
		request.setAttribute("store_title",
				super.getMessage(request, "konka.jbill.store.title." + entity.getBill_type()));
		request.setAttribute("price_title",
				super.getMessage(request, "konka.jbill.price.title." + entity.getBill_type()));
		request.setAttribute("goods_money_title",
				super.getMessage(request, "konka.jbill.goods.money.title." + entity.getBill_type()));
		request.setAttribute("rec_money_title",
				super.getMessage(request, "konka.jbill.rec.money.title." + entity.getBill_type()));
		request.setAttribute("money_title",
				super.getMessage(request, "konka.jbill.money.title." + entity.getBill_type()));

		if(entity.getBill_type()==10||entity.getBill_type()==11){
			return new ActionForward("/../customer/JBill/view_other.jsp");
		}
		return mapping.findForward("view");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String bill_id = (String) dynaBean.get("bill_id");

		if (!GenericValidator.isLong(bill_id)) {
			super.saveError(request, "errors.param");
			return mapping.findForward("list");
		}

		JBill entity = new JBill();
		entity.setBill_id(Long.valueOf(bill_id));
		int res = super.getFacade().getJBillService().removeJBillAndDetails(entity);

		//封装成JSON字符串
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("result", res);
		JSONArray jsonArray = JSONArray.fromObject(m);
		int start =jsonArray.toString().indexOf("[");
		int end = jsonArray.toString().lastIndexOf("}");
		String jsonStr = jsonArray.toString().substring(start+1, end+1);
		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(jsonStr);
		out.flush();
		out.close();
		return null;
	}

	public ActionForward ajaxSetGoodsBysn(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String goods_sn = (String) dynaBean.get("goods_sn");
		StringBuffer sb = new StringBuffer("{");
		sb = sb.append("\"status\":");
		if (StringUtils.isBlank(goods_sn)) {
			sb = sb.append("-1");
			sb = sb.append("}");
			super.renderJson(response, sb.toString());
			return null;
		}
		PeProdUser user = (PeProdUser) getSessionAttribute(request, Constants.CUSTOMER_USER_INFO);

		JBaseGoods jBaseGoods = new JBaseGoods();
		jBaseGoods.setGoods_sn(goods_sn);
		jBaseGoods.setGoods_stutes(0);
		jBaseGoods.setC_id(user.getCust_id());

		List<JBaseGoods> jBaseGoodsList = super.getFacade().getJBaseGoodsService().getJBaseGoodsList(jBaseGoods);

		if (null == jBaseGoodsList || jBaseGoodsList.size() == 0) {
			sb = sb.append("0");
		} else {
			sb = sb.append("1").append(",");
			sb = sb.append("\"goods_id\":").append(jBaseGoodsList.get(0).getGoods_id());
		}
		sb = sb.append("}");
		super.renderJson(response, sb.toString());
		return null;
	}

	/**
	 * 获取商品信息，包括库存
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward ajaxSetGoodsByid(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String goods_id = (String) dynaBean.get("goods_id");
		String bill_type = (String) dynaBean.get("bill_type");
		String store_id = (String) dynaBean.get("store_id");
		StringBuffer sb = new StringBuffer("{");
		sb = sb.append("\"status\":");
		if (StringUtils.isBlank(goods_id) || StringUtils.isBlank(store_id)) {
			sb = sb.append("-1");
			sb = sb.append("}");
			super.renderJson(response, sb.toString());
			return null;
		}
		PeProdUser user = (PeProdUser) getSessionAttribute(request, Constants.CUSTOMER_USER_INFO);

		JBaseGoods jBaseGoods = new JBaseGoods();
		jBaseGoods.setGoods_id(Long.valueOf(goods_id));
		jBaseGoods.setGoods_stutes(0);
		jBaseGoods.setC_id(user.getCust_id());
		String md_name = "";

		List<JBaseGoods> jBaseGoodsList = super.getFacade().getJBaseGoodsService().getJBaseGoodsList(jBaseGoods);

		if (null == jBaseGoodsList || jBaseGoodsList.size() == 0) {
			sb = sb.append("0");
		} else {
			jBaseGoods = jBaseGoodsList.get(0);
			sb = sb.append("1").append(",");
			if ("10".equals(bill_type) || "11".equals(bill_type)) {
				sb = sb.append("\"price\":\"").append(jBaseGoods.getBuy_price()).append("\"");
			} else {
				sb = sb.append("\"price\":\"").append(jBaseGoods.getSell_price()).append("\"");
			}
			md_name = jBaseGoods.getGoods_name();
			// 获取商品单位
			JBaseType base_type = new JBaseType();
			base_type.setType_id(jBaseGoods.getGoods_unit());
			base_type = super.getFacade().getJBaseTypeService().getJBaseType(base_type);
			if (null != base_type) {
				sb = sb.append(",\"unit\":\"").append(base_type.getType_name()).append("\"");
			}

			// 获取实时库存
			Long stocks = 0L;

			JBaseStore jbs = new JBaseStore();
			jbs.setStore_id(Long.valueOf(store_id));
			jbs = super.getFacade().getJBaseStoreService().getJBaseStore(jbs);
			KonkaR3Shop r3shop = new KonkaR3Shop();
			r3shop.setId(user.getCust_id());
			r3shop = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(r3shop);
			logger.info("仓库：" + jbs.getStore_name().toString());
			// stocks = super.getStocks(Long.valueOf(goods_id),
			// Long.valueOf(store_id), user.getCust_id());
			stocks = super.getKhStocks(r3shop.getR3_code(), md_name, Long.valueOf(store_id));

			if (null != stocks) {
				sb = sb.append(",\"stocks\":\"").append(stocks.toString()).append("\"");
			}

		}
		sb = sb.append("}");
		logger.info("sb {}", sb);
		super.renderJson(response, sb.toString());
		return null;
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
		String type_id = (String) dynaBean.get("type_id");
		StringBuffer sb = new StringBuffer("{");
		sb = sb.append("\"list\":[");
		if (StringUtils.isBlank(type_id)) {
			sb = sb.append("]}");
			super.renderJson(response, sb.toString());
			return null;
		}
		PeProdUser user = (PeProdUser) getSessionAttribute(request, Constants.CUSTOMER_USER_INFO);

		JBaseGoods goods = new JBaseGoods();
		goods.setC_id(user.getCust_id());
		goods.setGoods_type(Long.valueOf(type_id));
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

	public ActionForward chooseBillSn(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String bill_sn_like = (String) dynaBean.get("bill_sn_like");
		String opr_date_gt = (String) dynaBean.get("opr_date_gt");
		String opr_date_lt = (String) dynaBean.get("opr_date_lt");
		String partner_id = (String) dynaBean.get("partner_id");

		PeProdUser user = (PeProdUser) getSessionAttribute(request, Constants.CUSTOMER_USER_INFO);

		request.setAttribute("jBasePartners", super.getJBasePartners("1", null, user.getCust_id()));

		JBill entity = new JBill();
		entity.setBill_type(20);
		entity.setBill_state(1);
		entity.setC_id(user.getCust_id());
		if (StringUtils.isNotBlank(bill_sn_like)) {
			entity.getMap().put("bill_sn_like", bill_sn_like);
		}
		if (StringUtils.isNotBlank(opr_date_gt)) {
			entity.getMap().put("opr_date_gt", opr_date_gt);
		}
		if (StringUtils.isNotBlank(opr_date_lt)) {
			entity.getMap().put("opr_date_lt", opr_date_lt);
		}
		if (StringUtils.isNotBlank(partner_id)) {
			entity.setPartner_id(Long.valueOf(partner_id));
		}

		entity.getMap().put("order_by_opt_desc", true);

		Long recordCount = getFacade().getJBillService().getJBillCount(entity);
		pager.init(recordCount, 40, pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(40);
		List<JBill> list = getFacade().getJBillService().getJBillPaginatedList(entity);
		request.setAttribute("entityList", list);
		return new ActionForward("/../customer/JBill/choose_billsn.jsp");
	}

	public ActionForward ajaxSetRBillSnDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String r_bill_sn = (String) dynaBean.get("r_bill_sn");
		StringBuffer sb = new StringBuffer("{");
		if (StringUtils.isBlank(r_bill_sn)) {
			sb = sb.append("}");
			super.renderJson(response, sb.toString());
			return null;
		}

		JBill jbill = new JBill();
		jbill.setBill_sn(r_bill_sn);
		jbill = super.getFacade().getJBillService().getJBill(jbill);

		if (null != jbill) {
			sb = sb.append("\"partner_id\":\"").append(jbill.getPartner_id()).append("\",");
		}
		sb = sb.append("\"list\":[");
		JBillDetails jbillDetails = new JBillDetails();
		jbillDetails.setBill_id(jbill.getBill_id());

		List<JBillDetails> jbillDetailsList = super.getFacade().getJBillDetailsService()
				.getJBillDetailsAndGoodsList(jbillDetails);

		if (null != jbillDetailsList && jbillDetailsList.size() > 0) {
			for (JBillDetails temp : jbillDetailsList) {
				sb = sb.append("{");
				JBaseStore store = new JBaseStore();
				store.setStore_id(temp.getStore_id());
				store = super.getFacade().getJBaseStoreService().getJBaseStore(store);
				sb = sb.append("\"store_name\":\"").append(store.getStore_name()).append("\",");
				sb = sb.append("\"store_id\":\"").append(temp.getStore_id()).append("\",");

				JBaseGoods goods = new JBaseGoods();
				goods.setGoods_id(temp.getGoods_id());
				goods = super.getFacade().getJBaseGoodsService().getJBaseGoods(goods);
				if (goods != null) {
					sb = sb.append("\"goods_name\":\"").append(goods.getGoods_name()).append("\",");
					sb = sb.append("\"goods_id\":\"").append(temp.getGoods_id()).append("\",");

					if (null != goods.getGoods_unit()) {
						JBaseType unit = new JBaseType();
						unit.setType_id(goods.getGoods_unit());
						unit = super.getFacade().getJBaseTypeService().getJBaseType(unit);
						if (unit != null) {
							sb = sb.append("\"unit\":\"").append(unit.getType_name()).append("\",");
						}
					}

					if (null != goods.getGoods_type()) {
						JBaseType type = new JBaseType();
						type.setType_id(goods.getGoods_type());
						type = super.getFacade().getJBaseTypeService().getJBaseType(type);
						if (type != null) {
							sb = sb.append("\"type\":\"").append(type.getType_name()).append("\",");
						}
					}
				}

				sb = sb.append("\"sell_num\":\"").append(temp.getNum()).append("\",");
				sb = sb.append("\"sell_price\":\"").append(temp.getPrice()).append("\",");
				if (null != temp.getCost()) {
					sb = sb.append("\"goods_cost\":\"")
							.append(temp.getCost().divide(new BigDecimal(temp.getNum().toString()))).append("\",");
				} else {
					sb = sb.append("\"goods_cost\":\"").append("\",");
				}
				sb = sb.append("\"sell_money\":\"").append(temp.getMoney()).append("\"");
				sb = sb.append("},");
			}
		}
		String sb_str = StringUtils.removeEnd(sb.toString(), ",") + "]}";
		logger.info("sb_str {}", sb_str);
		super.renderJson(response, sb_str);
		return null;
	}

	// ajax检查库存
	public ActionForward ajaxGetStockList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String goods_nums = (String) dynaBean.get("goods_nums");
		PeProdUser user = (PeProdUser) getSessionAttribute(request, Constants.CUSTOMER_USER_INFO);
		JBill entity = new JBill();
		super.copyProperties(entity, form);

		String re_stock = "";
		if (goods_nums != null && goods_nums.length() > 0) {
			String[] goods_num = goods_nums.split(",");
			for (int i = 0; i < goods_num.length; i++) {
				// String goods_id = goods_num[i].substring(0,
				// goods_num[i].indexOf(":"));
				// String num = goods_num[i].substring(goods_num[i].indexOf(":")
				// + 1, goods_num[i].length());
				String[] gns = goods_num[i].split(":");
				String goods_id = gns[0];
				String num = gns[1];
				String store_id = gns[2];
				KonkaR3Shop r3shop = new KonkaR3Shop();
				r3shop.setId(user.getCust_id());
				r3shop = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(r3shop);
				if (r3shop != null) {
					if (null != r3shop.getR3_code()) {
						Integer issales = 0;
						issales = super.getR3IsSales(r3shop.getR3_code());// 判断R3客户是否允许负卖
						if (null != issales && issales == 1) {// 如果不允许负卖
							JBaseGoods jGoods = new JBaseGoods();
							jGoods.setGoods_id(Long.parseLong(goods_id));
							jGoods.setGoods_stutes(0);
							jGoods.setC_id(user.getCust_id());
							jGoods = super.getFacade().getJBaseGoodsService().getJBaseGoods(jGoods);

							// 获取实时库存
							Long stock = 0L;
							stock = super.getKhStocks(r3shop.getR3_code(), jGoods.getGoods_name(),
									Long.parseLong(store_id));
							logger.info("goods_id=" + goods_id + "   num=" + num + "   store_id=" + store_id
									+ "   stock=" + stock);
							if (jGoods.getIs_konka() == 1) {// 判断是否是康佳产品，是
								// 执行如下操作
								if (Long.valueOf(num) > stock) {// 判断销量是否大于库存
									re_stock = re_stock + jGoods.getGoods_name() + "库存不足，请重新选择！\n";
								}
							}
						}

					}
				}
			}
		}
		if (re_stock != null && re_stock.length() > 0) {
			super.renderText(response, re_stock);
			return null;
		}

		return null;
	}

	/**
	 * 打印订单
	 * @author Liang Houen
	 * @since 2015-7-2
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward print(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String bill_id = (String) dynaBean.get("bill_id");

		PeProdUser user = (PeProdUser) getSessionAttribute(request, Constants.CUSTOMER_USER_INFO);

		JBill jBill = new JBill();
		JBillDetails jBillDetails = new JBillDetails();
		JBasePartner partner = new JBasePartner();
		if (StringUtils.isNotBlank(bill_id)) {
			jBill.setBill_id(Long.parseLong(bill_id));
			jBillDetails.setBill_id(Long.parseLong(bill_id));
		}
		jBill = super.getFacade().getJBillService().getJBill(jBill);
		
		//销售单位
		if(jBill.getC_id().equals(jBill.getXs_id()) || null == jBill.getXs_id()){
			KonkaR3Shop shop = new KonkaR3Shop();
			shop.setId(jBill.getC_id());
			shop = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(shop);
			request.setAttribute("p_name", shop.getCustomer_name());
		}else{
			KonkaR3Store store = new KonkaR3Store();
			store.setStore_id(jBill.getXs_id());
			store = super.getFacade().getKonkaR3StoreService().getKonkaR3Store(store);
			request.setAttribute("p_name", store.getStore_name());
		}
		
		//送货方式
		if(0==jBill.getSend_type()){
			request.setAttribute("check1", true);
			request.setAttribute("check2", false);
		}
		if(1==jBill.getSend_type()){
			request.setAttribute("check2", true);
			request.setAttribute("check1", false);
		}

		KonkaR3Shop r3shop = new KonkaR3Shop();
		r3shop = super.getKonkaR3ShopById(user.getCust_id());
		request.setAttribute("sell_cust_name", r3shop.getCustomer_name());

		JBillDetails details = new JBillDetails();
		details.setBill_id(jBill.getBill_id());
		List<JBillDetails> detailsList = super.getFacade().getJBillDetailsService().getJBillDetailsList(details);

		if (null != detailsList && detailsList.size() > 0) {
			
			//打印单取第一个商品的仓库
			JBaseStore jbs = new JBaseStore();
			jbs.setStore_id(detailsList.get(0).getStore_id());
			jbs = super.getFacade().getJBaseStoreService().getJBaseStore(jbs);
			if(null!=jbs){
				request.setAttribute("store_name", jbs.getStore_name());
			}
			
			for (JBillDetails temp : detailsList) {
				JBaseGoods goods = new JBaseGoods();
				if (null != temp.getGoods_id()) {
					goods.setGoods_id(temp.getGoods_id());
					goods = super.getFacade().getJBaseGoodsService().getJBaseGoods(goods);
				}
				if (null != goods && null != goods.getGoods_name()) {
					temp.getMap().put("goods_name", goods.getGoods_name());
				}
				JBaseType unit = new JBaseType();
				if (null != goods && null != goods.getGoods_type()) {
					unit.setType_id(goods.getGoods_unit());
					unit = super.getFacade().getJBaseTypeService().getJBaseType(unit);
				}
				if (null != unit && null != unit.getType_name()) {
					temp.getMap().put("unit", unit.getType_name());
				}
				JBaseType type = new JBaseType();
				if (null != goods && null != goods.getGoods_type()) {
					type.setType_id(goods.getGoods_type());
					type = super.getFacade().getJBaseTypeService().getJBaseType(type);
				}

				if (null != type && null != type.getType_name()) {
					temp.getMap().put("type", type.getType_name());
				}
			}
		}

		request.setAttribute("entityList", detailsList);

		GiftInfo gift = new GiftInfo();
		gift.setStatus(0);
		List<GiftInfo> giftInfoList = super.getFacade().getGiftInfoService().getGiftInfoResultForListWithCate(gift);
		request.setAttribute("giftInfoList", giftInfoList);

		partner.setPartner_id(jBill.getPartner_id());
		partner = super.getFacade().getJBasePartnerService().getJBasePartner(partner);

		request.setAttribute("user", user);
		request.setAttribute("partner", partner);
		request.setAttribute("nowDate", new Date());
		super.copyProperties(form, jBill);

		return new ActionForward("/../customer/JBill/print.jsp");
	}

	/**
	 * 查询零售明细列表
	 * @author Liang Houen
	 * @since 2015-7-6
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward listForPrint(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String export = (String) dynaBean.get("export");
		Pager pager = (Pager) dynaBean.get("pager");
		pager.setPageSize(10);
		String opr_date_gt = (String) dynaBean.get("opr_date_gt");// 起始时间
		String opr_date_lt = (String) dynaBean.get("opr_date_lt");// 结束时间
		String bill_sn_like = (String) dynaBean.get("bill_sn_like");  //销售单号
		String bill_type = (String) dynaBean.get("bill_type");  //单据类型
		String p_name_like = (String) dynaBean.get("p_name_like");  //销售单位
		String p_id_like = (String) dynaBean.get("p_id_like");  //销售单位编码
		String report_name_like = (String) dynaBean.get("report_name_like");  //上报人
		String type_id = (String) dynaBean.get("type_id");  //商品类型
		String goods_name_like = (String) dynaBean.get("goods_name_like"); //机型
		String store_id = (String) dynaBean.get("store_id"); //出货仓库id
		String data_type = (String) dynaBean.get("data_type"); //数据来源
		String customer_name_like = (String) dynaBean.get("customer_name_like");  //顾客姓名
		String mobile_like = (String) dynaBean.get("mobile_like");  //顾客电话

		PeProdUser user = (PeProdUser) getSessionAttribute(request, Constants.CUSTOMER_USER_INFO);
		JBillDetails entity = new JBillDetails();

		if (StringUtils.isNotBlank(opr_date_gt)) {
			entity.getMap().put("opr_date_gt", opr_date_gt+" 00:00:00");
		}
		if (StringUtils.isNotBlank(opr_date_lt)) {
			entity.getMap().put("opr_date_lt", opr_date_lt+" 23:59:59");
		}
		if (StringUtils.isNotBlank(bill_sn_like)) {
			entity.getMap().put("bill_sn_like", bill_sn_like);
		}
		if (StringUtils.isNotBlank(bill_type)) {
			entity.getMap().put("bill_type", bill_type);
		}
		if (StringUtils.isNotBlank(p_name_like)) {
			entity.getMap().put("p_name_like", p_name_like);
		}
		if (StringUtils.isNotBlank(p_id_like)) {
			entity.getMap().put("p_id_like", p_id_like);
		}
		if (StringUtils.isNotBlank(report_name_like)) {
			entity.getMap().put("report_name_like", report_name_like);
		}
		if (StringUtils.isNotBlank(type_id)) {
			entity.setType_id(Long.parseLong(type_id));
		}
		if (StringUtils.isNotBlank(goods_name_like)) {
			entity.getMap().put("goods_name_like", goods_name_like);
		}
		if (StringUtils.isNotBlank(store_id)) {
			entity.getMap().put("store_id", store_id);
		}
		if (StringUtils.isNotBlank(data_type)) {
			if("1".equals(data_type)){
				entity.getMap().put("bill_type_xs", true);
			}else{
				entity.getMap().put("bill_type_ls", true);
			}
		}else{
			entity.getMap().put("all_type", true);
		}
		if (StringUtils.isNotBlank(customer_name_like)) {
			entity.getMap().put("customer_name_like", customer_name_like);
		}
		if (StringUtils.isNotBlank(mobile_like)) {
			entity.getMap().put("mobile_like", mobile_like);
		}
		entity.getMap().put("c_id", user.getCust_id());
		
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat exformat = new SimpleDateFormat("yyyyMMddhhmmss");

		if (StringUtils.isBlank(opr_date_gt) && StringUtils.isBlank(opr_date_lt)) {
			opr_date_lt = dateformat.format(calendar.getTime());// 结束时间
			calendar.set(Calendar.DAY_OF_MONTH, calendar
					.getActualMinimum(Calendar.DAY_OF_MONTH));// 初始化为当月1号
			opr_date_gt = dateformat.format(calendar.getTime());// 开始时间
			entity.getMap().put("opr_date_gt", opr_date_gt+" 00:00:00");
			entity.getMap().put("opr_date_lt", opr_date_lt+" 23:59:59");
		}

		dynaBean.set("opr_date_gt", opr_date_gt);
		dynaBean.set("opr_date_lt", opr_date_lt);

		Long recordCount = super.getFacade().getJBillDetailsService().getJBillDetailsForSalesCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setCount(pager.getRowCount());
		entity.getRow().setFirst(pager.getFirstRow());
		List<HashMap> entityList = super.getFacade().getJBillDetailsService()
				.getJBillDetailsForSalesList(entity);

		request.setAttribute("entityList", entityList);
		request.setAttribute("jBaseTypes", super.getJBaseTypes(10001L, user.getCust_id())); // 商品类型
		request.setAttribute("jBaseGoodsList", super.getJBaseGoodsList(user.getCust_id(), null)); // 机型
		request.setAttribute("storesList", super.getJBaseStores(request, 1)); // 出货仓库列表

		if (StringUtils.isNotBlank(export)) {
			String timestr = exformat.format(calendar.getTime());
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename=" + 
					EncryptUtils.encodingFileName("零售明细-"+timestr) + ".xls");
			entity.getRow().setCount(recordCount.intValue());
			dynaBean.set("export", export);
			List<HashMap> entityList1 = getFacade().getJBillDetailsService().getJBillDetailsForSalesList(entity);

			request.setAttribute("entityList1", entityList1);
			return new ActionForward("/../customer/JBill/listForReport.jsp");
		}

		return new ActionForward("/../customer/JBill/listHistory.jsp");
	}
	
	/**
	 * 进入财务确认界面
	 * @author Liang Houen
	 * @since 2015-7-2
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward confirm(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String bill_id = (String) dynaBean.get("bill_id");

		PeProdUser user = (PeProdUser) getSessionAttribute(request, Constants.CUSTOMER_USER_INFO);

		JBill jBill = new JBill();
		JBillDetails jBillDetails = new JBillDetails();
		JBasePartner partner = new JBasePartner();
		if (StringUtils.isNotBlank(bill_id)) {
			jBill.setBill_id(Long.parseLong(bill_id));
			jBillDetails.setBill_id(Long.parseLong(bill_id));
		}
		jBill = super.getFacade().getJBillService().getJBill(jBill);
		
		//销售单位
		if(jBill.getC_id().equals(jBill.getXs_id()) || null == jBill.getXs_id()){
			KonkaR3Shop shop = new KonkaR3Shop();
			shop.setId(jBill.getC_id());
			shop = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(shop);
			request.setAttribute("p_name", shop.getCustomer_name());
		}else{
			KonkaR3Store store = new KonkaR3Store();
			store.setStore_id(jBill.getXs_id());
			store = super.getFacade().getKonkaR3StoreService().getKonkaR3Store(store);
			request.setAttribute("p_name", store.getStore_name());
		}

		//送货方式
		if(0==jBill.getSend_type()){
			request.setAttribute("check1", true);
			request.setAttribute("check2", false);
		}
		if(1==jBill.getSend_type()){
			request.setAttribute("check2", true);
			request.setAttribute("check1", false);
		}

		KonkaR3Shop r3shop = new KonkaR3Shop();
		r3shop = super.getKonkaR3ShopById(user.getCust_id());
		request.setAttribute("sell_cust_name", r3shop.getCustomer_name());

		JBillDetails details = new JBillDetails();
		details.setBill_id(jBill.getBill_id());
		List<JBillDetails> detailsList = super.getFacade().getJBillDetailsService().getJBillDetailsList(details);

		if (null != detailsList && detailsList.size() > 0) {
			
			//打印单取第一个商品的仓库
			JBaseStore jbs = new JBaseStore();
			jbs.setStore_id(detailsList.get(0).getStore_id());
			jbs = super.getFacade().getJBaseStoreService().getJBaseStore(jbs);
			if(null!=jbs){
				request.setAttribute("store_name", jbs.getStore_name());
			}
			
			for (JBillDetails temp : detailsList) {
				JBaseGoods goods = new JBaseGoods();
				if (null != temp.getGoods_id()) {
					goods.setGoods_id(temp.getGoods_id());
					goods = super.getFacade().getJBaseGoodsService().getJBaseGoods(goods);
				}
				if (null != goods && null != goods.getGoods_name()) {
					temp.getMap().put("goods_name", goods.getGoods_name());
				}
				JBaseType unit = new JBaseType();
				if (null != goods && null != goods.getGoods_type()) {
					unit.setType_id(goods.getGoods_unit());
					unit = super.getFacade().getJBaseTypeService().getJBaseType(unit);
				}
				if (null != unit && null != unit.getType_name()) {
					temp.getMap().put("unit", unit.getType_name());
				}
				JBaseType type = new JBaseType();
				if (null != goods && null != goods.getGoods_type()) {
					type.setType_id(goods.getGoods_type());
					type = super.getFacade().getJBaseTypeService().getJBaseType(type);
				}

				if (null != type && null != type.getType_name()) {
					temp.getMap().put("type", type.getType_name());
				}
			}
		}

		request.setAttribute("entityList", detailsList);

		partner.setPartner_id(jBill.getPartner_id());
		partner = super.getFacade().getJBasePartnerService().getJBasePartner(partner);

		request.setAttribute("user", user);
		request.setAttribute("partner", partner);
		request.setAttribute("nowDate", new Date());
		super.copyProperties(form, jBill);

		return new ActionForward("/../customer/JBill/confirm.jsp");
	}
	
	/**
	 * 财务确认保存
	 * @author Liang Houen
	 * @since 2015-7-2
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String bill_id = (String) dynaBean.get("bill_id");

		PeProdUser user = (PeProdUser) getSessionAttribute(request, Constants.CUSTOMER_USER_INFO);
		JBill entity = new JBill();
		super.copyProperties(entity, form);

		entity.setC_id(user.getCust_id());
		if (StringUtils.isNotEmpty(bill_id)) {
			entity.setBill_state(1);
			super.getFacade().getJBillService().modifyJBill(entity);
		}
		return null;
	}
	
	
	/**
	 * 零售退货中选择关联销售单号查询消费者信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward ajaxGetAgentDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String r_bill_sn = (String) dynaBean.get("r_bill_sn");   //关联的销售单号
		Map<String, Object> m = new HashMap<String, Object>();
		
		//单号为空则返回
		if (StringUtils.isBlank(r_bill_sn)) {
			super.renderJson(response, "{}");
			return null;
		}

		JBill jbill = new JBill();
		jbill.setBill_sn(r_bill_sn);
		jbill = super.getFacade().getJBillService().getJBill(jbill);

		if (null != jbill) {
			JBasePartner entry = new JBasePartner();
			entry.setPartner_id(jbill.getPartner_id());
			entry = super.getFacade().getJBasePartnerService().getJBasePartner(entry);
			m.put("agent", entry);
			m.put("jbill", jbill);
		}
		JBillDetails jbillDetails = new JBillDetails();
		jbillDetails.setBill_id(jbill.getBill_id());

		List<JBillDetails> jbillDetailsList = super.getFacade().getJBillDetailsService()
				.getJBillDetailsAndGoodsList(jbillDetails);

		if (null != jbillDetailsList && jbillDetailsList.size() > 0) {
			for (JBillDetails temp : jbillDetailsList) {
				JBaseStore store = new JBaseStore();
				store.setStore_id(temp.getStore_id());
				store = super.getFacade().getJBaseStoreService().getJBaseStore(store);
				if(null!=store){
					temp.getMap().put("store_name", store.getStore_name());
					temp.getMap().put("store_id", store.getStore_id());
				}

				JBaseGoods goods = new JBaseGoods();
				goods.setGoods_id(temp.getGoods_id());
				goods = super.getFacade().getJBaseGoodsService().getJBaseGoods(goods);
				if (goods != null) {
					temp.getMap().put("goods_name", goods.getGoods_name());
					temp.getMap().put("goods_id", temp.getGoods_id());

					if (null != goods.getGoods_unit()) {
						JBaseType unit = new JBaseType();
						unit.setType_id(goods.getGoods_unit());
						unit = super.getFacade().getJBaseTypeService().getJBaseType(unit);
						if (unit != null) {
							temp.getMap().put("unit", unit.getType_name());
						}
					}

					if (null != goods.getGoods_type()) {
						JBaseType type = new JBaseType();
						type.setType_id(goods.getGoods_type());
						type = super.getFacade().getJBaseTypeService().getJBaseType(type);
						if (type != null) {
							temp.getMap().put("type_name", type.getType_name());
							temp.getMap().put("type_id", type.getType_id());
						}
					}
				}
				temp.getMap().put("num", temp.getNum());
				temp.getMap().put("price", temp.getPrice());
				if (null != temp.getCost()) {
					temp.getMap().put("goods_cost", temp.getCost().divide(new BigDecimal(temp.getNum().toString())));
				} else {
					temp.getMap().put("goods_cost", "");
				}
				temp.getMap().put("sell_money", temp.getMoney());
			}
			m.put("jdetails", jbillDetailsList);
		}

		//封装成JSON字符串
		JSONArray jsonArray = JSONArray.fromObject(m);
		int start =jsonArray.toString().indexOf("[");
		int end = jsonArray.toString().lastIndexOf("}");
		String jsonStr = jsonArray.toString().substring(start+1, end+1);
		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(jsonStr);
		out.flush();
		out.close();
		return null;
	}

	/**
	 * 其他产品-进货查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward listForOther(ActionMapping mapping, ActionForm form, HttpServletRequest request,
							  HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String opr_date_gt = (String) dynaBean.get("opr_date_gt");  //开始时间
		String opr_date_lt = (String) dynaBean.get("opr_date_lt");  //结束时间
		String bill_sn_like = (String) dynaBean.get("bill_sn_like");  //采购单号
		String bill_type = (String) dynaBean.get("bill_type");  //采购类型
		String p_name_like = (String) dynaBean.get("p_name_like");  //供应商名称
		String p_id_like = (String) dynaBean.get("p_id_like");  //供应商编码
		String bill_state = (String) dynaBean.get("bill_state");  //财务确认状态

		String view_type = (String) dynaBean.get("view_type");
		String mod_id = (String) dynaBean.get("mod_id");
		String export = (String) dynaBean.get("export");

		PeProdUser user = (PeProdUser) getSessionAttribute(request, Constants.CUSTOMER_USER_INFO);

		JBill entity = new JBill();
		entity.setC_id(user.getCust_id());
		if(StringUtils.isNotBlank(bill_type)){
			entity.setBill_type(Integer.valueOf(bill_type));
		}
		if (StringUtils.isNotBlank(bill_sn_like)) {
			entity.getMap().put("bill_sn_like", bill_sn_like);
		}
		if (StringUtils.isNotBlank(opr_date_gt)) {
			entity.getMap().put("opr_date_gt", opr_date_gt);
		}
		if (StringUtils.isNotBlank(opr_date_lt)) {
			entity.getMap().put("opr_date_lt", opr_date_lt);
		}
		if (StringUtils.isNotBlank(p_name_like)) {
			entity.getMap().put("p_name_like", p_name_like);
		}
		if (StringUtils.isNotBlank(p_id_like)) {
			entity.getMap().put("p_id_like", p_id_like);
		}
		if (StringUtils.isNotBlank(bill_state)) {
			entity.setBill_state(Integer.parseInt(bill_state));
		}
		entity.getMap().put("order_by_opt_desc", true);

		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat exformat = new SimpleDateFormat("yyyyMMddhhmmss");

		if (StringUtils.isBlank(opr_date_gt) && StringUtils.isBlank(opr_date_lt)) {
			opr_date_lt = dateformat.format(calendar.getTime());// 结束时间
			calendar.set(Calendar.DAY_OF_MONTH, calendar
					.getActualMinimum(Calendar.DAY_OF_MONTH));// 初始化为当月1号
			opr_date_gt = dateformat.format(calendar.getTime());// 开始时间
			entity.getMap().put("opr_date_gt", opr_date_gt);
			entity.getMap().put("opr_date_lt", opr_date_lt);
		}

		dynaBean.set("opr_date_gt", opr_date_gt);
		dynaBean.set("opr_date_lt", opr_date_lt);

		Long recordCount = super.getFacade().getJBillService().getOtherSaleDataCount(entity);
		pager.init(recordCount, 10, pager.getRequestPage());
		entity.getRow().setCount(pager.getRowCount());
		entity.getRow().setFirst(pager.getFirstRow());

		//导出
		if(StringUtils.isNotBlank(export)){
			String timestr = exformat.format(calendar.getTime());
			String file_name = "进货查询";
			if("11".equals(bill_type)){
				file_name = "退货查询";
			}
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename=" + EncryptUtils.encodingFileName(file_name+"-"+timestr)
					+ ".xls");
			entity.getRow().setCount(recordCount.intValue());
			List<HashMap> allList = super.getFacade().getJBillService().getOtherSaleDataList(entity);

			request.setAttribute("allList", allList);
			return new ActionForward("/../customer/JBill/listOtherForExcel.jsp");
		}


		List<HashMap> entityList = super.getFacade().getJBillService().getOtherSaleDataList(entity);
		request.setAttribute("entityList", entityList);

		GiftInfo gift = new GiftInfo();
		gift.setStatus(0);
		List<GiftInfo> giftInfoList = super.getFacade().getGiftInfoService().getGiftInfoResultForListWithCate(gift);
		request.setAttribute("giftInfoList", giftInfoList);

		// 初始化页面标签
		request.setAttribute("bill_sn_title", super.getMessage(request, "konka.jbill.sn.title." + bill_type));
		request.setAttribute("price_title", super.getMessage(request, "konka.jbill.price.title." + bill_type));
		request.setAttribute("goods_money_title",
				super.getMessage(request, "konka.jbill.goods.money.title." + bill_type));
		request.setAttribute("rec_money_title", super.getMessage(request, "konka.jbill.rec.money.title." + bill_type));
		request.setAttribute("money_title", super.getMessage(request, "konka.jbill.money.title." + bill_type));

		return new ActionForward("/../customer/JBill/list_other.jsp");
	}

	/**
	 * 进货订单修改
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward editOther(ActionMapping mapping, ActionForm form, HttpServletRequest request,
							  HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String bill_type = (String) dynaBean.get("bill_type");
		String bill_id = (String) dynaBean.get("bill_id");
		PeProdUser user = (PeProdUser) getSessionAttribute(request, Constants.CUSTOMER_USER_INFO);

		if (!GenericValidator.isLong(bill_id) || !GenericValidator.isLong(bill_type)) {
			super.saveError(request, "errors.param");
			return mapping.findForward("list");
		}
		List<JBaseStore> jBaseStores = new ArrayList<JBaseStore>();
		switch (Integer.valueOf(bill_type)) {
			case 10:
				jBaseStores = super.getJBaseStores(request, 0);
				dynaBean.set("history_list_mod_id", 105040600);
				break;
			case 11:
				jBaseStores = super.getJBaseStores(request, 0);
				dynaBean.set("history_list_mod_id", 105040800);
				break;
		}

//		if (bill_type.equals("10")) {// 客户进货
			request.setAttribute("jBasePartners", super.getJBasePartners("0", null, user.getCust_id()));
//		}else if (bill_type.equals("11")) {// 客户退货
//			JBasePartner jp = new JBasePartner();
//			jp.getMap().put("c_id", user.getCust_id());
//			jp.getMap().put("bill_type", "10");
//			jp.getMap().put("partner_type_value", "0");
//			request.setAttribute("jBasePartners", super.getFacade().getJBasePartnerService()
//					.getJBasePartnerForBillList(jp));
//		}

		request.setAttribute("jBaseStores", jBaseStores);
		request.setAttribute("jBaseTypes", super.getJBaseTypes(10001L, user.getCust_id(),"康佳电视")); // 商品类型

		JBill entity = new JBill();
		entity.setBill_id(Long.valueOf(bill_id));

		entity = super.getFacade().getJBillService().getJBill(entity);
		entity.setQueryString(super.serialize(request, "bill_id", "method"));

		JBasePartner jBasePartner = new JBasePartner();
		jBasePartner.setPartner_id(entity.getPartner_id());
		jBasePartner = super.getFacade().getJBasePartnerService().getJBasePartner(jBasePartner);
		entity.setjBasePartner(jBasePartner);

		PeProdUser add_user = new PeProdUser();
		add_user.setId(entity.getAdd_user_id());
		add_user = super.getFacade().getPeProdUserService().getPeProdUser(add_user);
		//创建人
		request.setAttribute("add_user_name",add_user.getUser_name());

		super.copyProperties(form, entity);

		JBillDetails details = new JBillDetails();
		details.setBill_id(entity.getBill_id());
		List<JBillDetails> detailsList = super.getFacade().getJBillDetailsService().getJBillDetailsList(details);

		if (null != detailsList && detailsList.size() > 0) {
			for (JBillDetails temp : detailsList) {

				//获取商品列表
				JBaseGoods goods1 = new JBaseGoods();
				goods1.setC_id(user.getCust_id());
				goods1.setGoods_type(temp.getType_id());
				goods1.setGoods_stutes(0);
				List<JBaseGoods> goodsList = super.getFacade().getJBaseGoodsService().getJBaseGoodsList(goods1);
				temp.getMap().put("goodsList", goodsList);

				JBaseGoods goods = new JBaseGoods();
				goods.setGoods_id(temp.getGoods_id());
				goods = super.getFacade().getJBaseGoodsService().getJBaseGoods(goods);
				if (null != goods && null != goods.getGoods_name()) {
					temp.getMap().put("goods_name", goods.getGoods_name());
				}

				JBaseType unit = new JBaseType();
				if (null != goods && null != goods.getGoods_unit()) {
					unit.setType_id(goods.getGoods_unit());
					unit = super.getFacade().getJBaseTypeService().getJBaseType(unit);
				}
				if (null != unit && null != unit.getType_name()) {
					temp.getMap().put("unit", unit.getType_name());
				}

				// 获取实时库存
				Long stocks = 0L;
				JBaseStore jbs = new JBaseStore();
				jbs.setStore_id(temp.getStore_id());
				jbs = super.getFacade().getJBaseStoreService().getJBaseStore(jbs);
				KonkaR3Shop r3shop = new KonkaR3Shop();
				r3shop.setId(user.getCust_id());
				r3shop = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(r3shop);
				stocks = super.getKhStocks(r3shop.getR3_code(), goods.getGoods_name(), temp.getStore_id());
				temp.getMap().put("stocks", stocks);
			}
		}

		request.setAttribute("detailsList", detailsList);

		// 初始化页面标签
		request.setAttribute("j_bill_history_list_title",
				super.getMessage(request, "konka.jbill.history.title." + bill_type));
		request.setAttribute("bill_sn_title", super.getMessage(request, "konka.jbill.sn.title." + bill_type));
		request.setAttribute("store_title", super.getMessage(request, "konka.jbill.store.title." + bill_type));
		request.setAttribute("price_title", super.getMessage(request, "konka.jbill.price.title." + bill_type));
		request.setAttribute("goods_money_title",
				super.getMessage(request, "konka.jbill.goods.money.title." + bill_type));
		request.setAttribute("rec_money_title", super.getMessage(request, "konka.jbill.rec.money.title." + bill_type));
		request.setAttribute("money_title", super.getMessage(request, "konka.jbill.money.title." + bill_type));

		return mapping.findForward("input");
	}
}
