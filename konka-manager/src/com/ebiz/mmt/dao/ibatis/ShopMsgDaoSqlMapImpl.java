package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.ebiz.mmt.dao.ShopMsgDao;
import com.ebiz.mmt.domain.ShopMsg;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * @author Jin,QingHua
 */
@Repository
@SuppressWarnings("unchecked")
public class ShopMsgDaoSqlMapImpl extends EntityDaoSqlMapImpl<ShopMsg> implements ShopMsgDao {

	public List<ShopMsg> selectUserMsgWithShopPaginatedList(ShopMsg t) throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForList("selectShopPdWithShopNameList", t);
	}

	public Long selectUserMsgWithShopCount(ShopMsg t) throws DataAccessException {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectUserMsgShopCount", t);
	}

}
