package com.ebiz.mmt.dao.ibatis;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaXxActMessageDao;
import com.ebiz.mmt.domain.KonkaXxActMessage;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * @author Ren,zhong
 * @version 2012-03-19 13:48
 */
@Service
public class KonkaXxActMessageDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaXxActMessage> implements
		KonkaXxActMessageDao {

	public Long insertKonkaXxActMessageForNotice(KonkaXxActMessage t) {
		return (Long) super.getSqlMapClientTemplate().insert("insertKonkaXxActMessageForNotice", t);
	}

}
