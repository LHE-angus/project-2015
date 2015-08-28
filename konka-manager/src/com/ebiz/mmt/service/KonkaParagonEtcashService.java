package com.ebiz.mmt.service;

import java.util.HashMap;
import java.util.List;
import com.ebiz.mmt.domain.KonkaParagonEtcash;

public interface KonkaParagonEtcashService {

	Long createKonkaParagonEtcash(KonkaParagonEtcash t);

	int modifyKonkaParagonEtcash(KonkaParagonEtcash t);

	int removeKonkaParagonEtcash(KonkaParagonEtcash t);

	KonkaParagonEtcash getKonkaParagonEtcash(KonkaParagonEtcash t);

	List<KonkaParagonEtcash> getKonkaParagonEtcashList(KonkaParagonEtcash t);

	Long getKonkaParagonEtcashCount(KonkaParagonEtcash t);

	List<KonkaParagonEtcash> getKonkaParagonEtcashPaginatedList(
			KonkaParagonEtcash t);

	List<HashMap<String, String>> selectKonkaParagonEtcashListByOne(
			KonkaParagonEtcash t);
}
