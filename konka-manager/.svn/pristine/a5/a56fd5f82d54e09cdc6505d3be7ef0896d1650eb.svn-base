package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.StdEntpKeysKeysDao;
import com.ebiz.mmt.dao.StdEntpKeysOprRecDao;
import com.ebiz.mmt.dao.StdEntpUserDao;
import com.ebiz.mmt.domain.StdEntpKeysKeys;
import com.ebiz.mmt.domain.StdEntpKeysOprRec;
import com.ebiz.mmt.domain.StdEntpUser;
import com.ebiz.mmt.service.StdEntpKeysOprRecService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2011-09-20 15:43:53
 */
@Service
public class StdEntpKeysOprRecServiceImpl implements StdEntpKeysOprRecService {

	@Resource
	private StdEntpKeysOprRecDao stdEntpKeysOprRecDao;

	@Resource
	private StdEntpUserDao stdEntpUserDao;

	@Resource
	private StdEntpKeysKeysDao stdEntpKeysKeysDao;

	public Long createStdEntpKeysOprRec(StdEntpKeysOprRec t) {
		Long id = this.stdEntpKeysOprRecDao.insertEntity(t);

		if (null != t.getEntp_id()) {
			StdEntpUser user = new StdEntpUser();
			if (1 == t.getOwn_sys()) {
				user.setUser_state(0); // jdxx
			} else if (2 == t.getOwn_sys()) {
				user.setUser_state(1); // yjhx
			}
			user.setEntp_id(t.getEntp_id());
			List<StdEntpUser> userList = stdEntpUserDao.selectEntityList(user);
			if (null != userList && userList.size() > 0) {
				for (StdEntpUser u : userList) {
					StdEntpKeysKeys key = new StdEntpKeysKeys();
					key.setOpr_id(id);
					key.setKey_seq(u.getKey_seq());
					stdEntpKeysKeysDao.insertEntity(key);
				}
			}
		}

		return id;
	}

	public StdEntpKeysOprRec getStdEntpKeysOprRec(StdEntpKeysOprRec t) {
		return this.stdEntpKeysOprRecDao.selectEntity(t);
	}

	public Long getStdEntpKeysOprRecCount(StdEntpKeysOprRec t) {
		return this.stdEntpKeysOprRecDao.selectEntityCount(t);
	}

	public List<StdEntpKeysOprRec> getStdEntpKeysOprRecList(StdEntpKeysOprRec t) {
		return this.stdEntpKeysOprRecDao.selectEntityList(t);
	}

	public int modifyStdEntpKeysOprRec(StdEntpKeysOprRec t) {
		return this.stdEntpKeysOprRecDao.updateEntity(t);
	}

	public int removeStdEntpKeysOprRec(StdEntpKeysOprRec t) {
		return this.stdEntpKeysOprRecDao.deleteEntity(t);
	}

	public List<StdEntpKeysOprRec> getStdEntpKeysOprRecPaginatedList(StdEntpKeysOprRec t) {
		return this.stdEntpKeysOprRecDao.selectEntityPaginatedList(t);
	}

}
