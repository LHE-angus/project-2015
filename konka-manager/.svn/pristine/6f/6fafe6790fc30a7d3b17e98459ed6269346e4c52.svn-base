package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.ebiz.mmt.dao.CategoryDao;
import com.ebiz.mmt.domain.Category;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * @author Xu,ZhengYang
 * @date 2010-11-14 10:33:45
 */
@Repository
public class CategoryDaoSqlMapImpl extends EntityDaoSqlMapImpl<Category> implements CategoryDao {

	/**
	 * @author Hui,Gang
	 * @version Build 2010-12-13
	 */
	@SuppressWarnings("unchecked")
	public List<Category> selectCategoryListForFiles(Category category) throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForList("selectCategoryListForFiles", category);
	}

}
