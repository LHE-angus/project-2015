package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.EcCustDao;
import com.ebiz.mmt.domain.EcCust;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-12-08 14:09:38
 */
@Service
public class EcCustDaoSqlMapImpl extends EntityDaoSqlMapImpl<EcCust> implements EcCustDao {

	@Override
	public Long selectEcCustForDetailsCount(EcCust t) {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectEcCustForDetailsCount", t);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EcCust> selectEcCustForDetailsPaginatedList(EcCust t) {
		return super.getSqlMapClientTemplate().queryForList("selectEcCustForDetailsPaginatedList", t);
	}

}
