package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaPeArticleGUsersDao;
import com.ebiz.mmt.dao.KonkaPeArticleUserGroupDao;
import com.ebiz.mmt.domain.KonkaPeArticleGUsers;
import com.ebiz.mmt.domain.KonkaPeArticleUserGroup;
import com.ebiz.mmt.service.KonkaPeArticleUserGroupService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-07-11 17:25:49
 */
@Service
public class KonkaPeArticleUserGroupServiceImpl implements KonkaPeArticleUserGroupService {

	@Resource
	private KonkaPeArticleUserGroupDao konkaPeArticleUserGroupDao;

	@Resource
	private KonkaPeArticleGUsersDao konkaPeArticleGUsersDao;

	public Long createKonkaPeArticleUserGroup(KonkaPeArticleUserGroup t) {
		return this.konkaPeArticleUserGroupDao.insertEntity(t);
	}

	public KonkaPeArticleUserGroup getKonkaPeArticleUserGroup(KonkaPeArticleUserGroup t) {
		return this.konkaPeArticleUserGroupDao.selectEntity(t);
	}

	public Long getKonkaPeArticleUserGroupCount(KonkaPeArticleUserGroup t) {
		return this.konkaPeArticleUserGroupDao.selectEntityCount(t);
	}

	public List<KonkaPeArticleUserGroup> getKonkaPeArticleUserGroupList(KonkaPeArticleUserGroup t) {
		return this.konkaPeArticleUserGroupDao.selectEntityList(t);
	}

	public int modifyKonkaPeArticleUserGroup(KonkaPeArticleUserGroup t) {
		return this.konkaPeArticleUserGroupDao.updateEntity(t);
	}

	public int removeKonkaPeArticleUserGroup(KonkaPeArticleUserGroup t) {
		return this.konkaPeArticleUserGroupDao.deleteEntity(t);
	}

	public List<KonkaPeArticleUserGroup> getKonkaPeArticleUserGroupPaginatedList(KonkaPeArticleUserGroup t) {
		return this.konkaPeArticleUserGroupDao.selectEntityPaginatedList(t);
	}

	/**
	 * @author Hu,Hao
	 * @version 2013-07-13
	 */
	public void createKonkaPeArticleUserGroupForUsers(KonkaPeArticleUserGroup t) {
		Long id = this.konkaPeArticleUserGroupDao.insertEntity(t);

		String group_user_ids = t.getMap().get("group_user_ids").toString();
		if (null != group_user_ids) {
			String[] group_user_id = StringUtils.split(group_user_ids, ",");
			for (int i = 0; i < group_user_id.length; i++) {

				KonkaPeArticleGUsers entity = new KonkaPeArticleGUsers();
				entity.setGroup_id(id);
				entity.setGroup_user_id(group_user_id[i].split("##")[0]);
				entity.setGroup_user_name(group_user_id[i].split("##")[1]);
				this.konkaPeArticleGUsersDao.insertEntity(entity);
			}
		}
	}

	/**
	 * @author Hu,Hao
	 * @version 2013-07-13
	 */
	public void modifyKonkaPeArticleUserGroupForUsers(KonkaPeArticleUserGroup t) {
		this.konkaPeArticleUserGroupDao.updateEntity(t);// 更新群组信息

		KonkaPeArticleGUsers entity1 = new KonkaPeArticleGUsers();// 删除群组成员
		entity1.setGroup_id(t.getGroup_id());
		this.konkaPeArticleGUsersDao.deleteEntity(entity1);

		String group_user_ids = t.getMap().get("group_user_ids").toString();
		if (null != group_user_ids) {// 新增群组成员
			String[] group_user_id = StringUtils.split(group_user_ids, ",");
			for (int i = 0; i < group_user_id.length; i++) {

				KonkaPeArticleGUsers entity = new KonkaPeArticleGUsers();
				entity.setGroup_id(t.getGroup_id());
				entity.setGroup_user_id(group_user_id[i].split("##")[0]);
				entity.setGroup_user_name(group_user_id[i].split("##")[1]);
				this.konkaPeArticleGUsersDao.insertEntity(entity);
			}
		}
	}

}
