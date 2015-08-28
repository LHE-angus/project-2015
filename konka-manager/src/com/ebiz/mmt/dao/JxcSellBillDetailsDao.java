package com.ebiz.mmt.dao;

import java.util.List;
import java.util.Map;

import com.ebiz.mmt.domain.JxcSellBillDetails;
import com.ebiz.ssi.dao.EntityDao;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2011-03-03 09:42:37
 */
public interface JxcSellBillDetailsDao extends EntityDao<JxcSellBillDetails> {

	JxcSellBillDetails selectJxcSellBillDetailsStatForReal(Map<String, Object> map);

	List<JxcSellBillDetails> selectJxcSellBillDetailsStatForPdReal(Map<String, Object> map);

	List<JxcSellBillDetails> selectJxcPdModelAnalysis(Map<String, Object> map);
	
	/**
	 * @author Ren Zhong
	 * @date 2011-4-19
	 */
	List<JxcSellBillDetails> selectJxcPdTypeAnalysisResultForList(JxcSellBillDetails t);
	
	List<JxcSellBillDetails> selectJxcCusAnalysisResultForList(JxcSellBillDetails t);
	
	/**
	 * @author Ren Zhong
	 * @date 2011-4-20
	 */
	List<JxcSellBillDetails> selectJxcDataAnalysisResultForList(JxcSellBillDetails t);

	List<JxcSellBillDetails> selectJxcStoresAnalysis(Map<String, Object> map);

	List<JxcSellBillDetails> selectJxcStoresAnalysisAnother(Map<String, Object> map);

}
