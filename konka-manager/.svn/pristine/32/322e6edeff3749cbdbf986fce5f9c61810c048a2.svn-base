package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;
import com.ebiz.mmt.dao.KonkaMobileRetailRestDao;
import com.ebiz.mmt.domain.KonkaMobileRetailRest;

@Repository
public class KonkaMobileRetailRestDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaMobileRetailRest> implements KonkaMobileRetailRestDao {

	public Long getRetailRestStatisticsCount(KonkaMobileRetailRest t) {
		return (Long)super.getSqlMapClientTemplate().queryForObject("getRetailRestStatisticsCount", t);
	}

	@SuppressWarnings("unchecked")
	public List<KonkaMobileRetailRest> getRetailRestStatisticsPaginatedList(
			KonkaMobileRetailRest t) {
		return super.getSqlMapClientTemplate().queryForList("getRetailRestStatisticsPaginatedList", t);
	}

}

