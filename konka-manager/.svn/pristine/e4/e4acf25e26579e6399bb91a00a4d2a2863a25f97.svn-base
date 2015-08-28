package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.JnhmSelledPdCodeDao;
import com.ebiz.mmt.domain.JnhmSelledPdCode;
import com.ebiz.mmt.service.JnhmSelledPdCodeService;

/**
 * @author Li,Ka
 * @version 2012-08-08 09:38
 */
@Service
public class JnhmSelledPdCodeServiceImpl implements JnhmSelledPdCodeService {

	@Resource
	private JnhmSelledPdCodeDao jnhmSelledPdCodeDao;

	public Long createJnhmSelledPdCode(JnhmSelledPdCode t) {
		return this.jnhmSelledPdCodeDao.insertEntity(t);
	}

	public JnhmSelledPdCode getJnhmSelledPdCode(JnhmSelledPdCode t) {
		return this.jnhmSelledPdCodeDao.selectEntity(t);
	}

	public Long getJnhmSelledPdCodeCount(JnhmSelledPdCode t) {
		return this.jnhmSelledPdCodeDao.selectEntityCount(t);
	}

	public List<JnhmSelledPdCode> getJnhmSelledPdCodeList(JnhmSelledPdCode t) {
		return this.jnhmSelledPdCodeDao.selectEntityList(t);
	}

	public int modifyJnhmSelledPdCode(JnhmSelledPdCode t) {
		return this.jnhmSelledPdCodeDao.updateEntity(t);
	}

	public int removeJnhmSelledPdCode(JnhmSelledPdCode t) {
		return this.jnhmSelledPdCodeDao.deleteEntity(t);
	}

	public List<JnhmSelledPdCode> getJnhmSelledPdCodePaginatedList(JnhmSelledPdCode t) {
		return this.jnhmSelledPdCodeDao.selectEntityPaginatedList(t);
	}

	public List<JnhmSelledPdCode> getJnhmSelledPdCodeListForJnhm(JnhmSelledPdCode t) {
		return this.jnhmSelledPdCodeDao.selectJnhmSelledPdCodeListForJnhm(t);
	}

}
