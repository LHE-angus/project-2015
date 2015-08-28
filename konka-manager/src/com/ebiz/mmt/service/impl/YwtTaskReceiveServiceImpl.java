package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.YwtTaskDao;
import com.ebiz.mmt.dao.YwtTaskReceiveDao;
import com.ebiz.mmt.domain.YwtTask;
import com.ebiz.mmt.domain.YwtTaskReceive;
import com.ebiz.mmt.service.YwtTaskReceiveService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-10-20 10:01:47
 */
@Service
public class YwtTaskReceiveServiceImpl implements YwtTaskReceiveService {

	@Resource
	private YwtTaskReceiveDao ywtTaskReceiveDao;
	
	public Long createYwtTaskReceive(YwtTaskReceive t) {
		return this.ywtTaskReceiveDao.insertEntity(t);
	}

	public YwtTaskReceive getYwtTaskReceive(YwtTaskReceive t) {
		return this.ywtTaskReceiveDao.selectEntity(t);
	}

	public Long getYwtTaskReceiveCount(YwtTaskReceive t) {
		return this.ywtTaskReceiveDao.selectEntityCount(t);
	}

	public List<YwtTaskReceive> getYwtTaskReceiveList(YwtTaskReceive t) {
		return this.ywtTaskReceiveDao.selectEntityList(t);
	}

	public int modifyYwtTaskReceive(YwtTaskReceive t) {
		return this.ywtTaskReceiveDao.updateEntity(t);
	}

	public int removeYwtTaskReceive(YwtTaskReceive t) {
		return this.ywtTaskReceiveDao.deleteEntity(t);
	}

	public List<YwtTaskReceive> getYwtTaskReceivePaginatedList(YwtTaskReceive t) {
		return this.ywtTaskReceiveDao.selectEntityPaginatedList(t);
	}

	@Override
	public Long getYwtTaskReceiveLBCount(YwtTaskReceive v) {
		return this.ywtTaskReceiveDao.selectYwtTaskReceiveLBCount(v);
	}

	@Override
	public List<YwtTaskReceive> getYwtTaskReceiveLBPaginatedList(YwtTaskReceive v) {
		return this.ywtTaskReceiveDao.selectYwtTaskReceiveLBPaginatedList(v);
	}

	@Override
	public List<YwtTaskReceive> getCurTaskSub(YwtTaskReceive v) {
		
		return this.ywtTaskReceiveDao.selectCurTaskSub(v);
	}
	public Long updateYwtTaskReceive(YwtTaskReceive v) {
		
		return this.ywtTaskReceiveDao.updateYwtTaskReceive(v);
	}

	@Override
	public List<YwtTaskReceive> selectYwtTaskReceiveTaskName(YwtTaskReceive t) {
		
		return this.ywtTaskReceiveDao.selectYwtTaskReceiveTaskName(t);
	}
	
	public List<YwtTaskReceive> selectSubTaskList(YwtTaskReceive v) {
		
		return this.ywtTaskReceiveDao.selectSubTaskList(v);
		
	}

}
