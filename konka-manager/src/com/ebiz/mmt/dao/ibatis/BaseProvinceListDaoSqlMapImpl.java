package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.ebiz.mmt.dao.BaseProvinceListDao;
import com.ebiz.mmt.domain.BaseProvinceList;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * @author Hui,Gang
 */
@Repository
public class BaseProvinceListDaoSqlMapImpl extends EntityDaoSqlMapImpl<BaseProvinceList> implements BaseProvinceListDao {

	/**
	 * @author Xing,XiuDong
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<BaseProvinceList> selectBaseProvinceListParentList(BaseProvinceList t) throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForList("selectBaseProvinceListParentList", t);
	}

	/**
	 * @author Chen,LiLin
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<BaseProvinceList> selectBaseProvinceListChildrenList(BaseProvinceList t) throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForList("selectBaseProvinceListChildrenList", t);
	}

	/**
	 * @author Li,Ka
	 * @version 2011-11-28
	 * 根据p_index查找上级P_index
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<BaseProvinceList> selectBaseProvinceListAllParPindexByPindex(BaseProvinceList t) throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForList("selectBaseProvinceListAllParPindexByPindex", t);
	}
}
