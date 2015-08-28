package com.ebiz.mmt.dao.ibatis;

import java.util.HashMap;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;
import com.ebiz.mmt.dao.KonkaParagonAttentionCDao;
import com.ebiz.mmt.domain.KonkaParagonAttentionC;

@Repository
public class KonkaParagonAttentionCDaoSqlMapImpl extends
		EntityDaoSqlMapImpl<KonkaParagonAttentionC> implements
		KonkaParagonAttentionCDao {

	@SuppressWarnings("unchecked")
	public List<HashMap<String, String>> selectKonkaParagonAttentionPaginatedList(
			KonkaParagonAttentionC t) throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForList(
				"selectKonkaParagonAttentionPaginatedList", t);
	}
}
