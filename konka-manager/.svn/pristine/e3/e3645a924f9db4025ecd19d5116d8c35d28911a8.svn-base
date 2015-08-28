package com.ebiz.mmt.dao.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.JxcStockBillDetailsDao;
import com.ebiz.mmt.domain.JxcStockBillDetails;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2011-03-03 09:42:37
 */
@Service
@SuppressWarnings("unchecked")
public class JxcStockBillDetailsDaoSqlMapImpl extends EntityDaoSqlMapImpl<JxcStockBillDetails> implements
		JxcStockBillDetailsDao {

	@Override
	public JxcStockBillDetails selectJxcStockBillDetailStatForReal(Map<String, Object> map) {
		return (JxcStockBillDetails) super.getSqlMapClientTemplate().queryForObject(
				"selectJxcStockBillDetailsStatForReal", map);
	}


	@Override
	public List<Map> selectJxcstockBillDetailsSumPdCountList(JxcStockBillDetails t) {
		return super.getSqlMapClientTemplate().queryForList("selectJxcstockBillDetailsSumPdCountList",t);
	}

	@Override
	public Long  selectJxcstockBillDetailsSumPdCountNotSrc(JxcStockBillDetails t) {
		return (Long)super.getSqlMapClientTemplate().queryForObject("selectJxcstockBillDetailsSumPdCountNotSrc",t);
	}

}
