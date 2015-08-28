package com.ebiz.mmt.dao;

import java.util.List;

import com.ebiz.mmt.domain.EcSpecFbUgdetail;
import com.ebiz.ssi.dao.EntityDao;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-06-08 10:31:47
 */
public interface EcSpecFbUgdetailDao extends EntityDao<EcSpecFbUgdetail> {
	List<EcSpecFbUgdetail> selectEcSpecFbUgdetailForTjList(EcSpecFbUgdetail t);

	List<EcSpecFbUgdetail> selectEcSpecFbUgdetailForJlList(EcSpecFbUgdetail t);

	List<EcSpecFbUgdetail> selectEcSpecFbUgdetailForExecList(EcSpecFbUgdetail t);

	Long selectEcSpecFbUgdetailForTjCount(EcSpecFbUgdetail t);
}
