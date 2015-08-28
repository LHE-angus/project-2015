package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaProSqDao;
import com.ebiz.mmt.domain.KonkaProSq;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-10-25 12:36:17
 */
@Service
public class KonkaProSqDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaProSq> implements KonkaProSqDao {

	public Long selectKonkaProSqNoMax(KonkaProSq t) {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectKonkaProSqNoMax", t);
	}

	@SuppressWarnings("unchecked")
	public List<KonkaProSq> selectKonkaProSqAndFighterInfoPaginatedList(KonkaProSq t) {
		return super.getSqlMapClientTemplate().queryForList("selectKonkaProSqAndFighterInfoPaginatedList", t);
	}

}
