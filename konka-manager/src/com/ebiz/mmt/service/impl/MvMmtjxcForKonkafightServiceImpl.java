package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.MvMmtjxcForKonkafightDao;
import com.ebiz.mmt.domain.MvMmtjxcForKonkafight;
import com.ebiz.mmt.service.MvMmtjxcForKonkafightService;

/**
 * Code by the CodeGenerator(Version 2.2)
 * 
 * @author Hui,Gang (mr.huig [at] gmail.com)
 * @datetime 2012-06-06 09:28:21
 */
@Service
public class MvMmtjxcForKonkafightServiceImpl implements MvMmtjxcForKonkafightService {

	@Resource
	private MvMmtjxcForKonkafightDao mvMmtjxcForKonkafightDao;

	public Long createMvMmtjxcForKonkafight(MvMmtjxcForKonkafight t) {
		return this.mvMmtjxcForKonkafightDao.insertEntity(t);
	}

	public MvMmtjxcForKonkafight getMvMmtjxcForKonkafight(MvMmtjxcForKonkafight t) {
		return this.mvMmtjxcForKonkafightDao.selectEntity(t);
	}

	public Long getMvMmtjxcForKonkafightCount(MvMmtjxcForKonkafight t) {
		return this.mvMmtjxcForKonkafightDao.selectEntityCount(t);
	}

	public List<MvMmtjxcForKonkafight> getMvMmtjxcForKonkafightList(MvMmtjxcForKonkafight t) {
		return this.mvMmtjxcForKonkafightDao.selectEntityList(t);
	}

	public int modifyMvMmtjxcForKonkafight(MvMmtjxcForKonkafight t) {
		return this.mvMmtjxcForKonkafightDao.updateEntity(t);
	}

	public int removeMvMmtjxcForKonkafight(MvMmtjxcForKonkafight t) {
		return this.mvMmtjxcForKonkafightDao.deleteEntity(t);
	}

	public List<MvMmtjxcForKonkafight> getMvMmtjxcForKonkafightPaginatedList(MvMmtjxcForKonkafight t) {
		return this.mvMmtjxcForKonkafightDao.selectEntityPaginatedList(t);
	}

	@Override
	public MvMmtjxcForKonkafight getMvMmtjxcForKonkafightSumXlXe(MvMmtjxcForKonkafight t) {
		return this.mvMmtjxcForKonkafightDao.selectMvMmtjxcForKonkafightSumXlXe(t);
	}

}