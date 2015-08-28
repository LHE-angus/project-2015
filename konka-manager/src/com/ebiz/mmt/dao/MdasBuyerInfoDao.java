package com.ebiz.mmt.dao;

import java.util.List;

import com.ebiz.mmt.domain.MdasBuyerInfo;
import com.ebiz.ssi.dao.EntityDao;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2010-08-05 08:43:57
 */
public interface MdasBuyerInfoDao extends EntityDao<MdasBuyerInfo> {

	List<MdasBuyerInfo> selectMdasBuyerInfoSumList(MdasBuyerInfo t);

	/**
	 * @author Du,HongGang
	 * @version 2010-09-07
	 */
	public List<MdasBuyerInfo> selectMdasBuyerInfoListForXsCount(MdasBuyerInfo t);

	/**
	 * @author Du,HongGang
	 * @version 2010-09-08
	 */
	public List<MdasBuyerInfo> selectMdasBuyerInfoListForBrandXsCount(MdasBuyerInfo t);

	/**
	 * @author Du,HongGang
	 * @version 2010-09-09
	 */
	public List<MdasBuyerInfo> selectMdasBuyerInfoListForMsg(MdasBuyerInfo t);

	/**
	 * @author Du,HongGang
	 * @version 2010-09-10
	 */
	public List<MdasBuyerInfo> selectMdasBuyerInfoCountList(MdasBuyerInfo t);

	/**
	 * @author Du,HongGang
	 * @version 2010-09-11
	 */
	public List<MdasBuyerInfo> selectMdasBuyerInfoForShopCount(MdasBuyerInfo t);

	/**
	 * @author Xu,XiaoYuan
	 * @version 2010-09-08
	 */
	public Long selectEntpShopStatisticForTable(MdasBuyerInfo t);

	/**
	 * @author li,Yuan
	 * @version 2010-09-20
	 */
	public MdasBuyerInfo selectSumPrice(MdasBuyerInfo t);

	/**
	 * @author Du,HongGang
	 * @version 2010-09-15
	 */
	public List<MdasBuyerInfo> selectMdasBuyerInfoListForBrandXsSales(MdasBuyerInfo t);

	/**
	 * @author Du,HongGang
	 * @version 2010-09-26
	 */
	public List<MdasBuyerInfo> selectMdasBuyerInfoForPdCount(MdasBuyerInfo t);
}
