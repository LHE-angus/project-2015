package com.ebiz.mmt.dao;

import java.util.List;

import com.ebiz.mmt.domain.EcSpecFbCal;
import com.ebiz.ssi.dao.EntityDao;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-06-08 10:31:47
 */
public interface EcSpecFbCalDao extends EntityDao<EcSpecFbCal> {
	
	List<EcSpecFbCal> selectEcSpecFbCalForTjList(EcSpecFbCal t);
	
}
