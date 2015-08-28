package com.ebiz.mmt.dao.ibatis;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.ConsumerInfoDao;
import com.ebiz.mmt.domain.ConsumerInfo;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;


@Service
public class ConsumerInfoDaoSqlMapImpl extends EntityDaoSqlMapImpl<ConsumerInfo> implements ConsumerInfoDao {

	@Override
	public Long selectConsumerInfoAllCount(ConsumerInfo t) {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectConsumerInfoAllCount", t);
	}

	@Override
	public List<ConsumerInfo> selectConsumerInfoForList(ConsumerInfo t) {
		return super.getSqlMapClientTemplate().queryForList("selectConsumerInfoForList", t);
	}

	@Override
	public List<HashMap> selectConsumerInfoDetails(ConsumerInfo t) {
		return super.getSqlMapClientTemplate().queryForList("selectConsumerInfoDetails", t);
	}

}
