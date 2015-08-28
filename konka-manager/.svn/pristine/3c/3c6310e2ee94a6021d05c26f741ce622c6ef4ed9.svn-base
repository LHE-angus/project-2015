package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.PeRoleInfoDao;
import com.ebiz.mmt.domain.PeRoleInfo;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2011-05-11 15:59:43
 */
@Service
@SuppressWarnings("unchecked")
public class PeRoleInfoDaoSqlMapImpl extends EntityDaoSqlMapImpl<PeRoleInfo> implements PeRoleInfoDao {

	/**
	 * @author Li,Yuan
	 * @version 2011-05-16
	 */
	public Long selectPeRoleInfoWithNameCount(PeRoleInfo t) throws DataAccessException {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectPeRoleInfoWithNameCount", t);
	}

	public Long insertPeRoleInfoWithoutAutoKey(PeRoleInfo t) throws DataAccessException {
		return (Long) super.getSqlMapClientTemplate().insert("insertPeRoleInfoWithoutAutoKey", t);
	}

	/**
	 * @author Li,Yuan
	 * @version 2011-05-16
	 */

	public List<PeRoleInfo> selectPeRoleInfoWithNamePaginatedList(PeRoleInfo t) throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForList("selectPeRoleInfoWithNamePaginatedList", t);
	}

	@Override
	public List<PeRoleInfo> selectPeRoleInfoByUserIdsList(PeRoleInfo t) throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForList("selectPeRoleInfoByUserIdsList", t);
	}

	/**
	 * @author Hu,Hao
	 * @version 2013-05-25
	 */
	public List<PeRoleInfo> selectPeRoleInfoForDeptNamePaginatedList(PeRoleInfo t) throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForList("selectPeRoleInfoForDeptNamePaginatedList", t);
	}

	/**
	 * @author Hu,Hao
	 * @version 2014-01-13
	 */
	public PeRoleInfo selectPeRoleInfoForRoleNames(PeRoleInfo t) throws DataAccessException {
		return (PeRoleInfo) super.getSqlMapClientTemplate().queryForObject("selectPeRoleInfoForRoleNames", t);
	}
}
