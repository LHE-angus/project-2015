package com.ebiz.mmt.dao.ibatis;

import java.sql.SQLException;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaBbItr2ImportDao;
import com.ebiz.mmt.domain.KonkaBbItr2Import;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;


@Service
public class KonkaBbItr2ImportDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaBbItr2Import> implements KonkaBbItr2ImportDao {

	@Override
	public void removeAllKonkaBbItr2Import() throws SQLException {
		super.getSqlMapClient().delete("deleteAllKonkaBbItr2Import");
	}
	@Override
	public String selectMaxSyncTime() throws SQLException {
		return (String) super.getSqlMapClient().queryForObject("selectKonkaBbItr2Import4LastSyncTime");
	}
}
