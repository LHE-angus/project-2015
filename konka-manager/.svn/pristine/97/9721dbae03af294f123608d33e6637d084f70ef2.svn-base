package com.ebiz.mmt.dao.ibatis;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.EcAuctionBuyDao;
import com.ebiz.mmt.domain.EcAuctionBuy;
import com.ebiz.mmt.domain.EcAuctionMain;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-08-28 15:50:21
 */
@Service
public class EcAuctionBuyDaoSqlMapImpl extends EntityDaoSqlMapImpl<EcAuctionBuy> implements EcAuctionBuyDao {

	/**
	 * @author tudp
	 * @version 2014-09-2
	 */
	public EcAuctionBuy selectEcAuctionBuyForMaxPrice(Long auction_id)throws DataAccessException {
		return (EcAuctionBuy)this.getSqlMapClientTemplate().queryForObject("selectEcAuctionBuyForMaxPrice",auction_id);
	}
}
