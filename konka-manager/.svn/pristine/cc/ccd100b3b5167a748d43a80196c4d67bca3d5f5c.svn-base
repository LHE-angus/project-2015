package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.GiftInfoDao;
import com.ebiz.mmt.domain.GiftInfo;
import com.ebiz.mmt.service.GiftInfoService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-11-07 11:39:53
 */
@Service
public class GiftInfoServiceImpl implements GiftInfoService {

	@Resource
	private GiftInfoDao giftInfoDao;

	public Long createGiftInfo(GiftInfo t) {
		return this.giftInfoDao.insertEntity(t);
	}

	public GiftInfo getGiftInfo(GiftInfo t) {
		return this.giftInfoDao.selectEntity(t);
	}

	public Long getGiftInfoCount(GiftInfo t) {
		return this.giftInfoDao.selectEntityCount(t);
	}

	public List<GiftInfo> getGiftInfoList(GiftInfo t) {
		return this.giftInfoDao.selectEntityList(t);
	}

	public int modifyGiftInfo(GiftInfo t) {
		return this.giftInfoDao.updateEntity(t);
	}

	public int removeGiftInfo(GiftInfo t) {
		return this.giftInfoDao.deleteEntity(t);
	}

	public List<GiftInfo> getGiftInfoPaginatedList(GiftInfo t) {
		return this.giftInfoDao.selectEntityPaginatedList(t);
	}

	/**
	 * @author Hu,Hao
	 * @version 2013-11-07
	 */
	public List<GiftInfo> getGiftInfoForNamePaginatedList(GiftInfo t) {
		return this.giftInfoDao.selectGiftInfoForNamePaginatedList(t);
	}
	
	/**
	 * @author Xing,XiuDong
	 * @version 2013-11-09
	 */
	public List<GiftInfo> getGiftInfoResultForListWithCate(GiftInfo t) {
		return this.giftInfoDao.selectGiftInfoResultForListWithCate(t);
	}
}
