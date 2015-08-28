package com.ebiz.mmt.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ebiz.mmt.domain.PdModel;
import com.ebiz.ssi.dao.EntityDao;

/**
 * @author Liu,Huan
 */
public interface PdModelDao extends EntityDao<PdModel> {

	/**
	 * @author Chen,Lide
	 */
	Long insertPdModelWithoutSequence(PdModel t) throws DataAccessException;

	/**
	 * @author Chen,Lilin
	 */
	List<PdModel> selectPdModelListWithShopPd(PdModel t) throws DataAccessException;

	List<PdModel> selectPdModelWithPdTypeSignList(PdModel t) throws DataAccessException;

	PdModel selectPdModelForShopPdContrast(PdModel t) throws DataAccessException;

	Long selectPdModelCountForShopPdContrast(PdModel t) throws DataAccessException;

	List<PdModel> selectPdModelPaginatedListForShopPdContrast(PdModel t) throws DataAccessException;

	Long selectPdModelCountForSearch(PdModel t) throws DataAccessException;

	List<PdModel> selectPdModelPaginatedListForSearch(PdModel t) throws DataAccessException;

	/**
	 * @author Jiang,JiaYong
	 */
	List<PdModel> selectPdModelListForLoadImgFromJdxx(PdModel t) throws DataAccessException;

	/**
	 * @author Wu,ShangLong
	 */
	Long selectBrandCountInPdMode(PdModel t) throws DataAccessException;

	/**
	 * @author Du,HongGang
	 * @version 2010-11-17
	 */
	List<PdModel> selectPdModelListForPop(PdModel t) throws DataAccessException;

	/**
	 * @author Wu,ShangLong
	 * @version 2010-11-23
	 */
	List<PdModel> selectPdModelListForHot(PdModel t) throws DataAccessException;

	/**
	 * @author Li,Yuan
	 * @version 2011-02-25
	 */
	List<PdModel> selectPdModelListForProd(PdModel t) throws DataAccessException;

	/**
	 * @author Li,Yuan
	 * @version 2011-03-25
	 */
	Long selectPdModelListForProdCount(PdModel t) throws DataAccessException;

	/**
	 * @author Li,Yuan
	 * @version 2011-02-25
	 */
	Long selectPdModelListCountProd(PdModel t) throws DataAccessException;

	List<PdModel> selectPdModelListForJxcPd(PdModel t) throws DataAccessException;

	/**
	 * @author Ren,Zhong
	 * @version 2011-3-16
	 */
	List<PdModel> selectPdModelPaginatedListForJxcPd(PdModel t) throws DataAccessException;

	Long selectPdModelCountForJxcPd(PdModel t) throws DataAccessException;

	/**
	 * @author Kun,Zheng
	 * @version 2011-06-14
	 */
	Long selectPdModelCountForSearchAppend(PdModel t) throws DataAccessException;

	List<PdModel> selectPdModelPaginatedListForSearchAppend(PdModel t) throws DataAccessException;

}
