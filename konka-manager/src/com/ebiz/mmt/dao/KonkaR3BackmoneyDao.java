package com.ebiz.mmt.dao;

import java.util.List;

import com.ebiz.mmt.domain.KonkaR3Backmoney;
import com.ebiz.ssi.dao.EntityDao;

/**
 * Code by AutoGenerated Builder AutoGenerated Builder Version 2.1
 * 
 * @author Hui,Gang
 * @datetime 2011-11-10 15:33:16
 */
public interface KonkaR3BackmoneyDao extends EntityDao<KonkaR3Backmoney> {

	/**
	 * @author Hu,hao
	 * @version 2013-07-05
	 */
	List<KonkaR3Backmoney> selectKonkaR3BackmoneyListForTb(KonkaR3Backmoney t);

	/**
	 * @author Hu,hao
	 * @version 2013-09-13
	 */
	List<KonkaR3Backmoney> selectKonkaR3BackmoneyForMonth(KonkaR3Backmoney t);

	/**
	 * @author Hu,hao
	 * @version 2013-09-17
	 */
	List<KonkaR3Backmoney> selectKonkaR3BackmoneyListForHkJd(KonkaR3Backmoney t);

	/**
	 * @author Hu,hao
	 * @version 2013-10-11
	 * @desc 根据用户的数据权限查询R3回款信息
	 */
	Long selectKonkaR3BackmoneyForRoleDataCount(KonkaR3Backmoney t);

	/**
	 * @author Hu,hao
	 * @version 2013-10-11
	 * @desc 根据用户的数据权限查询R3回款信息
	 */
	List<KonkaR3Backmoney> selectKonkaR3BackmoneyForRoleDataPaginatedList(KonkaR3Backmoney t);

	/**
	 * @author Hu,hao
	 * @version 2013-11-06
	 * @desc 回款数据
	 */
	List<KonkaR3Backmoney> selectKonkaR3BackmoneyFoHkDataPaginatedList(KonkaR3Backmoney t);

	/**
	 * @author Hu,hao
	 * @version 2013-11-06
	 * @desc 零售数据
	 */
	List<KonkaR3Backmoney> selectKonkaR3BackmoneyFoSailDataPaginatedList(KonkaR3Backmoney t);

	/**
	 * @author Hu,hao
	 * @version 2013-11-06
	 * @desc 结算数据
	 */
	List<KonkaR3Backmoney> selectKonkaR3BackmoneyFoJsDataPaginatedList(KonkaR3Backmoney t);

	/**
	 * @author Hu,hao
	 * @version 2013-11-06
	 * @desc 结算数据
	 */
	List<KonkaR3Backmoney> selectKonkaR3BackmoneyFoRwDataPaginatedList(KonkaR3Backmoney t);
	
	/**
	 * @author Hu,hao
	 * @version 2013-12-05
	 * @desc 首页展示数据
	 */
	List<KonkaR3Backmoney> selectKonkaR3SailAndJsDataToIndexPaginatedList(KonkaR3Backmoney t);
	
}