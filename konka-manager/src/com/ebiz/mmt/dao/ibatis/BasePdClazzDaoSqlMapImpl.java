package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.BasePdClazzDao;
import com.ebiz.mmt.domain.BasePdClazz;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Chen,ShunHua
 * @date 2011-09-21 09:45:12
 */
@Service
public class BasePdClazzDaoSqlMapImpl extends EntityDaoSqlMapImpl<BasePdClazz> implements BasePdClazzDao {

	/**
	 * @author Chen,ShunHua
	 * @version 2011.09.21
	 */
	@SuppressWarnings("unchecked")
	public List<BasePdClazz> selectBasePdClazzWithPropnamesList(BasePdClazz t) {
		return super.getSqlMapClientTemplate().queryForList("selectBasePdClazzWithPropnamesList", t);
	}

}
