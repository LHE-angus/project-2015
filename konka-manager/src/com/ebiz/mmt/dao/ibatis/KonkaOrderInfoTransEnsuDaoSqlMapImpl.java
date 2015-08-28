package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaOrderInfoTransEnsuDao;
import com.ebiz.mmt.domain.KonkaOrderInfoTransEnsu;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-05-24 17:30:54
 */
@Service
public class KonkaOrderInfoTransEnsuDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaOrderInfoTransEnsu> implements KonkaOrderInfoTransEnsuDao {

	@Override
	public Long selectKonkaOrderInfoTransEnsurSumEnsured(KonkaOrderInfoTransEnsu t) {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectKonkaOrderInfoTransEnsurSumEnsured", t);
	}

	@Override
	public Long selectKonkaOrderInfoTransEnsuAndDetailsCount(KonkaOrderInfoTransEnsu konkaOrderInfoTransEnsu) {
		
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectKonkaOrderInfoTransEnsuAndDetailsCount",konkaOrderInfoTransEnsu);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<KonkaOrderInfoTransEnsu> selectKonkaOrderInfoTransEnsuAndDetailsPaginatedList(
			KonkaOrderInfoTransEnsu konkaOrderInfoTransEnsu) {
		
		return (List<KonkaOrderInfoTransEnsu>)super.getSqlMapClientTemplate().queryForList("selectKonkaOrderInfoTransEnsuAndDetailsPaginatedList", konkaOrderInfoTransEnsu);
	}

}
