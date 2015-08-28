package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.MdasShopBrandsalesDao;
import com.ebiz.mmt.domain.MdasShopBrandsales;
import com.ebiz.mmt.service.MdasShopBrandsalesService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2011-05-18 16:05:09
 */
@Service
public class MdasShopBrandsalesServiceImpl implements MdasShopBrandsalesService {

	@Resource
	private MdasShopBrandsalesDao mdasShopBrandsalesDao;

	public Long createMdasShopBrandsales(MdasShopBrandsales t) {
		return this.mdasShopBrandsalesDao.insertEntity(t);
	}

	public MdasShopBrandsales getMdasShopBrandsales(MdasShopBrandsales t) {
		return this.mdasShopBrandsalesDao.selectEntity(t);
	}

	public Long getMdasShopBrandsalesCount(MdasShopBrandsales t) {
		return this.mdasShopBrandsalesDao.selectEntityCount(t);
	}

	public List<MdasShopBrandsales> getMdasShopBrandsalesList(MdasShopBrandsales t) {
		return this.mdasShopBrandsalesDao.selectEntityList(t);
	}

	public int modifyMdasShopBrandsales(MdasShopBrandsales t) {
		return this.mdasShopBrandsalesDao.updateEntity(t);
	}

	public int removeMdasShopBrandsales(MdasShopBrandsales t) {
		return this.mdasShopBrandsalesDao.deleteEntity(t);
	}

	public List<MdasShopBrandsales> getMdasShopBrandsalesPaginatedList(MdasShopBrandsales t) {
		return this.mdasShopBrandsalesDao.selectEntityPaginatedList(t);
	}

	/**
	 * @author Jiang,JiaYong
	 * @version 2011-05-20
	 */
	public List<MdasShopBrandsales> getMdasShopBrandsalesForPdTypeAndBrandList(MdasShopBrandsales t) {
		return this.mdasShopBrandsalesDao.selectMdasShopBrandsalesForPdTypeAndBrandList(t);
	}

	/**
	 * @author Jiang,JiaYong
	 * @version 2011-08-16
	 */
	public List<MdasShopBrandsales> getMdasShopBrandsalesForSellOrderList(MdasShopBrandsales t) {
		return this.mdasShopBrandsalesDao.selectMdasShopBrandsalesForSellOrderList(t);
	}

	/**
	 * @author Jiang,JiaYong
	 * @version 2011-08-19
	 */
	public Long getMdasShopBrandsalesForShopRank(MdasShopBrandsales t) {
		return this.mdasShopBrandsalesDao.selectMdasShopBrandsalesForShopRank(t);
	}

	/**
	 * @author Li,Yuan
	 * @version 2011-09-15
	 */
	public Long getMdasShopBrandsalesForXs(MdasShopBrandsales t) {
		return this.mdasShopBrandsalesDao.selectMdasShopBrandsalesForXs(t);
	}

	/**
	 * @author Ren,Zhong
	 * @version 2011-09-21
	 */
	public List<MdasShopBrandsales> getMdasShopBrandsalesTopResultList(MdasShopBrandsales t) {
		return this.mdasShopBrandsalesDao.selectMdasShopBrandsalesTopResultList(t);
	}
	/**
	  * 作战地图.区域销售统计
	  * @author Cheng,Bing
	  * @version 2011-11-07
	  */
	public List<MdasShopBrandsales> getMdasShopBrandsalesForAreaList(MdasShopBrandsales t) {
	    return this.mdasShopBrandsalesDao.selectMdasShopBrandsalesForAreaList(t);
	}
	 
	 /**
	  * 作战地图.网点销量排名
	  * @author Cheng,Bing
	  * @version 2011-11-07
	  */
	public List<MdasShopBrandsales> getMdasShopBrandsalesShopOrderList(MdasShopBrandsales t) {
	    return this.mdasShopBrandsalesDao.selectMdasShopBrandsalesForShopOrderList(t);
	}
	
	/**
	  * 买买提数据库中表名中含有"MDAS_SHOP_BRANDSALES"的查询，用于网店经营情况查询
	  * @author Chen,Shunhua
	  * @version 2012-02-15
	  */
	 public List<String> selectMdasShopNamesForQuery(MdasShopBrandsales t){
		 return this.mdasShopBrandsalesDao.selectMdasShopNamesForQuery(t);
	 }
}
