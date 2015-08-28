package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaInterfaceAccessLogDao;
import com.ebiz.mmt.domain.KonkaInterfaceAccessLog;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-09-22 14:12:24
 */
@Service
public class KonkaInterfaceAccessLogDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaInterfaceAccessLog> implements
        KonkaInterfaceAccessLogDao {

	@SuppressWarnings("unchecked")
	public List<KonkaInterfaceAccessLog> selectKonkaInterfaceAccessLogForUserNamePaginatedList(KonkaInterfaceAccessLog t) {
		return super.getSqlMapClientTemplate().queryForList("selectKonkaInterfaceAccessLogForUserNamePaginatedList", t);
	}

}
