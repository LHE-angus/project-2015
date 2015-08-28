package com.ebiz.mmt.dao;

import java.util.List;

import com.ebiz.mmt.domain.KonkaJxcStockBillDetails;
import com.ebiz.ssi.dao.EntityDao;

/**
 * @author Wu,Yang
 * @version 2011-10-12 16:24
 */
public interface KonkaJxcStockBillDetailsDao extends EntityDao<KonkaJxcStockBillDetails> {
	/** Li,Ka : 查询包含仓库信息的记录 */
	KonkaJxcStockBillDetails selectKonkaJxcStockBillDetailsWithStoreInfo(KonkaJxcStockBillDetails t);
	
	List<KonkaJxcStockBillDetails> selectKonkaJxcStockBillDetailsWithStoreInfoList(KonkaJxcStockBillDetails t);
}
