package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.JfScortsDetailsHisDao;
import com.ebiz.mmt.domain.JfScortsDetailsHis;
import com.ebiz.mmt.service.JfScortsDetailsHisService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-06-28 11:46:31
 */
@Service
public class JfScortsDetailsHisServiceImpl implements JfScortsDetailsHisService {

	@Resource
	private JfScortsDetailsHisDao jfScortsDetailsHisDao;
	

	public Long createJfScortsDetailsHis(JfScortsDetailsHis t) {
		return this.jfScortsDetailsHisDao.insertEntity(t);
	}

	public JfScortsDetailsHis getJfScortsDetailsHis(JfScortsDetailsHis t) {
		return this.jfScortsDetailsHisDao.selectEntity(t);
	}

	public Long getJfScortsDetailsHisCount(JfScortsDetailsHis t) {
		return this.jfScortsDetailsHisDao.selectEntityCount(t);
	}

	public List<JfScortsDetailsHis> getJfScortsDetailsHisList(JfScortsDetailsHis t) {
		return this.jfScortsDetailsHisDao.selectEntityList(t);
	}

	public int modifyJfScortsDetailsHis(JfScortsDetailsHis t) {
		return this.jfScortsDetailsHisDao.updateEntity(t);
	}

	public int removeJfScortsDetailsHis(JfScortsDetailsHis t) {
		return this.jfScortsDetailsHisDao.deleteEntity(t);
	}

	public List<JfScortsDetailsHis> getJfScortsDetailsHisPaginatedList(JfScortsDetailsHis t) {
		return this.jfScortsDetailsHisDao.selectEntityPaginatedList(t);
	}

}
