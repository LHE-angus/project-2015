package com.ebiz.mmt.dao.ibatis;

import org.springframework.stereotype.Repository;

import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;
import com.ebiz.mmt.dao.MmtUserInfoDao;
import com.ebiz.mmt.domain.MmtUserInfo;

@Repository
public class MmtUserInfoDaoSqlMapImpl extends EntityDaoSqlMapImpl<MmtUserInfo> implements MmtUserInfoDao {

}

