package com.ebiz.mmt.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.ConsumerInfoDao;
import com.ebiz.mmt.domain.ConsumerInfo;
import com.ebiz.mmt.service.ConsumerInfoService;


@Service
public class ConsumerInfoServiceImpl implements ConsumerInfoService {

	@Resource
	private ConsumerInfoDao consumerInfoDao;
	

	public Long createConsumerInfo(ConsumerInfo t) {
		return this.consumerInfoDao.insertEntity(t);
	}

	public ConsumerInfo getConsumerInfo(ConsumerInfo t) {
		return this.consumerInfoDao.selectEntity(t);
	}

	public Long getConsumerInfoCount(ConsumerInfo t) {
		return this.consumerInfoDao.selectEntityCount(t);
	}

	public List<ConsumerInfo> getConsumerInfoList(ConsumerInfo t) {
		return this.consumerInfoDao.selectEntityList(t);
	}

	public int modifyConsumerInfo(ConsumerInfo t) {
		return this.consumerInfoDao.updateEntity(t);
	}

	public int removeConsumerInfo(ConsumerInfo t) {
		return this.consumerInfoDao.deleteEntity(t);
	}

	public List<ConsumerInfo> getConsumerInfoPaginatedList(ConsumerInfo t) {
		return this.consumerInfoDao.selectEntityPaginatedList(t);
	}

	public Long getConsumerInfoAllCount(ConsumerInfo t) {
		return this.consumerInfoDao.selectConsumerInfoAllCount(t);
	}
	
	public List<ConsumerInfo> getConsumerInfoForList(ConsumerInfo t) {
		return this.consumerInfoDao.selectConsumerInfoForList(t);
	}

	@Override
	public List<HashMap> getConsumerInfoDetails(ConsumerInfo t) {
		return this.consumerInfoDao.selectConsumerInfoDetails(t);
	}
}
