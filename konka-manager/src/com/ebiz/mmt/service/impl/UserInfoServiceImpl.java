package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.EntpShopDao;
import com.ebiz.mmt.dao.UserInfoDao;
import com.ebiz.mmt.domain.EntpShop;
import com.ebiz.mmt.domain.UserInfo;
import com.ebiz.mmt.service.UserInfoService;

/**
 * @author Liu,AiZhou
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

	@Resource
	private UserInfoDao userInfoDao;

	@Resource
	private EntpShopDao entpShopDao;

	//
	// @Resource
	// private BuyerAttachmentDao buyerAttachmentDao;

	/*
	 * public void setUserInfoDao(UserInfoDao userInfoDao) { this.userInfoDao = userInfoDao; }
	 */

	public Long createUserInfo(UserInfo t) {
		Long id = this.userInfoDao.insertEntity(t);

		if (null != t.getMap().get("shop_id")) {
			EntpShop entpShop = new EntpShop();
			entpShop.setShop_id(Long.valueOf(String.valueOf(t.getMap().get("shop_id"))));
			entpShop.setHost_id(id);
			entpShopDao.updateEntity(entpShop);
		}

		return id;
	}

	public UserInfo getUserInfo(UserInfo t) {
		return this.userInfoDao.selectEntity(t);
	}

	public Long getUserInfoCount(UserInfo t) {
		return this.userInfoDao.selectEntityCount(t);
	}

	public List<UserInfo> getUserInfoList(UserInfo t) {
		return this.userInfoDao.selectEntityList(t);
	}

	public int modifyUserInfo(UserInfo t) {
		// String auditUserCard = (String) t.getMap().get("auditUserCard");
		// if (StringUtils.isNotBlank(auditUserCard)) {
		// List<BuyerAttachment> buyerAttachmentList = t.getBuyerAttachmentList();
		// if (null != buyerAttachmentList && buyerAttachmentList.size() > 0) {
		// for (BuyerAttachment temp : buyerAttachmentList) {
		// this.buyerAttachmentDao.updateEntity(temp);
		// }
		//
		// this.userInfoDao.updateEntity(t);
		// }
		// return 1;
		// }
		return this.userInfoDao.updateEntity(t);
	}

	public int removeUserInfo(UserInfo t) {
		return this.userInfoDao.deleteEntity(t);
	}

	public List<UserInfo> getUserInfoPaginatedList(UserInfo t) {
		return this.userInfoDao.selectEntityPaginatedList(t);
	}

	public List<UserInfo> getUserInfoListForEntpTypeId(UserInfo t) {
		return this.userInfoDao.selectUserInfoListForEntpTypeId(t);
	}

	/**
	 * @author Jiang,JiaYong
	 * @version 2010-07-23
	 */
	public List<UserInfo> getUserInfoListForBindWithEntpNameAndPindex(UserInfo t) {
		return this.userInfoDao.selectUserInfoListForBindWithEntpNameAndPindex(t);
	}
}
