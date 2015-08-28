package com.ebiz.mmt.dao.ibatis;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.LogOfJobDao;
import com.ebiz.mmt.domain.LogOfJob;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;


@Service
public class LogOfJobDaoSqlMapImpl extends EntityDaoSqlMapImpl<LogOfJob> implements LogOfJobDao {

	@Override
	public List<LogOfJob> selectSynTypeList() {
		return super.getSqlMapClientTemplate().queryForList("selectSynTypeList");
	}

	@Override
	public List<HashMap> selectLogOfJobListForMap(LogOfJob t) {
		return super.getSqlMapClientTemplate().queryForList("selectLogOfJobListForMap", t);
	}

}
