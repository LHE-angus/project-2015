package com.ebiz.mmt.web.struts.jxc;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.JxcPd;
import com.ebiz.mmt.domain.JxcSellBill;
import com.ebiz.mmt.domain.JxcSellBillDetails;
import com.ebiz.mmt.domain.JxcStockBill;
import com.ebiz.mmt.domain.KonkaBranchCategory;
import com.ebiz.mmt.domain.KonkaPdTypeJoinPdClass;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.StdEntpUser;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Zhang,Xufeng
 * @version 2011-11-15
 */
public class JxcFxConfirmAction extends BaseJxcAction {
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String keySeq = (String) dynaBean.get("keySeq");

		StdEntpUser user = super.getStdEntpUserFromRequest(request, response, keySeq);
		Pager pager = (Pager) dynaBean.get("pager");
		if (null == user) {
			return null;
		}
		Long shop_id = user.getStdEntpMain().getShop_id();//

		dynaBean.set("keySeq", keySeq);
		Calendar cal = Calendar.getInstance();
		Calendar f = (Calendar) cal.clone();
		f.clear();
		f.set(Calendar.YEAR, cal.get(Calendar.YEAR));
		f.set(Calendar.MONTH, cal.get(Calendar.MONTH));
		String firstday = new SimpleDateFormat("yyyy-MM-dd").format(f.getTime());
		String today = DateFormatUtils.format(new Date(), "yyyy-MM-dd");

		String is_confirm = (String) dynaBean.get("is_confirm");
		String sell_date_ge = (String) dynaBean.get("sell_date_ge");
		String sell_date_le = (String) dynaBean.get("sell_date_le");

		if (sell_date_ge == null) {
			sell_date_ge = firstday;
		}
		if (sell_date_le == null) {
			sell_date_le = today;
		}
		dynaBean.set("sell_date_ge", sell_date_ge);
		dynaBean.set("sell_date_le", sell_date_le);

		// 取代理商发给登录用户的 门店销售单
		JxcSellBill jxcSellBill = new JxcSellBill();
		jxcSellBill.setOwn_sys(3);// 所属系统 ： 3-产品分销
		jxcSellBill.setJxs_shop_id(shop_id);// 登录用户的网点id
		super.copyProperties(jxcSellBill, form);

		if (null == is_confirm) {
			jxcSellBill.setIs_confirm(0);
			dynaBean.set("is_confirm", "0");
		}
		jxcSellBill.getMap().put("sell_date_ge", sell_date_ge);
		jxcSellBill.getMap().put("sell_date_le", sell_date_le);
		jxcSellBill.setIs_del(0);
		Long recordCount = getFacade().getJxcSellBillService().getJxcSellBillCount(jxcSellBill);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		jxcSellBill.getRow().setFirst(pager.getFirstRow());
		jxcSellBill.getRow().setCount(pager.getRowCount());
		List<JxcSellBill> jxcSellBillList = super.getFacade().getJxcSellBillService().getJxcSellBillPaginatedList(
				jxcSellBill);
		for (JxcSellBill jsb : jxcSellBillList) {
			// 取代理商,根据登录用户的shop_id在konka_branch_category表中，查类型为20的konka_r3_id所对应的代理商
			KonkaBranchCategory konkaBranchCategory = new KonkaBranchCategory();
			konkaBranchCategory.setCategory_id(20L);
			konkaBranchCategory.setMmt_shop_id(shop_id);
			konkaBranchCategory = getFacade().getKonkaBranchCategoryService().getKonkaBranchCategory(
					konkaBranchCategory);
			KonkaR3Shop konkaR3Shop = new KonkaR3Shop();
			if (null != konkaBranchCategory) {
				konkaR3Shop.setId(konkaBranchCategory.getKonka_r3_id());
				konkaR3Shop.setIs_del(0L);
				konkaR3Shop = getFacade().getKonkaR3ShopService().getKonkaR3Shop(konkaR3Shop);
				if (null != konkaR3Shop) {
					jsb.getMap().put("supplier_name", konkaR3Shop.getCustomer_name());
				}
			}
		}

		request.setAttribute("jxcSellBillList", jxcSellBillList);
		return mapping.findForward("list");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String keySeq = (String) dynaBean.get("keySeq");

		StdEntpUser user = super.getStdEntpUserFromRequest(request, response, keySeq);
		if (null == user) {
			return null;
		}
		Long shop_id = user.getStdEntpMain().getShop_id();//
		String id = (String) dynaBean.get("id");

		if (GenericValidator.isLong(id)) {
			JxcSellBill entity = new JxcSellBill();
			entity.setId(Long.valueOf(id));
			entity = super.getFacade().getJxcSellBillService().getJxcSellBill(entity);
			if (null == entity) {
				saveMessage(request, "entity.missing");
				return mapping.findForward("list");
			}
			super.copyProperties(form, entity);
			// 取代理商,根据登录用户的shop_id在konka_branch_category表中，查类型为20的konka_r3_id所对应的代理商
			KonkaBranchCategory konkaBranchCategory = new KonkaBranchCategory();
			konkaBranchCategory.setCategory_id(20L);
			konkaBranchCategory.setMmt_shop_id(shop_id);
			konkaBranchCategory = getFacade().getKonkaBranchCategoryService().getKonkaBranchCategory(
					konkaBranchCategory);
			KonkaR3Shop konkaR3Shop = new KonkaR3Shop();
			if (null != konkaBranchCategory) {
				konkaR3Shop.setId(konkaBranchCategory.getKonka_r3_id());
				konkaR3Shop.setIs_del(0L);
				konkaR3Shop = getFacade().getKonkaR3ShopService().getKonkaR3Shop(konkaR3Shop);
				if (null != konkaR3Shop) {
					dynaBean.set("supplier_name", konkaR3Shop.getCustomer_name());
				}
			}

			// 取门店销售明细list
			// 回显 发布对象信息
			JxcSellBillDetails jxcSellBillDetails = new JxcSellBillDetails();
			jxcSellBillDetails.setSell_bill_id(entity.getId());
			jxcSellBillDetails.setSell_src(3);// 销售来源 ： 3-产品分销

			List<JxcSellBillDetails> jxcSellBillDetailsList = super.getFacade().getJxcSellBillDetailsService()
					.getJxcSellBillDetailsList(jxcSellBillDetails);
			request.setAttribute("jxcSellBillDetailsList", jxcSellBillDetailsList);
			return mapping.findForward("view");
		} else {
			saveError(request, "errors.long", id);
			return mapping.findForward("list");
		}
	}

	public ActionForward confirm(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String keySeq = (String) dynaBean.get("keySeq");

		StdEntpUser user = super.getStdEntpUserFromRequest(request, response, keySeq);
		if (null == user) {
			return null;
		}
		Long shop_id = user.getStdEntpMain().getShop_id();//
		String id = (String) dynaBean.get("id");

		if (GenericValidator.isLong(id)) {
			JxcSellBill entity = new JxcSellBill();
			entity.setId(Long.valueOf(id));
			entity = super.getFacade().getJxcSellBillService().getJxcSellBill(entity);
			if (null == entity) {
				saveMessage(request, "entity.missing");
				return mapping.findForward("list");
			}
			super.copyProperties(form, entity);
			// 取代理商,根据登录用户的shop_id在konka_branch_category表中，查类型为20的konka_r3_id所对应的代理商
			KonkaBranchCategory konkaBranchCategory = new KonkaBranchCategory();
			konkaBranchCategory.setCategory_id(20L);
			konkaBranchCategory.setMmt_shop_id(shop_id);
			konkaBranchCategory = getFacade().getKonkaBranchCategoryService().getKonkaBranchCategory(
					konkaBranchCategory);
			KonkaR3Shop konkaR3Shop = new KonkaR3Shop();
			if (null != konkaBranchCategory) {
				konkaR3Shop.setId(konkaBranchCategory.getKonka_r3_id());
				konkaR3Shop.setIs_del(0L);
				konkaR3Shop = getFacade().getKonkaR3ShopService().getKonkaR3Shop(konkaR3Shop);
				if (null != konkaR3Shop) {
					// dynaBean.set("supplier_p_index", konkaR3Shop.getP_index());
					dynaBean.set("supplier_id", konkaR3Shop.getId());
					dynaBean.set("supplier_name", konkaR3Shop.getCustomer_name());
				}
			}
			// 取门店销售明细list
			// 回显 发布对象信息
			JxcSellBillDetails jxcSellBillDetails = new JxcSellBillDetails();
			jxcSellBillDetails.setSell_bill_id(entity.getId());
			jxcSellBillDetails.setSell_src(3);// 销售来源 ： 3-产品分销

			List<JxcSellBillDetails> jxcSellBillDetailsList = super.getFacade().getJxcSellBillDetailsService()
					.getJxcSellBillDetailsList(jxcSellBillDetails);
			request.setAttribute("jxcSellBillDetailsList", jxcSellBillDetailsList);
			return mapping.findForward("input");
		} else {
			saveError(request, "errors.long", id);
			return mapping.findForward("list");
		}
	}

	/** 收货确认，检查产品型号是否存在：存在更新其库存，不存在插入型号 */
	public ActionForward saveConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String keySeq = (String) dynaBean.get("keySeq");
		String[] sell_bill_detail_ids = request.getParameterValues("sell_bill_detail_id");// 所有门店销售明细ID
		String[] prices = request.getParameterValues("price");// 所有明细对应的 销售单价
		String[] counts = request.getParameterValues("count");// 所有明细对应的 销售数量

		StdEntpUser user = super.getStdEntpUserFromRequest(request, response, keySeq);
		if (null == user) {
			return null;
		}

		String id = (String) dynaBean.get("id");// 销售单id
		String supplier_id = (String) dynaBean.get("supplier_id");// 供应商id 对应 销售单中的shop_id对应的代理商id
		String supplier_name = (String) dynaBean.get("supplier_name");// 供应商名称 即 门店销售单的门店商铺shop_id对应的代理商名称
		// String supplier_p_index = (String) dynaBean.get("supplier_p_index");// 供应商名称 即 门店销售单的门店商铺shop_id对应的代理商名称
		String own_sys = "3";// 产品分销所属系统
		String stock_src = "3";// 产品分销进货来源
		// if (StringUtils.isBlank(own_sys)) {
		// String send_error = "请选择收货的所属系统！";
		// super.renderJavaScript(response, "alert('" + send_error + "');history.back();");
		// return null;
		// }

		// 收货单中若有型号的大类在关联表konka_pd_type_join_pd_class（konka_base_pd_clazz表与konka_base_pd_type表）中不存在，则不能确认收货
		for (int i = 0; i < sell_bill_detail_ids.length; i++) {
			JxcSellBillDetails jxcSellBillDetails = new JxcSellBillDetails();
			jxcSellBillDetails.setId(Long.valueOf(sell_bill_detail_ids[i]));
			jxcSellBillDetails = super.getFacade().getJxcSellBillDetailsService().getJxcSellBillDetails(
					jxcSellBillDetails);
			if (null != jxcSellBillDetails) {
				Long pdId = jxcSellBillDetails.getPd_id();// 型号ID

				JxcPd jxcPd = new JxcPd();
				jxcPd.setShop_id(user.getStdEntpMain().getShop_id());
				jxcPd.setOwn_sys(Integer.valueOf(own_sys));
				jxcPd.setOut_sys_id(pdId);
				jxcPd = super.getFacade().getJxcPdService().getJxcPd(jxcPd);
				if (null == jxcPd) {
					KonkaPdTypeJoinPdClass konkaPdTypeJoinPdClass = new KonkaPdTypeJoinPdClass();
					konkaPdTypeJoinPdClass.setCls_id(jxcSellBillDetails.getPd_type());
					konkaPdTypeJoinPdClass = super.getFacade().getKonkaPdTypeJoinPdClassService()
							.getKonkaPdTypeJoinPdClass(konkaPdTypeJoinPdClass);
					if (null == konkaPdTypeJoinPdClass) {
						String send_error = "由于产品分销单中【";
						super.renderJavaScript(response, "alert('" + send_error + jxcSellBillDetails.getPd_type_name()
								+ "】大类不存在，不能进行分销确认！');history.back();");
						return null;
					}
				}
			}
		}

		if (GenericValidator.isLong(id)) {
			JxcStockBill entity = new JxcStockBill();
			entity.setShop_id(user.getStdEntpMain().getShop_id());
			entity.setShop_p_index(user.getStdEntpMain().getP_index().longValue());
			entity.setAdd_user_id(user.getUser_id());
			entity.setOpr_man(user.getUser_name());// 经办人默认为登陆人
			entity.setOwn_sys(Integer.valueOf(own_sys)); // 产品所属系统

			JxcSellBill jxcSellBill = new JxcSellBill();
			jxcSellBill.setId(Long.valueOf(id));
			jxcSellBill = getFacade().getJxcSellBillService().getJxcSellBill(jxcSellBill);
			entity.setXs_sn(jxcSellBill.getSn());// 销售单号

			entity.setStock_src(Integer.valueOf(stock_src));// 进货来源

			entity.getMap().put("sell_bill_id", Long.valueOf(id));// 销售单ID
			entity.getMap().put("supplier_name", supplier_name);// 供应商名称
			// entity.getMap().put("supplier_p_index", supplier_p_index);// 供应商
			entity.getMap().put("supplier_id", supplier_id);// 供应商id
			entity.getMap().put("own_sys", Integer.valueOf(own_sys));
			entity.getMap().put("sell_bill_detail_ids", sell_bill_detail_ids);// 所有发货明细ID
			entity.getMap().put("prices", prices);// 所有明细对应的进货单价
			entity.getMap().put("counts", counts);// // 所有明细对应的进货数量

			super.getFacade().getJxcStockBillService().createJxcStockBillByFxConfirm(entity);
			saveError(request, "entity.inserted", id);
			return new ActionForward("/JxcFxConfirm.do?method=list&keySeq=" + keySeq, true);
			// return mapping.findForward("list");
		} else {
			saveError(request, "errors.long", id);
			return mapping.findForward("list");
		}
	}
}
