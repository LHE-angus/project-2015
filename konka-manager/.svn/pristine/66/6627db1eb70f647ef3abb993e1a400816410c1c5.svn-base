package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.MdasShopSalesDao;
import com.ebiz.mmt.domain.MdasShopSales;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2010-10-12 17:31:02
 */
@Service
public class MdasShopSalesDaoSqlMapImpl extends EntityDaoSqlMapImpl<MdasShopSales> implements MdasShopSalesDao {
	/**
	 * @author Zhang,DaPeng
	 * @version 2010-10-13
	 */
	@SuppressWarnings("unchecked")
	public List<MdasShopSales> selectMdasShopSalesListForGSite(MdasShopSales t) throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForList("selectMdasShopSalesListForGSite", t);
	}
}
