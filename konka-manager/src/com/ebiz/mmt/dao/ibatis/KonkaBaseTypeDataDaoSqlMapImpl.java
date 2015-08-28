package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaBaseTypeDataDao;
import com.ebiz.mmt.domain.KonkaBaseTypeData;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-10-14 11:28:20
 */
@Service
public class KonkaBaseTypeDataDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaBaseTypeData> implements KonkaBaseTypeDataDao {

	@Override
	public Long selectKonkaMobileCustVisitLBCount(KonkaBaseTypeData entity) {
		 return (Long) super.getSqlMapClientTemplate().queryForObject("selectKonkaBaseTypeDataLBCount",entity);
	}

	@Override
	public List<KonkaBaseTypeData> selectKonkaMobileCustVisitPaginatedLBList(
			KonkaBaseTypeData entity) {
		return super.getSqlMapClientTemplate().queryForList("selectKonkaBaseTypeDataLBPaginatedList", entity);
	}

}
