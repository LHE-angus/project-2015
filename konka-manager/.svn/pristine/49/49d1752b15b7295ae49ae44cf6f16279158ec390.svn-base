package com.ebiz.mmt.dao.ibatis;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.EcPdEavlDao;
import com.ebiz.mmt.domain.EcPdEavl;
import com.ebiz.mmt.domain.EcUserAddrs;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-09-09 10:06:25
 */
@Service
public class EcPdEavlDaoSqlMapImpl extends EntityDaoSqlMapImpl<EcPdEavl> implements EcPdEavlDao {

	
	

	/**
	 * @author tudp 
	 * @throws 2013-09-17
	 */
	public Long selectEcPdEavlSumEvalScore(EcPdEavl t) throws DataAccessException { 
		return (Long)this.getSqlMapClientTemplate().queryForObject("selectEcPdEavlSumEvalScore", t); 
	}
}
