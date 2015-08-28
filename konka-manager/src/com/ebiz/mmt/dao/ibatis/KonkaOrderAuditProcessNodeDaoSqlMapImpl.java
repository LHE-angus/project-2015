package com.ebiz.mmt.dao.ibatis;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaOrderAuditProcessNodeDao;
import com.ebiz.mmt.domain.KonkaOrderAuditProcessNode;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * @author Wu,Yang
 * @version 2011-11-30 14:09
 */
@Service
public class KonkaOrderAuditProcessNodeDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaOrderAuditProcessNode> implements KonkaOrderAuditProcessNodeDao {

	@Override
	public Long selectKonkaOrderAuditProcessNodeMaxLevel(KonkaOrderAuditProcessNode t) throws DataAccessException {
		return (Long) this.getSqlMapClientTemplate().queryForObject("selectKonkaOrderAuditProcessNodeMaxLevel", t);
	}

}
