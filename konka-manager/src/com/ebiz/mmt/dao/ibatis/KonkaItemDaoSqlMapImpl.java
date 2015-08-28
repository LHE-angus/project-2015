package com.ebiz.mmt.dao.ibatis;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaItemDao;
import com.ebiz.mmt.domain.KonkaItem;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * @author Ren,Peng
 * @version 2011-10-20 16:41
 */
@Service
public class KonkaItemDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaItem> implements KonkaItemDao {

}
