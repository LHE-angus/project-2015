package com.ebiz.mmt.dao;

import java.sql.SQLException;

import com.ebiz.mmt.domain.KonkaBbItr2Import;
import com.ebiz.ssi.dao.EntityDao;

public interface KonkaBbItr2ImportDao extends EntityDao<KonkaBbItr2Import> {
	void removeAllKonkaBbItr2Import() throws SQLException;

	String selectMaxSyncTime() throws SQLException;
}