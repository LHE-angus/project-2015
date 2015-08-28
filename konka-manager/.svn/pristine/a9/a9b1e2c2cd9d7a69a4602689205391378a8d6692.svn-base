package com.ebiz.mmt.struts.manager.zmd;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.axis2.AxisFault;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ebiz.mmt.web.struts.manager.zmd.BaseZmdAction;

public class BaseZmdTest extends BaseZmdAction implements ApplicationContextAware {

	Logger logger = LoggerFactory.getLogger(BaseZmdTest.class);

	@Test
	public void testGetStocks() throws AxisFault {
		logger.info("{}", super.getStocks("LC42F1000PD", 2127l));
		logger.info("{}", super.getStocks("LC42F1000PD", 2233l));
	}
	
	@Test
	public void testIsMobileNO(){      
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");      
        Matcher m = p.matcher("13987871234");      
        logger.info(": {}", m.matches());      
    } 

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		applicationContext = new ClassPathXmlApplicationContext(new String[] { "spring-context.xml" });
	}
}
