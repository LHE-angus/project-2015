package com.sf.integration.warehouse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.namespace.QName;

import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * @author TUDP
 * @version 2013-11-25
 * @description 顺丰接口 测试
 */
public class SfOrderService {

	public SfOrderService() {
		super();
	}

	private RPCServiceClient serviceClient;

	private Options options;

	private EndpointReference targetEPR;

	public SfOrderService(String endpoint) throws AxisFault {
		serviceClient = new RPCServiceClient();
		options = serviceClient.getOptions();
		targetEPR = new EndpointReference(endpoint);
		options.setTo(targetEPR);
	}

	@SuppressWarnings("unchecked")
	public Object[] invokeOp(String targetNamespace, String opName, Object[] opArgs, Class[] opReturnType)
	        throws AxisFault, ClassNotFoundException {
		QName opQName = new QName(targetNamespace, opName);
		return serviceClient.invokeBlocking(opQName, opArgs, opReturnType);
	}

	/**
	 * @param args
	 * @throws ClassNotFoundException
	 * @throws DocumentException
	 * @throws IOException
	 */
	@SuppressWarnings( { "unchecked" })
	public static void main(String[] args) throws ClassNotFoundException, DocumentException, IOException {
		final String endPointReference = "http://219.134.187.154:9154/bsp-wms/ws/WarehouseService?wsdl";
		final String targetNamespace = "http://service.warehouse.integration.sf.com/";
		SfOrderService client = new SfOrderService(endPointReference);

		OrderData o = new OrderData();
		OrderDataDetail d = new OrderDataDetail();
		List<OrderDataDetail> detailList = new ArrayList<OrderDataDetail>();
		d.setErp_order_line_num("1.0");
		d.setItem("kk10001");
		d.setItem_name("kk10001");
		d.setUom("枚");
		d.setQty("3.0");
		detailList.add(d);
		o.setOrderDataDetailList(detailList);

		@SuppressWarnings("unused")
		String opName = "wmsSailOrderService";// 销售订单
		@SuppressWarnings("unused")
		String opName2 = "wmsMerchantCatalogService";// 商品目录入库
		@SuppressWarnings("unused")
		String opName3 = "wmsSailOrderStatusQueryService";// 订单状态查询
		String opName4 = "wmsSailOrderQueryService";// 订单明细

		// SAXReader reader = new SAXReader();
		// Document document = reader.read(new File("D:\\33.xml"));
		// String ss = document.asXML();
		// Document doc = DocumentHelper.parseText(ss);
		// OutputFormat format = new OutputFormat("    ", true);
		// format.setEncoding("UTF-8");
		// StringWriter out = new StringWriter();
		// XMLWriter xmlWriter = new XMLWriter(out, format);
		// xmlWriter.write(doc);
		// xmlWriter.flush();
		// String s = out.toString();
		// //System.out.println(s);

		@SuppressWarnings("unused")
		String xml = "<wmsSailOrderRequest>" + "<checkword>01f18980363f40e48416464baf4cc7c0</checkword>" + "<header>"
		        + "<company>KONKA</company>" + "<warehouse>755DCA</warehouse> " + "<shop_name>宝路联合旗舰店</shop_name>"
		        + "<erp_order>2013092115373766</erp_order>" + "<order_date>2013-09-21 15:37:37</order_date>"
		        + "<ship_from_attention_to>李娜珊</ship_from_attention_to>"
		        + "<ship_from_address>南安市诗山镇林埔口工业区53号</ship_from_address>"
		        + "<ship_from_postal_code>362200</ship_from_postal_code>"
		        + "<ship_from_phone_num>18065575018</ship_from_phone_num>"
		        + "<ship_from_tel_num>0595-82905852</ship_from_tel_num>" + "<carrier>顺丰速运</carrier>"
		        + "<ship_to_name>李添椿</ship_to_name>" + "<ship_to_attention_to>李添椿</ship_to_attention_to>"
		        + "<ship_to_country>中国</ship_to_country>" + "<ship_to_province>福建省</ship_to_province>"
		        + "<ship_to_city>泉州市</ship_to_city>" + "<ship_to_area>德化县</ship_to_area>"
		        + "<ship_to_address>德化瓷国明珠酒店</ship_to_address>" + "<ship_to_postal_code>362311</ship_to_postal_code>"
		        + "<ship_to_phone_num>15715986690</ship_to_phone_num>"
		        + "<order_total_amount>222.0</order_total_amount>" + "<ship_to_tel_num>15715986690</ship_to_tel_num>"
		        + "<monthly_account>7550144315</monthly_account>" + "</header>" + "<detailList>" + "<item>"
		        + "<erp_order_line_num>1</erp_order_line_num>" + "<item>666d888</item>"
		        + "<item_name>集运商品1</item_name>" + "<uom>个</uom>" + " <qty>2</qty>" + "<item_price>55.0</item_price>"
		        + "</item>" + "</detailList>" + "</wmsSailOrderRequest>";

		String xml2 = "<wmsMerchantCatalogRequest>" + "<checkword>01f18980363f40e48416464baf4cc7c0</checkword>"
		        + "<company>KONKA</company>" + "<item>666d888</item>" + "<description>集运商品1</description>"
		        + "<storage_template>个</storage_template>" + "<sequence_1>1</sequence_1>"
		        + "<conversion_qty_1>1</conversion_qty_1>" + "<height_1>1</height_1>" + "<width_1>1</width_1>"
		        + "<length_1>1</length_1>" + "<weight_1>0.25</weight_1>" + "<quantity_um_1>个</quantity_um_1>"
		        + "<weight_um_1>KG</weight_um_1>" + "<dimension_um_1>CM</dimension_um_1>"
		        + "<treat_as_loose_1>Y</treat_as_loose_1>" + "<lot_controlled>否</lot_controlled>"
		        + "<lot_template></lot_template>" + "	<serial_num_track_inbound>否</serial_num_track_inbound>"
		        + "<serial_num_track_outbound>否</serial_num_track_outbound>" + "<bom_action>否</bom_action>"
		        + "<interface_action_code>SAVE</interface_action_code>" + "</wmsMerchantCatalogRequest>";

		String xml3 = "<wmsSailOrderStatusQueryRequest>" + "<checkword>01f18980363f40e48416464baf4cc7c0</checkword>"
		        + "<company>KONKA</company>" + "<orderid>2013092115373766</orderid>" + "<waybillno></waybillno>"
		        + "</wmsSailOrderStatusQueryRequest>";

		String xml4 = "<wmsSailOrderQueryRequest>" + "<checkword>01f18980363f40e48416464baf4cc7c0</checkword>"
		        + "<company>KONKA</company>" + "<orderid>2013092115373766</orderid>" + "<warehouse>755DCA</warehouse> "
		        + "<item></item> " + "</wmsSailOrderQueryRequest>";

		Object[] opArgs = new Object[] { xml4 };
		// //System.out.println(xml);

		Class[] opReturnType = new Class[] { String.class };
		client.options.setManageSession(true);
		Object[] response = client.invokeOp(targetNamespace, opName4, opArgs, opReturnType);

		// //System.out.println(((String) response[0]));

		Document doc = DocumentHelper.parseText((String) response[0]);
		Element rootElt = doc.getRootElement(); // 获取根节点
		// //System.out.println("根节点：" + rootElt.getName()); // 拿到根节点的名称
		// String result = rootElt.elementTextTrim("result"); // 获取result节点的文本值
		// //System.out.println(result);
		Iterator iter = rootElt.elementIterator("order");
		while (iter.hasNext()) {
			Element order = (Element) iter.next();
			// //System.out.println(order);
			Iterator steps = order.elementIterator("steps");
			while (steps.hasNext()) {
				Element step = (Element) steps.next();
				// //System.out.println(step);
				Iterator steps2 = step.elementIterator("step");
				while (steps2.hasNext()) {
					Element step3 = (Element) steps2.next();
					// //System.out.println(step3.elementTextTrim("acceptTime"));
				}
			}
		}

		String ss = "<wmsSailOrderResponse>" + "<orderid>131202192738658054</orderid>" + "<result>1</result>"
		        + "<remark>已成功导入1条订单数据！ </remark>" + "</wmsSailOrderResponse>";
		doc = DocumentHelper.parseText(ss);
		Element rootElt1 = doc.getRootElement(); // 获取根节点
		// //System.out.println("根节点：" + rootElt1.getName());
		// String result1 = rootElt1.elementTextTrim("result");
		// String orderid = rootElt1.elementTextTrim("orderid");
		// //System.out.println(result1);
		// //System.out.println(orderid);

		String ss2 = "<wmsSailOrderQueryResponse>" + " <result>1</result>" + " <header>" + "<company>KONKA</company>"
		        + " <warehouse>755DCA</warehouse>" + "<erp_order>2013092115373766</erp_order>"
		        + "<carrier>韵达快运</carrier>" + " <user_def8>10001</user_def8>" + "</header>"
		        + "</wmsSailOrderQueryResponse>";
		doc = DocumentHelper.parseText(ss2);
		Element rootElt2 = doc.getRootElement(); // 获取根节点
		// //System.out.println("根节点：" + rootElt2.getName());
		Iterator iter2 = rootElt2.elementIterator("header");
		while (iter2.hasNext()) {
			Element order = (Element) iter2.next();
			String waybill_no = order.elementTextTrim("waybill_no");
			// //System.out.println(waybill_no);
		}

		String ss3 = "<responseDetail>" + "<orders>" + " <order mailno='887994323412'>" + "<codValue>5000.0</codValue>"
		        + "<valueInsurance>4.0</valueInsurance>" + "<chargedWeight>55.0</chargedWeight>" + "<pieces>1</pieces>"
		        + "<freight>768.0</freight>" + "</order>" + "</orders>" + "</responseDetail>";
		doc = DocumentHelper.parseText(ss3);
		Element rootElt3 = doc.getRootElement(); // 获取根节点
		//System.out.println("根节点：" + rootElt3.getName());
		Iterator iter3 = rootElt3.elementIterator("orders");
		while (iter3.hasNext()) {
			Element order = (Element) iter3.next();
			Iterator iter4 = order.elementIterator("order");
			while (iter4.hasNext()) {
				Element order2 = (Element) iter4.next();
				String freight = order2.elementTextTrim("freight");
				//System.out.println(freight);
			}
		}

	}

	/**
	 * 
	 * @param endPointReference
	 * @param targetNamespace
	 * @param xml
	 *            传入的订单xml
	 * @param opName
	 * @return 调用顺丰接口方法
	 * @throws AxisFault
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings("unchecked")
	public String sfWebService(String xml, String opName) {
		Object[] response = new Object[] { "" };

		try {
			final String endPointReference = "http://219.134.187.154:9154/bsp-wms/ws/WarehouseService?wsdl";
			final String targetNamespace = "http://service.warehouse.integration.sf.com/";
			SfOrderService client = new SfOrderService(endPointReference);
			Object[] opArgs = new Object[] { xml };
			Class[] opReturnType = new Class[] { String.class };
			client.options.setManageSession(true);
			response = client.invokeOp(targetNamespace, opName, opArgs, opReturnType);
		} catch (Exception e) {

		}
		return (String) response[0];
	}

	@SuppressWarnings("unchecked")
	public String sfWebService2(String xml, String opName) {
		Object[] response = new Object[] { "" };
		String ss = "";
		try {
			// 下订单 ，路由查询 , 订单结果查询
			// final String endPointReference =
			// "http://219.134.187.132:9090/bsp-ois/ws/expressService?wsdl";//测试接口
			// final String endPointReference =
			// "http://bsp-oisp.sf-express.com/bsp-oisp/ws/expressService?wsdl";//正式接口,
			// 不限制IP
			final String endPointReference = "http://bsp-ois.sf-express.com/bsp-ois/ws/expressService?wsdl";// 正式接口

			final String targetNamespace = "http://service.expressservice.integration.sf.com/";
			SfOrderService client = new SfOrderService(endPointReference);
			Object[] opArgs = new Object[] { xml };
			Class[] opReturnType = new Class[] { String.class };
			client.options.setManageSession(true);
			response = client.invokeOp(targetNamespace, opName, opArgs, opReturnType);
		} catch (Exception e) {
			ss = "顺丰物流接口调用错误：" + e;
			return ss;
		}
		return (String) response[0];
	}

	// 运单费用用这个接口
	@SuppressWarnings("unchecked")
	public String sfWebService3(String xml, String opName) {
		Object[] response = new Object[] { "" };
		String ss = "";
		try {
			// 运单费用http://219.134.187.154:9154/bsp-oip/ws/CustomerService?wsdl
			// 正式接口http://bsp.sf-express.com/bsp-oip/ws/CustomerService?wsdl
			// 运费查询
			final String endPointReference = "http://bsp.sf-express.com/bsp-oip/ws/CustomerService?wsdl";
			final String targetNamespace = "http://service.serviceprovide.module.sf.com/";
			SfOrderService client = new SfOrderService(endPointReference);
			Object[] opArgs = new Object[] { xml };
			Class[] opReturnType = new Class[] { String.class };
			client.options.setManageSession(true);
			response = client.invokeOp(targetNamespace, opName, opArgs, opReturnType);
		} catch (Exception e) {
			return ss;
		}
		return (String) response[0];
	}

	// 短信接口
	@SuppressWarnings("unchecked")
	public String sfWebService4(String xml, String opName) {
		Object[] response = new Object[] { "" };
		String ss = "";
		try {
			final String endPointReference = "http://106.ihuyi.com/webservice/sms.php?WSDL";
			final String targetNamespace = "http://106.ihuyi.com/";
			SfOrderService client = new SfOrderService(endPointReference);
			Object[] opArgs = new Object[] { xml };
			Class[] opReturnType = new Class[] { String.class };
			client.options.setManageSession(true);
			response = client.invokeOp(targetNamespace, opName, opArgs, opReturnType);
		} catch (Exception e) {
			return ss;
		}
		return (String) response[0];
	}

	// 顺丰第二次联调测试环境接口(下单、路由路查、订单查询)
	@SuppressWarnings("unchecked")
	public String sfWebService5(String xml, String opName) {
		Object[] response = new Object[] { "" };
		String ss = "";
		try {
			final String endPointReference = "http://bsp-test.sf-express.com:9090/bsp-ois/ws/expressService?wsdl";
			final String targetNamespace = "http://service.expressservice.integration.sf.com/";
			SfOrderService client = new SfOrderService(endPointReference);
			Object[] opArgs = new Object[] { xml };
			Class[] opReturnType = new Class[] { String.class };
			client.options.setManageSession(true);
			response = client.invokeOp(targetNamespace, opName, opArgs, opReturnType);
		} catch (Exception e) {
			return ss;
		}
		return (String) response[0];
	}

	// 顺丰第二次联调测试环境接口(运费)
	@SuppressWarnings("unchecked")
	public String sfWebService6(String xml, String opName) {
		Object[] response = new Object[] { "" };
		String ss = "";
		try {
			final String endPointReference = "http://219.134.187.154:9154/bsp-oip/ws/CustomerService?wsdl";
			final String targetNamespace = "http://service.serviceprovide.module.sf.com/";
			SfOrderService client = new SfOrderService(endPointReference);
			Object[] opArgs = new Object[] { xml };
			Class[] opReturnType = new Class[] { String.class };
			client.options.setManageSession(true);
			response = client.invokeOp(targetNamespace, opName, opArgs, opReturnType);
		} catch (Exception e) {
			return ss;
		}
		return (String) response[0];
	}

}