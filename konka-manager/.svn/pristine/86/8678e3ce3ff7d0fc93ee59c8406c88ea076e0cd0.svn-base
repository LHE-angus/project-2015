package com.ebiz.mmt.dao.ibatis;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.EcAuctionMainDao;
import com.ebiz.mmt.domain.EcAuctionMain;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-08-28 15:50:21
 */
@Service
public class EcAuctionMainDaoSqlMapImpl extends EntityDaoSqlMapImpl<EcAuctionMain> implements EcAuctionMainDao {

	/**
	 * @author tudp
	 * @version 2014-09-1
	 */
	public int updateEcAuctionMainViewCounts(EcAuctionMain t)throws DataAccessException {
		return this.getSqlMapClientTemplate().update("updateEcAuctionMainViewCounts", t);
	}
	
	/**
	 * @author tudp
	 * @version 2014-09-2
	 */
	public int updateEcAuctionMainDelayNum(EcAuctionMain t)throws DataAccessException {
		return this.getSqlMapClientTemplate().update("updateEcAuctionMainDelayNum", t);
	}
}
