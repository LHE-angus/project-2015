package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaXxZmdDao;
import com.ebiz.mmt.domain.KonkaXxZmd;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2012-01-09 09:19:48
 */
@Service
public class KonkaXxZmdDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaXxZmd> implements KonkaXxZmdDao {

	/**
	 * @author Jiang,JiaYong
	 * @version 2012-01-09
	 */
	@SuppressWarnings("unchecked")
	public List<KonkaXxZmd> selectKonkaXxZmdPaginatedListIncludeRelevanceInfo(KonkaXxZmd t) throws DataAccessException {
		return this.getSqlMapClientTemplate().queryForList("selectKonkaXxZmdPaginatedListIncludeRelevanceInfo", t);
	}

	/**
	 * @author Zheng,Kun
	 * @version 2012-01-10
	 */
	public Long selectKonkaXxZmdForClerkCount(KonkaXxZmd t) throws DataAccessException {
		return (Long) this.getSqlMapClientTemplate().queryForObject("selectKonkaXxZmdForClerkCount", t);
	}

	/**
	 * @author Zheng,Kun
	 * @version 2012-01-10
	 */
	@SuppressWarnings("unchecked")
	public List<KonkaXxZmd> selectKonkaXxZmdForClerkPaginatedList(KonkaXxZmd t) throws DataAccessException {
		return this.getSqlMapClientTemplate().queryForList("selectKonkaXxZmdForClerkPaginatedList", t);
	}

	/**
	 * @author Wu,ShangLong
	 * @version 2012-03-20
	 */
	@SuppressWarnings("unchecked")
	public List<KonkaXxZmd> selectKonkaXxZmdAndManagerList(KonkaXxZmd t) throws DataAccessException {
		return this.getSqlMapClientTemplate().queryForList("selectKonkaXxZmdAndManagerList", t);
	}

	/**
	 * @author Ren,zhong
	 * @throws 2012-04-05
	 */
	public int deleteKonkaXxZmdForPosAllocation(KonkaXxZmd t) throws DataAccessException {
		return this.getSqlMapClientTemplate().delete("deleteKonkaXxZmdForPosAllocation", t);
	}

	/**
	 * @author Ren,zhong
	 * @throws 2012-04-06
	 */
	public int updateKonkaXxZmdForCreditLine(KonkaXxZmd t) throws DataAccessException {
		return this.getSqlMapClientTemplate().update("updateKonkaXxZmdForCreditLine", t);
	}
	
	/**
	 * @author Hu,Hao
	 * @version 2013-06-19
	 */
	public Long selectKonkaXxZmdForRoleCount(KonkaXxZmd t){
		return (Long)this.getSqlMapClientTemplate().queryForObject("selectKonkaXxZmdForRoleCount",t);
	}
	
	/**
	 * @author Hu,Hao
	 * @version 2013-06-19
	 */
	@SuppressWarnings("unchecked")
	public List<KonkaXxZmd> selectKonkaXxZmdForRolePaginatedList(KonkaXxZmd t){
		return this.getSqlMapClientTemplate().queryForList("selectKonkaXxZmdForRolePaginatedList", t);
	}

	/**
	 * @author Hu,Hao
	 * @version 2013-08-04
	 */
	@SuppressWarnings("unchecked")
	public List<KonkaXxZmd> selectKonkaXxZmdForRoleIdList(KonkaXxZmd t){
		return this.getSqlMapClientTemplate().queryForList("selectKonkaXxZmdForRoleIdList", t);
	}
}
