package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.EcBaseExpressReachDayDao;
import com.ebiz.mmt.domain.EcBaseExpressReachDay;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-09-24 11:15:06
 */
@Service
public class EcBaseExpressReachDayDaoSqlMapImpl extends EntityDaoSqlMapImpl<EcBaseExpressReachDay> implements
        EcBaseExpressReachDayDao {

	public Long selectEcBaseExpressReachDayForStoreNameAndFullNameCount(EcBaseExpressReachDay t) {
		return (Long) super.getSqlMapClientTemplate().queryForObject(
		        "selectEcBaseExpressReachDayForStoreNameAndFullNameCount", t);
	}

	@SuppressWarnings("unchecked")
	public List<EcBaseExpressReachDay> selectEcBaseExpressReachDayForStoreNameAndFullNamePaginatedList(
	        EcBaseExpressReachDay t) {
		return super.getSqlMapClientTemplate().queryForList(
		        "selectEcBaseExpressReachDayForStoreNameAndFullNamePaginatedList", t);
	}

}
