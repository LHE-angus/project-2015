package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.ebiz.mmt.dao.KonkaParagonShowmanreDao;
import com.ebiz.mmt.domain.KonkaParagonShowmanre;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

@Repository
public class KonkaParagonShowmanreDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaParagonShowmanre> implements KonkaParagonShowmanreDao {

	public KonkaParagonShowmanre selectEntityForEdit(KonkaParagonShowmanre t) {
		return (KonkaParagonShowmanre) super.getSqlMapClientTemplate().queryForObject("selectKonkaParagonShowmanreForEdit", t);
	}

	@Override
	public Long selectEntityCountForShowOut(KonkaParagonShowmanre t)
			throws DataAccessException {
		return (Long) super.getSqlMapClientTemplate().queryForObject(
				"selectKonkaParagonShowmanreCountForShowOut", t);
	}

	@SuppressWarnings("unchecked")
	public List<KonkaParagonShowmanre> selectEntityPaginatedListForShowOut(
			KonkaParagonShowmanre t) throws DataAccessException {
		return this.getSqlMapClientTemplate().queryForList(
				"selectKonkaParagonShowmanrePaginatedListForShowOut", t);
	}

	
}

