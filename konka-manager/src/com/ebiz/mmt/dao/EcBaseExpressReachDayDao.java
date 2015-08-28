package com.ebiz.mmt.dao;

import java.util.List;

import com.ebiz.mmt.domain.EcBaseExpressReachDay;
import com.ebiz.ssi.dao.EntityDao;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-09-24 11:15:06
 */
public interface EcBaseExpressReachDayDao extends EntityDao<EcBaseExpressReachDay> {

	Long selectEcBaseExpressReachDayForStoreNameAndFullNameCount(EcBaseExpressReachDay t);

	List<EcBaseExpressReachDay> selectEcBaseExpressReachDayForStoreNameAndFullNamePaginatedList(EcBaseExpressReachDay t);
}
