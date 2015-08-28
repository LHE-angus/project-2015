package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.ebiz.mmt.dao.KonkaMobileCategoryDao;
import com.ebiz.mmt.domain.KonkaMobileCategory;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * @author Xu,ZhengYang
 * @date 2010-11-14 10:33:45
 */
@Repository
public class KonkaMobileCategoryDaoSqlMapImpl extends
		EntityDaoSqlMapImpl<KonkaMobileCategory> implements
		KonkaMobileCategoryDao {

	/**
	 * @author Hui,Gang
	 * @version Build 2010-12-13
	 */
	@SuppressWarnings("unchecked")
	public List<KonkaMobileCategory> selectKonkaMobileCategoryListForFiles(
			KonkaMobileCategory konkaMobileCategory) throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForList(
				"selectKonkaMobileCategoryListForFiles", konkaMobileCategory);
	}

	@SuppressWarnings("unchecked")
	public List<KonkaMobileCategory> selectKonkaMobileCategoryListForSelect(
			KonkaMobileCategory konkaMobileCategory) throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForList(
				"selectKonkaMobileCategoryListForSelect", konkaMobileCategory);
	}

	public Long insertKonkaMobileCategoryForAddType(KonkaMobileCategory t)
			throws DataAccessException {
		return (Long) super.getSqlMapClientTemplate().insert(
				"insertKonkaMobileCategoryForAddType", t);
	}

	public String selectKonkaMobileDept(KonkaMobileCategory t)
			throws DataAccessException {
		return (String) super.getSqlMapClientTemplate().queryForObject(
				"selectKonkaMobileDept", t);
	}

}
