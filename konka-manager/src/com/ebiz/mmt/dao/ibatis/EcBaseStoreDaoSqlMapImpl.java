package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.EcBaseStoreDao;
import com.ebiz.mmt.domain.EcBaseStore;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-09-09 10:06:25
 */
@Service
public class EcBaseStoreDaoSqlMapImpl extends EntityDaoSqlMapImpl<EcBaseStore> implements EcBaseStoreDao {

	@SuppressWarnings("unchecked")
	public List<EcBaseStore> selectEcBaseStoreForPdList(EcBaseStore t) {
		return this.getSqlMapClientTemplate().queryForList("selectEcBaseStoreForPdList", t);
	}

	public Long selectEcBaseStoreForAreaCount(EcBaseStore t) {
		return (Long) this.getSqlMapClientTemplate().queryForObject("selectEcBaseStoreForAreaCount", t);
	}

	public Long selectEcBaseStoreForDeptCount(EcBaseStore t) {
		return (Long) this.getSqlMapClientTemplate().queryForObject("selectEcBaseStoreForDeptCount", t);
	}

	@SuppressWarnings("unchecked")
	public List<EcBaseStore> selectEcBaseStoreForDeptNamePaginatedList(EcBaseStore t) {
		return this.getSqlMapClientTemplate().queryForList("selectEcBaseStoreForDeptNamePaginatedList", t);
	}

	@SuppressWarnings("unchecked")
	public List<EcBaseStore> selectEcBaseStoreForAreaForList(EcBaseStore t) {
		return this.getSqlMapClientTemplate().queryForList("selectEcBaseStoreForAreaForList", t);
	}

	@SuppressWarnings("unchecked")
	public List<EcBaseStore> selectEcBaseStoreForPindexIdForList(EcBaseStore t) {
		return this.getSqlMapClientTemplate().queryForList("selectEcBaseStoreForPindexIdForList", t);
	}

	@SuppressWarnings("unchecked")
	public List<EcBaseStore> selectEcBaseStoreForPindexAndPnameList(EcBaseStore t) {
		return this.getSqlMapClientTemplate().queryForList("selectEcBaseStoreForPindexAndPnameList", t);
	}

}
