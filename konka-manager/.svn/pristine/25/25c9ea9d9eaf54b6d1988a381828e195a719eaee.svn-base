package com.ebiz.mmt.dao.ibatis;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaAuditListInfoDao;
import com.ebiz.mmt.domain.KonkaAuditListInfo;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;


@Service
public class KonkaAuditListInfoDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaAuditListInfo> implements KonkaAuditListInfoDao {

	@Override
	public List<HashMap> selectKonkaAuditListInfoListForMap(KonkaAuditListInfo t) {
		return super.getSqlMapClientTemplate().queryForList("selectKonkaAuditListInfoListForMap", t);
	}

	@Override
	public HashMap selectKonkaAuditListInfoForMap(KonkaAuditListInfo t) {
		return (HashMap) super.getSqlMapClientTemplate().queryForObject("selectKonkaAuditListInfoForMap", t);
	}

	@Override
	public int updateKonkaAuditListInfoForNew(KonkaAuditListInfo t) {
		return super.getSqlMapClientTemplate().update("modifyKonkaAuditListInfoForNew", t);
	}

}
