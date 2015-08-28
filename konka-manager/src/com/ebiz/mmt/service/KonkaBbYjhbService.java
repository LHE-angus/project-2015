package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.KonkaBbYjhb;


public interface KonkaBbYjhbService {

	Long createKonkaBbYjhb(KonkaBbYjhb t);

	int modifyKonkaBbYjhb(KonkaBbYjhb t);

	int removeKonkaBbYjhb(KonkaBbYjhb t);

	KonkaBbYjhb getKonkaBbYjhb(KonkaBbYjhb t);

	List<KonkaBbYjhb> getKonkaBbYjhbList(KonkaBbYjhb t);

	Long getKonkaBbYjhbCount(KonkaBbYjhb t);

	List<KonkaBbYjhb> getKonkaBbYjhbPaginatedList(KonkaBbYjhb t);

	void createKonkaBbYjhbBatch(List<KonkaBbYjhb> konkaBbYjhbList);

}