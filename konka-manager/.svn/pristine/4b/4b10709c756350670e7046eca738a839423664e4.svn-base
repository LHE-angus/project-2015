package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.MmtEntpUserRegDao;
import com.ebiz.mmt.domain.MmtEntpUserReg;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2011-04-29 14:55:31
 */
@Service
public class MmtEntpUserRegDaoSqlMapImpl extends EntityDaoSqlMapImpl<MmtEntpUserReg> implements MmtEntpUserRegDao {

	/**
	 * @author Wu,ShangLong
	 * @version 2011-05-25
	 */
	public Long selectEntpUserRegWithLinkerCount(MmtEntpUserReg t) throws DataAccessException {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectEntpUserRegWithLinkerCount", t);
	}

	/**
	 * @author Wu,ShangLong
	 * @version 2011-05-25
	 */
	@SuppressWarnings("unchecked")
	public List<MmtEntpUserReg> selectEntpUserRegWithLinkerPaginatedList(MmtEntpUserReg t) throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForList("selectEntpUserRegWithLinkerPaginatedList", t);
	}
}
