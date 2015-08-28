package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.StdEntpKeysKeysDao;
import com.ebiz.mmt.domain.StdEntpKeysKeys;
import com.ebiz.mmt.service.StdEntpKeysKeysService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2011-09-20 15:43:53
 */
@Service
public class StdEntpKeysKeysServiceImpl implements StdEntpKeysKeysService {

	@Resource
	private StdEntpKeysKeysDao stdEntpKeysKeysDao;
	

	public Long createStdEntpKeysKeys(StdEntpKeysKeys t) {
		return this.stdEntpKeysKeysDao.insertEntity(t);
	}

	public StdEntpKeysKeys getStdEntpKeysKeys(StdEntpKeysKeys t) {
		return this.stdEntpKeysKeysDao.selectEntity(t);
	}

	public Long getStdEntpKeysKeysCount(StdEntpKeysKeys t) {
		return this.stdEntpKeysKeysDao.selectEntityCount(t);
	}

	public List<StdEntpKeysKeys> getStdEntpKeysKeysList(StdEntpKeysKeys t) {
		return this.stdEntpKeysKeysDao.selectEntityList(t);
	}

	public int modifyStdEntpKeysKeys(StdEntpKeysKeys t) {
		return this.stdEntpKeysKeysDao.updateEntity(t);
	}

	public int removeStdEntpKeysKeys(StdEntpKeysKeys t) {
		return this.stdEntpKeysKeysDao.deleteEntity(t);
	}

	public List<StdEntpKeysKeys> getStdEntpKeysKeysPaginatedList(StdEntpKeysKeys t) {
		return this.stdEntpKeysKeysDao.selectEntityPaginatedList(t);
	}

}
