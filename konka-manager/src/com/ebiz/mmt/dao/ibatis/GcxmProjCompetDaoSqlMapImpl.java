package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.GcxmProjCompetDao;
import com.ebiz.mmt.domain.GcxmProjCompet;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2015-03-23 13:53:09
 */
@Service
public class GcxmProjCompetDaoSqlMapImpl extends EntityDaoSqlMapImpl<GcxmProjCompet> implements GcxmProjCompetDao {

	public Long selectGcxmProjCompetForProjCount(GcxmProjCompet t) {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectGcxmProjCompetForProjCount", t);
	}

	@SuppressWarnings("unchecked")
	public List<GcxmProjCompet> selectGcxmProjCompetForProjPaginatedList(GcxmProjCompet t) {
		return super.getSqlMapClientTemplate().queryForList("selectGcxmProjCompetForProjPaginatedList", t);
	}

}
