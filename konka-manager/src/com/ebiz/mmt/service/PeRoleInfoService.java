package com.ebiz.mmt.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ebiz.mmt.domain.PeRoleInfo;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2011-05-11 15:59:43
 */
public interface PeRoleInfoService {

	Long createPeRoleInfo(PeRoleInfo t);

	Long createPeRoleInfoWithoutAutoKey(PeRoleInfo t);

	int modifyPeRoleInfo(PeRoleInfo t);

	int removePeRoleInfo(PeRoleInfo t);

	PeRoleInfo getPeRoleInfo(PeRoleInfo t);

	List<PeRoleInfo> getPeRoleInfoList(PeRoleInfo t);

	Long getPeRoleInfoCount(PeRoleInfo t);

	List<PeRoleInfo> getPeRoleInfoPaginatedList(PeRoleInfo t);

	/**
	 * @author Li,Yuan
	 * @version 2011-05-16
	 */
	Long getPeRoleInfoWithNameCount(PeRoleInfo t) throws DataAccessException;

	/**
	 * @author Li,Yuan
	 * @version 2011-05-16
	 */
	List<PeRoleInfo> getPeRoleInfoWithNamePaginatedList(PeRoleInfo t) throws DataAccessException;

	/**
	 * @author du,zhiming
	 * @version 2011-12-27
	 * @desc 根据用户IDS获取去除重复的角色列表
	 */
	List<PeRoleInfo> getPeRoleInfoByUserIdsList(PeRoleInfo t) throws DataAccessException;

	/**
	 * @author Hu,Hao
	 * @version 2013-05-25
	 */
	List<PeRoleInfo> getPeRoleInfoForDeptNamePaginatedList(PeRoleInfo t) throws DataAccessException;
	
	/**
	 * @author Hu,Hao
	 * @version 2013-05-25
	 */
	void modifyPeRoleInfoForDept(PeRoleInfo t);
	
	
	/**
	 * @author Hu,Hao
	 * @version 2014-01-13
	 */
	PeRoleInfo getPeRoleInfoForRoleNames(PeRoleInfo t) throws DataAccessException;
}