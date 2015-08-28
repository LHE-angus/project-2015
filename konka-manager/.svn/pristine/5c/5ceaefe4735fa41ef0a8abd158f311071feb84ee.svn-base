package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaXxZmdUsersDao;
import com.ebiz.mmt.domain.KonkaXxZmdUsers;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2012-01-09 17:41:31
 */
@Service
public class KonkaXxZmdUsersDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaXxZmdUsers> implements KonkaXxZmdUsersDao {

	/**
	 * @author Zheng,Kun
	 * @version 2012-01-10
	 */
	@SuppressWarnings("unchecked")
	public List<KonkaXxZmdUsers> selectKonkaXxZmdForClerkManPaginatedList(KonkaXxZmdUsers t) throws DataAccessException {
		return this.getSqlMapClientTemplate().queryForList("selectKonkaXxZmdForClerkManPaginatedList", t);
	}

	/**
	 * @author Zheng,Kun
	 * @version 2012-01-10
	 */
	@SuppressWarnings("unchecked")
	public List<KonkaXxZmdUsers> selectKonkaXxZmdForWithUserList(KonkaXxZmdUsers t) throws DataAccessException {
		return this.getSqlMapClientTemplate().queryForList("selectKonkaXxZmdForWithUserList", t);
	}
}
