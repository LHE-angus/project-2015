package com.ebiz.mmt.dao.ibatis;

import org.springframework.stereotype.Repository;

import com.ebiz.mmt.dao.OperLogDao;
import com.ebiz.mmt.domain.OperLog;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

@Repository
public class OperLogDaoSqlMapImpl extends EntityDaoSqlMapImpl<OperLog> implements OperLogDao {

}

