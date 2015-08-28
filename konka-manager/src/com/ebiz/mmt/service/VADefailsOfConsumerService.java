package com.ebiz.mmt.service;

import java.util.HashMap;
import java.util.List;

import com.ebiz.mmt.domain.VADefailsOfConsumer;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-07-08 17:11:28
 */
public interface VADefailsOfConsumerService {

	Long createVADefailsOfConsumer(VADefailsOfConsumer t);

	int modifyVADefailsOfConsumer(VADefailsOfConsumer t);

	int removeVADefailsOfConsumer(VADefailsOfConsumer t);

	VADefailsOfConsumer getVADefailsOfConsumer(VADefailsOfConsumer t);

	List<VADefailsOfConsumer> getVADefailsOfConsumerList(VADefailsOfConsumer t);

	Long getVADefailsOfConsumerCount(VADefailsOfConsumer t);

	List<VADefailsOfConsumer> getVADefailsOfConsumerPaginatedList(VADefailsOfConsumer t);
	
	List<HashMap> getVADefailsOfConsumerMapList(VADefailsOfConsumer t);

}