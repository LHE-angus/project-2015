package com.ebiz.mmt.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkamobilesaledatasDao;
import com.ebiz.mmt.domain.Konkamobilesaledatas;
import com.ebiz.mmt.service.KonkamobilesaledatasService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-05-01 15:59:22
 */
@Service
public class KonkamobilesaledatasServiceImpl implements
		KonkamobilesaledatasService {

	@Resource
	private KonkamobilesaledatasDao konkamobilesaledatasDao;

	public Long createKonkamobilesaledatas(Konkamobilesaledatas t) {
		return this.konkamobilesaledatasDao.insertEntity(t);
	}

	public Konkamobilesaledatas getKonkamobilesaledatas(Konkamobilesaledatas t) {
		return this.konkamobilesaledatasDao.selectEntity(t);
	}

	public Long getKonkamobilesaledatasCount(Konkamobilesaledatas t) {
		return this.konkamobilesaledatasDao.selectEntityCount(t);
	}

	public List<Konkamobilesaledatas> getKonkamobilesaledatasList(
			Konkamobilesaledatas t) {
		return this.konkamobilesaledatasDao.selectEntityList(t);
	}

	public int modifyKonkamobilesaledatas(Konkamobilesaledatas t) {
		return this.konkamobilesaledatasDao.updateEntity(t);
	}

	public int removeKonkamobilesaledatas(Konkamobilesaledatas t) {
		return this.konkamobilesaledatasDao.deleteEntity(t);
	}

	public List<Konkamobilesaledatas> getKonkamobilesaledatasPaginatedList(
			Konkamobilesaledatas t) {
		return this.konkamobilesaledatasDao.selectEntityPaginatedList(t);
	}

	public Long getKonkamobilesaledatasCountMonth(Konkamobilesaledatas t) {
		return this.konkamobilesaledatasDao
				.getKonkamobilesaledatasCountMonth(t);
	}

	public List<HashMap<String, String>> getKonkamobilesaledatasPaginatedListMonth(
			Konkamobilesaledatas t) {
		return this.konkamobilesaledatasDao
				.getKonkamobilesaledatasPaginatedListMonth(t);
	}

	public Long getKonkamobilesaledatasCountDay(Konkamobilesaledatas t) {
		return this.konkamobilesaledatasDao.getKonkamobilesaledatasCountDay(t);
	}

	public List<HashMap<String, String>> getKonkamobilesaledatasPaginatedListDay(
			Konkamobilesaledatas t) {
		return this.konkamobilesaledatasDao
				.getKonkamobilesaledatasPaginatedListDay(t);
	}

	public Long getKonkamobilesaledatasCountSeason(Konkamobilesaledatas t) {
		return this.konkamobilesaledatasDao
				.getKonkamobilesaledatasCountSeason(t);
	}

	public List<HashMap<String, String>> getKonkamobilesaledatasPaginatedListSeason(
			Konkamobilesaledatas t) {
		return this.konkamobilesaledatasDao
				.getKonkamobilesaledatasPaginatedListSeason(t);
	}

	public Long getKonkamobilesaledatasCountYear(Konkamobilesaledatas t) {
		return this.konkamobilesaledatasDao.getKonkamobilesaledatasCountYear(t);
	}

	public List<HashMap<String, String>> gettKonkamobilesaledatasPaginatedListYear(
			Konkamobilesaledatas t) {
		return this.konkamobilesaledatasDao
				.gettKonkamobilesaledatasPaginatedListYear(t);
	}

	public List<HashMap<String, String>> getKonkamobilesaledatasMobile(
			Konkamobilesaledatas t) {
		return this.konkamobilesaledatasDao.getKonkamobilesaledatasMobile(t);
	}

	public List<HashMap<String, String>> getKonkamobilesaledatasMobileFlot(
			Konkamobilesaledatas t) {
		return this.konkamobilesaledatasDao
				.getKonkamobilesaledatasMobileFlot(t);
	}

	public List<HashMap<String, String>> getKonkamobilesaledatasMobileFlot3(
			Konkamobilesaledatas t) {
		return this.konkamobilesaledatasDao
				.getKonkamobilesaledatasMobileFlot3(t);
	}

}
