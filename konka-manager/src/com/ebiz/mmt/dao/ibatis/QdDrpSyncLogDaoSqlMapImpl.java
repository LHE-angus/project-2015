package com.ebiz.mmt.dao.ibatis;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.QdDrpSyncLogDao;
import com.ebiz.mmt.domain.QdDrpSyncLog;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

@Service
public class QdDrpSyncLogDaoSqlMapImpl extends EntityDaoSqlMapImpl<QdDrpSyncLog> implements QdDrpSyncLogDao {

}
