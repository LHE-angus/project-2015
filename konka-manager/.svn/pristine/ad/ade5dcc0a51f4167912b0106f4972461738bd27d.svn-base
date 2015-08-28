package com.ebiz.mmt.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ebiz.mmt.domain.BaseProvinceList;
import com.ebiz.ssi.dao.EntityDao;

/**
 * @author Hui,Gang
 */
public interface BaseProvinceListDao extends EntityDao<BaseProvinceList> {

	/**
	 * @author Xing,XiuDong
	 */
	public List<BaseProvinceList> selectBaseProvinceListParentList(BaseProvinceList t) throws DataAccessException;


	/**
	 * @author Chen,LiLin
	 */
	public List<BaseProvinceList> selectBaseProvinceListChildrenList(BaseProvinceList t) throws DataAccessException;
	
	/**
	 * @author Li,Ka
	 * @version 2011-11-28
	 * 根据p_index查找上级P_index
	 */
	public List<BaseProvinceList> selectBaseProvinceListAllParPindexByPindex(BaseProvinceList t) throws DataAccessException;
	
}
