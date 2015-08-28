package com.ebiz.mmt.service;

import java.util.List;
import com.ebiz.mmt.domain.KonkaParagonShowt;

public interface KonkaParagonShowtService {

	Long createKonkaParagonShowt(KonkaParagonShowt t);

	int modifyKonkaParagonShowt(KonkaParagonShowt t);

	int removeKonkaParagonShowt(KonkaParagonShowt t);

	KonkaParagonShowt getKonkaParagonShowt(KonkaParagonShowt t);

	List<KonkaParagonShowt> getKonkaParagonShowtList(KonkaParagonShowt t);

	Long getKonkaParagonShowtCount(KonkaParagonShowt t);

	List<KonkaParagonShowt> getKonkaParagonShowtPaginatedList(KonkaParagonShowt t);

}
