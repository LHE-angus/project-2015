package com.ebiz.mmt.service;

import java.util.List;
import com.ebiz.mmt.domain.KonkaMobileRetailRest;

public interface KonkaMobileRetailRestService {

	Long createKonkaMobileRetailRest(KonkaMobileRetailRest t);

	int modifyKonkaMobileRetailRest(KonkaMobileRetailRest t);

	int removeKonkaMobileRetailRest(KonkaMobileRetailRest t);

	KonkaMobileRetailRest getKonkaMobileRetailRest(KonkaMobileRetailRest t);

	List<KonkaMobileRetailRest> getKonkaMobileRetailRestList(KonkaMobileRetailRest t);

	Long getKonkaMobileRetailRestCount(KonkaMobileRetailRest t);
	
	List<KonkaMobileRetailRest> getKonkaMobileRetailRestPaginatedList(KonkaMobileRetailRest t);
	
	Long getRetailRestStatisticsCount(KonkaMobileRetailRest t);

	List<KonkaMobileRetailRest> getRetailRestStatisticsPaginatedList(KonkaMobileRetailRest t);

}
