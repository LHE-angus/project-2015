package com.ebiz.mmt.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ebiz.mmt.domain.SysModule;
import com.ebiz.ssi.dao.EntityDao;

/**
 * @author Xu,ZhengYang
 * @date 2010-11-11 02:47:12
 */

public interface SysModuleDao extends EntityDao<SysModule> {

	List<SysModule> selectSysModuleListForModPopedom(SysModule sysModule) throws DataAccessException;

	/**
	 * @author Hui,Gang
	 * @version Build 2010-11-16
	 */
	List<SysModule> selectSysModuleListForLeftTree(SysModule sysModule) throws DataAccessException;

	List<SysModule> selectSysModuleSonList(SysModule t) throws DataAccessException;

	List<SysModule> selectSysModuleParentList(SysModule t) throws DataAccessException;

	List<SysModule> selectSysModuleListForChildModPopedom(SysModule t) throws DataAccessException;

	/**
	 * @desc 手机网页端权限用户角色获取功能结点
	 * @author Hui,Gang
	 * @version 2012-4-27 下午3:10:49
	 */
	List<SysModule> selectSysModuleListForMobileRole(SysModule sysModule) throws DataAccessException;
}
