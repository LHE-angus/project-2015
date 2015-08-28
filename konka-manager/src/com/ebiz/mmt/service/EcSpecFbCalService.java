package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.EcSpecFbCal;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-06-08 10:31:47
 */
public interface EcSpecFbCalService {

	Long createEcSpecFbCal(EcSpecFbCal t);

	int modifyEcSpecFbCal(EcSpecFbCal t);

	int removeEcSpecFbCal(EcSpecFbCal t);

	EcSpecFbCal getEcSpecFbCal(EcSpecFbCal t);

	List<EcSpecFbCal> getEcSpecFbCalList(EcSpecFbCal t);

	Long getEcSpecFbCalCount(EcSpecFbCal t);

	List<EcSpecFbCal> getEcSpecFbCalPaginatedList(EcSpecFbCal t);

	List<EcSpecFbCal> getEcSpecFbCalForTjList(EcSpecFbCal t);

}