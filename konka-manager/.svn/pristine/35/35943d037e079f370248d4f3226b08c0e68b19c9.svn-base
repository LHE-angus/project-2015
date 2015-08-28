package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.ebiz.mmt.dao.PdPropertyDao;
import com.ebiz.mmt.domain.PdProperty;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * @author Jin,QingHua
 */
@Repository
public class PdPropertyDaoSqlMapImpl extends EntityDaoSqlMapImpl<PdProperty> implements PdPropertyDao {

	@SuppressWarnings("unchecked")
	public List<PdProperty> selectPdPropertyForModelList(PdProperty t) throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForList("selectPdPropertyForModelList", t);
	}

	@SuppressWarnings("unchecked")
	public List<PdProperty> selectPdPropertyListForShopPdContrast(PdProperty t) throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForList("selectPdPropertyListForShopPdContrast", t);
	}

}
