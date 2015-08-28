package com.ebiz.mmt.service;

import java.util.HashMap;
import java.util.List;

import com.ebiz.mmt.domain.KonkaMobileSailData;
import com.ebiz.mmt.domain.KonkaStoreTaskFinishReport;

public interface KonkaMobileSailDataService {

	Long createKonkaMobileSailData(KonkaMobileSailData t);

	int modifyKonkaMobileSailData(KonkaMobileSailData t);

	int removeKonkaMobileSailData(KonkaMobileSailData t);

	KonkaMobileSailData getKonkaMobileSailData(KonkaMobileSailData t);

	List<KonkaMobileSailData> getKonkaMobileSailDataList(KonkaMobileSailData t);

	/**
	 * @author Jiang,JiaYong
	 * @version 2013-08-19
	 */
	List<KonkaMobileSailData> getKonkaMobileSailDataInR3InfoPaginatedList(KonkaMobileSailData t);

	Long getKonkaMobileSailDataCount(KonkaMobileSailData t);

	Long getKonkaMobileSailDataCountForFourWeek(KonkaMobileSailData t);

	List<KonkaMobileSailData> getKonkaMobileSailDataPaginatedList(KonkaMobileSailData t);

	Long createKonkaMobileSailDataForHis(KonkaMobileSailData t);

	Long createKonkaMobileSailDataList(List<KonkaMobileSailData> t);

	/**
	 * @author Hu,Hao
	 * @version 2013-09-14
	 */
	List<KonkaMobileSailData> getKonkaMobileSailDataForFgsTopCount(KonkaMobileSailData t);

	/**
	 * @author Hu,Hao
	 * @version 2013-11-20
	 */
	Long getKonkaMobileSailDataToJsonCount(KonkaMobileSailData t);

	/**
	 * @author Hu,Hao
	 * @version 2013-11-20
	 */
	List<KonkaMobileSailData> getKonkaMobileSailDataToJsonPaginatedList(KonkaMobileSailData t);

	/**
	 * @author Hu,Hao
	 * @version 2013-11-21
	 */
	Long getKonkaMobileSailDataToJson3Count(KonkaMobileSailData t);

	/**
	 * @author Hu,Hao
	 * @version 2013-11-21
	 */
	List<KonkaMobileSailData> getKonkaMobileSailDataToJson3PaginatedList(KonkaMobileSailData t);

	/**
	 * @author Hu,Hao
	 * @version 2013-12-08
	 */
	List<KonkaMobileSailData> getKonkaMobileSailDataToExlList(KonkaMobileSailData t);

	/**
	 * @author Pan,Gang
	 * @version 2013-12-08
	 * @desc 手机零售上报上传图片保存
	 */
	Long createKonkaMobileSailDataForAttachment(KonkaMobileSailData t, String link_id, String link_tab);

	/**
	 * @author Pan,Gang
	 * @version 2014-01-06
	 * @desc 零售上报保存附件
	 */
	Long createKonkaMobileSailData(KonkaMobileSailData t, Long lid);

	/**
	 * @author Xiao,GuoJian
	 * @version 2014-06-17
	 * @desc 获取销售总数量和总金额
	 */
	KonkaMobileSailData getKonkaMobileSailDataForTotal(KonkaMobileSailData t);

	/**
	 * 获取排名总数
	 * 
	 * @author Lianghouen
	 * @date 2014-7-3
	 * @param t
	 * @return
	 */
	Long getStarOfBanCount(KonkaMobileSailData t);

	/**
	 * 获取列表
	 * 
	 * @author Lianghouen
	 * @date 2014-7-3
	 * @param t
	 * @return
	 */
	List<HashMap> getStarOfBanList(KonkaMobileSailData t);

	/**
	 * 查询总量
	 * 
	 * @author Lianghouen
	 * @date 2014-7-4
	 * @param t
	 * @return
	 */
	HashMap getAllMoneyAndNum(KonkaMobileSailData t);

	/**
	 * 门店任务完成排名
	 * 
	 * @author zhou
	 * @since 2014-07-28
	 */
	List<KonkaStoreTaskFinishReport> getKonkaStoreTaskFinishReport(KonkaMobileSailData t);

	Long getKonkaStoreTaskFinishReportForCount(KonkaMobileSailData t);

	List<KonkaMobileSailData> getKonkaMobileSailDataForExportList(KonkaMobileSailData entity);

	Long createKonkaMobileSailDataAndBill(KonkaMobileSailData kbs, Long valueOf);

	List<KonkaMobileSailData> getKonkaMobileSailDataAndBillPaginatedList(KonkaMobileSailData entity);

	List<KonkaMobileSailData> getkonkaMobileSailDataAndBillForSwitchList(KonkaMobileSailData entity);

	void getkonkaMobileSailDataAndBillForSwitchSave(KonkaMobileSailData entity);

	List<HashMap> getkonkaMobileSailDataAndBillForSalaryList(KonkaMobileSailData entity);

	Long getkonkaMobileSailDataAndBillForSalaryCount(KonkaMobileSailData entity);

	Long getKonkaMobileSailDataAndBillCount(KonkaMobileSailData entity);

	List<KonkaMobileSailData> getKonkaMobileSailDataBillFileList(KonkaMobileSailData t99);

	// 统计所有数据的总商品数量和总价格
	List<KonkaMobileSailData> getKonkaMobileSailDataNumAndMoneyCount(KonkaMobileSailData entity);

	// 根据R3JOB编号查出零售信息
	List<HashMap<String, Object>> getKonkaMobileSailDataListByR3JobId(KonkaMobileSailData entity);

	// 根据FgsCode编号查出零售信息
	List<HashMap<String, Object>> getKonkaMobileSailDataListByFgsCode(HashMap<String, Object> map);

	List<HashMap<String, Object>> getKonkaMobileSailDataForTerminalMap(KonkaMobileSailData entity);

	List<KonkaMobileSailData> getKonkaMobileSailDataForTop10List(KonkaMobileSailData t);

	List<KonkaMobileSailData> getKonkaMobileSailDataForSaleNumList(KonkaMobileSailData t);

	/**
	 * 零售畅销机型
	 * @param t
	 * @return
	 */
	List<HashMap> getCxModelList(KonkaMobileSailData t);

	/**
	 * 零售排名
	 * @param t
	 * @return
	 */
	List<HashMap> getPMForDeptList(KonkaMobileSailData t);
}
