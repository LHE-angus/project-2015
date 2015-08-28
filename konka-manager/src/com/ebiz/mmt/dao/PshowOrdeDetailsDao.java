package com.ebiz.mmt.dao;

import java.util.List;

import com.ebiz.mmt.domain.PshowOrdeDetails;
import com.ebiz.ssi.dao.EntityDao;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-08-09 18:06:13
 */
public interface PshowOrdeDetailsDao extends EntityDao<PshowOrdeDetails> {

	List<PshowOrdeDetails> selectPshowOrdeForPDSNDetailsList(PshowOrdeDetails t);

	List<PshowOrdeDetails> selectPshowOrdeDetailsForRebatesPaginatedList(PshowOrdeDetails t);

	Long selectPshowOrdeDetailsForRebatesCount(PshowOrdeDetails t);

	String selectPshowOrdeDetailsSumRebates(PshowOrdeDetails t);

	Long selectPshowOrdeDetailsForFgsCount(PshowOrdeDetails t);

	List<PshowOrdeDetails> selectPshowOrdeDetailsForFgsPaginatedList(PshowOrdeDetails t);

	Long selectPshowOrdeDetailsNumByGoodsIdCount(PshowOrdeDetails t);

	List<PshowOrdeDetails> selectPshowOrdeDetailsFromUser(Long id);

	List<PshowOrdeDetails> selectPshowOrdeDetailsStatusFromUser(Long id);

	List<PshowOrdeDetails> selectPshowOrdeDetailsForPayList(PshowOrdeDetails t);

	List<PshowOrdeDetails> selectPshowOrdeDetailsAndDaySellForTjList(PshowOrdeDetails t);

	List<PshowOrdeDetails> selectPshowOrdeDetailsByProdTypeList(PshowOrdeDetails t);

}
