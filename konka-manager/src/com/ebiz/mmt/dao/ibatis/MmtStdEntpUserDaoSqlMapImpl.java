package com.ebiz.mmt.dao.ibatis;

import org.springframework.stereotype.Repository;

import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;
import com.ebiz.mmt.dao.MmtStdEntpUserDao;
import com.ebiz.mmt.domain.MmtStdEntpUser;

@Repository
public class MmtStdEntpUserDaoSqlMapImpl extends EntityDaoSqlMapImpl<MmtStdEntpUser> implements MmtStdEntpUserDao {

}

