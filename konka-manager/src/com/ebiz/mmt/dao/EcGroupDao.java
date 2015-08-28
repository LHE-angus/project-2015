package com.ebiz.mmt.dao;

import java.util.List;

import com.ebiz.mmt.domain.EcGroup;
import com.ebiz.ssi.dao.EntityDao;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-12-08 14:09:38
 */
public interface EcGroupDao extends EntityDao<EcGroup> {
	List<EcGroup> selectEcGroupForTreePaginatedList(EcGroup t);

	List<EcGroup> selectEcGroupForTreeNameList(EcGroup t);
}
