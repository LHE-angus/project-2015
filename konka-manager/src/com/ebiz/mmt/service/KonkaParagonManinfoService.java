package com.ebiz.mmt.service;

import java.util.List;
import com.ebiz.mmt.domain.KonkaParagonManinfo;

public interface KonkaParagonManinfoService {

	Long createKonkaParagonManinfo(KonkaParagonManinfo t);

	int modifyKonkaParagonManinfo(KonkaParagonManinfo t);

	int removeKonkaParagonManinfo(KonkaParagonManinfo t);

	KonkaParagonManinfo getKonkaParagonManinfo(KonkaParagonManinfo t);

	List<KonkaParagonManinfo> getKonkaParagonManinfoList(KonkaParagonManinfo t);

	Long getKonkaParagonManinfoCount(KonkaParagonManinfo t);

	List<KonkaParagonManinfo> getKonkaParagonManinfoPaginatedList(KonkaParagonManinfo t);

}
