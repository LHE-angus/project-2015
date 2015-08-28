package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.JfScortsDao;
import com.ebiz.mmt.domain.JfScorts;
import com.ebiz.mmt.service.JfScortsService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-06-25 15:25:02
 */
@Service
public class JfScortsServiceImpl implements JfScortsService {

	@Resource
	private JfScortsDao jfScortsDao;

	public Long createJfScorts(JfScorts t) {
		return this.jfScortsDao.insertEntity(t);
	}

	public JfScorts getJfScorts(JfScorts t) {
		return this.jfScortsDao.selectEntity(t);
	}

	public Long getJfScortsCount(JfScorts t) {
		return this.jfScortsDao.selectEntityCount(t);
	}

	public List<JfScorts> getJfScortsList(JfScorts t) {
		return this.jfScortsDao.selectEntityList(t);
	}

	public int modifyJfScorts(JfScorts t) {
		return this.jfScortsDao.updateEntity(t);
	}

	public int removeJfScorts(JfScorts t) {
		return this.jfScortsDao.deleteEntity(t);
	}

	public List<JfScorts> getJfScortsPaginatedList(JfScorts t) {
		return this.jfScortsDao.selectEntityPaginatedList(t);
	}

	public List<JfScorts> getJfScortsForScortsList(JfScorts t) {
		return this.jfScortsDao.selectJfScortsForScortsList(t);
	}

}
