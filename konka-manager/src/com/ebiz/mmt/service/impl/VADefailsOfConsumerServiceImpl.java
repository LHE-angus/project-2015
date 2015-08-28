package com.ebiz.mmt.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.VADefailsOfConsumerDao;
import com.ebiz.mmt.domain.VADefailsOfConsumer;
import com.ebiz.mmt.service.VADefailsOfConsumerService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-07-08 17:11:28
 */
@Service
public class VADefailsOfConsumerServiceImpl implements VADefailsOfConsumerService {

	@Resource
	private VADefailsOfConsumerDao vADefailsOfConsumerDao;
	

	public Long createVADefailsOfConsumer(VADefailsOfConsumer t) {
		return this.vADefailsOfConsumerDao.insertEntity(t);
	}

	public VADefailsOfConsumer getVADefailsOfConsumer(VADefailsOfConsumer t) {
		return this.vADefailsOfConsumerDao.selectEntity(t);
	}

	public Long getVADefailsOfConsumerCount(VADefailsOfConsumer t) {
		return this.vADefailsOfConsumerDao.selectEntityCount(t);
	}

	public List<VADefailsOfConsumer> getVADefailsOfConsumerList(VADefailsOfConsumer t) {
		return this.vADefailsOfConsumerDao.selectEntityList(t);
	}

	public int modifyVADefailsOfConsumer(VADefailsOfConsumer t) {
		return this.vADefailsOfConsumerDao.updateEntity(t);
	}

	public int removeVADefailsOfConsumer(VADefailsOfConsumer t) {
		return this.vADefailsOfConsumerDao.deleteEntity(t);
	}

	public List<VADefailsOfConsumer> getVADefailsOfConsumerPaginatedList(VADefailsOfConsumer t) {
		return this.vADefailsOfConsumerDao.selectEntityPaginatedList(t);
	}
	
	public List<HashMap> getVADefailsOfConsumerMapList(VADefailsOfConsumer t) {
		return this.vADefailsOfConsumerDao.selectVADefailsOfConsumerMapList(t);
	}

}
