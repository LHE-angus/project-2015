package com.ebiz.mmt.service;

import java.util.List;
import com.ebiz.mmt.domain.KonkaParagonShowmanre;

public interface KonkaParagonShowmanreService {

	Long createKonkaParagonShowmanre(KonkaParagonShowmanre t);

	int modifyKonkaParagonShowmanre(KonkaParagonShowmanre t);

	int removeKonkaParagonShowmanre(KonkaParagonShowmanre t);

	KonkaParagonShowmanre getKonkaParagonShowmanre(KonkaParagonShowmanre t);

	List<KonkaParagonShowmanre> getKonkaParagonShowmanreList(KonkaParagonShowmanre t);

	Long getKonkaParagonShowmanreCount(KonkaParagonShowmanre t);

	List<KonkaParagonShowmanre> getKonkaParagonShowmanrePaginatedList(KonkaParagonShowmanre t);

	KonkaParagonShowmanre getKonkaParagonShowmanreForEdit(KonkaParagonShowmanre t);
	
	Long getKonkaParagonShowmanreCountForShowOut(KonkaParagonShowmanre t);

	List<KonkaParagonShowmanre> getKonkaParagonShowmanrePaginatedListForShowOut(KonkaParagonShowmanre t);
	
}
