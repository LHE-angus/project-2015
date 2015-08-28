package com.ebiz.mmt.dao;

import com.ebiz.mmt.domain.EntpShopBrand;
import com.ebiz.ssi.dao.EntityDao;

/**
 * @author Xing, XiuDong
 */
public interface EntpShopBrandDao extends EntityDao<EntpShopBrand> {

	/**
	 * @author Jiang,JiaYong
	 */
	int deleteEntpShopBrandByEntpShop(EntpShopBrand t);
}
