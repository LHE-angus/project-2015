package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;
import com.ebiz.mmt.dao.KonkaParagonEquipmentCDao;
import com.ebiz.mmt.domain.KonkaParagonEquipmentC;

@Repository
public class KonkaParagonEquipmentCDaoSqlMapImpl extends
		EntityDaoSqlMapImpl<KonkaParagonEquipmentC> implements
		KonkaParagonEquipmentCDao {
	@SuppressWarnings("unchecked")
	public List<KonkaParagonEquipmentC> selectKonkaParagonEquipment(
			KonkaParagonEquipmentC t) throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForList(
				"selectKonkaParagonEquipment", t);
	}
}
