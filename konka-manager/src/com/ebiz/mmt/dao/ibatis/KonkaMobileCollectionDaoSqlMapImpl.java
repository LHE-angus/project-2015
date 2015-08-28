package com.ebiz.mmt.dao.ibatis;

import org.springframework.stereotype.Repository;

import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;
import com.ebiz.mmt.dao.KonkaMobileCollectionDao;
import com.ebiz.mmt.domain.KonkaMobileCollection;

@Repository
public class KonkaMobileCollectionDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaMobileCollection> implements KonkaMobileCollectionDao {

}

