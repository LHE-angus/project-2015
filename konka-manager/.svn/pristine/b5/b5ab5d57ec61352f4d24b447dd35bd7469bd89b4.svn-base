package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.PeShopLeaderRecDao;
import com.ebiz.mmt.domain.PeShopLeaderRec;
import com.ebiz.mmt.service.PeShopLeaderRecService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2011-05-11 15:59:43
 */
@Service
public class PeShopLeaderRecServiceImpl implements PeShopLeaderRecService {

	@Resource
	private PeShopLeaderRecDao peShopLeaderRecDao;

	public Long createPeShopLeaderRec(PeShopLeaderRec t) {
		return this.peShopLeaderRecDao.insertEntity(t);
	}

	public PeShopLeaderRec getPeShopLeaderRec(PeShopLeaderRec t) {
		return this.peShopLeaderRecDao.selectEntity(t);
	}

	public Long getPeShopLeaderRecCount(PeShopLeaderRec t) {
		return this.peShopLeaderRecDao.selectEntityCount(t);
	}

	public List<PeShopLeaderRec> getPeShopLeaderRecList(PeShopLeaderRec t) {
		return this.peShopLeaderRecDao.selectEntityList(t);
	}

	public int modifyPeShopLeaderRec(PeShopLeaderRec t) {
		return this.peShopLeaderRecDao.updateEntity(t);
	}

	public int removePeShopLeaderRec(PeShopLeaderRec t) {
		return this.peShopLeaderRecDao.deleteEntity(t);
	}

	public List<PeShopLeaderRec> getPeShopLeaderRecPaginatedList(PeShopLeaderRec t) {
		return this.peShopLeaderRecDao.selectEntityPaginatedList(t);
	}

	/**
	 * @author Li,Yuan
	 * @version 2011-05-23
	 */
	public List<PeShopLeaderRec> getPeShopLeaderRecWithNameList(PeShopLeaderRec t) {
		return this.peShopLeaderRecDao.selectPeShopLeaderRecWithNameList(t);
	}
}
