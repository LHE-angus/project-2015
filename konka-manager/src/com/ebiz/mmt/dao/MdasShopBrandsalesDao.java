package com.ebiz.mmt.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ebiz.mmt.domain.MdasShopBrandsales;
import com.ebiz.ssi.dao.EntityDao;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2011-05-18 16:05:09
 */
public interface MdasShopBrandsalesDao extends EntityDao<MdasShopBrandsales> {
	/**
	 * @author Jiang,JiaYong
	 * @version 2011-05-20
	 */
	public List<MdasShopBrandsales> selectMdasShopBrandsalesForPdTypeAndBrandList(MdasShopBrandsales t)
			throws DataAccessException;

	/**
	 * @author Jiang,JiaYong
	 * @version 2011-08-16
	 */
	public List<MdasShopBrandsales> selectMdasShopBrandsalesForSellOrderList(MdasShopBrandsales t)
			throws DataAccessException;

	/**
	 * @author Jiang,JiaYong
	 * @version 2011-08-19
	 */
	public Long selectMdasShopBrandsalesForShopRank(MdasShopBrandsales t) throws DataAccessException;

	/**
	 * @author Li,Yuan
	 * @version 2011-09-15
	 */
	public Long selectMdasShopBrandsalesForXs(MdasShopBrandsales t);
	
	/**
	 * @author Ren,Zhong
	 * @version 2011-09-21
	 */
	public List<MdasShopBrandsales> selectMdasShopBrandsalesTopResultList(MdasShopBrandsales t) throws DataAccessException;
	
	 /**
	  * 作战地图.区域销售统计
	  * @author Cheng,Bing
	  * @version 2011-11-07
	  */
	 public List<MdasShopBrandsales> selectMdasShopBrandsalesForAreaList(MdasShopBrandsales t)
	   throws DataAccessException;
	 /**
	  * 作战地图.网点销量排名
	  * @author Cheng,Bing
	  * @version 2011-11-07
	  */
	 public List<MdasShopBrandsales> selectMdasShopBrandsalesForShopOrderList(MdasShopBrandsales t)
	   throws DataAccessException;
	 
	 /**
	  * 买买提数据库中表名中含有"MDAS_SHOP_BRANDSALES"的查询，用于网店经营情况查询
	  * @author Chen,Shunhua
	  * @version 2012-02-15
	  */
	 public List<String> selectMdasShopNamesForQuery(MdasShopBrandsales t)
	   throws DataAccessException ;
}
