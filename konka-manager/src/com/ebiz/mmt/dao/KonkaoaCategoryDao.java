package com.ebiz.mmt.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ebiz.mmt.domain.KonkaoaCategory;
import com.ebiz.ssi.dao.EntityDao;

/**
 * @author Xu,ZhengYang
 * @date 2010-11-14 10:33:45
 */
public interface KonkaoaCategoryDao extends EntityDao<KonkaoaCategory> {

	/**
	 * @author Hui,Gang
	 * @version Build 2010-12-13
	 */
	List<KonkaoaCategory> selectKonkaoaCategoryListForFiles(KonkaoaCategory category) throws DataAccessException;

}
