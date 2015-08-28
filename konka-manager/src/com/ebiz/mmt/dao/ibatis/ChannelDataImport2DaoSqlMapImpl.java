package com.ebiz.mmt.dao.ibatis;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ebiz.mmt.dao.ChannelDataImport2Dao;
import com.ebiz.mmt.domain.ChannelDataImport2;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;
import com.ibatis.sqlmap.client.SqlMapClient;


@Repository
public class ChannelDataImport2DaoSqlMapImpl extends EntityDaoSqlMapImpl<ChannelDataImport2> implements
		ChannelDataImport2Dao {

	@SuppressWarnings(value = { "unchecked" })
	@Override
	public HashMap<String, BigDecimal> selectChannelDataImport2AllCountAndAllMoney(ChannelDataImport2 t) {
		return (HashMap<String, BigDecimal>) super.getSqlMapClientTemplate().queryForObject(
				"selectChannelDataImport2AllCountAndAllMoney", t);
	}

	@Override
	public void insertChannelDataImport2Batch(List<ChannelDataImport2> list) throws SQLException {
		SqlMapClient sqt = super.getSqlMapClient();
		sqt.startBatch();
		for (ChannelDataImport2 t : list) {
			sqt.insert("insertChannelDataImport2", t);
		}
		sqt.executeBatch();
	}

	/**
	 * @author Hu,Hao
	 * @version 2013-11-18
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<ChannelDataImport2> selectChannelDataImport2ForFgsTop(ChannelDataImport2 t){
		return super.getSqlMapClientTemplate().queryForList("selectChannelDataImport2ForFgsTop", t);
	}
	
	/**
	 * @author Hu,Hao
	 * @version 2013-12-23
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<ChannelDataImport2> selectChannelDataImport2ForFgsTop2(ChannelDataImport2 t){
		return super.getSqlMapClientTemplate().queryForList("selectChannelDataImport2ForFgsTop2", t);
	}

	/**
	 * @author ZHOU
	 * @param t
	 * @return
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<ChannelDataImport2> selectChannelDataImport2ForFgsTop3(ChannelDataImport2 t) {
		return super.getSqlMapClientTemplate().queryForList("selectChannelDataImport2ForFgsTop3", t);
	}
}