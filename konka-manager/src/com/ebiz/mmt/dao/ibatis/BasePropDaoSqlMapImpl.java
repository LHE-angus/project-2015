package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.BasePropDao;
import com.ebiz.mmt.domain.BaseProp;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Chen,ShunHua
 * @date 2011-09-22 09:45:12
 */
@Service
public class BasePropDaoSqlMapImpl extends EntityDaoSqlMapImpl<BaseProp> implements BasePropDao {

	/**
	 * @author Wu,Chen,ShunHua
	 */
	@SuppressWarnings("unchecked")
	public List<BaseProp> selectBasePropWithCateNameList(BaseProp t) throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForList("selectBasePropWithCateNameList", t);
	}

	/**
	 * @author Chen,ShunHua
	 * @version 2011-09-22
	 */
	@SuppressWarnings("unchecked")
	public List<BaseProp> selectBasePropWithTreeNameList(BaseProp t) throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForList("selectBasePropWithTreeNameList", t);
	}
}
