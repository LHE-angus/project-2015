package com.ebiz.mmt.dao.ibatis;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.JBaseStoreDao;
import com.ebiz.mmt.domain.JBaseStore;
import com.ebiz.mmt.domain.JBaseStoreR3;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-06-08 17:03:35
 */
@Service
public class JBaseStoreDaoSqlMapImpl extends EntityDaoSqlMapImpl<JBaseStore> implements JBaseStoreDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<JBaseStoreR3> selectStoreSnWeForList(JBaseStoreR3 jBaseStoreR3) {
		
		return super.getSqlMapClientTemplate().queryForList("selectstoresnweforlist", jBaseStoreR3);
	}

	@Override
	public JBaseStore selectJBaseStoreAndDetails(JBaseStore entity) {
		
		return (JBaseStore) super.getSqlMapClientTemplate().queryForObject("selectjbasestoreanddetails", entity);
	}

	/**
	 * 获取仓库列表，包含对应的售达方和送达方
	 * 
	 * @author Xiao,GuoJian
	 * @param jBaseStore
	 * @return jBaseStore
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<JBaseStore> selectJBaseStoreForR3PaginatedList(JBaseStore jBaseStore) {
		return super.getSqlMapClientTemplate().queryForList("selectJBaseStoreForR3PaginatedList", jBaseStore);
	}

	/**
	 * 获取仓库列表，包含对应的售达方和送达方
	 * 
	 * @author Xiao,GuoJian
	 * @param jBaseStore
	 * @return jBaseStore
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<JBaseStore> selectJBaseStoreForR3List(JBaseStore jBaseStore) {
		return super.getSqlMapClientTemplate().queryForList("selectJBaseStoreForR3List", jBaseStore);
	}

	@Override
	public List<HashMap> selectJBaseStoreMapList(JBaseStore jBaseStore) {
		return super.getSqlMapClientTemplate().queryForList("selectJBaseStoreMapList", jBaseStore);
	}

	@Override
	public void clearStockUseR3code(HashMap map) {
		
		super.getSqlMapClientTemplate().queryForList("clearStockUseR3code", map);
	}

}
