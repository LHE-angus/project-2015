package com.ebiz.mmt.web.struts.manager.admin;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.BasePdClazz;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.KonkaSellReportTmp;
import com.ebiz.mmt.domain.KonkaStockDetails;
import com.ebiz.mmt.domain.PePdModel;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

public class KonkaRealTimeStock3Action extends BaseAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}

		DynaBean dynaBean = (DynaBean) form;
		setNaviStringToRequestScope(form, request);
		// add_date_st = DateFormatUtils.format(new Date(), "yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_MONTH, 1);
		String add_date_st = DateFormatUtils.format(c.getTime(), "yyyy-MM-dd"); // 取当月第一天
		dynaBean.set("add_date_st", add_date_st);
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		String add_date_en = DateFormatUtils.format(calendar.getTime(), "yyyy-MM-dd");
		dynaBean.set("add_date_en", add_date_en);
		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

		// 获取用户角色
		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(peProdUser.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);

		boolean role_id_ge_20_le_29 = false;
		boolean role_id_ge_30_le_39 = false;
		boolean role_id_eq_10 = false;
		boolean role_id_ge_30 = false;
		for (PeRoleUser peRoleUser : peRoleUserList) {
			if (peRoleUser.getRole_id() >= 20L && peRoleUser.getRole_id() < 29L) {
				role_id_ge_20_le_29 = true;
			}
			if (peRoleUser.getRole_id() >= 30L && peRoleUser.getRole_id() < 39L) {
				role_id_ge_30_le_39 = true;
			}
			if (peRoleUser.getRole_id() == 10L) {
				role_id_eq_10 = true;
			}
			if (peRoleUser.getRole_id() >= 30L) {
				role_id_ge_30 = true;
			}
		}

		// 产品类型
		List<BasePdClazz> basePdClassList = new ArrayList<BasePdClazz>();
		if (role_id_eq_10) {
			BasePdClazz basePdClazz = new BasePdClazz();
			basePdClazz.setIs_del(0);
			basePdClazz.setPar_id(1010000L);
			basePdClassList = getFacade().getBasePdClazzService().getBasePdClazzList(basePdClazz);
		} else if (role_id_ge_20_le_29) {// 事业部
			BasePdClazz basePdClazz = new BasePdClazz();
			basePdClazz.setIs_del(0);
			basePdClassList = getFacade().getBasePdClazzService().getBasePdClazzList(basePdClazz);
			request.setAttribute("role_id_syb", "syb");
		} else if (role_id_ge_30_le_39) {// 分公司
			BasePdClazz basePdClazz = new BasePdClazz();
			basePdClazz.getMap().put("getOwnDeptCls", "ture");
			basePdClazz.getMap().put("dept_id", peProdUser.getDept_id());
			basePdClazz.setIs_del(0);
			basePdClassList = getFacade().getBasePdClazzService().getBasePdClazzList(basePdClazz);
		} else {// 经办 或业务员
			BasePdClazz basePdClazz = new BasePdClazz();
			basePdClazz.getMap().put("getOwnDeptCls", "ture");
			if (super.getSuperiorForDeptType(peProdUser.getDept_id(), 3) != null) {
				basePdClazz.getMap().put("dept_id",
						super.getSuperiorForDeptType(peProdUser.getDept_id(), 3).getDept_id());
			}
			basePdClazz.setIs_del(0);
			basePdClassList = getFacade().getBasePdClazzService().getBasePdClazzList(basePdClazz);
		}

		KonkaR3Shop r3 = new KonkaR3Shop();
		r3.setIs_del(0L);
		if (role_id_ge_30) {
			r3.setBranch_area_name(super.getSuperiorForDeptType(super.getSessionUserInfo(request).getDept_id(), 3)
					.getDept_name());
		}
		List<KonkaR3Shop> handleList = getFacade().getKonkaR3ShopService().getKonkaR3ShopGroupByHandleName(r3);

		request.setAttribute("handleList", handleList);
		dynaBean.set("customer_type", "1");
		request.setAttribute("basePdClassList", basePdClassList);
		return mapping.findForward("list");
	}

	public ActionForward rlist(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String customer_type = (String) dynaBean.get("customer_type");
		String keyword = (String) dynaBean.get("keyword");
		String add_date_st = (String) dynaBean.get("add_date_st");
		String add_date_en = (String) dynaBean.get("add_date_en");
		String cls_id = (String) dynaBean.get("cls_id");
		String md_name_like = (String) dynaBean.get("md_name_like");
		String handle_name = (String) dynaBean.get("handle_name");

		if (StringUtils.isNotBlank(cls_id)) {
			dynaBean.set("cls_id", cls_id);
		}

		KonkaSellReportTmp _t = new KonkaSellReportTmp();
		_t.getMap().put("date_start", add_date_st);
		_t.getMap().put("date_end", add_date_en);
		_t.getMap().put("md_name_like", md_name_like);
		_t.getMap().put("cls_id", cls_id);
		_t.getMap().put("customer_type", customer_type);// 查询集类
		_t.getMap().put("shop_code_like", keyword);

		// List<HashMap<String, Object>> bList = new ArrayList<HashMap<String,
		// Object>>();
		Long recordCount = 0l;
		if ("5".equals(customer_type)) {
			_t.getMap().put("handle_name", handle_name);// 经办
		} else if ("3".equals(customer_type)) {
			_t.getMap().put("fg", "西安");// 分公司
		}
		recordCount = getFacade().getKonkaSellReportTmpService().reportTMPCount(_t);
		// bList =
		// super.getFacade().getKonkaSellReportTmpService().reportTMPAll(_t);
		pager.init(recordCount, 10, pager.getRequestPage());
		_t.getRow().setFirst(pager.getFirstRow());
		_t.getRow().setCount(pager.getRowCount());

		// request.setAttribute("A_ALL", bList.get(0).get("SUM(A_ALL)"));
		// request.setAttribute("B_ALL", bList.get(0).get("SUM(B_ALL)"));
		// request.setAttribute("C_ALL", bList.get(0).get("SUM(C_ALL)"));
		// request.setAttribute("C_S_ALL", bList.get(0).get("SUM(C_S_ALL)"));
		// request.setAttribute("D_ALL", bList.get(0).get("SUM(D_ALL)"));
		request.setAttribute("stockList", super.getFacade().getKonkaSellReportTmpService().reportTMP(_t));

		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

		// 获取用户角色
		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(peProdUser.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);

		boolean role_id_ge_20_le_29 = false;
		boolean role_id_ge_30_le_39 = false;
		boolean role_id_eq_10 = false;
		for (PeRoleUser peRoleUser : peRoleUserList) {
			if (peRoleUser.getRole_id() >= 20L && peRoleUser.getRole_id() < 29L) {
				role_id_ge_20_le_29 = true;
			}
			if (peRoleUser.getRole_id() >= 30L && peRoleUser.getRole_id() < 39L) {
				role_id_ge_30_le_39 = true;
			}
			if (peRoleUser.getRole_id() == 10L) {
				role_id_eq_10 = true;
			}
		}

		// 产品类型
		List<BasePdClazz> basePdClassList = new ArrayList<BasePdClazz>();
		if (role_id_eq_10) {
			BasePdClazz basePdClazz = new BasePdClazz();
			basePdClazz.setIs_del(0);
			basePdClazz.setPar_id(1010000L);
			basePdClassList = getFacade().getBasePdClazzService().getBasePdClazzList(basePdClazz);
		} else if (role_id_ge_20_le_29) {// 事业部
			BasePdClazz basePdClazz = new BasePdClazz();
			basePdClazz.setIs_del(0);
			basePdClassList = getFacade().getBasePdClazzService().getBasePdClazzList(basePdClazz);
			request.setAttribute("role_id_syb", "syb");
		} else if (role_id_ge_30_le_39) {// 分公司
			BasePdClazz basePdClazz = new BasePdClazz();
			basePdClazz.getMap().put("getOwnDeptCls", "ture");
			basePdClazz.getMap().put("dept_id", peProdUser.getDept_id());
			basePdClazz.setIs_del(0);
			basePdClassList = getFacade().getBasePdClazzService().getBasePdClazzList(basePdClazz);
		} else {// 经办 或业务员
			BasePdClazz basePdClazz = new BasePdClazz();
			basePdClazz.getMap().put("getOwnDeptCls", "ture");
			if (super.getSuperiorForDeptType(peProdUser.getDept_id(), 3) != null) {
				basePdClazz.getMap().put("dept_id",
						super.getSuperiorForDeptType(peProdUser.getDept_id(), 3).getDept_id());
			}
			basePdClazz.setIs_del(0);
			basePdClassList = getFacade().getBasePdClazzService().getBasePdClazzList(basePdClazz);
		}

		request.setAttribute("basePdClassList", basePdClassList);
		return mapping.findForward("rlist");

	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String customer_type = (String) dynaBean.get("customer_type");
		String keyword = (String) dynaBean.get("keyword");
		String customer_name_like = (String) dynaBean.get("customer_name_like");
		String add_date_st = (String) dynaBean.get("add_date_st");
		String add_date_en = (String) dynaBean.get("add_date_en");
		String cls_id = (String) dynaBean.get("cls_id");
		String md_name_like = (String) dynaBean.get("md_name_like");
		// String handle_name = (String) dynaBean.get("handle_name");

		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");

		super.encodeCharacterForGetMethod(dynaBean, request);

		if (StringUtils.isBlank(add_date_st)) {
			Calendar c = Calendar.getInstance();
			c.set(Calendar.DAY_OF_MONTH, 1);
			add_date_st = DateFormatUtils.format(c.getTime(), "yyyy-MM-dd"); // 取当月第一天
		}
		if (StringUtils.isBlank(add_date_en)) {
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
			add_date_en = DateFormatUtils.format(calendar.getTime(), "yyyy-MM-dd");
		}

		dynaBean.set("add_date_st", add_date_st);
		dynaBean.set("add_date_en", add_date_en);

		Date startDate = s.parse(add_date_st);
		Date endDate = s.parse(add_date_en);
		dynaBean.set("startDate", startDate);
		dynaBean.set("endDate", endDate);

		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
		KonkaR3Shop r3Shop = super.getKonkaR3ShopForStockSell(mapping, form, request, response, ui.getDept_id());

		if (StringUtils.isBlank(customer_type)) {
			dynaBean.set("customer_type", "1");
		} else {
			dynaBean.set("customer_type", customer_type);
		}

		List<KonkaStockDetails> stockList = new ArrayList<KonkaStockDetails>();

		if (StringUtils.isNotBlank(keyword)) {
			r3Shop.getMap().put("keyword_like", keyword);
		}
		if (StringUtils.isNotBlank(customer_name_like)) {
			r3Shop.getMap().put("customer_name_like", customer_name_like);
		}
		Long recordCount = getFacade().getKonkaR3ShopService().getKonkaR3ShopCount(r3Shop);
		pager.init(recordCount, 1, pager.getRequestPage());
		r3Shop.getRow().setFirst(pager.getFirstRow());
		r3Shop.getRow().setCount(pager.getRowCount());
		List<KonkaR3Shop> r3ShopList = getFacade().getKonkaR3ShopService().getKonkaR3ShopPaginatedList(r3Shop);

		for (KonkaR3Shop i : r3ShopList) {
			PePdModel ppm = new PePdModel();
			ppm.getMap().put("keyword", i.getR3_code());
			ppm.getMap().put("md_name_like", md_name_like);
			ppm.getMap().put("date_start", s.format(startDate) + " 00:00:00");
			ppm.getMap().put("date_end", s.format(endDate) + " 23:59:59");
			if (GenericValidator.isLong(cls_id)) {
				ppm.setCls_id(Long.valueOf(cls_id));
			}
			List<PePdModel> pePdModelList = super.getFacade().getKonkaRealTimeStockService()
					.getRealTimeStockByKeyword(ppm);
			dynaBean.set("cus_name", i.getCustomer_name());

			KonkaStockDetails stockDetails = new KonkaStockDetails();
			stockDetails.setR3_code(i.getR3_code());
			stockDetails.getMap().put("stock_date_end", s.format(startDate) + " 23:59:59");
			List<KonkaStockDetails> startStockList = getFacade().getKonkaStockDetailsService()
					.getKonkaStockDetailsList(stockDetails);

			stockDetails.setR3_code(i.getR3_code());
			stockDetails.getMap().put("stock_date_end", null);
			stockDetails.getMap().put("stock_date_end", s.format(endDate) + " 23:59:59");
			List<KonkaStockDetails> endStockList = getFacade().getKonkaStockDetailsService().getKonkaStockDetailsList(
					stockDetails);

			for (PePdModel t : pePdModelList) {
				KonkaStockDetails entity = this.getResultForDKHNew(i, t, startStockList, endStockList, startDate,
						endDate);
				Long dataImportSum = 0L;
				BigDecimal dataImportMoneySum = new BigDecimal(0);

				if (null != t.getMap().get("channel_count")) {
					dataImportSum = Long.valueOf(t.getMap().get("channel_count").toString());
				}
				if (null != t.getMap().get("channel_money")) {
					dataImportMoneySum = new BigDecimal(t.getMap().get("channel_money").toString());
				}
				BigDecimal dataImportPrice = new BigDecimal(0);// 单价
				int r = dataImportMoneySum.compareTo(BigDecimal.ZERO);
				if (r == 0 || (dataImportSum == 0)) {
					dataImportPrice = BigDecimal.ZERO;
				} else {
					dataImportPrice = dataImportMoneySum.divide(new BigDecimal(dataImportSum), 2,
							BigDecimal.ROUND_HALF_UP);
				}
				entity.getMap().put("dataImportSum", dataImportSum);
				entity.getMap().put("dataImportMoneySum", dataImportMoneySum);
				entity.getMap().put("dataImportPrice", dataImportPrice);

				Long sellCount = 0L;
				BigDecimal sellMoney = new BigDecimal(0);// 销售金额
				if (null != t.getMap().get("sell_count")) {
					sellCount = Long.valueOf(t.getMap().get("sell_count").toString());
				}
				if (null != t.getMap().get("sell_money")) {
					sellMoney = new BigDecimal(t.getMap().get("sell_money").toString());
				}

				BigDecimal price = new BigDecimal(0);// 销售单价
				int z = sellMoney.compareTo(BigDecimal.ZERO);
				if (sellCount == 0 || z == 0) {
					price = BigDecimal.ZERO;
				} else {
					price = sellMoney.divide(new BigDecimal(sellCount), 2, BigDecimal.ROUND_HALF_UP);
				}
				BigDecimal cost = new BigDecimal(entity.getMap().get("sellCost").toString());
				BigDecimal sellPrice = new BigDecimal(0);// 成本单价
				int p = cost.compareTo(BigDecimal.ZERO);
				if (sellCount == 0 || p == 0) {
					sellPrice = BigDecimal.ZERO;
					entity.getMap().put("sellCost", new BigDecimal(0));
				} else {
					sellPrice = cost.divide(new BigDecimal(sellCount), 2, BigDecimal.ROUND_HALF_UP);
				}
				entity.getMap().put("sellCount", sellCount);
				entity.getMap().put("sellMoney", sellMoney);
				entity.getMap().put("sellPrice", sellPrice);
				entity.getMap().put("price", price);
				stockList.add(entity);
			}
		}
		this.getTotle(mapping, form, request, response, stockList);
		// } else if (("3").equals(customer_type)) { // 分公司
		//
		// PeRoleUser role = new PeRoleUser();
		// role.setUser_id(super.getSessionUserInfo(request).getId());
		// role = getFacade().getPeRoleUserService().getPeRoleUser(role);
		//
		// PePdModel ppm = new PePdModel();
		// String branch_area_name = "";
		// if(role.getRole_id()>=30){
		// branch_area_name =
		// super.getSuperiorForDeptType(super.getSessionUserInfo(request).getDept_id(),
		// 3)
		// .getDept_name();
		// }
		// ppm.getMap().put("branch_area_name", branch_area_name);
		// ppm.getMap().put("md_name_like", md_name_like);
		// ppm.getMap().put("date_end", s.format(endDate) + " 23:59:59");
		// if(GenericValidator.isLong(cls_id)){
		// ppm.setCls_id(Long.valueOf(cls_id));
		// }
		// List<PePdModel> pePdModelList =
		// super.getFacade().getKonkaRealTimeStockService()
		// .getIsUseProductByKeyword(ppm);
		//
		// KonkaSellDetails selldetails = new KonkaSellDetails();
		// selldetails.getMap().put("state", 1);
		// selldetails.getMap().put("branch_area_name", branch_area_name);
		// selldetails.getMap().put("sell_date_start", s.format(startDate) +
		// " 00:00:00");
		// selldetails.getMap().put("sell_date_end", s.format(endDate) +
		// " 23:59:59");
		// List<KonkaSellDetails> sellList =
		// getFacade().getKonkaSellDetailsService().getKonkaSellDetailsList(
		// selldetails);
		//
		// ChannelDataImport dataImport = new ChannelDataImport();
		// dataImport.getMap().put("branch_area_name", branch_area_name);
		// dataImport.getMap().put("startDate", s.format(startDate) +
		// " 00:00:00");
		// dataImport.getMap().put("endDate", s.format(endDate) + " 23:59:59");
		// List<ChannelDataImport> dataImportList =
		// getFacade().getChannelDataImportService()
		// .getChannelDataListByPdId(dataImport);
		//
		// KonkaStockDetails stockDetails = new KonkaStockDetails();
		// stockDetails.getMap().put("branch_area_name", branch_area_name);
		// stockDetails.getMap().put("stock_date_end", s.format(startDate) +
		// " 23:59:59");
		// List<KonkaStockDetails> startStockList =
		// getFacade().getKonkaStockDetailsService()
		// .getKonkaStockDetailsList(stockDetails);
		//
		// stockDetails.getMap().put("branch_area_name", branch_area_name);
		// stockDetails.getMap().put("stock_date_end", null);
		// stockDetails.getMap().put("stock_date_end", s.format(endDate) +
		// " 23:59:59");
		// List<KonkaStockDetails> endStockList =
		// getFacade().getKonkaStockDetailsService().getKonkaStockDetailsList(
		// stockDetails);
		//
		// for (PePdModel t : pePdModelList) {
		// KonkaStockDetails entity = this.getResultForDKH(null, t,
		// startStockList, endStockList, startDate,
		// endDate);
		// Long dataImportSum = 0L;
		// BigDecimal dataImportMoneySum = new BigDecimal(0);
		// for (ChannelDataImport ct : dataImportList) {
		//
		// if (StringUtils.isNotBlank(ct.getColumn_1()) &&
		// StringUtils.isNotBlank(ct.getColumn_11())
		// && ct.getColumn_11().equals(t.getMd_name())) {
		// dataImportSum = dataImportSum + Long.valueOf(ct.getColumn_12());// 数量
		// dataImportMoneySum = dataImportMoneySum.add(new
		// BigDecimal(ct.getColumn_14().toString()));// 总金额
		// BigDecimal dataImportPrice = new BigDecimal(0);// 单价
		// int r = dataImportMoneySum.compareTo(BigDecimal.ZERO);
		// if (r == 0 || (dataImportSum == 0)) {
		// dataImportPrice = BigDecimal.ZERO;
		// } else {
		// dataImportPrice = dataImportMoneySum.divide(new
		// BigDecimal(dataImportSum), 2,
		// BigDecimal.ROUND_HALF_UP);
		// }
		// entity.getMap().put("dataImportSum", dataImportSum);
		// entity.getMap().put("dataImportMoneySum", dataImportMoneySum);
		// entity.getMap().put("dataImportPrice", dataImportPrice);
		// }
		// }
		// Long sellCount = 0L;
		// BigDecimal sellMoney = new BigDecimal(0);// 销售金额
		// for (KonkaSellDetails st : sellList) {
		// if (StringUtils.isNotBlank(st.getMap().get("cus_sn").toString())
		// && StringUtils.isNotBlank(st.getPd_id().toString()) &&
		// st.getPd_id().equals(t.getPd_id())) {
		// sellCount = sellCount + st.getSell_count();
		// sellMoney = sellMoney.add(st.getSell_money().multiply(new
		// BigDecimal(st.getSell_count())));
		//
		// BigDecimal price = new BigDecimal(0);// 销售单价
		// int z = sellMoney.compareTo(BigDecimal.ZERO);
		// if (sellCount == 0 || z == 0) {
		// price = BigDecimal.ZERO;
		// } else {
		// price = sellMoney.divide(new BigDecimal(sellCount), 2,
		// BigDecimal.ROUND_HALF_UP);
		// }
		// BigDecimal cost = new
		// BigDecimal(entity.getMap().get("sellCost").toString());
		// BigDecimal sellPrice = new BigDecimal(0);// 成本单价
		// int p = cost.compareTo(BigDecimal.ZERO);
		// if (sellCount == 0 || p == 0) {
		// sellPrice = BigDecimal.ZERO;
		// entity.getMap().put("sellCost",new BigDecimal(0));
		// } else {
		// sellPrice = cost.divide(new BigDecimal(sellCount), 2,
		// BigDecimal.ROUND_HALF_UP);
		// }
		// entity.getMap().put("sellCount", sellCount);
		// entity.getMap().put("sellMoney", sellMoney);
		// entity.getMap().put("sellPrice", sellPrice);
		// entity.getMap().put("price", price);
		// }
		// stockList.add(entity);
		// }
		// }
		// } else if (("5").equals(customer_type)) { // 经办
		// PePdModel ppm = new PePdModel();
		// ppm.getMap().put("handle_name", handle_name);
		// dynaBean.set("cus_name", handle_name);
		// ppm.getMap().put("md_name_like", md_name_like);
		// ppm.getMap().put("date_end", s.format(endDate) + " 23:59:59");
		// if(GenericValidator.isLong(cls_id)){
		// ppm.setCls_id(Long.valueOf(cls_id));
		// }
		// List<PePdModel> pePdModelList =
		// super.getFacade().getKonkaRealTimeStockService()
		// .getIsUseProductByKeyword(ppm);
		//
		// KonkaR3Shop r3 = new KonkaR3Shop();
		// r3.setHandle_name(handle_name);
		// List<KonkaR3Shop> reShopList =
		// getFacade().getKonkaR3ShopService().getKonkaR3ShopList(r3);
		//
		// KonkaSellDetails selldetails = new KonkaSellDetails();
		// selldetails.getMap().put("state", 1);
		// selldetails.getMap().put("handle_name", handle_name);
		// selldetails.getMap().put("md_name_like", md_name_like);
		// selldetails.getMap().put("sell_date_start", s.format(startDate) +
		// " 00:00:00");
		// selldetails.getMap().put("sell_date_end", s.format(endDate) +
		// " 23:59:59");
		// List<KonkaSellDetails> sellList =
		// getFacade().getKonkaSellDetailsService().getKonkaSellDetailsList(
		// selldetails);
		//
		// ChannelDataImport dataImport = new ChannelDataImport();
		// dataImport.getMap().put("handle_name", handle_name);
		// dataImport.getMap().put("md_name_like", md_name_like);
		// dataImport.getMap().put("startDate", s.format(startDate) +
		// " 00:00:00");
		// dataImport.getMap().put("endDate", s.format(endDate) + " 23:59:59");
		// List<ChannelDataImport> dataImportList =
		// getFacade().getChannelDataImportService()
		// .getChannelDataListByPdId(dataImport);
		//
		// KonkaStockDetails stockDetails = new KonkaStockDetails();
		// stockDetails.getMap().put("handle_name", handle_name);
		// stockDetails.getMap().put("md_name_like", md_name_like);
		// stockDetails.getMap().put("stock_date_end", s.format(startDate) +
		// " 23:59:59");
		// List<KonkaStockDetails> startStockList =
		// getFacade().getKonkaStockDetailsService()
		// .getKonkaStockDetailsList(stockDetails);
		//
		// stockDetails.getMap().put("stock_date_end", null);
		// stockDetails.getMap().put("stock_date_end", s.format(endDate) +
		// " 23:59:59");
		// List<KonkaStockDetails> endStockList =
		// getFacade().getKonkaStockDetailsService().getKonkaStockDetailsList(
		// stockDetails);
		//
		// for (PePdModel t : pePdModelList) {
		//
		// List<KonkaStockDetails> startList = new
		// ArrayList<KonkaStockDetails>();
		// List<KonkaStockDetails> endList = new ArrayList<KonkaStockDetails>();
		//
		// Long currentCountStart = 0L;
		// BigDecimal currentPriceStart = new BigDecimal(0);
		// BigDecimal currentCostStart = new BigDecimal(0);
		//
		// Long currentCountEnd = 0L;
		// BigDecimal currentPriceEnd = new BigDecimal(0);
		// BigDecimal currentCostEnd = new BigDecimal(0);
		//
		// BigDecimal sellCost = new BigDecimal(0);
		//
		// KonkaStockDetails entity = new KonkaStockDetails();
		//
		// for(KonkaR3Shop i : reShopList){
		// for(KonkaStockDetails entityStock : startStockList){
		// if(entityStock.getR3_code().equals(i.getR3_code())){
		// startList.add(entityStock);
		// }
		// }
		// for(KonkaStockDetails entityStock : endStockList){
		// if(entityStock.getR3_code().equals(i.getR3_code())){
		// endList.add(entityStock);
		// }
		// }
		//
		// KonkaStockDetails entityDetails = this.getResultForDKH(null, t,
		// startList, endList, startDate,
		// endDate);
		// if(entityDetails!=null){
		// if(null!=entityDetails.getMap().get("currentCountStart")){
		// currentCountStart = currentCountStart +
		// Long.valueOf(entityDetails.getMap().get("currentCountStart").toString());
		// }
		// if(null!=entityDetails.getMap().get("currentCostStart")){
		// currentCostStart = currentCostStart.add(new
		// BigDecimal(entityDetails.getMap().get("currentCostStart").toString()));
		// }
		// if(null!=entityDetails.getMap().get("currentCountEnd")){
		// currentCountEnd = currentCountEnd +
		// Long.valueOf(entityDetails.getMap().get("currentCountEnd").toString());
		// }
		// if(null!=entityDetails.getMap().get("currentCostEnd")){
		// currentCostEnd = currentCostEnd.add(new
		// BigDecimal(entityDetails.getMap().get("currentCostEnd").toString()));
		// }
		// if(null!=entityDetails.getMap().get("sellCost")){
		// sellCost = sellCost.add(new
		// BigDecimal(entityDetails.getMap().get("sellCost").toString()));
		// }
		// entity.getMap().put("md_name",
		// entityDetails.getMap().get("md_name"));
		// entity.getMap().put("cls_name",
		// entityDetails.getMap().get("cls_name"));
		// }
		// }
		// if(!currentCountStart.equals(0) &&
		// currentCostStart.compareTo(BigDecimal.ZERO)!=0){
		// currentPriceStart=currentCostStart.divide(new
		// BigDecimal(currentCountStart) ,2,
		// BigDecimal.ROUND_HALF_UP);
		// }
		// if(!currentCountEnd.equals(0) &&
		// currentCostEnd.compareTo(BigDecimal.ZERO)!=0){
		// currentPriceEnd=currentCostEnd.divide(new
		// BigDecimal(currentCountEnd),2,
		// BigDecimal.ROUND_HALF_UP);
		// }
		//
		// entity.getMap().put("currentCountStart", currentCountStart);
		// entity.getMap().put("currentPriceStart", currentPriceStart);
		// entity.getMap().put("currentCostStart", currentCostStart);
		// entity.getMap().put("currentCountEnd", currentCountEnd);
		// entity.getMap().put("currentPriceEnd", currentPriceEnd);
		// entity.getMap().put("currentCostEnd", currentCostEnd);
		// entity.getMap().put("sellCost", sellCost);
		// entity.getMap().put("customer_name", handle_name);
		// Long dataImportSum = 0L;
		// BigDecimal dataImportMoneySum = new BigDecimal(0);
		// for (ChannelDataImport ct : dataImportList) {
		//
		// if (StringUtils.isNotBlank(ct.getColumn_1()) &&
		// StringUtils.isNotBlank(ct.getColumn_11())
		// && ct.getColumn_11().equals(t.getMd_name())) {
		// dataImportSum = dataImportSum + Long.valueOf(ct.getColumn_12());// 数量
		// dataImportMoneySum = dataImportMoneySum.add(new
		// BigDecimal(ct.getColumn_14().toString()));// 总金额
		// BigDecimal dataImportPrice = new BigDecimal(0);// 单价
		// int r = dataImportMoneySum.compareTo(BigDecimal.ZERO);
		// if (r == 0 || (dataImportSum == 0)) {
		// dataImportPrice = BigDecimal.ZERO;
		// } else {
		// dataImportPrice = dataImportMoneySum.divide(new
		// BigDecimal(dataImportSum), 2,
		// BigDecimal.ROUND_HALF_UP);
		// }
		// entity.getMap().put("dataImportSum", dataImportSum);
		// entity.getMap().put("dataImportMoneySum", dataImportMoneySum);
		// entity.getMap().put("dataImportPrice", dataImportPrice);
		// }
		// }
		// Long sellCount = 0L;
		// BigDecimal sellMoney = new BigDecimal(0);// 销售金额
		// for (KonkaSellDetails st : sellList) {
		// if (null!=st.getMap().get("cus_sn")
		// && StringUtils.isNotBlank(st.getPd_id().toString()) &&
		// st.getPd_id().equals(t.getPd_id())) {
		// sellCount = sellCount + st.getSell_count();
		// sellMoney = sellMoney.add(st.getSell_money().multiply(new
		// BigDecimal(st.getSell_count())));
		//
		// BigDecimal price = new BigDecimal(0);// 销售单价
		// int z = sellMoney.compareTo(BigDecimal.ZERO);
		// if (sellCount == 0 || z == 0) {
		// price = BigDecimal.ZERO;
		// } else {
		// price = sellMoney.divide(new BigDecimal(sellCount), 2,
		// BigDecimal.ROUND_HALF_UP);
		// }
		// BigDecimal cost = new BigDecimal(0);
		// if(null != entity.getMap().get("sellCost")){
		// cost = new BigDecimal(entity.getMap().get("sellCost").toString());
		// }
		// BigDecimal sellPrice = new BigDecimal(0);// 成本单价
		// int p = cost.compareTo(BigDecimal.ZERO);
		// if (sellCount == 0 || p == 0) {
		// sellPrice = BigDecimal.ZERO;
		// } else {
		// sellPrice = cost.divide(new BigDecimal(sellCount), 2,
		// BigDecimal.ROUND_HALF_UP);
		// }
		// entity.getMap().put("sellCount", sellCount);
		// entity.getMap().put("sellMoney", sellMoney);
		// entity.getMap().put("sellPrice", sellPrice);
		// entity.getMap().put("price", price);
		// }
		// }
		// stockList.add(entity);
		// }

		// }

		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

		// 获取用户角色
		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(peProdUser.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);

		boolean role_id_ge_20_le_29 = false;
		boolean role_id_ge_30_le_39 = false;
		boolean role_id_eq_10 = false;
		boolean role_id_ge_30 = false;
		for (PeRoleUser peRoleUser : peRoleUserList) {
			if (peRoleUser.getRole_id() >= 20L && peRoleUser.getRole_id() < 29L) {
				role_id_ge_20_le_29 = true;
			}
			if (peRoleUser.getRole_id() >= 30L && peRoleUser.getRole_id() < 39L) {
				role_id_ge_30_le_39 = true;
			}
			if (peRoleUser.getRole_id() == 10L) {
				role_id_eq_10 = true;
			}
			if (peRoleUser.getRole_id() >= 30L) {
				role_id_ge_30 = true;
			}
		}

		// 产品类型
		List<BasePdClazz> basePdClassList = new ArrayList<BasePdClazz>();

		if (role_id_eq_10) {
			BasePdClazz basePdClazz = new BasePdClazz();
			basePdClazz.setIs_del(0);
			basePdClazz.setPar_id(1010000L);
			basePdClassList = getFacade().getBasePdClazzService().getBasePdClazzList(basePdClazz);
		} else if (role_id_ge_20_le_29) {// 事业部
			BasePdClazz basePdClazz = new BasePdClazz();
			basePdClazz.setIs_del(0);
			basePdClassList = getFacade().getBasePdClazzService().getBasePdClazzList(basePdClazz);
			request.setAttribute("role_id_syb", "syb");
		} else if (role_id_ge_30_le_39) {// 分公司
			BasePdClazz basePdClazz = new BasePdClazz();
			basePdClazz.getMap().put("getOwnDeptCls", "ture");
			basePdClazz.getMap().put("dept_id", peProdUser.getDept_id());
			basePdClazz.setIs_del(0);
			basePdClassList = getFacade().getBasePdClazzService().getBasePdClazzList(basePdClazz);
		} else {// 经办 或业务员
			BasePdClazz basePdClazz = new BasePdClazz();
			basePdClazz.getMap().put("getOwnDeptCls", "ture");
			if (super.getSuperiorForDeptType(peProdUser.getDept_id(), 3) != null) {
				basePdClazz.getMap().put("dept_id",
						super.getSuperiorForDeptType(peProdUser.getDept_id(), 3).getDept_id());
			}
			basePdClazz.setIs_del(0);
			basePdClassList = getFacade().getBasePdClazzService().getBasePdClazzList(basePdClazz);
		}
		KonkaR3Shop r3 = new KonkaR3Shop();
		r3.setIs_del(0L);
		if (role_id_ge_30) {
			r3.setBranch_area_name(super.getSuperiorForDeptType(super.getSessionUserInfo(request).getDept_id(), 3)
					.getDept_name());
		}
		List<KonkaR3Shop> handleList = getFacade().getKonkaR3ShopService().getKonkaR3ShopGroupByHandleName(r3);

		request.setAttribute("handleList", handleList);
		request.setAttribute("stockList", stockList);
		request.getSession().setAttribute("stockList", stockList);
		request.setAttribute("basePdClassList", basePdClassList);
		return mapping.findForward("list");
	}

	public KonkaStockDetails getResultForDKHNew(KonkaR3Shop i, PePdModel t, List<KonkaStockDetails> startStockList,
			List<KonkaStockDetails> endStockList, Date startDate, Date endDate) throws Exception {
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");

		BigDecimal sellCostSumStart = new BigDecimal(0);
		BigDecimal sellCostSumEnd = new BigDecimal(0);

		KonkaStockDetails entity = new KonkaStockDetails();

		KonkaStockDetails stockStart = new KonkaStockDetails();
		KonkaStockDetails stockEnd = new KonkaStockDetails();

		if (null == t.getMap().get("stock_date")) {
			// KonkaStockDetails stockDetails = new KonkaStockDetails();
			// stockDetails.setId(0L);
			// stockDetails.setStock_count(0L);
			// stockDetails.setStock_cost(new BigDecimal(0));
			// stockDetails.setPd_id(t.getPd_id());
			// stockDetails.setR3_code(i.getR3_code());
			// stockDetails.setStock_date(s.parse("2011-01-01 00:00:00"));
			// stockDetails.getMap().put("md_name", t.getMd_name());
			// stockStart = super.getCurrentCountCost(stockDetails,
			// s.format(startDate) + " 00:00:00"); //TODO 排查
			// sellCostSumStart = new
			// BigDecimal(stockStart.getMap().get("sellCostSum").toString());
			// entity.getMap().put("currentCountStart",
			// stockStart.getCurrent_count());
			// entity.getMap().put("currentPriceStart",
			// stockStart.getCurrent_cost());
			// entity.getMap().put("currentCostStart",
			// stockStart.getCurrent_cost().multiply(new
			// BigDecimal(stockStart.getCurrent_count())));
			//
			// stockEnd = super.getCurrentCountCost(stockDetails,
			// s.format(endDate) + " 23:59:59");
			// sellCostSumEnd = new
			// BigDecimal(stockEnd.getMap().get("sellCostSum").toString());

			// entity.getMap().put("currentCountEnd",
			// stockEnd.getCurrent_count());
			// entity.getMap().put("currentPriceEnd",
			// stockEnd.getCurrent_cost());
			// entity.getMap().put("currentCostEnd",
			// stockEnd.getCurrent_cost().multiply(new
			// BigDecimal(stockEnd.getCurrent_count())));
		}
		if (null != t.getMap().get("stock_date")) {

			for (KonkaStockDetails sd : startStockList) {
				if (sd.getPd_id().equals(t.getPd_id())) {
					stockStart = super.getCurrentCountCost(sd, s.format(startDate) + " 00:00:00"); // TODO
																									// 排查
					sellCostSumStart = new BigDecimal(stockStart.getMap().get("sellCostSum").toString());
					entity.getMap().put("currentCountStart", stockStart.getCurrent_count());
					entity.getMap().put("currentPriceStart", stockStart.getCurrent_cost());
					entity.getMap().put("currentCostStart",
							stockStart.getCurrent_cost().multiply(new BigDecimal(stockStart.getCurrent_count())));
				} else {
					if (entity.getMap().get("currentCountStart") == null) {
						entity.getMap().put("currentCountStart", 0);
					}
					if (entity.getMap().get("currentPriceStart") == null) {
						entity.getMap().put("currentPriceStart", 0);
					}
					if (entity.getMap().get("currentCostStart") == null) {
						entity.getMap().put("currentCostStart", 0);
					}
				}
			}

			for (KonkaStockDetails sd : endStockList) {
				if (sd.getPd_id().equals(t.getPd_id())) {
					stockEnd = super.getCurrentCountCost(sd, s.format(endDate) + " 23:59:59");
					sellCostSumEnd = new BigDecimal(stockEnd.getMap().get("sellCostSum").toString());

					entity.getMap().put("currentCountEnd", stockEnd.getCurrent_count());
					entity.getMap().put("currentPriceEnd", stockEnd.getCurrent_cost());
					entity.getMap().put("currentCostEnd",
							stockEnd.getCurrent_cost().multiply(new BigDecimal(stockEnd.getCurrent_count())));
				} else {
					if (entity.getMap().get("currentCountEnd") == null) {
						entity.getMap().put("currentCountEnd", 0);
					}
					if (entity.getMap().get("currentPriceEnd") == null) {
						entity.getMap().put("currentPriceEnd", 0);
					}
					if (entity.getMap().get("currentCostEnd") == null) {
						entity.getMap().put("currentCostEnd", 0);
					}
				}
			}
		}

		BigDecimal sellCost = sellCostSumEnd.subtract(sellCostSumStart); // 销售成本

		if (i != null) {
			entity.getMap().put("customer_name", i.getCustomer_name());
		}
		entity.getMap().put("sellCost", sellCost);
		entity.getMap().put("md_name", t.getMd_name());
		entity.getMap().put("cls_name", t.getMap().get("cls_name"));

		return entity;
	}

	public KonkaStockDetails getResultForDKH(KonkaR3Shop i, PePdModel t, List<KonkaStockDetails> startStockList,
			List<KonkaStockDetails> endStockList, Date startDate, Date endDate) throws Exception {
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");

		BigDecimal sellCostSumStart = new BigDecimal(0);
		BigDecimal sellCostSumEnd = new BigDecimal(0);

		KonkaStockDetails entity = new KonkaStockDetails();
		// entity.getMap().put("currentCountStart", 0);
		// entity.getMap().put("currentPriceStart", 0);
		// entity.getMap().put("currentCostStart",0);
		// entity.getMap().put("currentCountEnd",0);
		// entity.getMap().put("currentPriceEnd", 0);
		// entity.getMap().put("currentCostEnd",0);

		KonkaStockDetails stockStart = new KonkaStockDetails();
		KonkaStockDetails stockEnd = new KonkaStockDetails();

		// KonkaStockDetails stockDetails = new KonkaStockDetails();
		// stockDetails.setPd_id(t.getPd_id());
		// stockDetails.setR3_code(i.getR3_code());
		// stockDetails.getMap().put("stock_date_end",
		// s.format(startDate) + " 23:59:59");
		// stockDetails = getFacade().getKonkaStockDetailsService()
		// .getKonkaStockDetails(stockDetails);

		for (KonkaStockDetails sd : startStockList) {
			if (sd.getPd_id().equals(t.getPd_id())) {
				stockStart = super.getCurrentCountCost(sd, s.format(startDate) + " 00:00:00"); // TODO
																								// 排查
				sellCostSumStart = new BigDecimal(stockStart.getMap().get("sellCostSum").toString());
				entity.getMap().put("currentCountStart", stockStart.getCurrent_count());
				entity.getMap().put("currentPriceStart", stockStart.getCurrent_cost());
				entity.getMap().put("currentCostStart",
						stockStart.getCurrent_cost().multiply(new BigDecimal(stockStart.getCurrent_count())));
			} else {
				if (entity.getMap().get("currentCountStart") == null) {
					entity.getMap().put("currentCountStart", 0);
				}
				if (entity.getMap().get("currentPriceStart") == null) {
					entity.getMap().put("currentPriceStart", 0);
				}
				if (entity.getMap().get("currentCostStart") == null) {
					entity.getMap().put("currentCostStart", 0);
				}
			}
		}

		// KonkaStockDetails stockDetailsEnd = new KonkaStockDetails();
		// stockDetailsEnd.setPd_id(t.getPd_id());
		// stockDetailsEnd.setR3_code(i.getR3_code());
		// stockDetailsEnd.getMap().put("stock_date_end",
		// s.format(endDate) + " 23:59:59");
		// stockDetailsEnd = getFacade().getKonkaStockDetailsService()
		// .getKonkaStockDetails(stockDetailsEnd);

		for (KonkaStockDetails sd : endStockList) {
			if (sd.getPd_id().equals(t.getPd_id())) {
				stockEnd = super.getCurrentCountCost(sd, s.format(endDate) + " 23:59:59");
				sellCostSumEnd = new BigDecimal(stockEnd.getMap().get("sellCostSum").toString());

				// KonkaStockRegulation stockRegulation = new
				// KonkaStockRegulation();
				// stockRegulation.setStock_id(sd.getId());
				// stockRegulation.getMap().put("regulation_date_start",
				// s.format(startDate) + " 00:00:00");
				// stockRegulation.getMap().put("regulation_date_end",
				// s.format(endDate) + " 23:59:59");
				// Long regulation =
				// getFacade().getKonkaStockRegulationService().getKonkaStockRegulationSumByStockId(
				// stockRegulation);
				// BigDecimal regulationCost =
				// getFacade().getKonkaStockRegulationService()
				// .getKonkaStockRegulationCostSumByStockId(stockRegulation);
				//
				// BigDecimal regulationPrice = new BigDecimal(0);
				// if (regulation != null && regulationCost != null) {
				//
				// int q = regulationCost.compareTo(BigDecimal.ZERO);
				// if (q == 0 || (regulation == 0)) {
				// regulationPrice = BigDecimal.ZERO;
				// } else {
				// regulationPrice = regulationCost
				// .divide(new BigDecimal(regulation), 2,
				// BigDecimal.ROUND_HALF_UP);
				// }
				// } else {
				// regulation = 0L;
				// regulationCost = new BigDecimal(0);
				// }
				// entity.getMap().put("regulation", regulation);
				// entity.getMap().put("regulationPrice", regulationPrice);
				// entity.getMap().put("regulationCost", regulationCost);
				entity.getMap().put("currentCountEnd", stockEnd.getCurrent_count());
				entity.getMap().put("currentPriceEnd", stockEnd.getCurrent_cost());
				entity.getMap().put("currentCostEnd",
						stockEnd.getCurrent_cost().multiply(new BigDecimal(stockEnd.getCurrent_count())));
			} else {
				// if(entity.getMap().get("regulation")==null){
				// entity.getMap().put("regulation", 0);
				// }
				// if(entity.getMap().get("regulationPrice")==null){
				// entity.getMap().put("regulationPrice", 0);
				// }
				// if(entity.getMap().get("regulationCost")==null){
				// entity.getMap().put("regulationCost", 0);
				// }
				if (entity.getMap().get("currentCountEnd") == null) {
					entity.getMap().put("currentCountEnd", 0);
				}
				if (entity.getMap().get("currentPriceEnd") == null) {
					entity.getMap().put("currentPriceEnd", 0);
				}
				if (entity.getMap().get("currentCostEnd") == null) {
					entity.getMap().put("currentCostEnd", 0);
				}
			}
		}

		// log.info("333333333333333333333 stockStart.getCurrent_count()=="+
		// stockStart.getCurrent_count());
		// log.info("333333333333333333333 stockStart.getCurrent_cost()=="+
		// stockStart.getCurrent_cost());
		// entity.getMap().put("currentCountStart",
		// stockStart.getCurrent_count());
		// entity.getMap().put("currentPriceStart",
		// stockStart.getCurrent_cost());
		// entity.getMap().put("currentCostStart",stockStart.getCurrent_cost().multiply(
		// new BigDecimal(stockStart.getCurrent_count())));
		//
		// log.info("4444444444444444444444444 stockEnd.getCurrent_count()=="+
		// stockEnd.getCurrent_count());
		// log.info("4444444444444444444444444 stockEnd.getCurrent_cost()=="+
		// stockEnd.getCurrent_cost());
		// entity.getMap().put("currentCountEnd", stockEnd.getCurrent_count());
		// entity.getMap().put("currentPriceEnd", stockEnd.getCurrent_cost());
		// entity.getMap().put(
		// "currentCostEnd",
		// stockEnd.getCurrent_cost().multiply(
		// new BigDecimal(stockEnd.getCurrent_count())));

		BigDecimal sellCost = sellCostSumEnd.subtract(sellCostSumStart); // 销售成本

		if (i != null) {
			entity.getMap().put("customer_name", i.getCustomer_name());
		}
		entity.getMap().put("sellCost", sellCost);
		entity.getMap().put("md_name", t.getMd_name());
		entity.getMap().put("cls_name", t.getMap().get("cls_name"));

		return entity;
	}

	public void getTotle(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response, List<KonkaStockDetails> stockList) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		Long totleCurrentCountStart = 0L;
		Long totleCurrentCountEnd = 0L;
		Long totleDataImportSum = 0L;
		Long totleSellCount = 0L;

		BigDecimal totleCurrentCostStart = new BigDecimal(0);
		BigDecimal totleCurrentCostEnd = new BigDecimal(0);
		BigDecimal totleDataImportMoneySum = new BigDecimal(0);
		BigDecimal totleSellCost = new BigDecimal(0);
		BigDecimal totleSellMoney = new BigDecimal(0);

		for (KonkaStockDetails t : stockList) {
			if (null != t.getMap().get("currentPriceEnd")) {
				if (null != t.getMap().get("currentCountStart")) {
					totleCurrentCountStart = totleCurrentCountStart
							+ Long.valueOf(t.getMap().get("currentCountStart").toString());
				}
				if (null != t.getMap().get("currentCountEnd")) {
					totleCurrentCountEnd = totleCurrentCountEnd
							+ Long.valueOf(t.getMap().get("currentCountEnd").toString());
				}
				if (null != t.getMap().get("dataImportSum")) {
					totleDataImportSum = totleDataImportSum + Long.valueOf(t.getMap().get("dataImportSum").toString());
				}
				if (null != t.getMap().get("sellCount")) {
					totleSellCount = totleSellCount + Long.valueOf(t.getMap().get("sellCount").toString());
				}

				if (null != t.getMap().get("currentCostStart")) {
					totleCurrentCostStart = totleCurrentCostStart.add(new BigDecimal(t.getMap().get("currentCostStart")
							.toString()));
				}
				if (null != t.getMap().get("currentCostEnd")) {
					totleCurrentCostEnd = totleCurrentCostEnd.add(new BigDecimal(t.getMap().get("currentCostEnd")
							.toString()));
				}
				if (null != t.getMap().get("dataImportMoneySum")) {
					totleDataImportMoneySum = totleDataImportMoneySum.add(new BigDecimal(t.getMap()
							.get("dataImportMoneySum").toString()));
				}
				if (null != t.getMap().get("sellCost")) {
					totleSellCost = totleSellCost.add(new BigDecimal(t.getMap().get("sellCost").toString()));
				}
				if (null != t.getMap().get("sellMoney")) {
					totleSellMoney = totleSellMoney.add(new BigDecimal(t.getMap().get("sellMoney").toString()));
				}
			}
		}

		dynaBean.set("totleCurrentCountStart", totleCurrentCountStart);
		dynaBean.set("totleCurrentCountEnd", totleCurrentCountEnd);
		dynaBean.set("totleDataImportSum", totleDataImportSum);
		dynaBean.set("totleSellCount", totleSellCount);
		dynaBean.set("totleCurrentCostStart", totleCurrentCostStart);
		dynaBean.set("totleCurrentCostEnd", totleCurrentCostEnd);
		dynaBean.set("totleDataImportMoneySum", totleDataImportMoneySum);
		dynaBean.set("totleSellCost", totleSellCost);
		dynaBean.set("totleSellMoney", totleSellMoney);
		request.getSession().setAttribute("totleCurrentCountStart", totleCurrentCountStart);
		request.getSession().setAttribute("totleCurrentCountEnd", totleCurrentCountEnd);
		request.getSession().setAttribute("totleDataImportSum", totleDataImportSum);
		request.getSession().setAttribute("totleSellCount", totleSellCount);
		request.getSession().setAttribute("totleCurrentCostStart", totleCurrentCostStart);
		request.getSession().setAttribute("totleCurrentCostEnd", totleCurrentCostEnd);
		request.getSession().setAttribute("totleDataImportMoneySum", totleDataImportMoneySum);
		request.getSession().setAttribute("totleSellCost", totleSellCost);
		request.getSession().setAttribute("totleSellMoney", totleSellMoney);
	}

	@SuppressWarnings("unchecked")
	public ActionForward download(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		// DynaBean dynaBean = (DynaBean) form;

		// HttpSession session = request.getSession();
		// PeProdUser ui = (PeProdUser)
		// session.getAttribute(Constants.USER_INFO);
		//
		// PeRoleUser peRoleUser = (PeRoleUser)
		// request.getSession().getAttribute(Constants.ROLE_INFO);

		List<KonkaStockDetails> stockList = (List<KonkaStockDetails>) request.getSession().getAttribute("stockList");
		Long totleCurrentCountStart = (Long) request.getSession().getAttribute("totleCurrentCountStart");
		Long totleCurrentCountEnd = (Long) request.getSession().getAttribute("totleCurrentCountEnd");
		Long totleDataImportSum = (Long) request.getSession().getAttribute("totleDataImportSum");
		Long totleSellCount = (Long) request.getSession().getAttribute("totleSellCount");
		BigDecimal totleCurrentCostStart = (BigDecimal) request.getSession().getAttribute("totleCurrentCostStart");
		BigDecimal totleCurrentCostEnd = (BigDecimal) request.getSession().getAttribute("totleCurrentCostEnd");
		BigDecimal totleDataImportMoneySum = (BigDecimal) request.getSession().getAttribute("totleDataImportMoneySum");
		BigDecimal totleSellCost = (BigDecimal) request.getSession().getAttribute("totleSellCost");
		BigDecimal totleSellMoney = (BigDecimal) request.getSession().getAttribute("totleSellMoney");
		WritableWorkbook wwb = null;
		String excel_name = "Customer_Invoicing.xls";

		try {
			// 获取系统实际路径
			String SystemPath = getServlet().getServletContext().getRealPath(String.valueOf(File.separatorChar));

			// 首先要使用Workbook类的工厂方法创建一个可写入的工作薄(Workbook)对象
			wwb = Workbook.createWorkbook(new File(SystemPath + "files/" + excel_name));
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 创建一个可写入的工作表
		// Workbook的createSheet方法有两个参数，第一个是工作表的名称，第二个是工作表在工作薄中的位置
		WritableSheet ws = wwb.createSheet("sheet1", 0);
		if (wwb != null) {
			ws.addCell(new Label(0, 0, "序号"));
			ws.addCell(new Label(1, 0, "名称"));
			ws.addCell(new Label(2, 0, "产品型号"));
			ws.addCell(new Label(3, 0, "产品类型"));
			ws.addCell(new Label(4, 0, ""));
			ws.addCell(new Label(5, 0, "期初结存"));
			ws.addCell(new Label(6, 0, ""));
			ws.addCell(new Label(7, 0, ""));
			ws.addCell(new Label(8, 0, "日常进货"));
			ws.addCell(new Label(9, 0, ""));
			ws.addCell(new Label(10, 0, ""));
			ws.addCell(new Label(11, 0, ""));
			ws.addCell(new Label(12, 0, "日常出货"));
			ws.addCell(new Label(13, 0, ""));
			ws.addCell(new Label(14, 0, ""));
			ws.addCell(new Label(15, 0, ""));
			ws.addCell(new Label(16, 0, "期末结存"));
			ws.addCell(new Label(17, 0, ""));
			ws.addCell(new Label(4, 1, "数量"));
			ws.addCell(new Label(5, 1, "单价"));
			ws.addCell(new Label(6, 1, "金额"));
			ws.addCell(new Label(7, 1, "数量"));
			ws.addCell(new Label(8, 1, "单价"));
			ws.addCell(new Label(9, 1, "金额"));
			ws.addCell(new Label(10, 1, "数量"));
			ws.addCell(new Label(11, 1, "单价"));
			ws.addCell(new Label(12, 1, "成本金额"));
			ws.addCell(new Label(13, 1, "零售单价"));
			ws.addCell(new Label(14, 1, "零售金额"));
			ws.addCell(new Label(15, 1, "数量"));
			ws.addCell(new Label(16, 1, "单价"));
			ws.addCell(new Label(17, 1, "金额"));
			ws.addCell(new Label(3, 2, "合计"));
			ws.addCell(new Label(4, 2, totleCurrentCountStart.toString()));
			ws.addCell(new Label(5, 2, ""));
			ws.addCell(new Label(6, 2, totleCurrentCostStart.toEngineeringString()));
			ws.addCell(new Label(7, 2, totleDataImportSum.toString()));
			ws.addCell(new Label(8, 2, ""));
			ws.addCell(new Label(9, 2, totleDataImportMoneySum.toString()));
			ws.addCell(new Label(10, 2, totleSellCount.toString()));
			ws.addCell(new Label(11, 2, ""));
			ws.addCell(new Label(12, 2, totleSellCost.toString()));
			ws.addCell(new Label(13, 2, ""));
			ws.addCell(new Label(14, 2, totleSellMoney.toString()));
			ws.addCell(new Label(15, 2, totleCurrentCountEnd.toString()));
			ws.addCell(new Label(16, 2, ""));
			ws.addCell(new Label(17, 2, totleCurrentCostEnd.toString()));
		}
		Integer i = 3, j = 1;
		for (KonkaStockDetails t : stockList) {
			if (wwb != null) {
				if (!t.getMap().get("currentCostStart").equals("0") || null != t.getMap().get("dataImportMoneySum")
						|| null != t.getMap().get("sellMoney") || !t.getMap().get("currentCostEnd").equals("0")) {
					ws.addCell(new Label(0, i, j.toString()));
					ws.addCell(new Label(1, i, t.getMap().get("customer_name").toString()));
					ws.addCell(new Label(2, i, t.getMap().get("md_name").toString()));
					ws.addCell(new Label(3, i, t.getMap().get("cls_name").toString()));
					ws.addCell(new Label(4, i, t.getMap().get("currentCountStart").toString()));
					ws.addCell(new Label(5, i, t.getMap().get("currentPriceStart").toString()));
					ws.addCell(new Label(6, i, t.getMap().get("currentCostStart").toString()));
					ws.addCell(new Label(7, i, t.getMap().get("dataImportSum").toString()));
					ws.addCell(new Label(8, i, t.getMap().get("dataImportPrice").toString()));
					ws.addCell(new Label(9, i, t.getMap().get("dataImportMoneySum").toString()));
					ws.addCell(new Label(10, i, t.getMap().get("sellCount").toString()));
					ws.addCell(new Label(11, i, t.getMap().get("sellPrice").toString()));
					ws.addCell(new Label(12, i, t.getMap().get("sellCost").toString()));
					ws.addCell(new Label(13, i, t.getMap().get("price").toString()));
					ws.addCell(new Label(14, i, t.getMap().get("sellMoney").toString()));
					ws.addCell(new Label(15, i, t.getMap().get("currentCountEnd").toString()));
					ws.addCell(new Label(16, i, t.getMap().get("currentPriceEnd").toString()));
					ws.addCell(new Label(17, i, t.getMap().get("currentCostEnd").toString()));
				}
			}
			i++;
			j++;
		}
		try {
			wwb.write();// 从内存中写入文件中
			wwb.close();// 关闭资源，释放内存
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}

		String ctx = super.getCtxPath(request);
		super.renderJavaScript(response, "window.location.href='" + ctx + "/files/" + excel_name + "';");
		return null;
	}

	public ActionForward calcheck(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String date_like = (String) dynaBean.get("date_like");
		KonkaSellReportTmp _t = new KonkaSellReportTmp();
		_t.getMap().put("date_like", date_like);
		Long i = super.getFacade().getKonkaSellReportTmpService().getKonkaSellReportTmpCount(_t);
		if (i > 0)
			super.renderText(response, "1");
		else
			super.renderText(response, "2");
		return null;
	}

	public ActionForward toCal(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return new ActionForward(response.encodeRedirectURL("/admin/KonkaRealTimeStock/toCal.jsp"));

	}

	public ActionForward cal(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String date_like = (String) dynaBean.get("date_like");
		super.getFacade().getKonkaSellReportTmpService().calculateDay(date_like);
		return null;
	}

	public ActionForward Calcula(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String date_like = (String) dynaBean.get("date_like");
		SimpleDateFormat _ft = new SimpleDateFormat("yyyyMMdd");
		Date DayDate = _ft.parse(date_like);
		Date today = new Date();

		log.info("==========DayDate:   " + DayDate);
		log.info("==========today:   " + today);

		super.getFacade().getKonkaSellReportTmpService().calculateDay(date_like);

		while (DayDate.before(today)) {

			Calendar calendar11 = Calendar.getInstance();
			calendar11.setTime(DayDate);
			calendar11.add(java.util.Calendar.DAY_OF_YEAR, +1);
			DayDate = calendar11.getTime();
			super.getFacade().getKonkaSellReportTmpService().calculateDay(_ft.format(DayDate));
		}
		return null;
	}
}
