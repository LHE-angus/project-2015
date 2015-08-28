package com.ebiz.mmt.dao.ibatis;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaItemPropertyDao;
import com.ebiz.mmt.domain.KonkaItemProperty;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * @author Ren,Peng
 * @version 2011-10-20 16:41
 */
@Service
public class KonkaItemPropertyDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaItemProperty> implements KonkaItemPropertyDao {

	public String selectPropertyName(Long id) throws DataAccessException {
		return (String) super.getSqlMapClientTemplate().queryForObject("selectPropertyName", id);
	}
}
