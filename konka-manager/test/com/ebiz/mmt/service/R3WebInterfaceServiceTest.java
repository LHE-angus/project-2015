package com.ebiz.mmt.service;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ebiz.mmt.domain.KonkaOrderInfo;
import com.ebiz.mmt.domain.KonkaOrderInfoDetails;

public class R3WebInterfaceServiceTest {

	Logger logger = LoggerFactory.getLogger(R3WebInterfaceServiceTest.class);

	static Facade facade;

	static KonkaOrderInfoService ks;
	static KonkaOrderInfoDetailsService ksd;
	static R3WebInterfaceService rs;

	@BeforeClass
	public static void setUp() {
		ApplicationContext cxt = new ClassPathXmlApplicationContext("spring-context.xml");
		ks = (KonkaOrderInfoService) cxt.getBean("konkaOrderInfoServiceImpl");
		ksd = (KonkaOrderInfoDetailsService) cxt.getBean("konkaOrderInfoDetailsServiceImpl");
		rs = (R3WebInterfaceService) cxt.getBean("r3WebInterfaceServiceImpl");
	}

	@Test
	public void testSaveKonkaOrderInfo() {
		KonkaOrderInfo konkaOrderInfo = new KonkaOrderInfo();
		konkaOrderInfo.setId(1696l);
		konkaOrderInfo = ks.getKonkaOrderInfo(konkaOrderInfo);
		// //System.out.println(konkaOrderInfo.getTrade_index());
		// //System.out.println(konkaOrderInfo.getDoc_type());
		// //System.out.println(konkaOrderInfo.getCg_order_date());
		// //System.out.println(konkaOrderInfo.getThird_cg_order_num());

		List<KonkaOrderInfoDetails> infolist = null;

		KonkaOrderInfoDetails infod = new KonkaOrderInfoDetails();
		infod.setOrder_id(1696l);
		infolist = ksd.getKonkaOrderInfoDetailsList(infod);

		//System.out.println(infolist.size());

		konkaOrderInfo.setKonkaOrderInfoDetailsList(infolist);

		// ReturnInfo ret = rs.saveKonkaOrderInfo(konkaOrderInfo, "testName");
		
		// //System.out.println(ret.getItemNO());
		// 同步成功后,其它处理

	}

}
