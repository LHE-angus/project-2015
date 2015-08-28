package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.GcxmProjDao;
import com.ebiz.mmt.domain.GcxmProj;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2015-03-23 13:53:08
 */
@Service
public class GcxmProjDaoSqlMapImpl extends EntityDaoSqlMapImpl<GcxmProj> implements GcxmProjDao {

	public Long selectGcxmProjForUnionCount(GcxmProj t) {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectGcxmProjForUnionCount", t);
	}

	@SuppressWarnings("unchecked")
	public List<GcxmProj> selectGcxmProjForUnionPaginatedList(GcxmProj t) {
		return super.getSqlMapClientTemplate().queryForList("selectGcxmProjForUnionPaginatedList", t);
	}

}
