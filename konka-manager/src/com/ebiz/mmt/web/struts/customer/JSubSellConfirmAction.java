package com.ebiz.mmt.web.struts.customer;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.JBaseGoods;
import com.ebiz.mmt.domain.JBaseGoodsInitStock;
import com.ebiz.mmt.domain.JBasePartner;
import com.ebiz.mmt.domain.JBaseType;
import com.ebiz.mmt.domain.JBill;
import com.ebiz.mmt.domain.JBillDetails;
import com.ebiz.mmt.domain.JSubSellRec;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * 
 * @author Wu,ShangLong
 * @version 2013-6-17
 */
public class JSubSellConfirmAction extends BaseClientJxcAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String status = (String) dynaBean.get("status");
		
		String opr_date_gt = (String) dynaBean.get("opr_date_gt");// 起始时间
		String opr_date_lt = (String) dynaBean.get("opr_date_lt");// 结束时间
		String partner_name_like = (String) dynaBean.get("partner_name_like");// 分销商名称
		String bill_type = (String) dynaBean.get("bill_type");// 分销类型
		String bill_sn_like = (String) dynaBean.get("bill_sn_like");// 分销单号

		PeProdUser user = (PeProdUser) getSessionAttribute(request, Constants.CUSTOMER_USER_INFO);

		status = null == status ? "0" : status;
		dynaBean.set("status", status);  //默认查询未确认分销订单

		JSubSellRec entity = new JSubSellRec();
		if (StringUtils.isNotBlank(status)) {
			entity.setStatus(Integer.valueOf(status));
		}
		if (StringUtils.isNotBlank(opr_date_gt)) {
			entity.getMap().put("opr_date_gt", opr_date_gt);
		}
		if (StringUtils.isNotBlank(opr_date_lt)) {
			entity.getMap().put("opr_date_lt", opr_date_lt);
		}
		
		//设置查询时间段
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
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
		
		entity.setBuy_partner_id(user.getCust_id());
		
		if(StringUtils.isNotBlank(partner_name_like)){
			entity.getMap().put("partner_name_like", partner_name_like);
		}
		if(StringUtils.isNotBlank(bill_type)){
			entity.getMap().put("bill_type", bill_type);
		}
		if(StringUtils.isNotBlank(bill_sn_like)){
			entity.getMap().put("bill_sn_like", bill_sn_like);
		}

		entity.getMap().put("order_by_add_date_desc", true);
		entity.getMap().put("sell_sn_is_not_value", true);

		Long recordCount = super.getFacade().getJSubSellRecService().getJSubSellRecCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setCount(pager.getRowCount());
		entity.getRow().setFirst(pager.getFirstRow());

		List<JSubSellRec> entityList = super.getFacade().getJSubSellRecService().getJSubSellRecPaginatedList(entity);
		
		if (null != entityList && entityList.size() > 0) {
			for (JSubSellRec temp : entityList) {
				PeProdUser pe_user = new PeProdUser();
				pe_user.setCust_id(temp.getSell_partner_id());
				
				KonkaR3Shop r3shop = new KonkaR3Shop();
				r3shop = super.getKonkaR3ShopById(temp.getSell_partner_id());
				if (null != r3shop) {
					temp.getMap().put("sell_partner_name", r3shop.getCustomer_name());
				}
			}
		}

		request.setAttribute("entityList", entityList);

		return mapping.findForward("list");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String sub_sell_id = (String) dynaBean.get("sub_sell_id");
		String sell_bill_sn = (String) dynaBean.get("sell_bill_sn");
		PeProdUser user = (PeProdUser) getSessionAttribute(request, Constants.CUSTOMER_USER_INFO);

		if (!GenericValidator.isLong(sub_sell_id) || StringUtils.isEmpty(sell_bill_sn)) {
			super.saveError(request, "errors.param");
			return mapping.findForward("list");
		}

		// request.setAttribute("jBasePartners", super.getJBasePartners(null,
		// null, user.getCust_id()));
		request.setAttribute("jBaseStores", super.getJBaseStores(request, 0));
		request.setAttribute("jBaseTypes", super.getJBaseTypes(10001L, user.getCust_id())); // 商品类型

		JSubSellRec subSell = new JSubSellRec();
		subSell.setId(Long.valueOf(sub_sell_id));

		subSell = super.getFacade().getJSubSellRecService().getJSubSellRec(subSell);

		// 网店进货单
		JBill buy_bill = new JBill();

		// 代理商销售单
		JBill sell_bill = new JBill();
		sell_bill.setBill_sn(sell_bill_sn);
		List<JBill> billList = super.getFacade().getJBillService().getJBillList(sell_bill);

		List<JBillDetails> bill_detailsList = new ArrayList<JBillDetails>();
		if (null != billList && billList.size() > 0) {
			buy_bill = billList.get(0);
			JBillDetails details = new JBillDetails();
			details.setBill_id(buy_bill.getBill_id());
			bill_detailsList = super.getFacade().getJBillDetailsService().getJBillDetailsList(details);
		}

		KonkaR3Shop kShop = new KonkaR3Shop();
		kShop.setId(subSell.getSell_partner_id());
		List<KonkaR3Shop> kShopList = super.getFacade().getKonkaR3ShopService().getKonkaR3ShopList(kShop);
		if (kShopList.size() > 0 && kShopList.get(0).getIs_konka() == 1) {
			dynaBean.set("gys_name", kShopList.get(0).getCustomer_name());
			buy_bill.setPartner_id(subSell.getSell_partner_id());
		} else if (kShopList.size() > 0 && kShopList.get(0).getIs_konka() == 0) {
			JBasePartner partner = new JBasePartner();
			partner.setPartner_c_id(subSell.getSell_partner_id());
			// partner.setC_id(subSell.getBuy_partner_id());

			List<JBasePartner> partners = super.getFacade().getJBasePartnerService().getJBasePartnerList(partner);

			dynaBean.set("gys_name", partners.get(0).getPartner_name());
			buy_bill.setPartner_id(partners.get(0).getPartner_id());
		} else {
			super.renderJavaScript(response, "alert('找不到上级分销商，请联系管理员处理！');history.back();");
			return null;
		}

		// JBasePartner partner = new JBasePartner();
		// partner.setPartner_c_id(subSell.getSell_partner_id());
		// // partner.setC_id(subSell.getBuy_partner_id());
		//
		// List<JBasePartner> partners =
		// super.getFacade().getJBasePartnerService().getJBasePartnerList(partner);
		//
		// if (partners.size() > 0) {
		// dynaBean.set("gys_name", partners.get(0).getPartner_name());
		// buy_bill.setPartner_id(partners.get(0).getPartner_id());
		// }

		buy_bill.setBill_type(10);
		buy_bill.setBill_sn(super.getJBillSn("CG"));

		buy_bill.setOpr_date(new Date());
		buy_bill.setAdd_date(new Date());
		buy_bill.setC_id(user.getCust_id());

		super.copyProperties(form, buy_bill);

		if (null != bill_detailsList && bill_detailsList.size() > 0) {
			for (JBillDetails bill_details : bill_detailsList) {
				// 商品类型

				// 商品
				Long sell_goods_id = bill_details.getGoods_id();
				JBaseGoods sellGoods = new JBaseGoods();
				sellGoods.setGoods_id(sell_goods_id);
				sellGoods.setC_id(subSell.getSell_partner_id());

				sellGoods = super.getFacade().getJBaseGoodsService().getJBaseGoods(sellGoods);

				if (null != sellGoods) {
					JBaseGoods buyGood = new JBaseGoods();
					buyGood.setGoods_name(sellGoods.getGoods_name());
					buyGood.setC_id(user.getCust_id());

					List<JBaseGoods> buyGoodList = super.getFacade().getJBaseGoodsService().getJBaseGoodsList(buyGood);// 获取商品名称
					// 商品不存在
					if (null == buyGoodList || buyGoodList.size() == 0) {
						bill_details.setGoods_id(null);
						bill_details.getMap().put("partner_goods_id", sell_goods_id);
						bill_details.getMap().put("goods_name", sellGoods.getGoods_name());
					} else { // 商品存在
						buyGood = buyGoodList.get(0);
						bill_details.setGoods_id(buyGood.getGoods_id());

						bill_details.getMap().put("goods_name", buyGood.getGoods_name());
						bill_details.getMap().put("goods_type", buyGood.getGoods_type());

						JBaseType unit = new JBaseType();
						unit.setType_id(buyGood.getGoods_unit());
						unit = super.getFacade().getJBaseTypeService().getJBaseType(unit);
						bill_details.getMap().put("unit", unit.getType_name());

						JBaseType type = new JBaseType();
						type.setType_id(buyGood.getGoods_type());
						type = super.getFacade().getJBaseTypeService().getJBaseType(type);
						bill_details.getMap().put("type", type.getType_name());
					}
				}

			}

		}

		request.setAttribute("detailsList", bill_detailsList);

		// 初始化页面标签
		request.setAttribute("j_bill_history_list_title", super.getMessage(request, "konka.jbill.history.title.10"));
		request.setAttribute("bill_sn_title", super.getMessage(request, "konka.jbill.sn.title.10"));
		request.setAttribute("store_title", super.getMessage(request, "konka.jbill.store.title.10"));
		request.setAttribute("price_title", super.getMessage(request, "konka.jbill.price.title.10"));
		request.setAttribute("goods_money_title", super.getMessage(request, "konka.jbill.goods.money.title.10"));
		request.setAttribute("rec_money_title", super.getMessage(request, "konka.jbill.rec.money.title.10"));
		request.setAttribute("money_title", super.getMessage(request, "konka.jbill.money.title.10"));

		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		
		String sub_sell_id = (String) dynaBean.get("sub_sell_id");
		String confirm_money = (String) dynaBean.get("confirm_money");
		String[] in_store_ids = request.getParameterValues("in_store_id");
		String[] bill_item_id = request.getParameterValues("bill_item_id");
		String[] goods_names = request.getParameterValues("goods_name");
		String[] nums = request.getParameterValues("num");
		String[] rec_money = request.getParameterValues("rec_money");
		String con_state =  (String) dynaBean.get("con_state");
		String bill_id =  (String) dynaBean.get("bill_id");

		if("1".equals(con_state)){
			PeProdUser user = (PeProdUser) getSessionAttribute(request, Constants.CUSTOMER_USER_INFO);
			JBill jbill = new JBill();
			jbill.setBill_id(Long.parseLong(bill_id));
			jbill = super.getFacade().getJBillService().getJBill(jbill);
			super.copyProperties(jbill, form);
	
			List<JBillDetails> detailsList = new ArrayList<JBillDetails>();
			if (null != bill_item_id) {
				for (int i = 0; i < bill_item_id.length; i++) {
					JBillDetails details = new JBillDetails();
					details.setIn_store_id(Long.valueOf(in_store_ids[i]));
					details.setBill_item_id(Long.valueOf(bill_item_id[i]));
					details.setBill_id(Long.parseLong(bill_id));
					details.setRec_money(BigDecimal.valueOf(Double.parseDouble(rec_money[i])));
					details.getMap().put("goods_name", goods_names[i]);
					details.setNum(Long.valueOf(nums[i]));
					detailsList.add(details);
				}
				jbill.setjBillDetailsList(detailsList);
			}
	
			JSubSellRec subSellRec = new JSubSellRec();
			subSellRec.setId(Long.valueOf(sub_sell_id));
			subSellRec.setConfirm_money(confirm_money);
			subSellRec.setStatus(1);
			super.getFacade().getJSubSellRecService().modifyJSubAndStore(subSellRec, jbill, user.getCust_id(),user.getId());
			super.saveMessage(request, "konka.jbill.insert.success");
		}
		if("2".equals(con_state)){
			JSubSellRec subSellRec = new JSubSellRec();
			subSellRec.setId(Long.valueOf(sub_sell_id));
			subSellRec.setStatus(2);
			super.getFacade().getJSubSellRecService().modifyJSubSellRec(subSellRec);
			super.saveMessage(request, "konka.jbill.insert.success");
		}

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());

		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

}
