package com.ebiz.mmt.dao;

import java.util.List;

import com.ebiz.mmt.domain.PeShopCategory;
import com.ebiz.ssi.dao.EntityDao;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2011-05-11 15:59:43
 */
public interface PeShopCategoryDao extends EntityDao<PeShopCategory> {

	/**
	 * @author Li,Yuan
	 * @version 2011-05-17
	 */
	Long selectPeShopCategoryWithNameCount(PeShopCategory t);

	/**
	 * @author Li,Yuan
	 * @version 2011-05-17
	 */
	List<PeShopCategory> selectPeShopCategoryWithNamePaginatedList(PeShopCategory t);
	
	/**
	 * @author Hui,Gang
	 * @version 2011-10-19
	 */
	List<PeShopCategory> selectPeShopCategoryListWithLevel(PeShopCategory t);
}
