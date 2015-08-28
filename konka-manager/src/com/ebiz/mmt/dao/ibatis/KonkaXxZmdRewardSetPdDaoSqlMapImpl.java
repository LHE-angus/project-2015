package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaXxZmdRewardSetPdDao;
import com.ebiz.mmt.domain.KonkaXxZmdRewardSetPd;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * @author Xu,XiaoYuan
 * @version 2012-03-02 09:12
 */
@Service
public class KonkaXxZmdRewardSetPdDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaXxZmdRewardSetPd> implements
		KonkaXxZmdRewardSetPdDao {

	/**
	 * @author Wu,ShangLong
	 * @version 2012-03-02
	 */
	public Long selectKonkaXxZmdRewardSetPdWithZmdSnCount(KonkaXxZmdRewardSetPd t) throws DataAccessException {
		return (Long) this.getSqlMapClientTemplate().queryForObject("selectKonkaXxZmdRewardSetPdWithZmdSnCount", t);
	}

	/**
	 * @author Wu,ShangLong
	 * @version 2012-03-02
	 */
	@SuppressWarnings("unchecked")
	public List<KonkaXxZmdRewardSetPd> selectKonkaXxZmdRewardSetPdWithZmdSnPaginatedList(KonkaXxZmdRewardSetPd t)
			throws DataAccessException {
		return this.getSqlMapClientTemplate().queryForList("selectKonkaXxZmdRewardSetPdWithZmdSnPaginatedList", t);
	}

}
