package com.ebiz.mmt.dao.ibatis;

import java.util.HashMap;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;
import com.ebiz.mmt.dao.KonkaParagonEquipmentJDao;
import com.ebiz.mmt.domain.KonkaParagonEquipmentJ;

@Repository
public class KonkaParagonEquipmentJDaoSqlMapImpl extends
		EntityDaoSqlMapImpl<KonkaParagonEquipmentJ> implements
		KonkaParagonEquipmentJDao {

	public Long selectEquipmentNum(KonkaParagonEquipmentJ t)
			throws DataAccessException {

		return (Long) getSqlMapClientTemplate().queryForObject(
				"selectEquipmentNum", t);
	}

	@SuppressWarnings("unchecked")
	public List<HashMap<String, String>> selectKonkaParagonEquipmentPaginatedList(
			KonkaParagonEquipmentJ t) throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForList(
				"selectKonkaParagonEquipmentPaginatedList", t);
	}

}
