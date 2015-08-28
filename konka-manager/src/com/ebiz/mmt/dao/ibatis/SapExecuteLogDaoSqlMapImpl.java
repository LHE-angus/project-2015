package com.ebiz.mmt.dao.ibatis;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.SapExecuteLogDao;
import com.ebiz.mmt.domain.SapExecuteLog;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;


@Service
public class SapExecuteLogDaoSqlMapImpl extends EntityDaoSqlMapImpl<SapExecuteLog> implements SapExecuteLogDao {

	@Override
	public List<HashMap> selectSapExecuteLogReport() {

		List<HashMap> list = new ArrayList<HashMap>();
		try {
			list = super.getSqlMapClientTemplate().getSqlMapClient().queryForList("selectSapExecuteLogReport");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

}
