package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaR3ShopTaskDao;
import com.ebiz.mmt.domain.KonkaR3ShopTask;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-12-05 09:51:13
 */
@Service
public class KonkaR3ShopTaskDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaR3ShopTask> implements KonkaR3ShopTaskDao {

	/**
	 * @author Hu,Hap
	 * @version 2013-12-06
	 */
	@SuppressWarnings("unchecked")
	public List<KonkaR3ShopTask> selectKonkaR3ShopTaskToDeptUserPaginatedList(KonkaR3ShopTask t) {
		return super.getSqlMapClientTemplate().queryForList("selectKonkaR3ShopTaskToDeptUserPaginatedList", t);
	}

	/**
	 * @author Hu,Hap
	 * @version 2013-12-06
	 */
	public Long selectKonkaR3ShopTaskToDeptUserCount(KonkaR3ShopTask t) {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectKonkaR3ShopTaskToDeptUserCount", t);
	}
}
