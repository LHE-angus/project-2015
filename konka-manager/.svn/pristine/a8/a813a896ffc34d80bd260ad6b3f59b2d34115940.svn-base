package com.ebiz.mmt.dao.ibatis;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.EcShoppingCartDao;
import com.ebiz.mmt.domain.EcShoppingCart;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-09-09 10:06:25
 */
@Service
public class EcShoppingCartDaoSqlMapImpl extends EntityDaoSqlMapImpl<EcShoppingCart> implements EcShoppingCartDao {

	/**
	 * @author Jiang,JiaYong
	 * @version 2013-09-15
	 */
	public int deleteEcShoppingCartWithGoodsIdAndUserId(EcShoppingCart t) throws DataAccessException {
		return this.getSqlMapClientTemplate().update("deleteEcShoppingCartWithGoodsIdAndUserId", t);
	}

}
