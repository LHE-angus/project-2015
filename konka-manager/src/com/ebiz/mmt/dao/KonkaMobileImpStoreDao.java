package com.ebiz.mmt.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ebiz.mmt.domain.KonkaMobileImpStore;
import com.ebiz.ssi.dao.EntityDao;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-05-23 15:45:29
 */
public interface KonkaMobileImpStoreDao extends EntityDao<KonkaMobileImpStore> {

	/**
	 * @author Xing,XiuDong
	 * @version 2011-10-21 
	 */
	List<KonkaMobileImpStore> selectKonkaMobileImpStoreListForDistinctSdf(KonkaMobileImpStore t) throws DataAccessException;

}
