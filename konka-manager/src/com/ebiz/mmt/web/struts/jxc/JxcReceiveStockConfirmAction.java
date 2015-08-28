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

import com.ebiz.mmt.domain.BasePdClass;
import com.ebiz.mmt.domain.JxcPd;
import com.ebiz.mmt.domain.JxcStockBill;
import com.ebiz.mmt.domain.KonkaJxcFhBill;
import com.ebiz.mmt.domain.KonkaJxcFhBillDetails;
import com.ebiz.mmt.domain.KonkaPdTypeJoinPdClass;
import com.ebiz.mmt.domain.StdEntpUser;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Li,Ka
 * @version 2010-10-28
 */
@SuppressWarnings("unused")
public class JxcReceiveStockConfirmAction extends BaseJxcAction {
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

		dynaBean.set("keySeq", keySeq);
		Calendar cal = Calendar.getInstance();
		Calendar f = (Calendar) cal.clone();
		f.clear();
		f.set(Calendar.YEAR, cal.get(Calendar.YEAR));
		f.set(Calendar.MONTH, cal.get(Calendar.MONTH));
		String firstday = new SimpleDateFormat("yyyy-MM-dd").format(f.getTime());
		String today = DateFormatUtils.format(new Date(), "yyyy-MM-dd");

		/*
		 * String supplier_id = (String) dynaBean.get("supplier_id"); request.setAttribute("supplier_id", supplier_id);
		 */
		String is_confirm = (String) dynaBean.get("is_confirm");
		String data_src = (String) dynaBean.get("data_src");
		String add_date_gt = (String) dynaBean.get("add_date_gt");
		String add_date_lt = (String) dynaBean.get("add_date_lt");

		if (add_date_gt == null) {
			add_date_gt = firstday;
		}
		if (add_date_lt == null) {
			add_date_lt = today;
		}
		dynaBean.set("add_date_gt", add_date_gt);
		dynaBean.set("add_date_lt", add_date_lt);
		// 供应商列表
		/*
		 * JxcSupplier jxcSupplier = new JxcSupplier(); jxcSupplier.setShop_id(user.getStdEntpMain().getShop_id());
		 * jxcSupplier.setIs_del(0); List<JxcSupplier> listJxcSupplier =
		 * super.getFacade().getJxcSupplierService().getJxcSupplierList(jxcSupplier);
		 * request.setAttribute("listJxcSupplier", listJxcSupplier);
		 */
		/*
		 * if (StringUtils.isNotEmpty(supplier_id)) { }
		 */
		KonkaJxcFhBill konkaJxcFhBill = new KonkaJxcFhBill();
		super.copyProperties(konkaJxcFhBill, form);
		/*if (null == is_confirm) {
			konkaJxcFhBill.setIs_confirm(0);
			dynaBean.set("is_confirm", "0");
		}*/
		konkaJxcFhBill.getMap().put("start_date", add_date_gt);
		konkaJxcFhBill.getMap().put("end_date", add_date_lt);
		konkaJxcFhBill.setIs_del(0);
		konkaJxcFhBill.getMap().put("shop_id", user.getStdEntpMain().getShop_id());
		Long recordCount = getFacade().getKonkaJxcFhBillService().getKonkaJxcFhBillCount(konkaJxcFhBill);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		konkaJxcFhBill.getRow().setFirst(pager.getFirstRow());
		konkaJxcFhBill.getRow().setCount(pager.getRowCount());
		List<KonkaJxcFhBill> konkaJxcFhBillList = super.getFacade().getKonkaJxcFhBillService()
				.getKonkaJxcFhBillPaginatedList(konkaJxcFhBill);

		request.setAttribute("konkaJxcFhBillList", konkaJxcFhBillList);
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

		String id = (String) dynaBean.get("id");

		if (GenericValidator.isLong(id)) {
			KonkaJxcFhBill entity = new KonkaJxcFhBill();
			entity.setId(Long.valueOf(id));
			entity = super.getFacade().getKonkaJxcFhBillService().getKonkaJxcFhBill(entity);
			if (null == entity) {
				saveMessage(request, "entity.missing");
				return mapping.findForward("list");
			}
			super.copyProperties(form, entity);
			// 取发货登记明细list
			// 回显 发布对象信息
			KonkaJxcFhBillDetails konkaJxcFhBillDetails = new KonkaJxcFhBillDetails();
			konkaJxcFhBillDetails.setFh_bill_id(entity.getId());
			konkaJxcFhBillDetails.getMap().put("is_fenjingban", "true");

			List<KonkaJxcFhBillDetails> konkaJxcFhBillDetailsList = super.getFacade().getKonkaJxcFhBillDetailsService()
					.getKonkaJxcFhBillDetailsWithNamesList(konkaJxcFhBillDetails);
			request.setAttribute("konkaJxcFhBillDetailsList", konkaJxcFhBillDetailsList);
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
		
		String id = (String) dynaBean.get("id");
		
		if (GenericValidator.isLong(id)) {
			KonkaJxcFhBill entity = new KonkaJxcFhBill();
			entity.setId(Long.valueOf(id));
			entity = super.getFacade().getKonkaJxcFhBillService().getKonkaJxcFhBill(entity);
			if (null == entity) {
				saveMessage(request, "entity.missing");
				return mapping.findForward("list");
			}
			super.copyProperties(form, entity);
			dynaBean.set("own_sys", 1);
			// 取发货登记明细list
			// 回显 发布对象信息
			KonkaJxcFhBillDetails konkaJxcFhBillDetails = new KonkaJxcFhBillDetails();
			konkaJxcFhBillDetails.setFh_bill_id(entity.getId());
			konkaJxcFhBillDetails.getMap().put("is_fenjingban", "true");
			
			List<KonkaJxcFhBillDetails> konkaJxcFhBillDetailsList = super.getFacade().getKonkaJxcFhBillDetailsService()
			.getKonkaJxcFhBillDetailsWithNamesList(konkaJxcFhBillDetails);
			request.setAttribute("konkaJxcFhBillDetailsList", konkaJxcFhBillDetailsList);
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
		String[] fh_bill_detail_ids = request.getParameterValues("fh_bill_detail_id");// 所有发货明细ID
		String[] prices = request.getParameterValues("price");// 所有明细对应的进货单价
		String[] counts = request.getParameterValues("fh_bill_detail_count");// 所有明细对应的进货数量

		StdEntpUser user = super.getStdEntpUserFromRequest(request, response, keySeq);
		if (null == user) {
			return null;
		}

		String id = (String) dynaBean.get("id");
		String supplier_name = (String) dynaBean.get("supplier_name");// 供应商名称即发货单的发货人部门
		/*String own_sys = (String) dynaBean.get("own_sys");// 收货产品所属系统
		if (StringUtils.isBlank(own_sys)) {
			String send_error = "请选择收货的所属系统！";
			super.renderJavaScript(response, "alert('" + send_error + "');history.back();");
			return null;
		}*/

		// 收货单中若有型号的大类在关联表konka_pd_type_join_pd_class（konka_base_pd_clazz表与konka_base_pd_type表）中不存在，则不能确认收货
		for (int i = 0; i < fh_bill_detail_ids.length; i++) {
			KonkaJxcFhBillDetails konkaJxcFhBillDetails = new KonkaJxcFhBillDetails();
			konkaJxcFhBillDetails.setId(Long.valueOf(fh_bill_detail_ids[i]));
			konkaJxcFhBillDetails = super.getFacade().getKonkaJxcFhBillDetailsService().getKonkaJxcFhBillDetails(
					konkaJxcFhBillDetails);
			if (null != konkaJxcFhBillDetails) {
				Long pdId = konkaJxcFhBillDetails.getPd_id();// 型号ID

				JxcPd jxcPd = new JxcPd();
				jxcPd.setShop_id(user.getStdEntpMain().getShop_id());
				
				if (pdId.longValue() > 1999999999l && pdId.longValue() < 3000000000l) {// 根据康佳品牌的型号id关联买卖提pe_pd_model表，查询该型号下乡还是非下乡：2开头是下乡  1开头是非下乡，其余都是外部添加的（非下乡：9开头）
					jxcPd.setOwn_sys(1);
				} else {
					jxcPd.setOwn_sys(0);
				}
				
				jxcPd.setOut_sys_id(pdId);
				jxcPd = super.getFacade().getJxcPdService().getJxcPd(jxcPd);
				if (null == jxcPd) {
					//由于客户进销存中大类使用的是买卖提的大类（konka_base_pd_type），而康佳使用的是自己的（konka_base_pd_class），所以二者需要关联对应表（konka_pd_type_join_pd_class）
					BasePdClass basePdClass = new BasePdClass();
					basePdClass.setIs_del(0);
					basePdClass.setCls_id(konkaJxcFhBillDetails.getPd_type_id());
					basePdClass = super.getFacade().getBasePdClassService().getBasePdClass(basePdClass);
					if (null != basePdClass) {
						KonkaPdTypeJoinPdClass konkaPdTypeJoinPdClass = new KonkaPdTypeJoinPdClass();
						//konkaPdTypeJoinPdClass.setCls_id(konkaJxcFhBillDetails.getPd_type_id());
						konkaPdTypeJoinPdClass.setCls_id(basePdClass.getPar_id());
						konkaPdTypeJoinPdClass = super.getFacade().getKonkaPdTypeJoinPdClassService()
								.getKonkaPdTypeJoinPdClass(konkaPdTypeJoinPdClass);
						if (null == konkaPdTypeJoinPdClass) {
							String send_error = "由于发货单中【";
							super.renderJavaScript(response, "alert('" + send_error
									+ konkaJxcFhBillDetails.getPd_type_name() + "】大类不存在，不能确认收货！');history.back();");
							return null;
						}
					}
				}
			}
		}

		if (GenericValidator.isLong(id)) {
			JxcStockBill entity = new JxcStockBill();
			entity.setShop_id(user.getStdEntpMain().getShop_id());
			entity.setShop_p_index(user.getStdEntpMain().getP_index().longValue());
			entity.setAdd_user_id(user.getUser_id());
			
			entity.setStock_src(2);//进货来源：收货确认
			//收货确认的进货单保存发货单号
			KonkaJxcFhBill konkaJxcFhBill = new KonkaJxcFhBill();
			konkaJxcFhBill.setId(Long.valueOf(id));
			konkaJxcFhBill = super.getFacade().getKonkaJxcFhBillService().getKonkaJxcFhBill(konkaJxcFhBill);
			if (null != konkaJxcFhBill) {
				entity.setFh_sn(konkaJxcFhBill.getSn());//发货单号
			}
			
			entity.setOpr_man(user.getUser_name());// 经办人默认为登陆人
			entity.getMap().put("fh_bill_id", Long.valueOf(id));// 发货单ID
			entity.getMap().put("supplier_name", supplier_name);// 供应商名称即发货单的发货人部门
			//entity.getMap().put("own_sys", Integer.valueOf(own_sys));// 产品所属系统
			entity.getMap().put("fh_bill_detail_ids", fh_bill_detail_ids);// 所有发货明细ID
			entity.getMap().put("prices", prices);// 所有明细对应的进货单价
			entity.getMap().put("counts", counts);// // 所有明细对应的进货数量

			super.getFacade().getJxcStockBillService().createJxcStockBillByConfirm(entity);
			saveError(request, "entity.inserted", id);
			return new ActionForward("/JxcReceiveStockConfirm.do?method=list&keySeq=" + keySeq, true);
			// return mapping.findForward("list");
		} else {
			saveError(request, "errors.long", id);
			return mapping.findForward("list");
		}
	}
}
