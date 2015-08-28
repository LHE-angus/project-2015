package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaMobileImpStoreDao;
import com.ebiz.mmt.domain.KonkaMobileImpStore;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-05-23 15:45:29
 */
@Service
public class KonkaMobileImpStoreDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaMobileImpStore> implements KonkaMobileImpStoreDao {

	@SuppressWarnings("unchecked")
	public List<KonkaMobileImpStore> selectKonkaMobileImpStoreListForDistinctSdf(KonkaMobileImpStore t)
			throws DataAccessException {
		return this.getSqlMapClientTemplate().queryForList("selectKonkaMobileImpStoreListForDistinctSdf", t);
	}

}
