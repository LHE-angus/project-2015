package com.ebiz.mmt.dao.ibatis;

import org.springframework.stereotype.Repository;

import com.ebiz.mmt.dao.EntpShopBrandDao;
import com.ebiz.mmt.domain.EntpShopBrand;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * @author Xing, XiuDong
 */
@Repository
public class EntpShopBrandDaoSqlMapImpl extends EntityDaoSqlMapImpl<EntpShopBrand> implements EntpShopBrandDao {

	public int deleteEntpShopBrandByEntpShop(EntpShopBrand t) {
		return super.getSqlMapClientTemplate().delete("deleteEntpShopBrandByEntpShop", t);
	}

}
