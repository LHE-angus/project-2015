package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.ebiz.mmt.dao.PdModelDao;
import com.ebiz.mmt.domain.PdModel;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * @author Liu,Huan
 */
@Repository
public class PdModelDaoSqlMapImpl extends EntityDaoSqlMapImpl<PdModel> implements PdModelDao {

	/**
	 * @author Chen,Lide
	 */
	public Long insertPdModelWithoutSequence(PdModel t) throws DataAccessException {
		return (Long) super.getSqlMapClientTemplate().insert("insertPdModelWithoutSequence", t);
	}

	/**
	 * @author Chen,Lilin
	 */
	@SuppressWarnings("unchecked")
	public List<PdModel> selectPdModelListWithShopPd(PdModel t) throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForList("selectPdModelListWithShopPd", t);
	}

	public PdModel selectPdModelForShopPdContrast(PdModel t) throws DataAccessException {
		return (PdModel) super.getSqlMapClientTemplate().queryForObject("selectPdModelForShopPdContrast", t);
	}

	public Long selectPdModelCountForShopPdContrast(PdModel t) throws DataAccessException {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectPdModelCountForShopPdContrast", t);
	}

	@SuppressWarnings("unchecked")
	public List<PdModel> selectPdModelPaginatedListForShopPdContrast(PdModel t) throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForList("selectPdModelPaginatedListForShopPdContrast", t);
	}

	@SuppressWarnings("unchecked")
	public List<PdModel> selectPdModelWithPdTypeSignList(PdModel t) throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForList("selectPdModelWithPdTypeSignList", t);
	}

	@SuppressWarnings("unchecked")
	public List<PdModel> selectPdModelPaginatedListForSearch(PdModel t) throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForList("selectPdModelPaginatedListForSearch", t);
	}

	public Long selectPdModelCountForSearch(PdModel t) throws DataAccessException {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectPdModelCountForSearch", t);
	}

	/**
	 * @author Jiang,JiaYong
	 */
	@SuppressWarnings("unchecked")
	public List<PdModel> selectPdModelListForLoadImgFromJdxx(PdModel t) throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForList("selectPdModelListForLoadImgFromJdxx", t);
	}

	/**
	 * @author Wu,ShangLong
	 */
	public Long selectBrandCountInPdMode(PdModel t) throws DataAccessException {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectBrandCountInPdModel", t);
	}

	/**
	 * @author Du,HongGang
	 * @version 2010-11-17
	 */
	@SuppressWarnings("unchecked")
	public List<PdModel> selectPdModelListForPop(PdModel t) throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForList("selectPdModelListForPop", t);
	}

	/**
	 * @author Wu,ShangLong
	 * @version 2010-11-23
	 */
	@SuppressWarnings("unchecked")
	public List<PdModel> selectPdModelListForHot(PdModel t) throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForList("selectPdModelListForHot", t);
	}

	/**
	 * @author Li,Yuan
	 * @version 2011-02-25
	 */
	@SuppressWarnings("unchecked")
	public List<PdModel> selectPdModelListForProd(PdModel t) throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForList("selectPdModelListForProd", t);
	}

	/**
	 * @author Li,Yuan
	 * @version 2011-03-25
	 */
	public Long selectPdModelListForProdCount(PdModel t) throws DataAccessException {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectPdModelListForProdCount", t);
	}

	/**
	 * @author Li,Yuan
	 * @version 2011-02-25
	 */
	public Long selectPdModelListCountProd(PdModel t) throws DataAccessException {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectPdModelListCountProd", t);
	}

	@SuppressWarnings("unchecked")
	public List<PdModel> selectPdModelListForJxcPd(PdModel t) throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForList("selectPdModelListForJxcPd", t);
	}

	@SuppressWarnings("unchecked")
	public List<PdModel> selectPdModelPaginatedListForJxcPd(PdModel t) throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForList("selectPdModelPaginatedListForJxcPd", t);
	}

	public Long selectPdModelCountForJxcPd(PdModel t) throws DataAccessException {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectPdModelCountForJxcPd", t);
	}
	
	/**
	 * @author Kun,Zheng
	 * @version 2011-06-14
	 */
	public Long selectPdModelCountForSearchAppend(PdModel t)
			throws DataAccessException {
	
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectPdModelCountForSearchAppend",t);
	}

	/**
	 * @author Kun,Zheng
	 * @version 2011-06-14
	 */
	@SuppressWarnings("unchecked")
	public List<PdModel> selectPdModelPaginatedListForSearchAppend(PdModel t)
			throws DataAccessException {
	
		return  super.getSqlMapClientTemplate().queryForList("selectPdModelPaginatedListForSearchAppend", t);
	}

}
