package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaOrderInfoTransDao;
import com.ebiz.mmt.domain.KonkaOrderInfoTrans;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-05-22 15:41:39
 */
@Service
public class KonkaOrderInfoTransDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaOrderInfoTrans> implements KonkaOrderInfoTransDao {
	@SuppressWarnings("unchecked")
	public List<KonkaOrderInfoTrans> selectKonkaOrderInfoTransForFHDPaginatedList(KonkaOrderInfoTrans t) {
		return super.getSqlMapClientTemplate().queryForList("selectKonkaOrderInfoTransForFHDPaginatedList", t);
	}
	
	public Long selectKonkaOrderInfoTransForFHDCount(KonkaOrderInfoTrans t) {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectKonkaOrderInfoTransForFHDCount", t);
	}

	public KonkaOrderInfoTrans selectKonkaOrderInfoTransForPrint(
			KonkaOrderInfoTrans t) {
		return (KonkaOrderInfoTrans)super.getSqlMapClientTemplate().queryForObject("selectKonkaOrderInfoTransForPrint", t);
	}
}
