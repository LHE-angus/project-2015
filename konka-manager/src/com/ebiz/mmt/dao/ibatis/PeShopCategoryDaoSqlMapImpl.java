package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.PeShopCategoryDao;
import com.ebiz.mmt.domain.PeShopCategory;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2011-05-11 15:59:43
 */
@Service
public class PeShopCategoryDaoSqlMapImpl extends EntityDaoSqlMapImpl<PeShopCategory> implements PeShopCategoryDao {

	/**
	 * @author Li,Yuan
	 * @version 2011-05-17
	 */
	public Long selectPeShopCategoryWithNameCount(PeShopCategory t) {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectPeShopCategoryWithNameCount", t);
	}

	/**
	 * @author Li,Yuan
	 * @version 2011-05-17
	 */
	@SuppressWarnings("unchecked")
	public List<PeShopCategory> selectPeShopCategoryWithNamePaginatedList(PeShopCategory t) {
		return super.getSqlMapClientTemplate().queryForList("selectPeShopCategoryWithNamePaginatedList", t);
	}

	/**
	 * @author Hui,Gang
	 * @version 2011-10-19
	 */
	@SuppressWarnings("unchecked")
	public List<PeShopCategory> selectPeShopCategoryListWithLevel(PeShopCategory t) {
		return super.getSqlMapClientTemplate().queryForList("selectPeShopCategoryListWithLevel", t);
	}
}
