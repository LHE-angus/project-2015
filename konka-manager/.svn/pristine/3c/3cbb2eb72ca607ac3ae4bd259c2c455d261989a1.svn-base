package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaXxGoodsDao;
import com.ebiz.mmt.domain.KonkaXxGoods;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2012-01-09 09:19:48
 */
@Service
public class KonkaXxGoodsDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaXxGoods> implements KonkaXxGoodsDao {

	/**
	 * @author Jiang,JiaYong
	 * @version 2012-01-09
	 */
	@SuppressWarnings("unchecked")
	public List<KonkaXxGoods> selectKonkaXxGoodsPaginatedListIncludeRelevanceInfo(KonkaXxGoods t) throws DataAccessException {
		return this.getSqlMapClientTemplate().queryForList("selectKonkaXxGoodsPaginatedListIncludeRelevanceInfo", t);
	}

}
