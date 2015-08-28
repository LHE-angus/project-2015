package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.PshowOrdeDetailsDao;
import com.ebiz.mmt.domain.PshowOrdeDetails;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-08-09 18:06:13
 */
@Service
public class PshowOrdeDetailsDaoSqlMapImpl extends EntityDaoSqlMapImpl<PshowOrdeDetails> implements PshowOrdeDetailsDao {

	@SuppressWarnings("unchecked")
	public List<PshowOrdeDetails> selectPshowOrdeForPDSNDetailsList(PshowOrdeDetails t) {
		return this.getSqlMapClientTemplate().queryForList("selectPshowOrdeForPDSNDetailsList", t);
	}

	@SuppressWarnings("unchecked")
	public List<PshowOrdeDetails> selectPshowOrdeDetailsForRebatesPaginatedList(PshowOrdeDetails t) {
		return this.getSqlMapClientTemplate().queryForList("selectPshowOrdeDetailsForRebatesPaginatedList", t);
	}

	public Long selectPshowOrdeDetailsForRebatesCount(PshowOrdeDetails t) {
		return (Long) this.getSqlMapClientTemplate().queryForObject("selectPshowOrdeDetailsForRebatesCount", t);
	}

	public String selectPshowOrdeDetailsSumRebates(PshowOrdeDetails t) {
		return (String) this.getSqlMapClientTemplate().queryForObject("selectPshowOrdeDetailsSumRebates", t);
	}

	public Long selectPshowOrdeDetailsForFgsCount(PshowOrdeDetails t) {
		return (Long) this.getSqlMapClientTemplate().queryForObject("selectPshowOrdeDetailsForFgsCount", t);
	}

	@SuppressWarnings("unchecked")
	public List<PshowOrdeDetails> selectPshowOrdeDetailsForFgsPaginatedList(PshowOrdeDetails t) {
		return this.getSqlMapClientTemplate().queryForList("selectPshowOrdeDetailsForFgsPaginatedList", t);
	}

	public Long selectPshowOrdeDetailsNumByGoodsIdCount(PshowOrdeDetails t) {
		return (Long) this.getSqlMapClientTemplate().queryForObject("selectPshowOrdeDetailsNumByGoodsIdCount", t);
	}

	@SuppressWarnings("unchecked")
	public List<PshowOrdeDetails> selectPshowOrdeDetailsFromUser(Long id) {
		return this.getSqlMapClientTemplate().queryForList("selectPshowOrdeDetailsFromUser", id);
	}

	@SuppressWarnings("unchecked")
	public List<PshowOrdeDetails> selectPshowOrdeDetailsStatusFromUser(Long id) {
		return this.getSqlMapClientTemplate().queryForList("selectPshowOrdeDetailsStatusFromUser", id);
	}

	@SuppressWarnings("unchecked")
	public List<PshowOrdeDetails> selectPshowOrdeDetailsForPayList(PshowOrdeDetails t) {
		return this.getSqlMapClientTemplate().queryForList("selectPshowOrdeDetailsForPayList", t);
	}

	@SuppressWarnings("unchecked")
	public List<PshowOrdeDetails> selectPshowOrdeDetailsAndDaySellForTjList(PshowOrdeDetails t) {
		return this.getSqlMapClientTemplate().queryForList("selectPshowOrdeDetailsAndDaySellForTjList", t);
	}

	@SuppressWarnings("unchecked")
	public List<PshowOrdeDetails> selectPshowOrdeDetailsByProdTypeList(PshowOrdeDetails t) {
		return this.getSqlMapClientTemplate().queryForList("selectPshowOrdeDetailsByProdTypeList", t);
	}

}
