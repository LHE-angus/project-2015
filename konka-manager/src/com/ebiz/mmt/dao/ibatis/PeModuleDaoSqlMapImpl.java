package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.PeModuleDao;
import com.ebiz.mmt.domain.PeModule;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2011-02-24 10:17:10
 */
@Service
public class PeModuleDaoSqlMapImpl extends EntityDaoSqlMapImpl<PeModule> implements PeModuleDao {

	// add by liyuan
	@SuppressWarnings("unchecked")
	public List<PeModule> selectPeModuleParentList(PeModule t) throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForList("selectPeModuleParentList", t);
	}

	/**
	 * @author Jiang,JiaYong
	 * @date 2011-02-25
	 */
	@SuppressWarnings("unchecked")
	public List<PeModule> selectPeModuleAllParentList(PeModule t) throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForList("selectPeModuleAllParentList", t);
	}

	/**
	 * @author Li,Yuan
	 * @version 2011-05-13
	 */
	@SuppressWarnings("unchecked")
	public List<PeModule> selectPeModuleListForAuthor() throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForList("selectPeModuleListForAuthor");
	}

	/**
	 * @author Jiang,JiaYong
	 * @version 2011-05-16
	 */
	@SuppressWarnings("unchecked")
	public List<PeModule> selectPeModuleForPeUserList(PeModule t) throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForList("selectPeModuleForPeUserList", t);
	}
}
