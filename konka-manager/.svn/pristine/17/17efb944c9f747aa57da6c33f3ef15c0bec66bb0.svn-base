package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.ebiz.mmt.dao.SysModuleDao;
import com.ebiz.mmt.domain.SysModule;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * @author Xu,ZhengYang
 * @date 2010-11-11 02:47:12
 */

@Repository
public class SysModuleDaoSqlMapImpl extends EntityDaoSqlMapImpl<SysModule> implements SysModuleDao {

	/**
	 * @author Xu,ZhengYang
	 */
	@SuppressWarnings("unchecked")
	public List<SysModule> selectSysModuleListForModPopedom(SysModule sysModule) throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForList("selectSysModuleListForModPopedom", sysModule);
	}

	/**
	 * @author Hui,Gang
	 * @version Build 2010-11-16
	 */
	@SuppressWarnings("unchecked")
	public List<SysModule> selectSysModuleListForLeftTree(SysModule sysModule) throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForList("selectSysModuleListForLeftTree", sysModule);
	}
	
	@SuppressWarnings("unchecked")
	public List<SysModule> selectSysModuleParentList(SysModule t) throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForList("selectSysModuleParentList", t);
	}
	
	@SuppressWarnings("unchecked")
	public List<SysModule> selectSysModuleSonList(SysModule t) throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForList("selectSysModuleSonList", t);
	}
	
	/**
	 * @author Hui,Gang
	 * @version Build 2011-10-11
	 */
	@SuppressWarnings("unchecked")	
	public List<SysModule> selectSysModuleListForChildModPopedom(SysModule t) throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForList("selectSysModuleListForChildModPopedom", t);
	}

	/**
	 * @desc 手机网页端权限用户角色获取功能结点
	 * @author Hui,Gang
	 * @version 2012-4-27 下午3:10:49
	 */
	@SuppressWarnings("unchecked")
	public List<SysModule> selectSysModuleListForMobileRole(SysModule sysModule) throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForList("selectSysModuleListForMobileRole", sysModule);
	}
}
