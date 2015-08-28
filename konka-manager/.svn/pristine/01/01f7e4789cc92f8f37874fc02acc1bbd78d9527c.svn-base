package com.ebiz.mmt.dao.ibatis;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaSpMdSailDao;
import com.ebiz.mmt.domain.KonkaSpMdSail;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-02-10 17:06:35
 */
@Service
public class KonkaSpMdSailDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaSpMdSail> implements KonkaSpMdSailDao {

	@Override
	public KonkaSpMdSail selectKonkaSpMdSailForSum(KonkaSpMdSail t) {
		return (KonkaSpMdSail) super.getSqlMapClientTemplate().queryForObject("selectKonkaSpMdSailForSum", t);
	}

}
