package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.MdasBuyerInfoDao;
import com.ebiz.mmt.domain.MdasBuyerInfo;
import com.ebiz.mmt.service.MdasBuyerInfoService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2010-08-05 08:43:57
 */
@Service
public class MdasBuyerInfoServiceImpl implements MdasBuyerInfoService {

	@Resource
	private MdasBuyerInfoDao mdasBuyerInfoDao;

	public Long createMdasBuyerInfo(MdasBuyerInfo t) {
		return this.mdasBuyerInfoDao.insertEntity(t);
	}

	public MdasBuyerInfo getMdasBuyerInfo(MdasBuyerInfo t) {
		return this.mdasBuyerInfoDao.selectEntity(t);
	}

	public Long getMdasBuyerInfoCount(MdasBuyerInfo t) {
		return this.mdasBuyerInfoDao.selectEntityCount(t);
	}

	public List<MdasBuyerInfo> getMdasBuyerInfoList(MdasBuyerInfo t) {
		return this.mdasBuyerInfoDao.selectEntityList(t);
	}

	public int modifyMdasBuyerInfo(MdasBuyerInfo t) {
		return this.mdasBuyerInfoDao.updateEntity(t);
	}

	public int removeMdasBuyerInfo(MdasBuyerInfo t) {
		return this.mdasBuyerInfoDao.deleteEntity(t);
	}

	public List<MdasBuyerInfo> getMdasBuyerInfoPaginatedList(MdasBuyerInfo t) {
		return this.mdasBuyerInfoDao.selectEntityPaginatedList(t);
	}

	public List<MdasBuyerInfo> getMdasBuyerInfoSumList(MdasBuyerInfo t) {
		return this.mdasBuyerInfoDao.selectMdasBuyerInfoSumList(t);
	}

	/**
	 * @author Du,HongGang
	 * @version 2010-09-07
	 */
	public List<MdasBuyerInfo> getMdasBuyerInfoListForXsCount(MdasBuyerInfo t) {
		return this.mdasBuyerInfoDao.selectMdasBuyerInfoListForXsCount(t);
	}

	/**
	 * @author Du,HongGang
	 * @version 2010-09-08
	 */
	public List<MdasBuyerInfo> getMdasBuyerInfoListForBrandXsCount(MdasBuyerInfo t) {
		return this.mdasBuyerInfoDao.selectMdasBuyerInfoListForBrandXsCount(t);
	}

	/**
	 * @author Du,HongGang
	 * @version 2010-09-09
	 */
	public List<MdasBuyerInfo> getMdasBuyerInfoListForMsg(MdasBuyerInfo t) {
		return this.mdasBuyerInfoDao.selectMdasBuyerInfoListForMsg(t);
	}

	/**
	 * @author Du,HongGang
	 * @version 2010-09-10
	 */
	public List<MdasBuyerInfo> getMdasBuyerInfoCountList(MdasBuyerInfo t) {
		return this.mdasBuyerInfoDao.selectMdasBuyerInfoCountList(t);
	}

	/**
	 * @author Du,HongGang
	 * @version 2010-09-11
	 */
	public List<MdasBuyerInfo> getMdasBuyerInfoForShopCount(MdasBuyerInfo t) {
		return this.mdasBuyerInfoDao.selectMdasBuyerInfoForShopCount(t);
	}

	/**
	 * @author Xu,XiaoYuan
	 * @version 2010-09-08
	 */
	public Long getEntpShopStatisticForTable(MdasBuyerInfo t) {
		return this.mdasBuyerInfoDao.selectEntpShopStatisticForTable(t);
	}

	/**
	 * @author Du,HongGang
	 * @version 2010-09-15
	 */
	public List<MdasBuyerInfo> getMdasBuyerInfoListForBrandXsSales(MdasBuyerInfo t) {
		return this.mdasBuyerInfoDao.selectMdasBuyerInfoListForBrandXsSales(t);
	}

	/**
	 * @author Li.Yuan
	 * @version 2010-09-20
	 */
	public MdasBuyerInfo getSumPrice(MdasBuyerInfo t) {
		return this.mdasBuyerInfoDao.selectSumPrice(t);
	}

	/**
	 * @author Du,HongGang
	 * @version 2010-09-26
	 */
	public List<MdasBuyerInfo> getMdasBuyerInfoForPdCount(MdasBuyerInfo t) {
		return this.mdasBuyerInfoDao.selectMdasBuyerInfoForPdCount(t);
	}
}
