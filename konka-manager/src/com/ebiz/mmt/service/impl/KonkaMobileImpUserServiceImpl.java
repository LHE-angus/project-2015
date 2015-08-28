package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaMobileImpHisDao;
import com.ebiz.mmt.dao.KonkaMobileImpUserDao;
import com.ebiz.mmt.domain.KonkaMobileImpHis;
import com.ebiz.mmt.domain.KonkaMobileImpUser;
import com.ebiz.mmt.service.KonkaMobileImpUserService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-05-23 15:45:29
 */
@Service
public class KonkaMobileImpUserServiceImpl implements KonkaMobileImpUserService {

	@Resource
	private KonkaMobileImpUserDao konkaMobileImpUserDao;
	
	@Resource
	private KonkaMobileImpHisDao konkaMobileImpHisDao;

	public Long createKonkaMobileImpUser(KonkaMobileImpUser t) {
		return this.konkaMobileImpUserDao.insertEntity(t);
	}
	
	public Long createKonkaMobileImpUserListAndHis(List<KonkaMobileImpUser> konkaMobileImpUserList, KonkaMobileImpHis konkaMobileImpHis) {
		Long his_id = this.konkaMobileImpHisDao.insertEntity(konkaMobileImpHis);
		for (KonkaMobileImpUser konkaMobileImpUser : konkaMobileImpUserList) {
			konkaMobileImpUser.setOpr_his_id(his_id);
			this.konkaMobileImpUserDao.insertEntity(konkaMobileImpUser);
		}
		return Long.valueOf(konkaMobileImpUserList.size());
	}

	public KonkaMobileImpUser getKonkaMobileImpUser(KonkaMobileImpUser t) {
		return this.konkaMobileImpUserDao.selectEntity(t);
	}

	public Long getKonkaMobileImpUserCount(KonkaMobileImpUser t) {
		return this.konkaMobileImpUserDao.selectEntityCount(t);
	}

	public List<KonkaMobileImpUser> getKonkaMobileImpUserList(KonkaMobileImpUser t) {
		return this.konkaMobileImpUserDao.selectEntityList(t);
	}

	public int modifyKonkaMobileImpUser(KonkaMobileImpUser t) {
		return this.konkaMobileImpUserDao.updateEntity(t);
	}

	public int removeKonkaMobileImpUser(KonkaMobileImpUser t) {
		return this.konkaMobileImpUserDao.deleteEntity(t);
	}

	public List<KonkaMobileImpUser> getKonkaMobileImpUserPaginatedList(KonkaMobileImpUser t) {
		return this.konkaMobileImpUserDao.selectEntityPaginatedList(t);
	}

}
