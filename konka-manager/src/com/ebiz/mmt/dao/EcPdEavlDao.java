package com.ebiz.mmt.dao;

import org.springframework.dao.DataAccessException;

import com.ebiz.mmt.domain.EcPdEavl;
import com.ebiz.ssi.dao.EntityDao;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-09-09 10:06:25
 */
public interface EcPdEavlDao extends EntityDao<EcPdEavl> {

	/**
	 * @author tudp 
	 * @throws 2013-09-17
	 */
	public Long selectEcPdEavlSumEvalScore(EcPdEavl t) throws DataAccessException;
}
