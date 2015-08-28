package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.JnhmSelledPdCodeDao;
import com.ebiz.mmt.domain.JnhmSelledPdCode;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * @author Li,Ka
 * @version 2012-08-08 09:38
 */
@Service
public class JnhmSelledPdCodeDaoSqlMapImpl extends EntityDaoSqlMapImpl<JnhmSelledPdCode> implements JnhmSelledPdCodeDao {

	@SuppressWarnings("unchecked")
	public List<JnhmSelledPdCode> selectJnhmSelledPdCodeListForJnhm(JnhmSelledPdCode t) {
		return super.getSqlMapClientTemplate().queryForList("selectJnhmSelledPdCodeListForJnhm", t);
	}

}
