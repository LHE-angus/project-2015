package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.EcBcompPdUpNewDao;
import com.ebiz.mmt.domain.EcBcompPdUpNew;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-12-05 12:05:18
 */
@Service
public class EcBcompPdUpNewDaoSqlMapImpl extends EntityDaoSqlMapImpl<EcBcompPdUpNew> implements EcBcompPdUpNewDao {

	@Override
	public Long selectEcBcompPdUpNewForDetailsCount(EcBcompPdUpNew t) {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectEcBcompPdUpNewForDetailsCount", t);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EcBcompPdUpNew> selectEcBcompPdUpNewForDetailsPaginatedList(EcBcompPdUpNew t) {
		return super.getSqlMapClientTemplate().queryForList("selectEcBcompPdUpNewForDetailsPaginatedList", t);
	}

}
