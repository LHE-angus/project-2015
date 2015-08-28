package com.ebiz.mmt.dao.ibatis;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaBcompPdContentDao;
import com.ebiz.mmt.domain.KonkaBcompPdContent;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-08-21 12:02:11
 */
@Service
public class KonkaBcompPdContentDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaBcompPdContent> implements
        KonkaBcompPdContentDao {

	public int deleteKonkaBcompPdContentWithKPDID(KonkaBcompPdContent t) {
		return this.getSqlMapClientTemplate().update("deleteKonkaBcompPdContentWithKPDID", t);
	}
}
