package com.ebiz.mmt.dao;

import java.util.List;

import com.ebiz.mmt.domain.GcxmProj;
import com.ebiz.ssi.dao.EntityDao;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2015-03-23 13:53:08
 */
public interface GcxmProjDao extends EntityDao<GcxmProj> {

	Long selectGcxmProjForUnionCount(GcxmProj t);

	List<GcxmProj> selectGcxmProjForUnionPaginatedList(GcxmProj t);

}
