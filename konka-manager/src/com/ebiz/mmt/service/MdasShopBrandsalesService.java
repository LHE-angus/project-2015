package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.MdasShopBrandsales;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2011-05-18 16:05:09
 */
public interface MdasShopBrandsalesService {

	Long createMdasShopBrandsales(MdasShopBrandsales t);

	int modifyMdasShopBrandsales(MdasShopBrandsales t);

	int removeMdasShopBrandsales(MdasShopBrandsales t);

	MdasShopBrandsales getMdasShopBrandsales(MdasShopBrandsales t);

	List<MdasShopBrandsales> getMdasShopBrandsalesList(MdasShopBrandsales t);

	Long getMdasShopBrandsalesCount(MdasShopBrandsales t);

	List<MdasShopBrandsales> getMdasShopBrandsalesPaginatedList(MdasShopBrandsales t);

	/**
	 * @author Jiang,JiaYong
	 * @version 2011-05-20
	 */
	List<MdasShopBrandsales> getMdasShopBrandsalesForPdTypeAndBrandList(MdasShopBrandsales t);

	/**
	 * @author Jiang,JiaYong
	 * @version 2011-08-16
	 */
	List<MdasShopBrandsales> getMdasShopBrandsalesForSellOrderList(MdasShopBrandsales t);

	/**
	 * @author Jiang,JiaYong
	 * @version 2011-08-19
	 */
	Long getMdasShopBrandsalesForShopRank(MdasShopBrandsales t);

	/**
	 * @author Li,Yuan
	 * @version 2011-09-15
	 */
	Long getMdasShopBrandsalesForXs(MdasShopBrandsales t);
	
	/**
	 * @author Ren,Zhong
	 * @version 2011-09-21
	 */
	List<MdasShopBrandsales> getMdasShopBrandsalesTopResultList(MdasShopBrandsales t);
	
	/**
	 * 作战地图.区域销售统计
	 * @author Cheng,Bing
	 * @version 2011-11-07
	 */
	List<MdasShopBrandsales> getMdasShopBrandsalesForAreaList(MdasShopBrandsales t);

	/**
	 * 作战地图.网点销量排名
	 * @author Cheng,Bing
	 * @version 2011-11-07
	 */
	List<MdasShopBrandsales> getMdasShopBrandsalesShopOrderList(MdasShopBrandsales t);
	
	/**
	  * 买买提数据库中表名中含有"MDAS_SHOP_BRANDSALES"的查询，用于网店经营情况查询
	  * @author Chen,Shunhua
	  * @version 2012-02-15
	  */
	 List<String> selectMdasShopNamesForQuery(MdasShopBrandsales t);
}