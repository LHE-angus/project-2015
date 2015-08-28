package com.ebiz.mmt.service;

import java.util.HashMap;
import java.util.List;

import com.ebiz.mmt.domain.VADetailsOfSalesData;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-09-08 12:53:15
 */
public interface VADetailsOfSalesDataService {

	Long createVADetailsOfSalesData(VADetailsOfSalesData t);

	int modifyVADetailsOfSalesData(VADetailsOfSalesData t);

	int removeVADetailsOfSalesData(VADetailsOfSalesData t);

	VADetailsOfSalesData getVADetailsOfSalesData(VADetailsOfSalesData t);

	List<VADetailsOfSalesData> getVADetailsOfSalesDataList(VADetailsOfSalesData t);

	Long getVADetailsOfSalesDataCount(VADetailsOfSalesData t);

	List<VADetailsOfSalesData> getVADetailsOfSalesDataPaginatedList(VADetailsOfSalesData t);

	List<VADetailsOfSalesData> getVADetailsOfSalesDataListForFX(VADetailsOfSalesData t);
	
	List<HashMap> getVADetailsOfSalesDataListForFXNew(VADetailsOfSalesData t);

	List<HashMap> getKonkaMobileSailDataForSum(VADetailsOfSalesData t);
	
	/**
	 * 进销存首页新的查询方法
	 * @param t
	 * @return
	 * @author angus_vm
	 * @date 2015-03-31
	 */
	public List<HashMap> getVADetailsOfSalesDataListForMap(VADetailsOfSalesData t);
	
	/**
	 * 根据客户信息，查询型号信息
	 * @param t
	 * @return
	 * @author angus_vm
	 * @date 2015-03-31
	 */
	public List<HashMap> getCustomModelList(VADetailsOfSalesData t);
	
	/**
	 * 查询型号记录-进货
	 * @param t
	 * @return
	 */
	public List<HashMap> getModelInDetailsList(VADetailsOfSalesData t);
	
	/**
	 * 查询型号记录-销售
	 * @param t
	 * @return
	 */
	public List<HashMap> getModelOutDetailsList(VADetailsOfSalesData t);
	
	/**
	 * 按部门维度查询
	 * @param t
	 * @return
	 */
	public List<HashMap> getSalesDataOfDeptListForMap(VADetailsOfSalesData t);
	
	/**
	 * 按渠道维度查询
	 * @param t
	 * @return
	 */
	public List<HashMap> getSalesDataOfChannelListForMap(VADetailsOfSalesData t);
	
	/**
	 * 查看渠道细分类型数据
	 * @param t
	 * @return
	 */
	public List<HashMap> getChannelDetailsList(VADetailsOfSalesData t);
}