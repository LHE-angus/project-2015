package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.KonkaPeArticleGUsers;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-07-11 17:25:49
 */
public interface KonkaPeArticleGUsersService {

	Long createKonkaPeArticleGUsers(KonkaPeArticleGUsers t);

	int modifyKonkaPeArticleGUsers(KonkaPeArticleGUsers t);

	int removeKonkaPeArticleGUsers(KonkaPeArticleGUsers t);

	KonkaPeArticleGUsers getKonkaPeArticleGUsers(KonkaPeArticleGUsers t);

	List<KonkaPeArticleGUsers> getKonkaPeArticleGUsersList(KonkaPeArticleGUsers t);

	Long getKonkaPeArticleGUsersCount(KonkaPeArticleGUsers t);

	List<KonkaPeArticleGUsers> getKonkaPeArticleGUsersPaginatedList(KonkaPeArticleGUsers t);

}