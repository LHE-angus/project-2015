package com.ebiz.mmt.dao.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.JxcSellBillDetailsDao;
import com.ebiz.mmt.domain.JxcSellBillDetails;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2011-03-03 09:42:37
 */
@Service
public class JxcSellBillDetailsDaoSqlMapImpl extends EntityDaoSqlMapImpl<JxcSellBillDetails> implements
		JxcSellBillDetailsDao {

	@Override
	public JxcSellBillDetails selectJxcSellBillDetailsStatForReal(Map<String, Object> map) {
		return (JxcSellBillDetails) super.getSqlMapClientTemplate().queryForObject(
				"selectJxcSellBillDetailsStatForReal", map);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<JxcSellBillDetails> selectJxcSellBillDetailsStatForPdReal(Map<String, Object> map) {
		return super.getSqlMapClientTemplate().queryForList("selectJxcSellBillDetailsStatForPdReal", map);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<JxcSellBillDetails> selectJxcPdModelAnalysis(Map<String, Object> map) {
		return super.getSqlMapClientTemplate().queryForList("selectJxcPdModelAnalysis", map);
	}
	
	@SuppressWarnings("unchecked")
	public List<JxcSellBillDetails> selectJxcPdTypeAnalysisResultForList(JxcSellBillDetails t) {
		return super.getSqlMapClientTemplate().queryForList("selectJxcPdTypeAnalysisResultForList", t);
	}

	@SuppressWarnings("unchecked")
	public List<JxcSellBillDetails> selectJxcCusAnalysisResultForList(JxcSellBillDetails t) {
		return super.getSqlMapClientTemplate().queryForList("selectJxcCusAnalysisResultForList", t);
	}

	@SuppressWarnings("unchecked")
	public List<JxcSellBillDetails> selectJxcDataAnalysisResultForList(JxcSellBillDetails t) {
		return super.getSqlMapClientTemplate().queryForList("selectJxcDataAnalysisResultForList", t);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<JxcSellBillDetails> selectJxcStoresAnalysis(Map<String, Object> map) {
		return super.getSqlMapClientTemplate().queryForList("selectJxcStoresAnalysis", map);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<JxcSellBillDetails> selectJxcStoresAnalysisAnother(Map<String, Object> map) {
		return super.getSqlMapClientTemplate().queryForList("selectJxcStoresAnalysisAnother", map);
	}

}
