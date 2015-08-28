package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.MdasShopBrandsalesDao;
import com.ebiz.mmt.domain.MdasShopBrandsales;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2011-05-18 16:05:09
 */
@Service
public class MdasShopBrandsalesDaoSqlMapImpl extends EntityDaoSqlMapImpl<MdasShopBrandsales> implements
		MdasShopBrandsalesDao {
	/**
	 * @author Jiang,JiaYong
	 * @version 2011-05-20
	 */
	@SuppressWarnings("unchecked")
	public List<MdasShopBrandsales> selectMdasShopBrandsalesForPdTypeAndBrandList(MdasShopBrandsales t)
			throws DataAccessException {
		return this.getSqlMapClientTemplate().queryForList("selectMdasShopBrandsalesForPdTypeAndBrandList", t);
	}

	/**
	 * @author Jiang,JiaYong
	 * @version 2011-08-16
	 */
	@SuppressWarnings("unchecked")
	public List<MdasShopBrandsales> selectMdasShopBrandsalesForSellOrderList(MdasShopBrandsales t)
			throws DataAccessException {
		return this.getSqlMapClientTemplate().queryForList("selectMdasShopBrandsalesForSellOrderList", t);
	}

	/**
	 * @author Jiang,JiaYong
	 * @version 2011-08-19
	 */
	public Long selectMdasShopBrandsalesForShopRank(MdasShopBrandsales t) throws DataAccessException {
		return (Long) this.getSqlMapClientTemplate().queryForObject("selectMdasShopBrandsalesForShopRank", t);
	}

	/**
	 * @author Li,Yuan
	 * @version 2011-09-15
	 */
	public Long selectMdasShopBrandsalesForXs(MdasShopBrandsales t) {
		return (Long) this.getSqlMapClientTemplate().queryForObject("selectMdasShopBrandsalesForXs", t);
	}

	/**
	 * @author Ren,Zhong
	 * @version 2011-09-21
	 */
	@SuppressWarnings("unchecked")
	public List<MdasShopBrandsales> selectMdasShopBrandsalesTopResultList(MdasShopBrandsales t)
			throws DataAccessException {
		return this.getSqlMapClientTemplate().queryForList("selectMdasShopBrandsalesTopResultList", t);
	}
	/**
	  * 作战地图.区域销售统计
	  * @author Cheng,Bing
	  * @version 2011-11-07
	  */
	 @SuppressWarnings("unchecked")
	 public List<MdasShopBrandsales> selectMdasShopBrandsalesForAreaList(MdasShopBrandsales t)
	   throws DataAccessException {
	  return this.getSqlMapClientTemplate().queryForList("selectMdasShopBrandsalesForAreaList", t);
	 }
	 
	 /**
	  * 作战地图.网点销量排名
	  * @author Cheng,Bing
	  * @version 2011-11-07
	  */
	 @SuppressWarnings("unchecked")
	 public List<MdasShopBrandsales> selectMdasShopBrandsalesForShopOrderList(MdasShopBrandsales t)
	   throws DataAccessException {
	  return this.getSqlMapClientTemplate().queryForList("selectMdasShopBrandsalesForShopOrderList", t);
	 }
	 
	 /**
	  * 买买提数据库中表名中含有"MDAS_SHOP_BRANDSALES"的查询，用于网店经营情况查询
	  * @author Chen,Shunhua
	  * @version 2012-02-15
	  */
	 @SuppressWarnings("unchecked")
	public List<String> selectMdasShopNamesForQuery(MdasShopBrandsales t)
	   throws DataAccessException {
		 return (List<String>) this.getSqlMapClientTemplate().queryForList("selectMdasShopNamesForQuery",t);
	 }
}
