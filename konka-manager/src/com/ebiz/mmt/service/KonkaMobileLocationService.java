package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.KonkaMobileLocation;

/**
 * Code by the Code Generator(Version 2.2)
 *  
 * @author Hui,Gang (mr.huig [at] gmail.com)
 * @datetime 2012-05-15 11:41:41
 */
public interface KonkaMobileLocationService {

	Long createKonkaMobileLocation(KonkaMobileLocation t);

	int modifyKonkaMobileLocation(KonkaMobileLocation t);

	int removeKonkaMobileLocation(KonkaMobileLocation t);

	KonkaMobileLocation getKonkaMobileLocation(KonkaMobileLocation t);

	List<KonkaMobileLocation> getKonkaMobileLocationList(KonkaMobileLocation t);

	Long getKonkaMobileLocationCount(KonkaMobileLocation t);

	List<KonkaMobileLocation> getKonkaMobileLocationPaginatedList(KonkaMobileLocation t);

}