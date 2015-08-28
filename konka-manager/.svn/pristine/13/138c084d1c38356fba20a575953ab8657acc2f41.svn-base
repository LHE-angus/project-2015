package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.ebiz.mmt.dao.KonkaBranchCategoryDao;
import com.ebiz.mmt.domain.KonkaBranchCategory;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

@Repository
public class KonkaBranchCategoryDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaBranchCategory> implements
		KonkaBranchCategoryDao {

	@SuppressWarnings("unchecked")
	public List<KonkaBranchCategory> selectKonkaBranchCategoryListForMsgAndArticle(KonkaBranchCategory t)
			throws DataAccessException {
		return this.getSqlMapClientTemplate().queryForList("selectKonkaBranchCategoryListForMsgAndArticle", t);
	}

	@SuppressWarnings("unchecked")
	public List<KonkaBranchCategory> selectKonkaBranchCategoryWithCustomerNamesList(KonkaBranchCategory t)
			throws DataAccessException {
		return this.getSqlMapClientTemplate().queryForList("selectKonkaBranchCategoryWithCustomerNamesList", t);
	}

}
