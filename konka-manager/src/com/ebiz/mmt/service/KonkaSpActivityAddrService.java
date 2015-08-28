package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.KonkaSpActivityAddr;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-12-29 11:19:39
 */
public interface KonkaSpActivityAddrService {

	Long createKonkaSpActivityAddr(KonkaSpActivityAddr t);

	int modifyKonkaSpActivityAddr(KonkaSpActivityAddr t);

	int removeKonkaSpActivityAddr(KonkaSpActivityAddr t);

	KonkaSpActivityAddr getKonkaSpActivityAddr(KonkaSpActivityAddr t);

	List<KonkaSpActivityAddr> getKonkaSpActivityAddrList(KonkaSpActivityAddr t);

	Long getKonkaSpActivityAddrCount(KonkaSpActivityAddr t);

	List<KonkaSpActivityAddr> getKonkaSpActivityAddrPaginatedList(KonkaSpActivityAddr t);

}