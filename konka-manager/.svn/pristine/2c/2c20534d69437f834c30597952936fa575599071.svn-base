package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaOrderInfoAuditDao;
import com.ebiz.mmt.domain.KonkaOrderInfoAudit;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * @author Wu,Yang
 * @version 2011-11-26 11:39
 */
@Service
@SuppressWarnings("unchecked")
public class KonkaOrderInfoAuditDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaOrderInfoAudit> implements
		KonkaOrderInfoAuditDao {

	/** 订单对应的审核记录包含角色名称和ID */
	public List<KonkaOrderInfoAudit> selectKonkaOrderInfoAuditWithRoleInfoList(KonkaOrderInfoAudit t) {
		return super.getSqlMapClientTemplate().queryForList("selectKonkaOrderInfoAuditWithRoleInfoList", t);
	}

	/** 订单对应的审核记录中最高的审核级别 */
	public Long selectKonkaOrderInfoAuditWithMaxAuditLevel(KonkaOrderInfoAudit t) {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectKonkaOrderInfoAuditWithMaxAuditLevel", t);
	}

	/**
	 * @author Wu,ShangLong
	 * @version 2013-08-05
	 */
	public List<KonkaOrderInfoAudit> selectKonkaOrderInfoAuditAndRoleList(KonkaOrderInfoAudit t) {
		return super.getSqlMapClientTemplate().queryForList("selectKonkaOrderInfoAuditAndRoleList", t);
	}

}
