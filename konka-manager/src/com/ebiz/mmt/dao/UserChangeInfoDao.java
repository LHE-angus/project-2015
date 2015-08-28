package com.ebiz.mmt.dao;

import java.util.List;

import com.ebiz.mmt.domain.UserChangeInfo;
import com.ebiz.ssi.dao.EntityDao;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-11-09 15:28:38
 */
public interface UserChangeInfoDao extends EntityDao<UserChangeInfo> {

	List<UserChangeInfo> selectUserChangeInfoByTimeList(UserChangeInfo t);

}
