package com.ebiz.mmt.service;

import java.util.List;
import com.ebiz.mmt.domain.KonkaMobileSailsReturn;

public interface KonkaMobileSailsReturnService {

	Long createKonkaMobileSailsReturn(KonkaMobileSailsReturn t);

	int modifyKonkaMobileSailsReturn(KonkaMobileSailsReturn t);

	int removeKonkaMobileSailsReturn(KonkaMobileSailsReturn t);

	KonkaMobileSailsReturn getKonkaMobileSailsReturn(KonkaMobileSailsReturn t);

	List<KonkaMobileSailsReturn> getKonkaMobileSailsReturnList(KonkaMobileSailsReturn t);

	Long getKonkaMobileSailsReturnCount(KonkaMobileSailsReturn t);

	List<KonkaMobileSailsReturn> getKonkaMobileSailsReturnPaginatedList(KonkaMobileSailsReturn t);

	Long createKonkaMobileSailsReturnForHis(KonkaMobileSailsReturn entity);

}
