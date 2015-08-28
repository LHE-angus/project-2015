package com.ebiz.mmt.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ebiz.mmt.domain.StdEntpMain;
import com.ebiz.ssi.dao.EntityDao;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2010-05-28 16:48:39
 */
public interface StdEntpMainDao extends EntityDao<StdEntpMain> {

	/**
	 * @author Jiang,JiaYong
	 * @version 2010-07-07
	 */
	public List<StdEntpMain> selectStdEntpMainListForStatistics(StdEntpMain t) throws DataAccessException;

	/**
	 * @author Jiang,JiaYong
	 * @version 2010-07-07
	 */
	public Long selectStdEntpMainPublishCountForStatistics(StdEntpMain t) throws DataAccessException;

	/**
	 * @author Jiang,JiaYong
	 * @version 2010-07-27
	 */
	public Long selectStdEntpMainListForStatisticsCount(StdEntpMain t) throws DataAccessException;

	/**
	 * @author Chen,LiLin
	 * @version 2010-08-17
	 */
	public int deleteStdEntpMainAndUserForMmst(StdEntpMain t) throws DataAccessException;

	/**
	 * @author Zhang,DaPeng
	 * @version 2010-09-21
	 */
	// public Long queryShopIdByEntpId(StdEntpMain t) throws DataAccessException;

	/**
	 * @author Du,HongGang
	 * @version 2010-12-08
	 */
	public List<StdEntpMain> selectStdEntpMainListForPay(StdEntpMain t) throws DataAccessException;
	
	/**
	 * @author Li,Ka
	 * @version 2012-3-16
	 * @desc  有密钥登录时查询登录网点企业信息，需要合并买卖提用户和官网用户的所有数据
	 */
	public List<StdEntpMain> selectStdEntpMainOrEntpShopList(StdEntpMain t) throws DataAccessException;
}
