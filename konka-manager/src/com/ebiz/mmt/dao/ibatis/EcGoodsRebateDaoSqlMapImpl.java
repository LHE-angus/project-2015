package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.EcGoodsRebateDao;
import com.ebiz.mmt.domain.EcGoodsRebate;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-12-26 16:03:16
 */
@Service
public class EcGoodsRebateDaoSqlMapImpl extends EntityDaoSqlMapImpl<EcGoodsRebate> implements EcGoodsRebateDao {

	@Override
	public Long selectEcGoodsRebateNewCount(EcGoodsRebate t) {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectEcGoodsRebateNewCount", t);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EcGoodsRebate> selectEcGoodsRebateNewPaginatedList(EcGoodsRebate t) {
		return super.getSqlMapClientTemplate().queryForList("selectEcGoodsRebateNewPaginatedList", t);
	}

}
