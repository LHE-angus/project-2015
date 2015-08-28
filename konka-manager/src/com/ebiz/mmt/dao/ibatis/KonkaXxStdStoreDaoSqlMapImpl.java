package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaXxStdStoreDao;
import com.ebiz.mmt.domain.KonkaXxStdStore;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2012-01-11 10:56:03
 */
@Service
public class KonkaXxStdStoreDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaXxStdStore> implements KonkaXxStdStoreDao {

	/**
	 * @author Li,Yuan
	 * @version 2012-01-12
	 */
	@SuppressWarnings("unchecked")
	public List<KonkaXxStdStore> selectKonkaXxFactoryIdList(KonkaXxStdStore t) {
		return super.getSqlMapClientTemplate().queryForList("selectKonkaXxFactoryIdList", t);
	}

	/**
	 * @author Li,Yuan
	 * @version 2012-01-12
	 */
	@SuppressWarnings("unchecked")
	public List<KonkaXxStdStore> selectKonkaXxStoreIdList(KonkaXxStdStore t) {
		return super.getSqlMapClientTemplate().queryForList("selectKonkaXxStoreIdList", t);
	}
}
