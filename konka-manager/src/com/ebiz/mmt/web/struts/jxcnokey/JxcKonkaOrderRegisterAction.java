package com.ebiz.mmt.web.struts.jxcnokey;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.impl.cookie.DateUtils;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.BranchAssign;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaOrderAuditProcess;
import com.ebiz.mmt.domain.KonkaOrderAuditProcessNode;
import com.ebiz.mmt.domain.KonkaOrderInfo;
import com.ebiz.mmt.domain.KonkaOrderInfoAudit;
import com.ebiz.mmt.domain.KonkaOrderInfoDetails;
import com.ebiz.mmt.domain.KonkaPePdModelLowestPrice;
import com.ebiz.mmt.domain.KonkaR3MmtMatch;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.PePdModel;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleInfo;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.domain.StdEntpMain;
import com.ebiz.mmt.domain.StdEntpUser;
import com.ebiz.mmt.domain.UserInfo;
import com.ebiz.mmt.service.impl.InteractWebServiceImpl;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.util.Md5Encrypt;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Li,Ka
 * @version 2011-11-25
 */
@SuppressWarnings("unused")
public class JxcKonkaOrderRegisterAction extends BaseJxcAction {
	private static HashMap<String, String> properties = new HashMap<String, String>();
	static {
		InputStream inputStream = InteractWebServiceImpl.class.getClassLoader().getResourceAsStream(
				"webservice-url.properties");
		Properties p = new Properties();
		try {
			p.load(inputStream);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		for (Object key : p.keySet()) {
			properties.put((String) key, (String) p.get(key));
		}
	}
	
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String keySeq = (String) dynaBean.get("keySeq");
		// StdEntpUser user = super.getStdEntpUserFromRequest(request, response, keySeq);
		UserInfo user = super.getUserInfoFromWebService(request, response);
		if (null == user) {
			return null;
		}
		return this.list(mapping, form, request, response);
		/*
		 * Calendar cal = Calendar.getInstance(); Calendar f = (Calendar) cal.clone(); f.clear(); f.set(Calendar.YEAR,
		 * cal.get(Calendar.YEAR)); f.set(Calendar.MONTH, cal.get(Calendar.MONTH)); String firstday = new
		 * SimpleDateFormat("yyyy-MM-dd").format(f.getTime()); String today = DateFormatUtils.format(new Date(),
		 * "yyyy-MM-dd"); dynaBean.set("keySeq", keySeq); dynaBean.set("add_date_gt", firstday);
		 * dynaBean.set("add_date_lt", today); return mapping.findForward("list");
		 */
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String keySeq = (String) dynaBean.get("keySeq");

		// StdEntpUser user = super.getStdEntpUserFromRequest(request, response, keySeq);
		UserInfo user = super.getUserInfoFromWebService(request, response);
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
		if (StringUtils.isNotBlank(trade_index_like)) {
			konkaOrderInfo.getMap().put("trade_index_like", trade_index_like);
		}
		konkaOrderInfo.setShop_id(user.getEntp_shop().getShop_id());
		super.copyProperties(konkaOrderInfo, form);
		konkaOrderInfo.getMap().put("start_date", add_date_gt);
		konkaOrderInfo.getMap().put("end_date", add_date_lt);
		Long recordCount = super.getFacade().getKonkaOrderInfoService().getKonkaOrderInfoCount(konkaOrderInfo);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		konkaOrderInfo.getRow().setFirst(pager.getFirstRow());
		konkaOrderInfo.getRow().setCount(pager.getRowCount());

		List<KonkaOrderInfo> konkaOrderInfoList = super.getFacade().getKonkaOrderInfoService()
				.getKonkaOrderInfoPaginatedListWithShopName(konkaOrderInfo);

		// 确定每个订单当前审核角色
		// 1、有审核记录（记录中最高等级的后一步审核角色，要判断是否在最后一步【根据audit_state】）
		// 2、无审核记录（该网点分配的用户角色）
		if (konkaOrderInfoList.size() > 0) {
			for (KonkaOrderInfo o : konkaOrderInfoList) {
				// 根据订单id，取所对应的分公司
				KonkaDept konkaDeptForFGS = super.getKonkaFGSByOrderId(o.getId());
				// 取订单当前所处的审核流程(随时可能变动)
				KonkaOrderAuditProcess konkaOrderAuditProcess = new KonkaOrderAuditProcess();
				konkaOrderAuditProcess.setAdd_dept_id(konkaDeptForFGS.getDept_id());//
				konkaOrderAuditProcess.setProcess_type(o.getProcess_state());
				konkaOrderAuditProcess.setIs_del(0);
				konkaOrderAuditProcess = getFacade().getKonkaOrderAuditProcessService().getKonkaOrderAuditProcess(
						konkaOrderAuditProcess);

				if (null == konkaOrderAuditProcess) {
					// 审核流程还没定义
					o.getMap().put("audit_role_name", "等待审核");
					continue;
				}

				List<KonkaOrderInfoAudit> list = super.getKonkaOrderInfoAuditWithRoleInfoList(o.getId());// 订单审核信息列表
				if (o.getAudit_state() != 0) {
					// 有审核
					if (o.getAudit_state() == 1) {// 审核中，显示当前审核角色的 下一步审核角色
						KonkaOrderAuditProcessNode entity = new KonkaOrderAuditProcessNode();
						entity.setAudit_proces_id(konkaOrderAuditProcess.getId());
						// 审核等级，最后一级
						Long process_max_level = super.getKonkaOrderAuditProcessNodeMaxLevel(konkaDeptForFGS
								.getDept_id(), new Long(o.getId()));// 流程最高级别
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
						o.getMap().put("audit_role_name", StringUtils.join(nextRoleNames, ","));

					} else if (o.getAudit_state() == 2 || o.getAudit_state() == 3) {// 审核通过或者未通过，显示最后审核角色
						o.getMap().put("audit_role_name", list.get(0).getMap().get("role_name"));
					}

				} else {
					// 未审核,显示当前审核角色 即该网点分配的用户角色
					KonkaR3MmtMatch konkaR3MmtMatch = new KonkaR3MmtMatch();
					konkaR3MmtMatch.setMmt_shop_id(o.getShop_id());
					konkaR3MmtMatch = getFacade().getKonkaR3MmtMatchService().getKonkaR3MmtMatch(konkaR3MmtMatch);
					if (null != konkaR3MmtMatch) {
						BranchAssign branchAssign = new BranchAssign();
						branchAssign.setBranch_type(1);
						branchAssign.setKonka_r3_id(konkaR3MmtMatch.getR3_shop_id());
						branchAssign = getFacade().getBranchAssignService().getBranchAssign(branchAssign);
						if (null != branchAssign) {
							if (null != branchAssign.getUser_id()) {
								// 已分配到具体的人员
								PeRoleUser peRoleUser = new PeRoleUser();
//								peRoleUser.setUser_id(branchAssign.getUser_id());
//								peRoleUser = getFacade().getPeRoleUserService().getPeRoleUser(peRoleUser);
								peRoleUser = super.getRoleInfoByUserId(branchAssign.getUser_id());
								if (null != peRoleUser) {// 查找角色名称
									PeRoleInfo peRoleInfo = new PeRoleInfo();
									peRoleInfo.setRole_id(peRoleUser.getRole_id());
									peRoleInfo = getFacade().getPeRoleInfoService().getPeRoleInfo(peRoleInfo);
									if (null != peRoleInfo) {
										o.getMap().put("audit_role_name", peRoleInfo.getRole_name());
									}
								}
							} else {
								// 未分配到具体的人员
								if (null != branchAssign.getBsc_id() || null != branchAssign.getJyb_id()
										|| null != branchAssign.getFgs_id()) {
									// 办事处、经营部、分公司
									o.getMap().put("audit_role_name", "暂无");
								}
							}
						}
					}
				}
			}
		}

		request.setAttribute("konkaOrderInfoList", konkaOrderInfoList);
		return mapping.findForward("list");
	}

	/** 订单登记只进康佳品牌的型号 */
	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		DynaBean dynaBean = (DynaBean) form;
		String keySeq = (String) dynaBean.get("keySeq");

		// StdEntpUser user = super.getStdEntpUserFromRequest(request, response, keySeq);
		UserInfo user = super.getUserInfoFromWebService(request, response);

		if (null == user) {
			return null;
		}
		StdEntpMain entp = super.getStdEntpMainByShopId(user.getEntp_shop().getShop_id());
		dynaBean.set("keySeq", keySeq);
		dynaBean.set("brandId", Constants.KONKA_BRAND_ID);
		dynaBean.set("tradeIndex", super.generateTradeIndex());// 交易流水号
		Date today = new Date();
		dynaBean.set("today", DateFormatUtils.format(today, "yyyy-MM-dd"));// 订单日期
		dynaBean.set("userName", user.getUser_name());
		dynaBean.set("userShopName", entp.getEntp_name());
		dynaBean.set("userZip", entp.getPostcode());
		dynaBean.set("userAddr", entp.getAddr());
		dynaBean.set("userTel", entp.getTel());
		// dynaBean.set("userPIndex", user.getStdEntpMain().getP_index());
		super.setprovinceAndcityAndcountryToFrom(dynaBean, entp.getP_index().longValue());

		// 门店的康佳型号列表
		/*
		 * JxcPd jxcPd = new JxcPd(); jxcPd.setShop_id(user.getStdEntpMain().getShop_id());
		 * jxcPd.setBrand_id(Constants.KONKA_BRAND_ID); jxcPd.setIs_del(0); List<JxcPd> pdList =
		 * getFacade().getJxcPdService().getJxcPdListForJX(jxcPd);
		 */

		HashMap<String, String> result = super.getKonkaDeptIdAndType(user.getEntp_shop().getShop_id(), true);
		if (!StringUtils.equals(result.get("result_code"), "0")) {
			String[] deptIds = result.get("r3_dept_ids").split(",");
			Long deptId = new Long(deptIds[0]);
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

		return mapping.findForward("input");
	}

	/** 保存订单 */
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String keySeq = (String) dynaBean.get("keySeq");
		String order_id = (String) dynaBean.get("order_id");
		// StdEntpUser user = super.getStdEntpUserFromRequest(request, response, keySeq);
		UserInfo user = super.getUserInfoFromWebService(request, response);
		if (null == user) {
			return null;
		}
		Long shop_id = user.getEntp_shop().getShop_id();
		if (null == shop_id) {
			String send_error = "无法获取商铺ID，请重新登录或者刷新后重试！";
			super.renderJavaScript(response, "alert('" + send_error + "');history.back();");
			return null;
		}
		dynaBean.set("keySeq", keySeq);

		String[] pd_ids = request.getParameterValues("pd_id");// 型号
		// String[] pd_type_names = request.getParameterValues("pd_type_name");//隐藏域大类
		String[] good_counts = request.getParameterValues("good_count");// 数量
		String[] good_units = request.getParameterValues("good_unit");// 单位
		String[] good_prices = request.getParameterValues("good_price");// 单价
		// String[] sum_prices = request.getParameterValues("sum_price");//单个金额
		String[] good_discounts = request.getParameterValues("good_discount");// 折让
		String[] pd_remark = request.getParameterValues("pd_remark");// 产品备注
		// String[] discount_prices = request.getParameterValues("discount_price");// 单个折让金额
		// String order_num = (String) dynaBean.get("order_num");// 订单总数
		// String money = (String) dynaBean.get("money_sum");// 订单总金额
		// String discount_price_sum = (String) dynaBean.get("discount_price_sum");// 订单折让总金额

		KonkaOrderInfo konkaOrderInfo = new KonkaOrderInfo();
		super.copyProperties(konkaOrderInfo, form);

		if (null != order_id) {
			konkaOrderInfo.setId(new Long(order_id));
		}

		/** add 20120305===== */
		konkaOrderInfo.setIs_del(0);// 未删除
		konkaOrderInfo.setIs_submit(1);// 0:暂存，1：已提交
		konkaOrderInfo.setIs_end(0);// 未完结
		/** add 20120305===== */

		konkaOrderInfo.setShop_id(shop_id);
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
				// 取网点对应的分公司部门ID
				HashMap<String, String> result = super.getKonkaDeptIdAndType(shop_id, true);
				if (!StringUtils.equals(result.get("result_code"), "0")) {
					String[] deptIds = result.get("r3_dept_ids").split(",");
					Long deptId = new Long(deptIds[0]);// 目前保证一个网点只匹配和分配一个分公司下
					if (null != deptId && deptId.intValue() != -1) {// deptId=-1l,说明没有找到上级分公司（暂时没做限制添加订单的处理）

						// 判断是否定义特殊流程，如果没定义则不改变订单状态为特殊流程状态
						KonkaOrderAuditProcess konkaOrderAuditProcess = super.getSpecialProcessByFgsDeptId(deptId);
						if (null != konkaOrderAuditProcess) {
							// 取分公司的产品限价
							KonkaPePdModelLowestPrice konkaPePdModelLowestPrice = new KonkaPePdModelLowestPrice();
							konkaPePdModelLowestPrice.setAdd_dept_id(deptId);
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
				// konkaOrderInfoDetails.setGood_sum_price(new BigDecimal(sum_prices[i]));
				konkaOrderInfoDetails.setGood_sum_price(new BigDecimal(good_prices[i]).multiply(new BigDecimal(
						good_counts[i])));
				orderMoney = orderMoney.add(konkaOrderInfoDetails.getGood_sum_price());

				konkaOrderInfoDetails.setGood_discount(new BigDecimal(good_discounts[i]));
				// konkaOrderInfoDetails.setGood_discount_price(new BigDecimal(discount_prices[i]));

				konkaOrderInfoDetails.setGood_discount_price(konkaOrderInfoDetails.getGood_sum_price().multiply(
						new BigDecimal(good_discounts[i])).divide(new BigDecimal("100")));

				konkaOrderInfoDetails.setPd_remark(pd_remark[i]);

				orderDiscountPrice = orderDiscountPrice.add(konkaOrderInfoDetails.getGood_discount_price());
				konkaOrderInfoDetailsList.add(konkaOrderInfoDetails);
			}

			konkaOrderInfo.setOrder_num(orderNum);// 订单总数
			// konkaOrderInfo.setMoney(new BigDecimal(money));//订单总金额
			// konkaOrderInfo.setGood_discount_price(new BigDecimal(discount_price_sum));//订单折让总金额
			konkaOrderInfo.setMoney(orderMoney);// 订单总金额
			konkaOrderInfo.setGood_discount_price(orderDiscountPrice);// 订单折让总金额
			konkaOrderInfo.setKonkaOrderInfoDetailsList(konkaOrderInfoDetailsList);

			// 判断匹配或者 作为经销商被开拓到代理商下时，对应的R3用户有没有被分配到业务员：如果有，则业务员角色自动审核、如果没有则不处理
			KonkaR3Shop r3 = super.getKonkaR3ShopByShopId(shop_id);
			if (null != r3) {
				BranchAssign branchAssign = super.getBranchAssignByKonkaR3Id(r3.getId());
				if (null != branchAssign) {// 分配了用户管理网点
					if (null != branchAssign.getUser_id()) {// 分配的业务员操作人
						Long userId = branchAssign.getUser_id();
						PeRoleInfo peRoleInfo = super.getPeRoleInfoByUserId(userId);
						PeProdUser peProdUser = super.getPeProdUserByUserId(userId);
						if (peRoleInfo.getRole_id().intValue() == Constants.ROLE_ID_YWY) {// 业务员操作人是业务员角色
							// 将第一步的业务员自动审核

							konkaOrderInfo.setAudit_state(1);// 更新为审核中
							List<KonkaOrderInfoAudit> konkaOrderInfoAuditList = new ArrayList<KonkaOrderInfoAudit>();
							KonkaOrderInfoAudit konkaOrderInfoAudit = new KonkaOrderInfoAudit();// 业务员自动审核的信息
							konkaOrderInfoAudit.setAudit_type(0);
							konkaOrderInfoAudit.setAudit_level(1);// 业务员是第一级审核
							konkaOrderInfoAudit.setAudit_user_id(userId);
							konkaOrderInfoAudit.setAudit_user(peProdUser.getUser_name());
							konkaOrderInfoAudit.setAudit_comment("同意（业务员角色：系统自动审核通过）");
							konkaOrderInfoAudit.setAudit_result(1);
							konkaOrderInfoAudit.setAudit_dept_name(peProdUser.getDepartment());
							konkaOrderInfoAudit.setAudit_dept_id(peProdUser.getDept_id());
							konkaOrderInfoAuditList.add(konkaOrderInfoAudit);

							konkaOrderInfo.setKonkaOrderInfoAuditList(konkaOrderInfoAuditList);
						}
						if (null != peProdUser.getLink_phone()) {// 指定的业务员信息中，手机号码不为空
							// 测试用的uuid、key、网址，正式用的时候需要申请账号
//							String url = "http://sms.mymyty.com/webservice/MerchantSendSms.do";
							String mc_uuid = "cb59271b-c0d4-427c-a5dc-9cba9c0f52a4";// 签订商户协议后，在买卖提短信平台上面生产的“商户UUID”
							String key = "3D055735-03FB-4F09-AF29-87D362320AE6";// 校验码(content + mc_uuid + mobile +
							// 商户协议中约定的key)经过MD5加密之后的值>
							String mobile = peProdUser.getLink_phone();// 发送短信的手机号码
							String dateTime = DateFormatUtils.format(Calendar.getInstance().getTime(),
									"yyyy年MM月dd日HH时mm分ss秒");
							String content = user.getEntp_shop().getShop_name() + "于" + dateTime
									+ "向您提交订单，请及时审核    [来自：康佳渠道管理系统]！";// 短信内容
							String out_sms_sn = "konka0000000001";// 来自什么系统

							String privateKey = content + mc_uuid + mobile + out_sms_sn + key;
							logger.info("URLEncoder.encode before   privateKey : {}", privateKey);

							String privateKey_MD5 = Md5Encrypt.md5(privateKey);
							logger.info("URLEncoder.encode after   privateKey : {}", privateKey_MD5);

							List<NameValuePair> qparams = new ArrayList<NameValuePair>();
							qparams.add(new BasicNameValuePair("method", "sendMessage"));
							qparams.add(new BasicNameValuePair("mobile", mobile));
							qparams.add(new BasicNameValuePair("mc_uuid", mc_uuid));
							qparams.add(new BasicNameValuePair("content", content));
							qparams.add(new BasicNameValuePair("out_sms_sn", out_sms_sn));
							qparams.add(new BasicNameValuePair("key", privateKey_MD5));

							konkaOrderInfo.getMap().put("url", properties.get("kongka.cb.fsdx"));
							konkaOrderInfo.getMap().put("qparams", qparams);
							konkaOrderInfo.getMap().put("charset", Constants.SYS_ENCODING);
						}
					}
				}

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

			HashMap<String, String> result = getFacade().getKonkaOrderInfoService()
					.createKonkaOrderInfo(konkaOrderInfo);// 保存订单与短信接口返回值
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
				} else if ("-41".equals(inputline)) {// upload file is too big(size :2M);
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
				} else if ("-101".equals(inputline)) {// 商户的参数为空mc param is null;
					saveMessage(request, "konka.jxc.inserted.sended.-101");
				} else if ("-102".equals(inputline)) {// 商户不存在mc is not exist;
					saveMessage(request, "konka.jxc.inserted.sended.-102");
				} else if ("-103".equals(inputline)) { // 商户密钥校验错误mc key is error;
					saveMessage(request, "konka.jxc.inserted.sended.-103");
				} else if ("-104".equals(inputline)) {// 商户IP地址不是合同指定的mc ip is error;
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
		}
		return new ActionForward("/JxcKonkaOrderRegister.do?method=list&keySeq=" + keySeq, true);
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		DynaBean dynaBean = (DynaBean) form;
		String keySeq = (String) dynaBean.get("keySeq");
		// StdEntpUser user = super.getStdEntpUserFromRequest(request, response, keySeq);
		UserInfo user = super.getUserInfoFromWebService(request, response);
		if (null == user) {
			return null;
		}
		dynaBean.set("keySeq", keySeq);

		// 进货登记单
		String order_id = (String) dynaBean.get("order_id");
		KonkaOrderInfo konkaOrderInfo = new KonkaOrderInfo();
		konkaOrderInfo.setId(Long.valueOf(order_id));
		konkaOrderInfo = getFacade().getKonkaOrderInfoService().getKonkaOrderInfo(konkaOrderInfo);
		dynaBean.set("fullName", super.getPIndexFullName(konkaOrderInfo.getUser_p_index()));

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

		List<KonkaOrderInfoAudit> list = super.getKonkaOrderInfoAuditWithRoleInfoList(konkaOrderInfo.getId());// 订单审核信息列表
		if (konkaOrderInfo.getAudit_state() != 0) {
			// 有审核
			if (konkaOrderInfo.getAudit_state() == 1) {// 审核中，显示当前审核角色的 下一步审核角色
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

			} else if (konkaOrderInfo.getAudit_state() == 2 || konkaOrderInfo.getAudit_state() == 3) {// 审核通过或者未通过，显示最后审核角色
				konkaOrderInfo.getMap().put("audit_role_name", list.get(0).getMap().get("role_name"));
			}
			request.setAttribute("konkaOrderAuditList", list);
		} else {
			// 未审核,显示当前审核角色 即该网点分配的用户角色
			KonkaR3MmtMatch konkaR3MmtMatch = new KonkaR3MmtMatch();
			konkaR3MmtMatch.setMmt_shop_id(konkaOrderInfo.getShop_id());
			konkaR3MmtMatch = getFacade().getKonkaR3MmtMatchService().getKonkaR3MmtMatch(konkaR3MmtMatch);
			if (null != konkaR3MmtMatch) {
				BranchAssign branchAssign = new BranchAssign();
				branchAssign.setBranch_type(1);
				branchAssign.setKonka_r3_id(konkaR3MmtMatch.getR3_shop_id());
				branchAssign = getFacade().getBranchAssignService().getBranchAssign(branchAssign);
				if (null != branchAssign) {
					if (null != branchAssign.getUser_id()) {
						// 已分配到具体的人员
						PeRoleUser peRoleUser = new PeRoleUser();
//						peRoleUser.setUser_id(branchAssign.getUser_id());
//						peRoleUser = getFacade().getPeRoleUserService().getPeRoleUser(peRoleUser);
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
		}
		super.copyProperties(form, konkaOrderInfo);
		return mapping.findForward("view");
		/* 订单审核信息 end */
	}

	/** 暂存订单 */
	public ActionForward tempSave(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws ParseException {
		DynaBean dynaBean = (DynaBean) form;
		String keySeq = (String) dynaBean.get("keySeq");
		String order_id = (String) dynaBean.get("order_id");
		UserInfo user = super.getUserInfoFromWebService(request, response);
		if (null == user) {
			return null;
		}
		Long shop_id = user.getEntp_shop().getShop_id();
		if (null == shop_id) {
			String send_error = "无法获取商铺ID，请重新登录或者刷新后重试！";
			super.renderJavaScript(response, "alert('" + send_error + "');history.back();");
			return null;
		}
		dynaBean.set("keySeq", keySeq);

		String[] pd_ids = request.getParameterValues("pd_id");// 型号
		// String[] pd_type_names = request.getParameterValues("pd_type_name");//隐藏域大类
		String[] good_counts = request.getParameterValues("good_count");// 数量
		String[] good_units = request.getParameterValues("good_unit");// 单位
		String[] good_prices = request.getParameterValues("good_price");// 单价
		// String[] sum_prices = request.getParameterValues("sum_price");//单个金额
		String[] good_discounts = request.getParameterValues("good_discount");// 折让
		String[] pd_remark = request.getParameterValues("pd_remark");// 产品备注
		// String[] discount_prices = request.getParameterValues("discount_price");// 单个折让金额
		// String order_num = (String) dynaBean.get("order_num");// 订单总数
		// String money = (String) dynaBean.get("money_sum");// 订单总金额
		// String discount_price_sum = (String) dynaBean.get("discount_price_sum");// 订单折让总金额

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

		konkaOrderInfo.setShop_id(shop_id);
		konkaOrderInfo.setOrder_state(0);
		konkaOrderInfo.setAdd_user_id(user.getId());
		konkaOrderInfo.setAdd_user_name(user.getUser_name());
		konkaOrderInfo.setProcess_state(1);// 普通流程
		// konkaOrderInfo.setAudit_state(0);//未审核 默认
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
				// 取网点对应的分公司部门ID
				HashMap<String, String> result = super.getKonkaDeptIdAndType(shop_id, true);
				if (!StringUtils.equals(result.get("result_code"), "0")) {
					String[] deptIds = result.get("r3_dept_ids").split(",");
					Long deptId = new Long(deptIds[0]);// 目前保证一个网点只匹配和分配一个分公司下
					if (null != deptId && deptId.intValue() != -1) {// deptId=-1l,说明没有找到上级分公司（暂时没做限制添加订单的处理）

						// 判断是否定义特殊流程，如果没定义则不改变订单状态为特殊流程状态
						KonkaOrderAuditProcess konkaOrderAuditProcess = super.getSpecialProcessByFgsDeptId(deptId);
						if (null != konkaOrderAuditProcess) {
							// 取分公司的产品限价
							KonkaPePdModelLowestPrice konkaPePdModelLowestPrice = new KonkaPePdModelLowestPrice();
							konkaPePdModelLowestPrice.setAdd_dept_id(deptId);
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
				// konkaOrderInfoDetails.setGood_sum_price(new BigDecimal(sum_prices[i]));
				konkaOrderInfoDetails.setGood_sum_price(new BigDecimal(good_prices[i]).multiply(new BigDecimal(
						good_counts[i])));
				orderMoney = orderMoney.add(konkaOrderInfoDetails.getGood_sum_price());

				konkaOrderInfoDetails.setGood_discount(new BigDecimal(good_discounts[i]));
				// konkaOrderInfoDetails.setGood_discount_price(new BigDecimal(discount_prices[i]));

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

		}

		return new ActionForward("/JxcKonkaOrderRegister.do?method=list&keySeq=" + keySeq, true);
	}

	/** 修改订单 */
	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		DynaBean dynaBean = (DynaBean) form;
		String keySeq = (String) dynaBean.get("keySeq");
		UserInfo user = super.getUserInfoFromWebService(request, response);
		if (null == user) {
			return null;
		}

		dynaBean.set("keySeq", keySeq);
		dynaBean.set("brandId", Constants.KONKA_BRAND_ID);
		dynaBean.set("tradeIndex", super.generateTradeIndex());// 交易流水号
		Date today = new Date();
		dynaBean.set("today", DateFormatUtils.format(today, "yyyy-MM-dd"));// 订单日期
		dynaBean.set("userName", user.getUser_name());
		dynaBean.set("userShopName", user.getEntp_shop().getShop_name());
		dynaBean.set("userZip", user.getEntp_shop().getPost_code());
		dynaBean.set("userAddr", user.getEntp_shop().getStreet_addr());
		dynaBean.set("userTel", user.getEntp_shop().getLink_phone());
		// dynaBean.set("userPIndex", user.getStdEntpMain().getP_index());
		super.setprovinceAndcityAndcountryToFrom(dynaBean, user.getEntp_shop().getP_index().longValue());

		HashMap<String, String> result = super.getKonkaDeptIdAndType(user.getEntp_shop().getShop_id(), true);
		if (!StringUtils.equals(result.get("result_code"), "0")) {
			String[] deptIds = result.get("r3_dept_ids").split(",");
			Long deptId = new Long(deptIds[0]);
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

		KonkaOrderInfoDetails konkaOrderInfoDetails = new KonkaOrderInfoDetails();
		konkaOrderInfoDetails.setOrder_id(Long.valueOf(order_id));
		List<KonkaOrderInfoDetails> konkaOrderInfoDetailsList = getFacade().getKonkaOrderInfoDetailsService()
				.getKonkaOrderInfoDetailsList(konkaOrderInfoDetails);
		request.setAttribute("konkaOrderInfoDetailsList", konkaOrderInfoDetailsList);

		String[] pd_ids = new String[konkaOrderInfoDetailsList.size()];
		for(int i =0;i<konkaOrderInfoDetailsList.size();i++){
			pd_ids[i] = konkaOrderInfoDetailsList.get(i).getPd_id().toString();
		}
		dynaBean.set("pd_ids", StringUtils.join(pd_ids,","));
		
		
		super.copyProperties(form, konkaOrderInfo);
		return new ActionForward("/JxcKonkaOrderRegister/form_edit.jsp");
	}
}
