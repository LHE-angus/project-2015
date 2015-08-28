package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.BaseProvinceListFourDao;
import com.ebiz.mmt.domain.BaseProvinceListFour;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2011-05-03 17:04:13
 */
@Service
public class BaseProvinceListFourDaoSqlMapImpl extends EntityDaoSqlMapImpl<BaseProvinceListFour> implements
        BaseProvinceListFourDao {

	/**
	 * @author Chen,LiLin
	 * @date 2011-05-16
	 */
	@SuppressWarnings("unchecked")
	public List<BaseProvinceListFour> selectBaseProvinceListFourParentList(BaseProvinceListFour t)
	        throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForList("selectBaseProvinceListFourParentList", t);
	}

	/**
	 * @author Jiang,JiaYong
	 * @date 2013-09-10
	 */
	public BaseProvinceListFour selectBaseProvinceListFourParent(BaseProvinceListFour t) throws DataAccessException {
		return (BaseProvinceListFour) super.getSqlMapClientTemplate().queryForObject(
		        "selectBaseProvinceListFourParent", t);
	}

	/**
	 * @author Cheng,Bing
	 * @date 2011-11-7
	 */
	@SuppressWarnings("unchecked")
	public List<BaseProvinceListFour> selectBaseProvinceListFourSonTreeList(BaseProvinceListFour t)
	        throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForList("selectBaseProvinceListFourSonTreeList", t);
	}

	@SuppressWarnings("unchecked")
	public List<BaseProvinceListFour> selectBaseProvinceListFourForProvinceInList(BaseProvinceListFour t)
	        throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForList("selectBaseProvinceListFourForProvinceInList", t);
	}

	@SuppressWarnings("unchecked")
	public List<BaseProvinceListFour> selectBaseProvinceListFourForProvinceNotInList(BaseProvinceListFour t)
	        throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForList("selectBaseProvinceListFourForProvinceNotInList", t);
	}

	@SuppressWarnings("unchecked")
	public List<BaseProvinceListFour> selectBaseProvinceListFourForProvinceList(BaseProvinceListFour t)
	        throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForList("selectBaseProvinceListFourForProvinceList", t);
	}

	@SuppressWarnings("unchecked")
	public List<BaseProvinceListFour> selectBaseProvinceListFourForCityList(BaseProvinceListFour t)
	        throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForList("selectBaseProvinceListFourForCityList", t);
	}

	@Override
	public List<BaseProvinceListFour> selectBaseProvinceListFourByCityNameList(BaseProvinceListFour t)
	        throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForList("selectBaseProvinceListFourByCityNameList", t);
	}

}
