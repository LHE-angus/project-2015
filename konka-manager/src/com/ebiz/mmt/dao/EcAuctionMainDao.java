package com.ebiz.mmt.dao;

import com.ebiz.mmt.domain.EcAuctionMain;
import com.ebiz.ssi.dao.EntityDao;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-08-28 15:50:21
 */
public interface EcAuctionMainDao extends EntityDao<EcAuctionMain> {

	 int updateEcAuctionMainViewCounts(EcAuctionMain t);
	 
	 int updateEcAuctionMainDelayNum(EcAuctionMain t);
}
