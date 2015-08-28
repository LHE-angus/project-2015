package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.BuyerInfoDao;
import com.ebiz.mmt.domain.BuyerInfo;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2010-06-02 12:48:04
 */
@Service
public class BuyerInfoDaoSqlMapImpl extends EntityDaoSqlMapImpl<BuyerInfo> implements BuyerInfoDao {

	/**
	 * @author Jiang,JiaYong
	 * @version 2010-06-11
	 */
	public Long selectBuyerInfoCountWithShopId(BuyerInfo t) throws DataAccessException {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectBuyerInfoCountWithShopId", t);
	}

	@SuppressWarnings("unchecked")
	public List<BuyerInfo> selectBuyerInfoPaginatedListWithShopId(BuyerInfo t) throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForList("selectBuyerInfoPaginatedListWithShopId", t);
	}

}
