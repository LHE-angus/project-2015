package com.ebiz.mmt.dao.ibatis;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.JBaseStoreR3Dao;
import com.ebiz.mmt.domain.JBaseStoreR3;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-06-13 14:54:36
 */
@Service
public class JBaseStoreR3DaoSqlMapImpl extends EntityDaoSqlMapImpl<JBaseStoreR3> implements JBaseStoreR3Dao {

	@Override
	public HashMap selectSDFStore(JBaseStoreR3 t) {
		return (HashMap) super.getSqlMapClientTemplate().queryForObject("selectSDFStore", t);
	}

}
