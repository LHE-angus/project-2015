package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.YwtAttendanceSignTimeDao;
import com.ebiz.mmt.domain.YwtAttendanceSignTime;
import com.ebiz.mmt.service.YwtAttendanceSignTimeService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-10-20 10:01:47
 */
@Service
public class YwtAttendanceSignTimeServiceImpl implements YwtAttendanceSignTimeService {

	@Resource
	private YwtAttendanceSignTimeDao ywtAttendanceSignTimeDao;
	

	public Long createYwtAttendanceSignTime(YwtAttendanceSignTime t) {
		return this.ywtAttendanceSignTimeDao.insertEntity(t);
	}

	public YwtAttendanceSignTime getYwtAttendanceSignTime(YwtAttendanceSignTime t) {
		return this.ywtAttendanceSignTimeDao.selectEntity(t);
	}

	public Long getYwtAttendanceSignTimeCount(YwtAttendanceSignTime t) {
		return this.ywtAttendanceSignTimeDao.selectEntityCount(t);
	}

	public List<YwtAttendanceSignTime> getYwtAttendanceSignTimeList(YwtAttendanceSignTime t) {
		return this.ywtAttendanceSignTimeDao.selectEntityList(t);
	}

	public int modifyYwtAttendanceSignTime(YwtAttendanceSignTime t) {
		return this.ywtAttendanceSignTimeDao.updateEntity(t);
	}

	public int removeYwtAttendanceSignTime(YwtAttendanceSignTime t) {
		return this.ywtAttendanceSignTimeDao.deleteEntity(t);
	}

	public List<YwtAttendanceSignTime> getYwtAttendanceSignTimePaginatedList(YwtAttendanceSignTime t) {
		return this.ywtAttendanceSignTimeDao.selectEntityPaginatedList(t);
	}

	@Override
	public List<YwtAttendanceSignTime> getYwtAttendanceSignTimeSignTimeList(
			YwtAttendanceSignTime v) {
		return this.ywtAttendanceSignTimeDao.selectYwtAttendanceSignTimeSignTimeList(v);
	}
	
	@Override
	public List<YwtAttendanceSignTime> selectAllYwtAttendanceSignTimeSignTimeList(
			YwtAttendanceSignTime v) {
		return this.ywtAttendanceSignTimeDao.selectAllYwtAttendanceSignTimeSignTimeList(v);
	}

}
