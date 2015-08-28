package com.ebiz.mmt.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ebiz.mmt.domain.ShopMsg;
import com.ebiz.ssi.dao.EntityDao;

/**
 * @author Jin,QingHua
 */
public interface ShopMsgDao extends EntityDao<ShopMsg> {
	List<ShopMsg> selectUserMsgWithShopPaginatedList(ShopMsg t) throws DataAccessException;

	Long selectUserMsgWithShopCount(ShopMsg t) throws DataAccessException;
}
