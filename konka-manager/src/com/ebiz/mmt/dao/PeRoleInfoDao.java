package com.ebiz.mmt.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ebiz.mmt.domain.PeRoleInfo;
import com.ebiz.ssi.dao.EntityDao;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2011-05-11 15:59:43
 */
public interface PeRoleInfoDao extends EntityDao<PeRoleInfo> {

	/**
	 * @author Li,Yuan
	 * @version 2011-05-16
	 */
	Long selectPeRoleInfoWithNameCount(PeRoleInfo t) throws DataAccessException;
	
	Long insertPeRoleInfoWithoutAutoKey(PeRoleInfo t) throws DataAccessException;

	/**
	 * @author Li,Yuan
	 * @version 2011-05-16
	 */
	List<PeRoleInfo> selectPeRoleInfoWithNamePaginatedList(PeRoleInfo t) throws DataAccessException;

	/**
	 * @author du,zhiming
	 * @version 2011-12-27
	 * @desc 根据用户IDS获取去除重复的角色列表
	 */
	List<PeRoleInfo> selectPeRoleInfoByUserIdsList(PeRoleInfo t) throws DataAccessException;

	/**
	 * @author Hu,Hao
	 * @version 2013-05-25
	 */
	List<PeRoleInfo> selectPeRoleInfoForDeptNamePaginatedList(PeRoleInfo t) throws DataAccessException;
	
	/**
	 * @author Hu,Hao
	 * @version 2014-01-13
	 */
	PeRoleInfo selectPeRoleInfoForRoleNames(PeRoleInfo t) throws DataAccessException;
	
}
