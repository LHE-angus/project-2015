package com.ebiz.mmt.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ebiz.mmt.domain.BuyerInfo;
import com.ebiz.ssi.dao.EntityDao;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2010-06-02 12:48:03
 */
public interface BuyerInfoDao extends EntityDao<BuyerInfo> {

	/**
	 * @author Jiang,JiaYong
	 * @version 2010-06-11
	 */
	Long selectBuyerInfoCountWithShopId(BuyerInfo t) throws DataAccessException;

	List<BuyerInfo> selectBuyerInfoPaginatedListWithShopId(BuyerInfo t) throws DataAccessException;
}
