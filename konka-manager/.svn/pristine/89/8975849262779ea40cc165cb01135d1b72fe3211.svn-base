package com.ebiz.mmt.service.impl;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaBbItr2ImportDao;
import com.ebiz.mmt.domain.KonkaBbItr2Import;
import com.ebiz.mmt.service.KonkaBbItr2ImportService;


@Service
public class KonkaBbItr2ImportServiceImpl implements KonkaBbItr2ImportService {

	@Resource
	private KonkaBbItr2ImportDao konkaBbItr2ImportDao;
	

	@Override
	public Long createKonkaBbItr2Import(KonkaBbItr2Import t) {
		return this.konkaBbItr2ImportDao.insertEntity(t);
	}

	@Override
	public KonkaBbItr2Import getKonkaBbItr2Import(KonkaBbItr2Import t) {
		return this.konkaBbItr2ImportDao.selectEntity(t);
	}

	@Override
	public Long getKonkaBbItr2ImportCount(KonkaBbItr2Import t) {
		return this.konkaBbItr2ImportDao.selectEntityCount(t);
	}

	@Override
	public List<KonkaBbItr2Import> getKonkaBbItr2ImportList(KonkaBbItr2Import t) {
		return this.konkaBbItr2ImportDao.selectEntityList(t);
	}

	@Override
	public int modifyKonkaBbItr2Import(KonkaBbItr2Import t) {
		return this.konkaBbItr2ImportDao.updateEntity(t);
	}

	@Override
	public int removeKonkaBbItr2Import(KonkaBbItr2Import t) {
		return this.konkaBbItr2ImportDao.deleteEntity(t);
	}

	@Override
	public List<KonkaBbItr2Import> getKonkaBbItr2ImportPaginatedList(KonkaBbItr2Import t) {
		return this.konkaBbItr2ImportDao.selectEntityPaginatedList(t);
	}

	@Override
	public void removeAllKonkaBbItr2Import() {
		try {
			this.konkaBbItr2ImportDao.removeAllKonkaBbItr2Import();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public String getMaxSyncTime() throws SQLException {
		try {
			return this.konkaBbItr2ImportDao.selectMaxSyncTime();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
