package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.PeRoleUserDao;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.service.PeRoleUserService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2011-05-11 15:59:43
 */
@Service
public class PeRoleUserServiceImpl implements PeRoleUserService {

	@Resource
	private PeRoleUserDao peRoleUserDao;

	public Long createPeRoleUser(PeRoleUser t) {
		PeRoleUser ru = new PeRoleUser();
		ru.setUser_id(t.getUser_id());
		ru.setRole_id(t.getRole_id());
		ru.getMap().put("role_id_and_user_id", true);
		peRoleUserDao.deleteEntity(ru);

		String[] user_ids = (String[]) t.getMap().get("user_ids");
		if (null != user_ids) {
			for (String user_id : user_ids) {
				PeRoleUser _ru = new PeRoleUser();
				_ru.setUser_id(new Long(user_id));
				_ru.setRole_id(t.getRole_id());
				this.peRoleUserDao.insertEntity(_ru);
			}
		}

		return 1l;
	}

	public PeRoleUser getPeRoleUser(PeRoleUser t) {
		return this.peRoleUserDao.selectEntity(t);
	}

	public Long getPeRoleUserCount(PeRoleUser t) {
		return this.peRoleUserDao.selectEntityCount(t);
	}

	public List<PeRoleUser> getPeRoleUserList(PeRoleUser t) {
		return this.peRoleUserDao.selectEntityList(t);
	}

	public int modifyPeRoleUser(PeRoleUser t) {
		return this.peRoleUserDao.updateEntity(t);
	}

	public int removePeRoleUser(PeRoleUser t) {
		return this.peRoleUserDao.deleteEntity(t);
	}

	public List<PeRoleUser> getPeRoleUserPaginatedList(PeRoleUser t) {
		return this.peRoleUserDao.selectEntityPaginatedList(t);
	}

}
