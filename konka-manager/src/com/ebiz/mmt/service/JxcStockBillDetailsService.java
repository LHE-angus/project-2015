package com.ebiz.mmt.service;

import java.util.List;
import java.util.Map;

import com.ebiz.mmt.domain.JxcStockBillDetails;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2011-03-03 09:42:37
 */
@SuppressWarnings("unchecked")
public interface JxcStockBillDetailsService {

	Long createJxcStockBillDetails(JxcStockBillDetails t);

	int modifyJxcStockBillDetails(JxcStockBillDetails t);

	int removeJxcStockBillDetails(JxcStockBillDetails t);

	JxcStockBillDetails getJxcStockBillDetails(JxcStockBillDetails t);

	JxcStockBillDetails getJxcStockBillDetailsStatForReal(Map<String, Object> map);

	List<JxcStockBillDetails> getJxcStockBillDetailsList(JxcStockBillDetails t);

	Long getJxcStockBillDetailsCount(JxcStockBillDetails t);

	List<JxcStockBillDetails> getJxcStockBillDetailsPaginatedList(JxcStockBillDetails t);
	

	List<Map> getJxcstockBillDetailsSumPdCountList(JxcStockBillDetails t);
	
	Long getJxcstockBillDetailsSumPdCountNotSrc(JxcStockBillDetails t);
	

}