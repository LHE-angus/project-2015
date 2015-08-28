package com.ebiz.mmt.dao.ibatis;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaSysAplicationDao;
import com.ebiz.mmt.domain.KonkaSysAplication;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;


@Service
public class KonkaSysAplicationDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaSysAplication> implements KonkaSysAplicationDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<HashMap> selectKonkaSysAplicationList(KonkaSysAplication t) {
		return super.getSqlMapClientTemplate().queryForList("selectKonkaSysAplicationPaginatedList", t);
	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap selectKonkaSysAplication(KonkaSysAplication t) {
		return (HashMap) super.getSqlMapClientTemplate().queryForObject("selectKonkaSysAplication", t);
	}

	@Override
	public int modifyAplicationStatus(KonkaSysAplication t) {
		return super.getSqlMapClientTemplate().update("modifyAplicationStatus", t);
	}
}
