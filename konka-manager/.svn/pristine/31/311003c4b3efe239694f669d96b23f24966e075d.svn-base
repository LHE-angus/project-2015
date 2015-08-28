package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.EcRuleDao;
import com.ebiz.mmt.domain.EcGoodsPrice;
import com.ebiz.mmt.domain.EcRule;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-05-09 17:52:25
 */
@Service
public class EcRuleDaoSqlMapImpl extends EntityDaoSqlMapImpl<EcRule> implements EcRuleDao {
	
	@SuppressWarnings("unchecked")
	public List<EcRule> selectEcRuleForGoodsList(EcRule t) throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForList("selectEcRuleForGoodsList", t);
	}
}
