package com.ebiz.mmt.dao.ibatis;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.VADefailsOfConsumerDao;
import com.ebiz.mmt.domain.VADefailsOfConsumer;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-07-08 17:11:27
 */
@Service
public class VADefailsOfConsumerDaoSqlMapImpl extends EntityDaoSqlMapImpl<VADefailsOfConsumer> implements VADefailsOfConsumerDao {

	@Override
	public List<HashMap> selectVADefailsOfConsumerMapList(VADefailsOfConsumer t) {
		
		return super.getSqlMapClientTemplate().queryForList("selectVADefailsOfConsumerMapList",t);
	}

}
