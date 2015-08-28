package com.ebiz.mmt.web.servlet;

import java.awt.image.BufferedImage;
import java.math.BigDecimal;
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
import com.ebiz.mmt.domain.EcSelfArea;
import com.ebiz.mmt.domain.KonkaBcompPd;
import com.ebiz.mmt.domain.PshowOrdeDetails;
import com.ebiz.mmt.domain.PshowOrder;
import com.ebiz.mmt.service.Facade;
import com.sf.integration.warehouse.Order;
import com.sf.module.ewaybill.print.BillDrawUtil;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class SFCodeServlet extends HttpServlet {

	private static final long serialVersionUID = -3981794330055840248L;

	public void init(ServletConfig config) throws ServletException {
	}

	public void destroy() {
		super.destroy();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
	        java.io.IOException {

		String order_id = (String) request.getParameter("order_id");
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

		PshowOrdeDetails psd = new PshowOrdeDetails();
		psd.setOrder_id(Long.valueOf(order_id));
		List<PshowOrdeDetails> pshowordedetails = facade.getPshowOrdeDetailsService().getPshowOrdeForPDSNDetailsList(
		        psd);
		int t_num2 = 0;
		for (PshowOrdeDetails pshowOrdeDetails2 : pshowordedetails) {
			t_num2 += pshowOrdeDetails2.getNum();
		}

		int t_num = 0;
		String pd_sn_all = "";
		BigDecimal dk_price = entity.getDedu_price();// 除去抵扣金额
		BigDecimal dk_price_2 = dk_price.divide(new BigDecimal(t_num2),4).setScale(2, BigDecimal.ROUND_HALF_UP);

		BigDecimal sj_weight = new BigDecimal("0.00");
		BigDecimal p_weight = new BigDecimal("0.00");

		for (PshowOrdeDetails ps : pshowordedetails) {
			t_num += ps.getNum();
			KonkaBcompPd pd = new KonkaBcompPd();
			pd.setId(ps.getPd_id());
			pd = facade.getKonkaBcompPdService().getKonkaBcompPd(pd);
			BigDecimal s_weight = new BigDecimal("0.00");
			BigDecimal _weight = new BigDecimal("0.00");
			if (null != pd) {
				pd_sn_all = pd_sn_all + pd.getPd_sn() + "*" + String.valueOf(ps.getNum()) + " = "
				        + (ps.getTotal_price().subtract(dk_price_2.multiply(new BigDecimal(ps.getNum())))) + " ";
				if (pd.getSj_weight() != null) {
					s_weight = pd.getSj_weight();
				}
				if (pd.getP_weight() != null) {
					_weight = pd.getP_weight();
				}
				sj_weight = sj_weight.add(s_weight.multiply(new BigDecimal(ps.getNum())));
				p_weight = p_weight.add(_weight.multiply(new BigDecimal(ps.getNum())));
			}
		}

		Order order = new Order();

		if (null != entity.getOpr_dept_id()) {
			if (entity.getOpr_dept_id() == 13) {
				order.setJ_contact("周仁明");
				order.setJ_tel("0550-3089116");
				order.setJ_address("安徽省滁州市中都大道999号");
				order.setJ_province("安徽省");
				order.setJ_city("滁州市");
				order.setJ_county("南谯区");
				order.setJ_mobile("13655504217");
				order.setValue1("5500047967");
				order.setCustid("5500047967");
			} else if (entity.getOpr_dept_id() == 6) {
				order.setJ_contact("黄生");
				order.setJ_tel("0769-82552522");
				order.setJ_address("广东东莞凤岗康佳直销中心");
				order.setJ_province("广东省");
				order.setJ_city("东莞市");
				order.setJ_county("凤岗");
				order.setValue1("7691250396");
				order.setCustid("7691250396");
			} else if (entity.getOpr_dept_id().intValue() == 10) {// 哈尔滨分公司
				order.setJ_contact("张帅");
				order.setJ_tel("0451-55550681");
				order.setJ_address("哈尔滨市道外区大新街75号康佳集团哈尔滨分公司");
				order.setJ_province("黑龙江省");
				order.setJ_city("哈尔滨市");
				order.setJ_county("道外区");
				order.setJ_mobile("18645145490");
				order.setHead("kjjt,IHcoDnPIalG8oYBzxMYomOwMmHzdykSO");// 哈尔滨顾客编码，密码

				// 保价
				order.setS_price("0");
				order.setPrice(entity.getPay_price().toString());
				order.setCustid("4512483409");
			}
		}

		response.addHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
		response.setHeader("Cache-Control", "pre-check=0,post-check=0");
		response.setDateHeader("Expires", 0);

		Map<String, Object> valueMap2 = new HashMap<String, Object>();

		valueMap2.put("waybillNo", ec == null ? "" : ec.getLogistic_sn() == null ? "" : ec.getLogistic_sn());
		valueMap2.put("custCode", order.getCustid());// 月结账号，康佳与顺丰签合同上有的
		valueMap2.put("sourceZoneCode", ec == null ? "" : ec.getOrder_from() == null ? "" : ec.getOrder_from());
		valueMap2.put("destZoneCode", ec == null ? "" : ec.getOrder_to() == null ? "" : ec.getOrder_to());

		// 合并寄件人信息
		String shipperInfo = order.getJ_address() + " ";
		shipperInfo += order.getJ_contact() + " ";
		shipperInfo += order.getJ_tel() + " ";
		valueMap2.put("EXT_SHIPPER_INFO", shipperInfo);

		// 判断是否是自提
		String p_index = "";
		String addr = "";
		String zt = "";
		if (entity.getIs_self() == 1 && null != entity.getSelf_id()) {
			EcSelfArea ecs = new EcSelfArea();
			ecs.setId(entity.getSelf_id());
			ecs = facade.getEcSelfAreaService().getEcSelfArea(ecs);
			p_index = ecs.getP_index().toString();
			addr = ecs.getSelf_addr();
			zt = "（客户自提）";
		} else {
			if (entity.getBuyer_p_index() != null && entity.getBuyer_p_index() != -1) {
				p_index = entity.getBuyer_p_index().toString();
			}
			addr = entity.getBuyer_addr();
			zt = "";
		}

		StringBuffer fullName = new StringBuffer("");
		BaseProvinceListFour baseProvince = new BaseProvinceListFour();
		baseProvince.setP_index(Long.valueOf(p_index));
		List<BaseProvinceListFour> baseProvinceList = facade.getBaseProvinceListFourService()
		        .getBaseProvinceListFourParentList(baseProvince);
		for (BaseProvinceListFour t : baseProvinceList)
			fullName.append(t.getP_name());
		// 合并收件人信息
		String addresseeInfo = fullName.toString() + " ";
		addresseeInfo += addr + " ";
		addresseeInfo += entity.getBuyer_name() + " ";
		addresseeInfo += entity.getBuyer_mp() + " ";
		valueMap2.put("EXT_ADDRESSEE_INFO", addresseeInfo + zt);
		// 付款方式
		valueMap2.put("EXT_PAY_INFO", "寄付");

		// 如果是货到付款，需要打印该项
		if (entity.getPay_way() == 0) {
			String srvInfo = "";
			srvInfo += "代收货款:" + entity.getPay_price();
			srvInfo += "\n";
			srvInfo += "卡号:" + order.getValue1();
			srvInfo += "\n";
			srvInfo += "声明价值:";
			valueMap2.put("EXT_SRV_INFO", srvInfo);
		}

		// 如果是哈尔滨分公司发货，需要打印该项
		if (entity.getOpr_dept_id().intValue() == 10) {
			String srvInfo = "";
			srvInfo = srvInfo + "声明价值:" + entity.getPay_price() + "   元";
			srvInfo = srvInfo + "\n";
			srvInfo = srvInfo + "保价费用:         元";
			valueMap2.put("EXT_SRV_INFO", srvInfo);
		}

		valueMap2.put("cons_name", pd_sn_all);

		valueMap2.put("waybillCount", t_num);
		valueMap2.put("billing_weight", p_weight.toString());// 计费总量（拋重）
		valueMap2.put("read_weight", sj_weight.toString());// 实际重量
		valueMap2.put("amount", "");
		valueMap2.put("total_amount", "");
		valueMap2.put("total_amount2", "");

		if (null != entity.getDeliver_time()) {
			if (entity.getDeliver_time() == 0) {
				valueMap2.put("waybillRemk", "只双休日、假日送货（工作日不用送）");
			} else if (entity.getDeliver_time() == 1) {
				valueMap2.put("waybillRemk", "工作日、双休日与假日均可送货");
			} else {
				valueMap2.put("waybillRemk", "只工作日送货（双休日、假日不用送）");
			}

		}

		try {
			BufferedImage bufferImage = BillDrawUtil.drawWaybillImageA5(valueMap2, 5);
			ServletOutputStream sos = response.getOutputStream();
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(sos);
			encoder.encode(bufferImage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
