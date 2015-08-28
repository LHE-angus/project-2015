package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.GcxmProj;
import com.ebiz.mmt.domain.GcxmProjOffer;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2015-03-23 13:53:09
 */
public interface GcxmProjOfferService {

	Long createGcxmProjOffer(GcxmProjOffer t);

	int modifyGcxmProjOffer(GcxmProjOffer t);

	int modifyGcxmProjOfferForCh(GcxmProjOffer t);

	int removeGcxmProjOffer(GcxmProjOffer t);

	GcxmProjOffer getGcxmProjOffer(GcxmProjOffer t);

	List<GcxmProjOffer> getGcxmProjOfferList(GcxmProjOffer t);

	Long getGcxmProjOfferCount(GcxmProjOffer t);

	List<GcxmProjOffer> getGcxmProjOfferPaginatedList(GcxmProjOffer t);

}