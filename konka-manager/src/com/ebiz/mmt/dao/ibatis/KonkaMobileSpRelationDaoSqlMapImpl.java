package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaMobileSpRelationDao;
import com.ebiz.mmt.domain.KonkaMobileSpRelation;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-04-15 00:43:15
 */
@Service
public class KonkaMobileSpRelationDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaMobileSpRelation> implements
        KonkaMobileSpRelationDao {
	/**
	 * @author Jiang,JiaYong
	 * @version 2013-04-18
	 */
	@SuppressWarnings("unchecked")
	public List<KonkaMobileSpRelation> selectKonkaMobileSpRelationInShopNameList(KonkaMobileSpRelation t)
	        throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForList("selectKonkaMobileSpRelationInShopNameList", t);
	}

	/**
	 * @author Hu,Hao
	 * @version 2013-06-27
	 */
	@SuppressWarnings("unchecked")
	public List<KonkaMobileSpRelation> selectKonkaMobileSpRelationForCxyIdList(KonkaMobileSpRelation t)
	        throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForList("selectKonkaMobileSpRelationForCxyIdList", t);
	}

	@SuppressWarnings("unchecked")
	public List<KonkaMobileSpRelation> selectKonkaMobileSpRelationForCxyList(KonkaMobileSpRelation t)
	        throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForList("selectKonkaMobileSpRelationForCxyList", t);
	}

}
