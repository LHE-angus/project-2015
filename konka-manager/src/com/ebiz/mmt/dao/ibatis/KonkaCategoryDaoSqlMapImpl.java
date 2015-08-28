package com.ebiz.mmt.dao.ibatis;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaCategoryDao;
import com.ebiz.mmt.domain.KonkaCategory;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-06-26 09:19:45
 */
@Service
public class KonkaCategoryDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaCategory> implements KonkaCategoryDao {

	/**
	 * @author Hu,hao
	 * @version 2013-08-27
	 * @desc 客户大类
	 */
	@SuppressWarnings("unchecked")
	public List<KonkaCategory> selectKonkaCategoryGroupByCCommList(KonkaCategory t) {
		return super.getSqlMapClientTemplate().queryForList("selectKonkaCategoryGroupByCCommList", t);
	}

	/**
	 * 客户大类
	 */
	@Override
	public List<HashMap> selectKonkaCategoryGroupByCCommNew(KonkaCategory t) {
		
		return super.getSqlMapClientTemplate().queryForList("selectKonkaCategoryGroupByCCommNew", t);
	}

	@Override
	public List<HashMap> selectEntityListNew(KonkaCategory t) {
		
		return super.getSqlMapClientTemplate().queryForList("selectKonkaCategoryListNew", t);
	}

	@Override
	public List<HashMap> selectKonkaCategoryMapList(KonkaCategory t) {
		return super.getSqlMapClientTemplate().queryForList("selectKonkaCategoryMapList", t);
	}

	@Override
	public List<KonkaCategory> selectKonkaCategoryAndParIndexList(KonkaCategory t) {
		return super.getSqlMapClientTemplate().queryForList("selectKonkaCategoryAndParIndexList", t);
	}

}
