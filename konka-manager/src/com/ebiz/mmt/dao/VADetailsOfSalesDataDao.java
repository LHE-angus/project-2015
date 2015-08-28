package com.ebiz.mmt.dao;

import java.util.HashMap;
import java.util.List;

import com.ebiz.mmt.domain.VADetailsOfSalesData;
import com.ebiz.ssi.dao.EntityDao;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-09-08 12:53:15
 */
public interface VADetailsOfSalesDataDao extends EntityDao<VADetailsOfSalesData> {
	List<VADetailsOfSalesData> selectVADetailsOfSalesDataListForFX(VADetailsOfSalesData t);
	
	List<HashMap> selectVADetailsOfSalesDataListForFXNew(VADetailsOfSalesData t);
	
	List<HashMap> selectKonkaMobileSailDataForSum(VADetailsOfSalesData t);
	
	List<HashMap> selectVADetailsOfSalesDataListForMap(VADetailsOfSalesData t);
	
	List<HashMap> selectCustomModelList(VADetailsOfSalesData t);
	
	List<HashMap> selectModelInDetailsList(VADetailsOfSalesData t);
	
	List<HashMap> selectModelOutDetailsList(VADetailsOfSalesData t);
	
	List<HashMap> selectSalesDataOfDeptListForMap(VADetailsOfSalesData t);
	
	List<HashMap> selectSalesDataOfChannelListForMap(VADetailsOfSalesData t);
	
	List<HashMap> selectChannelDetailsList(VADetailsOfSalesData t);
}
