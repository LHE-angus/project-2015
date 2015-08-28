package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.PePositionDao;
import com.ebiz.mmt.domain.PePosition;
import com.ebiz.mmt.service.PePositionService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2011-05-12 11:39:08
 */
@Service
public class PePositionServiceImpl implements PePositionService {

	@Resource
	private PePositionDao pePositionDao;

	public Long createPePosition(PePosition t) {
		return this.pePositionDao.insertEntity(t);
	}

	public PePosition getPePosition(PePosition t) {
		return this.pePositionDao.selectEntity(t);
	}

	public Long getPePositionCount(PePosition t) {
		return this.pePositionDao.selectEntityCount(t);
	}

	public List<PePosition> getPePositionList(PePosition t) {
		return this.pePositionDao.selectEntityList(t);
	}

	public int modifyPePosition(PePosition t) {
		return this.pePositionDao.updateEntity(t);
	}

	public int removePePosition(PePosition t) {
		return this.pePositionDao.deleteEntity(t);
	}

	public List<PePosition> getPePositionPaginatedList(PePosition t) {
		return this.pePositionDao.selectEntityPaginatedList(t);
	}

	/**
	 * @author Li,Yuan
	 * @version 2011-05-12
	 */
	public Long getPePositionWithNameCount(PePosition t) {
		return this.pePositionDao.selectPePositionWithNameCount(t);
	}

	/**
	 * @author Li,Yuan
	 * @version 2011-05-12
	 */
	public List<PePosition> getPePositionWithNamePaginatedList(PePosition t) {
		return this.pePositionDao.selectPePositionWithNamePaginatedList(t);
	}
}
