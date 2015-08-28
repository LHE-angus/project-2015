package com.ebiz.mmt.dao;

import java.util.List;

import com.ebiz.mmt.domain.JnhmSelledPdCode;
import com.ebiz.ssi.dao.EntityDao;

/**
 * @author Li,Ka
 * @version 2012-08-08 09:38
 */
public interface JnhmSelledPdCodeDao extends EntityDao<JnhmSelledPdCode> {

	public List<JnhmSelledPdCode> selectJnhmSelledPdCodeListForJnhm(JnhmSelledPdCode t);
}
