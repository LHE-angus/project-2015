package com.sf.integration.warehouse;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.axis2.AxisFault;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.ebiz.mmt.domain.EcOrderExpressInfo;

public class TestSfService {

	/**
	 * @param args
	 * @throws ClassNotFoundException
	 * @throws AxisFault
	 * @throws DocumentException
	 */
	public static void main(String[] args) throws AxisFault, ClassNotFoundException, DocumentException {
		TestSfService t = new TestSfService();

		// 仓储接口测试
		// t.spmldr();
		// t.xsdd("8888888888");
		// t.xsddzt("8888888888");
		// t.xsddmx("8888888888");
		// t.xsddfy("887994323412");

		// BSP接口测试
		// t.xsddBsp();// 下订单
		// t.orderBind();// 订单绑定
		// t.xsddfy("588143472239");// 订单运费
		// t.lycx();// 路由查询
		// t.xmlParse();

		// Order order = new Order();
		// order.setPay_way("0");
		// order.setValue("255555");
		// order.setValue2("");
		// order.setValue3("");
		// order.setValue4("");
		// //System.out.println(order.toXml());
		// t.xmlParse2();
		// t.xml3Parse();
		// t.aa();
		t.testXdd();
		// t.testLycx();
		// t.testYf("887994405513");
		// t.testDdcx();

	}

	public String spmldr() {
		SfOrderService sf = new SfOrderService();
		String spmlOpName = "wmsMerchantCatalogService";
		Spml spml = new Spml();
		spml.setCheckword("01f18980363f40e48416464baf4cc7c0");
		spml.setCompany("KONKA");
		spml.setItem("10000189699");
		spml.setDescription("55英寸 窄边网络智能3D电视 (黑色) 货源不多!");
		spml.setStorage_template("个");
		spml.setSequence_1("1");
		spml.setConversion_qty_1("1");
		spml.setHeight_1("1");
		spml.setWidth_1("1");
		spml.setLength_1("1");
		spml.setWeight_1("1");
		spml.setQuantity_um_1("个");
		spml.setWeight_um_1("KG");
		spml.setDimension_um_1("CM");
		spml.setTreat_as_loose_1("Y");
		spml.setLot_controlled("否");
		spml.setSerial_num_track_inbound("否");
		spml.setSerial_num_track_outbound("否");
		spml.setBom_action("否");
		spml.setInterface_action_code("NEW");
		String spmlXml = spml.toXml();
		String returnXml = sf.sfWebService(spmlXml, spmlOpName);
		//System.out.println("商品目录导入返回xml------>>>" + returnXml);
		return returnXml;
	}

	public String xsdd(String orderid, String item) {
		SfOrderService sf = new SfOrderService();
		String sxddOpName = "wmsSailOrderService";
		OrderData orderData = new OrderData();
		orderData.setCheckword("01f18980363f40e48416464baf4cc7c0");
		orderData.setCompany("KONKA");
		orderData.setWarehouse("755DCA");
		orderData.setShop_name("宝路联合旗舰店");
		orderData.setErp_order(orderid);
		orderData.setOrder_date("2013-12-02 15:37:37");
		orderData.setShip_from_attention_to("李娜珊");
		orderData.setShip_from_address("南安市诗山镇林埔口工业区53号");
		orderData.setShip_from_postal_code("362200");
		orderData.setShip_from_phone_num("18065575018");
		orderData.setShip_from_tel_num("0595-82905852");
		orderData.setCarrier("顺丰速运");
		orderData.setShip_to_name("李添椿");
		orderData.setShip_to_attention_to("李添椿");
		orderData.setShip_to_country("中国");
		orderData.setShip_to_province("福建省");
		orderData.setShip_to_city("泉州市");
		orderData.setShip_to_area("德化县");
		orderData.setShip_to_address("德化瓷国明珠酒店");
		orderData.setShip_to_postal_code("362311");
		orderData.setShip_to_phone_num("15715986690");
		orderData.setOrder_total_amount("222.0");
		orderData.setShip_to_tel_num("15715986690");
		orderData.setMonthly_account("7550144315");
		OrderDataDetail d = new OrderDataDetail();
		List<OrderDataDetail> detailList = new ArrayList<OrderDataDetail>();
		d.setErp_order_line_num("1");
		d.setItem(item);
		d.setItem_name("55英寸 窄边网络智能3D电视 (黑色) 货源不多!");
		d.setUom("个");
		d.setQty("2");
		d.setItem_price("55.0");
		detailList.add(d);
		orderData.setOrderDataDetailList(detailList);

		String orderDataXml = orderData.toXml();
		// //System.out.println(orderDataXml);
		String returnXml = sf.sfWebService(orderDataXml, sxddOpName);
		//System.out.println("销售订单导入返回xml------>>>" + returnXml);
		return returnXml;
	}

	public String xsddzt(String orderid) {
		SfOrderService sf = new SfOrderService();
		String sxddmxOpName = "wmsSailOrderStatusQueryService";
		OrderData orderData = new OrderData();
		orderData.setCheckword("01f18980363f40e48416464baf4cc7c0");
		orderData.setCompany("KONKA");
		orderData.setErp_order(orderid);
		String orderMxXml = orderData.todxztXml();
		String returnXml = sf.sfWebService(orderMxXml, sxddmxOpName);
		//System.out.println(returnXml);
		return returnXml;
	}

	public String xsddmx(String orderid) {
		SfOrderService sf = new SfOrderService();
		String sxddmxOpName = "wmsSailOrderQueryService";
		OrderData orderData = new OrderData();
		orderData.setCheckword("01f18980363f40e48416464baf4cc7c0");
		orderData.setCompany("KONKA");
		orderData.setWarehouse("755DCA");
		orderData.setErp_order(orderid);
		String orderMxXml = orderData.todxMxXml();
		String returnXml = "";
		returnXml = sf.sfWebService(orderMxXml, sxddmxOpName);
		//System.out.println(returnXml);
		return returnXml;
	}

	public String xsddfy(String waybill_no) {
		SfOrderService sf = new SfOrderService();
		String sxddmxOpName = "queryMailnoDetailService";
		OrderFy orderData = new OrderFy();
		orderData.setCheckword("IaCc2UOBDzmQQFq;QL8}jsJR129eJ_th");
		orderData.setCustid("7699462068");
		orderData.setMailno(waybill_no);
		String xsddfy = orderData.toXml();
		//System.out.println("xxxxxxxxxxxxx" + xsddfy);
		String returnXml = sf.sfWebService3(xsddfy, sxddmxOpName);
		//System.out.println(returnXml);
		return returnXml;
	}

	public String xsddBsp() {
		SfOrderService sf = new SfOrderService();
		String sxddmxOpName = "sfexpressService";
		Order order = new Order();
		order.setOrderid("KJJT201312111855");
		order.setExpress_type("1");
		order.setCustid("7699462068");
		order.setD_deliverycode("");
		order.setJ_shippercode("");
		order.setParcel_quantity("1");
		order.setPay_method("1");
		order.setJ_company("KONKA");
		order.setJ_contact("潘刚");
		order.setJ_tel("0755-62548568");
		order.setJ_province("广东省");
		order.setJ_city("深圳市");
		order.setJ_county("");
		order.setJ_address("新洲十一街万基商务大厦 10 楼");
		order.setD_contact("胡浩");
		order.setD_tel("0755-62548568");
		order.setD_province("广东省");
		order.setD_city("深圳市");
		order.setD_company("安徽买买提");
		order.setD_county("福田区");
		order.setD_address("新洲十一街万基商务大厦 10 楼");
		// order.setCargo("大衣");
		// order.setCargo_count("1");
		// order.setCargo_unit("件");
		// order.setCargo_weight("55.0");
		// order.setCargo_amount("1000");
		order.setCargo("");
		order.setCargo_count("");
		order.setCargo_unit("");
		order.setCargo_weight("");
		order.setCargo_amount("");
		order.setCargo_total_weight("33");
		order.setSendstarttime("");
		String xml = order.toXml();
		// //System.out.println(xml);
		String xml3 = "<Request service='OrderService' lang='zh-CN'>"
		        + "<Head>kjjt,iMrExqDcXvxV</Head>"
		        + "<Body>"
		        + "<Order orderid='KJ8888888899' express_type='1' j_company='KONKA' j_province='广东省' j_city='深圳市' j_county='福田区' j_contact='潘刚'  j_tel='0755-62548568' j_address='新洲十一街万基商务大厦10楼' d_company='安徽买买提' d_contact='胡浩' d_province='广东省' d_city='深圳市' d_county='福田区' d_tel='0755-62548568' d_address='新洲十一街万基商务大厦10楼' parcel_quantity='1' pay_method='1'>"
		        + "</Order> " + "</Body> " + "</Request>";
		// //System.out.println(xml3);

		String xml4 = "<Request service='OrderService' lang='zh-CN'><Head>kjjt,iMrExqDcXvxV</Head><Body><Order orderid='KJJT2013121115015356' express_type='1' j_company='KONKA' j_contact='潘刚' j_tel='0755-62548568' j_address='新洲十一街万基商务大厦 10 楼' d_company='安徽买买提' d_contact='胡浩' d_tel='0755-62548568' d_address='新洲十一街万基商务大厦 10 楼' parcel_quantity='1' pay_method='1' j_province='广东省' j_city='深圳市' d_province='广东省' d_city='深圳市' j_county='福田区' d_county='福田区'><OrderOption custid='7699462068' j_shippercode='755' d_deliverycode='755' cargo_total_weight='55'></OrderOption></Order></Body></Request>";
		String returnXml = sf.sfWebService2(xml, sxddmxOpName);
		//System.out.println(returnXml);
		return returnXml;
	}

	@SuppressWarnings("unchecked")
	public String lycx() throws DocumentException {
		SfOrderService sf = new SfOrderService();
		String sxddmxOpName = "sfexpressService";
		String xml5 = "<Request service='RouteService' lang='zh-CN'><Head>7699462068,IaCc2UOBDzmQQFq;QL8}jsJR129eJ_th</Head><Body><RouteRequest tracking_type='1' method_type='1' tracking_number='588157162525'></RouteRequest></Body></Request>";
		String returnXml = sf.sfWebService2(xml5, sxddmxOpName);
		//System.out.println("returnXml==========>{}" + returnXml);
		Document doc = DocumentHelper.parseText(returnXml);
		Element rootElt = doc.getRootElement();
		// //System.out.println("根节点名称：" + rootElt.getName());
		// //System.out.println(rootElt.elementTextTrim("Head"));
		Iterator<Element> iter = rootElt.elementIterator("Body");
		List<String> remarkList = new ArrayList<String>();
		while (iter.hasNext()) {
			Element em = iter.next();
			// //System.out.println(em.getName());
			Iterator<Element> iter2 = em.elementIterator("RouteResponse");
			while (iter2.hasNext()) {
				Element em2 = iter2.next();
				// //System.out.println(em2.getName());
				// //System.out.println(em2.attributeValue("mailno"));
				Iterator<Element> iter3 = em2.elementIterator("Route");
				while (iter3.hasNext()) {
					Element em3 = iter3.next();
					// //System.out.println(em3.attributeValue("remark"));
					remarkList.add(em3.attributeValue("remark"));
				}
			}

		}
		// //System.out.println("订单状态------------》{}" + remarkList.size());
		StringBuffer sb = new StringBuffer();
		String ss = "";
		if (null != remarkList && remarkList.size() > 0) {
			for (int i = 0; i < remarkList.size(); i++) {
				if (remarkList.get(i) == null || remarkList.get(i) == "") {
					continue;
				}
				sb.append(remarkList.get(i));
			}
			ss = remarkList.get(remarkList.size() - 1);
			//System.out.println("最终状态：-----------》" + ss);
		}
		//System.out.println(sb);
		if (sb.indexOf("已签收") != -1) {
			// //System.out.println("邮件已签收！！！！");
		}

		return returnXml;
	}

	public String orderBind() throws AxisFault, ClassNotFoundException {
		SfOrderService sf = new SfOrderService();
		String sxddmxOpName = "orderMailBindingService";
		String xml3 = "<tporderBinding>" + "<orderid>BT13120900000999</orderid>" + "<custid>7699462068</custid>"
		        + "<mailno>887986219805</mailno>" + "<checkword>iMrExqDcXvxV</checkword>" + "</tporderBinding>";

		String returnXml = sf.sfWebService2(xml3, sxddmxOpName);
		//System.out.println(returnXml);
		return returnXml;
	}

	public String xmlParse() throws DocumentException {
		String xml = "<Response service='OrderService'><Head>OK</Head><Body><OrderResponse filter_result='2' destcode='755' mailno='887980159093' origincode='755' orderid='KJ8888888899'/></Body></Response>";
		Document doc = DocumentHelper.parseText(xml);
		Element rootElt = doc.getRootElement();
		// //System.out.println("根节点名称：" + rootElt.getName());
		// //System.out.println(rootElt.elementTextTrim("Head"));
		Iterator<Element> iter = rootElt.elementIterator("Body");
		while (iter.hasNext()) {
			Element iter2 = iter.next();
			// //System.out.println(iter2.getName());
			Iterator<Element> iter3 = iter2.elementIterator("OrderResponse");
			while (iter3.hasNext()) {
				Element iter4 = iter3.next();
				// //System.out.println(iter4.getName());
				// //System.out.println(iter4.attributeValue("mailno"));
			}
		}
		return null;
	}

	public String xmlParse2() throws DocumentException {
		String xml = "<?xml version='1.0' encoding='UTF-8'?><Response service='RouteService'><Head>OK</Head><Body><RouteResponse mailno='113740178251'><Route remark='已收件' accept_time='2014-01-15 12:17:03' accept_address='东莞市' opcode='50'/></RouteResponse></Body></Response>";
		List<HashMap> list = new ArrayList<HashMap>();
		Document doc = DocumentHelper.parseText(xml);
		Element rootElt = doc.getRootElement();
		Iterator<Element> iter = rootElt.elementIterator("Body");
		while (iter.hasNext()) {
			Element em = iter.next();
			Iterator<Element> iter2 = em.elementIterator("RouteResponse");
			while (iter2.hasNext()) {
				Element em2 = iter2.next();
				Iterator<Element> iter3 = em2.elementIterator("Route");
				while (iter3.hasNext()) {
					Element em3 = iter3.next();
					HashMap map = new HashMap();
					map.put("accept_time", em3.attributeValue("accept_time").trim());
					map.put("remark", em3.attributeValue("remark").replaceAll(" ", "").replaceAll(" ", "").trim());
					list.add(map);
				}
			}

		}
		//System.out.println(list.size());
		return null;
	}

	public String xml3Parse() throws DocumentException {
		String xml = "<?xml version='1.0' encoding='utf-8'?><SubmitResult xmlns='http://106.ihuyi.com/'><code>2</code><msg>提交成功</msg><smsid>9872484</smsid></SubmitResult>";
		Document doc = DocumentHelper.parseText(xml);
		Element rootElt = doc.getRootElement();
		Iterator<Element> iter = rootElt.elementIterator("code");
		while (iter.hasNext()) {
			Element iter2 = iter.next();
			//System.out.println(iter2.getText());
		}
		return null;
	}

	// private static final String ENCODING = "GBK";

	// public static String getApiWithUrl(String url) throws Exception {
	// InputStream is = null;
	// try {
	// HttpClient client = new DefaultHttpClient();
	// HttpGet httpGet = new HttpGet(url);
	// HttpResponse response = client.execute(httpGet);
	// is = response.getEntity().getContent();
	// BufferedReader reader = new BufferedReader(new InputStreamReader(is,
	// ENCODING));
	// StringBuffer result = new StringBuffer();
	// String string = null;
	// if (null != (string = reader.readLine())) {
	// result.append(string);
	// }
	//
	// // //System.out.println("result: " + result.toString());
	// return result.toString();
	// } finally {
	// // close stream here
	// is.close();
	// }
	// }

	public String aa() {
		String returnXml = "<?xml version='1.0' encoding='UTF-8'?><Response service='OrderService'><Head>OK</Head><Body><OrderResponse filter_result='2' destcode='775' mailno='588319443429' origincode='769' orderid='140704154835115058'/></Body></Response>";
		String ss = "";
		try {
			Document doc = DocumentHelper.parseText(returnXml);
			Element rootElt = doc.getRootElement();
			if (rootElt.elementTextTrim("Head").equalsIgnoreCase("OK")) {
				Iterator<Element> iter = rootElt.elementIterator("Body");
				while (iter.hasNext()) {
					Element iter2 = iter.next();
					Iterator<Element> iter3 = iter2.elementIterator("OrderResponse");
					while (iter3.hasNext()) {
						Element iter4 = iter3.next();
						if (StringUtils.isNotBlank(iter4.attributeValue("mailno"))) {
							EcOrderExpressInfo ex = new EcOrderExpressInfo();
							// ex.setTrade_index(p.getTrade_index());
							// ex.setExpress_id(p.getExpress_id());
							// ex.setExpress_name(ee.getExpress_name());
							// String[] sn =
							// iter4.attributeValue("mailno").split(",");
							// ex.setLogistic_sn(sn[0]);
							ex.setLogistic_sn(iter4.attributeValue("mailno"));
							ex.setOrder_from(iter4.attributeValue("origincode"));
							ex.setOrder_to(iter4.attributeValue("destcode"));
							ex.setAdd_date(new Date());
							// super.getFacade().getEcOrderExpressInfoService().createEcOrderExpressInfo(ex);
							//System.out.println("订单入顺丰成功！！！");
							ss = "1";
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("报错啦！！" + e);
			ss = "0" + returnXml + e;
		}
		//System.out.println("sssssssssssss" + ss);

		return null;
	}

	// 测试下订单
	public String testXdd() {
		// 444402907746 ..444402910101
		SfOrderService sf = new SfOrderService();
		String sxddmxOpName = "sfexpressService";
		Order order = new Order();
		order.setOrderid("KJJT201312111855");
		order.setExpress_type("1");
		order.setCustid("7699462068");
		order.setD_deliverycode("");
		order.setJ_shippercode("");
		order.setParcel_quantity("1");
		order.setPay_method("1");
		order.setJ_company("KONKA");
		order.setJ_contact("潘刚");
		order.setJ_tel("0755-62548568");
		order.setJ_province("广东省");
		order.setJ_city("深圳市");
		order.setJ_county("");
		order.setJ_address("新洲十一街万基商务大厦 10 楼");
		order.setD_contact("胡浩");
		order.setD_tel("0755-62548568");
		order.setD_province("广东省");
		order.setD_city("深圳市");
		order.setD_company("安徽买买提");
		order.setD_county("福田区");
		order.setD_address("新洲十一街万基商务大厦 10 楼");
		// order.setCargo("大衣");
		// order.setCargo_count("1");
		// order.setCargo_unit("件");
		// order.setCargo_weight("55.0");
		// order.setCargo_amount("1000");
		order.setCargo("");
		order.setCargo_count("");
		order.setCargo_unit("");
		order.setCargo_weight("");
		order.setCargo_amount("");
		order.setCargo_total_weight("33");
		order.setSendstarttime("");
		String xml = order.toXml();
		// //System.out.println(xml);
		String xml3 = "<Request service='OrderService' lang='zh-CN'>"
		        + "<Head>KJJTKH,oLoqBeDPX8ckxalf</Head>"
		        + "<Body>"
		        + "<Order orderid='kj3333333' express_type='2' j_company='KONKA' j_province='广东省' j_city='深圳市' j_county='福田区' j_contact='潘刚'  j_tel='0755-62548568' j_address='新洲十一街万基商务大厦10楼' d_company='安徽买买提' d_contact='胡浩' d_province='广东省' d_city='深圳市' d_county='福田区' d_tel='0755-62548568' d_address='新洲十一街万基商务大厦10楼' parcel_quantity='1' custid='4512483409' pay_method='1'>"
		        + "<Cargo name='康佳彩电' count='2'></Cargo>"
		        // + "<AddedService  name='INSURE' value='200'>";
		        // xml3 += "</AddedService>"
		        + "</Order> " + "</Body> " + "</Request>";
		//System.out.println(xml3);

		// String xml4 =
		// "<Request service='OrderService' lang='zh-CN'><Head>kjjt,iMrExqDcXvxV</Head><Body><Order orderid='KJJT2013121115015356' express_type='1' j_company='KONKA' j_contact='潘刚' j_tel='0755-62548568' j_address='新洲十一街万基商务大厦 10 楼' d_company='安徽买买提' d_contact='胡浩' d_tel='0755-62548568' d_address='新洲十一街万基商务大厦 10 楼' parcel_quantity='1' pay_method='1' j_province='广东省' j_city='深圳市' d_province='广东省' d_city='深圳市' j_county='福田区' d_county='福田区'><OrderOption custid='7699462068' j_shippercode='755' d_deliverycode='755' cargo_total_weight='55'></OrderOption></Order></Body></Request>";
		String returnXml = sf.sfWebService5(xml3, sxddmxOpName);
		//System.out.println(returnXml);
		return returnXml;
	}

	// 测试路由查询
	public String testLycx() throws DocumentException {
		// 运单号444402901555
		SfOrderService sf = new SfOrderService();
		String sxddmxOpName = "sfexpressService";
		String xml5 = "<Request service='RouteService' lang='zh-CN'><Head>KJJTKH,oLoqBeDPX8ckxalf</Head><Body><RouteRequest tracking_type='1' method_type='1' tracking_number='444402901555'></RouteRequest></Body></Request>";
		String returnXml = sf.sfWebService5(xml5, sxddmxOpName);
		//System.out.println("returnXml==========>{}" + returnXml);

		return returnXml;
	}

	// 测试运费查询
	public String testYf(String waybill_no) {
		SfOrderService sf = new SfOrderService();
		String sxddmxOpName = "queryMailnoDetailService";
		OrderFy orderData = new OrderFy();
		orderData.setCheckword("oLoqBeDPX8ckxalf");
		orderData.setCustid("4512483409");
		orderData.setMailno(waybill_no);
		String xsddfy = orderData.toXml();
		//System.out.println("xxxxxxxxxxxxx" + xsddfy);
		String returnXml = sf.sfWebService6(xsddfy, sxddmxOpName);
		//System.out.println(returnXml);
		return returnXml;
	}

	// 测试订单查询，根据订单号 查询运单号
	public String testDdcx() {
		SfOrderService sf = new SfOrderService();
		String sxddmxOpName = "sfexpressService";
		// String xml5 =
		// "<Request service='OrderSearchService' lang='zh-CN'><Head>KJJTKH,oLoqBeDPX8ckxalf</Head><Body><OrderSearch orderid='kj6666666' /></Body></Request>";
		OrderCx order = new OrderCx();
		order.setCustid("KJJTKH");
		order.setCheckword("oLoqBeDPX8ckxalf");
		order.setOrderid("kj6666666");
		String xml5 = order.toXml();
		//System.out.println("xxxxxxxxxxxxx" + xml5);
		String returnXml = sf.sfWebService5(xml5, sxddmxOpName);
		//System.out.println(returnXml);
		return returnXml;
	}
}
