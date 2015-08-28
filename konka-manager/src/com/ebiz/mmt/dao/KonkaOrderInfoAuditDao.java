package com.ebiz.mmt.dao;

import java.util.List;

import com.ebiz.mmt.domain.KonkaOrderInfoAudit;
import com.ebiz.ssi.dao.EntityDao;

/**
 * @author Wu,Yang
 * @version 2011-11-26 11:39
 */
public interface KonkaOrderInfoAuditDao extends EntityDao<KonkaOrderInfoAudit> {

	/** 订单对应的审核记录包含角色名称和ID */
	List<KonkaOrderInfoAudit> selectKonkaOrderInfoAuditWithRoleInfoList(KonkaOrderInfoAudit t);

	/** 订单对应的审核记录中最高的审核级别 */
	Long selectKonkaOrderInfoAuditWithMaxAuditLevel(KonkaOrderInfoAudit t);

	/**
	 * @author Wu,ShangLong
	 * @version 2013-08-05
	 */
	List<KonkaOrderInfoAudit> selectKonkaOrderInfoAuditAndRoleList(KonkaOrderInfoAudit t);
}
