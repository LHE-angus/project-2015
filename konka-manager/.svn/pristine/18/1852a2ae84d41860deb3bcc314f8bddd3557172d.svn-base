package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.JnhmPdContentsDao;
import com.ebiz.mmt.domain.JnhmPdContents;
import com.ebiz.mmt.service.JnhmPdContentsService;

/**
 * @author Li,Ka
 * @version 2012-07-30 19:32
 */
@Service
public class JnhmPdContentsServiceImpl implements JnhmPdContentsService {

	@Resource
	private JnhmPdContentsDao jnhmPdContentsDao;
	

	public Long createJnhmPdContents(JnhmPdContents t) {
		return this.jnhmPdContentsDao.insertEntity(t);
	}

	public JnhmPdContents getJnhmPdContents(JnhmPdContents t) {
		return this.jnhmPdContentsDao.selectEntity(t);
	}

	public Long getJnhmPdContentsCount(JnhmPdContents t) {
		return this.jnhmPdContentsDao.selectEntityCount(t);
	}

	public List<JnhmPdContents> getJnhmPdContentsList(JnhmPdContents t) {
		return this.jnhmPdContentsDao.selectEntityList(t);
	}

	public int modifyJnhmPdContents(JnhmPdContents t) {
		return this.jnhmPdContentsDao.updateEntity(t);
	}

	public int removeJnhmPdContents(JnhmPdContents t) {
		return this.jnhmPdContentsDao.deleteEntity(t);
	}

	public List<JnhmPdContents> getJnhmPdContentsPaginatedList(JnhmPdContents t) {
		return this.jnhmPdContentsDao.selectEntityPaginatedList(t);
	}

}
