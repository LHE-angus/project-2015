package com.ebiz.mmt.dao;

import java.util.List;

import com.ebiz.mmt.domain.EcBcompPdUpNew;
import com.ebiz.ssi.dao.EntityDao;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-12-05 12:05:18
 */
public interface EcBcompPdUpNewDao extends EntityDao<EcBcompPdUpNew> {

	Long selectEcBcompPdUpNewForDetailsCount(EcBcompPdUpNew t);

	List<EcBcompPdUpNew> selectEcBcompPdUpNewForDetailsPaginatedList(EcBcompPdUpNew t);
}
