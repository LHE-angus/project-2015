package com.ebiz.mmt.dao.ibatis;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.JBaseTypeDao;
import com.ebiz.mmt.domain.JBaseType;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-06-08 17:03:35
 */
@Service
public class JBaseTypeDaoSqlMapImpl extends EntityDaoSqlMapImpl<JBaseType> implements JBaseTypeDao {

	@Override
	public List<HashMap> selectJBaseTypeMap(JBaseType t) {
		return super.getSqlMapClientTemplate().queryForList("selectJBaseTypeMap", t);
	}

}
