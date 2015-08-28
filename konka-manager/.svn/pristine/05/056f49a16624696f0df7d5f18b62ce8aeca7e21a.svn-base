package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.JsTimesDao;
import com.ebiz.mmt.domain.JsTimes;
import com.ebiz.mmt.service.JsTimesService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2014-01-09 15:57:14
 */
@Service
public class JsTimesServiceImpl implements JsTimesService {

	@Resource
	private JsTimesDao jsTimesDao;
	

	public Long createJsTimes(JsTimes t) {
		return this.jsTimesDao.insertEntity(t);
	}

	public JsTimes getJsTimes(JsTimes t) {
		return this.jsTimesDao.selectEntity(t);
	}

	public Long getJsTimesCount(JsTimes t) {
		return this.jsTimesDao.selectEntityCount(t);
	}

	public List<JsTimes> getJsTimesList(JsTimes t) {
		return this.jsTimesDao.selectEntityList(t);
	}

	public int modifyJsTimes(JsTimes t) {
		return this.jsTimesDao.updateEntity(t);
	}

	public int removeJsTimes(JsTimes t) {
		return this.jsTimesDao.deleteEntity(t);
	}

	public List<JsTimes> getJsTimesPaginatedList(JsTimes t) {
		return this.jsTimesDao.selectEntityPaginatedList(t);
	}

}
