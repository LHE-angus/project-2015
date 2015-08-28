package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.EntpShopDao;
import com.ebiz.mmt.dao.StdEntpKeysKeysDao;
import com.ebiz.mmt.dao.StdEntpKeysOprRecDao;
import com.ebiz.mmt.dao.StdEntpMainDao;
import com.ebiz.mmt.dao.StdEntpUserDao;
import com.ebiz.mmt.domain.EntpShop;
import com.ebiz.mmt.domain.StdEntpKeysKeys;
import com.ebiz.mmt.domain.StdEntpKeysOprRec;
import com.ebiz.mmt.domain.StdEntpMain;
import com.ebiz.mmt.domain.StdEntpUser;
import com.ebiz.mmt.service.StdEntpUserService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2010-05-28 16:48:39
 */
@Service
public class StdEntpUserServiceImpl implements StdEntpUserService {

	@Resource
	private StdEntpUserDao stdEntpUserDao;

	@Resource
	private StdEntpMainDao stdEntpMainDao;

	@Resource
	private EntpShopDao entpShopDao;

	@Resource
	private StdEntpKeysOprRecDao stdEntpKeysOprRecDao;

	@Resource
	private StdEntpKeysKeysDao stdEntpKeysKeysDao;

	public Long createStdEntpUser(StdEntpUser t) {
		StdEntpMain stdEntpMain = (StdEntpMain) t.getMap().get("stdEntpMain");

		if (null != stdEntpMain) {
			if (null == stdEntpMain.getId() || "".equals(stdEntpMain.getId().toString())
					|| "0".equals(stdEntpMain.getId().toString())) {
				this.stdEntpMainDao.insertEntity(stdEntpMain);
			} else {
				this.stdEntpMainDao.updateEntity(stdEntpMain);
			}
		}

		// 以旧换新 绑定密钥的时候处理
		EntpShop es = (EntpShop) t.getMap().get("entpShop");
		if (null != es) {
			this.entpShopDao.updateEntity(es);
		}

		if (null == t.getUser_id() || "".equals(t.getUser_id().toString()) || "0".equals(t.getUser_id().toString())) {
			return this.stdEntpUserDao.insertEntity(t);
		}
		return Long.valueOf(this.modifyStdEntpUser(t));
	}

	public StdEntpUser getStdEntpUser(StdEntpUser t) {
		return this.stdEntpUserDao.selectEntity(t);
	}

	public Long getStdEntpUserCount(StdEntpUser t) {
		return this.stdEntpUserDao.selectEntityCount(t);
	}

	public List<StdEntpUser> getStdEntpUserList(StdEntpUser t) {
		return this.stdEntpUserDao.selectEntityList(t);
	}

	public int modifyStdEntpUser(StdEntpUser t) {
		StdEntpKeysOprRec oprRec = t.getStdEntpKeysOprRec();
		if (null != oprRec) {
			Long oprRecId = this.stdEntpKeysOprRecDao.insertEntity(oprRec);
			if (null != oprRec.getEntp_id()) {
				StdEntpUser user = new StdEntpUser();
				if (1 == oprRec.getOwn_sys()) {
					user.setUser_state(0); // jdxx
				} else if (2 == oprRec.getOwn_sys()) {
					user.setUser_state(1); // yjhx
				}
				user.setEntp_id(oprRec.getEntp_id());
				List<StdEntpUser> userList = stdEntpUserDao.selectEntityList(user);
				if (null != userList && userList.size() > 0) {
					for (StdEntpUser u : userList) {
						StdEntpKeysKeys key = new StdEntpKeysKeys();
						key.setOpr_id(oprRecId);
						key.setKey_seq(u.getKey_seq());
						stdEntpKeysKeysDao.insertEntity(key);
					}
				}
			}
		}

		int count = stdEntpUserDao.updateEntity(t);

		return count;
	}

	public int removeStdEntpUser(StdEntpUser t) {
		return this.stdEntpUserDao.deleteEntity(t);
	}

	public List<StdEntpUser> getStdEntpUserPaginatedList(StdEntpUser t) {
		return this.stdEntpUserDao.selectEntityPaginatedList(t);
	}

	public int modifyStdEntpUserAtLogin(StdEntpUser t) {
		StdEntpMain stdEntpMain = (StdEntpMain) t.getMap().get("stdEntpMain");
		EntpShop entpShop = (EntpShop) t.getMap().get("entpShop");

		if (null != stdEntpMain) {
			this.stdEntpMainDao.updateEntity(stdEntpMain);
		}

		if (null != entpShop) {
			this.entpShopDao.updateEntity(entpShop);
		}

		return this.stdEntpUserDao.updateEntity(t);
	}

	public int modifyStdEntpUserWithKeySeq(StdEntpUser t) {
		return this.stdEntpUserDao.updateStdEntpUserWithKeySeq(t);
	}

	/**
	 * @author Du,HongGang
	 * @version 2010-07-05
	 */
	public Long getDistinctMmtUserStdEntpUserCount(StdEntpUser t) {
		return this.stdEntpUserDao.selectDistinctMmtUserStdEntpUserCount(t);
	}

	/**
	 * @author Du,HongGang
	 * @version 2010-07-05
	 */
	public Long getDistinctEntpIdStdEntpUserCount(StdEntpUser t) {
		return this.stdEntpUserDao.selectDistinctEntpIdStdEntpUserCount(t);
	}

	/**
	 * @author Jiang,JiaYong
	 * @version 2011-05-19
	 */
	public List<StdEntpUser> getStdEntpUserWithShopIdRandomOrderList(StdEntpUser t) {
		return this.stdEntpUserDao.selectStdEntpUserWithShopIdRandomOrderList(t);
	}

	/**
	 * @author Jiang,JiaYong
	 * @version 2011-06-28
	 */
	public int createOrModifyStdEntpUserInStdEntpMain(StdEntpUser t) {
		StdEntpMain sem = t.getStdEntpMain();
		StdEntpKeysOprRec oprRec = t.getStdEntpKeysOprRec();

		if (null != oprRec) {
			Long oprRecId = this.stdEntpKeysOprRecDao.insertEntity(oprRec);
			if (null != oprRec.getEntp_id()) {
				StdEntpUser user = new StdEntpUser();
				if (1 == oprRec.getOwn_sys()) {
					user.setUser_state(0); // jdxx
				} else if (2 == oprRec.getOwn_sys()) {
					user.setUser_state(1); // yjhx
				}
				user.setEntp_id(oprRec.getEntp_id());
				List<StdEntpUser> userList = stdEntpUserDao.selectEntityList(user);
				if (null != userList && userList.size() > 0) {
					for (StdEntpUser u : userList) {
						StdEntpKeysKeys key = new StdEntpKeysKeys();
						key.setOpr_id(oprRecId);
						key.setKey_seq(u.getKey_seq());
						stdEntpKeysKeysDao.insertEntity(key);
					}
				}
			}
		}

		int count = 0;
		if (null != t.getUser_id() && StringUtils.isNotEmpty(t.getUser_id().toString())) {
			count = this.stdEntpUserDao.updateEntity(t);
		} else {
			count = this.stdEntpUserDao.insertEntity(t).intValue();
		}

		if (null != sem.getId() && StringUtils.isNotEmpty(sem.getId().toString())) {
			this.stdEntpMainDao.updateEntity(sem);
		} else {
			this.stdEntpMainDao.insertEntity(sem);
		}

		return count;
	}

	/**
	 * @author Zheng,Kun
	 * @version 2011-07-15
	 */
	public List<StdEntpUser> getKeyEntpShopInfoMatching(StdEntpUser t) throws DataAccessException {

		return this.stdEntpUserDao.selectKeyEntpShopInfoMatching(t);
	}

	/**
	 * @author Ren, zhong
	 * @version 2011-08-17
	 */
	public List<StdEntpUser> getKeyEntpUserInfoForLogoutResultList(StdEntpUser t) throws DataAccessException {
		return this.stdEntpUserDao.selectKeyEntpUserInfoForLogoutResultList(t);
	}

	/**
	 * @author Wu,Yang
	 * @version 2011-10-13
	 * @desc 康佳进销存，用户登录时，取用户对应企业所绑定的所有密钥
	 */
	public List<StdEntpUser> getStdEntpUserListWithOwnStdEntpMain(StdEntpUser t) {
		return this.stdEntpUserDao.selectStdEntpUserListWithOwnStdEntpMain(t);
	}

	/**
	 * @author Li,Ka
	 * @version 2012-3-15
	 * @desc 有密钥登录时查询登录网点信息和企业信息，需要合并买卖提用户和官网用户的所有数据，防止查询不到
	 */
	public List<StdEntpUser> getStdEntpUserOrUserInfoList(StdEntpUser t) {
		return this.stdEntpUserDao.selectStdEntpUserOrUserInfoList(t);
	}

	/**
	 * @author Zhang,DaPeng
	 * @version 2010-09-21
	 */
	// public Long queryIdByKey(StdEntpUser seu) {
	// return this.stdEntpUserDao.queryIdByKey(seu);
	// }

}
