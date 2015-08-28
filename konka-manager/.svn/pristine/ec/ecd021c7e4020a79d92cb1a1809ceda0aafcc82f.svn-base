package com.ebiz.mmt.web.struts.jxcnokey;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.Alignment;
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
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.BaseBrandInfo;
import com.ebiz.mmt.domain.BasePdType;
import com.ebiz.mmt.domain.BaseProvinceListFour;
import com.ebiz.mmt.domain.JnhmPdContents;
import com.ebiz.mmt.domain.JnhmSelledPdCode;
import com.ebiz.mmt.domain.JxcCustomer;
import com.ebiz.mmt.domain.JxcPd;
import com.ebiz.mmt.domain.JxcPdType;
import com.ebiz.mmt.domain.JxcSellBill;
import com.ebiz.mmt.domain.JxcSellBillDetails;
import com.ebiz.mmt.domain.JxcShopOrgSn;
import com.ebiz.mmt.domain.JxcSzDetails;
import com.ebiz.mmt.domain.KonkaAgentsShopRel;
import com.ebiz.mmt.domain.KonkaBranchCategory;
import com.ebiz.mmt.domain.KonkaJxcFhBill;
import com.ebiz.mmt.domain.KonkaJxcFhBillDetails;
import com.ebiz.mmt.domain.KonkaPeAttachments;
import com.ebiz.mmt.domain.MmtEntpShop;
import com.ebiz.mmt.domain.MvPdTypeJoinBrand;
import com.ebiz.mmt.domain.StdEntpMain;
import com.ebiz.mmt.domain.UserInfo;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.MmtFilePathConfig;
import com.ebiz.mmt.web.util.FileTools;
import com.ebiz.ssi.web.struts.bean.Pager;
import com.ebiz.ssi.web.struts.bean.UploadFile;

/**
 * @author Du,Zhiming
 * @version 2011-10-11
 */
public class JxcSellBillAction extends BaseJxcAction {
	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String keySeq = (String) dynaBean.get("keySeq");
		if (StringUtils.isBlank(keySeq)) {
			String msg = super.getMessage(request, "client.login.fail.keyseq.none");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}
		UserInfo currentUser = super.getUserInfoFromWebService(request, response);
		int flag_fx = 0;
		if (null != currentUser) {
			Long shop_id = currentUser.getEntp_shop().getShop_id();// 获取当前用户的shop_id
			KonkaAgentsShopRel Konka_rel = new KonkaAgentsShopRel();
			Konka_rel.setShop_id(shop_id);
			Long count_xj_shop = getFacade().getKonkaAgentsShopRelService().getKonkaAgentsShopRelCount(Konka_rel);
			if (count_xj_shop > 0) {
				flag_fx = 1;
			}
		}
		request.setAttribute("flag_fx", flag_fx);
		// 判断是否是代理商
		HashMap<String, String> result = super.getKonkaDeptIdAndType(currentUser.getEntp_shop().getShop_id());
		if ("10".endsWith(result.get("result_code"))) {
			dynaBean.set("hasWds", "true");
		}
		return new ActionForward("/JxcSellBill/form_switch.jsp");
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String keySeq = (String) dynaBean.get("keySeq");
		String customer_name = (String) dynaBean.get("customer_name");// 消费者名
		String search_flag = (String) dynaBean.get("search_flag");// 是否点查询

		request.setAttribute("not_validate_record", "true");
		// StdEntpUser user = super.getStdEntpUserFromRequest(request, response,
		// keySeq);
		UserInfo user = super.getUserInfoFromWebService(request, response);

		if (null == user) {
			return null;
		}
		dynaBean.set("keySeq", keySeq);

		Long shop_id = user.getEntp_shop().getShop_id();
		// request.setAttribute("own_sys", user.getOwn_sys());

		Pager pager = (Pager) dynaBean.get("pager");
		// String customer_id = (String) dynaBean.get("customer_id");
		String sell_date_ge = (String) dynaBean.get("sell_date_ge");
		String sell_date_le = (String) dynaBean.get("sell_date_le");

		Date now = new Date();
		if (StringUtils.isBlank(sell_date_ge)) {
			sell_date_ge = DateFormatUtils.format(now, "yyyy-MM-") + "01";
		}
		if (StringUtils.isBlank(sell_date_le)) {
			sell_date_le = DateFormatUtils.format(now, "yyyy-MM-dd");
		}
		BigDecimal countMoney = new BigDecimal(0);
		BigDecimal countPayMoney = new BigDecimal(0);
		JxcSellBill sellBill = new JxcSellBill();

		if (StringUtils.isNotBlank(search_flag)) {

			// if (GenericValidator.isLong(customer_id)) {
			// sellBill.setCustomer_id(Long.valueOf(customer_id));
			// }
			if (StringUtils.isNotBlank(customer_name.trim())) {// 根据消费者查询列表条件
				sellBill.getMap().put("customer_name", customer_name.trim());
				if (StringUtils.isNotBlank(shop_id.toString())) {// 根据消费者shop_id查询列表条件
					sellBill.getMap().put("customer_shop_id", shop_id);
				}
			}
			sellBill.getMap().put("sell_date_ge", sell_date_ge);
			sellBill.getMap().put("sell_date_le", sell_date_le);
			sellBill.getMap().put("is_del_j", 0);
			sellBill.getMap().put("is_del_c", 0);
			sellBill.getMap().put("shop_id_j", shop_id);

			Long recCount = super.getFacade().getJxcSellBillService().getJxcSellBillCountForXS(sellBill);

			pager.init(recCount, pager.getPageSize(), pager.getRequestPage());
			sellBill.getRow().setFirst(pager.getFirstRow());
			sellBill.getRow().setCount(pager.getRowCount());
			List<JxcSellBill> sellBillList = super.getFacade().getJxcSellBillService()
					.getJxcSellBillPaginatedListForXS(sellBill);
			request.setAttribute("sellBillList", sellBillList);

			for (JxcSellBill jxcSellBill : sellBillList) {
				countMoney = countMoney.add(jxcSellBill.getMoney());
				countPayMoney = countPayMoney.add(jxcSellBill.getPay_money());
			}
		}

		request.setAttribute("countMoney", countMoney);
		request.setAttribute("countPayMoney", countPayMoney);

		// 客户列表 业务更改不需取此列表 暂留备用
		// List<JxcCustomer> customerList = super.getCustomerList(shop_id);
		// request.setAttribute("customerList", customerList);

		dynaBean.set("sell_date_ge", sell_date_ge);
		dynaBean.set("sell_date_le", sell_date_le);

		return mapping.findForward("list");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String keySeq = (String) dynaBean.get("keySeq");

		request.setAttribute("not_validate_record", "true");
		// StdEntpUser user = super.getStdEntpUserFromRequest(request, response,
		// keySeq);
		UserInfo user = super.getUserInfoFromWebService(request, response);
		if (null == user) {
			return null;
		}
		dynaBean.set("keySeq", keySeq);
		// dynaBean.set("province", "150000");
		// Long shop_id = user.getStdEntpMain().getShop_id();

		// 客户列表 业务修改后无需客户列表 暂留备用
		// List<JxcCustomer> customerList = super.getCustomerList(shop_id);
		// request.setAttribute("customerList", customerList);

		// 商铺产品列表
		// JxcPd pd = new JxcPd();
		// pd.setShop_id(shop_id);
		// pd.setIs_del(0);
		// pd.getMap().put("count_gt", true);
		// pd.setOwn_sys(0);// 默认非家电下乡产品
		// List<JxcPd> pdList =
		// super.getFacade().getJxcPdService().getJxcPdListForJX(pd);
		// request.setAttribute("pdList", pdList);

		BasePdType basePdType = new BasePdType();
		basePdType.setDel_mark(new Short((short) 0));
		basePdType.setIs_model(new Short((short) 1));
		List<BasePdType> basePdTypeList = super.getFacade().getBasePdTypeService().getBasePdTypeList(basePdType);
		request.setAttribute("basePdTypeList", basePdTypeList);// 产品大类

		JxcPdType jxcPdType = new JxcPdType();
		List<JxcPdType> JxcPdTypeList = getFacade().getJxcPdTypeService().getJxcPdTypeList(jxcPdType);
		request.setAttribute("JxcPdTypeList", JxcPdTypeList);// 根据Shop_id查进销存自定义产品大类

		Date now = new Date();
		request.setAttribute("today", DateFormatUtils.format(now, "yyyy-MM-dd"));
		request.setAttribute("sn", "XS" + DateFormatUtils.format(now, "yyyyMMddHHmmssSSS"));
		request.setAttribute("userName", user.getUser_name());
		request.setAttribute("user", user);

		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		String keySeq = (String) dynaBean.get("keySeq");
		// String customer_id = (String) dynaBean.get("customer_id");
		String sell_date = (String) dynaBean.get("sell_date");
		String remarks = (String) dynaBean.get("remarks");
		String opr_man = (String) dynaBean.get("opr_man");
		String money = (String) dynaBean.get("money");
		String price = (String) dynaBean.get("price");// 销售价
		String count = (String) dynaBean.get("count");// 销售数量
		String pay_money = (String) dynaBean.get("pay_money");
		String sn = (String) dynaBean.get("sn");
		String type = (String) dynaBean.get("type");

		String customer_name = (String) dynaBean.get("customer_name");// 客户名称
		String customer_idcard = (String) dynaBean.get("customer_idcard");// 身份证号
		String cus_name = (String) dynaBean.get("cus_name");// 机构名称
		String cus_idcard = (String) dynaBean.get("cus_idcard");// 结构代码

		// String customer_link_name = (String)
		// dynaBean.get("customer_link_name");// 客户联系人名称
		String customer_remark = (String) dynaBean.get("customer_remark");// 客户备注
		String customer_tel = (String) dynaBean.get("customer_tel");// 联系人名称
		String customer_addr = (String) dynaBean.get("customer_addr");// 联系人名称
		String p_index = (String) dynaBean.get("p_index");// 客户联系人 区域p_index

		String cus_idcard_type = (String) dynaBean.get("cus_idcard_type");// 购买类型
		String ch_invoice_date = (String) dynaBean.get("ch_invoice_date");// 出货开发票日期
		String invoice_bh = (String) dynaBean.get("invoice_bh");// 增值税（销售）发票号
		String allowance = (String) dynaBean.get("allowance");// 单台补贴金额
		String allowance_money = (String) dynaBean.get("allowance_money");// 总补贴金额
		String pd_unique_code = (String) dynaBean.get("pd_unique_code");// 产品唯一编码
		String favorable = (String) dynaBean.get("favorable");// 优惠

		request.setAttribute("not_validate_record", "true");
		// StdEntpUser user = super.getStdEntpUserFromRequest(request, response,
		// keySeq);
		UserInfo user = super.getUserInfoFromWebService(request, response);
		// logger.info("============keySeq:{}", keySeq);
		if (null == user) {
			return null;
		}
		dynaBean.set("keySeq", keySeq);

		Long shop_id = user.getEntp_shop().getShop_id();
		JxcCustomer customer = new JxcCustomer();
		if (StringUtils.equals("1", cus_idcard_type)) {// 个人
			// customer_name = (String) dynaBean.get("customer_name");// 客户名称
			customer.setName(customer_name);
			customer.setCus_idcard_type(1);
		}
		if (StringUtils.equals("5", cus_idcard_type)) {// 机构
			customer.setName(cus_name);
			customer.setCus_idcard_type(5);
		}
		customer.setShop_id(shop_id);
		customer.setIs_del(0);
		customer = getFacade().getJxcCustomerService().getJxcCustomer(customer);
		JxcCustomer customer_new = new JxcCustomer();

		// Long p_index = user.getP_index().longValue();
		Integer p_index_I = super.getStdEntpMainByShopId(shop_id).getP_index();
		Long p_index_II = new Long(p_index_I);
		String[] pdIdList = request.getParameterValues("pd_id");// 产品型号
		String[] pdNums = request.getParameterValues("count");// 产品数量
		String[] remarkList = request.getParameterValues("remarks");// 备注
		String[] pdPrice = request.getParameterValues("price");// 单价
		// String[] pd_bhs = request.getParameterValues("pd_bh")//产品编号
		// String[] pd_chs = request.getParameterValues("pd_ch");//产品串号

		JxcSellBill sellBill = new JxcSellBill();
		super.copyProperties(sellBill, dynaBean);
		if (null == customer) {// 新客户 添加客户名称、联系人名称
			// customer_new.setName(customer_name);
			if (StringUtils.equals("1", cus_idcard_type)) {// 个人
				// customer_name = (String) dynaBean.get("customer_name");//
				// 客户名称
				customer_new.setName(customer_name);
				customer_new.setCus_idcard_type(1);
				customer_new.setCus_idcard(customer_idcard);
			}
			if (StringUtils.equals("5", cus_idcard_type)) {// 机构
				customer_new.setName(cus_name);
				customer_new.setCus_idcard_type(5);
				customer_new.setCus_idcard(cus_idcard);
			}
			customer_new.setLink_name(customer_name);
			customer_new.setShop_id(shop_id);
			customer_new.setAdd_date(new Date());
			customer_new.setAdd_user_id(user.getId());
			customer_new.setCus_type(0);
			customer_new.setInit_pay(new BigDecimal("0"));
			customer_new.setCur_pay(new BigDecimal("0"));
		} else {// 已有客户取得Id 放到sellbill中
			Long customer_id = customer.getId();
			sellBill.setCustomer_id(customer_id);
			super.copyProperties(customer_new, customer);
		}
		customer_new.setRemarks(customer_remark);
		customer_new.setTel(customer_tel);

		// 地址的前缀，根据p_index取详细地址
		if (GenericValidator.isLong(p_index)) {
			customer_new.setP_index(Long.valueOf(p_index));
			BaseProvinceListFour baseProvinceListFour = super.getBaseProvinceListFourByPindex(Long.valueOf(p_index));
			String pre_customer_addr = "";
			if (null != baseProvinceListFour) {
				pre_customer_addr = baseProvinceListFour.getFull_name();
				customer_addr = pre_customer_addr.concat(customer_addr);
			}
		}
		customer_new.setAddr(customer_addr);

		sellBill.setJxcCustomer(customer_new);
		sellBill.setShop_id(shop_id);
		sellBill.setShop_p_index(p_index_II);
		sellBill.setSn(sn);
		sellBill.setSell_date(DateUtils.parseDate(sell_date, new String[] { "yyyy-MM-dd" }));
		sellBill.setOpr_man(opr_man);
		if (StringUtils.isNotBlank(money)) {
			sellBill.setMoney(new BigDecimal(money));
		} else {// 防止为空
			sellBill.setMoney(new BigDecimal(Double.valueOf(price) * Long.valueOf(count)));
		}
		sellBill.setPay_money(new BigDecimal(pay_money));
		sellBill.setRemarks(remarks);
		sellBill.setAdd_user_id(user.getId());

		if (ArrayUtils.isNotEmpty(pdIdList)) {

			JxcSellBillDetails sellBillDetails = null;
			List<JxcSellBillDetails> sellBillDetailList = new ArrayList<JxcSellBillDetails>();
			for (int i = 0; i < pdIdList.length; i++) {
				sellBillDetails = new JxcSellBillDetails();
				super.copyProperties(sellBillDetails, dynaBean);// 复制表单字段
				// 单个产品时使用
				// 多产品需set相应字段
				JxcPd pd = new JxcPd();
				pd.setIs_del(0);
				if (GenericValidator.isLong(pdIdList[i])) {
					Long pd_id = Long.valueOf(pdIdList[i]);
					pd.setId(pd_id);
					sellBillDetails.setPd_id(pd_id);
				} else {
					String msg = "产品型号为空，不能提交！";
					super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
					return null;
				}
				pd = super.getFacade().getJxcPdService().getJxcPd(pd);
				sellBillDetails.setPd_name(pd.getName());
				sellBillDetails.setPd_type(pd.getPd_type());
				sellBillDetails.setPd_type_name(pd.getPd_type_name());
				sellBillDetails.setBrand_id(pd.getBrand_id());
				sellBillDetails.setBrand_name(pd.getBrand_name());
				sellBillDetails.setShop_id(shop_id);
				sellBillDetails.setCount(Long.valueOf(pdNums[i]));
				sellBillDetails.setPrice(new BigDecimal(pdPrice[i]));
				sellBillDetails.setRemarks(remarkList[i]);
				sellBillDetails.setCost_price(pd.getCur_cost_price());
				sellBillDetails.setAdd_date(new Date());
				sellBillDetails.setSell_src(0);// 销售来源：默认进销存

				if (StringUtils.isNotBlank(allowance)) {
					sellBillDetails.setAllowance(new Long(allowance));
				}
				if (StringUtils.isNotBlank(allowance_money)) {
					sellBillDetails.setAllowance_money(new Long(allowance_money));
					sellBill.setBill_allowance_money(Long.valueOf(allowance_money));
				}
				sellBillDetails.setPd_unique_code(pd_unique_code);
				sellBillDetails.setInvoice_bh(invoice_bh);
				sellBillDetails.setCh_invoice_date(DateUtils.parseDate(ch_invoice_date, new String[] { "yyyy-MM-dd" }));
				sellBillDetails.setFavorable(favorable);

				// 产品唯一编码
				if (StringUtils.isNotBlank(pd_unique_code)) {
					List<JnhmSelledPdCode> pdCodeList = new ArrayList<JnhmSelledPdCode>();
					for (String s : pd_unique_code.split(",")) {
						JnhmSelledPdCode jspc = new JnhmSelledPdCode();
						jspc.setPd_type_id(pd.getPd_type());
						jspc.setPd_type_name(pd.getPd_type_name());
						jspc.setBrand_id(pd.getBrand_id());
						jspc.setBrand_name(pd.getBrand_name());
						jspc.setShop_id(shop_id);
						jspc.setPd_id(pd.getId());
						jspc.setPd_name(pd.getName());
						jspc.setPd_unique_code(s);

						pdCodeList.add(jspc);
					}
					sellBillDetails.setJnhmSelledPdCodeList(pdCodeList);
				}

				// 多个产品时使用
				// sellBillDetails.setPd_bh(pd_bhs[i]);
				// sellBillDetails.setPd_ch(pd_chs[i]);
				sellBillDetailList.add(sellBillDetails);
			}
			sellBill.setSellBillDetailList(sellBillDetailList);// 添加子表
		}

		Long sell_bill_id = super.getFacade().getJxcSellBillService().createJxcSellBill(sellBill);
		saveMessage(request, "entity.inserted");
		if ("close".equals(type)) {
			return new ActionForward("/JxcSellBill.do?method=list&keySeq=" + keySeq, true);
		} else if ("printView".equals(type)) {
			return new ActionForward("/JxcSellBill.do?method=sellShow&status=" + type + "&sell_bill_id=" + sell_bill_id
					+ "&keySeq=" + keySeq, true);
		} else if ("outView".equals(type)) {
			return new ActionForward("/JxcSellBill.do?method=sellShow&status=" + type + "&sell_bill_id=" + sell_bill_id
					+ "&keySeq=" + keySeq, true);
		} else {
			return new ActionForward("/JxcSellBill.do?keySeq=" + keySeq, true);
		}
	}

	public ActionForward debitInput(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String keySeq = (String) dynaBean.get("keySeq");
		String customer_id = (String) dynaBean.get("customer_id");
		request.setAttribute("not_validate_record", "true");
		// StdEntpUser user = super.getStdEntpUserFromRequest(request, response,
		// keySeq);
		UserInfo user = super.getUserInfoFromWebService(request, response);

		if (null == user) {
			return null;
		}
		dynaBean.set("keySeq", keySeq);

		Long shop_id = user.getEntp_shop().getShop_id();

		if (GenericValidator.isLong(customer_id)) {
			JxcCustomer customer = new JxcCustomer();
			customer.setId(Long.valueOf(customer_id));
			customer.setIs_del(0);
			customer = super.getFacade().getJxcCustomerService().getJxcCustomer(customer);
			request.setAttribute("customer", customer);
		}

		// 客户列表
		JxcCustomer cust = new JxcCustomer();
		cust.setShop_id(shop_id);
		cust.setIs_del(0);
		List<JxcCustomer> custList = super.getFacade().getJxcCustomerService().getJxcCustomerList(cust);
		request.setAttribute("custList", custList);

		Date now = new Date();
		request.setAttribute("today", DateFormatUtils.format(now, "yyyy-MM-dd"));
		request.setAttribute("sz_sn", "SK" + DateFormatUtils.format(now, "yyyyMMddHHmmssSSS"));

		return new ActionForward("/../WEB-INF/pages/jsp/client/JxcSellBill/debitform.jsp");
	}

	public ActionForward debitList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String keySeq = (String) dynaBean.get("keySeq");
		request.setAttribute("not_validate_record", "true");
		// StdEntpUser user = super.getStdEntpUserFromRequest(request, response,
		// keySeq);
		UserInfo user = super.getUserInfoFromWebService(request, response);

		if (null == user) {
			return null;
		}
		dynaBean.set("keySeq", keySeq);

		Long shop_id = user.getEntp_shop().getShop_id();

		String customer_id = (String) dynaBean.get("customer_id");
		String sell_date_ge = (String) dynaBean.get("sell_date_ge");
		String sell_date_le = (String) dynaBean.get("sell_date_le");
		Date now = new Date();
		if (StringUtils.isBlank(sell_date_ge)) {
			sell_date_ge = DateFormatUtils.format(now, "yyyy-MM-") + "01";
		}
		if (StringUtils.isBlank(sell_date_le)) {
			sell_date_le = DateFormatUtils.format(now, "yyyy-MM-dd");
		}

		JxcCustomer customer = new JxcCustomer();
		if (GenericValidator.isLong(customer_id)) {
			customer.setId(Long.valueOf(customer_id));
		}
		List<JxcCustomer> customerList = super.getCustomerList(shop_id);
		for (JxcCustomer jxcCustomer : customerList) {
			JxcSellBill sellBill = new JxcSellBill();
			sellBill.setCustomer_id(jxcCustomer.getId());
			sellBill.getMap().put("sell_date_le_0", sell_date_ge);
			sellBill.setShop_id(shop_id);
			JxcSellBill sellBillrec = super.getFacade().getJxcSellBillService().getJxcSellBillForSZ(sellBill);
			if (null != sellBillrec) {
				BigDecimal YS1 = (BigDecimal) sellBillrec.getMap().get("countmoney");
				BigDecimal SS1 = (BigDecimal) sellBillrec.getMap().get("countpay_money");
				BigDecimal SK1 = (BigDecimal) sellBillrec.getMap().get("countsz_money");
				jxcCustomer.setInit_pay(jxcCustomer.getInit_pay().add(YS1).subtract(SS1).subtract(SK1));
			}

			sellBill.getMap().put("sell_date_ge", sell_date_ge);
			sellBill.getMap().put("sell_date_le", sell_date_le);
			sellBill.getMap().put("sell_date_le_0", null);
			sellBillrec = super.getFacade().getJxcSellBillService().getJxcSellBillForSZ(sellBill);
			if (null != sellBillrec) {
				BigDecimal YS2 = (BigDecimal) sellBillrec.getMap().get("countmoney");
				BigDecimal SS2 = (BigDecimal) sellBillrec.getMap().get("countpay_money");
				BigDecimal SK2 = (BigDecimal) sellBillrec.getMap().get("countsz_money");
				jxcCustomer.getMap().put("ysmoney", YS2);
				jxcCustomer.getMap().put("ssmoney", SS2.add(SK2));
				jxcCustomer.getMap().put("qmysmoney", jxcCustomer.getInit_pay().add(YS2).subtract(SS2).subtract(SK2));
			}
		}

		request.setAttribute("customerList", customerList);

		// 客户列表
		JxcCustomer cust = new JxcCustomer();
		cust.setShop_id(shop_id);
		cust.setIs_del(0);
		List<JxcCustomer> custList = super.getFacade().getJxcCustomerService().getJxcCustomerList(cust);
		request.setAttribute("custList", custList);

		dynaBean.set("sell_date_ge", sell_date_ge);
		dynaBean.set("sell_date_le", sell_date_le);

		return new ActionForward("/../WEB-INF/pages/jsp/client/JxcSellBill/debitlist.jsp");
	}

	public ActionForward saveSZ(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String keySeq = (String) dynaBean.get("keySeq");
		String sz_obj_id = (String) dynaBean.get("sz_obj_id");
		String money = (String) dynaBean.get("money");// 本次应付款
		String summary = (String) dynaBean.get("summary");
		String sz_sn = (String) dynaBean.get("sz_sn");
		String busi_date = (String) dynaBean.get("busi_date");

		// test
		// keySeq = "2225126926682095959";
		request.setAttribute("not_validate_record", "true");
		// StdEntpUser user = super.getStdEntpUserFromRequest(request, response,
		// keySeq);
		UserInfo user = super.getUserInfoFromWebService(request, response);

		if (null == user) {
			return null;
		}
		dynaBean.set("keySeq", keySeq);

		Long shop_id = user.getEntp_shop().getShop_id();
		// Long p_index = user.getP_index().longValue();
		Integer p_index_I = super.getStdEntpMainByShopId(shop_id).getP_index();
		Long p_index = new Long(p_index_I);

		JxcSzDetails szDetails = new JxcSzDetails();
		szDetails.setShop_id(shop_id);
		szDetails.setShop_p_index(p_index);
		szDetails.setSz_type(1);
		szDetails.setSz_sn(sz_sn);
		if (GenericValidator.isLong(sz_obj_id)) {
			szDetails.setSz_obj_id(Long.valueOf(sz_obj_id));
		}
		if (StringUtils.isNotBlank(money)) {
			szDetails.setMoney(BigDecimal.valueOf(Double.valueOf(money)));
		}
		szDetails.setSummary(summary);
		szDetails.setBusi_date(DateUtils.parseDate(busi_date, new String[] { "yyyy-MM-dd" }));

		super.getFacade().getJxcSzDetailsService().createJxcSzDetails(szDetails);

		return new ActionForward("/JxcSellBill.do?method=debitList&keySeq=" + keySeq, true);
	}

	public ActionForward szShow(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String keySeq = (String) dynaBean.get("keySeq");
		String customer_id = (String) dynaBean.get("customer_id");
		String sell_date_ge = (String) dynaBean.get("sell_date_ge");
		String sell_date_le = (String) dynaBean.get("sell_date_le");
		Date now = new Date();
		if (StringUtils.isBlank(sell_date_ge)) {
			sell_date_ge = DateFormatUtils.format(now, "yyyy-MM-") + "01";
		}
		if (StringUtils.isBlank(sell_date_le)) {
			sell_date_le = DateFormatUtils.format(now, "yyyy-MM-dd");
		}
		// test
		// keySeq = "2225126926682095959";
		request.setAttribute("not_validate_record", "true");
		// StdEntpUser user = super.getStdEntpUserFromRequest(request, response,
		// keySeq);
		UserInfo user = super.getUserInfoFromWebService(request, response);

		if (null == user) {
			return null;
		}
		dynaBean.set("keySeq", keySeq);

		Long shop_id = user.getEntp_shop().getShop_id();

		if (!GenericValidator.isLong(customer_id)) {
			return null;
		}

		// 客户应收款明细
		JxcSellBill sellBill = new JxcSellBill();
		sellBill.setShop_id(shop_id);
		sellBill.setCustomer_id(Long.valueOf(customer_id));

		// 客户信息
		JxcCustomer cust = new JxcCustomer();
		cust.setId(Long.valueOf(customer_id));
		cust.setShop_id(shop_id);
		cust.setIs_del(0);
		cust = super.getFacade().getJxcCustomerService().getJxcCustomer(cust);

		// 客户期初结存
		sellBill.getMap().put("sell_date_le_0", sell_date_ge);
		JxcSellBill sellBillrec = super.getFacade().getJxcSellBillService().getJxcSellBillForSZ(sellBill);
		if (null != sellBillrec) {
			BigDecimal YS1 = (BigDecimal) sellBillrec.getMap().get("countmoney");
			BigDecimal SS1 = (BigDecimal) sellBillrec.getMap().get("countpay_money");
			BigDecimal SK1 = (BigDecimal) sellBillrec.getMap().get("countsz_money");
			cust.setInit_pay(cust.getInit_pay().add(YS1).subtract(SS1).subtract(SK1));
		}
		request.setAttribute("cust", cust);

		sellBill.getMap().put("sell_date_ge", sell_date_ge);
		sellBill.getMap().put("sell_date_le", sell_date_le);
		sellBill.getMap().put("sell_date_le_0", null);

		List<JxcSellBill> sellBillList = super.getFacade().getJxcSellBillService().getJxcSellBillForSZMX(sellBill);

		request.setAttribute("sellBillList", sellBillList);

		dynaBean.set("sell_date_ge", sell_date_ge);
		dynaBean.set("sell_date_le", sell_date_le);

		BigDecimal allAmount = cust.getInit_pay();
		BigDecimal ysAmount = new BigDecimal(0);
		BigDecimal shAmount = new BigDecimal(0);

		for (JxcSellBill jxcSellBill : sellBillList) {
			ysAmount = ysAmount.add(jxcSellBill.getMoney());
			shAmount = shAmount.add(jxcSellBill.getPay_money());
			jxcSellBill.getMap().put("qmAmount", allAmount.add(ysAmount).subtract(shAmount));
		}
		allAmount = allAmount.add(ysAmount).subtract(shAmount);
		request.setAttribute("allAmount", allAmount);
		request.setAttribute("ysAmount", ysAmount);
		request.setAttribute("shAmount", shAmount);

		return new ActionForward("/../WEB-INF/pages/jsp/client/JxcSellBill/szshow.jsp");
	}

	public ActionForward sellShow(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String keySeq = (String) dynaBean.get("keySeq");
		String sell_bill_id = (String) dynaBean.get("sell_bill_id");
		request.setAttribute("not_validate_record", "true");
		// StdEntpUser user = super.getStdEntpUserFromRequest(request, response,
		// keySeq);
		UserInfo user = super.getUserInfoFromWebService(request, response);
		if (null == user) {
			return null;
		}
		dynaBean.set("keySeq", keySeq);
		Long shop_id = user.getEntp_shop().getShop_id();
		if (!GenericValidator.isLong(sell_bill_id)) {
			return null;
		}

		// 销售单明细
		JxcSellBillDetails details = new JxcSellBillDetails();
		details.setSell_bill_id(Long.valueOf(sell_bill_id));

		List<JxcSellBillDetails> detailList = super.getFacade().getJxcSellBillDetailsService()
				.getJxcSellBillDetailsList(details);

		request.setAttribute("detailList", detailList);

		// 销售单
		JxcSellBill sellBill = new JxcSellBill();
		sellBill.setId(Long.valueOf(sell_bill_id));
		sellBill.setIs_del(0);
		sellBill = super.getFacade().getJxcSellBillService().getJxcSellBill(sellBill);
		request.setAttribute("sellBill", sellBill);

		// 客户
		JxcCustomer cust = new JxcCustomer();
		cust.setShop_id(shop_id);
		cust.setIs_del(0);
		cust.setId(sellBill.getCustomer_id());
		cust = super.getFacade().getJxcCustomerService().getJxcCustomer(cust);
		request.setAttribute("cust", cust);

		return new ActionForward("/JxcSellBill/sellshow.jsp");
	}

	@Override
	public ActionForward toExcel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws RowsExceededException, WriteException, IOException {
		DynaBean dynaBean = (DynaBean) form;
		String type = (String) dynaBean.get("type");
		// String keySeq = (String) dynaBean.get("keySeq");
		String sell_bill_id = (String) dynaBean.get("sell_bill_id");
		// String customer_id = (String) dynaBean.get("customer_id");
		// String sell_date_ge = (String) dynaBean.get("sell_date_ge");
		// String sell_date_le = (String) dynaBean.get("sell_date_le");

		request.setAttribute("not_validate_record", "true");
		// StdEntpUser user = this.getStdEntpUserFromRequest(request, response,
		// keySeq);
		UserInfo user = super.getUserInfoFromWebService(request, response);
		if (null == user) {
			return null;
		}

		Long shop_id = user.getEntp_shop().getShop_id();
		//
		// if ("xsdlist".equals(type)) {
		// JxcSellBill sellBill = new JxcSellBill();
		//
		// if (GenericValidator.isLong(customer_id)) {
		// sellBill.setCustomer_id(Long.valueOf(customer_id));
		// }
		// sellBill.getMap().put("sell_date_ge", sell_date_ge);
		// sellBill.getMap().put("sell_date_le", sell_date_le);
		// sellBill.getMap().put("is_del_j", 0);
		// sellBill.getMap().put("is_del_c", 0);
		// sellBill.getMap().put("shop_id_j", shop_id);
		//
		// List<JxcSellBill> sellBillList =
		// super.getFacade().getJxcSellBillService()
		// .getJxcSellBillListForXS(sellBill);
		// createExcelforList(request, response, sellBillList,
		// user.getStdEntpMain());
		// }

		if ("xsddetail".equals(type)) {
			JxcSellBillDetails details = new JxcSellBillDetails();
			details.setSell_bill_id(Long.valueOf(sell_bill_id));

			List<JxcSellBillDetails> detailList = super.getFacade().getJxcSellBillDetailsService()
					.getJxcSellBillDetailsList(details);

			// 销售单
			JxcSellBill sellBill = new JxcSellBill();
			sellBill.setId(Long.valueOf(sell_bill_id));
			sellBill.setIs_del(0);
			sellBill = super.getFacade().getJxcSellBillService().getJxcSellBill(sellBill);

			// 客户
			JxcCustomer cust = new JxcCustomer();
			cust.setShop_id(shop_id);
			cust.setIs_del(0);
			cust.setId(sellBill.getCustomer_id());
			cust = super.getFacade().getJxcCustomerService().getJxcCustomer(cust);
			createExcelforDetail(request, response, sellBill, cust, detailList, super.getStdEntpMainByShopId(shop_id));
		}

		// if ("ysklist".equals(type)) {
		// JxcCustomer customer = new JxcCustomer();
		// if (GenericValidator.isLong(customer_id)) {
		// customer.setId(Long.valueOf(customer_id));
		// }
		// customer.setIs_del(0);
		// customer.setShop_id(shop_id);
		// List<JxcCustomer> customerList =
		// super.getFacade().getJxcCustomerService().getJxcCustomerList(customer);
		// for (JxcCustomer jxcCustomer : customerList) {
		// JxcSellBill sellBill = new JxcSellBill();
		// sellBill.setCustomer_id(jxcCustomer.getId());
		// sellBill.getMap().put("sell_date_le_0", sell_date_ge);
		// sellBill.setShop_id(shop_id);
		// JxcSellBill sellBillrec =
		// super.getFacade().getJxcSellBillService().getJxcSellBillForSZ(sellBill);
		// if (null != sellBillrec) {
		// BigDecimal YS1 = (BigDecimal) sellBillrec.getMap().get("countmoney");
		// BigDecimal SS1 = (BigDecimal)
		// sellBillrec.getMap().get("countpay_money");
		// BigDecimal SK1 = (BigDecimal)
		// sellBillrec.getMap().get("countsz_money");
		// jxcCustomer.setInit_pay(jxcCustomer.getInit_pay().add(YS1).subtract(SS1).subtract(SK1));
		// }
		//
		// sellBill.getMap().put("sell_date_ge", sell_date_ge);
		// sellBill.getMap().put("sell_date_le", sell_date_le);
		// sellBill.getMap().put("sell_date_le_0", null);
		// sellBillrec =
		// super.getFacade().getJxcSellBillService().getJxcSellBillForSZ(sellBill);
		// if (null != sellBillrec) {
		// BigDecimal YS2 = (BigDecimal) sellBillrec.getMap().get("countmoney");
		// BigDecimal SS2 = (BigDecimal)
		// sellBillrec.getMap().get("countpay_money");
		// BigDecimal SK2 = (BigDecimal)
		// sellBillrec.getMap().get("countsz_money");
		// jxcCustomer.getMap().put("ysmoney", YS2);
		// jxcCustomer.getMap().put("ssmoney", SS2.add(SK2));
		// jxcCustomer.getMap().put("qmysmoney",
		// jxcCustomer.getInit_pay().add(YS2).subtract(SS2).subtract(SK2));
		// }
		// }
		// createExcelforYskList(request, response, customerList,
		// user.getStdEntpMain());
		// }
		//
		// if ("yskdetail".equals(type)) {
		// // 客户应收款明细
		// JxcSellBill sellBill = new JxcSellBill();
		// sellBill.setShop_id(shop_id);
		// sellBill.setCustomer_id(Long.valueOf(customer_id));
		//
		// // 客户信息
		// JxcCustomer cust = new JxcCustomer();
		// cust.setId(Long.valueOf(customer_id));
		// cust.setShop_id(shop_id);
		// cust.setIs_del(0);
		// cust =
		// super.getFacade().getJxcCustomerService().getJxcCustomer(cust);
		//
		// // 客户期初结存
		// sellBill.getMap().put("sell_date_le_0", sell_date_ge);
		// JxcSellBill sellBillrec =
		// super.getFacade().getJxcSellBillService().getJxcSellBillForSZ(sellBill);
		// if (null != sellBillrec) {
		// BigDecimal YS1 = (BigDecimal) sellBillrec.getMap().get("countmoney");
		// BigDecimal SS1 = (BigDecimal)
		// sellBillrec.getMap().get("countpay_money");
		// BigDecimal SK1 = (BigDecimal)
		// sellBillrec.getMap().get("countsz_money");
		// cust.setInit_pay(cust.getInit_pay().add(YS1).subtract(SS1).subtract(SK1));
		// }
		//
		// sellBill.getMap().put("sell_date_ge", sell_date_ge);
		// sellBill.getMap().put("sell_date_le", sell_date_le);
		// sellBill.getMap().put("sell_date_le_0", null);
		//
		// List<JxcSellBill> sellBillList =
		// super.getFacade().getJxcSellBillService().getJxcSellBillForSZMX(sellBill);
		//
		// BigDecimal allAmount = cust.getInit_pay();
		// BigDecimal ysAmount = new BigDecimal(0);
		// BigDecimal shAmount = new BigDecimal(0);
		//
		// for (JxcSellBill jxcSellBill : sellBillList) {
		// ysAmount = ysAmount.add(jxcSellBill.getMoney());
		// shAmount = shAmount.add(jxcSellBill.getPay_money());
		// jxcSellBill.getMap().put("qmAmount",
		// allAmount.add(ysAmount).subtract(shAmount));
		// }
		// allAmount = allAmount.add(ysAmount).subtract(shAmount);
		// cust.getMap().put("allAmount", allAmount);
		// cust.getMap().put("ysAmount", ysAmount);
		// cust.getMap().put("shAmount", shAmount);
		// createExcelforYskDetail(request, response, sellBillList, cust,
		// user.getStdEntpMain());
		// }

		return null;
	}

	@SuppressWarnings("unused")
	private void createExcelforList(HttpServletRequest request, HttpServletResponse response,
			List<JxcSellBill> jxcSellBillList, StdEntpMain entpMain) throws IOException, RowsExceededException,
			WriteException {
		if (jxcSellBillList == null || jxcSellBillList.size() < 0) {
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
			// Label(column,row,content)其中column代表单元格的第column+1列，第row+1行,
			// 单元格的内容是content
			WritableSheet sheet = wwb.createSheet(entpMain.getEntp_name().concat("销售单列表"), 0);
			// String[] title0 = { "", "", "", "品牌", "数量", "单价", "金额", "备注" };
			int row = 0;// 行
			int column = 0;// 列

			sheet.mergeCells(0, row, 6, row);// 合并单元格(左列，左行，右列，右行)
			// 合并第row行，从第0列到第7列
			String info0 = "联系人：".concat(entpMain.getLinkman()).concat("  联系电话：").concat(entpMain.getTel());
			Label label0 = new Label(0, row, info0);
			label0.setCellFormat(cellFormatRight);
			sheet.addCell(label0);

			row++;
			sheet.mergeCells(0, row, 6, row);
			String info1 = entpMain.getEntp_name();
			Label label1 = new Label(0, row, info1);
			label1.setCellFormat(cellFormatCenter);
			sheet.addCell(label1);

			row++;
			sheet.mergeCells(0, row, 6, row);
			label1 = new Label(0, row, "销售单列表");
			label1.setCellFormat(cellFormatCenter);
			sheet.addCell(label1);

			row++;
			sheet.mergeCells(0, row, 6, row);
			String dateFromTo = super.queryDate(request.getParameter("sell_date_ge"), request
					.getParameter("sell_date_le"));
			label1 = new Label(0, row, dateFromTo);
			label1.setCellFormat(cellFormatLeft);
			sheet.addCell(label1);

			row++;
			String[] title = { "行号", "销售日期", "销售单号", "客户名称", "应收金额", "实收金额", "说明" };
			for (int i = 0; i < title.length; i++) {
				sheet.addCell(new Label(i, row, title[i]));
			}

			row++;
			// 填充产品价格

			double allMoney = 0;
			double allPayMoney = 0;
			for (int i = 0; i < jxcSellBillList.size(); i++) {
				column = 0;
				JxcSellBill sellBill = jxcSellBillList.get(i);
				sheet.addCell(new jxl.write.Number(column++, row, i + 1));// 行号
				sheet.addCell(new Label(column++, row, DateFormatUtils.format(sellBill.getSell_date(), "yyyy-MM-dd")));// 销售日期
				sheet.addCell(new Label(column++, row, sellBill.getSn()));// 销售单号
				sheet.addCell(new Label(column++, row, (String) sellBill.getMap().get("customer_name")));// 客户名称
				sheet.addCell(new jxl.write.Number(column++, row, sellBill.getMoney().doubleValue(), wcf));// 应收金额
				allMoney += sellBill.getMoney().doubleValue();
				sheet.addCell(new jxl.write.Number(column++, row, sellBill.getPay_money().doubleValue(), wcf));// 实收金额
				allPayMoney += sellBill.getPay_money().doubleValue();
				sheet.addCell(new Label(column++, row, sellBill.getRemarks()));// 说明
				row++;
			}

			sheet.mergeCells(0, row, 3, row);
			sheet.addCell(new Label(0, row, "合计："));

			sheet.addCell(new jxl.write.Number(4, row, allMoney, wcf));
			sheet.addCell(new jxl.write.Number(5, row, allPayMoney, wcf));

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

	private void createExcelforDetail(HttpServletRequest request, HttpServletResponse response,
			JxcSellBill jxcSellBill, JxcCustomer cust, List<JxcSellBillDetails> detailList, StdEntpMain entpMain)
			throws IOException, RowsExceededException, WriteException {
		if (detailList == null || detailList.size() < 0) {
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
			WritableCellFormat wcf = new jxl.write.WritableCellFormat(new jxl.write.NumberFormat("￥0.00"));
			// 创建一个可写入的工作表
			// Label(column,row,content)其中column代表单元格的第column+1列，第row+1行,
			// 单元格的内容是content
			WritableSheet sheet = wwb.createSheet(cust.getName().concat("销售单明细"), 0);
			// String[] title0 = { "", "", "", "品牌", "数量", "单价", "金额", "备注" };
			int row = 0;// 行
			int column = 0;// 列

			sheet.mergeCells(0, row, 7, row);// 合并单元格(左列，左行，右列，右行)
			// 合并第row行，从第0列到第7列
			String info0 = "联系人：".concat(entpMain.getLinkman() == null ? "" : entpMain.getLinkman()).concat("  联系电话：")
					.concat(entpMain.getTel() == null ? "" : entpMain.getTel());
			Label label0 = new Label(0, row, info0);
			label0.setCellFormat(cellFormatRight);
			sheet.addCell(label0);

			row++;
			sheet.mergeCells(0, row, 7, row);
			String info1 = entpMain.getEntp_name();
			Label label1 = new Label(0, row, info1);
			label1.setCellFormat(cellFormatCenter);
			sheet.addCell(label1);

			row++;
			sheet.mergeCells(0, row, 7, row);
			label1 = new Label(0, row, "销售单");
			label1.setCellFormat(cellFormatCenter);
			sheet.addCell(label1);

			row++;
			sheet.mergeCells(0, row, 2, row);
			sheet.mergeCells(3, row, 4, row);
			sheet.mergeCells(5, row, 7, row);
			Label label3 = new Label(0, row, "客户：".concat(cust.getName()));
			sheet.addCell(label3);
			label3 = new Label(3, row, "日期：".concat(DateFormatUtils.format(jxcSellBill.getAdd_date(), "yyyy-MM-dd")));
			sheet.addCell(label3);
			label3 = new Label(5, row, "No.".concat(jxcSellBill.getSn()));
			label3.setCellFormat(cellFormatRight);
			sheet.addCell(label3);

			row++;
			String[] title = { "行号", "货品代码", "品名规格", "品牌", "数量", "单价", "金额", "备注" };
			for (int i = 0; i < title.length; i++) {
				sheet.addCell(new Label(i, row, title[i]));
			}

			row++;
			// 填充产品价格
			for (int i = 0; i < detailList.size(); i++) {
				column = 0;
				JxcSellBillDetails details = detailList.get(i);
				sheet.addCell(new jxl.write.Number(column++, row, i + 1));// 行号
				sheet.addCell(new Label(column++, row, details.getPd_name()));// 货品代码
				sheet.addCell(new Label(column++, row, details.getPd_type_name()));// 品名规格
				sheet.addCell(new Label(column++, row, details.getBrand_name()));// 品牌
				sheet.addCell(new jxl.write.Number(column++, row, details.getCount()));// 数量
				sheet.addCell(new jxl.write.Number(column++, row, details.getPrice().doubleValue(), wcf));// 单价
				sheet.addCell(new jxl.write.Number(column++, row,
						details.getCount() * details.getPrice().doubleValue(), wcf));// 金额
				sheet.addCell(new Label(column++, row, details.getRemarks()));// 备注
				row++;
			}

			sheet.mergeCells(0, row, 2, row);
			if (null != jxcSellBill.getRemarks() && StringUtils.isNotBlank(jxcSellBill.getRemarks())) {
				sheet.addCell(new Label(0, row, "说明：".concat(jxcSellBill.getRemarks() + " ")));
			} else {
				sheet.addCell(new Label(0, row, "说明："));
			}
			sheet.addCell(new Label(3, row, "应付金额："));
			sheet.addCell(new jxl.write.Number(4, row, jxcSellBill.getMoney().doubleValue(), wcf));
			sheet.addCell(new Label(6, row, "本次付款："));
			sheet.addCell(new jxl.write.Number(7, row, jxcSellBill.getPay_money().doubleValue(), wcf));

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

	@SuppressWarnings("unused")
	private void createExcelforYskList(HttpServletRequest request, HttpServletResponse response,
			List<JxcCustomer> customerList, StdEntpMain entpMain) throws IOException, RowsExceededException,
			WriteException {
		if (customerList == null || customerList.size() < 0) {
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
			// Label(column,row,content)其中column代表单元格的第column+1列，第row+1行,
			// 单元格的内容是content
			WritableSheet sheet = wwb.createSheet(entpMain.getEntp_name().concat("应收款账"), 0);
			// String[] title0 = { "", "", "", "品牌", "数量", "单价", "金额", "备注" };
			int row = 0;// 行
			int column = 0;// 列

			sheet.mergeCells(0, row, 5, row);// 合并单元格(左列，左行，右列，右行)
			// 合并第row行，从第0列到第5列
			String info0 = "联系人：".concat(entpMain.getLinkman()).concat("  联系电话：").concat(entpMain.getTel());
			Label label0 = new Label(0, row, info0);
			label0.setCellFormat(cellFormatRight);
			sheet.addCell(label0);

			row++;
			sheet.mergeCells(0, row, 5, row);
			String info1 = entpMain.getEntp_name();
			Label label1 = new Label(0, row, info1);
			label1.setCellFormat(cellFormatCenter);
			sheet.addCell(label1);

			row++;
			sheet.mergeCells(0, row, 5, row);
			label1 = new Label(0, row, "应收款账");
			label1.setCellFormat(cellFormatCenter);
			sheet.addCell(label1);

			row++;
			sheet.mergeCells(0, row, 5, row);
			String dateFromTo = super.queryDate(request.getParameter("sell_date_ge"), request
					.getParameter("sell_date_le"));
			label1 = new Label(0, row, dateFromTo);
			label1.setCellFormat(cellFormatLeft);
			sheet.addCell(label1);

			row++;
			String[] title = { "行号", "客户名称", "期初应收款", "增加应收款", "收取应收款", "期末应收款" };
			for (int i = 0; i < title.length; i++) {
				sheet.addCell(new Label(i, row, title[i]));
			}

			row++;
			for (int i = 0; i < customerList.size(); i++) {
				column = 0;
				JxcCustomer customer = customerList.get(i);
				sheet.addCell(new jxl.write.Number(column++, row, i + 1));// 行号
				sheet.addCell(new Label(column++, row, customer.getName()));// 客户名称
				sheet.addCell(new jxl.write.Number(column++, row, customer.getInit_pay().doubleValue(), wcf));// 期初应收款
				sheet.addCell(new jxl.write.Number(column++, row, ((BigDecimal) customer.getMap().get("ysmoney"))
						.doubleValue(), wcf));// 增加应收款
				sheet.addCell(new jxl.write.Number(column++, row, ((BigDecimal) customer.getMap().get("ssmoney"))
						.doubleValue(), wcf));// 收取应收款
				sheet.addCell(new jxl.write.Number(column++, row, ((BigDecimal) customer.getMap().get("qmysmoney"))
						.doubleValue(), wcf));// 期末应收款
				row++;
			}

			sheet.mergeCells(0, row, 5, row);
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

	@SuppressWarnings("unused")
	private void createExcelforYskDetail(HttpServletRequest request, HttpServletResponse response,
			List<JxcSellBill> sellBillList, JxcCustomer cust, StdEntpMain entpMain) throws IOException,
			RowsExceededException, WriteException {
		if (sellBillList == null || sellBillList.size() < 0) {
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
			// Label(column,row,content)其中column代表单元格的第column+1列，第row+1行,
			// 单元格的内容是content
			WritableSheet sheet = wwb.createSheet(cust.getName().concat("应收明细账"), 0);
			// String[] title0 = { "", "", "", "品牌", "数量", "单价", "金额", "备注" };
			int row = 0;// 行
			int column = 0;// 列

			sheet.mergeCells(0, row, 6, row);// 合并单元格(左列，左行，右列，右行)
			// 合并第row行，从第0列到第6列
			String info0 = "联系人：".concat(entpMain.getLinkman()).concat("  联系电话：").concat(entpMain.getTel());
			Label label0 = new Label(0, row, info0);
			label0.setCellFormat(cellFormatRight);
			sheet.addCell(label0);

			row++;
			sheet.mergeCells(0, row, 6, row);
			String info1 = entpMain.getEntp_name();
			Label label1 = new Label(0, row, info1);
			label1.setCellFormat(cellFormatCenter);
			sheet.addCell(label1);

			row++;
			sheet.mergeCells(0, row, 6, row);
			label1 = new Label(0, row, "应收明细账");
			label1.setCellFormat(cellFormatCenter);
			sheet.addCell(label1);

			row++;
			sheet.mergeCells(0, row, 6, row);
			String dateFromTo = super.queryDate(request.getParameter("sell_date_ge"), request
					.getParameter("sell_date_le"));
			label1 = new Label(0, row, "客户：".concat(cust.getName()).concat("  " + dateFromTo));
			label1.setCellFormat(cellFormatLeft);
			sheet.addCell(label1);

			row++;
			String[] title = { "行号", "业务日期", "业务单号", "摘要", "增加应收款", "收回应收款", "期末应收款" };
			for (int i = 0; i < title.length; i++) {
				sheet.addCell(new Label(i, row, title[i]));
			}

			row++;
			sheet.mergeCells(1, row, 3, row);
			sheet.addCell(new Label(0, row, "合计"));
			sheet
					.addCell(new jxl.write.Number(4, row, ((BigDecimal) cust.getMap().get("ysAmount")).doubleValue(),
							wcf));
			sheet
					.addCell(new jxl.write.Number(5, row, ((BigDecimal) cust.getMap().get("shAmount")).doubleValue(),
							wcf));
			sheet
					.addCell(new jxl.write.Number(6, row, ((BigDecimal) cust.getMap().get("allAmount")).doubleValue(),
							wcf));

			// 期初结存
			row++;
			sheet.addCell(new jxl.write.Number(0, row, 1));
			sheet.addCell(new Label(1, row, request.getParameter("sell_date_ge")));
			sheet.addCell(new Label(2, row, ""));
			sheet.addCell(new Label(3, row, "期初结存"));
			sheet.addCell(new Label(4, row, ""));
			sheet.addCell(new Label(5, row, ""));
			sheet.addCell(new jxl.write.Number(6, row, cust.getInit_pay().doubleValue(), wcf));

			row++;
			for (int i = 0; i < sellBillList.size(); i++) {
				column = 0;
				JxcSellBill sellBill = sellBillList.get(i);
				sheet.addCell(new jxl.write.Number(column++, row, i + 2));// 行号
				sheet.addCell(new Label(column++, row, DateFormatUtils.format(sellBill.getSell_date(), "yyyy-MM-dd")));// 业务日期
				sheet.addCell(new Label(column++, row, sellBill.getSn()));// 业务单号
				// 摘要
				String remarks = "";
				if ("1".equals(sellBill.getMap().get("sm").toString())) {
					remarks = "销售货品";
				} else {
					remarks = "收欠款/预收款";
				}
				sheet.addCell(new Label(column++, row, remarks));
				// 增加应收款
				if (sellBill.getMoney().doubleValue() == 0) {
					sheet.addCell(new Label(column++, row, ""));
				} else {
					sheet.addCell(new jxl.write.Number(column++, row, sellBill.getMoney().doubleValue(), wcf));
				}

				sheet.addCell(new jxl.write.Number(column++, row, sellBill.getPay_money().doubleValue(), wcf));// 收回应收款
				sheet.addCell(new jxl.write.Number(column++, row, ((BigDecimal) sellBill.getMap().get("qmAmount"))
						.doubleValue(), wcf));// 期末应收款
				row++;
			}

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

	// 选择品牌
	@Override
	public ActionForward chooseBrand(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String brand_name_like = (String) dynaBean.get("brand_name_like");
		String pd_type = (String) dynaBean.get("pd_type");

		if ("0".equals(pd_type)) {
			BaseBrandInfo entity = new BaseBrandInfo();
			super.copyProperties(entity, dynaBean);
			entity.setIs_del(0);
			entity.getMap().put("brand_name_like", brand_name_like);

			Long recordCount = getFacade().getBaseBrandInfoService().getBaseBrandInfoCount(entity);
			pager.init(recordCount, 45, pager.getRequestPage());
			entity.getRow().setFirst(pager.getFirstRow());
			entity.getRow().setCount(45);
			List<BaseBrandInfo> list = getFacade().getBaseBrandInfoService().getBaseBrandInfoPaginatedList(entity);
			request.setAttribute("entityList", list);
		} else {
			MvPdTypeJoinBrand entity = new MvPdTypeJoinBrand();
			super.copyProperties(entity, form);
			if (StringUtils.isNotBlank(pd_type)) {
				entity.setPd_type(Long.valueOf(pd_type));
			}
			entity.getMap().put("brand_name_like", brand_name_like);

			Long recordCount = getFacade().getMvPdTypeJoinBrandService().getMvPdTypeJoinBrandCount(entity);
			pager.init(recordCount, 45, pager.getRequestPage());
			entity.getRow().setFirst(pager.getFirstRow());
			entity.getRow().setCount(45);
			List<MvPdTypeJoinBrand> list = getFacade().getMvPdTypeJoinBrandService().getMvPdTypeJoinBrandPaginatedList(
					entity);
			request.setAttribute("entityList", list);
		}

		dynaBean.set("pd_type", pd_type);
		return new ActionForward("/JxcSellBill/chooseBrand.jsp");
	}

	public ActionForward getPdModel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {// 根据大类、品牌获取产品类型
		DynaBean dynaBean = (DynaBean) form;
		String pd_type = (String) dynaBean.get("pd_type");
		String jxc_pd_type_id = (String) dynaBean.get("jxc_pd_type_id");
		String brand_id = (String) dynaBean.get("brand_id");
		// String keySeq = (String) dynaBean.get("keySeq");
		logger.info("==pd_type:{}", pd_type);
		logger.info("==jxc_pd_type_id:{}", jxc_pd_type_id);
		request.setAttribute("not_validate_record", "true");
		// StdEntpUser user = super.getStdEntpUserFromRequest(request, response,
		// keySeq);
		UserInfo user = super.getUserInfoFromWebService(request, response);

		if (null == user) {
			return null;
		}
		Long shop_id = user.getEntp_shop().getShop_id();
		if (StringUtils.isNotBlank(brand_id) && StringUtils.isNotBlank(pd_type)) {
			JxcPd jxcPd = new JxcPd();
			if ("0".equals(pd_type) && StringUtils.isNotBlank(jxc_pd_type_id)) {
				jxcPd.setJxc_pd_type_id(Long.valueOf(jxc_pd_type_id));
			} else {
				jxcPd.setPd_type(new Long(pd_type));
			}
			jxcPd.setBrand_id(new Long(brand_id));
			jxcPd.setShop_id(shop_id);
			jxcPd.setIs_del(0);
			// jxcPd.getMap().put("count_gt", true);
			jxcPd.setOwn_sys(0);// 默认非家电下乡产品
			List<JxcPd> list = getFacade().getJxcPdService().getJxcPdList(jxcPd);

			StringBuffer sb = new StringBuffer("[");
			for (JxcPd pm : list) {
				JnhmPdContents entity = new JnhmPdContents();
				// entity.setBrand_name(pm.getBrand_name());
				entity.setPd_name(pm.getName());
				List<JnhmPdContents> jnhmList = getFacade().getJnhmPdContentsService().getJnhmPdContentsList(entity);

				sb.append("{\"pd_id\":\"" + pm.getId() + "\",");

				if (jnhmList.size() > 0) {
					List<String> l = new ArrayList<String>();
					for (JnhmPdContents o : jnhmList) {
						l.add(o.getAllowance().toString());
					}
					sb.append("\"values\":\"" + pm.getId() + "#$" + pm.getPrice() + "#$" + pm.getCount() + "#$"
							+ StringUtils.join(l, ",") + "\",");
					// sb.append("\"allowance\":\"" + entity.getAllowance() +
					// "\"},");
				} else {
					sb
							.append("\"values\":\"" + pm.getId() + "#$" + pm.getPrice() + "#$" + pm.getCount() + "#$"
									+ "\",");
				}

				sb.append("\"md_name\":\"" + pm.getName() + "\"},");
			}
			sb.append("{\"end\":\"rz\"}]");
			// log.info("sb------"+sb.toString());
			super.renderJson(response, sb.toString());
		}

		return null;
	}

	public ActionForward saveJxcCustomer(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String name = (String) dynaBean.get("name");
		// String keySeq = (String) dynaBean.get("keySeq");

		// StdEntpUser user = super.getStdEntpUserFromRequest(request, response,
		// keySeq);
		UserInfo user = super.getUserInfoFromWebService(request, response);
		if (null == user) {
			return null;
		}
		JxcCustomer customer = new JxcCustomer();
		super.copyProperties(customer, dynaBean);

		String isExist = "-1";
		Long shop_id = user.getEntp_shop().getShop_id();
		Long user_id = user.getId();
		if (StringUtils.isNotBlank(name)) {
			JxcCustomer customerIsReapt = new JxcCustomer();
			customerIsReapt.setName(name);
			customerIsReapt.setIs_del(0);
			customerIsReapt.setShop_id(shop_id);
			Long count = getFacade().getJxcCustomerService().getJxcCustomerCount(customerIsReapt);
			if (count <= 0) {
				// 设置属性
				customer.setAdd_user_id(user_id);
				customer.setShop_id(shop_id);
				Long id = getFacade().getJxcCustomerService().createJxcCustomer(customer);
				isExist = id.toString();
			}

		}
		super.render(response, isExist, "text/x-json;charset=UTF-8");
		return null;
	}

	// public ActionForward goToJdxxSell(ActionMapping mapping, ActionForm form,
	// HttpServletRequest request,
	// HttpServletResponse response) {
	// DynaBean dynaBean = (DynaBean) form;
	// String keySeq = (String) dynaBean.get("keySeq");
	// // StdEntpUser user = super.getStdEntpUserFromRequest(request, response,
	// keySeq);
	// UserInfo user = super.getUserInfoFromWebService(request, response);
	// if (null == user) {
	// return null;
	// }
	// return new ActionForward("/JxcSellBill/goToJdxxSell.jsp");
	// }
	//
	// public ActionForward goToYjhxSell(ActionMapping mapping, ActionForm form,
	// HttpServletRequest request,
	// HttpServletResponse response) {
	// DynaBean dynaBean = (DynaBean) form;
	// String keySeq = (String) dynaBean.get("keySeq");
	// StdEntpUser user = super.getStdEntpUserFromRequest(request, response,
	// keySeq);
	// if (null == user) {
	// return null;
	// }
	// return new ActionForward("/JxcSellBill/goToYjhxSell.jsp");
	// }

	public ActionForward addForFx(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		DynaBean dynaBean = (DynaBean) form;
		String keySeq = (String) dynaBean.get("keySeq");
		// StdEntpUser user = super.getStdEntpUserFromRequest(request, response,
		// keySeq);
		UserInfo user = super.getUserInfoFromWebService(request, response);
		if (null == user) {
			return null;
		}
		dynaBean.set("keySeq", keySeq);

		Long shop_id = user.getEntp_shop().getShop_id();

		// 下级经销商
		HashMap<String, String> result = super.getKonkaDeptIdAndType(shop_id);
		if (!"10".endsWith(result.get("result_code"))) {
			String msg = "对不起，本商铺不是代理商，不能进行分销!";
			renderJavaScript(response, "alert('" + msg + "');history.back();");
			return null;
		}
		List<KonkaBranchCategory> konkaBranchCategoryList = super
				.gettSuperiorAgentKonkaBranchCategoryListByMmtShopId(shop_id);

		for (KonkaBranchCategory kbc : konkaBranchCategoryList) {
			MmtEntpShop konkaEntpShop = new MmtEntpShop();
			konkaEntpShop.setShop_id(kbc.getMmt_shop_id() == null ? -1L : kbc.getMmt_shop_id());
			konkaEntpShop = getFacade().getMmtEntpShopService().getMmtEntpShop(konkaEntpShop);
			// KonkaR3Shop konkaR3Shop = new KonkaR3Shop();
			// konkaR3Shop.setId(kbc.getMmt_shop_id());//
			// 根据mmt_shop_id在表中取对应的经销商名称
			// konkaR3Shop =
			// getFacade().getKonkaR3ShopService().getKonkaR3Shop(konkaR3Shop);
			if (null != konkaEntpShop) {
				kbc.getMap().put("jxs_name", konkaEntpShop.getShop_name());
			}
		}
		request.setAttribute("konkaBranchCategoryList", konkaBranchCategoryList);

		BasePdType basePdType = new BasePdType();
		basePdType.setDel_mark(new Short((short) 0));
		basePdType.setIs_model(new Short((short) 1));
		List<BasePdType> basePdTypeList = super.getFacade().getBasePdTypeService().getBasePdTypeList(basePdType);
		request.setAttribute("basePdTypeList", basePdTypeList);// 产品大类

		JxcPdType jxcPdType = new JxcPdType();
		List<JxcPdType> JxcPdTypeList = getFacade().getJxcPdTypeService().getJxcPdTypeList(jxcPdType);
		request.setAttribute("JxcPdTypeList", JxcPdTypeList);// 根据Shop_id查进销存自定义产品大类

		Date now = new Date();
		request.setAttribute("today", DateFormatUtils.format(now, "yyyy-MM-dd"));
		request.setAttribute("sn", "XS" + DateFormatUtils.format(now, "yyyyMMddHHmmssSSS"));
		request.setAttribute("userName", user.getUser_name());
		logger.info("++sn++{}:" + "XS" + DateFormatUtils.format(now, "yyyyMMddHHmmssSSS"));

		// 产品分销
		return new ActionForward("/JxcSellBill/form_fx.jsp");
	}

	public ActionForward saveForFx(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 添加发货登记、发货登记明细、门店销售单、门店销售商品明细、更新产品库JXC_PD库存数量
		DynaBean dynaBean = (DynaBean) form;
		String keySeq = (String) dynaBean.get("keySeq");
		String out_sys_id = (String) dynaBean.get("out_sys_id");// 用于判断提交时是否选择了型号
		if (StringUtils.isBlank(out_sys_id)) {
			String msg = "您选择的产品类型中没有型号,请重新选择!";
			renderJavaScript(response, "alert('" + msg + "');history.back();");
			return null;
		}

		String pd_type = request.getParameter("pd_type");// 产品类型
		String brand_id = request.getParameter("brand_id");// 产品品牌id
		String brand_name = request.getParameter("brand_name");// 品牌名称
		String pd_id = request.getParameter("pd_id");// 产品型号
		String count = request.getParameter("count");// 产品数量
		String opr_man = (String) dynaBean.get("opr_man");// 经办人
		String sn = (String) dynaBean.get("sn");// 销售单号
		String sell_date = (String) dynaBean.get("sell_date");// 销售日期
		String pd_bh = (String) dynaBean.get("pd_bh");// 产品编号
		String pd_ch = (String) dynaBean.get("pd_ch");// 产品串号
		String remarks = Constants.JXC_FX_REMARK;// 备注的说明文字
		request.setAttribute("not_validate_record", "true");

		// String customer_id = (String) dynaBean.get("customer_id");// 客户id==
		String jxs_shop_id = (String) dynaBean.get("jxs_shop_id");// 下级经销商id
		String jxs_name = "";// 客户名称

		MmtEntpShop konkaEntpShop = new MmtEntpShop();
		konkaEntpShop.setShop_id(new Long(jxs_shop_id));
		konkaEntpShop = getFacade().getMmtEntpShopService().getMmtEntpShop(konkaEntpShop);
		if (null != konkaEntpShop) {
			jxs_name = konkaEntpShop.getShop_name();
		}
		// KonkaR3Shop konkaR3Shop = new KonkaR3Shop();
		// konkaR3Shop.setId(new Long(jxs_shop_id));
		// konkaR3Shop =
		// getFacade().getKonkaR3ShopService().getKonkaR3Shop(konkaR3Shop);
		// jxs_name = konkaR3Shop.getCustomer_name();

		// StdEntpUser user = super.getStdEntpUserFromRequest(request, response,
		// keySeq);
		UserInfo user = super.getUserInfoFromWebService(request, response);
		if (null == user) {
			return null;
		}
		dynaBean.set("keySeq", keySeq);

		Long shop_id = user.getEntp_shop().getShop_id();
		// if(null != shop_id )
		Integer p_index_I = super.getStdEntpMainByShopId(shop_id).getP_index();
		Long p_index = new Long(p_index_I);
		// user.getStdEntpMain().getP_index().longValue();

		// 根据商铺id和客户的名称==判断，是否存在于JXC_CUSTOMER【门店客户信息表】中
		// 如果不存在则添加一条数据，返回的id为该客户的id-------------------------------------
		// ====start===
		Long customer_id = null;// 定义客户的id
		JxcCustomer jxcCustomer = new JxcCustomer();
		jxcCustomer.setShop_id(shop_id);
		jxcCustomer.setName(jxs_name);// 经销商名称
		jxcCustomer = getFacade().getJxcCustomerService().getJxcCustomer(jxcCustomer);
		if (null != jxcCustomer) {
			customer_id = jxcCustomer.getId();
		} else {
			// 插入一条数据，返回id
			JxcCustomer jxcCustomer2 = new JxcCustomer();
			jxcCustomer2.setShop_id(shop_id);
			jxcCustomer2.setName(jxs_name);//
			jxcCustomer2.setIs_del(0);
			jxcCustomer2.setAdd_date(new Date());
			jxcCustomer2.setAdd_user_id(user.getId());
			jxcCustomer2.setInit_pay(new BigDecimal("0"));
			jxcCustomer2.setCur_pay(new BigDecimal("0"));
			customer_id = getFacade().getJxcCustomerService().createJxcCustomer(jxcCustomer2);
		}

		// 1 ====添加发货登记====
		KonkaJxcFhBill konkaJxcFhBill = new KonkaJxcFhBill();
		konkaJxcFhBill.setShop_id(shop_id);// 商铺id
		konkaJxcFhBill.setFh_sum_count(new Long(count));// =======发货数量=====价格从产品库中取
		konkaJxcFhBill.setAdd_date(new Date());
		konkaJxcFhBill.setAdd_user_id(user.getId());
		konkaJxcFhBill.setAdd_dept_id(user.getEntp_id());
		konkaJxcFhBill.setIs_del(0);
		konkaJxcFhBill.setRemark(remarks);
		konkaJxcFhBill.setSn(sn);
		konkaJxcFhBill.setIs_confirm(0);

		// 发货登记明细
		KonkaJxcFhBillDetails konkaJxcFhBillDetails = new KonkaJxcFhBillDetails();
		konkaJxcFhBillDetails.setPd_type_id(new Long(pd_type));// 类型
		konkaJxcFhBillDetails.setPd_id(new Long(pd_id));// 型号

		BasePdType basePdType = new BasePdType();
		basePdType.setPd_type(new Long(pd_type));
		basePdType = getFacade().getBasePdTypeService().getBasePdType(basePdType);
		if (null != basePdType) {// 类型名称
			konkaJxcFhBillDetails.setPd_type_name(basePdType.getPd_name());
		}

		JxcPd jxcPd = new JxcPd();
		jxcPd.setId(new Long(pd_id));
		jxcPd.setPd_type(new Long(pd_type));
		jxcPd.setBrand_id(new Long(brand_id));
		jxcPd.setShop_id(shop_id);
		jxcPd.setIs_del(0);
		// jxcPd.setShop_p_index(p_index);
		jxcPd.setOwn_sys(0);// 默认进销存
		jxcPd = getFacade().getJxcPdService().getJxcPd(jxcPd);
		if (null != jxcPd) {// 型号名称
			konkaJxcFhBillDetails.setPd_name(jxcPd.getName());
		}

		konkaJxcFhBillDetails.setBrand_id(new Long(brand_id));// 品牌id，name
		konkaJxcFhBillDetails.setBrand_name(brand_name);
		konkaJxcFhBillDetails.setCount(new Long(count));// 数量
		konkaJxcFhBillDetails.setIs_pc(0);// 未盘存
		konkaJxcFhBillDetails.setAdd_date(new Date());
		konkaJxcFhBillDetails.setAdd_user_id(user.getId());// 用户id
		konkaJxcFhBillDetails.setAdd_dept_id(user.getEntp_id());// 部门id
		konkaJxcFhBillDetails.setRemark(remarks);

		// ==========service添加【发货登记明细】=====
		List<KonkaJxcFhBillDetails> konkaJxcFhBillDetailsList = new ArrayList<KonkaJxcFhBillDetails>();
		konkaJxcFhBillDetailsList.add(konkaJxcFhBillDetails);
		konkaJxcFhBill.setKonkaJxcFhBillDetails(konkaJxcFhBillDetailsList);
		Long konkaJxcFhBillId = super.getFacade().getKonkaJxcFhBillService().createKonkaJxcFhBill(konkaJxcFhBill);
		if (null != konkaJxcFhBillId) {
			// 2====添加门店销售单====

			// 门店销售商品明细
			JxcSellBillDetails jxcSellBillDetails = new JxcSellBillDetails();
			jxcSellBillDetails.setPd_id(new Long(pd_id));// 型号id,name===jxcPd对象中的id,name==
			if (null != jxcPd) {
				jxcSellBillDetails.setPd_name(jxcPd.getName());
			}
			jxcSellBillDetails.setPd_type(new Long(pd_type));// 大类id,name
			jxcSellBillDetails.setPd_type_name(basePdType.getPd_name());
			jxcSellBillDetails.setBrand_id(new Long(brand_id));// 品牌id,name
			jxcSellBillDetails.setBrand_name(brand_name);
			jxcSellBillDetails.setCount(new Long(count));// 数量
			// 单价和成本价相同，jxcPd中的价格
			if (null != jxcPd) {
				jxcSellBillDetails.setPrice(jxcPd.getPrice());
				jxcSellBillDetails.setCost_price(jxcPd.getPrice());
			}
			jxcSellBillDetails.setSell_src(3);// 销售来源：0-进销存，1-家电下乡,3-康佳进销存分销
			jxcSellBillDetails.setAdd_date(new Date());
			jxcSellBillDetails.setShop_id(shop_id);// 商铺id
			jxcSellBillDetails.setPd_bh(pd_bh);// 产品编号
			jxcSellBillDetails.setPd_ch(pd_ch);// 产品串号
			jxcSellBillDetails.setRemarks(remarks);

			// 门店销售单
			JxcSellBill jxcSellBill = new JxcSellBill();
			jxcSellBill.setShop_id(shop_id);
			jxcSellBill.setShop_p_index(p_index);
			jxcSellBill.setSn(sn);// 销售单号
			if (null != customer_id) {
				jxcSellBill.setCustomer_id(customer_id);// 客户id===
			}
			if (null != jxs_shop_id) {
				jxcSellBill.setJxs_shop_id(new Long(jxs_shop_id));// 下级经销商mmt_shop_id=======
			}
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			jxcSellBill.setSell_date(format.parse(sell_date));// 销售日期
			jxcSellBill.setOpr_man(opr_man);
			BigDecimal pdPrice = new BigDecimal("0");
			if (null != jxcPd) {
				pdPrice = jxcPd.getPrice().multiply(new BigDecimal(count));
			}
			jxcSellBill.setMoney(pdPrice);// 金额
			jxcSellBill.setPay_money(pdPrice);
			jxcSellBill.setRemarks(remarks);
			jxcSellBill.setAdd_user_id(user.getId());
			jxcSellBill.setAdd_date(new Date());
			jxcSellBill.setIs_del(0);
			jxcSellBill.setOwn_sys(3);// 所属系统：0-默认买卖提，1-家电下乡，2-以旧换新，3、康佳进销存分销
			// logger.info("sn:" + sn);
			// ==========service添加【门店销售商品明细】
			List<JxcSellBillDetails> sellBillDetailList = new ArrayList<JxcSellBillDetails>();
			sellBillDetailList.add(jxcSellBillDetails);
			jxcSellBill.setSellBillDetailList(sellBillDetailList);
			// service更新【产品库JXC_PD库存数量】
			super.getFacade().getJxcSellBillService().createJxcSellBill(jxcSellBill);
		} else {
			return null;
		}

		return new ActionForward("/JxcSellBill.do?method=list&keySeq=" + keySeq, true);
	}

	/** 打印补贴确认单 2012-7-30 */
	public ActionForward printBtqrd(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException, Exception {
		DynaBean dynaBean = (DynaBean) form;
		// String type = (String) dynaBean.get("type");
		// String keySeq = (String) dynaBean.get("keySeq");
		String sell_bill_id = (String) dynaBean.get("sell_bill_id");
		UserInfo user = super.getUserInfoFromWebService(request, response);
		if (null == user) {
			return null;
		}
		JxcSellBill jsb = new JxcSellBill();
		jsb.setId(Long.valueOf(sell_bill_id));
		jsb = getFacade().getJxcSellBillService().getJxcSellBill(jsb);
		if (null == jsb) {
			String msg = "没有查到相应的销售单";
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}
		request.setAttribute("jxcSellBill", jsb);

		JxcCustomer c = new JxcCustomer();
		c.setId(jsb.getCustomer_id());
		c = getFacade().getJxcCustomerService().getJxcCustomer(c);
		request.setAttribute("jxcCustomer", c);

		JxcSellBillDetails jsbd = new JxcSellBillDetails();
		jsbd.setSell_bill_id(jsb.getId());
		jsbd = getFacade().getJxcSellBillDetailsService().getJxcSellBillDetails(jsbd);
		request.setAttribute("jxcSellBillDetails", jsbd);

		JnhmPdContents jpc = new JnhmPdContents();
		jpc.setBrand_name(jsbd.getBrand_name());
		jpc.setPd_name(jsbd.getPd_name());
		jpc = getFacade().getJnhmPdContentsService().getJnhmPdContents(jpc);
		request.setAttribute("jnhmPdContents", jpc);

		StdEntpMain entp = super.getStdEntpMainByShopId(user.getEntp_shop().getShop_id());
		dynaBean.set("userShopName", entp.getEntp_name());

		return new ActionForward("/../jxcnokey/JxcSellBill/_print_btqrd.jsp");
	}

	/** 上传3个附件 2012-7-31 */
	public ActionForward fileUpload(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String keySeq = (String) dynaBean.get("keySeq");
		String sell_bill_id = (String) dynaBean.get("sell_bill_id");
		request.setAttribute("not_validate_record", "true");
		// StdEntpUser user = super.getStdEntpUserFromRequest(request, response,
		// keySeq);
		UserInfo user = super.getUserInfoFromWebService(request, response);
		if (null == user) {
			return null;
		}
		dynaBean.set("keySeq", keySeq);
		Long shop_id = user.getEntp_shop().getShop_id();
		if (!GenericValidator.isLong(sell_bill_id)) {
			return null;
		}

		// 销售单明细
		JxcSellBillDetails details = new JxcSellBillDetails();
		details.setSell_bill_id(Long.valueOf(sell_bill_id));

		List<JxcSellBillDetails> detailList = super.getFacade().getJxcSellBillDetailsService()
				.getJxcSellBillDetailsList(details);

		request.setAttribute("detailList", detailList);

		// 销售单
		JxcSellBill sellBill = new JxcSellBill();
		sellBill.setId(Long.valueOf(sell_bill_id));
		sellBill.setIs_del(0);
		sellBill = super.getFacade().getJxcSellBillService().getJxcSellBill(sellBill);
		request.setAttribute("sellBill", sellBill);

		// 客户
		JxcCustomer cust = new JxcCustomer();
		cust.setShop_id(shop_id);
		cust.setIs_del(0);
		cust.setId(sellBill.getCustomer_id());
		cust = super.getFacade().getJxcCustomerService().getJxcCustomer(cust);
		request.setAttribute("cust", cust);

		// 附件
		KonkaPeAttachments attachment = new KonkaPeAttachments();
		attachment.setLink_id(Long.valueOf(sell_bill_id));
		attachment.setLink_tab("JXC_SELL_BILL");
		attachment.setIs_del(0l);
		request.setAttribute("attachmentList", super.getFacade().getKonkaPeAttachmentsService()
				.getKonkaPeAttachmentsList(attachment));

		return new ActionForward("/JxcSellBill/upload_view.jsp");
	}

	public ActionForward saveUploadFile(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		String keySeq = (String) dynaBean.get("keySeq");
		String sell_bill_id = (String) dynaBean.get("sell_bill_id");
		UserInfo user = super.getUserInfoFromWebService(request, response);
		// logger.info("============keySeq:{}", keySeq);
		if (null == user) {
			return null;
		}
		dynaBean.set("keySeq", keySeq);

		// 附件
		// List<UploadFile> uploadFileList = super.uploadFile(form, true, 0, new
		// int[] { 240 });
		List<UploadFile> uploadFileList = super.uploadFile(form, MmtFilePathConfig.OTHERS_PATH, true, 0,
				new int[] { 240 });
		// List<KonkaPeAttachments> attachmentList = new
		// ArrayList<KonkaPeAttachments>();
		KonkaPeAttachments attachment = null;
		/*
		 * if (uploadFileList.size() != 3) { String msg =
		 * "请上传身份证复印件、发票复印件和《“节能产品惠民工程”补贴确认函》三个文件!"; renderJavaScript(response,
		 * "alert('" + msg + "');history.back();"); return null; }
		 */

		for (UploadFile uploadFile : uploadFileList) {

			attachment = new KonkaPeAttachments();
			attachment.setFile_name(uploadFile.getFileName());
			attachment.setFile_type(uploadFile.getContentType());
			attachment.setFile_size(new Long(uploadFile.getFileSize()));
			attachment.setSave_path(uploadFile.getFileSavePath());
			attachment.setSave_name(uploadFile.getFileSaveName());
			attachment.setIs_del(0l);
			// attachment.setLink_tab(uploadFile.getFormName());
			attachment.setLink_id(Long.valueOf(sell_bill_id));
			attachment.setLink_tab("JXC_SELL_BILL");
			attachment.setAdd_user_name(user.getUser_name());
			attachment.setAdd_user_id(user.getId());
			// attachmentList.add(attachment);

			getFacade().getKonkaPeAttachmentsService().createKonkaPeAttachments(attachment);
		}
		return new ActionForward("/JxcSellBill.do?method=list&keySeq=" + keySeq, true);
	}

	public ActionForward deleteFile(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String file_path = (String) dynaBean.get("file_path");

		if (StringUtils.isBlank(id) || StringUtils.isBlank(file_path)) {
			super.renderText(response, "fail");
			return null;
		}
		logger.info("id:{}", id);
		logger.info("file_path:{}", file_path);

		KonkaPeAttachments entity = new KonkaPeAttachments();
		entity.setId(Long.valueOf(id));
		getFacade().getKonkaPeAttachmentsService().removeKonkaPeAttachments(entity);

		String ctx = request.getSession().getServletContext().getRealPath(String.valueOf(File.separatorChar));
		String realFilePath = ctx + file_path;
		FileTools.deleteFile(realFilePath);

		super.renderText(response, "success");
		return null;
	}

	// 检查产品唯一编码是否重复
	public ActionForward checkPdUnique(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {// 根据大类、品牌获取产品唯一编码
		DynaBean dynaBean = (DynaBean) form;
		String pd_type_id = (String) dynaBean.get("pd_type_id");
		String brand_id = (String) dynaBean.get("brand_id");
		String pd_unique_codes = (String) dynaBean.get("pd_unique_codes");
		// String keySeq = (String) dynaBean.get("keySeq");
		// StdEntpUser user = super.getStdEntpUserFromRequest(request, response,
		// keySeq);
		// UserInfo user = super.getUserInfoFromWebService(request, response);

		// Long shop_id = user.getEntp_shop().getShop_id();
		if (StringUtils.isNotBlank(brand_id) && StringUtils.isNotBlank(pd_type_id)
				&& StringUtils.isNotBlank(pd_unique_codes)) {
			StringBuffer sb = new StringBuffer("[");

			JnhmSelledPdCode jspc = new JnhmSelledPdCode();
			jspc.setPd_type_id(Long.valueOf(pd_type_id));
			jspc.setBrand_id(Long.valueOf(brand_id));
			for (String s : pd_unique_codes.split(",")) {
				jspc.setPd_unique_code(s);
				Long num = getFacade().getJnhmSelledPdCodeService().getJnhmSelledPdCodeCount(jspc);

				if (num.intValue() > 0) {
					sb.append("{\"end\":\"rz\"},");
					// log.info("sb------"+sb.toString());
				}
			}
			sb.append("{\"end\":\"rz\"}]");
			// log.info("sb------"+sb.toString());
			super.renderJson(response, sb.toString());
		}

		return null;
	}

	// 检查组织机构代码
	public ActionForward checkOrgSn(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		UserInfo user = super.getUserInfoFromWebService(request, response);
		if (null == user) {
			return null;
		}

		if (null != user.getEntp_shop().getShop_id()) {
			StringBuffer sb = new StringBuffer("[");

			JxcShopOrgSn jsos = new JxcShopOrgSn();
			jsos.setIs_del(0);
			jsos.setShop_id(Long.valueOf(user.getEntp_shop().getShop_id()));
			jsos = getFacade().getJxcShopOrgSnService().getJxcShopOrgSn(jsos);

			if (null != jsos) {
				sb.append("{\"start\":\"rz\"},");
			}
			sb.append("{\"end\":\"rz\"}]");
			super.renderJson(response, sb.toString());
		}
		return null;
	}

	// 添加组织机构代码
	public ActionForward saveOrgSn(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String isSucess = "0";
		UserInfo user = super.getUserInfoFromWebService(request, response);
		if (null == user) {
			isSucess = "-1";// 密钥key丢失
			super.render(response, isSucess, "text/x-json;charset=UTF-8");
			return null;
		}
		String org_sn = (String) dynaBean.get("org_sn");
		boolean isOrgSn = false;

		JxcShopOrgSn jsos = new JxcShopOrgSn();
		jsos.setIs_del(0);
		// jsos.setShop_id(Long.valueOf(user.getEntp_shop().getShop_id()));
		jsos.setOrg_sn(org_sn);
		jsos = getFacade().getJxcShopOrgSnService().getJxcShopOrgSn(jsos);
		if (null != jsos) {
			isOrgSn = true;
			isSucess = "-2";
			super.render(response, isSucess, "text/x-json;charset=UTF-8");
			return null;
		}

		if (!isOrgSn) {
			JxcShopOrgSn entity = new JxcShopOrgSn();
			entity.setIs_del(0);
			entity.setShop_id(user.getEntp_shop().getShop_id());
			entity.setOrg_sn(org_sn);
			entity.setAdd_user_id(user.getId());
			entity.setAdd_date(new Date());
			Long id = getFacade().getJxcShopOrgSnService().createJxcShopOrgSn(entity);
			isSucess = id.toString();
		}
		super.render(response, isSucess, "text/x-json;charset=UTF-8");
		return null;
	}
}
