package com.ebiz.mmt.dao;

import org.springframework.dao.DataAccessException;

import com.ebiz.mmt.domain.EcShoppingCart;
import com.ebiz.ssi.dao.EntityDao;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-09-09 10:06:25
 */
public interface EcShoppingCartDao extends EntityDao<EcShoppingCart> {
	
	/**
	 * @author Jiang,JiaYong
	 * @version 2013-09-15
	 */
	public abstract int deleteEcShoppingCartWithGoodsIdAndUserId(EcShoppingCart t) throws DataAccessException;
}
