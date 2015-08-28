package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaJxcStockBillDetailsDao;
import com.ebiz.mmt.domain.KonkaJxcStockBillDetails;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * @author Wu,Yang
 * @version 2011-10-12 16:24
 */
@Service
@SuppressWarnings("unchecked")
public class KonkaJxcStockBillDetailsDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaJxcStockBillDetails> implements KonkaJxcStockBillDetailsDao {

	/** Li,Ka : 查询包含仓库信息的记录 */
	public KonkaJxcStockBillDetails selectKonkaJxcStockBillDetailsWithStoreInfo(KonkaJxcStockBillDetails t) {
		return (KonkaJxcStockBillDetails) super.getSqlMapClientTemplate().queryForObject("selectKonkaJxcStockBillDetailsWithStoreInfo", t);
	}

	
	public List<KonkaJxcStockBillDetails> selectKonkaJxcStockBillDetailsWithStoreInfoList(KonkaJxcStockBillDetails t) {
		return super.getSqlMapClientTemplate().queryForList("selectKonkaJxcStockBillDetailsWithStoreInfoList", t);
	}

}
