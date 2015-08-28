package com.ebiz.mmt.web.servlet;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.GenericValidator;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.ebiz.mmt.domain.BaseProvinceListFour;
import com.ebiz.mmt.domain.EcOrderExpressInfo;
import com.ebiz.mmt.domain.PshowOrder;
import com.ebiz.mmt.service.Facade;
import com.sf.integration.warehouse.Order;
import com.sf.module.ewaybill.print.BillDrawUtil;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class SFCode2Servlet extends HttpServlet {

	private static final long serialVersionUID = -3981794330055840248L;

	public void init(ServletConfig config) throws ServletException {
	}

	public void destroy() {
		super.destroy();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
	        java.io.IOException {

		String order_id = (String) request.getParameter("order_id");
		//System.out.println("---------------->" + order_id);
		PshowOrder entity = new PshowOrder();
		if (!GenericValidator.isLong(order_id)) {
			return;
		}

		ServletContext servletContext = request.getSession().getServletContext();
		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
		Facade facade = (Facade) wac.getBean("facade");
		entity.setId(Long.valueOf(order_id));
		entity = facade.getPshowOrderService().getPshowOrder(entity);

		EcOrderExpressInfo ec = new EcOrderExpressInfo();
		if (null != entity && null != entity.getTrade_index()) {
			ec.setTrade_index(entity.getTrade_index());
			ec = facade.getEcOrderExpressInfoService().getEcOrderExpressInfo(ec);
		}

		StringBuffer fullName = new StringBuffer("");
		BaseProvinceListFour baseProvince = new BaseProvinceListFour();
		if (null != entity.getBuyer_p_index()) {
			baseProvince.setP_index(entity.getBuyer_p_index());
			List<BaseProvinceListFour> baseProvinceList = facade.getBaseProvinceListFourService()
			        .getBaseProvinceListFourParentList(baseProvince);
			for (BaseProvinceListFour t : baseProvinceList)
				fullName.append(t.getP_name());
		}

		Order order = new Order();

		response.addHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
		response.setHeader("Cache-Control", "pre-check=0,post-check=0");
		response.setDateHeader("Expires", 0);

		Map<String, Object> valueMap2 = new HashMap<String, Object>();

		valueMap2.put("waybillNo", ec == null ? "" : ec.getLogistic_sn() == null ? "" : ec.getLogistic_sn());
		valueMap2.put("custcode", order.getCustid());
		valueMap2.put("sourceZoneCode", order.getJ_shippercode());
		valueMap2.put("destZoneCode", order.getD_deliverycode());

		// 寄件人信息
		valueMap2.put("consignorContName", order.getJ_contact());
		valueMap2.put("consignorAddr", order.getJ_address());
		valueMap2.put("consignorPhone", order.getJ_tel());
		valueMap2.put("consignorCompName", order.getJ_company());

		// 收件人信息
		valueMap2.put("addresseeContName", entity.getBuyer_name());
		valueMap2.put("addresseeAddr", fullName.toString());
		valueMap2.put("addresseePhone", entity.getBuyer_mp());
		valueMap2.put("addresseeCompName", "");

		// 合并寄件人信息
		String shipperInfo = "consignorAddr  ";
		shipperInfo += "consignorContName";
		shipperInfo += "consignorPhone";
		valueMap2.put("EXT_SHIPPER_INFO", shipperInfo);
		// 合并收件人信息
		String addresseeInfo = "addresseeAddr";
		addresseeInfo += "addresseeContName ";
		addresseeInfo += "addresseePhone ";
		valueMap2.put("EXT_ADDRESSEE_INFO", addresseeInfo);
		// 付款方式
		valueMap2.put("EXT_PAY_INFO", "寄付");

		valueMap2.put("waybillCount", "2");
		valueMap2.put("billing_weight", "100");
		valueMap2.put("read_weight", "200");
		valueMap2.put("amount", "105");
		valueMap2.put("total_amount", "合计1");
		valueMap2.put("total_amount2", "合计2");

		if (null != entity.getDeliver_time()) {
			if (entity.getDeliver_time() == 0) {
				valueMap2.put("waybillRemk", "只工作日送货（双休日、假日不用送）");
			} else if (entity.getDeliver_time() == 1) {
				valueMap2.put("waybillRemk", "工作日、双休日与假日均可送货");
			} else {
				valueMap2.put("waybillRemk", "只双休日、假日送货（工作日不送货）");
			}

		}

		try {
			BufferedImage bufferImage = BillDrawUtil.drawWaybillImageA4(valueMap2, 5);
			ServletOutputStream sos = response.getOutputStream();
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(sos);
			encoder.encode(bufferImage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
