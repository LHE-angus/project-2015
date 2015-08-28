package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.YwtAttendanceSignTime;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-10-20 10:01:47
 */
public interface YwtAttendanceSignTimeService {

	Long createYwtAttendanceSignTime(YwtAttendanceSignTime t);

	int modifyYwtAttendanceSignTime(YwtAttendanceSignTime t);

	int removeYwtAttendanceSignTime(YwtAttendanceSignTime t);

	YwtAttendanceSignTime getYwtAttendanceSignTime(YwtAttendanceSignTime t);

	List<YwtAttendanceSignTime> getYwtAttendanceSignTimeList(YwtAttendanceSignTime t);

	Long getYwtAttendanceSignTimeCount(YwtAttendanceSignTime t);

	List<YwtAttendanceSignTime> getYwtAttendanceSignTimePaginatedList(YwtAttendanceSignTime t);
	
	/**
	 * 分公司取自己的没有就取总部的
	 * @param v
	 * @return
	 */
	List<YwtAttendanceSignTime> getYwtAttendanceSignTimeSignTimeList(YwtAttendanceSignTime v);

	List<YwtAttendanceSignTime> selectAllYwtAttendanceSignTimeSignTimeList(
			YwtAttendanceSignTime v);

}