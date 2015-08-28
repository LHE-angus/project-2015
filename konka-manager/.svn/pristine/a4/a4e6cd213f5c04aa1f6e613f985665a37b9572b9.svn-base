package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaPeArticleGUsersDao;
import com.ebiz.mmt.domain.KonkaPeArticleGUsers;
import com.ebiz.mmt.service.KonkaPeArticleGUsersService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-07-11 17:25:49
 */
@Service
public class KonkaPeArticleGUsersServiceImpl implements KonkaPeArticleGUsersService {

	@Resource
	private KonkaPeArticleGUsersDao konkaPeArticleGUsersDao;
	

	public Long createKonkaPeArticleGUsers(KonkaPeArticleGUsers t) {
		return this.konkaPeArticleGUsersDao.insertEntity(t);
	}

	public KonkaPeArticleGUsers getKonkaPeArticleGUsers(KonkaPeArticleGUsers t) {
		return this.konkaPeArticleGUsersDao.selectEntity(t);
	}

	public Long getKonkaPeArticleGUsersCount(KonkaPeArticleGUsers t) {
		return this.konkaPeArticleGUsersDao.selectEntityCount(t);
	}

	public List<KonkaPeArticleGUsers> getKonkaPeArticleGUsersList(KonkaPeArticleGUsers t) {
		return this.konkaPeArticleGUsersDao.selectEntityList(t);
	}

	public int modifyKonkaPeArticleGUsers(KonkaPeArticleGUsers t) {
		return this.konkaPeArticleGUsersDao.updateEntity(t);
	}

	public int removeKonkaPeArticleGUsers(KonkaPeArticleGUsers t) {
		return this.konkaPeArticleGUsersDao.deleteEntity(t);
	}

	public List<KonkaPeArticleGUsers> getKonkaPeArticleGUsersPaginatedList(KonkaPeArticleGUsers t) {
		return this.konkaPeArticleGUsersDao.selectEntityPaginatedList(t);
	}

}
