package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.BasePropCategoryDao;
import com.ebiz.mmt.domain.BasePropCategory;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Chen,ShunHua
 * @date 2011-09-22 09:45:12
 */
@Service
public class BasePropCategoryDaoSqlMapImpl extends EntityDaoSqlMapImpl<BasePropCategory> implements BasePropCategoryDao {

	/**
	 * @author Chen,ShunHua
	 * @version 2011.09.22
	 */
	@SuppressWarnings("unchecked")
	public List<BasePropCategory> selectBasePropCategoryByNameList(BasePropCategory t) {
		return super.getSqlMapClientTemplate().queryForList("selectBasePropCategoryByNameList", t);
	}
}
