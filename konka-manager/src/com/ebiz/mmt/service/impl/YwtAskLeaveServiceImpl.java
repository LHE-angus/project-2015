package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.YwtAskLeaveDao;
import com.ebiz.mmt.domain.YwtAskLeave;
import com.ebiz.mmt.service.YwtAskLeaveService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-10-20 10:01:47
 */
@Service
public class YwtAskLeaveServiceImpl implements YwtAskLeaveService {

	@Resource
	private YwtAskLeaveDao ywtAskLeaveDao;
	

	public Long createYwtAskLeave(YwtAskLeave t) {
		return this.ywtAskLeaveDao.insertEntity(t);
	}

	public YwtAskLeave getYwtAskLeave(YwtAskLeave t) {
		return this.ywtAskLeaveDao.selectEntity(t);
	}

	public Long getYwtAskLeaveCount(YwtAskLeave t) {
		return this.ywtAskLeaveDao.selectEntityCount(t);
	}

	public List<YwtAskLeave> getYwtAskLeaveList(YwtAskLeave t) {
		return this.ywtAskLeaveDao.selectEntityList(t);
	}

	public int modifyYwtAskLeave(YwtAskLeave t) {
		return this.ywtAskLeaveDao.updateEntity(t);
	}

	public int removeYwtAskLeave(YwtAskLeave t) {
		return this.ywtAskLeaveDao.deleteEntity(t);
	}

	public List<YwtAskLeave> getYwtAskLeavePaginatedList(YwtAskLeave t) {
		return this.ywtAskLeaveDao.selectEntityPaginatedList(t);
	}

}
