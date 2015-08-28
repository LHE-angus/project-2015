package com.ebiz.mmt.dao;

import java.util.List;

import com.ebiz.mmt.domain.KonkaInterfaceAccessLog;
import com.ebiz.ssi.dao.EntityDao;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-09-22 14:12:24
 */
public interface KonkaInterfaceAccessLogDao extends EntityDao<KonkaInterfaceAccessLog> {
	List<KonkaInterfaceAccessLog> selectKonkaInterfaceAccessLogForUserNamePaginatedList(KonkaInterfaceAccessLog t);
}
