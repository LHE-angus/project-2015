package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.EcUserDao;
import com.ebiz.mmt.domain.EcUser;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-09-09 10:06:25
 */
@Service
public class EcUserDaoSqlMapImpl extends EntityDaoSqlMapImpl<EcUser> implements EcUserDao {

	@SuppressWarnings("unchecked")
	public List<EcUser> selectEcUserWithPositionNameAndFullDeptNamePaginatedList(EcUser t) throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForList("selectEcUserWithPositionNameAndFullDeptNamePaginatedList",
		        t);
	}

	@SuppressWarnings("unchecked")
	public List<EcUser> selectEcUserPaginatedForHydjList(EcUser t) throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForList("selectEcUserPaginatedForHydjList", t);
	}

	@SuppressWarnings("unchecked")
	public Long selectEcUserForHydjCount(EcUser t) throws DataAccessException {
		return (Long) this.getSqlMapClientTemplate().queryForObject("selectEcUserForHydjCount", t);
	}

	@Override
	public int insertEcUser(EcUser t) {
		this.getSqlMapClientTemplate().insert("insertEcUser", t);
		return 0;
	}

	// 查找当前用户的下级会员信息总数
	@Override
	public Long selectSubEcUserByUserNameCount(EcUser t) throws DataAccessException {
		return (Long) this.getSqlMapClientTemplate().queryForObject("selectSubEcUserByUserNameCount", t);
	}

	// 查找当前用户的下级会员信息
	@Override
	public List<EcUser> selectSubEcUserByUserNameList(EcUser t) throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForList("selectSubEcUserByUserNameList", t);
	}

	@Override
	public Long selectEcUserNewCount(EcUser t) throws DataAccessException {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectEcUserNewCount", t);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EcUser> selectEcUserNewPaginatedList(EcUser t) throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForList("selectEcUserNewPaginatedList", t);
	}

	@Override
	public Long selectEcUserNo(EcUser t) throws DataAccessException {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectEcUserNo", t);
	}

}
