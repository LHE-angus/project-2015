package com.ebiz.mmt.web.struts.manager.spgl;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.ebiz.mmt.domain.BaseProvinceListFour;
import com.ebiz.mmt.domain.EcMessage;
import com.ebiz.mmt.domain.EcOrderExpressInfo;
import com.ebiz.mmt.domain.EcUser;
import com.ebiz.mmt.domain.KonkaBcompPd;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PshowOrdeAudit;
import com.ebiz.mmt.domain.PshowOrdeDetails;
import com.ebiz.mmt.domain.PshowOrder;
import com.ebiz.mmt.domain.SfhkCompany;
import com.ebiz.mmt.domain.SfhkOutStorage;
import com.ebiz.mmt.domain.SfhkRelEppOrder;
import com.ebiz.mmt.domain.SyncRecodeSfhk;
import com.ebiz.mmt.web.struts.BaseAction;
import com.sf.integration.warehouse.MailnoQuery;
import com.sf.integration.warehouse.OrderFy;
import com.sf.integration.warehouse.SfOrderService;

/**
 * @author Pan,Gang
 * @version 2013-12-18
 */
public class BasePshowOrderAction extends BaseAction {
	/**
	 * 订单与顺丰接口 物流费用批量更新
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String orderFy() {
		EcOrderExpressInfo p = new EcOrderExpressInfo();
		p.getMap().put("logistic_sn_is_not_null", true);
		p.getMap().put("logistic_price_is_null", true);
		List<EcOrderExpressInfo> entityList = super.getFacade().getEcOrderExpressInfoService()
				.getEcOrderExpressInfoList(p);

		String ss = "";
		int i = 0;
		if (null != entityList && entityList.size() > 0) {
			for (EcOrderExpressInfo pp : entityList) {
				try {
					if (StringUtils.isNotBlank(pp.getLogistic_sn())) {
						// TestSfService sfService = new TestSfService();
						// String orderFyXml =
						// sfService.xsddfy(pp.getLogistic_sn().trim());

						PshowOrder po = new PshowOrder();
						po.setTrade_index(pp.getTrade_index());
						po = super.getFacade().getPshowOrderService().getPshowOrder(po);

						SfOrderService sf = new SfOrderService();
						String sxddmxOpName = "queryMailnoDetailService";
						OrderFy orderData = new OrderFy();
						if (po != null && po.getOpr_dept_id() != null && po.getOpr_dept_id().intValue() == 10) {
							orderData.setCheckword("IHcoDnPIalG8oYBzxMYomOwMmHzdykSO");
							orderData.setCustid("kjjt");
						}
						orderData.setMailno(pp.getLogistic_sn().trim());
						String xsddfy = orderData.toXml();
						//System.out.println("xxxxxxxxxxxxx" + xsddfy);
						String orderFyXml = sf.sfWebService3(xsddfy, sxddmxOpName);

						//System.out.println("运单费用明细：" + orderFyXml);
						Document doc1 = DocumentHelper.parseText(orderFyXml);
						Element rootElt3 = doc1.getRootElement();
						Iterator iter3 = rootElt3.elementIterator("orders");
						while (iter3.hasNext()) {
							Element order1 = (Element) iter3.next();
							Iterator iter4 = order1.elementIterator("order");
							while (iter4.hasNext()) {
								Element order2 = (Element) iter4.next();
								String freight = order2.elementTextTrim("freight");
								//System.out.println("运费：-----》{}" + freight);
								if (StringUtils.isNotBlank(freight)) {
									EcOrderExpressInfo p3 = new EcOrderExpressInfo();
									p3.setId(pp.getId());
									p3.setLogistic_price(new BigDecimal(freight));
									super.getFacade().getEcOrderExpressInfoService().modifyEcOrderExpressInfo(p3);
									i++;
								}
							}
						}
					}
				} catch (Exception e) {
					ss = "更新失败,顺丰系统连接超时！";
				}
			}
		}
		ss = "这次一共更新了" + i + "条数据！";
		//System.out.println("这次更新了多少条数据：------------》" + ss);
		return ss;
	}

	/**
	 * 根据运单号查询订单在顺丰物流的状态
	 * 
	 * @param logistic_sn运单号
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String orderState(EcOrderExpressInfo ec) {
		PshowOrder pp = new PshowOrder();
		pp.setTrade_index(ec.getTrade_index());
		pp = super.getFacade().getPshowOrderService().getPshowOrder(pp);
		StringBuffer oper = new StringBuffer("{\"result\":");
		SfOrderService sf = new SfOrderService();
		String sxddmxOpName = "sfexpressService";
		MailnoQuery mq = new MailnoQuery();
		mq.setTracking_type("1");
		mq.setMethod_type("1");
		if (pp.getOpr_dept_id() != null && pp.getOpr_dept_id().intValue() == 10) {// 哈尔滨分公司
			mq.setHead("kjjt,IHcoDnPIalG8oYBzxMYomOwMmHzdykSO");
		}
		mq.setTracking_number(ec.getLogistic_sn().trim());
		String xml2 = mq.toXml();
		//System.out.println("xml------------->" + xml2);
		String returnXml = sf.sfWebService2(xml2, sxddmxOpName);
		//System.out.println("returnXml======>{}" + returnXml);
		if (returnXml.equals("查询超时")) {
			oper.append("\"" + "查询超时" + "\"");
			oper.append("}");
		}
		try {
			Document doc = DocumentHelper.parseText(returnXml);
			Element rootElt = doc.getRootElement();
			Iterator<Element> iter = rootElt.elementIterator("Body");
			List<String> remarkList = new ArrayList<String>();
			while (iter.hasNext()) {
				Element em = iter.next();
				Iterator<Element> iter2 = em.elementIterator("RouteResponse");
				while (iter2.hasNext()) {
					Element em2 = iter2.next();
					Iterator<Element> iter3 = em2.elementIterator("Route");
					while (iter3.hasNext()) {
						Element em3 = iter3.next();
						remarkList.add(em3.attributeValue("remark"));
					}
				}

			}
			String sb = "";
			if (null != remarkList && remarkList.size() > 0) {
				sb = remarkList.get(remarkList.size() - 1);
			} else {
				sb = "暂时没有数据!";
			}
			oper.append("\"" + sb + "\"");
			oper.append("}");
		} catch (Exception e) {
			oper.append("\"" + "查询超时" + "\"");
			oper.append("}");
		}
		return oper.toString();
	}

	/**
	 * 批量更新订单在顺丰物流的状态
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String updateorderState(PeProdUser user) throws Exception {
		String ss = "";
		EcOrderExpressInfo ec = new EcOrderExpressInfo();
		ec.getMap().put("ts_not_in", true);
		List<EcOrderExpressInfo> ecList = super.getFacade().getEcOrderExpressInfoService()
				.getEcOrderExpressInfoList(ec);
		int i = 0;
		if (null != ecList && ecList.size() > 0) {
			for (EcOrderExpressInfo ee : ecList) {
				if (null != ee.getLogistic_content() && ee.getLogistic_content().indexOf("已签收") != -1) {
					PshowOrder pp = new PshowOrder();
					pp.setTrade_index(ee.getTrade_index());
					pp = super.getFacade().getPshowOrderService().getPshowOrder(pp);
					// if (null != pp && pp.getState() == 40 && pp.getPay_way()
					// != 0) {
					if (null != pp && pp.getState() == 40) {
						// 取订单详细信息积分、佣金、下单人信息
						PshowOrdeDetails pd = new PshowOrdeDetails();
						pd.setOrder_id(pp.getId());
						List<PshowOrdeDetails> pdList = super.getFacade().getPshowOrdeDetailsService()
								.getPshowOrdeDetailsList(pd);

						int t_num = 0;
						String pd_name_num = "";// 机型*数量
						BigDecimal rebate = new BigDecimal("0.0");
						for (PshowOrdeDetails ps : pdList) {
							t_num += ps.getNum();
							KonkaBcompPd konkaBcompPd = new KonkaBcompPd();
							konkaBcompPd.setId(ps.getPd_id());
							konkaBcompPd = super.getFacade().getKonkaBcompPdService().getKonkaBcompPd(konkaBcompPd);
							pd_name_num += konkaBcompPd.getPd_sn() + "*" + ps.getNum() + " ";
							if (ps.getRebates() != null) {
								rebate = rebate.add(ps.getRebates());
							}
						}
						// 取订单客户信息、下单人信息
						String kh_name = pp.getBuyer_name();
						String trade_index = pp.getTrade_index();
						int integral = pp.getIntegral() != null ? pp.getIntegral().intValue() : 0;
						int pay_integral = pp.getPay_price() != null ? pp.getPay_price().intValue() : 0;
						int all_integral = integral + pay_integral;
						String rebates = rebate.setScale(2).toString();

						EcUser ecUser = new EcUser();
						ecUser.setId(pp.getOrder_user_id());
						ecUser = super.getFacade().getEcUserService().getEcUser(ecUser);

						String user_real_name = ecUser.getReal_name();
						String user_tel = ecUser.getLink_phone();

						// 更新订单表的状态
						PshowOrder p = new PshowOrder();
						p.setState(60);
						p.setId(pp.getId());
						super.getFacade().getPshowOrderService().modifyPshowOrder(p);

						// 更新审核信息表
						PshowOrdeAudit poa = new PshowOrdeAudit();
						poa.setRemark("系统自动更新");
						poa.setOper_date(new Date());
						poa.setOrder_id(pp.getId());
						poa.setState(60);
						poa.setOpr_user_id(user.getId());// 默认超级管理员
						poa.setOpr_user_real_name(user.getReal_name());
						poa.setTotal_price(pp.getPay_price());
						super.getFacade().getPshowOrdeAuditService().createPshowOrdeAudit(poa);

						if (null != ecUser.getLink_phone() && !"".equals(ecUser.getLink_phone())) {
							String msg = user_real_name + "您好," + kh_name + "的订单" + trade_index + "已签收,积分"
									+ all_integral + "其中:付款积分" + pay_integral + ",奖励积分" + integral + "已到账;佣金" + rebates
									+ ",请您及时进入会员中心,点击提现或转积分![奉献精致产品,引领美妙生活!康佳EPP平台,客服热线0755-26608866-6346]";
							if (ecUser.getUser_type().intValue() == 2) {
								if (ecUser.getDept_id() != null && ecUser.getDept_id().intValue() == 2110) {
									msg = "【康佳&顺丰直销商城】" + user_real_name + "您好," + kh_name + "的订单" + trade_index + "（"
											+ pd_name_num + "）已签收[奉献精致产品,引领美妙生活!客服热线0755-26608866-6346]";
								} else {
									msg = "【康佳网上直销商城】" + user_real_name + "您好," + kh_name + "的订单" + trade_index + "（"
											+ pd_name_num + "）已签收[奉献精致产品,引领美妙生活!客服热线0755-26608866-6346]";
								}
							}
							if (getSendMessageResult(ecUser.getLink_phone(), msg)) {
								EcMessage ecm = new EcMessage();
								ecm.setAdd_date(new Date());
								ecm.setContent(msg);
								ecm.setMobile(user_tel);
								ecm.setOrder_id(p.getId().toString());
								ecm.setSend_date(new Date());
								ecm.setOrder_state(60);
								ecm.setSend_state(0);
								super.getFacade().getEcMessageService().createEcMessage(ecm);
							} else {
								EcMessage ecm = new EcMessage();
								ecm.setAdd_date(new Date());
								ecm.setContent(msg);
								ecm.setMobile(user_tel);
								ecm.setOrder_id(p.getId().toString());
								ecm.setSend_date(new Date());
								ecm.setOrder_state(60);
								ecm.setSend_state(1);
								super.getFacade().getEcMessageService().createEcMessage(ecm);
							}
						}
					}
					continue;
				} else if (null != ee.getLogistic_content() && ee.getLogistic_content().indexOf("签收人是") != -1) {
					PshowOrder pp = new PshowOrder();
					pp.setTrade_index(ee.getTrade_index());
					pp = super.getFacade().getPshowOrderService().getPshowOrder(pp);
					// if (null != pp && pp.getState() == 40 && pp.getPay_way()
					// != 0) {
					if (null != pp && pp.getState() == 40) {
						// 取订单详细信息积分、佣金、下单人信息
						PshowOrdeDetails pd = new PshowOrdeDetails();
						pd.setOrder_id(pp.getId());
						List<PshowOrdeDetails> pdList = super.getFacade().getPshowOrdeDetailsService()
								.getPshowOrdeDetailsList(pd);

						int t_num = 0;
						String pd_name_num = "";// 机型*数量
						BigDecimal rebate = new BigDecimal("0.0");
						for (PshowOrdeDetails ps : pdList) {
							t_num += ps.getNum();
							KonkaBcompPd konkaBcompPd = new KonkaBcompPd();
							konkaBcompPd.setId(ps.getPd_id());
							konkaBcompPd = super.getFacade().getKonkaBcompPdService().getKonkaBcompPd(konkaBcompPd);
							pd_name_num += konkaBcompPd.getPd_sn() + "*" + ps.getNum() + " ";
							if (ps.getRebates() != null) {
								rebate = rebate.add(ps.getRebates());
							}
						}
						// 取订单客户信息、下单人信息
						String kh_name = pp.getBuyer_name();
						String trade_index = pp.getTrade_index();
						int integral = pp.getIntegral() != null ? pp.getIntegral().intValue() : 0;
						int pay_integral = pp.getPay_price() != null ? pp.getPay_price().intValue() : 0;
						int all_integral = integral + pay_integral;
						String rebates = rebate.setScale(2).toString();

						EcUser ecUser = new EcUser();
						ecUser.setId(pp.getOrder_user_id());
						ecUser = super.getFacade().getEcUserService().getEcUser(ecUser);

						String user_real_name = ecUser.getReal_name();
						String user_tel = ecUser.getLink_phone();

						// 更新订单表的状态
						PshowOrder p = new PshowOrder();
						p.setState(60);
						p.setId(pp.getId());
						super.getFacade().getPshowOrderService().modifyPshowOrder(p);

						// 更新审核信息表
						PshowOrdeAudit poa = new PshowOrdeAudit();
						poa.setRemark("系统自动更新");
						poa.setOper_date(new Date());
						poa.setOrder_id(pp.getId());
						poa.setState(60);
						poa.setOpr_user_id(user.getId());// 默认超级管理员
						poa.setOpr_user_real_name(user.getReal_name());
						poa.setTotal_price(pp.getPay_price());
						super.getFacade().getPshowOrdeAuditService().createPshowOrdeAudit(poa);

						if (null != ecUser.getLink_phone() && !"".equals(ecUser.getLink_phone())) {
							String msg = user_real_name + "您好," + kh_name + "的订单" + trade_index + "已签收,积分"
									+ all_integral + "其中:付款积分" + pay_integral + ",奖励积分" + integral + "已到账;佣金" + rebates
									+ ",请您及时进入会员中心,点击提现或转积分![奉献精致产品,引领美妙生活!康佳EPP平台,客服热线0755-26608866-6346]";
							if (ecUser.getUser_type().intValue() == 2) {
								if (ecUser.getDept_id() != null && ecUser.getDept_id().intValue() == 2110) {
									msg = "【康佳&顺丰直销商城】" + user_real_name + "您好," + kh_name + "的订单" + trade_index + "（"
											+ pd_name_num + "）已签收[奉献精致产品,引领美妙生活!客服热线0755-26608866-6346]";
								} else {
									msg = "【康佳网上直销商城】" + user_real_name + "您好," + kh_name + "的订单" + trade_index + "（"
											+ pd_name_num + "）已签收[奉献精致产品,引领美妙生活!客服热线0755-26608866-6346]";
								}
							}
							if (getSendMessageResult(ecUser.getLink_phone(), msg)) {
								EcMessage ecm = new EcMessage();
								ecm.setAdd_date(new Date());
								ecm.setContent(msg);
								ecm.setMobile(user_tel);
								ecm.setOrder_id(p.getId().toString());
								ecm.setSend_date(new Date());
								ecm.setOrder_state(60);
								ecm.setSend_state(0);
								super.getFacade().getEcMessageService().createEcMessage(ecm);
							} else {
								EcMessage ecm = new EcMessage();
								ecm.setAdd_date(new Date());
								ecm.setContent(msg);
								ecm.setMobile(user_tel);
								ecm.setOrder_id(p.getId().toString());
								ecm.setSend_date(new Date());
								ecm.setOrder_state(60);
								ecm.setSend_state(1);
								super.getFacade().getEcMessageService().createEcMessage(ecm);
							}
						}
					}
					continue;
				} else {
					PshowOrder pp = new PshowOrder();
					pp.setTrade_index(ee.getTrade_index());
					pp = super.getFacade().getPshowOrderService().getPshowOrder(pp);

					if (null != pp && pp.getState() == 40) {
						SfOrderService sf = new SfOrderService();
						String sxddmxOpName = "sfexpressService";
						MailnoQuery mq = new MailnoQuery();
						mq.setTracking_type("1");
						mq.setMethod_type("1");
						if (pp.getOpr_dept_id() != null && pp.getOpr_dept_id().intValue() == 10) {// 哈尔滨分公司
							mq.setHead("kjjt,IHcoDnPIalG8oYBzxMYomOwMmHzdykSO");
						}

						mq.setTracking_number(ee.getLogistic_sn().trim());
						String xml2 = mq.toXml();
						//System.out.println("xml2--------->" + xml2);
						String returnXml = sf.sfWebService2(xml2, sxddmxOpName);
						//System.out.println("returnxml----------->" + returnXml);
						if (!"".equals(returnXml) && returnXml.indexOf("remark") != -1) {
							EcOrderExpressInfo ep = new EcOrderExpressInfo();
							ep.setId(ee.getId());
							ep.setLogistic_content(returnXml.replaceAll("&#x9", ""));
							super.getFacade().getEcOrderExpressInfoService().modifyEcOrderExpressInfo(ep);
							i++;

						}
					}
				}
			}

		}
		ss = "这次一共更新了" + i + "条数据！";
		//System.out.println("这次更新了多少条数据：------------》" + ss);
		return ss;
	}

	private static final String ENCODING = "UTF-8";

	public static String getApiWithUrl(String url) throws Exception {
		InputStream is = null;
		try {
			HttpClient client = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet(url);
			HttpResponse response = client.execute(httpGet);

			is = response.getEntity().getContent();
			BufferedReader reader = new BufferedReader(new InputStreamReader(is, ENCODING));
			StringBuffer result = new StringBuffer();
			String string = null;
			if (null != (string = reader.readLine())) {
				result.append(string);
			}
			// //System.out.println("result: " + result.toString());
			return result.toString();
		} finally {
			// close stream here
			if (is != null) {
				is.close();
			}
		}
	}

	/**
	 * @param start_date
	 * @param end_date
	 * @param page
	 * @param pagesize
	 * @return 同步顺丰嘿客订单
	 */
	public String syncOrderNew(String start_date, String end_date, String page, String pagesize, PeProdUser user) {
		Date now = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String timestamp = sf.format(now);
		timestamp = timestamp.replaceAll(" ", "%20");
		start_date = start_date + "%2000:00:00";
		end_date = end_date + "%2023:59:59";
		// String access_token = "kangjia001";//3897103a6066ded4439a80756447cd6f
		// 3897103a6066ded4439a80756447cd6f
		String access_token = "3897103a6066ded4439a80756447cd6f";

		String url = "http://partner.sfheike.com/app/api/index?method=sfhk.order.search&access_token=" + access_token
				+ "&timestamp=" + timestamp + "&page=" + page + "&order_state=2&start_date=" + start_date
				+ "&end_date=" + end_date;
		String result;
		int i = 0;
		int k = 0;
		Long s_now = System.currentTimeMillis();
		try {
			result = getApiWithUrl(url);
			JSONObject listTotal = JSONObject.fromObject(result);
			JSONArray jsonsTotal = JSONArray.fromObject(listTotal.get("sfhk_order_search_response"));
			String total1 = "0";
			for (Object object : jsonsTotal) {
				JSONObject jsonNode = JSONObject.fromObject(object);
				String code = jsonNode.getString("code");
				if (code.equals("0")) {
					total1 = jsonNode.getString("total");
				}

			}
			int new_page;// 50
			if (Integer.valueOf(total1) == 0) {
				new_page = 1;
			} else if (Integer.valueOf(total1) % Integer.valueOf(pagesize) != 0) {
				new_page = Integer.valueOf(total1) / Integer.valueOf(pagesize) + 1;
			} else {
				new_page = Integer.valueOf(total1) / Integer.valueOf(pagesize);
			}

			for (int j = 1; j < new_page + 1; j++) {
				url = "http://partner.sfheike.com/app/api/index?method=sfhk.order.search&access_token=" + access_token
						+ "&timestamp=" + timestamp + "&page=" + j + "&order_state=2&start_date=" + start_date
						+ "&end_date=" + end_date;

				//System.out.println("url--->" + url);

				// 防止交易流水号重复

				result = getApiWithUrl(url);
				JSONObject list = JSONObject.fromObject(result);
				JSONArray jsons = JSONArray.fromObject(list.get("sfhk_order_search_response"));

				for (Object o : jsons) {
					JSONObject jsonNode = JSONObject.fromObject(o);
					String code = jsonNode.getString("code");
					String total = jsonNode.getString("total");
					if (code.equals("0") && Integer.valueOf(total) > 0) {
						// String tt = jsonNode.getString("timestamp");// 访问接口时间
						JSONArray order_list = JSONArray.fromObject(jsonNode.get("order_list"));

						String sf_order_ids = "";
						SfhkRelEppOrder sr = new SfhkRelEppOrder();// 已经存在的顺丰嘿客订单id
						/*
						 * List<SfhkRelEppOrder> srList = super.getFacade().getSfhkRelEppOrderService()
						 * .getSfhkRelEppOrderAndOrderIdList(sr); if (srList != null && srList.size() > 0) { sr =
						 * srList.get(0); if (sr.getMap().get("sf_order_id") != null) { sf_order_ids = (String)
						 * sr.getMap().get("sf_order_id"); } }
						 */
						List<String> ids = new ArrayList<String>();
						List<SfhkRelEppOrder> srList = super.getFacade().getSfhkRelEppOrderService()
								.getSfhkRelEppOrderList(sr);
						if (srList != null && srList.size() > 0)
							for (SfhkRelEppOrder so : srList) {
								ids.add(so.getSf_order_id());
							}
						sf_order_ids = StringUtils.join(ids, ",");

						//System.out.println("sf_order_ids--->" + sf_order_ids);

						if (order_list.size() > 0) {
							//
							PshowOrder pp = new PshowOrder();
							JSONObject orderJson = new JSONObject();
							for (Object order : order_list) {
								pp = new PshowOrder();
								PshowOrdeDetails pd = new PshowOrdeDetails();
								k++;
								orderJson = new JSONObject();
								orderJson = JSONObject.fromObject(order);
								if (sf_order_ids.contains(orderJson.getString("order_id"))) {
									continue;
								}
								String order_id = orderJson.getString("order_id");// 顺丰嘿客订单id
								String order_state = orderJson.getString("order_state");// 顺丰订单状态
								// 1待付款
								// 2
								// 已付款
								// 4已发货5确认收货

								String invoice = orderJson.getString("invoice");// 是否要发票0否1是
								String order_payment = orderJson.getString("order_payment");// 订单应付金额
								String order_remark = orderJson.getString("order_remark");// 客户订单备注
								String pay_time = orderJson.getString("pay_time");// 订单支付时间
								String vendor_id = orderJson.getString("vendor_id");// 商家id
								String invoice_type = orderJson.getString("invoice_type");// 发票类型
								String invoice_content = orderJson.getString("invoice_content");// 发票内容（家电，日常用品）
								String invoice_title = "";
								invoice_title = orderJson.getString("invoice_title");// 发票单位
								String order_start_time = orderJson.getString("order_start_time");// 下单时间
								String pay_type = orderJson.getString("pay_type");// 支付方式1在线支付2门店支付
								String order_seller_price = orderJson.getString("order_seller_price");// 订单商品总金额
								String seller_discount = orderJson.getString("seller_discount");// 折扣金额
								String order_total_price = orderJson.getString("order_total_price");// 订单总金额（可能包含运费）
								String pin = orderJson.getString("pin");// 买家在顺丰嘿客的账号
								String parent_order_id = orderJson.getString("parent_order_id");// 父订单id，退换货可能用到
								// String vendor_remark =
								// orderJson.getString("vendor_remark");// 商家备注

								JSONArray jsonsConsignee_info = JSONArray.fromObject(orderJson.get("consignee_info"));
								String buy_addr = "";// 收货人地址全称
								for (Object object : jsonsConsignee_info) {
									JSONObject jsd = JSONObject.fromObject(object);
									String province = jsd.getString("province");// 省
									String city = jsd.getString("city");// 市
									String county = jsd.getString("county");// 县
									String full_address = jsd.getString("full_address");// 详细地址
									String mobile = jsd.getString("mobile");// 手机
									String telephone = jsd.getString("telephone");// 电话
									String fullname = jsd.getString("fullname");// 姓名
									buy_addr = province + city + county + full_address;

									// 顺丰嘿客 如果是北京地区，可能是province=北京市 city= 东城区
									// county="";

									BaseProvinceListFour bf = new BaseProvinceListFour();
									pp.setBuyer_p_index(null);// 清空pindex
									if (!county.equals("")) {
										bf.getMap().put("country", county);
										bf.getMap().put("city", city);
										bf.getMap().put("province", province);
										List<BaseProvinceListFour> bfList = super.getFacade()
												.getBaseProvinceListFourService()
												.getBaseProvinceListFourByCityNameList(bf);
										if (null != bfList && bfList.size() > 0) {
											pp.setBuyer_p_index(bfList.get(0).getP_index());// 省市县行政区划编码
										}
									} else {
										if (!province.contains("市")) {
											province = province + "市";
										}
										bf.getMap().put("country", city);
										bf.getMap().put("province", province);
										List<BaseProvinceListFour> bfList = super.getFacade()
												.getBaseProvinceListFourService()
												.getBaseProvinceListFourByCityNameList(bf);
										if (null != bfList && bfList.size() > 0) {
											pp.setBuyer_p_index(bfList.get(0).getP_index());// 省市县行政区划编码
										}
									}
									pp.setBuyer_mp(mobile);
									pp.setBuyer_tel(telephone);
									pp.setBuyer_name(fullname);
									pp.setBuyer_addr(buy_addr);// 存全部地址信息，防止匹配不到pindex，客服可以手动修改
								}
								// pp.setRemark(vendor_remark);
								pp.setIs_del(0);
								pp.setState(10);// 已确认付款，待财务审核
								pp.setUuid(UUID.randomUUID().toString());
								String trade_index = DateFormatUtils.format(now, "yyMMddHHmmss")
										+ StringUtils.substring(String.valueOf(now.getTime() + k), 7);
								pp.setTrade_index(trade_index);
								pp.setLogistic_sn("顺丰嘿客下单人是：" + pin);// 把下单人备注到客户备注栏
								pp.setSfhk_order_name(pin);
								pp.setPay_way(8);// 支付方式
								pp.setPay_date(sf.parse(pay_time));
								pp.setOrder_from(2);
								pp.setOrder_type(0);
								// pp.setOrder_user_id(100180L);// 默认一个下单人，写死
								// (测试)4616L
								// pp.setOrder_user_name("MMTLJ");// 测试（不会念经的小沙弥）

								pp.setOrder_user_id(128008L);
								pp.setOrder_user_name("顺丰嘿客商城");

								pp.setDeliver_time(1);
								pp.setDeliver_is_call(1);
								pp.setPay_price(new BigDecimal(order_payment));
								pp.setTotal_price(new BigDecimal(order_total_price));
								pp.setDedu_price(new BigDecimal("0.00"));
								pp.setIntegral(new BigDecimal("0.00"));
								pp.setPay_integral(new BigDecimal("0.00"));
								pp.setIs_deduction(1);
								// 是否索要发票
								if (invoice.equals("1")) {
									pp.setBill_is_add(1);
								} else {
									pp.setBill_is_add(1);// 不管顺丰嘿客传什么，康佳这边都要开发票
								}

								// 发票类型
								if (StringUtils.isNotBlank(invoice_title)) {
									if (invoice_title.equals("个人")) {
										pp.setBill_head(0);
									} else {
										pp.setBill_head(1);
										pp.setBill_company(invoice_title);
									}
								} else {
									pp.setBill_head(0);
								}

								if (invoice_type.equals("普通发票")) {
									pp.setBill_type(0);
								} else if (invoice_type.equals("增值税发票")) {
									pp.setBill_type(1);
								} else {
									pp.setBill_type(0);
								}

								// 发票单位
								// pp.setBill_company(invoice_title);
								pp.setAdd_date(sf.parse(order_start_time));// 取顺丰嘿客下单时间

								Long id = super.getFacade().getPshowOrderService().createPshowOrder(pp);

								SfhkRelEppOrder sfe = new SfhkRelEppOrder();
								sfe.setAdd_date(new Date());
								sfe.setAdd_user_id(user.getId());
								sfe.setReal_name(user.getReal_name());
								sfe.setBuy_addr(buy_addr);
								sfe.setEpp_order_id(String.valueOf(id));
								sfe.setSf_order_id(order_id);
								sfe.setPay_state(10);
								sfe.setSf_state(Integer.valueOf(order_state));
								super.getFacade().getSfhkRelEppOrderService().createSfhkRelEppOrder(sfe);

								JSONArray jsondetails = JSONArray.fromObject(orderJson.get("product_info_list"));
								for (Object js : jsondetails) {
									JSONObject jsd = JSONObject.fromObject(js);
									String product_id = jsd.getString("product_id");// 嘿客商品id
									String sfhk_price = jsd.getString("sfhk_price");// 嘿客价
									String quantity = jsd.getString("quantity");// 数量
									String product_no = jsd.getString("product_no");// 商户商品编码，关联我们系统商品id
									String sku_id = jsd.getString("sku_id");// sku_id
									// 用来关联我们系统里的商品id

									// 根据product_id
									// ,sku_id查找sku_list,和对应的outer_sku_id
									/*
									 * String url2 =
									 * "http://hkapi.st.sf-express.com:2002/app/api/index?method=sfhk.item.get&access_token=kangjia001&product_id="
									 * + product_id + "&timestamp=" + timestamp; String result2; result2 =
									 * getApiWithUrl(url2); JSONObject list2 = JSONObject.fromObject(result2); JSONArray
									 * jsons2 =JSONArray.fromObject(list2.get( "sfhk_item_get_response")); String
									 * out_sku_id = ""; for (Object o2 : jsons2) { JSONObject jsonNode2 =
									 * JSONObject.fromObject(o2); String code2 = jsonNode2.getString("code"); if
									 * (code2.equals("0")) { JSONArray product_info = JSONArray .fromObject(jsonNode2
									 * .get("product_info")); for (Object object : product_info) { JSONObject ob =
									 * JSONObject.fromObject(object); JSONArray sku_list =
									 * JSONArray.fromObject(ob.get("sku_list")); for (Object oo : sku_list) { JSONObject
									 * jn = JSONObject.fromObject(oo); if (jn.getString("sku_id").equals(sku_id)) {
									 * out_sku_id = jn.getString("outer_sku_id"); break; } } } } }
									 */

									if (GenericValidator.isLong(product_no)) {
										KonkaBcompPd kp = new KonkaBcompPd();
										kp.setId(Long.valueOf(product_no));
										kp = super.getFacade().getKonkaBcompPdService().getKonkaBcompPd(kp);
										if (kp != null) {
											pd.setIntegral(new BigDecimal("0.00"));
											pd.setPd_id(kp.getId());
											pd.setPd_name(kp.getPd_name());
											pd.setNum(Long.valueOf(quantity));
											pd.setPrice(new BigDecimal(sfhk_price));
											pd.setTotal_price(new BigDecimal(sfhk_price).multiply(new BigDecimal(
													quantity)));
											pd.setOrder_id(id);
											pd.setState(0);
											pd.setPay_integral(new BigDecimal("0.00"));
											super.getFacade().getPshowOrdeDetailsService().createPshowOrdeDetails(pd);
										}

									}

								}
								i++;
							}
						}
					}

				}
			}
			Long e_now = System.currentTimeMillis();
			SyncRecodeSfhk ss = new SyncRecodeSfhk();
			ss.setAdd_date(new Date());
			ss.setAdd_user_id(user.getId());
			ss.setStart_date(start_date.replaceAll("%20", " "));
			ss.setEnd_date(end_date.replaceAll("%20", " "));
			ss.setNeed_date(String.valueOf(e_now - s_now));
			ss.setReal_name(user.getReal_name());
			ss.setSync_count(Long.valueOf(i));
			ss.setSync_state(0);
			super.getFacade().getSyncRecodeSfhkService().createSyncRecodeSfhk(ss);

		} catch (Exception e) {
			Long e_now = System.currentTimeMillis();
			SyncRecodeSfhk ss = new SyncRecodeSfhk();
			ss.setAdd_date(new Date());
			ss.setAdd_user_id(user.getId());
			ss.setStart_date(start_date.replaceAll("%20", " "));
			ss.setEnd_date(end_date.replaceAll("%20", " "));
			ss.setNeed_date(String.valueOf(e_now - s_now));
			ss.setReal_name(user.getReal_name());
			ss.setSync_count(Long.valueOf(i));
			ss.setSync_state(1);
			super.getFacade().getSyncRecodeSfhkService().createSyncRecodeSfhk(ss);
			e.printStackTrace();
		}

		String message = "";

		message = "这次一共更新了" + i + "条数据！";
		//System.out.println("这次更新了多少条数据：------------》" + message);
		return message;
	}

	public String syncSfCompNew(PeProdUser user) {
		Date now = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String timestamp = sf.format(now);
		timestamp = timestamp.replaceAll(" ", "%20");
		// String access_token = "kangjia001";
		String access_token = "3897103a6066ded4439a80756447cd6f";
		String url = "http://partner.sfheike.com/app/api/index?method=sfhk.logistics.company.get&access_token="
				+ access_token + "&timestamp=" + timestamp;
		String result;
		String message = "";

		List<String> ids = new ArrayList<String>();
		SfhkCompany sr = new SfhkCompany();// 已经存在的物流公司
		List<SfhkCompany> srList = super.getFacade().getSfhkCompanyService().getSfhkCompanyList(sr);
		if (srList != null && srList.size() > 0)
			for (SfhkCompany ss : srList) {
				ids.add(ss.getComp_id());
			}

		try {
			result = getApiWithUrl(url);
			JSONObject list = JSONObject.fromObject(result);
			JSONArray jsons = JSONArray.fromObject(list.get("sfhk_logistics_company_get_response"));
			for (Object o : jsons) {
				JSONObject jsonNode = JSONObject.fromObject(o);
				String code = jsonNode.getString("code");
				if (code.equals("0")) {
					JSONArray logistics_company_list = JSONArray.fromObject(jsonNode.get("logistics_company_list"));
					for (Object object : logistics_company_list) {
						JSONObject ob = JSONObject.fromObject(object);
						if (ids.contains(ob.getString("id"))) {
							continue;
						}
						SfhkCompany entity = new SfhkCompany();
						entity.setAdd_date(new Date());
						entity.setAdd_user_id(user.getId());
						entity.setComp_id(ob.getString("id"));
						entity.setComp_name(ob.getString("name"));
						super.getFacade().getSfhkCompanyService().createSfhkCompany(entity);
						message = "同步成功";
					}
				}
			}

		} catch (Exception e) {
			message = "同步失败，发生异常";
			e.printStackTrace();
		}

		return message;

	}

	public String outStock(String sf_id, String epp_id, String log_sn, PeProdUser user) {
		Date now = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String timestamp = sf.format(now);
		timestamp = timestamp.replaceAll(" ", "%20");
		// String access_token = "kangjia001";
		String access_token = "3897103a6066ded4439a80756447cd6f";

		SfhkCompany sc = new SfhkCompany();
		sc.setComp_name("顺丰物流");
		sc = super.getFacade().getSfhkCompanyService().getSfhkCompany(sc);
		if (sc == null) {
			return "没有找到顺丰快递公司";
		}
		// http://hkapi.st.sf-express.com:2002/app/api/index
		String url = "http://partner.sfheike.com/app/api/index?method=sfhk.order.outstorage&access_token="
				+ access_token + "&order_id=" + sf_id + "&timestamp=" + timestamp + "&logistics_id=" + sc.getComp_id()
				+ "&waybill=" + log_sn;
		//System.out.println("url--->" + url);

		String result;
		String message = "";

		try {
			result = getApiWithUrl(url);
			//System.out.println("result--->" + result);
			JSONObject list = JSONObject.fromObject(result);
			JSONArray jsons = JSONArray.fromObject(list.get("sfhk_order_outstorage_response"));
			for (Object o : jsons) {
				JSONObject jsonNode = JSONObject.fromObject(o);
				String code = jsonNode.getString("code");
				if (code.equals("0")) {
					SfhkOutStorage so1 = new SfhkOutStorage();
					so1.setSf_order_id(sf_id);
					super.getFacade().getSfhkOutStorageService().removeSfhkOutStorage(so1);

					SfhkOutStorage so = new SfhkOutStorage();
					so.setAdd_date(new Date());
					so.setSf_order_id(sf_id);
					so.setEpp_order_id(epp_id);
					so.setState(0);
					super.getFacade().getSfhkOutStorageService().createSfhkOutStorage(so);
					message = "订单出库成功";
				} else {
					SfhkOutStorage so1 = new SfhkOutStorage();
					so1.setSf_order_id(sf_id);
					super.getFacade().getSfhkOutStorageService().removeSfhkOutStorage(so1);

					SfhkOutStorage so = new SfhkOutStorage();
					so.setAdd_date(new Date());
					so.setSf_order_id(sf_id);
					so.setEpp_order_id(epp_id);
					so.setState(1);
					super.getFacade().getSfhkOutStorageService().createSfhkOutStorage(so);
					message = "订单出库失败";
				}
			}

		} catch (Exception e) {
			SfhkOutStorage so1 = new SfhkOutStorage();
			so1.setSf_order_id(sf_id);
			super.getFacade().getSfhkOutStorageService().removeSfhkOutStorage(so1);

			SfhkOutStorage so = new SfhkOutStorage();
			so.setAdd_date(new Date());
			so.setSf_order_id(sf_id);
			so.setEpp_order_id(epp_id);
			so.setState(1);
			super.getFacade().getSfhkOutStorageService().createSfhkOutStorage(so);
			message = "订单出库失败";
			e.printStackTrace();
		}

		return message;

	}

	public String syncOrder(String start_date, String end_date, String page, String pagesize, PeProdUser user) {
		Date now = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String timestamp = sf.format(now);
		timestamp = timestamp.replaceAll(" ", "%20");
		start_date = start_date + "%2000:00:00";
		end_date = end_date + "%2023:59:59";
		// String access_token = "kangjia001";//3897103a6066ded4439a80756447cd6f
		String access_token = "3897103a6066ded4439a80756447cd6f";

		String url = "http://partner.sfheike.com/app/api/index?method=sfhk.order.search&access_token=" + access_token
				+ "&timestamp=" + timestamp + "&page=" + page + "&page_size=" + pagesize + "&order_state=2&start_date="
				+ start_date + "&end_date=" + end_date;
		String result;
		int i = 0;
		int k = 0;
		Long s_now = System.currentTimeMillis();
		try {
			result = getApiWithUrl(url);
			JSONObject listTotal = JSONObject.fromObject(result);
			JSONArray jsonsTotal = JSONArray.fromObject(listTotal.get("sfhk_order_search_response"));
			String total1 = "0";
			for (Object object : jsonsTotal) {
				JSONObject jsonNode = JSONObject.fromObject(object);
				String code = jsonNode.getString("code");
				if (code.equals("0")) {
					total1 = jsonNode.getString("total");
				}

			}
			int new_page;// 50
			if (Integer.valueOf(total1) == 0) {
				new_page = 1;
			} else if (Integer.valueOf(total1) % Integer.valueOf(pagesize) != 0) {
				new_page = Integer.valueOf(total1) / Integer.valueOf(pagesize) + 1;
			} else {
				new_page = Integer.valueOf(total1) / Integer.valueOf(pagesize);
			}

			for (int j = 1; j < new_page + 1; j++) {
				url = "http://partner.sfheike.com/app/api/index?method=sfhk.order.search&access_token=" + access_token
						+ "&timestamp=" + timestamp + "&page=" + j + "&page_size=" + pagesize
						+ "&order_state=2&start_date=" + start_date + "&end_date=" + end_date;

				//System.out.println("url--->" + url);

				// 防止交易流水号重复

				result = getApiWithUrl(url);
				JSONObject list = JSONObject.fromObject(result);
				JSONArray jsons = JSONArray.fromObject(list.get("sfhk_order_search_response"));

				for (Object o : jsons) {
					JSONObject jsonNode = JSONObject.fromObject(o);
					String code = jsonNode.getString("code");
					String total = jsonNode.getString("total");
					if (code.equals("0") && Integer.valueOf(total) > 0) {
						// String tt = jsonNode.getString("timestamp");// 访问接口时间
						JSONArray order_list = JSONArray.fromObject(jsonNode.get("order_list"));

						String sf_order_ids = "";
						SfhkRelEppOrder sr = new SfhkRelEppOrder();// 已经存在的顺丰嘿客订单id
						/*
						 * List<SfhkRelEppOrder> srList = super.getFacade().getSfhkRelEppOrderService()
						 * .getSfhkRelEppOrderAndOrderIdList(sr); if (srList != null && srList.size() > 0) { sr =
						 * srList.get(0); if (sr.getMap().get("sf_order_id") != null) { sf_order_ids = (String)
						 * sr.getMap().get("sf_order_id"); } }
						 */
						List<String> ids = new ArrayList<String>();
						List<SfhkRelEppOrder> srList = super.getFacade().getSfhkRelEppOrderService()
								.getSfhkRelEppOrderList(sr);
						if (srList != null && srList.size() > 0)
							for (SfhkRelEppOrder so : srList) {
								ids.add(so.getSf_order_id());
							}
						sf_order_ids = StringUtils.join(ids, ",");

						//System.out.println("sf_order_ids--->" + sf_order_ids);

						if (order_list.size() > 0) {
							//
							PshowOrder pp = new PshowOrder();
							JSONObject orderJson = new JSONObject();
							for (Object order : order_list) {
								pp = new PshowOrder();
								PshowOrdeDetails pd = new PshowOrdeDetails();
								k++;
								orderJson = new JSONObject();
								orderJson = JSONObject.fromObject(order);
								if (sf_order_ids.contains(orderJson.getString("order_id"))) {
									continue;
								}
								String order_id = orderJson.getString("order_id");// 顺丰嘿客订单id
								String order_state = orderJson.getString("order_state");// 顺丰订单状态
								// 1待付款
								// 2
								// 已付款
								// 4已发货5确认收货

								String invoice = orderJson.getString("invoice");// 是否要发票0否1是
								String order_payment = orderJson.getString("order_payment");// 订单应付金额
								String order_remark = orderJson.getString("order_remark");// 客户订单备注
								String pay_time = orderJson.getString("pay_time");// 订单支付时间
								String vendor_id = orderJson.getString("vendor_id");// 商家id
								String invoice_type = orderJson.getString("invoice_type");// 发票类型
								String invoice_content = orderJson.getString("invoice_content");// 发票内容（家电，日常用品）
								String invoice_title = "";
								invoice_title = orderJson.getString("invoice_title");// 发票单位
								String order_start_time = orderJson.getString("order_start_time");// 下单时间
								String pay_type = orderJson.getString("pay_type");// 支付方式1在线支付2门店支付
								String order_seller_price = orderJson.getString("order_seller_price");// 订单商品总金额
								String seller_discount = orderJson.getString("seller_discount");// 折扣金额
								String order_total_price = orderJson.getString("order_total_price");// 订单总金额（可能包含运费）
								String pin = orderJson.getString("pin");// 买家在顺丰嘿客的账号
								String parent_order_id = orderJson.getString("parent_order_id");// 父订单id，退换货可能用到
								// String vendor_remark =
								// orderJson.getString("vendor_remark");// 商家备注

								JSONArray jsonsConsignee_info = JSONArray.fromObject(orderJson.get("consignee_info"));
								String buy_addr = "";// 收货人地址全称
								for (Object object : jsonsConsignee_info) {
									JSONObject jsd = JSONObject.fromObject(object);
									String province = jsd.getString("province");// 省
									String city = jsd.getString("city");// 市
									String county = jsd.getString("county");// 县
									String full_address = jsd.getString("full_address");// 详细地址
									String mobile = jsd.getString("mobile");// 手机
									String telephone = jsd.getString("telephone");// 电话
									String fullname = jsd.getString("fullname");// 姓名
									buy_addr = province + city + county + full_address;

									// 顺丰嘿客 如果是北京地区，可能是province=北京市 city= 东城区
									// county="";

									BaseProvinceListFour bf = new BaseProvinceListFour();
									pp.setBuyer_p_index(null);// 清空pindex
									if (!county.equals("")) {
										bf.getMap().put("country", county);
										bf.getMap().put("city", city);
										bf.getMap().put("province", province);
										List<BaseProvinceListFour> bfList = super.getFacade()
												.getBaseProvinceListFourService()
												.getBaseProvinceListFourByCityNameList(bf);
										if (null != bfList && bfList.size() > 0) {
											pp.setBuyer_p_index(bfList.get(0).getP_index());// 省市县行政区划编码
										}
									} else {
										if (!province.contains("市")) {
											province = province + "市";
										}
										bf.getMap().put("country", city);
										bf.getMap().put("province", province);
										List<BaseProvinceListFour> bfList = super.getFacade()
												.getBaseProvinceListFourService()
												.getBaseProvinceListFourByCityNameList(bf);
										if (null != bfList && bfList.size() > 0) {
											pp.setBuyer_p_index(bfList.get(0).getP_index());// 省市县行政区划编码
										}
									}
									pp.setBuyer_mp(mobile);
									pp.setBuyer_tel(telephone);
									pp.setBuyer_name(fullname);
									pp.setBuyer_addr(buy_addr);// 存全部地址信息，防止匹配不到pindex，客服可以手动修改
								}
								// pp.setRemark(vendor_remark);
								pp.setIs_del(0);
								pp.setState(10);// 已确认付款，待财务审核
								pp.setUuid(UUID.randomUUID().toString());
								String trade_index = DateFormatUtils.format(now, "yyMMddHHmmss")
										+ StringUtils.substring(String.valueOf(now.getTime() + k), 7);
								pp.setTrade_index(trade_index);
								pp.setLogistic_sn(order_remark + "顺丰嘿客下单人是：" + pin);// 把下单人备注到客户备注栏
								pp.setSfhk_order_name(pin);
								pp.setPay_way(8);// 支付方式
								pp.setPay_date(sf.parse(pay_time));
								pp.setOrder_from(2);
								pp.setOrder_type(0);
								pp.setOrder_user_id(128008L);// 默认一个下单人，写死
								// (测试)4616L
								pp.setOrder_user_name("顺丰嘿客商城");// 测试（不会念经的小沙弥）

								pp.setDeliver_time(1);
								pp.setDeliver_is_call(1);
								// order_payment
								pp.setPay_price(new BigDecimal(order_total_price));
								pp.setTotal_price(new BigDecimal(order_total_price));
								if (!"".equals(seller_discount)) {
									pp.setDedu_price(new BigDecimal(seller_discount));
								} else {
									pp.setDedu_price(new BigDecimal("0.00"));
								}
								pp.setIntegral(new BigDecimal("0.00"));
								pp.setPay_integral(new BigDecimal("0.00"));
								pp.setIs_deduction(1);
								// 是否索要发票
								if (invoice.equals("1")) {
									pp.setBill_is_add(1);
								} else {
									pp.setBill_is_add(1);// 不管顺丰嘿客传什么，康佳这边都要开发票
								}
								// 发票类型
								if (invoice_type.equals("个人")) {
									pp.setBill_head(0);
									pp.setBill_type(0);
								} else if (invoice_type.equals("单位")) {
									pp.setBill_head(1);
									pp.setBill_type(1);
								} else {
									pp.setBill_head(0);
									pp.setBill_type(0);
								}
								// 发票单位
								pp.setBill_company(invoice_title);
								pp.setAdd_date(sf.parse(order_start_time));// 取顺丰嘿客下单时间

								Long id = super.getFacade().getPshowOrderService().createPshowOrder(pp);

								SfhkRelEppOrder sfe = new SfhkRelEppOrder();
								sfe.setAdd_date(new Date());
								sfe.setAdd_user_id(user.getId());
								sfe.setReal_name(user.getReal_name());
								sfe.setBuy_addr(buy_addr);
								sfe.setEpp_order_id(String.valueOf(id));
								sfe.setSf_order_id(order_id);
								sfe.setPay_state(10);
								sfe.setSf_state(Integer.valueOf(order_state));
								super.getFacade().getSfhkRelEppOrderService().createSfhkRelEppOrder(sfe);

								JSONArray jsondetails = JSONArray.fromObject(orderJson.get("product_info_list"));
								for (Object js : jsondetails) {
									JSONObject jsd = JSONObject.fromObject(js);
									String product_id = jsd.getString("product_id");// 嘿客商品id
									String sfhk_price = jsd.getString("sfhk_price");// 嘿客价
									String quantity = jsd.getString("quantity");// 数量
									String product_no = jsd.getString("product_no");// 商户商品编码，关联我们系统商品id
									String sku_id = jsd.getString("sku_id");// sku_id
									// 用来关联我们系统里的商品id

									// 根据product_id
									// ,sku_id查找sku_list,和对应的outer_sku_id
									/*
									 * String url2 =
									 * "http://hkapi.st.sf-express.com:2002/app/api/index?method=sfhk.item.get&access_token=kangjia001&product_id="
									 * + product_id + "&timestamp=" + timestamp; String result2; result2 =
									 * getApiWithUrl(url2); JSONObject list2 = JSONObject.fromObject(result2); JSONArray
									 * jsons2 =JSONArray.fromObject(list2.get( "sfhk_item_get_response")); String
									 * out_sku_id = ""; for (Object o2 : jsons2) { JSONObject jsonNode2 =
									 * JSONObject.fromObject(o2); String code2 = jsonNode2.getString("code"); if
									 * (code2.equals("0")) { JSONArray product_info = JSONArray .fromObject(jsonNode2
									 * .get("product_info")); for (Object object : product_info) { JSONObject ob =
									 * JSONObject.fromObject(object); JSONArray sku_list =
									 * JSONArray.fromObject(ob.get("sku_list")); for (Object oo : sku_list) { JSONObject
									 * jn = JSONObject.fromObject(oo); if (jn.getString("sku_id").equals(sku_id)) {
									 * out_sku_id = jn.getString("outer_sku_id"); break; } } } } }
									 */

									if (GenericValidator.isLong(product_no)) {
										KonkaBcompPd kp = new KonkaBcompPd();
										kp.setId(Long.valueOf(product_no));
										kp = super.getFacade().getKonkaBcompPdService().getKonkaBcompPd(kp);
										if (kp != null) {
											pd.setIntegral(new BigDecimal("0.00"));
											pd.setPd_id(kp.getId());
											pd.setPd_name(kp.getPd_name());
											pd.setNum(Long.valueOf(quantity));
											pd.setPrice(new BigDecimal(sfhk_price));
											pd.setTotal_price(new BigDecimal(sfhk_price).multiply(new BigDecimal(
													quantity)));
											pd.setOrder_id(id);
											pd.setState(0);
											pd.setPay_integral(new BigDecimal("0.00"));
											super.getFacade().getPshowOrdeDetailsService().createPshowOrdeDetails(pd);
										}

									}

								}
								i++;
							}
						}
					}

				}
			}
			Long e_now = System.currentTimeMillis();
			SyncRecodeSfhk ss = new SyncRecodeSfhk();
			ss.setAdd_date(new Date());
			ss.setAdd_user_id(user.getId());
			ss.setStart_date(start_date.replaceAll("%20", " "));
			ss.setEnd_date(end_date.replaceAll("%20", " "));
			ss.setNeed_date(String.valueOf(e_now - s_now));
			ss.setReal_name(user.getReal_name());
			ss.setSync_count(Long.valueOf(i));
			ss.setSync_state(0);
			super.getFacade().getSyncRecodeSfhkService().createSyncRecodeSfhk(ss);

		} catch (Exception e) {
			Long e_now = System.currentTimeMillis();
			SyncRecodeSfhk ss = new SyncRecodeSfhk();
			ss.setAdd_date(new Date());
			ss.setAdd_user_id(user.getId());
			ss.setStart_date(start_date.replaceAll("%20", " "));
			ss.setEnd_date(end_date.replaceAll("%20", " "));
			ss.setNeed_date(String.valueOf(e_now - s_now));
			ss.setReal_name(user.getReal_name());
			ss.setSync_count(Long.valueOf(i));
			ss.setSync_state(1);
			super.getFacade().getSyncRecodeSfhkService().createSyncRecodeSfhk(ss);
			e.printStackTrace();
		}

		String message = "";

		message = "这次一共更新了" + i + "条数据！";
		//System.out.println("这次更新了多少条数据：------------》" + message);
		return message;
	}

	public String syncSfComp(PeProdUser user) {
		Date now = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String timestamp = sf.format(now);
		timestamp = timestamp.replaceAll(" ", "%20");
		// String access_token = "kangjia001";
		String access_token = "3897103a6066ded4439a80756447cd6f";
		String url = "http://partner.sfheike.com/app/api/index?method=sfhk.logistics.company.get&access_token="
				+ access_token + "&timestamp=" + timestamp;
		String result;
		String message = "";

		List<String> ids = new ArrayList<String>();
		SfhkCompany sr = new SfhkCompany();// 已经存在的物流公司
		List<SfhkCompany> srList = super.getFacade().getSfhkCompanyService().getSfhkCompanyList(sr);
		if (srList != null && srList.size() > 0)
			for (SfhkCompany ss : srList) {
				ids.add(ss.getComp_id());
			}

		try {
			result = getApiWithUrl(url);
			JSONObject list = JSONObject.fromObject(result);
			JSONArray jsons = JSONArray.fromObject(list.get("sfhk_logistics_company_get_response"));
			for (Object o : jsons) {
				JSONObject jsonNode = JSONObject.fromObject(o);
				String code = jsonNode.getString("code");
				if (code.equals("0")) {
					JSONArray logistics_company_list = JSONArray.fromObject(jsonNode.get("logistics_company_list"));
					for (Object object : logistics_company_list) {
						JSONObject ob = JSONObject.fromObject(object);
						if (ids.contains(ob.getString("id"))) {
							continue;
						}
						SfhkCompany entity = new SfhkCompany();
						entity.setAdd_date(new Date());
						entity.setAdd_user_id(user.getId());
						entity.setComp_id(ob.getString("id"));
						entity.setComp_name(ob.getString("name"));
						super.getFacade().getSfhkCompanyService().createSfhkCompany(entity);
						message = "同步成功";
					}
				}
			}

		} catch (Exception e) {
			message = "同步失败，发生异常";
			e.printStackTrace();
		}

		return message;

	}

	public String outStockNew(String sf_id, String epp_id, String log_sn, PeProdUser user) {
		Date now = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String timestamp = sf.format(now);
		timestamp = timestamp.replaceAll(" ", "%20");
		// String access_token = "kangjia001";
		String access_token = "3897103a6066ded4439a80756447cd6f";

		SfhkCompany sc = new SfhkCompany();
		sc.setComp_name("顺丰物流");
		sc = super.getFacade().getSfhkCompanyService().getSfhkCompany(sc);
		if (sc == null) {
			return "没有找到顺丰快递公司";
		}
		// http://hkapi.st.sf-express.com:2002/app/api/index
		String url = "http://partner.sfheike.com/app/api/index?method=sfhk.order.outstorage&access_token="
				+ access_token + "&order_id=" + sf_id + "&timestamp=" + timestamp + "&logistics_id=" + sc.getComp_id()
				+ "&waybill=" + log_sn;
		//System.out.println("url--->" + url);

		String result;
		String message = "";

		try {
			result = getApiWithUrl(url);
			//System.out.println("result--->" + result);
			JSONObject list = JSONObject.fromObject(result);
			JSONArray jsons = JSONArray.fromObject(list.get("sfhk_order_outstorage_response"));
			for (Object o : jsons) {
				JSONObject jsonNode = JSONObject.fromObject(o);
				String code = jsonNode.getString("code");
				if (code.equals("0")) {
					SfhkOutStorage so1 = new SfhkOutStorage();
					so1.setSf_order_id(sf_id);
					super.getFacade().getSfhkOutStorageService().removeSfhkOutStorage(so1);

					SfhkOutStorage so = new SfhkOutStorage();
					so.setAdd_date(new Date());
					so.setSf_order_id(sf_id);
					so.setEpp_order_id(epp_id);
					so.setState(0);
					super.getFacade().getSfhkOutStorageService().createSfhkOutStorage(so);
					message = "订单出库成功";
				} else {
					SfhkOutStorage so1 = new SfhkOutStorage();
					so1.setSf_order_id(sf_id);
					super.getFacade().getSfhkOutStorageService().removeSfhkOutStorage(so1);

					SfhkOutStorage so = new SfhkOutStorage();
					so.setAdd_date(new Date());
					so.setSf_order_id(sf_id);
					so.setEpp_order_id(epp_id);
					so.setState(1);
					super.getFacade().getSfhkOutStorageService().createSfhkOutStorage(so);
					message = "订单出库失败";
				}
			}

		} catch (Exception e) {
			SfhkOutStorage so1 = new SfhkOutStorage();
			so1.setSf_order_id(sf_id);
			super.getFacade().getSfhkOutStorageService().removeSfhkOutStorage(so1);

			SfhkOutStorage so = new SfhkOutStorage();
			so.setAdd_date(new Date());
			so.setSf_order_id(sf_id);
			so.setEpp_order_id(epp_id);
			so.setState(1);
			super.getFacade().getSfhkOutStorageService().createSfhkOutStorage(so);
			message = "订单出库失败";
			e.printStackTrace();
		}

		return message;

	}

}