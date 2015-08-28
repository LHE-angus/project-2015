package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaXxZmdRewardSetHdDao;
import com.ebiz.mmt.domain.KonkaXxZmdRewardSetHd;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * @author Xu,XiaoYuan
 * @version 2012-03-02 09:12
 */
@Service
public class KonkaXxZmdRewardSetHdDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaXxZmdRewardSetHd> implements
		KonkaXxZmdRewardSetHdDao {

	/**
	 * @author Wu,ShangLong
	 * @version 2012-03-05
	 */
	public Long selectKonkaXxZmdRewardSetHdWithZmdAndHdCount(KonkaXxZmdRewardSetHd t) throws DataAccessException {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectKonkaXxZmdRewardSetHdWithZmdAndHdCount", t);
	}

	/**
	 * @author Wu,ShangLong
	 * @version 2012-03-05
	 */
	@SuppressWarnings("unchecked")
	public List<KonkaXxZmdRewardSetHd> selectKonkaXxZmdRewardSetHdWithZmdAndHdPaginatedList(KonkaXxZmdRewardSetHd t)
			throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForList("selectKonkaXxZmdRewardSetHdWithZmdAndHdPaginatedList", t);
	}
}
