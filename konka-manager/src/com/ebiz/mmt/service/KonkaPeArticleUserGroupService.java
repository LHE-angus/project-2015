package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.KonkaPeArticleUserGroup;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-07-11 17:25:49
 */
public interface KonkaPeArticleUserGroupService {

	Long createKonkaPeArticleUserGroup(KonkaPeArticleUserGroup t);

	int modifyKonkaPeArticleUserGroup(KonkaPeArticleUserGroup t);

	int removeKonkaPeArticleUserGroup(KonkaPeArticleUserGroup t);

	KonkaPeArticleUserGroup getKonkaPeArticleUserGroup(KonkaPeArticleUserGroup t);

	List<KonkaPeArticleUserGroup> getKonkaPeArticleUserGroupList(KonkaPeArticleUserGroup t);

	Long getKonkaPeArticleUserGroupCount(KonkaPeArticleUserGroup t);

	List<KonkaPeArticleUserGroup> getKonkaPeArticleUserGroupPaginatedList(KonkaPeArticleUserGroup t);

	/**
	 * @author Hu,Hao
	 * @version 2013-07-13
	 */
	void createKonkaPeArticleUserGroupForUsers(KonkaPeArticleUserGroup t);
	
	/**
	 * @author Hu,Hao
	 * @version 2013-07-13
	 */
	void modifyKonkaPeArticleUserGroupForUsers(KonkaPeArticleUserGroup t);

}