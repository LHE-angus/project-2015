package com.ebiz.mmt.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ebiz.mmt.domain.StdEntpMain;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2010-05-28 16:48:39
 */
public interface StdEntpMainService {

	Long createStdEntpMain(StdEntpMain t);

	int modifyStdEntpMain(StdEntpMain t);

	int removeStdEntpMain(StdEntpMain t);

	StdEntpMain getStdEntpMain(StdEntpMain t);

	List<StdEntpMain> getStdEntpMainList(StdEntpMain t);

	Long getStdEntpMainCount(StdEntpMain t);

	List<StdEntpMain> getStdEntpMainPaginatedList(StdEntpMain t);

	/**
	 * @author Jiang,JiaYong
	 * @version 2010-07-07
	 */
	List<StdEntpMain> getStdEntpMainListForStatistics(StdEntpMain t);

	/**
	 * @author Jiang,JiaYong
	 * @version 2010-07-07
	 */
	Long getStdEntpMainPublishCountForStatistics(StdEntpMain t);

	/**
	 * @author Jiang,JiaYong
	 * @version 2010-07-27
	 */
	Long getStdEntpMainListForStatisticsCount(StdEntpMain t);

	/**
	 * @author Chen,LiLin
	 * @version 2010-08-08
	 */
	int modifyStdEntpMainForBindStdEntpMain(StdEntpMain t);

	/**
	 * @author Chen,LiLin
	 * @version 2010-08-08
	 */
	int removeAllEntpForMmst(StdEntpMain t);

	/**
	 * @author Zhang,DaPeng
	 * @version 2010-09-21
	 */
	// Long queryShopIdByEntpId(StdEntpMain mai);

	/**
	 * @author Du,HongGang
	 * @version 2010-12-08
	 */
	public List<StdEntpMain> getStdEntpMainListForPay(StdEntpMain t) throws DataAccessException;
	
	/**
	 * @author Li,Ka
	 * @version 2012-3-16
	 * @desc  有密钥登录时查询登录网点企业信息，需要合并买卖提用户和官网用户的所有数据
	 */
	public List<StdEntpMain> getStdEntpMainOrEntpShopList(StdEntpMain t) throws DataAccessException;
}