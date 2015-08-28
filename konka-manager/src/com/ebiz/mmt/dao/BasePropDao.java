package com.ebiz.mmt.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ebiz.mmt.domain.BaseProp;
import com.ebiz.ssi.dao.EntityDao;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Chen,ShunHua
 * @date 2011-09-21 10:59:12
 */
public interface BasePropDao extends EntityDao<BaseProp> {

	/**
	 * @author Chen,ShunHua
	 */
	List<BaseProp> selectBasePropWithCateNameList(BaseProp t) throws DataAccessException;

	/**
	 * @author Chen,ShunHua
	 * @version 2011-09-21
	 */
	List<BaseProp> selectBasePropWithTreeNameList(BaseProp t) throws DataAccessException;

}
