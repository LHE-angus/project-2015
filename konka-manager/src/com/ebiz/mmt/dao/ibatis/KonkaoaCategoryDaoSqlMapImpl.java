package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.ebiz.mmt.dao.KonkaoaCategoryDao;
import com.ebiz.mmt.domain.KonkaoaCategory;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * @author Xu,ZhengYang
 * @date 2010-11-14 10:33:45
 */
@Repository
public class KonkaoaCategoryDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaoaCategory> implements KonkaoaCategoryDao {

	/**
	 * @author Hui,Gang
	 * @version Build 2010-12-13
	 */
	@SuppressWarnings("unchecked")
	public List<KonkaoaCategory> selectKonkaoaCategoryListForFiles(KonkaoaCategory category) throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForList("selectKonkaoaCategoryListForFiles", category);
	}

}
