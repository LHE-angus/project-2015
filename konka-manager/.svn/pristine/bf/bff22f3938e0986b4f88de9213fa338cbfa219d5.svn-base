package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.StdEntpLoginDao;
import com.ebiz.mmt.domain.StdEntpLogin;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2010-12-23 10:47:01
 */
@Service
public class StdEntpLoginDaoSqlMapImpl extends EntityDaoSqlMapImpl<StdEntpLogin> implements StdEntpLoginDao {

	/**
	 * @author Jiang,JiaYong
	 * @version 2010-12-23
	 */
	@SuppressWarnings("unchecked")
	public List<StdEntpLogin> selectStdEntpLoginGroupByVersionList(StdEntpLogin t) throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForList("selectStdEntpLoginGroupByVersionList", t);
	}

	/**
	 * @author Ren,Zhong
	 * @version 2011-7-6
	 */
	@SuppressWarnings("unchecked")
	public List<StdEntpLogin> selectEveryDayLoginShopCountForResultList(StdEntpLogin t) throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForList("selectEveryDayLoginShopCountForResultList", t);
	}

	/**
	 * @author Wu,ShangLong
	 * @version 2011-07-06
	 */
	@SuppressWarnings("unchecked")
	public List<StdEntpLogin> selectStdEntpLoginNumPerDayList(StdEntpLogin t) throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForList("selectStdEntpLoginNumPerDayList", t);
	}
}
