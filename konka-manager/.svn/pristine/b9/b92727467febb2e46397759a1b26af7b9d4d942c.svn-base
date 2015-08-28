package com.ebiz.mmt.web.struts.jxc;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.BasePdType;
import com.ebiz.mmt.domain.JxcPd;
import com.ebiz.mmt.domain.JxcPdType;
import com.ebiz.mmt.domain.JxcStockBill;
import com.ebiz.mmt.domain.JxcStockBillDetails;
import com.ebiz.mmt.domain.JxcSupplier;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaPdTypeJoinPdClass;
import com.ebiz.mmt.domain.PdModel;
import com.ebiz.mmt.domain.PePdModel;
import com.ebiz.mmt.domain.StdEntpUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Li,Ka
 * @version 2010-10-11
 */
public class JxcStockBillAction extends BaseJxcAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String keySeq = (String) dynaBean.get("keySeq");
		StdEntpUser user = super.getStdEntpUserFromRequest(request, response, keySeq);
		if (null == user) {
			return null;
		}

		Calendar cal = Calendar.getInstance();
		Calendar f = (Calendar) cal.clone();
		f.clear();
		f.set(Calendar.YEAR, cal.get(Calendar.YEAR));
		f.set(Calendar.MONTH, cal.get(Calendar.MONTH));
		String firstday = new SimpleDateFormat("yyyy-MM-dd").format(f.getTime());
		String today = DateFormatUtils.format(new Date(), "yyyy-MM-dd");

		dynaBean.set("keySeq", keySeq);
		JxcSupplier jxcSupplier = new JxcSupplier();
		jxcSupplier.setShop_id(user.getStdEntpMain().getShop_id());
		jxcSupplier.setIs_del(0);
		List<JxcSupplier> listJxcSupplier = super.getFacade().getJxcSupplierService().getJxcSupplierList(jxcSupplier);
		request.setAttribute("listJxcSupplier", listJxcSupplier);
		request.setAttribute("add_date_gt", firstday);
		request.setAttribute("add_date_lt", today);
		BigDecimal countMoney = new BigDecimal(0);
		request.setAttribute("countMoney", countMoney);

		return mapping.findForward("list");
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String keySeq = (String) dynaBean.get("keySeq");
		request.setAttribute("not_validate_record", "true");
		StdEntpUser user = super.getStdEntpUserFromRequest(request, response, keySeq);
		Pager pager = (Pager) dynaBean.get("pager");
		if (null == user) {
			return null;
		}

		Calendar cal = Calendar.getInstance();
		Calendar f = (Calendar) cal.clone();
		f.clear();
		f.set(Calendar.YEAR, cal.get(Calendar.YEAR));
		f.set(Calendar.MONTH, cal.get(Calendar.MONTH));
		String firstday = new SimpleDateFormat("yyyy-MM-dd").format(f.getTime());
		String today = DateFormatUtils.format(new Date(), "yyyy-MM-dd");

		dynaBean.set("keySeq", keySeq);
		String supplier_id = (String) dynaBean.get("supplier_id");
		String add_date_gt = (String) dynaBean.get("add_date_gt");
		String add_date_lt = (String) dynaBean.get("add_date_lt");

		if (add_date_gt == null) {
			add_date_gt = firstday;
		}
		if (add_date_lt == null) {
			add_date_lt = today;
		}

		JxcSupplier jxcSupplier = new JxcSupplier();
		jxcSupplier.setShop_id(user.getStdEntpMain().getShop_id());
		jxcSupplier.setIs_del(0);
		List<JxcSupplier> listJxcSupplier = super.getFacade().getJxcSupplierService().getJxcSupplierList(jxcSupplier);
		request.setAttribute("listJxcSupplier", listJxcSupplier);

		JxcStockBill jxcStockBill = new JxcStockBill();
		if (StringUtils.isNotEmpty(supplier_id)) {
			jxcStockBill.setSupplier_id(Long.valueOf(supplier_id));
		}
		jxcStockBill.getMap().put("add_date_gt", add_date_gt);
		jxcStockBill.getMap().put("add_date_lt", add_date_lt);
		jxcStockBill.setIs_del(0);
		jxcStockBill.setShop_id(user.getStdEntpMain().getShop_id());
		request.setAttribute("add_date_lt", add_date_lt);
		request.setAttribute("add_date_gt", add_date_gt);
		request.setAttribute("supplier_id", supplier_id);

		Long recordCount = super.getFacade().getJxcStockBillService().getJxcStockBillCount(jxcStockBill);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		jxcStockBill.getRow().setFirst(pager.getFirstRow());
		jxcStockBill.getRow().setCount(pager.getRowCount());

		List<JxcStockBill> listJxcStockBill = super.getFacade().getJxcStockBillService()
				.getJxcStockBillPaginatedListWithSname(jxcStockBill);
		request.setAttribute("listJxcStockBill", listJxcStockBill);
		BigDecimal countMoney = new BigDecimal(0);
		for (int i = 0; i < listJxcStockBill.size(); i++) {
			countMoney = countMoney.add(listJxcStockBill.get(i).getPaid_money());
		}
		request.setAttribute("countMoney", countMoney);

		return mapping.findForward("list");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		DynaBean dynaBean = (DynaBean) form;
		String keySeq = (String) dynaBean.get("keySeq");
		request.setAttribute("not_validate_record", "true");
		StdEntpUser user = super.getStdEntpUserFromRequest(request, response, keySeq);
		if (null == user) {
			return null;
		}
		dynaBean.set("keySeq", keySeq);
		dynaBean.set("own_sys", "0");// 默认所属系统
		request.setAttribute("userName", user.getUser_name());// 默认经办人为登录用户

		BasePdType basePdType = new BasePdType();
		basePdType.setDel_mark(new Short((short) 0));
		basePdType.setIs_model(new Short((short) 1));
		List<BasePdType> basePdTypeList = super.getFacade().getBasePdTypeService().getBasePdTypeList(basePdType);
		request.setAttribute("basePdTypeList", basePdTypeList);// 产品大类

		JxcPdType jxcPdType = new JxcPdType();
		List<JxcPdType> JxcPdTypeList = getFacade().getJxcPdTypeService().getJxcPdTypeList(jxcPdType);
		request.setAttribute("JxcPdTypeList", JxcPdTypeList);// 根据Shop_id查进销存自定义产品大类

		JxcSupplier jxcSupplier = new JxcSupplier();
		jxcSupplier.setShop_id(user.getStdEntpMain().getShop_id());
		jxcSupplier.setIs_del(0);
		List<JxcSupplier> listJxcSupplier = super.getFacade().getJxcSupplierService().getJxcSupplierList(jxcSupplier);
		request.setAttribute("listJxcSupplier", listJxcSupplier);
		request.setAttribute("sn", "JH" + DateFormatUtils.format(new Date(), "yyyyMMddHHmmssSSS"));// 生成固定格式的进货单编号
		Date today = new Date();
		request.setAttribute("today", DateFormatUtils.format(today, "yyyy-MM-dd"));
		return mapping.findForward("input");
	}

	/** 保存进货登记单 */
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws ParseException {
		DynaBean dynaBean = (DynaBean) form;
		String keySeq = (String) dynaBean.get("keySeq");
		request.setAttribute("not_validate_record", "true");
		StdEntpUser user = super.getStdEntpUserFromRequest(request, response, keySeq);
		if (null == user) {
			return null;
		}
		Long shop_id = user.getStdEntpMain().getShop_id();
		dynaBean.set("keySeq", keySeq);
		String sn = (String) dynaBean.get("sn");// 进货单号
		String in_date = (String) dynaBean.get("in_date");// 进货单号
		String own_sys = (String) dynaBean.get("own_sys");// 所属系统
		String pd_type = (String) dynaBean.get("pd_type");// 基础大类id
		String jxc_pd_type_id = (String) dynaBean.get("jxc_pd_type_id");// 进销存大类id
		String brand_id = (String) dynaBean.get("brand_id");// 品牌id
		String brand_name = (String) dynaBean.get("brand_name");// 品牌名称
		String jxc_supplier_id = (String) dynaBean.get("jxc_supplier_id");// 供应商id
		String stock_remarks = (String) dynaBean.get("stock_remarks");// 进货单说明
		String opr_man = (String) dynaBean.get("opr_man");// 经办人
		String pay_money = (String) dynaBean.get("pay_money");// 应收金额
		String paid_money = (String) dynaBean.get("paid_money");// 实付金额
		String name = "";

		// 判断如果是康佳供应商是否能找到上级代理商
		if (StringUtils.equals(String.valueOf(Constants.KONKA_BRAND_ID), brand_id)) {
			HashMap<String, String> result = super.getKonkaDeptIdAndType(shop_id, true);
			if ("0".endsWith(result.get("result_code"))) {
				String send_error = "您选择的康佳品牌，由于您的门店找不到上级部门信息无法保存！";
				super.renderJavaScript(response, "alert('" + send_error + "');history.back();");
				return null;
			}
			String dept_ids = result.get("r3_dept_ids");

			KonkaDept konkaDept = new KonkaDept();
			konkaDept.getMap().put("dept_ids", dept_ids);
			konkaDept.getMap().put("dept_types", "3,4,5");
			konkaDept.getMap().put("order_by_dept_type_desc", "true");
			List<KonkaDept> konkaDeptList = getFacade().getKonkaDeptService().getKonkaDeptList(konkaDept);
			if (null == konkaDeptList || konkaDeptList.size() == 0) {
				String send_error = "您选择的康佳品牌，由于您的门店找不到上级部门信息无法保存！";
				super.renderJavaScript(response, "alert('" + send_error + "');history.back();");
				return null;
			}

			String customer_name_ = konkaDeptList.get(0).getDept_name();
			JxcSupplier supplier = new JxcSupplier();
			supplier.setShop_id(shop_id);
			supplier.setName(customer_name_);
			supplier.setIs_del(0);
			supplier = getFacade().getJxcSupplierService().getJxcSupplier(supplier);
			if (null == supplier) {// insert
				JxcSupplier newSupplier = new JxcSupplier();
				newSupplier.setCur_pay(BigDecimal.valueOf(0));
				newSupplier.setName(customer_name_);
				newSupplier.setAdd_user_id(user.getUser_id());
				newSupplier.setShop_id(shop_id);
				newSupplier.setRemarks("康佳R3网点上级部门");
				Long spId = getFacade().getJxcSupplierService().createJxcSupplier(newSupplier);
				jxc_supplier_id = spId.toString();
			} else {
				jxc_supplier_id = supplier.getId().toString();
			}
		}

		String[] pd_ids = request.getParameterValues("pd_ids");
		String[] units = request.getParameterValues("units");
		String[] init_counts = request.getParameterValues("init_counts");
		String[] jh_counts = request.getParameterValues("jh_counts");
		String[] jh_prices = request.getParameterValues("jh_prices");
		String[] remarks = request.getParameterValues("remarks");

		BigDecimal pay_money_calc = new BigDecimal("0");
		List<JxcStockBillDetails> jxcStockBillDetailsList = new ArrayList<JxcStockBillDetails>();
		if (ArrayUtils.isNotEmpty(pd_ids)) {
			for (int i = 1; i < pd_ids.length; i++) {
				String temp_pd_id = pd_ids[i];
				if (StringUtils.isBlank(temp_pd_id)) {
					continue;
				}
				String pd_id = "";
				String out_sys_id = "";
				if (StringUtils.contains(temp_pd_id, "pd_id")) {
					pd_id = StringUtils.split(temp_pd_id, ":")[1];
				}
				if (StringUtils.contains(temp_pd_id, "out_sys_id")) {
					out_sys_id = StringUtils.split(temp_pd_id, ":")[1];
				}
				// 门店产品型号
				JxcPd jxcPd = new JxcPd();
				jxcPd.setShop_id(shop_id);
				jxcPd.setOwn_sys(Integer.valueOf(own_sys));
				if (StringUtils.isNotBlank(pd_id)) {
					jxcPd.setId(Long.valueOf(pd_id));// 常用产品型号id（对应jxc_pd表ID）
				} else if (StringUtils.isNotBlank(out_sys_id)) {// 买卖提产品型号id（对应jxc_pd表out_sys_id）

					// 康佳从 KONKA_PE_PD_MODEL表中查型号
					if (StringUtils.equals(String.valueOf(Constants.KONKA_BRAND_ID), brand_id)) {
						PePdModel pePdModel = new PePdModel();
						pePdModel.setPd_id(Long.valueOf(out_sys_id));
						pePdModel = getFacade().getPePdModelService().getPePdModel(pePdModel);
						name = pePdModel.getMd_name();
					} else {// 非康佳从 chea_fill.pd_model表中查型号
						PdModel pdModel = new PdModel();
						pdModel.setPd_id(Long.valueOf(out_sys_id));
						pdModel = getFacade().getPdModelService().getPdModel(pdModel);
						name = pdModel.getMd_name();// 取型号名称
					}
					if (jxcPd.getOwn_sys().intValue() == 1) {// 如果是家电下乡，通过shop_id own_sys out_sys_id 判断是否存在
						jxcPd.setOut_sys_id(Long.valueOf(out_sys_id));
					} else {// 如果是非家电下乡，通过shop_id own_sys name 判断是否存在
						jxcPd.setName(name);
					}
				}

				JxcPd entity = super.getFacade().getJxcPdService().getJxcPd(jxcPd);
				if (null != entity) {// 有库存时，检查退货数量是否超过库存
					jxcPd = entity;
					jxcPd.getMap().put("updateJxcPd", "true");
					Long totalCount = entity.getCount() + Long.valueOf(jh_counts[i]);
					if (totalCount.intValue() < 0) {
						String send_error = "您输入的退货数量，导致该产品[" + jxcPd.getName() + "]库存不足，请重新输入！";
						super.renderJavaScript(response, "alert('" + send_error + "');history.back();");
						return null;
					}
				} else {// 无该型号，不能退货
					jxcPd.getMap().put("insertJxcPd", "true");
					if (StringUtils.isBlank(out_sys_id)) {
						String send_error = "您选择产品型号！";
						super.renderJavaScript(response, "alert('" + send_error + "');history.back();");
						return null;
					}
					jxcPd.setOut_sys_id(Long.valueOf(out_sys_id));
					if (Long.valueOf(jh_counts[i]).intValue() < 0) {
						String send_error = "您选择该产品型号该商铺没有库存不能退货，请重新输入！";
						super.renderJavaScript(response, "alert('" + send_error + "');history.back();");
						return null;
					}
				}
				jxcPd.setRef_price(new BigDecimal(jh_prices[i]));
				if (null != jxcPd.getMap().get("insertJxcPd")) {// 添加时
					jxcPd.setPd_type(Long.valueOf(pd_type));
					String pdTypeName = ""; // 大类名称
					if (StringUtils.equals("0", pd_type) && StringUtils.isNotBlank(jxc_pd_type_id)) {// 大类选择"其他",查询自定义大类
						JxcPdType jxcPdType = new JxcPdType();
						jxcPdType.setId(Long.valueOf(jxc_pd_type_id));
						pdTypeName = getFacade().getJxcPdTypeService().getJxcPdType(jxcPdType).getName();
						jxcPd.setJxc_pd_type_id(Long.valueOf(jxc_pd_type_id));
					} else {
						BasePdType basePdType = new BasePdType();
						basePdType.setPd_type(Long.valueOf(pd_type));
						pdTypeName = getFacade().getBasePdTypeService().getBasePdType(basePdType).getPd_name();
					}
					jxcPd.setPd_type_name(pdTypeName);
					jxcPd.setShop_p_index(user.getStdEntpMain().getP_index().longValue());
					jxcPd.setAdd_user_id(user.getUser_id());
					jxcPd.setBrand_id(Long.valueOf(brand_id));
					jxcPd.setBrand_name(brand_name);
					jxcPd.setName(name);
					jxcPd.setInit_count(Long.valueOf(init_counts[i]));
					jxcPd.setInit_cost_price(jxcPd.getRef_price());
					jxcPd.setCount(jxcPd.getInit_count() + Long.valueOf(jh_counts[i]));// 实时库存
					jxcPd.setCur_cost_price(jxcPd.getRef_price());// 实时成本价
					jxcPd.setUnit(units[i]);
					jxcPd.setPrice(new BigDecimal(jh_prices[i]));
					jxcPd.setPf_price(new BigDecimal(jh_prices[i]));
				}

				// 进货单明细
				JxcStockBillDetails jxcStockBillDetails = new JxcStockBillDetails();
				jxcStockBillDetails.setShop_id(shop_id);
				if (null != jxcPd.getJxc_pd_type_id()) {// 大类选择"其他",查询自定义大类
					jxcStockBillDetails.setPd_type(jxcPd.getJxc_pd_type_id());
				} else {
					jxcStockBillDetails.setPd_type(jxcPd.getPd_type());
				}
				jxcStockBillDetails.setPd_type_name(jxcPd.getPd_type_name());
				jxcStockBillDetails.setBrand_id(jxcPd.getBrand_id());
				jxcStockBillDetails.setBrand_name(jxcPd.getBrand_name());
				jxcStockBillDetails.setPd_name(jxcPd.getName());// 型号名称
				jxcStockBillDetails.setCount(Long.valueOf(jh_counts[i]));
				jxcStockBillDetails.setPrice(new BigDecimal(jh_prices[i]));
				jxcStockBillDetails.setRemarks(remarks[i]);
				if (StringUtils.isBlank(pay_money)) {// 防止由于浏览器历史记忆选择时，该参数为空
					pay_money_calc.add(new BigDecimal(jxcStockBillDetails.getCount().doubleValue()
							* jxcStockBillDetails.getPrice().doubleValue()));
				}

				jxcStockBillDetails.setJxcPd(jxcPd);// 添加产品信息到进货单明细中,方面后台插入pd_id
				jxcStockBillDetailsList.add(jxcStockBillDetails);// 添加进货单明细
			}
		}

		// 进货单
		JxcStockBill jxcStockBill = new JxcStockBill();
		jxcStockBill.setShop_id(user.getStdEntpMain().getShop_id());
		jxcStockBill.setShop_p_index(user.getStdEntpMain().getP_index().longValue());
		jxcStockBill.setAdd_user_id(user.getUser_id());
		jxcStockBill.setSn(sn);// 设置进货单号
		jxcStockBill.setIn_date(DateUtils.parseDate(in_date, new String[] { "yyyy-MM-dd" }));// 设置进货日期
		jxcStockBill.setRemarks(stock_remarks);
		jxcStockBill.setOpr_man(opr_man);
		if (StringUtils.isNotBlank(pay_money)) {
			jxcStockBill.setPay_money(new BigDecimal(pay_money));
		} else {// 防止由于浏览器历史记忆选择时，该参数为空
			jxcStockBill.setPay_money(pay_money_calc);
		}
		jxcStockBill.setPaid_money(new BigDecimal(paid_money));
		jxcStockBill.setJxcStockBillDetailsList(jxcStockBillDetailsList);// 进货单明细
		jxcStockBill.setSupplier_id(Long.valueOf(jxc_supplier_id));
		jxcStockBill.setStock_src(1);// 进货来源：进货

		JxcSupplier jxcSupplier = new JxcSupplier();
		jxcSupplier.setId(Long.valueOf(jxc_supplier_id));
		jxcSupplier = super.getFacade().getJxcSupplierService().getJxcSupplier(jxcSupplier);
		if (null != jxcSupplier) {
			jxcSupplier.setCur_pay(jxcSupplier.getCur_pay().add(
					jxcStockBill.getPay_money().subtract(jxcStockBill.getPaid_money())));// 当前应付款=更新前当前应付款+（应付金额-本次付款）
			jxcStockBill.setJxcSupplier(jxcSupplier);// 更新供应商信息
		}

		getFacade().getJxcStockBillService().createJxcStockBill(jxcStockBill);

		saveMessage(request, "entity.inserted");
		return new ActionForward("/JxcStockBill.do?method=list&keySeq=" + keySeq, true);
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String keySeq = (String) dynaBean.get("keySeq");

		request.setAttribute("not_validate_record", "true");
		StdEntpUser user = super.getStdEntpUserFromRequest(request, response, keySeq);
		if (null == user) {
			return null;
		}
		dynaBean.set("keySeq", keySeq);

		if (StringUtils.isNotBlank(id)) {
			// 进货登记单
			JxcStockBill jxcStockBill = new JxcStockBill();
			jxcStockBill.setId(Long.valueOf(id));
			jxcStockBill = super.getFacade().getJxcStockBillService().getJxcStockBill(jxcStockBill);

			// 进货明细
			JxcStockBillDetails jxcStockBillDetails = new JxcStockBillDetails();
			jxcStockBillDetails.setStock_bill_id(Long.valueOf(id));
			List<JxcStockBillDetails> listJxcStockBillDetails = getFacade().getJxcStockBillDetailsService()
					.getJxcStockBillDetailsList(jxcStockBillDetails);
			jxcStockBill.setJxcStockBillDetailsList(listJxcStockBillDetails);

			if (listJxcStockBillDetails.size() > 0) {
				JxcPd jxcPd = new JxcPd();
				jxcPd.setId(listJxcStockBillDetails.get(0).getPd_id());
				jxcPd = getFacade().getJxcPdService().getJxcPd(jxcPd);
				if (null != jxcPd) {
					jxcStockBill.getMap().put("curr_count", jxcPd.getCount());// 该产品当前库存
				}
			}

			// 供应商信息
			JxcSupplier jxcSupplier = new JxcSupplier();
			jxcSupplier.setId(jxcStockBill.getSupplier_id());
			jxcSupplier = getFacade().getJxcSupplierService().getJxcSupplier(jxcSupplier);
			if (null != jxcSupplier) {
				jxcStockBill.getMap().put("supplier_id", jxcSupplier.getId());// 供应商名称
				jxcStockBill.getMap().put("supplier_name", jxcSupplier.getName());// 供应商名称
			}

			super.copyProperties(form, jxcStockBill);
			// dynaBean.set("canNotEdit", "canNotEdit");
			// return mapping.findForward("input");
			return new ActionForward("/JxcStockBill/editForm.jsp");
		} else {
			saveError(request, "errors.long", id);
			return mapping.findForward("list");
		}
	}

	/** 保存更新进货单 */
	public ActionForward saveUpdate(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String stock_bill_details_id = (String) dynaBean.get("stock_bill_details_id");// 进货明细ID
		String supplier_id = (String) dynaBean.get("supplier_id");// 供应商ID
		String count = (String) dynaBean.get("count");// 进货数量
		String price = (String) dynaBean.get("price");// 进货单件
		String paid_money = (String) dynaBean.get("paid_money");// 实付金额
		String keySeq = (String) dynaBean.get("keySeq");

		if (StringUtils.isNotBlank(id)) {
			JxcStockBill jxcStockBill = new JxcStockBill();
			jxcStockBill.setId(new Long(id));
			jxcStockBill.setPay_money(new BigDecimal(count).multiply(new BigDecimal(price)));// 本次应付款
			jxcStockBill.setPaid_money(new BigDecimal(paid_money));

			JxcStockBillDetails jxcStockBillDetails = new JxcStockBillDetails();
			jxcStockBillDetails.setId(new Long(stock_bill_details_id));
			jxcStockBillDetails.setCount(new Long(count));
			jxcStockBillDetails.setPrice(new BigDecimal(price));
			List<JxcStockBillDetails> listJxcStockBillDetails = new ArrayList<JxcStockBillDetails>();
			listJxcStockBillDetails.add(jxcStockBillDetails);
			jxcStockBill.setJxcStockBillDetailsList(listJxcStockBillDetails);

			JxcSupplier jxcSupplier = new JxcSupplier();
			jxcSupplier.setId(new Long(supplier_id));
			List<JxcSupplier> listJxcSupplier = new ArrayList<JxcSupplier>();
			listJxcSupplier.add(jxcSupplier);
			jxcStockBill.setListJxcSupplier(listJxcSupplier);

			getFacade().getJxcStockBillService().modifyJxcStockBillWithDetailsAndSupplier(jxcStockBill);

			saveMessage(request, "entity.updated");
			return new ActionForward("/JxcStockBill.do?method=list&keySeq=" + keySeq, true);
		} else {
			saveError(request, "errors.long", id);
			return mapping.findForward("list");
		}
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		DynaBean dynaBean = (DynaBean) form;
		String keySeq = (String) dynaBean.get("keySeq");
		StdEntpUser user = super.getStdEntpUserFromRequest(request, response, keySeq);
		if (null == user) {
			return null;
		}
		dynaBean.set("keySeq", keySeq);

		// 进货登记单
		String stock_bill_id = (String) dynaBean.get("stock_bill_id");
		JxcStockBill jxcStockBill = new JxcStockBill();
		jxcStockBill.setId(Long.valueOf(stock_bill_id));
		jxcStockBill = super.getFacade().getJxcStockBillService().getJxcStockBill(jxcStockBill);

		// 进货明细
		JxcStockBillDetails jxcStockBillDetails = new JxcStockBillDetails();
		jxcStockBillDetails.setStock_bill_id(Long.valueOf(stock_bill_id));
		List<JxcStockBillDetails> jxcStockBillDetailsList = getFacade().getJxcStockBillDetailsService()
				.getJxcStockBillDetailsList(jxcStockBillDetails);
		request.setAttribute("jxcStockBillDetailsList", jxcStockBillDetailsList);

		// 供应商信息
		JxcSupplier jxcSupplier = new JxcSupplier();
		jxcSupplier.setId(jxcStockBill.getSupplier_id());
		jxcSupplier = getFacade().getJxcSupplierService().getJxcSupplier(jxcSupplier);
		if (null != jxcSupplier) {
			jxcStockBill.getMap().put("supplier_name", jxcSupplier.getName());
		}

		super.copyProperties(form, jxcStockBill);
		return mapping.findForward("view");
	}

	/** 根据产品大类、品牌查询买卖提的型号 */
	public ActionForward getPdModel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String pd_type = (String) dynaBean.get("pd_type");
		String brand_id = (String) dynaBean.get("brand_id");
		String own_sys = (String) dynaBean.get("own_sys");// 0:非下乡  1：下乡
		String keySeq = (String) dynaBean.get("keySeq");

		request.setAttribute("not_validate_record", "true");
		StdEntpUser user = super.getStdEntpUserFromRequest(request, response, keySeq);
		if (null == user) {
			return null;
		}
		Long shop_id = user.getStdEntpMain().getShop_id();

		if (StringUtils.isNotBlank(brand_id) && StringUtils.isNotBlank(pd_type)) {
			StringBuffer sb = new StringBuffer("[");
			if (StringUtils.equals(brand_id, Constants.KONKA_BRAND_ID.toString())) {// 康佳产品取产品型号只根据部门Id(产品需要部门授权了产品才能查到)
				HashMap<String, String> result = this.getKonkaDeptIdAndType(shop_id, true);
				if (!"0".equals(result.get("result_code"))) {
					Long parClsId = null;
					KonkaPdTypeJoinPdClass konkaPdTypeJoinPdClass = new KonkaPdTypeJoinPdClass();
					konkaPdTypeJoinPdClass.setPd_type(Long.valueOf(pd_type));
					konkaPdTypeJoinPdClass = getFacade().getKonkaPdTypeJoinPdClassService().getKonkaPdTypeJoinPdClass(konkaPdTypeJoinPdClass);
					if (null != konkaPdTypeJoinPdClass) {
						parClsId = konkaPdTypeJoinPdClass.getCls_id();
					}
					
					//List<PePdModel> ppmList = super.getPePdModelListByDeptIds(result.get("r3_dept_ids"));
					List<PePdModel> ppmList = super.getPePdModelListByDeptIdsAndOwnSys(result.get("r3_dept_ids"), Integer.valueOf(own_sys), Long.valueOf(brand_id), parClsId);

					/*String isXX = "";
					if (StringUtils.equals("0", own_sys)) {
						isXX = "非下乡:";
					} else {
						isXX = "下乡:";
					}*/
					for (PePdModel ppm : ppmList) {
						sb.append("{\"pd_id\":\"" + ppm.getPd_id() + "\",");
						sb.append("\"md_name\":\"" + ppm.getMd_name() + "\"},");
					}
				}
			} else {// 非康佳品牌
				PdModel pdModel = new PdModel();
				// pdModel.setPd_type(Long.valueOf(pd_type));
				pdModel.setPd_big_type(Long.valueOf(pd_type));
				pdModel.setBrand_id(Long.valueOf(brand_id));
				pdModel.getMap().put("jxcAddPdFilterPdModel", "true");
				pdModel.getMap().put("p_index_jxcPd", user.getStdEntpMain().getP_index());
				List<PdModel> list = getFacade().getPdModelService().getPdModelListForJxcPd(pdModel);// 买卖提的产品型号

				for (PdModel pm : list) {
					sb.append("{\"pd_id\":\"" + pm.getPd_id() + "\",");
					sb.append("\"md_name\":\"" + pm.getMd_name() + "\"},");
				}
			}

			sb.append("{\"end\":\"lk\"}]");
			logger.info("sb--111111--{}", sb.toString());
			super.renderJson(response, sb.toString());
		}

		return null;
	}

	/** 根据产品大类、品牌查询进销存的常用型号 */
	public ActionForward getJxcPd(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String pd_type = (String) dynaBean.get("pd_type");
		String own_sys = (String) dynaBean.get("own_sys");
		String brand_id = (String) dynaBean.get("brand_id");
		String isOther = (String) dynaBean.get("isOther");
		String keySeq = (String) dynaBean.get("keySeq");

		request.setAttribute("not_validate_record", "true");
		StdEntpUser user = super.getStdEntpUserFromRequest(request, response, keySeq);
		if (null == user) {
			return null;
		}

		if (StringUtils.isNotBlank(brand_id) && StringUtils.isNotBlank(pd_type)) {
			// 进销存中常用产品型号:根据类型、型号查询
			JxcPd jxcPd = new JxcPd();
			jxcPd.setShop_id(user.getStdEntpMain().getShop_id());
			if ("0".equals(isOther)) {// 选择了其他
				jxcPd.setJxc_pd_type_id(Long.valueOf(pd_type));
			} else {
				jxcPd.setPd_type(Long.valueOf(pd_type));
			}
			if (StringUtils.isNotBlank(own_sys)) {
				jxcPd.setOwn_sys(Integer.valueOf(own_sys));
			}
			jxcPd.setBrand_id(Long.valueOf(brand_id));
			jxcPd.setIs_del(0);
			List<JxcPd> list = getFacade().getJxcPdService().getJxcPdList(jxcPd);
			String separator = "$$";
			StringBuffer sb = new StringBuffer("[");
			for (JxcPd pm : list) {
				String md_name = pm.getName();
				StringBuffer values = new StringBuffer();
				values.append(pm.getId()).append(separator);
				values.append(pm.getInit_count()).append(separator);
				values.append(pm.getCount()).append(separator);
				values.append(pm.getRef_price()).append(separator);
				values.append(md_name);
				sb.append("{\"values\":\"" + values.toString() + "\",");
				sb.append("\"md_name\":\"" + md_name + "\"},");
			}
			sb.append("{\"end\":\"lk\"}]");
			logger.info("===sb.toString():{}", sb.toString());
			super.renderJson(response, sb.toString());
		}

		return null;
	}

	/** 增加自定义产品大类 */
	public ActionForward saveJxcPdType(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String jxc_pd_type_name = (String) dynaBean.get("jxc_pd_type_name");
		String keySeq = (String) dynaBean.get("keySeq");

		request.setAttribute("not_validate_record", "true");
		StdEntpUser user = super.getStdEntpUserFromRequest(request, response, keySeq);
		if (null == user) {
			return null;
		}

		String isExist = "0";

		boolean isBasePdType = false;

		BasePdType basePdType = new BasePdType();
		basePdType.setDel_mark(new Short((short) 0));
		basePdType.setIs_model(new Short((short) 1));
		List<BasePdType> basePdTypeList = super.getFacade().getBasePdTypeService().getBasePdTypeList(basePdType);
		for (BasePdType bpt : basePdTypeList) {
			if (StringUtils.equals(jxc_pd_type_name, bpt.getPd_name())) {
				isBasePdType = true;
				isExist = "-1";// 基础品类中已存在
				super.render(response, isExist, "text/x-json;charset=UTF-8");
				return null;
			}
		}

		JxcPdType jxcPdType = new JxcPdType();
		List<JxcPdType> jxcPdTypeList = getFacade().getJxcPdTypeService().getJxcPdTypeList(jxcPdType);
		if (null != jxcPdTypeList && jxcPdTypeList.size() > 0) {
			for (JxcPdType jpg : jxcPdTypeList) {
				if (StringUtils.equals(jxc_pd_type_name, jpg.getName())) {// 检查添加自定义品类是否存在
					isBasePdType = true;
					isExist = "-2";// 自定义品类中已存在
					super.render(response, isExist, "text/x-json;charset=UTF-8");
					return null;
				}
			}
		}

		if (!isBasePdType && StringUtils.isNotBlank(jxc_pd_type_name)) {
			JxcPdType entity = new JxcPdType();
			entity.setName(jxc_pd_type_name);
			Long count = getFacade().getJxcPdTypeService().getJxcPdTypeCount(entity);
			if (count <= 0) {
				entity.setShop_id(user.getStdEntpMain().getShop_id());
				entity.setAdd_user_id(user.getStdEntpMain().getId());
				entity.setAdd_date(new Date());
				Long id = getFacade().getJxcPdTypeService().createJxcPdType(entity);
				isExist = id.toString();
			}
		}
		super.render(response, isExist, "text/x-json;charset=UTF-8");
		return null;
	}

	/** 增加自定义产品型号(只有非家电下乡可以添加型号) */
	public ActionForward saveJxcPd(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String keySeq = (String) dynaBean.get("keySeq");
		String isExist = "0";
		StdEntpUser user = super.getStdEntpUserFromRequest(request, response, keySeq);
		if (null == user) {
			isExist = "-1";// 密钥key丢失
			super.render(response, isExist, "text/x-json;charset=UTF-8");
			return null;
		}
		String pd_type = (String) dynaBean.get("pd_type");
		String jxc_pd_type_id = (String) dynaBean.get("out_sys_id");// 外部大类ID（自定义大类ID）
		String brand_id = (String) dynaBean.get("brand_id");
		String pd_type_name = (String) dynaBean.get("pd_type_name");
		String brand_name = (String) dynaBean.get("brand_name");
		String name = (String) dynaBean.get("name");
		boolean isJxcPd = false;

		if (StringUtils.equals(brand_id, Constants.KONKA_BRAND_ID.toString())) {
			isJxcPd = true;
			isExist = "-3";// 康佳品牌不能新增常用型号
			super.render(response, isExist, "text/x-json;charset=UTF-8");
			return null;
		}

		JxcPd jxcPd = new JxcPd();
		jxcPd.setIs_del(0);
		jxcPd.setShop_id(user.getStdEntpMain().getShop_id());
		if (StringUtils.equals(pd_type, "0")) {// 选择自定义大类
			jxcPd.setJxc_pd_type_id(Long.valueOf(jxc_pd_type_id));
		} else {
			jxcPd.setPd_type(Long.valueOf(pd_type));
		}
		jxcPd.setBrand_id(Long.valueOf(brand_id));
		jxcPd.setName(name);
		List<JxcPd> jxcPdList = getFacade().getJxcPdService().getJxcPdList(jxcPd);
		if (null != jxcPdList && jxcPdList.size() > 0) {
			for (JxcPd obj : jxcPdList) {
				if (StringUtils.equals(name, obj.getName())) {// 检查添加自定义品类是否存在
					isJxcPd = true;
					isExist = "-2";// 自定义品类中已存在
					super.render(response, isExist, "text/x-json;charset=UTF-8");
					return null;
				}
			}
		}

		if (!isJxcPd && StringUtils.isNotBlank(name)) {
			JxcPd entity = new JxcPd();
			entity.setShop_id(user.getStdEntpMain().getShop_id());
			entity.setShop_p_index(user.getStdEntpMain().getP_index().longValue());
			entity.setAdd_user_id(user.getUser_id());
			entity.setOwn_sys(0);
			entity.setBrand_id(Long.valueOf(brand_id));
			entity.setPd_type_name(pd_type_name);
			entity.setBrand_name(brand_name);
			entity.setName(name);
			entity.setPrice(new BigDecimal(0));
			entity.setRef_price(new BigDecimal(0));
			entity.setPf_price(new BigDecimal(0));
			entity.setCur_cost_price(new BigDecimal(0));
			entity.setInit_cost_price(new BigDecimal(0));
			if (StringUtils.equals(pd_type, "0")) {// 选择自定义大类
				entity.setJxc_pd_type_id(Long.valueOf(jxc_pd_type_id));
				entity.setPd_type(Long.valueOf("0"));
			} else {
				entity.setPd_type(Long.valueOf(pd_type));
			}
			Long id = getFacade().getJxcPdService().createJxcPd(entity);
			isExist = id.toString();
		}
		super.render(response, isExist, "text/x-json;charset=UTF-8");
		return null;
	}

	/** Ajax取选择产品型号的数据 */
	public ActionForward AjaxGetJxcPdPropertis(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String keySeq = (String) dynaBean.get("keySeq");
		String pd_id = (String) dynaBean.get("pd_id");// jxc_pd的ID
		StdEntpUser user = super.getStdEntpUserFromRequest(request, response, keySeq);
		if (null == user) {
			return null;
		}
		if (StringUtils.isNotBlank(pd_id)) {
			// 进销存中常用产品型号:根据ID
			JxcPd jxcPd = new JxcPd();
			jxcPd.setId(Long.valueOf(pd_id));
			jxcPd.setIs_del(0);
			List<JxcPd> list = getFacade().getJxcPdService().getJxcPdList(jxcPd);
			StringBuffer sb = new StringBuffer("[");
			if (list.size() > 0) {
				for (JxcPd pd : list) {
					sb.append("{\"unit\":\"" + pd.getUnit() + "\",");// 计量单位
					sb.append("\"ref_price\":\"" + pd.getRef_price() + "\",");// 参考进货价
					sb.append("\"pf_price\":\"" + pd.getPf_price() + "\",");// 批发价
					sb.append("\"init_count\":\"" + pd.getInit_count() + "\",");// 起初库存
					sb.append("\"count\":\"" + pd.getCount() + "\",");// 当前库存
					sb.append("\"price\":\"" + pd.getPrice() + "\"},");// 零售价
				}
				sb.append("{\"end\":\"lk\"}]");
			}
			super.renderJson(response, sb.toString());
			log.info(sb.toString());
		}
		return null;
	}

	/** 增加供应商(只有非康佳品牌才能添加) */
	public ActionForward saveJxcSupplier(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String keySeq = (String) dynaBean.get("keySeq");
		String isExist = "0";
		StdEntpUser user = super.getStdEntpUserFromRequest(request, response, keySeq);
		if (null == user) {
			isExist = "-1";// 密钥key丢失
			super.render(response, isExist, "text/x-json;charset=UTF-8");
			return null;
		}
		String supplier_name = (String) dynaBean.get("supplier_name");
		String link_man = (String) dynaBean.get("link_man");// 供应商联系人
		String tel = (String) dynaBean.get("tel");// 供应商联系电话
		String fax = (String) dynaBean.get("fax");// 供应商传真
		String qq = (String) dynaBean.get("qq");
		String e_mail = (String) dynaBean.get("e_mail");
		String post = (String) dynaBean.get("post");// 邮编
		String addr = (String) dynaBean.get("addr");// 街道地址
		String supplier_remarks = (String) dynaBean.get("supplier_remarks");// 供应商备注
		String p_index = (String) dynaBean.get("p_index");// 供应商所在地区
		boolean isJxcSupplier = false;

		JxcSupplier jxcSupplier = new JxcSupplier();
		jxcSupplier.setIs_del(0);
		jxcSupplier.setShop_id(user.getStdEntpMain().getShop_id());
		List<JxcSupplier> jxcSupplierList = getFacade().getJxcSupplierService().getJxcSupplierList(jxcSupplier);
		if (null != jxcSupplierList && jxcSupplierList.size() > 0) {
			for (JxcSupplier obj : jxcSupplierList) {
				if (StringUtils.equals(supplier_name, obj.getName())) {// 检查添加供应商是否存在
					isJxcSupplier = true;
					isExist = "-2";// 供应商已存在
					super.render(response, isExist, "text/x-json;charset=UTF-8");
					return null;
				}
			}
		}

		if (!isJxcSupplier && StringUtils.isNotBlank(supplier_name)) {
			JxcSupplier entity = new JxcSupplier();
			entity.setShop_id(user.getStdEntpMain().getShop_id());// 门店所在的地区
			entity.setName(supplier_name);
			entity.setAdd_user_id(user.getUser_id());
			entity.setShop_p_index(user.getStdEntpMain().getP_index().longValue());
			entity.setLink_man(link_man);
			entity.setTel(tel);
			entity.setFax(fax);
			entity.setQq(qq);
			entity.setE_mail(e_mail);
			entity.setPost(post);
			entity.setAddr(addr);
			entity.setRemarks(supplier_remarks);
			if (!StringUtils.equals("", p_index)) {
				entity.setP_index(new Long(p_index));
			}
			Long id = getFacade().getJxcSupplierService().createJxcSupplier(entity);
			isExist = id.toString();
		}
		super.render(response, isExist, "text/x-json;charset=UTF-8");
		return null;
	}

	/** Ajax取选择供应商的数据 */
	public ActionForward AjaxGetJxcSupplierPropertis(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String keySeq = (String) dynaBean.get("keySeq");
		String supplier_id = (String) dynaBean.get("supplier_id");// jxc_supplier的ID
		StdEntpUser user = super.getStdEntpUserFromRequest(request, response, keySeq);
		if (null == user) {
			return null;
		}
		if (StringUtils.isNotBlank(supplier_id)) {
			// 进销存中供应商:根据ID查询
			JxcSupplier jxcSupplier = new JxcSupplier();
			jxcSupplier.setId(Long.valueOf(supplier_id));
			List<JxcSupplier> list = getFacade().getJxcSupplierService().getJxcSupplierList(jxcSupplier);
			StringBuffer sb = new StringBuffer("[");
			if (list.size() > 0) {
				for (JxcSupplier sp : list) {
					sb.append("{\"link_man\":\"" + sp.getLink_man() + "\",");// 联系人姓名
					sb.append("\"tel\":\"" + sp.getTel() + "\",");// 联系人电话
					sb.append("\"fax\":\"" + sp.getFax() + "\",");// 传真
					sb.append("\"qq\":\"" + sp.getQq() + "\",");// QQ
					sb.append("\"e_mail\":\"" + sp.getE_mail() + "\",");// 电子邮箱
					sb.append("\"post\":\"" + sp.getPost() + "\",");// 邮政编码
					sb.append("\"p_index\":\"" + sp.getP_index() + "\",");// 所在地区
					sb.append("\"addr\":\"" + sp.getAddr() + "\"},");// 街道地址
				}
				sb.append("{\"end\":\"lk\"}]");
			}
			super.renderJson(response, sb.toString());
		}
		return null;
	}

	/** 目前进货单只进一种型号产品，此查看详细方法用不到 */
	public ActionForward show(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		DynaBean dynaBean = (DynaBean) form;
		String keySeq = (String) dynaBean.get("keySeq");
		Pager pager = (Pager) dynaBean.get("pager");
		request.setAttribute("not_validate_record", "true");
		StdEntpUser user = super.getStdEntpUserFromRequest(request, response, keySeq);
		if (null == user) {
			return null;
		}
		dynaBean.set("keySeq", keySeq);

		String stock_bill_id = (String) dynaBean.get("stock_bill_id");

		JxcSupplier jxcSupplier = new JxcSupplier();
		jxcSupplier.setShop_id(user.getStdEntpMain().getShop_id());
		List<JxcSupplier> listJxcSupplier = super.getFacade().getJxcSupplierService().getJxcSupplierList(jxcSupplier);
		request.setAttribute("listJxcSupplier", listJxcSupplier);

		JxcStockBill jxcStockBill = new JxcStockBill();
		jxcStockBill.setId(Long.valueOf(stock_bill_id));
		jxcStockBill = super.getFacade().getJxcStockBillService().getJxcStockBill(jxcStockBill);

		JxcSupplier jxcs = new JxcSupplier();
		jxcs.setId(jxcStockBill.getSupplier_id());
		jxcs = super.getFacade().getJxcSupplierService().getJxcSupplier(jxcs);
		request.setAttribute("jxcs", jxcs);
		JxcStockBillDetails jxcStockBillDetails = new JxcStockBillDetails();
		jxcStockBillDetails.setStock_bill_id(Long.valueOf(stock_bill_id));
		Long recordCount = super.getFacade().getJxcStockBillDetailsService().getJxcStockBillDetailsCount(
				jxcStockBillDetails);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		jxcStockBillDetails.getRow().setFirst(pager.getFirstRow());
		jxcStockBillDetails.getRow().setCount(pager.getRowCount());

		List<JxcStockBillDetails> list = super.getFacade().getJxcStockBillDetailsService()
				.getJxcStockBillDetailsPaginatedList(jxcStockBillDetails);
		request.setAttribute("jxcStockBill", jxcStockBill);
		request.setAttribute("list", list);

		return new ActionForward("/JxcStockBill/detail.jsp");
	}

	public ActionForward toExcel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException, RowsExceededException, WriteException, BiffException {
		DynaBean dynaBean = (DynaBean) form;
		String keySeq = (String) dynaBean.get("keySeq");
		String stock_bill_id = (String) dynaBean.get("stock_bill_id");
		request.setAttribute("not_validate_record", "true");
		StdEntpUser user = this.getStdEntpUserFromRequest(request, response, keySeq);
		if (null == user) {
			return null;
		}
		JxcStockBill jxcStockBill = new JxcStockBill();
		jxcStockBill.setId(Long.valueOf(stock_bill_id));
		jxcStockBill = super.getFacade().getJxcStockBillService().getJxcStockBill(jxcStockBill);

		JxcSupplier jxcSupplier = new JxcSupplier();
		jxcSupplier.setId(jxcStockBill.getSupplier_id());
		jxcSupplier = super.getFacade().getJxcSupplierService().getJxcSupplier(jxcSupplier);

		JxcStockBillDetails jxcStockBillDetails = new JxcStockBillDetails();
		jxcStockBillDetails.setStock_bill_id(Long.valueOf(stock_bill_id));

		List<JxcStockBillDetails> jxcStockBillDetailsList = super.getFacade().getJxcStockBillDetailsService()
				.getJxcStockBillDetailsList(jxcStockBillDetails);
		String userName = user.getStdEntpMain().getLinkman();
		String tel = user.getStdEntpMain().getTel();
		createExcelFile(request, response, jxcStockBill, jxcSupplier, jxcStockBillDetailsList, userName, tel);

		return null;
	}

	private void createExcelFile(HttpServletRequest request, HttpServletResponse response, JxcStockBill jxcStockBill,
			JxcSupplier jxcSupplier, List<JxcStockBillDetails> jxcStockBillDetailsList, String userName, String tel)
			throws IOException, RowsExceededException, WriteException, BiffException {
		if (jxcStockBillDetailsList == null || jxcStockBillDetailsList.size() < 0) {
			return;
		}
		WritableWorkbook wwb = null;
		String sFileName = DateFormatUtils.format(new Date(), "yyyyMMddhhmmss") + ".xls";
		// 获取系统实际路径
		String SystemPath = getServlet().getServletContext().getRealPath(String.valueOf(File.separatorChar));
		File fDownloadExcel = new File(SystemPath + "files/download_excel/");
		if (!fDownloadExcel.exists()) {
			FileUtils.forceMkdir(fDownloadExcel);
		}
		// 首先要使用Workbook类的工厂方法创建一个可写入的工作薄(Workbook)对象
		wwb = Workbook.createWorkbook(new File(SystemPath + "files/download_excel/" + sFileName));

		int row = 0;// 行
		int column = 0;// 列

		if (wwb != null) {
			WritableCellFormat cellFormatRight = new WritableCellFormat();
			cellFormatRight.setAlignment(Alignment.RIGHT);
			WritableCellFormat cellFormatCenter = new WritableCellFormat();
			cellFormatCenter.setAlignment(Alignment.CENTRE);
			WritableCellFormat wcf = new jxl.write.WritableCellFormat(new jxl.write.NumberFormat("￥0.00"));
			// 创建一个可写入的工作表
			// Label(column,row,content)其中column代表单元格的第column+1列，第row+1行, 单元格的内容是content
			WritableSheet sheet = wwb.createSheet(userName.concat("进货单明细"), 0);
			// String[] title0 = { "", "", "", "品牌", "数量", "单价", "金额", "备注" };

			sheet.mergeCells(0, row, 7, row);// 合并单元格(左列，左行，右列，右行) 合并第row行，从第0列到第7列
			String info0 = "联系人：".concat(userName).concat("  联系电话：").concat(tel);
			Label label0 = new Label(0, row, info0);
			label0.setCellFormat(cellFormatRight);
			sheet.addCell(label0);

			row++;
			sheet.mergeCells(0, row, 7, row);
			String info1 = jxcStockBill.getOpr_man();
			Label label1 = new Label(0, row, info1);
			label1.setCellFormat(cellFormatCenter);
			sheet.addCell(label1);

			row++;
			sheet.mergeCells(0, row, 7, row);
			label1 = new Label(0, row, "进货单");
			label1.setCellFormat(cellFormatCenter);
			sheet.addCell(label1);

			row++;
			sheet.mergeCells(0, row, 2, row);
			sheet.mergeCells(3, row, 4, row);
			sheet.mergeCells(5, row, 7, row);
			Label label3 = new Label(0, row, "供应商：".concat(jxcSupplier.getName()));
			sheet.addCell(label3);
			label3 = new Label(3, row, "日期：".concat(DateFormatUtils.format(jxcStockBill.getAdd_date(), "yyyy-MM-dd")));
			sheet.addCell(label3);
			label3 = new Label(5, row, "No.".concat(jxcStockBill.getSn()));
			label3.setCellFormat(cellFormatRight);
			sheet.addCell(label3);

			row++;
			String[] title = { "行号", "货品代码", "品名规格", "品牌", "数量", "单价", "金额", "备注" };
			for (int i = 0; i < title.length; i++) {
				sheet.addCell(new Label(i, row, title[i]));
			}

			row++;
			// 填充产品价格
			for (int i = 0; i < jxcStockBillDetailsList.size(); i++) {
				column = 0;
				JxcStockBillDetails jxcStockBillDetails = jxcStockBillDetailsList.get(i);
				sheet.addCell(new jxl.write.Number(column++, row, i + 1));// 行号
				sheet.addCell(new Label(column++, row, jxcStockBillDetails.getPd_name()));// 货品代码
				sheet.addCell(new Label(column++, row, jxcStockBillDetails.getPd_type_name()));// 品名规格
				sheet.addCell(new Label(column++, row, jxcStockBillDetails.getBrand_name()));// 品牌
				sheet.addCell(new jxl.write.Number(column++, row, jxcStockBillDetails.getCount()));// 数量
				sheet.addCell(new jxl.write.Number(column++, row, jxcStockBillDetails.getPrice().doubleValue(), wcf));// 单价
				sheet.addCell(new jxl.write.Number(column++, row, jxcStockBillDetails.getCount()
						* jxcStockBillDetails.getPrice().doubleValue(), wcf));// 金额
				sheet.addCell(new Label(column++, row, jxcStockBillDetails.getRemarks()));// 备注
				row++;
			}

			sheet.mergeCells(0, row, 2, row);
			if (null != jxcStockBill.getRemarks() && StringUtils.isNotBlank(jxcStockBill.getRemarks())) {
				sheet.addCell(new Label(0, row, "说明：".concat(jxcStockBill.getRemarks() + " ")));
			} else {
				sheet.addCell(new Label(0, row, "说明："));
			}
			sheet.addCell(new Label(3, row, "应付金额："));
			sheet.addCell(new jxl.write.Number(4, row, jxcStockBill.getPay_money().doubleValue(), wcf));
			sheet.addCell(new Label(6, row, "本次付款："));
			sheet.addCell(new jxl.write.Number(7, row, jxcStockBill.getPaid_money().doubleValue(), wcf));

			row++;
			sheet.mergeCells(0, row, 7, row);
			String info = "签收人：";
			Label label = new Label(0, row, info);
			label.setCellFormat(cellFormatCenter);
			sheet.addCell(label);
			// 从内存中写入文件中
			wwb.write();
			// 关闭资源，释放内存
			wwb.close();
		}
		// super.renderJavaScript(response, "window.location.href='" + ctx + "/files/download_excel/" + sFileName +
		// "';");

		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename=" + sFileName);
		FileInputStream fileInputStream = new FileInputStream(SystemPath + "files/download_excel/" + sFileName);
		OutputStream out = response.getOutputStream();
		int i = 0;
		while ((i = fileInputStream.read()) != -1) {
			out.write(i);
		}
		fileInputStream.close();
		out.close();
	}

	public ActionForward toExcel1(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws RowsExceededException, WriteException, IOException {
		DynaBean dynaBean = (DynaBean) form;
		String keySeq = (String) dynaBean.get("keySeq");
		request.setAttribute("not_validate_record", "true");
		StdEntpUser user = this.getStdEntpUserFromRequest(request, response, keySeq);
		if (null == user) {
			return null;
		}
		String supplier_id = (String) dynaBean.get("supplier_id");
		String add_date_lt = (String) dynaBean.get("add_date_lt");
		String add_date_gt = (String) dynaBean.get("add_date_gt");
		String userName = user.getStdEntpMain().getEntp_name();
		String tel = user.getStdEntpMain().getTel();
		JxcStockBill jxcStockBill = new JxcStockBill();
		jxcStockBill.setIs_del(0);
		jxcStockBill.setShop_id(user.getStdEntpMain().getShop_id());
		if (StringUtils.isNotEmpty(supplier_id)) {
			jxcStockBill.setSupplier_id(Long.valueOf(supplier_id));
		}
		jxcStockBill.getMap().put("add_date_gt", add_date_gt);
		jxcStockBill.getMap().put("add_date_lt", add_date_lt);
		jxcStockBill.setIs_del(0);
		List<JxcStockBill> listJxcStockBill = super.getFacade().getJxcStockBillService().getJxcStockBillList(
				jxcStockBill);
		BigDecimal countMoney = new BigDecimal(0);
		for (int i = 0; i < listJxcStockBill.size(); i++) {
			countMoney = countMoney.add(listJxcStockBill.get(i).getPaid_money());
		}
		createExcelFile1(request, response, userName, tel, listJxcStockBill, countMoney, add_date_gt, add_date_lt);
		return null;
	}

	private void createExcelFile1(HttpServletRequest request, HttpServletResponse response, String userName,
			String tel, List<JxcStockBill> listJxcStockBill, BigDecimal countMoney, String add_date_gt,
			String add_date_lt) throws RowsExceededException, WriteException, IOException {
		if (listJxcStockBill == null || listJxcStockBill.size() < 0) {
			return;
		}
		WritableWorkbook wwb = null;
		String sFileName = DateFormatUtils.format(new Date(), "yyyyMMddhhmmss") + ".xls";
		// 获取系统实际路径
		String SystemPath = getServlet().getServletContext().getRealPath(String.valueOf(File.separatorChar));
		File fDownloadExcel = new File(SystemPath + "files/download_excel/");
		if (!fDownloadExcel.exists()) {
			FileUtils.forceMkdir(fDownloadExcel);
		}
		// 首先要使用Workbook类的工厂方法创建一个可写入的工作薄(Workbook)对象
		wwb = Workbook.createWorkbook(new File(SystemPath + "files/download_excel/" + sFileName));

		if (wwb != null) {
			WritableCellFormat cellFormatRight = new WritableCellFormat();
			cellFormatRight.setAlignment(Alignment.RIGHT);
			WritableCellFormat cellFormatCenter = new WritableCellFormat();
			cellFormatCenter.setAlignment(Alignment.CENTRE);
			WritableCellFormat cellFormatLeft = new WritableCellFormat();
			cellFormatLeft.setAlignment(Alignment.LEFT);
			WritableCellFormat wcf = new jxl.write.WritableCellFormat(new jxl.write.NumberFormat("￥0.00"));
			// 创建一个可写入的工作表
			// Label(column,row,content)其中column代表单元格的第column+1列，第row+1行, 单元格的内容是content
			WritableSheet sheet = wwb.createSheet(userName.concat("进货单列表"), 0);
			int row = 0;// 行
			int column = 0;// 列

			sheet.mergeCells(0, row, 6, row);// 合并单元格(左列，左行，右列，右行) 合并第row行，从第0列到第7列
			String info0 = "联系人：".concat(userName).concat("  联系电话：").concat(tel);
			Label label0 = new Label(0, row, info0);
			label0.setCellFormat(cellFormatRight);
			sheet.addCell(label0);

			row++;
			sheet.mergeCells(0, row, 6, row);
			Label label1 = new Label(0, row, "进货单列表");
			label1.setCellFormat(cellFormatCenter);
			sheet.addCell(label1);

			row++;
			sheet.mergeCells(0, row, 6, row);
			String s = queryDate(add_date_gt, add_date_lt);
			label1 = new Label(0, row, s);
			label1.setCellFormat(cellFormatLeft);
			sheet.addCell(label1);

			row++;
			String[] title = { "行号", "单据日期", "单据编号", "供应商名称", "进货金额", "付款金额", "说明" };
			for (int i = 0; i < title.length; i++) {
				sheet.addCell(new Label(i, row, title[i]));
			}

			row++;
			// 填充产品价格
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			for (int i = 0; i < listJxcStockBill.size(); i++) {
				column = 0;
				JxcStockBill jxcStockBill = listJxcStockBill.get(i);
				sheet.addCell(new jxl.write.Number(column++, row, i + 1));// 行号
				sheet.addCell(new Label(column++, row, format.format(jxcStockBill.getIn_date())));// 单据日期
				sheet.addCell(new Label(column++, row, jxcStockBill.getSn()));// 单据编号
				sheet.addCell(new Label(column++, row, (String) jxcStockBill.getMap().get("supplier_name")));// 供应商名称
				sheet.addCell(new jxl.write.Number(column++, row, jxcStockBill.getPay_money().doubleValue(), wcf));// 进货金额
				sheet.addCell(new jxl.write.Number(column++, row, jxcStockBill.getPaid_money().doubleValue(), wcf));// 付款金额
				sheet.addCell(new Label(column++, row, jxcStockBill.getRemarks()));// 备注
				row++;
			}

			sheet.addCell(new Label(0, row, "合计"));
			sheet.mergeCells(1, row, 4, row);
			sheet.addCell(new jxl.write.Number(5, row, countMoney.doubleValue(), wcf));

			row++;
			sheet.mergeCells(0, row, 6, row);
			String info = "签收人：";
			Label label = new Label(0, row, info);
			label.setCellFormat(cellFormatCenter);
			sheet.addCell(label);
			// 从内存中写入文件中
			wwb.write();
			// 关闭资源，释放内存
			wwb.close();
		}
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename=" + sFileName);
		FileInputStream fileInputStream = new FileInputStream(SystemPath + "files/download_excel/" + sFileName);
		OutputStream out = response.getOutputStream();
		int i = 0;
		while ((i = fileInputStream.read()) != -1) {
			out.write(i);
		}
		fileInputStream.close();
		out.close();
	}

}
