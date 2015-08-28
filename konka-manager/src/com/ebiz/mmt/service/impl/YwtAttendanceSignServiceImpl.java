package com.ebiz.mmt.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.YwtAttendanceSignDao;
import com.ebiz.mmt.domain.YwtAttendanceSign;
import com.ebiz.mmt.service.YwtAttendanceSignService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-10-20 10:01:47
 */
@Service
public class YwtAttendanceSignServiceImpl implements YwtAttendanceSignService {

	@Resource
	private YwtAttendanceSignDao ywtAttendanceSignDao;
	

	public Long createYwtAttendanceSign(YwtAttendanceSign t) {
		return this.ywtAttendanceSignDao.insertEntity(t);
	}

	public YwtAttendanceSign getYwtAttendanceSign(YwtAttendanceSign t) {
		return this.ywtAttendanceSignDao.selectEntity(t);
	}

	public Long getYwtAttendanceSignCount(YwtAttendanceSign t) {
		return this.ywtAttendanceSignDao.selectEntityCount(t);
	}

	public List<YwtAttendanceSign> getYwtAttendanceSignList(YwtAttendanceSign t) {
		return this.ywtAttendanceSignDao.selectEntityList(t);
	}

	public int modifyYwtAttendanceSign(YwtAttendanceSign t) {
		return this.ywtAttendanceSignDao.updateEntity(t);
	}

	public int removeYwtAttendanceSign(YwtAttendanceSign t) {
		return this.ywtAttendanceSignDao.deleteEntity(t);
	}

	public List<YwtAttendanceSign> getYwtAttendanceSignPaginatedList(YwtAttendanceSign t) {
		return this.ywtAttendanceSignDao.selectEntityPaginatedList(t);
	}

	@Override
	public List<Map<String, Object>> getYwtAttendanceSignLBPaginatedList(
			YwtAttendanceSign v) {
		return this.ywtAttendanceSignDao.selectYwtAttendanceSignLBPaginatedList(v);
	}

	@Override
	public Long gettYwtAttendanceSignLBCount(YwtAttendanceSign v) {
		return this.ywtAttendanceSignDao.selectYwtAttendanceSignLBCount(v);
	}

	@Override
	public Long gettYwtAttendanceSignMobileCount(YwtAttendanceSign v) {
		return this.ywtAttendanceSignDao.selectYwtAttendanceSignMobileCount(v);
	}

	@Override
	public List<Map<String, Object>> getYwtAttendanceSignMobilePaginatedList(
			YwtAttendanceSign v) {
		return this.ywtAttendanceSignDao.selectYwtAttendanceSignMobilePaginatedList(v);
	}

	@Override
	public List<Map<String, Object>> getMonthYwtAttendanceSignList(
			YwtAttendanceSign v) {
		return this.ywtAttendanceSignDao.selectMonthYwtAttendanceSignList(v);
	}

	@Override
	public List<Map<String, Object>> getDaySignList(YwtAttendanceSign v) {
		return this.ywtAttendanceSignDao.selectDaySignList(v);
	}
}
