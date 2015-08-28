package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.UserChangeInfoDao;
import com.ebiz.mmt.domain.UserChangeInfo;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-11-09 15:28:38
 */
@Service
public class UserChangeInfoDaoSqlMapImpl extends EntityDaoSqlMapImpl<UserChangeInfo> implements UserChangeInfoDao {

	@SuppressWarnings("unchecked")
	public List<UserChangeInfo> selectUserChangeInfoByTimeList(UserChangeInfo t) {
		return this.getSqlMapClientTemplate().queryForList("selectUserChangeInfoByTimeList", t);
	}

}
