package com.ebiz.mmt.dao.ibatis;

import com.ebiz.mmt.dao.ChannelDataImportDao;
import com.ebiz.mmt.domain.ChannelDataImport;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;
import com.ibatis.sqlmap.client.SqlMapClient;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


@Repository
public class ChannelDataImportDaoSqlMapImpl extends EntityDaoSqlMapImpl<ChannelDataImport> implements
		ChannelDataImportDao {

	/**
	 * @author Wang,Yang
	 * @version 2011-11-10
	 */
	@Override
	public java.math.BigDecimal selectChannelDataSumByPdId(ChannelDataImport t) throws DataAccessException {
		return (java.math.BigDecimal) this.getSqlMapClientTemplate().queryForObject("selectChannelDataSumByPdId", t);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<ChannelDataImport> selectChannelDataImportListToSum(ChannelDataImport t) throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForList("selectChannelDataImportListToSum", t);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<ChannelDataImport> selectChannelDataImportPaginatedListForStat(ChannelDataImport t)
			throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForList("selectChannelDataImportPaginatedListForStat", t);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<ChannelDataImport> selectChannelDataImportPaginatedListForModel(ChannelDataImport t)
			throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForList("selectChannelDataImportPaginatedListForModel", t);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ChannelDataImport> selectChannelDataListByPdId(ChannelDataImport cdi) throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForList("selectChannelDataListByPdId", cdi);
	}

	@Override
	public Long selectChannelDataImportForSum(ChannelDataImport cdi) throws DataAccessException {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectChannelDataImportForSum", cdi);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<ChannelDataImport> selectChannelDataImportListForStatistics(ChannelDataImport t)
			throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForList("selectChannelDataImportListForStatistics", t);
	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<BigDecimal, BigDecimal> selectChannelDataImportAllCountAndAllMoney(ChannelDataImport t) {
		return (HashMap<BigDecimal, BigDecimal>) super.getSqlMapClientTemplate().queryForObject(
				"selectChannelDataImportAllCountAndAllMoney", t);
	}

	@Override
	public Date selectTheMaxImportDate(ChannelDataImport t) {
		return (Date) super.getSqlMapClientTemplate().queryForObject(
				"selectChannelDataImportForMaxDate", t);
	}

	@Override
	public void insertChannelDataImportBatch(List<ChannelDataImport> list) throws SQLException {
		SqlMapClient sqt = super.getSqlMapClient();
		sqt.startBatch();
		for (ChannelDataImport t : list) {
			sqt.insert("insertChannelDataImport", t);
		}
		sqt.executeBatch();
	}
	
	/**
	 * @author Hu,Hao
	 * @version 2013-09-14
	 * @desc 分公司业绩排名
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<ChannelDataImport> selectChannelDataImportForFgsTop(ChannelDataImport t) throws DataAccessException{
		return super.getSqlMapClientTemplate().queryForList("selectChannelDataImportForFgsTop",t);
	}
	
	/**
	 * @author Hu,Hao
	 * @version 2013-09-15
	 * @desc 经办业绩排名
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<ChannelDataImport> selectChannelDataImportForJybTop(ChannelDataImport t){
		return super.getSqlMapClientTemplate().queryForList("selectChannelDataImportForJybTop", t);
	}
	
	/**
	 * @author Hu,Hao
	 * @version 2013-09-22
	 * @desc 任务完成分析
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<ChannelDataImport> selectChannelDataImportListToMonth(ChannelDataImport t){
		return super.getSqlMapClientTemplate().queryForList("selectChannelDataImportListToMonth", t);
	}
	/**
	 * @author Hu,Hao
	 * @version 2013-09-22
	 * @desc 任务完成分析
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<ChannelDataImport> selectChannelDataImportListToSeason(ChannelDataImport t){
		return super.getSqlMapClientTemplate().queryForList("selectChannelDataImportListToSeason", t);
	}
	
	/**
	 * @author Hu,Hao
	 * @version 2013-09-22
	 * @desc 任务完成分析
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<ChannelDataImport> selectChannelDataImportListToAll(ChannelDataImport t){
		return super.getSqlMapClientTemplate().queryForList("selectChannelDataImportListToAll", t);
	}
	
	/**
	 * @author Hu,Hao
	 * @version 2013-11-14
	 * @desc 分公司业绩排名（回款）
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<ChannelDataImport> selectChannelDataImportForFgsHkTop(ChannelDataImport t){
		return super.getSqlMapClientTemplate().queryForList("selectChannelDataImportForFgsHkTop", t);
	}
	

	/**
	 * @author Hu,Hao
	 * @version 2013-12-18
	 * @desc 分公司业绩排名（其他）
	 */
	@SuppressWarnings("unchecked")
	public List<ChannelDataImport> selectChannelDataImportForFgsHkQt(ChannelDataImport t){
		return super.getSqlMapClientTemplate().queryForList("selectChannelDataImportForFgsHkQt", t);
	}
	
	/**
	 * @author Xing,XiuDong
	 * @version 2013-12-23
	 */
	@SuppressWarnings("unchecked")
	public List<ChannelDataImport> selectGroupByPdStatList(ChannelDataImport t){
		return super.getSqlMapClientTemplate().queryForList("selectGroupByPdStatList", t);
	}

	@Override
	public Long selectR3codeIsJs(ChannelDataImport cd)
			throws DataAccessException {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectR3codeIsJs", cd);
	}

	@Override
	public List<ChannelDataImport> selectChannelDataImportListForFifo(ChannelDataImport channelDataImport) {
		return super.getSqlMapClientTemplate().queryForList("selectChannelDataImportListForFifo", channelDataImport);
	}
}