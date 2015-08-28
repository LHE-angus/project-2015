package com.ebiz.mmt.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ebiz.mmt.domain.Category;
import com.ebiz.ssi.dao.EntityDao;

/**
 * @author Xu,ZhengYang
 * @date 2010-11-14 10:33:45
 */
public interface CategoryDao extends EntityDao<Category> {

	/**
	 * @author Hui,Gang
	 * @version Build 2010-12-13
	 */
	List<Category> selectCategoryListForFiles(Category category) throws DataAccessException;

}
