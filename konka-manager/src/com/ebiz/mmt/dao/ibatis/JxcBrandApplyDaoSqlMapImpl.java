package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.JxcBrandApplyDao;
import com.ebiz.mmt.domain.JxcBrandApply;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2011-03-03 09:42:37
 */
@Service
public class JxcBrandApplyDaoSqlMapImpl extends EntityDaoSqlMapImpl<JxcBrandApply> implements JxcBrandApplyDao {

	/**
	 * @author Xing,XiuDong
	 * @date 2011-05-17
	 */
	@SuppressWarnings("unchecked")
	public List<JxcBrandApply> selectJxcBrandApplyXPaginatedList(JxcBrandApply t) {
		return super.getSqlMapClientTemplate().queryForList("selectJxcBrandApplyXPaginatedList", t);
	}

	/**
	 * @author Xing,XiuDong
	 * @date 2011-05-17
	 */
	public Long selectJxcBrandApplyXCount(JxcBrandApply t) {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectJxcBrandApplyXCount", t);
	}

	/**
	 * @author Zhang,ShiMing
	 */
	@SuppressWarnings("unchecked")
	public List<JxcBrandApply> selectEntityListWithShopName(JxcBrandApply t) {
		return super.getSqlMapClientTemplate().queryForList("selectJxcBrandApplyPaginatedListWithShopName", t);
	}

	/**
	 * @author Zhang,ShiMing
	 */
	public Long selectEntityWithShopNameCount(JxcBrandApply t) {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectJxcBrandApplyWithShopNameCount", t);
	}

}
