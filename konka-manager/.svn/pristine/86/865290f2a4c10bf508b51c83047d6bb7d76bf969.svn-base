package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.StdEntpMainDao;
import com.ebiz.mmt.domain.StdEntpMain;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2010-05-28 16:48:39
 */
@Service
public class StdEntpMainDaoSqlMapImpl extends EntityDaoSqlMapImpl<StdEntpMain> implements StdEntpMainDao {

	/**
	 * @author Jiang,JiaYong
	 * @version 2010-07-07
	 */
	@SuppressWarnings("unchecked")
	public List<StdEntpMain> selectStdEntpMainListForStatistics(StdEntpMain t) throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForList("selectStdEntpMainListForStatistics", t);
	}

	/**
	 * @author Jiang,JiaYong
	 * @version 2010-07-07
	 */
	public Long selectStdEntpMainPublishCountForStatistics(StdEntpMain t) throws DataAccessException {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectStdEntpMainPublishCountForStatistics", t);
	}

	/**
	 * @author Jiang,JiaYong
	 * @version 2010-07-27
	 */
	public Long selectStdEntpMainListForStatisticsCount(StdEntpMain t) throws DataAccessException {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectStdEntpMainListForStatisticsCount", t);
	}

	/**
	 * @author Chen,LiLin
	 * @version 2010-08-17
	 */
	public int deleteStdEntpMainAndUserForMmst(StdEntpMain t) throws DataAccessException {
		return super.getSqlMapClientTemplate().delete("deleteStdEntpMainForMmst", t);
	}

	/**
	 * @author Zhang,DaPeng
	 * @version 2010-09-21
	 */

	// public Long queryShopIdByEntpId(StdEntpMain t) throws DataAccessException {
	// return (Long)super.getSqlMapClientTemplate().queryForObject("selectStdMainID",t);
	// }

	/**
	 * @author Du,HongGang
	 * @version 2010-12-08
	 */
	@SuppressWarnings("unchecked")
	public List<StdEntpMain> selectStdEntpMainListForPay(StdEntpMain t) throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForList("selectStdEntpMainListForPay", t);
	}

	/**
	 * @author Li,Ka
	 * @version 2012-3-16
	 * @desc  有密钥登录时查询登录网点企业信息，需要合并买卖提用户和官网用户的所有数据
	 */
	@SuppressWarnings("unchecked")
	public List<StdEntpMain> selectStdEntpMainOrEntpShopList(StdEntpMain t) throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForList("selectStdEntpMainOrEntpShopList", t);
	}
}
