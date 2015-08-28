package com.ebiz.mmt.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ebiz.mmt.domain.KonkaMobileCategory;
import com.ebiz.ssi.dao.EntityDao;

/**
 * @author Xu,ZhengYang
 * @date 2010-11-14 10:33:45
 */
public interface KonkaMobileCategoryDao extends EntityDao<KonkaMobileCategory> {

	/**
	 * @author Hui,Gang
	 * @version Build 2010-12-13
	 */

	List<KonkaMobileCategory> selectKonkaMobileCategoryListForFiles(
			KonkaMobileCategory konkaMobileCategory) throws DataAccessException;

	List<KonkaMobileCategory> selectKonkaMobileCategoryListForSelect(
			KonkaMobileCategory t) throws DataAccessException;

	Long insertKonkaMobileCategoryForAddType(KonkaMobileCategory t)
			throws DataAccessException;

	String selectKonkaMobileDept(KonkaMobileCategory t)
			throws DataAccessException;
}
