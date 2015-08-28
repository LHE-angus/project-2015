package com.ebiz.mmt.dao.ibatis;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaOrderAuditProcessDao;
import com.ebiz.mmt.domain.KonkaOrderAuditProcess;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * @author Wu,Yang
 * @version 2011-11-30 14:09
 */
@Service
public class KonkaOrderAuditProcessDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaOrderAuditProcess> implements KonkaOrderAuditProcessDao {

}
