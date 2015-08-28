package com.ebiz.mmt.dao;

import java.util.HashMap;
import java.util.List;

import com.ebiz.mmt.domain.ConsumerInfo;
import com.ebiz.ssi.dao.EntityDao;


public interface ConsumerInfoDao extends EntityDao<ConsumerInfo> {
	Long selectConsumerInfoAllCount(ConsumerInfo t);
	
	List<ConsumerInfo> selectConsumerInfoForList(ConsumerInfo t);
	
	List<HashMap> selectConsumerInfoDetails(ConsumerInfo t);
}
