package com.ebiz.mmt.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ebiz.mmt.domain.PdModel;

/**
 * @author Liu,Huan
 */
public interface PdModelService {

	Long createPdModel(PdModel t);

	int modifyPdModel(PdModel t);

	int removePdModel(PdModel t);

	PdModel getPdModel(PdModel t);

	List<PdModel> getPdModelList(PdModel t);

	Long getPdModelCount(PdModel t);

	List<PdModel> getPdModelPaginatedList(PdModel t);

	/**
	 * @author Chen,Lide
	 */
	Long createPdModelWithoutSequence(PdModel t);

	/**
	 * @author Chen,Lilin
	 */
	List<PdModel> getPdModelListWithShopPd(PdModel t);

	PdModel getPdModelForShopPdContrast(PdModel t);

	Long getPdModelCountForShopPdContrast(PdModel t);

	List<PdModel> getPdModelPaginatedListForShopPdContrast(PdModel t);

	List<PdModel> getPdModelPaginatedListForSearch(PdModel t);

	List<PdModel> getPdModelWithPdTypeSignList(PdModel t);

	Long getPdModelCountForSearch(PdModel t);

	/**
	 * @author Jiang,JiaYong
	 */
	List<PdModel> getPdModelListForLoadImgFromJdxx(PdModel t);

	/**
	 * @author Wu,ShangLong
	 */
	public Long getBrandCountInPdMode(PdModel t);

	/**
	 * @author Du,HongGang
	 * @version 2010-11-17
	 */
	List<PdModel> getPdModelListForPop(PdModel t) throws DataAccessException;

	/**
	 * @author Wu,ShangLong
	 * @version 2010-11-23
	 */
	List<PdModel> getPdModelListForHot(PdModel t) throws DataAccessException;

	/**
	 * @author Li,Yuan
	 * @version 2011-02-25
	 */
	List<PdModel> getPdModelListForProd(PdModel t);

	/**
	 * @author Li,Yuan
	 * @version 2011-03-25
	 */
	Long getPdModelListForProdCount(PdModel t);

	/**
	 * @author Li,Yuan
	 * @version 2011-02-25
	 */
	Long getPdModelListCountProd(PdModel t);

	List<PdModel> getPdModelListForJxcPd(PdModel t);

	/**
	 * @author Ren, Zhong
	 * @version 2011-3-16
	 */
	List<PdModel> getPdModelPaginatedListForJxcPd(PdModel t);

	Long getPdModelCountForJxcPd(PdModel t);
	
	/**
	 * @author Kun,Zheng
	 * @version 2011-06-14
	 */
	List<PdModel> getPdModelPaginatedListForSearchAppend(PdModel t);

	/**
	 * @author Kun,Zheng
	 * @version 2011-06-14
	 */
	Long getPdModelCountForSearchAppend(PdModel t);
	
}
