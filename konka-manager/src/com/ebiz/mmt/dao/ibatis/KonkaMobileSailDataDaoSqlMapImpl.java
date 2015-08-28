package com.ebiz.mmt.dao.ibatis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.ebiz.mmt.dao.KonkaMobileSailDataDao;
import com.ebiz.mmt.domain.KonkaMobileSailData;
import com.ebiz.mmt.domain.KonkaStoreTaskFinishReport;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

@Repository
public class KonkaMobileSailDataDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaMobileSailData> implements
		KonkaMobileSailDataDao {

	/**
	 * @author Jiang,JiaYong
	 * @version 2013-08-19
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<KonkaMobileSailData> selectKonkaMobileSailDataInR3InfoPaginatedList(KonkaMobileSailData t)
			throws DataAccessException {
		return this.getSqlMapClientTemplate().queryForList("selectKonkaMobileSailDataInR3InfoPaginatedList", t);
	}

	/**
	 * @author Hu,Hao
	 * @version 2013-09-14
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<KonkaMobileSailData> selectKonkaMobileSailDataForFgsTopCount(KonkaMobileSailData t) {
		return super.getSqlMapClientTemplate().queryForList("selectKonkaMobileSailDataForFgsTopCount", t);
	}

	/**
	 * @author Hu,Hao
	 * @version 2013-11-20
	 */
	@Override
	public Long selectKonkaMobileSailDataToJsonCount(KonkaMobileSailData t) {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectKonkaMobileSailDataToJsonCount", t);
	}

	/**
	 * @author Hu,Hao
	 * @version 2013-11-20
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<KonkaMobileSailData> selectKonkaMobileSailDataToJsonPaginatedList(KonkaMobileSailData t) {
		return super.getSqlMapClientTemplate().queryForList("selectKonkaMobileSailDataToJsonPaginatedList", t);
	}

	/**
	 * @author Hu,Hao
	 * @version 2013-11-21
	 */
	@Override
	public Long selectKonkaMobileSailDataToJson3Count(KonkaMobileSailData t) {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectKonkaMobileSailDataToJson3Count", t);
	}

	/**
	 * @author Hu,Hao
	 * @version 2013-11-21
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<KonkaMobileSailData> selectKonkaMobileSailDataToJson3PaginatedList(KonkaMobileSailData t) {
		return super.getSqlMapClientTemplate().queryForList("selectKonkaMobileSailDataToJson3PaginatedList", t);
	}

	/**
	 * @author Hu,Hao
	 * @version 2013-12-08
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<KonkaMobileSailData> selectKonkaMobileSailDataToExlList(KonkaMobileSailData t) {
		return super.getSqlMapClientTemplate().queryForList("selectKonkaMobileSailDataToExlList", t);
	}

	@Override
	public Long selectKonkaMobileSailDataCountForFourWeek(KonkaMobileSailData t) {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectKonkaMobileSailDataCountForFourWeek", t);
	}

	@Override
	public KonkaMobileSailData selectKonkaMobileSailDataForTotal(KonkaMobileSailData t) {
		return (KonkaMobileSailData) super.getSqlMapClientTemplate().queryForObject(
				"selectKonkaMobileSailDataForTotal", t);
	}

	@Override
	public Long selectStarOfBanCount(KonkaMobileSailData t) {
		
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectStarOfBanCount", t);
	}

	@Override
	public List<HashMap> selectStarOfBanList(KonkaMobileSailData t) {
		
		return super.getSqlMapClientTemplate().queryForList("selectStarOfBanList", t);
	}

	@Override
	public HashMap selectAllMoneyAndNum(KonkaMobileSailData t) {
		
		return (HashMap) super.getSqlMapClientTemplate().queryForObject("selectAllmoneyAndNum", t);
	}

	//
	@SuppressWarnings("unchecked")
	@Override
	public List<KonkaStoreTaskFinishReport> selectAllStoreTaskFinish(KonkaMobileSailData t) {

		List<KonkaStoreTaskFinishReport> list = new ArrayList<KonkaStoreTaskFinishReport>();
		list = super.getSqlMapClientTemplate().queryForList("selectAllStoreTaskFinish", t);
		return list;
	}

	@Override
	public Long selectAllStoreTaskFinish4count(KonkaMobileSailData t) {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectAllStoreTaskFinish4count", t);
	}

	@Override
	public List<KonkaMobileSailData> selectKonkaMobileSailDataForExportList(KonkaMobileSailData t) {
		
		return super.getSqlMapClientTemplate().queryForList("selectKonkaMobileSailDataForExportList", t);
	}

	@Override
	public List<KonkaMobileSailData> selectKonkaMobileSailDataAndBillPaginatedList(KonkaMobileSailData t) {
		
		return super.getSqlMapClientTemplate().queryForList("selectKonkaMobileSailDataAndBillPaginatedList", t);
	}

	@Override
	public List<KonkaMobileSailData> selectkonkaMobileSailDataAndBillForSwitchList(KonkaMobileSailData t) {
		
		return super.getSqlMapClientTemplate().queryForList("selectkonkaMobileSailDataAndBillForSwitchList", t);
	}

	@Override
	public void selectkonkaMobileSailDataAndBillForSwitchSave(KonkaMobileSailData entity) {
		
		super.getSqlMapClientTemplate().insert("insertkonkaMobileSailDataAndBillForSwitchSave-lo", entity);
		super.getSqlMapClientTemplate().update("upkonkaMobileSailDataAndBillForSwitchSave_bill", entity);
		super.getSqlMapClientTemplate().update("upkonkaMobileSailDataAndBillForSwitchSave_sail", entity);
	}

	@Override
	public Long selectkonkaMobileSailDataAndBillForSalaryCount(KonkaMobileSailData entity) {
		
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectkonkaMobileSailDataAndBillForSalaryCount",
				entity);
	}

	@Override
	public List<HashMap> selectkonkaMobileSailDataAndBillForSalaryList(KonkaMobileSailData entity) {
		
		return super.getSqlMapClientTemplate().queryForList("selectkonkaMobileSailDataAndBillForSalaryList", entity);
	}

	@Override
	public Long selectKonkaMobileSailDataAndBillCount(KonkaMobileSailData entity) {
		
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectKonkaMobileSailDataAndBillCount", entity);
	}

	@Override
	public List<KonkaMobileSailData> selectKonkaMobileSailDataBillFileList(KonkaMobileSailData t) {
		
		return super.getSqlMapClientTemplate().queryForList("selectKonkaMobileSailDataBillFileList", t);
	}

	/**
	 * 统计所有数据的总商品数量和总价格
	 */
	@Override
	public List<KonkaMobileSailData> selectKonkaMobileSailDataNumAndMoneyCount(KonkaMobileSailData t) {
		
		return super.getSqlMapClientTemplate().queryForList("selectKonkaMobileSailDataNumAndMoneyCount", t);
	}

	@Override
	public List<HashMap<String, Object>> selectKonkaMobileSailDataListByR3JobId(KonkaMobileSailData entity) {
		
		return super.getSqlMapClientTemplate().queryForList("selectKonkaMobileSailDataListByR3JobId", entity);
	}

	@Override
	public List<HashMap<String, Object>> selectKonkaMobileSailDataForTerminalMap(KonkaMobileSailData entity) {
		
		return super.getSqlMapClientTemplate().queryForList("selectKonkaMobileSailDataForTerminalMap", entity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ebiz.mmt.dao.KonkaMobileSailDataDao#
	 * selectKonkaMobileSailDataListByFgsCode
	 * (com.ebiz.mmt.domain.KonkaMobileSailData)
	 */
	@Override
	public List<HashMap<String, Object>> selectKonkaMobileSailDataListByFgsCode(HashMap<String, Object> map) {
		
		return super.getSqlMapClientTemplate().queryForList("selectKonkaMobileSailDataListByFgsCode", map);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<KonkaMobileSailData> selectKonkaMobileSailDataForTop10List(KonkaMobileSailData t) {
		return super.getSqlMapClientTemplate().queryForList("selectKonkaMobileSailDataForTop10List", t);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<KonkaMobileSailData> selectKonkaMobileSailDataForSaleNumList(KonkaMobileSailData t) {
		return super.getSqlMapClientTemplate().queryForList("selectKonkaMobileSailDataForSaleNumList", t);
	}

	@Override
	public List<HashMap> selectCxModelList(KonkaMobileSailData t) {
		return super.getSqlMapClientTemplate().queryForList("selectCxModelList", t);
	}

	@Override
	public List<HashMap> selectPMForDeptList(KonkaMobileSailData t) {
		return super.getSqlMapClientTemplate().queryForList("selectPMForDeptList", t);
	}

}
