package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaR3DeptStoreDao;
import com.ebiz.mmt.domain.KonkaR3DeptStore;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-09-24 11:31:51
 */
@Service
public class KonkaR3DeptStoreDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaR3DeptStore> implements KonkaR3DeptStoreDao {

	/**
	 * @author Hu,Hao
	 * @version 2013-09-24
	 * @desc 分公司查询 
	 */
	@SuppressWarnings("unchecked")
	public List<KonkaR3DeptStore> selectKonkaR3DeptStoreForFgsNameList(KonkaR3DeptStore t){
		return super.getSqlMapClientTemplate().queryForList("selectKonkaR3DeptStoreForFgsNameList", t);
	}
	
	/**
	 * @author Hu,Hao
	 * @version 2013-09-24
	 * @desc 工厂查询 
	 */
	@SuppressWarnings("unchecked")
	public List<KonkaR3DeptStore> selectKonkaR3DeptStoreForFacSnList(KonkaR3DeptStore t){
		return super.getSqlMapClientTemplate().queryForList("selectKonkaR3DeptStoreForFacSnList", t);
	}
	
}
