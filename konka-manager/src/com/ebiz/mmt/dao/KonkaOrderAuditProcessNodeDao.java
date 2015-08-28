package com.ebiz.mmt.dao;

import org.springframework.dao.DataAccessException;

import com.ebiz.mmt.domain.KonkaOrderAuditProcessNode;
import com.ebiz.ssi.dao.EntityDao;

/**
 * @author Wu,Yang
 * @version 2011-11-30 14:09
 */
public interface KonkaOrderAuditProcessNodeDao extends EntityDao<KonkaOrderAuditProcessNode> {
	public Long selectKonkaOrderAuditProcessNodeMaxLevel(KonkaOrderAuditProcessNode t)  throws DataAccessException;

}
