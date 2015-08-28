package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaFightActivityManagerDao;
import com.ebiz.mmt.domain.KonkaFightActivityManager;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2015-03-16 11:42:03
 */
@Service
public class KonkaFightActivityManagerDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaFightActivityManager> implements KonkaFightActivityManagerDao {

	@Override
	public Long selectKonkaFightActivityManagerMainCount(
			KonkaFightActivityManager entity) {
		
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectKonkaFightActivityManagerMainCount",entity);
	}

	@Override
	public List<KonkaFightActivityManager> selectKonkaFightActivityManagerMainPaginatedList(
			KonkaFightActivityManager entity) {
		
		return super.getSqlMapClientTemplate().queryForList("selectKonkaFightActivityManagerMainPaginatedList",entity);
	}

}
