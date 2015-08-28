package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.WeixinBindUserDao;
import com.ebiz.mmt.domain.WeixinBindUser;
import com.ebiz.mmt.service.WeixinBindUserService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-10-24 14:43:36
 */
@Service
public class WeixinBindUserServiceImpl implements WeixinBindUserService {

	@Resource
	private WeixinBindUserDao weixinBindUserDao;
	

	public Long createWeixinBindUser(WeixinBindUser t) {
		return this.weixinBindUserDao.insertEntity(t);
	}

	public WeixinBindUser getWeixinBindUser(WeixinBindUser t) {
		return this.weixinBindUserDao.selectEntity(t);
	}

	public Long getWeixinBindUserCount(WeixinBindUser t) {
		return this.weixinBindUserDao.selectEntityCount(t);
	}

	public List<WeixinBindUser> getWeixinBindUserList(WeixinBindUser t) {
		return this.weixinBindUserDao.selectEntityList(t);
	}

	public int modifyWeixinBindUser(WeixinBindUser t) {
		return this.weixinBindUserDao.updateEntity(t);
	}

	public int removeWeixinBindUser(WeixinBindUser t) {
		return this.weixinBindUserDao.deleteEntity(t);
	}

	public List<WeixinBindUser> getWeixinBindUserPaginatedList(WeixinBindUser t) {
		return this.weixinBindUserDao.selectEntityPaginatedList(t);
	}

}
