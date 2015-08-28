package com.ebiz.mmt.dao.ibatis;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.EcLuckyMainDao;
import com.ebiz.mmt.domain.EcLuckyMain;
import com.ebiz.mmt.domain.PshowOrder;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-07-11 16:03:36
 */
@Service
public class EcLuckyMainDaoSqlMapImpl extends EntityDaoSqlMapImpl<EcLuckyMain> implements EcLuckyMainDao {

	/**
	 * @author tudp
	 * @version 2014-07-18
	 */
	public int updateEcLuckyMainViewCounts(EcLuckyMain t)throws DataAccessException {
		return this.getSqlMapClientTemplate().update("updateEcLuckyMainViewCounts", t);
	}
}
