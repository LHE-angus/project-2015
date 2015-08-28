package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.EcGoodsIntegralDao;
import com.ebiz.mmt.domain.EcGoodsIntegral;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-12-11 10:26:51
 */
@Service
public class EcGoodsIntegralDaoSqlMapImpl extends EntityDaoSqlMapImpl<EcGoodsIntegral> implements EcGoodsIntegralDao {

	@Override
	public Long selectEcGoodsIntegralNewCount(EcGoodsIntegral t) {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectEcGoodsIntegralNewCount", t);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EcGoodsIntegral> selectEcGoodsIntegralNewPaginatedList(EcGoodsIntegral t) {

		return super.getSqlMapClientTemplate().queryForList("selectEcGoodsIntegralNewPaginatedList", t);
	}

}
