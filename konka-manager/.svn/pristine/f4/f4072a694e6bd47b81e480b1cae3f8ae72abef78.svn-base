package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.YwtAttendanceDayDao;
import com.ebiz.mmt.domain.YwtAttendanceDay;
import com.ebiz.mmt.service.YwtAttendanceDayService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-10-20 10:01:47
 */
@Service
public class YwtAttendanceDayServiceImpl implements YwtAttendanceDayService {

	@Resource
	private YwtAttendanceDayDao ywtAttendanceDayDao;
	

	public Long createYwtAttendanceDay(YwtAttendanceDay t) {
		return this.ywtAttendanceDayDao.insertEntity(t);
	}

	public YwtAttendanceDay getYwtAttendanceDay(YwtAttendanceDay t) {
		return this.ywtAttendanceDayDao.selectEntity(t);
	}

	public Long getYwtAttendanceDayCount(YwtAttendanceDay t) {
		return this.ywtAttendanceDayDao.selectEntityCount(t);
	}

	public List<YwtAttendanceDay> getYwtAttendanceDayList(YwtAttendanceDay t) {
		return this.ywtAttendanceDayDao.selectEntityList(t);
	}

	public int modifyYwtAttendanceDay(YwtAttendanceDay t) {
		return this.ywtAttendanceDayDao.updateEntity(t);
	}

	public int removeYwtAttendanceDay(YwtAttendanceDay t) {
		return this.ywtAttendanceDayDao.deleteEntity(t);
	}

	public List<YwtAttendanceDay> getYwtAttendanceDayPaginatedList(YwtAttendanceDay t) {
		return this.ywtAttendanceDayDao.selectEntityPaginatedList(t);
	}

	@Override
	public Long getYwtAttendanceDayLBCount(YwtAttendanceDay v) {
		return this.ywtAttendanceDayDao.selectYwtAttendanceDayLBCount(v);
	}

	@Override
	public List<YwtAttendanceDay> getYwtAttendanceDayLBPaginatedList(
			YwtAttendanceDay v) {
		return this.ywtAttendanceDayDao.selectYwtAttendanceDayLBPaginatedList(v);
	}

}
