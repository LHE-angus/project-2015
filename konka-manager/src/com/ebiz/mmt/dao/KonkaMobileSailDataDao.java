package com.ebiz.mmt.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ebiz.mmt.domain.KonkaMobileSailData;
import com.ebiz.mmt.domain.KonkaStoreTaskFinishReport;
import com.ebiz.ssi.dao.EntityDao;

public interface KonkaMobileSailDataDao extends EntityDao<KonkaMobileSailData> {

	/**
	 * @author Jiang,JiaYong
	 * @version 2013-08-19
	 */
	public abstract List<KonkaMobileSailData> selectKonkaMobileSailDataInR3InfoPaginatedList(KonkaMobileSailData t)
			throws DataAccessException;

	/**
	 * @author Hu,Hao
	 * @version 2013-09-14
	 */
	List<KonkaMobileSailData> selectKonkaMobileSailDataForFgsTopCount(KonkaMobileSailData t);

	/**
	 * @author Hu,Hao
	 * @version 2013-11-20
	 */
	Long selectKonkaMobileSailDataToJsonCount(KonkaMobileSailData t);

	/**
	 * @author liujia
	 * @version 2014-03-23
	 */
	Long selectKonkaMobileSailDataCountForFourWeek(KonkaMobileSailData t);

	/**
	 * @author Hu,Hao
	 * @version 2013-11-20
	 */
	List<KonkaMobileSailData> selectKonkaMobileSailDataToJsonPaginatedList(KonkaMobileSailData t);

	/**
	 * @author Hu,Hao
	 * @version 2013-11-21
	 */
	Long selectKonkaMobileSailDataToJson3Count(KonkaMobileSailData t);

	/**
	 * @author Hu,Hao
	 * @version 2013-11-1
	 */
	List<KonkaMobileSailData> selectKonkaMobileSailDataToJson3PaginatedList(KonkaMobileSailData t);

	/**
	 * @author Hu,Hao
	 * @version 2013-12-08
	 */
	List<KonkaMobileSailData> selectKonkaMobileSailDataToExlList(KonkaMobileSailData t);

	/**
	 * @author Xiao,GuoJian
	 * @version 2014-06-17
	 * @desc 获取销售总数量和总金额
	 */
	KonkaMobileSailData selectKonkaMobileSailDataForTotal(KonkaMobileSailData t);

	/**
	 * 获取排名总数
	 * 
	 * @author Lianghouen
	 * @date 2014-7-3
	 * @param t
	 * @return
	 */
	Long selectStarOfBanCount(KonkaMobileSailData t);

	/**
	 * 获取列表
	 * 
	 * @author Lianghouen
	 * @date 2014-7-3
	 * @param t
	 * @return
	 */
	List<HashMap> selectStarOfBanList(KonkaMobileSailData t);

	/**
	 * 查询总量
	 * 
	 * @author Lianghouen
	 * @date 2014-7-4
	 * @param t
	 * @return
	 */
	HashMap selectAllMoneyAndNum(KonkaMobileSailData t);

	//
	List<KonkaStoreTaskFinishReport> selectAllStoreTaskFinish(KonkaMobileSailData t);

	Long selectAllStoreTaskFinish4count(KonkaMobileSailData t);

	List<KonkaMobileSailData> selectKonkaMobileSailDataForExportList(KonkaMobileSailData t);

	List<KonkaMobileSailData> selectKonkaMobileSailDataAndBillPaginatedList(KonkaMobileSailData t);

	List<KonkaMobileSailData> selectkonkaMobileSailDataAndBillForSwitchList(KonkaMobileSailData entity);

	void selectkonkaMobileSailDataAndBillForSwitchSave(KonkaMobileSailData entity);

	Long selectkonkaMobileSailDataAndBillForSalaryCount(KonkaMobileSailData entity);

	List<HashMap> selectkonkaMobileSailDataAndBillForSalaryList(KonkaMobileSailData entity);

	Long selectKonkaMobileSailDataAndBillCount(KonkaMobileSailData entity);

	List<KonkaMobileSailData> selectKonkaMobileSailDataBillFileList(KonkaMobileSailData t);

	/**
	 * 统计所有数据的总商品数量和总价格
	 */
	List<KonkaMobileSailData> selectKonkaMobileSailDataNumAndMoneyCount(KonkaMobileSailData t);

	// 专给OA使用
	List<HashMap<String, Object>> selectKonkaMobileSailDataListByR3JobId(KonkaMobileSailData entity);

	// 专给OA使用
	// fgs_code ,date_begin,date_end
	List<HashMap<String, Object>> selectKonkaMobileSailDataListByFgsCode(HashMap<String, Object> map);

	List<HashMap<String, Object>> selectKonkaMobileSailDataForTerminalMap(KonkaMobileSailData entity);

	List<KonkaMobileSailData> selectKonkaMobileSailDataForTop10List(KonkaMobileSailData t);

	List<KonkaMobileSailData> selectKonkaMobileSailDataForSaleNumList(KonkaMobileSailData t);

	List<HashMap> selectCxModelList(KonkaMobileSailData t);

	List<HashMap> selectPMForDeptList(KonkaMobileSailData t);

}