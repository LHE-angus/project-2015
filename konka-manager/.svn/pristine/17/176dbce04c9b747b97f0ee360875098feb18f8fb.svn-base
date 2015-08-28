package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.FighterInfoDao;
import com.ebiz.mmt.domain.FighterInfo;
import com.ebiz.mmt.service.FighterInfoService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-10-25 11:16:14
 */
@Service
public class FighterInfoServiceImpl implements FighterInfoService {

	@Resource
	private FighterInfoDao fighterInfoDao;
	

	public Long createFighterInfo(FighterInfo t) {
		return this.fighterInfoDao.insertEntity(t);
	}

	public FighterInfo getFighterInfo(FighterInfo t) {
		return this.fighterInfoDao.selectEntity(t);
	}

	public Long getFighterInfoCount(FighterInfo t) {
		return this.fighterInfoDao.selectEntityCount(t);
	}

	public List<FighterInfo> getFighterInfoList(FighterInfo t) {
		return this.fighterInfoDao.selectEntityList(t);
	}

	public int modifyFighterInfo(FighterInfo t) {
		return this.fighterInfoDao.updateEntity(t);
	}

	public int removeFighterInfo(FighterInfo t) {
		return this.fighterInfoDao.deleteEntity(t);
	}

	public List<FighterInfo> getFighterInfoPaginatedList(FighterInfo t) {
		return this.fighterInfoDao.selectEntityPaginatedList(t);
	}

}
