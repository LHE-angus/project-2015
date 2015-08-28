package com.ebiz.mmt.dao;

import java.util.List;

import com.ebiz.mmt.domain.GcxmProjCompet;
import com.ebiz.ssi.dao.EntityDao;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2015-03-23 13:53:08
 */
public interface GcxmProjCompetDao extends EntityDao<GcxmProjCompet> {

	Long selectGcxmProjCompetForProjCount(GcxmProjCompet t);

	List<GcxmProjCompet> selectGcxmProjCompetForProjPaginatedList(GcxmProjCompet t);
}
