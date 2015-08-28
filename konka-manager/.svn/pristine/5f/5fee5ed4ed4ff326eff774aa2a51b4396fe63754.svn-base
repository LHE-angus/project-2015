package com.ebiz.mmt.web.struts.manager.admin;

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
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.alibaba.fastjson.JSON;
import com.ebiz.mmt.domain.BranchAssign;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaMobileImpStore;
import com.ebiz.mmt.domain.KonkaOrderAuditProcess;
import com.ebiz.mmt.domain.KonkaOrderAuditProcessNode;
import com.ebiz.mmt.domain.KonkaOrderInfo;
import com.ebiz.mmt.domain.KonkaOrderInfoAudit;
import com.ebiz.mmt.domain.KonkaOrderInfoDetails;
import com.ebiz.mmt.domain.KonkaPePdModelLowestPrice;
import com.ebiz.mmt.domain.KonkaR3MmtMatch;
import com.ebiz.mmt.domain.KonkaR3OrderLines;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.PePdModel;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleInfo;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.r3.KNVP;
import com.ebiz.mmt.r3.ReturnInfo;
import com.ebiz.mmt.r3.StockCheckResult;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.customer.BaseClientJxcAction;
import com.ebiz.ssi.web.struts.bean.Pager;

public class JxcKonkaOrderRegisterAction extends BaseClientJxcAction {

	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);
		Pager pager = (Pager) dynaBean.get("pager");
		String cust_id = (String) dynaBean.get("cust_id");

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
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

		String add_date_gt = (String) dynaBean.get("add_date_gt");
		String add_date_lt = (String) dynaBean.get("add_date_lt");
		String trade_index_like = (String) dynaBean.get("trade_index_like");

		if (add_date_gt == null) {
			add_date_gt = firstday;
		}
		if (add_date_lt == null) {
			add_date_lt = today;
		}

		dynaBean.set("add_date_gt", add_date_gt);
		dynaBean.set("add_date_lt", add_date_lt);

		KonkaOrderInfo konkaOrderInfo = new KonkaOrderInfo();
		super.copyProperties(konkaOrderInfo, form);
		if (StringUtils.isNotBlank(trade_index_like)) {
			konkaOrderInfo.getMap().put("trade_index_like", trade_index_like);
		}
		if (StringUtils.isNotBlank(cust_id)) {
			konkaOrderInfo.setCust_id(Long.valueOf(cust_id)); // 业务员，直销员登录查看
		} else {
			konkaOrderInfo.setCust_id(user.getCust_id()); // 客户本身来下单
		}
		konkaOrderInfo.getMap().put("start_date", add_date_gt);
		konkaOrderInfo.getMap().put("end_date", add_date_lt);
		Long recordCount = super.getFacade().getKonkaOrderInfoService().getKonkaOrderInfoCount(konkaOrderInfo);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		konkaOrderInfo.getRow().setFirst(pager.getFirstRow());
		konkaOrderInfo.getRow().setCount(pager.getRowCount());

		List<KonkaOrderInfo> konkaOrderInfoList = super.getFacade().getKonkaOrderInfoService()
				.getKonkaOrderInfoResultForPaginatedList(konkaOrderInfo);

		// 确定每个订单当前审核角色
		// 1、有审核记录（记录中最高等级的后一步审核角色，要判断是否在最后一步【根据audit_state】）
		// 2、无审核记录（该网点分配的用户角色）
		// ...................

		request.setAttribute("konkaOrderInfoList", konkaOrderInfoList);
		return mapping.findForward("list");
	}

	/** 订单登记只进康佳品牌的型号 */
	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String cust_id = (String) dynaBean.get("cust_id");

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}
		logger.info("zoule+++++++++++++++++++++++++++");
		Long current_cust_id = null;

		if (null != user.getCust_id()) {
			// 当前登录人是客户
			current_cust_id = user.getCust_id();
		} else {
			// 当前登录人不是客户也就是业务员
			PeRoleUser _peRoleUser = new PeRoleUser();
			_peRoleUser.setUser_id(user.getId());
			List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);

			boolean role_id_eq_60 = false;
			for (PeRoleUser peRoleUser : peRoleUserList) {
				if (peRoleUser.getRole_id() == 60L) {
					role_id_eq_60 = true;
				}
			}
			if (user.getCust_id() == null && role_id_eq_60) {
				if (!GenericValidator.isLong(cust_id)) {
					return null;
				}
				current_cust_id = Long.valueOf(cust_id);
			}
		}

		if (null == current_cust_id) {
			return null;
		}

		request.setAttribute("cust_id", current_cust_id);

		KonkaR3Shop konkaR3Shop = new KonkaR3Shop();
		konkaR3Shop.setId(current_cust_id);
		konkaR3Shop = getFacade().getKonkaR3ShopService().getKonkaR3Shop(konkaR3Shop);
		if (null == konkaR3Shop) {
			super.renderJavaScript(response, "alert('" + super.getMessage(request, "konka.r3.customer.none")
					+ "');history.back();");
			return null;
		}

		// 查询客户用户信息
		PeProdUser u = new PeProdUser();
		u.setCust_id(current_cust_id);
		List<PeProdUser> uList = super.getFacade().getPeProdUserService().getPeProdUserList(u);

		if (null != uList && uList.size() > 0) {
			request.setAttribute("no_user_tip", true);
			if (null != uList.get(0).getP_index()) {
				super.setprovinceAndcityAndcountryToFrom(dynaBean, u.getP_index());
			}
		}

		dynaBean.set("brandId", Constants.KONKA_BRAND_ID);
		dynaBean.set("tradeIndex", super.generateTradeIndex());// 交易流水号
		dynaBean.set("today", DateFormatUtils.format(new Date(), "yyyy-MM-dd"));// 订单日期
		dynaBean.set("userShopName", konkaR3Shop.getCustomer_name());

		// dynaBean.set("userName", user.getReal_name());
		// dynaBean.set("userAddr", user.getLink_addr());

		// dynaBean.set("userZip", entp.getPostcode());
		// dynaBean.set("userAddr", entp.getAddr());
		// dynaBean.set("userTel", entp.getTel());
		// dynaBean.set("userPIndex", user.getStdEntpMain().getP_index());

		String fgsSN = konkaR3Shop.getBranch_area_name_2();
		String fgsSN2 = konkaR3Shop.getR3_sales_org_code();
		request.setAttribute("sales_org", StringUtils.isBlank(fgsSN2) ? fgsSN : fgsSN2);

		if (super.isCallR3Interface(request)) {
//			List<KNVP> knvpList = super.getFacade().getR3WebInterfaceService().getKnvpsList(fgsSN,
//					Constants.default_vtweg, Constants.default_spart, konkaR3Shop.getR3_code());
//			
			
			List<KNVP> knvpList = new ArrayList<KNVP>();
			ReturnInfo<KNVP> info = new ReturnInfo<KNVP>();
			info = super.getFacade().getR3WebInterfaceService()
					.getKnvpsList(fgsSN,
					Constants.default_vtweg, Constants.default_spart, konkaR3Shop.getR3_code());
			if (info.getSuccess() == true) {
				knvpList = info.getDataResult();
			}
			
			// AG:售达方
			// RE:出票方
			// RG:付款方
			// WE:送达方

			List<KNVP> agList = new ArrayList<KNVP>();
			List<KNVP> reList = new ArrayList<KNVP>();
			List<KNVP> rgList = new ArrayList<KNVP>();
			List<KNVP> weList = new ArrayList<KNVP>();

			if (null != knvpList) {
				for (KNVP cur : knvpList) {
					if ("AG".equalsIgnoreCase(cur.getPARVW())) {
						agList.add(cur);
					} else if ("RE".equalsIgnoreCase(cur.getPARVW())) {
						reList.add(cur);
					} else if ("RG".equalsIgnoreCase(cur.getPARVW())) {
						rgList.add(cur);
					} else if ("WE".equalsIgnoreCase(cur.getPARVW())) {
						weList.add(cur);
					}
				}
			}

			request.setAttribute("agList", agList);
			request.setAttribute("reList", reList);
			request.setAttribute("rgList", rgList);
			request.setAttribute("weList", weList);
		} else {
			request.setAttribute("ag", konkaR3Shop.getR3_code());
			request.setAttribute("re", konkaR3Shop.getR3_code());
			request.setAttribute("rg", konkaR3Shop.getR3_code());

			KonkaMobileImpStore s = new KonkaMobileImpStore();
			s.setR3_shdf_sn(konkaR3Shop.getR3_code());
			List<KonkaMobileImpStore> sList = super.getFacade().getKonkaMobileImpStoreService()
					.getKonkaMobileImpStoreListForDistinctSdf(s);

			List<KNVP> weList = new ArrayList<KNVP>();
			for (KonkaMobileImpStore cur : sList) {
				KNVP k = new KNVP();
				k.setKUNN2(cur.getR3_sdf_sn());
				weList.add(k);
			}
			request.setAttribute("weList", weList);
		}

		return mapping.findForward("input");
	}

	/** 保存订单 */
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String cust_id = (String) dynaBean.get("cust_id");
		String order_id = (String) dynaBean.get("order_id");
		String zbukrs = (String) dynaBean.get("sales_org");
		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user || StringUtils.isBlank(cust_id)) {
			super.renderJavaScript(response, "alert('登录超时请重新登录。');history.back();");
			return null;
		}

		String[] pd_ids = request.getParameterValues("pd_id");// 型号
		String[] md_names = request.getParameterValues("md_name");// 产品编码
		String[] good_counts = request.getParameterValues("good_count");// 数量
		String[] good_units = request.getParameterValues("good_unit");// 单位
		String[] good_prices = request.getParameterValues("good_price");// 单价
		// String[] sum_prices = request.getParameterValues("sum_price");//单个金额
		String[] good_discounts = request.getParameterValues("good_discount");// 折让
		String[] pd_remark = request.getParameterValues("pd_remark");// 产品备注
		// String[] discount_prices =
		// request.getParameterValues("discount_price");// 单个折让金额
		// String order_num = (String) dynaBean.get("order_num");// 订单总数
		// String money = (String) dynaBean.get("money_sum");// 订单总金额
		// String discount_price_sum = (String)
		// dynaBean.get("discount_price_sum");// 订单折让总金额
		KonkaOrderInfo konkaOrderInfo = new KonkaOrderInfo();
		super.copyProperties(konkaOrderInfo, form);

		if (null != order_id) {
			konkaOrderInfo.setId(new Long(order_id));
		}

		// 检查库存 1 通过,0 不通过
		if (super.isCallR3Interface(request)) {
			String[] md_names2 = new String[pd_ids.length - 1];
			String[] good_counts2 = new String[pd_ids.length - 1];
			// 开始. 取过来的值是{,xxx,yyy}
			for (int i = 1; i < md_names.length; i++) {
				md_names2[i - 1] = md_names[i];
			}
			for (int i = 1; i < good_counts.length; i++) {
				good_counts2[i - 1] = good_counts[i];
			}

			int isPass = 0;
			List<StockCheckResult> checkResult = this.checkStock(zbukrs, md_names2, good_counts2);
			for (StockCheckResult sr : checkResult) {
				isPass = isPass * sr.getIsOk();
			}

			if (isPass != 1) {
				// 库存检查没有通过,显示检查结果
				request.setAttribute("checkResult", checkResult);
				return null;
			}
		}

		/** add 20120305===== */
		konkaOrderInfo.setIs_del(0);// 未删除
		konkaOrderInfo.setIs_submit(1);// 0:暂存，1：已提交
		konkaOrderInfo.setIs_end(0);// 未完结
		/** add 20120305===== */

		konkaOrderInfo.setCust_id(Long.valueOf(cust_id));
		// konkaOrderInfo.setShop_id(shop_id); //客户端：康佳客户不保存
		konkaOrderInfo.setOrder_state(0);
		konkaOrderInfo.setAdd_user_id(user.getId());
		konkaOrderInfo.setAdd_user_name(user.getUser_name());
		konkaOrderInfo.setProcess_state(1);// 普通流程
		String YM = DateFormatUtils.format(konkaOrderInfo.getOrder_date(), "yyyyMMdd");
		int year = Integer.valueOf(YM.substring(0, 4));// 订单年份
		int month = Integer.valueOf(YM.substring(4, 6));// 订单月份

		KonkaDept fgsOfCurrentCustmer = super.getKonkaDeptByCustomerId(Long.valueOf(cust_id));
		if (null == fgsOfCurrentCustmer) {
			super.renderJavaScript(response, "alert('可能是您不是分公司管理员，或者您的客户信息不完整，没有权限操作。');history.back();");
			return null;
		}
		// 取该分公司定义的特殊流程
		KonkaOrderAuditProcess konkaOrderAuditProcess = super.getSpecialProcessByFgsDeptId(fgsOfCurrentCustmer
				.getDept_id());

		// 产品信息
		List<KonkaOrderInfoDetails> konkaOrderInfoDetailsList = new ArrayList<KonkaOrderInfoDetails>();
		if (ArrayUtils.isEmpty(pd_ids)) {
			super.renderJavaScript(response, "alert('未选择产品。');history.back();");
			return null;
		}

		Long orderNum = 0L;
		BigDecimal orderMoney = new BigDecimal("0.00");
		BigDecimal orderDiscountPrice = new BigDecimal("0.00");
		for (int i = 1; i < pd_ids.length; i++) {
			KonkaOrderInfoDetails konkaOrderInfoDetails = new KonkaOrderInfoDetails();
			konkaOrderInfoDetails.setPd_id(Long.valueOf(pd_ids[i]));
			// 取型号、大类信息
			PePdModel pePdModel = new PePdModel();
			pePdModel.setBrand_id(Constants.KONKA_BRAND_ID);
			pePdModel.setIs_del(0);
			pePdModel.setPd_id(Long.valueOf(pd_ids[i]));
			pePdModel = getFacade().getPePdModelService().getPePdModel(pePdModel);
			if (null != pePdModel) {
				konkaOrderInfoDetails.setPd_name(pePdModel.getMd_name());
				konkaOrderInfoDetails.setBrand_id(pePdModel.getBrand_id());
				konkaOrderInfoDetails.setBrand_name(Constants.KONKA_BRAND_NAME);
				konkaOrderInfoDetails.setPd_type_id(pePdModel.getCls_id());
				konkaOrderInfoDetails.setPd_type_name(super.getBasePdClazz(pePdModel.getCls_id()).getCls_name());
			}

			// 判断是否定义特殊流程，如果没定义则不改变订单状态为特殊流程状态
			if (null != konkaOrderAuditProcess // 该分公司定义了特殊流程，即产品低于限价需要走特殊流程
					&& konkaOrderInfo.getProcess_state() != 2 // 此行为优化作用，若该订单已被设置需要走特殊流程（n[n>1]个产品需要走特殊流程，仅作1次查询）
			) {
				// 获取分公司的产品限价
				KonkaPePdModelLowestPrice konkaPePdModelLowestPrice = new KonkaPePdModelLowestPrice();
				konkaPePdModelLowestPrice.setAdd_dept_id(fgsOfCurrentCustmer.getDept_id()); // 分公司
				konkaPePdModelLowestPrice.setPd_id(new Long(pd_ids[i])); // 产品
				konkaPePdModelLowestPrice.setSet_year(year); // 年
				konkaPePdModelLowestPrice.setSet_month(month); // 月
				konkaPePdModelLowestPrice.setIs_del(0);
				konkaPePdModelLowestPrice = getFacade().getKonkaPePdModelLowestPriceService()
						.getKonkaPePdModelLowestPrice(konkaPePdModelLowestPrice);
				if (null != konkaPePdModelLowestPrice // 设置了产品限价
						&& new Long(good_prices[i]) < konkaPePdModelLowestPrice.getLowest_price().longValue()) {
					konkaOrderInfo.setProcess_state(2);// 特殊流程（产品价格低于了部门产品的最低限价）
				}
			}
			konkaOrderInfoDetails.setGood_count(Integer.valueOf(good_counts[i]));
			orderNum += Long.valueOf(good_counts[i]);

			konkaOrderInfoDetails.setGood_price(new BigDecimal(good_prices[i]));
			konkaOrderInfoDetails.setGood_unit(good_units[i]);
			// konkaOrderInfoDetails.setGood_sum_price(new
			// BigDecimal(sum_prices[i]));
			konkaOrderInfoDetails.setGood_sum_price(new BigDecimal(good_prices[i]).multiply(new BigDecimal(
					good_counts[i])));
			orderMoney = orderMoney.add(konkaOrderInfoDetails.getGood_sum_price());

			konkaOrderInfoDetails.setGood_discount(new BigDecimal(good_discounts[i]));
			// konkaOrderInfoDetails.setGood_discount_price(new
			// BigDecimal(discount_prices[i]));

			konkaOrderInfoDetails.setGood_discount_price(konkaOrderInfoDetails.getGood_sum_price().multiply(
					new BigDecimal(good_discounts[i])).divide(new BigDecimal("100")));

			konkaOrderInfoDetails.setPd_remark(pd_remark[i]);
			// 处理异常标识符
			if (StringUtils.containsIgnoreCase(pd_remark[i], "退市样机")) {
				konkaOrderInfoDetails.setPd_remark("退市样机");
			} else if (StringUtils.containsIgnoreCase(pd_remark[i], "常规处理机")) {
				konkaOrderInfoDetails.setPd_remark("常规处理机");
			}

			orderDiscountPrice = orderDiscountPrice.add(konkaOrderInfoDetails.getGood_discount_price());
			konkaOrderInfoDetailsList.add(konkaOrderInfoDetails);
		}

		konkaOrderInfo.setOrder_num(orderNum);// 订单总数
		// konkaOrderInfo.setMoney(new BigDecimal(money));//订单总金额
		// konkaOrderInfo.setGood_discount_price(new
		// BigDecimal(discount_price_sum));//订单折让总金额
		konkaOrderInfo.setMoney(orderMoney);// 订单总金额
		konkaOrderInfo.setGood_discount_price(orderDiscountPrice);// 订单折让总金额
		konkaOrderInfo.setKonkaOrderInfoDetailsList(konkaOrderInfoDetailsList);

		// 判断匹配或者
		// 作为经销商被开拓到代理商下时，对应的R3用户有没有被分配到业务员：如果有，则业务员角色自动审核、如果没有则不处理
		PeProdUser ywyOfCustomer = super.getYwyOfCustomerByCustomerId(Long.valueOf(cust_id));
		if (null != ywyOfCustomer) {
			// 如果有业务员则作业务员自动审核操作
			konkaOrderInfo.setAudit_state(1);// 更新为审核中
			List<KonkaOrderInfoAudit> konkaOrderInfoAuditList = new ArrayList<KonkaOrderInfoAudit>();
			KonkaOrderInfoAudit konkaOrderInfoAudit = new KonkaOrderInfoAudit();// 业务员自动审核的信息
			konkaOrderInfoAudit.setAudit_type(0);
			konkaOrderInfoAudit.setAudit_level(1);// 业务员是第一级审核
			konkaOrderInfoAudit.setAudit_user_id(ywyOfCustomer.getId());
			konkaOrderInfoAudit.setAudit_user(ywyOfCustomer.getUser_name());
			konkaOrderInfoAudit.setAudit_comment("同意（业务员角色：系统自动审核通过）");
			konkaOrderInfoAudit.setAudit_result(1);
			konkaOrderInfoAudit.setAudit_dept_name(ywyOfCustomer.getDepartment());
			konkaOrderInfoAudit.setAudit_dept_id(ywyOfCustomer.getDept_id());
			konkaOrderInfoAuditList.add(konkaOrderInfoAudit);

			konkaOrderInfo.setKonkaOrderInfoAuditList(konkaOrderInfoAuditList);
		}

		if (null != order_id) {
			// 暂存后提交
			konkaOrderInfo.getMap().put("is_temp_save", "true");
			KonkaOrderInfoDetails konkaOrderInfoDetails = new KonkaOrderInfoDetails();
			konkaOrderInfoDetails.setOrder_id(new Long(order_id));
			List<KonkaOrderInfoDetails> konkaOrderInfoDetailsListForDel = super.getFacade()
					.getKonkaOrderInfoDetailsService().getKonkaOrderInfoDetailsList(konkaOrderInfoDetails);
			konkaOrderInfo.setKonkaOrderInfoDetailsListForDel(konkaOrderInfoDetailsListForDel);// 需要删除的详细信息
		}

		HashMap<String, String> result = getFacade().getKonkaOrderInfoService().createKonkaOrderInfo(konkaOrderInfo);// 保存订单与短信接口返回值
		String inputline = result.get("sendResult");

		if (null != inputline) {
			// 返回值对应表
			if ("0".equals(inputline)) {// 发送成功success;
				saveMessage(request, "konka.jxc.inserted.sended.0");
			} else if ("-1".equals(inputline)) {// 参数为空params is null;
				saveMessage(request, "konka.jxc.inserted.sended.-1");
			} else if ("-2".equals(inputline)) {// 手机号码不合法mp is illegal;
				saveMessage(request, "konka.jxc.inserted.sended.-2");
			} else if ("-3".equals(inputline)) {// IOException;
				saveMessage(request, "konka.jxc.inserted.sended.-99999");// 帐号或者密码错误
			} else if ("-4".equals(inputline)) {// upload exception;
				saveMessage(request, "konka.jxc.inserted.sended.-99999");// 上传出现异常
			} else if ("-41".equals(inputline)) {// upload file is too
				// big(size :2M);
				saveMessage(request, "konka.jxc.inserted.sended.-99999");// 上传文件太大
			} else if ("-5".equals(inputline)) {// ParseException;
				saveMessage(request, "konka.jxc.inserted.sended.-99999");// 通过异常
			} else if ("-11".equals(inputline)) {// 保留remain;
				saveMessage(request, "konka.jxc.inserted.sended.-99999");// 定时发送时间不是有效的时间格式
			} else if ("-12".equals(inputline)) {// 校验不合法key illegal;
				saveMessage(request, "konka.jxc.inserted.sended.-12");
			} else if ("-13".equals(inputline)) {// ip illegal;
				saveMessage(request, "konka.jxc.inserted.sended.-13");
			} else if ("-14".equals(inputline)) {// unknown error;
				saveMessage(request, "konka.jxc.inserted.sended.-99999");// 未知错误
			} else if ("-101".equals(inputline)) {// 商户的参数为空mc param is
				// null;
				saveMessage(request, "konka.jxc.inserted.sended.-101");
			} else if ("-102".equals(inputline)) {// 商户不存在mc is not
				// exist;
				saveMessage(request, "konka.jxc.inserted.sended.-102");
			} else if ("-103".equals(inputline)) { // 商户密钥校验错误mc key is
				// error;
				saveMessage(request, "konka.jxc.inserted.sended.-103");
			} else if ("-104".equals(inputline)) {// 商户IP地址不是合同指定的mc ip
				// is
				// error;
				saveMessage(request, "konka.jxc.inserted.sended.-104");
			} else if ("-105,**".equals(inputline)) {// 短信内容超过最大长度，**为数字表示最大长度
				saveMessage(request, "konka.jxc.inserted.sended.-105");
			} else if ("-106,**".equals(inputline)) {// 群发短信超过最大限制，**为数字表示最大群发数
				saveMessage(request, "konka.jxc.inserted.sended.-106");
			}
		}
		if (null == inputline) {// 没有任何返回值
			saveMessage(request, "entity.inserted");
		}

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&cust_id=" + cust_id);
		pathBuffer.append("&");
		// pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request,
		// "id", "pks", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;

	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user || null == user.getCust_id()) {
			return null;
		}

		// 进货登记单
		String order_id = (String) dynaBean.get("order_id");
		KonkaOrderInfo konkaOrderInfo = new KonkaOrderInfo();
		konkaOrderInfo.setId(Long.valueOf(order_id));
		konkaOrderInfo = getFacade().getKonkaOrderInfoService().getKonkaOrderInfo(konkaOrderInfo);

		if (null == konkaOrderInfo) {
			super.renderJavaScript(response, "alert('指定定单不存在。');history.back();");
			return null;
		}

		// dynaBean.set("fullName",
		// super.getPIndexFullName(konkaOrderInfo.getUser_p_index()));
		dynaBean.set("fullName", super.getPIndexName(konkaOrderInfo.getUser_p_index(), ""));

		KonkaOrderInfoDetails konkaOrderInfoDetails = new KonkaOrderInfoDetails();
		konkaOrderInfoDetails.setOrder_id(Long.valueOf(order_id));
		List<KonkaOrderInfoDetails> konkaOrderInfoDetailsList = getFacade().getKonkaOrderInfoDetailsService()
				.getKonkaOrderInfoDetailsList(konkaOrderInfoDetails);
		request.setAttribute("konkaOrderInfoDetailsList", konkaOrderInfoDetailsList);

		/* 订单审核信息 start */
		// 取订单当前所处的审核流程(随时可能变动)

		// 根据订单id，取所对应的分公司
		KonkaDept konkaDeptForFGS = super.getKonkaFGSByOrderId(konkaOrderInfo.getId());
		// 取订单当前所处的审核流程(随时可能变动)
		KonkaOrderAuditProcess konkaOrderAuditProcess = new KonkaOrderAuditProcess();
		konkaOrderAuditProcess.setAdd_dept_id(konkaDeptForFGS.getDept_id());//
		konkaOrderAuditProcess.setProcess_type(konkaOrderInfo.getProcess_state());
		konkaOrderAuditProcess.setIs_del(0);
		konkaOrderAuditProcess = getFacade().getKonkaOrderAuditProcessService().getKonkaOrderAuditProcess(
				konkaOrderAuditProcess);

		if (null == konkaOrderAuditProcess) {
			// 审核流程还没定义
			konkaOrderInfo.getMap().put("audit_role_name", "等待审核");
		}

		// 审核状态：0 未审核 ，1 审核中 ，2 审核未通过 ， 3 审核通过 （2、3两种状态为审核完成的最终状态
		// 即标示为2、3的订单已完成审核）
		if (konkaOrderInfo.getAudit_state() != 0) {
			// 有审核
			List<KonkaOrderInfoAudit> list = super.getKonkaOrderInfoAuditWithRoleInfoList(konkaOrderInfo.getId());// 订单审核信息列表

			if (konkaOrderInfo.getAudit_state() == 1) {
				// 审核中，显示当前审核角色的 下一步审核角色

				KonkaOrderAuditProcessNode entity = new KonkaOrderAuditProcessNode();
				entity.setAudit_proces_id(konkaOrderAuditProcess.getId());
				// 审核等级，最后一级
				Long process_max_level = super.getKonkaOrderAuditProcessNodeMaxLevel(konkaDeptForFGS.getDept_id(),
						new Long(konkaOrderInfo.getId()));// 流程最高级别
				if (process_max_level == list.get(0).getAudit_level().longValue()) {

					entity.setAudit_level(list.get(0).getAudit_level());
				} else {
					entity.setAudit_level(list.get(0).getAudit_level() + 1);
				}
				List<KonkaOrderAuditProcessNode> nextKonkaOrderAuditProcessNodeList = getFacade()
						.getKonkaOrderAuditProcessNodeService().getKonkaOrderAuditProcessNodeList(entity);
				String[] nextRoleNames = new String[nextKonkaOrderAuditProcessNodeList.size()];
				for (int i = 0; i < nextKonkaOrderAuditProcessNodeList.size(); i++) {
					nextRoleNames[i] = nextKonkaOrderAuditProcessNodeList.get(i).getRole_name();
				}
				konkaOrderInfo.getMap().put("audit_role_name", StringUtils.join(nextRoleNames, ","));

			} else if (konkaOrderInfo.getAudit_state() == 2 || konkaOrderInfo.getAudit_state() == 3) {
				// 审核通过或者未通过，显示最后审核角色

				konkaOrderInfo.getMap().put("audit_role_name", list.get(0).getMap().get("role_name"));
				logger.info("audit_role_name1============{}", list.get(0).getMap().get("role_name"));
			}
			request.setAttribute("konkaOrderAuditList", list);
		} else {
			// 未审核,显示当前审核角色 即该网点分配的用户角色

			Long r3_shop_id = null;
			if (null != konkaOrderInfo.getShop_id()) {
				// 根据买卖提商铺ID 获取SHOP_ID
				KonkaR3MmtMatch konkaR3MmtMatch = new KonkaR3MmtMatch();
				konkaR3MmtMatch.setMmt_shop_id(konkaOrderInfo.getShop_id());
				konkaR3MmtMatch = getFacade().getKonkaR3MmtMatchService().getKonkaR3MmtMatch(konkaR3MmtMatch);

				r3_shop_id = konkaR3MmtMatch.getR3_shop_id();
			} else if (null != konkaOrderInfo.getCust_id()) {
				r3_shop_id = konkaOrderInfo.getCust_id();
			}

			BranchAssign branchAssign = new BranchAssign();
			branchAssign.setBranch_type(1);
			branchAssign.setKonka_r3_id(r3_shop_id);
			branchAssign = getFacade().getBranchAssignService().getBranchAssign(branchAssign);
			if (null != branchAssign) {
				if (null != branchAssign.getUser_id()) {
					// 已分配到具体的人员
					PeRoleUser peRoleUser = new PeRoleUser();
					// peRoleUser.setUser_id(branchAssign.getUser_id());
					// peRoleUser =
					// getFacade().getPeRoleUserService().getPeRoleUser(peRoleUser);
					logger.info("user_id============={}", branchAssign.getUser_id());
					peRoleUser = super.getRoleInfoByUserId(branchAssign.getUser_id());
					if (null != peRoleUser) {// 查找角色名称
						PeRoleInfo peRoleInfo = new PeRoleInfo();
						peRoleInfo.setRole_id(peRoleUser.getRole_id());
						peRoleInfo = getFacade().getPeRoleInfoService().getPeRoleInfo(peRoleInfo);
						if (null != peRoleInfo) {
							konkaOrderInfo.getMap().put("audit_role_name", peRoleInfo.getRole_name());
						}
					}

				} else {
					// 未分配到具体的人员
					if (null != branchAssign.getBsc_id() || null != branchAssign.getJyb_id()
							|| null != branchAssign.getFgs_id()) {
						// 办事处、经营部、分公司
						konkaOrderInfo.getMap().put("audit_role_name", "暂无");
					}
				}
			}
		}

		super.copyProperties(form, konkaOrderInfo);
		return mapping.findForward("view");
	}

	/** 暂存订单 */
	public ActionForward tempSave(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws ParseException {
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String cust_id = (String) dynaBean.get("cust_id");
		String order_id = (String) dynaBean.get("order_id");

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		} else if (null == user.getCust_id()) {
			return null;
		}

		if (StringUtils.isBlank(cust_id)) {
			return null;
		}

		String[] pd_ids = request.getParameterValues("pd_id");// 型号
		// String[] pd_type_names =
		// request.getParameterValues("pd_type_name");//隐藏域大类
		String[] good_counts = request.getParameterValues("good_count");// 数量
		String[] good_units = request.getParameterValues("good_unit");// 单位
		String[] good_prices = request.getParameterValues("good_price");// 单价
		// String[] sum_prices = request.getParameterValues("sum_price");//单个金额
		String[] good_discounts = request.getParameterValues("good_discount");// 折让
		String[] pd_remark = request.getParameterValues("pd_remark");// 产品备注
		// String[] discount_prices =
		// request.getParameterValues("discount_price");// 单个折让金额
		// String order_num = (String) dynaBean.get("order_num");// 订单总数
		// String money = (String) dynaBean.get("money_sum");// 订单总金额
		// String discount_price_sum = (String)
		// dynaBean.get("discount_price_sum");// 订单折让总金额

		KonkaOrderInfo konkaOrderInfo = new KonkaOrderInfo();
		super.copyProperties(konkaOrderInfo, form);
		if (null != order_id) {
			konkaOrderInfo.setId(new Long(order_id));
		}

		/** add 20120305===== */
		konkaOrderInfo.setIs_del(0);// 未删除
		konkaOrderInfo.setIs_submit(0);// 0:暂存，1：已提交
		konkaOrderInfo.setIs_end(0);// 未完结
		/** add 20120305===== */

		konkaOrderInfo.setCust_id(Long.valueOf(cust_id));
		// konkaOrderInfo.setShop_id(shop_id); //客户端：康佳客户不保存
		konkaOrderInfo.setOrder_state(0);
		konkaOrderInfo.setAdd_user_id(user.getId());
		konkaOrderInfo.setAdd_user_name(user.getUser_name());
		konkaOrderInfo.setProcess_state(1);// 普通流程
		String YM = DateFormatUtils.format(konkaOrderInfo.getOrder_date(), "yyyyMMdd");
		int year = Integer.valueOf(YM.substring(0, 4));// 订单年份
		int month = Integer.valueOf(YM.substring(4, 6));// 订单月份

		List<KonkaOrderInfoDetails> konkaOrderInfoDetailsList = new ArrayList<KonkaOrderInfoDetails>();
		if (ArrayUtils.isNotEmpty(pd_ids)) {
			Long orderNum = 0L;
			BigDecimal orderMoney = new BigDecimal("0.00");
			BigDecimal orderDiscountPrice = new BigDecimal("0.00");
			for (int i = 1; i < pd_ids.length; i++) {
				KonkaOrderInfoDetails konkaOrderInfoDetails = new KonkaOrderInfoDetails();
				konkaOrderInfoDetails.setPd_id(Long.valueOf(pd_ids[i]));
				// 取型号、大类信息
				PePdModel pePdModel = new PePdModel();
				pePdModel.setBrand_id(Constants.KONKA_BRAND_ID);
				pePdModel.setIs_del(0);
				pePdModel.setPd_id(Long.valueOf(pd_ids[i]));
				pePdModel = getFacade().getPePdModelService().getPePdModel(pePdModel);
				if (null != pePdModel) {
					konkaOrderInfoDetails.setPd_name(pePdModel.getMd_name());
					konkaOrderInfoDetails.setBrand_id(pePdModel.getBrand_id());
					konkaOrderInfoDetails.setBrand_name(Constants.KONKA_BRAND_NAME);
					konkaOrderInfoDetails.setPd_type_id(pePdModel.getCls_id());
					konkaOrderInfoDetails.setPd_type_name(super.getBasePdClazz(pePdModel.getCls_id()).getCls_name());
				}
				// 取R3网点分公司ID
				KonkaR3Shop krs = new KonkaR3Shop();
				krs.setId(Long.valueOf(cust_id));
				krs = getFacade().getKonkaR3ShopService().getKonkaR3ShopWithBranchAssign(krs);
				if (null != krs) {
					BigDecimal deptId = (BigDecimal) krs.getMap().get("fgs_id");
					if (null != deptId && deptId.intValue() != -1) {// deptId=-1l,说明没有找到上级分公司（暂时没做限制添加订单的处理）

						// 判断是否定义特殊流程，如果没定义则不改变订单状态为特殊流程状态
						KonkaOrderAuditProcess konkaOrderAuditProcess = super.getSpecialProcessByFgsDeptId(deptId
								.longValue());
						if (null != konkaOrderAuditProcess) {
							// 取分公司的产品限价
							KonkaPePdModelLowestPrice konkaPePdModelLowestPrice = new KonkaPePdModelLowestPrice();
							konkaPePdModelLowestPrice.setAdd_dept_id(deptId.longValue());
							konkaPePdModelLowestPrice.setPd_id(new Long(pd_ids[i]));
							konkaPePdModelLowestPrice.setSet_year(year);
							konkaPePdModelLowestPrice.setSet_month(month);
							konkaPePdModelLowestPrice.setIs_del(0);
							konkaPePdModelLowestPrice = getFacade().getKonkaPePdModelLowestPriceService()
									.getKonkaPePdModelLowestPrice(konkaPePdModelLowestPrice);
							if (null != konkaPePdModelLowestPrice) {
								if (new BigDecimal(good_prices[i]).longValue() < konkaPePdModelLowestPrice
										.getLowest_price().longValue()) {
									konkaOrderInfo.setProcess_state(2);// 特殊流程（产品价格低于了部门产品的最低限价）
								}
							}
						}
					}
				}

				konkaOrderInfoDetails.setGood_count(Integer.valueOf(good_counts[i]));
				orderNum += Long.valueOf(good_counts[i]);

				konkaOrderInfoDetails.setGood_price(new BigDecimal(good_prices[i]));
				konkaOrderInfoDetails.setGood_unit(good_units[i]);
				// konkaOrderInfoDetails.setGood_sum_price(new
				// BigDecimal(sum_prices[i]));
				konkaOrderInfoDetails.setGood_sum_price(new BigDecimal(good_prices[i]).multiply(new BigDecimal(
						good_counts[i])));
				orderMoney = orderMoney.add(konkaOrderInfoDetails.getGood_sum_price());

				konkaOrderInfoDetails.setGood_discount(new BigDecimal(good_discounts[i]));
				// konkaOrderInfoDetails.setGood_discount_price(new
				// BigDecimal(discount_prices[i]));

				konkaOrderInfoDetails.setGood_discount_price(konkaOrderInfoDetails.getGood_sum_price().multiply(
						new BigDecimal(good_discounts[i])).divide(new BigDecimal("100")));

				konkaOrderInfoDetails.setPd_remark(pd_remark[i]);

				orderDiscountPrice = orderDiscountPrice.add(konkaOrderInfoDetails.getGood_discount_price());
				konkaOrderInfoDetailsList.add(konkaOrderInfoDetails);
			}

			konkaOrderInfo.setOrder_num(orderNum);// 订单总数
			konkaOrderInfo.setMoney(orderMoney);// 订单总金额
			konkaOrderInfo.setGood_discount_price(orderDiscountPrice);// 订单折让总金额
			konkaOrderInfo.setKonkaOrderInfoDetailsList(konkaOrderInfoDetailsList);

			if (null != order_id) {
				// 暂存后又暂存,修改订单主表，先删除后添加详细信息
				konkaOrderInfo.getMap().put("is_temp_save", "true");// 判断是暂存，还是订单审核时的修改

				KonkaOrderInfoDetails konkaOrderInfoDetails = new KonkaOrderInfoDetails();
				konkaOrderInfoDetails.setOrder_id(new Long(order_id));
				List<KonkaOrderInfoDetails> konkaOrderInfoDetailsListForDel = super.getFacade()
						.getKonkaOrderInfoDetailsService().getKonkaOrderInfoDetailsList(konkaOrderInfoDetails);
				konkaOrderInfo.setKonkaOrderInfoDetailsListForDel(konkaOrderInfoDetailsListForDel);// 需要删除的详细信息
			}
			super.getFacade().getKonkaOrderInfoService().createKonkaOrderInfo(konkaOrderInfo);// 保存订单，业务员不自动审核、不发送短信
			saveMessage(request, "entity.temp.inserted");
		}

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		// pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request,
		// "id", "pks", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	/** 修改订单 */
	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String cust_id = (String) dynaBean.get("cust_id");

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		} else if (null == user.getCust_id()) {
			return null;
		}

		if (StringUtils.isBlank(cust_id)) {
			return null;
		}

		KonkaR3Shop konkaR3Shop = new KonkaR3Shop();
		konkaR3Shop.setId(user.getCust_id());
		konkaR3Shop = getFacade().getKonkaR3ShopService().getKonkaR3Shop(konkaR3Shop);
		if (null == konkaR3Shop) {
			return null;
		}

		dynaBean.set("brandId", Constants.KONKA_BRAND_ID);
		Date today = new Date();
		dynaBean.set("today", DateFormatUtils.format(today, "yyyy-MM-dd"));// 订单日期

		// dynaBean.set("userPIndex", user.getStdEntpMain().getP_index());

		// super.setprovinceAndcityAndcountryToFrom(dynaBean,
		// user.getEntp_shop().getP_index().longValue());

		// 取R3网点分公司ID
		KonkaR3Shop krs = new KonkaR3Shop();
		krs.setId(Long.valueOf(cust_id));
		krs = getFacade().getKonkaR3ShopService().getKonkaR3ShopWithBranchAssign(krs);
		if (null != krs) {
			BigDecimal deptId = (BigDecimal) krs.getMap().get("fgs_id");
			if (null != deptId && deptId.intValue() == -1) {// deptId=-1l,说明没有找到上级分公司
				request.setAttribute("confirm", 1);
			}
		}

		// 全部康佳产品
		PePdModel pePdModel = new PePdModel();
		pePdModel.setBrand_id(Constants.KONKA_BRAND_ID);
		pePdModel.setIs_del(0);
		List<PePdModel> pdList = getFacade().getPePdModelService().getPePdModelWithClsNameBrandNameList(pePdModel);

		request.setAttribute("pdList", pdList);

		// 进货登记单
		String order_id = (String) dynaBean.get("order_id");
		KonkaOrderInfo konkaOrderInfo = new KonkaOrderInfo();
		konkaOrderInfo.setId(Long.valueOf(order_id));
		konkaOrderInfo = getFacade().getKonkaOrderInfoService().getKonkaOrderInfo(konkaOrderInfo);
		dynaBean.set("fullName", super.getPIndexFullName(konkaOrderInfo.getUser_p_index()));
		dynaBean.set("order_id", order_id);
		dynaBean.set("userName", konkaOrderInfo.getUser_name());
		dynaBean.set("userShopName", konkaOrderInfo.getUser_shop_name());
		dynaBean.set("userZip", konkaOrderInfo.getUser_zip());
		dynaBean.set("userAddr", konkaOrderInfo.getUser_addr());
		dynaBean.set("userTel", konkaOrderInfo.getUser_tel());
		dynaBean.set("tradeIndex", konkaOrderInfo.getTrade_index());// 交易流水号
		super.setprovinceAndcityAndcountryToFrom(dynaBean, konkaOrderInfo.getUser_p_index());

		KonkaOrderInfoDetails konkaOrderInfoDetails = new KonkaOrderInfoDetails();
		konkaOrderInfoDetails.setOrder_id(Long.valueOf(order_id));
		List<KonkaOrderInfoDetails> konkaOrderInfoDetailsList = getFacade().getKonkaOrderInfoDetailsService()
				.getKonkaOrderInfoDetailsList(konkaOrderInfoDetails);
		request.setAttribute("konkaOrderInfoDetailsList", konkaOrderInfoDetailsList);

		String[] pd_ids = new String[konkaOrderInfoDetailsList.size()];
		for (int i = 0; i < konkaOrderInfoDetailsList.size(); i++) {
			pd_ids[i] = konkaOrderInfoDetailsList.get(i).getPd_id().toString();
		}
		dynaBean.set("pd_ids", StringUtils.join(pd_ids, ","));

		String fgsSN = konkaR3Shop.getBranch_area_name_2();
		String fgsSN2 = konkaR3Shop.getR3_sales_org_code();
		request.setAttribute("sales_org", StringUtils.isBlank(fgsSN2) ? fgsSN : fgsSN2);

		if (super.isCallR3Interface(request)) {
			String sales_org = StringUtils.isBlank(fgsSN2) ? fgsSN : fgsSN2;
			// List<KNVP> knvpList =
			// super.getFacade().getR3WebInterfaceService().getKnvpsList(sales_org,
			// Constants.default_vtweg, Constants.default_spart,
			// konkaR3Shop.getR3_code());
			//
			List<KNVP> knvpList = new ArrayList<KNVP>();
			ReturnInfo<KNVP> info = new ReturnInfo<KNVP>();
			info = super
					.getFacade()
					.getR3WebInterfaceService()
					.getKnvpsList(sales_org,
					Constants.default_vtweg, Constants.default_spart, konkaR3Shop.getR3_code());
			if (info.getSuccess() == true) {
				knvpList = info.getDataResult();
			}

			// AG:售达方
			// RE:出票方
			// RG:付款方
			// WE:送达方

			List<KNVP> agList = new ArrayList<KNVP>();
			List<KNVP> reList = new ArrayList<KNVP>();
			List<KNVP> rgList = new ArrayList<KNVP>();
			List<KNVP> weList = new ArrayList<KNVP>();

			if (null != knvpList) {
				for (KNVP cur : knvpList) {
					if ("AG".equalsIgnoreCase(cur.getPARVW())) {
						agList.add(cur);
					} else if ("RE".equalsIgnoreCase(cur.getPARVW())) {
						reList.add(cur);
					} else if ("RG".equalsIgnoreCase(cur.getPARVW())) {
						rgList.add(cur);
					} else if ("WE".equalsIgnoreCase(cur.getPARVW())) {
						weList.add(cur);
					}
				}
			}

			request.setAttribute("agList", agList);
			request.setAttribute("reList", reList);
			request.setAttribute("rgList", rgList);
			request.setAttribute("weList", weList);

			request.setAttribute("knvpList", knvpList);
		}

		super.copyProperties(form, konkaOrderInfo);
		return new ActionForward("/../admin/JxcKonkaOrderRegister/form_edit.jsp");
	}

	// 检查订单上的产品的库存
	private List<StockCheckResult> checkStock(String zbukrs, String[] md_names, String[] good_counts) {
		List<KonkaR3OrderLines> itemList = new ArrayList<KonkaR3OrderLines>();
		if (md_names.length != 0 && md_names.length == good_counts.length) {
			for (int i = 0; i < md_names.length; i++) {
				KonkaR3OrderLines konkaR3OrderLines = new KonkaR3OrderLines();
				konkaR3OrderLines.setReview_amount(new BigDecimal(good_counts[i].trim() + ""));
				konkaR3OrderLines.setMaterial_code(md_names[i]);
				itemList.add(konkaR3OrderLines);
			}
		}

		// List<StockCheckResult> checkResult = null;
		// checkResult =
		// getFacade().getR3WebInterfaceService().checkStock(zbukrs, itemList);
		//
		List<StockCheckResult> checkResult = new ArrayList<StockCheckResult>();
		ReturnInfo<StockCheckResult> info = new ReturnInfo<StockCheckResult>();
		info = super.getFacade().getR3WebInterfaceService().checkStock(zbukrs, itemList);
		if (info.getSuccess() == true) {
			checkResult = info.getDataResult();
		}

		return checkResult;
	}

	/**
	 * 检查订单上的产品的库存,ajax
	 */
	public ActionForward checkStockForAjax(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (!super.isCallR3Interface(request)) {
			return null;
		}

		DynaBean dynaBean = (DynaBean) form;
		String zbukrs = (String) dynaBean.get("sales_org"); // 销售组织
		String md_names = (String) dynaBean.get("md_name");// 产品编码
		String good_counts = (String) dynaBean.get("good_count");// 数量

		String[] md_names_array = md_names.split(",");
		String[] good_counts_array = good_counts.split(",");
		List<StockCheckResult> checkResult = this.checkStock(zbukrs, md_names_array, good_counts_array);

		logger.info("json string : {}", JSON.toJSONString(checkResult));

		super.renderJson(response, JSON.toJSONString(checkResult));
		return null;
	}

}
