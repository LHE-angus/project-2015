package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.EcBcompPdRebates;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-09-09 21:36:35
 */
public interface EcBcompPdRebatesService {

	Long createEcBcompPdRebates(EcBcompPdRebates t);

	int modifyEcBcompPdRebates(EcBcompPdRebates t);

	int removeEcBcompPdRebates(EcBcompPdRebates t);

	EcBcompPdRebates getEcBcompPdRebates(EcBcompPdRebates t);

	List<EcBcompPdRebates> getEcBcompPdRebatesList(EcBcompPdRebates t);

	Long getEcBcompPdRebatesCount(EcBcompPdRebates t);

	List<EcBcompPdRebates> getEcBcompPdRebatesPaginatedList(EcBcompPdRebates t);

}