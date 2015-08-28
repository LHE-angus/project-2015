package com.ebiz.mmt.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ebiz.mmt.domain.KonkaBranchCategory;
import com.ebiz.ssi.dao.EntityDao;

public interface KonkaBranchCategoryDao extends EntityDao<KonkaBranchCategory> {

	/**
	 * @author Hui,Gang
	 * @version 2011-10-21 上午10:22:47
	 */
	List<KonkaBranchCategory> selectKonkaBranchCategoryListForMsgAndArticle(KonkaBranchCategory t) throws DataAccessException;
	
	/**
	 * @author Zhang,Xufeng
	 * @version 2011-10-21 
	 */
	List<KonkaBranchCategory> selectKonkaBranchCategoryWithCustomerNamesList(KonkaBranchCategory t) throws DataAccessException;

}