package com.ebiz.mmt.service;

import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ebiz.mmt.domain.KonkaOrderInfo;
import com.ebiz.mmt.r3.ReturnInfo;

public class AutoServiceTest {

	Logger logger = LoggerFactory.getLogger(AutoServiceTest.class);

	static Facade facade;
	static AutoRunService autoRunService;

	@BeforeClass
	public static void setUp() {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
		facade = (Facade) context.getBean("facade");
		autoRunService = (AutoRunService) context.getBean("autoRunService");
	}

	@Test
	public void testSyncR3OrderToCustPd() {
		autoRunService.syncR3OrderToCustPd();
	}
	
}
