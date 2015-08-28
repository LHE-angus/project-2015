package com.ebiz.mmt.dao.ibatis;

import java.util.HashMap;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;
import com.ebiz.mmt.dao.KonkaParagonEtcashDao;
import com.ebiz.mmt.domain.KonkaParagonEtcash;

@Repository
public class KonkaParagonEtcashDaoSqlMapImpl extends
		EntityDaoSqlMapImpl<KonkaParagonEtcash> implements
		KonkaParagonEtcashDao {

	@SuppressWarnings("unchecked")
	public List<HashMap<String, String>> selectKonkaParagonEtcashListByOne(
			KonkaParagonEtcash t) throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForList(
				"selectKonkaParagonEtcashListByOne", t);
	}
}
