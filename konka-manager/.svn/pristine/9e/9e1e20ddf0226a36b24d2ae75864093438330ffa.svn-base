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

import com.ebiz.mmt.domain.ConsumerInfo;
import com.ebiz.mmt.domain.JBaseGoods;
import com.ebiz.mmt.domain.JBasePartner;
import com.ebiz.mmt.domain.JBaseStore;
import com.ebiz.mmt.domain.JBaseType;
import com.ebiz.mmt.domain.JBill;
import com.ebiz.mmt.domain.JBillDetails;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.PePdModel;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.ssi.util.EncryptUtils;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * 
 * @author Wu,ShangLong
 * @version 2013-6-17
 */
public class JSubSellRecAction extends BaseClientJxcAction {

	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return this.add(mapping, form, request, response);
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String bill_type = (String) dynaBean.get("bill_type");
		PeProdUser user = (PeProdUser) getSessionAttribute(request, Constants.CUSTOMER_USER_INFO);

		if (!GenericValidator.isLong(bill_type)) {
			super.saveError(request, "errors.param");
			return mapping.findForward("list");
		}

		if (bill_type != null && bill_type.equals("40")) {// 分销
			dynaBean.set("bill_sn", super.getJBillSn("FX"));
			dynaBean.set("history_list_mod_id", 110020600);
		} else if (bill_type != null && bill_type.equals("42")) {// 分销退货
			dynaBean.set("bill_sn", super.getJBillSn("FXTH"));
			dynaBean.set("history_list_mod_id", 110020600);
		}

		Date today = new Date();
		dynaBean.set("opr_date", today);

		// 默认 折扣为0%
		dynaBean.set("discount", 0);
		
		//默认当前登陆人
		request.setAttribute("add_user_name", user.getUser_name());
		
		//默认单据状态
		dynaBean.set("bill_state", 0);

		request.setAttribute("jBaseTypes", super.getJBaseTypes(10001L, user.getCust_id())); // 商品类型
		request.setAttribute("jBaseStores", super.getJBaseStores(request, 1));

		if (bill_type != null && bill_type.equals("40")) {// 分销
			//request.setAttribute("jBaseGoodsList", super.getJBaseGoodsList(user.getCust_id()));
			request.setAttribute("jBasePartners", getEntityList(user.getCust_id()));
		} else if (bill_type != null && bill_type.equals("42")) {// 分销退货
			JBasePartner jp = new JBasePartner();
			jp.getMap().put("c_id", user.getCust_id());
			jp.getMap().put("bill_type", "40");
			jp.getMap().put("partner_type_value", "1");
			request.setAttribute("jBasePartners", super.getFacade().getJBasePartnerService()
					.getJBasePartnerForBillList(jp));
			//request.setAttribute("jBaseGoodsList", super.getJBaseGoodsList(user.getCust_id(), null));
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

		if (bill_type != null && bill_type.equals("42")) {// 分销退货
			return new ActionForward("/../customer/JSubSellRec/fxth_form.jsp");
		}

		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String bill_id = (String) dynaBean.get("bill_id");
		String[] store_ids = request.getParameterValues("store_id");
		String[] goods_ids = request.getParameterValues("goods_id");
		String[] nums = request.getParameterValues("num");
		String[] prices = request.getParameterValues("price");
		String[] goods_moneys = request.getParameterValues("goods_money");
		String[] dis_moneys = request.getParameterValues("dis_money_d");
		String[] rec_moneys = request.getParameterValues("rec_money_d");
		String[] goods_cost = request.getParameterValues("goods_cost");

		String[] gift_id = request.getParameterValues("gift_id");
		String[] gift_count = request.getParameterValues("gift_count");
		String[] gift_desc = request.getParameterValues("gift_desc");
		String[] notes = request.getParameterValues("notes");

		PeProdUser user = (PeProdUser) getSessionAttribute(request, Constants.CUSTOMER_USER_INFO);
		JBill entity = new JBill();
		super.copyProperties(entity, form);
		
		entity.setAdd_user_id(user.getId());
		entity.setAdd_user_name(user.getUser_name());

		
		String re_stock = "";
		//分销
		if (entity != null && entity.getBill_type() == 40) {
			if (null != goods_ids && goods_ids.length > 0) {
				for (int i = 0; i < goods_ids.length; i++) {
					KonkaR3Shop r3shop = new KonkaR3Shop();
					r3shop.setId(user.getCust_id());
					r3shop = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(r3shop);
					if (r3shop != null) {
						//若不允许负卖，则需查询实时库存
						if (null != r3shop.getIs_minus_sales() && r3shop.getIs_minus_sales() == 1) {
							JBaseGoods jGoods = new JBaseGoods();
							jGoods.setGoods_id(Long.parseLong(goods_ids[i]));
							jGoods.setGoods_stutes(0);
							jGoods.setC_id(user.getCust_id());
							jGoods = super.getFacade().getJBaseGoodsService().getJBaseGoods(jGoods);

							// 获取实时库存
							Long stock = 0L;
							if (null != store_ids[i] && !"".equals(store_ids[i])) {
								stock = super.getKhStocks(r3shop.getR3_code(), jGoods.getGoods_name(),
										Long.valueOf(store_ids[i]));
							}
							// 判断是否是康佳产品
							if (jGoods.getIs_konka() == 1) {
								if (Long.valueOf(nums[i]) > stock) {// 判断销量是否大于库存
									re_stock = re_stock + jGoods.getGoods_name() + "库存不足，请重新选择！\\n";
								}
							}
						}
					}
				}
			}
		}
		//库存不足，则返回
		if (!"".equals(re_stock) && re_stock.length() > 0) {
			super.renderJavaScript(response, "alert('" + re_stock + "');history.back();");
			return null;
		}

		List<JBillDetails> detailsList = new ArrayList<JBillDetails>();
		if (null != goods_ids) {
			for (int i = 0; i < goods_ids.length; i++) {
				JBillDetails details = new JBillDetails();
				details.setGoods_id(Long.valueOf(goods_ids[i]));
				details.setStore_id(Long.valueOf(store_ids[i]));
				
				//分销退货
				if (entity != null && entity.getBill_type() == 42) {
					details.setNum(Long.valueOf(nums[i]));
					if (null != goods_cost && StringUtils.isNotBlank(goods_cost[i])) {
						details.setCost(new BigDecimal(0).subtract(new BigDecimal(goods_cost[i])
								.multiply(new BigDecimal(nums[i]))));
					}
				} 
				else {
					details.setNum(Long.valueOf(nums[i]));
					if (null != goods_cost && StringUtils.isNotBlank(goods_cost[i])) {
						details.setCost(new BigDecimal(goods_cost[i]).multiply(new BigDecimal(nums[i])));
					}
				}
				if (null != prices && StringUtils.isNotBlank(prices[i])) {
					details.setPrice(new BigDecimal(prices[i]));
				}
				if (null != dis_moneys && StringUtils.isNotBlank(dis_moneys[i])) {
					details.setDis_money(new BigDecimal(dis_moneys[i]));
				}
				if (null != rec_moneys && StringUtils.isNotBlank(rec_moneys[i])) {
					details.setRec_money(new BigDecimal(rec_moneys[i]));
				}
				details.setPrice(new BigDecimal(prices[i]));
				if (null != goods_moneys[i]) {
					details.setMoney(new BigDecimal(goods_moneys[i]));
				} else {
					details.setMoney(new BigDecimal(prices[i]).multiply(new BigDecimal(nums[i])));
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
				//分销退货
				if (entity != null && entity.getBill_type() == 42) {
					details.setSale_cost(super.getSaleCost(user.getCust_id(), Long.valueOf(goods_ids[i]),
							Long.valueOf(store_ids[i]), new BigDecimal("0").subtract(new BigDecimal(nums[i]))));
				} 
				else {
					details.setSale_cost(super.getSaleCost(user.getCust_id(), Long.valueOf(goods_ids[i]),
							Long.valueOf(store_ids[i]), new BigDecimal(nums[i])));
				}

				detailsList.add(details);
			}
			entity.setjBillDetailsList(detailsList);
		}

		entity.setC_id(user.getCust_id());
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append("JSubSellRec.do");
		pathBuffer.append("?mod_id=" + mod_id);
		
		//单据id为空，则新增，否则修改订单
		if (StringUtils.isEmpty(bill_id)) {
			super.getFacade().getJBillService().createJBillSubSell(entity);
			super.saveMessage(request, "konka.jbill.insert.success");
			pathBuffer.append("&bill_type=" + entity.getBill_type());
		} else {
			super.getFacade().getJBillService().modifyJBillAndDeatails(entity);
			super.saveMessage(request, "konka.jbill.update.success");
			pathBuffer.append("&method=listForPrint");
		}
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		return forward;
	}

	public ActionForward saveConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String bill_id = (String) dynaBean.get("bill_id");

		PeProdUser user = (PeProdUser) getSessionAttribute(request, Constants.CUSTOMER_USER_INFO);
		JBill entity = new JBill();
		super.copyProperties(entity, form);
		logger.info("money===" + entity.getMoney());

		entity.setC_id(user.getCust_id());
		if (StringUtils.isNotEmpty(bill_id)) {
			entity.setBill_state(1);
			super.getFacade().getJBillService().modifyJBill(entity);
			super.saveMessage(request, "konka.jbill.update.success");
		}
		super.renderJavaScript(response, "window.close();");
		return null;
	}

	/**
	 * 查询分销明细记录
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String export = (String) dynaBean.get("export");
		Pager pager = (Pager) dynaBean.get("pager");
		pager.setPageSize(10);
		
		String opr_date_gt = (String) dynaBean.get("opr_date_gt");// 起始时间
		String opr_date_lt = (String) dynaBean.get("opr_date_lt");// 结束时间
		String bill_sn_like = (String) dynaBean.get("bill_sn_like");// 销售单号
		String bill_type = (String) dynaBean.get("bill_type");
		String partner_name_like = (String) dynaBean.get("partner_name_like");// 网店名称
		String partner_id_like = (String) dynaBean.get("partner_id_like");// 网店编码
		String link_name_like = (String) dynaBean.get("link_name_like");// 联系人
		String type_id = (String) dynaBean.get("type_id");// 商品类型
		String goods_id = (String) dynaBean.get("goods_id");// 机型
		String status = (String) dynaBean.get("status");// 销售单状态
		String wd_state = (String) dynaBean.get("wd_state");// 网点确认状态

		PeProdUser user = (PeProdUser) getSessionAttribute(request, Constants.CUSTOMER_USER_INFO);
		JBillDetails entity = new JBillDetails();

		if (StringUtils.isNotBlank(opr_date_gt)) {
			entity.getMap().put("opr_date_gt", opr_date_gt);
		}
		if (StringUtils.isNotBlank(opr_date_lt)) {
			entity.getMap().put("opr_date_lt", opr_date_lt);
		}
		if (StringUtils.isNotBlank(bill_sn_like)) {
			entity.getMap().put("bill_sn_like", bill_sn_like);
		}
		if (StringUtils.isNotBlank(type_id)) {
			entity.getMap().put("type_id", type_id);
		}
		if (StringUtils.isNotBlank(goods_id)) {
			entity.getMap().put("goods_id", goods_id);
		}
		if (StringUtils.isNotBlank(status)) {
			entity.getMap().put("status", status);
		}
		if (StringUtils.isNotBlank(partner_name_like)) {
			entity.getMap().put("partner_name_like", partner_name_like);
		}
		if (StringUtils.isNotBlank(partner_id_like)) {
			entity.getMap().put("partner_id_like", partner_id_like);
		}
		if (StringUtils.isNotBlank(link_name_like)) {
			entity.getMap().put("link_name_like", link_name_like);
		}
		if (StringUtils.isNotBlank(bill_type)) {
			entity.getMap().put("bill_type", bill_type);
		}
		if (StringUtils.isNotBlank(wd_state)) {
			entity.getMap().put("wd_state", wd_state);
		}
		entity.getMap().put("add_user_id",user.getId());
		

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

		Long recordCount = super.getFacade().getJBillDetailsService().getJBillDetailsForJSubSellRecCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setCount(pager.getRowCount());
		entity.getRow().setFirst(pager.getFirstRow());
		List<JBillDetails> entityList = super.getFacade().getJBillDetailsService()
				.getJBillDetailsForClient(entity);

		request.setAttribute("entityList", entityList);
		request.setAttribute("jBaseTypes", super.getJBaseTypes(10001L, user.getCust_id())); // 商品类型
		request.setAttribute("jBaseGoodsList", super.getJBaseGoodsList(user.getCust_id(), null)); // 机型

		if (StringUtils.isNotBlank(export)) {
			String timestr = exformat.format(calendar.getTime());
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename=" + EncryptUtils.encodingFileName("分销明细-"+timestr)
					+ ".xls");
			entity.getRow().setCount(recordCount.intValue());
			dynaBean.set("export", export);
			List<JBillDetails> entityList1 = super.getFacade().getJBillDetailsService()
					.getJBillDetailsForClient(entity);

			request.setAttribute("entityList1", entityList1);
			return new ActionForward("/../customer/JSubSellRec/fxDetailsExport.jsp");
		}

		return mapping.findForward("list");
	}

	/**
	 * 分销查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward listForPrint(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {// 获取分销查询
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String export = (String) dynaBean.get("export");
		String mod_id = (String) dynaBean.get("mod_id");
		String opr_date_gt = (String) dynaBean.get("opr_date_gt");// 创建起始时间
		String opr_date_lt = (String) dynaBean.get("opr_date_lt");// 创建结束时间
		String bill_sn_like = (String) dynaBean.get("bill_sn_like");// 分销单号
		String partner_name_like = (String) dynaBean.get("partner_name_like");// 网点名称
		String partner_sn_like = (String) dynaBean.get("partner_sn_like");// 网点编码
		String link_name_like = (String) dynaBean.get("link_name_like");// 联系人
		String bill_state = (String) dynaBean.get("bill_state");// 财务确认状态
		String wd_state = (String) dynaBean.get("wd_state");// 网点确认状态

		PeProdUser user = (PeProdUser) getSessionAttribute(request, Constants.CUSTOMER_USER_INFO);
		JBill entity = new JBill();
		super.copyProperties(entity, form);

		if (user != null && user.getCust_id() != null) {
			entity.setC_id(user.getCust_id());
		}
		if (StringUtils.isNotBlank(opr_date_gt)) {
			entity.getMap().put("opr_date_gt", opr_date_gt);
		}
		if (StringUtils.isNotBlank(opr_date_lt)) {
			entity.getMap().put("opr_date_lt", opr_date_lt);
		}
		if (StringUtils.isNotBlank(bill_sn_like)) {
			entity.getMap().put("bill_sn_like", bill_sn_like);
		}
		if (StringUtils.isNotBlank(partner_name_like)) {
			entity.getMap().put("partner_name_like", partner_name_like);
		}
		if (StringUtils.isNotBlank(partner_sn_like)) {
			entity.getMap().put("partner_sn_like", partner_sn_like);
		}
		if (StringUtils.isNotBlank(link_name_like)) {
			entity.getMap().put("link_name_like", link_name_like);
		}
		if (StringUtils.isNotBlank(bill_state)) {
			entity.setBill_state(Integer.parseInt(bill_state));
		}
		if (StringUtils.isNotBlank(wd_state)) {
			entity.getMap().put("wd_state", wd_state);
		}
		entity.getMap().put("order_by_opt_desc", true);

		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat exformat = new SimpleDateFormat("yyyyMMddhhmmss");

		if (StringUtils.isBlank(opr_date_gt) && StringUtils.isBlank(opr_date_lt)) {
			opr_date_lt = dateformat.format(calendar.getTime());// 结束时间
			calendar.add(Calendar.MONTH, -3);
			opr_date_gt = dateformat.format(calendar.getTime());// 开始时间
			entity.getMap().put("opr_date_gt", opr_date_gt);
			entity.getMap().put("opr_date_lt", opr_date_lt);
		}

		dynaBean.set("opr_date_gt", opr_date_gt);
		dynaBean.set("opr_date_lt", opr_date_lt);
		entity.getMap().put("cust_id", user.getCust_id());

		Long recordCount = super.getFacade().getJBillService().getFXJbillCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setCount(pager.getRowCount());
		entity.getRow().setFirst(pager.getFirstRow());
		
		if (StringUtils.isNotBlank(export)) {
			String timestr = exformat.format(calendar.getTime());
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename=" + EncryptUtils.encodingFileName("分销查询-"+timestr)
					+ ".xls");
			entity.getRow().setCount(recordCount.intValue());
			List<JBill> allList = super.getFacade().getJBillService().getFXJbillList(entity);

			request.setAttribute("allList", allList);
			return new ActionForward("/../customer/JSubSellRec/fxExport.jsp");
		}
		
		List<JBill> entityList = super.getFacade().getJBillService().getFXJbillList(entity);

		request.setAttribute("entityList", entityList);
		return new ActionForward("/../customer/JSubSellRec/listForPrint.jsp");
	}
	
	/**
	 * 入库查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward listForConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String export = (String) dynaBean.get("export");
		Pager pager = (Pager) dynaBean.get("pager");
		pager.setPageSize(10);
		
		String opr_date_gt = (String) dynaBean.get("opr_date_gt");// 起始时间
		String opr_date_lt = (String) dynaBean.get("opr_date_lt");// 结束时间
		String bill_sn_like = (String) dynaBean.get("bill_sn_like");// 销售单号
		String bill_type = (String) dynaBean.get("bill_type");
		String partner_name_like = (String) dynaBean.get("partner_name_like");// 网店名称
		String partner_id_like = (String) dynaBean.get("partner_id_like");// 网店编码
		String link_name_like = (String) dynaBean.get("link_name_like");// 联系人
		String type_id = (String) dynaBean.get("type_id");// 商品类型
		String goods_id = (String) dynaBean.get("goods_id");// 机型
		String status = (String) dynaBean.get("status");// 销售单状态
		String wd_state = (String) dynaBean.get("wd_state");// 网点确认状态
		String in_store_id = (String) dynaBean.get("in_store_id");// 入库仓库

		PeProdUser user = (PeProdUser) getSessionAttribute(request, Constants.CUSTOMER_USER_INFO);
		JBillDetails entity = new JBillDetails();

		if (StringUtils.isNotBlank(opr_date_gt)) {
			entity.getMap().put("opr_date_gt", opr_date_gt);
		}
		if (StringUtils.isNotBlank(opr_date_lt)) {
			entity.getMap().put("opr_date_lt", opr_date_lt);
		}
		if (StringUtils.isNotBlank(bill_sn_like)) {
			entity.getMap().put("bill_sn_like", bill_sn_like);
		}
		if (StringUtils.isNotBlank(type_id)) {
			entity.getMap().put("type_id", type_id);
		}
		if (StringUtils.isNotBlank(goods_id)) {
			entity.getMap().put("goods_id", goods_id);
		}
		if (StringUtils.isNotBlank(status)) {
			entity.getMap().put("status", status);
		}
		if (StringUtils.isNotBlank(partner_name_like)) {
			entity.getMap().put("partner_name_like", partner_name_like);
		}
		if (StringUtils.isNotBlank(partner_id_like)) {
			entity.getMap().put("partner_id_like", partner_id_like);
		}
		if (StringUtils.isNotBlank(link_name_like)) {
			entity.getMap().put("link_name_like", link_name_like);
		}
		if (StringUtils.isNotBlank(bill_type)) {
			entity.getMap().put("bill_type", bill_type);
		}
		if (StringUtils.isNotBlank(wd_state)) {
			entity.getMap().put("wd_state", wd_state);
		}
		if (StringUtils.isNotBlank(in_store_id)) {
			entity.setIn_store_id(Long.valueOf(in_store_id));
		}
		entity.getMap().put("cust_id",user.getCust_id());

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

		Long recordCount = super.getFacade().getJBillDetailsService().getJBillDetailsForConfirmCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setCount(pager.getRowCount());
		entity.getRow().setFirst(pager.getFirstRow());
		
		//导出
		if (StringUtils.isNotBlank(export)) {
			String timestr = exformat.format(calendar.getTime());
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename=" + EncryptUtils.encodingFileName("入库查询-"+timestr)
					+ ".xls");
			entity.getRow().setCount(recordCount.intValue());
			dynaBean.set("export", export);
			List<HashMap> allList = super.getFacade().getJBillDetailsService()
					.getJBillDetailsForConfirmList(entity);
			
			request.setAttribute("allList", allList);
			return new ActionForward("/../customer/JSubSellRec/confirm_Export.jsp");
		}
		
		List<HashMap> entityList = super.getFacade().getJBillDetailsService()
				.getJBillDetailsForConfirmList(entity);

		request.setAttribute("entityList", entityList);
		request.setAttribute("jBaseTypes", super.getJBaseTypes(10001L, user.getCust_id())); // 商品类型
		request.setAttribute("jBaseGoodsList", super.getJBaseGoodsList(user.getCust_id(), null)); // 机型
		
		//仓库列表 add by Liang Houen on 2015-06-25
		JBaseStore jbs = new JBaseStore();
		jbs.setC_id(user.getCust_id());
		jbs.setIs_del(0);
		List<JBaseStore> storeList = super.getFacade().getJBaseStoreService().getJBaseStoreList(jbs);
		request.setAttribute("storeList",storeList);


		return new ActionForward("/../customer/JSubSellRec/listForConfirm.jsp");
	}

	public ActionForward print(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {// 打印
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String bill_id = (String) dynaBean.get("bill_id");
		Pager pager = (Pager) dynaBean.get("pager");

		PeProdUser user = (PeProdUser) getSessionAttribute(request, Constants.CUSTOMER_USER_INFO);

		JBill jBill = new JBill();
		JBillDetails jBillDetails = new JBillDetails();
		JBasePartner partner = new JBasePartner();
		if (StringUtils.isNotBlank(bill_id)) {
			jBill.setBill_id(Long.parseLong(bill_id));
			jBillDetails.setBill_id(Long.parseLong(bill_id));
		}
		jBill = super.getFacade().getJBillService().getJBill(jBill);
		
		if(null!=jBill.getSend_type()){
			if(0==jBill.getSend_type()){
				request.setAttribute("check1", true);
				request.setAttribute("check2", false);
			}
			if(1==jBill.getSend_type()){
				request.setAttribute("check2", true);
				request.setAttribute("check1", false);
			}
		}

		Long recordCount = super.getFacade().getJBillDetailsService().getJBillDetailsForJSubSellRecCount(jBillDetails);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		jBillDetails.getRow().setCount(pager.getRowCount());
		jBillDetails.getRow().setFirst(pager.getFirstRow());
		List<JBillDetails> entityList = super.getFacade().getJBillDetailsService()
				.getJBillDetailsForJSubSellRecForExcelList(jBillDetails);
		
		//打印单取第一个商品的仓库
		if(null!=entityList){
			JBaseStore jbs = new JBaseStore();
			jbs.setStore_id(entityList.get(0).getStore_id());
			jbs = super.getFacade().getJBaseStoreService().getJBaseStore(jbs);
			if(null!=jbs){
				request.setAttribute("store_name", jbs.getStore_name());
			}
		}
		
		KonkaR3Shop r3shop = new KonkaR3Shop();
		r3shop = super.getKonkaR3ShopById(user.getCust_id());
		request.setAttribute("sell_cust_name", r3shop.getCustomer_name());

		// partner.setIs_del(0);
		partner.setPartner_id(jBill.getPartner_id());
		partner = super.getFacade().getJBasePartnerService().getJBasePartner(partner);

		request.setAttribute("user", user);
		request.setAttribute("entityList", entityList);
		request.setAttribute("partner", partner);
		request.setAttribute("nowDate", new Date());
		super.copyProperties(form, jBill);

		return new ActionForward("/../customer/JSubSellRec/print.jsp");
	}

	public ActionForward confirm(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {// 打印
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String bill_id = (String) dynaBean.get("bill_id");
		Pager pager = (Pager) dynaBean.get("pager");

		PeProdUser user = (PeProdUser) getSessionAttribute(request, Constants.CUSTOMER_USER_INFO);

		JBill jBill = new JBill();
		JBillDetails jBillDetails = new JBillDetails();
		JBasePartner partner = new JBasePartner();
		if (StringUtils.isNotBlank(bill_id)) {
			jBill.setBill_id(Long.parseLong(bill_id));
			jBillDetails.setBill_id(Long.parseLong(bill_id));
		}
		jBill = super.getFacade().getJBillService().getJBill(jBill);

		Long recordCount = super.getFacade().getJBillDetailsService().getJBillDetailsForJSubSellRecCount(jBillDetails);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		jBillDetails.getRow().setCount(pager.getRowCount());
		jBillDetails.getRow().setFirst(pager.getFirstRow());
		List<JBillDetails> entityList = super.getFacade().getJBillDetailsService()
				.getJBillDetailsForJSubSellRecForExcelList(jBillDetails);

		KonkaR3Shop r3shop = new KonkaR3Shop();
		r3shop = super.getKonkaR3ShopById(user.getCust_id());
		request.setAttribute("sell_cust_name", r3shop.getCustomer_name());

		// partner.setIs_del(0);
		partner.setPartner_id(jBill.getPartner_id());
		partner = super.getFacade().getJBasePartnerService().getJBasePartner(partner);

		request.setAttribute("user", user);
		request.setAttribute("entityList", entityList);
		request.setAttribute("partner", partner);
		request.setAttribute("nowDate", new Date());
		super.copyProperties(form, jBill);

		return new ActionForward("/../customer/JSubSellRec/confirm.jsp");
	}

	public List<JBasePartner> getEntityList(Long cust_id) {

		List<JBasePartner> entityList = new ArrayList<JBasePartner>();

		KonkaR3Shop shop = new KonkaR3Shop();
		shop.setId(cust_id);
		shop = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(shop);

		if (null != shop) {

			JBasePartner entity = new JBasePartner();

			if (shop.getIs_konka() == 0) {
				JBasePartner jp = new JBasePartner();
				jp.setPartner_c_id(cust_id);
				jp.setIs_del(0);
				List<JBasePartner> jpList = super.getFacade().getJBasePartnerService().getJBasePartnerList(jp);
				if (jpList.size() > 0) {
					entity.getMap().put("is_not_konka", jpList.get(0).getPartner_id());
					entity.getMap().put("is_not_self_id", jpList.get(0).getPartner_id());
				} else {
					entity.getMap().put("is_not_konka", -1);
				}

			} else {
				entity.getMap().put("is_konka", cust_id);

				// // 如果是R3客户，增加客户的编号和名称
				// JBasePartner jbp = new JBasePartner();
				// jbp.setPartner_id(cust_id);
				// jbp.setPartner_name(shop.getCustomer_name());
				// entityList.add(jbp);
			}

			entity.setPartner_type(1);
			entity.setPartner_obj(1);
			entity.setIs_del(0);
			List<JBasePartner> entityList1 = super.getFacade().getJBasePartnerService()
					.getJBasePartnerForSonPaginatedList(entity);
			entityList.addAll(entityList1);

		} else {
			entityList = null;
		}
		return entityList;
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

		// request.setAttribute("jBasePartners", super.getJBasePartners("1",
		// null, user.getCust_id()));
		request.setAttribute("jBasePartners", getEntityList(user.getCust_id()));

		JBill entity = new JBill();
		entity.setBill_type(40);
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
		entity.setBill_state(1);
		entity.getMap().put("fxth", 1);

		Long recordCount = getFacade().getJBillService().getJBillForTHCount(entity);
		pager.init(recordCount, 45, pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(45);
		List<HashMap> list = getFacade().getJBillService().getJBillForTH(entity);
		request.setAttribute("entityList", list);
		return new ActionForward("/../customer/JSubSellRec/choose_billsn.jsp");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String bill_id = (String) dynaBean.get("bill_id");
		String bill_sn = (String) dynaBean.get("bill_sn");
		String r_bill_sn = (String) dynaBean.get("r_bill_sn");
		String sub_sell_id = (String) dynaBean.get("sub_sell_id");
		
		PeProdUser user = (PeProdUser) getSessionAttribute(request, Constants.CUSTOMER_USER_INFO);
		
		//分销入库点击查看时，需显示分销商信息
		String bill_confirm = (String) dynaBean.get("bill_confirm");
		
		if (!GenericValidator.isLong(bill_id) && StringUtils.isEmpty(bill_sn) && StringUtils.isEmpty(r_bill_sn)) {
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
		if (StringUtils.isNotBlank(r_bill_sn)) {
			entity.setBill_sn(r_bill_sn);
		}
		entity = super.getFacade().getJBillService().getJBill(entity);
		
		if(null!=entity.getAdd_user_id()){
			PeProdUser puser = new PeProdUser();
			puser.setId(entity.getAdd_user_id());
			puser = super.getFacade().getPeProdUserService().getPeProdUser(puser);
			if(null!=puser){
				entity.getMap().put("add_name", puser.getUser_name());
			}
		}

		JBasePartner partner = new JBasePartner();
		partner.setPartner_id(entity.getPartner_id());

		partner = super.getFacade().getJBasePartnerService().getJBasePartner(partner);

		super.copyProperties(form, entity);
		request.setAttribute("agent", partner);

		JBillDetails details = new JBillDetails();
		details.setBill_id(entity.getBill_id());

		//订单商品详情
		List<JBillDetails> detailsList = super.getFacade().getJBillDetailsService().getJBillDetailsList(details);
		if (null != detailsList && detailsList.size() > 0) {
			for (JBillDetails temp : detailsList) {
				JBaseStore store = new JBaseStore();
				store.setStore_id(temp.getStore_id());
				store = super.getFacade().getJBaseStoreService().getJBaseStore(store);

				temp.getMap().put("store_name", store.getStore_name());
				temp.getMap().put("store_id", store.getStore_id());

				
				//add by Liang Houen on 2015-06-24
				JBaseGoods jbg = new JBaseGoods();
				jbg.setGoods_id(temp.getGoods_id());
				jbg.setC_id(entity.getC_id());
				jbg = super.getFacade().getJBaseGoodsService().getJBaseGoods(jbg);
				if(null!=jbg){
					temp.getMap().put("goods_name", jbg.getGoods_name());
					temp.getMap().put("goods_id", jbg.getGoods_id());
					
					JBaseType type = new JBaseType();
					type.setType_id(jbg.getGoods_unit());
					type = super.getFacade().getJBaseTypeService().getJBaseType(type);
					temp.getMap().put("unit", type.getType_name());
				}
				
			}
		}

		request.setAttribute("detailsList", detailsList);
		
		//当前客户仓库列表
		JBaseStore store1 = new JBaseStore();
		store1.setC_id(user.getCust_id());
		store1.getMap().put("add_date", true);
		List<JBaseStore> slist = super.getFacade().getJBaseStoreService().getJBaseStoreList(store1);
		request.setAttribute("storelist", slist);

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

		if(StringUtils.isNotBlank(bill_confirm)){
			request.setAttribute("bill_confirm", bill_confirm);
			
			//分销入库
			if("1".equals(bill_confirm)){
				request.setAttribute("sub_sell_id", sub_sell_id);
				request.setAttribute("mod_id", mod_id);
				request.setAttribute("c_id", entity.getC_id());
				request.setAttribute("bill_id", entity.getBill_id());
				return new ActionForward("/../customer/JSubSellConfirm/form.jsp");
			}
		}
		return mapping.findForward("view");
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
							if (StringUtils.isNotEmpty(store_id)) {
								stock = super.getKhStocks(r3shop.getR3_code(), jGoods.getGoods_name(),
										Long.parseLong(store_id));
							}
							logger.info("ajax     goods_id=" + goods_id + "   num=" + num + "   store_id=" + store_id
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
	 * 获取选择的网点信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getAgentInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;

		String partner_id = (String) dynaBean.get("partner_id");
		
		JBasePartner entry = new JBasePartner();
		entry.setPartner_id(Long.parseLong(partner_id));
		
		entry = super.getFacade().getJBasePartnerService().getJBasePartner(entry);

		//封装成JSON字符串
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("entry", entry);
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
	 * 分销退货中选择关联销售单号查询网点信息
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
		String bill_sn = (String) dynaBean.get("bill_sn");   //零售退货查询
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
		if(StringUtils.isNotBlank(bill_sn)){
			JBill jb = new JBill();
			jb.setBill_sn(bill_sn);
			jb = super.getFacade().getJBillService().getJBill(jb);
			if(null!=jb){
				jbillDetails.setBill_id(jb.getBill_id());
			}
		}
		
		if(null==jbillDetails.getBill_id()){
			jbillDetails.setBill_id(jbill.getBill_id());
		}

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
	 * 修改分销记录
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String bill_id = (String) dynaBean.get("bill_id");
		Pager pager = (Pager) dynaBean.get("pager");

		PeProdUser user = (PeProdUser) getSessionAttribute(request, Constants.CUSTOMER_USER_INFO);

		JBill jBill = new JBill();
		JBillDetails jBillDetails = new JBillDetails();
		JBasePartner partner = new JBasePartner();
		if (StringUtils.isNotBlank(bill_id)) {
			jBill.setBill_id(Long.parseLong(bill_id));
			jBillDetails.setBill_id(Long.parseLong(bill_id));
		}
		jBill = super.getFacade().getJBillService().getJBill(jBill);

		super.copyProperties(form, jBill);
		
		//查询订单商品列表信息
		JBillDetails jbillDetails = new JBillDetails();
		jbillDetails.setBill_id(jBill.getBill_id());

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
				if(40==jBill.getBill_type()){
					temp.getMap().put("price", temp.getPrice());
				}
				//分销退货，单价取分销单中的单价
				if(42==jBill.getBill_type()){
					JBill fjb = new JBill();
					fjb.setBill_sn(jBill.getR_bill_sn());
					fjb = super.getFacade().getJBillService().getJBill(fjb);
					if(null!=fjb){
						JBillDetails fjbd = new JBillDetails();
						fjbd.setBill_id(fjb.getBill_id());
						fjbd.setGoods_id(temp.getGoods_id());
						fjbd.setStore_id(temp.getStore_id());
						fjbd = super.getFacade().getJBillDetailsService().getJBillDetails(fjbd);
						if(null!=fjbd){
							temp.getMap().put("price", fjbd.getPrice());
						}
					}
				}
				temp.getMap().put("num", temp.getNum());
				if (null != temp.getCost()) {
					temp.getMap().put("goods_cost", temp.getCost().divide(new BigDecimal(temp.getNum().toString())));
				} else {
					temp.getMap().put("goods_cost", "");
				}
				temp.getMap().put("sell_money", temp.getMoney());
				
				// 获取实时库存
				Long stocks = 0L;
				JBaseStore jbs = new JBaseStore();
				jbs.setStore_id(Long.valueOf(store.getStore_id()));
				jbs = super.getFacade().getJBaseStoreService().getJBaseStore(jbs);
				KonkaR3Shop r3shop = new KonkaR3Shop();
				r3shop.setId(user.getCust_id());
				r3shop = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(r3shop);
				logger.info("仓库：" + jbs.getStore_name().toString());
				stocks = super.getKhStocks(r3shop.getR3_code(), goods.getGoods_name(), store.getStore_id());
				temp.getMap().put("stocks", stocks);
			}
			request.setAttribute("jbillDetailsList", jbillDetailsList);
		}
		//查询网点信息
		partner.setPartner_id(jBill.getPartner_id());
		partner = super.getFacade().getJBasePartnerService().getJBasePartner(partner);
		if(null==partner){
			super.renderJavaScript(response, "alert('查询不到关联网点，请联系管理员！');history.back();");
			return null;
		}
		String add_str = "";
		if(null!=partner.getMap().get("PROVINCE")){
			add_str += partner.getMap().get("PROVINCE").toString();
		}
		if(null!=partner.getMap().get("CITY")){
			add_str += partner.getMap().get("CITY").toString();
		}
		if(null!=partner.getMap().get("COUTRY")){
			add_str += partner.getMap().get("COUTRY").toString();
		}
		if(null!=partner.getMap().get("TOWN")){
			add_str += partner.getMap().get("TOWN").toString();
		}
		request.setAttribute("send_addr", add_str);
		request.setAttribute("partner_name", partner.getPartner_name());
		request.setAttribute("partner_id", partner.getPartner_id());
		request.setAttribute("link_name", partner.getLink_name());
		request.setAttribute("link_mobile", partner.getLink_mobile());
		request.setAttribute("add_user_name", jBill.getAdd_user_name());
		request.setAttribute("flag", 1);
		
		
		String bill_type = jBill.getBill_type().toString();
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
		
		request.setAttribute("jBaseTypes", super.getJBaseTypes(10001L, user.getCust_id())); // 商品类型
		request.setAttribute("jBaseStores", super.getJBaseStores(request, 1));

		//根据分销类型跳转到对应页面
		if("40".equals(bill_type)){
			request.setAttribute("jBaseGoodsList", super.getJBaseGoodsList(user.getCust_id()));
			return new ActionForward("/../customer/JSubSellRec/form.jsp");
		}
		if("42".equals(bill_type)){
			request.setAttribute("jBaseGoodsList", super.getJBaseGoodsList(user.getCust_id(), null));
			return new ActionForward("/../customer/JSubSellRec/fxth_form.jsp");
		}
		return null;
	}
	
	/**
	 * 删除分销订单
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward deleteBill(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;

		String bill_id = (String) dynaBean.get("bill_id");
		
		JBill jb = new JBill();
		jb.setBill_id(Long.parseLong(bill_id));
		int res = super.getFacade().getJBillService().removeJBillAndDetails(jb);
		
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
}
